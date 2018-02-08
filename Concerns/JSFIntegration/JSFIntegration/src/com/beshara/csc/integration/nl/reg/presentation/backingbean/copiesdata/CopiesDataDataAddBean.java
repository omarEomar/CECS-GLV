package com.beshara.csc.nl.reg.presentation.backingbean.copiesdata;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.gn.sec.business.dto.ISecWorkCenterUsersDTO;
import com.beshara.csc.gn.sec.business.dto.ISecWorkCenterUsersSearchDTO;
import com.beshara.csc.gn.sec.business.dto.SecDTOFactory;
import com.beshara.csc.gn.sec.business.entity.ISecWorkCenterUsersEntityKey;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.WorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegSubjectCopiesToDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.dto.RegSubjectCopiesToDTO;
import com.beshara.csc.nl.reg.business.entity.SubjectsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class CopiesDataDataAddBean extends LookupMaintainBaseBean {
    private boolean showValidation = false;
    private boolean sucesseadding;
    private List<IBasicDTO> pageDtoBufferlist;
    private IMinistriesDTO pageDtoBuffer;
    List<WorkCentersDTO> workCentersDTOList;
    ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO;
    private List<IBasicDTO> lovDataTableList = new ArrayList();
    private Boolean edit;
    private boolean divflag;
    private IBasicDTO pageDtoToReturn;

    public CopiesDataDataAddBean() {
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setClient(RegClientFactory.getregSubjectCopiesClientForCenter());
        secWorkCenterUsersSearchDTO =  SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        setLovDiv("ConditionLovDivClass");
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().getSelectedDTOS().clear();
         try {
            pageDtoBuffer =(IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(CSCSecurityInfoHelper.getLoggedInMinistryCode()));
        } catch (DataBaseException e) {
        e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowLovDiv(true);

        return app;
    }

    public void setSucesseadding(boolean sucesseadding) {
        this.sucesseadding = sucesseadding;
    }

    public boolean isSucesseadding() {
        return sucesseadding;
    }


    public void setShowValidation(boolean showValidation) {
        this.showValidation = showValidation;
    }

    public boolean isShowValidation() {
        return showValidation;
    }

    public void openSearchLovDiv() {

        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setCheckAllFlag(false);
        resetLovPageIndex();
        setDivflag(true);
        try {
            workCentersDTOList =SecClientFactory.getSecWorkCenterUsersClientForCenter().getWorkCentersByMin(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        getLovBaseBean().setReturnMethodName("copiesDataDataAddBean.addSelectedElement"); //ok action method that will be called when user select one item
        getLovBaseBean().setSearchMethod("copiesDataDataAddBean.searchLovDiv"); // method that will be called when user press search button
        getLovBaseBean().setCancelSearchMethod("copiesDataDataAddBean.cancelSearchLovDiv"); // method that will be called when user press cancel search button
        getLovBaseBean().getOkCommandButton().setReRender("cnt1Panel,scriptpanel,OperationBar,dataT_data_panel,paging_panel,emp_love_div_group,navigationpanel");
    }

    public void resetLovPageIndex() {
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        getLovBaseBean().getMyDataTable().setFirst(0);
    }

    public void setLovDataTableList(List<IBasicDTO> lovDataTableList) {
        this.lovDataTableList = lovDataTableList;
    }

    public List<IBasicDTO> getLovDataTableList() {
        return lovDataTableList;
    }


    public void setWorkCentersDTOList(List<WorkCentersDTO> workCentersDTOList) {
        this.workCentersDTOList = workCentersDTOList;
    }

    public List<WorkCentersDTO> getWorkCentersDTOList() {
        return workCentersDTOList;
    }

    public void setSecWorkCenterUsersSearchDTO(ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO) {
        this.secWorkCenterUsersSearchDTO = secWorkCenterUsersSearchDTO;
    }

    public ISecWorkCenterUsersSearchDTO getSecWorkCenterUsersSearchDTO() {
        return secWorkCenterUsersSearchDTO;
    }

    public String searchLovDiv() {
        try {
            List<ISecWorkCenterUsersDTO> Result=SecClientFactory.getSecWorkCenterUsersClientForCenter().SearchByCaretira(getSecWorkCenterUsersSearchDTO());
            getLovBaseBean().setMyTableData(Result);
            getLovBaseBean().setSelectedRadio("");
        } catch (DataBaseException e) {
            getLovBaseBean().setMyTableData(new ArrayList());
            getLovBaseBean().setSelectedRadio("");
             e.printStackTrace(); 
        }
        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();");
        getLovBaseBean().setSearchMode(true);
        return "";
    }

    public String cancelSearchLovDiv() {
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().getSelectedDTOS().clear();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setMyTableData(new ArrayList());
        secWorkCenterUsersSearchDTO =  SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        openSearchLovDiv();
        return "";
    }

    public void openTransferDiv(ActionEvent ae) {

        setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.divLov ) ; ");
    }

    public void closeLovDiv() throws DataBaseException {
        if (edit == false) {
            if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null) {
                ((RegSubjectCopiesToDTO)getPageDTO()).getSecWorkCenterUsersDTO().setUserName(((ISecWorkCenterUsersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getUserName());
                ((RegSubjectCopiesToDTO)getPageDTO()).getSecWorkCenterUsersDTO().setCivilname(((ISecWorkCenterUsersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getCivilname());
                ((RegSubjectCopiesToDTO)getPageDTO()).setUserCode(((ISecWorkCenterUsersEntityKey)(((ISecWorkCenterUsersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getCode())).getUserCode());
            }
        } else if (getLovBaseBean().getSelectedDTOS() != null && 
                   getLovBaseBean().getSelectedDTOS().get(0) != null) {
            ((RegSubjectCopiesToDTO)getSelectedPageDTO()).getSecWorkCenterUsersDTO().setUserName(((ISecWorkCenterUsersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getUserName());
            ((RegSubjectCopiesToDTO)getSelectedPageDTO()).getSecWorkCenterUsersDTO().setCivilname(((ISecWorkCenterUsersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getCivilname());
            ((RegSubjectCopiesToDTO)getSelectedPageDTO()).setUserCode(((ISecWorkCenterUsersEntityKey)(((ISecWorkCenterUsersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getCode())).getUserCode());
        }
        secWorkCenterUsersSearchDTO = SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        divflag = false;
        resetLovBaseBean();
    }

    public void filterDataBygeha() {

        if(getSecWorkCenterUsersSearchDTO().getMinCode()==null)
        {
        getSecWorkCenterUsersSearchDTO().setWrkCode(null);
        }
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().getSelectedDTOS().clear();
        getLovBaseBean().setMyTableData(new ArrayList());
        setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.divLov ) ; ");
    }

    private void resetLovBaseBean() throws DataBaseException {

        for (int i = 0; i < getLovBaseBean().getMyTableData().size(); i++) {

            if (((IClientDTO)getLovBaseBean().getMyTableData().get(i)).getChecked() != 
                null && 
                ((IClientDTO)getLovBaseBean().getMyTableData().get(i)).getChecked().booleanValue()) {

                ((IClientDTO)getLovBaseBean().getMyTableData().get(i)).setChecked(Boolean.FALSE);
            }
        }
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().getSelectedDTOS().clear();
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
    }

    public String saveCopiesdata() throws DataBaseException, 
                                          SharedApplicationException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        CopiesDataDataList copiesDataDataList = 
            (CopiesDataDataList)app.createValueBinding("#{copiesDataDataListBean}").getValue(fc);
        SharedUtilBean sharedUtil = getSharedUtil();
        if (edit == false) {
            try {
                ((RegSubjectCopiesToDTO)getPageDTO()).setSubjectCode(((SubjectsEntityKey)(((RegSubjectCopiesToDTO)getPageDTO()).getSubjectsDTO().getCode())).getSubjectCode());
                super.add();
                copiesDataDataList.getAll();
                copiesDataDataList.sort(copiesDataDataList.getMyTableData(),copiesDataDataList.getFullColumnName(),copiesDataDataList.isAscending(),true);
                sharedUtil.setSuccessMsgValue("SuccessAdds");
                copiesDataDataList.getHighlightedDTOS().clear();
                setPageDtoToReturn(getPageDTO());
                copiesDataDataList.getHighlightedDTOS().add(getPageDTO());
                setPageDtoToReturn(getPageDTO());
                copiesDataDataList.getPageIndex((String)getPageDTO().getCode().getKey());
            } catch (DataBaseException e) {
                this.setShowErrorMsg(true);
                sharedUtil.handleException(e);
                this.setErrorMsg(sharedUtil.getErrMsgValue());
            } catch (Exception e) {
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInAdd");
                sharedUtil.handleException(e, 
                                           "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                           "FailureInAdd");
            }
        } else {
            super.edit();
            copiesDataDataList.getAll();
            copiesDataDataList.sort(copiesDataDataList.getMyTableData(),copiesDataDataList.getFullColumnName(),copiesDataDataList.isAscending(),true);
            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            copiesDataDataList.getHighlightedDTOS().clear();
            copiesDataDataList.getHighlightedDTOS().add(getSelectedPageDTO());
            copiesDataDataList.getPageIndex((String)getSelectedPageDTO().getCode().getKey());
        }
        return "CopiesDataDataList";
    }

    public String back() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        CopiesDataDataList copiesDataDataList = 
            (CopiesDataDataList)app.createValueBinding("#{copiesDataDataListBean}").getValue(fc);
        try {
            copiesDataDataList.getAll();
            copiesDataDataList.sort(copiesDataDataList.getMyTableData(),copiesDataDataList.getFullColumnName(),copiesDataDataList.isAscending(),true);
        } catch (DataBaseException e) {
           e.printStackTrace();
        }

        if (getPageDtoToReturn() != null &&  getPageDtoToReturn().getCode() != null) {
            try {
                copiesDataDataList.getPageIndex((String)getPageDtoToReturn().getCode().getKey());
                copiesDataDataList.getHighlightedDTOS().add(getPageDtoToReturn());
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return "CopiesDataDataList";
    }

    public void hideLovDiv() {
        cancelSearchLovDiv();
        setDivflag(false);
        secWorkCenterUsersSearchDTO =SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        getLovBaseBean().hideLovDiv();
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setDivflag(boolean divflag) {
        this.divflag = divflag;
    }

    public boolean isDivflag() {
        return divflag;
    }

    public void setPageDtoBuffer(IMinistriesDTO pageDtoBuffer) {
        this.pageDtoBuffer = pageDtoBuffer;
    }

    public IMinistriesDTO getPageDtoBuffer() {
        return pageDtoBuffer;
    }

    public void setPageDtoBufferlist(List<IBasicDTO> pageDtoBufferlist) {
        this.pageDtoBufferlist = pageDtoBufferlist;
    }

    public List<IBasicDTO> getPageDtoBufferlist() {
        try {
            pageDtoBufferlist =SecClientFactory.getSecWorkCenterUsersClientForCenter().GetDistnictMinCodeByUserCode(CSCSecurityInfoHelper.getUserCode());
        } catch (DataBaseException e) {
            pageDtoBufferlist=new ArrayList();
            e.printStackTrace();
        }
        return pageDtoBufferlist;
    }

    public void saveCopiesdataAndNew() throws DataBaseException {
        reIntializeMsgs();
        try {
            this.saveCopiesdata();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals(""))
            this.setSuccess(true);
        this.reInitializePageDTO();
    }

    public void reInitializePageDTO() {

        if (edit == false) {
            ISubjectsDTO SubjectsDTOtemp = 
                ((IRegSubjectCopiesToDTO)getPageDTO()).getSubjectsDTO();
            this.setPageDTO(RegDTOFactory.createRegSubjectCopiesToDTO());
            ((IRegSubjectCopiesToDTO)getPageDTO()).setSubjectsDTO(SubjectsDTOtemp);
        } else {
            ISubjectsDTO SubjectsDTOtempEdit =((IRegSubjectCopiesToDTO)getSelectedPageDTO()).getSubjectsDTO();
            this.setSelectedPageDTO(RegDTOFactory.createRegSubjectCopiesToDTO());
            ((IRegSubjectCopiesToDTO)getSelectedPageDTO()).setSubjectsDTO(SubjectsDTOtempEdit);
            setEdit(false);
        }
    }

    public void setPageDtoToReturn(IBasicDTO pageDtoToReturn) {
        this.pageDtoToReturn = pageDtoToReturn;
    }

    public IBasicDTO getPageDtoToReturn() {
        return pageDtoToReturn;
    }
}
