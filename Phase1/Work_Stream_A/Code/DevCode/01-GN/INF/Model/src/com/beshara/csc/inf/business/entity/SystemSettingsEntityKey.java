package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class SystemSettingsEntityKey extends EntityKey implements ISystemSettingsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long syssettingCode;
    public SystemSettingsEntityKey() {        super();
    }    public SystemSettingsEntityKey(Long syssettingCode) {        super(new Object[] { syssettingCode });
        this.syssettingCode = syssettingCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getSyssettingCode() {        return syssettingCode;
    }}
