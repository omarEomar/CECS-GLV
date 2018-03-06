package com.beshara.csc.inf.business.entity;


import com.beshara.csc.gn.inf.business.entity.IInfOldDataAuditEntityKey;
import com.beshara.csc.gn.inf.business.entity.InfOldDataAuditEntityKey;
import com.beshara.csc.inf.business.entity.emp.IInfReasonDataEntityKey;
import com.beshara.csc.inf.business.entity.emp.IInfReasonTypesEntityKey;
import com.beshara.csc.inf.business.entity.emp.InfReasonDataEntityKey;
import com.beshara.csc.inf.business.entity.emp.InfReasonTypesEntityKey;

import java.sql.Date;
import java.sql.Timestamp;

public class InfEntityKeyFactory {
    public static IApprovalMakersEntityKey createApprovalMakersEntityKey() {
        return new ApprovalMakersEntityKey();
    }

    public static IApprovalMakersEntityKey createApprovalMakersEntityKey(Long aprmakerCode) {
        return new ApprovalMakersEntityKey(aprmakerCode);
    }


    public static IBloodGroupsEntityKey createBloodGroupsEntityKey() {
        return new BloodGroupsEntityKey();
    }

    public static IBloodGroupsEntityKey createBloodGroupsEntityKey(Long bldgroupCode) {
        return new BloodGroupsEntityKey(bldgroupCode);
    }

    public static IBuildingOwnerTypesEntityKey createBuildingOwnerTypesEntityKey(Long buildingOwnerCode1) {
        return new BuildingOwnerTypesEntityKey(buildingOwnerCode1);
    }

    public static ICountriesEntityKey createCountriesEntityKey(Long cntryCode) {
        return new CountriesEntityKey(cntryCode);
    }

    public static ICountryCitiesEntityKey createCountryCitiesEntityKey() {
        return new CountryCitiesEntityKey();
    }

    public static ICountryCitiesEntityKey createCountryCitiesEntityKey(Long cntryCode, Long cntrycityCode) {
        return new CountryCitiesEntityKey(cntryCode, cntrycityCode);
    }

    public static ICountryGroupsEntityKey createCountryGroupsEntityKey() {
        return new CountryGroupsEntityKey();
    }

    public static ICountryGroupsEntityKey createCountryGroupsEntityKey(Long cntrygrpCode) {
        return new CountryGroupsEntityKey(cntrygrpCode);
    }

    public static ICurrenciesEntityKey createCurrenciesEntityKey(Long currencyCode) {
        return new CurrenciesEntityKey(currencyCode);
    }

    public static IDecisionMakerTypesEntityKey createDecisionMakerTypesEntityKey(Long decmkrtypeCode) {
        return new DecisionMakerTypesEntityKey(decmkrtypeCode);
    }

    public static IDecisionMakerTypesEntityKey createDecisionMakerTypesEntityKey() {
        return new DecisionMakerTypesEntityKey();
    }

    public static IDmlStatmentTypesEntityKey createDmlStatmentTypesEntityKey() {
        return new DmlStatmentTypesEntityKey();
    }

    public static IDmlStatmentTypesEntityKey createDmlStatmentTypesEntityKey(Long dmlstatypeCode) {
        return new DmlStatmentTypesEntityKey(dmlstatypeCode);
    }

    public static IFieldsEntityKey createFieldsEntityKey() {
        return new FieldsEntityKey();
    }

    public static IFieldsEntityKey createFieldsEntityKey(Long fldCode) {
        return new FieldsEntityKey(fldCode);
    }

    public static IFieldTypesEntityKey createFieldTypesEntityKey() {
        return new FieldTypesEntityKey();
    }

    public static IFieldTypesEntityKey createFieldTypesEntityKey(Long fldtypeCode) {
        return new FieldTypesEntityKey(fldtypeCode);
    }

    public static IGenderCountryEntityKey createGenderCountryEntityKey() {
        return new GenderCountryEntityKey();
    }

    public static IGenderCountryEntityKey createGenderCountryEntityKey(Long gentypeCode, Long cntryCode) {
        return new GenderCountryEntityKey(gentypeCode, cntryCode);
    }

