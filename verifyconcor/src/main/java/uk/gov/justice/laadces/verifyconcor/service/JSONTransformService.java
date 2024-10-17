package uk.gov.justice.laadces.verifyconcor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;

/**
 * Service to transform a Concor Contribution DTO to and from JSON.
 */
@RequiredArgsConstructor
@Service
public class JSONTransformService {
    private final ObjectMapper objectMapper;

    public String toJSON(final CONTRIBUTIONS dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public CONTRIBUTIONS fromJSON(final String json) {
        try {
            return objectMapper.readValue(json, CONTRIBUTIONS.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
