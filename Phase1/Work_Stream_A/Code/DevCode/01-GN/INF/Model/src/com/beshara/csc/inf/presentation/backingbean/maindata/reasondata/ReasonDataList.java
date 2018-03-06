package com.beshara.csc.inf.presentation.backingbean.maindata.reasondata;


import com.beshara.csc.inf.business.client.IInfReasonDataClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IInfReasonDataDTO;
import com.beshara.csc.inf.business.dto.IInfReasonTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.InfReasonDataDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;


public class ReasonDataList extends LookUpBaseBean {
    private List<IInfReasonTypesDTO> reasonType;
    private long selectedModulesKeyDufalut = -100L;
    private long reasontypecode = selectedModulesKeyDufalut;

    public ReasonDataList() {
        setPageDTO(InfDTOFactory.createReasonDataDTO());
        super.setSelectedPageDTO(InfDTOFactory.createReasonDataDTO());
        setClient(InfClientFactory.getReasonDataClient());
        setDivMainContent("customDivMainContentMany");
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowContent1(true);

        return app;
    }

    public void search() throws DataBaseException, NoResultException {

        this.setSearchMode(true);


        IInfReasonDataDTO infReasonDataDTo = 
            InfDTOFactory.createReasonDataDTO();

        //                IExtraDataTypesDTO.setExtdattypeFlag(((IExtraDataTypesDTO)getPageDTO()).getExtdattypeFlag());
        if (getSearchType().intValue() == 0) {
            infReasonDataDTo.setCode(InfEntityKeyFactory.createReasonDataEntityKey(Long.valueOf(getSearchQuery()), 
                                                                                   ((InfReasonDataDTO)getPageDTO()).getRestypeCode()));
        } else if (getSearchType().intValue() == 1) {
            //  IExtraDataTypesDTO.setName(formatSearchString(getSearchQuery()));
            infReasonDataDTo.setName(getSearchQuery());
           infReasonDataDTo.setRestypeCode(((InfReasonDataDTO)getPageDTO()).getRestypeCode());
        }
        try {
            setMyTableData((InfClientFactory.getReasonDataClient()).searchReasonData(infReasonDataDTo));
        } catch (Exception f) {
            setMyTableData(new ArrayList());
            f.printStackTrace();
        }

        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
        this.repositionPage(0);
        setSelectedRadio("");
    }


    public void setReasonType(List<IInfReasonTypesDTO> reasonType) {
        this.reasonType = reasonType;
    }

    public List<IInfReasonTypesDTO> getReasonType() {

        try {
            reasonType = 
                    ((List)InfClientFactory.getReasonTypesClient().getAll()); //<IInfReasonTypesDTO>)
            System.out.println("11111");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return reasonType;
    }

    public void reInitializePageDTO() {
        if (getPageDTO() != null) {
            ((IInfReasonDataDTO)this.getPageDTO()).setName("");
        }
        getHighlightedDTOS().clear();
        getHighlightedDTOS().add(getPageDTO());
       
        try {
            if(getPageDTO().getCode() != null)
                getPageIndex((String)getPageDTO().getCode().getKey());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void setReasontypecode(long reasontypecode) {
        this.reasontypecode = reasontypecode;
    }

    public long getReasontypecode() {
        return reasontypecode;
    }

    public void setSelectedModulesKeyDufalut(long selectedModulesKeyDufalut) {
        this.selectedModulesKeyDufalut = selectedModulesKeyDufalut;
    }

    public long getSelectedModulesKeyDufalut() {
        selectedModulesKeyDufalut = -100L;
        return selectedModulesKeyDufalut;
    }

    public void fillDataTable() {
        setSelectedRadio("");
        getHighlightedDTOS().clear();
        setCheckAllFlag(false);
        getSelectedDTOS().clear();
        try {
            cancelSearch();
        } catch (DataBaseException e) {
          e.printStackTrace();  // TODO
        }
        getAll();
    }

    public void getAll() {

        if (((InfReasonDataDTO)getPageDTO()).getRestypeCode() != null) {
            try {

                if (((InfReasonDataDTO)getPageDTO()).getRestypeCode() != 
                    selectedModulesKeyDufalut) {
                    
                    setMyTableData(((IInfReasonDataClient)getClient()).getReasonDataByType(((InfReasonDataDTO)getPageDTO()).getRestypeCode()));
                    this.reinitializeSort();
                } else {
                    getMyDataTable().setFirst(0);
                    restoreTablePosition();
                    setMyTableData(new ArrayList());
                }

            } catch (SharedApplicationException e) {
                getMyDataTable().setFirst(0);
                restoreTablePosition();
                setMyTableData(new ArrayList());
                e.printStackTrace();
            } catch (DataBaseException e) {

                getMyDataTable().setFirst(0);
                restoreTablePosition();
                setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        } else {
            getMyDataTable().setFirst(0);
            restoreTablePosition();


        }

    }

//    public void save() {
//        try {
//            reIntializeMsgs();
//            this.add();
//            this.reInitializePageDTO();
//            reIntializeAddDivMsgs();
//
//        } catch (Exception ex) {
//            this.setErrorMsg(ex.getCause().getMessage());
//            //showDiv();
//        }
//    }

    //    public void save() {
    //        try {
    //            reIntializeMsgs();
    //            ((InfReasonDataDTO)getPageDTO()).setCode(restypeCode);
    //            this.add();
    //            this.reInitializePageDTO();
    //        } catch (Exception ex) {
    //            this.setErrorMsg(ex.getCause().getMessage());
    //        }
    //    }
     public void edit() throws DataBaseException, ItemNotFoundException, 
                               SharedApplicationException{
                               
                               super.edit();
                               getSelectedDTOS().clear();
                               }
}


