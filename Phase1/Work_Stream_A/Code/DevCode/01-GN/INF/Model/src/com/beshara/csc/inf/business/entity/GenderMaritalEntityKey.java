package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class GenderMaritalEntityKey extends EntityKey implements IGenderMaritalEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gentypeCode;
    private Long marstatusCode;
    public GenderMaritalEntityKey() {        super();
    }    public GenderMaritalEntityKey(Long gentypeCode, Long marstatusCode) {        super(new Object[] { gentypeCode, marstatusCode });
        this.gentypeCode = gentypeCode;
        this.marstatusCode = marstatusCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getGentypeCode() {        return gentypeCode;
    }    public Long getMarstatusCode() {        return marstatusCode;
    }}
