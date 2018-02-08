package com.beshara.csc.nl.bnk.integration.presentation.backingbean.ministriesbnkaccounts;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.sal.business.client.ISalDeductToMinistriesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalBankAccountTypesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalDeductToMinistriesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalBankAccountTypesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.hr.sal.business.shared.exception.InvalidBankAccountNumber;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.nl.bnk.business.dto.BanksDTO;
import com.beshara.csc.nl.bnk.business.entity.BankBranchesEntityKey;
import com.beshara.csc.nl.bnk.business.entity.BnkEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.entity.IBankBranchesEntityKey;
import com.beshara.csc.nl.bnk.business.entity.IBanksEntityKey;
import com.beshara.csc.nl.bnk.business.shared.IBnkConstant;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.integration.presentation.backingbean.ministry.SearchMinistryWithPagingHelper;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class MinistriesBnkAccountsBean extends LookUpBaseBean {
    private String integrationBundle ="com.beshara.csc.nl.bnk.integration.presentation.resources.integration";

    private Long govFlag = IBnkConstant.GOVERNMENTAL;
    private Long nonGovFlag = IBnkConstant.NON_GOVERNMENTAL;

    private List<IBasicDTO> categories;
    private List<IBasicDTO> ministries;
    protected List<IBasicDTO> ministryAccountsList;

    private String selectedTypeId = IBnkConstant.NON_GOVERNMENTAL.toString(); //getVirtualLongValue();
    private Long selectedCatId;
    protected Long selectedMiniId;
    private Long bankAccountTypeId;
    private Long selectedPurposeId;
    protected Long selectedBankId = getVirtualLongValue();
    private Long selectedBankBranchId; // = getVirtualLongValue();
    private boolean enableAddBtn = false;
    private String selectedCatName;
    private String selectedMiniName;
    private List<IBasicDTO> accountPurposesList = new ArrayList();
    protected List<IBasicDTO> banksList = new ArrayList();
    protected List<IBasicDTO> bankBranchesList = new ArrayList();
    protected String[] minsAccountNumber = new String[9];

    private List<Long> statusList = new ArrayList<Long>();
    private String localBundle = "com.beshara.csc.nl.bnk.integration.presentation.resources.integration";
    private String globalExBundle = "com.beshara.jsfbase.csc.msgresources.globalexceptions";
    private static final String BEAN_NAME = "ministriesBnkAccountsBean";
    private SearchMinistryWithPagingHelper searchMinistryHelper;
    private boolean wrongMinCode;
    private Long Logged_in_APP_MODULE_CODE = CSCSecurityInfoHelper.getModuleCode();
    private Long BNK_APP_MODULE_CODE = 423L;
    private Long SCP_APP_MODULE_CODE = 9L;
    private List banksTypeList = new  ArrayList<IBasicDTO>();

    public MinistriesBnkAccountsBean() {
        setPageDTO(SalDTOFactory.createSalDeductToMinistriesDTO());
        setSelectedPageDTO(SalDTOFactory.createSalDeductToMinistriesDTO());
        setClient(SalClientFactory.getSalDeductToMinistriesClient());
        setLookupAddDiv("divREGIntegrate");
        setLookupEditDiv("divREGIntegrate");
        if (minsAccountNumber == null)
            minsAccountNumber = new String[9];
        minsAccountNumber[0] = "KW";
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowbar(true);
        app.setShowshortCut(true);
        app.setShowLookupAdd(true);
        app.setShowLovDiv(false);
        return app;
    }

    @Override
    public void initiateBeanOnce() {
        
        
        if (banksTypeList != null) {
            IBasicDTO typeDTO1 = new BasicDTO();
            IEntityKey banksEntityKey1 = new EntityKey();
            banksEntityKey1.setKeys(new String[]{nonGovFlag.toString()});
            typeDTO1.setName(getSharedUtil().messageLocator(integrationBundle, "nongovernmental"));
    //            banksEntityKey1.setKey();
            typeDTO1.setCode(banksEntityKey1);
            banksTypeList.add(typeDTO1);
            System.out.println( "Logged_in_APP_MODULE_CODE========"+ Logged_in_APP_MODULE_CODE);
            if (Logged_in_APP_MODULE_CODE != null && Logged_in_APP_MODULE_CODE.equals(SCP_APP_MODULE_CODE)) {
                IBasicDTO typeDTO2 = new BasicDTO();
                IEntityKey banksEntityKey2 = new EntityKey();
                banksEntityKey2.setKeys(new String[]{govFlag.toString()});
    //                banksEntityKey2.setKey();
                typeDTO2.setName(getSharedUtil().messageLocator(integrationBundle, "governmental"));
                typeDTO2.setCode(banksEntityKey2);
                banksTypeList.add(typeDTO2);
            }
        }
    }
    
    
    public static MinistriesBnkAccountsBean getInstance() {
        return (MinistriesBnkAccountsBean)JSFHelper.getValue(BEAN_NAME);
    }

    public void openSearchMinistryDiv() {

        String returnSearchMethodName = BEAN_NAME + ".returnSearchMethod";
        getSearchMinistryHelper().setRenderedCmpIds("ministryName,minFilterationId,OperationBar");
        getSearchMinistryHelper().initDiv(selectedCatId, returnSearchMethodName);
    }

    public void returnSearchMethod() {
        List list = getSearchMinistryHelper().getSelectedDTOS();
        if (list != null && !list.isEmpty()) {
            IMinistriesDTO ministryDTO = (IMinistriesDTO)list.get(0);
            selectedMiniId = ((IMinistriesEntityKey)ministryDTO.getCode()).getMinCode();
            selectedMiniName = ministryDTO.getName();
        }

        changeMinistry(null);
    }

    public void updateAccountsList() {
        try {
            ministryAccountsList = ((ISalDeductToMinistriesClient)getClient()).getByMinCode(selectedMiniId);
        } catch (Exception e) {
            e.printStackTrace();
            ministryAccountsList = new ArrayList<IBasicDTO>();
        }
        setMyTableData(ministryAccountsList);
        repositionPage(0);
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setSelectedRadio("");
    }


    public void changeMinistry(ActionEvent ae) {
        enableAddBtn = false;
        wrongMinCode = false;
        if (selectedCatId != null && !selectedCatId.equals(getVirtualLongValue()) && selectedMiniId != null &&
            !selectedMiniId.equals(getVirtualLongValue())) {
            if (ae != null) {
                try {
                    IMinistriesDTO ministryDTO =
                        (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getCodeNameByCatCodeAndMinCode(selectedCatId,
                                                                                                              selectedMiniId);
                    if (ministryDTO != null) {
                        selectedMiniName = ministryDTO.getName();
                    } else {
                        wrongMinCode = true;
                        selectedMiniName = null;
                        return;
                    }
                } catch (Exception e) {
                    wrongMinCode = true;
                    selectedMiniName = null;
                    return;
                }
            }
            enableAddBtn = true;

        }
    }

    public void updateCategories(ActionEvent ae) {
        try {
            enableAddBtn = false;
            setSelectedCatId(getVirtualLongValue());
            selectedMiniId = getVirtualLongValue();
            selectedMiniName = "";
            ministries = new ArrayList<IBasicDTO>();
            //            if(selectedTypeId != null && selectedTypeId != getVirtualLongValue() )
            //            {
            System.out.println("selectedTypeId =================" + selectedTypeId);
            categories = OrgClientFactory.getCatsClient().getCatsByGovFlag(Long.parseLong(selectedTypeId));
            //sharedClient.getCategoriesByGovFlag(governmental);
            //            }else{
            //                categories = new ArrayList<IBasicDTO>();
            //            }
            //requestCreatorDTO.setMinistryName(null);
            //requestCreatorDTO.setMinistryId(null);
        } catch (Exception e) {
            e.printStackTrace();
            categories = new ArrayList<IBasicDTO>();
        }
    }

    public void clearMinData(ActionEvent ae) {
        selectedMiniId = getVirtualLongValue();
        selectedMiniName = "";
        ministries = new ArrayList<IBasicDTO>();
    }

    public void updateMinistryList(ActionEvent ae) {
        if (selectedCatId != null) {
            try {
                enableAddBtn = false;
                selectedMiniId = getVirtualLongValue();
                ministries = OrgClientFactory.getMinistriesClient().getAllByCategory(selectedCatId);
            } catch (Exception e) {
                e.printStackTrace();
                ministries = new ArrayList<IBasicDTO>();
            }
        }
    }

    public void updateBankBranchesList(ActionEvent ae) {
        if (selectedBankId != null) {
            try {
                //selectedBankBranchId = getVirtualLongValue();
                IBanksEntityKey bankKey = (IBanksEntityKey)BnkEntityKeyFactory.createBanksEntityKey(selectedBankId);
                BanksDTO bankDTO = (BanksDTO)BnkClientFactory.getBanksClient().getById(bankKey);
                minsAccountNumber[2] = bankDTO.getShortName();
                bankBranchesList = BnkClientFactory.getBankBranchesClient().getRelatedBranches(selectedBankId);
            } catch (Exception e) {
                e.printStackTrace();
                bankBranchesList = new ArrayList<IBasicDTO>();
            }
        }
    }

    public void search() throws DataBaseException, NoResultException {
        try {
            if (this.getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
    }

    public void setCategories(List<IBasicDTO> categories) {
        this.categories = categories;
    }

    public List<IBasicDTO> getCategories() {
        if (categories == null) {
            try {
                //categories = OrgClientFactory.getCatsClient().getAll();
                categories = OrgClientFactory.getCatsClient().getCatsByGovFlag(Long.parseLong(selectedTypeId));
            } catch (Exception e) {
                e.printStackTrace();
                categories = new ArrayList<IBasicDTO>();
            }
        }
        return categories;
    }

    public void setMinistries(List<IBasicDTO> ministries) {
        this.ministries = ministries;
    }

    public List<IBasicDTO> getMinistries() {
        return ministries;
    }

    public void setSelectedCatId(Long selectedCatId) {
        this.selectedCatId = selectedCatId;
    }

    public Long getSelectedCatId() {
        return selectedCatId;
    }

    public void setSelectedMiniId(Long selectedMiniId) {
        this.selectedMiniId = selectedMiniId;
    }

    public Long getSelectedMiniId() {
        return selectedMiniId;
    }

    public void setMinistryAccountsList(List<IBasicDTO> ministryAccountsList) {
        this.ministryAccountsList = ministryAccountsList;
    }

    public List<IBasicDTO> getMinistryAccountsList() {
        return ministryAccountsList;
    }

    public void preAdd() {
        super.preAdd();

        try {
            int catIndex = getItemIndex(selectedCatId + "", categories);
            IBasicDTO dto = categories.get(catIndex);
            selectedCatName = new String(dto.getName());
            //            int minIndex = getItemIndex(selectedMiniId + "", ministries);
            //            dto = ministries.get(minIndex);
            //            selectedMiniName = new String(dto.getName());

            minsAccountNumber = new String[9];
            minsAccountNumber[0] = "KW";
            reInitializePageDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showEditDiv() {

        reIntializeMsgs();
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                ISalDeductToMinistriesDTO selectedDTO = (ISalDeductToMinistriesDTO)getClient().getById(dto.getCode());
                setSelectedPageDTO(selectedDTO);
                int catIndex = getItemIndex(selectedCatId + "", categories);
                IBasicDTO catDTO = categories.get(catIndex);
                selectedCatName = new String(catDTO.getName());
                //                updateMinistryList(null);
                //                int minIndex = getItemIndex(selectedMiniId + "", ministries);
                //                IBasicDTO minDTO = ministries.get(minIndex);
                //                selectedMiniName = new String(minDTO.getName());
                bankAccountTypeId =
                        ((ISalBankAccountTypesEntityKey)selectedDTO.getSalBankAccountTypesDTO().getCode()).getBnkacttypeCode();
                selectedBankId = ((IBankBranchesEntityKey)selectedDTO.getBankBranchesDTO().getCode()).getBankCode();
                updateBankBranchesList(null);
                selectedBankBranchId =
                        ((IBankBranchesEntityKey)selectedDTO.getBankBranchesDTO().getCode()).getBnkbranchCode();
                String accountNo = selectedDTO.getAccountNo();
                if (accountNo.length() == 30) {
                    minsAccountNumber[0] = accountNo.substring(0, 2);
                    minsAccountNumber[1] = accountNo.substring(2, 4);
                    minsAccountNumber[2] = accountNo.substring(4, 8);
                    minsAccountNumber[3] = accountNo.substring(8, 12);
                    minsAccountNumber[4] = accountNo.substring(12, 16);
                    minsAccountNumber[5] = accountNo.substring(16, 20);
                    minsAccountNumber[6] = accountNo.substring(20, 24);
                    minsAccountNumber[7] = accountNo.substring(24, 28);
                    minsAccountNumber[8] = accountNo.substring(28);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            setShowEdit(true);
            setPageMode(2);

        } else {
            setSelectedPageDTO(SalDTOFactory.createSalDeductToMinistriesDTO());
            setShowEdit(false);
            setPageMode(0);

        }


    }

    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
        setPageMode(0);
        ISalBankAccountTypesDTO dto = SalDTOFactory.createSalBankAccountTypesDTO();
        dto.setCode(SalEntityKeyFactory.createSalBankAccountTypesEntityKey(bankAccountTypeId));
        ((ISalDeductToMinistriesDTO)getSelectedPageDTO()).setSalBankAccountTypesDTO(dto);
        getClient().update(getSelectedPageDTO());
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setSelectedRadio("");
        return getSelectedPageDTO();
    }


    public void reInitializePageDTO() {
        ((ISalDeductToMinistriesDTO)getPageDTO()).setAccountName(null);
        ((ISalDeductToMinistriesDTO)getPageDTO()).setAccountNo(null);
        selectedPurposeId = getVirtualLongValue();
        bankAccountTypeId = getVirtualLongValue();
        ((ISalDeductToMinistriesDTO)getPageDTO()).setStatus(statusList.get(0));

        if (selectedTypeId.equals(govFlag)) {
            setSelectedBankId(1L);
            updateBankBranchesList(null);
        } else {
            setSelectedBankId(getVirtualLongValue());
            setBankBranchesList(new ArrayList<IBasicDTO>());
        }
        setSelectedBankBranchId(null);
    }

    public void setEnableAddBtn(boolean enableAddBtn) {
        this.enableAddBtn = enableAddBtn;
    }

    public boolean isEnableAddBtn() {
        return enableAddBtn;
    }


    public void setSelectedCatName(String selectedCatName) {
        this.selectedCatName = selectedCatName;
    }

    public String getSelectedCatName() {
        return selectedCatName;
    }

    public void setSelectedMiniName(String selectedMiniName) {
        this.selectedMiniName = selectedMiniName;
    }

    public String getSelectedMiniName() {
        return selectedMiniName;
    }

    public void setAccountPurposesList(List<IBasicDTO> accountPurposesList) {
        this.accountPurposesList = accountPurposesList;
    }

    public List<IBasicDTO> getAccountPurposesList() {
        if (accountPurposesList.isEmpty()) {
            try {
                accountPurposesList = SalClientFactory.getSalBankAccountTypesClient().getCodeName();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return accountPurposesList;
    }


    public void setStatusList(List<Long> statusList) {
        this.statusList = statusList;
    }

    public List<Long> getStatusList() {
        if (statusList.isEmpty()) {
            statusList.add(IScpConstants.ACCOUNT_VALID_STATUS);
            statusList.add(IScpConstants.ACCOUNT_NOT_VALID_STATUS);
            statusList.add(IScpConstants.ACCOUNT_FREEZE_STATUS);
        }
        return statusList;
    }

    public void setSelectedPurposeId(Long selectedPurposeId) {
        this.selectedPurposeId = selectedPurposeId;
    }

    public Long getSelectedPurposeId() {
        return selectedPurposeId;
    }

    public String getNewErrMsgValue() {
        String val = "";
        if (getErrorMsg() == null || getErrorMsg().equals("")) {
            return "";
        }
        try {
            val = getSharedUtil().messageLocator(globalExBundle, "FailureInAdd");
            val = getSharedUtil().messageLocator(globalExBundle, getErrorMsg());
            if (val == null || val.equals("")) {
                val = getSharedUtil().messageLocator(localBundle, getErrorMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public void add() throws DataBaseException {

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
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");

        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e, localBundle, "account_no_already_exist");
            this.setErrorMsg("account_no_already_exist");
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            if (e instanceof InvalidBankAccountNumber) {
                this.setErrorMsg("invalid_Account_number");
                sharedUtil.handleException(e, localBundle, "invalid_Account_number");
            } else {
                this.setErrorMsg("FailureInAdd");
                sharedUtil.handleException(e);
            }


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("account_no_already_exist");
            sharedUtil.handleException(e, localBundle, "account_no_already_exist");

        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }

    public IBasicDTO executeAdd() throws DataBaseException, SharedApplicationException {

        //  selectedPurposeId
        ISalBankAccountTypesDTO dto = SalDTOFactory.createSalBankAccountTypesDTO();
        dto.setCode(SalEntityKeyFactory.createSalBankAccountTypesEntityKey(selectedPurposeId));
        ((ISalDeductToMinistriesDTO)getPageDTO()).setSalBankAccountTypesDTO(dto);
        //if(selectedBankBranchId == null)selectedBankBranchId = 1L;
        BankBranchesEntityKey bbEK =
            (BankBranchesEntityKey)BnkEntityKeyFactory.createBankBranchesEntityKey(selectedBankId,
                                                                                   selectedBankBranchId);
        BankBranchesDTO bbdto = (BankBranchesDTO)BnkClientFactory.getBankBranchesClient().getById(bbEK);
        //InfDTOFactory.createBankBranchesDTO(selectedBankId, selectedBankBranchId , "");
        //// commented by nora until sal change
        ((ISalDeductToMinistriesDTO)getPageDTO()).setBankBranchesDTO(bbdto);
        ((ISalDeductToMinistriesDTO)getPageDTO()).setAccountNo(ConcatAccountNo());

        IMinistriesDTO ministriesDTO = OrgDTOFactory.createMinistriesDTO(selectedMiniId, "");
        ministriesDTO.setCatKey(selectedCatId.toString() );
        System.out.println("MinistriesBnkAccountsBean executeAdd() selectedMiniId = "+selectedMiniId + " , selectedCatId = "+selectedCatId);
        ((ISalDeductToMinistriesDTO)getPageDTO()).setMinistriesDTO(ministriesDTO);

        return getClient().add(getPageDTO());
    }

    private String ConcatAccountNo() {

        String strAccountNumber = "";
        if (minsAccountNumber == null || minsAccountNumber.length == 0)
            return strAccountNumber;

        for (int iIndex = 0; iIndex < minsAccountNumber.length; iIndex++) {
            strAccountNumber += minsAccountNumber[iIndex];
        }
        return strAccountNumber;
    }

    public void cancelAdd() {

        super.cancelAdd();
        setPageMode(0);
        minsAccountNumber = new String[9];
        minsAccountNumber[0] = "KW";

    }

    public void cancelEdit() {
        setPageMode(0);
    }

    public List getTotalList() {
        if (selectedMiniId != null && !selectedMiniId.equals(getVirtualLongValue())) {
            updateAccountsList();
        }
        return ministryAccountsList;
    }

    public void save() {
        super.save();
        setPageMode(0);
    }

    public void saveAndNew() throws DataBaseException {
        reIntializeMsgs();
        this.add();
        if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals("")) {
            this.setSuccess(true);
            this.reInitializePageDTO();
        }
    }

    public void setGovFlag(Long govFlag) {
        this.govFlag = govFlag;
    }

    public Long getGovFlag() {
        return govFlag;
    }

    public void setNonGovFlag(Long nonGovFlag) {
        this.nonGovFlag = nonGovFlag;
    }

    public Long getNonGovFlag() {
        return nonGovFlag;
    }

    public void setSelectedTypeId(String selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
    }

    public String getSelectedTypeId() {
        return selectedTypeId;
    }

    public void setBanksList(List<IBasicDTO> banksList) {
        this.banksList = banksList;
    }

    public List<IBasicDTO> getBanksList() {
        if (banksList.isEmpty()) {
            try {
                banksList = BnkClientFactory.getBanksClient().getAll();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return banksList;
    }

    public void setBankBranchesList(List<IBasicDTO> bankBranchesList) {
        this.bankBranchesList = bankBranchesList;
    }

    public List<IBasicDTO> getBankBranchesList() {
        return bankBranchesList;
    }

    public void setSelectedBankId(Long selectedBankId) {
        this.selectedBankId = selectedBankId;
    }

    public Long getSelectedBankId() {
        return selectedBankId;
    }

    public void setSelectedBankBranchId(Long selectedBankBranchId) {
        this.selectedBankBranchId = selectedBankBranchId;
    }

    public Long getSelectedBankBranchId() {
        return selectedBankBranchId;
    }

    public void setMinsAccountNumber(String[] minsAccountNumber) {
        this.minsAccountNumber = minsAccountNumber;
    }

    public String[] getMinsAccountNumber() {
        return minsAccountNumber;
    }

    public void setBankAccountTypeId(Long bankAccountTypeId) {
        this.bankAccountTypeId = bankAccountTypeId;
    }

    public Long getBankAccountTypeId() {
        return bankAccountTypeId;
    }

    public void setSearchMinistryHelper(SearchMinistryWithPagingHelper SearchMinistryHelper) {
        this.searchMinistryHelper = SearchMinistryHelper;
    }

    public SearchMinistryWithPagingHelper getSearchMinistryHelper() {
        if (searchMinistryHelper == null) {
            searchMinistryHelper = new SearchMinistryWithPagingHelper();
        }
        return searchMinistryHelper;
    }

    public void setWrongMinCode(boolean wrongMinCode) {
        this.wrongMinCode = wrongMinCode;
    }

    public boolean isWrongMinCode() {
        return wrongMinCode;
    }

    public void setLogged_in_APP_MODULE_CODE(Long Logged_in_APP_MODULE_CODE) {
        this.Logged_in_APP_MODULE_CODE = Logged_in_APP_MODULE_CODE;
    }

    public Long getLogged_in_APP_MODULE_CODE() {
        return Logged_in_APP_MODULE_CODE;
    }

    public void setBNK_APP_MODULE_CODE(Long BNK_APP_MODULE_CODE) {
        this.BNK_APP_MODULE_CODE = BNK_APP_MODULE_CODE;
    }

    public Long getBNK_APP_MODULE_CODE() {
        return BNK_APP_MODULE_CODE;
    }

    public void setSCP_APP_MODULE_CODE(Long SCP_APP_MODULE_CODE) {
        this.SCP_APP_MODULE_CODE = SCP_APP_MODULE_CODE;
    }

    public Long getSCP_APP_MODULE_CODE() {
        return SCP_APP_MODULE_CODE;
    }

    public void setBanksTypeList(List banksTypeList) {
        this.banksTypeList = banksTypeList;
    }

    public List getBanksTypeList() {
        return banksTypeList;
    }
}
