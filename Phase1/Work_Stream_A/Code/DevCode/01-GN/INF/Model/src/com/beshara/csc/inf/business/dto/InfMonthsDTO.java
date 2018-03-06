package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfMonthsEntity;
import com.beshara.csc.inf.business.entity.InfMonthsEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Fitiany    03-SEP-2007     Created
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
public class InfMonthsDTO extends InfDTO implements IInfMonthsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long monthCode;
    private String monthName;
    private Long createdBy;
    private Timestamp createdDate;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private Long auditStatus;
    private Long tabrecSerial;


    /**
     * GenderTypesDTO Default Constructor */
    public InfMonthsDTO() {
        super();
    }

    public InfMonthsDTO(Long code, String name) {
        setCode(new InfMonthsEntityKey(code));
        setName(name);
    }

    /**
     * @param genderTypesEntity
     */
    public InfMonthsDTO(InfMonthsEntity infMonthsEntity) {
        setCode(new InfMonthsEntityKey(infMonthsEntity.getMonthCode()));
        setName(infMonthsEntity.getMonthName());
        this.monthCode = infMonthsEntity.getMonthCode();
        this.monthName = infMonthsEntity.getMonthName();
        this.setCreatedBy(infMonthsEntity.getCreatedBy());
        this.setCreatedDate(infMonthsEntity.getCreatedDate());
        this.setLastUpdatedBy(infMonthsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(infMonthsEntity.getLastUpdatedDate());
        this.auditStatus = infMonthsEntity.getAuditStatus();
        this.tabrecSerial = infMonthsEntity.getTabrecSerial();
    }

    /**
     * @return Long
     */
    public Long getMonthCode() {
        return monthCode;
    }

    /**
     * @return String
     */
    public String getMonthName() {
        return monthName;
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
     * @param monthCode
     */
    public void setMonthCode(Long monthCode) {
        this.monthCode = monthCode;
    }

    /**
     * @param monthName
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    } /**
     * @param createdBy
     */

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

