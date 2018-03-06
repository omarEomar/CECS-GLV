package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.inf.business.entity.HolidaysEntityKey;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.sql.Date;

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
public class HolidaysDTO extends InfDTO implements IHolidaysDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private IYearsDTO yearsDTO;
    private IHolidayTypesDTO holidayTypesDTO;
    private Date fromDate;
    private Date untilDate;
    private Long status;
    private boolean statusBoolean = true;
    private Long auditStatus;
    private Long tabrecSerial;
    private String holidayDesc;

    /**
     * HolidaysDTO Default Constructor */
    public HolidaysDTO() {
        super();
    }

    public HolidaysDTO(HolidaysEntity holidaysEntity) {
        setCode(new HolidaysEntityKey(holidaysEntity.getYearsEntity().getYearCode(),
                                      holidaysEntity.getHolidayTypesEntity().getHoltypeCode(),
                                      holidaysEntity.getFromDate()));
        setYearsDTO(new YearsDTO(holidaysEntity.getYearsEntity()));
        setHolidayTypesDTO(new HolidayTypesDTO(holidaysEntity.getHolidayTypesEntity()));
        setFromDate(holidaysEntity.getFromDate());
        setUntilDate(holidaysEntity.getUntilDate());
        setStatus(holidaysEntity.getStatus());
        setCreatedBy(holidaysEntity.getCreatedBy());
        setCreatedDate(holidaysEntity.getCreatedDate());
        setLastUpdatedBy(holidaysEntity.getLastUpdatedBy());
        setLastUpdatedDate(holidaysEntity.getLastUpdatedDate());
        setAuditStatus(holidaysEntity.getAuditStatus());
        setTabrecSerial(holidaysEntity.getTabrecSerial());
        if (holidaysEntity.getStatus() != null && holidaysEntity.getStatus().equals(ISystemConstant.NON_ACTIVE_FLAG)) {
            this.statusBoolean = false;
        }
        setHolidayDesc(holidaysEntity.getHolidayDescribtion());
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
     * @param status
     */
    public void setStatus(Long status) {
        this.status = status;
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

    public void setHolidayTypesDTO(IHolidayTypesDTO holidayTypesDTO) {
        this.holidayTypesDTO = holidayTypesDTO;
    }

    public IHolidayTypesDTO getHolidayTypesDTO() {
        return holidayTypesDTO;
    }

    public void setYearsDTO(IYearsDTO yearsDTO) {
        this.yearsDTO = yearsDTO;
    }

    public IYearsDTO getYearsDTO() {
        return yearsDTO;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setStatusBoolean(boolean statusBoolean) {
        if (statusBoolean == true) {
            this.setStatus(ISystemConstant.ACTIVE_FLAG);
        } else {
            this.setStatus(ISystemConstant.NON_ACTIVE_FLAG);
        }
        this.statusBoolean = statusBoolean;
    }

    public boolean isStatusBoolean() {
        return statusBoolean;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }
}
