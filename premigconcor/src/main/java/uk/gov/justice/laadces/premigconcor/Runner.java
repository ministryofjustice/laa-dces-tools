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
    private final MigrationScopeRepository migrationScopeRepository;
    private final ConcorContributionRepository concorContributionRepository;
    private final FdcContributionRepository fdcContributionRepository;
    private final CaseMigrationRepository caseMigrationRepository;
    private final CsvOutputService csvOutputService;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("Entering run...");
        final var maatIds = migrationScopeRepository.findAll();
        log.info("Found {} maatIds from migration database", maatIds.size());

        final var foundConcors = new HashSet<CaseMigration>();
        final var missingConcors = new TreeSet<Long>();
        concorContributionRepository.addLatestIdsByMaatIds(maatIds, foundConcors, missingConcors);
        log.info("Found {} concor cases from maat database, and missing {} maatIds", foundConcors.size(), missingConcors.size());
        csvOutputService.writeCaseMigrations("/tmp/premigconcor-concorCases.csv", foundConcors);
        csvOutputService.writeMaatIds("/tmp/premigconcor-concorMissing.csv", MaatId.of(missingConcors));

        final var foundFdcs = new HashSet<CaseMigration>();
        final var missingFdcs = new TreeSet<Long>();
        fdcContributionRepository.addLatestIdsByMaatIds(maatIds, foundFdcs, missingFdcs);
        log.info("Found {} fdc cases from maat database, and missing {} maatIds", foundFdcs.size(), missingFdcs.size());
        csvOutputService.writeCaseMigrations("/tmp/premigconcor-fdcCases.csv", foundFdcs);
        csvOutputService.writeMaatIds("/tmp/premigconcor-fdcMissing.csv", MaatId.of(missingFdcs));

        final var found = new HashSet<CaseMigration>();
        found.addAll(foundConcors);
        found.addAll(foundFdcs);
        log.info("Checking {} case-migrations for existing duplicates", found.size());
        caseMigrationRepository.removeExisting(found);

        log.info("Persisting {} non-duplicate case-migrations", found.size());
        caseMigrationRepository.saveAll(found);

        log.info("Exiting run...");
    }
}
