package uk.gov.justice.laadces.premigconcor.dao.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
@Slf4j
public class CaseMigrationRepository {
    private final JdbcClient integration;

    public CaseMigrationRepository(@Qualifier("integrationJdbcClient") final JdbcClient integration) {
        this.integration = integration;
    }

    public void saveAll(final List<CaseMigration> caseMigrations) {
        for (CaseMigration caseMigration : caseMigrations) {
            try {
                save(caseMigration);
            } catch (Exception e) {
                log.warn("Could not save {}", caseMigration, e);
            }
        }
    }

    public void save(final CaseMigration caseMigration) {
        final String sql = """
                INSERT INTO case_migration
                    (maat_id, record_type, concor_contribution_id, fdc_id, batch_id, is_processed, processed_date, http_status, payload)
                VALUES
                    (:maatId, :recordType, :concorContributionId, :fdcId, :batchId, :isProcessed, :processedDate, :httpStatus, :payload)
                """;
        int noOfrowsAffected = integration.sql(sql)
                .param("maatId", caseMigration.maatId(), Types.INTEGER)
                .param("recordType", caseMigration.recordType(), Types.VARCHAR)
                .param("concorContributionId", caseMigration.concorContributionId(), Types.INTEGER)
                .param("fdcId", caseMigration.fdcId(), Types.INTEGER)
                .param("batchId", caseMigration.batchId(), Types.INTEGER)
                .param("isProcessed", caseMigration.isProcessed(), Types.BOOLEAN)
                .param("processedDate", caseMigration.processedDate(), Types.TIMESTAMP)
                .param("httpStatus", caseMigration.httpStatus(), Types.INTEGER)
                .param("payload", caseMigration.payload(), Types.VARCHAR)
                .update();
    }
}
