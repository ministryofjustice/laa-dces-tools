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
import uk.gov.justice.laadces.deltaconcor.report.ChangeLog;
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

/**
 * ApplicationRunner class that reports on the data in the database.
 * The `Runner#run(ApplicationArguments)` method is basically the `#main(String[])` of this application.
 */
@Component
@Profile("!test")
@RequiredArgsConstructor
@Slf4j
class Runner implements ApplicationRunner {
    private static final LocalDate START_DATE = LocalDate.of(2024, 6, 1); // inclusive
    private static final LocalDate END_DATE = LocalDate.of(2024, 7, 1); // exclusive

    private static final int BATCH_SIZE = 1000;

    // Constructor-wired Spring components
    private final ConcorContributionRepository concorRepository;
    private final ConcorTemplateRepository templRepository;
    private final XMLTransformService xmlTransformService;
    private final ChangeService changeService;
    private final CsvOutputService csvoutput;

    private final Set<Long> maatIdsToBackFill = new TreeSet<>();
    private final Map<Long, CONTRIBUTIONS> maatIdToContribution = new TreeMap<>();
    private final ArrayList<Change> newChanges = new ArrayList<>();
    private final ArrayList<Change> updChanges = new ArrayList<>();
    private final ArrayList<ChangeLog> changeLogs = new ArrayList<>();

    private int countRowsInDateRange = 0;
    private int countRows1 = 0;
    private int countRows2 = 0;

    /**
     * Main runner method, just iterates over the database table, calling #examineConcorContribution() for each row.
     * Handles limiting the data size and paging efficiently, with some progress-logging to the application log.
     *
     * @param args ApplicationArguments ignored.
     */
    @Override
    public void run(final ApplicationArguments args) {
        reset(START_DATE, END_DATE);

        log.info("run: Locating concorId range to process");
        final long startId = templRepository.minIdAfterOrEquals(START_DATE);
        final long endId = templRepository.maxIdBefore(END_DATE);

        log.info("run: Finding maatIds in range {} <= concorId <= {}", startId, endId);
        findMaatIdsToBackFill(startId, endId);
        log.info("run: Found {} new maatIds, {} maatIds to back-fill, {} concorIds in range, {} concorIds #1, {} concorIds #2",
                maatIdToContribution.size(), maatIdsToBackFill.size(), countRowsInDateRange, countRows1, countRows2);

        if (!maatIdsToBackFill.isEmpty()) {
            final long lastId = slowBackFillMaatIds(startId - 1L);
            log.info("run: {} maatIds left to back-fill after slow walk back {} >= concorId >= {}", maatIdsToBackFill.size(), startId - 1L, lastId);
            if (!maatIdsToBackFill.isEmpty()) {
                final long lastId2 = fastBackFillMaatIds(lastId - 1L);
                log.info("run: {} maatIds left to back-fill after fast walk back {} >= concorId >= {}", maatIdsToBackFill.size(), lastId - 1L, lastId2);
            }
        }
        log.info("run: {} maatId predecessors found", maatIdToContribution.size());

        generateDailyCounts(START_DATE, startId, endId);
        log.info("run: Counts generated, now outputting CSV files");

        csvoutput.writeChanges("/tmp/deltaconcor-new.csv", newChanges);
        csvoutput.writeChanges("/tmp/deltaconcor-upd.csv", updChanges);
        csvoutput.writeChangeLogs("/tmp/deltaconcor-log.csv", changeLogs);
        log.info("run: Done");
    }

    private void reset(final LocalDate startDate, final LocalDate endDate) {
        final int nDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
        log.info("reset: {} days in period [{}, {})", nDays, startDate, endDate);
        maatIdsToBackFill.clear();
        maatIdToContribution.clear();
        newChanges.clear();
        updChanges.clear();
        newChanges.trimToSize();
        updChanges.trimToSize();
        newChanges.ensureCapacity(nDays);
        updChanges.ensureCapacity(nDays);
        for (int i = 0; i < nDays; ++i) {
            newChanges.add(new Change(startDate.plusDays(i).toString()));
            updChanges.add(new Change(startDate.plusDays(i).toString()));
        }
        changeLogs.clear();
        changeLogs.trimToSize();
        changeLogs.ensureCapacity(nDays); // will actually be a large multiple of nDays
    }

