package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of WeekDays Entity.
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
@NamedQueries( { @NamedQuery(name = "WeekDaysEntity.findAll", 
                             query = "select o from WeekDaysEntity o order by o.daynum")
        , 
        @NamedQuery(name = "WeekDaysEntity.findNewId", query = "select MAX(o.daynum) from WeekDaysEntity o")
        , 
        @NamedQuery(name = "WeekDaysEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.WeekDaysDTO(o.daynum, o.dayName) from WeekDaysEntity o order by o.daynum")
        , 
        @NamedQuery(name = "WeekDaysEntity.searchByName", query = "select o from WeekDaysEntity o where o.dayName like :WeekDaysName order by o.dayName")
        , 
        @NamedQuery(name = "WeekDaysEntity.searchByCode", query = "select o from WeekDaysEntity o where o.daynum=:WeekDaysCode order by o.daynum")         
        } )
@Table(name = "INF_WEEK_DAYS")
@IdClass(IWeekDaysEntityKey.class)
public class

WeekDaysEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "DAYNUM", nullable = false)
    private Long daynum;
    @Column(name = "DAY_NAME", nullable = false)
    private String dayName;

    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * WeekDaysEntity Default Constructor
     */
    public WeekDaysEntity() {
    }


    /**
     * @return Long
     */
    public Long getDaynum() {
        return daynum;
    }

    /**
     * @return String
     */
    public String getDayName() {
        return dayName;
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
     * @param daynum
     */
    public void setDaynum(Long daynum) {
        this.daynum = daynum;
    }

    /**
     * @param dayName
     */
    public void setDayName(String dayName) {
        this.dayName = dayName;
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
