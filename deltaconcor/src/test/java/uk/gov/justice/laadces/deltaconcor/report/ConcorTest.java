package uk.gov.justice.laadces.deltaconcor.report;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laadces.deltaconcor.generated.CONTRIBUTIONS;
import uk.gov.justice.laadces.deltaconcor.service.ChangeService;
import uk.gov.justice.laadces.deltaconcor.service.CsvOutputService;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ConcorTest {
    @Autowired
    private ChangeService changeService;
    @Autowired
    private CsvOutputService csvoutput;

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
        Change counts = new Change("2024-12-31");
        if (changeService.compare(c1, c2, counts)) {
            counts.setChangedRecords(counts.getChangedRecords() + 1);
        }
        final var countsList = List.of(counts);
        // then
        csvoutput.writeChanges("/tmp/deltaconcor.csv", countsList);
    }
}
