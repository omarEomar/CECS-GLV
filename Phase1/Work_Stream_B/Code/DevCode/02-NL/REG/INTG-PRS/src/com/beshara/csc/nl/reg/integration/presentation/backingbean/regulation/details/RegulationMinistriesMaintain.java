package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.ICatsDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.reg.business.client.IRegulationMinistriesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationMinistriesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationMinistriesEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


public class RegulationMinistriesMaintain extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private static final Long CATERGOY_GOV = 1L;
    //private static final Long CATERGOY_NON_GOV = 0L;

    private List<ICatsDTO> categories;
    //private Long govType;
    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private IRegulationSearchDTO searchDto = RegDTOFactory.createRegulationSearchDTO();
    private Timestamp dateNow ;
    private Timestamp masterApplyDate;
    private Timestamp masterCancelDate;
    private int disableCheckAllFlag = 0;

    public RegulationMinistriesMaintain() {
    //    setClient(RegClientFactory.getMinistriesClient());
     setClient(RegClientFactory.getRegulationMinistriesClient());
        //BeansUtil.setValue("regulationMaintainBean.content1Div","divContent1Dynamic");
        //BeansUtil.setValue("regulationMaintainBean.lookupAddDiv","regulationDivSearch");
        //BeansUtil.setValue("regulationMaintainBean.lookupAddDiv","divContent1Dynamic");
        setContent1Div("divContent1Dynamic");
        setLookupAddDiv("regulationDivSearch");
        setDivMainContentMany("divMainContentManyWithContent1ThreeRowsSmall4");
//        setSaveSortingState(true);
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        return app;
    }

    public void getListAvailable() throws DataBaseException {
        setAvailableDetails(new ArrayList<IBasicDTO>());
        /*
        if(getMasterEntityKey()!=null){
            try {
                setAvailableDetails(((IRegulationMinistriesClient)getClient()).listAvailableEntitiesCenter(getMasterEntityKey()));
            } catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList<BasicDTO>());
            }
        }else{
            try {
                setAvailableDetails(((IRegulationMinistriesClient)getClient()).listAvailableEntitiesCenter(new RegulationsEntityKey()));
                super.getListAvailable();
            } catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList<BasicDTO>());
            }
        }   */
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return ((IRegulationMinistriesDTO)dto).getMinMinistriesDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        IRegulationsDTO regDTO=null;
        IRegulationsEntityKey regEntityKey=null;
        IRegulationMinistriesEntityKey  ministryEntityKey=null;
        
        
        IRegulationMinistriesDTO regulationMinistriesDTO = 
            RegDTOFactory.createRegulationMinistriesDTO();
            
        regulationMinistriesDTO.setMinMinistriesDTO((IMinistriesDTO)dto);
        
        if (this.getMasterEntityKey() != null) {
            regEntityKey = (IRegulationsEntityKey)this.getMasterEntityKey();
            regDTO=RegDTOFactory.createRegulationsDTO();
            regDTO.setCode(regEntityKey);
            regulationMinistriesDTO.setRegulationsDTO(regDTO);
        }
        
        
        if(regEntityKey !=null)
            ministryEntityKey = (IRegulationMinistriesEntityKey)RegEntityKeyFactory.createRegulationMinistriesEntityKey(regEntityKey.getRegtypeCode(),regEntityKey.getRegyearCode(),regEntityKey.getRegulationNumber(),Long.valueOf(dto.getCode().getKey().toString()));
        else
             ministryEntityKey = (IRegulationMinistriesEntityKey)RegEntityKeyFactory.createRegulationMinistriesEntityKey(null,null,null,Long.valueOf(dto.getCode().getKey().toString()));  
            
        regulationMinistriesDTO.setCode(ministryEntityKey);
        
        
        regulationMinistriesDTO.setAppliedDate(masterApplyDate);
        regulationMinistriesDTO.setCancelDate(masterCancelDate);
        return regulationMinistriesDTO;
    }

    public void fillCategories() {
        setAvailableDetails(new ArrayList<IBasicDTO>());
        searchDto.setCode1(itemSelectionRequiredValue);
        try {
            categories = (List)OrgClientFactory.getCatsClient().getCatsByGovFlag(CATERGOY_GOV);
            return;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        categories = new ArrayList<ICatsDTO>();
    }
    
    public void delete() {
        if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() > 0) {
            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);
                Long statusFlag = getCurrentSelectedDetail(selected).getStatusFlag();

                if (statusFlag != null && statusFlag.equals(ISystemConstant.ADDED_ITEM)) {
                    getCurrentDetails().remove(getCurrentSelectedDetail(selected));
                    getSelectedCurrentDetails().remove(i);
                    try {
                        getAvailableDetails().add(selected);
                    } catch (SharedApplicationException e) {
                        e.printStackTrace();
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    }
                    i--;
                }
            }
        }
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), this.getCurrentDetails());
        this.resetSelection();
    }

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
        try {
            searchDto.setMasterCode(getMasterEntityKey() == null ? 
                                    RegEntityKeyFactory.createRegulationsEntityKey() : 
                                    getMasterEntityKey());
            setAvailableDetails(((IRegulationMinistriesClient)getClient()).filterAvailableEntitiesCenter(searchDto));

        } catch (SharedApplicationException e) {
            setSearchMode(true);
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.searchAvailable();
    }

    public void cancelMinistries() throws DataBaseException, 
                                          SharedApplicationException {
        searchDto.setMasterCode(getMasterEntityKey() == null ? 
                                RegEntityKeyFactory.createRegulationsEntityKey() : 
                                getMasterEntityKey());
        //((IRegulationMinistriesClient)getClient()).(searchDto)
    }

    public void setSearchDto(IRegulationSearchDTO searchDto) {
        this.searchDto = searchDto;
    }

    public IRegulationSearchDTO getSearchDto() {
        return searchDto;
    }
