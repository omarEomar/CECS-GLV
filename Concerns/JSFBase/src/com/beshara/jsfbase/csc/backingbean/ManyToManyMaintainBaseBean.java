package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.locking.dto.ILockableItem;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;
import com.beshara.jsfbase.csc.util.wizardbar.WizardStep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class ManyToManyMaintainBaseBean extends ManyToManyBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public static final int MAINTAIN_MODE_ADD = 0;
    public static final int MAINTAIN_MODE_EDIT = 1;
    public static final int MAINTAIN_MODE_VIEW_ONLY = 2;
    public static final int MAINTAIN_MODE_CLONE = 3;
    
    private String nextNavigationCase;
    private String previousNavigationCase;
    private String finishNavigationCase;
    private String currentNavigationCase;
    private String currentStep;
    private int maintainMode; //0 add 1 edit 2 view only
    private boolean renderSave = false; //just for test the default is true
    private boolean renderFinish = true;
    private IBasicDTO masterDTO;
    private boolean showPrevious;
    private int pageIndexAdd = 0;
    private transient HtmlDataScroller dataScroller = new HtmlDataScroller();
    private String currentStepJSValidation;

    public ManyToManyMaintainBaseBean() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = 
                (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);


    }

    public void setFrm(HtmlForm frm) {

        this.frm = frm;
    }

    //        public HtmlForm getFrm() {
    //        if(this.appMainLayoutBuilder() != null)
    //                    setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
    //                    
    //          return frm;
    //        }

    public HtmlForm getFrm() {

        if (getUsingPortal()) {
            if (this.appMainLayoutPortalBuilder() != null)
                setCurrentApplictionMainLayoutPortal(this.appMainLayoutPortalBuilder());
        } else {
            if (this.appMainLayoutBuilder() != null)
                setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
        }
        this.initiateBeanOnce();
        return frm;
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.showAddeditPage();
        app.setShowContent1(true);
        app.setShowbar(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowpaging(false);
        app.setShowSearch(false);
        app.setShowdatatableContent(false);
        return app;
    }
    
    public boolean isAddMode(){
        return maintainMode == MAINTAIN_MODE_ADD;
    }
    
    public boolean isEditMode(){
        return maintainMode == MAINTAIN_MODE_EDIT;
    }
    
    public boolean isViewOnlyMode(){
        return maintainMode == MAINTAIN_MODE_VIEW_ONLY;
    }
    
    public boolean isCloneMode(){
        return maintainMode == MAINTAIN_MODE_CLONE;
    }

    //handle navigation in codename div

    //     public void scrollerAction(ActionEvent ae) {
    //       
    //         setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusOnlyOnElement('searchText')");
    //         //pageIndexAdd=((HtmlDataScroller)ae.getComponent()).getPageIndex();
    //          
    //     }

    public void changePageIndex(ValueChangeEvent event) throws Exception {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");

    }


    public void navigate(javax.faces.event.ActionEvent actionEvent) {

        String stepKey = actionEvent.getComponent().getId();
        this.updateStepDependancies(currentStep);
        String nCase = null;
        if (validateStep(currentStep, stepKey) && 
            checkStepRelevants(stepKey)) {
            //added for finish button status
            setValidated(currentStep);
            setVisited(currentStep);
            setVisited(stepKey);
            //set the finish button status
            setCurrentStep(stepKey);
            getWizardBar().setCurrentStep(stepKey);
            if (getFinishButtonOverride(currentStep) == 1) {
                setRenderFinish(getFinishButtonStatus(stepKey));
            } else if (getFinishButtonOverride(currentStep) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            //set the save button status


            //
            nCase = getNavigationCase(stepKey);
            setNextNavigationCase(getNextNavigationCase(stepKey));
            setPreviousNavigationCase(getPreviousNavigationCase(stepKey));
//            System.out.println(stepKey);
        }
        if (nCase != null)
            handleNavigation(nCase);


    }

    public void handleNavigation(String nCase) {

        NavigationHandler nHandler = 
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nHandler.handleNavigation(FacesContext.getCurrentInstance(), null, 
                                  nCase);

    }
    
    public String next() {
       // nextNavigationCase      "workcenterextradatavaluesaddedit"      String
//        System.out.println("presentation:Before:ManyToManyMaintainBaseBean. next()"+nextNavigationCase+new Date(System.currentTimeMillis()).toString());
        String nCase = nextNavigationCase;
        //
        this.updateStepDependancies(currentStep);
        nCase = getNavigationCase(this.getAvailableNextStep(currentStep));

        //
        String targetStep = getNextStep(currentStep);
        if (targetStep != null)
            if (validateStep(currentStep, targetStep) && 
                checkStepRelevants(targetStep)) {

                setNextNavigationCase(getNextNavigationCase(getNextStep(currentStep)));
                setPreviousNavigationCase(getNavigationCase(currentStep));

                // setNextNavigationCase(getNavigationCase(this.getAvailableNextStep(targetStep)));
                // setPreviousNavigationCase(getNavigationCase(this.getAvailablePreviousStep(targetStep)));


                setValidated(currentStep);
                setVisited(currentStep);
                setVisited(getNextStep(currentStep));

                if (getFinishButtonOverride(getNextStep(currentStep)) == 1) {
                    setRenderFinish(getFinishButtonStatus(getNextStep(currentStep)));
                } else if (getFinishButtonOverride(getNextStep(currentStep)) == 
                           2) {
                    setRenderFinish(false);
                } else {
                    setRenderFinish(true);
                }


                setCurrentStep(getNextStep(currentStep));
                getWizardBar().setCurrentStep(currentStep);
//                System.out.println("presentation:After:ManyToManyMaintainBaseBean. next()"+nextNavigationCase+new Date(System.currentTimeMillis()).toString());
                
                return nCase;
            }

//        System.out.println("presentation:After:ManyToManyMaintainBaseBean. next()"+nextNavigationCase+new Date(System.currentTimeMillis()).toString());
        
        return null;
    }


    public String goToStep(String targetStep) {

        String nCase = getNavigationCase(targetStep);
        this.updateStepDependancies(currentStep);
        if (validateStep(currentStep, targetStep) && 
            checkStepRelevants(targetStep)) {

            setNextNavigationCase(getNextNavigationCase(targetStep));
            setPreviousNavigationCase(getNavigationCase(currentStep));
            setValidated(currentStep);
            setVisited(currentStep);
            setVisited(targetStep);

            if (getFinishButtonOverride(targetStep) == 1) {
                setRenderFinish(getFinishButtonStatus(targetStep));
            } else if (getFinishButtonOverride(targetStep) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }


            setCurrentStep(targetStep);
            getWizardBar().setCurrentStep(targetStep);

            return nCase;
        }


        return null;

    }


    public String previous() {

//        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
        
        String nCase = previousNavigationCase;
        this.updateStepDependancies(currentStep);
        //
        nCase = getNavigationCase(this.getAvailablePreviousStep(currentStep));
        //

        String targetStep = getPreviousStep(currentStep);
        if (targetStep != null)
            if (validateStep(currentStep, targetStep) && 
                checkStepRelevants(targetStep)) {

                setNextNavigationCase(getNavigationCase(currentStep));
                setPreviousNavigationCase(getPreviousNavigationCase(getPreviousStep(currentStep)));

                // setNextNavigationCase(getNavigationCase(this.getAvailableNextStep(targetStep)));
                // setPreviousNavigationCase(getNavigationCase(this.getAvailablePreviousStep(targetStep)));


                setVisited(getPreviousStep(currentStep));
                setValidated(currentStep);
                setVisited(currentStep);
                if (getFinishButtonOverride(getPreviousStep(currentStep)) == 
                    1) {
                    setRenderFinish(getFinishButtonStatus(getPreviousStep(currentStep)));
                } else if (getFinishButtonOverride(getPreviousStep(currentStep)) == 
                           2) {
                    setRenderFinish(false);
                } else {
                    setRenderFinish(true);
                }


                setCurrentStep(getPreviousStep(currentStep));
                getWizardBar().setCurrentStep(currentStep);
//                System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
                
                return nCase;
            }
//        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
        
        return null;
    }

    public String finish() throws DataBaseException, ItemNotFoundException, 
                                  SharedApplicationException {
         try {
            if (getFinishButtonOverride(currentStep) == 1) {
                setRenderFinish(getFinishButtonStatus(currentStep));
            } else if (getFinishButtonOverride(currentStep) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            if (validateStep(currentStep, null)) {
                if (this.getMaintainMode() == 0) {
                    this.add();
                } else if (this.getMaintainMode() == 1) {
                    //TODO locking code
                    // return to the listing page in case of the item 
                    // lock was removed from the locking server
                    if (!insureLocked()) {
                         
                        return getFinishNavigationCase();
                    }

                    this.edit();
                    //   setPageDTO(super.getClient().getById(getPageDTO().getCode()));
                }

                updateStepData(currentStep);

                //TODO locking code
                unlock();
                 
                return getFinishNavigationCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }


    public void updateStepData(String stepKey) {
        String stepBeanName = getStepBeanName(stepKey);
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                              stepBeanName + 
                                                                              "}").setValue(FacesContext.getCurrentInstance(), 
                                                                                            null);


    }


    public void saveObject() throws DataBaseException, ItemNotFoundException, 
                                    SharedApplicationException {
        if (getFinishButtonOverride(currentStep) == 1) {
            setRenderFinish(getFinishButtonStatus(currentStep));
        } else if (getFinishButtonOverride(currentStep) == 2) {
            setRenderFinish(false);
        } else {
            setRenderFinish(true);
        }


        setRenderFinish(getFinishButtonStatus(currentStep));
        if (validateStep(currentStep, null)) {
            if (maintainMode == 0) {
                this.add();
            }

            if (maintainMode == 1) {

                this.edit();
                //  setPageDTO(super.getClient().getById(getPageDTO().getCode()));

            }
            updateStepData(currentStep);
        }
    }


    public boolean validateStep(String stepKey, String targetStep) {

        boolean valid = true;
        String detailBeanName = getStepBeanName(stepKey);
        //   if(isMandatory(stepKey)){


        MethodBinding validateMethod = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                   detailBeanName + 
                                                                                   ".validate}", 
                                                                                   null);
        Boolean isValid = 
            (Boolean)validateMethod.invoke(FacesContext.getCurrentInstance(), 
                                           null);
        if (isValid != null)
            valid = valid && isValid.booleanValue();


        // }

        if (isDependant(targetStep, stepKey)) {

            MethodBinding methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       detailBeanName + 
                                                                                       ".validateTarget" + 
                                                                                       "}", 
                                                                                       new Class[] { String.class });

            Boolean isValid2 = 
                (Boolean)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                              new Object[] { targetStep });
            valid = valid && isValid2.booleanValue();

        }


        return valid;

    }


    public void setNextNavigationCase(String nextNavigationCase) {
        this.nextNavigationCase = nextNavigationCase;
    }

    public String getNextNavigationCase() {
        return nextNavigationCase;
    }

    public void setPreviousNavigationCase(String previousNavigationCase) {
        this.previousNavigationCase = previousNavigationCase;
    }

    public String getPreviousNavigationCase() {
        return previousNavigationCase;
    }

    public void setFinishNavigationCase(String finishNavigationCase) {
        this.finishNavigationCase = finishNavigationCase;
    }

    public String getFinishNavigationCase() {
        return finishNavigationCase;
    }

    public void setCurrentNavigationCase(String currentNavigationCase) {
        this.currentNavigationCase = currentNavigationCase;
    }

    public String getCurrentNavigationCase() {
        return currentNavigationCase;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setMaintainMode(int maintainMode) {
        this.maintainMode = maintainMode;
    }

    public int getMaintainMode() {
        return maintainMode;
    }

    public void setRenderSave(boolean renderSave) {
        this.renderSave = renderSave;
    }

    public boolean isRenderSave() {
        return renderSave;
    }

    public void setRenderFinish(boolean renderFinish) {
        this.renderFinish = renderFinish;
    }

    public boolean isRenderFinish() {
        if (this.maintainMode == 1)
            return true;
        return super.getFinishButtonStatus(this.getCurrentStep());
        //  return renderFinish;
    }

    public void setMasterDTO(IBasicDTO masterDTO) {
        this.masterDTO = masterDTO;
    }

    public IBasicDTO getMasterDTO() {
        return masterDTO;
    }


    public String back() {
//        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.back()"+new Date(System.currentTimeMillis()).toString());
        
        //TODO locking code
        unlock();
//        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.back()"+new Date(System.currentTimeMillis()).toString());
        
        return this.getFinishNavigationCase();

    }


    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowPrevious() {
        //if the first step don't render previous   (to be implemented for next )
        if (this.getCurrentStep() != null && this.getStepByOrder(1) != null && 
            this.getCurrentStep().equalsIgnoreCase(this.getStepByOrder(1))) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        return showPrevious;
    }

    public void setPageIndexAdd(int pageIndexAdd) {
        this.pageIndexAdd = pageIndexAdd;
    }

    public int getPageIndexAdd() {
        if (dataScroller != null) {
            pageIndexAdd = dataScroller.getPageIndex();
        }
        return pageIndexAdd;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }
    //check if the target step depend on the current step

    public boolean isDependant(String targetStep, String currentStep) {
        boolean dependant = false;
        if (targetStep != null) {
            List<String> relevantSteps = 
                getWizardStep(targetStep).getRelevantSteps();
            for (String relevant: relevantSteps) {
                if (relevant.equalsIgnoreCase(currentStep)) {
                    dependant = true;
                }

            }
        }
        return dependant;
    }

    public boolean isMandatory(String step) {

        return getWizardStep(step).isMandatory();

    }

    /**
     * Purpose: this method created to update the dependancy map of all the steps wich depend on this step 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void updateStepDependancies(String currentStep) {

        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();


        while (it.hasNext()) {
            String key = (String)it.next();
            WizardStep step = (WizardStep)wizardSteps.get(key);
            if (isDependant(key, currentStep)) {

                step.getDependancyMap().put(currentStep, 
                                            validateStep(currentStep, key));


            }


        }


    }

    /**
     * Purpose: this method created to check if all the steps that this step depend on visited and validated
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public boolean checkStepRelevants(String currentStep) {

        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();

        WizardStep step = (WizardStep)wizardSteps.get(currentStep);
        if (step != null)
            while (it.hasNext()) {
                String key = (String)it.next();

                if (isDependant(currentStep, key)) {

                    if (((Boolean)step.getDependancyMap().get(key)) == false) {
                        return false;
                    }


                }


            }


        return true;


    }

    public String getLocalizedMessage(String bundleBase, String bundleKey) {
        try {
            PropertyResourceBundle p = 
                (PropertyResourceBundle)ResourceBundle.getBundle(bundleBase, 
                                                                 this.getLocale());
            System.out.print(this.getLocale().getLanguage());
            return p.getString(bundleKey);

        } catch (Exception e) {
            ;
        }

        return "";

    }

    public Locale getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();

    }

    public void getAll() throws DataBaseException {
        // super.getAll();
    }


    public void generateMenuIndexes(ModuleObject currentObj, 
                                    String currentMenuItemIndex) {
        String ret = "";
        ModuleObject obj = null;
        List<ModuleObject> list = currentObj.getChildsList();
        if (list.size() == 0) {
            if (currentObj.getObjPage() != null) {
                allIndexes += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("1") && 
                currentObj.getObjPage() != null) {
                casesArray[1] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("2") && 
                currentObj.getObjPage() != null) {
                casesArray[1] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
                casesArray[2] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("0") && 
                currentObj.getObjPage() != null) {
                casesArray[0] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
                casesArray[1] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
                casesArray[2] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
        }


        for (int i = 0; i < list.size(); i++) {
            obj = list.get(i);
            // if( obj != null && obj.getChildsList() != null && obj.getChildsList().size() > 0 ){
            int index = i + 5;
            generateMenuIndexes(obj, currentMenuItemIndex + "-" + index);
            //}
        }
    }

    public String[] getCasesArray() {

        objects = tableHeaderBean.getObjects();

        if (casesArray == null) {
            casesArray = new String[10];
            for (int i = 0; i < casesArray.length; i++) {
                casesArray[i] = "";
            }

        }
        int countParent = -1;
        // int countChild = 4;
//        System.out.println("objects.size();  " + objects.size());
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getObjType().equals("menu")) {
                countParent++;
                generateMenuIndexes(objects.get(i), "" + countParent + "");
            }
        }
        //            System.out.println("Yassminee ");
        //            System.out.println("casesArray[0]  "+casesArray[0]);
        //            System.out.println("casesArray[1]  "+casesArray[1]);
        //            System.out.println("casesArray[3]  "+casesArray[2]);
        return casesArray;
    }

    public String[] getCasesArrayButton() {
        objects = tableHeaderBean.getObjects();
        if (casesArrayButton == null) {
            casesArrayButton = new String[10];
            for (int i = 0; i < casesArrayButton.length; i++) {
                casesArrayButton[i] = "";
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getObjType().equals("button")) { //BUTTONS
                allIndexesButton += objects.get(i).getObjCode() + ",";
                if (objects.get(i).getSelectionFlag().equals("0")) {
                    casesArrayButton[0] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[2] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("1")) {

                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("2")) {

                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[2] += objects.get(i).getObjCode() + ",";
                }
            }
        }
        //            System.out.println("casesArrayButton  "+casesArrayButton[0]);
        //            System.out.println("casesArrayButton  "+casesArrayButton[1]);
        //            System.out.println("casesArrayButton  "+casesArrayButton[2]);
        //            System.out.println("allIndexesButton  "+allIndexesButton);
        return casesArrayButton;
    }

    /**
     * Purpose: this method created to highlight the current dto in tree listing page
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date: 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void highlightTreetDTO(String backBeanName) {

        if (this.getPageDTO().getCode() != null) {

            try {

                executeMethodBindingWithParams(backBeanName + 
                                               ".highlightAddEdit", 
                                               new Object[] { backBeanName, 
                                                              (String)this.getPageDTO().getCode().getKey() }, 
                                               new Class[] { String.class, 
                                                             String.class });

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }


    /**
     * Purpose: this method created to highlight the current dto in listing page
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void highlightListDTO(String backBeanName) {

        if (this.getPageDTO().getCode() != null) {
            try {
                BaseBean listBean = 
                    (BaseBean)evaluateValueBinding(backBeanName);
                List list = 
                    (ArrayList)evaluateValueBinding(backBeanName + ".myTableData");
                listBean.getAll();
                listBean.getHighlightedDTOS().add(this.getPageDTO());
                listBean.getPageIndex((String)this.getPageDTO().getCode().getKey());

            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void setCurrentStepJSValidation(String currentStepJSValidation) {
        this.currentStepJSValidation = currentStepJSValidation;
    }

    public String getCurrentStepJSValidation() {

        String clientSideJavaScript = null;
        WizardStep currentStep = 
            (WizardStep)(getWizardBar().getWizardSteps()).get(getCurrentStep());

        if (currentStep != null)
            clientSideJavaScript = currentStep.getJsValidation();

        if (clientSideJavaScript == null)
            clientSideJavaScript = "return stepValidation();";
        return clientSideJavaScript;
    }

    //TODO locking code    

    /**
     * gets the item that will be used in the locking methods
     * @return the item that will be locked
     */
    @Override
    protected ILockableItem getLockingItem() {
        if (getPageDTO() != null) {
            IBasicDTO dto = getPageDTO();
            if (dto instanceof ILockableItem) {
                return (ILockableItem)dto;
            }
        }
        return null;
    }

}


