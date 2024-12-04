package uk.gov.justice.laadces.deltaconcor.report;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a change log entry.
 */
// IntelliJ complains about the following annotation that 'Lombok needs a default constructor in the base class'.
// However, this compiles just fine with Gradle.
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeLog extends Change {
    public ChangeLog(final String date) {
        super(date);
    }
    // the following fields will be output alphabetically as columns in the CSV file.
    private long maatId;
    private long next_concorContributionId;
    private long prev_concorContributionId;
}
