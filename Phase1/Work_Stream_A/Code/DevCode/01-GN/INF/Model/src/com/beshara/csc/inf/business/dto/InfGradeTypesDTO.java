package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntityKey;

public class InfGradeTypesDTO extends InfDTO implements IInfGradeTypesDTO{

    @SuppressWarnings("compatibility:-1708456050999518515")
    private static final long serialVersionUID = 1L;
    private Long gradeTypeCode;
    private String gradeTypeName;
    private Long referenceFlag;
    private String gradeTypeValType;
    private String formula;
    private Long minValue;
    private Long maxValue;


   
    public InfGradeTypesDTO() {
        super();
     
    }
    
    public InfGradeTypesDTO(Long gradeTypeCode ,String gradeTypeName) {
      setCode(new InfGradeTypesEntityKey(gradeTypeCode));
        setName(gradeTypeName);
     
    }
    public InfGradeTypesDTO(InfGradeTypesEntity gradeTypeEntity) {
      
        setCode(InfEntityKeyFactory.createGradeTypesEntityKey(gradeTypeEntity.getGradeTypeCode()));
        setName(gradeTypeEntity.getGradeTypeName());
        setGradeTypeName(gradeTypeEntity.getGradeTypeName());
        setReferenceFlag(gradeTypeEntity.getReferenceFlag());
        if (gradeTypeEntity.getGradeTypeValType()!=null) {
            setGradeTypeValType(gradeTypeEntity.getGradeTypeValType().toString());
        }
        setFormula(gradeTypeEntity.getFormula());
        setMinValue(gradeTypeEntity.getMinValue());
        setMaxValue(gradeTypeEntity.getMaxValue());
        
        
        setCreatedBy(gradeTypeEntity.getCreatedBy());
        setCreatedDate(gradeTypeEntity.getCreatedDate());
        setLastUpdatedBy(gradeTypeEntity.getLastUpdatedBy());
        setLastUpdatedDate(gradeTypeEntity.getLastUpdatedDate());
        setAuditStatus(gradeTypeEntity.getAuditStatus());
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

    public void setGradeTypeValType(String gradeTypeValType) {
        this.gradeTypeValType = gradeTypeValType;
    }

    public String getGradeTypeValType() {
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
