package com.beshara.csc.gn.inf.integration.presentation.backingbean.documenttypes;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ResourceBundle;


public class DocumentTypesIntegrationBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private ResourceBundle bundle = null;
    private static final String GLOBAL_BUNDLE_EXCEPTION = "com.beshara.jsfbase.csc.msgresources.globalexceptions";

    public DocumentTypesIntegrationBean() {
        setClient(InfClientFactory.getInfDocumentTypesClient());
        setSelectedPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
        setPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
        setSingleSelection(true);
        setSaveSortingState(true);
        setBundle(ResourceBundle.getBundle("com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"));

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createApprovalMakersDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void cancelAdd() {
        super.cancelAdd();
        setPageMode(0);
        setSelectedRadio("");
        reInitializePageDTO();
    }


    public void cancelEdit() {
        setPageMode(0);
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void add() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            this.reIntializeMsgs();
            setPageDTO(getClient().add(getPageDTO()));
            getAll();
            getPageIndex((String)getPageDTO().getCode().getKey());
        } catch (SharedApplicationException e) {
            setShowErrorMsg(true);
            String errorMsgValue =
                getSharedUtil().messageLocator(GLOBAL_BUNDLE_EXCEPTION, "PrimaryKeyDuplicatedException");
            shared_util.setErrMsgValue(errorMsgValue);
            setErrorMsg("PrimaryKeyDuplicatedException");
            return;
        } catch (DataBaseException e) {
            setShowErrorMsg(true);
            String errorMsgValue =
                getSharedUtil().messageLocator(GLOBAL_BUNDLE_EXCEPTION, "PrimaryKeyDuplicatedException");
            shared_util.setErrMsgValue(errorMsgValue);
            setErrorMsg("PrimaryKeyDuplicatedException");
            return;
        }
    }

    public void save() {
        try {
            this.add();
            getAll();
            if (getPageDTO() != null && getPageDTO().getCode() != null) {
                getHighlightedDTOS().add(getPageDTO());
                getSharedUtil().setSuccessMsgValue("SuccessAdds");
            }
            this.reInitializePageDTO();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void saveAndNew() throws DataBaseException {
        try {

            this.add();
            getAll();

            if (getPageDTO() != null && getPageDTO().getCode() != null) {
                getHighlightedDTOS().add(getPageDTO());
                getPageIndex((String)getPageDTO().getCode().getKey());
                getSharedUtil().setSuccessMsgValue("SuccessAdds");
                setSuccess(true);
                this.reInitializePageDTO();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            getClient().update(getSelectedPageDTO());
            getAll();
            if (getSelectedPageDTO() != null && getSelectedPageDTO().getCode() != null) {
                getHighlightedDTOS().add(getSelectedPageDTO());
            }
            super.setShowEdit(false);
            shared_util.setSuccessMsgValue("SuccesUpdated");

        } catch (DataBaseException e) {
            setShowErrorMsg(true);
            String errorMsgValue =
                getSharedUtil().messageLocator(GLOBAL_BUNDLE_EXCEPTION, "PrimaryKeyDuplicatedException");
            shared_util.setErrMsgValue(errorMsgValue);
            return;
        } catch (SharedApplicationException e) {
            setShowErrorMsg(true);
            String errorMsgValue =
                getSharedUtil().messageLocator(GLOBAL_BUNDLE_EXCEPTION, "PrimaryKeyDuplicatedException");
            shared_util.setErrMsgValue(errorMsgValue);
            return;
        }
    }
}
