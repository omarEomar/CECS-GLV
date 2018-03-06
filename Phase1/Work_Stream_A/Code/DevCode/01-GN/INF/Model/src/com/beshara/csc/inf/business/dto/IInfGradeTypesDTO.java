package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.IBasicDTO;

public interface IInfGradeTypesDTO extends IInfDTO{
    public void setGradeTypeCode(Long gradeTypeCode) ;
    public Long getGradeTypeCode() ;

    public void setGradeTypeName(String gradeTypeName) ;
    public String getGradeTypeName() ;

    public void setReferenceFlag(Long referenceFlag) ;

    public Long getReferenceFlag() ;

    public void setGradeTypeValType(String gradeTypeValType) ;

    public String getGradeTypeValType() ;

    public void setFormula(String formula);
    public String getFormula() ;
    public void setMinValue(Long minValue) ;

    public Long getMinValue() ;
    public void setMaxValue(Long maxValue) ;
    public Long getMaxValue() ;
}
