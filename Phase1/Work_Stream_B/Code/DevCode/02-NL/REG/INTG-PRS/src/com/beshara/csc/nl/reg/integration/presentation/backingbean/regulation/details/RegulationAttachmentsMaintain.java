package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;

import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDedignTablesDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabColumnsDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabRecordsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IREGDedignTablesEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.RegulationMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class RegulationAttachmentsMaintain extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


     private int disableCheckAllFlag = 0;
     
    private IREGDedignTablesDTO preEditDTO;
    private IREGDedignTablesDTO editDTO;
    private List<IREGDedignTablesDTO> dataTableValue;
    private int index;
    private RegulationMaintainBean regulationMaintainBean;
    private List nonDeletedTables=new ArrayList();
    
    private final static String RECORD_NAVIGATION_CASE="RECORD_LIST";
    public RegulationAttachmentsMaintain() {
//        setClient(RegClientFactory.getREGDedignTablesClient());
        setDivMainContentMany("divREGMainContentMany");
        setSaveSortingState(true);
        setDivMainContent("divContent1Dynamic2");
        setPageDTO(RegDTOFactory.createREGDedignTablesDTO());
        setEditDTO(RegDTOFactory.createREGDedignTablesDTO());
        setPreEditDTO(RegDTOFactory.createREGDedignTablesDTO());
        setSelectedPageDTO(RegDTOFactory.createREGDedignTablesDTO());
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        app.setShowLookupEdit(true);
        app.setShowDelConfirm(true);
        return app;
    }
    
