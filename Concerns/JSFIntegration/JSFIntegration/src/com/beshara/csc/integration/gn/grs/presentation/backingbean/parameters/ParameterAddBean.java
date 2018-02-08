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
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class ParameterAddBean extends LookupMaintainBaseBean {
    private List<IBasicDTO> fieldTypes = new ArrayList<IBasicDTO>();
    private IFieldTypesClient ifieldTypesClient = 
        InfClientFactory.getFieldTypesClient();
    private String fieldType;
    private String validValue = "1";
    private String inValidValue = "0";
    private String statusValue;
    private boolean notUseInCalc = false;
    private boolean validSqlStatement = true;
    private boolean sucessSqlSatementFlag = false;

    public ParameterAddBean() {
        setPageDTO(GrsDTOFactory.createParametersDTO());
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

    public void add() throws DataBaseException {
        IFieldTypesDTO fieldTypesDTO = GrsDTOFactory.createFieldTypesDTO();
        if (statusValue != null && !(statusValue.equals("")))
            ((IParametersDTO)getPageDTO()).setStatus(Long.valueOf(statusValue));
        if (fieldType != null && 
            !(fieldType.equals(""))) { //fieldTypesDTO.setFldtypeCode ( Long.valueOf ( fieldType ) ) ; 
            fieldTypesDTO.setCode(InfEntityKeyFactory.createFieldTypesEntityKey(Long.valueOf(fieldType)));
            ((IParametersDTO)getPageDTO()).setFiledTypesDTO(fieldTypesDTO);
        }
        if ((((IParametersDTO)getPageDTO()).getSqlStatement() != null) && 
            ((IParametersDTO)getPageDTO()).getSqlStatement().equals("")) {
            ((IParametersDTO)getPageDTO()).setSqlStatement(null);
        }
        super.add();
    }

    public void reInitializePageDTO() {
        setPageDTO(GrsDTOFactory.createParametersDTO());
        statusValue = "";
        fieldType = "";
    }

    public String saveParameter() throws DataBaseException {
        add();
        ParameterListBean parameterListBean = 
            (ParameterListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{parameterListBean}").getValue(FacesContext.getCurrentInstance());
        super.setMyTableData(parameterListBean.getMyTableData());
        super.setMyDataTable(parameterListBean.getMyDataTable());
        if (getPageDTO() != null && getPageDTO().getCode() != null) {
            parameterListBean.getHighlightedDTOS().add(getPageDTO());
            setMyTableData(parameterListBean.getMyTableData());
            super.getPageIndex((String)getPageDTO().getCode().getKey());
        }
        return "Parameter_List_Page";
    }

    public void changeUseInCalc(ActionEvent ae) {
        System.out.println("calling changeUseInCalc ( ) --------");
        if (fieldType != null && fieldType.equals("1")) {
            notUseInCalc = false;
        } else {
            notUseInCalc = true;
            ((IParametersDTO)getPageDTO()).setBooleanUseInCalc(false);
        }
    }

    public String backToParameterList() throws DataBaseException { // ParameterListBean parameterListBean= ( ParameterListBean ) FacesContext.getCurrentInstance ( ) .getApplication ( ) .createValueBinding ( "# { parameterListBean } " ) .getValue ( FacesContext.getCurrentInstance ( ) ) ; 
        // 
        // 
        // super.setMyTableData ( parameterListBean.getMyTableData ( ) ) ; 
        // super.setMyDataTable ( parameterListBean.getMyDataTable ( ) ) ; 
        // 
        // if ( getPageDTO ( ) != null && getPageDTO ( ) .getCode ( ) != null ) { 
        // parameterListBean.getHighlightedDTOS ( ) .add ( getPageDTO ( ) ) ; 
        // setMyTableData ( parameterListBean.getMyTableData ( ) ) ; 
        // super.getPageIndex ( ( String ) getPageDTO ( ) .getCode ( ) .getKey ( ) ) ; } 
        return "Parameter_List_Page";
    }

    public void setNotUseInCalc(boolean notUseInCalc) {
        this.notUseInCalc = notUseInCalc;
    }

    public boolean isNotUseInCalc() {
        return notUseInCalc;
    }

    public String validateSqlStatement() {
        IParametersDTO parametersDTO = (IParametersDTO)getPageDTO();
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

    public void setValidSqlStatement(boolean invalidSqlStatement) {
        this.validSqlStatement = invalidSqlStatement;
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

  
   
    public void saveAndNew() throws DataBaseException {
        validSqlStatement = true;
        IParametersDTO parametersDTO = (IParametersDTO)getPageDTO();
        if (parametersDTO.getSqlStatement() != null && 
            !(parametersDTO.getSqlStatement().equals(""))) {
            validSqlStatement = 
                    ((IParametersClient)GrsClientFactory.getParametersClient()).validateSQL(parametersDTO.getSqlStatement());
        }
        if (validSqlStatement ||((parametersDTO.getSqlStatement() == null) || (parametersDTO.getSqlStatement().equals("")))) {
            super.saveAndNew();
        }
        if (validSqlStatement == false) {
            this.setSuccess(false);
        }
        getSharedUtil().setSuccessMsgValue(null);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowNavigation(true);
        return app;
    }
}
