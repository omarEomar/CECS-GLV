package com.beshara.csc.gn.grs.integration.presentation.backingbean.addconditions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.ILinesClient;
import com.beshara.csc.gn.grs.business.client.IParametersClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionDetailsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.ISearchLinesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


public class ConditionLineSub extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long code = null;
    private String lineName = "";
    private String paramCode = "";
    private List lineParamter;
    private String itemSelectionRequiredValueString = ISystemConstant.VIRTUAL_VALUE.toString();
    private IConditionDetailsDTO dataTable; // = GrsDTOFactory.createConditionDetailsDTO ( ) ;
    private int rowNo;

    private IBasicDTO selectedRow;

    public ConditionLineSub() {
        setPageDTO(GrsDTOFactory.createLinesDTO());
        super.setSelectedPageDTO(GrsDTOFactory.createLinesDTO());
        setClient((ILinesClient)GrsClientFactory.getLinesClient());
        setDivMainContent("divContentConditionLineSub");
    }
    
    public static ConditionLineSub getInstance() {
        return (ConditionLineSub)JSFHelper.getValue("conditionIntgLineSub");
    }

    /**
     * Purpose: this method handle show and hide divs * Creation/Modification History : * 1.1 - Developer Name: Ahmed Abd El-Fatah * 1.2 - Date: Jul 21 , 2008 * 1.3 - Creation/Modification: * 1.4- Description: * @return
     * @throws
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(true);
        app.setShowCommonData(true);
        app.setShowContent1(true);
        app.setShowNavigation(true);
        return app;
    }

    private String getMainParameterType() {
        try {
            return ((IConditionsDTO)ConditionsMaintainBean.getInstance().getPageDTO()).getParameterTypesDTO().getCode().getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List getTotalList() {
        List totalList = null;
        try {
            String paramType = getMainParameterType();
            if (paramType != null) {
                totalList = GrsClientFactory.getLinesClient().getAllByParameterTypeSimple(Long.parseLong(paramType));
            } else {
                totalList = GrsClientFactory.getLinesClient().getAll();
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

    public String cancelSearchLine() {
        setParamCode(ISystemConstant.VIRTUAL_VALUE.toString());
        setLineName("");
        setCode(null);
        setMyTableData(getTotalList());

        this.repositionPage(0);
        return null;
    }

    public void radioBtnChecked(ValueChangeEvent v) {
        IBasicDTO b = (IBasicDTO)this.getMyDataTable().getRowData();
        selectedRow = b;
        /*ValueBinding vb =
            getAppliction().createValueBinding("#{conditionLineDetailBean}");
        ConditionLineDetailBean bean =
            (ConditionLineDetailBean)vb.getValue(FacesContext.getCurrentInstance());
        IConditionDetailsDTO returnDTO =
            (IConditionDetailsDTO)bean.getCurrentDisplayDetails().get(getRowNo());
        returnDTO.setName(b.getName());
        returnDTO.setLinesDTO(b);
        vb.setValue(FacesContext.getCurrentInstance(), bean);*/
    }

    public static Application getAppliction() {
        return FacesContext.getCurrentInstance().getApplication();
    }

    public String searchLine() {
        SharedUtilBean shared_util = getSharedUtil();
        System.out.println("------------------------------++++++++++++");
        ILinesClient lineSrch = GrsClientFactory.getLinesClient();
        ISearchLinesDTO data = GrsDTOFactory.createSearchLinesDTO();
        data.setLineCode(getCode());
        data.setLineName((getLineName()));
        data.setParameterCode(Long.parseLong(getParamCode()));
        data.setSelectedParameterType(getMainParameterType());
        try {
            setMyTableData(lineSrch.searchSimple(data));
        } catch (SharedApplicationException e) { // TODO
            setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (DataBaseException e) { // TODO
            setMyTableData(new ArrayList());
            e.printStackTrace();
        } /*catch (RemoteException e) { // TODO
            setMyTableData(new ArrayList());
            e.printStackTrace();
        }*/
        this.repositionPage(0);
        return "";
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineParamter(List lineParamter) {
        this.lineParamter = lineParamter;
    }

    public List getLineParamter() throws DataBaseException, SharedApplicationException {
        if (lineParamter == null) {
            IParametersClient paramClient = GrsClientFactory.getParametersClient();
            lineParamter = paramClient.getAllByParameterTypeList(Long.parseLong(getMainParameterType()));
        }
        return lineParamter;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setItemSelectionRequiredValueString(String itemSelectionRequiredValueString) {
        this.itemSelectionRequiredValueString = itemSelectionRequiredValueString;
    }

    public String getItemSelectionRequiredValueString() {
        return itemSelectionRequiredValueString;
    }

    public void setDataTable(IConditionDetailsDTO dataTable) {
        System.out.println(dataTable.getLeftArcs() + "------" + dataTable.getRightArcs());
        this.dataTable = dataTable;
    }

    public IConditionDetailsDTO getDataTable() {
        System.out.println(dataTable + "***************************************____");
        return dataTable;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getRowNo() {
        return rowNo;
    }

    public String backToLinesTab() {

        return _back();
    }
    public String backToLineSub() {

        return "condition_intg_line_sub";
    }



    public String navigateTOLinesTab() {
        ConditionLineDetailBean bean = ConditionLineDetailBean.getInstance();
        if (selectedRow == null) {
            getSharedUtil().setErrMsgValue("chooseLinevalue");
            return "";
        }
        IConditionDetailsDTO returnDTO = (IConditionDetailsDTO)bean.getCurrentDisplayDetails().get(getRowNo());
        returnDTO.setName(selectedRow.getName());
        returnDTO.setLinesDTO(selectedRow);

        return _back();
    }

    private String _back() {
        ConditionLineDetailBean.getInstance().unCheck();

        getWizardBar().setCurrentStep("conditionLines");
        return "conditionLine_intg_Maintain_Details";
    }

    public void setSelectedRow(IBasicDTO selectedRow) {
        this.selectedRow = selectedRow;
    }

    public IBasicDTO getSelectedRow() {
        return selectedRow;
    }
}
