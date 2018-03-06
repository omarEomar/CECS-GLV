package com.beshara.csc.inf.presentation.backingbean.maindata.maritalstatus;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.GenderMaritalClientImpl;
import com.beshara.csc.inf.business.client.IGenderMaritalClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.GenderMaritalDTO;
import com.beshara.csc.inf.business.dto.IGenderMaritalDTO;
import com.beshara.csc.inf.business.dto.IMaritalStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.MaritalStatusDTO;
import com.beshara.csc.inf.business.entity.GenderMaritalEntityKey;
import com.beshara.csc.inf.business.entity.IMaritalStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Edited on 25/11/2013 by Abdelrhman Fathy
 *
 * */
public class ListBean extends LookUpBaseBean {
    private boolean showLinkDiv;
    private List genderTypesList;
    private List genderEditList = new ArrayList();
    private boolean showEditLinkDiv;
    private String selectedGender;
    private String selectedGenderMarEdit;
    IGenderMaritalClient genderMaritalClient;


    SharedUtilBean sharedUtil = getSharedUtil();

    IGenderMaritalDTO genderMaritalDTO;
    //to show msg (this attribut is already linked before)
    private boolean alreadyLinked;

    private IGenderMaritalDTO maleLinkDTO = new GenderMaritalDTO();
    private IGenderMaritalDTO femaleLinkDTO = new GenderMaritalDTO();
    
    private IGenderMaritalDTO maleLinkEditDTO = new GenderMaritalDTO();
    private IGenderMaritalDTO femaleLinkEditDTO = new GenderMaritalDTO();

    public ListBean() {
        setPageDTO(InfDTOFactory.createMaritalStatusDTO());
        setSelectedPageDTO(InfDTOFactory.createMaritalStatusDTO());
        setClient(InfClientFactory.getMaritalStatusClient());
        setGenderMaritalDTO(InfDTOFactory.createGenderMaritalDTO());
        genderMaritalClient = InfClientFactory.getGenderMaritalClient();
        setSingleSelection(true);

        // init LovBaseBean
        setLovBaseBean(new LOVBaseBean());
        setLovDiv("customLovDiv");
        getLovBaseBean().setReturnMethodName("maritalStatusListBean.closeLovDiv");
        //setShowEditLinkDiv(true);
        //setShowEditLinkDiv(false);
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowLovDiv(true);
        //        app.setShowCustomDiv2(true);
        //        app.setShowCustomDiv1(true);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createMaritalStatusDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public IBasicDTO preLinkage() {

        reIntializeMsgs();
        //setPageMode(3);
        setShowLinkDiv(true);
        return null;
    }

    public void generateGenderTypesList() {
        if (genderTypesList == null || genderTypesList.size() == 0) {
            try {
                genderTypesList = InfClientFactory.getGenderTypesClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
                genderTypesList = new ArrayList();
            }
        }
    }

