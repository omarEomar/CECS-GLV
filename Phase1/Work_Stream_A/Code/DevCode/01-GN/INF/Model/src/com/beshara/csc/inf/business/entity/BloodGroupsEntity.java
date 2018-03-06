package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of BloodGroups Entity.
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
@NamedQueries( { @NamedQuery(name = "BloodGroupsEntity.findAll", 
                             query = "select o from BloodGroupsEntity o order by o.bldgroupName")
        , 
        @NamedQuery(name = "BloodGroupsEntity.findNewId", query = "select MAX(o.bldgroupCode) from BloodGroupsEntity o")
        ,
        @NamedQuery(name = "BloodGroupsEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.BloodGroupsDTO(o.bldgroupCode,o.bldgroupName) from BloodGroupsEntity o order by o.bldgroupName")
        , 
        @NamedQuery(name = "BloodGroupsEntity.searchByName", query = "select o from BloodGroupsEntity o where o.bldgroupName like :bldgroupName order by o.bldgroupName")
        , 
        @NamedQuery(name = "BloodGroupsEntity.searchByCode", query = "select o from BloodGroupsEntity o where o.bldgroupCode=:bldgroupCode order by o.bldgroupName")
                 ,
        @NamedQuery(name = "BloodGroupsEntity.getByName", query = "select o from BloodGroupsEntity o where o.bldgroupName =:name")          
        } )
@Table(name = "INF_BLOOD_GROUPS")
@IdClass(IBloodGroupsEntityKey.class)
public class

BloodGroupsEntity  extends BasicEntity   {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "BLDGROUP_CODE", nullable = false)
    private Long bldgroupCode;
    @Column(name = "BLDGROUP_NAME", nullable = false)
    private String bldgroupName;
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


    /**
     * BloodGroupsEntity Default Constructor
     */
    public BloodGroupsEntity() {
    }


    /**
     * @return Long
     */
    public Long getBldgroupCode() {
        return bldgroupCode;
    }

    /**
     * @return String
     */
    public String getBldgroupName() {
        return bldgroupName;
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
     * @param bldgroupCode
     */
    public void setBldgroupCode(Long bldgroupCode) {
        this.bldgroupCode = bldgroupCode;
    }

    /**
     * @param bldgroupName
     */
    public void setBldgroupName(String bldgroupName) {
        this.bldgroupName = bldgroupName;
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


}
