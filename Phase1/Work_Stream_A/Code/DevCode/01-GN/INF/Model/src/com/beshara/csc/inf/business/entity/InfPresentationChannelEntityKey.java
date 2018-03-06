package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfPresentationChannelEntityKey extends EntityKey implements IInfPresentationChannelEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long channelId;


    public InfPresentationChannelEntityKey() {
        super();
    }

    public InfPresentationChannelEntityKey(Long channelId) {
        super(new Object[] { channelId });
        this.channelId = channelId;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getChannelId() {
        return channelId;
    }

}
