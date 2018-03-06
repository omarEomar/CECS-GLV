package com.beshara.csc.inf.presentation.backingbean.maindata.religions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.IGenderReligionClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.GenderReligionDTO;
import com.beshara.csc.inf.business.dto.IGenderReligionDTO;
import com.beshara.csc.inf.business.dto.IReligionsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.GenderReligionEntityKey;
import com.beshara.csc.inf.business.entity.IReligionsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;


public class ListBean extends LookUpBaseBean {

    private List genderList = new ArrayList();
    private List genderEditList = new ArrayList();
    IGenderReligionClient genderReligionClient;
    private boolean showLinkDiv;
    private boolean showEditLinkDiv;
    private IGenderReligionDTO linkDTO;
    private IGenderReligionDTO maleLinkDTO = new GenderReligionDTO();
    private IGenderReligionDTO femaleLinkDTO = new GenderReligionDTO();

    private IGenderReligionDTO maleLinkEditDTO = new GenderReligionDTO();
    private IGenderReligionDTO femaleLinkEditDTO = new GenderReligionDTO();
    private String selectedGender;
    private String selectedGenderEdit;

    SharedUtilBean sharedUtil = getSharedUtil();
    //to show msg (this attribut is already linked before)
    private boolean alreadyLinked = false;
    private IBasicDTO regionsDTO;

    public ListBean() {
        setPageDTO(InfDTOFactory.createReligionsDTO());
        setLinkDTO(InfDTOFactory.createGenderReligionDTO());
        setSelectedPageDTO(InfDTOFactory.createReligionsDTO());
        setClient(InfClientFactory.getReligionsClient());
        genderReligionClient = InfClientFactory.getGenderReligionClient();
        setSingleSelection(true);
        // init LovBaseBean
        setLovBaseBean(new LOVBaseBean());
        setLovDiv("customLovDiv");
        getLovBaseBean().setReturnMethodName("religionsListBean.closeLovDiv");
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowLovDiv(true);
        return app;
    }

