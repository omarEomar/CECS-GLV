package com.beshara.csc.nl.bnk.integration.presentation.backingbean.bankBranches.bankBranchDetails;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.client.KwMapClientImpl;
import com.beshara.csc.inf.business.client.KwStreetsClientImpl;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwMapDTO;
import com.beshara.csc.inf.business.dto.KwStreetsDTO;
import com.beshara.csc.inf.business.entity.IKwStreetsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwMapEntityKey;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.client.IBankContactPersonClient;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.nl.bnk.business.dto.BnkDTOFactory;
import com.beshara.csc.nl.bnk.business.entity.BankBranchesEntityKey;
import com.beshara.csc.nl.bnk.integration.presentation.backingbean.bankBranches.BranchesListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;


public class BranchDetailsListBean extends LookUpBaseBean {

    private static final String BNK_RESOURCES = "com.beshara.csc.nl.bnk.integration.presentation.resources.integration";

    private Boolean success = false;
    private Boolean showErrorMsg = false;
    private String newErrMsgValue = "";
    private String branchName = "";
    private String branchEmail = "";
    private String branchTel = "";
    private String branchFax = "";
    private String branchBO = "";

    private Boolean validCivilId = false;
    private String civilId = "";
    private String civilEmail = "";
    private String civilTel = "";
    private String civilMobile = "";
    private String civilFax = "";
    private Boolean enableSave = false;

    //private IBanksDTO bankDTO = null;
    private BankBranchesDTO bankBranchDTO = null;
    private IKwtCitizensResidentsDTO kwtCitizenDTO = null;

    List<IBasicDTO> branchDetailsList = new ArrayList<IBasicDTO>();

