package uk.gov.justice.laadces.verifyconcor.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

/**
 * Spring Data JDBC repository for accessing the MAAT DB's CONCOR_CONTRIBUTIONS table.
 */
public interface ConcorContributionRepository extends PagingAndSortingRepository<ConcorContribution, Long> {
    int countByIdBetween(long lowId, long highId);
    int countByStatusAndIdBetween(String status, long lowId, long highId);

    Slice<ConcorContribution> findByIdBetweenOrderById(long lowId, long highId, Pageable limit);
    Slice<ConcorContribution> findByStatusAndIdBetweenOrderById(String status, long lowId, long highId, Pageable limit);
    Slice<ConcorContribution> findByIdInOrderById(Collection<Long> ids, Pageable limit);
}
