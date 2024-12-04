package uk.gov.justice.laadces.deltaconcor.service;

import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.deltaconcor.generated.CONTRIBUTIONS;
import uk.gov.justice.laadces.deltaconcor.report.Change;

import java.util.List;
import java.util.Objects;

/**
 * This class has a set of methods for updating Change instances based on the differences between two CONTRIBUTIONS
 * instances, or for combining multiple Change instances into a single Change instance by addition.
 * <p>
 * Note this is a concrete utility class, and has no relationship to the JRE Comparator or Comparable interfaces.
 */
@Component
public class ChangeService {
    /**
     * Adds the counts in `other` to the counts in `change`.
     * @param change Change counts to add `other` to (i.e. will contain the result).
     * @param other Change counts to be added to `change` (i.e. remains unchanged).
     */
    public void addTo(Change change, Change other) {
        change.setSentRecords(change.getSentRecords() + other.getSentRecords());
        change.setChangedRecords(change.getChangedRecords() + other.getChangedRecords());

        change.setApplicant(change.getApplicant() + other.getApplicant());
        change.setApplicant_firstName(change.getApplicant_firstName() + other.getApplicant_firstName());
        change.setApplicant_lastName(change.getApplicant_lastName() + other.getApplicant_lastName());
        change.setApplicant_dob(change.getApplicant_dob() + other.getApplicant_dob());
        change.setApplicant_niNumber(change.getApplicant_niNumber() + other.getApplicant_niNumber());
        change.setApplicant_landline(change.getApplicant_landline() + other.getApplicant_landline());
        change.setApplicant_mobile(change.getApplicant_mobile() + other.getApplicant_mobile());
        change.setApplicant_email(change.getApplicant_email() + other.getApplicant_email());
        change.setApplicant_preferredPaymentDay(change.getApplicant_preferredPaymentDay() + other.getApplicant_preferredPaymentDay());
        change.setApplicant_preferredPaymentMethod(change.getApplicant_preferredPaymentMethod() + other.getApplicant_preferredPaymentMethod());
        change.setApplicant_preferredPaymentMethod_code(change.getApplicant_preferredPaymentMethod_code() + other.getApplicant_preferredPaymentMethod_code());
        change.setApplicant_preferredPaymentMethod_description(change.getApplicant_preferredPaymentMethod_description() + other.getApplicant_preferredPaymentMethod_description());
        change.setApplicant_noFixedAbode(change.getApplicant_noFixedAbode() + other.getApplicant_noFixedAbode());
        change.setApplicant_specialInvestigation(change.getApplicant_specialInvestigation() + other.getApplicant_specialInvestigation());
        change.setApplicant_homeAddress(change.getApplicant_homeAddress() + other.getApplicant_homeAddress());
        change.setApplicant_homeAddress_detail(change.getApplicant_homeAddress_detail() + other.getApplicant_homeAddress_detail());
        change.setApplicant_homeAddress_detail_line1(change.getApplicant_homeAddress_detail_line1() + other.getApplicant_homeAddress_detail_line1());
        change.setApplicant_homeAddress_detail_line2(change.getApplicant_homeAddress_detail_line2() + other.getApplicant_homeAddress_detail_line2());
        change.setApplicant_homeAddress_detail_line3(change.getApplicant_homeAddress_detail_line3() + other.getApplicant_homeAddress_detail_line3());
        change.setApplicant_homeAddress_detail_city(change.getApplicant_homeAddress_detail_city() + other.getApplicant_homeAddress_detail_city());
        change.setApplicant_homeAddress_detail_country(change.getApplicant_homeAddress_detail_country() + other.getApplicant_homeAddress_detail_country());
        change.setApplicant_homeAddress_detail_postcode(change.getApplicant_homeAddress_detail_postcode() + other.getApplicant_homeAddress_detail_postcode());
        change.setApplicant_postalAddress(change.getApplicant_postalAddress() + other.getApplicant_postalAddress());
        change.setApplicant_postalAddress_detail(change.getApplicant_postalAddress_detail() + other.getApplicant_postalAddress_detail());
        change.setApplicant_postalAddress_detail_line1(change.getApplicant_postalAddress_detail_line1() + other.getApplicant_postalAddress_detail_line1());
        change.setApplicant_postalAddress_detail_line2(change.getApplicant_postalAddress_detail_line2() + other.getApplicant_postalAddress_detail_line2());
        change.setApplicant_postalAddress_detail_line3(change.getApplicant_postalAddress_detail_line3() + other.getApplicant_postalAddress_detail_line3());
        change.setApplicant_postalAddress_detail_city(change.getApplicant_postalAddress_detail_city() + other.getApplicant_postalAddress_detail_city());
        change.setApplicant_postalAddress_detail_country(change.getApplicant_postalAddress_detail_country() + other.getApplicant_postalAddress_detail_country());
        change.setApplicant_postalAddress_detail_postcode(change.getApplicant_postalAddress_detail_postcode() + other.getApplicant_postalAddress_detail_postcode());
        change.setApplicant_employmentStatus(change.getApplicant_employmentStatus() + other.getApplicant_employmentStatus());
        change.setApplicant_employmentStatus_code(change.getApplicant_employmentStatus_code() + other.getApplicant_employmentStatus_code());
        change.setApplicant_employmentStatus_description(change.getApplicant_employmentStatus_description() + other.getApplicant_employmentStatus_description());
        change.setApplicant_bankDetails(change.getApplicant_bankDetails() + other.getApplicant_bankDetails());
        change.setApplicant_bankDetails_sortCode(change.getApplicant_bankDetails_sortCode() + other.getApplicant_bankDetails_sortCode());
        change.setApplicant_bankDetails_accountNo(change.getApplicant_bankDetails_accountNo() + other.getApplicant_bankDetails_accountNo());
        change.setApplicant_bankDetails_accountName(change.getApplicant_bankDetails_accountName() + other.getApplicant_bankDetails_accountName());
        change.setApplicant_partner(change.getApplicant_partner() + other.getApplicant_partner());
        change.setApplicant_partner_hasPartner(change.getApplicant_partner_hasPartner() + other.getApplicant_partner_hasPartner());
        change.setApplicant_partner_contraryInterest(change.getApplicant_partner_contraryInterest() + other.getApplicant_partner_contraryInterest());
        change.setApplicant_partner_ciDetails(change.getApplicant_partner_ciDetails() + other.getApplicant_partner_ciDetails());
        change.setApplicant_partner_ciDetails_code(change.getApplicant_partner_ciDetails_code() + other.getApplicant_partner_ciDetails_code());
        change.setApplicant_partner_ciDetails_description(change.getApplicant_partner_ciDetails_description() + other.getApplicant_partner_ciDetails_description());
        change.setApplicant_partnerDetails(change.getApplicant_partnerDetails() + other.getApplicant_partnerDetails());
        change.setApplicant_partnerDetails_firstName(change.getApplicant_partnerDetails_firstName() + other.getApplicant_partnerDetails_firstName());
        change.setApplicant_partnerDetails_lastName(change.getApplicant_partnerDetails_lastName() + other.getApplicant_partnerDetails_lastName());
        change.setApplicant_partnerDetails_dob(change.getApplicant_partnerDetails_dob() + other.getApplicant_partnerDetails_dob());
        change.setApplicant_partnerDetails_niNumber(change.getApplicant_partnerDetails_niNumber() + other.getApplicant_partnerDetails_niNumber());
        change.setApplicant_partnerDetails_landline(change.getApplicant_partnerDetails_landline() + other.getApplicant_partnerDetails_landline());
        change.setApplicant_partnerDetails_employmentStatus(change.getApplicant_partnerDetails_employmentStatus() + other.getApplicant_partnerDetails_employmentStatus());
        change.setApplicant_partnerDetails_employmentStatus_code(change.getApplicant_partnerDetails_employmentStatus_code() + other.getApplicant_partnerDetails_employmentStatus_code());
        change.setApplicant_partnerDetails_employmentStatus_description(change.getApplicant_partnerDetails_employmentStatus_description() + other.getApplicant_partnerDetails_employmentStatus_description());
        change.setApplicant_disabilitySummary(change.getApplicant_disabilitySummary() + other.getApplicant_disabilitySummary());
        change.setApplicant_disabilitySummary_declaration(change.getApplicant_disabilitySummary_declaration() + other.getApplicant_disabilitySummary_declaration());
        change.setApplicant_disabilitySummary_disabilities(change.getApplicant_disabilitySummary_disabilities() + other.getApplicant_disabilitySummary_disabilities());
        change.setApplicant_disabilitySummary_disabilities_disability(change.getApplicant_disabilitySummary_disabilities_disability() + other.getApplicant_disabilitySummary_disabilities_disability());
        change.setApplicant_id(change.getApplicant_id() + other.getApplicant_id());

        change.setApplication(change.getApplication() + other.getApplication());
        change.setApplication_offenceType(change.getApplication_offenceType() + other.getApplication_offenceType());
        change.setApplication_offenceType_code(change.getApplication_offenceType_code() + other.getApplication_offenceType_code());
        change.setApplication_offenceType_description(change.getApplication_offenceType_description() + other.getApplication_offenceType_description());
        change.setApplication_caseType(change.getApplication_caseType() + other.getApplication_caseType());
        change.setApplication_caseType_code(change.getApplication_caseType_code() + other.getApplication_caseType_code());
        change.setApplication_caseType_description(change.getApplication_caseType_description() + other.getApplication_caseType_description());
        change.setApplication_repStatus(change.getApplication_repStatus() + other.getApplication_repStatus());
        change.setApplication_repStatus_status(change.getApplication_repStatus_status() + other.getApplication_repStatus_status());
        change.setApplication_repStatus_description(change.getApplication_repStatus_description() + other.getApplication_repStatus_description());
        change.setApplication_magsCourt(change.getApplication_magsCourt() + other.getApplication_magsCourt());
        change.setApplication_magsCourt_court(change.getApplication_magsCourt_court() + other.getApplication_magsCourt_court());
        change.setApplication_magsCourt_description(change.getApplication_magsCourt_description() + other.getApplication_magsCourt_description());
        change.setApplication_repStatusDate(change.getApplication_repStatusDate() + other.getApplication_repStatusDate());
        change.setApplication_arrestSummonsNumber(change.getApplication_arrestSummonsNumber() + other.getApplication_arrestSummonsNumber());
        change.setApplication_inCourtCustody(change.getApplication_inCourtCustody() + other.getApplication_inCourtCustody());
        change.setApplication_imprisoned(change.getApplication_imprisoned() + other.getApplication_imprisoned());
        change.setApplication_repOrderWithdrawalDate(change.getApplication_repOrderWithdrawalDate() + other.getApplication_repOrderWithdrawalDate());
        change.setApplication_committalDate(change.getApplication_committalDate() + other.getApplication_committalDate());
        change.setApplication_sentenceDate(change.getApplication_sentenceDate() + other.getApplication_sentenceDate());
        change.setApplication_appealType(change.getApplication_appealType() + other.getApplication_appealType());
        change.setApplication_appealType_code(change.getApplication_appealType_code() + other.getApplication_appealType_code());
        change.setApplication_appealType_description(change.getApplication_appealType_description() + other.getApplication_appealType_description());
        change.setApplication_ccHardship(change.getApplication_ccHardship() + other.getApplication_ccHardship());
        change.setApplication_ccHardship_reviewDate(change.getApplication_ccHardship_reviewDate() + other.getApplication_ccHardship_reviewDate());
        change.setApplication_ccHardship_reviewResult(change.getApplication_ccHardship_reviewResult() + other.getApplication_ccHardship_reviewResult());
        change.setApplication_solicitor(change.getApplication_solicitor() + other.getApplication_solicitor());
        change.setApplication_solicitor_accountCode(change.getApplication_solicitor_accountCode() + other.getApplication_solicitor_accountCode());
        change.setApplication_solicitor_name(change.getApplication_solicitor_name() + other.getApplication_solicitor_name());

        change.setAssessment(change.getAssessment() + other.getAssessment());
        change.setAssessment_effectiveDate(change.getAssessment_effectiveDate() + other.getAssessment_effectiveDate());
        change.setAssessment_monthlyContribution(change.getAssessment_monthlyContribution() + other.getAssessment_monthlyContribution());
        change.setAssessment_upfrontContribution(change.getAssessment_upfrontContribution() + other.getAssessment_upfrontContribution());
        change.setAssessment_incomeContributionCap(change.getAssessment_incomeContributionCap() + other.getAssessment_incomeContributionCap());
        change.setAssessment_assessmentReason(change.getAssessment_assessmentReason() + other.getAssessment_assessmentReason());
        change.setAssessment_assessmentReason_code(change.getAssessment_assessmentReason_code() + other.getAssessment_assessmentReason_code());
        change.setAssessment_assessmentReason_description(change.getAssessment_assessmentReason_description() + other.getAssessment_assessmentReason_description());
        change.setAssessment_assessmentDate(change.getAssessment_assessmentDate() + other.getAssessment_assessmentDate());
        change.setAssessment_upliftAppliedDate(change.getAssessment_upliftAppliedDate() + other.getAssessment_upliftAppliedDate());
        change.setAssessment_upliftRemovedDate(change.getAssessment_upliftRemovedDate() + other.getAssessment_upliftRemovedDate());
        change.setAssessment_incomeEvidenceList(change.getAssessment_incomeEvidenceList() + other.getAssessment_incomeEvidenceList());
        change.setAssessment_incomeEvidenceList_incomeEvidence(change.getAssessment_incomeEvidenceList_incomeEvidence() + other.getAssessment_incomeEvidenceList_incomeEvidence());
        change.setAssessment_sufficientDeclaredEquity(change.getAssessment_sufficientDeclaredEquity() + other.getAssessment_sufficientDeclaredEquity());
        change.setAssessment_sufficientVerifiedEquity(change.getAssessment_sufficientVerifiedEquity() + other.getAssessment_sufficientVerifiedEquity());
        change.setAssessment_sufficientCapitalandEquity(change.getAssessment_sufficientCapitalandEquity() + other.getAssessment_sufficientCapitalandEquity());

        change.setPassported(change.getPassported() + other.getPassported());
        change.setPassported_result(change.getPassported_result() + other.getPassported_result());
        change.setPassported_result_code(change.getPassported_result_code() + other.getPassported_result_code());
        change.setPassported_result_description(change.getPassported_result_description() + other.getPassported_result_description());
        change.setPassported_dateCompleted(change.getPassported_dateCompleted() + other.getPassported_dateCompleted());
        change.setPassported_reason(change.getPassported_reason() + other.getPassported_reason());
        change.setPassported_reason_code(change.getPassported_reason_code() + other.getPassported_reason_code());
        change.setPassported_reason_description(change.getPassported_reason_description() + other.getPassported_reason_description());

        change.setEquity(change.getEquity() + other.getEquity());
        change.setEquity_undeclaredProperty(change.getEquity_undeclaredProperty() + other.getEquity_undeclaredProperty());
        change.setEquity_equityVerifiedBy(change.getEquity_equityVerifiedBy() + other.getEquity_equityVerifiedBy());
        change.setEquity_equityVerifiedDate(change.getEquity_equityVerifiedDate() + other.getEquity_equityVerifiedDate());
        change.setEquity_equityVerified(change.getEquity_equityVerified() + other.getEquity_equityVerified());
        change.setEquity_propertyDescriptor(change.getEquity_propertyDescriptor() + other.getEquity_propertyDescriptor());
        change.setEquity_propertyDescriptor_bedRoomCount(change.getEquity_propertyDescriptor_bedRoomCount() + other.getEquity_propertyDescriptor_bedRoomCount());
        change.setEquity_propertyDescriptor_residentialStatus(change.getEquity_propertyDescriptor_residentialStatus() + other.getEquity_propertyDescriptor_residentialStatus());
        change.setEquity_propertyDescriptor_residentialStatus_code(change.getEquity_propertyDescriptor_residentialStatus_code() + other.getEquity_propertyDescriptor_residentialStatus_code());
        change.setEquity_propertyDescriptor_residentialStatus_description(change.getEquity_propertyDescriptor_residentialStatus_description() + other.getEquity_propertyDescriptor_residentialStatus_description());
        change.setEquity_propertyDescriptor_propertyType(change.getEquity_propertyDescriptor_propertyType() + other.getEquity_propertyDescriptor_propertyType());
        change.setEquity_propertyDescriptor_propertyType_code(change.getEquity_propertyDescriptor_propertyType_code() + other.getEquity_propertyDescriptor_propertyType_code());
        change.setEquity_propertyDescriptor_propertyType_description(change.getEquity_propertyDescriptor_propertyType_description() + other.getEquity_propertyDescriptor_propertyType_description());
        change.setEquity_propertyDescriptor_address(change.getEquity_propertyDescriptor_address() + other.getEquity_propertyDescriptor_address());
        change.setEquity_propertyDescriptor_address_detail(change.getEquity_propertyDescriptor_address_detail() + other.getEquity_propertyDescriptor_address_detail());
        change.setEquity_propertyDescriptor_address_detail_line1(change.getEquity_propertyDescriptor_address_detail_line1() + other.getEquity_propertyDescriptor_address_detail_line1());
        change.setEquity_propertyDescriptor_address_detail_line2(change.getEquity_propertyDescriptor_address_detail_line2() + other.getEquity_propertyDescriptor_address_detail_line2());
        change.setEquity_propertyDescriptor_address_detail_line3(change.getEquity_propertyDescriptor_address_detail_line3() + other.getEquity_propertyDescriptor_address_detail_line3());
        change.setEquity_propertyDescriptor_address_detail_city(change.getEquity_propertyDescriptor_address_detail_city() + other.getEquity_propertyDescriptor_address_detail_city());
        change.setEquity_propertyDescriptor_address_detail_country(change.getEquity_propertyDescriptor_address_detail_country() + other.getEquity_propertyDescriptor_address_detail_country());
        change.setEquity_propertyDescriptor_address_detail_postcode(change.getEquity_propertyDescriptor_address_detail_postcode() + other.getEquity_propertyDescriptor_address_detail_postcode());
        change.setEquity_propertyDescriptor_percentageApplicantOwned(change.getEquity_propertyDescriptor_percentageApplicantOwned() + other.getEquity_propertyDescriptor_percentageApplicantOwned());
        change.setEquity_propertyDescriptor_percentagePartnerOwned(change.getEquity_propertyDescriptor_percentagePartnerOwned() + other.getEquity_propertyDescriptor_percentagePartnerOwned());
        change.setEquity_propertyDescriptor_applicantEquityAmount(change.getEquity_propertyDescriptor_applicantEquityAmount() + other.getEquity_propertyDescriptor_applicantEquityAmount());
        change.setEquity_propertyDescriptor_partnerEquityAmount(change.getEquity_propertyDescriptor_partnerEquityAmount() + other.getEquity_propertyDescriptor_partnerEquityAmount());
        change.setEquity_propertyDescriptor_declaredMortgage(change.getEquity_propertyDescriptor_declaredMortgage() + other.getEquity_propertyDescriptor_declaredMortgage());
        change.setEquity_propertyDescriptor_declaredValue(change.getEquity_propertyDescriptor_declaredValue() + other.getEquity_propertyDescriptor_declaredValue());
        change.setEquity_propertyDescriptor_verifiedMortgage(change.getEquity_propertyDescriptor_verifiedMortgage() + other.getEquity_propertyDescriptor_verifiedMortgage());
        change.setEquity_propertyDescriptor_verifiedValue(change.getEquity_propertyDescriptor_verifiedValue() + other.getEquity_propertyDescriptor_verifiedValue());
        change.setEquity_propertyDescriptor_tenantInPlace(change.getEquity_propertyDescriptor_tenantInPlace() + other.getEquity_propertyDescriptor_tenantInPlace());
        change.setEquity_propertyDescriptor_thirdPartyList(change.getEquity_propertyDescriptor_thirdPartyList() + other.getEquity_propertyDescriptor_thirdPartyList());
        change.setEquity_propertyDescriptor_thirdPartyList_thirdParty(change.getEquity_propertyDescriptor_thirdPartyList_thirdParty() + other.getEquity_propertyDescriptor_thirdPartyList_thirdParty());

        change.setCapitalSummary(change.getCapitalSummary() + other.getCapitalSummary());
        change.setCapitalSummary_allEvidenceDate(change.getCapitalSummary_allEvidenceDate() + other.getCapitalSummary_allEvidenceDate());
        change.setCapitalSummary_totalCapitalAssets(change.getCapitalSummary_totalCapitalAssets() + other.getCapitalSummary_totalCapitalAssets());
        change.setCapitalSummary_noCapitalDeclared(change.getCapitalSummary_noCapitalDeclared() + other.getCapitalSummary_noCapitalDeclared());
        change.setCapitalSummary_capAllowanceWithheld(change.getCapitalSummary_capAllowanceWithheld() + other.getCapitalSummary_capAllowanceWithheld());
        change.setCapitalSummary_capAllowanceRestore(change.getCapitalSummary_capAllowanceRestore() + other.getCapitalSummary_capAllowanceRestore());
        change.setCapitalSummary_motorVehicleOwnership(change.getCapitalSummary_motorVehicleOwnership() + other.getCapitalSummary_motorVehicleOwnership());
        change.setCapitalSummary_motorVehicleOwnership_owner(change.getCapitalSummary_motorVehicleOwnership_owner() + other.getCapitalSummary_motorVehicleOwnership_owner());
        change.setCapitalSummary_motorVehicleOwnership_registrationList(change.getCapitalSummary_motorVehicleOwnership_registrationList() + other.getCapitalSummary_motorVehicleOwnership_registrationList());
        change.setCapitalSummary_motorVehicleOwnership_registrationList_registration(change.getCapitalSummary_motorVehicleOwnership_registrationList_registration() + other.getCapitalSummary_motorVehicleOwnership_registrationList_registration());
        change.setCapitalSummary_assetList(change.getCapitalSummary_assetList() + other.getCapitalSummary_assetList());
        change.setCapitalSummary_assetList_asset(change.getCapitalSummary_assetList_asset() + other.getCapitalSummary_assetList_asset());
        change.setCapitalSummary_propertyList(change.getCapitalSummary_propertyList() + other.getCapitalSummary_propertyList());
        change.setCapitalSummary_propertyList_property(change.getCapitalSummary_propertyList_property() + other.getCapitalSummary_propertyList_property());

        change.setCcOutcomes(change.getCcOutcomes() + other.getCcOutcomes());
        change.setCcOutcomes_ccOutcome(change.getCcOutcomes_ccOutcome() + other.getCcOutcomes_ccOutcome());

        change.setCorrespondence(change.getCorrespondence() + other.getCorrespondence());
        change.setCorrespondence_letter(change.getCorrespondence_letter() + other.getCorrespondence_letter());

        change.setBreathingSpaceInfo(change.getBreathingSpaceInfo() + other.getBreathingSpaceInfo());
        change.setBreathingSpaceInfo_breathingSpace(change.getBreathingSpaceInfo_breathingSpace() + other.getBreathingSpaceInfo_breathingSpace());
    }

