package com.beshara.csc.nl.reg.presentation.backingbean.regulation.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDedignTablesDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabRecordsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;


public class RecordsBean extends ManyToManyDetailsMaintain {

    private static final String ATTACHMENT_LIST_NAVIGATION_CASE ="regulationattachmaintain";
    private Record record;
    private RecordDataDTO editableRecordDataDTO=new RecordDataDTO(); 
    private RecordDataDTO finalEditableRecordDataDTO=new RecordDataDTO(); 
    private IREGDedignTablesDTO designTablesDTO;
    private List backupColumnsList;
    private boolean validDataFlag=true;
    private boolean enterAtLeastOneRow=true;
    
    public RecordsBean() {
     setDivMainContentMany("divMainContentRecordsTab");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowsteps(false);
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowDelAlert(false);
        
        return app;
    }
    
    public String SaveRecords() {
      if(validate()){
           validDataFlag=record.validateData();
           if(validDataFlag){
                    try {
                        designTablesDTO.setRegDesignTabColumnsDTOList(record.constructColumnList()); 
                        designTablesDTO.setChecked(false);
                        this.getWizardBar().setCurrentStep("attachadd");
                        return ATTACHMENT_LIST_NAVIGATION_CASE;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
           } 
      }   
       return null;   
    }
    

    public String back() {
        designTablesDTO.setRegDesignTabColumnsDTOList(backupColumnsList); 
        designTablesDTO.setChecked(false);
        this.getWizardBar().setCurrentStep("attachadd");
        return ATTACHMENT_LIST_NAVIGATION_CASE;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }
    public void add() {
        record.addNewRecord(getAdded());
        record.resetSelectionData();
        resetSelection() ;
        
    }


    
    
    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return dto;
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        return dto;
    }

