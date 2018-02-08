package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;


/**
 * this bean responsible for Master Detail
 * use this bean when implement Master Detail page
 */
public class MasterDetailBaseBean extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private String detailsUseCase = null;
    private String masterUseCase = null;
    private String detailsBackBeanName;
    private String masterBackBeanName;
    private IBasicDTO masterDTO;
    private List<IBasicDTO> masterDetailList = new ArrayList<IBasicDTO>();
    private String masterDetailTitleBindingString = null;
    private String masterDetailTitle;

    public void getDetailsList() {

    }

    public void setMasterDTO(IBasicDTO masterDTO) {

        this.masterDTO = masterDTO;

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
        app.showMasterDetailPage();
        app.showLookupListPage();
        return app;
    }


    public IBasicDTO getMasterDTO() {
        return masterDTO;
    }


    public MasterDetailBaseBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = 
                (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);


    }

    public String viewDetails() {

        if (this.getSelectedDTOS() != null) {

//            System.out.println("from the view details");
            IBasicDTO dto = this.getSelectedDTOS().get(0);

            IBasicDTO base = null;
            try {
                base = super.getClient().getById(dto.getCode());
            } catch (SharedApplicationException e) {
                // TODO
                ;
            } catch (DataBaseException e) {
                // TODO
                ;
            }
            MasterDetailBaseBean bean = 
                (MasterDetailBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                            this.getDetailsBackBeanName() + 
                                                                                                            "}").getValue(FacesContext.getCurrentInstance());
            bean.setMasterDTO(base);
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ex = ctx.getExternalContext();
            HttpSession session = (HttpSession)ex.getSession(true);
            session.setAttribute("master", base);
            super.setSelectedDTOS(null);
            super.setMyTableData(null);

        }


        return detailsUseCase;

    }


    public void setDetailsUseCase(String detailsUseCase) {
        this.detailsUseCase = detailsUseCase;
    }

    public String getDetailsUseCase() {
        return detailsUseCase;
    }

    public void setDetailsBackBeanName(String detailsBackBeanName) {
        this.detailsBackBeanName = detailsBackBeanName;
    }

    public String getDetailsBackBeanName() {
        return detailsBackBeanName;
    }

    public String back() {
//        System.out.println("Callig back");
        MasterDetailBaseBean bean = 
            (MasterDetailBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                        this.getMasterBackBeanName() + 
                                                                                                        "}").getValue(FacesContext.getCurrentInstance());
        try {

            bean.getAll();

        } catch (DataBaseException e) {
            // TODO
            ;
        }


        super.setSelectedDTOS(null);
        super.setMyTableData(null);

        return masterUseCase;

    }


    public void setMasterUseCase(String masterUseCase) {
        this.masterUseCase = masterUseCase;
    }

    public String getMasterUseCase() {
        return masterUseCase;
    }

    public void setMasterBackBeanName(String masterBackBeanName) {
        this.masterBackBeanName = masterBackBeanName;
    }

    public String getMasterBackBeanName() {
        return masterBackBeanName;
    }

    //ASSUMING THAT EACH DTO WILL HOLD ITS MASTER DATA

    public IBasicDTO preEdit() {


        super.setPageDTO(super.loadSelectedDTOS().get(0));
        return super.loadSelectedDTOS().get(0);

    }

    public void save() {
        try {
            reIntializeMsgs();
            this.add();
            this.reInitializePageDTO();
        } catch (Exception ex) {
            this.setErrorMsg(ex.getCause().getMessage());
        }
    }

    public void reInitializePageDTO() {
        this.setPageDTO(new BasicDTO());


    }

    public void saveAndNew() {
        try {
            reIntializeMsgs();
            this.add();
            if (getSharedUtil().getSuccessMsgValue() != null && 
                !getSharedUtil().getSuccessMsgValue().equals(""))
                this.setSuccess(true);
            this.reInitializePageDTO();
            //             showDiv();
        } catch (Exception ex) {
            this.setSuccess(false);
            this.setErrorMsg(ex.getCause().getMessage());
            //      showDiv();
        }
    }

    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            super.getClient().update(super.getSelectedPageDTO());
            this.getAll();
			cancelSearch();
            if (this.getHighlightedDTOS() != null)
                this.getHighlightedDTOS().add(super.getSelectedPageDTO());
            super.setSelectedPageDTO(null);
            super.setShowEdit(false);
            super.setSelectedDTOS(new ArrayList<IBasicDTO>());
            shared_util.setSuccessMsgValue("SuccesUpdated");
            if (super.isUsingPaging()) {
                generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

            } else {
                getPageIndex((String)getSelectedPageDTO().getCode().getKey());
            }
        } catch (DataBaseException e) {
            shared_util.handleException(e);
        } catch (ItemNotFoundException item) {
            shared_util.handleException(item);
            //shared_util.setSuccessMsgValue("FailureInUpdate");
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
//        System.out.println("Yassminee ");
//        System.out.println("casesArray[0]  " + casesArray[0]);
//        System.out.println("casesArray[1]  " + casesArray[1]);
//        System.out.println("casesArray[3]  " + casesArray[2]);
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
//        System.out.println("casesArrayButton  " + casesArrayButton[0]);
//        System.out.println("casesArrayButton  " + casesArrayButton[1]);
//        System.out.println("casesArrayButton  " + casesArrayButton[2]);
//        System.out.println("allIndexesButton  " + allIndexesButton);
        return casesArrayButton;
    }


    public void setMasterDetailList(List<IBasicDTO> masterDetailList) {
        this.masterDetailList = masterDetailList;
    }

    public List<IBasicDTO> getMasterDetailList() {
        if (masterDetailList == null || masterDetailList.size() == 0) {
            this.getDetailsList();
        }
        return masterDetailList;
    }

    public void setMasterDetailTitleBindingString(String masterDetailTitleBindingString) {
        this.masterDetailTitleBindingString = masterDetailTitleBindingString;
    }

    public String getMasterDetailTitleBindingString() {
        return masterDetailTitleBindingString;
    }

    public void setMasterDetailTitle(String masterDetailTitle) {
        this.masterDetailTitle = masterDetailTitle;
    }

    public String getMasterDetailTitle() {
        masterDetailTitle = 
                (String)this.getBindingValue(this.getMasterDetailTitleBindingString());
        return masterDetailTitle;
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
