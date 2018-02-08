package com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.util.ArrayList;
import java.util.List;


public class IncludedEmployeesList extends  LookUpBaseBean{
private IBasicDTO selectedDecision;
private String backNavigationCase;
    public IncludedEmployeesList() {
        setPageDTO(RegDTOFactory.createEmpDecisionsDTO()); //set this the page dto
        setSaveSortingState(true);
        try {
            setClient(RegClientFactory.getDecisionsClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
//        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowpaging(true);
        app.setShowNavigation(true);
//        app.setShowbar(true);
//        app.setShowDelAlert(true);
//        app.setShowDelConfirm(true);
//        app.setShowpaging(true);
        return app;
    }
 public void getAll() throws DataBaseException {
    try {
    IEntityKey key=getSelectedDecision().getCode();
    List<IBasicDTO> list =((IDecisionsClient)getClient()).getAllPersonsInDecision(key);
    if(list==null){
        list= new ArrayList<IBasicDTO>();
    }
        setMyTableData(list);
        this.reinitializeSort();
    } catch (SharedApplicationException e) {
        e.printStackTrace();
    }
 }

    public void setSelectedDecision(IBasicDTO selectedDecision) {
        this.selectedDecision = selectedDecision;
    }

    public IBasicDTO getSelectedDecision() {
        return selectedDecision;
    }
    
    public String back(){
        return backNavigationCase;
    }

    public void setBackNavigationCase(String backNavigationCase) {
        this.backNavigationCase = backNavigationCase;
    }

    public String getBackNavigationCase() {
        return backNavigationCase;
    }
}