    public static IGenderMaritalEntityKey createGenderMaritalEntityKey() {
        return new GenderMaritalEntityKey();
    }

    public static IGenderMaritalEntityKey createGenderMaritalEntityKey(Long gentypeCode, Long marstatusCode) {
        return new GenderMaritalEntityKey(gentypeCode, marstatusCode);
    }

    public static IGenderReligionEntityKey createGenderReligionEntityKey() {
        return new GenderReligionEntityKey();
    }

    public static IGenderReligionEntityKey createGenderReligionEntityKey(Long gentypeCode, Long religionCode) {
        return new GenderReligionEntityKey(gentypeCode, religionCode);
    }

    public static IGenderTypesEntityKey createGenderTypesEntityKey() {
        return new GenderTypesEntityKey();
    }

    public static IGenderTypesEntityKey createGenderTypesEntityKey(Long gentypeCode) {
        return new GenderTypesEntityKey(gentypeCode);
    }

    public static IInfGovernoratesEntityKey createInfGovernoratesEntityKey() {
        return new InfGovernoratesEntityKey();
    }

    public static IInfGovernoratesEntityKey createInfGovernoratesEntityKey(Long governoratesCode) {
        return new InfGovernoratesEntityKey(governoratesCode);
    }

    public static IInfMonthsEntityKey createInfMonthsEntityKey() {
        return new InfMonthsEntityKey();
    }

    public static IInfMonthsEntityKey createInfMonthsEntityKey(Long monthCode) {
        return new InfMonthsEntityKey(monthCode);
    }


    public static IGroupCountriesEntityKey createGroupCountriesEntityKey() {
        return new GroupCountriesEntityKey();
    }

    public static IGroupCountriesEntityKey createGroupCountriesEntityKey(Long cntrygrpCode, Long cntryCode) {
        return new GroupCountriesEntityKey(cntrygrpCode, cntryCode);
    }

    public static IHandicapStatusEntityKey createHandicapStatusEntityKey(Long capstatusCode) {
        return new HandicapStatusEntityKey(capstatusCode);
    }

    public static IHolidaysEntityKey createHolidaysEntityKey() {
        return new HolidaysEntityKey();
    }

    public static IHolidaysEntityKey createHolidaysEntityKey(Long yearCode, Long holtypeCode, Date fromDate) {
        return new HolidaysEntityKey(yearCode, holtypeCode, fromDate);
    }

    public static IHolidayTypesEntityKey createHolidayTypesEntityKey() {
        return new HolidayTypesEntityKey();
    }

    public static IHolidayTypesEntityKey createHolidayTypesEntityKey(Long holtypeCode) {
        return new HolidayTypesEntityKey(holtypeCode);
    }

    public static IInfCitizensPassportsEntityKey createInfCitizensPassportsEntityKey() {
        return new InfCitizensPassportsEntityKey();
    }

    public static IInfCitizensPassportsEntityKey createInfCitizensPassportsEntityKey(Long serialNo) {
        return new InfCitizensPassportsEntityKey(serialNo);
    }

    public static IInfCitizensResidentsDataEntityKey createInfCitizensResidentsDataEntityKey() {
        return new InfCitizensResidentsDataEntityKey();
    }

    public static IInfCitizensResidentsDataEntityKey createInfCitizensResidentsDataEntityKey(Long serial) {
        return new InfCitizensResidentsDataEntityKey(serial);
    }

    public static IInfPersonQualificationsEntityKey createInfPersonQualificationsEntityKey() {
        return new InfPersonQualificationsEntityKey();
    }

    public static IInfPersonQualificationsEntityKey createInfPersonQualificationsEntityKey(Long civilId,
                                                                                           String qualificationKey) {
        return new InfPersonQualificationsEntityKey(civilId, qualificationKey);
    }

    public static IKwMapEntityKey createKwMapEntityKey(String mapCode) {
        return new KwMapEntityKey(mapCode);
    }

    public static IKwMapEntityKey createKwMapEntityKey() {
        return new KwMapEntityKey();
    }

