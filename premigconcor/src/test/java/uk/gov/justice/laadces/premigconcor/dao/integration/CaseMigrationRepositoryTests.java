package uk.gov.justice.laadces.premigconcor.dao.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CaseMigrationRepositoryTests {
    @Autowired
    CaseMigrationRepository caseMigrationRepository;

    @Test
    void testPartition() {
        final var large = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final var split = caseMigrationRepository.partition(large, 3);
        assertThat(split).hasSize(4);
        assertThat(split).isEqualTo(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9), List.of(10)));
        assertThat(split.stream().flatMap(List::stream).toList()).hasSize(10);
    }
}
