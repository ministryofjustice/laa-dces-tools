package uk.gov.justice.laadces.premigconcor.dao.integration;

import java.time.LocalDateTime;

public record CaseMigration(long maatId                 /* required */,
                            String recordType           /* required */,
                            Long concorContributionId   /* null-or-id */,
                            Long fdcId                  /* null-or-id */,
                            int batchId                 /* vary */,
                            boolean isProcessed         /* false */,
                            LocalDateTime processedDate /* null */,
                            Integer httpStatus          /* null */,
                            String payload              /* null */) {

    public static CaseMigration ofConcorContribution(long maatId, long concorContributionId, int batchId) {
        return new CaseMigration(maatId, "Contribution", concorContributionId, null, batchId, false, null, null, null);
    }

    public static CaseMigration ofFdcContribution(long maatId, long fdcId, int batchId) {
        return new CaseMigration(maatId, "Fdc", null, fdcId, batchId, false, null, null, null);
    }
}
