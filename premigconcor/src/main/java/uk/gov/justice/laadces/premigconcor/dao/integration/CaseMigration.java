package uk.gov.justice.laadces.premigconcor.dao.integration;

import java.time.LocalDateTime;

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
}
