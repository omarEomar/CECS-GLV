package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.dto.holidays.HolidaysSimpleDTO;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.inf.business.entity.ApprovalMakersEntity;
import com.beshara.csc.inf.business.entity.BloodGroupsEntity;
import com.beshara.csc.inf.business.entity.BuildingOwnerTypesEntity;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.CountryCitiesEntity;
import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.CurrenciesEntity;
import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntity;
import com.beshara.csc.inf.business.entity.DmlStatmentTypesEntity;
import com.beshara.csc.inf.business.entity.FieldTypesEntity;
import com.beshara.csc.inf.business.entity.FieldsEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntity;
import com.beshara.csc.inf.business.entity.GenderMaritalEntity;
import com.beshara.csc.inf.business.entity.GenderReligionEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GroupCountriesEntity;
import com.beshara.csc.inf.business.entity.HandicapStatusEntity;
import com.beshara.csc.inf.business.entity.HolidayTypesEntity;
import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.inf.business.entity.InfCitizensPassportsEntity;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntity;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfEservicesDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfEservicesTypesEntity;
import com.beshara.csc.inf.business.entity.InfGovernoratesEntity;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.inf.business.entity.InfMonthsEntity;
import com.beshara.csc.inf.business.entity.InfOperationTypesEntity;
import com.beshara.csc.inf.business.entity.InfPersonQualificationsEntity;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.inf.business.entity.KwStreetsEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntity;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.inf.business.entity.MapTypesEntity;
import com.beshara.csc.inf.business.entity.MaritalStatusEntity;
import com.beshara.csc.inf.business.entity.MilitaryStatusEntity;
import com.beshara.csc.inf.business.entity.NewspapersEntity;
import com.beshara.csc.inf.business.entity.OracleErrorsEntity;
import com.beshara.csc.inf.business.entity.PersonDataChangesEntity;
import com.beshara.csc.inf.business.entity.PersonParameterValuesBkEntity;
import com.beshara.csc.inf.business.entity.PersonParameterValuesEntity;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntity;
import com.beshara.csc.inf.business.entity.ReligionsEntity;
import com.beshara.csc.inf.business.entity.ResidentTypeEntity;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntity;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntity;
import com.beshara.csc.inf.business.entity.StreetZonesEntity;
import com.beshara.csc.inf.business.entity.SystemSettingsEntity;
import com.beshara.csc.inf.business.entity.TrxStatusEntity;
import com.beshara.csc.inf.business.entity.WeekDaysEntity;
import com.beshara.csc.inf.business.entity.YearsEntity;

import java.util.List;


public class InfDTOFactory {
    public static IApprovalMakersDTO createApprovalMakersDTO() {
        return new ApprovalMakersDTO();
    }

    public static IApprovalMakersDTO createApprovalMakersDTO(ApprovalMakersEntity approvalMakersEntity) {
        return new ApprovalMakersDTO(approvalMakersEntity);
    }

    public static IApprovalMakersDTO createApprovalMakersDTO(Long code, String name) {
        return new ApprovalMakersDTO(code, name);
    }


    public static IBloodGroupsDTO createBloodGroupsDTO() {
        return new BloodGroupsDTO();
    }

    public static IBloodGroupsDTO createBloodGroupsDTO(BloodGroupsEntity bloodGroupsEntity) {
        return new BloodGroupsDTO(bloodGroupsEntity);
    }

    public static IBuildingOwnerTypesDTO createBuildingOwnerTypesDTO() {
        return new BuildingOwnerTypesDTO();
    }

    public static IBuildingOwnerTypesDTO createBuildingOwnerTypesDTO(BuildingOwnerTypesEntity buildingOwnerTypesEntity) {
        return new BuildingOwnerTypesDTO(buildingOwnerTypesEntity);
    }

    public static IBuildingOwnerTypesDTO createBuildingOwnerTypesDTO(Long code, String name) {
        return new BuildingOwnerTypesDTO(code, name);
    }

