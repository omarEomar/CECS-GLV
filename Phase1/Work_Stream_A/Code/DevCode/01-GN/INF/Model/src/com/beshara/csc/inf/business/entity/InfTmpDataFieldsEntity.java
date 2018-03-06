package com.beshara.csc.inf.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.beshara.base.entity.BasicEntity;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Entity
@NamedQueries( { @NamedQuery(name = "InfTmpDataFieldsEntity.findAll",
                             query = "select o from InfTmpDataFieldsEntity o"),
                 @NamedQuery(name = "InfTmpDataFieldsEntity.findNewId",
                             query = "select MAX(o.datatypCode) from InfTmpDataFieldsEntity o"),
                 @NamedQuery(name = "InfTmpDataFieldsEntity.searchByName",
                             query = "select o from InfTmpDataFieldsEntity o where o.name like :name order by o.name"),
                 @NamedQuery(name = "InfTmpDataFieldsEntity.getDataFieldsByType",
                             query = "select o from InfTmpDataFieldsEntity o where o.datatypCode=:dataType order by o.fieldOrder") })
@Table(name = "INF_TMP_DATA_FIELDS")
@IdClass(IInfTmpDataFieldsEntityKey.class)

public class InfTmpDataFieldsEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "DATATYP_CODE", nullable = false)
    private Long datatypCode;
    @Id
    @Column(name = "FIELD_ORDER", nullable = false)
    private Long fieldOrder;
    @Column(name = "FIELD_NAME", nullable = false)
    private String name;
    @Column(name = "VARIABLE_NAME", nullable = false)
    private String variableName;
    @Column(name = "FIELD_LABEL", nullable = true)
    private String fieldLabel;
    @Column(name = "FLDTYPE_CODE", nullable = false)
    private Long fldtypeCode;
    //@ManyToOne
    //@JoinColumn(name="FLDTYPE_CODE", referencedColumnName="FLDTYPE_CODE")
    //private InfFieldTypesEntity infFieldTypesEntity;
    //@ManyToOne
    //@JoinColumn(name="DATATYP_CODE", referencedColumnName="DATATYP_CODE")
    //private InfTmpDataTypesEntity infTmpDataTypesEntity;


    /**
     * InfTmpDataFieldsEntity Default Constructor
     */
    public InfTmpDataFieldsEntity() {
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


}
