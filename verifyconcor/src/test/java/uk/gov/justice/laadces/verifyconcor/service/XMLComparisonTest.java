package uk.gov.justice.laadces.verifyconcor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class XMLComparisonTest {
    @Autowired
    private XMLComparisonService xmlComparisonService;

    @Test
    void givenIdenticalXML_whenCompareXML_thenReturnEmpty() {
        // Arrange
        final var xml1 = "<root><element1>value1</element1><element2>value2</element2></root>";
        final var xml2 = "<root><element1>value1</element1><element2>value2</element2></root>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isEmpty();
    }

    @Test
    void givenNonidenticalXML_whenCompareXML_thenReturnDiffs() {
        // Arrange
        final var xml1 = "<root><element1>value1</element1><element2>value2</element2></root>";
        final var xml2 = "<rootOther><elementOther1>valueOther1</elementOther1><elementOther2>valueOther2</elementOther2></rootOther>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isNotEmpty(); // Should be different.
    }

    @Test
    void givenSimilarXML1_whenCompareXML_thenReturnEmpty() {
        // Arrange - non-empty amount (and some other) elements are compared as numbers
        final var xml1 = "<root><amount>.01</amount><amount>-.99</amount></root>";
        final var xml2 = "<root><amount>0.01</amount><amount>-0.99</amount></root>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isEmpty();
    }

    @Test
    void givenSimilarXML2_whenCompareXML_thenReturnDiffs() {
        // Arrange - non-amount non-empty elements are not compared as numbers
        final var xml1 = "<root><geoff>.01</geoff><bob>-.99</bob></root>";
        final var xml2 = "<root><geoff>0.01</geoff><bob>-0.99</bob></root>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isNotEmpty(); // Should be different.
    }

    @Test
    void givenSimilarXML3_whenCompareXML_thenReturnEmpty() {
        // Arrange - empty self-closing letter/printed element can be omitted.
        final var xml1 = "<correspondence><letter><id>99</id><sent>2023-01-04</sent><printed/></letter></correspondence>";
        final var xml2 = "<correspondence><letter><id>99</id><sent>2023-01-04</sent></letter></correspondence>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isEmpty();
    }

    @Test
    void givenSimilarXML4_whenCompareXML_thenReturnEmpty() {
        // Arrange - empty open/closed letter/printed element can be omitted.
        final var xml1 = "<correspondence><letter><id>99</id><sent>2023-01-04</sent><printed></printed></letter></correspondence>";
        final var xml2 = "<correspondence><letter><id>99</id><sent>2023-01-04</sent></letter></correspondence>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isEmpty();
    }

    @Test
    void givenSimilarXML5_whenCompareXML_thenReturnDiffs() {
        // Arrange - non-empty letter/printed element cannot be omitted.
        final var xml1 = "<correspondence><letter><id>99</id><sent>2023-01-04</sent><printed>2023-01-03</printed></letter></correspondence>";
        final var xml2 = "<correspondence><letter><id>99</id><sent>2023-01-04</sent></letter></correspondence>";
        // Act
        final var diffs = xmlComparisonService.compareXML(xml1, xml2);
        // Assert
        assertThat(diffs).isNotEmpty(); // Should be different.
    }

}
