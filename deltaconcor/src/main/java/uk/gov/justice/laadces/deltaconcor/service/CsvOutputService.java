package uk.gov.justice.laadces.deltaconcor.service;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laadces.deltaconcor.report.Change;
import uk.gov.justice.laadces.deltaconcor.report.ChangeFieldComparator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;

@RequiredArgsConstructor
@Service
@Slf4j
public class CsvOutputService {
    public void writeChanges(final String filename, final List<Change> countsList) {
        final FileWriter writer;
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            log.error("Failed to open file for writing: {}", filename, e);
            throw new IllegalArgumentException(e);
        }
        final var strategy = new HeaderColumnNameMappingStrategy<Change>();
        strategy.setType(Change.class);
        strategy.setColumnOrderOnWrite(ChangeFieldComparator.INSTANCE);
        final var beanToCsv = new StatefulBeanToCsvBuilder<Change>(writer)
                .withMappingStrategy(strategy)
                .withQuotechar(NO_QUOTE_CHARACTER)
                .build();
        try {
            beanToCsv.write(countsList);
        } catch (CsvDataTypeMismatchException|CsvRequiredFieldEmptyException e) {
            log.error("Failed to write CSV data", e);
            throw new IllegalArgumentException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            log.warn("Failed to close file after writing: {}", filename, e);
        }
    }
}
