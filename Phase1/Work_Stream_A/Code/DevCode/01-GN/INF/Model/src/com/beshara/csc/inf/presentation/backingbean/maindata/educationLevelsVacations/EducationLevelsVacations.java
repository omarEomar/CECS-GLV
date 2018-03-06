package com.beshara.csc.inf.presentation.backingbean.maindata.educationLevelsVacations;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.base.security.ICategoryInfo;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.hr.bgt.business.client.IBgtProgramsClient;
import com.beshara.csc.hr.bgt.business.dto.BgtProgramsDTO;
import com.beshara.csc.inf.business.client.ISpecialPeriodTypesClient;
import com.beshara.csc.inf.business.client.ISpecialPeriodsClient;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.SpecialPeriodTypesDTO;
import com.beshara.csc.inf.business.dto.SpecialPeriodsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class EducationLevelsVacations extends LookUpBaseBean {
    private static final Long DEFAULT_STATUS = 1L;
    private static final String BUNDLE_NAME = "com.beshara.csc.inf.presentation.resources.inf";
    private boolean displayButtonDisabled;
    private Long periodTypeID;
    private List<SpecialPeriodTypesDTO> periodTypesList;
    private List<BgtProgramsDTO> bgtProgramsDTOList;
    private SpecialPeriodsDTO specialPeriodsDTO;
    private boolean resetData;
    private Long selectedYear;
    private List yearList;
    private String selCategoyName;
    private String selMinistryName;
    private String selperiodName;
    private boolean booleanStatus;
    // categories - ministries
    private List<ICategoryInfo> categories = new ArrayList<ICategoryInfo>(0);
    private List<IMinistryInfo> ministries = new ArrayList<IMinistryInfo>(0);
    private Long categoryID;
    private Long ministryID;

    public EducationLevelsVacations() {
        setClient(ClientFactoryUtil.getInstance(ISpecialPeriodsClient.class));
        setPageDTO(new SpecialPeriodsDTO());
        setSelectedPageDTO(new SpecialPeriodsDTO());
        setUsingPaging(false);
        setUsingBsnPaging(false);
        setSingleSelection(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowpaging(true);
        app.setShowContent1(true);
        app.setShowLookupAdd(true);
        app.setShowLookupEdit(true);

        app.setShowCustomDiv1(false);
        app.setShowIntegrationDiv1(false);
        app.setShowDelConfirm(false);
        app.setShowDelAlert(false);
        app.setShowSearch(false);
        return app;
    }

    @Override
    public void reInitializePageDTO() {
        this.setPageDTO(new SpecialPeriodsDTO());
        ((SpecialPeriodsDTO)getPageDTO()).setMinCode(getMinistryID());
        ((SpecialPeriodsDTO)getPageDTO()).setSpcprdtypeCode(getperiodTypeID());
        ((SpecialPeriodsDTO)getPageDTO()).setYearCode(getSelectedYear());
    }

    @Override
    public void initiateBeanOnce() {
        fillDefaultCategoryMinistry();
        fillperiodTypesList();
        fillYearList();
        fillBgtProgramsDTOList();
        Calendar cal = Calendar.getInstance();
        selectedYear = (long)cal.get(Calendar.YEAR);
    }

    @Override
    public List getTotalList() {
        // - DO Nothing...
        return new ArrayList();
    }

    public void fillDataTable() throws DataBaseException {
        setDisplayButtonDisabled(true);
        fillSelCategoyName();
        fillSelMinistryName();
        fillSelperiodName();
        try {
            if (specialPeriodsDTO == null)
                specialPeriodsDTO = new SpecialPeriodsDTO();
            specialPeriodsDTO.setMinCode(getMinistryID());
            specialPeriodsDTO.setSpcprdtypeCode(getperiodTypeID());
            List ListData = ((ISpecialPeriodsClient)getClient()).getAllByTypeAndMinCode(specialPeriodsDTO);
            setMyTableData(ListData);
        } catch (Exception e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        }
    }

    @Override
    public void preAdd() {
        reInitializePageDTO();
        super.preAdd();
    }

    @Override
    public void add() throws DataBaseException {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            ((SpecialPeriodsDTO)getPageDTO()).setStatus(DEFAULT_STATUS);
            setPageDTO(executeAdd());
            if (isUsingPaging()) {
                getPagingBean().clearDTOS();
                generatePagingRequestDTO((String)getPageDTO().getCode().getKey());
            } else {
                fillDataTable();
                getPageIndex((String)getPageDTO().getCode().getKey());
            }

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
            sharedUtil.handleException(e, BUNDLE_NAME, e.getMessage());
            this.setErrorMsg(e.getMessage());
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        setSelectedRadio("");
    }

    @Override
    public void showEditDiv() {
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            setSelectedPageDTO(dto);
            setShowEdit(true);

            if (((SpecialPeriodsDTO)dto).getStatus() == null || ((SpecialPeriodsDTO)dto).getStatus() == 0L) {
                booleanStatus = false;
            } else {
                booleanStatus = true;
            }
        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);
        }
        setPageMode(2);
        reIntializeMsgs();
    }

    @Override
    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        if (booleanStatus) {
            ((SpecialPeriodsDTO)getSelectedPageDTO()).setStatus(1L);
        } else {
            ((SpecialPeriodsDTO)getSelectedPageDTO()).setStatus(0L);
        }
        super.edit();
        fillDataTable();
    }

    public void resetFilters() {
        setDisplayButtonDisabled(false);
        fillDefaultCategoryMinistry();
        specialPeriodsDTO = new SpecialPeriodsDTO();
        setMyTableData(new ArrayList());
        setResetData(true);
        setperiodTypeID(null);
        resetPageIndex();
        getSelectedDTOS().clear();
    }

    public void fillperiodTypesList() {
        try {
            setperiodTypesList((ClientFactoryUtil.getInstance(ISpecialPeriodTypesClient.class)).getCodeName());
        } catch (Exception e) {
            System.err.println("Error periodTypesList " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillYearList() {
        try {
            IYearsClient client = InfClientFactory.getYearsClient();
            yearList = client.getAll();
        } catch (Exception e) {
            System.err.println("Error fillYearList " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillBgtProgramsDTOList() {

        try {
            IBgtProgramsClient bgtProgramsClient = (ClientFactoryUtil.getInstance(IBgtProgramsClient.class));

            setBgtProgramsDTOList(bgtProgramsClient.getCodeName());
        } catch (Exception e) {
            System.err.println("Error fillBgtProgramsDTOList " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void fillSelCategoyName() {
        selCategoyName = "";
        if (getCategories() != null && getCategoryID() != null) {
            for (ICategoryInfo selCategory : getCategories()) {
                if (getCategoryID().equals(selCategory.getCode())) {
                    selCategoyName = selCategory.getName();
                    break;
                }
            }
        }
    }

    public void fillSelMinistryName() {
        selMinistryName = "";
        if (getMinistries() != null && getMinistryID() != null) {
            for (IMinistryInfo selMinistry : getMinistries()) {
                if (getMinistryID().equals(selMinistry.getCode())) {
                    selMinistryName = selMinistry.getName();
                    break;
                }
            }
        }
    }

    public void fillSelperiodName() {
        selperiodName = "";
        if (getperiodTypesList() != null && getperiodTypeID() != null) {
            for (SpecialPeriodTypesDTO selPeriod : getperiodTypesList()) {
                if (getperiodTypeID().equals(selPeriod.getSpcprdtypeCode())) {
                    selperiodName = selPeriod.getSpcprdtypeName();
                    break;
                }
            }
        }
    }
    // Start: categories - ministries

    public void fillDefaultCategoryMinistry() {
        setCategoryID(CSCSecurityInfoHelper.getLoggedInCategoryCode());
        fillMinistries();
        setMinistryID(CSCSecurityInfoHelper.getLoggedInMinistryCode());
    }

    public void setCategories(List<ICategoryInfo> categories) {
        this.categories = categories;
    }

    public void fillMinistries() {
        setMinistryID(null);
        getRelatedCategoryMinistries();
    }

    public void getRelatedCategoryMinistries() {
        ministries = new ArrayList<IMinistryInfo>(0);
        if (getCategoryID() != null) {
            try {
                for (ICategoryInfo iCategoryInfo : getCategories()) {
                    if (iCategoryInfo.getCode().equals(getCategoryID())) {
                        ministries.addAll(iCategoryInfo.getMinistries());
                        break;
                    }
                }
            } catch (Exception e) {
                ministries = new ArrayList<IMinistryInfo>(0);
                e.printStackTrace();
            }
        }
    }

    public List<ICategoryInfo> getCategories() {
        if (categories.size() == 0) {
            try {
                categories = (List<ICategoryInfo>)CSCSecurityInfoHelper.getCategories();
            } catch (Exception e) {
                categories = new ArrayList<ICategoryInfo>(0);
                e.printStackTrace();
            }
        }
        if (categories == null) {
            categories = new ArrayList<ICategoryInfo>(0);
        }
        return categories;
    }

    public void setMinistries(List<IMinistryInfo> ministries) {
        this.ministries = ministries;
    }

    public List<IMinistryInfo> getMinistries() {
        return ministries;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setMinistryID(Long ministryID) {
        this.ministryID = ministryID;

    }

    public Long getMinistryID() {
        return ministryID;
    }
    // End: categories - ministries


    public void setDisplayButtonDisabled(boolean displayButtonDisabled) {
        this.displayButtonDisabled = displayButtonDisabled;
    }

    public boolean isDisplayButtonDisabled() {
        return displayButtonDisabled;
    }

    public void setperiodTypeID(Long periodTypeID) {
        this.periodTypeID = periodTypeID;
    }

    public Long getperiodTypeID() {
        return periodTypeID;
    }


    public void setperiodTypesList(List periodTypesList) {
        this.periodTypesList = periodTypesList;
    }

    public List<SpecialPeriodTypesDTO> getperiodTypesList() {
        return periodTypesList;
    }


    public void setResetData(boolean resetData) {
        this.resetData = resetData;
    }

    public boolean isResetData() {
        return resetData;
    }

    public void setSelectedYear(Long selectedYear) {
        this.selectedYear = selectedYear;
    }

    public Long getSelectedYear() {
        return selectedYear;
    }

    public void setYearList(List yearList) {
        this.yearList = yearList;
    }

    public List getYearList() {
        return yearList;
    }

    public void setSpecialPeriodsDTO(SpecialPeriodsDTO specialPeriodsDTO) {
        this.specialPeriodsDTO = specialPeriodsDTO;
    }

    public SpecialPeriodsDTO getSpecialPeriodsDTO() {
        return specialPeriodsDTO;
    }

    public void setSelCategoyName(String selCategoyName) {
        this.selCategoyName = selCategoyName;
    }

    public String getSelCategoyName() {
        return selCategoyName;
    }

    public void setSelMinistryName(String selMinistryName) {
        this.selMinistryName = selMinistryName;
    }

    public String getSelMinistryName() {
        return selMinistryName;
    }

    public void setSelperiodName(String selperiodName) {
        this.selperiodName = selperiodName;
    }

    public String getSelperiodName() {
        return selperiodName;
    }

    public void setBgtProgramsDTOList(List bgtProgramsDTOList) {
        this.bgtProgramsDTOList = bgtProgramsDTOList;
    }

    public List<BgtProgramsDTO> getBgtProgramsDTOList() {
        return bgtProgramsDTOList;
    }

    public void setBooleanStatus(boolean booleanStatus) {
        this.booleanStatus = booleanStatus;
    }

    public boolean isBooleanStatus() {
        return booleanStatus;
    }
}