    /**
     * Updates the change counts in `counts` based on the differences between `c1` and `c2`
     * @param o1 CONTRIBUTIONS first instance.
     * @param o2 CONTRIBUTIONS second instance.
     * @param change Change counts to update.
     * @return true if there were any differences; false if there were none.
     */
    public boolean compare(CONTRIBUTIONS o1, CONTRIBUTIONS o2, Change change) {
        boolean diff = false;
        if (compareApplicant(o1 != null ? o1.getApplicant() : null, o2 != null ? o2.getApplicant() : null, change)) {
            diff = true;
            change.setApplicant(change.getApplicant() + 1);
        }
        if (compareApplication(o1 != null ? o1.getApplication() : null, o2 != null ? o2.getApplication() : null, change)) {
            diff = true;
            change.setApplication(change.getApplication() + 1);
        }
        if (compareAssessment(o1 != null ? o1.getAssessment() : null, o2 != null ? o2.getAssessment() : null, change)) {
            diff = true;
            change.setAssessment(change.getAssessment() + 1);
        }
        if (comparePassported(o1 != null ? o1.getPassported() : null, o2 != null ? o2.getPassported() : null, change)) {
            diff = true;
            change.setPassported(change.getPassported() + 1);
        }
        if (compareEquity(o1 != null ? o1.getEquity() : null, o2 != null ? o2.getEquity() : null, change)) {
            diff = true;
            change.setEquity(change.getEquity() + 1);
        }
        if (compareCapitalSummary(o1 != null ? o1.getCapitalSummary() : null, o2 != null ? o2.getCapitalSummary() : null, change)) {
            diff = true;
            change.setCapitalSummary(change.getCapitalSummary() + 1);
        }
        if (compareCcOutcomes(o1 != null ? o1.getCcOutcomes() : null, o2 != null ? o2.getCcOutcomes() : null, change)) {
            diff = true;
            change.setCcOutcomes(change.getCcOutcomes() + 1);
        }
        if (compareCorrespondence(o1 != null ? o1.getCorrespondence() : null, o2 != null ? o2.getCorrespondence() : null, change)) {
            diff = true;
            change.setCorrespondence(change.getCorrespondence() + 1);
        }
        if (compareBreathingSpaceInfo(o1 != null ? o1.getBreathingSpaceInfo() : null, o2 != null ? o2.getBreathingSpaceInfo() : null, change)) {
            diff = true;
            change.setBreathingSpaceInfo(change.getBreathingSpaceInfo() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getId() : null, o2 != null ? o2.getId() : null)) {
            diff = true;
            // calling method's responsibility to increment total.
        }
        // We deliberately avoid comparing `maatId` and `flag`.
        return diff;
    }

