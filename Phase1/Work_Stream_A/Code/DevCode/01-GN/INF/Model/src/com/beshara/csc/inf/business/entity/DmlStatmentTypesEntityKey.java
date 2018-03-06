package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class DmlStatmentTypesEntityKey extends EntityKey implements IDmlStatmentTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long dmlstatypeCode;
    public DmlStatmentTypesEntityKey() {        super();
    }    public DmlStatmentTypesEntityKey(Long dmlstatypeCode) {        super(new Object[] { dmlstatypeCode });
        this.dmlstatypeCode = dmlstatypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getDmlstatypeCode() {        return dmlstatypeCode;
    }}
