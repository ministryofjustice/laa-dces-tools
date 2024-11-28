package uk.gov.justice.laadces.deltaconcor.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring Data JDBC repository for accessing the MAAT DB's CONCOR_CONTRIBUTIONS table.
 */
public interface ConcorContributionRepository extends PagingAndSortingRepository<ConcorContribution, Long> {
    int countByIdBetween(long lowId, long highId);

    Slice<ConcorContribution> findByIdBetweenOrderById(long lowId, long highId, Pageable limit);
}
