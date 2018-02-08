package com.beshara.jsfbase.csc.util;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.operationbar.config.OperationBarUtils;
import com.beshara.common.web.jsf.shared.JSFHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class IntegrationBean implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    public static final String BEAN_NAME = "integrationBean";
    private String beanNameFrom;
    private String beanNameTo;
    private String navgationCaseTo;
    private String navgationCaseFrom;
    private String actionFrom; // set this attribute if you want to invoke action into bean from "must be this action return string(navigation case)"
    private String actionTo; // set this attribute if you want to invoke action into bean to "must be this action return string(navigation case)"
    private String cancelFrom; // this method will be invoked when clicking the cancel button "must  return string(navigation case)"
    private List<IBasicDTO> selectedDTOFrom = new ArrayList<IBasicDTO>(0);
    private List<IBasicDTO> selectedDTOTo = new ArrayList<IBasicDTO>(0);
    private boolean renderedBackButton = false;
    private String selectionType = OperationBarUtils.SELECTION_VALUE_SINGLE; // single or MULTI (OperationBarUtil.SELECTION_VALUE_MULTI)
    private boolean disableBackButton = false;
    private HashMap hmBaseBeanFrom = new HashMap();
    private String moduleFrom;
    private HashMap hmObjects = new HashMap();
    private String initializeMethod;

    public IntegrationBean() {
    }
    
    public static IntegrationBean getInstance(){
        return (IntegrationBean)JSFHelper.getValue(BEAN_NAME);
    }
    
    public void resetData(){
        this.beanNameFrom = null;
        this.beanNameTo = null;
        this.navgationCaseTo = null;
        this.navgationCaseFrom = null;
        this.actionFrom = null;
        this.actionTo = null;
        this.cancelFrom = null;
        this.selectedDTOFrom = new ArrayList<IBasicDTO>(0);
        this.selectedDTOTo = new ArrayList<IBasicDTO>(0);
        this.renderedBackButton = false;
        this.selectionType =  OperationBarUtils.SELECTION_VALUE_SINGLE;
        this.disableBackButton = false;
        this.hmBaseBeanFrom = new HashMap();
        this.moduleFrom = null;
        this.hmObjects = new HashMap();
        this.initializeMethod = null;
    }

    /**
     * Purpose: calling this methos when go to the integration bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 3, 2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public String goTO() {
        if ((getBeanNameTo() != null && !getBeanNameTo().equals("")) && 
            (getActionTo() != null && !getActionTo().equals(""))) {
            return (String)JSFHelper.callMethod( getBeanNameTo() + "." + getActionTo() );
        }
        return getNavgationCaseTo();
    }

    /**
     * Purpose: calling this methos when go to "from" the integration bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 3, 2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public String goFrom() {
        reInitializeBeanFrom();
        if ((getBeanNameFrom() != null && !getBeanNameFrom().equals("")) && 
            (getActionFrom() != null && !getActionFrom().equals(""))) {
            return (String)JSFHelper.callMethod( getBeanNameFrom() + "." + getActionFrom() );
        }
        return getNavgationCaseFrom();
    }

    /**
     * Purpose: calling this methos when cancel the integration bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 3, 2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public String cancelGoTO() {
        reInitializeBeanFrom();
        if ((getBeanNameFrom() != null && !getBeanNameFrom().equals("")) && 
            (getCancelFrom() != null && !getCancelFrom().equals(""))) {
            
            return (String)JSFHelper.callMethod( getBeanNameFrom() + "." + getCancelFrom() );
        }
        return getNavgationCaseFrom();
    }

    /**
     * Purpose: ReInitialize Bean From
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 3, 2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void reInitializeBeanFrom() {
        if (getHmBaseBeanFrom() != null && getHmBaseBeanFrom().size() != 0) {
            Iterator iterator = getHmBaseBeanFrom().keySet().iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                try {
                    if (object != null) {
                        JSFHelper.setValue(object.toString(), getHmBaseBeanFrom().get(object));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Purpose: ReInitialize Integration Bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 3, 2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void reInitializeBean() {
        try {
            JSFHelper.setValue(BEAN_NAME, new IntegrationBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBeanNameFrom(String beanNameFrom) {
        this.beanNameFrom = beanNameFrom;
    }

    public String getBeanNameFrom() {
        return beanNameFrom;
    }

    public void setBeanNameTo(String beanNameTo) {
        this.beanNameTo = beanNameTo;
    }

    public String getBeanNameTo() {
        return beanNameTo;
    }

    public void setNavgationCaseTo(String navgationCaseTo) {
        this.navgationCaseTo = navgationCaseTo;
    }

    public String getNavgationCaseTo() {
        return navgationCaseTo;
    }

    public void setNavgationCaseFrom(String navgationCaseFrom) {
        this.navgationCaseFrom = navgationCaseFrom;
    }

    public String getNavgationCaseFrom() {
        return navgationCaseFrom;
    }


    public void setRenderedBackButton(boolean renderedBackButton) {
        this.renderedBackButton = renderedBackButton;
    }

    public boolean isRenderedBackButton() {
        return renderedBackButton;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setDisableBackButton(boolean disableBackButton) {
        this.disableBackButton = disableBackButton;
    }

    public boolean isDisableBackButton() {
        return disableBackButton;
    }

    public void setSelectedDTOFrom(List<IBasicDTO> selectedDTOFrom) {
        this.selectedDTOFrom = selectedDTOFrom;
    }

    public List<IBasicDTO> getSelectedDTOFrom() {
        return selectedDTOFrom;
    }

    public void setSelectedDTOTo(List<IBasicDTO> selectedDTOTo) {
        this.selectedDTOTo = selectedDTOTo;
    }

    public List<IBasicDTO> getSelectedDTOTo() {
        return selectedDTOTo;
    }

    public void setHmBaseBeanFrom(HashMap hmBaseBeanFrom) {
        this.hmBaseBeanFrom = hmBaseBeanFrom;
    }

    public HashMap getHmBaseBeanFrom() {
        return hmBaseBeanFrom;
    }

    public void setActionFrom(String actionFrom) {
        this.actionFrom = actionFrom;
    }

    public String getActionFrom() {
        return actionFrom;
    }

    public void setActionTo(String actionTo) {
        this.actionTo = actionTo;
    }

    public String getActionTo() {
        return actionTo;
    }

    public void setModuleFrom(String moduleFrom) {
        this.moduleFrom = moduleFrom;
    }

    public String getModuleFrom() {
        return moduleFrom;
    }

    public void setHmObjects(HashMap hmObjects) {
        this.hmObjects = hmObjects;
    }

    public HashMap getHmObjects() {
        return hmObjects;
    }

    public void setCancelFrom(String cancelFrom) {
        this.cancelFrom = cancelFrom;
    }

    public String getCancelFrom() {
        return cancelFrom;
    }

    public void setInitializeMethod(String initializeMethod) {
        this.initializeMethod = initializeMethod;
    }

    public String getInitializeMethod() {
        return initializeMethod;
    }
}
