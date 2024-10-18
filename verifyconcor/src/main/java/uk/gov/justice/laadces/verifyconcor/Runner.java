package uk.gov.justice.laadces.verifyconcor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;
import uk.gov.justice.laadces.verifyconcor.repository.AnomalyRepository;
import uk.gov.justice.laadces.verifyconcor.repository.ConcorContribution;
import uk.gov.justice.laadces.verifyconcor.repository.ConcorContributionRepository;
import uk.gov.justice.laadces.verifyconcor.repository.StatisticRepository;
import uk.gov.justice.laadces.verifyconcor.service.JSONTransformService;
import uk.gov.justice.laadces.verifyconcor.service.JSONValidationService;
import uk.gov.justice.laadces.verifyconcor.service.XMLComparisonService;
import uk.gov.justice.laadces.verifyconcor.service.XMLTransformService;

import java.util.List;

/**
 * ApplicationRunner class that examines the data in the database.
 */
@Component
@Profile("!test")
@RequiredArgsConstructor
@Slf4j
class Runner implements ApplicationRunner {
    private final AnomalyRepository anomalyRepository;
    private final ConcorContributionRepository concorRepository;
    private final StatisticRepository statisticRepository;

    private final JSONTransformService jsonTransformService;
    private final JSONValidationService jsonValidationService;
    private final XMLComparisonService xmlComparisonService;
    private final XMLTransformService xmlTransformService;

    private static final long LOW_ID = 298_739_928L; // First rec of 2020
    private static final long HIGH_ID = 499_999_999L;
    private static final int BATCH_SIZE = 1000;

    /**
     * Main runner method, just iterates over the database table, calling #examineConcorContribution() for each row.
     * Handles limiting the data size and paging efficiently, with some progress-logging to the application log.
     *
     * @param args ApplicationArguments ignored.
     * @throws Exception if an error occurs.
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        final int recs = concorRepository.countByIdBetween(LOW_ID, HIGH_ID);
        log.info("There are {} rows with {} <= id <= {}", recs, LOW_ID, HIGH_ID);
        log.info("-----BEGIN-----");
        final long startTime = System.currentTimeMillis();

        long nextLowId = LOW_ID;
        int recsLoaded = 0;
        Slice<ConcorContribution> slice;
        List<ConcorContribution> content;
        int contentLen;
        long sliceLowId, sliceHighId;

        do {
            slice = concorRepository.findByIdBetweenOrderById(nextLowId, HIGH_ID, Pageable.ofSize(BATCH_SIZE));
            content = slice.getContent();
            contentLen = content.size();
            recsLoaded += contentLen;
            sliceLowId = contentLen > 0 ? content.get(0).id() : nextLowId;
            sliceHighId = contentLen > 0 ? content.get(contentLen - 1).id() : HIGH_ID;
            nextLowId = sliceHighId + 1;
            log.info("Loaded {} rows with {} <= id <= {}. {} / {} rows loaded, {} ms", contentLen, sliceLowId, sliceHighId, recsLoaded, recs, System.currentTimeMillis() - startTime);
            content.forEach(this::examineConcorContribution);
        } while (slice.hasNext() && contentLen > 0 && nextLowId <= HIGH_ID);

        final long timeTaken = System.currentTimeMillis() - startTime;
        log.info("-----END-----");
        log.info("Completed {} / {} rows, {} ms = {} ms / rec", recsLoaded, recs, timeTaken, 1.0 * timeTaken / recsLoaded);
        log.info("    Found {} diff anomalies", anomalyRepository.getDiffs().size());
        log.info("    Found {} violations", anomalyRepository.getViolations().size());
        log.info(statisticRepository.toString());
    }

    /**
     * Called for each row in the database table.
     * <p>
     * 1. Adds the row to the statistics repository.
     * 2. Transforms the XML to a DTO.
     * 3. Transforms the DTO to JSON.
     * 4. Validates the JSON against the JSON schema.
     * 5. Transforms the JSON back to a DTO.
     * 6. Transforms the DTO to XML.
     * 7. Compares the transformed XML to the original XML.
     * 8. Logs any anomalies (diffs or violations) to the application log.
     *
     * @param concorContribution The database table row to process.
     */
    void examineConcorContribution(ConcorContribution concorContribution) {
        try {
            statisticRepository.addStat(concorContribution);
            long id = concorContribution.id();
            String xmlOriginal = concorContribution.fullXml();
            // Transform XML -> DTO
            CONTRIBUTIONS dto1 = xmlTransformService.fromXML(xmlOriginal);
            // Transform DTO -> JSON
            String json = jsonTransformService.toJSON(dto1);
            // Validate transformed JSON against JSON schema.
            var violations = jsonValidationService.validateJSON(json);
            if (!violations.isEmpty()) {
                log.warn("id = {}, json violations = {}", id, violations);
                anomalyRepository.addViolation(id, violations);
            }
            // Transform JSON -> DTO
            CONTRIBUTIONS dto2 = jsonTransformService.fromJSON(json);
            // Transform DTO -> XML
            String xmlTransformed = xmlTransformService.toXML(dto2);
            // Compare transformed XML against original XML.
            var diffs = xmlComparisonService.compareXML(xmlOriginal, xmlTransformed);
            if (!diffs.isEmpty()) {
                log.warn("id = {}, xml diffs = {}", id, diffs);
                anomalyRepository.addDiff(id, diffs);
            }
        } catch (Exception e) {
            log.error("Exception thrown", e);
        }
    }
}
