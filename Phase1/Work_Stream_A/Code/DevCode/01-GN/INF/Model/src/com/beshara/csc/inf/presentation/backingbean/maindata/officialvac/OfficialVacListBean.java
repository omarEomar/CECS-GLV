package com.beshara.csc.inf.presentation.backingbean.maindata.officialvac;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IHolidayTypesDTO;
import com.beshara.csc.inf.business.dto.IHolidaysDTO;
import com.beshara.csc.inf.business.dto.IHolidaysSearchDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IHolidayTypesEntityKey;
import com.beshara.csc.inf.business.entity.IYearsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.integration.presentation.backingbean.reg.RegulationAddBean;
import com.beshara.csc.integration.presentation.backingbean.reg.RegulationJoinBean;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;


public class OfficialVacListBean extends LookUpBaseBean {

    public static String BACK_BEAN_NAME = "officialVacListBean";
    
    
    private static Map<String,Long> availableVacTypes = new HashMap<String,Long>(); 
    
    private boolean yearSelected = false;
    // for list part
    private String selectedYear = getManagedConstantsBean().getVirtualStringValueConstant();
    private List yearList = new ArrayList();
    private List<IBasicDTO> typeListAdd = new ArrayList();
    //for add - edit part
    private String selectedType = "";
    private Long selectedTypeDays;
    private List<IBasicDTO> typeList = new ArrayList<IBasicDTO>();
    private boolean editMode = false; // flag to swith between add and edit div //
    private String yearName;
    private Date firstDayInYears;
    private Date endDayInYears;
    // for search part
    private IHolidaysSearchDTO searchDTO = InfDTOFactory.createHolidaysSearchDTO();
    private String selectedSearchYear = "";
    private String selectedSearchType = "";

    //regulations
    private RegulationJoinBean regulationJoinBean = new RegulationJoinBean();

    public OfficialVacListBean() {
        setPageDTO(InfDTOFactory.createHolidaysDTO());
        setSelectedPageDTO(InfDTOFactory.createHolidaysDTO());
        setEntityKey(InfEntityKeyFactory.createHolidaysEntityKey());
        setClient(InfClientFactory.getHolidaysClient());
        setMasterDetailDiv("divREGIntegrate");
        setDivMainContent("divMainContent250");
        //        setDivSearch("divSearchCustom");

        getRegulationJoinBean().setBackBeanNameFrom(BACK_BEAN_NAME);

        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowContent1(true);
        app.setShowMasterDetail(true);
        app.setShowLookupEdit(false);
        return app;
    }

