package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.base.entity.*;
import java.sql.Date;
public class PersonsInformationEntityKey extends EntityKey implements IPersonsInformationEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long civilId;
    public Date registrationDate;
    public Long socCode;
    public PersonsInformationEntityKey() {    }    public PersonsInformationEntityKey(Long civilId, Date registrationDate,                                        Long socCode) {        super(new Object[] { civilId, registrationDate, socCode });
        this.civilId = civilId;
        this.registrationDate = registrationDate;
        this.socCode = socCode;
    }    public boolean equals(Object other) {        if (other instanceof PersonsInformationEntityKey) {            final PersonsInformationEntityKey otherPersonsInformationEntityPK =                 (PersonsInformationEntityKey)other;
            final boolean areEqual =                 (otherPersonsInformationEntityPK.civilId.equals(civilId) &&                  otherPersonsInformationEntityPK.registrationDate.equals(registrationDate) &&                  otherPersonsInformationEntityPK.socCode.equals(socCode));
            return areEqual;
        }        return false;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }    public Date getRegistrationDate() {        return registrationDate;
    }    public Long getSocCode() {        return socCode;
    }}
