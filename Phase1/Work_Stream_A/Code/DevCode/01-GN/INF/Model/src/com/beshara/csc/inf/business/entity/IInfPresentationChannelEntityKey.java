package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.IEntityKey;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IInfPresentationChannelEntityKey extends IEntityKey {

    public int hashCode();

    Long getChannelId();

}
