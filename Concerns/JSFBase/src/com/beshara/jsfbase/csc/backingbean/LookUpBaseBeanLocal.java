package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;


/**
 * this bean responsible for Lookup
 * use this bean when implement lookup page
 */
public class LookUpBaseBeanLocal extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private HtmlForm frm;


    public LookUpBaseBeanLocal() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = 
                (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);
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
        app.showLookupListPage();
        return app;
    }


    //this new functions to migrate popup to divs

    public void save() {
        try {
            reIntializeMsgs();
            this.add();
            this.reInitializePageDTO();
            reIntializeAddDivMsgs();
        } catch (Exception ex) {
            this.setErrorMsg(ex.getCause().getMessage());
            //showDiv();
        }
    }

    public void saveAndNew() throws DataBaseException {
        reIntializeMsgs();
        this.add();
        if (getSharedUtil().getSuccessMsgValue() != null && 
            !getSharedUtil().getSuccessMsgValue().equals(""))
            this.setSuccess(true);
        this.reInitializePageDTO();
    }


    ////////////////////////////////////////////////////////

    public void reInitializePageDTO() {
        this.setPageDTO(new BasicDTO());
    }

    //Paging

    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {

        SharedUtilBean shared_util = getSharedUtil();

        try {

            super.getClient().update(super.getSelectedPageDTO());

            cancelSearch();

            if (super.isUsingPaging()) {

                generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

            } else {
                getPageIndex((String)getSelectedPageDTO().getCode().getKey());
            }

            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().add(this.getSelectedPageDTO());
            }

            super.setShowEdit(false);

            shared_util.setSuccessMsgValue("SuccesUpdated");

        } catch (DataBaseException e) {
            shared_util.setErrMsgValue("FailureInUpdate");
        } catch (ItemNotFoundException item) {
            shared_util.setErrMsgValue("itemALreadyDeleted");
        }

        setSelectedRadio("");

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

        return casesArrayButton;
    }

    public void preAdd() {
        setPageMode(1);
        super.preAdd();
    }

    public void showEditDiv() {
        setPageMode(2);
        super.showEditDiv();
    }
}

