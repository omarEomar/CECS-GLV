package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of HolidayTypes Entity.
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
@NamedQueries( { @NamedQuery(name = "HolidayTypesEntity.findAll", 
                             query = "select o from HolidayTypesEntity o where o.holtypeCode NOT IN (11) and o.realFlag=1")
        , 
        @NamedQuery(name = "HolidayTypesEntity.findNewId", query = "select MAX(o.holtypeCode) from HolidayTypesEntity o")
        , 
        @NamedQuery(name = "HolidayTypesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.HolidayTypesDTO(o.holtypeCode,o.holtypeName) from HolidayTypesEntity o where o.holtypeCode <> 11 and o.realFlag=1")
        , 
        @NamedQuery(name = "HolidayTypesEntity.listAvailabe", query = "select new com.beshara.csc.inf.business.dto.HolidayTypesDTO(o.holtypeCode,o.holtypeName) from HolidayTypesEntity o WHERE o.holtypeCode NOT IN (select h.holtypeCode from HolidaysEntity h WHERE h.yearsEntity.yearCode=:yearCode) and o.realFlag=1")
        ,
        @NamedQuery(name = "HolidayTypesEntity.listAvailabeEntity", query = "select o from HolidayTypesEntity o WHERE o.holtypeCode NOT IN (select h.holtypeCode from HolidaysEntity h WHERE h.yearsEntity.yearCode=:yearCode ) and o.holtypeCode <> 11 and o.realFlag=1")
        
         ,
        @NamedQuery(name = "HolidayTypesEntity.getByName", query = "select o from HolidayTypesEntity o where o.holtypeName =:name and o.realFlag=1") 
        
        ,
        @NamedQuery(name = "HolidayTypesEntity.searchByName",
                             query = "select o from HolidayTypesEntity o where o.holtypeName like :holtypeName and o.holtypeCode NOT IN (11) and o.realFlag=1 order by o.holtypeName")
        ,
        @NamedQuery(name = "HolidayTypesEntity.searchByCode",
                             query = "select o from HolidayTypesEntity o where o.holtypeCode=:holtypeCode and o.holtypeCode NOT IN (11) and o.realFlag=1 order by o.holtypeName")  
                     , 
        @NamedQuery(name = "HolidayTypesEntity.getCodeNameForActiveHoliday", query = "select new com.beshara.csc.inf.business.dto.HolidayTypesDTO(o.holtypeCode,o.holtypeName) from HolidayTypesEntity o where o.realFlag=1")    
                 
                 } )
@Table(name = "INF_HOLIDAY_TYPES")
@IdClass(IHolidayTypesEntityKey.class)
public class HolidayTypesEntity  extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HOLTYPE_CODE", nullable = false)
    private Long holtypeCode;
    @Column(name = "HOLTYPE_NAME", nullable = false)
    private String holtypeName;
    @Column(name = "HOLTYPE_DAYS", nullable = false)
    private Long holtypeDays;
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
    @Column(name = "REAl_FLAG", nullable = false)
    private Long realFlag;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @OneToMany(mappedBy = "holidayTypesEntity")
    private List<HolidaysEntity> holidaysEntityList;


    /**
     * HolidayTypesEntity Default Constructor
     */
    public HolidayTypesEntity() {
    }


    /**
     * @return Long
     */
    public Long getHoltypeCode() {
        return holtypeCode;
    }

    /**
     * @return String
     */
    public String getHoltypeName() {
        return holtypeName;
    }

    /**
     * @return Long
     */
    public Long getHoltypeDays() {
        return holtypeDays;
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
     * @param holtypeCode
     */
    public void setHoltypeCode(Long holtypeCode) {
        this.holtypeCode = holtypeCode;
    }

    /**
     * @param holtypeName
     */
    public void setHoltypeName(String holtypeName) {
        this.holtypeName = holtypeName;
    }

    /**
     * @param holtypeDays
     */
    public void setHoltypeDays(Long holtypeDays) {
        this.holtypeDays = holtypeDays;
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


    public void setHolidaysEntityList(List<HolidaysEntity> holidaysEntityList) {
        this.holidaysEntityList = holidaysEntityList;
    }

    public List<HolidaysEntity> getHolidaysEntityList() {
        return holidaysEntityList;
    }
}
