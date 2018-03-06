package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class FieldsEntityKey extends EntityKey implements IFieldsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long fldCode;
    public FieldsEntityKey() {    }    public FieldsEntityKey(Long fldCode) {        super(new Object[] { fldCode });
        this.fldCode = fldCode;
        //setKey ( fldCode.toString ( ) ) ; 
    }    public Long getFldCode() {        return fldCode;
    }}