    public BranchDetailsListBean() {
        setPageDTO(BnkDTOFactory.createBankBranchesDTO());
        setSelectedPageDTO(BnkDTOFactory.createBankBranchesDTO());
        setClient(BnkClientFactory.getBankContactPersonClient());
        setMyTableData(branchDetailsList);
        setUsingPaging(false);
        setSaveSortingState(true);
        setSingleSelection(true);

        kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(false);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public BankBranchesDTO setBankBranchAddress(BankBranchesDTO bankBranchDTO) {
        BankBranchesDTO bankBranchDTO1 = bankBranchDTO;

        KwStreetsClientImpl kwStreetsClient = (KwStreetsClientImpl)InfClientFactory.getKwStreetsClient();
        Long streetCode = bankBranchDTO1.getStreetCode();
        if (streetCode != null && !streetCode.equals("")) {
            IKwStreetsEntityKey kwStreetsEntityKey;
            kwStreetsEntityKey = (IKwStreetsEntityKey)InfEntityKeyFactory.createKwStreetsEntityKey(streetCode);
            KwStreetsDTO kwStreetsDTO = null;
            try {
                kwStreetsDTO = (KwStreetsDTO)kwStreetsClient.getById(kwStreetsEntityKey);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (kwStreetsDTO != null) {
                bankBranchDTO.setStreetName(kwStreetsDTO.getName());
            }
        }
        String mapCode = bankBranchDTO1.getMapCode();
        if (mapCode != null && !mapCode.equals("")) {
            KwMapEntityKey kwMapEntityKey = (KwMapEntityKey)InfEntityKeyFactory.createKwMapEntityKey(mapCode);
            KwMapClientImpl kwMapClient = (KwMapClientImpl)InfClientFactory.getKwMapClient();
            KwMapDTO kwMapDTO = null;
            try {
                kwMapDTO = (KwMapDTO)kwMapClient.getById(kwMapEntityKey);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (kwMapDTO != null) {
                //Long treeLevel =  kwMapDTO.getTreeLevel();
                Long typeCode = kwMapDTO.getTypeCode();
                if (typeCode != null) {
                    if (typeCode.equals(3L)) { //part
                        bankBranchDTO.setPart(kwMapDTO.getName());
                        KwMapDTO parentkwMapDTO = (KwMapDTO)kwMapDTO.getParentObject();
                        if (parentkwMapDTO != null) { //zone
                            bankBranchDTO.setZone(parentkwMapDTO.getName());
                            KwMapDTO gParentkwMapDTO = (KwMapDTO)parentkwMapDTO.getParentObject();
                            if (gParentkwMapDTO != null) { //governate
                                bankBranchDTO.setGovernate(gParentkwMapDTO.getName());
                            }
                        }
                    } else if (typeCode.equals(2L)) { //zone
                        bankBranchDTO.setZone(kwMapDTO.getName());
                        KwMapDTO parentkwMapDTO = (KwMapDTO)kwMapDTO.getParentObject();
                        if (parentkwMapDTO != null) { //governate
                            bankBranchDTO.setGovernate(parentkwMapDTO.getName());
                        }
                    } else if (typeCode.equals(1L)) { //governate
                        bankBranchDTO.setGovernate(kwMapDTO.getName());
                    }

                }
            }
        }
        return bankBranchDTO;
    }


    public void getBankBranchDetailsList() {
        try {
            branchDetailsList =
                    ((IBankContactPersonClient)getClient()).getBankContactPersonsByBankBranch(Long.valueOf(bankBranchDTO.getBanksDTO().getCode().getKey()),
                                                                                              Long.valueOf(((BankBranchesEntityKey)bankBranchDTO.getCode()).getBnkbranchCode()));
            setMyTableData(branchDetailsList);

        } catch (SharedApplicationException sae) {
            //getSharedUtil().handleException(db);
            if (sae instanceof NoResultException)
                setMyTableData(new ArrayList());
            else
                getSharedUtil().handleException(sae);
        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            getSharedUtil().handleException(e);
        }
    }

    public String backToBankBranches() {
        BranchesListBean branchesListBean = (BranchesListBean)evaluateValueBinding("branchesListBean");
        try {
            branchesListBean.getPageIndex(bankBranchDTO.getCode().getKey());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        return "ShowBranchesList";
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

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchEmail(String branchEmail) {
        this.branchEmail = branchEmail;
    }

    public String getBranchEmail() {
        return branchEmail;
    }

    public void setBranchTel(String branchTel) {
        this.branchTel = branchTel;
    }

    public String getBranchTel() {
        return branchTel;
    }

    public void setBranchFax(String branchFax) {
        this.branchFax = branchFax;
    }

    public String getBranchFax() {
        return branchFax;
    }

    public void setBranchBO(String branchBO) {
        this.branchBO = branchBO;
    }

    public String getBranchBO() {
        return branchBO;
    }


    //    public void setBankDTO(IBanksDTO bankDTO) {
    //        this.bankDTO = bankDTO;
    //    }
    //
    //    public IBanksDTO getBankDTO() {
    //        return bankDTO;
    //    }

    public void setBankBranchDTO(BankBranchesDTO bankBranchDTO) {
        this.bankBranchDTO = bankBranchDTO;
    }

    public BankBranchesDTO getBankBranchDTO() {
        return bankBranchDTO;
    }

    public void setBranchDetailsList(List<IBasicDTO> branchDetailsList) {
        this.branchDetailsList = branchDetailsList;
    }

    public List<IBasicDTO> getBranchDetailsList() {
        return branchDetailsList;
    }

    public void setValidCivilId(Boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public Boolean getValidCivilId() {
        return validCivilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilEmail(String civilEmail) {
        this.civilEmail = civilEmail;
    }

    public String getCivilEmail() {
        return civilEmail;
    }

    public void setCivilTel(String civilTel) {
        this.civilTel = civilTel;
    }

    public String getCivilTel() {
        return civilTel;
    }

    public void setCivilMobile(String civilMobile) {
        this.civilMobile = civilMobile;
    }

    public String getCivilMobile() {
        return civilMobile;
    }

    public void setCivilFax(String civilFax) {
        this.civilFax = civilFax;
    }

    public String getCivilFax() {
        return civilFax;
    }

    public void setKwtCitizenDTO(IKwtCitizensResidentsDTO kwtCitizenDTO) {
        this.kwtCitizenDTO = kwtCitizenDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizenDTO() {
        return kwtCitizenDTO;
    }

    public void setEnableSave(Boolean enableSave) {
        this.enableSave = enableSave;
    }

    public Boolean getEnableSave() {
        return enableSave;
    }
}
