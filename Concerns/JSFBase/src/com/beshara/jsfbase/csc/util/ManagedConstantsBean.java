package com.beshara.jsfbase.csc.util;

import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.IAprConstants;
import com.beshara.csc.sharedutils.business.util.constants.IEosConstants;
import com.beshara.csc.sharedutils.business.util.constants.IFilConstants;
import com.beshara.csc.sharedutils.business.util.constants.IMovConstants;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.csc.sharedutils.business.util.constants.IVacConstants;
import com.beshara.csc.sharedutils.business.util.constants.IJobConstants;


/**
 * Purpose: this class contain accessor methods for business interface constants to allow accessing them from managed expressions 
 * Creation/Modification History :
 * 1.1 - Developer Name:  aboelhassan hamed
 * 1.2 - Date: 
 * 1.3 - Creation/Modification:Creation      
 * 1.4-  Description: 
 */
public class ManagedConstantsBean {


    public ManagedConstantsBean() {
    }
    
    
    //ISystemConstant.GENDER_FEMALE
     public String getGenderFemaleConstant() {
         return ISystemConstant.GENDER_FEMALE.toString();
     }
     
    public String getGenderMaleConstant() {
        return ISystemConstant.GENDER_MALE.toString();
    }
     
    public String getAllCertificateConstant() {
        return ISystemConstant.ALL_CERTIFICATIONS.toString();
    }

    public String getCertificateWithoutMappingConstent() {
        return ISystemConstant.CERTIFICATE_HAS_NOT_QUAL_MAPPING.toString();
    }

    public String getCertificateWithMappingConstent() {
        return ISystemConstant.CERTIFICATE_HAS_QUAL_MAPPING.toString();
    }


    public String getItemUpdatedConstant() {
        return ISystemConstant.ITEM_UPDATED;
    }

    public String getItemNotUpdatedConstant() {
        return ISystemConstant.ITEM_NOT_UPDATED;
    }

    public String getFutBookStatusRecievedConstant() {
        return ISystemConstant.FUT_BOOK_STATUS_RECIEVED.toString();
    }

    public String getCrsGradeTypePercentageConstant() {
        return ICRSConstant.GRADE_TYPE_PERCENTAGE.toString();
    }

    public String getCrsGradeTypeLatinConstant() {
        return ICRSConstant.GRADE_TYPE_LATIN.toString();
    }

    public String getCrsGradeTypeLiteralConstant() {
        return ICRSConstant.GRADE_TYPE_LITERAL.toString();
    }

    public String getCrsGradeTypePointFiveConstant() {
        return ICRSConstant.GRADE_TYPE_POINT_FIVE.toString();
    }

    public String getCrsGradeTypePointFourConstant() {
        return ICRSConstant.GRADE_TYPE_POINT_FOUR.toString();
    }

    public String getCrsGradeTypePointNineConstant() {
        return ICRSConstant.GRADE_TYPE_POINT_NINE.toString();
    }

    public String getVirtualStringValueConstant() {
        return ISystemConstant.VIRTUAL_VALUE.toString();
    }

    public Long getVirtualLongValueConstant() {
        return ISystemConstant.VIRTUAL_VALUE;
    }

    public Long getGovernmentalFlag() {
        return ISystemConstant.GOVERNMENT_FLAG;
    }

    public Long getDeductForWorkMinsConstant() {
        return ISalConstants.DEDUCT_FOR_WORK_MINS;
    }

    public Long getDeductForSpecMinsConstant() {
        return ISalConstants.DEDUCT_FOR_SPEC_MINS;
    }

    public Long getDeductForNotSpecMinsConstant() {
        return ISalConstants.DEDUCT_FOR_NOT_SPEC_MINS;
    }

    public Long getActiveFlagConstant() {
        return ISystemConstant.ACTIVE_FLAG;
    }

    public Long getNonActiveFlagConstant() {
        return ISystemConstant.NON_ACTIVE_FLAG;
    }

    // Start Sal Element Guide added by Sherif Muhammed Omar 30-11-2008  HR-427

