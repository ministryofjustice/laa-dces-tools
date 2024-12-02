package uk.gov.justice.laadces.deltaconcor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository for accessing the CONCOR_CONTRIBUTIONS table but in a way that doesn't involve Spring Data JDBC.
 * For example, methods that don't return a ConcorContribution entity instance or list.
 */
@Repository
@RequiredArgsConstructor
public class ConcorTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public Long maxIdBefore(LocalDate endDate) {
        return jdbcTemplate.queryForObject("SELECT MAX(id) FROM concor_contributions WHERE date_created < ?", Long.class, endDate);
    }

    public Long minIdAfterOrEquals(LocalDate startDate) {
        return jdbcTemplate.queryForObject("SELECT MIN(id) FROM concor_contributions WHERE date_created >= ?", Long.class,startDate);
    }
}
