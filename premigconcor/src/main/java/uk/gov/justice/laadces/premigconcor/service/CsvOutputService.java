package uk.gov.justice.laadces.premigconcor.service;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvFieldAssignmentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laadces.premigconcor.dao.integration.CaseMigration;
import uk.gov.justice.laadces.premigconcor.dao.migration.MaatId;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;

@RequiredArgsConstructor
@Service
public class CsvOutputService {
    public void writeCaseMigrations(final String filename, final Collection<CaseMigration> caseMigrations) {
        final FileWriter writer;
        try {
            writer = new FileWriter(filename);
            final var strategy = new HeaderColumnNameMappingStrategy<CaseMigration>();
            strategy.setType(CaseMigration.class);
            strategy.setColumnOrderOnWrite(CaseMigrationComponentComparator.INSTANCE);
            final var beanToCsv = new StatefulBeanToCsvBuilder<CaseMigration>(writer)
                    .withMappingStrategy(strategy)
                    .withQuotechar(NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(caseMigrations.iterator());
            writer.close();
        } catch (IOException | CsvFieldAssignmentException e) {
            throw new IllegalArgumentException("Failed to write CSV case-migrations file: " + filename, e);
        }
    }

    public void writeMaatIds(final String filename, final List<MaatId> maatIds) {
        final FileWriter writer;
        try {
            writer = new FileWriter(filename);
            final var beanToCsv = new StatefulBeanToCsvBuilder<MaatId>(writer)
                    .withQuotechar(NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(maatIds);
            writer.close();
        } catch (IOException | CsvFieldAssignmentException e) {
            throw new IllegalArgumentException("Failed to write CSV maat-ids file: " + filename, e);
        }
    }
}
