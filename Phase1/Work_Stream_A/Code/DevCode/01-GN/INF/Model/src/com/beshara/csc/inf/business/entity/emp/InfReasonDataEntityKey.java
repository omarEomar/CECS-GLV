package com.beshara.csc.inf.business.entity.emp;

import com.beshara.base.entity.EntityKey;


public class InfReasonDataEntityKey extends EntityKey implements IInfReasonDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long resdatSerial;
    private Long restypeCode;

    public InfReasonDataEntityKey() {
        super();
    }

    public InfReasonDataEntityKey(Long resdatSerial, Long restypeCode) {
        super( new Object[] {resdatSerial, restypeCode });
    this.resdatSerial=resdatSerial;
        this.restypeCode = restypeCode;
    
    }


    public Long getResdatSerial() {
        return resdatSerial;
    }


    public Long getRestypeCode() {
        return restypeCode;
    }
}
