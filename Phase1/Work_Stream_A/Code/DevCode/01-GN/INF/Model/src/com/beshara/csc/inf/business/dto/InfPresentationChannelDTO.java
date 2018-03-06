package com.beshara.csc.inf.business.dto;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */


public class InfPresentationChannelDTO extends InfDTO implements IInfPresentationChannelDTO {

    @SuppressWarnings("compatibility:7850104707659145384")
    private static final long serialVersionUID = 1L;

    private Long channelId;
    private String name;
    private String channelNameEn;


    /**
     * InfPresentationChannelDTO Default Constructor
     */
    public InfPresentationChannelDTO() {
        super();
    }


    /**
     * @return Long
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getChannelNameEn() {
        return channelNameEn;
    }


    /**
     * @param channelId
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param channelNameEn
     */
    public void setChannelNameEn(String channelNameEn) {
        this.channelNameEn = channelNameEn;
    }


}

