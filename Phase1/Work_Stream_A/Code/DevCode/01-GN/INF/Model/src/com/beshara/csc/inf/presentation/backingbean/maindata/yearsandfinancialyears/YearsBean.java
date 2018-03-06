package com.beshara.csc.inf.presentation.backingbean.maindata.yearsandfinancialyears;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.dto.IYearsSearchDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.YearsEntityKey;
import com.beshara.csc.inf.business.exception.InfYearCodeAlreadyExistsException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeUpdatedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;


public class YearsBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:973922404605106320")
    private static final long serialVersionUID = -7222272690178807192L;

    private String yearCode;

    private Date budgetStartDate;
    private Date budgetEndDate;

    private Date budgetStartAddDate;
    private Date budgetEndAddDate;

    private Date startDate;
    private Date endDate;
    private IYearsSearchDTO yearsSearchDto = InfDTOFactory.createYearsSearchDto();


    public YearsBean() {
        setClient(InfClientFactory.getYearsClient());
        setSelectedPageDTO(InfDTOFactory.createYearsDTO());
        setPageDTO(InfDTOFactory.createYearsDTO());
        setSingleSelection(true);
        setSaveSortingState(true);
        //        getLocale();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowCustomDiv1(true);
        return app;
    }

    // clearing recent values after adding new record

    //    public void getLocale(){
    //        Locale locale = Locale.getDefault();
    //        String local = locale.toString();
    //        if(local.contains("ar")){
    //            dateFormat = "yyyy/MM/dd" ;
    //            dateRegex =
    //                    "/^([2][0]\\d{2}\\/([0]\\d|[1][0-2])\\/([0-2]\\d|[3][0-1]))$|^([2][0]\\d{2}\\/([0]\\d|[1][0-2])\\/([0-2]\\d|[3][0-1])\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$/";
    //        }else if(local.contains("en")){
    //            dateFormat = "dd/MM/yyyy";
    //            dateRegex =
    //                    "/^(([0-2]\\d|[3][0-1])\\/([0]\\d|[1][0-2])\\/[2][0]\\d{2})$|^(([0-2]\\d|[3][0-1])\\/([0]\\d|[1][0-2])\\/[2][0]\\d{2}\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$/";
    //            }
    //        }

    public void clearRecentValues() {
        yearCode = null;
        startDate = null;
        endDate = null;
        budgetStartDate = null;
        budgetEndDate = null;
        setPageDTO(InfDTOFactory.createYearsDTO());
    }

    // method to create start and end date of a given year

    public void getPublishedDate() {
        startDate = new Date(yearCode + "/01/01");
        endDate = new Date(yearCode + "/12/31");

    }


    public void showEditDiv() {
        //TODO locking code
        // before showing the edit div we must lock this item
        // if the locking failed we are going to cancel the edit operation
        // and shows the exception message to the user instead of the edit div
        // This will be handled using the executeAfterLock javascript function
        //        SharedUtilBean sharedUtil = getSharedUtil();

        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            //            selectedPageDTO=this.getSelectedDTOS().get(0);
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                setSelectedPageDTO(getClient().getByIdInCenter(dto.getCode()));
                budgetStartDate = new Date(((IYearsDTO)getSelectedPageDTO()).getBudgetStartDate().getTime());
                budgetEndDate = new Date(((IYearsDTO)getSelectedPageDTO()).getBudgetEndDate().getTime());
                //                startDate = new Date(((IYearsDTO)getSelectedPageDTO()).getStartDate().getTime());
                //                endDate = new Date(((IYearsDTO)getSelectedPageDTO()).getEndDate().getTime());
            }
            //             catch (ItemNotFoundException e) {
            //                 sharedUtil.handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInUpdate");
            //                 e.printStackTrace();
            //             }
            catch (Exception e) {
                e.printStackTrace();
            }
            setShowEdit(true);
            //javaScriptCaller = "changeVisibilityDiv(window.blocker,window.lookupEditDiv);";
        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);
        }
        if (!lock()) {
            return;
        }
        setPageMode(2);
        reIntializeMsgs();
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

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
                    //                    ((IYearsDTO)getSelectedPageDTO()).setStartDate(new Timestamp(start_date.getTime()));
                    //                    ((IYearsDTO)getSelectedPageDTO()).setEndDate(new Timestamp(end_date.getTime()));
                    ((IYearsDTO)getSelectedPageDTO()).setBudgetStartDate(new Timestamp(budgetStartDate.getTime()));
                    ((IYearsDTO)getSelectedPageDTO()).setBudgetEndDate(new Timestamp(budgetEndDate.getTime()));
                    super.getClient().update(super.getSelectedPageDTO());
                } finally {
                    //TODO locking code
                    // unlock the edited item in update success or failure
                    // so we added it in this finally block
                    unlock();
                }

                cancelSearch();

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
        clearRecentValues();

    }

    public void preAdd() {
        clearRecentValues();
        setPageMode(1);
        super.preAdd();
    }

    // this method to add all new attributes to PageDTO to be saved

    public void fillNewAttInPageDTO() {
        ((IYearsDTO)getPageDTO()).setCode(new YearsEntityKey(Long.parseLong(yearCode)));
        ((IYearsDTO)getPageDTO()).setYearCode(Long.parseLong(yearCode));
        ((IYearsDTO)getPageDTO()).setStartDate(new Timestamp(startDate.getTime()));
        ((IYearsDTO)getPageDTO()).setEndDate(new Timestamp((endDate.getTime())));
        ((IYearsDTO)getPageDTO()).setBudgetStartDate(new Timestamp(budgetStartAddDate.getTime()));
        ((IYearsDTO)getPageDTO()).setBudgetEndDate(new Timestamp(getBudgetEndAddDate().getTime()));
    }

    public void save() {

        reIntializeMsgs();
        getPublishedDate();
        fillNewAttInPageDTO();
        try {
            try {
                if (getClient().getById(getPageDTO().getCode()) != null) {
                    throw new InfYearCodeAlreadyExistsException();
                }
            } catch (NoResultException e) {
                this.add();
            } catch (InfYearCodeAlreadyExistsException e) {
                getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf_ar",
                                                "yearCodeAlreadyExists");
            } catch (SharedApplicationException e) {
            }

        } catch (DataBaseException e) {
        }
        this.reInitializePageDTO();
        reIntializeAddDivMsgs();
        clearRecentValues();
    }

    public void saveAndNew() throws DataBaseException {
        reIntializeMsgs();
        getPublishedDate();
        fillNewAttInPageDTO();
        this.add();
        if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals("")) {
            this.setSuccess(true);
        }

        this.reInitializePageDTO();
        clearRecentValues();
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createYearsDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        setSearchMode(true);
        if (getYearCode() != null && !getYearCode().equals("")) {
            getYearsSearchDto().setYearCode(Long.valueOf(getYearCode()));
        }
        if (((IYearsDTO)getPageDTO()).getYearName() != null && !(((IYearsDTO)getPageDTO()).getYearName()).equals("")) {
            getYearsSearchDto().setYearName(((IYearsDTO)getPageDTO()).getYearName());
        }
        if (getEndDate() != null) {
            getYearsSearchDto().setEndDate(new Timestamp(getEndDate().getTime()));
        }
        if (getStartDate() != null) {
            getYearsSearchDto().setStartDate(new Timestamp(getStartDate().getTime()));
        }
        if (getYearsSearchDto() != null) {


            try {
                setMyTableData(getClient().search(getYearsSearchDto()));
            } catch (SharedApplicationException e) {
                setMyTableData(new ArrayList<IBasicDTO>());
                e.printStackTrace();
            } catch (DataBaseException e) {
                setMyTableData(new ArrayList<IBasicDTO>());
                e.printStackTrace();
            } catch (Exception e) {
                setMyTableData(new ArrayList<IBasicDTO>());
                e.printStackTrace();
            }
            //            if (getSelectedDTOS() != null)
            //                getSelectedDTOS().clear();
            //            if (getHighlightedDTOS() != null)
            //                getHighlightedDTOS().clear();
            //            this.repositionPage(0);
        }
    }

    public void ShowDivSearch() {
        setPageMode(3);
    }

    public void cancelSearch() {
        setPageMode(0);
        clearRecentValues();
        try {
            super.cancelSearch();
        } catch (DataBaseException e) {
        }
    }

    public void cancelAdd() {
        System.out.println("Calling cancelAdd()...");
        this.getPageDTO().setName("");
        reIntializeMsgs();
        try {
            unCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearRecentValues();
    }


    public void cancelEdit() {
        setPageMode(0);
        clearRecentValues();
    }


    public void setBudgetStartDate(Date budgetStartDate) {
        this.budgetStartDate = budgetStartDate;
    }

    public Date getBudgetStartDate() {
        return budgetStartDate;
    }

    public void setBudgetEndDate(Date budgetEndDate) {
        this.budgetEndDate = budgetEndDate;
    }

    public Date getBudgetEndDate() {
        return budgetEndDate;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }

    public String getYearCode() {
        return yearCode;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    public void setYearsSearchDto(IYearsSearchDTO yearsSearchDto) {
        this.yearsSearchDto = yearsSearchDto;
    }

    public IYearsSearchDTO getYearsSearchDto() {
        return yearsSearchDto;
    }

    public void setBudgetStartAddDate(Date budgetStartAddDate) {
        this.budgetStartAddDate = budgetStartAddDate;
    }

    public Date getBudgetStartAddDate() {
        return budgetStartAddDate;
    }

    public void setBudgetEndAddDate(Date budgetEndAddDate) {
        this.budgetEndAddDate = budgetEndAddDate;
    }

    public Date getBudgetEndAddDate() {
        return budgetEndAddDate;
    }
}