//    public String finish() throws DataBaseException, ItemNotFoundException, 
//                                  SharedApplicationException {
//        if(!validationRecordsColumnsTables())
//            return null;
//            
//        if (getRegulationMaintainBean().getPageDTO().getCode() == null) {
//            getRegulationMaintainBean().getPageDTO().setCode(RegEntityKeyFactory.createRegulationsEntityKey());
//        }
//        if(getRegulationMaintainBean().getIntegrationBean() != null && getRegulationMaintainBean().getIntegrationBean().getModuleFrom() != null && (getRegulationMaintainBean().getIntegrationBean().getModuleFrom()!=null)){
//        
//            String currentStepID=getRegulationMaintainBean().getCurrentStep();
//            if(currentStepID!=null && !currentStepID.equals("")){
//                try{
//                      String detailBeanName = getRegulationMaintainBean().getStepBeanName(getRegulationMaintainBean().getCurrentStep());
//                      Boolean isValid = (Boolean)executeMethodBinding(detailBeanName+".validate",null);
//                      
//                      if( isValid!=null && (!isValid.booleanValue()))
//                         return null;
//                     
//                  }catch(Exception e){e.printStackTrace();}
//                }
//
//            getRegulationMaintainBean().getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
//            return getRegulationMaintainBean().getIntegrationBean().goFrom();
//        }
//        else{
//            return getRegulationMaintainBean().defaultAddRegulation();
//        }
//       
//    }
    
    public String goRegulationaddcolumn() throws Exception{
        RegulationAddColumns regulationAddColumns = (RegulationAddColumns)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                           "regulationAddColumns" + 
                                                                           "}").getValue(FacesContext.getCurrentInstance());
                                                                           
            if (getSelectedCurrentDetails() != null && 
                getSelectedCurrentDetails().size() > 0) {
                regulationAddColumns = 
                    (RegulationAddColumns)evaluateValueBinding("regulationAddColumns");
                if (((IREGDedignTablesDTO)getSelectedCurrentDetails().get(0)).getRegDesignTabColumnsDTOList() == 
                    null)
                    ((IREGDedignTablesDTO)getSelectedCurrentDetails().get(0)).setRegDesignTabColumnsDTOList(new ArrayList<IREGDesignTabColumnsDTO>());
                List currentdetails = 
                    ((IREGDedignTablesDTO)getSelectedCurrentDetails().get(0)).getRegDesignTabColumnsDTOList();
//                if (regulationAddColumns.getCurrentDetails() == null || 
//                    regulationAddColumns.getCurrentDetails().isEmpty()) {
                    regulationAddColumns.setCurrentDetails(currentdetails);
//                }
//                regulationAddColumns.setPreviosExtraList((List)deepCopyObject(regulationAddColumns.getCurrentDetails()));
                regulationAddColumns.setBackupCurrentDetails(new ArrayList());
//                regulationAddColumns.reSetCurrentDetails(new ArrayList<IBasicDTO>());
                regulationAddColumns.setBackupCurrentDetails((List)SharedUtilBean.deepCopyObject(regulationAddColumns.getCurrentDetails()));
                return "regulationaddcolumn";
            }
            return null;
    }
        
    public void EditChange()
       {           
           resetSelection();
           try {
               setEditDTO((IREGDedignTablesDTO)SharedUtilBean.deepCopyObject(getPreEditDTO()));
               ((IREGDedignTablesDTO)getCurrentDisplayDetails().get(index)).setCode(getEditDTO().getCode());
               ((IREGDedignTablesDTO)getCurrentDisplayDetails().get(index)).setName(getEditDTO().getName());
               ((IREGDedignTablesDTO)getCurrentDisplayDetails().get(index)).setHeader1Text(getEditDTO().getHeader1Text());
               ((IREGDedignTablesDTO)getCurrentDisplayDetails().get(index)).setHeader2Text(getEditDTO().getHeader2Text());
               ((IREGDedignTablesDTO)getCurrentDisplayDetails().get(index)).setFooterText(getEditDTO().getFooterText());
           } catch (Exception e) {
               // TODO
           }           
       }
       
       public void preEditDiv() {

        try {
            editDTO = (IREGDedignTablesDTO)getCurrentDisplayDetails().get(index);
            setPreEditDTO((IREGDedignTablesDTO)SharedUtilBean.deepCopyObject(editDTO));
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
               
        }
           
    public void delete() {
           boolean hasRelatedCols=false;
           if (getCurrentDetails() == null)
               setCurrentDetails(new ArrayList<IBasicDTO>());
           if (getSelectedCurrentDetails() != null && 
               getSelectedCurrentDetails().size() > 0) {
               for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                   IREGDedignTablesDTO selected = (IREGDedignTablesDTO)getSelectedCurrentDetails().get(i);
                       if(selected.getRegDesignTabColumnsDTOList() !=null && selected.getRegDesignTabColumnsDTOList().size()>0)
                            hasRelatedCols=true;
                       if(hasRelatedCols){
                               nonDeletedTables.add(selected);
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
               
               this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                                this.getCurrentDetails());
               this.resetSelection();
               
               if(hasRelatedCols){ 
                   this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
               }   
           }

          
           
       }
       
    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
           IREGDedignTablesDTO rEGDedignTablesDTO = (IREGDedignTablesDTO)dto;
           return rEGDedignTablesDTO;
    }

     public void saveAndNew() throws DataBaseException {
        this.add(); 
     }   
     
    public void add() {
        getPageDTO().setStatusFlag(new Long(0));
        getPageDTO().setCode(RegEntityKeyFactory.createREGDedignTablesEntityKey(new Long(0),new Long(0),new Long(0),getMinId()));
        getCurrentDetails().add(getPageDTO());
        setPageDTO(RegDTOFactory.createREGDedignTablesDTO());
        this.resetSelection();
    }
    
    public Long getMinId() {

        Long maxId = new Long(-1);

        if (this.getCurrentDetails() == null || 
            this.getCurrentDetails().size() == 0) {
            return maxId;
        }

        for (IBasicDTO dto: this.getCurrentDetails()) {
            Long id = 
                ((IREGDedignTablesEntityKey)dto.getCode()).getDestableCode();
            if (id < maxId)
                maxId = id;

        }

        return maxId - 1;
    }

    public boolean validate() {
        if(!validationRecordsColumnsTables() && getRegulationMaintainBean().getCurrentStep().equals("attachadd"))
            return false;
        System.out.println("from validate------");
        this.unCheck();
        return true;
    }

    public String next() {
        if(!validationRecordsColumnsTables())
            return null;
        String nextNavCase= getRegulationMaintainBean().next();
        return nextNavCase;
    }

    public String previous() {
        if(!validationRecordsColumnsTables())
            return null;
        String previousNavCase= getRegulationMaintainBean().previous();
        return previousNavCase;
    }
    
    public Boolean validationRecordsColumnsTables(){
        ArrayList<IREGDedignTablesDTO> currentTablesDTOs = (ArrayList)getCurrentDisplayDetails();
        if(currentTablesDTOs == null || currentTablesDTOs.size()==0){
            return true;
        }
        for(IREGDedignTablesDTO rEGDedignTablesTempDTO:currentTablesDTOs){
             ArrayList<IREGDesignTabColumnsDTO> currentColumnsDTOs = (ArrayList<IREGDesignTabColumnsDTO>)rEGDedignTablesTempDTO.getRegDesignTabColumnsDTOList();
             if(currentColumnsDTOs == null || currentColumnsDTOs.size() == 0){
                getSharedUtil().handleException(null, "com.beshara.csc.nl.reg.integration.presentation.resources.integration", "columnsValuesValidation");
                return false;
             }
            for(IREGDesignTabColumnsDTO rEGDesignTabColumnsTempDTO:currentColumnsDTOs){
                ArrayList<IREGDesignTabRecordsDTO> currentRecordsDTOs = (ArrayList<IREGDesignTabRecordsDTO>)rEGDesignTabColumnsTempDTO.getRegDesignTabRecordsDTOList();
                if(currentRecordsDTOs == null || currentRecordsDTOs.size() == 0){
                    getSharedUtil().handleException(null, "com.beshara.csc.nl.reg.integration.presentation.resources.integration", "columnsValuesValidation");
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void setEditDTO(IREGDedignTablesDTO editDTO) {
        this.editDTO = editDTO;
    }

    public IREGDedignTablesDTO getEditDTO() {
        return editDTO;
    }
    
    public void cancelEdit() {
        try {
            setEditDTO((IREGDedignTablesDTO)SharedUtilBean.deepCopyObject(preEditDTO));
            getCurrentDisplayDetails().set(index,(IREGDedignTablesDTO)SharedUtilBean.deepCopyObject(preEditDTO));
        } catch (Exception e) {
            // TODO
        }
    }
    
    public void setPreEditDTO(IREGDedignTablesDTO preEditDTO) {
        this.preEditDTO = preEditDTO;
    }

    public IREGDedignTablesDTO getPreEditDTO() {
        return preEditDTO;
    }
    
    public void selectedCurrent(ActionEvent event) throws Exception {
        super.selectedCurrent(event);
        dataTableValue = (List<IREGDedignTablesDTO>)getCurrentDataTable().getValue();
        if (dataTableValue.get(getCurrentDataTable().getRowIndex()).getChecked())
            index = getCurrentDataTable().getRowIndex();
    }
    

     public void setDisableCheckAllFlag(int disableCheckAllFlag) {
         this.disableCheckAllFlag = disableCheckAllFlag;
     }
    


    public void setDataTableValue(List<IREGDedignTablesDTO> dataTableValue) {
        this.dataTableValue = dataTableValue;
    }

    public List<IREGDedignTablesDTO> getDataTableValue() {
        return dataTableValue;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public RegulationMaintainBean getRegulationMaintainBean() {
        return (RegulationMaintainBean)evaluateValueBinding("regulationMaintainBean");
    }
    
    public String goAddValues(){
    
       try {
           if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size()==1) {
                IREGDedignTablesDTO designTablesDTO=(IREGDedignTablesDTO)getSelectedCurrentDetails().get(0);
                    if(designTablesDTO.getRegDesignTabColumnsDTOList() !=null && designTablesDTO.getRegDesignTabColumnsDTOList().size()>0){
                         RecordsBean recordsBean = (RecordsBean)evaluateValueBinding("regulationRecordsBean");
                         recordsBean.setDesignTablesDTO(designTablesDTO);
                         recordsBean.setRecord(new Record(designTablesDTO.getRegDesignTabColumnsDTOList(),Helper.getAllDataRecords(designTablesDTO.getRegDesignTabColumnsDTOList(),null,0)));
                         recordsBean.setBackupColumnsList((List)getSharedUtil().deepCopyObject(designTablesDTO.getRegDesignTabColumnsDTOList()));
                         return RECORD_NAVIGATION_CASE;
                    }
                else
                {
                    getSharedUtil().handleException(null, "com.beshara.csc.nl.reg.integration.presentation.resources.integration", "error_columnsValues_Enter");
                     return null;
                }
            } 
       }
       catch (Exception e) {
           e.printStackTrace();
       }
     
       return null;
    }
    
    public void preAdd() {
        System.out.println("Calling preAdd()...");
        setSuccess(false);
        setShowErrorMsg(false);
        setPageDTO(RegDTOFactory.createREGDedignTablesDTO());
    }

    public void setNonDeletedTables(List nonDeletedTables) {
        this.nonDeletedTables = nonDeletedTables;
    }

    public List getNonDeletedTables() {
        return nonDeletedTables;
    }
}
