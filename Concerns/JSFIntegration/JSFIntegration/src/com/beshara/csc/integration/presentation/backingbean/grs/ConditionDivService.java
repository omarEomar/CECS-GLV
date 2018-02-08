package com.beshara.csc.integration.presentation.backingbean.grs;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.gn.grs.business.client.ILinesClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.SearchConditionsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.grs.WrongGRSConditionDataException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import javax.faces.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.component.html.ext.HtmlCommandButton;


public class ConditionDivService extends LookUpBaseBean {
    public static final String BEAN_NAME = "conditionDivService";
    private String externalSearchMethodBinding;
    private List linesMenuList = new ArrayList();
    private IConditionsDTO conditionsDTO = GrsDTOFactory.createConditionsDTO();
    private SearchConditionsDTO searchConditionsDTO = new SearchConditionsDTO();
    // private String defaultValue = ISystemConslinesMenuListtant.VIRTUAL_VALUE.toString();
    private String selectedLineValue = "";
    private String resetButtonMethod;
    private String okButtonForCancelMethod;
    private String conditionDivName = "customDiv1";
    private String containerBeanName = "";
    private HtmlCommandButton okCommandButton = new HtmlCommandButton();

    private String appModuleCode;
    private Long status;

    //Added By Yassmine
    private String okButtonMethod;

    private boolean searchInCenter = false;
    // added for display grs condition lines with status (fail|success)
    private List<IResultDTO> linesResultList = new ArrayList<IResultDTO>();
    private Long conditionCode = null;
    private Long civilId = null;
    ///////////////
    private String backMethodName;

    private Long[] exculdedConditionList;
    
    private List<IBasicDTO> relatedConditionsList;

    public ConditionDivService() {
        setMyTableData(new ArrayList());

    }
    
    public static ConditionDivService getInstance(){
        return (ConditionDivService) JSFHelper.getValue(BEAN_NAME);
    }