/*
    public void setGovType(Long govType) {
        this.govType = govType;
    }

    public Long getGovType() {
        if(govType == null){
            govType = CATERGOY_GOV;
            fillCategories();            
        }
        return govType;
    }
*/

    public boolean checkCheckAllFlag(){
        List currentDisplayDetails = getCurrentDisplayDetails();
        
        for(int i =0; i<currentDisplayDetails.size(); i++){
            if(((IRegDTO)currentDisplayDetails.get(i)).getStatusFlag() == null)
            {
                return false;//disable allcheckbox   
            }
        }
        
        return true; // there are no disabled elements
        
    }
    
    
    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

    public void setCategories(List<ICatsDTO> categories) {
        this.categories = categories;
    }

    public List<ICatsDTO> getCategories() {
        if(categories == null){
            fillCategories();
        }
        return categories;
    }

    public Long getCategoryGov() {
        return CATERGOY_GOV;
    }
/*
    public Long getCategoryNonGov() {
        return CATERGOY_NON_GOV;
    }
*/

    public void setDateNow(Timestamp dateNow) {
        this.dateNow = dateNow;
    }

    public Timestamp getDateNow() {
        if(dateNow == null){
            dateNow = SharedUtils.getCurrentTimestamp();
        }
        return dateNow;
    }
    
    public void applyOnAllMinistries(){
        try {
            searchDto.setCode1(null);
            searchAvailable();
            List<IBasicDTO> availableMinistriesList = getAvailableDetails();
            for(IBasicDTO dto : availableMinistriesList){
                IRegulationMinistriesDTO mdto = (IRegulationMinistriesDTO)this.mapToDetailDTO(dto);
                mdto.setStatusFlag(ISystemConstant.ADDED_ITEM);
                mdto.setAppliedDate(masterApplyDate);
                mdto.setCancelDate(masterCancelDate);
                getCurrentDetails().add(mdto);
            }
            setAvailableDetails(new ArrayList<IBasicDTO>(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMasterApplyDate(Timestamp masterApplyDate) {
        this.masterApplyDate = masterApplyDate;
    }

    public Timestamp getMasterApplyDate() {
        return masterApplyDate;
    }

    public void setMasterCancelDate(Timestamp masterCancelDate) {
        this.masterCancelDate = masterCancelDate;
    }

    public Timestamp getMasterCancelDate() {
        return masterCancelDate;
    }
    
    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
        super.cancelSearchAvailable();
        setSearchDto(RegDTOFactory.createRegulationSearchDTO());;
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


    public void setDisableCheckAllFlag(int disableCheckAllFlag) {
        this.disableCheckAllFlag = disableCheckAllFlag;
    }

    public int getDisableCheckAllFlag() {
        
        if(disableCheckAllFlag==0){
            if(!checkCheckAllFlag()){
                disableCheckAllFlag = 1; //disable check all
            }
            else{
                disableCheckAllFlag = 2;
            }
        }
    
    
        return disableCheckAllFlag;
    }
}
