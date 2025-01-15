package uk.gov.justice.laadces.premigconcor.dao.integration;

import java.time.LocalDateTime;
import java.util.Objects;

public record CaseMigration(long maatId                 /* required */,
                            String recordType           /* required */,
                            long concorContributionId   /* null-or-id */,
                            long fdcId                  /* null-or-id */,
                            Long batchId                /* vary */,
                            Boolean isProcessed         /* false */,
                            LocalDateTime processedDate /* null */,
                            Integer httpStatus          /* null */,
                            String payload              /* null */) {

    public static CaseMigration ofConcorContribution(long maatId, long concorContributionId, Long batchId) {
        return new CaseMigration(maatId, "Contribution", concorContributionId, 0, batchId, Boolean.FALSE, null, null, null);
    }

    public static CaseMigration ofFdcContribution(long maatId, long fdcId, Long batchId) {
        return new CaseMigration(maatId, "Fdc", 0, fdcId, batchId, Boolean.FALSE, null, null, null);
    }

    public static CaseMigration ofPrimaryKey(long maatId, long concorContributionId, long fdcId) {
        return new CaseMigration(maatId, concorContributionId != 0 ? "Contribution" : "Fdc", concorContributionId, fdcId, null, null, null, null, null);
    }

    public CaseMigration withBatchId(long batchId) {
        return new CaseMigration(maatId(), recordType(), concorContributionId(), fdcId(), batchId, isProcessed(), processedDate(), httpStatus(), payload());
    }

    /**
     * Implementation of equals() that ignores every record component except (maatId, concorContributionId, fdcId),
     * which is the primary key in the table. Annoying as one reason to use records was to avoid writing this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseMigration other) {
            return maatId == other.maatId && concorContributionId == other.concorContributionId && fdcId == other.fdcId;
        }
        return false;
    }

    /**
     * Implementation of hashCode() that ignores every record component except (maatId, concorContributionId, fdcId),
     * which is the primary key in the table. Annoying as one reason to use records was to avoid writing this.
     */
    @Override
    public int hashCode() {
        return Objects.hash(maatId, concorContributionId, fdcId);
    }
}