    public static ICountriesDTO createCountriesDTO() {
        return new CountriesDTO();
    }

    public static ICountriesDTO createCountriesDTO(Long code, String name) {
        return new CountriesDTO(code, name);
    }

    public static ICountriesDTO createCountriesDTO(CountriesEntity countriesEntity) {
        return new CountriesDTO(countriesEntity);
    }

    public static ICountryCitiesDTO createCountryCitiesDTO() {
        return new CountryCitiesDTO();
    }

    public static ICountryCitiesDTO createCountryCitiesDTO(CountryCitiesEntity countryCitiesEntity) {
        return new CountryCitiesDTO(countryCitiesEntity);
    }

    public static ICountryGroupsDTO createCountryGroupsDTO() {
        return new CountryGroupsDTO();
    }

    public static ICountryGroupsDTO createCountryGroupsDTO(CountryGroupsEntity countryGroupsEntity) {
        return new CountryGroupsDTO(countryGroupsEntity);
    }

    public static ICurrenciesDTO createCurrenciesDTO() {
        return new CurrenciesDTO();
    }

    public static ICurrenciesDTO createCurrenciesDTO(Long currencyCode, String currencyName) {
        return new CurrenciesDTO(currencyCode, currencyName);
    }

    public static ICurrenciesDTO createCurrenciesDTO(CurrenciesEntity currenciesEntity) {
        return new CurrenciesDTO(currenciesEntity);
    }


    public static IInfMonthsDTO createMonthsDTO() {
        return new InfMonthsDTO();
    }

    public static IInfMonthsDTO createMonthsDTO(Long code, String name) {
        return new InfMonthsDTO(code, name);
    }

    public static IInfMonthsDTO createMonthsDTO(InfMonthsEntity infMonthsEntity) {
        return new InfMonthsDTO(infMonthsEntity);
    }

    public static IDecisionMakerTypesDTO createDecisionMakerTypesDTO() {
        return new DecisionMakerTypesDTO();
    }

    public static IDecisionMakerTypesDTO createDecisionMakerTypesDTO(Long code, String name) {
        return new DecisionMakerTypesDTO(code, name);
    }

    public static IDecisionMakerTypesDTO createDecisionMakerTypesDTO(DecisionMakerTypesEntity decisionMakerTypesEntity) {
        return new DecisionMakerTypesDTO(decisionMakerTypesEntity);
    }

    public static IDmlStatmentTypesDTO createDmlStatmentTypesDTO() {
        return new DmlStatmentTypesDTO();
    }

    public static IDmlStatmentTypesDTO createDmlStatmentTypesDTO(DmlStatmentTypesEntity dmlStatmentTypesEntity) {
        return new DmlStatmentTypesDTO(dmlStatmentTypesEntity);
    }

    public static IFieldsDTO createFieldsDTO() {
        return new FieldsDTO();
    }

    public static IFieldsDTO createFieldsDTO(FieldsEntity fieldsEntity) {
        return new FieldsDTO(fieldsEntity);
    }

    public static IFieldsDTO createFieldsDTO(Long code, String name, Long _displayType) {
        return new FieldsDTO(code, name, _displayType);
    }

    public static IFieldTypesDTO createFieldTypesDTO() {
        return new FieldTypesDTO();
    }

    public static IFieldTypesDTO createFieldTypesDTO(FieldTypesEntity fieldTypesEntity) {
        return new FieldTypesDTO(fieldTypesEntity);
    }

    public static IFieldTypesDTO createFieldTypesDTO(Long code, String name) {
        return new FieldTypesDTO(code, name);
    }

    public static IFieldValueDTO createFieldValueDTO() {
        return new FieldValueDTO();
    }

    public static IFieldValueDTO createFieldValueDTO(String code, String name) {
        return new FieldValueDTO(code, name);
    }

