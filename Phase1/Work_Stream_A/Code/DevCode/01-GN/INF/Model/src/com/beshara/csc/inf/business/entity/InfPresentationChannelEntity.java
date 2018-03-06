package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Entity
@NamedQueries( { @NamedQuery(name = "InfPresentationChannelEntity.findAll",
                             query = "select o from InfPresentationChannelEntity o"),
                 @NamedQuery(name = "InfPresentationChannelEntity.findNewId",
                             query = "select MAX(o.channelId) from InfPresentationChannelEntity o"),
                 @NamedQuery(name = "InfPresentationChannelEntity.searchByCode",
                             query = "select o from InfPresentationChannelEntity o where o.channelId=:code"),

        @NamedQuery(name = "InfPresentationChannelEntity.searchByName",
                    query = "select o from InfPresentationChannelEntity o where o.name like :name order by o.name") })
@Table(name = "E_SERVICE_PRESENTATION_CHANNEL")
@IdClass(IInfPresentationChannelEntityKey.class)

public class InfPresentationChannelEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CHANNEL_ID", nullable = false)
    private Long channelId;
    @Column(name = "CHANNEL_NAME_AR", nullable = true)
    private String name;
    @Column(name = "CHANNEL_NAME_EN", nullable = true)
    private String channelNameEn;


    /**
     * InfPresentationChannelEntity Default Constructor
     */
    public InfPresentationChannelEntity() {
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
