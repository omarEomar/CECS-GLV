package com.beshara.csc.hr.ded.integration.presentation.backingbean.deductionenquiry;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.ded.integration.business.client.DedClientFactory;
import com.beshara.csc.hr.ded.integration.business.dto.CSCCodeNameDTO;
import com.beshara.csc.hr.ded.integration.business.dto.CSCDeductionEnquiryDTO;
import com.beshara.csc.hr.ded.integration.business.dto.CSCDeductionRemainingDataDto;
import com.beshara.csc.hr.ded.integration.business.dto.CSCEmpInstallmentsDTO;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;


public class DeductionEnquiryBean extends LookUpBaseBean {

    private final static String BEAN_NAME = "deductionEnquiryBean";
    private static final String EXCEPTIONS_RESOURCE_PATH =
        "com.beshara.csc.hr.ded.integration.presentation.backingbean.resources.exceptions";

    public final static String PAGE_NAV_CASE = "goToDeductEnquiryPage";
    private static final Long ABSENCE_ELEMENT_CODE = 14803L;
    private static final Long LATENCY_ELEMENT_CODE = 14805L;
    private static final Long AMOUNTS_RECEIVALBE_ELEMENT = 14849L;

    //private List<CSCDeductionEnquiryDTO> enquiryList;
    private List<CSCCodeNameDTO> elementGuideComboBoxList;
    private List<CSCCodeNameDTO> secandaryGuideComboBoxList;
    private List<CSCCodeNameDTO> categoryComboBoxList;
    private List<CSCCodeNameDTO> ministryComboBoxList;
    private List<CSCCodeNameDTO> dedForCategories;
    private List dedForMinistries;
    private CSCDeductionEnquiryDTO searchCriteria;
    private CSCDeductionEnquiryDTO extraDatadto;
    private CSCDeductionRemainingDataDto remainingDataDto;

    private List<CSCEmpInstallmentsDTO> installmentsList;

    private boolean resultMode = false;


    private Object[] savedDataObjects = new Object[1];
    private String backNavCase = null;
    private String filterMethodName = null;

    private List<Date> absDays = new ArrayList<Date>();
    private int NoOfDays;

    private Long monthly = 0L;
    private Long twoMonths = 1L;
    private Long threeMonths = 2L;
    private Long fourMonths = 3L;
    private Long halfYear = 4L;
    private Long yearly = 5L;
    private Long general = 0L;
    private Long privatee = 1L;
    private String expense = "14879";
    private String custoday = "14662";
    private String alimony_1 = "14880";
    private String alimony_2 = "14881";

    public DeductionEnquiryBean() {
        setPageDTO(new CSCDeductionEnquiryDTO());
    }

