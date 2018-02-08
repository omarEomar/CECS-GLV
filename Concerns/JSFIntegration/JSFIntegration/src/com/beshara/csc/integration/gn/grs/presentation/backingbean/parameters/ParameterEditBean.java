package com.beshara.csc.gn.grs.presentation.backingbean.parameters;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IParametersClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IParametersDTO;
import com.beshara.csc.inf.business.client.IFieldTypesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IFieldTypesDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class ParameterEditBean extends LookupMaintainBaseBean {
    private List<IBasicDTO> fieldTypes = new ArrayList<IBasicDTO>();
    private IFieldTypesClient ifieldTypesClient = 
        InfClientFactory.getFieldTypesClient();
    private String fieldType;
    private String validValue = "1";
    private String inValidValue = "0";
    private String statusValue;
    private boolean notUseInCalc = true;
    private boolean validSqlStatement = true;
    private boolean sucessSqlSatementFlag = false;

    public ParameterEditBean() {
        setClient((IParametersClient)GrsClientFactory.getParametersClient());
        setContent1Div("divContent1Fixed");
    }

    public void setFieldTypes(List<IBasicDTO> fieldTpes) {
        this.fieldTypes = fieldTpes;
    }

    public List<IBasicDTO> getFieldTypes() {
        if (fieldTypes == null || fieldTypes.size() == 0)
            try {
                fieldTypes = ifieldTypesClient.getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        return fieldTypes;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldType() {
        if (fieldType != null && fieldType.equals("1"))
            notUseInCalc = false;
        return fieldType;
    }

    public void setValidValue(String validValue) {
        this.validValue = validValue;
    }

    public String getValidValue() {
        return validValue;
    }

    public void setInValidValue(String inValidValue) {
        this.inValidValue = inValidValue;
    }

    public String getInValidValue() {
        return inValidValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void reInitializePageDTO() {
    }

    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {
        IFieldTypesDTO fieldTypesDTO = GrsDTOFactory.createFieldTypesDTO();
        if (statusValue != null && !(statusValue.equals("")))
            ((IParametersDTO)getSelectedPageDTO()).setStatus(Long.valueOf(statusValue));
        if (fieldType != null && 
            !(fieldType.equals(""))) { //fieldTypesDTO.setFldtypeCode ( Long.valueOf ( fieldType ) ) ; 
            fieldTypesDTO.setCode(InfEntityKeyFactory.createFieldTypesEntityKey(Long.valueOf(fieldType)));
            ((IParametersDTO)getSelectedPageDTO()).setFiledTypesDTO(fieldTypesDTO);
        }
        if (((IParametersDTO)getSelectedPageDTO()).getSqlStatement().equals("")) {
            ((IParametersDTO)getSelectedPageDTO()).setSqlStatement(null);
        }
        super.edit();
    }

    public String saveParameter() {
        try {
            edit();
        } catch (ItemNotFoundException e) {
            super.getSharedUtil().setErrMsgValue("FailureInUpdate");
        } catch (SharedApplicationException e) {
            super.getSharedUtil().setErrMsgValue("FailureInUpdate");
        } catch (DataBaseException e) {
            super.getSharedUtil().setErrMsgValue("FailureInUpdate");
        }
        ParameterListBean parameterListBean = 
            (ParameterListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{parameterListBean}").getValue(FacesContext.getCurrentInstance());
        try {
            super.setMyTableData(parameterListBean.getMyTableData());
        } catch (DataBaseException e) { // TODO 
        }
        super.setMyDataTable(parameterListBean.getMyDataTable());
        if (getSelectedPageDTO() != null && 
            getSelectedPageDTO().getCode() != null) {
            parameterListBean.getHighlightedDTOS().add(getSelectedPageDTO());
            try {
                setMyTableData(parameterListBean.getMyTableData());
            } catch (DataBaseException e) { // TODO 
            }
            try {
                super.getPageIndex((String)getSelectedPageDTO().getCode().getKey());
            } catch (DataBaseException e) { // TODO 
            }
        }
        return "Parameter_List_Page";
    }

    public void changeUseInCalc(ActionEvent ae) {
        if (fieldType != null && fieldType.equals("1"))
            notUseInCalc = false;
        else
            notUseInCalc = true;
    }

    public String backToParameterList() {
        return "Parameter_List_Page";
    }

    public void setNotUseInCalc(boolean notUseInCalc) {
        this.notUseInCalc = notUseInCalc;
    }

    public boolean isNotUseInCalc() {
        return notUseInCalc;
    }

    public void setValidSqlStatement(boolean validSqlStatement) {
        this.validSqlStatement = validSqlStatement;
    }

    public boolean isValidSqlStatement() {
        return validSqlStatement;
    }

    public void setSucessSqlSatementFlag(boolean sucessSqlSatementFlag) {
        this.sucessSqlSatementFlag = sucessSqlSatementFlag;
    }

    public boolean isSucessSqlSatementFlag() {
        return sucessSqlSatementFlag;
    }

    public String validateSqlStatement() {
        IParametersDTO parametersDTO = (IParametersDTO)getSelectedPageDTO();
        if (parametersDTO.getSqlStatement() != null && 
            !(parametersDTO.getSqlStatement().equals(""))) {
            try {
                validSqlStatement =
                    ((IParametersClient)GrsClientFactory.getParametersClient()).validateSQL(parametersDTO.getSqlStatement());
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            sucessSqlSatementFlag = validSqlStatement;
        } else
            validSqlStatement = true;
        return null;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowNavigation(true);
        return app;
    }
}
