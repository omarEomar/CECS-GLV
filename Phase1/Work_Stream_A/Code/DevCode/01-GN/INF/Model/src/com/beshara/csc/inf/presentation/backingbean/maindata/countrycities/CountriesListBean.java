package com.beshara.csc.inf.presentation.backingbean.maindata.countrycities;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.IGenderCountryClient;
import com.beshara.csc.inf.business.client.IGenderTypesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.MasterDetailBaseBean;

import java.util.ArrayList;
import java.util.List;


public class CountriesListBean extends MasterDetailBaseBean {

    private List languagesList = new ArrayList();
    private List curreniesList = new ArrayList();
    private List<ICountryCitiesDTO> cities = new ArrayList<ICountryCitiesDTO>();
    private IGenderCountryDTO maleNationalityDTO = InfDTOFactory.createGenderCountryDTO();
    private IGenderCountryDTO femaleNationalityDTO = InfDTOFactory.createGenderCountryDTO();
    private IGenderCountryDTO maleNationalityEditDTO = InfDTOFactory.createGenderCountryDTO();
    private IGenderCountryDTO femaleNationalityEditDTO = InfDTOFactory.createGenderCountryDTO();
    private Long genderType;
    private static Long GENDER_TYPE_FEMALE = 1L;
    private static Long GENDER_TYPE_MALE = 2L;
    
    public CountriesListBean() { 
        setClient(InfClientFactory.getCountriesClient());
        setDetailsUseCase("Cities_Page");
        setDetailsBackBeanName("citiesListBean");
        setMasterBackBeanName("countriesListBean");
        setSelectedPageDTO(InfDTOFactory.createCountriesDTO());
        setPageDTO(InfDTOFactory.createCountriesDTO());
        setSingleSelection(true);
        setSaveSortingState(true);
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowCustomDiv1(true);
        return app;
    }


    //    public void getDetailsList() {
    //        if (getSelectedPageDTO() != null &&
    //            getSelectedPageDTO().getCode() != null) {
    //            try {
    //                setMasterDetailList(InfClientFactory.getCountryCitiesClient().getCities(((ICountriesEntityKey)getSelectedPageDTO().getCode()).getCntryCode()));
    //            } catch (SharedApplicationException e) {
    //                setMasterDetailList(new ArrayList<IBasicDTO>());
    //                e.printStackTrace();
    //            } catch (Exception e) {
    //                setMasterDetailList(new ArrayList<IBasicDTO>());
    //                e.printStackTrace();
    //            }
    //        }
    //    }
    public void preAdd(){
        maleNationalityDTO=InfDTOFactory.createGenderCountryDTO();
        femaleNationalityDTO=InfDTOFactory.createGenderCountryDTO();
        super.preAdd();
    }

    public void save(){
        try {
            IGenderTypesClient genderTypesClient = InfClientFactory.getGenderTypesClient();
            ((ICountriesDTO)getPageDTO()).setGenderCountryDTOList(new ArrayList<IGenderCountryDTO>());
            
            maleNationalityDTO.setCountriesDTO(((ICountriesDTO)getPageDTO()));
            maleNationalityDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(2L)));

