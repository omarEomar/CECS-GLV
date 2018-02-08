package com.beshara.csc.nl.bnk.integration.presentation.backingbean.bankBranches;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.client.IBankBranchesClient;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.nl.bnk.business.dto.BanksDTO;
import com.beshara.csc.nl.bnk.business.dto.BnkDTOFactory;
import com.beshara.csc.nl.bnk.business.entity.IBanksEntityKey;
import com.beshara.csc.nl.bnk.business.exception.BankBranchUsedException;
import com.beshara.csc.nl.bnk.integration.presentation.backingbean.bankBranches.bankBranchDetails.BranchDetailsListBean;
import com.beshara.csc.nl.bnk.integration.presentation.backingbean.banks.BanksListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeUpdatedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;


public class BranchesListBean extends LookUpBaseBean {

    private static final String BNK_RESOURCES = "com.beshara.csc.nl.bnk.integration.presentation.resources.integration";

    private Boolean success = false;
    private Boolean showErrorMsg = false;

    private String newErrMsgValue = "";

    private BanksDTO bankDTO = null;
    private BankBranchesDTO branchDTO = null;

    private List<IBasicDTO> branchesList = new ArrayList<IBasicDTO>();


    public BranchesListBean() {
        setPageDTO(BnkDTOFactory.createBankBranchesDTO());
        setSelectedPageDTO(BnkDTOFactory.createBankBranchesDTO());
        setClient(BnkClientFactory.getBankBranchesClient());
        setMyTableData(branchesList);
        branchDTO = BnkDTOFactory.createBankBranchesDTO();

        setUsingPaging(false);
        setSaveSortingState(true);
        setSingleSelection(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public void getBankBranchesList() {
        try {
            branchesList =
                    BnkClientFactory.getBankBranchesClient().getAllBranches(Long.valueOf(bankDTO.getCode().getKey()));
            setMyTableData(branchesList);

        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            getSharedUtil().handleException(e);
        }
    }

    public String ShowBranchDetails() {
        try {
            BranchDetailsListBean branchDetailsListBean =
                (BranchDetailsListBean)evaluateValueBinding("branchDetailsListBean");
            BankBranchesDTO bankBranchesDTO = ((BankBranchesDTO)getSelectedDTOS().get(0));
            bankBranchesDTO = branchDetailsListBean.setBankBranchAddress(bankBranchesDTO);
            branchDetailsListBean.setBankBranchDTO(bankBranchesDTO);
            branchDetailsListBean.getBankBranchDetailsList();

            return "ShowBranchDetailsList";
        } catch (Exception e) {

        }
        return null;
    }

    public void delete() {
        SharedUtilBean shared_util = getSharedUtil();
        System.out.println("Calling delete()...");
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpSession session = (HttpSession)ex.getSession(true);

        if (getSelectedDTOS() == null)
            setSelectedDTOS(loadSelectedDTOS());

        //            for (IBasicDTO dto: getSelectedDTOS()) {


        try {
            List list = getClient().remove(getSelectedDTOS());
            setDeleteStatusDTOS(list);
            if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }

            this.getSelectedDTOS().clear();
            session.setAttribute("selectedDTOS", null);
            branchesList =
                    ((IBankBranchesClient)getClient()).getAllBranches(((IBanksEntityKey)bankDTO.getCode()).getBankCode());
            setMyTableData(branchesList);
            //cancelSearch();
            restoreTablePosition();
        } catch (DataBaseException db) {
            //shared_util.setErrMsgValue(db.getMessage());
            getSharedUtil().handleException(db, BNK_RESOURCES, "branchDeletionAlert");
        } catch (ItemNotFoundException item) {
            shared_util.setErrMsgValue("ItemCanNotBeDeletedException");
        } catch (SharedApplicationException e) {
            //shared_util.setErrMsgValue(e.getMessage());
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            //getSharedUtil().handleException(e);
            e.printStackTrace();
        }

    }

    public void reInitializePageDTO() {
        setPageDTO(BnkDTOFactory.createBankBranchesDTO());
    }

    public String backToBanks() {
        BanksListBean banksList = (BanksListBean)evaluateValueBinding("banksListBean");
        try {
            banksList.getPageIndex(bankDTO.getCode().getKey());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return "BackToBanks";
    }

    public String AddBranch() {

        try {
            NewBranchListBean newBrancheListBean = (NewBranchListBean)evaluateValueBinding("newBranchListBean");

            newBrancheListBean.setBankDTO(bankDTO);
            newBrancheListBean.IntiateGovernorates();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "AddNewBranch";
    }

    /* nora csc_bnk036 */

    public String editBranch() {

        try {
            NewBranchListBean newBrancheListBean = (NewBranchListBean)evaluateValueBinding("newBranchListBean");
            BankBranchesDTO bankBranchesDTO = ((BankBranchesDTO)getSelectedDTOS().get(0));
            newBrancheListBean.initEditMode(bankBranchesDTO);
            return "editNewBranch";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    public void setBranchesList(List<IBasicDTO> branchesList) {
        this.branchesList = branchesList;
    }

    public List<IBasicDTO> getBranchesList() {
        return branchesList;
    }

    public void setBankDTO(BanksDTO bankDTO) {
        this.bankDTO = bankDTO;
    }

    public BanksDTO getBankDTO() {
        return bankDTO;
    }

    public void setBranchDTO(BankBranchesDTO branchDTO) {
        this.branchDTO = branchDTO;
    }

    public BankBranchesDTO getBranchDTO() {
        return branchDTO;
    }

    public boolean isValid() {
        try {
            if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
                BankBranchesDTO bankBranchesDTO = ((BankBranchesDTO)getSelectedDTOS().get(0));
                if (bankBranchesDTO.getStatus() != null &&
                    bankBranchesDTO.getStatus().equals(ISystemConstant.ACTIVE_FLAG)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public void suspend() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        //TODO locking code
        // be sure that we are still locking the edited item
        // if not cancel the edit operation and show an exception
        // message to the user
        if (insureLocked()) {

            SharedUtilBean sharedUtil = getSharedUtil();

            try {
                // because we are always closing the edit div,
                // so we must always unlock the edited item
                // to leave it to other users
                try {
                    BankBranchesDTO bankBranchesDTO = ((BankBranchesDTO)getSelectedDTOS().get(0));
                    setSelectedPageDTO(bankBranchesDTO);
                    ((IBankBranchesClient)getClient()).suspend(bankBranchesDTO);
                } finally {
                    //TODO locking code
                    // unlock the edited item in update success or failure
                    // so we added it in this finally block
                    unlock();
                }

                getBankBranchesList();

                if (super.isUsingPaging()) {
                    generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

                } else {
                    getPageIndex((String)getSelectedPageDTO().getCode().getKey());
                }

                if (super.getHighlightedDTOS() != null) {
                    super.getHighlightedDTOS().clear();
                    super.getHighlightedDTOS().add(this.getSelectedPageDTO());
                }

                super.setShowEdit(false);
                sharedUtil.setSuccessMsgValue("SuccesUpdated");

            } catch (BankBranchUsedException e) {
                sharedUtil.handleException(e, BNK_RESOURCES, "BankBranchUsedException");
            } catch (DataBaseException e) {
                sharedUtil.handleException(e);
            } catch (ItemNotFoundException e) {
                sharedUtil.handleException(e);
            } catch (ItemCanNotBeUpdatedException e) {
                sharedUtil.handleException(e);

            } catch (Exception e) {
                sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                           "FailureInUpdate");

            }
        }
        setSelectedRadio("");
        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            getSelectedDTOS().clear();
        }

    }

    public void selectedRadioButton(ActionEvent actionEvent) throws Exception {
        super.selectedRadioButton(actionEvent);
    }
}