    public static IKwStreetsEntityKey createKwStreetsEntityKey(Long streetCode) {
        return new KwStreetsEntityKey(streetCode);
    }

    public static IKwtCitizensResidentsEntityKey createKwtCitizensResidentsEntityKey() {
        return new KwtCitizensResidentsEntityKey();
    }

    public static IKwtCitizensResidentsEntityKey createKwtCitizensResidentsEntityKey(Long civilId) {
        return new KwtCitizensResidentsEntityKey(civilId);
    }

    public static IKwtWorkDataEntityKey createKwtWorkDataEntityKey() {
        return new KwtWorkDataEntityKey();
    }

    public static IKwtWorkDataEntityKey createKwtWorkDataEntityKey(Long serial) {
        return new KwtWorkDataEntityKey(serial);
    }

    public static ILanguagesEntityKey createLanguagesEntityKey() {
        return new LanguagesEntityKey();
    }

    public static ILanguagesEntityKey createLanguagesEntityKey(Long languageCode) {
        return new LanguagesEntityKey(languageCode);
    }

    public static IMapTypesEntityKey createMapTypesEntityKey() {
        return new MapTypesEntityKey();
    }

    public static IMapTypesEntityKey createMapTypesEntityKey(Long typeCode) {
        return new MapTypesEntityKey(typeCode);
    }

    public static IMaritalStatusEntityKey createMaritalStatusEntityKey(Long marstatusCode) {
        return new MaritalStatusEntityKey(marstatusCode);
    }

    public static IMilitaryStatusEntityKey createMilitaryStatusEntityKey() {
        return new MilitaryStatusEntityKey();
    }

    public static IMilitaryStatusEntityKey createMilitaryStatusEntityKey(Long mltstatusCode) {
        return new MilitaryStatusEntityKey(mltstatusCode);
    }

    public static INewspapersEntityKey createNewspapersEntityKey() {
        return new NewspapersEntityKey();
    }

    public static INewspapersEntityKey createNewspapersEntityKey(Long paperId) {
        return new NewspapersEntityKey(paperId);
    }

    public static IOracleErrorsEntityKey createOracleErrorsEntityKey() {
        return new OracleErrorsEntityKey();
    }

    public static IOracleErrorsEntityKey createOracleErrorsEntityKey(Long errNum) {
        return new OracleErrorsEntityKey(errNum);
    }

    public static IPersonDataChangesEntityKey createPersonDataChangesEntityKey() {
        return new PersonDataChangesEntityKey();
    }

    public static IPersonDataChangesEntityKey createPersonDataChangesEntityKey(Long civilId, Long parameterCode,
                                                                               Timestamp changeDatetime) {
        return new PersonDataChangesEntityKey(civilId, parameterCode, changeDatetime);
    }

    public static IPersonParameterValuesBkEntityKey createPersonParameterValuesBkEntityKey() {
        return new PersonParameterValuesBkEntityKey();
    }

    public static IPersonParameterValuesBkEntityKey createPersonParameterValuesBkEntityKey(Long civilId,
                                                                                           Long parameterCode) {
        return new PersonParameterValuesBkEntityKey(civilId, parameterCode);
    }

    public static IPersonParameterValuesEntityKey createPersonParameterValuesEntityKey() {
        return new PersonParameterValuesEntityKey();
    }

    public static IPersonQualificationsEntityKey createPersonQualificationsEntityKey() {
        return new PersonQualificationsEntityKey();
    }

    public static IPersonQualificationsEntityKey createPersonQualificationsEntityKey(Long civilId,
                                                                                     String qualificationKey) {
        return new PersonQualificationsEntityKey(civilId, qualificationKey);
    }

    public static IPersonsInformationEntityKey createPersonsInformationEntityKey() {
        return new PersonsInformationEntityKey();
    }

    public static IPersonsInformationEntityKey createPersonsInformationEntityKey(Long civilId, Date registrationDate,
                                                                                 Long socCode) {
        return new PersonsInformationEntityKey(civilId, registrationDate, socCode);
    }

