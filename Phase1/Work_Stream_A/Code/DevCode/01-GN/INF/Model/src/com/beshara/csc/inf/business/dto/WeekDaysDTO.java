package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.WeekDaysEntity;
import com.beshara.csc.inf.business.entity.WeekDaysEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
/** 
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ; 
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ; 
 * - Add Javadoc Comments to Methods. * * @author Beshara Group 
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany 
 * @version 1.0 
 * @since 03/09/2007 
 */
public class WeekDaysDTO extends InfDTO implements IWeekDaysDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long createdBy;
    private Timestamp createdDate;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * WeekDaysDTO Default Constructor */
    public WeekDaysDTO() {        super();
    }    /** 
     * @param weekDaysEntity 
     */
    public WeekDaysDTO(WeekDaysEntity weekDaysEntity) {        this.setCode(new WeekDaysEntityKey(weekDaysEntity.getDaynum()));
        this.setName(weekDaysEntity.getDayName());
        this.createdBy = weekDaysEntity.getCreatedBy();
        this.createdDate = weekDaysEntity.getCreatedDate();
        this.lastUpdatedBy = weekDaysEntity.getLastUpdatedBy();
        this.lastUpdatedDate = weekDaysEntity.getLastUpdatedDate();
        this.auditStatus = weekDaysEntity.getAuditStatus();
        this.tabrecSerial = weekDaysEntity.getTabrecSerial();
    }    public WeekDaysDTO(Long code, String name) {        this.setCode(new WeekDaysEntityKey(code));
        this.setName(name);
    }    /** 
     * @return Long 
     */
    public Long getCreatedBy() {        return createdBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getCreatedDate() {        return createdDate;
    }    /** 
     * @return Long 
     */
    public Long getLastUpdatedBy() {        return lastUpdatedBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getLastUpdatedDate() {        return lastUpdatedDate;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param createdBy 
     */
    public void setCreatedBy(Long createdBy) {        this.createdBy = createdBy;
    }    /** 
     * @param createdDate 
     */
    public void setCreatedDate(Timestamp createdDate) {        this.createdDate = createdDate;
    }    /** 
     * @param lastUpdatedBy 
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {        this.lastUpdatedBy = lastUpdatedBy;
    }    /** 
     * @param lastUpdatedDate 
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {        this.lastUpdatedDate = lastUpdatedDate;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
