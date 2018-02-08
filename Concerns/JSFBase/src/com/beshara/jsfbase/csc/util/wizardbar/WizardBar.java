package com.beshara.jsfbase.csc.util.wizardbar;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlPanelGrid;


// controlTYPE 0 COMMANDBUTTON 1 COMMANDlINK  2 HTMLlINK
//STATUS     0 nonVISITED 1 VISITED 2 MODIFIED
//NAVIGATIONSTATUS 1 ALLOWED(ENABLED) 0 NOT ALLOWED(DISABLED)

public class WizardBar implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Map wizardSteps = new HashMap();
    private String configurationId;
    private List stepsList = new ArrayList();
    private String width = "700";
    private HtmlPanelGrid panelGrid;
    private String height = "25";
    private String currentStep;

    public WizardBar() {

    }


    public WizardBar(String _configurationId) {

        this.setConfigurationId(_configurationId);
        wizardSteps.clear();
        WizardConfigurator conf = 
            new WizardConfigurator(wizardSteps, this.getConfigurationId());
        conf.parseSteps();
    }

    /**
     * Purpose: this method created to this to parse the xml and load the bar conf
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void getBarConfiguration() {
        if (this.getConfigurationId() != null) {

            WizardConfigurator conf = 
                new WizardConfigurator(wizardSteps, this.getConfigurationId());
            conf.parseSteps();
            // this.constructPanelGrid();

        }
    }

    public void setWizardSteps(Map wizardSteps) {
        this.wizardSteps = wizardSteps;
    }

    public Map getWizardSteps() {
        return wizardSteps;
    }


    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    public String getConfigurationId() {


        return configurationId;
    }

    public void setStepsList(List stepsList) {
        this.stepsList = stepsList;
    }

    public List getStepsList() {
        return stepsList;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setPanelGrid(HtmlPanelGrid panelGrid) {
        this.panelGrid = panelGrid;
    }

    public HtmlPanelGrid getPanelGrid() {
        this.constructPanelGrid();
        return panelGrid;
    }

    public void constructPanelGrid() {
        System.out.println("the keys should be" + wizardSteps.size());
        if (wizardSteps != null) {
            panelGrid = new HtmlPanelGrid();
            panelGrid.getChildren().clear();
            Set mapKeys = wizardSteps.keySet();
            Iterator it = mapKeys.iterator();
            panelGrid.setColumns(1);
            List<WizardStep> steps = new ArrayList<WizardStep>();
            while (it.hasNext()) {
                String key = (String)it.next();
                System.out.println("this is the key------------" + key);
                WizardStep step = (WizardStep)wizardSteps.get(key);

                steps.add(step);


            }

            this.sort(steps, 0, steps.size() - 1);

            for (WizardStep step: steps) {
                constructStep(step);

            }

        }
    }

    public void constructStep(WizardStep step) {
        try {
            HtmlCommandButton button = new HtmlCommandButton();
            String buttonTitle = step.getTitle();
            //buttonTitle = String.valueOf(step.getOrder()) + "-" + buttonTitle;
            
            //            if(getStepDetailsNumber(step.getStepBeanName())!=null) //big problem here how to update this number from the bean
            //            buttonTitle=buttonTitle+" ("+getStepDetailsNumber(step.getStepBeanName()) +" )";
            button.setValue(buttonTitle);
            MethodBinding methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       step.getAction() + 
                                                                                       "}", 
                                                                                       new Class[] { ActionEvent.class });


            button.setActionListener(methodBinding);
            button.setId(step.getMapKey());

            //in case to make it simulate link
            //                if(step.getControlType()==1){
            //                button.setStyleClass("btn");
            //                }


            boolean next = false;

            //the css styles


            if (step.isVisited() && step.isValidated()) {

                button.setStyleClass("Step-finish_button");

            } else {

                button.setStyleClass("Setps-Next_button");
                next = true;
            }


            //if the this step=the current step or this is the first step of the bar 

            if

                ((currentStep != null && 
                  step.getMapKey().equals(currentStep)) || 
                 (currentStep == null && step.order == 1)) {
                button.setStyleClass("Step-current_button");
                next = false;
            }


            int maintainMode = getMaintainMode();

            // if the step is not mandatory check the status of all the mandatory steps first 

            if (!step.isMandatory()) {

                if (checkNavigationStatus()) {
                    step.setNavigateStatus(1);
                } else {
                    step.setNavigateStatus(0);
                }
            }

            //if the step is mandatory check that all the mandatory buttons before it visited and validated

            if (step.isMandatory()) {

                List<WizardStep> tempSteps = 
                    (ArrayList<WizardStep>)(((ArrayList<WizardStep>)getMandatroySteps()).clone());

                boolean active = true;
                for (WizardStep tStep: tempSteps) {
                    if ((!tStep.isVisited() || !tStep.isValidated()) && 
                        !tStep.getMapKey().equals(step.getMapKey()) && 
                        tStep.order < step.order)
                        active = false;

                }

                if (active)
                    step.setNavigateStatus(1);

                if (!active)
                    step.setNavigateStatus(0);

            }


            //check that all the steps that this step depend on are visited and validated 

            this.checkStepDependancy(step);

            if (maintainMode != 0) {

                step.setNavigateStatus(1);

            }


            if (step.getNavigateStatus() == 0) {

                button.setDisabled(true);

            }

            if (next && step.getNavigateStatus() == 1) {
                button.setStyleClass("Setps-Next_button_hand");

            }


            String clientSideJavaScript = null;
            WizardStep currentStep = 
                (WizardStep)getWizardSteps().get(getCurrentStep());

            if (currentStep != null)
                clientSideJavaScript = currentStep.getJsValidation();

            if (clientSideJavaScript == null)
                clientSideJavaScript = "return stepValidation();";

            button.setOnclick(clientSideJavaScript);
            panelGrid.getChildren().add(button);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }


    public void sort(List<WizardStep> l, int start, int end) {
        WizardStep swap = new WizardStep();
        if (start < end) {
            int k = findSmallest(l, start + 1, end);
            WizardStep smallest = l.get(k);
            WizardStep current = l.get(start);
            if (smallest.getOrder() < current.getOrder()) {

                swap = smallest;
                l.set(k, current);
                //smallest=current;
                l.set(start, swap);
                //current=swap;

            }
            sort(l, start + 1, end);
        }


    }

    public int findSmallest(List<WizardStep> l, int start, int end) {
        int k = 0, index = 0;
        if (start == end)
            return end;
        index = findSmallest(l, start + 1, end);
        WizardStep first = l.get(index);
        WizardStep second = l.get(start);
        if (first.getOrder() < second.getOrder()) {
            k = index;
        } else {
            k = start;
        }
        return k;
    }

    /**
     * Purpose: this method created to  check if all the mandatory steps visited or not 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public

    boolean checkNavigationStatus() {
        List<WizardStep> mandatorySteps = getMandatroySteps();

        for (WizardStep wStep: mandatorySteps) {

            if (!wStep.isVisited() || !wStep.isValidated())
                return false;

        }


        return true;

    }

    /**
     * Purpose: this method created to  return the mandatory  steps in the  current bar
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public List<WizardStep> getMandatroySteps() {


        List<WizardStep> mandatorySteps = new ArrayList<WizardStep>();
        Map wizardSteps = getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        while (it.hasNext()) {

            String key = (String)it.next();
            WizardStep step = (WizardStep)wizardSteps.get(key);

            if (step.isMandatory()) {
                mandatorySteps.add(step);
            }

        }

        return mandatorySteps;

    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    int getMaintainMode() {

        Integer mode = 
            (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{pageBeanName.maintainMode}").getValue(FacesContext.getCurrentInstance());
        if (mode == null)
            return 0;
        return mode.intValue();

    }

    public String getStepDetailsNumber(String stepBeanName) {

        Integer detailsNo = 
            (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                           stepBeanName + 
                                                                                           ".currentListSize}").getValue(FacesContext.getCurrentInstance());
        if (detailsNo == null)
            return null;
        return String.valueOf(detailsNo.intValue());
    }

    /**
     * Purpose: this method created to check that all the steps that this step depend on are visited and validated or not 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void checkStepDependancy(WizardStep step) {

        if (step.getRelevantSteps() != null && 
            step.getRelevantSteps().size() > 0) {

            for (String relevant: step.getRelevantSteps()) {
                WizardStep relevantStep = 
                    (WizardStep)this.getWizardSteps().get(relevant);
                if (!relevantStep.isVisited() || !relevantStep.isValidated()) {
                    step.setNavigateStatus(0);
                    break;

                }

            }


        }


    }

    /**
     * Purpose: this method created to reinitialize the wizard bar 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void reinitialize() {
        getWizardSteps().clear();
        getBarConfiguration();

    }


}
