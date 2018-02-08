package com.beshara.csc.gn.map.business.entity;
import com.beshara.base.entity.*;
public class ObjectTypesEntityKey extends EntityKey implements IObjectTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long objtypeCode;
    public ObjectTypesEntityKey() {    }    public ObjectTypesEntityKey(Long objtypeCode) {        super(new Object[] { objtypeCode });
        this.objtypeCode = objtypeCode;
        //key=objtypeCode.toString ( ) ; 
    }    public Long getObjtypeCode() {        return objtypeCode;
    }}
