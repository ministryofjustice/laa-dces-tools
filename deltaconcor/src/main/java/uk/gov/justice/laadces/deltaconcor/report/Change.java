package uk.gov.justice.laadces.deltaconcor.report;

import lombok.Data;

@Data
public class Change {
    private final String date; // predefined (YYYY-MM-DD) passed to constructor

    private int sentRecords; // agg
    private int changedRecords; // agg

    private int applicant; // agg
    private int applicant_firstName; // leaf String
    private int applicant_lastName; // leaf String
    private int applicant_dob; // leaf LocalDate
    private int applicant_niNumber; // leaf String
    private int applicant_landline; //leaf String
    private int applicant_mobile; //leaf String
    private int applicant_email; // leaf String
    private int applicant_preferredPaymentDay; // leaf Long
    private int applicant_preferredPaymentMethod; // agg
    private int applicant_preferredPaymentMethod_code; // leaf String
    private int applicant_preferredPaymentMethod_description; // leaf String
    private int applicant_noFixedAbode; // leaf String
    private int applicant_specialInvestigation; // leaf String
    private int applicant_homeAddress; // agg
    private int applicant_homeAddress_detail; // agg
    private int applicant_homeAddress_detail_line1; // leaf String
    private int applicant_homeAddress_detail_line2; // leaf String
    private int applicant_homeAddress_detail_line3; // leaf String
    private int applicant_homeAddress_detail_city; // leaf String
    private int applicant_homeAddress_detail_country; // leaf String
    private int applicant_homeAddress_detail_postcode; // leaf String
    private int applicant_postalAddress; // agg
    private int applicant_postalAddress_detail; // agg
    private int applicant_postalAddress_detail_line1; // leaf String
    private int applicant_postalAddress_detail_line2; // leaf String
    private int applicant_postalAddress_detail_line3; // leaf String
    private int applicant_postalAddress_detail_city; // leaf String
    private int applicant_postalAddress_detail_country; // leaf String
    private int applicant_postalAddress_detail_postcode; // leaf String
    private int applicant_employmentStatus; // agg
    private int applicant_employmentStatus_code; // leaf String
    private int applicant_employmentStatus_description; // leaf String
    private int applicant_bankDetails; // agg
    private int applicant_bankDetails_sortCode; // leaf String
    private int applicant_bankDetails_accountNo; // leaf Long
    private int applicant_bankDetails_accountName; // leaf String
    private int applicant_partner; // agg
    private int applicant_partner_hasPartner; // leaf String
    private int applicant_partner_contraryInterest; // leaf String
    private int applicant_partner_ciDetails; // agg
    private int applicant_partner_ciDetails_code; // leaf String
    private int applicant_partner_ciDetails_description; // leaf String
    private int applicant_partnerDetails; // agg
    private int applicant_partnerDetails_firstName; // leaf String
    private int applicant_partnerDetails_lastName; // leaf String
    private int applicant_partnerDetails_dob; // leaf LocalDate
    private int applicant_partnerDetails_niNumber; // leaf String
    private int applicant_partnerDetails_landline; // leaf String
    private int applicant_partnerDetails_employmentStatus; // agg
    private int applicant_partnerDetails_employmentStatus_code; // leaf String
    private int applicant_partnerDetails_employmentStatus_description; // leaf String
    private int applicant_disabilitySummary; // agg
    private int applicant_disabilitySummary_declaration; // leaf String
    private int applicant_disabilitySummary_disabilities; // agg
    private int applicant_disabilitySummary_disabilities_disability; // array agg (code/description)
    private int applicant_id; // leaf Long

    private int application; // agg
    private int application_offenceType; // agg
    private int application_offenceType_code; // leaf String
    private int application_offenceType_description; // leaf String
    private int application_caseType; // agg
    private int application_caseType_code; // leaf String
    private int application_caseType_description; // leaf String
    private int application_repStatus; // agg
    private int application_repStatus_status; // leaf String
    private int application_repStatus_description; // leaf String
    private int application_magsCourt; // agg
    private int application_magsCourt_court; // leaf Long
    private int application_magsCourt_description; // leaf String
    private int application_repStatusDate; // leaf LocalDate
    private int application_arrestSummonsNumber; // leaf String
    private int application_inCourtCustody; // leaf String
    private int application_imprisoned; // leaf String
    private int application_repOrderWithdrawalDate; // leaf LocalDate
    private int application_committalDate; // leaf LocalDate
    private int application_sentenceDate; // leaf LocalDate
    private int application_appealType; // agg
    private int application_appealType_code; // leaf String
    private int application_appealType_description; // leaf String
    private int application_ccHardship; // agg
    private int application_ccHardship_reviewDate; // leaf LocalDate
    private int application_ccHardship_reviewResult; // leaf String
    private int application_solicitor; // agg
    private int application_solicitor_accountCode; // leaf String
    private int application_solicitor_name; // leaf String

