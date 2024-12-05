package uk.gov.justice.laadces.premigconcor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.premigconcor.dao.migration.MaatId;
import uk.gov.justice.laadces.premigconcor.service.CsvOutputService;
import uk.gov.justice.laadces.premigconcor.dao.maat.ConcorContributionRepository;
import uk.gov.justice.laadces.premigconcor.dao.maat.FdcContributionRepository;
import uk.gov.justice.laadces.premigconcor.dao.migration.MigrationScopeRepository;

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
    private final CsvOutputService csvOutputService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run() was called");
        var maatIds = migrationScopeRepository.findAll();
        log.info("Queried {} maatIds from migration database", maatIds.size());
        var missingConcorMaatIds = new TreeSet<Long>();
        var cmsConcor = concorContributionRepository.findLatestIdsByMaatIds(maatIds, missingConcorMaatIds);
        log.info("Queried {} concor cases from maat database, {} missing", cmsConcor.size(), missingConcorMaatIds.size());
        var missingFdcMaatIds = new TreeSet<Long>();
        var cmsFdc = fdcContributionRepository.findLatestIdsByMaatIds(maatIds, missingFdcMaatIds);
        log.info("Queried {} fdc cases from maat database, {} missing", cmsFdc.size(), missingFdcMaatIds.size());
        csvOutputService.writeCaseMigrations("/tmp/premigconcor-concorCases.csv", cmsConcor);
        csvOutputService.writeMaatIds("/tmp/premigconcor-concorMissing.csv", MaatId.of(missingConcorMaatIds));
        csvOutputService.writeCaseMigrations("/tmp/premigconcor-fdcCases.csv", cmsFdc);
        csvOutputService.writeMaatIds("/tmp/premigconcor-fdcMissing.csv", MaatId.of(missingFdcMaatIds));
    }
}
