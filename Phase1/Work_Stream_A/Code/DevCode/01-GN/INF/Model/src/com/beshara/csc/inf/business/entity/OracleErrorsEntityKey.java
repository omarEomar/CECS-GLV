package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;

import java.io.Serializable;

public class OracleErrorsEntityKey extends EntityKey implements IOracleErrorsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long errNum;
    private Long errCode;

    public OracleErrorsEntityKey() {
        super();
    }

    public OracleErrorsEntityKey(Long errNum) {
        super(new Object[] { errNum });
        this.errNum = errNum;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getErrNum() {
        return errNum;
    }

    public Long getErrCode() {
        return errCode;
    }
}