    public void saveLinkage() {
        try {
            Long genTypeCode = Long.parseLong(selectedGender);
            Long marStatusCode = ((IMaritalStatusDTO)getSelectedDTOS().get(0)).getMarstatusCode();
            genderMaritalDTO.setCode(new GenderMaritalEntityKey(genTypeCode, marStatusCode));
            genderMaritalDTO.setMarstatusCode(marStatusCode);
            genderMaritalDTO.setGentypeCode(genTypeCode);
            genderMaritalClient.add(genderMaritalDTO);
            getSharedUtil().setSuccessMsgValue("SuccessAdds");
            setShowLinkDiv(false);
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e, "com.beshara.csc.inf.presentation.resources.inf", "FailureInLink");
            this.setErrorMsg(sharedUtil.getErrMsgValue());

        } catch (Exception e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e, "com.beshara.csc.inf.presentation.resources.inf", "FailureInAdd");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        } finally {
            setGenderMaritalDTO(InfDTOFactory.createGenderMaritalDTO());
        }
        genderMaritalDTO = InfDTOFactory.createGenderMaritalDTO();
        selectedGender = "";
        setShowLinkDiv(false);
    }

    public void cancelLinkage() {
        setPageMode(0);
        genderMaritalDTO = InfDTOFactory.createGenderMaritalDTO();
        setShowLinkDiv(false);
        setShowEditLinkDiv(false);
        selectedGender = "";
        reIntializeMsgs();

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

    public void fillLOVDataTable() {
        try {
            Long code = ((IMaritalStatusDTO)getSelectedDTOS().get(0)).getMarstatusCode();
            List<IBasicDTO> list = InfClientFactory.getGenderMaritalClient().searchByCode(code);

            getLovBaseBean().setMyTableData(list);

        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void preAdd() {
        maleLinkDTO = new GenderMaritalDTO();
        femaleLinkDTO = new GenderMaritalDTO();
        setPageDTO(InfDTOFactory.createMaritalStatusDTO());
        setPageMode(1);
        super.preAdd();
    }

    public void save() {
        maleLinkDTO.setGentypeCode(2L);
        femaleLinkDTO.setGentypeCode(1L);

        List<IGenderMaritalDTO> genderMaritalDTOList = new ArrayList<IGenderMaritalDTO>();
        genderMaritalDTOList.add(maleLinkDTO);
        genderMaritalDTOList.add(femaleLinkDTO);
        ((IMaritalStatusDTO)getPageDTO()).setGenderMaritalDTOList(genderMaritalDTOList);

        try {
            super.add();
            maleLinkDTO = new GenderMaritalDTO();
            femaleLinkDTO = new GenderMaritalDTO();
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        } catch (Exception e) {
            //            this.setShowErrorMsg(true);
            //            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                            "EnteredNameAlreadyExist");
            //            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            //            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        }
    }

    public void saveAndNew() {
        maleLinkDTO.setGentypeCode(2L);
        femaleLinkDTO.setGentypeCode(1L);

        List<IGenderMaritalDTO> genderMaritalList = new ArrayList<IGenderMaritalDTO>();
        genderMaritalList.add(maleLinkDTO);
        genderMaritalList.add(femaleLinkDTO);
        ((IMaritalStatusDTO)getPageDTO()).setGenderMaritalDTOList(genderMaritalList);

        try {
            //                        super.add();
//            getClient().add((IMaritalStatusDTO)getPageDTO());
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
            genderMaritalList = new ArrayList<IGenderMaritalDTO>();
            maleLinkDTO = new GenderMaritalDTO();
            femaleLinkDTO = new GenderMaritalDTO();
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


    public void showEditDiv() {
        setPageMode(2);
        getHighlightedDTOS().clear();
        super.showEditDiv();
        Long code =
            ((IMaritalStatusEntityKey)((IMaritalStatusDTO)getSelectedDTOS().get(0)).getCode()).getMarstatusCode();
        List<IBasicDTO> list;
        try {
            list = ((GenderMaritalClientImpl)InfClientFactory.getGenderMaritalClient()).searchByGenMarCode(1L, code);

            if (list != null && list.size() != 0) {
                setFemaleLinkEditDTO((IGenderMaritalDTO)list.get(0));
            }

            list = ((GenderMaritalClientImpl)InfClientFactory.getGenderMaritalClient()).searchByGenMarCode(2L, code);
            if (list != null && list.size() != 0) {
                setMaleLinkEditDTO((IGenderMaritalDTO)list.get(0));
            }

        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }

    }

    public void edit() {
        List<IGenderMaritalDTO> genderMaritalEditList = new ArrayList<IGenderMaritalDTO>();
        genderMaritalEditList.add(getMaleLinkEditDTO());
        genderMaritalEditList.add(getFemaleLinkEditDTO());
        ((IMaritalStatusDTO)getSelectedPageDTO()).setGenderMaritalDTOList(genderMaritalEditList);
        ((IMaritalStatusDTO)getPageDTO()).setCode(((IMaritalStatusDTO)getSelectedDTOS().get(0)).getCode());
        ((IMaritalStatusDTO)getPageDTO()).setGenderMaritalDTOList(genderMaritalEditList);

        try {
            super.edit();
            //getClient().update((IReligionsDTO)getSelectedPageDTO());
            super.setShowEdit(false);

        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "EnteredNameAlreadyExist");
            getSharedUtil().setErrMsgValue("EnteredNameAlreadyExist");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
        }
        maleLinkEditDTO = new GenderMaritalDTO();
        femaleLinkEditDTO = new GenderMaritalDTO();
    }

    public void linkAndNew() {
        try {
            reIntializeMsgs();
            Long genTypeCode = Long.parseLong(selectedGender);
            Long marStatusCode = ((IMaritalStatusDTO)getSelectedDTOS().get(0)).getMarstatusCode();
            genderMaritalDTO.setCode(new GenderMaritalEntityKey(genTypeCode, marStatusCode));
            genderMaritalDTO.setMarstatusCode(marStatusCode);
            genderMaritalDTO.setGentypeCode(genTypeCode);
            genderMaritalClient.add(genderMaritalDTO);
            setGenderMaritalDTO(InfDTOFactory.createGenderMaritalDTO());
            this.setSuccess(true);

        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setAlreadyLinked(true);
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e);
            this.setErrorMsg("FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInAdd");

        }

        genderMaritalDTO = InfDTOFactory.createGenderMaritalDTO();
        selectedGender = "";
    }

    public void loadGenderEditList() {
        Long marstatusCode =
            ((IMaritalStatusEntityKey)((IMaritalStatusDTO)getSelectedDTOS().get(0)).getCode()).getMarstatusCode();

        try {
            genderEditList = genderMaritalClient.searchByCode(marstatusCode);
            //System.out.print("list>>>>>>>>>" + genderEditList);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }


    public void loadgenMarNameEdit() {
        List list = null;
        Long genTypeCode = Long.parseLong(selectedGenderMarEdit);
        Long marstatusCode =
            ((IMaritalStatusEntityKey)((MaritalStatusDTO)getSelectedDTOS().get(0)).getCode()).getMarstatusCode();
        try {
            list = ((GenderMaritalClientImpl)genderMaritalClient).searchByGenMarCode(genTypeCode, marstatusCode);
            if (list.size() != 0) {
                genderMaritalDTO = (IGenderMaritalDTO)list.get(0);
            } else {
                genderMaritalDTO.setGenmarName("");
            }
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
    }

    //    public void cancelAdd() {
    //
    //        System.out.println("Calling cancelAdd()...");
    //        this.getPageDTO().setName("");
    //        setPageMode(0);
    //        reIntializeMsgs();
    //        try {
    //            unCheck();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

    public void saveEdit() {
        try {

            genderMaritalDTO.setCode(new GenderMaritalEntityKey(genderMaritalDTO.getGentypeCode(),
                                                                genderMaritalDTO.getMarstatusCode()));
            genderMaritalClient.update(genderMaritalDTO);
            this.setGenderMaritalDTO(InfDTOFactory.createGenderMaritalDTO());
            getSharedUtil().setSuccessMsgValue("SuccesUpdated");
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf", "FailureInLink");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());

        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInAdd");

        }

        genderMaritalDTO = InfDTOFactory.createGenderMaritalDTO();
        selectedGender = "";
    }

    public IBasicDTO preLinkEdit() {

        reIntializeMsgs();
        //setPageMode(4);
        setShowEditLinkDiv(true);
        return null;
    }

    //---------------------------Setters and Getters-------------------------------

    public void setShowLinkDiv(boolean showLinkDiv) {
        this.showLinkDiv = showLinkDiv;
    }

    public boolean isShowLinkDiv() {
        return showLinkDiv;
    }

    public void setGenderTypesList(List genderTypesList) {
        this.genderTypesList = genderTypesList;
    }

    public List getGenderTypesList() {
        generateGenderTypesList();
        return genderTypesList;
    }

    public void setGenderMaritalDTO(IGenderMaritalDTO genderMaritalDTO) {
        this.genderMaritalDTO = genderMaritalDTO;
    }

    public IGenderMaritalDTO getGenderMaritalDTO() {
        return genderMaritalDTO;
    }

    public void setAlreadyLinked(boolean alreadyLinked) {
        this.alreadyLinked = alreadyLinked;
    }

    public boolean isAlreadyLinked() {
        return alreadyLinked;
    }

    public void setGenderEditList(List genderEditList) {
        this.genderEditList = genderEditList;
    }

    public List getGenderEditList() {
        if (showEditLinkDiv == true) {
            loadGenderEditList();
        }
        return genderEditList;
    }

    public void setShowEditLinkDiv(boolean showEditLinkDiv) {
        this.showEditLinkDiv = showEditLinkDiv;
    }

    public boolean isShowEditLinkDiv() {
        return showEditLinkDiv;
    }


    public String getSelectedGender() {
        return selectedGender;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public void setSelectedGenderMarEdit(String selectedGenderMarEdit) {
        this.selectedGenderMarEdit = selectedGenderMarEdit;
    }

    public String getSelectedGenderMarEdit() {
        return selectedGenderMarEdit;
    }

    public void setMaleLinkDTO(IGenderMaritalDTO maleLinkDTO) {
        this.maleLinkDTO = maleLinkDTO;
    }

    public IGenderMaritalDTO getMaleLinkDTO() {
        return maleLinkDTO;
    }

    public void setFemaleLinkDTO(IGenderMaritalDTO femaleLinkDTO) {
        this.femaleLinkDTO = femaleLinkDTO;
    }

    public IGenderMaritalDTO getFemaleLinkDTO() {
        return femaleLinkDTO;
    }

    public void setMaleLinkEditDTO(IGenderMaritalDTO maleLinkEditDTO) {
        this.maleLinkEditDTO = maleLinkEditDTO;
    }

    public IGenderMaritalDTO getMaleLinkEditDTO() {
        return maleLinkEditDTO;
    }

    public void setFemaleLinkEditDTO(IGenderMaritalDTO femaleLinkEditDTO) {
        this.femaleLinkEditDTO = femaleLinkEditDTO;
    }

    public IGenderMaritalDTO getFemaleLinkEditDTO() {
        return femaleLinkEditDTO;
    }
}
