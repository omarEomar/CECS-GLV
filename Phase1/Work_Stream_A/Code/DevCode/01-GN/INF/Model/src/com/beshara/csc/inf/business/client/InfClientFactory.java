package com.beshara.csc.inf.business.client;


import com.beshara.base.deploy.SessionBeanProvider;
import com.beshara.common.factory.Factory;

import java.util.HashMap;
import java.util.Map;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents The Factory Which Generates appropriate Implementation * Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public abstract class InfClientFactory {
    private static SessionBeanProvider sessionBeanProvider;
    private static InfClientFactory instance;
    // default IKwtCitizensResidentsClient instance
    private static IKwtCitizensResidentsClient kwtCitizensResidentsClient;
    // IKwtCitizensResidentsClient instace for center [optional]
    private static IKwtCitizensResidentsClient kwtCitizensResidentsClientForCenter;
    // IKwtCitizensResidentsClient instances for each ministry [optional]
    private static Map<Long, IKwtCitizensResidentsClient> kwtCitizensResidentsClientForMinistries =
        new HashMap<Long, IKwtCitizensResidentsClient>();
    // get the default IKwtCitizensResidentsClient instance


    /**
     * ApprovalMakersClientFactory Default Constructor */
    public InfClientFactory() {
    }

    /**
     * @return IApprovalMakersClient
     */
    public static IApprovalMakersClient getApprovalMakersClient() {
        return new ApprovalMakersClientImpl();
    }

    private static IInfGradeTypesClient gradeTypesClient = null;

    /**
     * @return IInfGradeTypesClient
     */
    public static IInfGradeTypesClient getGradeTypesClient() {
        return new InfGradeTypesClientImpl();
    }

    /**
     * @return IInfGradeValuesClient
     */
    public static IInfGradeValuesClient getInfGradeValuesClient() {
        return new InfGradeValuesClientImpl();
    }

    //////////////////////
    private static IInfDocumentTypesClient documentTypesClient = null;

    /**
     * @return IInfDocumentTypesClient
     */
    public static IInfDocumentTypesClient getInfDocumentTypesClient() {

        return new InfDocumentTypesClientImpl();
    }


    ///////////////

    /**
     * @return IUnitsOfMeasureClient
     */
    public static IUnitsOfMeasureClient getUnitsOfMeasureClient() {
        return new UnitsOfMeasureClientImpl();
    }


    /**
     * @return IBloodGroupsClient
     */
    public static IBloodGroupsClient getBloodGroupsClient() {
        return new BloodGroupsClientImpl();
    }
    private static IInfMonthsClient monthsClient = null;

    /**
     * @return IGenderTypesClient
     */
    public static IInfMonthsClient getMonthsClient() {


        return new InfMonthsClientImpl();

    }

    /**
     * @return IBuildingOwnerTypesClient
     */
    public static IBuildingOwnerTypesClient getBuildingOwnerTypesClient() {
        return new BuildingOwnerTypesClientImpl();
    }

    /**
     * @return ICountriesClient
     */
    public static ICountriesClient getCountriesClient() {
        return new CountriesClientImpl();
    }

    /**
     * @return ICountryCitiesClient
     */
    public static ICountryCitiesClient getCountryCitiesClient() {
        return new CountryCitiesClientImpl();
    }

    /**
     * @return ICountryGroupsClient
     */
    public static ICountryGroupsClient getCountryGroupsClient() {
        return new CountryGroupsClientImpl();
    }

    /**
     * @return ICurrenciesClient
     */
    public static ICurrenciesClient getCurrenciesClient() {
        return new CurrenciesClientImpl();
    }

    /**
     * @return IGenderTypesClient
     */
    public static IDecisionMakerTypesClient getDecisionMakerTypesClient() {
        return new DecisionMakerTypesClientImpl();
    }

    public static InfClientFactory getInstance() {
        if (instance == null) {
            if (Factory.containsInstance(InfClientFactory.class)) {
                instance = (InfClientFactory)Factory.getImplInstance(InfClientFactory.class);
            } else {
                instance = new InfSessionBeanClientFactory();
            }
        }
        return instance;
    }

    //    public IDecisionMakerTypesClient getDecisionMakerTypesClientImpl() {
    //        if (decisionMakerTypesClient == null) {
    //            decisionMakerTypesClient = createDecisionMakerTypesClient();
    //        }
    //        return decisionMakerTypesClient;
    //    }

    // protected abstract IDecisionMakerTypesClient createDecisionMakerTypesClient();

    //    /**
    //     * @return IDecisionMakerTypesClient
    //     */
    //    public static IDecisionMakerTypesClient getDecisionMakerTypesClient() {
    //
    //        return getInstance().getDecisionMakerTypesClientImpl();
    //    }
    private static IDmlStatmentTypesClient dmlStatmentTypesClient = null;

    /**
     * @return IDmlStatmentTypesClient
     */
    public static IDmlStatmentTypesClient getDmlStatmentTypesClient() {

        return new DmlStatmentTypesClientImpl();

    }
    private static IFieldsClient fieldsClient = null;

    /**
     * @return IFieldsClient
     */
    public static IFieldsClient getFieldsClient() {
        if (fieldsClient == null) {
            fieldsClient = new FieldsClientImpl();
        }
        return fieldsClient;
    }

    /**
     * @return IFieldTypesClient
     */
    public static IFieldTypesClient getFieldTypesClient() {
        return new FieldTypesClientImpl();
    }
    private static IGenderCountryClient genderCountryClient = null;

    /**
     * @return IGenderCountryClient
     */
    public static IGenderCountryClient getGenderCountryClient() {
        return new GenderCountryClientImpl();
    }
    private static IGenderMaritalClient genderMaritalClient = null;

    /**
     * @return IGenderMaritalClient
     */
    public static IGenderMaritalClient getGenderMaritalClient() {
        return new GenderMaritalClientImpl();
    }

    /**
     * @return IGenderReligionClient
     */
    public static IGenderReligionClient getGenderReligionClient() {
        return new GenderReligionClientImpl();
    }
    private static IGenderTypesClient genderTypesClient = null;

    /**
     * @return IGenderTypesClient
     */
    public static IGenderTypesClient getGenderTypesClient() {
        if (genderTypesClient == null) {
            genderTypesClient = new GenderTypesClientImpl();
        }
        return genderTypesClient;
    }
    private static IInfGovernoratesClient infGovernoratesClient = null;

    /**
     * @return IGenderTypesClient
     */
    public static IInfGovernoratesClient getInfGovernoratesClient() {
        if (infGovernoratesClient == null) {
            infGovernoratesClient = new InfGovernoratesClientImpl();
        }
        return infGovernoratesClient;
    }
    private static IInfOperationTypesClient infOperationTypesClient = null;

    /**
     * @return IGenderTypesClient
     */
    public static IInfOperationTypesClient getInfOperationTypesClient() {
        return new InfOperationTypesClientImpl();
    }
    private static IGroupCountriesClient groupCountriesClient = null;

    /**
     * @return IGroupCountriesClient
     */
    public static IGroupCountriesClient getGroupCountriesClient() {
        if (groupCountriesClient == null) {
            groupCountriesClient = new GroupCountriesClientImpl();
        }
        return groupCountriesClient;
    }
    private static IHandicapStatusClient handicapStatusClient = null;

    /**
     * @return IHandicapStatusClient
     */
    public static IHandicapStatusClient getHandicapStatusClient() {
        if (handicapStatusClient == null) {

            handicapStatusClient = new HandicapStatusClientImpl();
        }
        return handicapStatusClient;
    }
    private static IHolidaysClient holidaysClient = null;

    /**
     * @return IHolidaysClient
     */
    public static IHolidaysClient getHolidaysClient() {
        if (holidaysClient == null) {
            holidaysClient = new HolidaysClientImpl();
        }
        return holidaysClient;
    }
    private static IHolidayTypesClient holidayTypesClient = null;

    /**
     * @return IHolidayTypesClient
     */
    public static IHolidayTypesClient getHolidayTypesClient() {
        if (holidayTypesClient == null) {
            holidayTypesClient = new HolidayTypesClientImpl();
        }
        return holidayTypesClient;
    }

    private static IHolidayTypesClient holidayTypesClientCenter = null;

    /**
     * @return IHolidayTypesClient
     */
    public static IHolidayTypesClient getHolidayTypesClientForCenter() {
        if (holidayTypesClientCenter == null) {
            holidayTypesClientCenter = new HolidayTypesClientImpl();
        }
        return holidayTypesClientCenter;
    }

    private static IKwMapClient kwMapClient = null;

    /**
     * @return IKwMapClient
     */
    public static IKwMapClient getKwMapClient() {
        if (kwMapClient == null) {
            kwMapClient = new KwMapClientImpl();
        }
        return kwMapClient;
    }

    /**
     * @return IKwStreetsClient
     */
    public static IKwStreetsClient getKwStreetsClient() {
        return new KwStreetsClientImpl();
    }

    /**
     * @return IKwtWorkDataClient
     */
    public static IKwtWorkDataClient getKwtWorkDataClient() {
        return new KwtWorkDataClientImpl();
    }
    private static IKwtWorkDataCMTClient kwtWorkDataCMTClient = null;

    /**
     * @return IKwtWorkDataCMTClient
     */
    public static IKwtWorkDataCMTClient getKwtWorkDataCMTClient() {
        return new KwtWorkDataCMTClientImpl();
    }
    private static ILanguagesClient languagesClient = null;

    /**
     * @return ILanguagesClient
     */
    public static ILanguagesClient getLanguagesClient() {
        return new LanguagesClientImpl();
    }
    private static IMapTypesClient mapTypesClient = null;

    /**
     * @return IMapTypesClient
     */
    public static IMapTypesClient getMapTypesClient() {
        return new MapTypesClientImpl();
    }

    /**
     * @return IMaritalStatusClient
     */
    public static IMaritalStatusClient getMaritalStatusClient() {
        return new MaritalStatusClientImpl();
    }
    private static IMilitaryStatusClient militaryStatusClient = null;

    /**
     * @return IMilitaryStatusClient
     */
    public static IMilitaryStatusClient getMilitaryStatusClient() {
        return new MilitaryStatusClientImpl();
    }
    private static INewspapersClient newspapersClient = null;

    /**
     * @return INewspapersClient
     */
    public static INewspapersClient getNewspapersClient() {
        if (newspapersClient == null) {
            newspapersClient = new NewspapersClientImpl();
        }
        return newspapersClient;
    }
    private static IOracleErrorsClient oracleErrorsClient = null;

    /**
     * @return IOracleErrorsClient
     */
    public static IOracleErrorsClient getOracleErrorsClient() {
        return new OracleErrorsClientImpl();
    }
    private static IPersonDataChangesClient personDataChangesClient = null;

    /**
     * @return IPersonDataChangesClient
     */
    public static IPersonDataChangesClient getPersonDataChangesClient() {
        return new PersonDataChangesClientImpl();
    }
    private static IPersonParameterValuesClient personParameterValuesClient = null;

    /**
     * @return IPersonParameterValuesClient
     */
    public static IPersonParameterValuesClient getPersonParameterValuesClient() {
        return new PersonParameterValuesClientImpl();
    }
    private static IPersonParameterValuesBkClient personParameterValuesBkClient = null;

    /**
     * @return IPersonParameterValuesBkClient
     */
    public static IPersonParameterValuesBkClient getPersonParameterValuesBkClient() {
        return new PersonParameterValuesBkClientImpl();
    }
    private static IPersonQualificationsClient personQualificationsClient = null;

    /**
     * @return IPersonQualificationsClient
     */
    public static IPersonQualificationsClient getPersonQualificationsClient() {

        return new PersonQualificationsClientImpl();

    }

    private static IPersonQualificationsCMTClient personQualificationsCMTClient = null;

    /**
     * @return IPersonQualificationsClient
     */
    public static IPersonQualificationsCMTClient getPersonQualificationsCMTClient() {
        return new PersonQualificationsCMTClientImpl();
    }

    private static IPersonsInformationCMTClient personsInformationCMTClient = null;

    /**
     * @return IPersonsInformationCMTClient
     */
    public static IPersonsInformationCMTClient getPersonsInformationCMTClient() {
        if (personsInformationCMTClient == null) {
            personsInformationCMTClient = new PersonsInformationCMTClientImpl();
        }
        return personsInformationCMTClient;
    }

    private static IPersonsInformationClient personsInformationClient = null;

    /**
     * @return IPersonsInformationClient
     */
    public static IPersonsInformationClient getPersonsInformationClient() {
        if (personsInformationClient == null) {
            personsInformationClient = new PersonsInformationClientImpl();
        }
        return personsInformationClient;
    }
    private static IRelatedFieldsClient relatedFieldsClient = null;

    /**
     * @return IRelatedFieldsClient
     */
    public static IRelatedFieldsClient getRelatedFieldsClient() {
        if (relatedFieldsClient == null) {
            relatedFieldsClient = new RelatedFieldsClientImpl();
        }
        return relatedFieldsClient;
    }
    private static IReligionsClient religionsClient = null;

    /**
     * @return IReligionsClient
     */
    public static IReligionsClient getReligionsClient() {
        if (religionsClient == null) {
            religionsClient = new ReligionsClientImpl();
        }
        return religionsClient;
    }
    private static IResidentTypeClient residentTypeClient = null;

    /**
     * @return IResidentTypeClient
     */
    public static IResidentTypeClient getResidentTypeClient() {

        return new ResidentTypeClientImpl();

    }
    private static ISeekerLanguageSkillsClient seekerLanguageSkillsClient = null;

    /**
     * @return ISeekerLanguageSkillsClient
     */
    public static ISeekerLanguageSkillsClient getSeekerLanguageSkillsClient() {
        if (seekerLanguageSkillsClient == null) {
            seekerLanguageSkillsClient = new SeekerLanguageSkillsClientImpl();
        }
        return seekerLanguageSkillsClient;
    }
    private static ISpecialCaseTypesClient specialCaseTypesClient = null;

    /**
     * @return ISpecialCaseTypesClient
     */
    public static ISpecialCaseTypesClient getSpecialCaseTypesClient() {
        if (specialCaseTypesClient == null) {
            specialCaseTypesClient = new SpecialCaseTypesClientImpl();
        }
        return specialCaseTypesClient;
    }
    private static IStreetZonesClient streetZonesClient = null;

    /**
     * @return IStreetZonesClient
     */
    public static IStreetZonesClient getStreetZonesClient() {
        if (streetZonesClient == null) {
            streetZonesClient = new StreetZonesClientImpl();
        }
        return streetZonesClient;
    }
    private static ISystemSettingsClient systemSettingsClient = null;

    /**
     * @return ISystemSettingsClient
     */
    public static ISystemSettingsClient getSystemSettingsClient() {
        if (systemSettingsClient == null) {
            systemSettingsClient = new SystemSettingsClientImpl();
        }
        return systemSettingsClient;
    }
    private static ITrxStatusClient trxStatusClient = null;

    /**
     * @return ITrxStatusClient
     */
    public static ITrxStatusClient getTrxStatusClient() {
        if (trxStatusClient == null) {
            trxStatusClient = new TrxStatusClientImpl();
        }
        return trxStatusClient;
    }
    private static IWeekDaysClient weekDaysClient = null;

    /**
     * @return IWeekDaysClient
     */
    public static IWeekDaysClient getWeekDaysClient() {
        if (weekDaysClient == null) {
            weekDaysClient = new WeekDaysClientImpl();
        }
        return weekDaysClient;
    }
    private static IYearsClient yearsClient = null;

    /**
     * @return IYearsClient
     */
    public static IYearsClient getYearsClient() {
        if (yearsClient == null) {
            yearsClient = new YearsClientImpl();
        }
        return yearsClient;
    }
    private static IInfCitizensResidentsDataClient infCitizensResidentsDataClient = null;

    /**
     * @return IInfCitizensResidentsDataClient
     */
    public static IInfCitizensResidentsDataClient getInfCitizensResidentsDataClient() {

        return new InfCitizensResidentsDataClientImpl();

    }

    /**
     * @return IInfCitizensPassportsClient
     */
    public static IInfCitizensPassportsClient getInfCitizensPassportsClient() {
        return new InfCitizensPassportsClientImpl();
    }
    private static IKwtCitizensResidentsCMTClient kwtCitizensResidentsCMTClient = null;

    /**
     * @return IKwtCitizensResidentsClient
     */
    public static IKwtCitizensResidentsCMTClient getKwtCitizensResidentsCMTClient() {

        return new KwtCitizensResidentsCMTClientImpl();

    }

    private static void getSessionBeanProvider() {
        if (sessionBeanProvider == null) {
            sessionBeanProvider = (SessionBeanProvider)Factory.getInstance(SessionBeanProvider.class);
        }
    }

    private static IInfReasonDataClient reasonDataClient = null;

    public static IInfReasonDataClient getReasonDataClient() {

        return new InfReasonDataClientImpl();

    }

    private static IInfReasonTypesClient reasonTypesClient = null;

    public static IInfReasonTypesClient getReasonTypesClient() {
        return new InfReasonTypesClientImpl();
    }

    private static IHandicapStatusCMTClient handicapStatusCMTClient = null;

    public static IHandicapStatusCMTClient getHandicapStatusCMTClient() {
        return new HandicapStatusCMTClientImpl();
    }

    public static IKwtCitizensResidentsClient getKwtCitizensResidentsClient() {
        return getInstance().getKwtCitizensResidentsClientImpl();
    }
    // get IKwtCitizensResidentsClient instance for center [optional]

    public static IKwtCitizensResidentsClient getKwtCitizensResidentsClientForCenter() {
        return getInstance().getKwtCitizensResidentsClientImplForCenter();
    }
    // get IKwtCitizensResidentsClient instance for ministry [optional]

    public static IKwtCitizensResidentsClient getKwtCitizensResidentsClientForMinistry(Long ministryCode) {
        return getInstance().getKwtCitizensResidentsClientImplForMinistry(ministryCode);
    }
    // get the default IKwtCitizensResidentsClient instance

    public IKwtCitizensResidentsClient getKwtCitizensResidentsClientImpl() {
        if (kwtCitizensResidentsClient == null) {
            kwtCitizensResidentsClient = createKwtCitizensResidentsClient();
        }
        return kwtCitizensResidentsClient;
    }
    // get IKwtCitizensResidentsClient instance for center [optional]

    public IKwtCitizensResidentsClient getKwtCitizensResidentsClientImplForCenter() {
        if (kwtCitizensResidentsClientForCenter == null) {
            kwtCitizensResidentsClientForCenter = createKwtCitizensResidentsClientForCenter();
        }
        return kwtCitizensResidentsClientForCenter;
    }
    // get IKwtCitizensResidentsClient instance for ministry [optional]

    public IKwtCitizensResidentsClient getKwtCitizensResidentsClientImplForMinistry(Long ministryCode) {
        synchronized (kwtCitizensResidentsClientForMinistries) {
            IKwtCitizensResidentsClient clientInstance = kwtCitizensResidentsClientForMinistries.get(ministryCode);
            if (clientInstance == null) {
                clientInstance = createKwtCitizensResidentsClientForMinistry(ministryCode);
                kwtCitizensResidentsClientForMinistries.put(ministryCode, clientInstance);
            }
            return clientInstance;
        }
    }
    // create the default IKwtCitizensResidentsClient instance

    protected abstract IKwtCitizensResidentsClient createKwtCitizensResidentsClient();
    // create IKwtCitizensResidentsClient instance for center [optional]

    protected abstract IKwtCitizensResidentsClient createKwtCitizensResidentsClientForCenter();
    // create IKwtCitizensResidentsClient instance for ministry [optional]

    protected abstract IKwtCitizensResidentsClient createKwtCitizensResidentsClientForMinistry(Long ministryCode);

    protected abstract IInfDocumentTypesClient createInfDocumentTypesClient();

    /**
     * @return IInfPresentationChannelClient
     */
    public static IInfPresentationChannelClient getInfPresentationChannelClient() {

        return new InfPresentationChannelClientImpl();
    }
    
    public static IInfMobileCompaniesClient getInfMobileCompaniesClient() {
        return new InfMobileCompaniesClientImpl();
    }

/**
* @return IInfTmpDataTypesClient
*/
public static IInfTmpDataTypesClient getInfTmpDataTypesClient() {
        
 return new InfTmpDataTypesClientImpl () ;
    } 

/**
* @return IInfTmpDataFieldsClient
*/
public static IInfTmpDataFieldsClient getInfTmpDataFieldsClient() {
        
 return new InfTmpDataFieldsClientImpl () ;
    } 

/**
* @return IInfTmpMigrCasesClient
*/
public static IInfTmpMigrCasesClient getInfTmpMigrCasesClient() {
        
 return new InfTmpMigrCasesClientImpl () ;
    } 

/**
* @return IInfTmpDataDisksClient
*/
public static IInfTmpDataDisksClient getInfTmpDataDisksClient() {
        
 return new InfTmpDataDisksClientImpl () ;
    } 

/**
* @return IInfTmpDataClient
*/
public static IInfTmpDataClient getInfTmpDataClient() {
        
 return new InfTmpDataClientImpl () ;
    } 

    public static IPersonDocAttachemntsClient getPersonDocAttachemntsClient() {

        return new PersonDocAttachemntsClientImpl();
    }
    
    public static IIdentificationTypesClient getInfTypesClient() {

        return new IdentificationTypesClientImpl();
    }

}
