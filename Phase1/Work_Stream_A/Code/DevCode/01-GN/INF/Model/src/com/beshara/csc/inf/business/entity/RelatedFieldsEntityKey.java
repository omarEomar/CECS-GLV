package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class RelatedFieldsEntityKey extends EntityKey implements IRelatedFieldsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long fldCode;
    public Long fldCodeReferenced;
    public RelatedFieldsEntityKey() {    }    public RelatedFieldsEntityKey(Long fldCode, Long fldCodeReferenced) {        super(new Object[] { fldCode, fldCodeReferenced });
        this.fldCode = fldCode;
        this.fldCodeReferenced = fldCodeReferenced;
    }    public Long getFldCode() {        return fldCode;
    }    public Long getFldCodeReferenced() {        return fldCodeReferenced;
    }}
