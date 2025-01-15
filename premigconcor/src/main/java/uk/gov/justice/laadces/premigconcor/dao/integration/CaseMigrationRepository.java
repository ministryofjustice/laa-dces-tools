package uk.gov.justice.laadces.premigconcor.dao.integration;

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
public class CaseMigrationRepository {
    private static final int BATCH_SIZE = 990;
    private final NamedParameterJdbcTemplate integration;

    public CaseMigrationRepository(@Qualifier("integrationNamedParameterJdbcTemplate") final NamedParameterJdbcTemplate integration) {
        this.integration = integration;
    }

    public int deleteUnprocessed(final List<Long> maatIds) {
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
        return Arrays.stream(rowsDeleted).sum(); // total number of deleted rows.
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
    <T> List<List<T>> partition(final List<T> input, final int partitionSize) {
        if (partitionSize < 1) {
            throw new IllegalArgumentException("partitionSize must be >= 1");
        }
        final int inputSize = input.size();
        final var output = new ArrayList<List<T>>((inputSize + partitionSize - 1) / partitionSize);
        for (int index = 0; index < inputSize; index += partitionSize) {
            output.add(input.subList(index, Math.min(index + partitionSize, inputSize)));
        }
        return output; // the list of lists (each of size partitionSize).
    }

    public int[] removeExisting(final Collection<CaseMigration> caseMigrations) {
        final int[] counts = {0, 0}; // {total, removed}
        integration.query("""
                SELECT maat_id, concor_contribution_id, fdc_id
                FROM case_migration
                """, rs -> {
            final var caseMigration = CaseMigration.ofPrimaryKey(rs.getLong(1), rs.getLong(2), rs.getLong(3));
            ++counts[0];
            if (caseMigrations.remove(caseMigration)) {
                ++counts[1];
            }
        });
        return counts; // size of case_migration table, duplicates between caseMigrations parameter and database table.
    }

    private static final long CONCOR_BATCH_ID_DIVISOR = 250L; // case_migration batch_id size for concor_contributions
    private static final long FDC_BATCH_ID_DIVISOR = 500L; // case_migration batch_id size for fdc_contributions

    /**
     * Returns a copy of the input where any CaseMigration without a batch_id is given a batch_id.
     *
     * @param caseMigrations input list of CaseMigration instances which may have null batch_ids.
     * @return List&lt;CaseMigration&gt; where each instance has a non-null batch_id.
     */
    public List<CaseMigration> renumberBatchIds(Collection<CaseMigration> caseMigrations) {
        final int[] counts = {0, 0}; // {concors, fdcs}
        final var renumbered = new ArrayList<CaseMigration>(caseMigrations.size());
        caseMigrations.forEach(caseMigration -> {
            if (caseMigration.batchId() != null) {
                renumbered.add(caseMigration); // has a batch_id already.
            } else {
                if (caseMigration.concorContributionId() > 0) {
                    renumbered.add(caseMigration.withBatchId((counts[0]++) / CONCOR_BATCH_ID_DIVISOR));
                } else {
                    renumbered.add(caseMigration.withBatchId((counts[1]++) / FDC_BATCH_ID_DIVISOR));
                }
            }
        });
        return renumbered; // the copied collection.
    }

    public int saveAll(final Collection<CaseMigration> caseMigrations) {
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
        return Arrays.stream(rowsInserted).flatMapToInt(Arrays::stream).sum(); // total number of inserted rows.
    }
}