    public static IGenderCountryDTO createGenderCountryDTO() {
        return new GenderCountryDTO();
    }

    public static IGenderCountryDTO createGenderCountryDTO(GenderCountryEntity genderCountryEntity) {
        return new GenderCountryDTO(genderCountryEntity);
    }

    public static IGenderMaritalDTO createGenderMaritalDTO() {
        return new GenderMaritalDTO();
    }

    public static IGenderMaritalDTO createGenderMaritalDTO(GenderMaritalEntity genderMaritalEntity) {
        return new GenderMaritalDTO(genderMaritalEntity);
    }

    public static IGenderReligionDTO createGenderReligionDTO() {
        return new GenderReligionDTO();
    }

    public static IGenderReligionDTO createGenderReligionDTO(GenderReligionEntity genderReligionEntity) {
        return new GenderReligionDTO(genderReligionEntity);
    }

    public static IGenderTypesDTO createGenderTypesDTO() {
        return new GenderTypesDTO();
    }

    public static IGenderTypesDTO createGenderTypesDTO(Long code, String name) {
        return new GenderTypesDTO(code, name);
    }

    public static IGenderTypesDTO createGenderTypesDTO(GenderTypesEntity genderTypesEntity) {
        return new GenderTypesDTO(genderTypesEntity);
    }

    public static IInfGovernoratesDTO createGovernoratesDTO() {
        return new InfGovernoratesDTO();
    }

    public static IInfGovernoratesDTO createGovernoratesDTO(Long code, String name) {
        return new InfGovernoratesDTO(code, name);
    }

    public static IInfOperationTypesDTO createInfOperationTypesDTO() {
        return new InfOperationTypesDTO();
    }

    public static IInfOperationTypesDTO createInfOperationTypesDTO(Long code, String name) {
        return new InfOperationTypesDTO(code, name);
    }

    public static IInfOperationTypesDTO createInfOperationTypesDTO(InfOperationTypesEntity infOperationTypesEntity) {
        return new InfOperationTypesDTO(infOperationTypesEntity);
    }

    public static IInfGovernoratesDTO createGovernoratesDTO(InfGovernoratesEntity governoratesEntity) {
        return new InfGovernoratesDTO(governoratesEntity);
    }

    public static IGroupCountriesDTO createGroupCountriesDTO() {
        return new GroupCountriesDTO();
    }

    public static IGroupCountriesDTO createGroupCountriesDTO(GroupCountriesEntity groupCountriesEntity) {
        return new GroupCountriesDTO(groupCountriesEntity);
    }

    public static IHandicapStatusDTO createHandicapStatusDTO() {
        return new HandicapStatusDTO();
    }

    public static IHandicapStatusDTO createHandicapStatusDTO(Long code, String name) {
        return new HandicapStatusDTO(code, name);
    }

    public static IHandicapStatusDTO createHandicapStatusDTO(HandicapStatusEntity handicapStatusEntity) {
        return new HandicapStatusDTO(handicapStatusEntity);
    }

    public static IHolidaysDTO createHolidaysDTO() {
        return new HolidaysDTO();
    }

    public static IHolidaysDTO createHolidaysDTO(HolidaysEntity holidaysEntity) {
        return new HolidaysDTO(holidaysEntity);
    }

    public static IHolidaysSearchDTO createHolidaysSearchDTO() {
        return new HolidaysSearchDTO();
    }

    public static IHolidayTypesDTO createHolidayTypesDTO() {
        return new HolidayTypesDTO();
    }

    public static IHolidayTypesDTO createHolidayTypesDTO(HolidayTypesEntity holidayTypesEntity) {
        return new HolidayTypesDTO(holidayTypesEntity);
    }

    public static IHolidayTypesDTO createHolidayTypesDTO(Long code, String name) {
        return new HolidayTypesDTO(code, name);
    }

    public static IInfCitizensPassportsDTO createInfCitizensPassportsDTO() {
        return new InfCitizensPassportsDTO();
    }

