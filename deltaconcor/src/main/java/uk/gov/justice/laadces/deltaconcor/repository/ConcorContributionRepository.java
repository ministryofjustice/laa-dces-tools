package uk.gov.justice.laadces.deltaconcor.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

/**
 * Spring Data JDBC repository for accessing the MAAT DB's CONCOR_CONTRIBUTIONS table.
 */
public interface ConcorContributionRepository extends PagingAndSortingRepository<ConcorContribution, Long> {
    int countByIdBetween(long lowId, long highId);

    Slice<ConcorContribution> findByIdBetweenAndStatusOrderById(long lowId, long highId, String status, Pageable limit);

    Slice<ConcorContribution> findByIdBetweenAndStatusOrderByIdDesc(long lowId, long highId, String status, Pageable limit);

    Slice<ConcorContribution> findByIdBetweenAndStatusAndRepIdInOrderByIdDesc(long lowId, long highId, String status, Set<Long> repIds, Pageable limit);
}
