package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialinsuranceperoidcalc;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.IPeroidCalcInsurDTO;
import com.beshara.csc.hr.sal.business.dto.ISalClientDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpExtraDataDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpExtraDataDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;

import weblogic.ejb20.utils.OrderedSet;


public class EmpSalaryBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:6743411428534758592")
    private static final long serialVersionUID = 1L;
    protected static final String RESOURCE_BUNDLE =
        "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String BEAN_NAME = "socialInsurPeroidCalcEmpSalaryBean";

    private String limitedPeroid = "0";
    private Date intervalFromDate;
    private Date intervalUnitlDate;
    private Long civilId;
    private Long minCode;
    
    private  HtmlDataTable changeTypeDataTable = new HtmlDataTable();
    private  List changeTypeList = new ArrayList();
    private  List selectedChangeTypeList = new ArrayList();

    public EmpSalaryBean() {
        setClient(SalClientFactory.getSalSalarySheetsClient());
        setContent1Div("module_tabs_cont1");
    }

    public static EmpSalaryBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (EmpSalaryBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();

        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowWizardBar(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public void setLimitedPeroid(String limitedPeroid) {
        this.limitedPeroid = limitedPeroid;
    }

    public String getLimitedPeroid() {
        return limitedPeroid;
    }

    public void setIntervalFromDate(Date intervalFromDate) {
        this.intervalFromDate = intervalFromDate;
    }

    public Date getIntervalFromDate() {
        return intervalFromDate;
    }

    public void setIntervalUnitlDate(Date intervalUnitlDate) {
        this.intervalUnitlDate = intervalUnitlDate;
    }

    public Date getIntervalUnitlDate() {
        return intervalUnitlDate;
    }


    public void changePeroid(ActionEvent actionEvent) throws Exception {
        intervalUnitlDate = null;
        intervalFromDate = null;
        if (limitedPeroid != null && limitedPeroid.equals("0")) {
            getAll();
        }
    }

    public void viewSalEmpSalaryInterval() throws DataBaseException {

        getAll();
    }

    private void initEmpsalaryData() {
        try {
            JobCategoryBean bean = JobCategoryBean.getInstance();
            setCivilId(Long.valueOf(bean.getEmpDTO().getCode().getKey()));
            System.out.println(bean.getEmpDTO().getMinCode());
            minCode  = bean.getEmpDTO().getMinCode();
        } catch (Exception nfe) {
            nfe.printStackTrace();
        }

    }

    public List getTotalList() {
        List<IBasicDTO> empSalaryElements = new ArrayList<IBasicDTO>();
        initEmpsalaryData();
        if (civilId != null) {
            ISalEmpSalaryElementsDTO dto = SalDTOFactory.createSalEmpSalaryElementsDTO();
            try {
                dto.setEmpCivilId(civilId);
                dto.setFromDate(intervalFromDate);
                dto.setUntilDate(intervalUnitlDate);

                dto.setMinName(minCode + "");
                dto.setSalEmpExtraDataList(getSelectedChangeTypeList());
                empSalaryElements = SalClientFactory.getSalEmpSalaryElementsClient().getAllElementsForEmp(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return empSalaryElements;
    }


    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {

            setUpdateMyPagedDataModel(true);
            setPagingRequestDTO( new PagingRequestDTO("getAllWithPaging"));

        } else {
            List total=this.getTotalList();
            setMyTableData(total);
            getMyDataTable().setFirst(0);
            this.reinitializeSort();

            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
            }
            try {
                sort(total, "fromDate", false, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }
    public void selectedChnageTypeCheckboxs(ActionEvent event) throws Exception {


        try {

            Set<IBasicDTO> selectedSet = new OrderedSet();
            selectedSet.addAll(selectedChangeTypeList);

            IClientDTO selected = (IClientDTO)this.getChangeTypeDataTable().getRowData();

            if (selected.getChecked()) {

                try {
                    selectedSet.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                selected.setChecked(Boolean.TRUE);

                for (IBasicDTO item : selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }

                selected.setChecked(Boolean.FALSE);

            }

            selectedChangeTypeList.clear();
            selectedChangeTypeList.addAll(selectedSet);
            System.out.print(selectedChangeTypeList.size());
        } catch (Exception e) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }

    }
    public void restoreSaveStateParams(Object obj) {
        try {

            IPeroidCalcInsurDTO peroidCalcInsurDTO = (IPeroidCalcInsurDTO)obj;
            setCivilId(Long.valueOf(peroidCalcInsurDTO.getEmpDTO().getCode().getKey()));
            System.out.println(getCivilId());
            getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setChangeTypeList(List changeTypeList) {
        this.changeTypeList = changeTypeList;
    }

    public List getChangeTypeList() {
        if(changeTypeList == null || changeTypeList.isEmpty()){
            
            ISalClientDTO raiseTypesDTO =  new SalEmpExtraDataDTO();
            raiseTypesDTO.setName(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "mainSalary_Label"));
            raiseTypesDTO.setCode(new EntityKey(ISalConstants.ELEMENT_GUIDE_TYPE_RAISE));
            changeTypeList.add(raiseTypesDTO);
            
            ISalEmpExtraDataDTO socialRaiseTypesDTO = new SalEmpExtraDataDTO();
            socialRaiseTypesDTO.setName(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "socialRaise_Label"));
            socialRaiseTypesDTO.setCode(new EntityKey(ISalConstants.ELEMENT_GUIDE_TYPE_SOCIAL_RAISE + ","+ISalConstants.ELEMENT_GUIDE_TYPE_SOCIAL_RAISE_MARRIED));
            changeTypeList.add(socialRaiseTypesDTO);
            
            ISalEmpExtraDataDTO childrenRiaseTypesDTO = new SalEmpExtraDataDTO();
            childrenRiaseTypesDTO.setName(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "childrenRiase_Label"));
            childrenRiaseTypesDTO.setCode(new EntityKey(ISalConstants.ELEMENT_GUIDE_TYPE_CHILDREN_PROMOTION));
            changeTypeList.add(childrenRiaseTypesDTO);
            
            ISalEmpExtraDataDTO rewardsTypesDTO = new SalEmpExtraDataDTO();
            rewardsTypesDTO.setName(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "benefits_rewards_Label"));
            rewardsTypesDTO.setCode(new EntityKey( ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_TYPE_ROOT + ","+ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_TYPE_ROOT) );
            changeTypeList.add(rewardsTypesDTO);
            
//            ISalEmpExtraDataDTO benefitsTypesDTO = new SalClientDTO();
//            benefitsTypesDTO.setName(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "benefits_Label"));
//            benefitsTypesDTO.setCode(new EntityKey(ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_TYPE_ROOT));
//            changeTypeList.add(benefitsTypesDTO);
            
            ISalEmpExtraDataDTO dedTypesDTO = new SalEmpExtraDataDTO();
            dedTypesDTO.setName(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "deductions_Label"));
            dedTypesDTO.setCode(new EntityKey(ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT));
            changeTypeList.add(dedTypesDTO);
            
        }
        return changeTypeList;
    }

    public void setChangeTypeDataTable(HtmlDataTable changeTypeDataTable) {
        this.changeTypeDataTable = changeTypeDataTable;
    }

    public HtmlDataTable getChangeTypeDataTable() {
        return changeTypeDataTable;
    }

    public void setSelectedChangeTypeList(List selectedChangeTypeList) {
        this.selectedChangeTypeList = selectedChangeTypeList;
    }

    public List getSelectedChangeTypeList() {
        return selectedChangeTypeList;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }
}