            femaleNationalityDTO.setCountriesDTO(((ICountriesDTO)getPageDTO()));
            femaleNationalityDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(1L)));
            
            ((ICountriesDTO)getPageDTO()).getGenderCountryDTOList().add(maleNationalityDTO);
            ((ICountriesDTO)getPageDTO()).getGenderCountryDTOList().add(femaleNationalityDTO);
            super.save();
            
            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().add(this.getPageDTO());
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "saving_natiality_failure");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "saving_natiality_failure");
        }
       
    }
    public void  saveAndNew(){
        try {
            IGenderTypesClient genderTypesClient = InfClientFactory.getGenderTypesClient();
            ((ICountriesDTO)getPageDTO()).setGenderCountryDTOList(new ArrayList<IGenderCountryDTO>());
            
            maleNationalityDTO.setCountriesDTO(((ICountriesDTO)getPageDTO()));
            maleNationalityDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(2L)));

            femaleNationalityDTO.setCountriesDTO(((ICountriesDTO)getPageDTO()));
            femaleNationalityDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(1L)));
            
            ((ICountriesDTO)getPageDTO()).getGenderCountryDTOList().add(maleNationalityDTO);
            ((ICountriesDTO)getPageDTO()).getGenderCountryDTOList().add(femaleNationalityDTO);
        
            setPageDTO(executeAdd());
            getAll();
            getPageIndex((String)getPageDTO().getCode().getKey());
            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);

            setSuccess(true);
            ((ICountriesDTO)getPageDTO()).setGenderCountryDTOList(new ArrayList<IGenderCountryDTO>());
            maleNationalityDTO = InfDTOFactory.createGenderCountryDTO();
            femaleNationalityDTO = InfDTOFactory.createGenderCountryDTO();
            setPageDTO(InfDTOFactory.createCountriesDTO());
            setPageMode(1);
        } catch (Exception e) {
            setSuccess(false);
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        }
        
    
    }
    
    public void edit(){
        try {
            IGenderTypesClient genderTypesClient = InfClientFactory.getGenderTypesClient();
            ((ICountriesDTO)getSelectedPageDTO()).setGenderCountryDTOList(new ArrayList<IGenderCountryDTO>());
            maleNationalityEditDTO.setCountriesDTO(((ICountriesDTO)getSelectedPageDTO()));
            maleNationalityEditDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(GENDER_TYPE_MALE)));
            maleNationalityEditDTO.setCode(InfEntityKeyFactory.createGenderCountryEntityKey(GENDER_TYPE_MALE,(((ICountriesEntityKey)((ICountriesDTO)getSelectedPageDTO()).getCode())).getCntryCode()));
            femaleNationalityEditDTO.setCountriesDTO(((ICountriesDTO)getSelectedPageDTO()));
            femaleNationalityEditDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(GENDER_TYPE_FEMALE)));
            femaleNationalityEditDTO.setCode(InfEntityKeyFactory.createGenderCountryEntityKey(GENDER_TYPE_FEMALE,(((ICountriesEntityKey)((ICountriesDTO)getSelectedPageDTO()).getCode())).getCntryCode()));
            
            ((ICountriesDTO)getSelectedPageDTO()).getGenderCountryDTOList().add(maleNationalityEditDTO);
            ((ICountriesDTO)getSelectedPageDTO()).getGenderCountryDTOList().add(femaleNationalityEditDTO);
            
            
            super.edit();
            
            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().add(this.getPageDTO());
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "saving_natiality_failure");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "saving_natiality_failure");
        }
    }
    public void showEditDiv(){
        maleNationalityDTO=InfDTOFactory.createGenderCountryDTO();
        femaleNationalityDTO=InfDTOFactory.createGenderCountryDTO();
        IGenderCountryClient genderCountryClient = InfClientFactory.getGenderCountryClient();
        List<IBasicDTO> genderCountryList = new ArrayList<IBasicDTO>();

        try {
            genderCountryList =
                    genderCountryClient.getGenderNames(((ICountriesEntityKey)((ICountriesDTO)getSelectedDTOS().get(0)).getCode()).getCntryCode());
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        for (IBasicDTO basicDTO : genderCountryList) {
            IGenderCountryDTO genderCountryDTO=(IGenderCountryDTO)basicDTO;
            if (genderCountryDTO.getGenderTypesDTO().getGentypeCode() == 2L) {
                maleNationalityEditDTO = genderCountryDTO;
            } else if (genderCountryDTO.getGenderTypesDTO().getGentypeCode() == 1L) {
                femaleNationalityEditDTO = genderCountryDTO;
            }
        }
        super.showEditDiv();
    }

    
    public void showCountryDetails() {
        super.showEditDiv();
        if (getSelectedPageDTO() != null && getSelectedPageDTO().getCode() != null) {
            try {
                setCities((List)InfClientFactory.getCountryCitiesClient().getCities(((ICountriesEntityKey)getSelectedPageDTO().getCode()).getCntryCode()));
            } catch (SharedApplicationException e) {
                setCities(new ArrayList<ICountryCitiesDTO>());
                e.printStackTrace();
            } catch (Exception e) {
                setCities(new ArrayList<ICountryCitiesDTO>());
                e.printStackTrace();
            }
        }
        //        setMasterDetailList(new ArrayList<IBasicDTO>());
    }

    public void setLanguagesList(List languagesList) {
        this.languagesList = languagesList;
    }

    public List getLanguagesList() {
        if (languagesList == null || languagesList.size() == 0) {
            try {
                languagesList = InfClientFactory.getLanguagesClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
                languagesList = new ArrayList();
            }
        }
        return languagesList;
    }

    public void setCurreniesList(List curreniesList) {
        this.curreniesList = curreniesList;
    }

    public List getCurreniesList() {
        if (curreniesList == null || curreniesList.size() == 0) {
            try {
                curreniesList = InfClientFactory.getCurrenciesClient().getCodeName();
            } catch (Exception e) {
                curreniesList = new ArrayList();
                e.printStackTrace();
            }
        }
        return curreniesList;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createCountriesDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void setCities(List<ICountryCitiesDTO> cities) {
        this.cities = cities;
    }

    public List<ICountryCitiesDTO> getCities() {
        return cities;
    }
    public void setGenderType(Long genderType) {
        this.genderType = genderType;
    }

    public Long getGenderType() {
        return genderType;
    }

    public void setMaleNationalityDTO(IGenderCountryDTO maleNationalityDTO) {
        this.maleNationalityDTO = maleNationalityDTO;
    }

    public IGenderCountryDTO getMaleNationalityDTO() {
        return maleNationalityDTO;
    }

    public void setFemaleNationalityDTO(IGenderCountryDTO femaleNationalityDTO) {
        this.femaleNationalityDTO = femaleNationalityDTO;
    }

    public IGenderCountryDTO getFemaleNationalityDTO() {
        return femaleNationalityDTO;
    }

    public void setMaleNationalityEditDTO(IGenderCountryDTO maleNationalityEditDTO) {
        this.maleNationalityEditDTO = maleNationalityEditDTO;
    }

    public IGenderCountryDTO getMaleNationalityEditDTO() {
        return maleNationalityEditDTO;
    }

    public void setFemaleNationalityEditDTO(IGenderCountryDTO femaleNationalityEditDTO) {
        this.femaleNationalityEditDTO = femaleNationalityEditDTO;
    }

    public IGenderCountryDTO getFemaleNationalityEditDTO() {
        return femaleNationalityEditDTO;
    }
}
