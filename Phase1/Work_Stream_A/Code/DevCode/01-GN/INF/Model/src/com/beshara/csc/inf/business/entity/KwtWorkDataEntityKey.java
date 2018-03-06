package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.EntityKey;


public class KwtWorkDataEntityKey extends EntityKey implements IKwtWorkDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serial;

    public KwtWorkDataEntityKey() {
    }

    public KwtWorkDataEntityKey(Long serial) {
        super(new Object[] { serial });
        this.serial = serial;
    }

    public boolean equals(Object other) {
        if (other instanceof KwtWorkDataEntityKey) {
            final KwtWorkDataEntityKey otherKwtWorkDataPK = (KwtWorkDataEntityKey)other;
            final boolean areEqual =
                (otherKwtWorkDataPK.serial.equals(serial));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getSerial() {
        return serial;
    }
}