    public void scrollerAction(ActionEvent ae) {
        if (getContainerBeanName() != null && !getContainerBeanName().equals(""))
            ((BaseBean)evaluateValueBinding(getContainerBeanName())).setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + conditionDivName + ");");
    }    
    
    public void viewRelatedConditions(){
        List<IBasicDTO> selectedDTOs = ((BaseBean)evaluateValueBinding(getContainerBeanName())).getSelectedDTOS();
        if (selectedDTOs != null && !selectedDTOs.isEmpty()) {
            Long tabrecSerial = (Long)evaluateValueBinding(getContainerBeanName()+".selectedDTOS[0].tabrecSerial");
            this.viewRelatedConditions(42811916L);
        }
    }
    
    public void viewRelatedConditions(Long relTabrecSerial){
        if(relTabrecSerial != null){
            List<Long> relTabrecSerialList = new ArrayList<Long>(1);
            relTabrecSerialList.add(relTabrecSerial);
            this.viewRelatedConditions(relTabrecSerialList);
        }
    }
    
    public void viewRelatedConditions(List<Long> relTabrecSerialList){
        if (relTabrecSerialList != null && !relTabrecSerialList.isEmpty()){
            try {
                relatedConditionsList = GrsClientFactory.getConditionRelationsClient().getAllByRelTabrecSerial(relTabrecSerialList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // called when presssing save button

    /**
     * Purpose: called @ action method that join condition from your bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     * reference : Sal Module (social raise ,increases,rewards,..)
     */
    public String saveCondition() {
        if (okButtonMethod != null) {
            executeMethodBinding(getOkButtonMethod(), null);
        }

        if (resetButtonMethod != null)
            executeMethodBinding(getResetButtonMethod(), null);

        this.cancelCondition();
        return "";
    }

    /**
     * Purpose:used to search @ condition data table @ join condition div
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public String searchConditionsAction() {
        getMyDataTable().setFirst(0);
        if (getExternalSearchMethodBinding() != null) {

            executeMethodBinding(getExternalSearchMethodBinding(), null);

        } else {

            generalSearch();

        }

        return null;
    }
    public void getAvailableConditionList(){
            try {
                IConditionsClient iConditionsClient = GrsClientFactory.getConditionsClient();
                SearchConditionsDTO dto = new SearchConditionsDTO();
                List temp = new ArrayList();
                //
                if (exculdedConditionList != null && exculdedConditionList.length > 0) {
                    dto.setExceptConditionsCode(exculdedConditionList);
                } else {

                    Long[] dumyExcludCondition = new Long[1];
                    dumyExcludCondition[0] = getVirtualLongValue();
                    dto.setExceptConditionsCode(dumyExcludCondition);
                }
                if (status != null) {
                    Long[] statusList = new Long[1];
                    statusList[0] = status;
                    dto.setSearchInStatus(statusList);
                }
                if (appModuleCode != null) {
                    dto.setAppModuleCode(appModuleCode);
                }
                if (searchInCenter)
                    temp = iConditionsClient.searchInCenter(dto);
                else
                    temp = iConditionsClient.search(dto);

                setMyTableData(temp);
            } catch (SharedApplicationException e) {
                setMyTableData(new ArrayList());
               
                e.printStackTrace();
            } catch (DataBaseException e) {
                setMyTableData(new ArrayList());
              
                e.printStackTrace();
            } catch (Exception e) {
                setMyTableData(new ArrayList());
               
                e.printStackTrace();
            }
        
        }
    public void generalSearch() {

        try {
            IConditionsClient iConditionsClient = GrsClientFactory.getConditionsClient();
            List temp = new ArrayList();
            //
            if (exculdedConditionList != null && exculdedConditionList.length > 0) {
                searchConditionsDTO.setExceptConditionsCode(exculdedConditionList);
            } else {

                Long[] dumyExcludCondition = new Long[1];
                dumyExcludCondition[0] = getVirtualLongValue();
                searchConditionsDTO.setExceptConditionsCode(dumyExcludCondition);
            }
            if (status != null) {
                Long[] statusList = new Long[1];
                statusList[0] = status;
                searchConditionsDTO.setSearchInStatus(statusList);
            }
            if (appModuleCode != null) {
                searchConditionsDTO.setAppModuleCode(appModuleCode);
            }
            if (searchInCenter)
                temp = iConditionsClient.searchInCenter(getSearchConditionsDTO());
            else
                temp = iConditionsClient.search(getSearchConditionsDTO());

            setMyTableData(temp);
        } catch (SharedApplicationException e) {
            setMyTableData(new ArrayList());
            //        clearSearchDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            setMyTableData(new ArrayList());
            //        clearSearchDTO();
            e.printStackTrace();
        } catch (Exception e) {
            setMyTableData(new ArrayList());
            //        clearSearchDTO();
            e.printStackTrace();
        }


        setSearchMode(true);


    }

    public String cancelCondition() {
        setSearchMode(false);
        clearSearchDTO();
        getAvailableConditionList();
        if (okButtonForCancelMethod != null) {
            executeMethodBinding(getOkButtonForCancelMethod(), null);
        }
        return "";
    }

    private void clearSearchDTO() {
        setSearchConditionsDTO(new SearchConditionsDTO());
        setSelectedLineValue(getVirtualConstValue());
        setMyTableData(new ArrayList());
    }

    public void setLinesMenuList(List linesMenuList) {
        this.linesMenuList = linesMenuList;
    }

    public List getLinesMenuList() {
        if (linesMenuList.size() == 0) {
            try {
                ILinesClient iLinesClient = GrsClientFactory.getLinesClient();
                System.out.println("ModuleCode = " + CSCSecurityInfoHelper.getModuleCode());
                if (CSCSecurityInfoHelper.getModuleCode() != null) {
                    Long moduleCode = CSCSecurityInfoHelper.getModuleCode();
                    linesMenuList = iLinesClient.getLinesByModule(moduleCode);
                } else {
                    linesMenuList = iLinesClient.getCodeName();
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
                linesMenuList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                linesMenuList = new ArrayList();
            }
        }
        return linesMenuList;
    }

    public void setConditionsDTO(IConditionsDTO cnditionsDTO) {
        this.conditionsDTO = cnditionsDTO;
    }

    public IConditionsDTO getConditionsDTO() {
        return conditionsDTO;
    }

    public void setSearchConditionsDTO(SearchConditionsDTO searchConditionsDTO) {
        this.searchConditionsDTO = searchConditionsDTO;
    }

    public SearchConditionsDTO getSearchConditionsDTO() {
        return searchConditionsDTO;
    }


    public void setSelectedLineValue(String selectedLineValue) {
        if (!selectedLineValue.equals(ISystemConstant.VIRTUAL_VALUE)) {
            getSearchConditionsDTO().setLineCode(Long.parseLong(selectedLineValue));
        }
        this.selectedLineValue = selectedLineValue;
    }

    public String getSelectedLineValue() {
        return selectedLineValue;
    }


    //    public void setConditionDivServiceDTO(BasicDTO conditionDivServiceDTO) {
    //        this.conditionDivServiceDTO = conditionDivServiceDTO;
    //    }
    //
    //    public BasicDTO getConditionDivServiceDTO() {
    //        return conditionDivServiceDTO;
    //    }

    public void setOkButtonMethod(String okButtonMethod) {
        this.okButtonMethod = okButtonMethod;
    }

    public String getOkButtonMethod() {
        return okButtonMethod;
    }

    public void setResetButtonMethod(String resetButtonMethod) {
        this.resetButtonMethod = resetButtonMethod;
    }

    public String getResetButtonMethod() {
        return resetButtonMethod;
    }

    public void setOkCommandButton(HtmlCommandButton okCommandButton) {
        this.okCommandButton = okCommandButton;
    }

    public HtmlCommandButton getOkCommandButton() {
        return okCommandButton;
    }

    /**
     * Purpose:called if u wants to reinitialize div for each open
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  24-11-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void reInitialize() {
        cancelCondition();
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0)
            getSelectedDTOS().clear();
    }

    public void setContainerBeanName(String containerBeanName) {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName() {
        return containerBeanName;
    }

    public void setExternalSearchMethodBinding(String externalSearchMethodBinding) {
        this.externalSearchMethodBinding = externalSearchMethodBinding;
    }

    public String getExternalSearchMethodBinding() {
        return externalSearchMethodBinding;
    }

    public void setSearchInCenter(boolean searchInCenter) {
        this.searchInCenter = searchInCenter;
    }

    public boolean isSearchInCenter() {
        return searchInCenter;
    }

    public void setLinesResultList(List<IResultDTO> linesResultList) {
        this.linesResultList = linesResultList;
    }

    public List<IResultDTO> getLinesResultList() {
        return linesResultList;
    }

    public void executeGRSCondition() {

        if (getConditionCode() != null && getCivilId() != null) {
            try {
                setLinesResultList(GrsClientFactory.getConditionsClient().executeGRSCondition(getConditionCode(),
                                                                                              getCivilId()));
            } catch (WrongGRSConditionDataException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConditionCode(Long conditionCode) {
        this.conditionCode = conditionCode;
    }

    public Long getConditionCode() {
        return conditionCode;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void backFromCondition() {
        if (backMethodName != null && !backMethodName.equals("")) {
            executeMethodBinding(backMethodName, null);
        } else
            cancelCondition();
    }

    public void setBackMethodName(String backMethodName) {
        this.backMethodName = backMethodName;
    }

    public String getBackMethodName() {
        return backMethodName;
    }

    public void setAppModuleCode(String appModuleCode) {
        this.appModuleCode = appModuleCode;
    }

    public String getAppModuleCode() {
        return appModuleCode;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setExculdedConditionList(Long[] exculdedConditionList) {
        this.exculdedConditionList = exculdedConditionList;
    }

    public Long[] getExculdedConditionList() {
        return exculdedConditionList;
    }

    public void setOkButtonForCancelMethod(String okButtonForCancelMethod) {
        this.okButtonForCancelMethod = okButtonForCancelMethod;
    }

    public String getOkButtonForCancelMethod() {
        return okButtonForCancelMethod;
    }

    public int getRelatedConditionsListSize() {
        if(relatedConditionsList != null){
            return relatedConditionsList.size();
        }
        return 0;
    }

    public void setRelatedConditionsList(List<IBasicDTO> relatedConditionsList) {
        this.relatedConditionsList = relatedConditionsList;
    }

    public List<IBasicDTO> getRelatedConditionsList() {
        return relatedConditionsList;
    }
}
