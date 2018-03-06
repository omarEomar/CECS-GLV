package com.beshara.csc.inf.business.dto;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IInfPresentationChannelDTO extends IInfDTO {

    /**
     * @return Long
     */
    Long getChannelId();

    /**
     * @return String
     */
    String getName();

    /**
     * @return String
     */
    String getChannelNameEn();


    /**
     * @param channelId
     */
    public void setChannelId(Long channelId);

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * @param channelNameEn
     */
    public void setChannelNameEn(String channelNameEn);


}
