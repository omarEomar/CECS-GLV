package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;
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
 * This Class Manipulate the Persistence Methods of Holidays Entity.
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
@NamedQueries( { @NamedQuery(name = "HolidaysEntity.findAll", query = "select o from HolidaysEntity o where o.holidayTypesEntity.realFlag=1"),
        @NamedQuery(name = "HolidaysEntity.findNewId", query = "select MAX(o.yearCode) from HolidaysEntity o"),
        @NamedQuery(name = "HolidaysEntity.findEntityById", query = "select o from HolidaysEntity o where o.yearCode=:yearCode and o.holtypeCode=:holtypeCode and o.holidayTypesEntity.realFlag=1"),
        @NamedQuery(name = "HolidaysEntity.findOfficialVacation", query = "select o from HolidaysEntity o where o.yearsEntity.yearCode=:yearCode and o.holidayTypesEntity.holtypeCode NOT IN (11) and o.holidayTypesEntity.realFlag=1"),
        @NamedQuery(name = "HolidaysEntity.getVacation", query = "select o from HolidaysEntity o where o.status=:status AND (o.fromDate BETWEEN :fromDate AND :untilDate OR o.untilDate BETWEEN :fromDate AND :untilDate OR (o.fromDate < :fromDate AND o.untilDate > :untilDate )) and o.holidayTypesEntity.realFlag=1"),
        @NamedQuery(name = "HolidaysEntity.search", query = "select o  from HolidaysEntity o where  o.yearCode = :yearCode and (o. holtypeCode = :holtypeCode OR :holtypeCode IS NULL) and ((o.fromDate >= :fromDate and :untilDate  IS NULL) OR  (o.untilDate <= :untilDate and :fromDate IS NULL)  OR ( o.untilDate <= :untilDate and o.fromDate >= :fromDate ) or (:fromDate IS NULL and :untilDate IS NULL) ) and o.holidayTypesEntity.realFlag=1"),
        @NamedQuery(name = "HolidaysEntity.getAllByYearAndTypeCode", query = "select o  from HolidaysEntity o where  o.yearCode = :yearCode and o. holtypeCode = :holtypeCode order by o.yearCode desc")})

@Table(name = "INF_HOLIDAYS")
@IdClass(IHolidaysEntityKey.class)
public class HolidaysEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "YEAR_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long yearCode;
    @Id
    @Column(name = "HOLTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long holtypeCode;
    @Id
    @Column(name = "FROM_DATE", nullable = false )
    private Date fromDate;
    
    @Column(name = "UNTIL_DATE", nullable = false)
    private Date untilDate;
    @Column(name = "STATUS", nullable = false)
    private Long status;
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
    @Column(name = "HOLIDAY_DESC", nullable = true)
    private String holidayDescribtion;
    @ManyToOne
    @JoinColumn(name = "HOLTYPE_CODE", referencedColumnName = "HOLTYPE_CODE")
    private HolidayTypesEntity holidayTypesEntity;
    @ManyToOne
    @JoinColumn(name = "YEAR_CODE", referencedColumnName = "YEAR_CODE")
    private YearsEntity yearsEntity;
    

    /**
     * HolidaysEntity Default Constructor
     */
    public HolidaysEntity() {
    }


    /**
     * @return Long
     */
    public Long getYearCode() {
        return yearCode;
    }

    /**
     * @return Long
     */
    public Long getHoltypeCode() {
        return holtypeCode;
    }

    /**
     * @return Timestamp
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @return Timestamp
     */
    public Date getUntilDate() {
        return untilDate;
    }

    /**
     * @return Long
     */
    public Long getStatus() {
        return status;
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
     * @param yearCode
     */
    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    /**
     * @param holtypeCode
     */
    public void setHoltypeCode(Long holtypeCode) {
        this.holtypeCode = holtypeCode;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @param untilDate
     */
    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    /**
     * @param status
     */
    public void setStatus(Long status) {
        this.status = status;
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


    public void setHolidayTypesEntity(HolidayTypesEntity holidayTypesEntity) {
        this.holidayTypesEntity = holidayTypesEntity;
    }

    public HolidayTypesEntity getHolidayTypesEntity() {
        return holidayTypesEntity;
    }

    public void setYearsEntity(YearsEntity yearsEntity) {
        this.yearsEntity = yearsEntity;
    }

    public YearsEntity getYearsEntity() {
        return yearsEntity;
    }

    public void setHolidayDescribtion(String holidayDescribtion) {
        this.holidayDescribtion = holidayDescribtion;
    }

    public String getHolidayDescribtion() {
        return holidayDescribtion;
    }
}
