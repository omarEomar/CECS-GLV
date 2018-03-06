package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class TrxStatusEntityKey extends EntityKey implements ITrxStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String trxstatusCode;
    public TrxStatusEntityKey() {    }   
    public TrxStatusEntityKey(String trxstatusCode) {        super(new Object[] { trxstatusCode });
        this.trxstatusCode = trxstatusCode;
    }    public String getTrxstatusCode() {        return trxstatusCode;
    }}
