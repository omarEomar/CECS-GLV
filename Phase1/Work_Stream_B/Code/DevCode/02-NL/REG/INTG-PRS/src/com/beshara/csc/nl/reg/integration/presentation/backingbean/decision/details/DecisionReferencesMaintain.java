package com.beshara.csc.nl.reg.integration.presentation.backingbean.decision.details;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.reg.business.client.IDecisionRefrencesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionRefrencesDTO;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionRefrencesEntityKey;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;


public class DecisionReferencesMaintain extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List<IBasicDTO> types;
    private List<IYearsDTO> issuanceYears;
    private List<IBasicDTO> status;
    private List<IDecisionMakerTypesDTO> decisionMakers;
    private IRegulationSearchDTO searchDto = 
        RegDTOFactory.createRegulationSearchDTO();

    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private boolean existReference = true;

    public DecisionReferencesMaintain() {
        setClient(RegClientFactory.getDecisionRefrencesClient());
        //        BeansUtil.setValue("decisionMaintainBean.content1Div","divContent1Dynamic");
        //        BeansUtil.setValue("decisionMaintainBean.lookupAddDiv","regulationDivSearch");
        setLookupAddDiv("regulationDivSearch");
        setDivMainContentMany("divREGMainContentMany");
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        return app;
    }

    public void getListAvailable() throws DataBaseException {
        setSearchMode(false);
        if (getMasterEntityKey() != null) {
            try {
                setAvailableDetails(((IDecisionRefrencesClient)getClient()).listAvailableEntities(getMasterEntityKey()));
            } catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList<IBasicDTO>());
            }
        } else {
            try {
                setAvailableDetails(((IDecisionRefrencesClient)getClient()).listAvailableEntities(RegEntityKeyFactory.createDecisionsEntityKey()));
                super.getListAvailable();
                Object obj = getAvailableDetails();
                System.out.println(obj.getClass());
            } catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList<IBasicDTO>());
            }
        }
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return ((IDecisionRefrencesDTO)dto).getRegulationsDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        
        IRegulationsDTO regDTO=null;
        IRegulationsEntityKey regEntityKey= (IRegulationsEntityKey)dto.getCode();
        IDecisionRefrencesDTO decisionRefrencesDTO =RegDTOFactory.createDecisionRefrencesDTO();
        decisionRefrencesDTO.setRegulationsDTO((IRegulationsDTO)dto);
        IDecisionsDTO decisionsDTO = null;
        IDecisionsEntityKey decisionsEntityKey = null;
        IDecisionRefrencesEntityKey decisionRefrencesEntityKey=null;
        if (this.getMasterEntityKey() != null) {
            decisionsEntityKey = (IDecisionsEntityKey)this.getMasterEntityKey();
            decisionsDTO=RegDTOFactory.createDecisionsDTO();
            decisionsDTO.setCode(decisionsEntityKey);
            decisionRefrencesDTO.setDecisionsDTO(decisionsDTO);
        }
        if (decisionsEntityKey != null)
            {
            decisionRefrencesEntityKey =RegEntityKeyFactory.createDecisionRefrencesEntityKey(regEntityKey.getRegtypeCode(),regEntityKey.getRegyearCode(),regEntityKey.getRegulationNumber(),decisionsEntityKey.getDectypeCode(),decisionsEntityKey.getDecyearCode(),decisionsEntityKey.getDecisionNumber());
            }
            else
            {
            decisionRefrencesEntityKey = RegEntityKeyFactory.createDecisionRefrencesEntityKey(regEntityKey.getRegtypeCode(),regEntityKey.getRegyearCode(),regEntityKey.getRegulationNumber(),null,null,null);
            }
        decisionRefrencesDTO.setCode(decisionRefrencesEntityKey);
        return decisionRefrencesDTO;
    }

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
        try {
            searchDto.setMasterCode(getMasterEntityKey() == null ? 
                                    RegEntityKeyFactory.createDecisionsEntityKey() : 
                                    getMasterEntityKey());
            setAvailableDetails(((IDecisionRefrencesClient)getClient()).filterAvailableEntities(searchDto));
            setSearchMode(true);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setSearchMode(true);
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (Exception ex) {
            ex.printStackTrace();
            setSearchMode(true);
            setAvailableDetails(new ArrayList<IBasicDTO>());
        }
        this.goFirstPage(this.getAvailableDataTable());

        // added clear seleted dtos and to make check all box uncheck
        if (getSelectedAvailableDetails() != null && 
            getSelectedAvailableDetails().size() > 0)
            getSelectedAvailableDetails().clear();
        setCheckAllFlagAvailable(false);

    }

    public void cancelSearchAvailable() {
        this.setSearchString(null);
        this.setSearchMode(false);
       setAvailableDetails(new ArrayList());
        this.removeCurrentFromAvailable();
        // added clear seleted dtos and to make check all box uncheck
        if (getSelectedAvailableDetails() != null && 
            getSelectedAvailableDetails().size() > 0)
            getSelectedAvailableDetails().clear();

        setCheckAllFlagAvailable(false);

        searchDto = RegDTOFactory.createRegulationSearchDTO();
        getAvailableDataTable().setFirst(0);
    }

    //================================================================Accessors=

    public void setTypes(List<IBasicDTO> types) {
        this.types = types;
    }

    public List<IBasicDTO> getTypes() throws DataBaseException {
        if (types == null || types.size() == 0) {
            types = 
                    RegClientFactory.getTypesClient().getCodeName(ISystemConstant.REGULATION_VALIDITY_REGULATION);
            if (types == null)
                types = new ArrayList<IBasicDTO>();
        }
        return types;
    }

    public void setIssuanceYears(List<IYearsDTO> issuanceYears) {
        this.issuanceYears = issuanceYears;
    }

    public List<IYearsDTO> getIssuanceYears() throws DataBaseException {
        if (issuanceYears == null || issuanceYears.size() == 0) {
            try {
                issuanceYears = InfClientFactory.getYearsClient().getCodeName();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (issuanceYears == null)
                issuanceYears = new ArrayList<IYearsDTO>();
        }
        return issuanceYears;
    }

    public void setStatus(List<IBasicDTO> status) {
        this.status = status;
    }

    public List<IBasicDTO> getStatus() throws DataBaseException, 
                                              SharedApplicationException {
        if (status == null || status.size() == 0) {
            status =RegClientFactory.getRegulationStatusClient().getCodeName();
            if (status == null)
                status = new ArrayList<IBasicDTO>();
        }
        return status;
    }

    public void setDecisionMakers(List<IDecisionMakerTypesDTO> decisionMakers) {
        this.decisionMakers = decisionMakers;
    }

    public List<IDecisionMakerTypesDTO> getDecisionMakers() throws DataBaseException {
        if (decisionMakers == null || decisionMakers.size() == 0) {
            try {
                decisionMakers =(List)InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (decisionMakers == null)
                decisionMakers = new ArrayList<IDecisionMakerTypesDTO>();
        }
        return decisionMakers;
    }

    public void setSearchDto(IRegulationSearchDTO searchDto) {
        this.searchDto = searchDto;
    }

    public IRegulationSearchDTO getSearchDto() {
        return searchDto;
    }

    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

// remve by amr galal depend on tab of decitionReference be not mandatory 
//    public boolean validate() {
//        existReference = (getCurrentListSize() > 0);
//        return existReference;
//    }

    public void setExistReference(boolean existReference) {
        this.existReference = existReference;
    }

    public boolean isExistReference() {
        return existReference;
    }

    public void add() {
        getAvailableDataTable().setFirst(0);
        super.add();
    }
    public void backSearchAvailable() {
       if(!isSearchMode()){
        this.setSearchString(null);
       setAvailableDetails(new ArrayList());
        this.removeCurrentFromAvailable();
        // added clear seleted dtos and to make check all box uncheck
         searchDto = RegDTOFactory.createRegulationSearchDTO();
       }  if (getSelectedAvailableDetails() != null && 
            getSelectedAvailableDetails().size() > 0)
            getSelectedAvailableDetails().clear();

        setCheckAllFlagAvailable(false);
        getAvailableDataTable().setFirst(0);
    }
    public void delete() {
    super.delete();
        setAvailableDetails(new ArrayList());
         this.removeCurrentFromAvailable();
    }

}
