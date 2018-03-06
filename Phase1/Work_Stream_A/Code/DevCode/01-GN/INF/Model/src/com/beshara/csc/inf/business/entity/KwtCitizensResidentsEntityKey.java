package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.EntityKey;

public class KwtCitizensResidentsEntityKey extends EntityKey implements IKwtCitizensResidentsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;

    public KwtCitizensResidentsEntityKey() {
        super();
    }

    public KwtCitizensResidentsEntityKey(Long civilId) {
        super(new Object[] { civilId });
        this.civilId = civilId;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCivilId() {
        return civilId;
    }
}
