package com.beshara.csc.nl.reg.integration.presentation.backingbean.viewregulations;


import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;


public class ViewRegulationsBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long tabRecSerial;
    private String beanName;
    
    public ViewRegulationsBean() {
        setMyTableData(new ArrayList());
    }

    public void displayData() {
        System.out.println(">>>>>>>>>openViewRegDiv>>>>>>>>>>>");
        if (tabRecSerial != null){
            try {
                 setMyTableData(RegClientFactory.getModuleRelationsClient().getRegualationByTabRecSerial(tabRecSerial));
                if (getMyTableData().size()!=0) {
                    ((BaseBean)evaluateValueBinding(beanName)).setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.integrationDiv1);");   
                    return;
                }
                ((BaseBean)evaluateValueBinding(beanName)).getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global",
                                                                              "NO_RELATED_REGULATIONS_MSG"));
            } catch (DataBaseException e) {
                ((BaseBean)evaluateValueBinding(beanName)).getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global",
                                                                              "NO_RELATED_REGULATIONS_MSG"));

            } catch (Exception e) {
                ((BaseBean)evaluateValueBinding(beanName)).getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global",
                                                                              "NO_RELATED_REGULATIONS_MSG"));
            }
        } else {
            ((BaseBean)evaluateValueBinding(beanName)).getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global",
                                                                          "NO_RELATED_REGULATIONS_MSG"));
            }  
        }
    
    public void scrollerAction(ActionEvent ae) {
        ((BaseBean)evaluateValueBinding(beanName)).setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.integrationDiv1);");
    }
    public void setTabRecSerial(Long tabRecSerial) {
        this.tabRecSerial = tabRecSerial;
    }

    public Long getTabRecSerial() {
        return tabRecSerial;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
