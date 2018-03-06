package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class InfPersonQualificationsEntityKey extends EntityKey implements IInfPersonQualificationsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long civilId;
    public String qualificationKey;
    public InfPersonQualificationsEntityKey() {        super();
    }    public InfPersonQualificationsEntityKey(Long civilId,                                             String qualificationKey) {        super(new Object[] { civilId, qualificationKey });
        this.civilId = civilId;
        this.qualificationKey = qualificationKey;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }    public String getQualificationKey() {        return qualificationKey;
    }}
