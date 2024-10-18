package uk.gov.justice.laadces.verifyconcor.config;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;

/**
 * JAXB configuration for the Concor Contribution XML transformation.
 */
@Configuration
class JaxbConfiguration {
    @Bean
    JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(CONTRIBUTIONS.class);
    }

    @Bean
    Marshaller marshaller(JAXBContext jaxbContext) throws JAXBException {
        return jaxbContext.createMarshaller();
    }

    @Bean
    Unmarshaller unmarshaller(JAXBContext jaxbContext) throws JAXBException {
        return jaxbContext.createUnmarshaller();
    }
}
