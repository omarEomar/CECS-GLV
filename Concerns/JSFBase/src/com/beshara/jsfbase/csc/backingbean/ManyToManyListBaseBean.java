package com.beshara.jsfbase.csc.backingbean;


import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;


public class ManyToManyListBaseBean extends ManyToManyBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private String addBeanName;
    private String editBeanName;
    private String addNavigationCase;
    private String editNavigationCase;

    public ManyToManyListBaseBean() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = 
                (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);


    }


    public void setAddBeanName(String addBeanName) {
        this.addBeanName = addBeanName;
    }

    public void setFrm(HtmlForm frm) {

        this.frm = frm;
    }

    public HtmlForm getFrm() {
        if (this.appMainLayoutBuilder() != null)
            setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
        this.initiateBeanOnce();
        return frm;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyListPage();

        return app;
    }


    public String getAddBeanName() {
        return addBeanName;
    }

    public void setEditBeanName(String editBeanName) {
        this.editBeanName = editBeanName;
    }

    public String getEditBeanName() {
        return editBeanName;
    }

    public void setAddNavigationCase(String addNavigationCase) {
        this.addNavigationCase = addNavigationCase;
    }

    public String getAddNavigationCase() {
        return addNavigationCase;
    }

    public void setEditNavigationCase(String editNavigationCase) {
        this.editNavigationCase = editNavigationCase;
    }

    public String getEditNavigationCase() {
        return editNavigationCase;
    }

    public String goAdd() {

        ManyToManyMaintainBaseBean maintainBean = 
            (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                              this.getAddBeanName() + 
                                                                                                              "}").getValue(FacesContext.getCurrentInstance());
        maintainBean.setMaintainMode(0);
        this.initializeObjectBeforeMaintain();
        maintainBean.setPageDTO(this.getPageDTO());
        return addNavigationCase;
    }

    public void initializeObjectBeforeMaintain() {

    }

    public String goEdit() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            ManyToManyMaintainBaseBean maintainBean = 
                (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                                  this.getAddBeanName() + 
                                                                                                                  "}").getValue(FacesContext.getCurrentInstance());

            setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
            maintainBean.setMaintainMode(1);
            this.initializeObjectBeforeMaintain();

            maintainBean.setPageDTO(this.getPageDTO());
            return editNavigationCase;

        }
        return null;
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


}
