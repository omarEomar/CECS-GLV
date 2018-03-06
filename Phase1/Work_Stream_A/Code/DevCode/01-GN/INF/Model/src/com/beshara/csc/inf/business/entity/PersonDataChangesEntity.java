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
 * This Class Manipulate the Persistence Methods of PersonDataChanges Entity.
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
@NamedQueries( { @NamedQuery(name = "PersonDataChangesEntity.findAll", 
                             query = "select o from PersonDataChangesEntity o")
        , 
        @NamedQuery(name = "PersonDataChangesEntity.findNewId", query = "select MAX(o.civilId) from PersonDataChangesEntity o")
        } )
@Table(name = "INF_PERSON_DATA_CHANGES")
@IdClass(IPersonDataChangesEntityKey.class)
public class PersonDataChangesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Id
    @Column(name = "PARAMETER_CODE", nullable = false)
    private Long parameterCode;
    @Id
    @Column(name = "CHANGE_DATETIME", nullable = false)
    private Timestamp changeDatetime;
    @Column(name = "DMLSTATYPE_CODE", nullable = false)
    private Long dmlstatypeCode;
    @Column(name = "OLD_VALUE", nullable = true)
    private String oldValue;
    @Column(name = "NEW_VALUE", nullable = true)
    private String newValue;
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
    //@ManyToOne 
    //@JoinColumn(name="PARAMETER_CODE", referencedColumnName="PARAMETER_CODE")
    //private GrsParametersEntity grsParametersEntity;
    //@ManyToOne 
    //@JoinColumn(name="DMLSTATYPE_CODE", referencedColumnName="DMLSTATYPE_CODE")
    //private DmlStatmentTypesEntity dmlStatmentTypesEntity;
    //@ManyToOne 
    //@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
    //private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;


    /**
     * PersonDataChangesEntity Default Constructor
     */
    public PersonDataChangesEntity() {
    }


    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return Long
     */
    public Long getParameterCode() {
        return parameterCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getChangeDatetime() {
        return changeDatetime;
    }

    /**
     * @return Long
     */
    public Long getDmlstatypeCode() {
        return dmlstatypeCode;
    }

    /**
     * @return String
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * @return String
     */
    public String getNewValue() {
        return newValue;
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
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param parameterCode
     */
    public void setParameterCode(Long parameterCode) {
        this.parameterCode = parameterCode;
    }

    /**
     * @param changeDatetime
     */
    public void setChangeDatetime(Timestamp changeDatetime) {
        this.changeDatetime = changeDatetime;
    }

    /**
     * @param dmlstatypeCode
     */
    public void setDmlstatypeCode(Long dmlstatypeCode) {
        this.dmlstatypeCode = dmlstatypeCode;
    }

    /**
     * @param oldValue
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * @param newValue
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
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
