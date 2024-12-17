package uk.gov.justice.laadces.premigconcor.service;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class CaseMigrationComponentComparatorTests {
    private final Comparator<String> comparator = CaseMigrationComponentComparator.INSTANCE;

    @Test
    void testCompare() {
        // this is used to order the columns in the CSV output file of CaseMigration rows.
        assertThat(comparator.compare("maatId", "fdcId")).isLessThan(0); // maatId is to the left of fdcId
        assertThat(comparator.compare("maatId", "concorContributionId")).isLessThan(0); // maatId is to the left of fdcId
        assertThat(comparator.compare("isProcessed", "processedDate")).isLessThan(0); // isProcessed is to the left of processedDate
        assertThat(comparator.compare("payload", "aardvark")).isLessThan(0); // unknown fields go to the right
    }
}
