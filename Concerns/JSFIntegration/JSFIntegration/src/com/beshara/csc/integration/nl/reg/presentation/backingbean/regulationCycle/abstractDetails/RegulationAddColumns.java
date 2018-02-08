package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDedignTablesDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabColumnsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.REGDesignTabColumnsEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class RegulationAddColumns extends ManyToManyDetailsMaintain {
    
    IREGDesignTabColumnsDTO rEGDesignTabColumnsDTO;
    
    private List<IREGDesignTabColumnsDTO> dataTableValue;
    private int disableCheckAllFlag = 0;
    private IREGDesignTabColumnsDTO preEditDTO;
    private IREGDesignTabColumnsDTO editDTO;
    private List previosExtraList = new ArrayList();

    private int index;
    
    private Long STRING_DATATYPE = new Long(1);
    private Long INTEGER_DATATYPE = new Long(2); 
    private List<IREGDesignTabColumnsDTO> backupCurrentDetails;
    private List nonDeletedColumns=new ArrayList();

    public RegulationAddColumns() {
        setPageDTO(RegDTOFactory.createREGDesignTabColumnsDTO()); //set this the page dto
         setEditDTO(RegDTOFactory.createREGDesignTabColumnsDTO());
         setPreEditDTO(RegDTOFactory.createREGDesignTabColumnsDTO());
        super.setSelectedPageDTO(RegDTOFactory.createREGDesignTabColumnsDTO());
        setDivMainContentMany("divREGMainContentManyAddColumns");
        setDivMainContent("divREGMainContentManyAddColumns");
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        app.setShowLookupEdit(true);
        app.setShowLookupAdd(true);
        app.setShowWizardBar(false);
        app.setShowNavigation(true);
        app.setShowContent1(true);
        app.setShowsteps(false);
        app.setShowDelConfirm(true);
        return app;
    }
    
    public void add(){
            getPageDTO().setStatusFlag(new Long(0));
            getPageDTO().setCode(new REGDesignTabColumnsEntityKey(new Long(0),new Long(0),new Long(0),new Long(0),getMinId()));
            getCurrentDetails().add(getPageDTO());
            setPageDTO(RegDTOFactory.createREGDesignTabColumnsDTO());
            unCheck();
    }
    
    
    public void saveAndNew() throws DataBaseException {
       this.add();
    } 
    
    
    public void EditChange()
       {           
           resetSelection();
           try {
               setEditDTO((IREGDesignTabColumnsDTO)SharedUtilBean.deepCopyObject(getPreEditDTO()));
               ((IREGDesignTabColumnsDTO)getCurrentDisplayDetails().get(index)).setCode(getEditDTO().getCode());
               ((IREGDesignTabColumnsDTO)getCurrentDisplayDetails().get(index)).setName(getEditDTO().getName());
               ((IREGDesignTabColumnsDTO)getCurrentDisplayDetails().get(index)).setDestabcolumnType(getEditDTO().getDestabcolumnType());
           } catch (Exception e) {
               // TODO
           }           
       }
       

       
       public void showEditDiv() {

        try {
            editDTO = (IREGDesignTabColumnsDTO)getCurrentDisplayDetails().get(index);
            setPreEditDTO((IREGDesignTabColumnsDTO)SharedUtilBean.deepCopyObject(editDTO));
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
               
        }
        
    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
           IREGDesignTabColumnsDTO rEGDesignTabColumnsDTO = (IREGDesignTabColumnsDTO)dto;
           return rEGDesignTabColumnsDTO;
    }
   
  
    public void delete() {
           boolean hasRelatedRows=false;
           if (getCurrentDetails() == null)
                setCurrentDetails(new ArrayList<IBasicDTO>());
           
           if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() > 0) {
                  
                   for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                        IREGDesignTabColumnsDTO selected = (IREGDesignTabColumnsDTO)getSelectedCurrentDetails().get(i);
                        if(selected.getRegDesignTabRecordsDTOList() !=null && selected.getRegDesignTabRecordsDTOList().size()>0)
                                hasRelatedRows=true;
                       
                        if(hasRelatedRows){
                                nonDeletedColumns.add(selected);
                        }          
                        else{
                            if (selected.getStatusFlag() == null) {
                            selected.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                            getSelectedCurrentDetails().remove(i);
                            i--;
                            }
                            if (selected.getStatusFlag().longValue() == ISystemConstant.ADDED_ITEM.longValue()) {
                            getCurrentDetails().remove(selected);
                            getSelectedCurrentDetails().remove(i);
                            i--;
                            }
                    }
                   }
                   this.restoreDetailsTablePosition(this.getCurrentDataTable(),this.getCurrentDetails());
                   this.resetSelection();
                   
               
               if(hasRelatedRows){ 
                   this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
               }   
           }
         
         
       }
    
    
    public Long getMinId() {

        Long maxId = new Long(-1);

        if (this.getCurrentDetails() == null || 
            this.getCurrentDetails().size() == 0) {
            return maxId;
        }

        for (IBasicDTO dto: this.getCurrentDetails()) {
            Long id = 
                ((REGDesignTabColumnsEntityKey)dto.getCode()).getDestabcolumnCode();
            if (id < maxId)
                maxId = id;

        }

        return maxId - 1;
    }
    
    public void selectedCurrent(ActionEvent event) throws Exception {
        super.selectedCurrent(event);
        dataTableValue = (List<IREGDesignTabColumnsDTO>)getCurrentDataTable().getValue();
        if (dataTableValue.get(getCurrentDataTable().getRowIndex()).getChecked()) {
            index = getCurrentDataTable().getRowIndex();
        }
    }
    
    public String finish() {
        resetSelection();
        getRegulationAttachmentsMaintain().resetSelection();
        this.getWizardBar().setCurrentStep("attachadd");
        return goToStep("attachadd");
    }
    
    
    public String back(){
    
////    for(IREGDesignTabColumnsDTO rEGDesignTabColumnsTempDTO : (ArrayList<IREGDesignTabColumnsDTO>)getCurrentDetails()){
//    int arraySize = getCurrentDetails().size();
//    for(int index = 0; index<arraySize;index++){
//        IREGDesignTabColumnsDTO rEGDesignTabColumnsTempDTO = (IREGDesignTabColumnsDTO)(getCurrentDetails().get(index));
//        if(rEGDesignTabColumnsTempDTO.getStatusFlag() != null && rEGDesignTabColumnsTempDTO.getStatusFlag().equals(new Long(0)))
//        {
//            getCurrentDetails().remove(rEGDesignTabColumnsTempDTO);
////            arraySize=arraySize-1;
//        }
//        else if(rEGDesignTabColumnsTempDTO.getStatusFlag() != null && rEGDesignTabColumnsTempDTO.getStatusFlag().equals(new Long(1)))
//        {
//            rEGDesignTabColumnsTempDTO.setStatusFlag(null);
//            getCurrentDisplayDetails().add(rEGDesignTabColumnsTempDTO);
//        }
//    }
        try {
//            setCurrentDetails(null);
            ((IREGDedignTablesDTO)getRegulationAttachmentsMaintain().getSelectedCurrentDetails().get(0)).setRegDesignTabColumnsDTOList(new ArrayList());
            ((IREGDedignTablesDTO)getRegulationAttachmentsMaintain().getSelectedCurrentDetails().get(0)).setRegDesignTabColumnsDTOList((List)SharedUtilBean.deepCopyObject(getBackupCurrentDetails()));
            reSetCurrentDetails(new ArrayList<IBasicDTO>());
            setCurrentDetails((List)SharedUtilBean.deepCopyObject(getBackupCurrentDetails()));
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
        resetSelection();
        getRegulationAttachmentsMaintain().resetSelection();
        this.getWizardBar().setCurrentStep("attachadd");   
        return goToStep("attachadd");
    }
    
    public void reSetCurrentDetails(List<IBasicDTO> dataList) {
        List<IBasicDTO> deletedItems = new ArrayList<IBasicDTO>();
        for (IBasicDTO dto: getCurrentDetails()) {
            if (dto.getStatusFlag() != null && 
                dto.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {

                deletedItems.add(dto);
            }
        }
        setCurrentDetails(dataList);
        for (IBasicDTO dto: deletedItems) {
            getCurrentDetails().add(dto);
        }
    }
      

 public String goToStep(String targetStep){

     String nCase = getNavigationCase(targetStep);
     getRegulationMaintainBean().updateStepDependancies(getRegulationMaintainBean().getCurrentStep());
     if (getRegulationMaintainBean().checkStepRelevants(targetStep)) {

         getRegulationMaintainBean().setNextNavigationCase(getNextNavigationCase(targetStep));
         getRegulationMaintainBean().setPreviousNavigationCase(getNavigationCase(getRegulationMaintainBean().getCurrentStep()));
         getRegulationMaintainBean().setValidated(getRegulationMaintainBean().getCurrentStep());
         getRegulationMaintainBean().setVisited(getRegulationMaintainBean().getCurrentStep());
         setVisited(targetStep);
         
         if(getRegulationMaintainBean().getFinishButtonOverride(targetStep)==1)
         {
             getRegulationMaintainBean().setRenderFinish(getRegulationMaintainBean().getFinishButtonStatus(targetStep));
         }else if(getRegulationMaintainBean().getFinishButtonOverride(targetStep)==2)
         {
         getRegulationMaintainBean().setRenderFinish(false);
         }else{
             getRegulationMaintainBean().setRenderFinish(true);
         }
         
         
         
         
         getRegulationMaintainBean().setCurrentStep(targetStep);
         getRegulationMaintainBean().getWizardBar().setCurrentStep(targetStep);

         return nCase;
     }


     return null;

 }
 
    public void unCheck() {
        super.unCheck();
        setCheckAllFlag(false);
    }

    public void setREGDesignTabColumnsDTO(IREGDesignTabColumnsDTO rEGDesignTabColumnsDTO) {
        this.rEGDesignTabColumnsDTO = rEGDesignTabColumnsDTO;
    }

    public IREGDesignTabColumnsDTO getREGDesignTabColumnsDTO() {
        return rEGDesignTabColumnsDTO;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setDataTableValue(List<IREGDesignTabColumnsDTO> dataTableValue) {
        this.dataTableValue = dataTableValue;
    }

    public List<IREGDesignTabColumnsDTO> getDataTableValue() {
        return dataTableValue;
    }

    public void setDisableCheckAllFlag(int disableCheckAllFlag) {
        this.disableCheckAllFlag = disableCheckAllFlag;
    }

    public int getDisableCheckAllFlag() {
        return disableCheckAllFlag;
    }

    public void setPreEditDTO(IREGDesignTabColumnsDTO preEditDTO) {
        this.preEditDTO = preEditDTO;
    }

    public IREGDesignTabColumnsDTO getPreEditDTO() {
        return preEditDTO;
    }

    public void setEditDTO(IREGDesignTabColumnsDTO editDTO) {
        this.editDTO = editDTO;
    }

    public IREGDesignTabColumnsDTO getEditDTO() {
        return editDTO;
    }

    public void setPreviosExtraList(List previosExtraList) {
        this.previosExtraList = previosExtraList;
    }

    public List getPreviosExtraList() {
        return previosExtraList;
    }

    public Long getSTRING_DATATYPE() {
        return STRING_DATATYPE;
    }

    public Long getINTEGER_DATATYPE() {
        return INTEGER_DATATYPE;
    }
    
    public RegulationCycleMaintainBean getRegulationMaintainBean() {
        return (RegulationCycleMaintainBean)evaluateValueBinding("regulationCycleMaintainBean");
    }

    public RegulationAttachmentsMaintain getRegulationAttachmentsMaintain() {
        return (RegulationAttachmentsMaintain)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                   "regulationCycleAttachmentsMaintain" + 
                                                                   "}").getValue(FacesContext.getCurrentInstance());
    }

    public void setBackupCurrentDetails(List<IREGDesignTabColumnsDTO> backupCurrentDetails) {
        this.backupCurrentDetails = backupCurrentDetails;
    }

    public List<IREGDesignTabColumnsDTO> getBackupCurrentDetails() {
        return backupCurrentDetails;
    }

    public void setNonDeletedColumns(List nonDeletedColumns) {
        this.nonDeletedColumns = nonDeletedColumns;
    }

    public List getNonDeletedColumns() {
        return nonDeletedColumns;
    }
}
