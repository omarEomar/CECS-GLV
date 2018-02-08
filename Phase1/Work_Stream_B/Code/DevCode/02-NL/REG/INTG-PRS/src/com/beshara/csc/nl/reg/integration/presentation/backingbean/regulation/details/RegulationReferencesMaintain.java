package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.reg.business.client.IRefrencesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRefrencesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationScopesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.ITypesDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRefrencesEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;


public class RegulationReferencesMaintain extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List<ITypesDTO> types;
    private List<IYearsDTO> issuanceYears;
    private List<IRegulationScopesDTO> scopes;
    private List<IDecisionMakerTypesDTO> decisionMakers;
    private IRegulationSearchDTO searchDto = RegDTOFactory.createRegulationSearchDTO();
    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;

    public RegulationReferencesMaintain() {
        setClient(RegClientFactory.getRefrencesClient());
        //BeansUtil.setValue("regulationMaintainBean.content1Div","divContent1Dynamic");
        //BeansUtil.setValue("regulationMaintainBean.lookupAddDiv","regulationDivSearch");
        setContent1Div("divContent1Dynamic");
        setLookupAddDiv("regulationDivSearch");
        setDivMainContentMany("divREGMainContentMany");
//        setSaveSortingState(true);
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        return app;
    }

    public void getListAvailable() throws DataBaseException {
    

        //        if (getMasterEntityKey() != null) {
        //            try {
        //                setAvailableDetails(((IRefrencesClient)getClient()).listAvailableEntitiesCenter(getMasterEntityKey()));
        //            } catch (SharedApplicationException e) {
        //                setAvailableDetails(new ArrayList<BasicDTO>());
        //            }
        //        } else {
        //            try {
        //                setAvailableDetails(((IRefrencesClient)getClient()).listAvailableEntitiesCenter(new RegulationsEntityKey()));
        //                super.getListAvailable();
        //            } catch (SharedApplicationException e) {
        //                setAvailableDetails(new ArrayList<BasicDTO>());
        //            }
        //        }
            if(!isSearchMode())
                setAvailableDetails(new ArrayList<IBasicDTO>(0));
                
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return ((IRefrencesDTO)dto).getRefregualtionDTO();
    }


    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        
        IRegulationsDTO regDTO=null;
        IRegulationsEntityKey regEntityKey=null;
        IRefrencesEntityKey  refrencesEntityKey=null;
        
        IRegulationsEntityKey regRefEntityKey = (IRegulationsEntityKey)dto.getCode();
        
        IRefrencesDTO refrencesDTO = RegDTOFactory.createRefrencesDTO();
        
        refrencesDTO.setRefregualtionDTO((IRegulationsDTO)dto);
        
        if (this.getMasterEntityKey() != null) {
            regEntityKey = (IRegulationsEntityKey)this.getMasterEntityKey();
            regDTO=RegDTOFactory.createRegulationsDTO();
            regDTO.setCode(regEntityKey);
            refrencesDTO.setRegualtionDTO(regDTO);
        }
        
        if(regEntityKey !=null)
            refrencesEntityKey=RegEntityKeyFactory.createRefrencesEntityKey(regEntityKey.getRegtypeCode(),regEntityKey.getRegyearCode(),regEntityKey.getRegulationNumber(),regRefEntityKey.getRegtypeCode(),regRefEntityKey.getRegyearCode(),regRefEntityKey.getRegulationNumber());
        else
            refrencesEntityKey=RegEntityKeyFactory.createRefrencesEntityKey(null,null,null,regRefEntityKey.getRegtypeCode(),regRefEntityKey.getRegyearCode(),regRefEntityKey.getRegulationNumber());
            
        refrencesDTO.setCode(refrencesEntityKey);

        return refrencesDTO;
    }

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
        try {
            searchDto.setMasterCode(getMasterEntityKey() == null ? 
                                    RegEntityKeyFactory.createRegulationsEntityKey() : 
                                    getMasterEntityKey());
            setAvailableDetails(((IRefrencesClient)getClient()).filterAvailableEntitiesCenter(searchDto));

        } catch (SharedApplicationException e) {
            setSearchMode(true);
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.searchAvailable();
    }

    public void setSearchDto(IRegulationSearchDTO searchDto) {
        this.searchDto = searchDto;
    }

    public IRegulationSearchDTO getSearchDto() {
        return searchDto;
    }

    public void setTypes(List<ITypesDTO> types) {
        this.types = types;
    }

    public List<ITypesDTO> getTypes() throws DataBaseException {
        if (types == null || types.size() == 0) {
            types = 
                    (List)RegClientFactory.getTypesClient().getCodeNameCenter(ISystemConstant.REGULATION_VALIDITY_REGULATION);

            if (types == null)
                types = new ArrayList<ITypesDTO>();
        }
        return types;
    }

    public void setIssuanceYears(List<IYearsDTO> issuanceYears) {
        this.issuanceYears = issuanceYears;
    }

    public List<IYearsDTO> getIssuanceYears() {
        if (issuanceYears == null || issuanceYears.size() == 0) {

            try {
                issuanceYears = InfClientFactory.getYearsClient().getCodeName();
            }  catch (Exception e) {
                e.printStackTrace();
            }
            if (issuanceYears == null)
                issuanceYears = new ArrayList<IYearsDTO>();
        }
        return issuanceYears;
    }

    public void setScopes(List<IRegulationScopesDTO> scopes) {
        this.scopes = scopes;
    }

    public List<IRegulationScopesDTO> getScopes() throws DataBaseException {
        if (scopes == null || scopes.size() == 0) {
            scopes = 
                    (List)RegClientFactory.getRegulationScopesClient().getCodeNameCenter();

            if (scopes == null)
                scopes = new ArrayList<IRegulationScopesDTO>();
        }
        return scopes;
    }

    public void setDecisionMakers(List<IDecisionMakerTypesDTO> decisionMakers) {
        this.decisionMakers = decisionMakers;
    }

    public List<IDecisionMakerTypesDTO> getDecisionMakers()  {
        if (decisionMakers == null || decisionMakers.size() == 0) {

            try {
                decisionMakers =
                    (List)InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (decisionMakers == null)
                decisionMakers = new ArrayList<IDecisionMakerTypesDTO>();
        }
        return decisionMakers;
    }

    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
    
        setSearchMode(false);
        this.getListAvailable();
        searchDto = RegDTOFactory.createRegulationSearchDTO();
        searchDto.setRegulationType(itemSelectionRequiredValue);
        searchDto.setRegulationYear(itemSelectionRequiredValue);
        searchDto.setDecisionMakerType(itemSelectionRequiredValue);
        searchDto.setRegulationScopes(itemSelectionRequiredValue);
        
        this.removeCurrentFromAvailable();
        
        // added clear seleted dtos and to make check all box uncheck
        if(getSelectedAvailableDetails()!=null && getSelectedAvailableDetails().size()>0)
                getSelectedAvailableDetails().clear();
          
         setCheckAllFlagAvailable(false); ;
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