    public void delete() {
        try {
            List selectedRowList=this.getSelectedCurrentDetails();
            if (selectedRowList != null && selectedRowList.size() > 0) {
                for (int i = 0; i < selectedRowList.size(); i++) {
                    IREGDesignTabRecordsDTO selected = (IREGDesignTabRecordsDTO)selectedRowList.get(i);
                    int rowIndex =(selected.getRecNum().intValue()-1);
                    record.deleteRecord(rowIndex);  
                    selectedRowList.remove(i);
                    i--;
                }
            }
            
            resetSelection() ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       
      
    }

    public void setEditableRecordDataDTO(RecordDataDTO editableRecordDataDTO) {
        this.editableRecordDataDTO = editableRecordDataDTO;
    }

    public RecordDataDTO getEditableRecordDataDTO() {
        return editableRecordDataDTO;
    }

    public void setFinalEditableRecordDataDTO(RecordDataDTO finalEditableRecordDataDTO) {
        this.finalEditableRecordDataDTO = finalEditableRecordDataDTO;
    }

    public RecordDataDTO getFinalEditableRecordDataDTO() {
        return finalEditableRecordDataDTO;
    }


    public void edit()  {
       try {
           if(editableRecordDataDTO !=null){
               record.updateRecord(editableRecordDataDTO);
               finalEditableRecordDataDTO = (RecordDataDTO)getSharedUtil().deepCopyObject(editableRecordDataDTO);
           }
           record.resetSelectionData();
           resetSelection() ;
       }
       catch (Exception e) {
           e.printStackTrace();
       }
    }
    public void cancelEdit()  {

        try {
            setEditableRecordDataDTO((RecordDataDTO)getSharedUtil().deepCopyObject(finalEditableRecordDataDTO));
            record.resetSelectionData();
            resetSelection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetSelection() {
    if (getSelectedCurrentDetails() != null)
        getSelectedCurrentDetails().clear();
        setCheckAllFlag(false);
        goFirstPage(this.getCurrentDataTable());
    }


  
    public int getCurrentListSize() {
        if (record != null && record.getData()!=null) {
            return record.getData().size();
        }
        
        return 0;
    }

    public void setDesignTablesDTO(IREGDedignTablesDTO designTablesDTO) {
        this.designTablesDTO = designTablesDTO;
    }

    public IREGDedignTablesDTO getDesignTablesDTO() {
        return designTablesDTO;
    }
    
    public void selectedCurrent(ActionEvent event) throws Exception {
        //event = null; // unused
      //  event.getComponent();
        try {
            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
           
            s.addAll(this.getSelectedCurrentDetails());
            
            List recordDataRow = (List)this.getCurrentDataTable().getRowData();
            IREGDesignTabRecordsDTO selected = (IREGDesignTabRecordsDTO)recordDataRow.get(1);
            
            if (selected.getChecked()) {
                if (recordDataRow.get(0) == null || recordDataRow.get(0).equals("")) {
                recordDataRow.set(0, "1");
                try {
                    s.add(this.mapToCodeNameDTO(selected));
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
                } else {
                    selected.setChecked(false);
                }
            }
            if (!(selected.getChecked())) {
                recordDataRow.set(0, "");
                s.remove(this.mapToCodeNameDTO(selected));
                
            }
            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);
            
            
           if(getSelectedCurrentDetails().size()==1){
               
               IREGDesignTabRecordsDTO selectedRow = (IREGDesignTabRecordsDTO)getSelectedCurrentDetails().get(0);
               int recIndex=selectedRow.getRecNum().intValue()-1;
               List<IREGDesignTabRecordsDTO> actualDataRow = (List<IREGDesignTabRecordsDTO>)getSharedUtil().deepCopyObject(record.getRecordRowByIndex(recIndex));
               actualDataRow.remove(0);
               editableRecordDataDTO.setRecordDataRow(getSharedUtil().deepCopyObject(actualDataRow));
               finalEditableRecordDataDTO.setRecordDataRow(actualDataRow);
               finalEditableRecordDataDTO.setRecIndex(recIndex);
               editableRecordDataDTO.setRecIndex(recIndex);
           }
        } catch (Exception e) {
          e.printStackTrace();

        }
        
    }
    public void selectedCurrentAll(ActionEvent event) throws Exception {
        event = null; // unused
        try {
            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());
            int first = this.getCurrentDataTable().getFirst();
            Integer rowsCountPerPage = getCurrentDataTable().getRowCount();
            
            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int count = rowsCountPerPage.intValue();
            for (int j = first; j < first + count; j++) {
                this.getCurrentDataTable().setRowIndex(j);
                if (!this.getCurrentDataTable().isRowAvailable())
                    break;
                List recordDataRow = (List)this.getCurrentDataTable().getRowData();
                IREGDesignTabRecordsDTO selected = (IREGDesignTabRecordsDTO)recordDataRow.get(1);
                
                if (isCheckAllFlag() == true) {
                    recordDataRow.set(0, "1");
                    s.add(selected);
                }
                if (isCheckAllFlag() == false) {
                    s.remove(selected);
                    recordDataRow.set(0, "");
                    
                }
            }
            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }


    public void setBackupColumnsList(List backupColumnsList) {
        this.backupColumnsList = backupColumnsList;
    }

    public List getBackupColumnsList() {
        return backupColumnsList;
    }


    public void deleteDiv() throws DataBaseException, ItemNotFoundException {
    System.out.println("size "+getSelectedListSize());
       
    }
    
    public void cancelDelete(){
        record.resetSelectionData();
        resetSelection();
    }


    public void setValidDataFlag(boolean validDataFlag) {
        this.validDataFlag = validDataFlag;
    }

    public boolean isValidDataFlag() {
        return validDataFlag;
    }

    public void setEnterAtLeastOneRow(boolean enterAtLeastOneRow) {
        this.enterAtLeastOneRow = enterAtLeastOneRow;
    }

    public boolean isEnterAtLeastOneRow() {
        return enterAtLeastOneRow;
    }

    public boolean validate() {
        if(getCurrentListSize()==0){
          enterAtLeastOneRow=false;
          return false;
        }
        return true;
    }
}
