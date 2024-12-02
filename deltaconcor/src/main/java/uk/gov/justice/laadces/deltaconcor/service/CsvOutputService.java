package uk.gov.justice.laadces.deltaconcor.service;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvFieldAssignmentException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laadces.deltaconcor.report.Change;
import uk.gov.justice.laadces.deltaconcor.report.ChangeLog;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;

@RequiredArgsConstructor
@Service
public class CsvOutputService {
    public void writeChanges(final String filename, final List<Change> changes) {
        final FileWriter writer;
        try {
            writer = new FileWriter(filename);
            final var strategy = new HeaderColumnNameMappingStrategy<Change>();
            strategy.setType(Change.class);
            strategy.setColumnOrderOnWrite(ChangeFieldComparator.INSTANCE);
            final var beanToCsv = new StatefulBeanToCsvBuilder<Change>(writer)
                    .withMappingStrategy(strategy)
                    .withQuotechar(NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(changes);
            writer.close();
        } catch (IOException | CsvFieldAssignmentException e) {
            throw new IllegalArgumentException("Failed to write CSV change file: " + filename, e);
        }
    }

    public void writeChangeLogs(final String filename, final List<ChangeLog> changeLogs) {
        final FileWriter writer;
        try {
            writer = new FileWriter(filename);
            final var strategy = new HeaderColumnNameMappingStrategy<ChangeLog>();
            strategy.setType(ChangeLog.class);
            strategy.setColumnOrderOnWrite(ChangeFieldComparator.INSTANCE);
            final var beanToCsv = new StatefulBeanToCsvBuilder<ChangeLog>(writer)
                    .withMappingStrategy(strategy)
                    .withQuotechar(NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(changeLogs);
            writer.close();
        } catch (IOException | CsvFieldAssignmentException e) {
            throw new IllegalArgumentException("Failed to write CSV change log file: " + filename, e);
        }
    }
}
