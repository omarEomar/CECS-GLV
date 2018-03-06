package com.beshara.csc.inf.business.entity;

import com.beshara.base.enhanced.entity.BaseEntity;
import com.beshara.base.entity.BaseEntityListener;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of StreetZones Entity.
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
@NamedQueries( { @NamedQuery(name = "StreetZonesEntity.findAll", query = "select o from StreetZonesEntity o"),
                 @NamedQuery(name = "StreetZonesEntity.getByMapCode",
                             query = "select o.kwStreetsEntity from StreetZonesEntity o where o.mapCode = :mapCode  ORDER BY o.streetCode"),
                 @NamedQuery(name = "StreetZonesEntity.getNotSelectedByMapCode",
                             query = "select o from KwStreetsEntity o where o.streetCode NOT IN (select s.streetCode from StreetZonesEntity s where s.mapCode = :mapCode) ORDER BY o.streetCode"),
                 @NamedQuery(name = "StreetZonesEntity.findNewId",
                             query = "select MAX(o.mapCode) from StreetZonesEntity o") })
@Table(name = "INF_STREET_ZONES")
@IdClass(IStreetZonesEntityKey.class)

public class StreetZonesEntity extends  BaseEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MAP_CODE", nullable = false, insertable = false, updatable = false)
    private String mapCode;
    @Id
    @Column(name = "STREET_CODE", nullable = false, insertable = false, updatable = false)
    private Long streetCode;
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "MAP_CODE", referencedColumnName = "MAP_CODE")
    private KwMapEntity kwMapEntity;
    @ManyToOne
    @JoinColumn(name = "STREET_CODE", referencedColumnName = "STREET_CODE")
    private KwStreetsEntity kwStreetsEntity;


    /**
     * StreetZonesEntity Default Constructor
     */
    public StreetZonesEntity() {
    }


    /**
     * @return String
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * @return Long
     */
    public Long getStreetCode() {
        return streetCode;
    }

    /**
     * @return Long
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
     * @return Long
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
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param mapCode
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    /**
     * @param streetCode
     */
    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
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

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


    public void setKwMapEntity(KwMapEntity kwMapEntity) {
        this.kwMapEntity = kwMapEntity;
    }

    public KwMapEntity getKwMapEntity() {
        return kwMapEntity;
    }

    public void setKwStreetsEntity(KwStreetsEntity kwStreetsEntity) {
        this.kwStreetsEntity = kwStreetsEntity;
    }

    public KwStreetsEntity getKwStreetsEntity() {
        return kwStreetsEntity;
    }
}
