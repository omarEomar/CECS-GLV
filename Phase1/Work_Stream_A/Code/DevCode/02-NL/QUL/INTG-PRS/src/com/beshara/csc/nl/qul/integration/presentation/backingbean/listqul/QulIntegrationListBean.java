package com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.crs.business.client.CrsClientFactory;
import com.beshara.csc.hr.crs.business.dto.CrsDTOFactory;
import com.beshara.csc.hr.crs.business.dto.ICandidatePersonsDTO;
import com.beshara.csc.hr.crs.business.dto.ICandidatePersonsSearchDTO;
import com.beshara.csc.hr.crs.business.dto.IJobNeedsDTO;
import com.beshara.csc.hr.crs.business.dto.IJobSeekersDTO;
import com.beshara.csc.inf.business.client.IPersonsInformationClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.PersonsInformationDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.PersonRvisionDayReviewedBefore;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.crs.DiffQulsEducationLevelException;
import com.beshara.csc.sharedutils.business.exception.crs.LrgQulDateNoPeriodFoundException;
import com.beshara.csc.sharedutils.business.exception.crs.LrgQulDatePeriodFoundException;
import com.beshara.csc.sharedutils.business.exception.crs.PersonNotHasRevisionDayException;
import com.beshara.csc.sharedutils.business.exception.crs.SameQulsEduLevelCandidateException;
import com.beshara.csc.sharedutils.business.exception.crs.SameQulsEduLevelRejectedException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;


public class QulIntegrationListBean extends ManyToManyDetailsMaintain {


    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    //set bean name QualificationsBean
    protected static final Integer WIZARD_BAR_PAGE = 1;
    protected static final Integer NORMAL_PAGE = 2;
    private Long civilId;
    private String citizinFullName;
    private IKwtCitizensResidentsDTO kwtCitizenDTO;
    private String excptionValue;
    //    private IQualificationsDTO qualificationsDTO;
    //    private IPersonQualificationsDTO personQualificationsDTO = new PersonQualificationsDTO();
    private ArrayList<IPersonsInformationDTO> personsInformationDTOList;
    private List<IPersonsInformationDTO> notSavedPersonsInformationDTOList;
    private String ministryName = "";
    private Integer listSize = 0;
    private Integer pageType = 0;
    private String qulListPageinWizardBar = "";
    private List saveStateList = new ArrayList();
    private boolean saveInDB = true;
    private String dataTablestyleClass;
    private String addQualificationIntegrationpage;
    private boolean needAddQualification = true;
    private String bundleMsg;
    private boolean showCustomDiv2;
    private String content1DivStyle;
    private int selectedListSize = 0;
    private List <IPersonsInformationDTO>deletedList = new ArrayList<IPersonsInformationDTO>();


    public QulIntegrationListBean() {
        super();
        getHighlightedDTOS().clear();

        reloadKwtCitizenInfo();
    }

    public void initiateBeanOnce() {
        if (notSavedPersonsInformationDTOList == null)
            notSavedPersonsInformationDTOList = new ArrayList<IPersonsInformationDTO>();
        loadKwtCitizenDTO();
    }

    public void reloadKwtCitizenInfo() {
        if (kwtCitizenDTO == null) {
        IntegrationBean integrationBean = IntegrationBean.getInstance();

        HashMap qulMap = integrationBean.getHmBaseBeanFrom();

        if (integrationBean != null && qulMap.get("qulIntegrationBean") != null && qulMap.size() > 0) {
            QulIntegrationListBean myBean = (QulIntegrationListBean)qulMap.get("qulIntegrationBean");

            if (myBean.getCivilId() != null) {

                civilId = myBean.getCivilId();
                kwtCitizenDTO = (IKwtCitizensResidentsDTO)fillKwtCitizenInfo(civilId);

                if ((IBasicDTO)qulMap.get("personsInformationDTO") == null) {
                    myBean.setKwtCitizenDTO(kwtCitizenDTO);
                }

            }
            if (myBean.getBundleMsg() != null) {
                bundleMsg = myBean.getBundleMsg();
            }
            if (pageType.equals(NORMAL_PAGE)) {
                if (qulMap.get("personsInformationDTO") != null) {
                    getHighlightedDTOS().add((IBasicDTO)qulMap.get("personsInformationDTO"));
                }
            }
                setSaveInDB(myBean.isSaveInDB());

                if (myBean.getDataTablestyleClass() != null) {
                    setDataTablestyleClass(myBean.getDataTablestyleClass());
                }
                if (myBean.getContent1DivStyle() != null) {
                    setContent1Div(myBean.getContent1DivStyle());
                } else {
                    setContent1Div("module_tabs_cont1");
                }

            getHighlightedDTOS();

        }
    }
    }

