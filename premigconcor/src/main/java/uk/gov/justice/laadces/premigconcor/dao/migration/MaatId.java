package uk.gov.justice.laadces.premigconcor.dao.migration;

import java.util.List;
import java.util.Set;

/**
 * Exists purely to output as CSV, this class is probably completely unnecessary.
 *
 * @param maatId A numeric maatId
 */
public record MaatId(long maatId) {
    public static List<MaatId> of(Set<Long> maatIds) {
        return maatIds.stream().map(MaatId::new).toList();
    }
}
