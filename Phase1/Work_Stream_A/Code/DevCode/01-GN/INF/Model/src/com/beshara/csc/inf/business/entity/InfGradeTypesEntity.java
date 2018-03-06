package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity

@NamedQueries( { @NamedQuery(name = "InfGradeTypesEntity.findAll",
                             query = "select o from InfGradeTypesEntity o order by o.gradeTypeName"),
                 @NamedQuery(name = "InfGradeTypesEntity.findNewId",
                             query = "select MAX(o.gradeTypeCode) from InfGradeTypesEntity o"),
                 @NamedQuery(name = "InfGradeTypesEntity.checkDuplicatName",
                             query = "select o from InfGradeTypesEntity o where o.gradeTypeName= :gradeTypeName "),
                 @NamedQuery(name = "InfGradeTypesEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.InfGradeTypesDTO(o.gradeTypeCode,o.gradeTypeName) from InfGradeTypesEntity o order by o.gradeTypeName"),
                 @NamedQuery(name = "InfGradeTypesEntity.searchByName",
                             query = "select o from InfGradeTypesEntity o where o.gradeTypeName like :gradeTypeName "),
                 @NamedQuery(name = "InfGradeTypesEntity.searchByCode",
                             query = "select o from InfGradeTypesEntity o where o.gradeTypeCode= :gradeTypeCode order by o.gradeTypeName") })

@Table(name = "INF_GRADE_TYPES")
@IdClass(IInfGradeTypesEntityKey.class)

public class InfGradeTypesEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-3538913966662359088")
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "GRDTYPE_CODE", nullable = false)
    private Long gradeTypeCode;

    @Column(name = "GRDTYPE_NAME", nullable = false)
    private String gradeTypeName;


    @Column(name = "REF_FLAG", nullable = false)
    private Long referenceFlag;

    @Column(name = "GRDTYPE_VALUES_TYPE")
    private Long gradeTypeValType;


    @Column(name = "CHANGE_TO_PRECENTAGE_FORMULA")
    private String formula;

    @Column(name = "MIN_VALUE")
    private Long minValue;

    @Column(name = "MAX_VALUE")
    private Long maxValue;


    public InfGradeTypesEntity() {

    }


    public void setGradeTypeCode(Long gradeTypeCode) {
        this.gradeTypeCode = gradeTypeCode;
    }

    public Long getGradeTypeCode() {
        return gradeTypeCode;
    }

    public void setGradeTypeName(String gradeTypeName) {
        this.gradeTypeName = gradeTypeName;
    }

    public String getGradeTypeName() {
        return gradeTypeName;
    }

    public void setReferenceFlag(Long referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public Long getReferenceFlag() {
        return referenceFlag;
    }

    public void setGradeTypeValType(Long gradeTypeValType) {
        this.gradeTypeValType = gradeTypeValType;
    }

    public Long getGradeTypeValType() {
        return gradeTypeValType;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }
}
