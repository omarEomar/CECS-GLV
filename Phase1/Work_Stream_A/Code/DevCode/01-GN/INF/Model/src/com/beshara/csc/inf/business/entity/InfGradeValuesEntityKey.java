package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfGradeValuesEntityKey extends EntityKey implements IInfGradeValuesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long gradeTypeCode;
    private String value;


    public InfGradeValuesEntityKey() {
        super();
    }


    public InfGradeValuesEntityKey(Long gradeTypeCode, String value) {
        super(new Object[] { gradeTypeCode, value });
        this.gradeTypeCode = gradeTypeCode;
        this.value = value;
    }

    public int hashCode() {
        return super.hashCode();
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
}
