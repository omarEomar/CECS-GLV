package com.beshara.jsfbase.csc.util.wizardbar2.beans;


import com.beshara.jsfbase.csc.util.wizardbar2.config.loading.ConfigLoader;
import com.beshara.jsfbase.csc.util.wizardbar2.model.NavButton;
import com.beshara.jsfbase.csc.util.wizardbar2.model.Step;
import com.beshara.jsfbase.csc.util.wizardbar2.model.Wizard;
import com.beshara.jsfbase.csc.util.wizardbar2.model.WizardBarConfig;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardState;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.component.html.ext.HtmlCommandButton;
import org.apache.myfaces.component.html.ext.HtmlPanelGrid;
import org.apache.myfaces.shared_tomahawk.taglib.UIComponentTagUtils;


public class WizardBarBean {
    private static ConfigLoader loader = null;
    HtmlPanelGrid stepsPanelGrid;
    HtmlPanelGrid buttonsPanelGrid;
    //TODO REPLACE HARDCODED ATTRIBUTES NAMES WITH THIS FINALS
    private static final String PANELGRID_DIR_ATTRIBUTE = "dir";
    private static final String BUTTON_VALUE_ATTRIBUTE = "value";
    private static final String BUTTON_STYLECLASS_ATTRIBUTE = "styleClass";
    private static final String STEP_DEFAULT_ACTION_lISTENER = 
        "#{wizardBarBean.navigate}";
    private static final String NAVTYPE_STEP = "step";
    private static final String NAVTYPE_CUSTOM = "custom";
    private static final String NEXT_ALIAS = "next";
    private static final String PREVIOUS_ALIAS = "prev";
    private static final String FINISH_ALIAS = "finish";
    private static final String CANCEL_ALIAS = "cancel";
    private static final String BUILTIN_NEXT = "#{wizardBarBean.next}";
    private static final String BUILTIN_PREVIOUS = "#{wizardBarBean.previous}";
    private static final String BUILTIN_FINISH = "#{wizardBarBean.finish}";
    private static final String BUILTIN_CANCEL = "#{wizardBarBean.back}";
    private static final String NAVIGATE_STEP_ACTIONLISTENER = 
        "#{wizardBarBean.navigateStep}";
    //  private static final boolean forceId=true;
    private static final String forceIdExpression = "#{wizardBarBean.forceId}";

    private Map requiredSteps = new LinkedHashMap();


    private boolean wizardBarInitialized = false;
    private Step currentStep;
    WizardBarConfig config;
    Wizard wizard;
    Map wizardStates = new HashMap<String, WizardState>();
    private boolean nextAvailableStepExist;
    private boolean previousAvailableStepExist;
    private boolean requiredStepsValidated;
    private String wizardId; //TODO:remove initial value
    private WizardInfo wizardInfo = new WizardInfo();

    public WizardBarBean() {

        //  initializeWizard();


    }