    public static IRelatedFieldsEntityKey createRelatedFieldsEntityKey() {
        return new RelatedFieldsEntityKey();
    }

    public static IRelatedFieldsEntityKey createRelatedFieldsEntityKey(Long fldCode, Long fldCodeReferenced) {
        return new RelatedFieldsEntityKey(fldCode, fldCodeReferenced);
    }

    public static IReligionsEntityKey createReligionsEntityKey(Long religionCode) {
        return new ReligionsEntityKey(religionCode);
    }

    public static IResidentTypeEntityKey createResidentTypeEntityKey(Long restypeCode) {
        return new ResidentTypeEntityKey(restypeCode);
    }

    public static ISeekerLanguageSkillsEntityKey createSeekerLanguageSkillsEntityKey() {
        return new SeekerLanguageSkillsEntityKey();
    }

    public static ISeekerLanguageSkillsEntityKey createSeekerLanguageSkillsEntityKey(Long civilId, Long languageCode) {
        return new SeekerLanguageSkillsEntityKey(civilId, languageCode);
    }

    public static ISpecialCaseTypesEntityKey createSpecialCaseTypesEntityKey(Long spccsetypeCode) {
        return new SpecialCaseTypesEntityKey(spccsetypeCode);
    }

    public static IStreetZonesEntityKey createStreetZonesEntityKey() {
        return new StreetZonesEntityKey();
    }

    public static IStreetZonesEntityKey createStreetZonesEntityKey(String mapCode, Long streetCode) {
        return new StreetZonesEntityKey(mapCode, streetCode);
    }

    public static ISystemSettingsEntityKey createSystemSettingsEntityKey() {
        return new SystemSettingsEntityKey();
    }

    public static ISystemSettingsEntityKey createSystemSettingsEntityKey(Long syssettingCode) {
        return new SystemSettingsEntityKey(syssettingCode);
    }

    public static ITrxStatusEntityKey createTrxStatusEntityKey() {
        return new TrxStatusEntityKey();
    }

    public static ITrxStatusEntityKey createTrxStatusEntityKey(String trxstatusCode) {
        return new TrxStatusEntityKey(trxstatusCode);
    }

    public static IWeekDaysEntityKey createWeekDaysEntityKey() {
        return new WeekDaysEntityKey();
    }

    public static IInfOperationTypesEntityKey createInfOperationTypesEntityKey(Long operationTypeCode) {
        return new InfOperationTypesEntityKey(operationTypeCode);
    }

    public static IWeekDaysEntityKey createWeekDaysEntityKey(Long daynum) {
        return new WeekDaysEntityKey(daynum);
    }

    public static IYearsEntityKey createYearsEntityKey(Long yearCode) {
        return new YearsEntityKey(yearCode);
    }

    public static IYearsEntityKey createYearsEntityKey() {
        return new YearsEntityKey();
    }

    public static IInfReasonDataEntityKey createReasonDataEntityKey(Long resdatSerial, Long restypeCode) {
        return new InfReasonDataEntityKey(resdatSerial, restypeCode);
    }

    public static IInfReasonTypesEntityKey createReasonTypesEntityKey(Long restypeCode) {
        return new InfReasonTypesEntityKey(restypeCode);
    }

    public static IInfGradeTypesEntityKey createGradeTypesEntityKey(Long gradeTypeCode) {
        return new InfGradeTypesEntityKey(gradeTypeCode);
    }

    public static IInfGradeValuesEntityKey createInfGradeValuesEntityKey(Long gradeTypeCode, String value) {
        return new InfGradeValuesEntityKey(gradeTypeCode, value);
    }

    public static IInfDocumentTypesEntityKey createInfDocumentTypesEntityKey(Long doctypeCode) {
        return new InfDocumentTypesEntityKey(doctypeCode);
    }

    public static IInfDocumentTypesEntityKey createInfDocumentTypesEntityKey() {
        return new InfDocumentTypesEntityKey();
    }

    public static IUnitsOfMeasureEntityKey createUnitsOfMeasureEntityKey() {
        return new UnitsOfMeasureEntityKey();
    }

