package com.beshara.csc.nl.reg.integration.presentation.backingbean.joinregulation;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.base.security.ICategoryInfo;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.shared.IntegrationBaseBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * Purpose: Bean that build join regulation div  and handle saving it
 * Creation/Modification History :
 * 1.1 - Developer Name: Nora Ismail
 * 1.2 - Date:   14-10-2008
 * 1.3 - Creation/Modification:Creation
 * 1.4-  Description:
 */
public class RegulationJoinBean extends IntegrationBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private IRegulationSearchDTO regulationSearchDTO =
        RegDTOFactory.createRegulationSearchDTO(); //new RegulationSearchDTO();
    // object that will be saved when user press ok @ add reg div
    private IModuleRelationsDTO moduleRelationsDTO = null;
    // respresents list of combo boxes that exist at add reg div
    private List regTypesList = new ArrayList();
    private List yearsList = new ArrayList();
    private List decisionMakerList = new ArrayList();
    private List subjectList = new ArrayList();

    private Boolean filterPnlCollapsed = false;
    
    private String resetButtonMethod;
    private String initializeBeforeSaveMethod;

    private Long searchStatusValue = ISystemConstant.REGULATION_STATUS_VALID;
    private String saveButtonAction = "";
    private String backMethodName;
    private Long virtualLongValue = ISystemConstant.VIRTUAL_VALUE.longValue();
    // added by A.Nour story CSC-20023 to enable single & multiple selection
    private boolean singleSelection = true;
    private boolean checkAllFlag;
    private Integer joinRegDevTableRowsCount = new Integer(5); 

    public void selectedCheckboxs(ActionEvent event) throws Exception {

                System.out.println("selectedCheckboxs only one time");

        try {

            Set<IBasicDTO> selectedSet = new HashSet();
            selectedSet.addAll(getSelectedDTOS());

            IClientDTO selected = (IClientDTO)this.getAvailableDataTable().getRowData();

            if (selected.getChecked()) {

                try {
                    selectedSet.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                selected.setChecked(Boolean.TRUE);

                for (IBasicDTO item : selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }

                selected.setChecked(Boolean.FALSE);

            }

            getSelectedDTOS().clear();
            getSelectedDTOS().addAll(selectedSet);
            System.out.println("RegulationJoinBean : selectedCheckboxs() : getSelectedDTOS().size() = "+getSelectedDTOS().size());
        } catch (Exception e) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }

    }

    public void selectedCheckboxsAll(ActionEvent event) throws Exception {

        try {

            Set<IBasicDTO> selectedSet = new HashSet();
            selectedSet.addAll(getSelectedDTOS());

            /*Integer rowsCountPerPage =
                (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());

            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");

            }*/

            int first = this.getAvailableDataTable().getFirst();

            for (int j = first; j < first + joinRegDevTableRowsCount.intValue(); j++) {

                this.getAvailableDataTable().setRowIndex(j);

                if (!this.getAvailableDataTable().isRowAvailable()) {
                    break;
                }

                IBasicDTO selected = (IBasicDTO)this.getAvailableDataTable().getRowData();

                if (this.isCheckAllFlag()) {

                    try {
                        selectedSet.add(selected);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    for (IBasicDTO item : selectedSet) {
                        if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                            selectedSet.remove(item);
                            break;
                        }
                    }

                }

            }

            getSelectedDTOS().clear();
            getSelectedDTOS().addAll(selectedSet);
            System.out.println("RegulationJoinBean : selectedCheckboxsAll() : getSelectedDTOS().size() = "+getSelectedDTOS().size());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public RegulationJoinBean() {
        setAvailableDetails(new ArrayList());
    }

    public void setRegulationSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
        this.regulationSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getRegulationSearchDTO() {
        return regulationSearchDTO;
    }

    public void setRegTypesList(List regTypesList) {
        this.regTypesList = regTypesList;
    }

    public List getRegTypesList() {
        return regTypesList;
    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() {
        return yearsList;
    }

    public void setDecisionMakerList(List decisionMakerList) {
        this.decisionMakerList = decisionMakerList;
    }

    public List getDecisionMakerList() {
        return decisionMakerList;
    }

    public void setSubjectList(List subjectList) {
        this.subjectList = subjectList;
    }

    public List getSubjectList() {
        return subjectList;
    }

    /**
     * Purpose:used to search @ regulation data table @ join reg div
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void search() {
        getAvailableDataTable().setFirst(0);
        setIntgSearchMode(true);
        regulationSearchDTO.setAppModuleCode(CSCSecurityInfoHelper.getModuleCode());
        regulationSearchDTO.setRegulationStatus(searchStatusValue);
        try {
            List list = RegClientFactory.getRegulationsClient().searchModuleValidRegulations(getRegulationSearchDTO());
            if (list != null) {
                setAvailableDetails(list);
            } else {
                setAvailableDetails(new ArrayList());
            }
        } catch (SharedApplicationException e) {
            setAvailableDetails(new ArrayList());
            e.printStackTrace();
        } catch (DataBaseException e) {
            setAvailableDetails(new ArrayList());
            e.printStackTrace();
        } catch (Exception e) {
            setAvailableDetails(new ArrayList());
            e.printStackTrace();
        }
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
    }

    public void cancelSearch() throws DataBaseException {
        resetData();
        this.search();
        setIntgSearchMode(false);
    }

    public void resetData() {
        super.resetData();
        regulationSearchDTO.setDateFrom(null);
        regulationSearchDTO.setDateTo(null);
        regulationSearchDTO.setTitle("");
        regulationSearchDTO.setNumber(null);
        regulationSearchDTO.setSubjectCode(getVirtualLongValue());
        regulationSearchDTO.setDecisionMakerType(getVirtualLongValue());
        regulationSearchDTO.setRegulationYear(getVirtualLongValue());
        regulationSearchDTO.setRegulationType(getVirtualLongValue());
        setRegTypesList(new ArrayList());
        setDecisionMakerList(new ArrayList());
        try {
            List<String> minCodes = new ArrayList<String>();
            for (ICategoryInfo categoryInfo : CSCSecurityInfoHelper.getCategories()) {
                List<IMinistryInfo> list = (List<IMinistryInfo>)categoryInfo.getMinistries();
                for (IMinistryInfo ministryInfo : list) {
                    minCodes.add(String.valueOf(ministryInfo.getCode()));
                }

            }
            regTypesList =
                    RegClientFactory.getTypesClient().getTypesList(ISystemConstant.REGULATION_VALIDITY_REGULATION,
                                                                   minCodes, null);

        } catch (DataBaseException e) {
            regTypesList = new ArrayList();
            e.printStackTrace();
        } catch (Exception e) {
            regTypesList = new ArrayList();
            e.printStackTrace();
        }
        try {
            decisionMakerList = InfClientFactory.getDecisionMakerTypesClient().getCodeName();
        } catch (DataBaseException e) {
            e.printStackTrace();
            decisionMakerList = new ArrayList();
        } catch (Exception e) {
            e.printStackTrace();
            decisionMakerList = new ArrayList();
        }
    }

    /**
     * Purpose:called when user press ok button to save selected reg row
     * u must initialize moduleRelationsDTO @ your bean by setting initializeBeforeSaveMethod or any method
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     * reference : Sal Module (social raise ,increases,rewards,..)
     */
    public void save() {

        if (saveButtonAction != null && !saveButtonAction.equals(""))
            executeMethodBinding(getSaveButtonAction(), null);
        else
            joinRegulation();
        
        checkAllFlag = false;
    }

    public void joinRegulation() {
        if (initializeBeforeSaveMethod != null) {
            executeMethodBinding(getInitializeBeforeSaveMethod(), null);

            if (moduleRelationsDTO != null && getSelectedDTOS() != null && getSelectedDTOS().size() > 0 &&
                getSelectedDTOS().get(0) != null) {
                    boolean hasPrimaryKeyDuplicatedExc = false;
                    boolean hasExc = false;
                    for (int i = 0; i < getSelectedDTOS().size() ; i++) {
                        try {
                            moduleRelationsDTO.setRegulationsDTO(getSelectedDTOS().get(i));
                            RegClientFactory.getModuleRelationsClient().add(moduleRelationsDTO);
                        } catch (SharedApplicationException e) {
                            hasExc = true;
                        } catch (DataBaseException e) {
                            if (e.getMessage() != null && !e.getMessage().equals("") &&
                                e.getMessage().equals("PrimaryKeyDuplicated") )hasPrimaryKeyDuplicatedExc= true;
                            else
                                hasExc = true;
                        } catch (Exception e) {
                            hasExc = true;
                        }
                    }

//                    moduleRelationsDTO.setRegulationsDTO(getSelectedDTOS().get(0));
//                    RegClientFactory.getModuleRelationsClient().add(moduleRelationsDTO);
//                    getSharedUtil().setSuccessMsgValue("SuccessJoinRegulation");
//                    getSelectedDTOS().clear();
                    if(hasPrimaryKeyDuplicatedExc){
                        if(getSelectedDTOS().size() == 1)
                            getSharedUtil().handleException(null, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                        "EntityExistsException_Join_Reg");
                        else{getSharedUtil().handleException(null, "com.beshara.csc.nl.reg.integration.presentation.resources.integration",
                                                        "some_regs_joined_before_error");}
                    }
                    else if(hasExc){
                        getSharedUtil().handleException(null, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                        "FailureInJoinRegulation");    
                    }else {
                        getSharedUtil().setSuccessMsgValue("SuccessJoinRegulation");   
                    }
                getSelectedDTOS().clear();
                try {
                    cancelSearch();
                } catch (DataBaseException e) {
                    e.printStackTrace();
                }
            } else
                getSharedUtil().setErrMsgValue("FailureInUpdateForRegulation");
        } else
            getSharedUtil().setErrMsgValue("FailureInUpdateForRegulation");

        if (resetButtonMethod != null)
            executeMethodBinding(getResetButtonMethod(), null);
    }

    public void cancelAdd() {
        resetData();
    }

    public void setInitializeBeforeSaveMethod(String okButtonMethod) {
        this.initializeBeforeSaveMethod = okButtonMethod;
    }

    public String getInitializeBeforeSaveMethod() {
        return initializeBeforeSaveMethod;
    }

    public void setModuleRelationsDTO(IModuleRelationsDTO moduleRelationsDTO) {
        this.moduleRelationsDTO = moduleRelationsDTO;
    }

    public IModuleRelationsDTO getModuleRelationsDTO() {
        return moduleRelationsDTO;
    }

    public void setResetButtonMethod(String resetButtonMethod) {
        this.resetButtonMethod = resetButtonMethod;
    }

    public String getResetButtonMethod() {
        return resetButtonMethod;
    }

    public void closeDiv() {
        try {
            cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    /*
     * Date Created: 11-9-2014
     * Purpose: Make a filter to change decision maker Type dropdown
     * Created By: H.Mounir
     */

    public void regTypeChanged(ActionEvent event) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<regTypeChanged>>>>>>>>>>>>>>>>>>>");
        try {
            List<String> minCodes = new ArrayList<String>();
            for (ICategoryInfo categoryInfo : CSCSecurityInfoHelper.getCategories()) {
                List<IMinistryInfo> list = (List<IMinistryInfo>)categoryInfo.getMinistries();
                for (IMinistryInfo ministryInfo : list) {
                    minCodes.add(String.valueOf(ministryInfo.getCode()));
                }

            }
            Long regType = getRegulationSearchDTO().getRegulationType();
            if (regType != null) {
                setDecisionMakerList(new ArrayList());
                decisionMakerList =
                        InfClientFactory.getDecisionMakerTypesClient().getDecisionMakerTypesByRecType(getRegulationSearchDTO().getRegulationType());
            } else {
                setDecisionMakerList(new ArrayList());
                decisionMakerList = InfClientFactory.getDecisionMakerTypesClient().getCodeName();
                regTypesList =
                        RegClientFactory.getTypesClient().getTypesList(ISystemConstant.REGULATION_VALIDITY_REGULATION,
                                                                       minCodes, null);
                //getRegulationSearchDTO().setDecisionMakerType(getVirtualLongValue());
                getRegulationSearchDTO().setRegulationType(getVirtualLongValue());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            decisionMakerList = new ArrayList();
        } catch (Exception e) {
            e.printStackTrace();
            decisionMakerList = new ArrayList();
        }
    }

    /*
     * Date Created: 11-9-2014
     * Purpose: Make a filter to change regulation Type dropdown
     * Created By: H.Mounir
     */

    public void decisionMakerTypeChanged(ActionEvent event) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<decisionMakerTypeChanged>>>>>>>>>>>>>>>>>>>");
        try {
            List<String> minCodes = new ArrayList<String>();
            for (ICategoryInfo categoryInfo : CSCSecurityInfoHelper.getCategories()) {
                List<IMinistryInfo> list = (List<IMinistryInfo>)categoryInfo.getMinistries();
                for (IMinistryInfo ministryInfo : list) {
                    minCodes.add(String.valueOf(ministryInfo.getCode()));
                }

            }
            Long decType = getRegulationSearchDTO().getDecisionMakerType();
            if (decType != null) {
                setRegTypesList(new ArrayList());
                regTypesList =
                        RegClientFactory.getTypesClient().getTypesList(ISystemConstant.REGULATION_VALIDITY_REGULATION,
                                                                       minCodes,
                                                                       getRegulationSearchDTO().getDecisionMakerType());
            } else {
                setRegTypesList(new ArrayList());
                regTypesList =
                        RegClientFactory.getTypesClient().getTypesList(ISystemConstant.REGULATION_VALIDITY_REGULATION,
                                                                       minCodes, null);
                decisionMakerList = InfClientFactory.getDecisionMakerTypesClient().getCodeName();
                getRegulationSearchDTO().setDecisionMakerType(getVirtualLongValue());
                //getRegulationSearchDTO().setRegulationType(getVirtualLongValue());
            }
        } catch (DataBaseException e) {
            regTypesList = new ArrayList();
            e.printStackTrace();
        } catch (Exception e) {
            regTypesList = new ArrayList();
            e.printStackTrace();
        }
    }

    /*
     * Date Created: 11-9-2014
     * Purpose: make a function to open div to inialialize dropdown lists
     * Created By: H.Mounir
     */

    public void openRegJoinDiv() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<div Opened>>>>>>>>>>>>>>>>>>>");
        this.search();
        setIntgSearchMode(false);
        checkAllFlag = false;
        try {
            List<String> minCodes = new ArrayList<String>();
            for (ICategoryInfo categoryInfo : CSCSecurityInfoHelper.getCategories()) {
                List<IMinistryInfo> list = (List<IMinistryInfo>)categoryInfo.getMinistries();
                for (IMinistryInfo ministryInfo : list) {
                    minCodes.add(String.valueOf(ministryInfo.getCode()));
                }

            }
            if (regTypesList == null || (regTypesList.size() == 0))
                regTypesList =
                        RegClientFactory.getTypesClient().getTypesList(ISystemConstant.REGULATION_VALIDITY_REGULATION,
                                                                       minCodes, null);
        } catch (DataBaseException e) {
            regTypesList = new ArrayList();
            e.printStackTrace();
        } catch (Exception e) {
            regTypesList = new ArrayList();
            e.printStackTrace();
        }
        try {
            if (subjectList == null || (subjectList.size() == 0))
                subjectList =
                        RegClientFactory.getSubjectsClient().getCodeNameByModuleCode(ISystemConstant.REGULATION_VALIDITY_REGULATION,
                                                                                     CSCSecurityInfoHelper.getModuleCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
            subjectList = new ArrayList();
        } catch (Exception e) {
            e.printStackTrace();
            subjectList = new ArrayList();
        }
        if (decisionMakerList == null || (decisionMakerList.size() == 0)) {
            try {
                decisionMakerList = InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                decisionMakerList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                decisionMakerList = new ArrayList();
            }
        }
        if (yearsList == null || (yearsList.size() == 0)) {
            try {
                yearsList = InfClientFactory.getYearsClient().getCodeName();
            } catch (DataBaseException e) {
                yearsList = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                yearsList = new ArrayList();
                e.printStackTrace();
            }
        }
    }


    public void setSearchStatusValue(Long searchStatusValue) {
        this.searchStatusValue = searchStatusValue;
    }

    public Long getSearchStatusValue() {
        return searchStatusValue;
    }

    public void setSaveButtonAction(String saveButtonAction) {
        this.saveButtonAction = saveButtonAction;
    }

    public String getSaveButtonAction() {
        return saveButtonAction;
    }

    public void setBackMethodName(String backMethodName) {
        this.backMethodName = backMethodName;
    }

    public String getBackMethodName() {
        return backMethodName;
    }

    public void setVirtualLongValue(Long virtualLongValue) {
        this.virtualLongValue = virtualLongValue;
    }

    public Long getVirtualLongValue() {
        return virtualLongValue;
    }

    public void scrollerAction(ActionEvent ae) {
        super.scrollerAction(ae);
        String javaScript = super.getContainerBean().getJavaScriptCaller();
        javaScript = javaScript.concat("handleRegIntgSearchBtn();");
        super.getContainerBean().setJavaScriptCaller(javaScript);
        System.out.println(javaScript);
    }
    /* to save state of filter panel when moving through pagging by Aly Nour 28/10/2014*/
    public void showHideFilterPnl(){
        if(filterPnlCollapsed){
            filterPnlCollapsed = false;
}
        else {
            filterPnlCollapsed = true;
        }
    }
    
    public void setFilterPnlCollapsed(Boolean filterPnlCollapsed) {
        this.filterPnlCollapsed = filterPnlCollapsed;
    }

    public Boolean getFilterPnlCollapsed() {
        return filterPnlCollapsed;
    }

    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    public void setCheckAllFlag(boolean checkAllFlag) {
        this.checkAllFlag = checkAllFlag;
    }

    public boolean isCheckAllFlag() {
        return checkAllFlag;
    }

    public void setJoinRegDevTableRowsCount(Integer joinRegDevTableRowsCount) {
        this.joinRegDevTableRowsCount = joinRegDevTableRowsCount;
    }

    public Integer getJoinRegDevTableRowsCount() {
        return joinRegDevTableRowsCount;
    }
}