    public void initializeWizard() {


        loader = ConfigLoader.getInstance();
        config = loader.getWizardBarConfig();
        if (wizardId == null)
            wizardId = 
                    (String)((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getAttribute("wizardbarid");
        wizard = config.getWizardMap().get(wizardId);

    }


    public void setStepsPanelGrid(HtmlPanelGrid stepsPanelGrid) {
        this.stepsPanelGrid = stepsPanelGrid;
    }

    public HtmlPanelGrid getStepsPanelGrid() {
        //TODO : each time will rebuild the panel
        // if (stepsPanelGrid == null)
        constructStepsPanelGrid();
        return stepsPanelGrid;
    }

    public void setButtonsPanelGrid(HtmlPanelGrid buttonsPanelGrid) {
        this.buttonsPanelGrid = buttonsPanelGrid;
    }

    public HtmlPanelGrid getButtonsPanelGrid() {

        //  if (buttonsPanelGrid == null)
        constructButtonsPanelGrid();
        return buttonsPanelGrid;
    }

    public boolean isForceId() {
        return true;
    }

    private void constructStepsPanelGrid() {


        initializeWizard();
        stepsPanelGrid = new HtmlPanelGrid();

        // the direction attribute 
        if (wizard != null) {
            String dir = wizard.getDir();
            if (dir == null)
                dir = config.getDir();

            if (dir != null)
                if (isValueReference(config.getDir())) {

                    stepsPanelGrid.setValueBinding(PANELGRID_DIR_ATTRIBUTE, 
                                                   createValueBinding(dir));

                } else {

                    stepsPanelGrid.setDir(dir);
                }


            if (wizard.getRendered() != null)
                if (isValueReference(wizard.getRendered())) {

                    stepsPanelGrid.setValueBinding("rendered", 
                                                   createValueBinding(wizard.getRendered()));
                } else {

                    stepsPanelGrid.setRendered(Boolean.valueOf(wizard.getRendered()));

                }
            //constructing steps buttons    

            stepsPanelGrid.getChildren().clear();
            Map wizardSteps = wizard.getStepMap();


            Set mapKeys = wizardSteps.keySet();
            Iterator it = mapKeys.iterator();

            // Ashraf Gaber 14012014, applying the new layout
            //stepsPanelGrid.setColumns(mapKeys.size());
            stepsPanelGrid.setColumns(1);

            while (it.hasNext()) {


                String key = (String)it.next();
                System.out.println("this is the key------------" + key);
                Step step = (Step)wizardSteps.get(key);

                //first time constructing the wizard bar
                //TODO : REMOVE THIS 3AK
                if (!isWizardBarInitialized()) {

                    setCurrentStep((Step)wizardSteps.get(key));
                    setWizardBarInitialized(true);

                    if (wizardStates.get(currentStep.getId()) == null) {
                        wizardStates.put(currentStep.getId(), 
                                         new WizardState());
                    }

                    WizardState currentStepState = 
                        (WizardState)wizardStates.get(currentStep.getId());

                    //required steps

                    currentStepState.setVisited(true);

                }

                constructStep(step);

            }
        }

    }


    private void constructStep(Step step) {

        HtmlCommandButton button = new HtmlCommandButton();


        button.setId(step.getId());
        // value attribute

        if (step.getLabel() != null)
            if (isValueReference(step.getLabel())) {

                button.setValueBinding("value", 
                                       createValueBinding(step.getLabel()));
            } else {

                button.setValue(step.getLabel());

            }

        //title attribute
        if (step.getTitle() != null)
            if (isValueReference(step.getTitle())) {

                button.setValueBinding("title", 
                                       createValueBinding(step.getTitle()));
            } else {

                button.setTitle(step.getTitle());

            }

        //rendered attribute
        if (step.getRendered() != null)
            if (isValueReference(step.getRendered())) {

                button.setValueBinding("rendered", 
                                       createValueBinding(step.getRendered()));
            } else {

                button.setRendered(Boolean.valueOf(step.getRendered()));

            }

        //disabled attribute
        if (step.getDisabled() != null)
            if (isValueReference(step.getDisabled())) {

                button.setValueBinding("disabled", 
                                       createValueBinding(step.getDisabled()));
            } else {

                button.setDisabled(Boolean.valueOf(step.getDisabled()));

            }


        //onclick attribute
        if (step.getOnClick() != null)
            if (isValueReference(step.getOnClick())) {

                button.setValueBinding("onclick", 
                                       createValueBinding(step.getOnClick()));
            } else {

                button.setOnclick(step.getOnClick());

            }


        if (step.getOnmousedown() != null)
            if (isValueReference(step.getOnmousedown())) {

                button.setValueBinding("onmousedown", 
                                       createValueBinding(step.getOnmousedown()));
            } else {

                button.setOnmousedown(step.getOnmousedown());

            }

        if (step.getOnblur() != null)
            if (isValueReference(step.getOnblur())) {

                button.setValueBinding("onblur", 
                                       createValueBinding(step.getOnblur()));
            } else {

                button.setOnblur(step.getOnblur());

            }


        //set default actionlistener attribute 

        MethodBinding methodBinding = createStepDefaultActionListener();
        button.setActionListener(methodBinding);


        //style sheet attribute
        //TODO : COMPLETE
        // current Step
        if (step.getId() == currentStep.getId()) {

            String currentStyleClass = step.getCurrentStyleClass();
            if (currentStyleClass == null)
                currentStyleClass = wizard.getCurrentStyleClass();
            if (currentStyleClass == null)
                currentStyleClass = config.getCurrentStyleClass();
            if (currentStyleClass != null)
                if (isValueReference(currentStyleClass)) {

                    button.setValueBinding("styleClass", 
                                           createValueBinding(currentStyleClass));

                } else {

                    button.setStyleClass(currentStyleClass);

                }

        } else {

            //visited and validated Step
            WizardState stepState = 
                (WizardState)wizardStates.get(step.getId());
            if (stepState != null && stepState.isValidated() && 
                stepState.isVisited()) {
                String visitedStyleClass = step.getVisitedStyleClass();
                if (visitedStyleClass == null)
                    visitedStyleClass = wizard.getVisitedStyleClass();
                if (visitedStyleClass == null)
                    visitedStyleClass = config.getVisitedStyleClass();
                if (visitedStyleClass != null)
                    if (isValueReference(visitedStyleClass)) {

                        button.setValueBinding("styleClass", 
                                               createValueBinding(visitedStyleClass));

                    } else {

                        button.setStyleClass(visitedStyleClass);

                    }

            } else {
                //not visited step    
                String notVisitedStyleClass = step.getNotVisitedStyleClass();
                if (notVisitedStyleClass == null)
                    notVisitedStyleClass = wizard.getNotVisitedStyleClass();
                if (notVisitedStyleClass == null)
                    notVisitedStyleClass = config.getNotVisitedStyleClass();
                if (notVisitedStyleClass != null)
                    if (isValueReference(notVisitedStyleClass)) {

                        button.setValueBinding("styleClass", 
                                               createValueBinding(notVisitedStyleClass));

                    } else {

                        button.setStyleClass(notVisitedStyleClass);

                    }

            }


        }


        //TODO

        stepsPanelGrid.getChildren().add(button);

    }

    private MethodBinding createStepDefaultActionListener() {
        MethodBinding methodBinding = 
            createActionListener(STEP_DEFAULT_ACTION_lISTENER);
        return methodBinding;
    }

    private MethodBinding createActionListener(String binding) {
        MethodBinding methodBinding = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding(binding, 
                                                                                   new Class[] { ActionEvent.class });
        return methodBinding;
    }

    private void constructButtonsPanelGrid() {


        buttonsPanelGrid = new HtmlPanelGrid();

        // the direction attribute 

        if (wizard != null) {


            String dir = wizard.getDir();
            if (dir == null)
                dir = config.getDir();


            if (isValueReference(config.getDir())) {

                buttonsPanelGrid.setValueBinding(PANELGRID_DIR_ATTRIBUTE, 
                                                 createValueBinding(dir));

            } else {

                buttonsPanelGrid.setDir(dir);
            }

         if(wizard.getNavBar()==null)
         return ;

            if (wizard.getNavBar().getRendered() != null)
                if (isValueReference(wizard.getNavBar().getRendered())) {

                    buttonsPanelGrid.setValueBinding("rendered", 
                                                     createValueBinding(wizard.getNavBar().getRendered()));
                } else {

                    buttonsPanelGrid.setRendered(Boolean.valueOf(wizard.getNavBar().getRendered()));

                }

            //constructing nav  buttons    

            buttonsPanelGrid.getChildren().clear();
            Map wizardButtons = wizard.getNavBar().getNavBarButtons();


            Set mapKeys = wizardButtons.keySet();
            Iterator it = mapKeys.iterator();


            buttonsPanelGrid.setColumns(mapKeys.size());

            while (it.hasNext()) {


                String key = (String)it.next();
                System.out.println("this is the button key------------" + key);
                NavButton button = (NavButton)wizardButtons.get(key);
                constructNavButton(button);


            }
        }

    }

    private boolean isValueReference(String value) {
        return UIComponentTagUtils.isValueReference(value);
    }

    private ValueBinding createValueBinding(String expression) {
        return FacesContext.getCurrentInstance().getApplication().createValueBinding(expression);
    }

    private MethodBinding createMethodBinding(String expression) {
        return FacesContext.getCurrentInstance().getApplication().createMethodBinding(expression, 
                                                                                      null);
    }

    /*wizard bar button defualt actions */

    public String next() {
        navigateNextAvailableStep();
        return null;
    }


    public String previous() {
        navigatePreviousAvailableStep();
        System.out.print("previous");
        return null;
    }


    public String back() {

        //  if (validateWizardStep(currentStep)) {

        //execute default buttons listener
        //execute registered default buttons navigation Listener 
        wizardInfo.setCurrentStep(getCurrentStep().getId());
        wizardInfo.setDefaultActionName(CANCEL_ALIAS);
        executeNavigationListener(wizard.getDefaultButtonsNavigationListener(), 
                                  wizardInfo);
        ////////////////////////////////////////////
        if (wizard.getOnCancel() != null) {
            String navCase = 
                (String)executeMethodBinding(wizard.getOnCancel());
            System.out.print("back");
            if (navCase != null && !navCase.equals(""))
                return navCase;
            else
                return wizard.getBackNavigation();

        }
        // }
        return wizard.getBackNavigation();
    }

    public String finish() {

        if (validateWizardStep(currentStep) && validateRequiredSteps()) {

            // execute default button listener 

            //execute default buttons listener
            //execute registered default buttons navigation Listener 
            wizardInfo.setCurrentStep(getCurrentStep().getId());
            wizardInfo.setDefaultActionName(FINISH_ALIAS);
            executeNavigationListener(wizard.getDefaultButtonsNavigationListener(), 
                                      wizardInfo);
            ////////////////////////////////////////////
            System.out.print("cancel");
            if (wizard.getOnFinish() != null) {
                String navCase = 
                    (String)executeMethodBinding(wizard.getOnFinish());
                if (navCase != null && !navCase.equals("")) {
                    return navCase;


                } else {
                    return wizard.getBackNavigation();
                }
            }

        }
        return null;
    }

    public void setWizardBarInitialized(boolean wizardBarInitialized) {
        this.wizardBarInitialized = wizardBarInitialized;
    }

    public boolean isWizardBarInitialized() {
        return wizardBarInitialized;
    }

    public void setCurrentStep(Step currentStep) {
        this.currentStep = currentStep;
    }

    public Step getCurrentStep() {
        return currentStep;
    }


    public void navigate(javax.faces.event.ActionEvent actionEvent) {

        String stepKey = actionEvent.getComponent().getId();
        Step step = wizard.getStepMap().get(stepKey);

        //update the state map
        if (wizardStates.get(stepKey) == null) {
            wizardStates.put(stepKey, new WizardState());
        }

        if (wizardStates.get(currentStep.getId()) == null) {
            wizardStates.put(currentStep.getId(), new WizardState());
        }

        WizardState currentStepState = 
            (WizardState)wizardStates.get(currentStep.getId());
        WizardState targetStepState = 
            (WizardState)wizardStates.get(step.getId());

        if (validateWizardStep(currentStep)) {

            //execute registered steps navigation Listener 
            wizardInfo.setCurrentStep(getCurrentStep().getId());
            wizardInfo.setTargetStep(step.getId());
            executeNavigationListener(wizard.getSeptsNavigationListener(), 
                                      wizardInfo);
            ////////////////////////////////////////////


            currentStepState.setValidated(true);
            setCurrentStep(step);
            targetStepState.setVisited(true);
            String nCase = step.getNavigation();
            if (nCase != null)
                handleNavigation(nCase);
        }


    }

    private void executeNavigationListener(String navigationListernerBindinig, 
                                           WizardInfo wizardInfo) {


        if (navigationListernerBindinig != null) {
            MethodBinding methodBinding = null;
            methodBinding = 
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding(navigationListernerBindinig, 
                                                                                           new Class[] { WizardInfo.class });


            methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                 new Object[] { wizardInfo });

        }
    }

    public void handleNavigation(String nCase) {
        if(nCase.startsWith("#")){
        ValueBinding vb = FacesContext.getCurrentInstance().getApplication().createValueBinding(nCase);
        String NavigationCase = (String)vb.getValue(FacesContext.getCurrentInstance());
        nCase = NavigationCase;
        System.out.println("NavigationCase :::: " +NavigationCase);
        }
        NavigationHandler nHandler = 
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nHandler.handleNavigation(FacesContext.getCurrentInstance(), null, 
                                  nCase);

    }


    public boolean validateWizardStep(Step step) {
        Boolean isValid = true;

        if (step.getOnValidate() != null) {

            MethodBinding validateMethod = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding(step.getOnValidate(), 
                                                                                       null);
            isValid = 
                    (Boolean)validateMethod.invoke(FacesContext.getCurrentInstance(), 
                                                   null);
        }

        return isValid;

    }

    public void setWizardStates(Map wizardStates) {
        this.wizardStates = wizardStates;
    }

    public Map getWizardStates() {
        return wizardStates;
    }


    private void constructNavButton(NavButton navButton) {

        HtmlCommandButton button = new HtmlCommandButton();

        button.setId(navButton.getId());
        button.setValueBinding("forceId", 
                               createValueBinding(forceIdExpression));
        // value attribute

        if (navButton.getLabel() != null)
            if (isValueReference(navButton.getLabel())) {

                button.setValueBinding("value", 
                                       createValueBinding(navButton.getLabel()));
            } else {

                button.setValue(navButton.getLabel());

            }

        //title attribute
        if (navButton.getTitle() != null)
            if (isValueReference(navButton.getTitle())) {

                button.setValueBinding("title", 
                                       createValueBinding(navButton.getTitle()));
            } else {

                button.setTitle(navButton.getTitle());

            }

        //rendered attribute
        if (navButton.getRendered() != null)
            if (isValueReference(navButton.getRendered())) {

                button.setValueBinding("rendered", 
                                       createValueBinding(navButton.getRendered()));
            } else {

                button.setRendered(Boolean.valueOf(navButton.getRendered()));

            }

        //disabled attribute
        if (navButton.getDisabled() != null)
            if (isValueReference(navButton.getDisabled())) {

                button.setValueBinding("disabled", 
                                       createValueBinding(navButton.getDisabled()));
            } else {

                button.setDisabled(Boolean.valueOf(navButton.getDisabled()));

            }

        //onclick attribute
        if (navButton.getOnClick() != null)
            if (isValueReference(navButton.getOnClick())) {
                button.setValueBinding("onclick", 
                                       createValueBinding(navButton.getOnClick()));
            } else {

                button.setOnclick(navButton.getOnClick());

            }


        if (navButton.getOnmousedown() != null)
            if (isValueReference(navButton.getOnmousedown())) {

                button.setValueBinding("onmousedown", 
                                       createValueBinding(navButton.getOnmousedown()));
            } else {

                button.setOnmousedown(navButton.getOnmousedown());

            }

        if (navButton.getOnblur() != null)
            if (isValueReference(navButton.getOnblur())) {

                button.setValueBinding("onblur", 
                                       createValueBinding(navButton.getOnblur()));
            } else {

                button.setOnblur(navButton.getOnblur());

            }

        String styleClass = navButton.getStyleClass();
        if (styleClass != null)
            if (isValueReference(styleClass)) {

                button.setValueBinding("styleClass", 
                                       createValueBinding(styleClass));

            } else {

                button.setStyleClass(styleClass);

            }

        //set default actionlistener attribute 
        if (navButton.getNavType() != null && 
            navButton.getNavigation() != null)
            if (navButton.getNavType().equals(NAVTYPE_STEP)) {


                MethodBinding methodBinding;

                // if in the wizard steps 
                if (wizard.getStepMap().get(navButton.getNavigation()) != 
                    null) {


                    methodBinding = 
                            createActionListener(NAVIGATE_STEP_ACTIONLISTENER);
                    button.setActionListener(methodBinding);

                } else { // if cutsom nex,prev,finish,cancel

                    methodBinding = 
                            createButtonBuiltInActionListener(navButton.getNavigation());
                    button.setAction(methodBinding);

                }


            } else {


                if (isValueReference(navButton.getNavigation())) {
                    MethodBinding methodBinding;
                    methodBinding = 
                            createMethodBinding(navButton.getNavigation());
                    button.setAction(methodBinding);
                } else {
                    //  throw 
                    button.setActionFor(navButton.getNavigation());
                    System.out.print("ssss"); //   button.setAction(navButton.getNavigation());

                }

            }


        buttonsPanelGrid.getChildren().add(button);


    }

    private MethodBinding createButtonBuiltInActionListener(String builtInAction) {
        String action = null;

        if (builtInAction.equals(NEXT_ALIAS)) {

            action = BUILTIN_NEXT;

        }
        if (builtInAction.equals(CANCEL_ALIAS)) {

            action = BUILTIN_CANCEL;

        }
        if (builtInAction.equals(PREVIOUS_ALIAS)) {

            action = BUILTIN_PREVIOUS;

        }
        if (builtInAction.equals(FINISH_ALIAS)) {

            action = BUILTIN_FINISH;

        }


        return createMethodBinding(action);

    }


    private void navigateNextAvailableStep() {


        if (validateWizardStep(currentStep)) {

            Step nextStep = getNextAvailableStep();
            //update current step state and next step state

            if (wizardStates.get(currentStep.getId()) == null) {
                wizardStates.put(currentStep.getId(), new WizardState());
            }

            WizardState currentStepState = 
                (WizardState)wizardStates.get(currentStep.getId());
            currentStepState.setValidated(true);

            if (nextStep != null) {


                //execute registered default buttons navigation Listener 
                wizardInfo.setCurrentStep(getCurrentStep().getId());
                wizardInfo.setTargetStep(nextStep.getId());
                wizardInfo.setDefaultActionName(NEXT_ALIAS);
                executeNavigationListener(wizard.getDefaultButtonsNavigationListener(), 
                                          wizardInfo);
                ////////////////////////////////////////////

                //update wizard state
                setCurrentStep(nextStep);
                if (wizardStates.get(nextStep.getId()) == null) {
                    wizardStates.put(nextStep.getId(), new WizardState());
                }

                WizardState nextStepState = 
                    (WizardState)wizardStates.get(nextStep.getId());
                nextStepState.setVisited(true);


                handleNavigation(nextStep.getNavigation());


            }

        }


    }

    private Step getNextAvailableStep() {
        Map wizardSteps = wizard.getStepMap();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();
        Step nextStep = null;
        boolean isNext = false;

        while (it.hasNext()) {

            String key = (String)it.next();
            Step step = (Step)wizardSteps.get(key);

            if (step.getId().equals(currentStep.getId())) {

                isNext = true;
                continue;

            }

            if (isNext && isStepRendered(step) && isStepEnabled(step)) {

                nextStep = step;
                break;
            }

        }
        return nextStep;
    }


    private void navigatePreviousAvailableStep() {


        if (validateWizardStep(currentStep)) {

            Step previousStep = getPreviousAvailableStep();
            //update current step state and next step state

            if (wizardStates.get(currentStep.getId()) == null) {
                wizardStates.put(currentStep.getId(), new WizardState());
            }

            WizardState currentStepState = 
                (WizardState)wizardStates.get(currentStep.getId());
            currentStepState.setValidated(true);

            if (previousStep != null) {

                //execute default buttons listener
                //execute registered default buttons navigation Listener 
                wizardInfo.setCurrentStep(getCurrentStep().getId());
                wizardInfo.setTargetStep(previousStep.getId());
                wizardInfo.setDefaultActionName(PREVIOUS_ALIAS);
                executeNavigationListener(wizard.getDefaultButtonsNavigationListener(), 
                                          wizardInfo);
                ////////////////////////////////////////////


                //update wizard steps
                setCurrentStep(previousStep);
                if (wizardStates.get(previousStep.getId()) == null) {
                    wizardStates.put(previousStep.getId(), new WizardState());
                }

                WizardState previousStepState = 
                    (WizardState)wizardStates.get(previousStep.getId());
                previousStepState.setVisited(true);


                handleNavigation(previousStep.getNavigation());


            }

        }


    }


    private Step getPreviousAvailableStep() {
        Map wizardSteps = wizard.getStepMap();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();
        Step previousStep = null;

        while (it.hasNext()) {

            String key = (String)it.next();
            Step step = (Step)wizardSteps.get(key);
            if (step.getId().equals(currentStep.getId())) {
                break;
            }

            if (isStepRendered(step) && isStepEnabled(step)) {
                previousStep = step;
            }

        }
        return previousStep;
    }


    private boolean isStepRendered(Step step) {

        //rendered attribute
        if (step.getRendered() != null)
            if (step.getRendered() != null)
                if (isValueReference(step.getRendered())) {

                    return (Boolean)evaluateValueBinding(step.getRendered());

                } else {

                    return Boolean.valueOf(step.getRendered());

                }

        return true;

    }


    private boolean isStepEnabled(Step step) {

        //disabled attribute
        if (step.getDisabled() != null)
            if (step.getDisabled() != null)

                if (isValueReference(step.getDisabled())) {

                    return !(Boolean)evaluateValueBinding(step.getDisabled());

                } else {

                    return !Boolean.valueOf(step.getDisabled());

                }
        return true;

    }

    private Object evaluateValueBinding(String valueBindingExpression) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return app.createValueBinding(valueBindingExpression).getValue(ctx);

    }


