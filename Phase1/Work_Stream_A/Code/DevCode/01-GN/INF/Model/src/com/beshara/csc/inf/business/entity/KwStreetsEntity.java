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
 * This Class Manipulate the Persistence Methods of KwStreets Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
@Entity
@NamedQueries( { @NamedQuery(name = "KwStreetsEntity.findAll", 
                             query = "select o from KwStreetsEntity o order by o.streetCode")
        , 
        @NamedQuery(name = "KwStreetsEntity.findNewId", query = "select MAX(o.streetCode) from KwStreetsEntity o")
        , 
        @NamedQuery(name = "KwStreetsEntity.findName", query = "select o from KwStreetsEntity o where o.streetName like :name order by o.streetName")
        , 
        @NamedQuery(name = "KwStreetsEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.KwStreetsDTO(o.streetCode,o.streetName) from KwStreetsEntity o order by o.streetName")
        ,
        @NamedQuery(name = "KwStreetsEntity.getByName", query = "select o from KwStreetsEntity o where o.streetName =:name")                 
        ,                 
        @NamedQuery(name = "KwStreetsEntity.searchByCode",
                             query = "select o from KwStreetsEntity o where o.streetCode=:streetCode order by o.streetName")} )
@Table(name = "INF_KW_STREETS")
@IdClass(IKwStreetsEntityKey.class)
public class KwStreetsEntity extends BasicEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "STREET_CODE", nullable = false)
    private Long streetCode;
    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;
    @Column(name = "STREET_LENGTH_IN_KM", nullable = true)
    private Long streetLengthInKm;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;


    /**
     * KwStreetsEntity Default Constructor
     */
    public KwStreetsEntity() {
    }


    /**
     * @return Long
     */
    public Long getStreetCode() {
        return streetCode;
    }

    /**
     * @return String
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @return Long
     */
    public Long getStreetLengthInKm() {
        return streetLengthInKm;
    }

    /**
     * @return String
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @return String
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }


    /**
     * @param streetCode
     */
    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * @param streetName
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @param streetLengthInKm
     */
    public void setStreetLengthInKm(Long streetLengthInKm) {
        this.streetLengthInKm = streetLengthInKm;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


}
