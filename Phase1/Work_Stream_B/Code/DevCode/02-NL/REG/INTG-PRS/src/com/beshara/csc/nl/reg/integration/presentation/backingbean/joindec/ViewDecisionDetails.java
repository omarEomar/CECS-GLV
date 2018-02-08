package com.beshara.csc.nl.reg.integration.presentation.backingbean.joindec;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


public class ViewDecisionDetails extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "decisionMaintainBean";
    private static final String INTEGRATION_PAGE_SUFFIX = "-integration";
    private Map<String, Object> detailsSavedStates;
    String finishNavigationCase = null;
    private boolean cancelDecisionMode;
    private boolean copyDecisionWithEmployeesMode = false;

    private String decisionKey;
    private boolean viewInNewWindow;
    private static final String BUNDULE_NAME = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
    private String year;
    private String month;
    private String day;

    public ViewDecisionDetails() {
        super.setClient(RegClientFactory.getDecisionsClient());
        currentDate();
        decisionKey =
                ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("decisionKey");
        if (decisionKey != null && !decisionKey.equals("")) {
            setViewInNewWindow(true);
            initPageDTOFromKey(decisionKey);
        }

    }

    private void initPageDTOFromKey(String decisionKey) {
        if (getPageDTO() == null && decisionKey != null && !decisionKey.equals("")) {
            IDecisionsEntityKey condCode = RegEntityKeyFactory.createDecisionsEntityKey(decisionKey);
            IBasicDTO decisionsDTO = null;
            try {
                decisionsDTO = RegClientFactory.getDecisionsClient().getById(condCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (decisionsDTO != null) {
                setPageDTO(decisionsDTO);
            }
        }
        currentDate();

    }

    public void currentDate() {

        Date date = new Date(System.currentTimeMillis());
        System.out.println(date.toString());
        String dates[] = date.toString().split(" ");
        setYear(dates[5]);
        Calendar dateCal = Calendar.getInstance();
        String pattern = "MMMM";
        SimpleDateFormat obDateFormat = new SimpleDateFormat(pattern);
        setMonth(obDateFormat.format(dateCal.getTime()));

        //        setMonth(dates[1]);
        setDay(dates[2]);
        System.out.println(dates[2] + "/" + dates[1] + "/" + dates[5]);

    }

    public void setDetailsSavedStates(Map<String, Object> detailsSavedStates) {
        this.detailsSavedStates = detailsSavedStates;
    }

    public Map<String, Object> getDetailsSavedStates() {
        if (detailsSavedStates == null) {
            detailsSavedStates = new HashMap<String, Object>();
        }
        return detailsSavedStates;
    }

    public void setCancelDecisionMode(boolean cancelDecisionMode) {
        this.cancelDecisionMode = cancelDecisionMode;
    }

    public void setCopyDecisionWithEmployeesMode(boolean copyDecisionWithEmployeesMode) {
        this.copyDecisionWithEmployeesMode = copyDecisionWithEmployeesMode;
    }

    public boolean isCopyDecisionWithEmployeesMode() {
        return copyDecisionWithEmployeesMode;
    }

    public void setDecisionKey(String decisionKey) {
        this.decisionKey = decisionKey;
    }

    public String getDecisionKey() {
        return decisionKey;
    }

    public void setViewInNewWindow(boolean viewInNewWindow) {
        this.viewInNewWindow = viewInNewWindow;
    }

    public boolean isViewInNewWindow() {
        return viewInNewWindow;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
