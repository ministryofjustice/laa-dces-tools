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
        // varchar column that may contain junk.
        return migration.sql("SELECT DISTINCT CAST(maatid AS INTEGER) FROM marston.migrationscope WHERE maatid ~ '^[1-9][0-9]*$' ORDER BY 1").query(Long.class).list();
    }
}
