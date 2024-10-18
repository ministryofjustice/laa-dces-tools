package uk.gov.justice.laadces.verifyconcor.service;

import com.networknt.schema.InputFormat;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service to validate Concor Contribution JSON against the Concor Contribution JSON schema.
 */
@RequiredArgsConstructor
@Service
public class JSONValidationService {
    private final JsonSchema jsonSchema;

    public List<String> validateJSON(final String json) {
        Set<ValidationMessage> errors = jsonSchema.validate(json, InputFormat.JSON);
        return errors.stream().map(ValidationMessage::getMessage).toList();
    }
}
