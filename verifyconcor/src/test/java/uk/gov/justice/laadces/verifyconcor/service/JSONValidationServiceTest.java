package uk.gov.justice.laadces.verifyconcor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class JSONValidationServiceTest {
    @Autowired
    private JSONValidationService jsonValidationService;

    @Test
    void givenValidJSON_whenCallValidateJSON_thenReturnEmpty() {
        // Arrange
        final var validJSON = "{\"maatId\":1}";
        // Act
        final var violations = jsonValidationService.validateJSON(validJSON);
        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    void givenInvalidJSON1_whenCallValidateJSON_thenReturnViolations() {
        // Arrange
        final var invalidJSON1 = "{\"maatId\":\"Tannhauser Gate\"}"; // maatId is string not integer.
        // Act
        final var violations = jsonValidationService.validateJSON(invalidJSON1);
        // Assert
        assertThat(violations).isNotEmpty();
    }

    @Test
    void givenInvalidJSON2_whenCallValidateJSON_thenReturnViolations() {
        // Arrange
        final var invalidJSON2 = "{\"spengler\":\"Don't cross the streams\"}"; // missing required maatId.
        // Act
        final var violations = jsonValidationService.validateJSON(invalidJSON2);
        // Assert
        assertThat(violations).isNotEmpty();
    }

    @Test
    void givenNonJSON_whenCallValidateJSON_thenThrowException() {
        // Arrange
        final var nonJSON = "Yikes! This is not JSON";
        // Act & Assert
        assertThatThrownBy(() -> jsonValidationService.validateJSON(nonJSON))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
