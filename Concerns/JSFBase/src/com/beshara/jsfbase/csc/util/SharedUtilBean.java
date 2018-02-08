package com.beshara.jsfbase.csc.util;


import com.beshara.base.config.ConfigManager;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Bean Encapsulate shared function in the application, such as (localization, page direction,...)
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Amir Nasr    13-SEP-2007     Created
 *    <br>
 *
 * @author       Beshara Group
 * @version      1.0
 * @since        13/09/2007
 *
 */
public class SharedUtilBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "shared_util";
    private static final int DEFAULT_NO_OF_TABLE_ROWS = 14;
    /* This field carry the cureent application locale, ar or en,.. */
    private String locale = "ar";

    /* This field carry the cureent application page direction, right to left or left to right */
    private String pageDirection = "rtl";

    /* This field carry the number of table rows in our system */
    private int noOfTableRows = DEFAULT_NO_OF_TABLE_ROWS;

    /* This field to check of the user is online or not */
    private boolean onlineUser = true;

    /* This field carry client browser type */
    private String browserType;
    /* This Field to handle the calendar arabic problem*/
    private boolean calendarpopupLeft = false;
    /* This Field carry the current page index of the paging bar*/
    private Integer pageIndex;

    /*This field carry value to success msg*/
    private String successMsgValue = null;

    /*This field carry value to err msg*/
    private String errMsgValue = null;
    private String globalBundle = "com.beshara.jsfbase.csc.msgresources.globalexceptions";
     private String localBundle = "";
    public SharedUtilBean() {
    }

    public static SharedUtilBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return (SharedUtilBean)ctx.getApplication().createValueBinding("#{" + 
                                                                       BEAN_NAME + 
                                                                       "}").getValue(ctx);
    }

    public void init() {
        setNoOfTableRows(DEFAULT_NO_OF_TABLE_ROWS);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setPageDirection(String pageDirection) {
        this.pageDirection = pageDirection;
    }

    public String getPageDirection() {
        return pageDirection;
    }

    public ResourceBundle getResourceBundle(String basename) {
        return ResourceBundle.getBundle(basename, 
                                        FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public String messageLocator(ResourceBundle resourceBundle, String key) {
        String valueOfSubmittedKey = "";
        try {
            valueOfSubmittedKey = resourceBundle.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueOfSubmittedKey;
    }

    public String messageLocator(String basename, String key) {

        String valueOfSubmittedKey = "";
        try {
            ResourceBundle bundle = 
                ResourceBundle.getBundle(basename, FacesContext.getCurrentInstance().getViewRoot().getLocale());
            valueOfSubmittedKey = bundle.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return valueOfSubmittedKey;
    }

    public void handleSuccMsg(String globalResourceMsg) {
        setSuccessMsgValue(globalResourceMsg);
    }

    public void handleSuccMsg(String bundle, String key) {
        if (bundle != null && !bundle.equals(""))
            setSuccessMsgValue(messageLocator(bundle, key));
    }

    public void handleException(Exception e) {
        handleException(e, null, null);
    }

    /**
     * Purpose:handle Excption
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date: 06-Aug-2008
     * 1.3 - Creation/Modification:Modification      
     * 1.4-  Description : I add  key!=null && !key.equals("")  in the 
     *  condition  so that if there are bundle and key sent to the method 
     *  so we will setErrMsgValue with the sent key  else we will setErrMsgValue with e.getMessage 
     *  
     */
    public void handleException(Exception e, String bundle, String key) {

        if (bundle != null && !bundle.equals("") && key != null && 
            !key.equals(""))
            setErrMsgValue(messageLocator(bundle, key));
        else if (e instanceof SharedApplicationException) {
            if (e.getMessage() != null && !e.getMessage().equals("")) {
                findMessageInResourceBundle(e);
            } else {
                setErrMsgValue("SystemFailureException");
            }
        } else if (e instanceof DataBaseException) {
            if (e.getMessage() != null && !e.getMessage().equals("")) {
                //setErrMsgValue(e.getMessage());
                findMessageInResourceBundle(e);
            } else {
                setErrMsgValue("SystemFailureException");
            }
        } else if (e instanceof Exception) {
            setErrMsgValue("SystemFailureException");
        }
        if (e != null) {
            e.printStackTrace();
        }
    }

    private void findMessageInResourceBundle(Exception e) {
        String bundle = getGlobalBundle();
             String errorMsgValue = "SystemFailureException";
             boolean messageFoundInLocal = false;
             String getMessageValue = "";

             // search in local resources
             if (!"".equals(getLocalBundle())) {
                 bundle = getLocalBundle();
                 getMessageValue = messageLocator(bundle, e.getMessage());
                 if (getMessageValue != null && !"".equals(getMessageValue)) {
                     errorMsgValue = getMessageValue;
                     messageFoundInLocal = true;
                 }
                 
             }

             //   search in Global Exceptions 
             if (!messageFoundInLocal) {
                  getMessageValue = messageLocator(getGlobalBundle(), e.getMessage());
                  errorMsgValue = e.getMessage();
             }
             setErrMsgValue(errorMsgValue);

    }

    public void setNoOfTableRows(int noOfTableRows) {
        this.noOfTableRows = noOfTableRows;
    }

    public int getNoOfTableRows() {
        return noOfTableRows;
    }

    public void switchToArabic() {
        locale = "ar";
        pageDirection = "rtl";
        this.setCalendarpopupLeft(false);
    }

    public void switchToEnglish() {
        locale = "en";
        pageDirection = "ltr";
        this.setCalendarpopupLeft(true);
    }

    public void setOnlineUser(boolean onlineUser) {
        this.onlineUser = onlineUser;
    }

    public boolean isOnlineUser() {
        return onlineUser;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setCalendarpopupLeft(boolean calendarpopupLeft) {
        this.calendarpopupLeft = calendarpopupLeft;
    }

    public boolean isCalendarpopupLeft() {

        return calendarpopupLeft;
    }

    public String getContextPath() {
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }

    public void changePageIndex(ValueChangeEvent event) {
        String newValue = (String)event.getNewValue();
        if (newValue != null && !newValue.equals(""))
            pageIndex = Integer.parseInt(newValue);
    }

    public void setMsgValue(ActionEvent event) {
        setErrMsgValue(null);
        setSuccessMsgValue(null);
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }


    public void setSuccessMsgValue(String successMsgValue) {
        this.successMsgValue = successMsgValue;
    }

    public String getSuccessMsgValue() {
        return successMsgValue;
    }

    public void setErrMsgValue(String errMsgValue) {
        this.errMsgValue = errMsgValue;
    }

    public String getErrMsgValue() {
        return errMsgValue;
    }

    /**
     * Purpose:return date object and return it in specific format 
     * Creation/Modification History :
     * 1.1 - Developer Name: 
     * 1.2 - Date:   
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     * Sugestion : Moving this method to shared uitl
     */
    public String formatDate(Object d, String dateFormat) {
        String format = "";
        if (dateFormat != null && !dateFormat.equals("") && d != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            format = sdf.format(d);
        }
        return format;
    }


    /**
     * Purpose:return long number as a string and that long is padded 0s from the left depends on the digitNum
     * for example: if num = "7" and digitNum = 2 then the returned value is "07"
     * Parameters
     *          String num : number to be formated,it must be in long or int format
     *          int digitNum : number of digits u want to format ur number
     * Creation/Modification History :
     * 1.1 - Developer Name: Omar EzzEldin
     * 1.2 - Date:   21/09/2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: Creation     
     */
    public static String formatNum(String num, int digitNum) {
        String sb = "";
        try {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(digitNum);
            nf.setMaximumIntegerDigits(digitNum);
            Long longNum = Long.parseLong(num);
            sb = nf.format(longNum.longValue());
        } catch (Exception e) {
            e.printStackTrace();
            sb = "";
        }
        return sb;
    }

    /**
     * Purpose: utility function to deep copy any object using serialization
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  20-10-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public static Object deepCopyObject(Object object) throws Exception {

        // serialize object into byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        byte buf[] = baos.toByteArray();
        oos.close();

        // deserialize byte array into object
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object newObject = ois.readObject();
        ois.close();
        return newObject;
    }

    /**
     * Purpose: this method created to return the current view id
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public static String getCurrentViewId() {

        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getId();

    }


    /**
     * Purpose: this method created to handle navigation case especially if your method has return type void 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void handleNavigation(String nCase) {

        NavigationHandler nHandler = 
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nHandler.handleNavigation(FacesContext.getCurrentInstance(), null, 
                                  nCase);

    }

    public String getDynamicValue(String key) {

        String keyValue = key;
        String[] baseNames = 
        { "com.beshara.jsfbase.csc.msgresources.global", "com.beshara.jsfbase.csc.msgresources.globalexceptions" };

        if (baseNames != null && baseNames.length > 0 && key != null && 
            !key.equals("")) {

            keyValue = 
                    messageLocator(FacesContext.getCurrentInstance().getApplication().getMessageBundle(), 
                                   key);

            if (keyValue != null && !keyValue.equals(""))
                return keyValue;
            for (int i = 0; i < baseNames.length; i++) {
                keyValue = messageLocator(baseNames[i], key);
                if (keyValue != null && !keyValue.equals(""))
                    return keyValue;
            }

        }
        return keyValue;
    }

    public Long getLastDayOfMonth(Long month, Long year) {
        if (month.equals(4L) || month.equals(6L) || month.equals(5L) || 
            month.equals(9L) || month.equals(11L)) {
            return 30L;
        } else if (month.equals(2L)) {
            if ((year.intValue() % 4) == 0)
                return 29L;
            return 28L;
        }
        return 31L;
    }


    /**
     * @Author amr galal
     * @Date 11/8/2009
     * @Desc get current year,month,day,number of day in month.
     *
     */
    public

    int currentYear() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH); // get cuurent month
        int year = cal.get(Calendar.YEAR); // get current year 
        int day = cal.get(Calendar.DAY_OF_MONTH); //get current Day 
        int noOfDay = 
            cal.getActualMaximum(Calendar.DAY_OF_MONTH); // get number of day in month(ex:may-----31)
        return year;
    }


    public int currentMonth() {
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int noOfDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return month + 1;
    }

    public String[] returnMonthYearFromSqlDate(Date date) {
        String[] returnMotnhYear = new String[2];
        SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("MM");
        String month = monthDayYearformatter.format(date);


        monthDayYearformatter = new SimpleDateFormat("yyyy");
        String year = monthDayYearformatter.format(date);
        returnMotnhYear[0] = month;
        returnMotnhYear[1] = year;

        return returnMotnhYear;
    }

    public String[] returnMonthYearFromTimeStampDate(java.util.Date date) {
        String[] returnMotnhYear = new String[2];
        Date sqlDate = new Date(date.getTime());
        returnMotnhYear = this.returnMonthYearFromSqlDate(sqlDate);
        return returnMotnhYear;
    }

    public Timestamp getTimeStampWithFirstDayOfMonth(Timestamp date) {

        SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("MM");
        int firstInstallMonth = 
            Integer.parseInt(monthDayYearformatter.format(date)) - 1;

        monthDayYearformatter = new SimpleDateFormat("yyyy");
        int firstInstallYear = 
            Integer.parseInt(monthDayYearformatter.format(date));

        Calendar toCal = 
            new GregorianCalendar(firstInstallYear, firstInstallMonth, 1);

        return new Timestamp(toCal.getTime().getTime());
    }

    public Timestamp getTimeStampWithLastDayOfMonth(Timestamp date) {

        SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("MM");
        int firstInstallMonth = 
            Integer.parseInt(monthDayYearformatter.format(date)) - 1;

        monthDayYearformatter = new SimpleDateFormat("yyyy");
        int firstInstallYear = 
            Integer.parseInt(monthDayYearformatter.format(date));

        Calendar toCal = 
            new GregorianCalendar(firstInstallYear, firstInstallMonth, 1);
        toCal.set(Calendar.DAY_OF_MONTH, 
                  toCal.getActualMaximum(toCal.DAY_OF_MONTH));

        return new Timestamp(toCal.getTime().getTime());
    }

    public static Date getDateWithFirstDayOfMonth(Long year, Long month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year.intValue(), (month.intValue()) - 1, 1);
        return new Date(calendar.getTime().getTime());
    }

    public void setGlobalBundle(String globalBundle) {
        this.globalBundle = globalBundle;
    }

    public String getGlobalBundle() {
        return globalBundle;
    }

    public void setLocalBundle(String localBundle) {
        this.localBundle = localBundle;
    }

    public String getLocalBundle() {
        return localBundle;
    }
    
    /* Date Created: 9-10-2014
        * Purpose: go to menu page    
        */ 
       public void goToMenuPage() throws IOException {
           ConfigManager cfg = ConfigManager.getInstance();
           String mnuUrl = cfg.getParam("glb:mnuUrl", true);
           FacesContext.getCurrentInstance().getExternalContext().redirect(mnuUrl);
       }

}
