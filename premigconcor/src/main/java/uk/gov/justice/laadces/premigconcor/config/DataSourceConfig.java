package uk.gov.justice.laadces.premigconcor.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

/**
 * Configure some qualified datasources for the three databases.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties integrationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public HikariDataSource integrationDataSource(@Qualifier("integrationDataSourceProperties") DataSourceProperties integrationDataSourceProperties) {
        return integrationDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public JdbcClient integrationJdbcClient(@Qualifier("integrationDataSource") DataSource integrationDataSource) {
        return JdbcClient.create(integrationDataSource);
    }

    @Bean
    @ConfigurationProperties("migration.datasource")
    public DataSourceProperties migrationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public HikariDataSource migrationDataSource(@Qualifier("migrationDataSourceProperties") DataSourceProperties migrationDataSourceProperties) {
        return migrationDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcClient migrationJdbcClient(@Qualifier("migrationDataSource") DataSource migrationDataSource) {
        return JdbcClient.create(migrationDataSource);
    }

    @Bean
    @ConfigurationProperties("maat.datasource")
    public DataSourceProperties maatDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public HikariDataSource maatDataSource(@Qualifier("maatDataSourceProperties") DataSourceProperties maatDataSourceProperties) {
        return maatDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcClient maatJdbcClient(@Qualifier("maatDataSource") DataSource maatDataSource) {
        return JdbcClient.create(maatDataSource);
    }
}
