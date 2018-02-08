package com.beshara.csc.nl.reg.presentation.backingbean.regulation.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.INewspapersDTO;
import com.beshara.csc.nl.reg.business.client.IPublishClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IPublishDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IPublishEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class RegulationPublishMaintain extends ManyToManyDetailsMaintain {
    private IPublishDTO preEditDTO;
    private IPublishDTO editDTO;
    private List<IPublishDTO> dataTableValue;
    private IRegulationSearchDTO regulationSearchDTO;
    private int index;
    
    public RegulationPublishMaintain() {
        setClient(RegClientFactory.getPublishClient());
        setCustomDiv1("publishEditDiv");
        setDivMainContentMany("divMainContentManyWithContent1ThreeRowsSmall3");
//        setSaveSortingState(true);
//        setDivMainContent("divContent1Dynamic2");
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
//        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
//        if(viewId.equals("/module/jsps/regulation/regulationpublishmaintainedit.jsp")
//            || viewId.equals("/module/jsps/regulation-integration/regulationpublishmaintainedit.jsp")){
//            app.setShowCustomDiv1(true);
//        }
        return app;
    }
    public void getListAvailable() throws DataBaseException, 
                                          SharedApplicationException {


        if (getMasterEntityKey() != null) {
            setAvailableDetails(((IPublishClient)getClient()).listAvailableEntitiesCenter(getMasterEntityKey()));

        } else {
            List<IBasicDTO> chk = 
                ((IPublishClient)getClient()).listAvailableEntitiesCenter(RegEntityKeyFactory.createRegulationsEntityKey());
            setAvailableDetails(chk);
        }

        super.getListAvailable();
    }
    
    public void delete() {
        if (getCurrentDetails() == null)
            setCurrentDetails( new ArrayList<IBasicDTO>());
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() > 0) {

            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);
                if (getCurrentSelectedDetail(selected).getStatusFlag() == null) {                    
                    ((IPublishDTO)getCurrentSelectedDetail(selected)).setPublishDate(null);
                    ((IPublishDTO)getCurrentSelectedDetail(selected)).setUntilDate(null);
                    getCurrentSelectedDetail(selected).setStatusFlag(ISystemConstant.DELEDTED_ITEM);                    
                    getSelectedCurrentDetails().remove(i);
                    i--;
                }else if (getCurrentSelectedDetail(selected).getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {                    
                    getCurrentDetails().remove(getCurrentSelectedDetail(selected));
                    try {
                        getAvailableDetails().add(selected);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                    getSelectedCurrentDetails().remove(i);
                    i--;
                }
            }
        }       
        this.restoreDetailsTablePosition(this.getCurrentDataTable(),this.getCurrentDetails());
        this.resetSelection();
    }


    public void selectedCurrent(ActionEvent event) throws Exception {
        super.selectedCurrent(event);
        dataTableValue = (List<IPublishDTO>)getCurrentDataTable().getValue();

        index = getCurrentDataTable().getRowIndex();
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {

        return ((IPublishDTO)dto).getNewspapersDTO(); 
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
    
        IRegulationsDTO regDTO=null;
        IRegulationsEntityKey regEntityKey=null;
        IPublishDTO publishDTO = RegDTOFactory.createPublishDTO();
        IPublishEntityKey  publishEntityKey=null;
        
        publishDTO.setNewspapersDTO((INewspapersDTO)dto); 
        
        
        if (this.getMasterEntityKey() != null) {
            regEntityKey = (IRegulationsEntityKey)this.getMasterEntityKey();
            regDTO=RegDTOFactory.createRegulationsDTO();
            regDTO.setCode(regEntityKey);
            publishDTO.setRegulationsDTO(regDTO);
        }
        
        if(regEntityKey !=null)
            publishEntityKey=RegEntityKeyFactory.createPublishEntityKey(regEntityKey.getRegtypeCode(),regEntityKey.getRegyearCode(),regEntityKey.getRegulationNumber(),Long.valueOf(dto.getCode().getKey().toString()));
        else
            publishEntityKey=RegEntityKeyFactory.createPublishEntityKey(null,null,null,Long.valueOf(dto.getCode().getKey().toString()));
            
        publishDTO.setCode(publishEntityKey);
        
        return publishDTO;
    }

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
    }

    public boolean validate() {
        System.out.println("from advantages Validate");
        return true;
    }

    public void resetDetailDTO(IBasicDTO dto) {
        //        ((JobDutiesDTO)dto).setActivateDate(null);
    }

    public void setRegulationSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
        this.regulationSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getRegulationSearchDTO() {
        return regulationSearchDTO;
    }


    public Integer getZeroAsInteger() {
        return new Integer(0);
    }

    public Integer getOneAsInteger() {
        return new Integer(1);
    }

    public String getCustomDiv1Title() {
        return "Cancel Regulation";
    }

    public void setEditDTO(IPublishDTO editDTO) {
        this.editDTO = editDTO;
    }

    public IPublishDTO getEditDTO() {
        return editDTO;
    }

    public void preEditDiv() {
        editDTO = dataTableValue.get(index);
        preEditDTO = RegDTOFactory.createPublishDTO();
        preEditDTO.setNewspapersDTO(editDTO.getNewspapersDTO());
        preEditDTO.setPublishDate(editDTO.getPublishDate());
        preEditDTO.setUntilDate(editDTO.getUntilDate());
        preEditDTO.setPublishRefrence(editDTO.getPublishRefrence());
        preEditDTO.setPublishNotes(editDTO.getPublishNotes());
    }

    public void cancelEdit() {
        editDTO = preEditDTO;
    }

    public void setDataTableValue(List<IPublishDTO> dataTableValue) {
        this.dataTableValue = dataTableValue;
    }

    public List<IPublishDTO> getDataTableValue() {
        return dataTableValue;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }


    public void setPreEditDTO(IPublishDTO preEditDTO) {
        this.preEditDTO = preEditDTO;
    }

    public IPublishDTO getPreEditDTO() {
        return preEditDTO;
    }
    
    
    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
        super.cancelSearchAvailable();
        regulationSearchDTO = null;
        setCheckAllFlagAvailable(false);
        
    }
    
    public void preAdd() {
        super.preAdd();
        try {
            this.cancelSearchAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