    public static QulIntegrationListBean getInstance() {
        return (QulIntegrationListBean)JSFHelper.getValue("qulListIntegrationBean");
    }

    /**
     * if page case is list set pageType=NORMAL_PAGE
     * if page case is WizardBar pageType=WIZARD_BAR_PAGE
     * @return
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        if (pageType.equals(WIZARD_BAR_PAGE)) {
            app.showManyToManyMaintain();
            app.setShowCustomDiv2(showCustomDiv2);
        }
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        app.setShowContent1(true);
        return app;
    }

    public String back() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        return integrationBean.goFrom();
    }

    public IBasicDTO fillKwtCitizenInfo(Long civilId) {

        IBasicDTO basicDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        personsInformationDTOList = new ArrayList<IPersonsInformationDTO>();
        try {
            basicDTO = InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidentQuls(civilId);
            //          basicDTO =InfClientFactory.getKwtCitizensResidentsClient().getById(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId));
            citizinFullName = ((IKwtCitizensResidentsDTO)basicDTO).getFullName();
            listSize = getListSize();
            //getKwtCitizinData();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } finally {
            IntegrationBean integrationBean = IntegrationBean.getInstance();
            HashMap qulMap = integrationBean.getHmBaseBeanFrom();
            QulIntegrationListBean myBean = (QulIntegrationListBean)qulMap.get("qulIntegrationBean");
            if (basicDTO.getCode() == null && myBean.getKwtCitizenDTO() != null) {
                try {
                    if (myBean.getKwtCitizenDTO().getPersonsInformationDTOList() == null) {
                        myBean.getKwtCitizenDTO().setPersonsInformationDTOList(new ArrayList<IPersonsInformationDTO>());
                    }
                    basicDTO = (IKwtCitizensResidentsDTO)getSharedUtil().deepCopyObject(myBean.getKwtCitizenDTO());
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        }

        return basicDTO;
    }

    public void chooseCandidateOrder() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() == 1) {
            try {
                IPersonsInformationDTO selectedDto = (IPersonsInformationDTO)getSelectedDTOS().get(0);
                if (selectedDto.getPersonQualificationsDTO() != null) {
                    InfClientFactory.getPersonQualificationsClient().validatePersonQualification(selectedDto.getPersonQualificationsDTO());
                    getSharedUtil().setSuccessMsgValue("SuccesUpdated");
                }

            } catch (LrgQulDatePeriodFoundException e) {
                e.printStackTrace();
                setExcptionValue("LrgQulDatePeriodFoundException");
            } catch (LrgQulDateNoPeriodFoundException e) {
                e.printStackTrace();
                setExcptionValue("LrgQulDateNoPeriodFoundException");
            } catch (DiffQulsEducationLevelException e) {
                e.printStackTrace();
                setExcptionValue("DiffQulsEducationLevelException");
            } catch (SameQulsEduLevelCandidateException e) {
                e.printStackTrace();
                setExcptionValue("SameQulsEduLevelCandidateException");
            } catch (SameQulsEduLevelRejectedException e) {
                e.printStackTrace();
                setExcptionValue("SameQulsEduLevelRejectedException");
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getSharedUtil().setErrMsgValue("FailureInUpdate");
            } catch (DataBaseException e) {
                e.printStackTrace();
                getSharedUtil().setErrMsgValue("FailureInUpdate");
            }
            if (getExcptionValue() == null || getExcptionValue().equals("")) {
                try {
                    InfClientFactory.getPersonQualificationsClient().updateDefaultCase(((IPersonsInformationDTO)getSelectedDTOS().get(0)).getPersonQualificationsDTO());
                    getKwtCitizinData();
                    getSelectedDTOS().clear();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    getSharedUtil().setErrMsgValue("FailureInUpdate");
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    getSharedUtil().setErrMsgValue("FailureInUpdate");
                }
            } else {
                //                maintainBean.setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; setFocusOnlyOnElement ( 'CancelButtonEdit' ) ; ");
            }
        }
    }

    public void getKwtCitizinData() {
        if (civilId != null) {
            try {
                setPageDTO(((IJobSeekersDTO)CrsClientFactory.getJobSeekersClient().getKwtCitizinData(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId))));
                if (((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getPersonQualificationsDTOList() ==
                    null) {
                    ((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().setPersonQualificationsDTOList(new ArrayList());
                }
                if (((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getPersonsInformationDTOList() ==
                    null) {
                    ((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().setPersonsInformationDTOList(new ArrayList());
                }
                if (!((IJobSeekersDTO)getPageDTO()).isUpdatePersonInformaion()) {
                    //                        setUpdatePersonInformaion(false);
                    //                        setErrorMsg("RevisionDateNotEqualCurrentDate");
                }
                if (((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getKwtWorkDataDTOList() == null) {
                    ((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().setKwtWorkDataDTOList(new ArrayList());
                }
                if (((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getSeekerLanguageSkillsDTOList() ==
                    null) {
                    ((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().setSeekerLanguageSkillsDTOList(new ArrayList());
                }
                ICandidatePersonsSearchDTO candidatePersonsSearchDTO = CrsDTOFactory.createCandidatePersonsSearchDTO();
                candidatePersonsSearchDTO.setCivilId(civilId);

                if (((IJobSeekersDTO)getPageDTO()).getRegStatusDTO() != null &&
                    ((IJobSeekersDTO)getPageDTO()).getRegStatusDTO().getCode().getKey().toString().equals(getManagedConstantsBean().getRegCandidateStatus().toString())) {

                    List candidateList = null;
                    try {
                        candidateList = CrsClientFactory.getCandidatePersonsClient().search(candidatePersonsSearchDTO);
                        ICandidatePersonsDTO candidatePersonsDTO = (ICandidatePersonsDTO)candidateList.get(0);
                        ministryName =
                                ((IJobNeedsDTO)candidatePersonsDTO.getJobNeedsDTO()).getNeedBooksDTO().getMinistriesDTO().getName();

                    } catch (Exception e) {
                        e.printStackTrace();
                        ministryName = "";
                    }
                } else {
                    ministryName = "";
                }

                //                    if (((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getKwtWorkDataDTOList() != null) {
                //                        ExperiencesBean experiencesBean =
                //                            (ExperiencesBean)evaluateValueBinding("manualScreenExperiencesBean");
                //                        List<IKwtWorkDataDTO> kwtWrkData =
                //                            ((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getKwtWorkDataDTOList();
                //                        for (IKwtWorkDataDTO dto : kwtWrkData) {
                //
                //                            experiencesBean.calcServiceDaysPerRow(dto);
                //
                //                        }
                //                        experiencesBean.calcServiceDays(kwtWrkData);
                //                    }
                // For Loob To Sort personInformationList by CrsRegistrationOrder BY Kareem Sayed
                setPersonsInformationDTOList((ArrayList)(((IJobSeekersDTO)getPageDTO()).getKwtCitizensResidentsDTO().getPersonsInformationDTOList()));
            } catch (PersonRvisionDayReviewedBefore e) {
                getSharedUtil().handleException(e, "com.beshara.csc.hr.crs.presentation.resources.crs",
                                                "PersonRvisionDayReviewedBeforeException");
                e.printStackTrace();
            } catch (PersonNotHasRevisionDayException e) {
                setErrorMsg("PersonNotHasRevisionDayException");
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                setPageDTO(CrsDTOFactory.createJobSeekersDTO());
                e.printStackTrace();
            } catch (DataBaseException e) {
                setPageDTO(CrsDTOFactory.createJobSeekersDTO());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                IJobSeekersDTO jobseeker = CrsDTOFactory.createJobSeekersDTO();

                setPageDTO(jobseeker);
            }
        }
    }

    public Integer getListSize() {
        if (civilId != null && kwtCitizenDTO != null) {
            return kwtCitizenDTO.getPersonsInformationDTOList() == null ? 0 :
                   kwtCitizenDTO.getPersonsInformationDTOList().size();
        }
        return 0;

    }

    public String goAdd() {
        if (getBundleMsg() != null && !getBundleMsg().isEmpty()) {
            getSharedUtil().setErrMsgValue(getBundleMsg());
            return null;
        }
        IntegrationBean integrationBean = IntegrationBean.getInstance();
        integrationBean.getHmBaseBeanFrom().put("qulListIntegrationBean", QulIntegrationListBean.getInstance());
        if (pageType.equals(WIZARD_BAR_PAGE)) {
            integrationBean.getHmBaseBeanFrom().put("backPage", qulListPageinWizardBar);
            if (getKwtCitizenDTO().getPersonsInformationDTOList() == null) {
                getKwtCitizenDTO().setPersonsInformationDTOList(new ArrayList<IPersonsInformationDTO>());
            }
            setNotSavedPersonsInformationDTOList(getKwtCitizenDTO().getPersonsInformationDTOList());
            integrationBean.setNavgationCaseFrom("qualificationsCandidatePage");
            integrationBean.setBeanNameFrom("qulListIntegrationBean");
            integrationBean.setActionFrom("retFromIntigration");
            return getAddQualificationIntegrationpage();
        } else {
            integrationBean.getHmBaseBeanFrom().put("backPage", "qulListPage");
            return "AddQualificationIntegration";
        }

    }

    public String retFromIntigration() {
        setSuccess(true);
        getHighlightedDTOS().clear();
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            getHighlightedDTOS().add(getSelectedDTOS().get(0));
        }
        return qulListPageinWizardBar;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public void setExcptionValue(String excptionValue) {
        this.excptionValue = excptionValue;
    }

    public String getExcptionValue() {
        return excptionValue;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {

        return civilId;
    }

    public void setCitizinFullName(String citizinFullName) {
        this.citizinFullName = citizinFullName;
    }

    public String getCitizinFullName() {
        return citizinFullName;
    }

    public void setKwtCitizenDTO(IKwtCitizensResidentsDTO kwtCitizenDTO) {
        this.kwtCitizenDTO = kwtCitizenDTO;
    }

    public void loadKwtCitizenDTO() {
        if (civilId != null && kwtCitizenDTO == null) {
            kwtCitizenDTO = (IKwtCitizensResidentsDTO)fillKwtCitizenInfo(civilId);
        } else if (civilId == null) {
            reloadKwtCitizenInfo();
        }
    }

    public IKwtCitizensResidentsDTO getKwtCitizenDTO() {
        return kwtCitizenDTO;
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

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setQulListPageinWizardBar(String qulListPageinWizardBar) {
        this.qulListPageinWizardBar = qulListPageinWizardBar;
    }

    public String getQulListPageinWizardBar() {
        return qulListPageinWizardBar;
    }

    public void setSaveStateList(List saveStateList) {
        this.saveStateList = saveStateList;
    }

    public List getSaveStateList() {
        return saveStateList;
    }

    public void setSaveInDB(boolean saveInDB) {
        this.saveInDB = saveInDB;
    }

    public boolean isSaveInDB() {
        return saveInDB;
    }

    public void setNotSavedPersonsInformationDTOList(List<IPersonsInformationDTO> notSavedPersonsInformationDTOList) {
        this.notSavedPersonsInformationDTOList = notSavedPersonsInformationDTOList;
    }

    public List<IPersonsInformationDTO> getNotSavedPersonsInformationDTOList() {
        return notSavedPersonsInformationDTOList;
    }

    public void setDataTablestyleClass(String dataTablestyleClass) {
        this.dataTablestyleClass = dataTablestyleClass;
    }

    public String getDataTablestyleClass() {
        if (dataTablestyleClass == null || dataTablestyleClass.equals("")) {
            return "dataT-With-1-row-filter";
        } else {
            return dataTablestyleClass;
        }

    }

    public void setAddQualificationIntegrationpage(String addQualificationIntegrationpage) {
        this.addQualificationIntegrationpage = addQualificationIntegrationpage;
    }

    public String getAddQualificationIntegrationpage() {
        return addQualificationIntegrationpage;
    }

    public void setNeedAddQualification(boolean needAddQualification) {
        this.needAddQualification = needAddQualification;
    }

    public boolean isNeedAddQualification() {
        return needAddQualification;
    }

    public void setBundleMsg(String bundleMsg) {
        this.bundleMsg = bundleMsg;
    }

    public String getBundleMsg() {
        return bundleMsg;
    }

    public List getTotalList() {
        return new ArrayList();
    }

    public void setShowCustomDiv2(boolean showCustomDiv2) {
        this.showCustomDiv2 = showCustomDiv2;
    }

    public boolean isShowCustomDiv2() {
        return showCustomDiv2;
    }

    public void setContent1DivStyle(String content1DivStyle) {
        this.content1DivStyle = content1DivStyle;
    }

    public String getContent1DivStyle() {
        return content1DivStyle;
    }

    /**
     * Added By H.Omar[B.Horse Team] @ 22/12/2016
     * remove PersonQualification
     */
    public void deleteQualification() {
        System.out.println("delete selected record ...");
        PersonsInformationDTO pInfoDTO = (PersonsInformationDTO)getMyDataTable().getRowData();
        PersonsInformationDTO itemToDelete = (PersonsInformationDTO)getSelectedRecord(pInfoDTO.getCode());
        IPersonsInformationClient client = ClientFactoryUtil.getInstance(IPersonsInformationClient.class);
        IPersonsInformationDTO pInfoDTOFromDB = null;
        try {
            pInfoDTOFromDB = (IPersonsInformationDTO)client.getById(itemToDelete.getCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        try {
            if (pInfoDTOFromDB != null) {
                client.remove(pInfoDTOFromDB);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        getKwtCitizenDTO().getPersonsInformationDTOList().remove(itemToDelete);
        refreshMyTableData();
    }

    private IBasicDTO getSelectedRecord(IEntityKey code) {
        for (IBasicDTO basicDTO : getKwtCitizenDTO().getPersonsInformationDTOList()) {
            if (basicDTO.getCode().equals(code)) {
                return basicDTO;
            }
        }
        return null;
    }


    private void refreshMyTableData() {
        this.reloadKwtCitizenInfo();
    }

    public void setSelectedListSize(int selectedListSize) {
        this.selectedListSize = selectedListSize;
}

    public int getSelectedListSize() {

        if (this.getSelectedDTOS() != null) {

            return this.getSelectedDTOS().size();

        }

        return selectedListSize;
    }


    public void doDelete() {
        System.out.println("doDElETE");
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
        
            deletedList.addAll(((List)getSelectedDTOS()));
            kwtCitizenDTO.getPersonsInformationDTOList().removeAll(getSelectedDTOS());
            getSelectedDTOS().clear();
        }
    }


    public void setDeletedList(List<IPersonsInformationDTO> deletedList) {
        this.deletedList = deletedList;
    }

    public List<IPersonsInformationDTO> getDeletedList() {
        return deletedList;
    }
}
