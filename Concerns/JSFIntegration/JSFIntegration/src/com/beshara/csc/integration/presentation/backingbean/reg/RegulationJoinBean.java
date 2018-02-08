package com.beshara.csc.integration.presentation.backingbean.reg;

import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


/**
 * Purpose: Bean that build join regulation div  and handle saving it 
 * Creation/Modification History :
 * 1.1 - Developer Name: Nora Ismail
 * 1.2 - Date:   14-10-2008
 * 1.3 - Creation/Modification:Creation      
 * 1.4-  Description: 
 */  
public class RegulationJoinBean extends LookUpBaseBean {

    private IRegulationSearchDTO  regulationSearchDTO=RegDTOFactory.createRegulationSearchDTO();   //new RegulationSearchDTO();
    // object that will be saved when user press ok @ add reg div
    private IModuleRelationsDTO moduleRelationsDTO=null;
    // respresents list of combo boxes that exist at add reg div 
    private List regTypesList=new ArrayList();
    private List yearsList=new ArrayList();
    private List decisionMakerList=new ArrayList();
    private List subjectList=new ArrayList();
    
    // this method will be called after save method in div to reset or reintialize before return to calling use-case
    private String resetButtonMethod;
    
    //Added By Yassmine ,called before saving moduleRelationsDTO method 
    private String initializeBeforeSaveMethod;
    private String backBeanNameFrom;
    
    //part of display regulations
    private Long tabRecSerial;
    private boolean showDetailDiv=false;
    private List regulationDataList = new ArrayList();
    private Long searchStatusValue=ISystemConstant.REGULATION_STATUS_VALID;
    private boolean viewInCenter;
    private String saveButtonAction = "";
    private String backMethodName;

    public RegulationJoinBean() {
        setMyTableData(new ArrayList());
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
        try
            {
                if( regTypesList == null || (regTypesList.size() == 0) )
                    regTypesList=RegClientFactory.getTypesClient().getCodeName(ISystemConstant.REGULATION_VALIDITY_REGULATION);
            }catch (DataBaseException e) {
                regTypesList=new ArrayList();
                e.printStackTrace();
            }catch (Exception e) {
                 regTypesList=new ArrayList();
                 e.printStackTrace();
             }
        return regTypesList;
    }


//    public void setSelectedSalElementGuidesDTO(BasicDTO selectedSalElementGuidesDTO) {
//        this.selectedSalElementGuidesDTO = selectedSalElementGuidesDTO;
//    }
//
//    public BasicDTO getSelectedSalElementGuidesDTO() {
//        return selectedSalElementGuidesDTO;
//    }

//    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
//        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
//    }
//
//    public Long getItemSelectionRequiredValue() {
//        return itemSelectionRequiredValue;
//    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() {
    
