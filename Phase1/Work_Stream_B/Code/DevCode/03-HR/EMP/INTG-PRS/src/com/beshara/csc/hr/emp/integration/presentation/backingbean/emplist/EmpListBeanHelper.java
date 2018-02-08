package com.beshara.csc.hr.emp.integration.presentation.backingbean.emplist;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @author Abdelrhman Fathy
 * @since 24Jun2014
 * @description helper to integrate with employees list.
 */
public abstract class EmpListBeanHelper implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    protected static final String DEF_HELPER_NAME = "empListHelper";
    private boolean empIntgListLoaded;
    private List<IBasicDTO> empIntgList = new ArrayList<IBasicDTO>(0);
    private IEntityKey code;
    private String fullURL;
    private String pageSavedTitle;
    private String empListPageUri =
        "/integration/emp/jsps/emplist/emplist.jsp";
        //"/module/jsps/alllevels/emplist.jsp";

    private static final String RESOURCE_BUNDLE_NAME =
        "com.beshara.csc.hr.emp.integration.presentation.resources.integration";
    private static final String MODULE_TITLE_KEY = "module_div_title";
    private static final String EMP_LIST_MENU_KEY = "emp_list_menu";
    private SharedUtilBean sharedUtils;
    EmpListBean empListBean;
    
    public EmpListBeanHelper(){
        sharedUtils = SharedUtilBean.getInstance();
        empIntgList  = new ArrayList<IBasicDTO>(0);
        //empListBean = (EmpListBean)JSFHelper.getValue("empListBean");
        //empListBean.setAliasValue(getContainerBeanName()+getHelperName());
    }
    
    
    protected abstract void loadEmpIntgList();    
    protected abstract String getContainerBeanName();  
    protected abstract IEntityKey getSelectedCode(); 
    protected abstract String getModuleBundle();
    public abstract String getPageTitle();

    

    public void constructEmpListPagePath() {
        fullURL = JSFHelper.viewIdToUrl(empListPageUri);
        System.out.println(fullURL);
    }
    
    protected void serializeData(List data) {
        try {
            
            File codeFile = new File("code.ser");
            if(!codeFile.exists()) {
                codeFile.createNewFile();
            } 
            FileOutputStream fileOut = new FileOutputStream(codeFile, false); 
            //FileOutputStream fileOut = new FileOutputStream("/tmp/code.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in code.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    protected List deserializeData() {
        try {
            FileInputStream fileIn = new FileInputStream("code.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List data = (List)in.readObject();
            setCode((IEntityKey)data.get(0));
            setPageSavedTitle((String)data.get(1));
            //setCode((IEntityKey)in.readObject());
            in.close();
            fileIn.close();
            return data;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("code class not found");
            c.printStackTrace();
        }
        return null;
    }
    
    protected void preOpen(){
        
    }
    
    protected void postOpen(){
        
    }
    
    
    protected void open(){
        //empListBean.setAliasValue(getContainerBeanName()+"."+getHelperName());
        this.constructEmpListPagePath();
        this.getContainerBean().setJavaScriptCaller("openEmpListWindow('" + getFullURL() + "' , '" + "test window title" + "');");
        //serialize code
        List list = new ArrayList(0);
        list.add(getSelectedCode());
        list.add(getPageTitle());
        list.add(this);
        serializeData(list);
    }
    
    public void openEmpList() {
        preOpen();
        open();
        postOpen();
    }
    
    
    
    public String getModuleTitle(){
        return getSharedUtils().getResourceBundle(getModuleBundle()).getString(MODULE_TITLE_KEY);
    }
    
    public Integer getEmpCount(){
        if(getEmpIntgList()!=null){
            return getEmpIntgList().size();
        }
        return 0;
    } 
    
    public String getEmpName(){
        if(CSCSecurityInfoHelper.getUser() !=null){
            return CSCSecurityInfoHelper.getUser().getName();
        }
        return "";
    } 
    
    public String getHour(){
        return  new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
    }
    
    public String getDate(){
        return  new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }
    
    public String getEmpListMenu(){
        return getSharedUtils().getResourceBundle(RESOURCE_BUNDLE_NAME).getString(EMP_LIST_MENU_KEY);
    }
    


    //--------------setters and getters---------------------

    protected String getHelperName(){
        return DEF_HELPER_NAME;
    }
    
    public void setEmpIntgListLoaded(boolean empIntgListLoaded) {
        this.empIntgListLoaded = empIntgListLoaded;
    }

    public boolean isEmpIntgListLoaded() {
        return empIntgListLoaded;
    }

    public void setEmpIntgList(List<IBasicDTO> empIntgList) {
        this.empIntgList = empIntgList;
    }

    public List<IBasicDTO> getEmpIntgList() {
        if(!isEmpIntgListLoaded()){
            //setCode(deserializeData());
            setCode((IEntityKey)deserializeData().get(0));
            loadEmpIntgList();
            setEmpIntgListLoaded(true);
        }
        return empIntgList;
    }

    public void setCode(IEntityKey code) {
        this.code = code;
    }

    public IEntityKey getCode() {
        return code;
    }
    
    public BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    public void setEmpListPageUri(String empListPageUri) {
        this.empListPageUri = empListPageUri;
    }

    public String getEmpListPageUri() {
        return empListPageUri;
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public SharedUtilBean getSharedUtils() {
        return sharedUtils;
    }

    public void setPageSavedTitle(String pageSavedTitle) {
        this.pageSavedTitle = pageSavedTitle;
    }

    public String getPageSavedTitle() {
        return (String)deserializeData().get(1);
    }
}
