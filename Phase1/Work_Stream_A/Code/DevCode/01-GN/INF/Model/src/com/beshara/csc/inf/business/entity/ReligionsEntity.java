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
 * This Class Manipulate the Persistence Methods of Religions Entity.
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
@NamedQueries( { @NamedQuery(name = "ReligionsEntity.findAll",
                             query = "select o from ReligionsEntity o order by o.religionCode"),
                 @NamedQuery(name = "ReligionsEntity.findNewId",
                             query = "select MAX(o.religionCode) from ReligionsEntity o"),
                 @NamedQuery(name = "ReligionsEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.ReligionsDTO(o.religionCode,o.religionName) from ReligionsEntity o order by o.religionName"),
                 @NamedQuery(name = "ReligionsEntity.searchByName",
                             query = "select o from ReligionsEntity o where o.religionName like :religionName order by o.religionCode"),
                 @NamedQuery(name = "ReligionsEntity.checkDublicateName",
                             query = "select o from ReligionsEntity o where o.religionName = :name"),
                 @NamedQuery(name = "ReligionsEntity.searchByCode",
                             query = "select o from ReligionsEntity o where o.religionCode=:religionCode order by o.religionCode") })
@Table(name = "INF_RELIGIONS")
@IdClass(IReligionsEntityKey.class)
public class ReligionsEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "RELIGION_CODE", nullable = false)
    private Long religionCode;

    @Column(name = "RELIGION_NAME", nullable = false)
    private String religionName;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
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
     * ReligionsEntity Default Constructor
     */
    public ReligionsEntity() {
    }


    /**
     * @return Long
     */
    public Long getReligionCode() {
        return religionCode;
    }

    /**
     * @return String
     */
    public String getReligionName() {
        return religionName;
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
     * @param religionCode
     */
    public void setReligionCode(Long religionCode) {
        this.religionCode = religionCode;
    }

    /**
     * @param religionName
     */
    public void setReligionName(String religionName) {
        this.religionName = religionName;
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


    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }


}
