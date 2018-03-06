package com.beshara.csc.inf.presentation.backingbean.maindata.infholidays;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.inf.business.shared.IInfConstant;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;


public class HolidaysListBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:2951528510615883133")
    private static final long serialVersionUID = 1L;
    private String selectedYears;
    private List<IBasicDTO> yearsList;
    private boolean status;

    private Date fromDate;
    private Date untilDate;
    private String holidayDesc;
    private boolean enableAddButton;
   
   
    public HolidaysListBean() {

        setPageDTO(InfDTOFactory.createHolidaysSimpleDTO());
        super.setSelectedPageDTO(InfDTOFactory.createHolidaysDTO());
        setClient(InfClientFactory.getHolidaysClient());
        setSingleSelection(true);
        setSaveSortingState(true);
        setSelectedYears( getSharedUtil().currentYear()+"");
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createHolidaysSimpleDTO());
    }

    public void initiateBeanOnce() {
        loadYearsList();
        loadTable();

    }
    public void add() throws DataBaseException {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            setPageDTO(executeAdd());
            loadTable();
            getPageIndex((String)getPageDTO().getCode().getKey());

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
                getHighlightedDTOS().add(getPageDTO());
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");
            
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
        
    }
    protected IBasicDTO executeAdd() throws DataBaseException, SharedApplicationException {
       ( (IHolidaysSimpleDTO)getPageDTO()).setYearCode(Long.valueOf(getSelectedYears()));
        ( (IHolidaysSimpleDTO)getPageDTO()).setStatus(1L);
        ( (IHolidaysSimpleDTO)getPageDTO()).setHoltypeCode(IInfConstant.HOLIDAY_TYPE_CODE);
        return InfClientFactory.getHolidaysClient().addHolidays(getPageDTO());
    }
    
    
    public void preAdd() {
        super.preAdd();
        this.setPageDTO(InfDTOFactory.createHolidaysSimpleDTO());
    }
    public void loadYearsList() {
        try {
            yearsList = (List)InfClientFactory.getYearsClient().getCodeName();
        } catch (Exception e) {
            yearsList = new ArrayList<IBasicDTO>();
        }
    }
    
    public List getTotalList() {
        
        return  new ArrayList();

    }
    
    public void loadTable(){
            setEnableAddButton(false);
        if(getSelectedYears() != null && !"".equals(getSelectedYears())){
        try{
            setMyTableData(InfClientFactory.getHolidaysClient().getAllByYearAndTypeCode(Long.valueOf(getSelectedYears()),IInfConstant.HOLIDAY_TYPE_CODE ));
            setEnableAddButton(true);
        }catch(Exception ex){
            ex.printStackTrace();
            setEnableAddButton(true);
            setMyTableData(new ArrayList());
        }
        }else{
            setMyTableData(new ArrayList());
        }
        getSelectedDTOS().clear();
        setSelectedRadio("");
    }
    

    public void showEditDiv() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            setSelectedPageDTO(getSelectedDTOS().get(0));
            setShowEdit(true);
            if (getSelectedPageDTO() != null && ((IHolidaysSimpleDTO)getSelectedPageDTO()).getStatus().equals(1L)) {
                setStatus(true);
            } else {
                setStatus(false);
            }
        }
        reIntializeMsgs();
    }

    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
        if (isStatus()) {
            ((IHolidaysSimpleDTO)getSelectedPageDTO()).setStatus(1L);
        } else {
            ((IHolidaysSimpleDTO)getSelectedPageDTO()).setStatus(0L);
        }
        InfClientFactory.getHolidaysClient().updateSimpleHoliday(super.getSelectedPageDTO());
        return getSelectedPageDTO();
    }
    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {

        super.edit();
        loadTable();

    }
    public void openSearchDiv() {
        resetData();
    }

    public void search() throws DataBaseException, NoResultException {
        setSearchType(1);
        this.setSearchMode(true);
        try {
            IHolidaysSimpleDTO searchDTO = InfDTOFactory.createHolidaysSimpleDTO();
            searchDTO.setFromDate(getFromDate());
            searchDTO.setUntilDate(getUntilDate());
            searchDTO.setHolidayDesc(getHolidayDesc());
            List searchResult = InfClientFactory.getHolidaysClient().search(searchDTO);

            setMyTableData(searchResult);

        } catch (Exception e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        }

        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
        this.repositionPage(0);
        setSelectedRadio("");
        reInitializePageDTO();
    }

    public void resetData() {
        setFromDate(null);
        setUntilDate(null);
        setHolidayDesc(null);
    }


    public void setSelectedYears(String selectedYears) {
        this.selectedYears = selectedYears;
    }

    public String getSelectedYears() {
        return selectedYears;
    }

    public void setYearsList(List<IBasicDTO> yearsList) {
        this.yearsList = yearsList;
    }

    public List<IBasicDTO> getYearsList() {
        return yearsList;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setEnableAddButton(boolean enableAddButton) {
        this.enableAddButton = enableAddButton;
    }

    public boolean isEnableAddButton() {
        return enableAddButton;
    }
}

