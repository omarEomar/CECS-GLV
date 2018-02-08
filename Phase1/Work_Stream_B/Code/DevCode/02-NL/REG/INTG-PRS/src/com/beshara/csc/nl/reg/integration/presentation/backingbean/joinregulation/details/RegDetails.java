package com.beshara.csc.nl.reg.integration.presentation.backingbean.joinregulation.details;


import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


public abstract class RegDetails implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    //constants
    private static final String MODULE_TITLE_KEY = "module_div_title";
    private static final String REG_DTLS_MENU_KEY = "reg_dtls_menu";
    private static final String REG_DTLS_TITLE_KEY = "reg_dtls_title";
    private static final String RESOURCE_BUNDLE_NAME =
        "com.beshara.csc.nl.reg.integration.presentation.resources.integration";

    private static final String REG_TYPE = "regType";
    private static final String REG_YEAR = "regYear";
    private static final String REG_NUM = "regNum";
    
    private String regDetailsPageUri =
        "/integration/reg/jsps/joinregulation/details/regdetails.jsf?";
    //instance variables
    private SharedUtilBean sharedUtils;
    private String fullURL;
    private Long regType;
    private Long regYear;
    private Long regNum;
    private IRegulationsDTO regDTO;
    private String beanName;
    
    public RegDetails() {
        super();
        init();
    }
    
    //abstract methods
    protected abstract String getModuleBundle();

    //business methods
    public void init(){
        sharedUtils = SharedUtilBean.getInstance();
        regType = getAtt(REG_TYPE);
        regYear = getAtt(REG_YEAR);
        regNum = getAtt(REG_NUM);
        if (regType != null && regYear != null  && regNum != null) {
            if(regDTO == null){
                try {//regDTO.getRegDedignTablesDTOList()
                    IEntityKey code = RegEntityKeyFactory.createRegulationsEntityKey(regType, regYear, regNum);
                    regDTO = (IRegulationsDTO)RegClientFactory.getRegulationsClient().getById(code);
                    regDTO.setRegDedignTablesDTOList((List)RegClientFactory.getREGDedignTablesClient().getByRegCode(code));
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }            
            }
        }
    }
    
    protected Long getAtt(String att){
        String value = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(att);
        if(value != null && !value.equals("") && !value.equals("null")){
            return Long.valueOf(value);
        }
        return null;
    }
    
    public void openRegDetails(IEntityKey regCode, String beanName) {
        preOpen();
        open(regCode, beanName);
        postOpen();
    }
    
    
    public void openRegDetails(javax.faces.event.ActionEvent actionEvent) {
        preOpen();
        open(actionEvent);
        postOpen();
    }

    protected void preOpen() {

    }

    protected void postOpen() {

    }

    protected void open(IEntityKey regCode, String beanName) {
        regType = ((IRegulationsEntityKey)regCode).getRegtypeCode();
        regNum = ((IRegulationsEntityKey)regCode).getRegulationNumber();
        regYear = ((IRegulationsEntityKey)regCode).getRegyearCode();
        setBeanName(beanName);
        this.constructRegDetailsPagePath();
        this.getContainerBean().setJavaScriptCaller("openEmpListWindow('" + getFullURL() + "' , '" + "" + "');");
    }
    
    public Object evaluateValueBinding(String valueBindingExpression) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return app.createValueBinding("#{" + valueBindingExpression + 
                                      "}").getValue(ctx);

    }
    
    protected void open(javax.faces.event.ActionEvent actionEvent) {
//      regNum = (Long)actionEvent.getComponent().getAttributes().get("regNum");
//      regYear = (Long)actionEvent.getComponent().getAttributes().get("regYear");
//      regType = (Long)actionEvent.getComponent().getAttributes().get("regType");
        
        regNum = StringToLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(REG_NUM));
        regYear = StringToLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(REG_YEAR));
        regType = StringToLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(REG_TYPE));
        this.constructRegDetailsPagePath();
    }
    
    public BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getBeanName());
    }
    
    public String getEmpName(){
        if(CSCSecurityInfoHelper.getUser() !=null){
            return CSCSecurityInfoHelper.getUser().getName();
        }
        return "";
    }
    
    public String getModuleTitle(){
        return getSharedUtils().getResourceBundle(getModuleBundle()).getString(MODULE_TITLE_KEY);
    }
    
    public void constructRegDetailsPagePath() {
        fullURL = JSFHelper.viewIdToUrl(regDetailsPageUri + "regType=" + regType + "&regYear=" + regYear + "&regNum=" + regNum);
        System.out.println(fullURL);
    }
    
    public String getRegDetailsMenu(){
        return getSharedUtils().getResourceBundle(RESOURCE_BUNDLE_NAME).getString(REG_DTLS_MENU_KEY);
    }
    
    public String getHour(){
        return  new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }
    
    public String getDate(){
        return  new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public String getPageTitle() {
        return getSharedUtils().getResourceBundle(RESOURCE_BUNDLE_NAME).getString(REG_DTLS_TITLE_KEY);
    }
    
    public int getMaterialsDTOListSize(){
        if(regDTO !=null &&regDTO.getRegRegulationMaterialsDTOList() !=null)
            return regDTO.getRegRegulationMaterialsDTOList().size();
        return 0;
    }
    
    public int getSubjectsDTOListSize(){
        if(regDTO !=null &&regDTO.getSubjectsDTOList() !=null)
            return regDTO.getSubjectsDTOList().size();
        return 0;
    }

    public int getDedignTablesDTOListSize(){
        if(regDTO !=null &&regDTO.getRegDedignTablesDTOList() !=null)
            return regDTO.getRegDedignTablesDTOList().size();
        return 0;
    }
    
    public Long StringToLong(String str){
        if(str == null || str.trim().equals("")){
            return null;
        }
        return Long.valueOf(str);
    }
    
    
    //------------------ getters and setters ------------------
    
    public void setSharedUtils(SharedUtilBean sharedUtils) {
        this.sharedUtils = sharedUtils;
    }

    public SharedUtilBean getSharedUtils() {
        return sharedUtils;
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setRegDTO(IRegulationsDTO regDTO) {
        this.regDTO = regDTO;
    }

    public IRegulationsDTO getRegDTO() {
        return regDTO;
    }
    
    

    public void setRegType(Long regType) {
        this.regType = regType;
    }

    public Long getRegType() {
        return regType;
    }

    public void setRegYear(Long regYear) {
        this.regYear = regYear;
    }

    public Long getRegYear() {
        return regYear;
    }

    public void setRegNum(Long regNum) {
        this.regNum = regNum;
    }

    public Long getRegNum() {
        return regNum;
    }
}