    private boolean compareApplicant(CONTRIBUTIONS.Applicant o1, CONTRIBUTIONS.Applicant o2, Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getFirstName() : null, o2 != null ? o2.getFirstName() : null)) {
            diff = true;
            counts.setApplicant_firstName(counts.getApplicant_firstName() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLastName() : null, o2 != null ? o2.getLastName() : null)) {
            diff = true;
            counts.setApplicant_lastName(counts.getApplicant_lastName() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDob() : null, o2 != null ? o2.getDob() : null)) {
            diff = true;
            counts.setApplicant_dob(counts.getApplicant_dob() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getNiNumber() : null, o2 != null ? o2.getNiNumber() : null)) {
            diff = true;
            counts.setApplicant_niNumber(counts.getApplicant_niNumber() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLandline() : null, o2 != null ? o2.getLandline() : null)) {
            diff = true;
            counts.setApplicant_landline(counts.getApplicant_landline() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getMobile() : null, o2 != null ? o2.getMobile() : null)) {
            diff = true;
            counts.setApplicant_mobile(counts.getApplicant_mobile() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getEmail() : null, o2 != null ? o2.getEmail() : null)) {
            diff = true;
            counts.setApplicant_email(counts.getApplicant_email() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPreferredPaymentDay() : null, o2 != null ? o2.getPreferredPaymentDay() : null)) {
            diff = true;
            counts.setApplicant_preferredPaymentDay(counts.getApplicant_preferredPaymentDay() + 1);
        }
        if (compareApplicant_PreferredPaymentMethod(o1 != null ? o1.getPreferredPaymentMethod() : null, o2 != null ? o2.getPreferredPaymentMethod() : null, counts)) {
            diff = true;
            counts.setApplicant_preferredPaymentMethod(counts.getApplicant_preferredPaymentMethod() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getNoFixedAbode() : null, o2 != null ? o2.getNoFixedAbode() : null)) {
            diff = true;
            counts.setApplicant_noFixedAbode(counts.getApplicant_noFixedAbode() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getSpecialInvestigation() : null, o2 != null ? o2.getSpecialInvestigation() : null)) {
            diff = true;
            counts.setApplicant_specialInvestigation(counts.getApplicant_specialInvestigation() + 1);
        }
        if (compareApplicant_HomeAddress(o1 != null ? o1.getHomeAddress() : null, o2 != null ? o2.getHomeAddress() : null, counts)) {
            diff = true;
            counts.setApplicant_homeAddress(counts.getApplicant_homeAddress() + 1);
        }
        if (compareApplicant_PostalAddress(o1 != null ? o1.getPostalAddress() : null, o2 != null ? o2.getPostalAddress() : null, counts)) {
            diff = true;
            counts.setApplicant_postalAddress(counts.getApplicant_postalAddress() + 1);
        }
        if (compareApplicant_EmploymentStatus(o1 != null ? o1.getEmploymentStatus() : null, o2 != null ? o2.getEmploymentStatus() : null, counts)) {
            diff = true;
            counts.setApplicant_employmentStatus(counts.getApplicant_employmentStatus() + 1);
        }
        if (compareApplicant_BankDetails(o1 != null ? o1.getBankDetails() : null, o2 != null ? o2.getBankDetails() : null, counts)) {
            diff = true;
            counts.setApplicant_bankDetails(counts.getApplicant_bankDetails() + 1);
        }
        if (compareApplicant_Partner(o1 != null ? o1.getPartner() : null, o2 != null ? o2.getPartner() : null, counts)) {
            diff = true;
            counts.setApplicant_partner(counts.getApplicant_partner() + 1);
        }
        if (compareApplicant_PartnerDetails(o1 != null ? o1.getPartnerDetails() : null, o2 != null ? o2.getPartnerDetails() : null, counts)) {
            diff = true;
            counts.setApplicant_partnerDetails(counts.getApplicant_partnerDetails() + 1);
        }
        if (compareApplicant_DisabilitySummary(o1 != null ? o1.getDisabilitySummary() : null, o2 != null ? o2.getDisabilitySummary() : null, counts)) {
            diff = true;
            counts.setApplicant_disabilitySummary(counts.getApplicant_disabilitySummary() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getId() : null, o2 != null ? o2.getId() : null)) {
            diff = true;
            // calling method's responsibility to increment applicant.
        }
        return diff;
    }

    private boolean compareApplicant_PreferredPaymentMethod(CONTRIBUTIONS.Applicant.PreferredPaymentMethod o1,
                                                            CONTRIBUTIONS.Applicant.PreferredPaymentMethod o2,
                                                            Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplicant_preferredPaymentMethod_code(counts.getApplicant_preferredPaymentMethod_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplicant_preferredPaymentMethod_description(counts.getApplicant_preferredPaymentMethod_description() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_HomeAddress(CONTRIBUTIONS.Applicant.HomeAddress o1,
                                                 CONTRIBUTIONS.Applicant.HomeAddress o2,
                                                 Change counts) {
        boolean diff = false;
        if (compareApplicant_HomeAddress_Detail(o1 != null ? o1.getDetail() : null, o2 != null ? o2.getDetail() : null, counts)) {
            diff = true;
            counts.setApplicant_homeAddress_detail(counts.getApplicant_homeAddress_detail() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_HomeAddress_Detail(CONTRIBUTIONS.Applicant.HomeAddress.Detail o1,
                                                        CONTRIBUTIONS.Applicant.HomeAddress.Detail o2,
                                                        Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getLine1() : null, o2 != null ? o2.getLine1() : null)) {
            diff = true;
            counts.setApplicant_homeAddress_detail_line1(counts.getApplicant_homeAddress_detail_line1() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLine2() : null, o2 != null ? o2.getLine2() : null)) {
            diff = true;
            counts.setApplicant_homeAddress_detail_line2(counts.getApplicant_homeAddress_detail_line2() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLine3() : null, o2 != null ? o2.getLine3() : null)) {
            diff = true;
            counts.setApplicant_homeAddress_detail_line3(counts.getApplicant_homeAddress_detail_line3() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCity() : null, o2 != null ? o2.getCity() : null)) {
            diff = true;
            counts.setApplicant_homeAddress_detail_city(counts.getApplicant_homeAddress_detail_city() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCountry() : null, o2 != null ? o2.getCountry() : null)) {
            diff = true;
            counts.setApplicant_homeAddress_detail_country(counts.getApplicant_homeAddress_detail_country() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPostcode() : null, o2 != null ? o2.getPostcode() : null)) {
            diff = true;
            counts.setApplicant_homeAddress_detail_postcode(counts.getApplicant_homeAddress_detail_postcode() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_PostalAddress(CONTRIBUTIONS.Applicant.PostalAddress o1,
                                                   CONTRIBUTIONS.Applicant.PostalAddress o2,
                                                   Change counts) {
        boolean diff = false;
        if (compareApplicant_PostalAddress_Detail(o1 != null ? o1.getDetail() : null, o2 != null ? o2.getDetail() : null, counts)) {
            diff = true;
            counts.setApplicant_postalAddress_detail(counts.getApplicant_postalAddress_detail() + 1);
        }
        return diff;
    }

    // Can we make the different address types common?
    private boolean compareApplicant_PostalAddress_Detail(CONTRIBUTIONS.Applicant.PostalAddress.Detail o1,
                                                          CONTRIBUTIONS.Applicant.PostalAddress.Detail o2,
                                                          Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getLine1() : null, o2 != null ? o2.getLine1() : null)) {
            diff = true;
            counts.setApplicant_postalAddress_detail_line1(counts.getApplicant_postalAddress_detail_line1() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLine2() : null, o2 != null ? o2.getLine2() : null)) {
            diff = true;
            counts.setApplicant_postalAddress_detail_line2(counts.getApplicant_postalAddress_detail_line2() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLine3() : null, o2 != null ? o2.getLine3() : null)) {
            diff = true;
            counts.setApplicant_postalAddress_detail_line3(counts.getApplicant_postalAddress_detail_line3() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCity() : null, o2 != null ? o2.getCity() : null)) {
            diff = true;
            counts.setApplicant_postalAddress_detail_city(counts.getApplicant_postalAddress_detail_city() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCountry() : null, o2 != null ? o2.getCountry() : null)) {
            diff = true;
            counts.setApplicant_postalAddress_detail_country(counts.getApplicant_postalAddress_detail_country() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPostcode() : null, o2 != null ? o2.getPostcode() : null)) {
            diff = true;
            counts.setApplicant_postalAddress_detail_postcode(counts.getApplicant_postalAddress_detail_postcode() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_EmploymentStatus(CONTRIBUTIONS.Applicant.EmploymentStatus o1,
                                                      CONTRIBUTIONS.Applicant.EmploymentStatus o2,
                                                      Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplicant_employmentStatus_code(counts.getApplicant_employmentStatus_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplicant_employmentStatus_description(counts.getApplicant_employmentStatus_description() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_BankDetails(CONTRIBUTIONS.Applicant.BankDetails o1,
                                                 CONTRIBUTIONS.Applicant.BankDetails o2,
                                                 Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getSortCode() : null, o2 != null ? o2.getSortCode() : null)) {
            diff = true;
            counts.setApplicant_bankDetails_sortCode(counts.getApplicant_bankDetails_sortCode() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getAccountNo() : null, o2 != null ? o2.getAccountNo() : null)) {
            diff = true;
            counts.setApplicant_bankDetails_accountNo(counts.getApplicant_bankDetails_accountNo() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getAccountName() : null, o2 != null ? o2.getAccountName() : null)) {
            diff = true;
            counts.setApplicant_bankDetails_accountName(counts.getApplicant_bankDetails_accountName() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_Partner(CONTRIBUTIONS.Applicant.Partner o1,
                                             CONTRIBUTIONS.Applicant.Partner o2,
                                             Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getHasPartner() : null, o2 != null ? o2.getHasPartner() : null)) {
            diff = true;
            counts.setApplicant_partner_hasPartner(counts.getApplicant_partner_hasPartner() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getContraryInterest() : null, o2 != null ? o2.getContraryInterest() : null)) {
            diff = true;
            counts.setApplicant_partner_contraryInterest(counts.getApplicant_partner_contraryInterest() + 1);
        }
        if (compareApplicant_Partner_CiDetails(o1 != null ? o1.getCiDetails() : null, o2 != null ? o2.getCiDetails() : null, counts)) {
            diff = true;
            counts.setApplicant_partner_ciDetails(counts.getApplicant_partner_ciDetails() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_Partner_CiDetails(CONTRIBUTIONS.Applicant.Partner.CiDetails o1,
                                                       CONTRIBUTIONS.Applicant.Partner.CiDetails o2,
                                                       Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplicant_partner_ciDetails_code(counts.getApplicant_partner_ciDetails_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplicant_partner_ciDetails_description(counts.getApplicant_partner_ciDetails_description() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_PartnerDetails(CONTRIBUTIONS.Applicant.PartnerDetails o1,
                                                    CONTRIBUTIONS.Applicant.PartnerDetails o2,
                                                    Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getFirstName() : null, o2 != null ? o2.getFirstName() : null)) {
            diff = true;
            counts.setApplicant_partnerDetails_firstName(counts.getApplicant_partnerDetails_firstName() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLastName() : null, o2 != null ? o2.getLastName() : null)) {
            diff = true;
            counts.setApplicant_partnerDetails_lastName(counts.getApplicant_partnerDetails_lastName() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDob() : null, o2 != null ? o2.getDob() : null)) {
            diff = true;
            counts.setApplicant_partnerDetails_dob(counts.getApplicant_partnerDetails_dob() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getNiNumber() : null, o2 != null ? o2.getNiNumber() : null)) {
            diff = true;
            counts.setApplicant_partnerDetails_niNumber(counts.getApplicant_partnerDetails_niNumber() + 1);
        }
        if (compareApplicant_PartnerDetails_EmploymentStatus(o1 != null ? o1.getEmploymentStatus() : null, o2 != null ? o2.getEmploymentStatus() : null, counts)) {
            diff = true;
            counts.setApplicant_partnerDetails_employmentStatus(counts.getApplicant_partnerDetails_employmentStatus() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_PartnerDetails_EmploymentStatus(CONTRIBUTIONS.Applicant.PartnerDetails.EmploymentStatus o1,
                                                                     CONTRIBUTIONS.Applicant.PartnerDetails.EmploymentStatus o2,
                                                                     Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplicant_partnerDetails_employmentStatus_code(counts.getApplicant_partnerDetails_employmentStatus_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplicant_partnerDetails_employmentStatus_description(counts.getApplicant_partnerDetails_employmentStatus_description() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_DisabilitySummary(CONTRIBUTIONS.Applicant.DisabilitySummary o1,
                                                       CONTRIBUTIONS.Applicant.DisabilitySummary o2,
                                                       Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getDeclaration() : null, o2 != null ? o2.getDeclaration() : null)) {
            diff = true;
            counts.setApplicant_disabilitySummary_declaration(counts.getApplicant_disabilitySummary_declaration() + 1);
        }
        if (compareApplicant_DisabilitySummary_Disabilities(o1 != null ? o1.getDisabilities() : null, o2 != null ? o2.getDisabilities() : null, counts)) {
            diff = true;
            counts.setApplicant_disabilitySummary_disabilities(counts.getApplicant_disabilitySummary_disabilities() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_DisabilitySummary_Disabilities(CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities o1,
                                                                    CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities o2,
                                                                    Change counts) {
        boolean diff = false;
        if (compareApplicant_DisabilitySummary_Disabilities_Disability(o1 != null ? o1.getDisability() : null, o2 != null ? o2.getDisability() : null, counts)) {
            diff = true;
            counts.setApplicant_disabilitySummary_disabilities_disability(counts.getApplicant_disabilitySummary_disabilities_disability() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_DisabilitySummary_Disabilities_Disability(List<CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities.Disability> o1,
                                                                               List<CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities.Disability> o2,
                                                                               Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareApplication(CONTRIBUTIONS.Application o1, CONTRIBUTIONS.Application o2, Change counts) {
        boolean diff = false;
        if (compareApplication_OffenceType(o1 != null ? o1.getOffenceType() : null, o2 != null ? o2.getOffenceType() : null, counts)) {
            diff = true;
            counts.setApplication_offenceType(counts.getApplication_offenceType() + 1);
        }
        if (compareApplication_CaseType(o1 != null ? o1.getCaseType() : null, o2 != null ? o2.getCaseType() : null, counts)) {
            diff = true;
            counts.setApplication_caseType(counts.getApplication_caseType() + 1);
        }
        if (compareApplication_RepStatus(o1 != null ? o1.getRepStatus() : null, o2 != null ? o2.getRepStatus() : null, counts)) {
            diff = true;
            counts.setApplication_repStatus(counts.getApplication_repStatus() + 1);
        }
        if (compareApplication_MagsCourt(o1 != null ? o1.getMagsCourt() : null, o2 != null ? o2.getMagsCourt() : null, counts)) {
            diff = true;
            counts.setApplication_magsCourt(counts.getApplication_magsCourt() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getRepStatusDate() : null, o2 != null ? o2.getRepStatusDate() : null)) {
            diff = true;
            counts.setApplication_repStatusDate(counts.getApplication_repStatusDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getArrestSummonsNumber() : null, o2 != null ? o2.getArrestSummonsNumber() : null)) {
            diff = true;
            counts.setApplication_arrestSummonsNumber(counts.getApplication_arrestSummonsNumber() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getInCourtCustody() : null, o2 != null ? o2.getInCourtCustody() : null)) {
            diff = true;
            counts.setApplication_inCourtCustody(counts.getApplication_inCourtCustody() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getImprisoned() : null, o2 != null ? o2.getImprisoned() : null)) {
            diff = true;
            counts.setApplication_imprisoned(counts.getApplication_imprisoned() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getRepOrderWithdrawalDate() : null, o2 != null ? o2.getRepOrderWithdrawalDate() : null)) {
            diff = true;
            counts.setApplication_repOrderWithdrawalDate(counts.getApplication_repOrderWithdrawalDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCommittalDate() : null, o2 != null ? o2.getCommittalDate() : null)) {
            diff = true;
            counts.setApplication_committalDate(counts.getApplication_committalDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getSentenceDate() : null, o2 != null ? o2.getSentenceDate() : null)) {
            diff = true;
            counts.setApplication_sentenceDate(counts.getApplication_sentenceDate() + 1);
        }
        if (compareApplication_AppealType(o1 != null ? o1.getAppealType() : null, o2 != null ? o2.getAppealType() : null, counts)) {
            diff = true;
            counts.setApplication_appealType(counts.getApplication_appealType() + 1);
        }
        if (compareApplication_CcHardship(o1 != null ? o1.getCcHardship() : null, o2 != null ? o2.getCcHardship() : null, counts)) {
            diff = true;
            counts.setApplication_ccHardship(counts.getApplication_ccHardship() + 1);
        }
        if (compareApplication_Solicitor(o1 != null ? o1.getSolicitor() : null, o2 != null ? o2.getSolicitor() : null, counts)) {
            diff = true;
            counts.setApplication_solicitor(counts.getApplication_solicitor() + 1);
        }
        return diff;
    }

    private boolean compareApplication_OffenceType(CONTRIBUTIONS.Application.OffenceType o1,
                                                   CONTRIBUTIONS.Application.OffenceType o2,
                                                   Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplication_offenceType_code(counts.getApplication_offenceType_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplication_offenceType_description(counts.getApplication_offenceType_description() + 1);
        }
        return diff;
    }

    private boolean compareApplication_CaseType(CONTRIBUTIONS.Application.CaseType o1,
                                                CONTRIBUTIONS.Application.CaseType o2,
                                                Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplication_caseType_code(counts.getApplication_caseType_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplication_caseType_description(counts.getApplication_caseType_description() + 1);
        }
        return diff;
    }

    private boolean compareApplication_RepStatus(CONTRIBUTIONS.Application.RepStatus o1,
                                                 CONTRIBUTIONS.Application.RepStatus o2,
                                                 Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getStatus() : null, o2 != null ? o2.getStatus() : null)) {
            diff = true;
            counts.setApplication_repStatus_status(counts.getApplication_repStatus_status() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplication_caseType_description(counts.getApplication_repStatus_description() + 1);
        }
        return diff;
    }

    private boolean compareApplication_MagsCourt(CONTRIBUTIONS.Application.MagsCourt o1,
                                                 CONTRIBUTIONS.Application.MagsCourt o2,
                                                 Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCourt() : null, o2 != null ? o2.getCourt() : null)) {
            diff = true;
            counts.setApplication_magsCourt_court(counts.getApplication_magsCourt_court() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplication_magsCourt_description(counts.getApplication_magsCourt_description() + 1);
        }
        return diff;
    }

    private boolean compareApplication_AppealType(CONTRIBUTIONS.Application.AppealType o1,
                                                  CONTRIBUTIONS.Application.AppealType o2,
                                                  Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setApplication_appealType_code(counts.getApplication_appealType_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setApplication_appealType_description(counts.getApplication_appealType_description() + 1);
        }
        return diff;
    }

    private boolean compareApplication_CcHardship(CONTRIBUTIONS.Application.CcHardship o1,
                                                  CONTRIBUTIONS.Application.CcHardship o2,
                                                  Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getReviewDate() : null, o2 != null ? o2.getReviewDate() : null)) {
            diff = true;
            counts.setApplication_ccHardship_reviewDate(counts.getApplication_ccHardship_reviewDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getReviewResult() : null, o2 != null ? o2.getReviewResult() : null)) {
            diff = true;
            counts.setApplication_ccHardship_reviewResult(counts.getApplication_ccHardship_reviewResult() + 1);
        }
        return diff;
    }

    private boolean compareApplication_Solicitor(CONTRIBUTIONS.Application.Solicitor o1,
                                                 CONTRIBUTIONS.Application.Solicitor o2,
                                                 Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getAccountCode() : null, o2 != null ? o2.getAccountCode() : null)) {
            diff = true;
            counts.setApplication_solicitor_accountCode(counts.getApplication_solicitor_accountCode() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getName() : null, o2 != null ? o2.getName() : null)) {
            diff = true;
            counts.setApplication_solicitor_name(counts.getApplication_solicitor_name() + 1);
        }
        return diff;
    }

    private boolean compareAssessment(CONTRIBUTIONS.Assessment o1, CONTRIBUTIONS.Assessment o2, Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getEffectiveDate() : null, o2 != null ? o2.getEffectiveDate() : null)) {
            diff = true;
            counts.setAssessment_effectiveDate(counts.getAssessment_effectiveDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getMonthlyContribution() : null, o2 != null ? o2.getMonthlyContribution() : null)) {
            diff = true;
            counts.setAssessment_monthlyContribution(counts.getAssessment_monthlyContribution() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getUpfrontContribution() : null, o2 != null ? o2.getUpfrontContribution() : null)) {
            diff = true;
            counts.setAssessment_upfrontContribution(counts.getAssessment_upfrontContribution() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getIncomeContributionCap() : null, o2 != null ? o2.getIncomeContributionCap() : null)) {
            diff = true;
            counts.setAssessment_incomeContributionCap(counts.getAssessment_incomeContributionCap() + 1);
        }
        if (compareAssessment_AssessmentReason(o1 != null ? o1.getAssessmentReason() : null, o2 != null ? o2.getAssessmentReason() : null, counts)) {
            diff = true;
            counts.setAssessment_assessmentReason(counts.getAssessment_assessmentReason() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getAssessmentDate() : null, o2 != null ? o2.getAssessmentDate() : null)) {
            diff = true;
            counts.setAssessment_assessmentDate(counts.getAssessment_assessmentDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getUpliftAppliedDate() : null, o2 != null ? o2.getUpliftAppliedDate() : null)) {
            diff = true;
            counts.setAssessment_upliftAppliedDate(counts.getAssessment_upliftAppliedDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getUpliftRemovedDate() : null, o2 != null ? o2.getUpliftRemovedDate() : null)) {
            diff = true;
            counts.setAssessment_upliftRemovedDate(counts.getAssessment_upliftRemovedDate() + 1);
        }
        if (compareAssessment_IncomeEvidenceList(o1 != null ? o1.getIncomeEvidenceList() : null, o2 != null ? o2.getIncomeEvidenceList() : null, counts)) {
            diff = true;
            counts.setAssessment_incomeEvidenceList(counts.getAssessment_incomeEvidenceList() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getSufficientDeclaredEquity() : null, o2 != null ? o2.getSufficientDeclaredEquity() : null)) {
            diff = true;
            counts.setAssessment_sufficientDeclaredEquity(counts.getAssessment_sufficientDeclaredEquity() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getSufficientVerifiedEquity() : null, o2 != null ? o2.getSufficientVerifiedEquity() : null)) {
            diff = true;
            counts.setAssessment_sufficientVerifiedEquity(counts.getAssessment_sufficientVerifiedEquity() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getSufficientCapitalandEquity() : null, o2 != null ? o2.getSufficientCapitalandEquity() : null)) {
            diff = true;
            counts.setAssessment_sufficientCapitalandEquity(counts.getAssessment_sufficientCapitalandEquity() + 1);
        }
        return diff;
    }

    private boolean compareAssessment_AssessmentReason(CONTRIBUTIONS.Assessment.AssessmentReason o1,
                                                       CONTRIBUTIONS.Assessment.AssessmentReason o2,
                                                       Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setAssessment_assessmentReason_code(counts.getAssessment_assessmentReason_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setAssessment_assessmentReason_description(counts.getAssessment_assessmentReason_description() + 1);
        }
        return diff;
    }

    private boolean compareAssessment_IncomeEvidenceList(CONTRIBUTIONS.Assessment.IncomeEvidenceList o1,
                                                         CONTRIBUTIONS.Assessment.IncomeEvidenceList o2,
                                                         Change counts) {
        boolean diff = false;
        if (compareAssessment_IncomeEvidenceList_IncomeEvidence(o1 != null ? o1.getIncomeEvidence() : null, o2 != null ? o2.getIncomeEvidence() : null, counts)) {
            diff = true;
            counts.setAssessment_incomeEvidenceList_incomeEvidence(counts.getAssessment_incomeEvidenceList_incomeEvidence() + 1);
        }
        return diff;
    }

    private boolean compareAssessment_IncomeEvidenceList_IncomeEvidence(List<CONTRIBUTIONS.Assessment.IncomeEvidenceList.IncomeEvidence> o1,
                                                                        List<CONTRIBUTIONS.Assessment.IncomeEvidenceList.IncomeEvidence> o2,
                                                                        Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean comparePassported(CONTRIBUTIONS.Passported o1, CONTRIBUTIONS.Passported o2, Change counts) {
        boolean diff = false;
        if (comparePassported_Result(o1 != null ? o1.getResult() : null, o2 != null ? o2.getResult() : null, counts)) {
            diff = true;
            counts.setPassported_result(counts.getPassported_result() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDateCompleted() : null, o2 != null ? o2.getDateCompleted() : null)) {
            diff = true;
            counts.setPassported_dateCompleted(counts.getPassported_dateCompleted() + 1);
        }
        if (comparePassported_Reason(o1 != null ? o1.getReason() : null, o2 != null ? o2.getReason() : null, counts)) {
            diff = true;
            counts.setPassported_reason(counts.getPassported_reason() + 1);
        }
        return diff;
    }

    private boolean comparePassported_Result(CONTRIBUTIONS.Passported.Result o1,
                                             CONTRIBUTIONS.Passported.Result o2,
                                             Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setPassported_result_code(counts.getPassported_result_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setPassported_result_description(counts.getPassported_result_description() + 1);
        }
        return diff;
    }

    private boolean comparePassported_Reason(CONTRIBUTIONS.Passported.Reason o1,
                                             CONTRIBUTIONS.Passported.Reason o2,
                                             Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setPassported_reason_code(counts.getPassported_reason_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setPassported_reason_description(counts.getPassported_result_description() + 1);
        }
        return diff;
    }

    private boolean compareEquity(CONTRIBUTIONS.Equity o1, CONTRIBUTIONS.Equity o2, Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getUndeclaredProperty() : null, o2 != null ? o2.getUndeclaredProperty() : null)) {
            diff = true;
            counts.setEquity_undeclaredProperty(counts.getEquity_undeclaredProperty() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getEquityVerifiedBy() : null, o2 != null ? o2.getEquityVerifiedBy() : null)) {
            diff = true;
            counts.setEquity_equityVerifiedBy(counts.getEquity_equityVerifiedBy() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getEquityVerifiedDate() : null, o2 != null ? o2.getEquityVerifiedDate() : null)) {
            diff = true;
            counts.setEquity_equityVerifiedDate(counts.getEquity_equityVerifiedDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getEquityVerified() : null, o2 != null ? o2.getEquityVerified() : null)) {
            diff = true;
            counts.setEquity_equityVerified(counts.getEquity_equityVerified() + 1);
        }
        if (compareEquity_PropertyDescriptor(o1 != null ? o1.getPropertyDescriptor() : null, o2 != null ? o2.getPropertyDescriptor() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor(counts.getEquity_propertyDescriptor() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor(CONTRIBUTIONS.Equity.PropertyDescriptor o1,
                                                     CONTRIBUTIONS.Equity.PropertyDescriptor o2,
                                                     Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getBedRoomCount() : null, o2 != null ? o2.getBedRoomCount() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_bedRoomCount(counts.getEquity_propertyDescriptor_bedRoomCount() + 1);
        }
        if (compareEquity_PropertyDescriptor_ResidentialStatus(o1 != null ? o1.getResidentialStatus() : null, o2 != null ? o2.getResidentialStatus() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_residentialStatus(counts.getEquity_propertyDescriptor_residentialStatus() + 1);
        }
        if (compareEquity_PropertyDescriptor_PropertyType(o1 != null ? o1.getPropertyType() : null, o2 != null ? o2.getPropertyType() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_propertyType(counts.getEquity_propertyDescriptor_propertyType() + 1);
        }
        if (compareEquity_PropertyDescriptor_Address(o1 != null ? o1.getAddress() : null, o2 != null ? o2.getAddress() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address(counts.getEquity_propertyDescriptor_address() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPercentageApplicantOwned() : null, o2 != null ? o2.getPercentageApplicantOwned() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_percentageApplicantOwned(counts.getEquity_propertyDescriptor_percentageApplicantOwned() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPercentagePartnerOwned() : null, o2 != null ? o2.getPercentagePartnerOwned() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_percentagePartnerOwned(counts.getEquity_propertyDescriptor_percentagePartnerOwned() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getApplicantEquityAmount() : null, o2 != null ? o2.getApplicantEquityAmount() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_applicantEquityAmount(counts.getEquity_propertyDescriptor_applicantEquityAmount() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPartnerEquityAmount() : null, o2 != null ? o2.getPartnerEquityAmount() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_partnerEquityAmount(counts.getEquity_propertyDescriptor_partnerEquityAmount() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDeclaredMortgage() : null, o2 != null ? o2.getDeclaredMortgage() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_declaredMortgage(counts.getEquity_propertyDescriptor_declaredMortgage() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDeclaredValue() : null, o2 != null ? o2.getDeclaredValue() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_declaredValue(counts.getEquity_propertyDescriptor_declaredValue() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getVerifiedMortgage() : null, o2 != null ? o2.getVerifiedMortgage() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_verifiedMortgage(counts.getEquity_propertyDescriptor_verifiedMortgage() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getVerifiedValue() : null, o2 != null ? o2.getVerifiedValue() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_verifiedValue(counts.getEquity_propertyDescriptor_verifiedValue() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getTenantInPlace() : null, o2 != null ? o2.getTenantInPlace() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_tenantInPlace(counts.getEquity_propertyDescriptor_tenantInPlace() + 1);
        }
        if (compareEquity_PropertyDescriptor_ThirdPartyList(o1 != null ? o1.getThirdPartyList() : null, o2 != null ? o2.getThirdPartyList() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_thirdPartyList(counts.getEquity_propertyDescriptor_thirdPartyList() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_ResidentialStatus(CONTRIBUTIONS.Equity.PropertyDescriptor.ResidentialStatus o1,
                                                                       CONTRIBUTIONS.Equity.PropertyDescriptor.ResidentialStatus o2,
                                                                       Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_residentialStatus_code(counts.getEquity_propertyDescriptor_residentialStatus_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_residentialStatus_description(counts.getEquity_propertyDescriptor_residentialStatus_description() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_PropertyType(CONTRIBUTIONS.Equity.PropertyDescriptor.PropertyType o1,
                                                                  CONTRIBUTIONS.Equity.PropertyDescriptor.PropertyType o2,
                                                                  Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getCode() : null, o2 != null ? o2.getCode() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_propertyType_code(counts.getEquity_propertyDescriptor_propertyType_code() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getDescription() : null, o2 != null ? o2.getDescription() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_propertyType_description(counts.getEquity_propertyDescriptor_propertyType_description() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_Address(CONTRIBUTIONS.Equity.PropertyDescriptor.Address o1,
                                                             CONTRIBUTIONS.Equity.PropertyDescriptor.Address o2,
                                                             Change counts) {
        boolean diff = false;
        if (compareEquity_PropertyDescriptor_Address_Detail(o1 != null ? o1.getDetail() : null, o2 != null ? o2.getDetail() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail(counts.getEquity_propertyDescriptor_address_detail() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_Address_Detail(CONTRIBUTIONS.Equity.PropertyDescriptor.Address.Detail o1,
                                                                    CONTRIBUTIONS.Equity.PropertyDescriptor.Address.Detail o2,
                                                                    Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getLine1() : null, o2 != null ? o2.getLine1() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail_line1(counts.getEquity_propertyDescriptor_address_detail_line1() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLine2() : null, o2 != null ? o2.getLine2() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail_line2(counts.getEquity_propertyDescriptor_address_detail_line2() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getLine3() : null, o2 != null ? o2.getLine3() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail_line3(counts.getEquity_propertyDescriptor_address_detail_line3() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCity() : null, o2 != null ? o2.getCity() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail_city(counts.getEquity_propertyDescriptor_address_detail_city() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCountry() : null, o2 != null ? o2.getCountry() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail_country(counts.getEquity_propertyDescriptor_address_detail_country() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getPostcode() : null, o2 != null ? o2.getPostcode() : null)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail_postcode(counts.getEquity_propertyDescriptor_address_detail_postcode() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_ThirdPartyList(CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList o1,
                                                                    CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList o2,
                                                                    Change counts) {
        boolean diff = false;
        if (compareEquity_PropertyDescriptor_ThirdPartyList_ThirdParty(o1 != null ? o1.getThirdParty() : null, o2 != null ? o2.getThirdParty() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_thirdPartyList_thirdParty(counts.getEquity_propertyDescriptor_thirdPartyList_thirdParty() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_ThirdPartyList_ThirdParty(List<CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.ThirdParty> o1,
                                                                               List<CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.ThirdParty> o2,
                                                                               Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCapitalSummary(CONTRIBUTIONS.CapitalSummary o1, CONTRIBUTIONS.CapitalSummary o2, Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getAllEvidenceDate() : null, o2 != null ? o2.getAllEvidenceDate() : null)) {
            diff = true;
            counts.setCapitalSummary_allEvidenceDate(counts.getCapitalSummary_allEvidenceDate() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getTotalCapitalAssets() : null, o2 != null ? o2.getTotalCapitalAssets() : null)) {
            diff = true;
            counts.setCapitalSummary_totalCapitalAssets(counts.getCapitalSummary_totalCapitalAssets() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getNoCapitalDeclared() : null, o2 != null ? o2.getNoCapitalDeclared() : null)) {
            diff = true;
            counts.setCapitalSummary_noCapitalDeclared(counts.getCapitalSummary_noCapitalDeclared() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCapAllowanceWithheld() : null, o2 != null ? o2.getCapAllowanceWithheld() : null)) {
            diff = true;
            counts.setCapitalSummary_capAllowanceWithheld(counts.getCapitalSummary_capAllowanceWithheld() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getCapAllowanceRestore() : null, o2 != null ? o2.getCapAllowanceRestore() : null)) {
            diff = true;
            counts.setCapitalSummary_capAllowanceRestore(counts.getCapitalSummary_capAllowanceRestore() + 1);
        }
        if (compareCapitalSummary_MotorVehicleOwnership(o1 != null ? o1.getMotorVehicleOwnership() : null, o2 != null ? o2.getMotorVehicleOwnership() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_motorVehicleOwnership(counts.getCapitalSummary_motorVehicleOwnership() + 1);
        }
        if (compareCapitalSummary_AssetList(o1 != null ? o1.getAssetList() : null, o2 != null ? o2.getAssetList() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_assetList(counts.getCapitalSummary_assetList() + 1);
        }
        if (compareCapitalSummary_PropertyList(o1 != null ? o1.getPropertyList() : null, o2 != null ? o2.getPropertyList() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_propertyList(counts.getCapitalSummary_propertyList() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_MotorVehicleOwnership(CONTRIBUTIONS.CapitalSummary.MotorVehicleOwnership o1,
                                                                CONTRIBUTIONS.CapitalSummary.MotorVehicleOwnership o2,
                                                                Change counts) {
        boolean diff = false;
        if (!Objects.equals(o1 != null ? o1.getOwner() : null, o2 != null ? o2.getOwner() : null)) {
            diff = true;
            counts.setCapitalSummary_motorVehicleOwnership_owner(counts.getCapitalSummary_motorVehicleOwnership_owner() + 1);
        }
        if (compareCapitalSummary_MotorVehicleOwnership_RegistrationList(o1 != null ? o1.getRegistrationList() : null, o2 != null ? o2.getRegistrationList() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_motorVehicleOwnership_registrationList(counts.getCapitalSummary_motorVehicleOwnership_registrationList() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_MotorVehicleOwnership_RegistrationList(CONTRIBUTIONS.CapitalSummary.MotorVehicleOwnership.RegistrationList o1,
                                                                                 CONTRIBUTIONS.CapitalSummary.MotorVehicleOwnership.RegistrationList o2,
                                                                                 Change counts) {
        boolean diff = false;
        if (compareCapitalSummary_MotorVehicleOwnership_RegistrationList_Registration(o1 != null ? o1.getRegistration() : null, o2 != null ? o2.getRegistration() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_motorVehicleOwnership_registrationList_registration(counts.getCapitalSummary_motorVehicleOwnership_registrationList_registration() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_MotorVehicleOwnership_RegistrationList_Registration(List<String> o1,
                                                                                              List<String> o2,
                                                                                              Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCapitalSummary_AssetList(CONTRIBUTIONS.CapitalSummary.AssetList o1,
                                                    CONTRIBUTIONS.CapitalSummary.AssetList o2,
                                                    Change counts) {
        boolean diff = false;
        if (compareCapitalSummary_AssetList_Asset(o1 != null ? o1.getAsset() : null, o2 != null ? o2.getAsset() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_assetList_asset(counts.getCapitalSummary_assetList_asset() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_AssetList_Asset(List<CONTRIBUTIONS.CapitalSummary.AssetList.Asset> o1,
                                                          List<CONTRIBUTIONS.CapitalSummary.AssetList.Asset> o2,
                                                          Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCapitalSummary_PropertyList(CONTRIBUTIONS.CapitalSummary.PropertyList o1,
                                                       CONTRIBUTIONS.CapitalSummary.PropertyList o2,
                                                       Change counts) {
        boolean diff = false;
        if (compareCapitalSummary_PropertyList_Property(o1 != null ? o1.getProperty() : null, o2 != null ? o2.getProperty() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_propertyList_property(counts.getCapitalSummary_propertyList_property() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_PropertyList_Property(List<CONTRIBUTIONS.CapitalSummary.PropertyList.Property> o1,
                                                                List<CONTRIBUTIONS.CapitalSummary.PropertyList.Property> o2,
                                                                Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCcOutcomes(CONTRIBUTIONS.CcOutcomes o1, CONTRIBUTIONS.CcOutcomes o2, Change counts) {
        boolean diff = false;
        if (compareCcOutcomes_CcOutcome(o1 != null ? o1.getCcOutcome() : null, o2 != null ? o2.getCcOutcome() : null, counts)) {
            diff = true;
            counts.setCcOutcomes_ccOutcome(counts.getCcOutcomes_ccOutcome() + 1);
        }
        return diff;
    }

    private boolean compareCcOutcomes_CcOutcome(List<CONTRIBUTIONS.CcOutcomes.CcOutcome> o1,
                                                List<CONTRIBUTIONS.CcOutcomes.CcOutcome> o2,
                                                Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCorrespondence(CONTRIBUTIONS.Correspondence o1, CONTRIBUTIONS.Correspondence o2, Change counts) {
        boolean diff = false;
        if (compareCorrespondence_Letter(o1 != null ? o1.getLetter() : null, o2 != null ? o2.getLetter() : null, counts)) {
            diff = true;
            counts.setCorrespondence_letter(counts.getCorrespondence_letter() + 1);
        }
        return diff;
    }

    private boolean compareCorrespondence_Letter(List<CONTRIBUTIONS.Correspondence.Letter> o1,
                                                 List<CONTRIBUTIONS.Correspondence.Letter> o2,
                                                 Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareBreathingSpaceInfo(CONTRIBUTIONS.BreathingSpaceInfo o1,
                                              CONTRIBUTIONS.BreathingSpaceInfo o2,
                                              Change counts) {
        boolean diff = false;
        if (compareBreathingSpaceInfo_BreathingSpace(o1 != null ? o1.getBreathingSpace() : null, o2 != null ? o2.getBreathingSpace() : null, counts)) {
            diff = true;
            counts.setBreathingSpaceInfo_breathingSpace(counts.getBreathingSpaceInfo_breathingSpace() + 1);
        }
        return diff;
    }

    private boolean compareBreathingSpaceInfo_BreathingSpace(List<CONTRIBUTIONS.BreathingSpaceInfo.BreathingSpace> o1,
                                                             List<CONTRIBUTIONS.BreathingSpaceInfo.BreathingSpace> o2,
                                                             Change counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }
}