    /**
     * Purpose: Action event fire when selected year changed
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  27-Aug-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void yearChanged(ActionEvent ae) {
        ae = null; //unused
        setMyTableData(null);
        reinitializeSort();
    }

    public void deleteDiv() {
        try {
            // this solution as selectedYear resetted in cancelSearch
            String x = getSelectedYear();
            super.deleteDiv();
            setSelectedYear(x);
            setMyTableData(null);
            reinitializeSort();
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }


    public void getAll() {
        setPageMode(0);
        cancelSearch();
        resetSearchFilter();
        if (getSelectedYear() != null &&
            !getSelectedYear().equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
            try {
                setYearSelected(true);
                // IHolidaysSearchDTO holidaysSearchDTO =  InfDTOFactory.createHolidaysSearchDTO();
                // holidaysSearchDTO.setYearCode(Long.parseLong(getSelectedYear()));
                setMyTableData(InfClientFactory.getHolidaysClient().getOfficialVacation(Long.valueOf(getSelectedYear())));
                //setMyTableData(((IHolidaysClient)getClient()).getOfficialVacation(Long.parseLong(getSelectedYear())));
                getMyDataTable().setFirst(0);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                setMyTableData(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                setMyTableData(new ArrayList());
            } catch (Exception e) {
                e.printStackTrace();
                setMyTableData(new ArrayList());
            }
        } else {
            setMyTableData(new ArrayList());
            setYearSelected(false);
        }
        reinitializeSort();
    }


    public void preAddOfficialVac() {
        setPageMode(1);
        reIntializeAddDivMsgs();
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        setEditMode(false);
        super.preAdd();
        setSelectedType("");
        setPageDTO(InfDTOFactory.createHolidaysDTO());
        if (selectedYear != null && !selectedYear.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
            //By Ashraf Gaber HR-683
            yearName = selectedYear;
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusAtTypeListAdd();");
            //            try
            //            {
            //                IYearsDTO year = (IYearsDTO)(InfClientFactory.getYearsClient().getById(new IYearsEntityKey(Long.parseLong(selectedYear))));
            //                yearName=year.getName();
            //                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");
            //            } catch (SharedApplicationException e) {
            //                e.printStackTrace();
            //            } catch (DataBaseException e) {
            //                e.printStackTrace();
            //            } catch (Exception e) {
            //               e.printStackTrace();
            //            }


            //            setFirstDayInYears("01/01/"+ yearName);
            //            setEndDayInYears("31/12/"+yearName);
            //            }


            int year = Integer.valueOf(yearName);
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.set(Calendar.YEAR, year);
            calendarStart.set(Calendar.MONTH, 0);
            calendarStart.set(Calendar.DAY_OF_MONTH, 1);
            // returning the first date
            Date startDate = calendarStart.getTime();
            setFirstDayInYears(startDate);
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.set(Calendar.YEAR, year);
            calendarEnd.set(Calendar.MONTH, 11);
            calendarEnd.set(Calendar.DAY_OF_MONTH, 31);

            // returning the last date
            Date endDate = calendarEnd.getTime();
            setEndDayInYears(endDate);
        }


    }

    public void calcUntilDate() {
        String selectedType = getSelectedType();
        Long numberOfDays;
        if(!"-100".equals(selectedType) && ((IHolidaysDTO) getPageDTO()).getFromDate() != null) {
            numberOfDays = getSelectedTypeDays();
            Calendar fromDate = Calendar.getInstance();
            Calendar untilDate = Calendar.getInstance();
            fromDate.setTime(((IHolidaysDTO) getPageDTO()).getFromDate());
            untilDate.setTime(fromDate.getTime()); 
            untilDate.add(Calendar.DATE, getSelectedTypeDaysAsInteger() - 1);
            
            java.sql.Date calculatedDate = new java.sql.Date(untilDate.getTimeInMillis());
            ((IHolidaysDTO) getPageDTO()).setUntilDate(calculatedDate);
        } else {
            ((IHolidaysDTO) getPageDTO()).setUntilDate(null);
        }
    
    }
    public void preEditOfficialVac() {
        try {
            setPageMode(1);
            setEditMode(true);
            reIntializeAddDivMsgs();
            //if (getSelectedPageDTO() != null && getSelectedPageDTO().getCode() != null) {
            //setEntityKey(getSelectedPageDTO().getCode());
            //} else

            //            if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            //                setEntityKey(getSelectedDTOS().get(0).getCode());
            //            }
            //            System.out.println(((IHolidaysDTO)getSelectedDTOS().get(0)).getFromDate().toString());
            //            setPageDTO(super.preEdit());

            IHolidaysDTO selectedHoliday = (IHolidaysDTO)getSelectedDTOS().get(0);
            IHolidaysSearchDTO holidaysSearchDTO = InfDTOFactory.createHolidaysSearchDTO();
            holidaysSearchDTO.setYearCode(((IYearsEntityKey)selectedHoliday.getYearsDTO().getCode()).getYearCode());
            holidaysSearchDTO.setHoltypeCode(((IHolidayTypesEntityKey)selectedHoliday.getHolidayTypesDTO().getCode()).getHoltypeCode());
            holidaysSearchDTO.setFromDate(selectedHoliday.getFromDate());
            List<IBasicDTO> result = getClient().searchInCenter(holidaysSearchDTO);
            if (result != null && result.size() != 0) {
                setPageDTO(result.get(0));
            }

            if (getPageDTO() != null && getPageDTO().getCode() != null) {
                if (((IHolidaysDTO)getPageDTO()).getHolidayTypesDTO() != null &&
                    ((IHolidaysDTO)getPageDTO()).getHolidayTypesDTO().getCode() != null)
                    setSelectedType(((IHolidaysDTO)getPageDTO()).getHolidayTypesDTO().getCode().getKey().toString());
            }
            if (((IHolidaysDTO)getPageDTO()).getYearsDTO() != null &&
                ((IHolidaysDTO)getPageDTO()).getYearsDTO().getCode() != null) {
                //By Ashraf Gaber HR-683
                Object key = ((IHolidaysDTO)getPageDTO()).getYearsDTO().getCode().getKey();
                if (key instanceof String)
                    yearName = (String)key;
                int year = Integer.valueOf(yearName);
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, 0);
                calendarStart.set(Calendar.DAY_OF_MONTH, 1);
                // returning the first date
                Date startDate = calendarStart.getTime();
                setFirstDayInYears(startDate);
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.set(Calendar.YEAR, year);
                calendarEnd.set(Calendar.MONTH, 11);
                calendarEnd.set(Calendar.DAY_OF_MONTH, 31);

                // returning the last date
                Date endDate = calendarEnd.getTime();
                setEndDayInYears(endDate);
                //yearName = ((IHolidaysDTO)getPageDTO()).getYearsDTO().getName();
            }
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusAtFromDateAdd();");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAdd() {
        super.cancelAdd();
        setPageMode(0);
    }


    public void saveOfficialVac() {
        SharedUtilBean sharedUtil = getSharedUtil();

        reIntializeMsgs();
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        sharedUtil.handleSuccMsg("");
        setErrorMsg(null);
        sharedUtil.setErrMsgValue(null);
        if (selectedType != null && !selectedType.equals("")) {
            IHolidayTypesDTO holidayTypesDTO = InfDTOFactory.createHolidayTypesDTO();
            holidayTypesDTO.setCode(InfEntityKeyFactory.createHolidayTypesEntityKey(Long.parseLong(selectedType)));
            ((IHolidaysDTO)getPageDTO()).setHolidayTypesDTO(holidayTypesDTO);
        }
        if (selectedYear != null && !selectedYear.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
            IYearsDTO yearsDTO = InfDTOFactory.createYearsDTO();
            yearsDTO.setCode(InfEntityKeyFactory.createYearsEntityKey(Long.parseLong(selectedYear)));
            ((IHolidaysDTO)getPageDTO()).setYearsDTO(yearsDTO);
        }
        try {
            setPageDTO(getClient().add(getPageDTO()));

            setSuccess(true);
            getAll();
            if (getPageDTO() != null && getPageDTO().getCode() != null) {
                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().clear();
                    getHighlightedDTOS().add(getPageDTO());
                }
                try {
                    getPageIndex((String)getPageDTO().getCode().getKey());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sharedUtil.setSuccessMsgValue("SuccessAdds");
            //sharedUtil.handleSuccMsg("SuccessAdds");
            setTypeListAdd(null);
        } catch (SharedApplicationException e) {
            setShowErrorMsg(true);
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");
            setErrorMsg("FailureInAdd");
        } catch (DataBaseException e) {
            setShowErrorMsg(true);
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");
            setErrorMsg("FailureInAdd");
        } catch (Exception e) {
            setShowErrorMsg(true);
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");
            setErrorMsg("FailureInAdd");
        }
    }


    public void editOfficialVac() {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
            boolean updated = InfClientFactory.getHolidaysClient().updateHoliday(getPageDTO(),((IHolidaysDTO)getSelectedDTOS().get(0)).getFromDate());
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (updated == true) {
                sharedUtil.setSuccessMsgValue("SuccesUpdated");
                //                getSharedUtil().handleSuccMsg(
                getAll();
                //getClient().getOfficialVacation(((HolidaysDTO)getSelectedDTOS()).getYearsDTO().getYearCode())
                if (getPageDTO() != null && getPageDTO().getCode() != null) {
                    if (getHighlightedDTOS() != null) {
                        getHighlightedDTOS().add(getPageDTO());
                    }
                    try {
                        getPageIndex((String)getPageDTO().getCode().getKey());
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sharedUtil.handleException(null, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                           "FailureInUpdate");
            }

        } catch (SharedApplicationException e) {
            unCheck();
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");

        } catch (DataBaseException e) {
            unCheck();
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        } catch (Exception e) {
            unCheck();
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
    }


    public void searchVac() {
        boolean untilDateWasNull = false;
        try {
            //setSelectedYear(getManagedConstantsBean().getVirtualStringValueConstant());
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
            setSearchMode(true);
            if (selectedSearchType != null && !selectedSearchType.equals("")) {
                searchDTO.setHoltypeCode(Long.parseLong(selectedSearchType));
            } else {
                searchDTO.setHoltypeCode(null);
            }

            if (selectedYear != null &&
                !selectedYear.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
                searchDTO.setYearCode(Long.parseLong(selectedYear));
            } else {
                searchDTO.setYearCode(null);
            }
            //            if (searchDTO.getFromDate() != null) {
            //                 untilDateWasNull = true;
            //                 searchDTO.setUntilDate(searchDTO.getFromDate());
            //            }
            //
            //           if (searchDTO.getUntilDate() == null) {
            //                untilDateWasNull = true;
            //                searchDTO.setUntilDate(searchDTO.getFromDate());
            //           }


            setMyTableData(InfClientFactory.getHolidaysClient().searchVacOfficial(searchDTO));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } finally {
            //            if(untilDateWasNull){
            //                searchDTO.setUntilDate(null);
            //            }
        }
        reinitializeSort();
    }


    public void saveAndNew() {
        saveOfficialVac();
        setPageDTO(InfDTOFactory.createHolidaysDTO());
        setSelectedType("");
        setPageMode(1);
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        //        if (getHighlightedDTOS() != null)
        //            getHighlightedDTOS().clear();
    }


    public void cancelSearch() {
        setSearchMode(false);
        resetSearchFilter();
        //        setSelectedYear(getManagedConstantsBean().getVirtualStringValueConstant());
        //        setSelectedSearchType(getVirtualConstValue());
        //        setSelectedSearchYear(getVirtualConstValue());
        //        setMyTableData(new ArrayList());
        //        reinitializeSort();
    }

    public void resetSearchFilter() {
        setSearchDTO(InfDTOFactory.createHolidaysSearchDTO());
        setSelectedSearchType(null);
        yearChanged(null);
    }

    /**
     * Purpose:called to navigate add reg page at reg module
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   28-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */

