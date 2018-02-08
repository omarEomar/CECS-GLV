package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialinsuranceperoidcalc;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;

import java.sql.Date;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class JobCategoryBean extends MainDataBean {
    @SuppressWarnings("compatibility:-7514072074191059142")
    private static final long serialVersionUID = 1L;
    private static final String BEAN_NAME = "socialInsurPeroidCalcJobCategoryBean";
    private boolean filterPnlCollapsed = false;
    private int tabIndex = 1;

    //reports
    private String reportUrlLink;
    private String saveCode;
    private static final String REPORT_PARAM_HOLDER = "#p#";
    private static final String REPORT_CODE_1738 = "1738";
    private static final String REPORT_CODE_1739 = "1739";
    private static final String REPORT_CODE_1740 = "1740";
    private static final String REPORT_CODE_1741 = "1741";
    private static final String REPORT_CODE_1742 = "1742";
    private static final String REPORT_CODE_1744 = "1744";
    private static final String REPORT_CODE_PATH_1744 =
        "rep=#p#&_paramsP_MONTHLY_DEDUCT=#p#&_paramsP_PERIOD=#p#&_paramsP_DECISION_NO=#p#&_paramsP_TABLE_NO=#p#&_paramsP_YEAR=#p#&_paramsP_MONTH=#p#&_paramsP_DATE=#p#&_paramsP_SAVE=#p#&_paramsP_MIN=#p#&_paramsP_CIVIL=#p#";
    private static final String REPORT_CODE_1745 = "1745";
    private static final String REPORT_CODE_PATH_1745 =
        "rep=#p#&_paramsP_VAC_TO=#p#&_paramsP_VAC_from=#p#&_paramsP_YEAR=#p#&_paramsP_MONTH=#p#&_paramsP_DATE=#p#&_paramsP_SAVE=#p#&_paramsP_MIN=#p#&_paramsP_CIVIL=#p#";

    private Long totalServiceDays = 0L;
    private Long totalServiceMonths = 0L;
    private Long totalServiceYears = 0L;
    

    public JobCategoryBean() {
        setClient(SalClientFactory.getSalSalarySheetsClient());
        setPageDTO(InfDTOFactory.createKwtWorkDataDTO());
        //       ((IKwtWorkDataDTO) getPageDTO()).get
        setContent1Div("module_tabs_cont1");
    }

    public static JobCategoryBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (JobCategoryBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
    }

    public void showHideFilterPnl() {
        if (filterPnlCollapsed) {
            filterPnlCollapsed = false;
        } else {
            filterPnlCollapsed = true;
        }
    }

    public void getAll() throws DataBaseException {
        SharedUtilBean shared_util = getSharedUtil();
        try {

            if (getRealCivilId() != null) {
                //CSC-20336
                ISalEmpSalaryElementsDTO dto = SalDTOFactory.createSalEmpSalaryElementsDTO();
                dto.setRealCivilId(getRealCivilId());
                dto.setUntilDate(getTodate());
                // setMyTableData(SalClientFactory.getSalEmpSalaryElementsClient().getJobCategoriesForEmp(dto));
                setMyTableData(InfClientFactory.getKwtWorkDataClient().getByCivilIdOrderByDateForSCP(getRealCivilId()));
                calcTotalServicePeriod(getMyTableData());
            } else {
                setMyTableData(new ArrayList());
            }

            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
        } catch (NoResultException e) {
            e.printStackTrace();
        } catch (SharedApplicationException f) {
            f.printStackTrace();
        } catch (DataBaseException db) {
            db.printStackTrace();
            shared_util.setErrMsgValue(db.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcTotalServicePeriod(List<IBasicDTO> basicDTOsList) {
        totalServiceDays = 0L;
        totalServiceMonths = 0L;
        totalServiceYears = 0L;

        for (IBasicDTO iBasicDTO : basicDTOsList) {

            IKwtWorkDataDTO kwtWorkDataDTO = (IKwtWorkDataDTO)iBasicDTO;
            totalServiceYears += kwtWorkDataDTO.getServiceYears();
            totalServiceMonths += kwtWorkDataDTO.getServiceMonths();
            totalServiceDays += kwtWorkDataDTO.getServiceDays();
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
        app.setShowEmpSrchDiv(true);
        return app;
    }


    public void filterByCivilId() {
        super.filterByCivilId();

        //        try {
        //            getAll();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }

    public void setFilterPnlCollapsed(boolean filterPnlCollapsed) {
        this.filterPnlCollapsed = filterPnlCollapsed;
    }

    public boolean isFilterPnlCollapsed() {
        return filterPnlCollapsed;
    }

    public void navigateSteps(WizardInfo wizardInfo) {
        tabIndex = 1;

        try {
            if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step2")) {
                tabIndex = 1;
            } else if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step3")) {
                tabIndex = 2;
            } else if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step4")) {
                tabIndex = 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void reSetData(ActionEvent ae) {
        super.reSetData(ae);

        try {
            getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }


    public void printTemplate() {
        String type = getCalcTemplateType();
        Date pdate = SharedUtils.getCurrentDate();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        reportUrlLink = "";
        String pdateStr = dt1.format(pdate);
        String month = getPeroidPerMonth() != null ? getPeroidPerMonth() + "" : null;
        String year = getYearPerMonth() != null ? getYearPerMonth() + "" : null;
        if (type.equals("1")) {
            //rep=#p#&_paramsP_DATE=#p#&_paramsP_SAVE=#p#&_paramsP_MIN=#p#&_paramsP_CIVIL=#p#
            reportUrlLink = ISALConstants.REPORT_CODE_1738;
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1738);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, pdateStr);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, saveCode);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getMinCode() + "");
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getRealCivilId() + "");

        } else {

            if (type.equals("2")) {
                reportUrlLink = ISALConstants.REPORT_CODE_1739;
                reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1739);
            } else if (type.equals("3")) {
                reportUrlLink = ISALConstants.REPORT_CODE_1740;
                reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1740);
            }
            if (type.equals("4")) {
                reportUrlLink = ISALConstants.REPORT_CODE_1741;
                reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1741);
            }
            if (type.equals("5")) {
                reportUrlLink = ISALConstants.REPORT_CODE_1742;
                reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1742);
            }
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, pdateStr);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, saveCode);
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getMinCode() + "");
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getRealCivilId() + "");

        }
        System.out.println("generated params -----> " + reportUrlLink);

    }

    public void printPaymentMethodTemplate() {
        Date pdate = SharedUtils.getCurrentDate();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        reportUrlLink = "";
        String pdateStr = dt1.format(pdate);
        String month = getPeroidPerMonth() != null ? getPeroidPerMonth() + "" : null;
        String year = getYearPerMonth() != null ? getYearPerMonth() + "" : null;
        String monthlydeductValue = getInstallmentValueDK() != null ? String.valueOf(getInstallmentValueDK()) : "";
        String yearPeroidInstallmentValue =
            getYearPeroidInstallmentValue() != null ? String.valueOf(getYearPeroidInstallmentValue()) : "";
        reportUrlLink = REPORT_CODE_PATH_1744;
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1744);

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, monthlydeductValue);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, yearPeroidInstallmentValue);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getDecNo());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getTableNo());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, pdateStr);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, saveCode);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getMinCode() + "");
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getRealCivilId() + "");
        System.out.println("generated params -----> " + reportUrlLink);
    }


    public void printEmpVacAbsenceDays() {
        Date pdate = SharedUtils.getCurrentDate();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        reportUrlLink = "";
        String pdateStr = dt1.format(pdate);
        String month = getPeroidPerMonth() != null ? getPeroidPerMonth() + "" : null;
        String year = getYearPerMonth() != null ? getYearPerMonth() + "" : null;

        String fParamDate = getFromDate() != null ? dt1.format(getFromDate()) : "";
        String tParamDate = getTodate() != null ? dt1.format(getTodate()) : "";

        reportUrlLink = REPORT_CODE_PATH_1745;
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, REPORT_CODE_1745);

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, tParamDate);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, fParamDate);

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, pdateStr);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, saveCode);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getMinCode() + "");
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, getRealCivilId() + "");
        System.out.println("generated params -----> " + reportUrlLink);
    }


    public void setSaveCode(String saveCode) {
        this.saveCode = saveCode;
    }

    public String getSaveCode() {
        return saveCode;
    }

    public void setReportUrlLink(String reportUrlLink) {
        this.reportUrlLink = reportUrlLink;
    }

    public String getReportUrlLink() {
        return reportUrlLink;
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
