package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfGradeTypesEntityKey extends EntityKey implements IInfGradeTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long gradeTypeCode;
    public InfGradeTypesEntityKey() {
        
           
    }
    
    public InfGradeTypesEntityKey(Long gradeTypeCode) {
    super(new Object[] { gradeTypeCode });
        
    this.gradeTypeCode=gradeTypeCode;
}


    public Long getGradeTypeCode() {
        return gradeTypeCode;
    }
}
