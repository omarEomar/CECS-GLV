package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class GenderTypesEntityKey extends EntityKey implements IGenderTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gentypeCode;
    public GenderTypesEntityKey() {        super();
    }    public GenderTypesEntityKey(Long gentypeCode) {        super(new Object[] { gentypeCode });
        this.gentypeCode = gentypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getGentypeCode() {        return gentypeCode;
    }}
