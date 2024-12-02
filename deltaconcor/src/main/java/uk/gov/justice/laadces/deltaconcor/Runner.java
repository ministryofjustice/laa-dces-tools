package uk.gov.justice.laadces.deltaconcor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.deltaconcor.generated.CONTRIBUTIONS;
import uk.gov.justice.laadces.deltaconcor.report.Change;
import uk.gov.justice.laadces.deltaconcor.repository.ConcorContribution;
import uk.gov.justice.laadces.deltaconcor.repository.ConcorContributionRepository;
import uk.gov.justice.laadces.deltaconcor.repository.ConcorTemplateRepository;
import uk.gov.justice.laadces.deltaconcor.service.ChangeService;
import uk.gov.justice.laadces.deltaconcor.service.CsvOutputService;
import uk.gov.justice.laadces.deltaconcor.service.XMLTransformService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ApplicationRunner class that examines the data in the database.
 */
@Component
@Profile("!test")
@RequiredArgsConstructor
@Slf4j
class Runner implements ApplicationRunner {
    private static final int BATCH_SIZE = 1000;

    // Constructor-wired Spring components
    private final ConcorContributionRepository concorRepository;
    private final ConcorTemplateRepository templRepository;
    private final XMLTransformService xmlTransformService;
    private final ChangeService changeService;
    private final CsvOutputService csvoutput;

    private final Set<Long> maatIdsToBackfill = new TreeSet<>();
    private final Map<Long, CONTRIBUTIONS> maatIdToContribution = new TreeMap<>();
    private final ArrayList<Change> newChanges = new ArrayList<>();
    private final ArrayList<Change> updChanges = new ArrayList<>();

    private int countRowsWithPredecessorsInDateRange = 0;

    /**
     * Main runner method, just iterates over the database table, calling #examineConcorContribution() for each row.
     * Handles limiting the data size and paging efficiently, with some progress-logging to the application log.
     *
     * @param args ApplicationArguments ignored.
     * @throws Exception if an error occurs.
     */
    @Override
    public void run(final ApplicationArguments args) throws Exception {
        final LocalDate startDate = LocalDate.of(2024, 10, 1); // inclusive
        final LocalDate endDate = LocalDate.of(2024, 11, 1); // exclusive
        final int nDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
        log.info("run: Processing {} days in period [{}, {})", nDays, startDate, endDate);

        reset(nDays);
        for (int i = 0; i < nDays; ++i) {
            newChanges.add(new Change(startDate.plusDays(i).toString()));
            updChanges.add(new Change(startDate.plusDays(i).toString()));
        }
        log.info("run: Counts reset, now finding ids to process");

        final long startId = templRepository.minIdAfterOrEquals(startDate);
        final long endId = templRepository.maxIdBefore(endDate);
        log.info("run: Processing concor_contributions rows with {} <= id <= {}", startId, endId);

        findMaatIdsToBackfill(startId, endId);
        log.info("run: {} new maatIds, {} maatIds to back-fill, {} contributions with predecessor in date range",
                maatIdToContribution.size(), maatIdsToBackfill.size(), countRowsWithPredecessorsInDateRange);

        if (!maatIdsToBackfill.isEmpty()) {
            final long lastId = slowBackfillMaatIds(startId - 1);
            log.info("run: {} maatIds left to back-fill after slow walk back {} >= id >= {}", maatIdsToBackfill.size(), startId - 1, lastId);
            if (!maatIdsToBackfill.isEmpty()) {
                final long lastId2 = fastBackfillMaatIds(lastId - 1);
                log.info("run: {} maatIds left to back-fill after fast walk back {} >= id >= {}", maatIdsToBackfill.size(), lastId - 1, lastId2);
            }
        }
        log.info("run: {} maatId predecessors found", maatIdToContribution.size());

        generateDailyCounts(startDate, startId, endId);
        log.info("run: Counts generated, now outputting CSV files");

        csvoutput.writeChanges("/tmp/deltaconcor-new.csv", newChanges);
        csvoutput.writeChanges("/tmp/deltaconcor-upd.csv", updChanges);
        log.info("run: Done");
    }

    private void reset(final int nDays) {
        maatIdsToBackfill.clear();
        maatIdToContribution.clear();
        newChanges.clear();
        newChanges.trimToSize();
        newChanges.ensureCapacity(nDays);
        updChanges.clear();
        updChanges.trimToSize();
        updChanges.ensureCapacity(nDays);
    }