    public String getELEMENT_GUIDE_TYPE_PAYROLL_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_PAYROLL_ROOT.toString();
    }

    public String getELEMENT_GUIDE_TYPE_BENIFIT_TYPE_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_TYPE_ROOT.toString();
    }

    public String getELEMENT_GUIDE_TYPE_REWARD_TYPE_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_TYPE_ROOT.toString();
    }

    public String getELEMENT_GUIDE_TYPE_INCREASE_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_INCREASE_ROOT.toString();
    }

    public String getELEMENT_GUIDE_TYPE_SOCIAL_RAISE_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_SOCIAL_RAISE_ROOT.toString();
    }

    //RaiseValueBean

    public Long getELEMENT_GUIDE_TYPE_SOCIAL_RAISE_TYPE() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_SOCIAL_RAISE;
    }


    public String getELEMENT_GUIDE_TYPE_INSURANCE_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_INSURANCE_ROOT.toString();
    }

    public String getELEMENT_GUIDE_TYPE_DESERVATION_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_DESERVATION_ROOT.toString();
    }

    public String getELEMENT_GUIDE_TYPE_CHILDREN_PROMOTION() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_CHILDREN_PROMOTION.toString();
    }

    public String getELEMENT_GUIDE_TYPE_HEALTHY_CHILDREN_PROMOTION() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_HEALTHY_CHILDREN_PROMOTION.toString();
    }

    public String getELEMENT_GUIDE_TYPE_HANDICAPPED_CHILDREN_PROMOTION() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_HANDICAPPED_CHILDREN_PROMOTION.toString();
    }

    public String getELEMENT_GUIDE_TYPE_DEDUCT_ROOT() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT_ROOT.toString();
    }

    public String getFutBookExternalType() {
        return ISystemConstant.FUT_BOOK_TYPE_EXTERNAL.toString();
    }

    public String getFutBookInternalType() {
        return ISystemConstant.FUT_BOOK_TYPE_INTERNAL.toString();
    }

    public String getElementGuideTypeDeductType() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT_TYPE.toString();
    }

    public String getElementGuideTypeDeduct() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT.toString();
    }

    public Long getElementGuideTypeDegree() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_DEGREE;
    }

    public Long getElementGuideTypeRaise() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_RAISE;
    }

    public Long getRevResultReviewFulFilling() {
        return ICRSConstant.REV_RESULT_REVIEW_FULFILLING;
    }

    public Long getRevResultReviewNotFulFilling() {
        return ICRSConstant.REV_RESULT_REVIEW_NOT_FULFILLING;
    }

    public Long getRegPeriodCentralEmployment() {
        return ICRSConstant.REG_PERIOD_CENTRAL_EMPLOYMENT;
    }

    public Long getRegPeriodSpecialEduMinistry() {
        return ICRSConstant.REG_PERIOD_SPECIAL_EDU_MINISTRY;
    }

    public Long getRegPeriodPublic() {
        return ICRSConstant.REG_PERIOD_PUBLIC;
    }

    public String getDecisionAdministrationType() {
        return IRegConstants.REGULATION_TYPE_ADMINISTRATIVE_DECISIONS.toString();
    }

    public String getEndServiceDecisionSubject() {
        return IRegConstants.REGULATION_SUBJECT_END_OF_SERVICE_DECISIONS.toString();
    }

    public String getDefaultInitiator() {
        return IEosConstants.EOS_DEFAULT_INITIATOR.toString();
    }

    public Long getRegQulifiedForCandidacyConstant() {
        return ICRSConstant.REG_QULIFIED_FOR_CANDIDACY;
    }

    public Long getRegCandidateRejectConstant() {
        return ICRSConstant.REG_CANDIDATE_REJECT;
    }

    public Long getAllQulLevelConstant() {
        return ICRSConstant.ALL_QUL_LEVEL;
    }

    public Long getEducationLevelConstant() {
        return ICRSConstant.EDUCATION_LEVEL;
    }

    public Long getQulLevelConstant() {
        return ICRSConstant.QUL_LEVEL;
    }

    public String getNeedQulConstant() {
        return ICRSConstant.NEED_QUL;
    }

    public String getNeedJobConstant() {
        return ICRSConstant.NEED_JOB;
    }

    public Long getPersonTypeBothConstant() {
        return ICRSConstant.PERSON_TYPE_BOTH;
    }

    public Long getPersonTypeQualifiedConstant() {
        return ICRSConstant.PERSON_TYPE_QUALIFIED;
    }

    public Long getPersonTypeRejectedConstant() {
        return ICRSConstant.PERSON_TYPE_REJECTED;
    }

    public Long getQulConstant() {
        return ICRSConstant.QUL;
    }

    public Long getQulMergeConstant() {
        return ICRSConstant.QUL_MERGE;
    }

    public Long getQulMergeGroupConstant() {
        return ICRSConstant.QUL_MERGE_GROUP;
    }

    public Long getMinCode() {
        Long loginedMinistrycode = null;
        try {
            loginedMinistrycode = 
                    CSCSecurityInfoHelper.getLoggedInMinistryCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (loginedMinistrycode == null) ? 13L : loginedMinistrycode;
    }

    public int getApprRatioMaxValue() {
        return 100;
    }

    public int getApprRatioMinValue() {
        return 0;
    }

    public String getEmpHireTypeExceptedFromCenteralEmployment() {
        return IEMPConstant.EMP_HIRE_TYPE_EXCEPTED_FROM_CENTRAL_EMPLOYMENT.toString(); //for ( مستثنون من التوظيف المركزى ) 
    }

    public String getEmpHireTypeNominationAgain() {
        return IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN.toString(); //for (المعاد تعينهم ) 
    }

    public String getEmpHireTypeNominationByMinistry() {
        return IEMPConstant.EMP_HIRE_TYPE_NOMINATION_BY_MINISTRY.toString(); //for (توظيف خاص بالجهة)
    }

    public Long getEmpStatusEmployment() {
        return IEMPConstant.EMP_STATUS_EMPLOYMENT;
    }

    public Long getEmpStatusMoving() {
        return IEMPConstant.EMP_STATUS_MOVING;
    }

    public Long getEmpStatusDelegation() {
        return IEMPConstant.EMP_STATUS_DELEGATION;
    }

    public Long getEmpStatusLoaning() {
        return IEMPConstant.EMP_STATUS_LOANINIG;
    }

    public Long getEmpStatusMission() {
        return IEMPConstant.EMP_STATUS_MISSION;
    }

    public Long getEmpStatusGrant() {
        return IEMPConstant.EMP_STATUS_GRANT;
    }

    public Long getEmpStatusVacation() {
        return IEMPConstant.EMP_STATUS_VACATION;
    }

    public Long getEmpStatusPrisonerLost() {
        return IEMPConstant.EMP_STATUS_PRISONER_LOST;
    }

    public Long getEmpStatusEndService() {
        return IEMPConstant.EMP_STATUS_END_SERVICE;
    }

    public Long getEmpStatusEndServiceConstant() {
        return IEMPConstant.EMP_STATUS_END_SERVICE;
    }

    public Long getEmpStatusDelegationOutFrom() {
        return IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM;
    }

    public Long getEmpStatusDelegationOutTo() {
        return IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO;
    }

    public Long getEmpStatusCandidate() {
        return IEMPConstant.EMP_STATUS_CANDIDATE;
    }

    public String getDecisionStatusCanceled() {
        return ISystemConstant.DECISION_STATUS_CANCELED.toString();
    }

    public String getDecisionStatusValid() {
        return ISystemConstant.DECISION_STATUS_VALID.toString();
    }

    public String getDecisionStatusCancel() {
        return ISystemConstant.DECISION_STATUS_CANCEL.toString();
    }

    public Long getVacTypeSickVac() {
        return IVacConstants.VAC_TYPE_SICK_VAC;
    }

    public Long getVacEmpVacStatusUnderDiscussion() {
        return IVacConstants.VAC_EMP_VAC_STATUS_UNDER_DISCUSSION;
    }

    public Long getVacEmpVacStatusApproved() {
        return IVacConstants.VAC_EMP_VAC_STATUS_APPROVED;
    }

    public Long getVacPaymentTypeFromSalary() {
        return IVacConstants.VAC_PAYMENT_TYPE_FROM_SALARY;
    }

    public Long getVacPaymentTypePreVac() {
        return IVacConstants.VAC_PAYMENT_TYPE_PRE_VAC;
    }

    public Long getVacPaymentTypeMandatoryPreVac() {
        return IVacConstants.VAC_PAYMENT_TYPE_MANDATORY_PRE_VAC;
    }

    public Long getVacReqSalWithSalary() {
        return IVacConstants.VAC_REQ_SAL_WITH_SALARY;
    }

    public Long getVacReqSalPreVac() {
        return IVacConstants.VAC_REQ_SAL_PRE_VAC;
    }

    public Long getVacReqSalMandatoryPreVac() {
        return IVacConstants.VAC_REQ_SAL_MANDATORY_PRE_VAC;
    }

    public Long getEmpHireStageDefault() {
        return IEMPConstant.EMP_STAGE_DEFAULT; //Hire Stage=1
    }

    public Long getEmpHireStatusCandidate() {
        return IEMPConstant.EMP_STATUS_CANDIDATE; //Hire status = 12
    }

    public Long getVacTrxTypeExtend() {
        return IVacConstants.VAC_TRXTYPE_EXTEND;
    }

    public Long getVacTrxTypeCut() {
        return IVacConstants.VAC_TRXTYPE_CUT;
    }

    public Long getVacTrxTypeCancel() {
        return IVacConstants.VAC_TRXTYPE_CANCEL;
    }

    public Long getSecondEducationLevel() {
        return ICRSConstant.SEC_EDU_LEVEL;
    }

    public Long getEmpStageDefault() {
        return IEMPConstant.EMP_STAGE_DEFAULT; //= new Long(1);
    }

    public Long getEmpStageWaitingEmploymentDecision() {
        return IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION; // = new Long(2);
    }

    public Long getEmpStageCompleteProc() {
        return IEMPConstant.EMP_STAGE_COMPLETE_PROC; // = new Long(7);
    }

    public Long getEmpStageCompleteDoc() {
        return IEMPConstant.EMP_STAGE_COMPLETE_DOC; //= new Long(8);
    }
    // by sherif.omar

    public String getMovStatusDewan_Rejected() {
        return IMovConstants.MOV_STATUS_DEWAN_REJECTED.toString();
    }

    public String getMovStatusMin_Rejected() {
        return IMovConstants.MOV_STATUS_MINS_REJECTED.toString();
    }
    // End

    public Long getMovRequestInitiatorsCurrentMinistry() {
        return IMovConstants.MOV_REQUEST_INITIATORS_CURRENT_MINISTRY;
    }

    public String getMovTypeDelegateExternalConst() {
        return IMovConstants.MOV_TYPE_DELEGATE_EXTERNAL.toString();
    }

    public String getMovTypeDelegateInternalConst() {
        return IMovConstants.MOV_TYPE_DELEGATE_INTERNAL.toString();
    }

    public String getMovTypeMovExternalConst() {
        return IMovConstants.MOV_TYPE_MOV_EXTERNAL.toString();
    }

    public String getMovTypeMovInternalConst() {
        return IMovConstants.MOV_TYPE_MOV_INTERNAL.toString();
    }

    public String getBookSendStatus() {
        return ISystemConstant.FUT_BOOK_STATUS_SENT.toString();
    }

    public String getDefaultEvalStatus() {
        return IAprConstants.STATUS_DEFAULT.toString();
    }

    public String getInProgressEvalStatus() {
        return IAprConstants.STATUS_CONTINUAL_EVALUATION.toString();
    }

    public String getRejectEvalStatus() {
        return IAprConstants.STATUS_REJECTED.toString();
    }

    public Long getFileTypeHire() {
        return ISystemConstant.FILE_TYPE_HIRE;
    }

    public Long getFileTypeRetired() {
        return ISystemConstant.FILE_TYPE_RETIRED;
    }

    public Long getFileTypeRevision() {
        return ISystemConstant.FILE_TYPE_REVISION;
    }

    public Long getFileMaxResult() {
        return ISystemConstant.FILE_MAX_RESULT;
    }

    public String getColumnNameKwtCitizenName() {
        return IFilConstants.COLUMN_NAME_KWT_CITIZEN_NAME;
    }

    public String getColumnNameHrFilFilesFileNum() {
        return IFilConstants.COLUMN_NAME_HR_FIL_FILES_FILE_NUM;
    }

    public String getColumnNameHrFilFilesFileTypeName() {
        return IFilConstants.COLUMN_NAME_HR_FIL_FILES_FILTYPE_NAME;
    }

    public String getColumnNameHrFilPersonFilesCivilId() {
        return IFilConstants.COLUMN_NAME_HR_FIL_PERSON_FILES_CIVIL_ID;
    }

    public String getColumnNameHrFilPersonFilesGivenDate() {
        return IFilConstants.COLUMN_NAME_HR_FIL_PERSON_FILES_GIVEN_DATE;
    }
    ////////////////////

    public Long getEqTypeTemp() {
        return ISalConstants.EQ_TYPE_TEMP;
    }

    public Long getEqTypePerm() {
        return ISalConstants.EQ_TYPE_PERM;
    }

    public Long getEqTypeNotEquivalence() {
        return ISalConstants.EQ_TYPE_NOT_EQUIVALENCE;

    }


    /* ********************************* BenifitsTree**********************************
        * Added by Sherif.omar 27-04-2009
        * */

    public Long getElem_Guide_Type_Ben_Type() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_TYPE;
    }

    public Long getElem_Guide_Type_Ben() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT;
    }

    public Long getElem_Guide_Type_Ben_Pay_Cat() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT;
    }
    /* ********************************************************************************* */


    /* ********************************* RewardsTree********************************** */

    public Long getElem_Guide_Type_Reward_Type() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_TYPE;
    }

    public Long getElem_Guide_Type_Reward() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_REWARD;
    }

    public Long getElem_Guide_Type_Reward_Pay_Cat() {
        return ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT;
    }
    /* *************************************************
         * End by Sherif.omar 27-04-2009
         *
         * **/

    public Long getEmpHireTypeExceptedFromCenteralEmploymentLong() {
        return IEMPConstant.EMP_HIRE_TYPE_EXCEPTED_FROM_CENTRAL_EMPLOYMENT;
    }

    public Long getEmpHireTypeNominationAgainLong() {
        return IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN;
    }

    public Long getEmpHireTypeNominationByMinistryLong() {
        return IEMPConstant.EMP_HIRE_TYPE_NOMINATION_BY_MINISTRY;
    }

    public Long getEqTypeTempConstant() {
        return ISalConstants.EQ_TYPE_TEMP;
    }

    public Long getRewardsParentConstant() {
        return ISalConstants.REWARDS_PARENT;
    }

    public Long getBenefitsParentConstant() {
        return ISalConstants.BENEFITS_PARENT;
    }

    public Long getMeritesConstant() {
        return new Long(1);
    }

    public Long getSalMainSheet() {
        return ISalConstants.SAL_MAIN_SHEET;
    }

    public Long getCrsSysNeedtype() {
        return ICRSConstant.SYS_NEED_TYPE_CODE.longValue();
    }

    public String getSTATUS_SHEET_COMPLETED() {
        return "1";
    }

    public String getHealtyElementType() {
        return String.valueOf(ISalConstants.ELEMENT_GUIDE_TYPE_HEALTHY_CHILDREN_PROMOTION_CAT);
    }

    public String getHandicappedElementType() {
        return String.valueOf(ISalConstants.ELEMENT_GUIDE_TYPE_HANDICAPPED_CHILDREN_PROMOTION_CAT);
    }

    public String getJobTimeTypeContract() {
        return "0";
    }

    public String getJobTimeTypeFullTime() {
        return "1";
    }

    public Long getRegColumnTextType() {
        return 1L;
    }

    public Long getRegColumnNumberType() {
        return 2L;
    }

    public Long getEducationMinistryCode() {
        return 1L;
    }

    public Long getMinistryCode() {
        return 1L;
    }

    public String getEmpHireTypeFromCenteralEmployment() {
        return IEMPConstant.EMP_HIRE_TYPE_FROM_CENTER_EMPLOYMENT.toString();
    }

    public String getEmpHireTypeMovedToOtherMinisties() {
        return IEMPConstant.EMP_HIRE_TYPE_MOVEED_TO_OTHER_MINISTRIES.toString();
    }

    public Long getRegCandidateStatus() {
        return ICRSConstant.REG_CANDIDATE;
    }

    public Long getQulMergSecondStage() {
        return ICRSConstant.QUL_MERGE_SECOND_STAGE;
    }

    public Long getCommitFlag() {
        return ICRSConstant.COMMIT;
    }

    public Long getNetTrySourceCode() {
        return ICRSConstant.TRAIL_SOURCE_NET;
    }

    public Long getRegStatsValid() {
        return 1L;
    }

    public Long getClincValidStatus() {

        return new Long(1);
    }

    public Long getClincInvalidStatus() {

        return new Long(0);
    }

    public Long getTransferToHealthyMinistry() {
        return IVacConstants.SICK_REQUEST_STATUS_TRANSFER_TO_MOH;
    }

    public Long getWaitingApproveRequestStatus() {
        return IVacConstants.SICK_REQUEST_STATUS_REQUEST_SAVED;
    }

    public Long getKwaitNationality() {
        return ISystemConstant.KUWAIT_NATIONALITY;
    }

    public Long getJobWCStatusRequest() {
        return IJobConstants.JOB_WC_STATUS_REQUEST;
    }

    public Long getJobWCStatusMinApprove() {
        return IJobConstants.JOB_WC_STATUS_MIN_APPROVED;
    }

    public Long getJobWCStatusMinReject() {
        return IJobConstants.JOB_WC_STATUS_MIN_REJECTED;
    }

    public Long getJobWCStatusDewanApprove() {
        return IJobConstants.JOB_WC_STATUS_DEWAN_APPROVED;
    }

    public Long getJobWCStatusDewanReject() {
        return IJobConstants.JOB_WC_STATUS_DEWAN_REJECTED;
    }

    public Long getJobDescApproved() {
        return IJobConstants.JOB_WC_JOB_DESC_APPROVED;
    }

    public Long getSickRequestManulaFlagYes() {
        return IVacConstants.SICK_REQUEST_MANUAL_FLAG_YES;
    }

    public Long getSickRequestManulaFlagNo() {
        return IVacConstants.SICK_REQUEST_MANUAL_FLAG_NO;
    }

    public String getSalElementTypeMeritCode() {
        return String.valueOf(ISalConstants.ELEMENT_GUIDE_TYPE_MERIT_ROOT);
    }
    // Employment Cycle Const

    public int getOrgCatCodeAttBgt() {
        return 3; //الهيئات ذات الميزانيات الملحقة
    }

    public int getOrgCatCodeSprBgt() {
        return 4; //المؤسسات ذات الميزانية المستقلة
    }

    public Long getBgtTypeForAttSpr() {
        return 1L; //ميزانية الجهة
    }

    public Long getDefultBgtTypeCodeForEmploymentCycle() {
        return 2L; //الاعتماد التكميلي الأول
    }

    public String getHIRE_STAGE_COMPLETING_JOB_NAME() {
        return IEMPConstant.HIRE_STAGE_COMPLETING_JOB_NAME.toString();
    }


    public String getHIRE_STAGE_FIN_DEGREE_REQUIRED() {
        return IEMPConstant.HIRE_STAGE_FIN_DEGREE_REQUIRED.toString();
    }


    public String getHIRE_STAGE_JOB_NAME_REQUIRED() {
        return IEMPConstant.HIRE_STAGE_JOB_NAME_REQUIRED.toString();
    }


    public String getHIRE_STAGE_REJECTED_BY_DEWAN() {
        return IEMPConstant.HIRE_STAGE_REJECTED_BY_DEWAN.toString();
    }


    public String getHIRE_STAGE_JOB_NAME_ACCEPTRD() {
        return IEMPConstant.HIRE_STAGE_JOB_NAME_ACCEPTRD.toString();
    }


    public String getHIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT() {
        return IEMPConstant.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT.toString();
    }


    public String getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() {
        return IEMPConstant.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED.toString();
    }


    public String getHIRE_STAGE_REJECTED_BY_FATWA() {
        return IEMPConstant.HIRE_STAGE_REJECTED_BY_FATWA.toString();
    }


}
