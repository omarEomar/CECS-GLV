package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;

import java.io.Serializable;

public class PersonQualificationsEntityKey extends EntityKey implements IPersonQualificationsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long civilId;
    public String qualificationKey;

    public PersonQualificationsEntityKey() {
        super();
    }

    public PersonQualificationsEntityKey(Long civilId, String qualificationKey) {
        super(new Object[] { civilId, qualificationKey });
        this.civilId = civilId;
        this.qualificationKey = qualificationKey;
    }
    /**
     *  this constructor was added for purpose of DB changes in qul table
     */
    public PersonQualificationsEntityKey(Long civilId, Long qualificationKey) {
        new PersonQualificationsEntityKey(civilId, qualificationKey.toString());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCivilId() {
        return civilId;
    }

    public String getQualificationKey() {
        return qualificationKey;
    }
}
