package uk.gov.justice.laadces.verifyconcor.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.SpecVersion;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.justice.laadces.verifyconcor.generated.CONTRIBUTIONS;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Jackson and Json-Schema-Validator beans and customizations.
 */
@Configuration
class JsonConfiguration {
    /**
     * Prevent "2024-05-21" in XMLGregorianCalendar from being serialized into JSON with a time and timezone,
     * like "2024-05-21T00:00:00Z" (by being cast to GregorianCalendar which can't have missing fields).
     */
    @JsonComponent
    static class XMLGregorianCalendarSerializer extends JsonSerializer<XMLGregorianCalendar> {
        @Override
        public void serialize(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeString(value.toXMLFormat());
            } else {
                gen.writeNull();
            }
        }
    }

    /**
     * Prevent "2024-05-21" in JSON from being deserialized into an XMLGregorianCalendar with a timezone,
     * like "2024-05-21Z" (if it were to be converted to a String using #toXMLFormat())).
     */
    @JsonComponent
    static class XMLGregorianCalendarDeserializer extends JsonDeserializer<XMLGregorianCalendar> {
        @Override
        public XMLGregorianCalendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String value = p.getValueAsString();
            return value != null ? DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(value) : null;
        }
    }

    /**
     * Same customizations as done on the ObjectMapper in the DCES DRC Integration service.
     * (Additional configuration for XMLGregorianCalendar deserialization.)
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder
                .mixIns(Arrays.stream(NON_NULL_CLASSES)
                        .collect(Collectors.toMap(Function.identity(), clazz -> NonNullMixIn.class)));
    }

    /**
     * Common MixIn class to apply to the DTO class.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class NonNullMixIn {
    }

    /**
     * Array of Concor Contribution classes to apply the NonNullMixIn MixIn to. See `jsonCustomizer()` method.
     */
    private static final Class<?>[] NON_NULL_CLASSES = {
            CONTRIBUTIONS.class,
            CONTRIBUTIONS.Applicant.class,
            CONTRIBUTIONS.Applicant.BankDetails.class,
            CONTRIBUTIONS.Applicant.DisabilitySummary.class,
            CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities.class,
            CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities.Disability.class,
            CONTRIBUTIONS.Applicant.EmploymentStatus.class,
            CONTRIBUTIONS.Applicant.HomeAddress.class,
            CONTRIBUTIONS.Applicant.HomeAddress.Detail.class,
            CONTRIBUTIONS.Applicant.Partner.class,
            CONTRIBUTIONS.Applicant.Partner.CiDetails.class,
            CONTRIBUTIONS.Applicant.PartnerDetails.class,
            CONTRIBUTIONS.Applicant.PartnerDetails.EmploymentStatus.class,
            CONTRIBUTIONS.Applicant.PostalAddress.class,
            CONTRIBUTIONS.Applicant.PostalAddress.Detail.class,
            CONTRIBUTIONS.Applicant.PreferredPaymentMethod.class,
            CONTRIBUTIONS.Application.class,
            CONTRIBUTIONS.Application.AppealType.class,
            CONTRIBUTIONS.Application.CaseType.class,
            CONTRIBUTIONS.Application.CcHardship.class,
            CONTRIBUTIONS.Application.MagsCourt.class,
            CONTRIBUTIONS.Application.OffenceType.class,
            CONTRIBUTIONS.Application.RepStatus.class,
            CONTRIBUTIONS.Application.Solicitor.class,
            CONTRIBUTIONS.Assessment.class,
            CONTRIBUTIONS.Assessment.AssessmentReason.class,
            CONTRIBUTIONS.Assessment.IncomeEvidenceList.class,
            CONTRIBUTIONS.Assessment.IncomeEvidenceList.IncomeEvidence.class,
            CONTRIBUTIONS.BreathingSpaceInfo.class,
            CONTRIBUTIONS.BreathingSpaceInfo.BreathingSpace.class,
            CONTRIBUTIONS.CapitalSummary.class,
            CONTRIBUTIONS.CapitalSummary.AssetList.class,
            CONTRIBUTIONS.CapitalSummary.AssetList.Asset.class,
            CONTRIBUTIONS.CapitalSummary.AssetList.Asset.Type.class,
            CONTRIBUTIONS.CapitalSummary.MotorVehicleOwnership.class,
            CONTRIBUTIONS.CapitalSummary.MotorVehicleOwnership.RegistrationList.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.PropertyType.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.Address.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.Address.Detail.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.ResidentialStatus.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.ThirdPartyList.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.ThirdPartyList.ThirdParty.class,
            CONTRIBUTIONS.CapitalSummary.PropertyList.Property.ThirdPartyList.ThirdParty.Relationship.class,
            CONTRIBUTIONS.CcOutcomes.class,
            CONTRIBUTIONS.CcOutcomes.CcOutcome.class,
            CONTRIBUTIONS.Correspondence.class,
            CONTRIBUTIONS.Correspondence.Letter.class,
            CONTRIBUTIONS.Equity.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.PropertyType.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.Address.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.Address.Detail.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.ResidentialStatus.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.ThirdParty.class,
            CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.ThirdParty.Relationship.class,
            CONTRIBUTIONS.Passported.class,
            CONTRIBUTIONS.Passported.Reason.class,
            CONTRIBUTIONS.Passported.Result.class,
    };

    /**
     * Concor Contribution JSON Schema bean used to validate the transformed Concor Contribution JSON.
     */
    @Bean
    JsonSchema jsonSchema() {
        SchemaValidatorsConfig config = SchemaValidatorsConfig.builder().formatAssertionsEnabled(true).build();
        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("schemas/concorContribution.opt.schema.json")) {
            return JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012).getSchema(resourceStream, config);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load JSON schema", e);
        }
    }
}
