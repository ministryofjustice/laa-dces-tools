package uk.gov.justice.laadces.verifyconcor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class JSONTransformServiceTest {
    @Autowired
    private JSONTransformService jsonTransformService;

    @Test
    void givenValidJSON_whenCallFromJSON_thenReturnValidDTO() {
        // Arrange
        final var validJSON = "{\"maatId\":1}";
        // Act
        final var dto = jsonTransformService.fromJSON(validJSON);
        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getMaatId()).isEqualTo(1);
    }

    @Test
    void givenInvalidJSON1_whenCallFromJSON_thenThrowException() {
        // Arrange
        final var invalidJSON1 = "{\"maatId\":\"Tannhauser Gate\"}"; // maatId is string not integer.
        // Act & Assert
        assertThatThrownBy(() -> jsonTransformService.fromJSON(invalidJSON1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenInvalidJSON2_whenCallFromJSON_thenReturnInvalidDTO() {
        // Arrange
        final var invalidJSON2 = "{\"spengler\":\"Don't cross the streams\"}"; // missing required maatId.
        // Act
        final var dto = jsonTransformService.fromJSON(invalidJSON2);
        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getMaatId()).isNull(); // invalid DTO.
    }

    @Test
    void givenNonJSON_whenCallFromJSON_thenThrowException() {
        // Arrange
        final var nonJSON = "Yikes! This is not JSON";
        // Act & Assert
        assertThatThrownBy(() -> jsonTransformService.fromJSON(nonJSON))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenValidDTO_whenCallToJSON_thenReturnValidJSON() {
        // Arrange
        final var validDTO = new CONTRIBUTIONS();
        validDTO.setMaatId(BigInteger.ONE);
        // Act
        final var json = jsonTransformService.toJSON(validDTO);
        // Assert
        assertThat(json).isNotNull();
        assertThat(json).isEqualTo("{\"maatId\":1}");
    }
}