     if(yearsList==null || (yearsList.size()==0)){
            try {
                yearsList=InfClientFactory.getYearsClient().getCodeName();
            } catch (DataBaseException e) {
                 yearsList=new ArrayList();
                 e.printStackTrace();
            }catch (Exception e) {
                  yearsList=new ArrayList();
                  e.printStackTrace();
            }
        }
        return yearsList;
    }

    public void setDecisionMakerList(List decisionMakerList) {
        this.decisionMakerList = decisionMakerList;
    }

    public List getDecisionMakerList() {
    
        if(decisionMakerList==null || (decisionMakerList.size()==0)){
            try {
                decisionMakerList=InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            } catch (DataBaseException e) {
              e.printStackTrace();
              decisionMakerList=new ArrayList();
            }catch (Exception e) {
              e.printStackTrace();
              decisionMakerList=new ArrayList();
          }
        }
        return decisionMakerList;
    }

    public void setSubjectList(List subjectList) {
        this.subjectList = subjectList;
    }

    public List getSubjectList() {
        try {
                if( subjectList==null || (subjectList.size()==0) )
                    subjectList=RegClientFactory.getSubjectsClient().getCodeName(ISystemConstant.REGULATION_VALIDITY_REGULATION);
        } catch (DataBaseException e) {
              e.printStackTrace();
              subjectList=new ArrayList();
        }catch (Exception e) {
                 e.printStackTrace();
                 subjectList=new ArrayList();
        }
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
    public void search()  {
    
        this.setSearchMode(true);
        regulationSearchDTO.setRegulationStatus(searchStatusValue);
        try {
            setMyTableData(RegClientFactory.getRegulationsClient().searchModuleValidRegulations(getRegulationSearchDTO()));
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
         if (getSelectedDTOS() != null)
             getSelectedDTOS().clear();
         if (getHighlightedDTOS() != null)
             getHighlightedDTOS().clear();
         this.repositionPage(0);
    }
    
    public void cancelSearch() throws DataBaseException {
       resetData();
       setSearchMode(false);

    }
    
    public void resetData(){
        regulationSearchDTO.setDateFrom(null);
        regulationSearchDTO.setDateTo(null);
        regulationSearchDTO.setTitle("");
        regulationSearchDTO.setNumber(null);
        regulationSearchDTO.setSubjectCode(getVirtualLongValue());
        regulationSearchDTO.setDecisionMakerType(getVirtualLongValue());
        regulationSearchDTO.setRegulationYear(getVirtualLongValue());
        regulationSearchDTO.setRegulationType(getVirtualLongValue());
        setMyTableData(new ArrayList());
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
    public void save(){
    
    if(saveButtonAction != null && !saveButtonAction.equals(""))
        executeMethodBinding(getSaveButtonAction(), null);
    else
        joinRegulation();
    }
    
    public void joinRegulation() {
        if(initializeBeforeSaveMethod != null){
            executeMethodBinding(getInitializeBeforeSaveMethod(), null);   
            
            if(moduleRelationsDTO!=null && getSelectedDTOS()!=null && getSelectedDTOS().size()>0 && getSelectedDTOS().get(0)!=null){
                try {
                    moduleRelationsDTO.setRegulationsDTO(getSelectedDTOS().get(0));
                    RegClientFactory.getModuleRelationsClient().add(moduleRelationsDTO);
                    getSharedUtil().setSuccessMsgValue("SuccessJoinRegulation");
                    getSelectedDTOS().clear();
                    /// NOTE :EZ , we need to handle exception with failure message
                }  
                catch (SharedApplicationException e) {
                    getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInJoinRegulation"); 
                } catch (DataBaseException e) {
                    if(e.getMessage() != null && !e.getMessage().equals("") &&e.getMessage().equals("PrimaryKeyDuplicated"))
                        getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","EntityExistsException_Join_Reg"); 
                     else
                    getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInJoinRegulation"); 
                }catch (Exception e) {
                    getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInJoinRegulation"); 
                }
                try {
                    cancelSearch();
                } catch (DataBaseException e) {
                    e.printStackTrace();
                }
            }
            else
                getSharedUtil().setErrMsgValue("FailureInUpdateForRegulation");
        }
        else
        getSharedUtil().setErrMsgValue("FailureInUpdateForRegulation");  
        
        if(resetButtonMethod != null)
            executeMethodBinding(getResetButtonMethod(), null);
    }
  
    public void cancelAdd() {
       resetData();
    }

    public void setBackBeanNameFrom(String backBeanNameFrom) {
        this.backBeanNameFrom = backBeanNameFrom;
    }

    public String getBackBeanNameFrom() {
        return backBeanNameFrom;
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
  /**
   * Purpose:called to make join reg div visible when user navigate to other pages @ it 
   * Creation/Modification History :
   * 1.1 - Developer Name: Nora Ismail
   * 1.2 - Date:   14-10-2008
   * 1.3 - Creation/Modification:Creation      
   * 1.4-  Description: 
   */  
    public void scrollerAction(ActionEvent event) {
        event = null; // unused
        if(backBeanNameFrom!=null && !backBeanNameFrom.equals(""))
           ((BaseBean)evaluateValueBinding(backBeanNameFrom)).setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.masterDetailDiv);");
        
    }

    public void setTabRecSerial(Long tabRecSerial) {
        this.tabRecSerial = tabRecSerial;
    }

    public Long getTabRecSerial() {
        return tabRecSerial;
    }
    
    public void displayRegulationList(){
    
        if (tabRecSerial != null)
            try {
                if(viewInCenter){
                    regulationDataList = RegClientFactory.getModuleRelationsClient().getRegualationByTabRecSerialInCenter(tabRecSerial);
                } else {
                    regulationDataList = RegClientFactory.getModuleRelationsClient().getRegualationByTabRecSerial(tabRecSerial);
                }
                
                if(regulationDataList !=null && regulationDataList.size() >0){
                    setShowDetailDiv(true);
                    if(backBeanNameFrom!=null && !backBeanNameFrom.equals("") )
                        ((BaseBean)evaluateValueBinding(backBeanNameFrom)).setJavaScriptCaller(" changeVisibilityDiv(window.blocker,window.masterDetailDiv); setFocusOnlyOnElement('BackButtonMasterDetailDiv_detail');");
                }
                else
                    getSharedUtil().handleException(new Exception(), "com.beshara.jsfbase.csc.msgresources.global", "NO_RELATED_REGULATIONS_MSG");
                
            } catch (Exception e) {
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.global", "NO_RELATED_REGULATIONS_MSG");
            }
        else
          getSharedUtil().handleException(new Exception(), "com.beshara.jsfbase.csc.msgresources.global", "NO_RELATED_REGULATIONS_MSG");
    }
    public void backFromDisplayDiv(){
        if (backMethodName != null && !backMethodName.equals("")) {
            executeMethodBinding(backMethodName, null);
        }
        setShowDetailDiv(false);;
    }
    public void setRegulationDataList(List regulationDataList) {
        this.regulationDataList = regulationDataList;
    }

    public List getRegulationDataList() {
        return regulationDataList;
    }

    public void setShowDetailDiv(boolean showDetailDiv) {
        this.showDetailDiv = showDetailDiv;
    }

    public boolean isShowDetailDiv() {
        return showDetailDiv;
    }


    public void setSearchStatusValue(Long searchStatusValue) {
        this.searchStatusValue = searchStatusValue;
    }

    public Long getSearchStatusValue() {
        return searchStatusValue;
    }

    public void setViewInCenter(boolean viewInCenter) {
        this.viewInCenter = viewInCenter;
    }

    public boolean isViewInCenter() {
        return viewInCenter;
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
}
