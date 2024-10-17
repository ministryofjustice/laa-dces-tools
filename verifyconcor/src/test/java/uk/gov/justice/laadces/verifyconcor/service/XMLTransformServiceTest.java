package uk.gov.justice.laadces.verifyconcor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class XMLTransformServiceTest {
    @Autowired
    private XMLTransformService xmlTransformService;

    @Test
    void givenValidXML_whenCallFromXML_thenReturnValidDTO() {
        // Arrange
        final var validXML = "<CONTRIBUTIONS><maat_id>1</maat_id></CONTRIBUTIONS>";
        // Act
        final var dto = xmlTransformService.fromXML(validXML);
        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getMaatId()).isEqualTo(1);
    }

    @Test
    void givenInvalidXML1_whenCallFromXML_thenThrowException() {
        // Arrange
        final var invalidXML1 = "<CONTRIBUTIONS><maat_id>Tannhauser Gate</maat_id></CONTRIBUTIONS>"; // maatId is string not integer.
        // Act
        final var dto = xmlTransformService.fromXML(invalidXML1); // better to throw exception, but not JAXB behavior.
        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getMaatId()).isNull();
    }

    @Test
    void givenInvalidXML2_whenCallFromXML_thenThrowException() {
        // Arrange
        final var invalidXML2 = "<CONTRIBUTIONS><spengler>Don&#39;t cross the streams</spengler></CONTRIBUTIONS>"; // missing required maatId.
        // Act
        final var dto = xmlTransformService.fromXML(invalidXML2);
        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getMaatId()).isNull();
    }

    @Test
    void givenNonXML_whenCallFromXML_thenThrowException() {
        // Arrange
        final var nonXML = "Yikes! This is not XML";
        // Act & Assert
        assertThatThrownBy(() -> xmlTransformService.fromXML(nonXML))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenValidDTO_whenCallToXML_thenReturnValidXML() {
        // Arrange
        final var validDTO = new CONTRIBUTIONS();
        validDTO.setMaatId(BigInteger.ONE);
        // Act
        final var xml = xmlTransformService.toXML(validDTO);
        // Assert
        assertThat(xml).isNotNull();
        assertThat(xml).isEqualTo("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><CONTRIBUTIONS><maat_id>1</maat_id></CONTRIBUTIONS>");
    }
}
