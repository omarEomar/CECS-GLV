package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class FieldTypesEntityKey extends EntityKey implements IFieldTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long fldtypeCode;
    public FieldTypesEntityKey() {    }    public FieldTypesEntityKey(Long fldtypeCode) {        super(new Object[] { fldtypeCode });
        this.fldtypeCode = fldtypeCode;
        //setKey ( fldtypeCode.toString ( ) ) ; 
    }    public Long getFldtypeCode() {        return fldtypeCode;
    }}
