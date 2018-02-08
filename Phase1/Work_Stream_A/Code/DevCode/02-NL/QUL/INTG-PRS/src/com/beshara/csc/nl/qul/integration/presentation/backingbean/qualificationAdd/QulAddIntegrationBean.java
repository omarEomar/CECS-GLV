package com.beshara.csc.nl.qul.integration.presentation.backingbean.qualificationAdd;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.CountriesDTO;
import com.beshara.csc.inf.business.dto.IInfGradeTypesDTO;
import com.beshara.csc.inf.business.dto.IInfGradeValuesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IPersonsInformationEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.qul.business.client.ICenterQualificationsClient;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsSearchCriteriaDTO;
import com.beshara.csc.nl.qul.business.dto.ICentersDTO;
import com.beshara.csc.nl.qul.business.dto.IEduLevelGenSepcsDTO;
import com.beshara.csc.nl.qul.business.dto.IGenSpecMajSpecsDTO;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;
import com.beshara.csc.nl.qul.business.dto.IQualificationsSearchCriteriaDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.business.entity.CenterQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.ICenterQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.ICentersEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.centers.QulCentersIntegrationBean;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.edulevels.QulEduLevelsIntegrationBean;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.ManagedConstantsBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Date;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class QulAddIntegrationBean extends ManyToManyDetailsMaintain {


    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private Long civilId;
    private String certifiedQul = "C"; // is flag referes to certified qulification
    private String nonCertifiedQul = "NC"; //is flag referes to non certified qulification

    private String selectedQulType = nonCertifiedQul;
    private String qulDegreeType;
    private String selectedQulDegreeValue;
    private boolean inValidValue = false;
    private boolean inValidMissionTypeCode = false;
    private List degreeValuesList = new ArrayList();
    private int qualificationListSize = 0;
    private String qualificationName;
    private String searchQulifictaionString;
    private Long centerCode;
    private Long societyCode;
    private int shangLangth = 1;
    private ArrayList<IPersonsInformationDTO> personsInformationDTOList;
    private String ministryName = "";
    //NOTE 2 we will use getPageDTO instead of defining another attribute
    //will not applyed ( argue and accepted )
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    //used just for repositioning
    private HtmlDataTable qualificationDataTable = new HtmlDataTable();
    // used for binding with certified qul page
    private IPersonQualificationsDTO personQualificationsDTO = InfDTOFactory.createPersonQualificationsDTO();
    // used for binding with non certified qul page
    private IPersonsInformationDTO personsInformationDTO = InfDTOFactory.createPersonsInformationDTO();
    //    private List qulificationMinistriesList = new ArrayList();
    private List countriesList = new ArrayList();
    private List centersList = new ArrayList();
    private List qualificationList = new ArrayList();
    private String searchQulifictaionCode = "";

    private String qulCountryCode;
    private List qulCountriesList = new ArrayList();
    private String qulCenterCode;
    private List qulCentersList = new ArrayList();

    private List<IBasicDTO> educationLevelsList;
    private List<IEduLevelGenSepcsDTO> generalSpecslist;
    private List<IGenSpecMajSpecsDTO> majorSpecsList;
    private String educationKey;
    private String generalSpecsKey;
    private String majorSpecsKey;

    private String qulAddDivTitleName;

    private String countryFieldValError;
    private String selectedCountryId = ISystemConstant.KUWAIT_NATIONALITY.toString();
    private String countryCode = getSelectedCountryId();
    private List qul_country_list = null;
    private List certificatesList;
    private List searchCertificatesList;
    private int searchCertificatesListSize;
    private String selectedCertificateId;// = getVirtualConstValue();
    private List<String> AvailableCountryCodeList = new ArrayList<String>();
    public final String INVALID_COUNTRY_CODE = "inavlid_country_code";
    private IBasicDTO countryInfo = new BasicDTO();
    private ResourceBundle bundle = null;
    // All search criteria
    private String selectedCenterName = "";
    private String certificateLevel = "";
    private String certificateLevelId;
    private boolean showEduLevelsTree;
    private Boolean qulCenterTreeDiv = true;
    private Boolean enableResetData = false;
    private String fireComponentID = "1";
    //  Activiation btn flags
    private Boolean addBtnDisabled = true;
    private ITreeDTO centerDTO;

    private ITreeDTO levelDTO;
    private ICenterQualificationsSearchCriteriaDTO centerQualificationsSearchCriteriaDTO =
        QulDTOFactory.createCenterQualificationsSearchCriteriaDTO();
    private String levelFieldValError = "";
    public final String INVALID_EDUCATION_LEVEL_VAL = "inavlid_edu_val";
    public static final String BEAN_NAME = "qualificationAddHelperBean";
    public static final String BUNDLE = "com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration";
    private IBasicClient centerClient;
    private IBasicClient levelsClient;

    //////////////////////////////////////////////////////
    private String prepareMethodName;
    private String returnMethodName;
    private Boolean disableQulDateRegistration;
    private String backPage = "filterPage";
    private QulCentersIntegrationBean qulCentersIntegrationBean;
    private QulEduLevelsIntegrationBean qulEduLevelsIntegrationBean;
    private QulIntegrationListBean qulIntegrationListBean = QulIntegrationListBean.getInstance();
    private IntegrationBean integrationBean = IntegrationBean.getInstance();
    
    private String certificateKey = null;
    private String certificateName = null;

    public QulAddIntegrationBean() {

        setBundle(ResourceBundle.getBundle("com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration"));
        setClient(QulClientFactory.getCenterQualificationsClient());
        setNameMaxLength(380);
        setSingleSelection(true);
        if (qulCentersIntegrationBean == null) {
            qulCentersIntegrationBean = new QulCentersIntegrationBean(BEAN_NAME, "divTree");
        }
        if(qulEduLevelsIntegrationBean == null){
            qulEduLevelsIntegrationBean = new QulEduLevelsIntegrationBean(BEAN_NAME, "divLov");    
        }
//        setIntegrationDiv2TitleKey("qualificationAdd");
//        setLovDivTitleKey("qualificationAdd");
//        setTreeDivTitleKey("qualificationAdd");
    }

    @Override
    public void scrollerAction(ActionEvent ae) {
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.integrationDiv2);");
        //pageIndexAdd=((HtmlDataScroller)ae.getComponent()).getPageIndex();
    }
    
    public void initiateBeanOnce() {
        HashMap map = integrationBean.getHmBaseBeanFrom();
        QulIntegrationListBean qulIntBean = (QulIntegrationListBean)map.get("qulListIntegrationBean");
        if (integrationBean != null && integrationBean.getHmBaseBeanFrom().size() > 0) {
            if ((integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")) != null) {
                kwtCitizensResidentsDTO = qulIntBean.getKwtCitizenDTO();
                backPage = (String)map.get("backPage");
            }
        }
        //System.out.println("startcenter: " + (new Date()));
        initCenterIntegration();
        //System.out.println("endCenter: " + (new Date()));
        init();
        //System.out.println("endInit: " + (new Date()));
        fillQul_country_list();
        //System.out.println("endCountry: " + (new Date()));
        fillQulCentersList();

    }


    public void setLevelsClient(IBasicClient levelsClient) {
        this.levelsClient = levelsClient;
    }

    public IBasicClient getLevelsClient() {
        return QulClientFactory.getEducationLevelsClient();
    }


    public void setCenterClient(IBasicClient centerClient) {
        this.centerClient = centerClient;
    }

    public IBasicClient getCenterClient() {
        return QulClientFactory.getCentersClient();
    }

    public void setCountryFieldValError(String countryFieldValError) {
        this.countryFieldValError = countryFieldValError;
    }

    public String getCountryFieldValError() {
        return countryFieldValError;
    }


    public void setSelectedCenterName(String selectedCenterName) {
        this.selectedCenterName = selectedCenterName;
    }

    public String getSelectedCenterName() {
        return selectedCenterName;
    }

    public void setAddBtnDisabled(Boolean addBtnDisabled) {
        this.addBtnDisabled = addBtnDisabled;
    }

    public Boolean getAddBtnDisabled() {
        return addBtnDisabled;
    }

    public void fillQulCentersList() {
        if (selectedCountryId != null &&
            !selectedCountryId.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
            try {
                qulCentersList =
                        QulClientFactory.getCentersClient().getAllByCountry(InfEntityKeyFactory.createCountriesEntityKey(Long.parseLong(selectedCountryId)));
            } catch (DataBaseException e) {
                e.printStackTrace();
                qulCentersList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                qulCentersList = new ArrayList();
            }
        }
    }

    public void filterDataTableByCountry() {
        qulCentersIntegrationBean.initializeTreeDiv();
        resetValues();
        certificatesList = new ArrayList();
        certificateLevel = "";
        setCountryCode(selectedCountryId);
        initCenterIntegration();
        qulCentersIntegrationBean.cancelTreeDivSearch();
        setSelectedCenterName("");
        qulCenterCode = null;
        //qulCenterCode = getManagedConstantsBean().getVirtualStringValueConstant();
        fillQulCentersList();
//        if (countryCode != null &&
//            !countryCode.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
//            try {
//                qulCentersList =
//                        QulClientFactory.getCentersClient().getAllByCountry(InfEntityKeyFactory.createCountriesEntityKey(Long.parseLong(countryCode)));
//            } catch (DataBaseException e) {
//                e.printStackTrace();
//                qulCentersList = new ArrayList();
//            } catch (Exception e) {
//                e.printStackTrace();
//                qulCentersList = new ArrayList();
//            }
//        }
    }

    private void setAvailableCountryCodes() {
        getAvailableCountryCodeList().clear();

        for (Object country : qul_country_list) {
            getAvailableCountryCodeList().add(((IBasicDTO)country).getCode().getKey());
        }
    }

    private void fillQul_country_list() {
        if (qul_country_list == null) {
            try {
                qul_country_list = InfClientFactory.getCountriesClient().getCodeNameInCenter();
                setAvailableCountryCodes();
            } catch (DataBaseException e) {
                e.printStackTrace();
                qul_country_list = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                qul_country_list = new ArrayList();
            }
        }
    }

    public List getQul_country_list() {
        return qul_country_list;
    }

    public void setCertificatesList(List certificatesList) {
        this.certificatesList = certificatesList;
    }

    public List getCertificatesList() {
        return certificatesList;
    }


    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }


    public void setSelectedCountryId(String selectedCountryId) {

        this.selectedCountryId = selectedCountryId;

    }

    public String getSelectedCountryId() {
        return selectedCountryId;
    }

    private boolean checkValidVal() {
        return this.getAvailableCountryCodeList().contains(this.countryCode);
    }


    public void countryValChanged() {
        this.setCountryFieldValError("");
        if (this.getCountryCode() != null && !this.getCountryCode().equals("")) {
            if (checkValidVal()) {

                this.selectedCountryId = this.getCountryCode();
            }

            else {
                this.setCountryFieldValError(getBundle().getString(INVALID_COUNTRY_CODE));
                this.countryCode = "";
                this.selectedCountryId = "0";

            }

        } else {

            this.selectedCountryId = "0";
        }
        initCenterIntegration();
        fillQulCentersList();

    }

    public void setAvailableCountryCodeList(List<String> AvailableCountryCodeList) {
        this.AvailableCountryCodeList = AvailableCountryCodeList;
    }

    public List<String> getAvailableCountryCodeList() {
        return AvailableCountryCodeList;
    }

    public void setCountryInfo(IBasicDTO countryInfo) {
        this.countryInfo = countryInfo;
    }

    public IBasicDTO getCountryInfo() {
        return countryInfo;
    }

    //    public IBasicDTO getSelectedCountryData(String selectedValue) {
    ////        IBasicDTO countryData = null;
    ////        ICountriesClient countries = InfClientFactory.getCountriesClient();
    ////        ICountriesEntityKey key = InfEntityKeyFactory.createCountriesEntityKey(Long.valueOf(selectedValue));
    ////        try {
    ////            countryData = countries.getByIdInCenter(key);
    ////        } catch (SharedApplicationException e) {
    ////            e.printStackTrace();
    ////            getSharedUtil().setErrMsgValue(e.getMessage());
    ////        } catch (DataBaseException e) {
    ////            getSharedUtil().setErrMsgValue(e.getMessage());
    ////            e.printStackTrace();
    ////        } catch (Exception e) {
    ////            getSharedUtil().setErrMsgValue(e.getMessage());
    ////            e.printStackTrace();
    ////        }
    //        return countryData;
    //    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setCenterDTO(ITreeDTO centerDTO) {
        this.centerDTO = centerDTO;
    }

    public ITreeDTO getCenterDTO() {
        return centerDTO;
    }

    public void setLevelDTO(ITreeDTO levelDTO) {
        this.levelDTO = levelDTO;
    }

    public ITreeDTO getLevelDTO() {
        return levelDTO;
    }

    public StringBuilder buildTreeLevelAsString(ITreeDTO dto) {

        List<String> strList = new ArrayList<String>();
        strList.add(dto.getName());
        while (dto.getParentObject() != null && dto.getParentObject().getCode() != null) {
            strList.add(dto.getParentObject().getName());
            dto = (ITreeDTO)dto.getParentObject();
        }

        StringBuilder str = new StringBuilder();
        for (int x = strList.size() - 1; x >= 0; x--) {
            str.append(strList.get(x) + " / ");
        }
        str.delete(str.length() - 2, str.length());
        return str;
    }

    public void setShowEduLevelsTree(boolean showEduLevelsTree) {
        this.showEduLevelsTree = showEduLevelsTree;
    }

    public boolean isShowEduLevelsTree() {
        return showEduLevelsTree;
    }

    public void setQulCenterTreeDiv(Boolean qulCenterTreeDiv) {
        this.qulCenterTreeDiv = qulCenterTreeDiv;
    }

    public Boolean getQulCenterTreeDiv() {
        return qulCenterTreeDiv;
    }

    public void setCertificateLevelId(String certificateLevelId) {
        this.certificateLevelId = certificateLevelId;
    }

    public String getCertificateLevelId() {
        return certificateLevelId;
    }

    public void setLevelFieldValError(String levelFieldValError) {
        this.levelFieldValError = levelFieldValError;
    }

    public String getLevelFieldValError() {
        return levelFieldValError;
    }

    public void setCertificateLevel(String certificateLevel) {
        this.certificateLevel = certificateLevel;
    }

    public String getCertificateLevel() {
        return certificateLevel;
    }

    public void OpenTreeLevelDiv() {
        initEduLevelsIntegration();
    }

    public void setEnableResetData(Boolean enableResetData) {
        this.enableResetData = enableResetData;
    }

    public Boolean getEnableResetData() {
        return enableResetData;
    }

    public void reSetButtonErase() {
        setCertificateLevel("");
        setCertificateLevelId("");
        certificatesList = new ArrayList();
        setLevelDTO(null);
        centerQualificationsSearchCriteriaDTO.setLevelCode(null);
        setEnableResetData(false);
        System.out.println("reSetButtonErase");
        resetValues();
    }

    public void setFireComponentID(String fireComponentID) {
        this.fireComponentID = fireComponentID;
    }

    public String getFireComponentID() {
        return fireComponentID;
    }

    /**
     * Purpose: called to initalize inner objects in both certified and non-certified qul * Creation/Modification History : * 1.1 - Developer Name: Nora * 1.2 - Date: 30/7/2008 * 1.3 - Creation/Modification:Creation * 1.4- Description: called to initalize inner objects in both certified and non-certified qul */
    private void init() { // Certified Mode
        if (personQualificationsDTO.getQualificationsDTO() == null)
            personQualificationsDTO.setQualificationsDTO(QulDTOFactory.createQualificationsDTO());
        // Non Certified Mode
        if (personsInformationDTO.getCenterQualificationsDTO() == null)
            personsInformationDTO.setCenterQualificationsDTO(QulDTOFactory.createCenterQualificationsDTO());
        if (personsInformationDTO.getSocietiesDTO() == null)
            personsInformationDTO.setSocietiesDTO(MapDTOFactory.createSocietiesDTO());
        if (selectedQulType.equals(nonCertifiedQul)) {
            personsInformationDTO.setDegree(null);
            personsInformationDTO.setRegistrationDate(null);
            personsInformationDTO.setUntilDate(null);
            societyCode = null;
        }
        //        if (personsInformationDTO.getRegistrationDate() == null ||
        //            personsInformationDTO.getRegistrationDate().equals("")) {
        //            personsInformationDTO.setRegistrationDate(SharedUtils.getCurrentDate());
        //        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowbar(false);
        app.setShowLookupAddDivBorder(false);
        app.setShowTreediv(true);
        app.setShowCommonData(false);
        app.setShowContent1(true);
        //        app.setShowCustomDiv1(true);
        app.setShowLovDiv(true);
        app.setShowIntegrationDiv2(true);

        return app;
    }

    public void getCertificatesByCenterLevel() {

        if (getCenterDTO() != null)
            centerQualificationsSearchCriteriaDTO.setCenterCode(((ICentersEntityKey)getCenterDTO().getCode()).getCenterCode());
        if (getLevelDTO() != null) {
            centerQualificationsSearchCriteriaDTO.setLevelCode(getLevelDTO().getCode());

        }
        try {
            resetValues();
            setCertificatesList(((ICenterQualificationsClient)getClient()).searchQulCenterBySrchCriteriaDTO(centerQualificationsSearchCriteriaDTO));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setCertificatesList(null);
        } catch (DataBaseException e) {
            e.printStackTrace();
            setCertificatesList(null);
        }

    }

    public void fillQulByCertificate() {
        for (Object iBasicDTO : certificatesList) {
            ICenterQualificationsDTO qualification = (ICenterQualificationsDTO)iBasicDTO;
//            if (qualification.getCode().getKey().equals(selectedCertificateId)) {
            if (((ICenterQualificationsEntityKey)qualification.getCode()).getCntqualificationCode().equals(selectedCertificateId)) {     
                try {
                    getPersonsInformationDTO().getCenterQualificationsDTO().setCode(qualification.getCode());
                    getPersonsInformationDTO().getCenterQualificationsDTO().setCentersDTO((ICentersDTO)qualification.getCentersDTO());
                    personQualificationsDTO.getQualificationsDTO().setCode(QulEntityKeyFactory.createQualificationsEntityKey(qualification.getCscQualificationCode()));
                    personQualificationsDTO.setCentersDTO((ICentersDTO)qualification.getCentersDTO());

                    qualificationName = qualification.getLevelName();
                    if (qualificationName == null || qualificationName.equals("")) {
                        qualificationName = bundle.getString("noQulToCertificate");
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setCertifiedQul(String certifiedQul) {
        this.certifiedQul = certifiedQul;
    }

    public String getCertifiedQul() {
        return certifiedQul;
    }

    public void setNonCertifiedQul(String nonCertifiedQul) {
        this.nonCertifiedQul = nonCertifiedQul;
    }

    public String getNonCertifiedQul() {
        return nonCertifiedQul;
    }

    public void setSelectedQulType(String selectedQulType) {
        this.selectedQulType = selectedQulType;
    }

    public String getSelectedQulType() {
        return selectedQulType;
    }

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO) {
        this.personQualificationsDTO = personQualificationsDTO;
    }

    public IPersonQualificationsDTO getPersonQualificationsDTO() {
        return personQualificationsDTO;
    }

    public void setPersonsInformationDTO(IPersonsInformationDTO personsInformationDTO) {
        this.personsInformationDTO = personsInformationDTO;
    }

    public IPersonsInformationDTO getPersonsInformationDTO() {
        return personsInformationDTO;
    }

    public void setSearchQulifictaionString(String searchQulifictaionString) {
        this.searchQulifictaionString = searchQulifictaionString;
    }

    public String getSearchQulifictaionString() {
        return searchQulifictaionString;
    }

    public void setQualificationList(List qualificationList) {
        this.qualificationList = qualificationList;
    }

    public void setQualificationDataTable(HtmlDataTable qualificationDataTable) {
        this.qualificationDataTable = qualificationDataTable;
    }

    public HtmlDataTable getQualificationDataTable() {
        return qualificationDataTable;
    }

    public void setQualificationListSize(int qualificationListSize) {
        this.qualificationListSize = qualificationListSize;
    }

    public IBasicDTO getKwtCitizenInfo(Long civilId) {
        IBasicDTO basicDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        try {
            basicDTO = InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(civilId);
            setPageDTO(basicDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return basicDTO;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setCountriesList(List countriesList) {
        this.countriesList = countriesList;
    }

    public List getCountriesList() {
        //        if (countriesList == null || countriesList.size() == 0)
        //            try {
        //                countriesList = InfClientFactory.getCountriesClient().getCodeName();
        //            } catch (DataBaseException e) {
        //                countriesList = new ArrayList();
        //                e.printStackTrace();
        //            } catch (Exception e) {
        //                countriesList = new ArrayList();
        //                e.printStackTrace();
        //            }
        return countriesList;
    }

    public void setCentersList(List centersList) {
        this.centersList = centersList;
    }

    public List getCentersList() {
        return centersList;
    }

    public void filterByCenter() {
        if (centerCode != null) {
            try {
                qualificationList = QulClientFactory.getCenterQualificationsClient().getAllByQulCenter(centerCode);
                if (qualificationDataTable != null)
                    qualificationDataTable.setFirst(0);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                qualificationList = new ArrayList();
            } catch (DataBaseException e) {
                e.printStackTrace();
                qualificationList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                qualificationList = new ArrayList();
            }
        }
    }

    public void setCenterCode(Long centerCode) {
        this.centerCode = centerCode;
    }

    public Long getCenterCode() {
        return centerCode;
    }

    public void setSocietyCode(Long societyCode) {
        this.societyCode = societyCode;
    }

    public Long getSocietyCode() {
        return societyCode;
    }

    public String saveQualification() {
        SharedUtilBean shared_util = getSharedUtil();
        qulIntegrationListBean.reloadKwtCitizenInfo();
        try {
            add();
            if (isInValidValue()) {
                setSuccess(false);
            } else {
                if (isSuccess() && qulIntegrationListBean.isSaveInDB()) {
                    shared_util.setSuccessMsgValue("SuccessAdds");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("PrimaryKeyDuplicated")) {
                this.setErrorMsg("FailureInAdd");
                shared_util.setErrMsgValue("FailureInAdd");
                setSuccess(false);
                return null;
            } else
                shared_util.setErrMsgValue("SystemFailureException");
        }

        return "null";
    }

    //    public void saveAndNew() throws DataBaseException {
    //        SharedUtilBean shared_util = getSharedUtil();
    //        reIntializeAddDivMsgs();
    //        try {
    //            add();
    //            if (isInValidValue()) {
    //                setSuccess(false);
    //            } else {
    //
    //                if (isSuccess()) {
    //                    resetPageData();
    //                    shared_util.setSuccessMsgValue("SuccessAdds");
    //                }
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            if (e.getMessage().equals("PrimaryKeyDuplicated")) {
    //                this.setErrorMsg("FailureInAdd");
    //                shared_util.setErrMsgValue("FailureInAdd");
    //                setSuccess(false);
    //            } else
    //                shared_util.setErrMsgValue("SystemFailureException");
    //        }
    //    }

    public void resetPageData() {
        //        reInitializePageDTO();
        init();
        qualificationName = null;
        societyCode = null;
        certificateLevel = "";
        enableResetData = false;
        certificatesList = new ArrayList();
        resetValues();
        personQualificationsDTO.setQualificationDegree(null);
        //        personsInformationDTO.setRegistrationDate(null);
        setSelectedCertificateId(null);//getVirtualConstValue());
        setQualificationName("");
        setSelectedCenterName("");
        setQulDegreeType(getVirtualConstValue());
        setSelectedQulDegreeValue(getVirtualConstValue());

        setQulCountryCode(getManagedConstantsBean().getVirtualStringValueConstant());
        setQulCenterCode(getManagedConstantsBean().getVirtualStringValueConstant());
    }

    public void add() {
        setInValidValue(false);
        SharedUtilBean shared_util = getSharedUtil();
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        backPage = (String)integrationBean.getHmBaseBeanFrom().get("backPage");
        try {

            if (!qulIntegrationListBean.getQulListPageinWizardBar().equals("")) {
                QulIntegrationListBean qulListBean =
                    (QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean");
                try {
                    kwtCitizensResidentsDTO =
                            (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidentQuls(qulListBean.getCivilId());
                } catch (Exception e) {
                    if ((kwtCitizensResidentsDTO == null || kwtCitizensResidentsDTO.getCode() == null) &&
                        qulListBean.getKwtCitizenDTO() != null) {
                        kwtCitizensResidentsDTO =
                                (IKwtCitizensResidentsDTO)getSharedUtil().deepCopyObject(qulListBean.getKwtCitizenDTO());
                    }
                }


            } else {
                QulIntegrationListBean qulListBean =
                    (QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean");
                if (qulListBean != null) {
                    kwtCitizensResidentsDTO =
                            (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidentQuls(qulListBean.getCivilId());
                } else {
                    kwtCitizensResidentsDTO =
                            (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(civilId);
                }
            }

            //            else {
            //                kwtCitizensResidentsDTO =
            //                        (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(civilId);
            //            }
            //            kwtCitizensResidentsDTO =
            //                    (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(civilId);
            if (kwtCitizensResidentsDTO != null && kwtCitizensResidentsDTO.getCode() != null) {
                preparePersonQualificationsDTO();
                preparePersonInformationDTO();
                if (!isInValidValue()) {
                    if (qulIntegrationListBean.isSaveInDB()) {
                        personsInformationDTO =
                                (IPersonsInformationDTO)InfClientFactory.getPersonsInformationClient().add(personsInformationDTO);
                    } else {
                        prparepersonsInformationDTO();
                        kwtCitizensResidentsDTO.setPersonsInformationDTOList(((QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getNotSavedPersonsInformationDTOList());
                        if (!checkDuplicateCode(personsInformationDTO)) {
                            if (kwtCitizensResidentsDTO.getPersonsInformationDTOList() == null) {
                                kwtCitizensResidentsDTO.setPersonsInformationDTOList(new ArrayList());
                            }
                            kwtCitizensResidentsDTO.getPersonsInformationDTOList().add(personsInformationDTO);
                            setSuccess(true);
                        } else {
                            setSuccess(false);
                        }
                        ((QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).setKwtCitizenDTO(kwtCitizensResidentsDTO);


                    }
                }
            }

            setQualificationList(null);
            if (qulIntegrationListBean.isSaveInDB()) {
                setSuccess(true);
            }

        } catch (DataBaseException e) {
            if (e.getMessage().equals("PrimaryKeyDuplicated")) {
                //                this.setErrorMsg("FailureInAdd");
                //                shared_util.setErrMsgValue("FailureInAdd");
                setErrorMsg("PrimaryKeyDuplicatedQulException");
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,
                                                                              "PrimaryKeyDuplicatedQulException"));
            } else {
                shared_util.setErrMsgValue("SystemFailureException");
                this.setErrorMsg("SystemFailureException");
            }
            this.setShowErrorMsg(true);
            setSuccess(false);
            e.printStackTrace();
            return;
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            e.printStackTrace();
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            e.printStackTrace();
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");
        }
    }

    private boolean checkDuplicateCode(IPersonsInformationDTO unsavedDto) {
        boolean duplicated = false;
        Long unsavedCivilId = ((IPersonsInformationEntityKey)unsavedDto.getCode()).getCivilId();
        java.sql.Date unsavedRegistrationDate =
            ((IPersonsInformationEntityKey)unsavedDto.getCode()).getRegistrationDate();
        Long unsavedSocCode = ((IPersonsInformationEntityKey)unsavedDto.getCode()).getSocCode();
        String unsavedCenterQulCode = unsavedDto.getCenterQualificationsDTO().getCode().getKey();
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        List<IPersonsInformationDTO> list =
            ((QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getNotSavedPersonsInformationDTOList();
        if (list != null) {
            for (IPersonsInformationDTO dto : list) {
                if (unsavedCivilId.equals(((IPersonsInformationEntityKey)dto.getCode()).getCivilId()) &&
                    unsavedRegistrationDate.equals(((IPersonsInformationEntityKey)dto.getCode()).getRegistrationDate()) &&
                    unsavedSocCode.equals(((IPersonsInformationEntityKey)dto.getCode()).getSocCode())) {
                    duplicated = true;
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,
                                                                                  "PrimaryKeyDuplicatedQulException"));
                    break;
                } else if (unsavedCivilId.equals(((IPersonsInformationEntityKey)dto.getCode()).getCivilId()) &&
                           unsavedCenterQulCode.equals(dto.getCenterQualificationsDTO().getCode().getKey()) &&
                           unsavedSocCode.equals(((IPersonsInformationEntityKey)dto.getCode()).getSocCode())) {
                    duplicated = true;
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "DuplicatedQulException"));
                    break;
                }
            }
        }

        return duplicated;
    }

    private Double calculateDegree(IPersonsInformationDTO personsInformationDTO) throws SharedApplicationException,
                                                                                        DataBaseException {
        Double finalDegree = 0D;
        Long percentageValue = 0L;
        Long gradeTypesCode = Long.valueOf(personsInformationDTO.getGradeTypeDto().getCode().getKey());
        Double qulDegree = personsInformationDTO.getDegree();
        // DecimalFormat df = new DecimalFormat("#.###");
        if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_PERCENTAGE)) {
            if (qulDegree < 0D || qulDegree > 100D) {
                throw new InvalidDataEntryException();
            }
            finalDegree = qulDegree;
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LATIN) ||
                   gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LITERAL)) {
            if (personsInformationDTO.getGradeTypeDto().getCode() != null) {
                IInfGradeValuesDTO gradeValuesDTO = null;
                try {
                    gradeValuesDTO =
                            (IInfGradeValuesDTO)InfClientFactory.getInfGradeValuesClient().getById(InfEntityKeyFactory.createInfGradeValuesEntityKey(gradeTypesCode,
                                                                                                                                                     personsInformationDTO.getGradeValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                percentageValue = gradeValuesDTO.getPercentageValue();
            }
            finalDegree = percentageValue.doubleValue();
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FIVE)) {
            if (qulDegree < 0D || qulDegree > 5D) {
                throw new InvalidDataEntryException();
            }
            finalDegree =
                    InfClientFactory.getGradeTypesClient().getFormulaByGradeType(gradeTypesCode, personsInformationDTO.getGradeValue());
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FOUR)) {
            if (qulDegree < 0D || qulDegree > 4D) {
                throw new InvalidDataEntryException();
            }
            finalDegree =
                    InfClientFactory.getGradeTypesClient().getFormulaByGradeType(gradeTypesCode, personsInformationDTO.getGradeValue());
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_NINE)) {
            if (qulDegree < 0D || qulDegree > 9D) {
                throw new InvalidDataEntryException();
            }
            finalDegree =
                    InfClientFactory.getGradeTypesClient().getFormulaByGradeType(gradeTypesCode, personsInformationDTO.getGradeValue());
        }
        return finalDegree;
    }

    /**
     * prepare DTO if not saved in DB for view in table
     */
    private void prparepersonsInformationDTO() {
        if (personQualificationsDTO.getQualificationsDTO().getCode().getKey() != null &&
            !personQualificationsDTO.getQualificationsDTO().getCode().getKey().equals("")) {
            personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(Long.valueOf(personsInformationDTO.getKwtCitizensResidentsDTO().getCode().getKey()),
                                                                                                    personQualificationsDTO.getQualificationsDTO().getCode().getKey()));

            IQualificationsDTO qualificationsDTO = null;
            try {
                qualificationsDTO =
                        (IQualificationsDTO)QulClientFactory.getQualificationsClient().getById(personQualificationsDTO.getQualificationsDTO().getCode());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            personQualificationsDTO.setQualificationsDTO(qualificationsDTO);
            if (personQualificationsDTO.getCrsRegistrationOrder() == null) {
                if (personQualificationsDTO.getKwtCitizensResidentsDTO().getPersonQualificationsDTOList() != null &&
                    personQualificationsDTO.getKwtCitizensResidentsDTO().getPersonQualificationsDTOList().size() > 0) {
                    personQualificationsDTO.setCrsRegistrationOrder(0L);
                } else {
                    personQualificationsDTO.setCrsRegistrationOrder(1L);

                }
            }
            personsInformationDTO.setPersonQualificationsDTO(personQualificationsDTO);
        } else {
            personsInformationDTO.setPersonQualificationsDTO(null);
        }
        String socCode = "";
        //personsInformationEntity.setSocCode(Long.valueOf(socCode));
        ICenterQualificationsDTO centerQualificationsDTO = null;
        try {
            socCode =
                    InfClientFactory.getPersonsInformationClient().getSocietyCodeByCenter(personsInformationDTO.getCenterQualificationsDTO().getCentersDTO().getCode().getKey());
            centerQualificationsDTO =
                    (ICenterQualificationsDTO)QulClientFactory.getCenterQualificationsClient().getById(personsInformationDTO.getCenterQualificationsDTO().getCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if (socCode != null && !socCode.equals("")) {
            String newSocCode = socCode.replace("[", "").replace("]", "").trim();
            personsInformationDTO.getSocietiesDTO().setCode(MapEntityKeyFactory.createSocietiesEntityKey(Long.valueOf(newSocCode)));

        }

        personsInformationDTO.setCode(InfEntityKeyFactory.createPersonsInformationEntityKey(Long.valueOf(personsInformationDTO.getKwtCitizensResidentsDTO().getCode().getKey()),
                                                                                            personsInformationDTO.getRegistrationDate(),
                                                                                            Long.valueOf(personsInformationDTO.getSocietiesDTO().getCode().getKey())));
        personsInformationDTO.setCenterQualificationsDTO(centerQualificationsDTO);
        personsInformationDTO.setStatusFlag(added);
        try {
            personsInformationDTO.setDegree(calculateDegree(personsInformationDTO));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void update() throws DataBaseException {
        setInValidValue(false);
        SharedUtilBean shared_util = getSharedUtil();

        try {
            if (kwtCitizensResidentsDTO != null && kwtCitizensResidentsDTO.getCode() != null) {

                preparePersonInformationDTO();

                if (!isInValidValue()) {
                    InfClientFactory.getPersonsInformationClient().update(personsInformationDTO);
                }
            }

            setQualificationList(null);
            this.setSuccess(true);

        } catch (DataBaseException e) {
            if (selectedQulType.equals(nonCertifiedQul)) {
                getSharedUtil().handleException(e);
            }

            this.setShowErrorMsg(true);
            setSuccess(false);
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            e.printStackTrace();
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            e.printStackTrace();
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");
        }
    }

    public void reInitializePageDTO() {
        personQualificationsDTO = InfDTOFactory.createPersonQualificationsDTO();
        personsInformationDTO = InfDTOFactory.createPersonsInformationDTO();
        qualificationName = null;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }


    public void updatePersonInformationQul() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            InfClientFactory.getPersonsInformationClient().update(personsInformationDTO);
            shared_util.setSuccessMsgValue("SuccesUpdated");
        } catch (DataBaseException e) {
            shared_util.setErrMsgValue("FailureInUpdate");
        } catch (ItemNotFoundException item) {
            shared_util.setErrMsgValue("itemALreadyDeleted");
        } catch (SharedApplicationException e) {
            shared_util.setErrMsgValue(e.getMessage());
        }
    }

    public void qulDegreeTypeChanged() {
        fillDegreeValuesList();
        ManagedConstantsBean managedConstantsBean = getManagedConstantsBean();
        if (qulDegreeType != null && !qulDegreeType.equals("")) {
            if (qulDegreeType.equals(managedConstantsBean.getCrsGradeTypePercentageConstant())) {
                setShangLangth(3);
            } else {
                setShangLangth(1);
            }
        }

        setInValidValue(false);
        getPersonQualificationsDTO().setQualificationDegree(null);
    }


    public void setQulDegreeType(String qulDegreeType) {
        this.qulDegreeType = qulDegreeType;
    }

    public String getQulDegreeType() {
        return qulDegreeType;
    }

    public void setInValidValue(boolean inValidValue) {
        this.inValidValue = inValidValue;
    }

    public boolean isInValidValue() {
        return inValidValue;
    }

    public void setDegreeValuesList(List degreeValuesList) {
        this.degreeValuesList = degreeValuesList;
    }


    public void fillDegreeValuesList() {
        if (qulDegreeType != null && !qulDegreeType.equals("-100")) {
            try {
                Long gradeTypeCode = Long.valueOf(qulDegreeType);
                degreeValuesList = InfClientFactory.getInfGradeValuesClient().getAllByTypeCode(gradeTypeCode);
                //CrsClientFactory.getGradeValuesClient().getCodeName(Long.parseLong(qulDegreeType));
            } catch (Exception e) {
                e.printStackTrace();
                degreeValuesList = new ArrayList();

            }
        }
    }

    public List getDegreeValuesList() {
        if (qulDegreeType != null && !qulDegreeType.equals("-100")) {
            try {
                Long gradeTypeCode = Long.valueOf(qulDegreeType);
                degreeValuesList = InfClientFactory.getInfGradeValuesClient().getAllByTypeCode(gradeTypeCode);
                //CrsClientFactory.getGradeValuesClient().getCodeName(Long.parseLong(qulDegreeType));
            } catch (Exception e) {
                e.printStackTrace();
                degreeValuesList = new ArrayList();
            }

        }
        return degreeValuesList;
    }

    public void setSelectedQulDegreeValue(String selectedQulDegreeValue) {
        this.selectedQulDegreeValue = selectedQulDegreeValue;
    }

    public String getSelectedQulDegreeValue() {
        return selectedQulDegreeValue;
    }

    public void setSearchQulifictaionCode(String searchQulifictaionCode) {
        this.searchQulifictaionCode = searchQulifictaionCode;
    }

    public String getSearchQulifictaionCode() {
        return searchQulifictaionCode;
    }

    public void setQulCountryCode(String qulCountryCode) {
        this.qulCountryCode = qulCountryCode;
    }

    public String getQulCountryCode() {
        return qulCountryCode;
    }

    public void setQulCountriesList(List qulCountriesList) {
        this.qulCountriesList = qulCountriesList;
    }

    public List getQulCountriesList() {
        //        if (qulCountriesList == null || qulCountriesList.size() == 0) {
        //            try {
        //                setQulCountriesList(InfClientFactory.getCountriesClient().getCodeName());
        //            } catch (DataBaseException e) {
        //                e.printStackTrace();
        //                qulCountriesList = new ArrayList();
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //                qulCountriesList = new ArrayList();
        //            }
        //        }

        return qulCountriesList;
    }

    public void setQulCenterCode(String qulCenterCode) {
        this.qulCenterCode = qulCenterCode;
    }

    public String getQulCenterCode() {
        return qulCenterCode;
    }

    public void setQulCentersList(List qulCentersList) {
        this.qulCentersList = qulCentersList;
    }

    public List getQulCentersList() {
        //        if ((qulCountryCode != null &&
        //             !qulCountryCode.equals(getManagedConstantsBean().getVirtualStringValueConstant()))) {
        //            try {
        //                setQulCentersList(QulClientFactory.getCentersClient().getAllByCountry(InfEntityKeyFactory.createCountriesEntityKey(Long.parseLong(qulCountryCode))));
        //            } catch (SharedApplicationException e) {
        //                qulCentersList = new ArrayList();
        //                e.printStackTrace();
        //            } catch (DataBaseException e) {
        //                qulCentersList = new ArrayList();
        //                e.printStackTrace();
        //            }
        //        }
        return qulCentersList;
    }

    public void countryChanged() {
        qulCenterCode = getManagedConstantsBean().getVirtualStringValueConstant();
        if (qulCountryCode != null &&
            !qulCountryCode.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
            try {

                qulCentersList =
                        QulClientFactory.getCentersClient().getAllByCountry(InfEntityKeyFactory.createCountriesEntityKey(Long.parseLong(qulCountryCode)));
            } catch (DataBaseException e) {
                e.printStackTrace();
                qulCentersList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                qulCentersList = new ArrayList();
            }
        }
    }

    public void setShangLangth(int shangLangth) {
        this.shangLangth = shangLangth;
    }

    public int getShangLangth() {
        return shangLangth;
    }

    //    public void loadGeneralSpecs() {
    //
    //        generalSpecslist = null;
    //        majorSpecsList = null;
    //
    //        generalSpecsKey = null;
    //        majorSpecsKey = null;
    //
    //        if ((educationKey != null) && !educationKey.equals(getVirtualConstValue())) {
    //
    //            try {
    //                generalSpecslist =
    //                        (List)QulClientFactory.getEduLevelGenSepcsClient().getActiveByEduLevel(Long.parseLong(educationKey));
    //            } catch (Throwable e) {
    //                generalSpecslist = new ArrayList<IEduLevelGenSepcsDTO>();
    //                e.printStackTrace();
    //            }
    //        }
    //    }

    //    public void loadMajorSpecs() {
    //        majorSpecsList = null;
    //        majorSpecsKey = null;
    //        if (generalSpecsKey != null && !generalSpecsKey.equals(getVirtualConstValue())) {
    //            IEduLevelGenSepcsDTO selectedGenSpec = null;
    //            for (IEduLevelGenSepcsDTO genSpec : generalSpecslist) {
    //                if (genSpec.getCode().getKey().equals(generalSpecsKey)) {
    //                    selectedGenSpec = genSpec;
    //                    break;
    //                }
    //            }
    //            try {
    //                majorSpecsList =
    //                        (List)QulClientFactory.getGenSpecMajSpecsClient().getActiveByGeneralSpec(Long.parseLong(selectedGenSpec.getGeneralSpecsDTO().getCode().getKey()));
    //            } catch (Throwable e) {
    //                majorSpecsList = new ArrayList<IGenSpecMajSpecsDTO>();
    //                e.printStackTrace();
    //            }
    //        }
    //    }

    public void setGeneralSpecslist(List<IEduLevelGenSepcsDTO> generalSpecslist) {
        this.generalSpecslist = generalSpecslist;
    }

    public List<IEduLevelGenSepcsDTO> getGeneralSpecslist() {
        return generalSpecslist;
    }

    public void setMajorSpecsList(List<IGenSpecMajSpecsDTO> majorSpecsList) {
        this.majorSpecsList = majorSpecsList;
    }

    public List<IGenSpecMajSpecsDTO> getMajorSpecsList() {
        return majorSpecsList;
    }

    public void setEducationKey(String educationKey) {
        this.educationKey = educationKey;
    }

    public String getEducationKey() {
        return educationKey;
    }

    public void setGeneralSpecsKey(String generalSpecsKey) {
        this.generalSpecsKey = generalSpecsKey;
    }

    public String getGeneralSpecsKey() {
        return generalSpecsKey;
    }

    public void setMajorSpecsKey(String majorSpecsKey) {
        this.majorSpecsKey = majorSpecsKey;
    }

    public String getMajorSpecsKey() {
        return majorSpecsKey;
    }

    public void setEducationLevelsList(List<IBasicDTO> educationLevelsList) {
        this.educationLevelsList = educationLevelsList;
    }

    public List<IBasicDTO> getEducationLevelsList() {
        //        if (educationLevelsList == null) {
        //            try {
        //                educationLevelsList = QulClientFactory.getEducationLevelsClient().getAllLeafs();
        //            } catch (Exception e) {
        //                educationLevelsList = new ArrayList<IBasicDTO>();
        //                e.printStackTrace();
        //            }
        //        }
        return educationLevelsList;
    }

    public void prepareForEdit() {

        //selectedQulType = certifiedQul;

        if (personsInformationDTO != null) {
            if (personsInformationDTO.getGradeTypeDto() != null) {
                qulDegreeType = personsInformationDTO.getGradeTypeDto().getCode().getKey();
            }

            if (personsInformationDTO.getPersonQualificationsDTO().getQualificationsDTO() != null) {
                qualificationName =
                        personsInformationDTO.getPersonQualificationsDTO().getQualificationsDTO().getName();
            }

            if (personsInformationDTO.getPersonQualificationsDTO().getCentersDTO() != null) {
                if (personsInformationDTO.getPersonQualificationsDTO().getCentersDTO().getCountries() != null) {
                    CountriesDTO coutriesDTO =
                        (CountriesDTO)personsInformationDTO.getPersonQualificationsDTO().getCentersDTO().getCountries();
                    qulCountryCode = coutriesDTO.getCode().getKey();
                    countryChanged();
                    qulCenterCode =
                            personsInformationDTO.getPersonQualificationsDTO().getCentersDTO().getCode().getKey();
                }
            }

            setQulDegreeType(getManagedConstantsBean().getCrsGradeTypePercentageConstant());
            setShangLangth(3);
        }

    }

    public void preparePersonQualificationsDTO() {
        IInfGradeTypesDTO gradeTypeDto = InfDTOFactory.createGradeTypesDTO();
        personQualificationsDTO.setKwtCitizensResidentsDTO(kwtCitizensResidentsDTO);
        personQualificationsDTO.setQualificationDate(getPersonsInformationDTO().getUntilDate());
        if (qulDegreeType != null && !qulDegreeType.equals("")) {
            gradeTypeDto.setCode(InfEntityKeyFactory.createGradeTypesEntityKey(Long.parseLong(qulDegreeType)));
            personQualificationsDTO.setGradeTypeDto(gradeTypeDto);

            if (personQualificationsDTO.getQualificationDegree() != null &&
                !personQualificationsDTO.getQualificationDegree().equals("")) {

                if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePercentageConstant())) {
                    if (personQualificationsDTO.getQualificationDegree() > 100D ||
                        personQualificationsDTO.getQualificationDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personQualificationsDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree().longValue()));

                    }
                } else if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePointFourConstant())) {
                    if (personQualificationsDTO.getQualificationDegree() > 4D ||
                        personQualificationsDTO.getQualificationDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personQualificationsDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree().longValue()));

                    }
                } else if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePointFiveConstant())) {
                    if (personQualificationsDTO.getQualificationDegree() > 5D ||
                        personQualificationsDTO.getQualificationDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personQualificationsDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree().longValue()));

                    }
                } else if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePointNineConstant())) {
                    if (personQualificationsDTO.getQualificationDegree() > 9D ||
                        personQualificationsDTO.getQualificationDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personQualificationsDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree().longValue()));

                    }
                }
            } else if (selectedQulDegreeValue != null && !selectedQulDegreeValue.equals("") &&
                       !selectedQulDegreeValue.equals(getVirtualConstValue())) {
                //personQualificationsDTO.setGradeValueCode(Long.parseLong(selectedQulDegreeValue));
                personQualificationsDTO.setGradeValue(selectedQulDegreeValue);
            }

            if (qulCenterCode != null && !qulCenterCode.equals("")) {
                ICentersDTO centerDTO = QulDTOFactory.createCentersDTO();
                centerDTO.setCode(QulEntityKeyFactory.createCentersEntityKey(Long.valueOf(qulCenterCode)));
                personQualificationsDTO.setCentersDTO(centerDTO);
            }
        }
    }

    public void preparePersonInformationDTO() {

        inValidValue = false;
        IInfGradeTypesDTO gradeTypeDto = InfDTOFactory.createGradeTypesDTO();
        personsInformationDTO.setKwtCitizensResidentsDTO(kwtCitizensResidentsDTO);
        personsInformationDTO.setPersonQualificationsDTO(personQualificationsDTO);
        if (qulDegreeType != null && !qulDegreeType.equals("")) {

            gradeTypeDto.setCode(InfEntityKeyFactory.createGradeTypesEntityKey(Long.valueOf(qulDegreeType)));
            personsInformationDTO.setGradeTypeDto(gradeTypeDto);
            if (getPersonQualificationsDTO() != null &&
                getPersonQualificationsDTO().getQualificationDegree() != null) {
                personsInformationDTO.setDegree(getPersonQualificationsDTO().getQualificationDegree());
                if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePercentageConstant())) {
                    if (personsInformationDTO.getDegree() > 100D ||
                        personsInformationDTO.getDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personsInformationDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree()));
                        personsInformationDTO.setDegree(getPersonQualificationsDTO().getQualificationDegree());
                    }
                }
                if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePointFourConstant())) {
                    if (personsInformationDTO.getDegree() > 4D ||
                        personsInformationDTO.getDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);

                    } else {
                        personsInformationDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree()));

                    }
                }
                if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePointFiveConstant())) {
                    if (personsInformationDTO.getDegree() > 5D ||
                        personsInformationDTO.getDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personsInformationDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree().longValue()));

                    }
                }
                if (qulDegreeType.equals(getManagedConstantsBean().getCrsGradeTypePointNineConstant())) {
                    if (personsInformationDTO.getDegree() > 9D ||
                        personsInformationDTO.getDegree() < 0D) {
                        setInValidValue(true);
                        personsInformationDTO.setRegistrationDate(null);
                    } else {
                        personsInformationDTO.setGradeValue(String.valueOf(getPersonQualificationsDTO().getQualificationDegree().longValue()));

                    }
                }
            }
            if (selectedQulDegreeValue != null && !selectedQulDegreeValue.equals("")) {
                personsInformationDTO.setGradeValue(selectedQulDegreeValue);
            }
            if (societyCode != null) {
                personsInformationDTO.getSocietiesDTO().setCode(MapEntityKeyFactory.createSocietiesEntityKey(societyCode));
            }
        }
    }

    public String Save() {
        String ret = "";
        if(personsInformationDTO.getRegistrationDate() == null){
            Date untilDate = personsInformationDTO.getUntilDate();
            Calendar calendar = Calendar.getInstance(); // this would default to now
            calendar.setTime(untilDate);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date regDate = new java.sql.Date(calendar.getTime().getTime());//SharedUtils.getCurrentDate()
            personsInformationDTO.setRegistrationDate(regDate);    
        }
        
        if (getPrepareMethodName() != null && !getPrepareMethodName().equals("")) {
            ret = (String)executeMethodBinding(getPrepareMethodName(), null);
        }
        saveQualification();

        if (getReturnMethodName() != null && !getReturnMethodName().equals("")) {
            ret = (String)executeMethodBinding(getReturnMethodName(), null);
        }
        if (isInValidValue()) {
            return null;
        } else {
            return backFromAdd();
        }
    }

    //    public String backFromAdd() {
    //        IntegrationBean integrationBean =
    //            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
    //
    //        if (!qulIntegrationListBean.getQulListPageinWizardBar().equals("")) {
    //            backPage = "";
    //            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().clear();
    //            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().add(personsInformationDTO);
    //            integrationBean.getHmBaseBeanFrom().put("personsInformationDTO", personsInformationDTO);
    //            return qulIntegrationListBean.getQulListPageinWizardBar();
    //
    //        } else if (backPage.equals("qulListPage")) {
    //            backPage = "";
    //            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().clear();
    //            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().add(personsInformationDTO);
    //            integrationBean.getHmBaseBeanFrom().put("personsInformationDTO", personsInformationDTO);
    //            return "viewIntegrationPeronalQuls";
    //        } else {
    //            ((BaseBean)integrationBean.getHmBaseBeanFrom().get(integrationBean.getBeanNameFrom())).getHighlightedDTOS().clear();
    //            ((BaseBean)integrationBean.getHmBaseBeanFrom().get(integrationBean.getBeanNameFrom())).getHighlightedDTOS().add(((BaseBean)integrationBean.getHmBaseBeanFrom().get(integrationBean.getBeanNameFrom())).getSelectedDTOS().get(0));
    //            return integrationBean.goFrom();
    //        }
    //    }

    public String backFromAdd() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        if (!qulIntegrationListBean.getQulListPageinWizardBar().equals("")) {
            //            backPage = "";
            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().clear();
            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().add(personsInformationDTO);
            integrationBean.getHmBaseBeanFrom().put("personsInformationDTO", personsInformationDTO);
            return integrationBean.goFrom();

        } else if (backPage.equals("qulListPage")) {
            backPage = "";
            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().clear();
            ((BaseBean)integrationBean.getHmBaseBeanFrom().get("qulListIntegrationBean")).getHighlightedDTOS().add(personsInformationDTO);
            integrationBean.getHmBaseBeanFrom().put("personsInformationDTO", personsInformationDTO);
            return "viewIntegrationPeronalQuls";
        } else {
            return integrationBean.goFrom();
        }
    }


    public String back() {

        if (backPage != null && backPage.equals("qulListPage")) {
            backPage = "";
            return "viewIntegrationPeronalQuls";
        } else {
            IntegrationBean integrationBean =
                (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
            return integrationBean.goFrom();
        }
    }

    /**
     * @param qulAddDivTitleName
     */
    public void setQulAddDivTitleName(String qulAddDivTitleName) {
        this.qulAddDivTitleName = qulAddDivTitleName;
    }

    public String getQulAddDivTitleName() {
        if (selectedQulType.equals(nonCertifiedQul)) {
            return "qul_not_certified_add";
        }
        return "qul_certified_add";
    }

    public void setSelectedCertificateId(String selectedCertificateId) {
        this.selectedCertificateId = selectedCertificateId;
    }

    public String getSelectedCertificateId() {
        return selectedCertificateId;
    }


    public void setReturnMethodName(String returnMethodName) {
        this.returnMethodName = returnMethodName;
    }

    public String getReturnMethodName() {
        return returnMethodName;
    }

    public void setPrepareMethodName(String prepareMethodName) {
        this.prepareMethodName = prepareMethodName;
    }

    public String getPrepareMethodName() {
        return prepareMethodName;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setPersonsInformationDTOList(ArrayList<IPersonsInformationDTO> personsInformationDTOList) {
        this.personsInformationDTOList = personsInformationDTOList;
    }

    public ArrayList<IPersonsInformationDTO> getPersonsInformationDTOList() {
        return personsInformationDTOList;
    }

    public void setMinistryName(String ministryName) {
        this.ministryName = ministryName;
    }

    public String getMinistryName() {
        return ministryName;
    }

    public void setDisableQulDateRegistration(Boolean disableQulDateRegistration) {
        this.disableQulDateRegistration = disableQulDateRegistration;
    }

    public Boolean getDisableQulDateRegistration() {
        return disableQulDateRegistration;
    }


    //Integration

    public void setQulCentersIntegrationBean(QulCentersIntegrationBean qulCentersIntegrationBean) {
        this.qulCentersIntegrationBean = qulCentersIntegrationBean;
    }

    public QulCentersIntegrationBean getQulCentersIntegrationBean() {
        //        if (qulCentersIntegrationBean == null) {
        //            initCenterIntegration();
        //        }
        return qulCentersIntegrationBean;
    }

    public void initCenterIntegration() {
        qulCentersIntegrationBean.setSelectedCountryId(selectedCountryId);
        qulCentersIntegrationBean.setReturnMethodName("#{" + BEAN_NAME + ".addQulCenter}");
        qulCentersIntegrationBean.setReRenderFields("eduLevelTeeDiv,scriptPanel,open_Level_Div,missionTypeCode,missionTypeNameID,dataT_data_panel,OperationBar,paging_panel,validationGroup,search_Button");
    }

    public void addQulCenterByCode() {
        inValidMissionTypeCode = true;
        centerDTO = null;
        setSelectedCenterName(null);
        resetValues();
        certificatesList = new ArrayList();
        certificateLevel = "";

        if (qulCentersList != null && qulCenterCode != null) {
            
            for (int i = 0; i < qulCentersList.size() ; i++) {
                if(qulCenterCode.equals(((IBasicDTO)qulCentersList.get(i)).getCode().getKey() ) ){
                    inValidMissionTypeCode = false;
                    setCenterDTO((ITreeDTO)((IBasicDTO)qulCentersList.get(i)));
                    break;
                }
            }

            if(centerDTO != null) {
                StringBuilder str = buildTreeLevelAsString(centerDTO);
                try {
                    setSelectedCenterName(str.toString());
//                    setCenterDTO(qulCentersIntegrationBean.getSelectedCenterDTO());
//                    setQulCenterCode(getCenterDTO().getCode().getKey());
                    initEduLevelsIntegration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void addQulCenter() {
        inValidMissionTypeCode = false;
        resetValues();
        certificatesList = new ArrayList();
        certificateLevel = "";

        if (qulCentersIntegrationBean.getSelectedCenterDTO() != null) {
            StringBuilder str = buildTreeLevelAsString(qulCentersIntegrationBean.getSelectedCenterDTO());
            try {
                setSelectedCenterName(str.toString());
                setCenterDTO(qulCentersIntegrationBean.getSelectedCenterDTO());
                setQulCenterCode(getCenterDTO().getCode().getKey());
                initEduLevelsIntegration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void setQulEduLevelsIntegrationBean(QulEduLevelsIntegrationBean qulEduLevelsIntegrationBean) {
        this.qulEduLevelsIntegrationBean = qulEduLevelsIntegrationBean;
    }

    public QulEduLevelsIntegrationBean getQulEduLevelsIntegrationBean() {
        if (qulEduLevelsIntegrationBean == null) {
            initEduLevelsIntegration();
        }
        return qulEduLevelsIntegrationBean;
    }

    private void initEduLevelsIntegration() {
        if (getCenterDTO() != null && getCenterDTO().getCode() != null) {
            qulEduLevelsIntegrationBean.setSelectedCenterId(Long.valueOf(getCenterDTO().getCode().getKey()));
        }
        qulEduLevelsIntegrationBean.setReturnMethodName("#{" + BEAN_NAME + ".addLevelType}");
        qulEduLevelsIntegrationBean.setReRenderFields("qul_level,dataT_data_panel,search_Button,certificatePnl,qulPanel,integrationDiv2");
    }

    public void addLevelType() {
        if (qulEduLevelsIntegrationBean.getSelectedEduLevelDTO() != null) {
            StringBuilder str = buildTreeLevelAsString(qulEduLevelsIntegrationBean.getSelectedEduLevelDTO());
            try {
                setCertificateLevel(str.toString());
                setLevelDTO(qulEduLevelsIntegrationBean.getSelectedEduLevelDTO());
                setEnableResetData(true);
                setCertificateLevelId(levelDTO.getCode().getKey().toString());
                getCertificatesByCenterLevel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void searchCertificates() {
        List<IBasicDTO> resultList = new ArrayList<IBasicDTO>();
        if ( (getCertificateKey() != null && !getCertificateKey().trim().equalsIgnoreCase("")) ||
            (getCertificateName() != null && !getCertificateName().trim().equalsIgnoreCase("") ) ) {
            searchCertificatesList = new ArrayList();
            if(certificatesList != null && certificatesList.size() > 0) {
                ICenterQualificationsDTO centerQualificationsDTO = null;
                String certCode = null;
                String certName = null;
                for (int i = 0 ; i < certificatesList.size() ; i++) {
                    centerQualificationsDTO = (ICenterQualificationsDTO)certificatesList.get(i);
                    certCode = ((ICenterQualificationsEntityKey)centerQualificationsDTO.getCode() ).getCntqualificationCode();
                    certName = centerQualificationsDTO.getName();
                    if ( (getCertificateKey() != null && !getCertificateKey().trim().equalsIgnoreCase("")) &&
                         (getCertificateName() != null && !getCertificateName().trim().equalsIgnoreCase("") ) ) {
                        if( getCertificateKey().trim().equals(certCode) && certName.contains(getCertificateName().trim() ) ) {
                            searchCertificatesList.add(centerQualificationsDTO);   
                        }
                    } else if ( (getCertificateKey() != null && !getCertificateKey().trim().equalsIgnoreCase("")) ){
                        if( getCertificateKey().trim().equals(certCode) ) {
                            searchCertificatesList.add(centerQualificationsDTO);   
                        }
                    } else if ( (getCertificateName() != null && !getCertificateName().trim().equalsIgnoreCase("") ) ){
                        if( certName.contains(getCertificateName().trim() ) ) {
                            searchCertificatesList.add(centerQualificationsDTO);   
                        }
                    }
                }
            }
        }
    }
    
    public void getCertificate() {
        ICenterQualificationsDTO centerQualificationsDTO = (ICenterQualificationsDTO)getSelectedDTOS().get(0);
        selectedCertificateId = ((ICenterQualificationsEntityKey)centerQualificationsDTO.getCode()).getCntqualificationCode();
        fillQulByCertificate();
        resetCerDivValues();
    }
    
    public void resetCerDivValues() {
        certificateKey = null;
        certificateName = null;
        searchCertificatesList = new ArrayList();
        setSelectedDTOS(new ArrayList());
    }
    
    public void resetValues() {
        resetCerDivValues();
        qualificationName = null;
        selectedCertificateId = null;
        enableResetData = false;
    }

    public void setQulIntegrationListBean(QulIntegrationListBean qulIntegrationListBean) {
        this.qulIntegrationListBean = qulIntegrationListBean;
    }

    public QulIntegrationListBean getQulIntegrationListBean() {
        return qulIntegrationListBean;
    }


    public void setQul_country_list(List qul_country_list) {
        this.qul_country_list = qul_country_list;
    }

    public List getTotalList() {
        return new ArrayList();
    }

    public void setInValidMissionTypeCode(boolean inValidMissionTypeCode) {
        this.inValidMissionTypeCode = inValidMissionTypeCode;
    }

    public boolean isInValidMissionTypeCode() {
        return inValidMissionTypeCode;
    }

    public void setCertificateKey(String certificateKey) {
        this.certificateKey = certificateKey;
    }

    public String getCertificateKey() {
        return certificateKey;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setSearchCertificatesList(List searchCertificatesList) {
        this.searchCertificatesList = searchCertificatesList;
    }

    public List getSearchCertificatesList() {
        return searchCertificatesList;
    }

    public void setSearchCertificatesListSize(int searchCertificatesListSize) {
        this.searchCertificatesListSize = searchCertificatesListSize;
    }

    public int getSearchCertificatesListSize() {
        searchCertificatesListSize = 0;
        if(searchCertificatesList != null)
            searchCertificatesListSize = searchCertificatesList.size();
        return searchCertificatesListSize;
    }
    
}
