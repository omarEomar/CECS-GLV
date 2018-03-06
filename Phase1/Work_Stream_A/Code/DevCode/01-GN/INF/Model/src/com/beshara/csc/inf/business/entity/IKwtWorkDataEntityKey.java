package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.IEntityKey;


public interface IKwtWorkDataEntityKey extends IEntityKey {
    public boolean equals(Object other);

    public int hashCode();

    public Long getSerial();

}
