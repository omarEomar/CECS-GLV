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
 * This Class Manipulate the Persistence Methods of InfCitizensPassports Entity.
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
@NamedQueries( { @NamedQuery(name = "InfCitizensPassportsEntity.findAll", 
                             query = 
                             "select o from InfCitizensPassportsEntity o")
        , 
        @NamedQuery(name = "InfCitizensPassportsEntity.findNewId", query = "select MAX(o.serialNo) from InfCitizensPassportsEntity o")
                         , 
        @NamedQuery(name = "InfCitizensPassportsEntity.getByPassportNo", query = "select o from InfCitizensPassportsEntity o where o.passportNo =:passportNo and o.civilId=:civilId")
        , 
        @NamedQuery(name = "InfCitizensPassportsEntity.getByCivilID", query = "select o from InfCitizensPassportsEntity o where o.civilId=:civilId order by o.issueDate DESC")
        } )
@Table(name = "INF_CITIZENS_PASSPORTS")
@IdClass(IInfCitizensPassportsEntityKey.class)
public class

InfCitizensPassportsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL_NO", nullable = false)
    private Long serialNo;
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "PASSPORT_NO", nullable = false)
    private String passportNo;
    @Column(name = "ISSUE_COUNTRY", nullable = true)
    private Long issueCountry;
    @Column(name = "ISSUE_DATE", nullable = true)
    private Date issueDate;
    @Column(name = "EXP_DATE", nullable = true)
    private Date expDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    //@ManyToOne 
    //@JoinColumn(name="ISSUE_COUNTRY", referencedColumnName="CNTRY_CODE")
    //private InfCountriesEntity infCountriesEntity;
    //@ManyToOne 
    //@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
    //private InfKwtCitizensResidentsEntity infKwtCitizensResidentsEntity;


    /**
     * InfCitizensPassportsEntity Default Constructor
     */
    public InfCitizensPassportsEntity() {
    }


    /**
     * @return Long
     */
    public Long getSerialNo() {
        return serialNo;
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
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @return Long
     */
    public Long getIssueCountry() {
        return issueCountry;
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
     * @param serialNo
     */
    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param passportNo
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @param issueCountry
     */
    public void setIssueCountry(Long issueCountry) {
        this.issueCountry = issueCountry;
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
