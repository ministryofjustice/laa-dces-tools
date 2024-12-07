package uk.gov.justice.laadces.premigconcor.dao.migration;

import java.util.Collection;
import java.util.List;

/**
 * Exists purely to output as CSV, this class is probably completely unnecessary.
 *
 * @param maatId A numeric maatId
 */
public record MaatId(long maatId) {
    public static List<MaatId> of(Collection<Long> maatIds) {
        return maatIds.stream().map(MaatId::new).toList();
    }
}
