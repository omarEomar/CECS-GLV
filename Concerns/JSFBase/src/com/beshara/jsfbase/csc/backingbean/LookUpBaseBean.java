package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeUpdatedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;


/**
 * this bean responsible for Lookup
 *
 * use this bean when implement lookup page
 */
public class LookUpBaseBean extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private transient HtmlForm frm;

    public LookUpBaseBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = 
                (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);
    }

    public void setFrm(HtmlForm frm) {

        this.frm = frm;
    }

    public HtmlForm getFrm() {

        if (getUsingPortal()) {
            if (this.appMainLayoutPortalBuilder() != null) {
                setCurrentApplictionMainLayoutPortal(this.appMainLayoutPortalBuilder());
            }
        } else {
            if (this.appMainLayoutBuilder() != null) {
                setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
            }
        }
       this.initiateBeanOnce();
        return frm;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        return app;
    }

    public AppMainLayoutPortal appMainLayoutPortalBuilder() {
        AppMainLayoutPortal app = new AppMainLayoutPortal();
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

        //TODO locking code
        // be sure that we are still locking the edited item
        // if not cancel the edit operation and show an exception 
        // message to the user
        if (insureLocked()) {

            SharedUtilBean sharedUtil = getSharedUtil();

            try {
                // because we are always closing the edit div, 
                // so we must always unlock the edited item 
                // to leave it to other users
                try {
                    executeEdit();
                } finally {
                    //TODO locking code
                    // unlock the edited item in update success or failure
                    // so we added it in this finally block
                    unlock();
                }

                cancelSearch();

                if (super.isUsingPaging()) {
                    generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

                } else {
                    getPageIndex((String)getSelectedPageDTO().getCode().getKey());
                }

                if (super.getHighlightedDTOS() != null) {
                    super.getHighlightedDTOS().clear();
                    super.getHighlightedDTOS().add(this.getSelectedPageDTO());
                }

                super.setShowEdit(false);
                sharedUtil.setSuccessMsgValue("SuccesUpdated");

            } catch (DataBaseException e) {
                sharedUtil.handleException(e);
            } catch (ItemNotFoundException e) {
                sharedUtil.handleException(e);
            } catch (ItemCanNotBeUpdatedException e) {
                sharedUtil.handleException(e);

            } catch (Exception e) {
                sharedUtil.handleException(e, 
                                           "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                           "FailureInUpdate");

            }
        }
        
        setSelectedRadio("");

    }
    
    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
        super.getClient().update(super.getSelectedPageDTO());
        return super.getSelectedPageDTO();
    }

    public void generateMenuIndexes(ModuleObject currentObj, 
                                    String currentMenuItemIndex) {
        String ret;
        ret = "";
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

    //    public void showEditDiv() {
    //        setPageMode(2);
    //        super.showEditDiv();
    //    }


    //this method override to use getAllInCenter() method

    public List getTotalList() {
        List totalList = null;
        try {
            IBasicDTO filterDTO = getFilterDTO();

            if (filterDTO == null) {
                totalList = getClient().getAllInCenter();
            } else {
                totalList = getClient().getAllInCenter(filterDTO);
            }

        } catch (SharedApplicationException ne) {
            totalList = new ArrayList();
            ne.printStackTrace();
        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            getSharedUtil().handleException(e);
        }
        return totalList;
    }

    //this method override to use searchByCodeInCenter(Object code) and searchByNameInCenter(String name) methods

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult = 
                        getClient().searchByCodeInCenter(getSearchEntityObj());
            } else if (getSearchType().intValue() == 1) {
                searchResult = 
                        getClient().searchByNameInCenter(formatSearchString(getSearchQuery()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList(0));
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_display}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    //this method override to use getByIdInCenter(EntityKey id) method

    public IBasicDTO preEdit() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            return getClient().getByIdInCenter(getEntityKey());
        } catch (ItemNotFoundException e) {
            shared_util.setErrMsgValue("itemALreadyDeleted");
        } catch (DataBaseException e) {
            //shared_util.setErrMsgValue("SystemFailureException");
            getSharedUtil().handleException(e);
        } catch (SharedApplicationException e) {
            //shared_util.setErrMsgValue(e.getMessage());
            getSharedUtil().handleException(e);
        }
        return null;
    }

    //this method override to use getByIdInCenter(EntityKey id) method

    public void showEditDiv() {
        //TODO locking code
        // before showing the edit div we must lock this item
        // if the locking failed we are going to cancel the edit operation
        // and shows the exception message to the user instead of the edit div
        // This will be handled using the executeAfterLock javascript function
         SharedUtilBean sharedUtil = getSharedUtil();
         if (this.getSelectedDTOS() != null && 
             this.getSelectedDTOS().size() == 1) {
             //            selectedPageDTO=this.getSelectedDTOS().get(0);
             IBasicDTO dto = this.getSelectedDTOS().get(0);
             try {
                 setSelectedPageDTO(getClient().getByIdInCenter(dto.getCode()));
             } 
//             catch (ItemNotFoundException e) {
//                 sharedUtil.handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInUpdate");
//                 e.printStackTrace();
//             }
            catch (Exception e) {
                 e.printStackTrace();
             }
             setShowEdit(true);
             //javaScriptCaller = "changeVisibilityDiv(window.blocker,window.lookupEditDiv);";
         } else {
             setSelectedPageDTO(new BasicDTO());
             setShowEdit(false);
         }
        if (!lock()) {
            return;
        }
        setPageMode(2);
        reIntializeMsgs();
    }

    //Paging
}
