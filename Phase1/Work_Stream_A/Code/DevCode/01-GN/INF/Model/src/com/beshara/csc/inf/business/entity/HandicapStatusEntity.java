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

/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of GroupCountries Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 *     
 * @author       Beshara Group   
 * @version      1.0   
 *
 * @author Aly Noor 
 * @version      2.0 
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */

@Entity
@NamedQueries( { @NamedQuery(name = "HandicapStatusEntity.findAll", 
                             query = "select o from HandicapStatusEntity o order by o.capstatusName")
        , 
        @NamedQuery(name = "HandicapStatusEntity.findNewId", query = "select MAX(o.capstatusCode) from HandicapStatusEntity o")
        , 
        @NamedQuery(name = "HandicapStatusEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.HandicapStatusDTO(o.capstatusCode,o.capstatusName) from HandicapStatusEntity o  where o.auditStatus IN (:notAduiting,:accepted) order by o.capstatusName")
        , 
        @NamedQuery(name = "HandicapStatusEntity.getCodeNameExceptOne", query = "select new com.beshara.csc.inf.business.dto.HandicapStatusDTO(o.capstatusCode,o.capstatusName) from HandicapStatusEntity o  where o.capstatusCode <> :natural order by o.capstatusName")
        ,          
        @NamedQuery(name = "HandicapStatusEntity.searchByName", query = "select o from HandicapStatusEntity o where o.capstatusName like :capStatusName order by o.capstatusName")
        , 
        @NamedQuery(name = "HandicapStatusEntity.searchByCode", query = "select o from HandicapStatusEntity o where o.capstatusCode=:capStatusCode order by o.capstatusName")
                         ,
        @NamedQuery(name = "HandicapStatusEntity.getByName", query = "select o from HandicapStatusEntity o where o.capstatusName =:name")                   
        } )
@Table(name = "INF_HANDICAP_STATUS")
@IdClass(IHandicapStatusEntityKey.class)
public class HandicapStatusEntity  extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CAPSTATUS_CODE", nullable = false)
    private Long capstatusCode;
    @Column(name = "CAPSTATUS_NAME")
    private String capstatusName;
    @Column(name = "HANDICAP_RATIO", nullable = false)
    private Double handicapRatio;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    public HandicapStatusEntity() {
    }


    public Long getCapstatusCode() {
        return capstatusCode;
    }

    public void setCapstatusCode(Long capstatusCode) {
        this.capstatusCode = capstatusCode;
    }

    public String getCapstatusName() {
        return capstatusName;
    }

    public void setCapstatusName(String capstatusName) {
        this.capstatusName = capstatusName;
    }


    public Double getHandicapRatio() {
        return handicapRatio;
    }

    public void setHandicapRatio(Double handicapRatio) {
        this.handicapRatio = handicapRatio;
    }



    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
