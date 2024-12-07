package uk.gov.justice.laadces.premigconcor.dao.maat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import uk.gov.justice.laadces.premigconcor.dao.integration.CaseMigration;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Repository
@Slf4j
public class FdcContributionRepository {
    private static final int BATCH_SIZE = 990;
    private final JdbcClient maat;

    public FdcContributionRepository(@Qualifier("maatJdbcClient") final JdbcClient maat) {
        this.maat = maat;
    }

    public List<CaseMigration> findLatestIdsByMaatIds(final List<Long> maatIds) {
        final int count = maatIds.size();
        final var cms = new ArrayList<CaseMigration>(count);
        for (int i = 0; i < count; i += BATCH_SIZE) {
            final var subList = maatIds.subList(i, Math.min(count, i + BATCH_SIZE));
            final var paramSource = new MapSqlParameterSource("maatIds", subList);
            final var set = new TreeSet<>(subList);
            cms.addAll(maat.sql("SELECT MAX(id), rep_id FROM togdata.fdc_contributions WHERE rep_id IN (:maatIds) GROUP BY rep_id ORDER BY rep_id")
                    .paramSource(paramSource)
                    .query((rs, rowNum) -> {
                        final long fdcId = rs.getLong(1);
                        final long maatId = rs.getLong(2);
                        set.remove(maatId);
                        return CaseMigration.ofFdcContribution(fdcId, maatId, rowNum);
                    })
                    .list());
            if (!set.isEmpty()) {
                log.warn("{} maatIds were not found: {}", set.size(), set);
            }
        }
        return cms;
    }
}
