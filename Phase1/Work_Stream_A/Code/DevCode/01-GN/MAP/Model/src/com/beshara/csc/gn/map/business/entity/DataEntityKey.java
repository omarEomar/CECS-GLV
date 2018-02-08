package com.beshara.csc.gn.map.business.entity;
import com.beshara.base.entity.*;
public class DataEntityKey extends EntityKey implements IDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long objtypeCode;
    public Long socCode;
    public DataEntityKey() {    }    public DataEntityKey(Long objtypeCode, Long socCode) {        super(new Object[] { objtypeCode, socCode });
        this.objtypeCode = objtypeCode;
        this.socCode = socCode;
        //key= ( objtypeCode+"*"+socCode ) .toString ( ) ; 
    }    public Long getObjtypeCode() {        return objtypeCode;
    }    public Long getSocCode() {        return socCode;
    }}