    private int assessment; // agg
    private int assessment_effectiveDate; // leaf LocalDate
    private int assessment_monthlyContribution; // leaf BigDecimal
    private int assessment_upfrontContribution; // leaf BigDecimal
    private int assessment_incomeContributionCap; // leaf BigDecimal
    private int assessment_assessmentReason; // agg
    private int assessment_assessmentReason_code; // leaf String
    private int assessment_assessmentReason_description; // leaf String
    private int assessment_assessmentDate; // leaf LocalDate
    private int assessment_upliftAppliedDate; // leaf LocalDate
    private int assessment_upliftRemovedDate; // leaf LocalDate
    private int assessment_incomeEvidenceList; // agg
    private int assessment_incomeEvidenceList_incomeEvidence; // arra agg (evidence/otherText/mandatory/dateReceived)
    private int assessment_sufficientDeclaredEquity; // leaf String
    private int assessment_sufficientVerifiedEquity; // leaf String
    private int assessment_sufficientCapitalandEquity; // leaf String

    private int passported; // agg
    private int passported_result; // agg
    private int passported_result_code; // leaf String
    private int passported_result_description; // leaf String
    private int passported_dateCompleted; // leaf LocalDate
    private int passported_reason; // agg
    private int passported_reason_code; // leaf String
    private int passported_reason_description; // leaf String

    private int equity; // agg
    private int equity_undeclaredProperty; // leaf String
    private int equity_equityVerifiedBy; // leaf String
    private int equity_equityVerifiedDate; // leaf LocalDate
    private int equity_equityVerified; // leaf String
    private int equity_propertyDescriptor; // agg
    private int equity_propertyDescriptor_bedRoomCount; // leaf String
    private int equity_propertyDescriptor_residentialStatus; // agg
    private int equity_propertyDescriptor_residentialStatus_code; // leaf String
    private int equity_propertyDescriptor_residentialStatus_description; // leaf String
    private int equity_propertyDescriptor_propertyType; // agg
    private int equity_propertyDescriptor_propertyType_code; // leaf String
    private int equity_propertyDescriptor_propertyType_description; // leaf String
    private int equity_propertyDescriptor_address; // agg
    private int equity_propertyDescriptor_address_detail; // agg
    private int equity_propertyDescriptor_address_detail_line1; // leaf String
    private int equity_propertyDescriptor_address_detail_line2; // leaf String
    private int equity_propertyDescriptor_address_detail_line3; // leaf String
    private int equity_propertyDescriptor_address_detail_city; // leaf String
    private int equity_propertyDescriptor_address_detail_country; // leaf String
    private int equity_propertyDescriptor_address_detail_postcode; // leaf String
    private int equity_propertyDescriptor_percentageApplicantOwned; // leaf BigDecimal
    private int equity_propertyDescriptor_percentagePartnerOwned; // leaf BigDecimal
    private int equity_propertyDescriptor_applicantEquityAmount; // leaf BigDecimal
    private int equity_propertyDescriptor_partnerEquityAmount; // leaf BigDecimal
    private int equity_propertyDescriptor_declaredMortgage; // leaf BigDecimal
    private int equity_propertyDescriptor_declaredValue; // leaf BigDecimal
    private int equity_propertyDescriptor_verifiedMortgage; // leaf BigDecimal
    private int equity_propertyDescriptor_verifiedValue; // leaf BigDecimal
    private int equity_propertyDescriptor_tenantInPlace; // leaf String
    private int equity_propertyDescriptor_thirdPartyList; // agg
    private int equity_propertyDescriptor_thirdPartyList_thirdParty; // array agg (name/relationship/otherDescription/dateCreated/dateDeleted)

    private int capitalSummary; // agg
    private int capitalSummary_allEvidenceDate; // leaf LocalDate
    private int capitalSummary_totalCapitalAssets; // leaf BigDecimal
    private int capitalSummary_noCapitalDeclared; // leaf String
    private int capitalSummary_capAllowanceWithheld; // leaf LocalDate
    private int capitalSummary_capAllowanceRestore; // leaf LocalDate
    private int capitalSummary_motorVehicleOwnership; // agg
    private int capitalSummary_motorVehicleOwnership_owner; // leaf String
    private int capitalSummary_motorVehicleOwnership_registrationList; // agg
    private int capitalSummary_motorVehicleOwnership_registrationList_registration; // array leaf String
    private int capitalSummary_assetList; // agg
    private int capitalSummary_assetList_asset; // array agg (type/amount/verified/otherDescription/dateVerified/evidenceReceivedDate)
    private int capitalSummary_propertyList; // agg
    private int capitalSummary_propertyList_property; // array agg (bedRoomCount/residentialStatus/propertyType/address/percentageApplicantOwned/percentagePartnerOwned/applicantEquityAmount/partnerEquityAmount/declaredMortgage/declaredValue/verifiedMortgage/verifiedValue/tenantInPlace/thirdPartyList)

    private int ccOutcomes; // agg
    private int ccOutcomes_ccOutcome; // array agg (code/date)

    private int correspondence; // agg
    private int correspondence_letter; // array agg (ref/id/type/created/printed)

    private int breathingSpaceInfo; // agg
    private int breathingSpaceInfo_breathingSpace; // array agg (id/type/status/bsStartDate/bsEndDate/debtAmt/debtRefNo/dateModified)
}
