package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;

import com.beshara.jsfbase.csc.backingbean.BaseBean;

public class MaritalStatus extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private boolean maritalStatusSingle;

    public MaritalStatus() {
    }

    public void setMaritalStatusSingle(boolean maritalStatusSingle) {
        this.maritalStatusSingle = maritalStatusSingle;
    }

    public boolean isMaritalStatusSingle() {
        return maritalStatusSingle;
    }

}