    private long findMaatIdsToBackfill(final long startId, final long endId) {
        return visitForwards(startId, endId, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("findMaatIdsToBackfill: Failed to transform XML for concorContribution {}, maatId {}", concorContribution.id(), maatId);
            } else if (dto.getFlag() == null) {
                log.warn("findMaatIdsToBackfill: No flag for concorContribution {}, maatId {}", concorContribution.id(), maatId);
            } else if (dto.getFlag().equalsIgnoreCase("new")) {
                maatIdToContribution.put(maatId, dto);
            } else if (dto.getFlag().equalsIgnoreCase("update")) {
                if (!maatIdToContribution.containsKey(maatId)) {
                    maatIdsToBackfill.add(maatId);
                } else {
                    ++countRowsWithPredecessorsInDateRange;
                }
            } else {
                log.warn("findMaatIdsToBackfill: Unknown flag {} for concorContribution {}, maatId {}", dto.getFlag(), concorContribution.id(), maatId);
            }
            return true;
        });
    }

    private long slowBackfillMaatIds(final long startId) {
        final AtomicInteger countMisses = new AtomicInteger(); // needed for 'effectively final' requirement in lambda.
        return slowVisitBackwards(startId, 0, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            countMisses.incrementAndGet();
            if (dto == null) {
                log.warn("slowBackfillMaatIds: Failed to transform XML for concorContribution {}, maatId {}", concorContribution.id(), maatId);
            } else  {
                if (maatIdsToBackfill.contains(maatId)) {
                    maatIdToContribution.put(maatId, dto);
                    maatIdsToBackfill.remove(maatId);
                    if (maatIdsToBackfill.size() % 10 == 0) {
                        log.info("slowBackfillMaatIds: {} maatIds left to back-fill ({} misses since last)", maatIdsToBackfill.size(), countMisses.get());
                    }
                    countMisses.set(0);
                }
            }
            // switch to fast walk-back after 200 misses in a row (when there are 1000 or fewer maatIds to back-fill).
            return !(maatIdsToBackfill.isEmpty() || (maatIdsToBackfill.size() <= 1000 && countMisses.get() >= 200));
        });
    }

    private long fastBackfillMaatIds(final long startId) {
        return fastVisitBackwards(startId, 0, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("fastBackfillMaatIds: Failed to transform XML for concorContribution {}, maatId {}", concorContribution.id(), maatId);
            } else  {
                if (maatIdsToBackfill.contains(maatId)) {
                    maatIdToContribution.put(maatId, dto);
                    maatIdsToBackfill.remove(maatId);
                    if (maatIdsToBackfill.size() % 10 == 0) {
                        log.info("fastBackfillMaatIds: {} maatIds left to back-fill", maatIdsToBackfill.size());
                    }
                }
            }
            return !maatIdsToBackfill.isEmpty();
        });
    }

    private long generateDailyCounts(LocalDate startDate, long startId, long endId) {
        return visitForwards(startId, endId, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("generateDailyCounts: Failed to transform XML for concorContribution {}, maatId {}", concorContribution.id(), maatId);
            } else {
                final int dayIndex = (int) ChronoUnit.DAYS.between(startDate, concorContribution.dateCreated());
                if (dayIndex < 0 || dayIndex >= newChanges.size()) {
                    log.warn("generateDailyCounts: {} out of range for concorContribution {}, maatId {}", concorContribution.dateCreated(), concorContribution.id(), maatId);
                    return true;
                }
                if (dto.getFlag().equalsIgnoreCase("new")) {
                    final Change counts = newChanges.get(dayIndex);
                    counts.setSentRecords(counts.getSentRecords() + 1);
                    if (changeService.compare(null, dto, counts)) {
                        counts.setChangedRecords(counts.getChangedRecords() + 1);
                    }
                } else if (dto.getFlag().equalsIgnoreCase("update")) {
                    final Change counts = updChanges.get(dayIndex);
                    counts.setSentRecords(counts.getSentRecords() + 1);
                    final var prev = maatIdToContribution.get(maatId);
                    if (changeService.compare(prev, dto, counts)) {
                        counts.setChangedRecords(counts.getChangedRecords() + 1);
                    }
                } else {
                    log.warn("generateDailyCounts: Unknown flag {} for concorContribution {}, maatId {}", dto.getFlag(), concorContribution.id(), maatId);
                }
                maatIdToContribution.put(maatId, dto);
            }
            return true;
        });
    }

    /**
     * Uses an exhaustive query to visit forwards through the ConcorContribution table.
     *
     * @param startId start ID to walk forward from
     * @param endId end ID to finish walking forward until
     * @param visitor the RecordVisitor to call for each record visited
     * @return the ID of the last record visited
     */
    private long visitForwards(final long startId, final long endId, final RecordVisitor visitor) {
        assert startId <= endId;

        long nextLowId = startId;
        Slice<ConcorContribution> slice;
        List<ConcorContribution> content;
        int contentLen;
        long sliceLowId, sliceHighId;

        do {
            slice = concorRepository.findByIdBetweenAndStatusOrderById(
                    nextLowId, endId, "SENT", Pageable.ofSize(BATCH_SIZE));
            content = slice.getContent();
            contentLen = content.size();
            sliceLowId = contentLen > 0 ? content.get(0).id() : nextLowId;
            sliceHighId = contentLen > 0 ? content.get(contentLen - 1).id() : endId;
            nextLowId = sliceHighId + 1;
            log.info("visitForwards: {} rows with {} <= id <= {}", contentLen, sliceLowId, sliceHighId);
            for (final ConcorContribution concorContribution : content) {
                if (!visitor.visit(concorContribution)) {
                    return concorContribution.id();
                }
            }
        } while (slice.hasNext() && contentLen > 0 && nextLowId <= endId);
        return endId;
    }

    /**
     * Uses a slower query to visit backwards through the ConcorContribution table; however this method works even if
     * there are more than 1000 maatIds to back-fill.
     *
     * @param startId start ID to walk back from
     * @param endId end ID to finish walking back at
     * @param visitor the RecordVisitor to call for each record visited
     * @return the ID of the last record visited
     */
    private long slowVisitBackwards(final long startId, final long endId, final RecordVisitor visitor) {
        assert startId >= endId;

        long nextHighId = startId;
        Slice<ConcorContribution> slice;
        List<ConcorContribution> content;
        int contentLen;
        long sliceLowId, sliceHighId;

        do {
            slice = concorRepository.findByIdBetweenAndStatusOrderByIdDesc(
                    endId, nextHighId, "SENT", Pageable.ofSize(BATCH_SIZE));
            content = slice.getContent();
            contentLen = content.size();
            sliceHighId = contentLen > 0 ? content.get(0).id() : nextHighId;
            sliceLowId = contentLen > 0 ? content.get(contentLen - 1).id() : endId;
            nextHighId = sliceLowId - 1;
            log.info("slowVisitBackwards: {} rows with {} >= id >= {}", contentLen, sliceHighId, sliceLowId);
            for (final ConcorContribution concorContribution : content) {
                if (!visitor.visit(concorContribution)) {
                    return concorContribution.id();
                }
            }
        } while (slice.hasNext() && contentLen > 0 && nextHighId >= endId);
        return endId;
    }

    /**
     * Uses a faster query to visit backwards through the ConcorContribution table; however it only works if there are
     * less than 1000 maatIds to back-fill.
     *
     * @param startId start ID to walk back from
     * @param endId end ID to finish walking back at
     * @param visitor the RecordVisitor to call for each record visited
     * @return the ID of the last record visited
     */
    private long fastVisitBackwards(final long startId, final long endId, final RecordVisitor visitor) {
        assert startId >= endId;

        long nextHighId = startId;
        Slice<ConcorContribution> slice;
        List<ConcorContribution> content;
        int contentLen;
        long sliceLowId, sliceHighId;

        do {
            slice = concorRepository.findByIdBetweenAndStatusAndRepIdInOrderByIdDesc(
                    endId, nextHighId, "SENT", maatIdsToBackfill, Pageable.ofSize(BATCH_SIZE));
            content = slice.getContent();
            contentLen = content.size();
            sliceHighId = contentLen > 0 ? content.get(0).id() : nextHighId;
            sliceLowId = contentLen > 0 ? content.get(contentLen - 1).id() : endId;
            nextHighId = sliceLowId - 1;
            log.info("fastVisitBackwards: {} rows with {} >= id >= {}", contentLen, sliceHighId, sliceLowId);
            for (final ConcorContribution concorContribution : content) {
                if (!visitor.visit(concorContribution)) {
                    return concorContribution.id();
                }
            }
        } while (slice.hasNext() && contentLen > 0 && nextHighId >= endId);
        return endId;
    }

    /**
     * Functional interface for visiting a set of ConcorContribution records (forwards or backwards).
     */
    @FunctionalInterface
    interface RecordVisitor {
        /**
         * Visits a ConcorContribution record.
         *
         * @param concorContribution the record to visit.
         * @return true to continue visiting, false to stop.
         */
        boolean visit(ConcorContribution concorContribution);
    }
}
