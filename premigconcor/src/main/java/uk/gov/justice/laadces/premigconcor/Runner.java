package uk.gov.justice.laadces.premigconcor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.premigconcor.dao.integration.CsvOutputService;
import uk.gov.justice.laadces.premigconcor.dao.maat.ConcorContributionRepository;
import uk.gov.justice.laadces.premigconcor.dao.maat.FdcContributionRepository;
import uk.gov.justice.laadces.premigconcor.dao.migration.MigrationScopeRepository;

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
        log.info("Queried {} maatid from migration database", maatIds.size());
        var cmsConcor = concorContributionRepository.findLatestIdsByMaatIds(maatIds);
        log.info("Queried {} concor cases from maat database", cmsConcor.size());
        var cmsFdc = fdcContributionRepository.findLatestIdsByMaatIds(maatIds);
        log.info("Queried {} fdc cases from maat database", cmsFdc.size());
        csvOutputService.writeCaseMigrations("/tmp/premigconcor-ccr.csv", cmsConcor);
        csvOutputService.writeCaseMigrations("/tmp/premigconcor-fdc.csv", cmsFdc);
    }
}
