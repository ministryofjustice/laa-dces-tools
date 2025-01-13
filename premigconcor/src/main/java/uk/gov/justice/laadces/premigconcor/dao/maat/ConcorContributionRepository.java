package uk.gov.justice.laadces.premigconcor.dao.maat;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import uk.gov.justice.laadces.premigconcor.dao.integration.CaseMigration;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Repository
public class ConcorContributionRepository {
    private static final int MAATDB_BATCH_QUERY_SIZE = 990; // concor_contributions query batch size
    private static final long INTDB_BATCH_ID_DIVISOR = 250L; // case_migration batch_id size
    private final JdbcClient maat;

    public ConcorContributionRepository(@Qualifier("maatJdbcClient") final JdbcClient maat) {
        this.maat = maat;
    }

    public void addLatestIdsByMaatIds(final List<Long> maatIds, final Collection<CaseMigration> foundConcors, final Collection<Long> missingConcors) {
        final int count = maatIds.size();
        final long[] batchIndex = {0L}; // Needs to be "effectively final".
        for (int i = 0; i < count; i += MAATDB_BATCH_QUERY_SIZE) {
            final var subList = maatIds.subList(i, Math.min(count, i + MAATDB_BATCH_QUERY_SIZE));
            final var paramSource = new MapSqlParameterSource("maatIds", subList);
            final var set = new TreeSet<>(subList);
            foundConcors.addAll(maat.sql("""
                            SELECT MAX(id), rep_id
                            FROM togdata.concor_contributions
                            WHERE rep_id IN (:maatIds) AND status = 'SENT'
                            GROUP BY rep_id
                            """)
                    .paramSource(paramSource)
                    .query((rs, rowNum) -> {
                        final long concorContributionId = rs.getLong(1);
                        final long maatId = rs.getLong(2);
                        set.remove(maatId);
                        return CaseMigration.ofConcorContribution(maatId, concorContributionId, (batchIndex[0]++) / INTDB_BATCH_ID_DIVISOR);
                    })
                    .list());
            if (!set.isEmpty()) {
                missingConcors.addAll(set);
            }
        }
    }
}
