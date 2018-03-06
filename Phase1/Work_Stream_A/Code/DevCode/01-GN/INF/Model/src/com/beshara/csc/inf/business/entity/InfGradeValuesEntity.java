package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity

@NamedQueries( { @NamedQuery(name = "InfGradeValuesEntity.findAll",
                             query = "select o from InfGradeValuesEntity o order by o.gradeTypeCode"),
                 @NamedQuery(name = "InfGradeValuesEntity.searchByName",
                             query = "select o from InfGradeValuesEntity o where o.value like :value "),
                 @NamedQuery(name = "InfGradeValuesEntity.searchByCode",
                             query = "select o from InfGradeValuesEntity o where o.gradeTypeCode= :gradeTypeCode order by o.value "),
                 @NamedQuery(name = "InfGradeValuesEntity.getAllByTypeCode",
                             query = "select o from InfGradeValuesEntity o where o.gradeTypeCode= :gradeTypeCode order by o.percentageValue DESC") })
@Table(name = "INF_GRADE_VALUES")
@IdClass(IInfGradeValuesEntityKey.class)
public class InfGradeValuesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "GRDTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long gradeTypeCode;
    @Id
    @Column(name = "VALUE", nullable = false, updatable = false)
    private String value;
    @Column(name = "PRECENTAGE_VALUE")
    private Long percentageValue;

    @ManyToOne
    @JoinColumn(name = "GRDTYPE_CODE", referencedColumnName = "GRDTYPE_CODE")
    private InfGradeTypesEntity gradeTypeEntity;


    public InfGradeValuesEntity() {
        super();
    }

    public void setGradeTypeCode(Long gradeTypeCode) {
        this.gradeTypeCode = gradeTypeCode;
    }

    public Long getGradeTypeCode() {
        return gradeTypeCode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setGradeTypeEntity(InfGradeTypesEntity gradeTypeEntity) {
        this.gradeTypeEntity = gradeTypeEntity;
    }

    public InfGradeTypesEntity getGradeTypeEntity() {
        return gradeTypeEntity;
    }

    public void setPercentageValue(Long percentageValue) {
        this.percentageValue = percentageValue;
    }

    public Long getPercentageValue() {
        return percentageValue;
    }
}


