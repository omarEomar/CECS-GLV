package com.beshara.csc.nl.reg.presentation.backingbean.regmintypesanddecmkr;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.client.IRegMinRegTypesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegMinRegTypesDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.ITypesDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.dto.RegMinRegTypesDTO;
import com.beshara.csc.nl.reg.business.entity.ISubjectsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class RegMinTypesBean extends LookUpBaseBean {

    private String ministryName = null;
    private String catName = null;
    private IMinistriesDTO ministryDTO; //= OrgDTOFactory.createMinistriesDTO();
    private static final String BACK_BEAN_NAME = "regMinTypesBean";

    public RegMinTypesBean() {

        setPageDTO(new RegMinRegTypesDTO()); //set this the page dto
        setSaveSortingState(true);
        super.setSelectedPageDTO(new RegMinRegTypesDTO());
        //IRegModuleSubjectsDTO
        setClient((IRegMinRegTypesClient)RegClientFactory.getRegMinRegTypesClient());
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSearchTyp("0");
        setLovBaseBean(new LOVBaseBean());
        setDivMainContent("divMainContentWithCnt");

        setLovDiv("divSheardStyle1");
        getLovBaseBean().setLovDiv("divSheardStyle1");
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowWizardBar(true);
        app.setShowLovDiv(true);


        return app;
    }


    public void openListOfValuesDiv() {
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().getSelectedDTOS().clear();
        //        getLovBaseBean().setMultiSelect(true);
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{pageBeanName.lovBaseBean.searchTyp}").setValue(FacesContext.getCurrentInstance(), 
                                                                                                                                "0");
        //getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().unCheck();

        try {

            RegClientFactory.getRegMinRegTypesClient().getAll();
        }
        //            catch (SharedApplicationException e) {
        //                e.printStackTrace();
        //                getLovBaseBean().setMyTableData(new ArrayList(0));
        //            }
        catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        if (getLovBaseBean().isSearchMode()) {
            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().setCleanDataTableFlage(true);
        } else {
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setCleanDataTableFlage(false);
            getLovBaseBean().setSearchQuery("");
        }
        getLovBaseBean().setCodeTypeString(false);
        getLovBaseBean().setReturnMethodName(BACK_BEAN_NAME + 
                                             ".returnLevelsLovDiv");
        getLovBaseBean().setSearchMethod(BACK_BEAN_NAME + ".searcher");
        getLovBaseBean().setCancelSearchMethod(BACK_BEAN_NAME + 
                                               ".cancelSearchLevelsLovDiv");
        getLovBaseBean().setRenderedDropDown("dataT_data_panel , OperationBar , theSelectedNodeId , selectedNodeTreeLevelId , treeDetailsDiv");
        getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + 
                                           " ; focusHighlightedNode ( ) ; ");
        getLovBaseBean().setHighlightedDTOS(null);
        getSelectedDTOS().clear();
        getLoveList();
        getLovBaseBean().getMyDataTable().setFirst(0);

    }

    private void getLoveList() {
        try {
            getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().listAvailableByMinCode(((IMinistriesEntityKey)getMinistryDTO().getCode()).getMinCode()));
        }

        catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        } catch (Exception e) {

            getLovBaseBean().setMyTableData(new ArrayList());
        }

    }

    public String returnLevelsLovDiv() {
        getSelectedDTOS().clear();
        try {
            List<IBasicDTO> insertableDTOS = new ArrayList<IBasicDTO>();
            for (IBasicDTO dto: getLovBaseBean().getSelectedDTOS()) {
                IRegMinRegTypesDTO _regMinRegTypesDTO = 
                    RegDTOFactory.createRegMinRegTypesDTO();
                _regMinRegTypesDTO.setTypesDTO((ITypesDTO)dto);
                _regMinRegTypesDTO.setMinCode(getManagedConstantsBean().getMinCode());
                insertableDTOS.add(_regMinRegTypesDTO);
            }

            String message = 
                getSharedUtil().messageLocator("com.beshara.csc.nl.reg.presentation.resources.reg", 
                                               "massgeConetionSuccess");
            RegClientFactory.getRegMinRegTypesClient().add(insertableDTOS);
            getAll();
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            getSharedUtil().setSuccessMsgValue(message);
        }
        //catch (SharedApplicationException e) {
        //         e.printStackTrace();
        //
        //     }
        catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            this.setShowErrorMsg(true);
            this.setErrorMsg(ex.getCause().getMessage());
            //showDiv();
        }
        this.setCurrentSelectedSearchIndex(getLovBaseBean().getIndexOfSelectedDataInDataTable());
        setSearchMode(false);

        return "";
    }

    public void searcher(Object searchType, Object searchQuery) {
        getLovBaseBean().unCheck();
        getSelectedDTOS().clear();
        Long MinCode = 
            (((IMinistriesEntityKey)getMinistryDTO().getCode()).getMinCode());
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {

                    getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().searchByCodeAndMinCode(new Long(searchQuery.toString()), 
                                                                                                             MinCode));
                    getLovBaseBean().unCheck();
                } else if (searchType.toString().equals("1")) {
                    getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().searchByNameAndMinCode(searchQuery.toString(), 
                                                                                                             MinCode));
                    getLovBaseBean().unCheck();
                }
            }
            setDataTableSearchResult(getLovBaseBean().getMyTableData());
            setSearchResultSize(getLovBaseBean().getMyTableData().size());
            getLovBaseBean().unCheck();
            getSelectedDTOS().clear();
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

    }

    public void cancelSearchLevelsLovDiv() {
        getLovBaseBean().getSelectedDTOS().clear();
        getLovBaseBean().unCheck();
        try {
            getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().listAvailableByMinCode(((IMinistriesEntityKey)getMinistryDTO().getCode()).getMinCode()));
            getLovBaseBean().unCheck();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
            new ArrayList();
        }
        setSearchMode(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();

        getLovBaseBean().setSearchTyp("0");


    }

    public void search() throws DataBaseException, NoResultException {

        try {
            if (getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            List searchResult = this.getBaseSearchResult();

            setMyTableData(searchResult);

            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
            }
            this.repositionPage(0);
          setSearchMode(true);
        }

                 
        catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }

    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult = 
                        RegClientFactory.getRegMinRegTypesClient().searchByCodeAndMinCode((Long)getSearchEntityObj(), 
                                                                                 Long.parseLong(getMinistryDTO().getCode().getKey()));
            } else if (getSearchType().intValue() == 1) {
                searchResult = 
                        RegClientFactory.getRegMinRegTypesClient().searchByNameAndMinCode(formatSearchString(getSearchQuery()), 
                                                                                 Long.parseLong(getMinistryDTO().getCode().getKey()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList(0));
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_display}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    public String getMinistryName() {
        ministryName = getMinistryDTO().getName();
        return ministryName;
    }

    public String getCatName() {
        if (getMinistryDTO() != null) {
            catName = getMinistryDTO().getCatsDTO().getName();
        }

        if (getMinistryDTO() != null) {
            catName = ministryDTO.getCatsDTO().getName();
        }
        return catName;

    }

    public void hideLovDiv() {

        if (!getLovBaseBean().isCleanDataTableFlage()) {
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        }
        unCheck();

        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        if (getLovBaseBean().getSelectedDTOS() != null) {
            getLovBaseBean().getSelectedDTOS().clear();
        }

    }

    public void setMinistryName(String ministryName) {
        this.ministryName = ministryName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setMinistryDTO(IMinistriesDTO ministryDTO) {
        this.ministryDTO = ministryDTO;
    }

    public List getTotalList() {

        List totalList = null;

        try {

            totalList = 
                    RegClientFactory.getRegMinRegTypesClient().getByMinCode(((IMinistriesEntityKey)getMinistryDTO().getCode()).getMinCode());

        } catch (SharedApplicationException ne) {
            totalList = new ArrayList();
            ne.printStackTrace();
        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            getSharedUtil().handleException(e);
        }

        return totalList;

    }

    public IMinistriesDTO getMinistryDTO() {
        if (getManagedConstantsBean().getMinCode() != null && 
            ministryDTO == null) {
            try {
                ministryDTO = 
                        (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(getManagedConstantsBean().getMinCode()));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            } catch (DataBaseException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            }
        }
        return ministryDTO;
    }
}
