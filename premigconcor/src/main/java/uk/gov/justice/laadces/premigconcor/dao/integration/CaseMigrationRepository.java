package uk.gov.justice.laadces.premigconcor.dao.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class CaseMigrationRepository {
    private static final int BATCH_SIZE = 990;
    private final NamedParameterJdbcTemplate integration;

    public CaseMigrationRepository(@Qualifier("integrationNamedParameterJdbcTemplate") final NamedParameterJdbcTemplate integration) {
        this.integration = integration;
    }

    public void deleteUnprocessed(final List<Long> maatIds) {
        final var partitions = partition(maatIds, BATCH_SIZE);
        final var batchArgs = partitions.stream()
                .map(partition -> new MapSqlParameterSource("maatIds", partition))
                .toArray(MapSqlParameterSource[]::new);
        final int[] rowsDeleted = integration.batchUpdate("""
                DELETE
                FROM case_migration
                WHERE is_processed <> TRUE
                AND maat_id IN (:maatIds)
                """, batchArgs);
        log.info("deleteUnprocessed: Deleted {} rows", Arrays.stream(rowsDeleted).sum());
    }

    /**
     * Break a list of elements of size >= 1 into multiple lists, each having a given length
     * (except possibly the last list, which may be smaller).
     *
     * @param input The large list to break into sublists.
     * @param partitionSize The desired length of the sublists.
     * @return A list of lists (each of size partitionSize, except perhaps the last).
     * @param <T> The type of the list.
     */
    private <T> List<List<T>> partition(final List<T> input, final int partitionSize) {
        if (partitionSize < 1) {
            throw new IllegalArgumentException("partitionSize must be >= 1");
        }
        final int inputSize = input.size();
        final var output = new ArrayList<List<T>>((inputSize + partitionSize - 1) / partitionSize);
        for (int index = 0; index < inputSize; index += partitionSize) {
            output.add(input.subList(index, Math.min(index + partitionSize, inputSize)));
        }
        return output;
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
        final int[][] rowsInserted = integration.getJdbcOperations().batchUpdate("""
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
        log.info("saveAll: Inserted {} rows", Arrays.stream(rowsInserted).flatMapToInt(Arrays::stream).sum());
    }
}