    private long findMaatIdsToBackFill(final long startId, final long endId) {
        return visitForwards(startId, endId, concorContribution -> {
            ++countRowsInDateRange;
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("findMaatIdsToBackFill: Failed to transform XML for concorId {}, maatId {}", concorContribution.id(), maatId);
            } else if (dto.getFlag() == null) {
                log.warn("findMaatIdsToBackFill: No flag for concorId {}, maatId {}", concorContribution.id(), maatId);
            } else if (dto.getFlag().equalsIgnoreCase("new")) {
                if (maatIdToContribution.containsKey(maatId)) {
                    log.warn("findMaatIdsToBackFill: More than one 'new' for concorId {}, maatId {}", concorContribution.id(), maatId);
                } else {
                    maatIdToContribution.put(maatId, dto);
                }
            } else if (dto.getFlag().equalsIgnoreCase("update")) {
                if (!maatIdToContribution.containsKey(maatId)) {
                    if (!maatIdsToBackFill.contains(maatId)) {
                        maatIdsToBackFill.add(maatId);
                    } else {
                        ++countRows1;
                    }
                } else {
                    ++countRows2;
                }
            } else {
                log.warn("findMaatIdsToBackFill: Unknown flag {} for concorId {}, maatId {}", dto.getFlag(), concorContribution.id(), maatId);
            }
            return true;
        });
    }

    private long slowBackFillMaatIds(final long startId) {
        return slowVisitBackwards(startId, 0L, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("slowBackFillMaatIds: Failed to transform XML for concorId {}, maatId {}", concorContribution.id(), maatId);
            } else  {
                if (maatIdsToBackFill.contains(maatId)) {
                    maatIdToContribution.put(maatId, dto);
                    maatIdsToBackFill.remove(maatId);
                    if (maatIdsToBackFill.size() % 100 == 0) {
                        log.info("slowBackFillMaatIds: {} maatIds left to back-fill", maatIdsToBackFill.size());
                    }
                }
            }
            // switch to fast walk-back as soon as there are 990 or fewer maatIds to back-fill.
            return !(maatIdsToBackFill.size() <= 990);
        });
    }

    private long fastBackFillMaatIds(final long startId) {
        return fastVisitBackwards(startId, 0L, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("fastBackFillMaatIds: Failed to transform XML for concorId {}, maatId {}", concorContribution.id(), maatId);
            } else  {
                if (maatIdsToBackFill.contains(maatId)) {
                    maatIdToContribution.put(maatId, dto);
                    maatIdsToBackFill.remove(maatId);
                    if (maatIdsToBackFill.size() % 10 == 0) {
                        log.info("fastBackFillMaatIds: {} maatIds left to back-fill", maatIdsToBackFill.size());
                    }
                }
            }
            return !maatIdsToBackFill.isEmpty();
        });
    }

    private long generateDailyCounts(final LocalDate startDate, final long startId, final long endId) {
        return visitForwards(startId, endId, concorContribution -> {
            final long maatId = concorContribution.repId();
            final CONTRIBUTIONS dto = xmlTransformService.fromXML(concorContribution.fullXml());
            if (dto == null) {
                log.warn("generateDailyCounts: Failed to transform XML for concorId {}, maatId {}", concorContribution.id(), maatId);
            } else {
                final int dayIndex = (int) ChronoUnit.DAYS.between(startDate, concorContribution.dateCreated());
                if (dayIndex < 0 || dayIndex >= newChanges.size()) {
                    log.warn("generateDailyCounts: {} out of range for concorId {}, maatId {}", concorContribution.dateCreated(), concorContribution.id(), maatId);
                    return true;
                }
                if (dto.getFlag().equalsIgnoreCase("new")) {
                    final ChangeLog log = new ChangeLog(concorContribution.dateCreated().toString());
                    log.setMaatId(maatId);
                    log.setNext_concorContributionId(concorContribution.id());
                    log.setSentRecords(1);
                    if (changeService.compare(null, dto, log)) {
                        log.setChangedRecords(1);
                    }
                    changeLogs.add(log);
                    changeService.addTo(newChanges.get(dayIndex), log);
                } else if (dto.getFlag().equalsIgnoreCase("update")) {
                    final var prev = maatIdToContribution.get(maatId);
                    final ChangeLog log = new ChangeLog(concorContribution.dateCreated().toString());
                    log.setMaatId(maatId);
                    log.setNext_concorContributionId(concorContribution.id());
                    log.setPrev_concorContributionId(prev != null ? prev.getId() : -1L);
                    log.setSentRecords(1);
                    if (changeService.compare(prev, dto, log)) {
                        log.setChangedRecords(1);
                    }
                    changeLogs.add(log);
                    changeService.addTo(updChanges.get(dayIndex), log);
                } else {
                    log.warn("generateDailyCounts: Unknown flag {} for concorId {}, maatId {}", dto.getFlag(), concorContribution.id(), maatId);
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
            log.info("visitForwards: {} rows with {} <= concorId <= {}", contentLen, sliceLowId, sliceHighId);
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
            nextHighId = sliceLowId - 1L;
            log.info("slowVisitBackwards: {} rows with {} >= concorId >= {}", contentLen, sliceHighId, sliceLowId);
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
                    endId, nextHighId, "SENT", maatIdsToBackFill, Pageable.ofSize(BATCH_SIZE));
            content = slice.getContent();
            contentLen = content.size();
            sliceHighId = contentLen > 0 ? content.get(0).id() : nextHighId;
            sliceLowId = contentLen > 0 ? content.get(contentLen - 1).id() : endId;
            nextHighId = sliceLowId - 1L;
            log.info("fastVisitBackwards: {} rows with {} >= concorId >= {}", contentLen, sliceHighId, sliceLowId);
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
