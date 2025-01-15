package uk.gov.justice.laadces.premigconcor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.premigconcor.dao.integration.CaseMigration;
import uk.gov.justice.laadces.premigconcor.dao.integration.CaseMigrationRepository;
import uk.gov.justice.laadces.premigconcor.dao.maat.ConcorContributionRepository;
import uk.gov.justice.laadces.premigconcor.dao.maat.FdcContributionRepository;
import uk.gov.justice.laadces.premigconcor.dao.migration.MaatId;
import uk.gov.justice.laadces.premigconcor.dao.migration.MigrationScopeRepository;
import uk.gov.justice.laadces.premigconcor.service.CsvOutputService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * ApplicationRunner class that examines the data in the database.
 */
@Component
@Profile("!test")
@RequiredArgsConstructor
@Slf4j
class Runner implements ApplicationRunner {
    // File paths to write CSV output to (for validation).
    private static final String PREFIX = "./" + LocalDate.now() + "_premigconcor-";
    private static final String PATH_CSV_OUTPUT_FOUND_CONCOR = PREFIX + "concorCases.csv";
    private static final String PATH_CSV_OUTPUT_MISSING_CONCOR = PREFIX + "concorMissing.csv";
    private static final String PATH_CSV_OUTPUT_FOUND_FDC = PREFIX + "fdcCases.csv";
    private static final String PATH_CSV_OUTPUT_MISSING_FDC = PREFIX + "fdcMissing.csv";
    private static final String PATH_CSV_OUTPUT_SAVED_CM = PREFIX + "saved.csv";
    private static final int MAX_COUNT_MAAT_IDS = -1; // Use full data if negative, truncated data if non-negative.

    private final MigrationScopeRepository migrationScopeRepository;
    private final ConcorContributionRepository concorContributionRepository;
    private final FdcContributionRepository fdcContributionRepository;
    private final CaseMigrationRepository caseMigrationRepository;
    private final CsvOutputService csvOutputService;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("Entering run...");
        final var allMaatIds = migrationScopeRepository.findAll();
        log.info("Found {} maatIds from migration database", allMaatIds.size());
        final var maatIds = MAX_COUNT_MAAT_IDS < 0 ? allMaatIds : allMaatIds.subList(0, Math.min(MAX_COUNT_MAAT_IDS, allMaatIds.size()));
        log.info("Truncating to {} maatIds to be processed", maatIds.size());

        final var foundConcors = new HashSet<CaseMigration>();
        final var missingConcors = new TreeSet<Long>();
        concorContributionRepository.addLatestIdsByMaatIds(maatIds, foundConcors, missingConcors);
        log.info("Found {} concor cases from maat database, and missing {} maatIds", foundConcors.size(), missingConcors.size());
        csvOutputService.writeCaseMigrations(PATH_CSV_OUTPUT_FOUND_CONCOR, foundConcors);
        csvOutputService.writeMaatIds(PATH_CSV_OUTPUT_MISSING_CONCOR, MaatId.of(missingConcors));

        final var foundFdcs = new HashSet<CaseMigration>();
        final var missingFdcs = new TreeSet<Long>();
        fdcContributionRepository.addLatestIdsByMaatIds(maatIds, foundFdcs, missingFdcs);
        log.info("Found {} fdc cases from maat database, and missing {} maatIds", foundFdcs.size(), missingFdcs.size());
        csvOutputService.writeCaseMigrations(PATH_CSV_OUTPUT_FOUND_FDC, foundFdcs);
        csvOutputService.writeMaatIds(PATH_CSV_OUTPUT_MISSING_FDC, MaatId.of(missingFdcs));

        log.info("Checking {} maatIds for unprocessed case-migrations", maatIds.size());
        final int deleted = caseMigrationRepository.deleteUnprocessed(maatIds);
        log.info("Deleted {} unprocessed case_migration rows from integration database", deleted);

        final var found = new HashSet<CaseMigration>();
        found.addAll(foundConcors);
        found.addAll(foundFdcs);
        log.info("Checking {} case-migrations for existing duplicates", found.size());
        final int[] removed = caseMigrationRepository.removeExisting(found);
        log.info("Left {} case-migrations (removed {} dups of {} case_migration rows)", found.size(), removed[1], removed[0]);

        final var renumbered = caseMigrationRepository.renumberBatchIds(found);
        log.info("Renumbered {} case-migrations", renumbered.size());
        csvOutputService.writeCaseMigrations(PATH_CSV_OUTPUT_SAVED_CM, renumbered);
        final int saved = caseMigrationRepository.saveAll(renumbered);
        log.info("Saved {} case_migration rows to the integration database", saved);

        log.info("Exiting run...");
    }
}
