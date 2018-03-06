package com.beshara.csc.inf.presentation.backingbean.maindata.kwtcitizensresident;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class ListBean extends LookUpBaseBean {
    IKwtCitizensResidentsSearchDTO kwtCitizensSearchDTO = InfDTOFactory.createKwtCitizensResidentsSearchDTO();
    private List<IBasicDTO> maritalStatusTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> nationalties = new ArrayList<IBasicDTO>();

    @SuppressWarnings("compatibility:1363948431298451683")
    private static final long serialVersionUID = -2083447614359694540L;

    public ListBean() {
        setClient(InfClientFactory.getKwtCitizensResidentsClient());
        setSelectedPageDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setPageDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setSingleSelection(true);
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        System.out.println("Calling search()...");
        this.setSearchMode(true);

        if (isUsingPaging()) {

            setUpdateMyPagedDataModel(true);

            setPagingRequestDTO(new PagingRequestDTO("baseSearchWithPaging"));

            setOldPageIndex(0);
            setCurrentPageIndex(1);

        } else {

            List searchResult = this.getBaseSearchResult();

            setMyTableData(searchResult);

        }
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
        this.repositionPage(0);
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }

    public void cancelSearch() throws DataBaseException {
        kwtCitizensSearchDTO = InfDTOFactory.createKwtCitizensResidentsSearchDTO();
        super.cancelSearch();
    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();
        IKwtCitizensResidentsSearchDTO dto = InfDTOFactory.createKwtCitizensResidentsSearchDTO();
        try {
            if (kwtCitizensSearchDTO.getCivilId() != null) {
                dto.setCivilId(kwtCitizensSearchDTO.getCivilId());
            }
            if (kwtCitizensSearchDTO.getName() != null && !kwtCitizensSearchDTO.getName().equalsIgnoreCase("")) {
                dto.setName(kwtCitizensSearchDTO.getName());
            }
            if (kwtCitizensSearchDTO.getBirthDate() != null) {
                dto.setBirthDate(kwtCitizensSearchDTO.getBirthDate());
            }
            if (kwtCitizensSearchDTO.getMaritalStatusCode() != null) {
                dto.setMaritalStatusCode(kwtCitizensSearchDTO.getMaritalStatusCode());
            }
            if (kwtCitizensSearchDTO.getNationality() != null) {
                dto.setNationality(kwtCitizensSearchDTO.getNationality());
            }
            searchResult = InfClientFactory.getKwtCitizensResidentsClient().searchAboutCitizens(dto);
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList(0));
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_display}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            setMyTableData(new ArrayList());
        }

        return searchResult;
    }

    public void cancelAdd() {
        super.cancelAdd();
        setPageMode(0);
        reInitializePageDTO();
    }


    public void cancelEdit() {
        setPageMode(0);
    }

    public void setKwtCitizensSearchDTO(IKwtCitizensResidentsSearchDTO kwtCitizensSearchDTO) {
        this.kwtCitizensSearchDTO = kwtCitizensSearchDTO;
    }

    public IKwtCitizensResidentsSearchDTO getKwtCitizensSearchDTO() {
        return kwtCitizensSearchDTO;
    }

    public void setMaritalStatusTyps(List<IBasicDTO> maritalStatusTyps) {
        this.maritalStatusTyps = maritalStatusTyps;
    }

    public List<IBasicDTO> getMaritalStatusTyps() {
        if (maritalStatusTyps.size() == 0) {
            try {
                maritalStatusTyps = InfClientFactory.getMaritalStatusClient().getAll();
            } catch (DataBaseException e) {
                e.printStackTrace();
                maritalStatusTyps = new ArrayList<IBasicDTO>();
            } catch (Exception e) {
                e.printStackTrace();
                maritalStatusTyps = new ArrayList<IBasicDTO>();
            }
        }
        if (maritalStatusTyps == null)
            maritalStatusTyps = new ArrayList<IBasicDTO>();
        return maritalStatusTyps;
    }

    public void setNationalties(List<IBasicDTO> nationalties) {
        this.nationalties = nationalties;
    }

    public List<IBasicDTO> getNationalties() {
        if (nationalties.size() == 0) {
            try {
                nationalties = InfClientFactory.getCountriesClient().getCodeName();
            } catch (DataBaseException e) {
                nationalties = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                nationalties = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (nationalties == null)
            nationalties = new ArrayList();
        return nationalties;
    }
}

