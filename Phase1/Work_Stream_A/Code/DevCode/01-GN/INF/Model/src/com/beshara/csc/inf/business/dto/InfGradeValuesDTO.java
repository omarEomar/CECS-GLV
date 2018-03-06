package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;

public class InfGradeValuesDTO extends InfDTO implements IInfGradeValuesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gradeTypeCode;
    private String value;
    private Long percentageValue;
    private IInfGradeTypesDTO gradeTypesDTO;


    public InfGradeValuesDTO() {
        super();
    }

    public InfGradeValuesDTO(InfGradeValuesEntity gradeTypeEntity) {
        InfEntityConvertr.getInfGradeValuesDTO(gradeTypeEntity);
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

    public void setPercentageValue(Long percentageValue) {
        this.percentageValue = percentageValue;
    }

    public Long getPercentageValue() {
        return percentageValue;
    }

    public void setGradeTypesDTO(IInfGradeTypesDTO gradeTypesDTO) {
        this.gradeTypesDTO = gradeTypesDTO;
    }

    public IInfGradeTypesDTO getGradeTypesDTO() {
        return gradeTypesDTO;
    }
}
