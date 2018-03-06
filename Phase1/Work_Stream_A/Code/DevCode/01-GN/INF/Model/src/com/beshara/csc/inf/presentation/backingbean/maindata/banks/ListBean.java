package com.beshara.csc.inf.presentation.backingbean.maindata.banks;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.IBankBranchesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.BanksDTO;
import com.beshara.csc.inf.business.dto.IBanksDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IBanksEntityKey;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.entity.MinistriesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class ListBean extends LookUpBaseBean {


    private boolean showLinkDiv;
    private List relatedBranchesList = new ArrayList();
    private int branchesListSize = 0;
    IBankBranchesClient bankBranchesClient;
    private int branchTableRowSize = 13;
    private String selecteBankName;
    private Long selecteBankCode;
    private HtmlDataTable branchesDataTable;
   

    public ListBean() {
        setClient(InfClientFactory.getBanksClient());
        bankBranchesClient = InfClientFactory.getBankBranchesClient();
        setSelectedPageDTO(InfDTOFactory.createBanksDTO());
        setPageDTO(InfDTOFactory.createBanksDTO());
        setSingleSelection(true);
        setSaveSortingState(true);
    }

    public List getTotalList() {
        IMinistriesClient minClient = OrgClientFactory.getMinistriesClient();
        List<BanksDTO> totalList = null;
        try {
            IBasicDTO filterDTO = getFilterDTO();

            if (filterDTO == null) {
                totalList = (List)getClient().getAllInCenter();
            } else {
                totalList = (List)getClient().getAllInCenter(filterDTO);
            }
            for (BanksDTO bank : totalList) {
                if (bank.getMinCode() != null) {
                    IBasicDTO minDTO = (IMinistriesDTO)minClient.getById(new MinistriesEntityKey(bank.getMinCode()));
                    bank.setMinName(minDTO.getName());
                }

            }

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

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowCustomDiv1(true);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createBanksDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void getRelatedBranches() {
        reIntializeMsgs();
        setShowLinkDiv(true);
        if (getRelatedBranchesList().size() == 0) {
//            try {
//                throw new DataBaseException("");
//            } catch (DataBaseException e) {
//               // setShowLinkDiv(false);
                
//                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.global_ar",
//                                                "global_noTableResults");
                this.setShowErrorMsg(true);
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global_ar", "global_noTableResults"));
                this.setErrorMsg(getSharedUtil().getErrMsgValue());
                setShowLinkDiv(false);
                setSelectedRadio("");
  //          }
        } else {
            getBranchesListSize();
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");

            //setPageMode(3);
        }
        //        return new IBasicDTO();
    }

    public void openLovDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }
        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
        Integer listSize = branchesListSize;
        Boolean flage = listSize == 0 ? true : false;
    }

    public List<BanksDTO> getBaseSearchResult() throws DataBaseException {

        List<BanksDTO> searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult = (List)getClient().searchByCode(getSearchEntityObj());
            } else if (getSearchType().intValue() == 1) {
                searchResult = (List)getClient().searchByName(formatSearchString(getSearchQuery()));
            }

            for (BanksDTO bank : searchResult) {
                if (bank.getMinCode() != null) {
                    IBasicDTO minDTO =
                        OrgClientFactory.getMinistriesClient().getById(new MinistriesEntityKey(bank.getMinCode()));
                    bank.setMinName(minDTO.getName());
                }

            }
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    public void cancelAdd() {
        super.cancelAdd();
        setPageMode(0);
        reInitializePageDTO();
    }

    public void cancelLink() {
        setPageMode(0);
        setShowLinkDiv(false);
        setSelectedPageDTO(InfDTOFactory.createBanksDTO());
        reIntializeMsgs();
        //        setCurrentPageIndex(1);
        //        setOldPageIndex(0);
        getBranchesDataTable().setFirst(0);
    }

    public void cancelEdit() {
        setPageMode(0);
    }

    public void setShowLinkDiv(boolean showLinkDiv) {
        this.showLinkDiv = showLinkDiv;
    }

    public boolean isShowLinkDiv() {
        return showLinkDiv;
    }

    public void setRelatedBranchesList(List relatedBranchesList) {
        this.relatedBranchesList = relatedBranchesList;
    }

    public List getRelatedBranchesList() {
        if (showLinkDiv == true) {
            Long bankCode = ((IBanksEntityKey)((IBanksDTO)getSelectedDTOS().get(0)).getCode()).getBankCode();
            setSelecteBankName(getSelectedDTOS().get(0).getName());
            setSelecteBankCode(((IBanksEntityKey)((IBanksDTO)getSelectedDTOS().get(0)).getCode()).getBankCode());
            try {
                relatedBranchesList = bankBranchesClient.getRelatedBranches(bankCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return relatedBranchesList;
    }

    public void setBranchesListSize(int branchesListSize) {
        this.branchesListSize = branchesListSize;
    }

    public int getBranchesListSize() {
        return relatedBranchesList.size();
    }

    public void setBankBranchesClient(IBankBranchesClient bankBranchesClient) {
        this.bankBranchesClient = bankBranchesClient;
    }

    public IBankBranchesClient getBankBranchesClient() {
        return bankBranchesClient;
    }

    public void setBranchTableRowSize(int branchTableRowSize) {
        this.branchTableRowSize = branchTableRowSize;
    }

    public int getBranchTableRowSize() {
        return branchTableRowSize;
    }

    public void setSelecteBankName(String selecteBankName) {
        this.selecteBankName = selecteBankName;
    }

    public String getSelecteBankName() {
        return selecteBankName;
    }

    public void setSelecteBankCode(Long selecteBankCode) {
        this.selecteBankCode = selecteBankCode;
    }

    public Long getSelecteBankCode() {
        return selecteBankCode;
    }

    public void setBranchesDataTable(HtmlDataTable branchesDataTable) {
        this.branchesDataTable = branchesDataTable;
    }

    public HtmlDataTable getBranchesDataTable() {
        return branchesDataTable;
    }
}
