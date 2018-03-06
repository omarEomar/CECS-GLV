package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

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
 * This Class Manipulate the Persistence Methods of InfCitizensResidentsData Entity.
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
@NamedQueries( { @NamedQuery(name = "InfCitizensResidentsDataEntity.findAll", 
                             query = 
                             "select o from InfCitizensResidentsDataEntity o")
        , 
        @NamedQuery(name = "InfCitizensResidentsDataEntity.findNewId", query = 
                    "select MAX(o.serial) from InfCitizensResidentsDataEntity o")
        , 
        @NamedQuery(name = "InfCitizensResidentsDataEntity.getByCivilID", query = 
                    "select o from InfCitizensResidentsDataEntity o where o.civilId=:civilId order by o.issueDate DESC")
        } )
@Table(name = "INF_CITIZENS_RESIDENTS_DATA")
@IdClass(IInfCitizensResidentsDataEntityKey.class)
public class

InfCitizensResidentsDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "RESIDENT_NO", nullable = false)
    private Long residentNo;
    @Column(name = "ISSUE_DATE", nullable = true)
    private Date issueDate;
    @Column(name = "EXP_DATE", nullable = true)
    private Date expDate;
    @Column(name = "RESTYPE_CODE", nullable = true)
    private Long restypeCode;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    //@ManyToOne 
    //@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
    //private InfKwtCitizensResidentsEntity infKwtCitizensResidentsEntity;
    //@ManyToOne 
    //@JoinColumn(name="RESTYPE_CODE", referencedColumnName="RESTYPE_CODE")
    //private InfResidentTypeEntity infResidentTypeEntity;


    /**
     * InfCitizensResidentsDataEntity Default Constructor
     */
    public InfCitizensResidentsDataEntity() {
    }


    /**
     * @return Long
     */
    public Long getSerial() {
        return serial;
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
    public Long getResidentNo() {
        return residentNo;
    }

    /**
     * @return Date
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * @return Date
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * @return Long
     */
    public Long getRestypeCode() {
        return restypeCode;
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
     * @param serial
     */
    public void setSerial(Long serial) {
        this.serial = serial;
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param residentNo
     */
    public void setResidentNo(Long residentNo) {
        this.residentNo = residentNo;
    }

    /**
     * @param issueDate
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @param expDate
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * @param restypeCode
     */
    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
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