    public void setNextAvailableStepExist(boolean nextAvailableStepExist) {

        this.nextAvailableStepExist = nextAvailableStepExist;

    }

    public boolean isNextAvailableStepExist() {
        if (getNextAvailableStep() != null)
            return true;
        return nextAvailableStepExist;
    }

    public void setPreviousAvailableStepExist(boolean previousAvailableStepExist) {
        this.previousAvailableStepExist = previousAvailableStepExist;
    }

    public boolean isPreviousAvailableStepExist() {
        if (getPreviousAvailableStep() != null)
            return true;
        return previousAvailableStepExist;
    }


    public Object executeMethodBinding(String methodBindingExepression) {

        MethodBinding methodBinding = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding(methodBindingExepression, 
                                                                                   null);
        return methodBinding.invoke(FacesContext.getCurrentInstance(), null);

    }


    public void setRequiredStepsValidated(boolean requiredStepsValidated) {
        this.requiredStepsValidated = requiredStepsValidated;
    }

    public boolean isRequiredStepsValidated() {

        return validateRequiredSteps();
    }


    private boolean validateRequiredSteps() {

        //TODO : IF the currentstep is the only required step enable finish or if the current step is the last step
        Map wizardSteps = wizard.getStepMap();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();
        while (it.hasNext()) {

            String key = (String)it.next();
            Step step = (Step)wizardSteps.get(key);
            boolean required = false;
            if (step.getRequired() != null) {


                if (isValueReference(step.getRequired())) {

                    required = 
                            (Boolean)evaluateValueBinding(step.getRequired());

                } else {

                    required = Boolean.valueOf(step.getRequired());

                }

                if (required && !validateWizardStep(step)) {
                    return false;
                }

            }

        }
        return true;
    }


