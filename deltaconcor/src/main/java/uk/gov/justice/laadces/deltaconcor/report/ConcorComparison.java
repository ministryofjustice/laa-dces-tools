package uk.gov.justice.laadces.deltaconcor.report;

import org.springframework.stereotype.Component;
import uk.gov.justice.laadces.deltaconcor.generated.CONTRIBUTIONS;

import java.util.List;
import java.util.Objects;

/**
 * This class has a set of methods for updating a ConcorChangeCounts instance based on the
 * differences between two CONTRIBUTIONS instances.
 * <p>
 * Note this is a concrete utility class, and has no relationship to the JRE Comparator or Comparable interfaces.
 */
@Component
public class ConcorComparison {
    /**
     * Updates the change counts in `counts` based on the differences between `c1` and `c2`
     * @param o1 CONTRIBUTIONS first instance
     * @param o2 CONTRIBUTIONS second instance
     * @param counts ConcorChangeCounts to update
     * @return true if there were any differences; false if there were none.
     */
    public boolean compare(CONTRIBUTIONS o1, CONTRIBUTIONS o2, ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareApplicant(o1 != null ? o1.getApplicant() : null, o2 != null ? o2.getApplicant() : null, counts)) {
            diff = true;
            counts.setApplicant(counts.getApplicant() + 1);
        }
        if (compareApplication(o1 != null ? o1.getApplication() : null, o2 != null ? o2.getApplication() : null, counts)) {
            diff = true;
            counts.setApplication(counts.getApplication() + 1);
        }
        if (compareAssessment(o1 != null ? o1.getAssessment() : null, o2 != null ? o2.getAssessment() : null, counts)) {
            diff = true;
            counts.setAssessment(counts.getAssessment() + 1);
        }
        if (comparePassported(o1 != null ? o1.getPassported() : null, o2 != null ? o2.getPassported() : null, counts)) {
            diff = true;
            counts.setPassported(counts.getPassported() + 1);
        }
        if (compareEquity(o1 != null ? o1.getEquity() : null, o2 != null ? o2.getEquity() : null, counts)) {
            diff = true;
            counts.setEquity(counts.getEquity() + 1);
        }
        if (compareCapitalSummary(o1 != null ? o1.getCapitalSummary() : null, o2 != null ? o2.getCapitalSummary() : null, counts)) {
            diff = true;
            counts.setCapitalSummary(counts.getCapitalSummary() + 1);
        }
        if (compareCcOutcomes(o1 != null ? o1.getCcOutcomes() : null, o2 != null ? o2.getCcOutcomes() : null, counts)) {
            diff = true;
            counts.setCcOutcomes(counts.getCcOutcomes() + 1);
        }
        if (compareCorrespondence(o1 != null ? o1.getCorrespondence() : null, o2 != null ? o2.getCorrespondence() : null, counts)) {
            diff = true;
            counts.setCorrespondence(counts.getCorrespondence() + 1);
        }
        if (compareBreathingSpaceInfo(o1 != null ? o1.getBreathingSpaceInfo() : null, o2 != null ? o2.getBreathingSpaceInfo() : null, counts)) {
            diff = true;
            counts.setBreathingSpaceInfo(counts.getBreathingSpaceInfo() + 1);
        }
        if (!Objects.equals(o1 != null ? o1.getId() : null, o2 != null ? o2.getId() : null)) {
            diff = true;
            // calling method's responsibility to increment total.
        }
        // We deliberately avoid comparing `maatId` and `flag`.
        return diff;
    }

    private boolean compareApplicant(CONTRIBUTIONS.Applicant o1, CONTRIBUTIONS.Applicant o2, ConcorChangeCounts counts) {
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
                                                            ConcorChangeCounts counts) {
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
                                                 ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareApplicant_HomeAddress_Detail(o1 != null ? o1.getDetail() : null, o2 != null ? o2.getDetail() : null, counts)) {
            diff = true;
            counts.setApplicant_homeAddress_detail(counts.getApplicant_homeAddress_detail() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_HomeAddress_Detail(CONTRIBUTIONS.Applicant.HomeAddress.Detail o1,
                                                        CONTRIBUTIONS.Applicant.HomeAddress.Detail o2,
                                                        ConcorChangeCounts counts) {
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
                                                   ConcorChangeCounts counts) {
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
                                                          ConcorChangeCounts counts) {
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
                                                      ConcorChangeCounts counts) {
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
                                                 ConcorChangeCounts counts) {
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
                                             ConcorChangeCounts counts) {
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
                                                       ConcorChangeCounts counts) {
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
                                                    ConcorChangeCounts counts) {
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
                                                                     ConcorChangeCounts counts) {
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
                                                       ConcorChangeCounts counts) {
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
                                                                    ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareApplicant_DisabilitySummary_Disabilities_Disability(o1 != null ? o1.getDisability() : null, o2 != null ? o2.getDisability() : null, counts)) {
            diff = true;
            counts.setApplicant_disabilitySummary_disabilities_disability(counts.getApplicant_disabilitySummary_disabilities_disability() + 1);
        }
        return diff;
    }

    private boolean compareApplicant_DisabilitySummary_Disabilities_Disability(List<CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities.Disability> o1,
                                                                               List<CONTRIBUTIONS.Applicant.DisabilitySummary.Disabilities.Disability> o2,
                                                                               ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareApplication(CONTRIBUTIONS.Application o1, CONTRIBUTIONS.Application o2, ConcorChangeCounts counts) {
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
                                                   ConcorChangeCounts counts) {
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
                                                ConcorChangeCounts counts) {
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
                                                 ConcorChangeCounts counts) {
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
                                                 ConcorChangeCounts counts) {
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
                                                  ConcorChangeCounts counts) {
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
                                                  ConcorChangeCounts counts) {
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
                                                 ConcorChangeCounts counts) {
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

    private boolean compareAssessment(CONTRIBUTIONS.Assessment o1, CONTRIBUTIONS.Assessment o2, ConcorChangeCounts counts) {
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
                                                       ConcorChangeCounts counts) {
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
                                                         ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareAssessment_IncomeEvidenceList_IncomeEvidence(o1 != null ? o1.getIncomeEvidence() : null, o2 != null ? o2.getIncomeEvidence() : null, counts)) {
            diff = true;
            counts.setAssessment_incomeEvidenceList_incomeEvidence(counts.getAssessment_incomeEvidenceList_incomeEvidence() + 1);
        }
        return diff;
    }

    private boolean compareAssessment_IncomeEvidenceList_IncomeEvidence(List<CONTRIBUTIONS.Assessment.IncomeEvidenceList.IncomeEvidence> o1,
                                                                        List<CONTRIBUTIONS.Assessment.IncomeEvidenceList.IncomeEvidence> o2,
                                                                        ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean comparePassported(CONTRIBUTIONS.Passported o1, CONTRIBUTIONS.Passported o2, ConcorChangeCounts counts) {
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
                                             ConcorChangeCounts counts) {
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
                                             ConcorChangeCounts counts) {
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

    private boolean compareEquity(CONTRIBUTIONS.Equity o1, CONTRIBUTIONS.Equity o2, ConcorChangeCounts counts) {
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
                                                     ConcorChangeCounts counts) {
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
                                                                       ConcorChangeCounts counts) {
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
                                                                  ConcorChangeCounts counts) {
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
                                                             ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareEquity_PropertyDescriptor_Address_Detail(o1 != null ? o1.getDetail() : null, o2 != null ? o2.getDetail() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_address_detail(counts.getEquity_propertyDescriptor_address_detail() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_Address_Detail(CONTRIBUTIONS.Equity.PropertyDescriptor.Address.Detail o1,
                                                                    CONTRIBUTIONS.Equity.PropertyDescriptor.Address.Detail o2,
                                                                    ConcorChangeCounts counts) {
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
                                                                    ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareEquity_PropertyDescriptor_ThirdPartyList_ThirdParty(o1 != null ? o1.getThirdParty() : null, o2 != null ? o2.getThirdParty() : null, counts)) {
            diff = true;
            counts.setEquity_propertyDescriptor_thirdPartyList_thirdParty(counts.getEquity_propertyDescriptor_thirdPartyList_thirdParty() + 1);
        }
        return diff;
    }

    private boolean compareEquity_PropertyDescriptor_ThirdPartyList_ThirdParty(List<CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.ThirdParty> o1,
                                                                               List<CONTRIBUTIONS.Equity.PropertyDescriptor.ThirdPartyList.ThirdParty> o2,
                                                                               ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCapitalSummary(CONTRIBUTIONS.CapitalSummary o1, CONTRIBUTIONS.CapitalSummary o2, ConcorChangeCounts counts) {
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
                                                                ConcorChangeCounts counts) {
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
                                                                                 ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareCapitalSummary_MotorVehicleOwnership_RegistrationList_Registration(o1 != null ? o1.getRegistration() : null, o2 != null ? o2.getRegistration() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_motorVehicleOwnership_registrationList_registration(counts.getCapitalSummary_motorVehicleOwnership_registrationList_registration() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_MotorVehicleOwnership_RegistrationList_Registration(List<String> o1,
                                                                                              List<String> o2,
                                                                                              ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCapitalSummary_AssetList(CONTRIBUTIONS.CapitalSummary.AssetList o1,
                                                    CONTRIBUTIONS.CapitalSummary.AssetList o2,
                                                    ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareCapitalSummary_AssetList_Asset(o1 != null ? o1.getAsset() : null, o2 != null ? o2.getAsset() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_assetList_asset(counts.getCapitalSummary_assetList_asset() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_AssetList_Asset(List<CONTRIBUTIONS.CapitalSummary.AssetList.Asset> o1,
                                                          List<CONTRIBUTIONS.CapitalSummary.AssetList.Asset> o2,
                                                          ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCapitalSummary_PropertyList(CONTRIBUTIONS.CapitalSummary.PropertyList o1,
                                                       CONTRIBUTIONS.CapitalSummary.PropertyList o2,
                                                       ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareCapitalSummary_PropertyList_Property(o1 != null ? o1.getProperty() : null, o2 != null ? o2.getProperty() : null, counts)) {
            diff = true;
            counts.setCapitalSummary_propertyList_property(counts.getCapitalSummary_propertyList_property() + 1);
        }
        return diff;
    }

    private boolean compareCapitalSummary_PropertyList_Property(List<CONTRIBUTIONS.CapitalSummary.PropertyList.Property> o1,
                                                                List<CONTRIBUTIONS.CapitalSummary.PropertyList.Property> o2,
                                                                ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCcOutcomes(CONTRIBUTIONS.CcOutcomes o1, CONTRIBUTIONS.CcOutcomes o2, ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareCcOutcomes_CcOutcome(o1 != null ? o1.getCcOutcome() : null, o2 != null ? o2.getCcOutcome() : null, counts)) {
            diff = true;
            counts.setCcOutcomes_ccOutcome(counts.getCcOutcomes_ccOutcome() + 1);
        }
        return diff;
    }

    private boolean compareCcOutcomes_CcOutcome(List<CONTRIBUTIONS.CcOutcomes.CcOutcome> o1,
                                                List<CONTRIBUTIONS.CcOutcomes.CcOutcome> o2,
                                                ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareCorrespondence(CONTRIBUTIONS.Correspondence o1, CONTRIBUTIONS.Correspondence o2, ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareCorrespondence_Letter(o1 != null ? o1.getLetter() : null, o2 != null ? o2.getLetter() : null, counts)) {
            diff = true;
            counts.setCorrespondence_letter(counts.getCorrespondence_letter() + 1);
        }
        return diff;
    }

    private boolean compareCorrespondence_Letter(List<CONTRIBUTIONS.Correspondence.Letter> o1,
                                                 List<CONTRIBUTIONS.Correspondence.Letter> o2,
                                                 ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }

    private boolean compareBreathingSpaceInfo(CONTRIBUTIONS.BreathingSpaceInfo o1,
                                              CONTRIBUTIONS.BreathingSpaceInfo o2,
                                              ConcorChangeCounts counts) {
        boolean diff = false;
        if (compareBreathingSpaceInfo_BreathingSpace(o1 != null ? o1.getBreathingSpace() : null, o2 != null ? o2.getBreathingSpace() : null, counts)) {
            diff = true;
            counts.setBreathingSpaceInfo_breathingSpace(counts.getBreathingSpaceInfo_breathingSpace() + 1);
        }
        return diff;
    }

    private boolean compareBreathingSpaceInfo_BreathingSpace(List<CONTRIBUTIONS.BreathingSpaceInfo.BreathingSpace> o1,
                                                             List<CONTRIBUTIONS.BreathingSpaceInfo.BreathingSpace> o2,
                                                             ConcorChangeCounts counts) {
        return !Objects.equals(o1 != null ? o1.size() : 0, o2 != null ? o2.size() : 0);
    }
}
