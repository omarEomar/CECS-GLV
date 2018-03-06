package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class ApprovalMakersEntityKey extends EntityKey implements IApprovalMakersEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long aprmakerCode;
    public ApprovalMakersEntityKey() {    }    public ApprovalMakersEntityKey(Long aprmakerCode) {        super(new Object[] { aprmakerCode });
        this.aprmakerCode = aprmakerCode;
        //setKey ( aprmakerCode.toString ( ) ) ; 
    }    public Long getAprmakerCode() {        return aprmakerCode;
    }}
