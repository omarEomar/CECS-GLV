package com.beshara.csc.nl.reg.presentation.backingbean.conceptadminjustice;

import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.util.wizardbar.WizardStep;

import javax.faces.event.ActionEvent;


public class ConceptAdminJusticeMaintainBean extends ManyToManyMaintainBaseBean {

    private static final String FINISH_NAVIGATION_CASE = "ADMIN_JUSTIC_LIST";
    private static final String CURRENT_STEP_KEY = "conceptadminjusticeKey";
    private static final String NEXT_NAVIGATION_CASE ="subjectmaintainnavigation";

    public ConceptAdminJusticeMaintainBean() {
        setClient(RegClientFactory.getREGAdminJusticeClientForCenter());
        setCurrentStep(CURRENT_STEP_KEY);
        setNextNavigationCase(NEXT_NAVIGATION_CASE);
        setFinishNavigationCase(FINISH_NAVIGATION_CASE);
    }

    public void scrollerAction(ActionEvent ae) {
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusOnlyOnElement('system_name')");
    }

    public String finish() {
        String navCase = null;
        ListBean listBean =(ListBean)evaluateValueBinding("principlesOfEliminationListBean");
        try {
            navCase = super.finish();
        } catch (ItemNotFoundException e) {
            unlock();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            unlock();
        } catch (DataBaseException e) {
            e.printStackTrace();
            unlock();
        }
        if (listBean != null) {
            try {
                setMyTableData(listBean.getMyTableData());
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            highlighDataTable("principlesOfEliminationListBean");

        }
        unlock();
        return navCase;
    }
    
    public String getCurrentStepJSValidation() {

        String clientSideJavaScript = null;
        WizardStep currentStep = 
            (WizardStep)(getWizardBar().getWizardSteps()).get(getCurrentStep());

        if (currentStep != null)
            clientSideJavaScript = currentStep.getJsValidation();

        if (clientSideJavaScript == null)
            clientSideJavaScript = "stepValidation();";
          return "javascript:x="+clientSideJavaScript+"if(x){block();}else{return x;}";
    }



}
