package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle;

import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.entity.IREGDedignTablesEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.Helper;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.Record;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.RecordsBean;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class AttachmentViewBean extends RecordsBean{

    // part for view page
    private boolean viewMode=false;
    private List tablesList=new ArrayList();
    private IRegulationsDTO selectedRegulationsDTO=null;
    private String selectedTableCode=getVirtualConstValue();
    private String backNavigationCase="";
    
    public AttachmentViewBean() {
        setDivMainContentMany("divMainContentRecordsTab");
        setDivMainContent("divMainContentRecordsTab");
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowdatatableContent(true);
        app.setShowCommonData(true);
        app.setShowshortCut(true);
        app.setShowContent1(true);
        app.setShowNavigation(true);
        return app;
    }
    
    public void setViewMode(boolean viewMode) {
        this.viewMode = viewMode;
    }

    public boolean isViewMode() {
        return viewMode;
    }

    public void setTablesList(List tablesList) {
        this.tablesList = tablesList;
    }

    public List getTablesList() {
        return tablesList;
    }

    public void setSelectedRegulationsDTO(IRegulationsDTO selectedRegulationsDTO) {
        this.selectedRegulationsDTO = selectedRegulationsDTO;
    }

    public IRegulationsDTO getSelectedRegulationsDTO() {
        return selectedRegulationsDTO;
    }
    
    public void fillTablesList(){
    
        if(selectedRegulationsDTO!=null && selectedRegulationsDTO.getCode()!=null){
              try {
                  tablesList=RegClientFactory.getREGDedignTablesClient().getCodeName(selectedRegulationsDTO.getCode());
              } catch (Exception e) {
                 e.printStackTrace();
                 tablesList=new ArrayList();
              }
          }
          else 
              tablesList=new ArrayList();
          
    }

    public void setSelectedTableCode(String selectedTableCode) {
        this.selectedTableCode = selectedTableCode;
    }

    public String getSelectedTableCode() {
        return selectedTableCode;
    }
    
    public void filterByTableCode(ActionEvent event){
           try {
           System.out.println("Herrrrrrrrrrrrrrrrrrr");
               if(selectedTableCode !=null && !selectedTableCode.equals(getVirtualConstValue())){
                   String[] keys=selectedTableCode.split("[*]");
                   IREGDedignTablesEntityKey key=RegEntityKeyFactory.createREGDedignTablesEntityKey(new Long(keys[0]) ,new Long(keys[1]) ,new Long(keys[2]),new Long(keys[3]) );
                   List columnsList=new ArrayList();
                    
                        columnsList = RegClientFactory.getREGDesignTabColumnsClient().getColumnsByTableCode(key);
                    
                  if(columnsList.size()>0)
                        setRecord(new Record(columnsList,Helper.getAllDataRecords(columnsList,null,0)));
                  else
                    setRecord(null);
          }
          else 
              setRecord(null);
        } catch (Exception e) {
           e.printStackTrace();
        } 
       
    }
    
    public String backToList(){
    
     return getBackNavigationCase();
    }

    public void setBackNavigationCase(String backNavigationCase) {
        this.backNavigationCase = backNavigationCase;
    }

    public String getBackNavigationCase() {
        return backNavigationCase;
    }
}