    public static IInfCitizensPassportsDTO createInfCitizensPassportsDTO(InfCitizensPassportsEntity infCitizensPassportsEntity) {
        return new InfCitizensPassportsDTO(infCitizensPassportsEntity);
    }

    public static IInfCitizensResidentsDataDTO createInfCitizensResidentsDataDTO() {
        return new InfCitizensResidentsDataDTO();
    }

    public static IInfCitizensResidentsDataDTO createInfCitizensResidentsDataDTO(InfCitizensResidentsDataEntity infCitizensResidentsDataEntity) {
        return new InfCitizensResidentsDataDTO(infCitizensResidentsDataEntity);
    }

    public static IInfPersonQualificationsDTO createInfPersonQualificationsDTO() {
        return new InfPersonQualificationsDTO();
    }

    public static IInfPersonQualificationsDTO createInfPersonQualificationsDTO(InfPersonQualificationsEntity infPersonQualificationsEntity) {
        return new InfPersonQualificationsDTO(infPersonQualificationsEntity);
    }

    public static IKwMapDTO createKwMapDTO() {
        return new KwMapDTO();
    }

    public static IKwMapDTO createKwMapDTO(String code, String name) {
        return new KwMapDTO(code, name);
    }

    public static IKwMapDTO createKwMapDTO(KwMapEntity kwMapEntity) {
        return new KwMapDTO(kwMapEntity);
    }

    public static IKwStreetsDTO createKwStreetsDTO() {
        return new KwStreetsDTO();
    }

    public static IKwStreetsDTO createKwStreetsDTO(KwStreetsEntity kwStreetsEntity) {
        return new KwStreetsDTO(kwStreetsEntity);
    }

    public static IKwStreetsDTO createKwStreetsDTO(Long code, String name) {
        return new KwStreetsDTO(code, name);
    }

    public static IKwtCitizensResidentsDTO createKwtCitizensResidentsDTO() {
        return new KwtCitizensResidentsDTO();
    }

    public static IKwtCitizensResidentsDTO createKwtCitizensResidentsDTO(Long civilId, String fName, String sName,
                                                                         String tName, String lName) {
        return new KwtCitizensResidentsDTO(civilId, fName, sName, tName, lName);
    }

    public static IKwtCitizensResidentsDTO createKwtCitizensResidentsDTO(List list) {
        return new KwtCitizensResidentsDTO(list);
    }