    public String goToRegulation() {
        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setRenderedBackButton(true);
        getIntegrationBean().setNavgationCaseFrom("officialVacList");
        getIntegrationBean().setNavgationCaseTo("regulationlist");
        getIntegrationBean().setBeanNameTo("regulationListBean");
        getIntegrationBean().setActionTo("goAdd");
        getIntegrationBean().getHmBaseBeanFrom().put(BACK_BEAN_NAME, evaluateValueBinding(BACK_BEAN_NAME));
        getIntegrationBean().setModuleFrom("vac");
        getIntegrationBean().setActionFrom("addRegulation");
        getIntegrationBean().setBeanNameFrom(BACK_BEAN_NAME);
        return getIntegrationBean().goTO();
    }

    /**
     * Purpose:save new added reg object
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   28-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */

    public String addRegulation() {

        RegulationAddBean regulationAddBean = (RegulationAddBean)evaluateValueBinding("regulationAddBean");
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0 && getSelectedDTOS().get(0).getCode() != null &&
            getIntegrationBean().getSelectedDTOTo() != null &&
            getIntegrationBean().getSelectedDTOTo().get(0) != null &&
            getIntegrationBean().getSelectedDTOTo().get(0).getCode() != null) {
            try {
                IModuleRelationsDTO moduleRelationsDTO =
                    (IModuleRelationsDTO)InfClientFactory.getHolidaysClient().getModuleRelationDTOInCenter(getSelectedDTOS().get(0).getCode());
                ((IRegulationsDTO)getIntegrationBean().getSelectedDTOTo().get(0)).setModuleRelationsDTOList(new ArrayList<IModuleRelationsDTO>());
                ((IRegulationsDTO)getIntegrationBean().getSelectedDTOTo().get(0)).getModuleRelationsDTOList().add(moduleRelationsDTO);
                regulationAddBean.setRegulationsDTO((IRegulationsDTO)getIntegrationBean().getSelectedDTOTo().get(0));
                resetData();
                regulationAddBean.save();

            } catch (SharedApplicationException e) {
                regulationAddBean.setRegulationsDTO(null);
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAddForRegulation");
            } catch (DataBaseException e) {
                regulationAddBean.setRegulationsDTO(null);
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAddForRegulation");
            } catch (Exception e) {
                regulationAddBean.setRegulationsDTO(null);
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAddForRegulation");
            }
        } else {
            regulationAddBean.setRegulationsDTO(null);
            setErrorMsg("FailureInAddForRegulation");
        }
        System.out.println("--------------------officialVacList----------------");
        return "officialVacList";
    }

    public void resetData() {
        setSelectedListSize(0);


    }

    /**
     * Purpose:called when user press join regulation option
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   28-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void openJoinRegualationDiv() {
        getRegulationJoinBean().setInitializeBeforeSaveMethod("officialVacListBean.initializeModuleRelationsDTO");
        getRegulationJoinBean().setResetButtonMethod("officialVacListBean.resetData");
    }

    /**
     * Purpose:used to initialize module relation dto @ RegulationJoinBean before execute saving
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   28-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void initializeModuleRelationsDTO() {

        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0 && getSelectedDTOS().get(0).getCode() != null) {
            try {
                getRegulationJoinBean().setModuleRelationsDTO((IModuleRelationsDTO)InfClientFactory.getHolidaysClient().getModuleRelationDTOInCenter(getSelectedDTOS().get(0).getCode()));
            } catch (SharedApplicationException e) {
                regulationJoinBean.setModuleRelationsDTO(null);
                e.printStackTrace();
            } catch (DataBaseException e) {
                regulationJoinBean.setModuleRelationsDTO(null);
                e.printStackTrace();
            } catch (Exception e) {
                regulationJoinBean.setModuleRelationsDTO(null);
                e.printStackTrace();
            }
        } else
            regulationJoinBean.setModuleRelationsDTO(null);
    }

    public List getYearList() {
        if (yearList == null || yearList.size() == 0) {
            try {
                yearList = InfClientFactory.getYearsClient().getCodeName();
            } catch (DataBaseException e) {
                yearList = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                yearList = new ArrayList();
                e.printStackTrace();
            }
        }
        return yearList;
    }

    public void setTypeList(List<IBasicDTO> typeList) {
        this.typeList = typeList;
    }

    public List<IBasicDTO> getTypeList() {
        try {
            typeList = InfClientFactory.getHolidayTypesClientForCenter().getCodeName();
            
        } catch (DataBaseException e) {
            e.printStackTrace();
            typeList = new ArrayList();
        } catch (Exception e) {
            e.printStackTrace();
            typeList = new ArrayList();
        }
        return typeList;
    }

    public void setSelectedSearchYear(String selectedSearchYear) {
        this.selectedSearchYear = selectedSearchYear;
    }

    public String getSelectedSearchYear() {
        return selectedSearchYear;
    }

    public void setSelectedSearchType(String selectedSearchType) {
        this.selectedSearchType = selectedSearchType;
    }

    public String getSelectedSearchType() {
        return selectedSearchType;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setYearList(List yearList) {
        this.yearList = yearList;
    }


    public void setSearchDTO(IHolidaysSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IHolidaysSearchDTO getSearchDTO() {
        return searchDTO;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getYearName() {
        return yearName;
    }

    public void setRegulationJoinBean(RegulationJoinBean regulationJoinBean) {
        this.regulationJoinBean = regulationJoinBean;
    }

    public RegulationJoinBean getRegulationJoinBean() {
        return regulationJoinBean;
    }

    public void setYearSelected(boolean yearSelected) {
        this.yearSelected = yearSelected;
    }

    public boolean isYearSelected() {
        yearSelected =
                !(selectedYear == null || selectedYear.equals(getManagedConstantsBean().getVirtualStringValueConstant()));
        return yearSelected;
    }

    public void setTypeListAdd(List typeListAdd) {
        this.typeListAdd = typeListAdd;
    }

    public List getTypeListAdd() {

        try {
            if (selectedYear != null &&
                !selectedYear.equals(getManagedConstantsBean().getVirtualStringValueConstant()))
                typeListAdd = InfClientFactory.getHolidaysClient().listAvailable(Long.parseLong(selectedYear));
            this.availableVacTypes.clear();
            for (IBasicDTO dto : typeListAdd) {
                if(((IHolidayTypesDTO)dto).getHoltypeDays()!= null){
                    this.getAvailableVacTypes().put(dto.getCode().getKey(),((IHolidayTypesDTO)dto).getHoltypeDays());
                }
            }
        } catch (DataBaseException e) {
            typeListAdd = new ArrayList();
            e.printStackTrace();
        } catch (Exception e) {
            typeListAdd = new ArrayList();
            e.printStackTrace();
        }

        return typeListAdd;
    }

    public void preSearch() {

        if (selectedYear != null && !selectedYear.equals(getManagedConstantsBean().getVirtualStringValueConstant())) {
            yearName = selectedYear;
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusAtTypeListAdd();");
            int year = Integer.valueOf(yearName);
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.set(Calendar.YEAR, year);
            calendarStart.set(Calendar.MONTH, 0);
            calendarStart.set(Calendar.DAY_OF_MONTH, 1);
            // returning the first date
            Date startDate = calendarStart.getTime();
            setFirstDayInYears(startDate);
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.set(Calendar.YEAR, year);
            calendarEnd.set(Calendar.MONTH, 11);
            calendarEnd.set(Calendar.DAY_OF_MONTH, 31);
            Date endDate = calendarEnd.getTime();
            setEndDayInYears(endDate);
        }
    }

    public String viewAvailableReg() {
        setEntityKey(getSelectedDTOS().get(0).getCode());
        IHolidaysDTO holidaysDTO = (IHolidaysDTO)preEdit();

        if (holidaysDTO != null) {
            try {
                long serial = holidaysDTO.getTabrecSerial();
                IRegulationsDTO regulationsDTO =
                    (IRegulationsDTO)RegClientFactory.getModuleRelationsClient().getLatestRelatedRegulationInCenter(serial);

                IModuleRelationsDTO moduleRelationsDTO = RegDTOFactory.createModuleRelationsDTO();
                moduleRelationsDTO.setRegulationsDTO(regulationsDTO);
                moduleRelationsDTO.setTabrecSerial(serial);
                moduleRelationsDTO.setCode(regulationsDTO.getCode());

                List<IBasicDTO> list = new ArrayList<IBasicDTO>();
                list.add(moduleRelationsDTO);


                getIntegrationBean().reInitializeBean();
                getIntegrationBean().setSelectedDTOFrom(list);
                getIntegrationBean().setRenderedBackButton(true);
                getIntegrationBean().setNavgationCaseFrom("officialVacList");
                getIntegrationBean().setNavgationCaseTo("regulationlist");
                getIntegrationBean().setBeanNameTo("regulationListBean");
                getIntegrationBean().setActionTo("goView");
                getIntegrationBean().getHmBaseBeanFrom().put(BACK_BEAN_NAME, evaluateValueBinding(BACK_BEAN_NAME));
                getIntegrationBean().setModuleFrom("vac");
                getIntegrationBean().setActionFrom("addRegulation");
                getIntegrationBean().setBeanNameFrom(BACK_BEAN_NAME);
                return getIntegrationBean().goTO();

            } catch (NoResultException e) {
                e.printStackTrace();
                this.setShowErrorMsg(true);
                this.setErrorMsg("noRegAvailable");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.global",
                                                "NO_RELATED_REGULATIONS_MSG");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void viewRelatedRegualations() {

        setEntityKey(getSelectedDTOS().get(0).getCode());
        IHolidaysDTO holidaysDTO = (IHolidaysDTO)preEdit();

        if (holidaysDTO != null) {
            try {
                long tabRecSerial = holidaysDTO.getTabrecSerial();
                regulationJoinBean.setTabRecSerial(tabRecSerial);
                regulationJoinBean.setViewInCenter(true);
                regulationJoinBean.displayRegulationList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void selectedTypeChange(){
        ((IHolidaysDTO)getPageDTO()).setFromDate(null);
        ((IHolidaysDTO)getPageDTO()).setUntilDate(null);
            
    }
    
    public void setFirstDayInYears(Date firstDayInYears) {
        this.firstDayInYears = firstDayInYears;
    }

    public Date getFirstDayInYears() {
        return firstDayInYears;
    }

    public void setEndDayInYears(Date endDayInYears) {
        this.endDayInYears = endDayInYears;
    }

    public Date getEndDayInYears() {
        return endDayInYears;
    }

    public void setSelectedTypeDays(Long selectedTypeDays) {
        this.selectedTypeDays = selectedTypeDays;
    }

    public Long getSelectedTypeDays() {
        if(!getVirtualConstValue().equals(this.selectedType) && getAvailableVacTypes().containsKey(this.selectedType)){
            return getAvailableVacTypes().get(this.selectedType);
        }
            if (getPageDTO() != null && getPageDTO().getCode() != null) {
                if (((IHolidaysDTO)getPageDTO()).getHolidayTypesDTO() != null &&
                    ((IHolidaysDTO)getPageDTO()).getHolidayTypesDTO().getCode() != null){
                   return ((IHolidaysDTO)getPageDTO()).getHolidayTypesDTO().getHoltypeDays();
                    }
            }
        
        return 0l;
    }
    
 

    private int getSelectedTypeDaysAsInteger() {
            return new Integer(getSelectedTypeDays().toString());
    }

    public static void setAvailableVacTypes(Map<String, Long> availableVacTypes) {
        OfficialVacListBean.availableVacTypes = availableVacTypes;
    }

    public static Map<String, Long> getAvailableVacTypes() {
        return availableVacTypes;
    }
}