    public void fillLOVDataTable() {

        try {
            Long code = ((IReligionsEntityKey)((IReligionsDTO)getSelectedDTOS().get(0)).getCode()).getReligionCode();
            List<IBasicDTO> list = InfClientFactory.getGenderReligionClient().searchByCode(code);
            if (list != null && list.size() != 0) {
                getLovBaseBean().setMyTableData(list);
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divLov);");
            } else {
                getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
                this.setSearchQuery("");
                this.setSearchType(0);
                this.setSearchMode(false);
                this.setShowErrorMsg(true);
                throw new Exception();
            }

        } catch (Exception e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.global_ar",
                                            "global_noTableResults");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
            setSelectedRadio("");
        }

    }

    public String openLovDiv() {
        fillLOVDataTable();
        getLovBaseBean().setCheckAllFlag(false);
        //getLovBaseBean().cancelSearchLovDiv();
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        return "";
    }

    public void closeLovDiv() {
        setLovBaseBean(new LOVBaseBean());
        setPageMode(0);

    }

    public void setGenderList(List genderList) {
        this.genderList = genderList;
    }

    public List getGenderList() {
        if (genderList == null || genderList.size() == 0) {
            try {
                genderList = InfClientFactory.getGenderTypesClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
                genderList = new ArrayList();
            }
        }

        return genderList;
    }

    public void setGenderEditList(List genderEditList) {
        this.genderEditList = genderEditList;
    }

    public List getGenderEditList() {

        if (showEditLinkDiv == true) {


            Long regionsCode =
                ((IReligionsEntityKey)((IReligionsDTO)getSelectedDTOS().get(0)).getCode()).getReligionCode();

            try {
                genderEditList = genderReligionClient.searchByCode(regionsCode);


                System.out.print("list>>>>>>>>>" + genderEditList);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }

        }
        return genderEditList;
    }

    public void preAdd() {
        maleLinkDTO = new GenderReligionDTO();
        femaleLinkDTO = new GenderReligionDTO();
        setPageDTO(InfDTOFactory.createReligionsDTO());
        setPageMode(1);
        super.preAdd();
    }

    public void saveAndNew() {
        maleLinkDTO.setGentypeCode(2L);
        femaleLinkDTO.setGentypeCode(1L);

        List<IGenderReligionDTO> genderReligionList = new ArrayList<IGenderReligionDTO>();
        genderReligionList.add(maleLinkDTO);
        genderReligionList.add(femaleLinkDTO);
        ((IReligionsDTO)getPageDTO()).setGenderReligionList(genderReligionList);

        try {
            //            super.add();
            //            getClient().add((IReligionsDTO)getPageDTO());
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
            genderReligionList = new ArrayList<IGenderReligionDTO>();
            maleLinkDTO = new GenderReligionDTO();
            femaleLinkDTO = new GenderReligionDTO();
            setPageDTO(InfDTOFactory.createReligionsDTO());
            setPageMode(1);
        } catch (DataBaseException e) {
            setSuccess(false);
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        } catch (Exception e) {
            setSuccess(false);
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        }
    }

    public void save() {
        reIntializeMsgs();
        maleLinkDTO.setGentypeCode(2L);
        femaleLinkDTO.setGentypeCode(1L);
        List<IGenderReligionDTO> genderReligionList = new ArrayList<IGenderReligionDTO>();
        List list = new ArrayList<IBasicDTO>();
        genderReligionList.add(maleLinkDTO);
        genderReligionList.add(femaleLinkDTO);
        ((IReligionsDTO)getPageDTO()).setGenderReligionList(genderReligionList);
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            setPageDTO(executeAdd());
            getAll();
            getPageIndex((String)getPageDTO().getCode().getKey());
            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");
            maleLinkDTO = new GenderReligionDTO();
            femaleLinkDTO = new GenderReligionDTO();
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        setSelectedRadio("");


    }

    public void showEditDiv() {
        getPageMode();
        super.showEditDiv();
        Long code = ((IReligionsEntityKey)((IReligionsDTO)getSelectedDTOS().get(0)).getCode()).getReligionCode();
        List<IBasicDTO> list;
        try {
            list = InfClientFactory.getGenderReligionClient().searchByCode(code);
            if (list != null && list.size() != 0) {
                for (IBasicDTO basicDTO : list) {
                    IGenderReligionDTO genderReligionDTO = (IGenderReligionDTO)basicDTO;
                    if (genderReligionDTO.getGentypeCode() == 1L) {
                        setFemaleLinkEditDTO(genderReligionDTO);
                    } else if (genderReligionDTO.getGentypeCode() == 2L) {
                        setMaleLinkEditDTO(genderReligionDTO);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        reIntializeMsgs();
        List<IGenderReligionDTO> genderReligionList = new ArrayList<IGenderReligionDTO>();
        genderReligionList.add(getMaleLinkEditDTO());
        genderReligionList.add(getFemaleLinkEditDTO());
        ((IReligionsDTO)getSelectedPageDTO()).setGenderReligionList(genderReligionList);
        ((IReligionsDTO)getPageDTO()).setCode(((IReligionsDTO)getSelectedDTOS().get(0)).getCode());

        try {
            getClient().update((IReligionsDTO)getSelectedPageDTO());
            getSharedUtil().setSuccessMsgValue("SuccesUpdated");
            super.setShowEdit(false);
            getAll();
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
        }
        unlock();
        maleLinkEditDTO = new GenderReligionDTO();
        femaleLinkEditDTO = new GenderReligionDTO();
    }
    
    //    public void linkGenRel() {
    //
    //
    //        try {
    //            if (selectedGender != null && selectedGender != "") {
    //                Long genTypeCode = Long.parseLong(selectedGender);
    //                Long regionsCode =((IReligionsEntityKey)((IReligionsDTO)regionsDTO).getCode()).getReligionCode();
    //                    //((IReligionsEntityKey)((IReligionsDTO)getSelectedDTOS().get(0)).getCode()).getReligionCode();
    //                linkDTO.setCode(new GenderReligionEntityKey(genTypeCode, regionsCode));
    //                linkDTO.setGentypeCode(genTypeCode);
    //                linkDTO.setReligionCode(regionsCode);
    //                genderReligionClient.add(linkDTO);
    //                this.setLinkDTO(InfDTOFactory.createGenderReligionDTO());
    //                sharedUtil.setSuccessMsgValue("SuccessAdds");
    //            }
    //        } catch (DataBaseException e) {
    //            e.printStackTrace();
    //            this.setShowErrorMsg(true);
    //            sharedUtil.handleException(e, "com.beshara.csc.inf.presentation.resources.inf", "FailureInLink");
    //            this.setErrorMsg(sharedUtil.getErrMsgValue());
    //
    //        } catch (SharedApplicationException e) {
    //            this.setShowErrorMsg(true);
    //            sharedUtil.handleException(e);
    //            this.setErrorMsg("FailureInAdd");
    //
    //
    //        } catch (Exception e) {
    //            this.setShowErrorMsg(true);
    //            this.setErrorMsg("FailureInAdd");
    //            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");
    //            this.setErrorMsg(sharedUtil.getErrMsgValue());
    //        }
    //        setSelectedGender("");
    //    }

    public void linkAndNew() {
        try {
            if (selectedGender != null && selectedGender != "") {
                reIntializeMsgs();
                IGenderReligionClient genderReligionClient = InfClientFactory.getGenderReligionClient();
                Long genTypeCode = Long.parseLong(selectedGender);
                Long regionsCode =
                    ((IReligionsEntityKey)((IReligionsDTO)getSelectedDTOS().get(0)).getCode()).getReligionCode();
                linkDTO.setCode(new GenderReligionEntityKey(genTypeCode, regionsCode));
                linkDTO.setGentypeCode(genTypeCode);
                linkDTO.setReligionCode(regionsCode);
                genderReligionClient.add(linkDTO);
                this.setSuccess(true);
                this.setLinkDTO(InfDTOFactory.createGenderReligionDTO());
            }

        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setAlreadyLinked(true);

        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        setSelectedGender("");

    }

    public void loadgenRegNameEdit() {
        List list = null;

        Long genTypeCode = Long.parseLong(selectedGenderEdit);
        Long regionsCode =
            ((IReligionsEntityKey)((IReligionsDTO)getSelectedDTOS().get(0)).getCode()).getReligionCode();
        try {
            list = genderReligionClient.searchByGenRegCode(genTypeCode, regionsCode);
            if (list.size() != 0) {
                linkDTO = (IGenderReligionDTO)list.get(0);
            } else {
                linkDTO.setGenregName("");
            }

        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }


    }

    public void saveEdit() {
        try {

            linkDTO.setCode(new GenderReligionEntityKey(linkDTO.getGentypeCode(), linkDTO.getReligionCode()));
            genderReligionClient.update(linkDTO);
            this.setLinkDTO(InfDTOFactory.createGenderReligionDTO());
            sharedUtil.setSuccessMsgValue("SuccesUpdated");
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e, "com.beshara.csc.inf.presentation.resources.inf", "FailureInLink");
            this.setErrorMsg(sharedUtil.getErrMsgValue());

        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");
            this.setErrorMsg(sharedUtil.getErrMsgValue());
        }
        setSelectedGender("");
    }


    public void cancelLink() {
        showEditLinkDiv = false;
        setPageMode(0);
        setShowLinkDiv(false);
        setShowEditLinkDiv(false);
        setLinkDTO(InfDTOFactory.createGenderReligionDTO());
        setSelectedPageDTO(InfDTOFactory.createReligionsDTO());
        selectedGender = "";
        selectedGenderEdit = "";
        reIntializeMsgs();


    }


    public IBasicDTO preLink() {
        reIntializeMsgs();
        setShowLinkDiv(true);
        return null;
    }


    public IBasicDTO preLinkEdit() {
        reIntializeMsgs();
        setShowEditLinkDiv(true);
        return null;
    }


    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createReligionsDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void setShowLinkDiv(boolean showLinkDiv) {
        this.showLinkDiv = showLinkDiv;
    }

    public boolean isShowLinkDiv() {
        return showLinkDiv;
    }


    public void setLinkDTO(IGenderReligionDTO linkDTO) {
        this.linkDTO = linkDTO;
    }

    public IGenderReligionDTO getLinkDTO() {
        return linkDTO;
    }

    public void setAlreadyLinked(boolean alreadyLined) {
        this.alreadyLinked = alreadyLined;
    }

    public boolean isAlreadyLinked() {
        return alreadyLinked;
    }

    public void setShowEditLinkDiv(boolean showEditLinkDiv) {
        this.showEditLinkDiv = showEditLinkDiv;
    }

    public boolean isShowEditLinkDiv() {
        return showEditLinkDiv;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public void setSelectedGenderEdit(String selectedGenderEdit) {
        this.selectedGenderEdit = selectedGenderEdit;
    }

    public String getSelectedGenderEdit() {
        return selectedGenderEdit;
    }


    public void setRegionsDTO(IBasicDTO regionsDTO) {
        this.regionsDTO = regionsDTO;
    }

    public IBasicDTO getRegionsDTO() {
        return regionsDTO;
    }

    public void setMaleLinkDTO(IGenderReligionDTO maleLinkDTO) {
        this.maleLinkDTO = maleLinkDTO;
    }

    public IGenderReligionDTO getMaleLinkDTO() {
        return maleLinkDTO;
    }

    public void setFemaleLinkDTO(IGenderReligionDTO femaleLinkDTO) {
        this.femaleLinkDTO = femaleLinkDTO;
    }

    public IGenderReligionDTO getFemaleLinkDTO() {
        return femaleLinkDTO;
    }

    public void setMaleLinkEditDTO(IGenderReligionDTO maleLinkEditDTO) {
        this.maleLinkEditDTO = maleLinkEditDTO;
    }

    public IGenderReligionDTO getMaleLinkEditDTO() {
        return maleLinkEditDTO;
    }

    public void setFemaleLinkEditDTO(IGenderReligionDTO femaleLinkEditDTO) {
        this.femaleLinkEditDTO = femaleLinkEditDTO;
    }

    public IGenderReligionDTO getFemaleLinkEditDTO() {
        return femaleLinkEditDTO;
    }
}