    public static IKwtCitizensResidentsDTO createKwtCitizensResidentsDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        return new KwtCitizensResidentsDTO(kwtCitizensResidentsEntity);
    }

    public static IKwtCitizensResidentsSearchDTO createKwtCitizensResidentsSearchDTO() {
        return new KwtCitizensResidentsSearchDTO();
    }

    public static IKwtWorkDataDTO createKwtWorkDataDTO() {
        return new KwtWorkDataDTO();
    }

    public static IKwtWorkDataDTO createKwtWorkDataDTO(KwtWorkDataEntity kwtWorkDataEntity) {
        return new KwtWorkDataDTO(kwtWorkDataEntity);
    }


    public static IKwtWorkDataTreeDTO createKwtWorkDataTreeDTO() {
        return new KwtWorkDataTreeDTO();
    }

    public static IKwtWorkDataTreeDTO createKwtWorkDataTreeDTO(KwtWorkDataEntity kwtWorkDataEntity) {
        return new KwtWorkDataTreeDTO(kwtWorkDataEntity);
    }


    public static ILanguagesDTO createLanguagesDTO() {
        return new LanguagesDTO();
    }

    public static ILanguagesDTO createLanguagesDTO(Long languageCode, String languageName) {
        return new LanguagesDTO(languageCode, languageName);
    }

    public static ILanguagesDTO createLanguagesDTO(LanguagesEntity languagesEntity) {
        return new LanguagesDTO(languagesEntity);
    }

    public static IMapTypesDTO createMapTypesDTO() {
        return new MapTypesDTO();
    }

    public static IMapTypesDTO createMapTypesDTO(MapTypesEntity mapTypesEntity) {
        return new MapTypesDTO(mapTypesEntity);
    }

    public static IMaritalStatusDTO createMaritalStatusDTO() {
        return new MaritalStatusDTO();
    }

    public static IMaritalStatusDTO createMaritalStatusDTO(Long code, String marstatusMasName,
                                                           String marstatusFemName) {
        return new MaritalStatusDTO(code, marstatusMasName, marstatusFemName);
    }

    public static IMaritalStatusDTO createMaritalStatusDTO(MaritalStatusEntity maritalStatusEntity) {
        return new MaritalStatusDTO(maritalStatusEntity);
    }

    public static IMilitaryStatusDTO createMilitaryStatusDTO() {
        return new MilitaryStatusDTO();
    }

    public static IMilitaryStatusDTO createMilitaryStatusDTO(MilitaryStatusEntity militaryStatusEntity) {
        return new MilitaryStatusDTO(militaryStatusEntity);
    }

    public static INewspapersDTO createNewspapersDTO() {
        return new NewspapersDTO();
    }

    public static INewspapersDTO createNewspapersDTO(Long paperCode, String paperName) {
        return new NewspapersDTO(paperCode, paperName);
    }

    public static INewspapersDTO createNewspapersDTO(NewspapersEntity newspapersEntity) {
        return new NewspapersDTO(newspapersEntity);
    }

    public static IOracleErrorsDTO createOracleErrorsDTO() {
        return new OracleErrorsDTO();
    }

    public static IOracleErrorsDTO createOracleErrorsDTO(OracleErrorsEntity oracleErrorsEntity) {
        return new OracleErrorsDTO(oracleErrorsEntity);
    }

    public static IPersonDataChangesDTO createPersonDataChangesDTO() {
        return new PersonDataChangesDTO();
    }

    public static IPersonDataChangesDTO createPersonDataChangesDTO(PersonDataChangesEntity personDataChangesEntity) {
        return new PersonDataChangesDTO(personDataChangesEntity);
    }

    public static IPersonParameterValuesBkDTO createPersonParameterValuesBkDTO() {
        return new PersonParameterValuesBkDTO();
    }

    public static IPersonParameterValuesBkDTO createPersonParameterValuesBkDTO(PersonParameterValuesBkEntity personParameterValuesBkEntity) {
        return new PersonParameterValuesBkDTO(personParameterValuesBkEntity);
    }

    public static IPersonParameterValuesDTO createPersonParameterValuesDTO() {
        return new PersonParameterValuesDTO();
    }

    public static IPersonParameterValuesDTO createPersonParameterValuesDTO(PersonParameterValuesEntity personParameterValuesEntity) {
        return new PersonParameterValuesDTO(personParameterValuesEntity);
    }

    public static IPersonQualificationsDTO createPersonQualificationsDTO() {
        return new PersonQualificationsDTO();
    }

    public static IPersonQualificationsDTO createPersonQualificationsDTO(PersonQualificationsEntity infPersonQualificationsEntity) {
        return new PersonQualificationsDTO(infPersonQualificationsEntity);
    }

    public static IPersonsInformationDTO createPersonsInformationDTO() {
        return new PersonsInformationDTO();
    }

    public static IPersonsInformationDTO createPersonsInformationDTO(PersonsInformationEntity personsInformationEntity) {
        return new PersonsInformationDTO(personsInformationEntity);
    }

    public static IRelatedFieldsDTO createRelatedFieldsDTO() {
        return new RelatedFieldsDTO();
    }

    public static IRelatedFieldsDTO createRelatedFieldsDTO(RelatedFieldsEntity entity) {
        return new RelatedFieldsDTO(entity);
    }

    public static IReligionsDTO createReligionsDTO() {
        return new ReligionsDTO();
    }

    public static IReligionsDTO createReligionsDTO(Long code, String name) {
        return new ReligionsDTO(code, name);
    }

    public static IReligionsDTO createReligionsDTO(ReligionsEntity religionsEntity) {
        return new ReligionsDTO(religionsEntity);
    }

    public static IRequestDTO createRequestDTO() {
        return new RequestDTO();
    }

    public static IRequestDTO createRequestDTO(Long firstRowNumber, List<String> sortColumnList) {
        return new RequestDTO(firstRowNumber, sortColumnList);
    }

    public static IResidentTypeDTO createResidentTypeDTO() {
        return new ResidentTypeDTO();
    }

    public static IResidentTypeDTO createResidentTypeDTO(ResidentTypeEntity residentTypeEntity) {
        return new ResidentTypeDTO(residentTypeEntity);
    }

    public static IResidentTypeDTO createResidentTypeDTO(Long code, String name) {
        return new ResidentTypeDTO(code, name);
    }

    public static IResponseDTO createResponseDTO() {
        return new ResponseDTO();
    }

    public static ISeekerLanguageSkillsDTO createSeekerLanguageSkillsDTO() {
        return new SeekerLanguageSkillsDTO();
    }

    public static ISeekerLanguageSkillsDTO createSeekerLanguageSkillsDTO(SeekerLanguageSkillsEntity seekerLanguageSkillsEntity) {
        return new SeekerLanguageSkillsDTO(seekerLanguageSkillsEntity);
    }

    public static ISpecialCaseTypesDTO createSpecialCaseTypesDTO() {
        return new SpecialCaseTypesDTO();
    }

    public static ISpecialCaseTypesDTO createSpecialCaseTypesDTO(Long code, String name) {
        return new SpecialCaseTypesDTO(code, name);
    }

    public static ISpecialCaseTypesDTO createSpecialCaseTypesDTO(SpecialCaseTypesEntity specialCaseTypesEntity) {
        return new SpecialCaseTypesDTO(specialCaseTypesEntity);
    }

    public static IStreetZonesDTO createStreetZonesDTO() {
        return new StreetZonesDTO();
    }

    public static IStreetZonesDTO createStreetZonesDTO(StreetZonesEntity streetZonesEntity) {
        return new StreetZonesDTO(streetZonesEntity);
    }

    public static ISystemSettingsDTO createSystemSettingsDTO() {
        return new SystemSettingsDTO();
    }

    public static ISystemSettingsDTO createSystemSettingsDTO(SystemSettingsEntity systemSettingsEntity) {
        return new SystemSettingsDTO(systemSettingsEntity);
    }

    public static ITrxStatusDTO createTrxStatusDTO() {
        return new TrxStatusDTO();
    }

    public static ITrxStatusDTO createTrxStatusDTO(TrxStatusEntity trxStatusEntity) {
        return new TrxStatusDTO(trxStatusEntity);
    }

    public static ITrxStatusDTO createTrxStatusDTO(String trxstatusCode, String trxstatusName) {
        return new TrxStatusDTO(trxstatusCode, trxstatusName);
    }

    public static IWeekDaysDTO createWeekDaysDTO() {
        return new WeekDaysDTO();
    }

    public static IWeekDaysDTO createWeekDaysDTO(WeekDaysEntity weekDaysEntity) {
        return new WeekDaysDTO(weekDaysEntity);
    }

    public static IWeekDaysDTO createWeekDaysDTO(Long code, String name) {
        return new WeekDaysDTO(code, name);
    }

    public static IYearsDTO createYearsDTO() {
        return new YearsDTO();
    }

    public static IYearsDTO createYearsDTO(Long code, String name) {
        return new YearsDTO(code, name);
    }

    public static IYearsDTO createYearsDTO(YearsEntity yearsEntity) {
        return new YearsDTO(yearsEntity);
    }

    public static IYearsDTO createYearsDTO(YearsEntity yearsEntity, boolean simple) {
        return new YearsDTO(yearsEntity, simple);
    }

    public static ICountriesSearchDTO createCountriesSearchDTO() {
        return new CountriesSearchDTO();
    }

    public static IInfReasonDataDTO createReasonDataDTO() {
        return new InfReasonDataDTO();
    }

    public static IInfReasonTypesDTO createReasonTypesDTO() {
        return new InfReasonTypesDTO();
    }

    public static IInfGradeTypesDTO createGradeTypesDTO() {
        return new InfGradeTypesDTO();
    }

    public static IInfGradeTypesDTO createGradeTypesDTO(Long code, String name) {
        return new InfGradeTypesDTO(code, name);
    }

    public static IInfGradeTypesDTO createGradeTypesDTO(InfGradeTypesEntity gradeTypeEntity) {
        return new InfGradeTypesDTO(gradeTypeEntity);
    }


    /// new for InvGradeValuesDTO


    public static IInfGradeValuesDTO createInfGradeValuesDTO() {
        return new InfGradeValuesDTO();
    }


    public static IInfGradeValuesDTO createInfGradeValuesDTO(InfGradeValuesEntity gradeValuesEntity) {
        return new InfGradeValuesDTO(gradeValuesEntity);
    }

    public static IYearsSearchDTO createYearsSearchDto() {
        return new YearsSearchDTO();
    }
    // IInfDocumentTypesDTO

    public static IInfDocumentTypesDTO createInfDocumentTypesDTO() {
        return new InfDocumentTypesDTO();
    }

    public static IWifeSonParametersDTO createWifeSonParametersDTO() {
        return new WifeSonParametersDTO();
    }

    public static IWifeSonInfoDTO createWifeSonInfoDTO() {
        return new WifeSonInfoDTO();
    }

    public static IInfDocumentTypesDTO createInfDocumentTypesDTO(InfDocumentTypesEntity documentTypesEntity) {
        return new InfDocumentTypesDTO(documentTypesEntity);
    }


    public static IInfPresentationChannelDTO createInfPresentationChannelDTO() {

        return new InfPresentationChannelDTO();
    }

    public static IHolidaysSimpleDTO createHolidaysSimpleDTO() {

        return new HolidaysSimpleDTO();
    }

    public static IInfMobileCompaniesDTO createInfMobileCompaniesDTO() {
        return new InfMobileCompaniesDTO();
    }

    public static IInfTmpDataTypesDTO createInfTmpDataTypesDTO() {

        return new InfTmpDataTypesDTO();
    }

    public static IInfTmpDataFieldsDTO createInfTmpDataFieldsDTO() {

        return new InfTmpDataFieldsDTO();
    }

    public static IInfTmpMigrCasesDTO createInfTmpMigrCasesDTO() {

        return new InfTmpMigrCasesDTO();
    }

    public static IInfTmpDataDisksDTO createInfTmpDataDisksDTO() {

        return new InfTmpDataDisksDTO();
    }

    public static IInfTmpDataDTO createInfTmpDataDTO() {

        return new InfTmpDataDTO();
    }

    public static IInfEservicesTypesDTO createInfEservicesTypesDTO(InfEservicesTypesEntity infEservicesTypesEntity) {
        return new InfEservicesTypesDTO(infEservicesTypesEntity);
    }

    public static IInfEservicesDocumentTypesDTO createInfEservicesDocumentTypesDTO(InfEservicesDocumentTypesEntity infEservicesDocumentTypesEntity) {
        return new InfEservicesDocumentTypesDTO(infEservicesDocumentTypesEntity);
    }
	
	public static PersonDocAttachemntsDTO createPersonDocAttachemntsDTO() {

        return new PersonDocAttachemntsDTO();
    }
        
    public static IdentificationTypesDTO createIdentificationTypesDTO() {

        return new IdentificationTypesDTO();
    }

}
