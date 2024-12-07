package uk.gov.justice.laadces.premigconcor.dao.migration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MigrationScopeRepository {
    private final JdbcClient migration;

    public MigrationScopeRepository(@Qualifier("migrationJdbcClient") final JdbcClient migration) {
        this.migration = migration;
    }

    public List<Long> findAll() {
        // varchar column that may contain junk; no need to order as would not be numeric order anyway.
        return migration.sql("""
                SELECT DISTINCT CAST(clientcasereference AS INTEGER) AS maat_id
                FROM transform.laacasedetails
                WHERE clientcasereference ~ '^[1-9][0-9]*$'
                """).query(Long.class).list();
    }
}
