package com.beshara.csc.nl.bnk.integration.presentation.backingbean.banks;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.dto.BanksDTO;
import com.beshara.csc.nl.bnk.business.dto.BnkDTOFactory;
import com.beshara.csc.nl.bnk.business.exception.BankShortNameFoundException;
import com.beshara.csc.nl.bnk.integration.presentation.backingbean.bankBranches.BranchesListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;


public class BanksListBean extends LookUpBaseBean {

    private static final String BNK_RESOURCES = "com.beshara.csc.nl.bnk.integration.presentation.resources.integration";

    private Boolean success = false;
    private Boolean showErrorMsg = false;
    private String newErrMsgValue = "";
    private String bankName = "";
    private String bankEmail = "";

    public BanksListBean() {
        setPageDTO(BnkDTOFactory.createBanksDTO());
        super.setSelectedPageDTO(BnkDTOFactory.createBanksDTO());
        setClient(BnkClientFactory.getBanksClient());
        setUsingPaging(false);
        setSaveSortingState(true);
        setSingleSelection(true);
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowContent1(false);
        return app;
    }

    public List getTotalList() {
        List totalList = null;
        try {
            totalList = getClient().getAll();
            setMyTableData(totalList);

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

    @Override
    public void preAdd() {
        try {
            reInitializePageDTO();
            this.setShowErrorMsg(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add() {
        SharedUtilBean sharedUtil = getSharedUtil();

        try {
            setPageDTO(executeAdd());

            if (isUsingPaging()) {

                getPagingBean().clearDTOS();

                generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

            } else {
                getAll();
                getPageIndex((String)getPageDTO().getCode().getKey());
            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            setSelectedRadio("");
            sharedUtil.handleSuccMsg(BNK_RESOURCES, "bank_add_success");
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("bank_already_exist");
            e.printStackTrace();
            sharedUtil.handleException(e, BNK_RESOURCES, "bank_already_exist");
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("bank_add_error");
            e.printStackTrace();
            sharedUtil.handleException(e, BNK_RESOURCES, "bank_add_error");
        }
    }

    @Override
    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        SharedUtilBean sharedUtil = getSharedUtil();

        try {

            executeEdit();

            cancelSearch();

            if (isUsingPaging()) {

                super.setUpdateMyPagedDataModel(true);
                super.setRepositionTable(true);
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO() == null) {
                    setPagingRequestDTO(new PagingRequestDTO());
                }
                getPagingRequestDTO().setRepositionKey((String)getSelectedPageDTO().getCode().getKey());

            }

            getHighlightedDTOS().clear();
            getHighlightedDTOS().add(getSelectedPageDTO());
            getPageIndex(getSelectedPageDTO().getCode().getKey());

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            //sharedUtil.handleSuccMsg(BNK_RESOURCES,"bank_edit_success");
            //                this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("bank_edit_error");
            e.printStackTrace();
            sharedUtil.handleException(e, BNK_RESOURCES, "bank_edit_error");

        } catch (SharedApplicationException e) {
            if(e instanceof BankShortNameFoundException){
                String bnkName = "";
                BanksDTO existDTO = null;
                String msgError = getSharedUtil().messageLocator(BNK_RESOURCES, "shortNameExistErrorMsg");
                if(e.getExtraInformation() != null && e.getExtraInformation().size() > 0 ) {
                    Object obj = e.getExtraInformation().get(0);
                    if(obj instanceof BanksDTO){
                        existDTO = (BanksDTO)obj;
                        bnkName = existDTO.getName();
                        System.out.println("***** bnkName = \n"+bnkName);
                        msgError = msgError.concat(bnkName);
                    }
                    this.setShowErrorMsg(true);
                    this.setErrorMsg(msgError);
                    System.out.println("***** msgError = \n"+msgError);
                    sharedUtil.setErrMsgValue(msgError);
                }
            }else {
                this.setShowErrorMsg(true);
                this.setErrorMsg("bank_already_exist");
                sharedUtil.handleException(e, BNK_RESOURCES, "bank_already_exist");
            }
            e.printStackTrace();
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");
        this.getSelectedDTOS().clear();

    }

    public IBasicDTO executeAdd() throws DataBaseException, SharedApplicationException {

        ((BanksDTO)getPageDTO()).setEmail(bankEmail);
        ((BanksDTO)getPageDTO()).setName(bankName);

        return getClient().add(getPageDTO());
    }

    //    public IBasicDTO executeUpdate() throws DataBaseException, SharedApplicationException {
    //
    //        ((BanksDTO)getPageDTO()).setEmail(bankEmail);
    //        ((BanksDTO)getPageDTO()).setName(bankName);
    //        this.setErrorMsg("bank_already_exist");
    //        return getClient().add(getPageDTO());
    //    }

    public String ShowBranches() {
        try {
            BranchesListBean branchesListBean = (BranchesListBean)evaluateValueBinding("branchesListBean");
            branchesListBean.setBankDTO(((BanksDTO)getSelectedDTOS().get(0)));
            branchesListBean.getBankBranchesList();
            return "ShowBranchesList";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reInitializePageDTO() {
        if (!isShowErrorMsg()) {
            bankEmail = "";
            bankName = "";
            setPageDTO(BnkDTOFactory.createBanksDTO());
        }
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setShowErrorMsg(Boolean showErrorMsg) {
        this.showErrorMsg = showErrorMsg;
    }

    public Boolean getShowErrorMsg() {
        return showErrorMsg;
    }

    public void setNewErrMsgValue(String newErrMsgValue) {
        this.newErrMsgValue = newErrMsgValue;
    }

    public String getNewErrMsgValue() {
        return newErrMsgValue;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankEmail(String bankEmail) {
        this.bankEmail = bankEmail;
    }

    public String getBankEmail() {
        return bankEmail;
    }


    public void deleteDiv() throws DataBaseException, ItemNotFoundException {
        this.delete();
    }

    public void delete() throws DataBaseException, ItemNotFoundException {
        SharedUtilBean shared_util = getSharedUtil();

        if (getSelectedDTOS() == null)
            setSelectedDTOS(loadSelectedDTOS());
        try {
            getClient().remove(getSelectedDTOS().get(0));
            shared_util.setSuccessMsgValue("Deleted");
            if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            shared_util.handleException(e, BNK_RESOURCES, "banks_Has_Account_msg");

        }


        //        }

        this.getSelectedDTOS().clear();
        cancelSearch();
        restoreTablePosition();


    }


}
