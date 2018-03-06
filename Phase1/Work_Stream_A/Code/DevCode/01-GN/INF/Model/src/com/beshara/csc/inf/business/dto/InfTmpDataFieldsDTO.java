package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfTmpDataFieldsEntity;

import java.io.Serializable;

import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */


public class InfTmpDataFieldsDTO extends InfDTO implements IInfTmpDataFieldsDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private Long datatypCode;
    private Long fieldOrder;
    private String name;
    private String variableName;
    private String fieldLabel;
    private Long fldtypeCode;
    private Long newFieldOrder;

    /**
     * InfTmpDataFieldsDTO Default Constructor
     */
    public InfTmpDataFieldsDTO() {
        super();
    }


    /**
     * @return Long
     */
    public Long getDatatypCode() {
        return datatypCode;
    }

    /**
     * @return Long
     */
    public Long getFieldOrder() {
        return fieldOrder;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * @return String
     */
    public String getFieldLabel() {
        return fieldLabel;
    }

    /**
     * @return Long
     */
    public Long getFldtypeCode() {
        return fldtypeCode;
    }


    /**
     * @param datatypCode
     */
    public void setDatatypCode(Long datatypCode) {
        this.datatypCode = datatypCode;
    }

    /**
     * @param fieldOrder
     */
    public void setFieldOrder(Long fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param variableName
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * @param fieldLabel
     */
    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    /**
     * @param fldtypeCode
     */
    public void setFldtypeCode(Long fldtypeCode) {
        this.fldtypeCode = fldtypeCode;
    }


    public void setNewFieldOrder(Long newFieldOrder) {
        this.newFieldOrder = newFieldOrder;
    }

    public Long getNewFieldOrder() {
        return newFieldOrder;
    }
}