    public void setRequiredSteps(Map requiredSteps) {
        this.requiredSteps = requiredSteps;
    }

    public Map getRequiredSteps() {
        return requiredSteps;
    }


    private void navigateStep(Step step) {


        if (validateWizardStep(currentStep) && isStepEnabled(step) && 
            isStepRendered(step)) {


            if (wizardStates.get(currentStep.getId()) == null) {
                wizardStates.put(currentStep.getId(), new WizardState());
            }

            WizardState currentStepState = 
                (WizardState)wizardStates.get(currentStep.getId());
            currentStepState.setValidated(true);

            if (step != null) {

                setCurrentStep(step);
                if (wizardStates.get(step.getId()) == null) {
                    wizardStates.put(step.getId(), new WizardState());
                }

                WizardState previousStepState = 
                    (WizardState)wizardStates.get(step.getId());
                previousStepState.setVisited(true);


                handleNavigation(step.getNavigation());


            }

        }


    }

    public void navigateStep(javax.faces.event.ActionEvent actionEvent) {

        String buttonId = actionEvent.getComponent().getId();
        NavButton button = wizard.getNavBar().getNavBarButtons().get(buttonId);
        String stepKey = button.getNavigation();
        navigateStep(wizard.getStepMap().get(stepKey));

    }

    public void externalStepNavigate(String stepKey, String WizardId) {

        setWizardId(WizardId);
        initializeWizard();
        setWizardBarInitialized(true);
        Step step = wizard.getStepMap().get(stepKey);


        if (step != null && isStepEnabled(step) && isStepRendered(step)) {


            if (step != null) {

                setCurrentStep(step);
                if (wizardStates.get(step.getId()) == null) {
                    wizardStates.put(step.getId(), new WizardState());
                }
                if (wizardStates.get(step.getId()) == null) {
                    wizardStates.put(step.getId(), new WizardState());
                }


                handleNavigation(step.getNavigation());


            }

        }


    }

    public void setWizardId(String wizardId) {
        this.wizardId = wizardId;
        initializeWizard();
    }

    public String getWizardId() {
        return wizardId;
    }
}
