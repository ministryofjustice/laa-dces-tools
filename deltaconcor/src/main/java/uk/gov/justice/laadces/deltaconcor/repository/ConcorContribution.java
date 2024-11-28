package uk.gov.justice.laadces.deltaconcor.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a row in the CONCOR_CONTRIBUTIONS table.
 */
@Table("CONCOR_CONTRIBUTIONS")
public record ConcorContribution(@Id Long id,
                                 Long repId,
                                 LocalDateTime dateCreated,
                                 LocalDate dateModified,
                                 String status,
                                 Long contribFileId,
                                 String fullXml) {
}
