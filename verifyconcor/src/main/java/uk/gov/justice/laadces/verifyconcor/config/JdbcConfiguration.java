package uk.gov.justice.laadces.verifyconcor.config;

import lombok.extern.slf4j.Slf4j;
import oracle.xdb.XMLType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Tell Spring Data JDBC how to convert an XMLType to a String.
 * <p>
 * This is needed for the XMLType column, FULL_XML, to be used as a String in the entity class.
 */
@Configuration
@Slf4j
class JdbcConfiguration extends AbstractJdbcConfiguration {
    @NonNull
    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(List.of(XMLTypeToStringConverter.INSTANCE));
    }

    /**
     * Using a lambda with List.of() seemed to cause compilation issues, so this is in long-hand.
     */
    private static class XMLTypeToStringConverter implements Converter<XMLType, String> {
        static XMLTypeToStringConverter INSTANCE = new XMLTypeToStringConverter();

        @Override
        public String convert(@NonNull XMLType source) {
            try {
                //noinspection deprecation
                return source.getStringVal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
