package com.beshara.csc.inf.business.dto;

public interface IInfGradeValuesDTO  extends IInfDTO{
    public void setGradeTypeCode(Long gradeTypeCode) ;
    public Long getGradeTypeCode() ;
    public void setValue(String value) ;
    public String getValue();

    public void setPercentageValue(Long percentageValue) ;

    public Long getPercentageValue() ;
    public void setGradeTypesDTO(IInfGradeTypesDTO gradeTypesDTO) ;

    public IInfGradeTypesDTO getGradeTypesDTO() ;

}
