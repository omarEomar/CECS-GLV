package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialinsuranceperoidcalc;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.vac.business.client.IVacVacationTypesClient;
import com.beshara.csc.hr.vac.business.client.VacClientFactory;
import com.beshara.csc.hr.vac.business.dto.IVacEmployeeVacationsDTO;
import com.beshara.csc.hr.vac.business.dto.VacDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class VacDataBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:-8817683199716769069")
    private static final long serialVersionUID = 1L;
    protected static final String RESOURCE_BUNDLE =
        "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String BEAN_NAME = "socialInsurPeroidCalcVacDataBean";
    private Long realCivilId;
    private Date fromDate;
    private Date todate;
    private String vacTypesKeys = null;

    private static final String VACTYPE_LOV_DIV_SEARCH_METHOD =
        "socialInsurPeroidCalcVacDataBean.searchVacTypesLovDiv";
    private static final String VACTYPE_LOV_DIV_CANCEL_SEARCH_METHOD =
        "socialInsurPeroidCalcVacDataBean.cancelSearchVacTypesLovDiv";
    private static final String VACTYPE_LOV_DIV_RETURN_METHOD =
        "socialInsurPeroidCalcVacDataBean.returnVacTypesLovDiv";
    private static final String VACTYPE_LOV_DIV_RERENDER_COMPONENT = "dataT_data_panel,paging_panel";

    private Long totalServiceDays = 0L;
    private Long totalServiceMonths = 0L;
    private Long totalServiceYears = 0L;

    public VacDataBean() {
        setClient(SalClientFactory.getSalSalarySheetsClient());
        setContent1Div("module_tabs_cont1");
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMultiSelect(true);
    }


    public List<IBasicDTO> fillVacTypeList() {
        List<IBasicDTO> vacTypeList = new ArrayList<IBasicDTO>();
        try {
            IVacVacationTypesClient client = VacClientFactory.getVacVacationTypesClient();
            vacTypeList = client.getBySalRType(4L);
        } catch (Exception dbe) {
            dbe.printStackTrace();
        }
        return vacTypeList;
    }

    public void openVacTypesLovDiv() {
        List data = fillVacTypeList();

        getLovBaseBean().setKeyIndex(null);
        getLovBaseBean().repositionPage(0);
        getLovBaseBean().setMyTableData(data);
        getLovBaseBean().setReturnMethodName(VACTYPE_LOV_DIV_RETURN_METHOD);
        getLovBaseBean().setSearchMethod(VACTYPE_LOV_DIV_SEARCH_METHOD);
        getLovBaseBean().setCancelSearchMethod(VACTYPE_LOV_DIV_CANCEL_SEARCH_METHOD);
        getLovBaseBean().setRenderedDropDown(VACTYPE_LOV_DIV_RERENDER_COMPONENT);
        getLovBaseBean().setHighlightedDTOS(null);
        // Start----CSC-14372-------Sub_Task-------CSC-14386----
        getLovBaseBean().cancelSearchLovDiv();

    }

    public String searchVacTypesLovDiv(Object searchType, Object searchQuery) {
        try {
            IVacVacationTypesClient client = VacClientFactory.getVacVacationTypesClient();
            List<IBasicDTO> result = new ArrayList<IBasicDTO>();
            if (searchQuery != null && !searchQuery.equals("")) {

                if (searchType.toString().equals("0")) {
                    result = client.searchByCodeorNameAndSalRCodeForShow(null ,searchQuery,4L);
                }

                else if (searchType.toString().equals("1")) {
                    result = client.searchByCodeorNameAndSalRCodeForShow(searchQuery.toString() , null, (Object)4L);

                }
                getLovBaseBean().setMyTableData(result);
            }


        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }
        return "";

    }

    public String cancelSearchVacTypesLovDiv() {
        getLovBaseBean().setMyTableData(fillVacTypeList());
        setSelectedRadio("");
        return "";
    }

    public String returnVacTypesLovDiv() {
        try {
            vacTypesKeys = "";
            List<IBasicDTO> selectedVacTypes = getLovBaseBean().getSelectedDTOS();
            for (IBasicDTO dto : selectedVacTypes) {
                vacTypesKeys = vacTypesKeys.concat(dto.getCode().getKey() + ",");
            }
            if (vacTypesKeys != null && !vacTypesKeys.isEmpty()) {
                vacTypesKeys = vacTypesKeys.substring(0, (vacTypesKeys.length() - 1));
                System.out.println(vacTypesKeys);
                getAll();
                repositionPage(0);
                setCheckAllFlag(false);
            }
        } catch (Exception dbe) {
            dbe.printStackTrace();
        }
        return "";
    }

    public static VacDataBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (VacDataBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();

        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowWizardBar(true);
        app.setShowLovDiv(true);
        return app;
    }

    private void initVacData() {
        JobCategoryBean bean = JobCategoryBean.getInstance();
        realCivilId = bean.getRealCivilId();
        fromDate = bean.getFromDate();
        todate = bean.getTodate();
    }

    public List getTotalList() {

        //  MaintainBean maintainBean=MaintainBean.getInstance();
        //  IPeroidCalcInsurDTO dto= maintainBean.getPeroidCalcInsurDTO();
        //  Long realcivil=dto.getRealCivilId();
        List<IBasicDTO> empvacList = new ArrayList<IBasicDTO>();
        initVacData();
        if (realCivilId != null) {
            IVacEmployeeVacationsDTO empVacDTO = VacDTOFactory.createVacEmployeeVacationsDTO();
            try {
                empVacDTO.setRcivilId(realCivilId);
                empVacDTO.setFromDate(fromDate);
                empVacDTO.setUntilDate(todate);
                empVacDTO.setName(vacTypesKeys);
                empvacList = SalClientFactory.getSalEmpSalaryElementsClient().getEmpVacForSocialInsurrance(empVacDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return empvacList;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }


    public void restoreSaveStateParams(Object obj) {
        //        try {
        //           // System.out.println("restoreSaveStateParams.init");
        //            IPeroidCalcInsurDTO peroidCalcInsurDTO = (IPeroidCalcInsurDTO)obj;
        //            BeanUtils.copyProperties(this, peroidCalcInsurDTO);
        //            getAll();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setVacTypesKeys(String vacTypesKeys) {
        this.vacTypesKeys = vacTypesKeys;
    }

    public String getVacTypesKeys() {
        return vacTypesKeys;
    }

    public void getAllVactTypes() throws DataBaseException {
        vacTypesKeys = null;
        getAll();

    }


    public void getAll() throws DataBaseException {
        super.getAll();
        calcTotalServicePeriod(getMyTableData());
        repositionPage(0);
    }


    public void calcTotalServicePeriod(List<IBasicDTO> basicDTOsList) {
        totalServiceDays = 0L;
        totalServiceMonths = 0L;
        totalServiceYears = 0L;

        for (IBasicDTO iBasicDTO : basicDTOsList) {

            IVacEmployeeVacationsDTO vacDTO = (IVacEmployeeVacationsDTO)iBasicDTO;
            totalServiceYears += vacDTO.getHistoryFlag();
            totalServiceMonths += vacDTO.getVacationPeriod();
            totalServiceDays += vacDTO.getChannelId();
            if (totalServiceDays >= 30) {
                totalServiceDays -= 30;
                totalServiceMonths += 1;
            }

            if (totalServiceMonths >= 12) {
                totalServiceMonths -= 12;
                totalServiceYears += 1;
            }

        }
    }

    public void setTotalServiceDays(Long totalServiceDays) {
        this.totalServiceDays = totalServiceDays;
    }

    public Long getTotalServiceDays() {
        return totalServiceDays;
    }

    public void setTotalServiceMonths(Long totalServiceMonths) {
        this.totalServiceMonths = totalServiceMonths;
    }

    public Long getTotalServiceMonths() {
        return totalServiceMonths;
    }

    public void setTotalServiceYears(Long totalServiceYears) {
        this.totalServiceYears = totalServiceYears;
    }

    public Long getTotalServiceYears() {
        return totalServiceYears;
    }
}