    public static DeductionEnquiryBean getInstance() {
        return (DeductionEnquiryBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(false);
        app.setShowCommonData(false);
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(false);
        app.setShowScirptGenerator(true);
        app.setShowshortCut(false);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        //app.setShowCustomDiv3(true);
        return app;
    }

    //    public List getMyTableData() throws DataBaseException {
    //        return enquiryList;
    //    }

    //    public Integer getListSize() throws DataBaseException {
    //
    //        if (enquiryList != null)
    //            return enquiryList.size();
    //
    //        return 0;
    //    }

    public void init(Object param, String navCase, String filterMethodName) {
        savedDataObjects = new Object[1];
        savedDataObjects[0] = param;
        this.backNavCase = navCase;
        this.filterMethodName = filterMethodName;
    }

    public String back() {
        try {
            executeMethodBindingWithParams(filterMethodName, savedDataObjects, new Class[] { Object.class });
            return backNavCase;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    public void searchRequests() {
    //        searchCriteria = ((CSCDeductionEnquiryDTO)getPageDTO());
    //        enquiryList.clear();
    //        searchCriteria.setLoginMinistry(CSCSecurityInfoHelper.getLoggedInMinistryCode());
    //        try {
    //            enquiryList = DedClientFactory.getDeductionsClient().searchDeductions(searchCriteria);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        resultMode = true;
    //
    //        if (enquiryList.size() == 0) {
    //            enquiryList.add(new CSCDeductionEnquiryDTO());
    //            clearTempLists();
    //            updateCategories(null);
    //            //TO-DO
    //            //confirm.showWarrningMsg("noresultfound", "warnningDivAction");
    //            return;
    //        }
    //    }

    public void searchDeductions(Object searchCriteria) {
        CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)searchCriteria;
        this.searchCriteria = new CSCDeductionEnquiryDTO();
        try {
            BeanUtils.copyProperties(this.searchCriteria, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.searchCriteria.setLoginMinistry(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        try {
            List list = DedClientFactory.getDeductionsClient().searchDeductions(this.searchCriteria);
            if (list != null && !list.isEmpty()) {
                setPageDTO((IBasicDTO)list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMode = true;

        if (getPageDTO() == null) {
            setPageDTO(new CSCDeductionEnquiryDTO());
            updateCategories(null);
            //TO-DO
            //confirm.showWarrningMsg("noresultfound", "warnningDivAction");
            return;
        }

    }

    //    public void showRemainingData() {
    //
    //        CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
    //        try {
    //            remainingDataDto =
    //                    DedClientFactory.getDeductionsClient().getRemaininigData(dto.getEmpCivilId(), dto.getId(),
    //                                                                             dto.getSecandaryGuideId(),null);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        setJavaScriptCaller("changeVisibilityDiv('blocker' ,window.remainingDataDiv)");
    //    }

    public void warnningDivAction() {
        resultMode = false;
        //confirm.setJavaScriptCaller(null);
    }

    //    public void cancelSearch() {
    //        getMyDataTable().setFirst(0);
    //        enquiryList.clear();
    //        enquiryList.add(new CSCDeductionEnquiryDTO());
    //        clearTempLists();
    //        updateCategories(null);
    //        resultMode = false;
    //
    //    }

    private void clearTempLists() {
        if (secandaryGuideComboBoxList != null) {
            secandaryGuideComboBoxList.clear();
        }
        if (ministryComboBoxList != null) {
            ministryComboBoxList.clear();
        }
    }

    public void changeElementGuide(ActionEvent ae) {
        if (secandaryGuideComboBoxList != null) {
            secandaryGuideComboBoxList.clear();
        }
        if (((CSCDeductionEnquiryDTO)getPageDTO()).getElementGuideId() != null &&
            !((CSCDeductionEnquiryDTO)getPageDTO()).getElementGuideId().equals("")) {
            try {
                secandaryGuideComboBoxList =
                        DedClientFactory.getDeductionsClient().getSecGuidesByElmentGuideAndMiniIdAndSysDate(((CSCDeductionEnquiryDTO)getPageDTO()).getElementGuideId(),
                                                                                                            CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void changeEmpCategory(ActionEvent ae) {
        ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpRCivilId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpCivilId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpName(null);
        if (ministryComboBoxList != null) {
            ministryComboBoxList.clear();
        }
        if (((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId() != null) {
            // ministryComboBoxList = DedClientFactory.getDeductionsClient().getAllGovMinistries(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId().toString());
            try {
                ministryComboBoxList =
                        DedClientFactory.getDeductionsClient().getEmpMinistriesByCat(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId(),
                                                                                     CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void changeEmpMinistry(ActionEvent ae) {
        ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpRCivilId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpCivilId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpName(null);
    }

    public void updateCategories(ActionEvent ae) {

        if (dedForCategories != null) {
            dedForCategories.clear();
        }
        ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForCatId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForMinId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForMinName(null);
        if (((CSCDeductionEnquiryDTO)getPageDTO()).getDedForGovFlag() != null) {
            try {
                dedForCategories =
                        DedClientFactory.getSharedClient().getCategoriesByGovFlag(Long.parseLong(((CSCDeductionEnquiryDTO)getPageDTO()).getDedForGovFlag()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMinistries(ActionEvent ae) {

        ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForMinId(null);
        ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForMinName(null);
    }

    //    public void openLovDivMinis() {
    //        initLovDivForSearchMinis();
    //        getLovBaseBean().cancelSearchLovDiv();
    //
    //
    //    }


    //    public void initLovDivForSearchMinis() {
    //        getLovBaseBean().setReturnMethodName("pageBeanName" + ".saveLovSelectionMinis");
    //        getLovBaseBean().setSearchMethod("pageBeanName" + ".searchLovDivMinis");
    //        getLovBaseBean().setCancelSearchMethod("pageBeanName" + ".cancelSearchLovDiv");
    //        getLovBaseBean().setFillDataModelMethod("pageBeanName" + ".fillLovDataModelMethodMinis");
    //        getLovBaseBean().setShowMode(IADSConstants.MINISTRIES_MODE);
    //        getLovBaseBean().setReRenderComponentIds("my_dataTable");
    //        getLovBaseBean().initLabels("chooseMinistry", "lovCode", "lovName");
    //        getLovBaseBean().updateDataModel();
    //
    //    }
    //
    //    public String saveLovSelectionMinis() {
    //
    //        try {
    //            ListOfValuesDTO dto = (ListOfValuesDTO)getLovBaseBean().getSelectedRow();
    //            ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForMinId(dto.getId());
    //            ((CSCDeductionEnquiryDTO)getPageDTO()).setDeductForMinName(new String(dto.getName()));
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //
    //        }
    //        return "";
    //    }
    //
    //    public String cancelSearchLovDiv() {
    //        lovBaseBean.reposition();
    //        lovBaseBean.setUpdateModel(true);
    //        lovBaseBean.setShowLovDiv(true);
    //        setJavaScriptCaller("showLovDiv();");
    //        return "";
    //    }

    //
    //    public String searchLovDivMinis(Object searchType, Object searchQuery) {
    //
    //
    //        String type = String.valueOf(searchType);
    //        List<ListOfValuesDTO> ministrylist = new ArrayList<ListOfValuesDTO>();
    //        boolean searchByCode = (type.equals(getLovBaseBean().getSearchByTypeCode())) ? true : false;
    //
    //        if (searchQuery != null && !searchQuery.equals("")) {
    //            getLovBaseBean().setUpdateModel(true);
    //            String searchQueryStr = String.valueOf(searchQuery);
    //            Long totalListSize = 0L;
    //            if (this.((CSCDeductionEnquiryDTO)getPageDTO()).getDedForGovFlag().equals(IADSConstants.GOVERNENTAL)) {
    //                if (searchByCode) {
    //
    //                    ministrylist =
    //                            DedClientFactory.getDeductionsClient().getGovMinistriesByCatIdAndMinId(((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(), Long.parseLong(searchQueryStr));
    //                    totalListSize = Long.valueOf(ministrylist.size());
    //                } else {
    //                    ministrylist =
    //                            DedClientFactory.getDeductionsClient().getGovMinistriesByCatIdAndMinNameWithPaging(((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(),
    //                                                                                     searchQueryStr,
    //                                                                                     getLovBaseBean().getPageStart(),
    //                                                                                     lovBaseBean.getMyDataTable().getRows());
    //                    totalListSize =
    //                            DedClientFactory.getDeductionsClient().getGovMinCountByCatAndName(((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(), searchQueryStr);
    //
    //                }
    //
    //            } else if (this.((CSCDeductionEnquiryDTO)getPageDTO()).getDedForGovFlag().equals(IADSConstants.NON_GOVERNENTAL)) {
    //                if (searchByCode) {
    //                    //                    ministrylist =
    //                    //                            DedClientFactory.getDeductionsClient().getNonGovMinistriesByCatIdAndMinId(((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(),
    //                    //                                                                            searchQueryStr);
    //                    totalListSize = Long.valueOf(ministrylist.size());
    //                } else {
    //                    //                    ministrylist =
    //                    //                            DedClientFactory.getDeductionsClient().getNonGovMinistriesByCatAndMinNameWithPaging(((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(),
    //                    //                                                                                      searchQueryStr,
    //                    //                                                                                      getLovBaseBean().getPageStart(),
    //                    //                                                                                      lovBaseBean.getMyDataTable().getRows());
    //                    //                    totalListSize =
    //                    //                            DedClientFactory.getDeductionsClient().getNonGovMinCountByCatAndName(((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(),
    //                    //                                                                       searchQueryStr);
    //                }
    //
    //
    //            }
    //
    //            getLovBaseBean().setLovDataModel(new PagedListDataModel(ministrylist, totalListSize.intValue(),
    //                                                                    lovBaseBean.getMyDataTable().getRows()));
    //
    //
    //        } else {
    //            getLovBaseBean().setMyTableData(new ArrayList());
    //        }
    //        setJavaScriptCaller("showLovDiv();");
    //
    //        return "";
    //    }
    //
    //
    //    public void fillLovDataModelMethodMinis() {
    //
    //        Long totalListSize = 0L;
    //        List pagedList = null;
    //        //        if (this.((CSCDeductionEnquiryDTO)getPageDTO()).getMakerMiniType() != null &&
    //        //            this.((CSCDeductionEnquiryDTO)getPageDTO()).getMakerMiniType().equals(IADSConstants.GOVERNENTAL.toString())) {
    //        totalListSize = DedClientFactory.getDeductionsClient().getGovMinsCountByCat(Long.valueOf(this.((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId()));
    //        pagedList =
    //                DedClientFactory.getDeductionsClient().getGovMinistriesByCatIdWithPaging(this.((CSCDeductionEnquiryDTO)getPageDTO()).getDeductForCatId(), getLovBaseBean().getPageStart(),
    //                                                               getLovBaseBean().getRowsNumber());
    //        getLovBaseBean().setLovDataModel(new PagedListDataModel(pagedList, totalListSize.intValue(),
    //                                                                getLovBaseBean().getRowsNumber()));
    //        //        } else if (this.((CSCDeductionEnquiryDTO)getPageDTO()).getMakerMiniType() != null &&
    //        //                   this.((CSCDeductionEnquiryDTO)getPageDTO()).getMakerMiniType().equals(IADSConstants.NON_GOVERNENTAL.toString())) {
    //        //            totalListSize =
    //        //                    DedClientFactory.getDeductionsClient().getNonGovMinsCountByCat(this.((CSCDeductionEnquiryDTO)getPageDTO()).getMakerCategoryId());
    //        //            pagedList =
    //        //                    DedClientFactory.getDeductionsClient().getNonGovMinistriesByCatWithPaging(this.((CSCDeductionEnquiryDTO)getPageDTO()).getMakerCategoryId(),
    //        //                                                                    getLovBaseBean().getPageStart(),
    //        //                                                                    getLovBaseBean().getRowsNumber());
    //        //            getLovBaseBean().setLovDataModel(new PagedListDataModel(pagedList, totalListSize.intValue(),
    //        //                                                                    getLovBaseBean().getRowsNumber()));
    //        //        }
    //
    //    }
    //
    //
    //    public void openLovDiv() {
    //        initLovDivForEmps();
    //        lovBaseBean.cancelSearchLovDiv();
    //
    //    }
    //
    //    public void initLovDivForEmps() {
    //
    //        lovBaseBean.setReturnMethodName("pageBeanName." + "saveLovSelection");
    //        lovBaseBean.setSearchMethod("pageBeanName." + "searchLovDiv");
    //        lovBaseBean.setCancelSearchMethod("pageBeanName." + "cancelSearchLovDiv");
    //        lovBaseBean.setFillDataModelMethod("pageBeanName." + "fillLovDiv");
    //        lovBaseBean.setReRenderComponentIds("my_dataTable");
    //        lovBaseBean.setShowMode(IADSConstants.EMPLOYEES_MODE);
    //        lovBaseBean.initLabels("employeeDivTitle", "empCivilId", "employeeName");
    //        lovBaseBean.updateDataModel();
    //
    //    }
    //
    //    public String saveLovSelection() {
    //        try {
    //            ListOfValuesDTO dto = (ListOfValuesDTO)getLovBaseBean().getSelectedRow();
    //            ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpCivilId(dto.getId());
    //            ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpRCivilId(Long.valueOf(dto.getRealCivilId()));
    //            ((CSCDeductionEnquiryDTO)getPageDTO()).setEmpName(dto.getName());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //
    //        }
    //        return "";
    //    }
    //
    //    public String searchLovDiv(Object searchType, Object searchQuery) {
    //        if (!((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId().equals("") && ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId() != null &&
    //            !((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId().equals("") && ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId() != null) {
    //            String type = String.valueOf(searchType);
    //            List<ListOfValuesDTO> emplist = new ArrayList<ListOfValuesDTO>();
    //            boolean searchByCode = (type.equals(lovBaseBean.getSearchByTypeCode())) ? true : false;
    //            if (searchQuery != null && !searchQuery.equals("")) {
    //                String searchQueryStr = String.valueOf(searchQuery);
    //                Long totalListSize = 0L;
    //                if (searchByCode) {
    //                    emplist =
    //                            DedClientFactory.getDeductionsClient().getEmpListWithPaging(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId(), ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId(),
    //                                                              null, new Long(searchQueryStr),
    //                                                              lovBaseBean.getPageStart(),
    //                                                              lovBaseBean.getMyDataTable().getRows());
    //                    totalListSize = Long.valueOf(emplist.size());
    //
    //                } else {
    //                    totalListSize =
    //                            DedClientFactory.getDeductionsClient().getCountSearchEmpsByName(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId(), ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId(),
    //                                                                  searchQueryStr);
    //                    emplist =
    //                            DedClientFactory.getDeductionsClient().getEmpListWithPaging(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId(), ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId(),
    //                                                              searchQueryStr, null, lovBaseBean.getPageStart(),
    //                                                              lovBaseBean.getMyDataTable().getRows());
    //                }
    //
    //                lovBaseBean.setLovDataModel(new PagedListDataModel(emplist, totalListSize.intValue(),
    //                                                                   lovBaseBean.getMyDataTable().getRows()));
    //                //  lovBaseBean.reposition();
    //
    //            } else {
    //                lovBaseBean.setMyTableData(new ArrayList());
    //            }
    //            setJavaScriptCaller("showLovDiv();");
    //        }
    //
    //        return "";
    //    }
    //
    //    public void fillLovDiv() {
    //
    //
    //        Long totalListSize = 0L;
    //        List pagedList = null;
    //        try {
    //            if (((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId() != null && !((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId().equals("") &&
    //                ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId() != null && !((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId().equals("")) {
    //
    //                totalListSize =
    //                        DedClientFactory.getDeductionsClient().getCountEmpsByCatAndMini(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId(), ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId());
    //                System.out.println("Before Date" + new Date());
    //                pagedList =
    //                        DedClientFactory.getDeductionsClient().getEmpListWithPaging(((CSCDeductionEnquiryDTO)getPageDTO()).getEmpCategoryId(), ((CSCDeductionEnquiryDTO)getPageDTO()).getEmpMinistryId(),
    //                                                          null, null, lovBaseBean.getPageStart(),
    //                                                          lovBaseBean.getMyDataTable().getRows());
    //                System.out.println("After Date" + new Date());
    //                lovBaseBean.setLovDataModel(new PagedListDataModel(pagedList, totalListSize.intValue(),
    //                                                                   lovBaseBean.getMyDataTable().getRows()));
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

    //    public void setEnquiryList(List<CSCDeductionEnquiryDTO> enquiryList) {
    //        this.enquiryList = enquiryList;
    //    }
    //
    //    public List<CSCDeductionEnquiryDTO> getEnquiryList() {
    //        if (enquiryList == null) {
    //            enquiryList = new ArrayList<CSCDeductionEnquiryDTO>();
    //            enquiryList.add(new CSCDeductionEnquiryDTO());
    //        }
    //        return enquiryList;
    //    }

    public void setSearchCriteria(CSCDeductionEnquiryDTO searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public CSCDeductionEnquiryDTO getSearchCriteria() {
        return searchCriteria;
    }

    public void setElementGuideComboBoxList(List<CSCCodeNameDTO> elementGuideComboBoxList) {
        this.elementGuideComboBoxList = elementGuideComboBoxList;
    }

    public List<CSCCodeNameDTO> getElementGuideComboBoxList() {
        if (resultMode) {
            try {
                elementGuideComboBoxList = DedClientFactory.getDeductionsClient().getAllElmGuides();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!resultMode) {
            try {
                elementGuideComboBoxList =
                        DedClientFactory.getDeductionsClient().getElmGuidesMiniIdAndSysDate(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return elementGuideComboBoxList;
    }

    public void setSecandaryGuideComboBoxList(List<CSCCodeNameDTO> secandaryGuideComboBoxList) {
        this.secandaryGuideComboBoxList = secandaryGuideComboBoxList;
    }

    public List<CSCCodeNameDTO> getSecandaryGuideComboBoxList() {
        return secandaryGuideComboBoxList;
    }

    //    public void setCategoryComboBoxList(List<CSCCodeNameDTO> categoryComboBoxList) {
    //        this.categoryComboBoxList = categoryComboBoxList;
    //    }
    //
    //    public List<CSCCodeNameDTO> getCategoryComboBoxList() {
    //        if (categoryComboBoxList == null || categoryComboBoxList.isEmpty()) {
    //            categoryComboBoxList = DedClientFactory.getDeductionsClient().getEmpCategories(getUserInfo().getMinistryId());
    //        }
    //        return categoryComboBoxList;
    //    }


    public void setDedForCategories(List<CSCCodeNameDTO> dedForCategories) {
        this.dedForCategories = dedForCategories;
    }

    public List<CSCCodeNameDTO> getDedForCategories() {
        if (dedForCategories == null) {
            try {
                dedForCategories =
                        DedClientFactory.getSharedClient().getCategoriesByGovFlag(Long.parseLong(((CSCDeductionEnquiryDTO)getPageDTO()).getDedForGovFlag()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dedForCategories;
    }

    public void setDedForMinistries(List dedForMinistries) {
        this.dedForMinistries = dedForMinistries;
    }

    public List getDedForMinistries() {
        return dedForMinistries;
    }

    public void afterPropertiesSet() {
    }

    public void setResultMode(boolean resultMode) {
        this.resultMode = resultMode;
    }

    public boolean isResultMode() {
        return resultMode;
    }

    public void setRemainingDataDto(CSCDeductionRemainingDataDto remainingDataDto) {
        this.remainingDataDto = remainingDataDto;
    }

    public CSCDeductionRemainingDataDto getRemainingDataDto() {
        return remainingDataDto;
    }

    //    public void fillReqDedResult() {
    //        CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
    //        //        DedClientFactory.getDeductionsClient().checkEnqueryRequestPermission(dto.getSecandaryGuideId(),
    //        //                                                                      getUserInfo().getMinistryId());
    //        DedEnquiryRequestBean bean = DedEnquiryRequestBean.getInstance();
    //        bean.setSecandaryGuideId(dto.getSecandaryGuideId());
    //        bean.showResultRequest(dto.getId().toString());
    //        if (bean.getDto().getId() == null) {
    //            getSharedUtil().handleException(null, EXCEPTIONS_RESOURCE_PATH, "ADS-0031");
    //            return;
    //        }
    //
    //        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.customDiv1);");
    //    }
    //
    //    public void fillReBasedOnReq() {
    //        CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
    //        //        DedClientFactory.getDeductionsClient().checkEnqueryRequestPermission(dto.getSecandaryGuideId(),
    //        //                                                                      getUserInfo().getMinistryId());
    //        DedEnquiryRequestBean bean = DedEnquiryRequestBean.getInstance();
    //        bean.setSecandaryGuideId(dto.getSecandaryGuideId());
    //        bean.showRequestsBasedOn(dto.getId().toString());
    //        if (bean.getRequestList() == null || bean.getRequestList().isEmpty()) {
    //            getSharedUtil().handleException(null, EXCEPTIONS_RESOURCE_PATH, "ADS-0032");
    //            return;
    //        }
    //
    //        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.customDiv1);");
    //    }

    public void showEmpInstallments() {
        CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO(); //(CSCDeductionEnquiryDTO)getPageDTO();

        if (dto.getId() == null) {
            getSharedUtil().handleException(null, EXCEPTIONS_RESOURCE_PATH, "ADS-0031");
            return;
        }

        try {
            installmentsList = DedClientFactory.getDeductionsClient().getEmpInstallmentsByTransactionNo(dto.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setJavaScriptCaller("showCustomDiv2WithDiffStyle();");
    }

    public void setSavedDataObjects(Object[] savedDataObjects) {
        this.savedDataObjects = savedDataObjects;
    }

    public Object[] getSavedDataObjects() {
        return savedDataObjects;
    }

    public void setBackNavCase(String backNavCase) {
        this.backNavCase = backNavCase;
    }

    public String getBackNavCase() {
        return backNavCase;
    }

    public void setFilterMethodName(String filterMethodName) {
        this.filterMethodName = filterMethodName;
    }

    public String getFilterMethodName() {
        return filterMethodName;
    }

    public void setInstallmentsList(List<CSCEmpInstallmentsDTO> installmentsList) {
        this.installmentsList = installmentsList;
    }

    public List<CSCEmpInstallmentsDTO> getInstallmentsList() {
        return installmentsList;
    }


    public void setMinistryComboBoxList(List<CSCCodeNameDTO> ministryComboBoxList) {
        this.ministryComboBoxList = ministryComboBoxList;
    }

    public List<CSCCodeNameDTO> getMinistryComboBoxList() {
        return ministryComboBoxList;
    }

    public void setCategoryComboBoxList(List<CSCCodeNameDTO> categoryComboBoxList) {
        this.categoryComboBoxList = categoryComboBoxList;
    }

    public List<CSCCodeNameDTO> getCategoryComboBoxList() {
        if (categoryComboBoxList == null || categoryComboBoxList.isEmpty()) {
            try {
                categoryComboBoxList =
                        DedClientFactory.getSharedClient().getEmpCategories(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categoryComboBoxList;
    }

    //    public void showDiscountDays() {
    //        CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
    //        try {
    //            absDays = DedClientFactory.getDeductionsClient().getDiscountDays(dto.getId());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //        setJavaScriptCaller("showCustomDiv3WithDiffStyle();");
    //    }
    //
        public void showExtraData() {
    
            extraDatadto = (CSCDeductionEnquiryDTO)getPageDTO();
            if (isDed11368()) {
                try {
                    absDays = DedClientFactory.getDeductionsClient().getDiscountDays(extraDatadto.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (absDays.size() > 0)
                    NoOfDays = absDays.size();
            }
            //        enquiryList.clear();
            //        searchCriteria.setLoginMinistry(getUserInfo().getMinistryId());
            //        enquiryList = DedClientFactory.getDeductionsClient().searchDeductions(searchCriteria);
           // setJavaScriptCaller("changeVisibilityDiv(null, window.customDiv4);");
        }

    public void setAbsDays(List<Date> absDays) {
        this.absDays = absDays;
    }

    public List<Date> getAbsDays() {
        return absDays;
    }

    public boolean isDed11368() {
        if (getPageDTO() != null && resultMode) {
            CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
            if (dto.getSecandaryGuideId() != null && dto.getSecandaryGuideId().equals(ABSENCE_ELEMENT_CODE)) {
                return true;
            }
        }
        return false;

    }

    public boolean isDed11369() {
        if (getPageDTO() != null && resultMode) {
            CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
            if (dto.getSecandaryGuideId() != null && dto.getSecandaryGuideId().equals(LATENCY_ELEMENT_CODE)) {
                return true;
            }
        }
        return false;

    }

    public boolean isDed11407() {
        if (getPageDTO() != null && resultMode) {
            CSCDeductionEnquiryDTO dto = (CSCDeductionEnquiryDTO)getPageDTO();
            if (dto.getSecandaryGuideId() != null && dto.getSecandaryGuideId().equals(AMOUNTS_RECEIVALBE_ELEMENT)) {
                return true;
            }
        }
        return false;

    }

    public void setExtraDatadto(CSCDeductionEnquiryDTO extraDatadto) {
        this.extraDatadto = extraDatadto;
    }

    public CSCDeductionEnquiryDTO getExtraDatadto() {
        if (getPageDTO() != null && resultMode) {
            extraDatadto = (CSCDeductionEnquiryDTO)getPageDTO();
        } else
            extraDatadto = new CSCDeductionEnquiryDTO();

        return extraDatadto;
    }

    public void setNoOfDays(int NoOfDays) {
        this.NoOfDays = NoOfDays;
    }

    public int getNoOfDays() {
        return NoOfDays;
    }

    public void setMonthly(Long monthly) {
        this.monthly = monthly;
    }

    public Long getMonthly() {
        return monthly;
    }

    public void setTwoMonths(Long twoMonths) {
        this.twoMonths = twoMonths;
    }

    public Long getTwoMonths() {
        return twoMonths;
    }

    public void setThreeMonths(Long threeMonths) {
        this.threeMonths = threeMonths;
    }

    public Long getThreeMonths() {
        return threeMonths;
    }

    public void setFourMonths(Long fourMonths) {
        this.fourMonths = fourMonths;
    }

    public Long getFourMonths() {
        return fourMonths;
    }

    public void setHalfYear(Long halfYear) {
        this.halfYear = halfYear;
    }

    public Long getHalfYear() {
        return halfYear;
    }

    public void setYearly(Long yearly) {
        this.yearly = yearly;
    }

    public Long getYearly() {
        return yearly;
    }

    public void setGeneral(Long general) {
        this.general = general;
    }

    public Long getGeneral() {
        return general;
    }

    public void setPrivatee(Long privatee) {
        this.privatee = privatee;
    }

    public Long getPrivatee() {
        return privatee;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getExpense() {
        return expense;
    }

    public void setCustoday(String custoday) {
        this.custoday = custoday;
    }

    public String getCustoday() {
        return custoday;
    }

    public void setAlimony_1(String alimony_1) {
        this.alimony_1 = alimony_1;
    }

    public String getAlimony_1() {
        return alimony_1;
    }

    public void setAlimony_2(String alimony_2) {
        this.alimony_2 = alimony_2;
    }

    public String getAlimony_2() {
        return alimony_2;
    }
}
