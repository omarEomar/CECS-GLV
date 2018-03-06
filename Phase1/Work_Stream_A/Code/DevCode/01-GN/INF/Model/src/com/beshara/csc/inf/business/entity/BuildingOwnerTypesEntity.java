package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// @EditedBy @author Aly Noor @since 06/26/2014 eidted to extends BasicEntity
// to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName

@Entity
@NamedQueries( { @NamedQuery(name = "BuildingOwnerTypesEntity.findAll", 
                             query = "select o from BuildingOwnerTypesEntity o order by o.owntypeName"), 
			     @NamedQuery(name = "BuildingOwnerTypesEntity.findNewId", 
			                 query = "select MAX(o.owntypeCode) from BuildingOwnerTypesEntity o"), 
			     @NamedQuery(name = "BuildingOwnerTypesEntity.getCodeName", 
			                 query = "select new com.beshara.csc.inf.business.dto.BuildingOwnerTypesDTO(o.owntypeCode,o.owntypeName) from BuildingOwnerTypesEntity o order by o.owntypeName"), 
			     @NamedQuery(name = "BuildingOwnerTypesEntity.searchByName", 
			                 query = "select o from BuildingOwnerTypesEntity o where o.owntypeName like :owntypeName order by o.owntypeName"), 
			     @NamedQuery(name = "BuildingOwnerTypesEntity.searchByCode", 
			                 query = "select o from BuildingOwnerTypesEntity o where o.owntypeCode=:owntypeCode order by o.owntypeName"),
                 @NamedQuery(name = "BuildingOwnerTypesEntity.getByName",
                             query = "select o from BuildingOwnerTypesEntity o where o.owntypeName =:name") })


@Table(name = "INF_BUILDING_OWNER_TYPES")
@IdClass(IBuildingOwnerTypesEntityKey.class)
public class BuildingOwnerTypesEntity   extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "OWNTYPE_CODE", nullable = false)
    private Long owntypeCode;
    @Column(name = "OWNTYPE_NAME", nullable = false)
    private String owntypeName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    public BuildingOwnerTypesEntity() {
    }

    public Long getOwntypeCode() {
        return owntypeCode;
    }

    public void setOwntypeCode(Long owntypeCode) {
        this.owntypeCode = owntypeCode;
    }

    public String getOwntypeName() {
        return owntypeName;
    }

    public void setOwntypeName(String owntypeName) {
        this.owntypeName = owntypeName;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