    public static IUnitsOfMeasureEntityKey createUnitsOfMeasureEntityKey(Long unitCode) {
        return new UnitsOfMeasureEntityKey(unitCode);
    }

    /**
     * @return InfPresentationChannelEntitykey
     */
    public static IInfPresentationChannelEntityKey createInfPresentationChannelEntityKey() {

        return new InfPresentationChannelEntityKey();
    }

    public static IInfPresentationChannelEntityKey createInfPresentationChannelEntityKey(Long channelId) {

        return new InfPresentationChannelEntityKey(channelId);
    }

    public static IInfMobileCompaniesEntityKey createInfMobileCompaniesEntityKey() {
        return new InfMobileCompaniesEntityKey();
    }

    public static IInfMobileCompaniesEntityKey createInfMobileCompaniesEntityKey(Long mobCompanyCode) {
        return new InfMobileCompaniesEntityKey(mobCompanyCode);
    }

    /**
     * @return InfTmpDataTypesEntitykey
     */
    public static IInfTmpDataTypesEntityKey createInfTmpDataTypesEntityKey() {

        return new InfTmpDataTypesEntityKey();
    }

    public static IInfTmpDataTypesEntityKey createInfTmpDataTypesEntityKey(Long datatypCode) {

        return new InfTmpDataTypesEntityKey(datatypCode);
    }

    /**
     * @return InfTmpDataFieldsEntitykey
     */
    public static IInfTmpDataFieldsEntityKey createInfTmpDataFieldsEntityKey() {

        return new InfTmpDataFieldsEntityKey();
    }

    public static IInfTmpDataFieldsEntityKey createInfTmpDataFieldsEntityKey(Long datatypCode, Long fieldOrder) {

        return new InfTmpDataFieldsEntityKey(datatypCode, fieldOrder);
    }

    /**
     * @return InfTmpMigrCasesEntitykey
     */
    public static IInfTmpMigrCasesEntityKey createInfTmpMigrCasesEntityKey() {

        return new InfTmpMigrCasesEntityKey();
    }

    public static IInfTmpMigrCasesEntityKey createInfTmpMigrCasesEntityKey(Long datatypCode, Long caseCode) {

        return new InfTmpMigrCasesEntityKey(datatypCode, caseCode);
    }

    /**
     * @return InfTmpDataDisksEntitykey
     */
    public static IInfTmpDataDisksEntityKey createInfTmpDataDisksEntityKey() {

        return new InfTmpDataDisksEntityKey();
    }

    public static IInfTmpDataDisksEntityKey createInfTmpDataDisksEntityKey(Long datatypCode, Long diskCode) {

        return new InfTmpDataDisksEntityKey(datatypCode, diskCode);
    }

    /**
     * @return InfTmpDataEntitykey
     */
    public static IInfTmpDataEntityKey createInfTmpDataEntityKey() {

        return new InfTmpDataEntityKey();
    }

    public static IInfTmpDataEntityKey createInfTmpDataEntityKey(Long datatypCode, Long diskCode, Long civilId) {

        return new InfTmpDataEntityKey(datatypCode, diskCode, civilId);
    }


    public static IInfEservicesDocumentTypesEntityKey createInfEservicesDocumentTypesEntityKey(Long doctypeCode,
                                                                                               Long servicesId) {
        return new InfEservicesDocumentTypesEntityKey(doctypeCode, servicesId);
    }

    public static IInfEservicesTypesEntityKey createInfEservicesTypesEntityKey(Long servicesId) {
        return new InfEservicesTypesEntityKey(servicesId);
    }
    
    /**
     * @return InfTypesEntitykey
     */
    public static IIdentificationTypesEntityKey createInfTypesEntityKey() {

        return new IdentificationTypesEntityKey();
    }

    public static IIdentificationTypesEntityKey createIdentificationTypesEntityKey(Long idtypeCode) {

        return new IdentificationTypesEntityKey(idtypeCode);
    } 
public static IInfOldDataAuditEntityKey createInfOldDataAuditEntityKey( Long serial ) {
            
     return new InfOldDataAuditEntityKey (serial) ;
        } 
}
