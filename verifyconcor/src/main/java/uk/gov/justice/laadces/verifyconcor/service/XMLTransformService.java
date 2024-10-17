package uk.gov.justice.laadces.verifyconcor.service;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Service to transform a Concor Contribution DTO to and from XML.
 */
@RequiredArgsConstructor
@Service
public class XMLTransformService {
    private static final QName _CONTRIBUTIONS_QNAME = new QName("", "CONTRIBUTIONS");
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public String toXML(final CONTRIBUTIONS dto) {
        final var writer = new StringWriter();
        try {
            marshaller.marshal(createCONTRIBUTIONS(dto), writer);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
        return writer.toString();
    }

    public CONTRIBUTIONS fromXML(final String xml) {
        final var source = new StreamSource(new StringReader(xml));
        try {
            return unmarshaller.unmarshal(source, CONTRIBUTIONS.class).getValue();
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static JAXBElement<CONTRIBUTIONS> createCONTRIBUTIONS(CONTRIBUTIONS value) {
        return new JAXBElement<>(_CONTRIBUTIONS_QNAME, CONTRIBUTIONS.class, value);
    }
}
