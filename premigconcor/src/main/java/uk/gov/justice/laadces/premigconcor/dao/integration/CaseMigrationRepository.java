package uk.gov.justice.laadces.premigconcor.dao.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Collection;

@Repository
@Slf4j
public class CaseMigrationRepository {
    private static final int BATCH_SIZE = 990;
    private final JdbcTemplate integration;

    public CaseMigrationRepository(@Qualifier("integrationJdbcTemplate") final JdbcTemplate integration) {
        this.integration = integration;
    }

    public void removeExisting(final Collection<CaseMigration> caseMigrations) {
        final int originalSize = caseMigrations.size();
        final var counts = new Object() { // need to be 'effectively final' to access from lambda.
            int total = 0;
            int removed = 0;
        };
        integration.query("""
                SELECT maat_id, concor_contribution_id, fdc_id
                FROM case_migration
                """, rs -> {
            final var caseMigration = CaseMigration.ofPrimaryKey(rs.getLong(1), rs.getLong(2), rs.getLong(3));
            ++counts.total;
            if (caseMigrations.remove(caseMigration)) {
                ++counts.removed;
            }
        });
        log.info("Removed {} of {} original case-migrations", counts.removed, originalSize);
        log.info("Remaining {} case-migrations are distinct from the {} already in the database", caseMigrations.size(), counts.total);
    }

    public void saveAll(final Collection<CaseMigration> caseMigrations) {
        integration.batchUpdate("""
                INSERT INTO case_migration (maat_id, record_type, concor_contribution_id, fdc_id,
                                            batch_id, is_processed, processed_date, http_status, payload)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """, caseMigrations, BATCH_SIZE, (stmt, caseMigration) -> {
            stmt.setLong(1, caseMigration.maatId());
            stmt.setString(2, caseMigration.recordType());
            stmt.setLong(3, caseMigration.concorContributionId());
            stmt.setLong(4, caseMigration.fdcId());
            final Long batchId = caseMigration.batchId();
            if (batchId != null) {
                stmt.setLong(5, caseMigration.batchId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            final Boolean isProcessed = caseMigration.isProcessed();
            if (isProcessed != null) {
                stmt.setBoolean(6, caseMigration.isProcessed());
            } else {
                stmt.setNull(6, Types.BOOLEAN);
            }
            final LocalDateTime processedDate = caseMigration.processedDate();
            if (processedDate != null) {
                stmt.setTimestamp(7, Timestamp.valueOf(processedDate));
            } else {
                stmt.setNull(7, Types.TIMESTAMP);
            }
            final Integer httpStatus = caseMigration.httpStatus();
            if (httpStatus != null) {
                stmt.setInt(8, httpStatus);
            } else {
                stmt.setNull(8, Types.INTEGER);
            }
            final String payload = caseMigration.payload();
            if (payload != null) {
                stmt.setString(9, caseMigration.payload());
            } else {
                stmt.setNull(9, Types.VARCHAR);
            }
        });
    }
}
