package uk.gov.justice.laadces.premigconcor.dao.migration;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaatIdTests {
    @Test
    void testOf() {
        final var maatIds = MaatId.of(List.of(1L, 2L, 3L));
        assertThat(maatIds).hasSize(3);
        assertThat(maatIds).isEqualTo(List.of(new MaatId(1L), new MaatId(2L), new MaatId(3L)));
    }
}
