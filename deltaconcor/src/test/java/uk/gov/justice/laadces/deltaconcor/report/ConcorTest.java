package uk.gov.justice.laadces.deltaconcor.report;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laadces.deltaconcor.generated.CONTRIBUTIONS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.opencsv.ICSVWriter.NO_QUOTE_CHARACTER;

@SpringBootTest
class ConcorTest {
    @Autowired
    private ConcorComparison comparison;

    @Test
    void givenTwoContributions_whenCompared_thenWrittenAsCsv() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        // given
        final var c1 = new CONTRIBUTIONS();
        final var b2 = new CONTRIBUTIONS.Applicant.BankDetails();
        b2.setSortCode("01-02-34");
        b2.setAccountNo(12345678L);
        b2.setAccountName("John Smith");
        final var a2 = new CONTRIBUTIONS.Applicant();
        a2.setFirstName("John");
        a2.setLastName("Smith");
        a2.setBankDetails(b2);
        final var c2 = new CONTRIBUTIONS();
        c2.setApplicant(a2);
        // when
        ConcorChangeCounts counts = new ConcorChangeCounts("2024-12-31", 1);
        if (comparison.compare(c1, c2, counts)) {
            counts.setChangedRecords(counts.getChangedRecords() + 1);
        }
        final var countsList = List.of(counts);
        // then
        final var writer = new FileWriter("/tmp/deltaconcor.csv");
        final var strategy = new HeaderColumnNameMappingStrategy<ConcorChangeCounts>();
        strategy.setType(ConcorChangeCounts.class);
        strategy.setColumnOrderOnWrite(ConcorChangeCountsFieldComparator.INSTANCE);
        final var beanToCsv = new StatefulBeanToCsvBuilder<ConcorChangeCounts>(writer)
                .withMappingStrategy(strategy)
                .withQuotechar(NO_QUOTE_CHARACTER)
                .build();
        beanToCsv.write(countsList);
        writer.close();
    }
}
