package com.beshara.jsfbase.csc.backingbean;

import com.beshara.jsfbase.csc.util.wizardbar.WizardStep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class ManyToManyBaseBean extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    public ManyToManyBaseBean() {
    }

    //this function deprecated

    public void showWizardStep(String stepKey) {
        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        while (it.hasNext()) {
            String key = (String)it.next();
            WizardStep step = (WizardStep)wizardSteps.get(key);
            if (stepKey.equals(key)) {
                step.setStatus(0);
            } else {
                step.setStatus(1);
            }

        }

    }

    // to return wizard step  

    public WizardStep getWizardStep(String mapKey) {

        return (WizardStep)getWizardBar().getWizardSteps().get(mapKey);

    }

    public String getNavigationCaseByOrder(int order) {

        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        while (it.hasNext()) {

            String key = (String)it.next();
            WizardStep step = (WizardStep)wizardSteps.get(key);

            if (step.getOrder() == order) {
                return step.getNavigationCase();
            }

        }

        return null;

    }

    /**
     * Purpose: this method created to return the navigation case of the next step
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String getNextNavigationCase(String mapKey) {

        WizardStep step = getWizardStep(mapKey);
        if (step != null)
            return getNavigationCaseByOrder(step.getOrder() + 1);
        return null;
    }

    /**
     * Purpose: this method created to  return the navigation case of the previous step
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String getPreviousNavigationCase(String mapKey) {

        WizardStep step = getWizardStep(mapKey);
        if (step != null)
            return getNavigationCaseByOrder(step.getOrder() - 1);
        return null;
    }

    /**
     * Purpose: this method created to return the navigation case of the step
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String getNavigationCase(String mapKey) {
        WizardStep step = getWizardStep(mapKey);
        if (step != null)
            return getNavigationCaseByOrder(step.getOrder());
        return null;
    }

    /**
     * Purpose: this method created to  return the step with this order
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String getStepByOrder(int order) {

        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        while (it.hasNext()) {

            String key = (String)it.next();
            WizardStep step = (WizardStep)wizardSteps.get(key);

            if (step.getOrder() == order) {
                return step.getMapKey();
            }

        }
        return null;

    }


    public String getNextStep(String stepKey) {
        return getAvailableNextStep(stepKey);
        //WizardStep step=(WizardStep)getWizardBar().getWizardSteps().get(stepKey);
        //return getStepByOrder(step.getOrder()+1);

    }

    public String getPreviousStep(String stepKey) {
        return getAvailablePreviousStep(stepKey);
        //      WizardStep step=(WizardStep)getWizardBar().getWizardSteps().get(stepKey);
        //      return getStepByOrder(step.getOrder()-1);

    }

    /**
     * Purpose: this method created to  return the list of the bar mandatory steps
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public List<WizardStep> getMandatroySteps() {


        List<WizardStep> mandatorySteps = new ArrayList<WizardStep>();
        Map wizardSteps = getWizardBar().getWizardSteps();
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

    public void setVisited(String mapKey) {

        WizardStep step = getWizardStep(mapKey);
        if (step != null)
            step.setVisited(true);


    }

    public void setValidated(String mapKey) {

        WizardStep step = getWizardStep(mapKey);
        if (step != null)
            step.setValidated(true);


    }

    public String getStepBeanName(String mapKey) {

        WizardStep step = getWizardStep(mapKey);
        if (step != null)
            return step.getStepBeanName();
        return null;
    }

    /**
     * Purpose: this method created to get the finish button status (the rendered attribute)
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public boolean getFinishButtonStatus(String mapKey) {
        List<WizardStep> mandatorySteps = getMandatroySteps();
        WizardStep step = getWizardStep(mapKey);
        //if the step not mandatory check the status of all the mandatory steps
        if (!step.isMandatory()) {

            for (WizardStep wStep: mandatorySteps) {

                if (!wStep.isVisited() || !wStep.isValidated())
                    return false;

            }


        } else {

            //if the step is mandatory check status of all the steps but the current steps //this will enable the finish button in the last mandatory step
            for (WizardStep wStep: mandatorySteps) {

                if ((!wStep.getMapKey().equals(mapKey)) && 
                    (!wStep.isVisited() || !wStep.isValidated()))
                    return false;

            }


        }

        return true;

    }

    /**
     * Purpose: this method created to check if the user want to override the status of the finish button         
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public int getFinishButtonOverride(String mapKey) {


        String detailBeanName = getStepBeanName(mapKey);
        Integer override = 
            (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                           detailBeanName + 
                                                                                           ".finishButtonOverride}").getValue(FacesContext.getCurrentInstance());
        return override.intValue();

    }

    /**
     * Purpose: this method created to check if the user want to override the status of the save button         
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public int getSaveButtonOverride(String mapKey) {

        String detailBeanName = getStepBeanName(mapKey);
        Integer override = 
            (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                           detailBeanName + 
                                                                                           ".saveButtonOverride}").getValue(FacesContext.getCurrentInstance());
        return override.intValue();

    }

    /**
     * Purpose: this method created to check if all the steps which this step depend on have been visited and validated or not
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public boolean isStepDependanciesResolved(String step) {
        WizardStep wStep = 
            (WizardStep)getWizardBar().getWizardSteps().get(step);
        Map dependancyMap = wStep.getDependancyMap();
        Set mapKeys = dependancyMap.keySet();
        Iterator it = mapKeys.iterator();

        while (it.hasNext()) {

            String key = (String)it.next();
            if (((Boolean)dependancyMap.get(key)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Purpose: this method created to get the next available step (will return the next available step with dependancies resolved )
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String getAvailableNextStep(String step) {

        WizardStep wStep = 
            (WizardStep)getWizardBar().getWizardSteps().get(step);
        int stepOrder = wStep.getOrder();


        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        List<WizardStep> steps = new ArrayList<WizardStep>();
        while (it.hasNext()) {
            String key = (String)it.next();

            WizardStep cstep = (WizardStep)wizardSteps.get(key);

            steps.add(cstep);


        }

        this.sort(steps, 0, steps.size() - 1);


        for (WizardStep cStep: steps) {


            if (cStep.getOrder() > stepOrder && 
                isStepDependanciesResolved(cStep.getMapKey())) {

                return cStep.getMapKey();

            }


        }

        return null;


    }

    /**
     * Purpose: this method created to return the available previous step with dependancies resolved
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation 
     * 1.4-  Description: 
     */
    public

    String getAvailablePreviousStep(String step) {

        WizardStep wStep = 
            (WizardStep)getWizardBar().getWizardSteps().get(step);
        int stepOrder = wStep.getOrder();
        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        List<WizardStep> steps = new ArrayList<WizardStep>();
        while (it.hasNext()) {
            String key = (String)it.next();

            WizardStep cstep = (WizardStep)wizardSteps.get(key);

            steps.add(cstep);


        }

        this.reverseSort(steps, 0, steps.size() - 1);


        for (WizardStep cStep: steps) {


            if (cStep.getOrder() < stepOrder && 
                isStepDependanciesResolved(cStep.getMapKey())) {

                return cStep.getMapKey();

            }


        }

        return null;


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

    public void reverseSort(List<WizardStep> l, int start, int end) {
        WizardStep swap = new WizardStep();
        if (start < end) {
            int k = findLargest(l, start + 1, end);
            WizardStep smallest = l.get(k);
            WizardStep current = l.get(start);
            if (smallest.getOrder() > current.getOrder()) {

                swap = smallest;
                l.set(k, current);
                //smallest=current;
                l.set(start, swap);
                //current=swap;

            }
            reverseSort(l, start + 1, end);
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


    public int findLargest(List<WizardStep> l, int start, int end) {
        int k = 0, index = 0;
        if (start == end)
            return end;
        index = findLargest(l, start + 1, end);
        WizardStep first = l.get(index);
        WizardStep second = l.get(start);
        if (first.getOrder() > second.getOrder()) {
            k = index;
        } else {
            k = start;
        }
        return k;
    }

    public void scrollerAction(ActionEvent ae) {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");
        //pageIndexAdd=((HtmlDataScroller)ae.getComponent()).getPageIndex();

    }

}
