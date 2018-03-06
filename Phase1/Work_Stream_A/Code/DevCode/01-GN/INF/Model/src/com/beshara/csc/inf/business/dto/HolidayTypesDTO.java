package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.HolidayTypesEntity;
import com.beshara.csc.inf.business.entity.HolidayTypesEntityKey;

import java.util.List;


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
public class

HolidayTypesDTO extends InfDTO implements IHolidayTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

//private Long holtypeCode ;
    private String holtypeName ; 
    private Long holtypeDays;
    private List<IHolidaysDTO> holidaysDTOList;
    private Long auditStatus;
    private Long tabrecSerial;

    /** 
     * HolidayTypesDTO Default Constructor */
    public HolidayTypesDTO() {
        super();
    }

    /** 
     * @param holidayTypesEntity 
     */
    public HolidayTypesDTO(HolidayTypesEntity holidayTypesEntity) { //this.holtypeCode = holidayTypesEntity.getHoltypeCode ( ) ; 
        this.holtypeName = holidayTypesEntity.getHoltypeName(); 
        setCode(new HolidayTypesEntityKey(holidayTypesEntity.getHoltypeCode()));
        if (holidayTypesEntity.getHoltypeName() != null)
            setName(holidayTypesEntity.getHoltypeName());
        if (holidayTypesEntity.getHoltypeDays() != null)
            this.holtypeDays = holidayTypesEntity.getHoltypeDays();
        if (holidayTypesEntity.getCreatedBy() != null)
            this.setCreatedBy(holidayTypesEntity.getCreatedBy());
        if (holidayTypesEntity.getCreatedDate() != null)
            this.setCreatedDate(holidayTypesEntity.getCreatedDate());
        if (holidayTypesEntity.getLastUpdatedBy() != null)
            this.setLastUpdatedBy(holidayTypesEntity.getLastUpdatedBy());
        if (holidayTypesEntity.getLastUpdatedDate() != null)
            this.setLastUpdatedDate(holidayTypesEntity.getLastUpdatedDate());
        if (holidayTypesEntity.getAuditStatus() != null)
            this.setAuditStatus(holidayTypesEntity.getAuditStatus());
        if (holidayTypesEntity.getTabrecSerial() != null)
            this.setTabrecSerial(holidayTypesEntity.getTabrecSerial());
    }

    public HolidayTypesDTO(Long code, String name) {
        this.setCode(new HolidayTypesEntityKey(code));
        this.setName(name);
    } ///** 
    //* @return Long 
    //*/ 
    //public Long getHoltypeCode ( ) { 
    // return holtypeCode ; 
    // } 
    ///** 
    /* @return String 
    */ 
    public String getHoltypeName ( ) { 
     return holtypeName ; 
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
    public Long getAuditStatus() {
        return auditStatus;
    }

    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    } ///** 
    //* @param holtypeCode 
    //*/ 
    //public void setHoltypeCode ( Long holtypeCode ) { 
    // this.holtypeCode=holtypeCode ; 
    // } 
    /** 
    * @param holtypeName 
    */ 
    public void setHoltypeName ( String holtypeName ) { 
     this.holtypeName=holtypeName ; 
     } 

    /** 
     * @param holtypeDays 
     */
    public void setHoltypeDays(Long holtypeDays) {
        this.holtypeDays = holtypeDays;
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

    public void setHolidaysDTOList(List<IHolidaysDTO> holidaysDTOList) {
        this.holidaysDTOList = holidaysDTOList;
    }

    public List<IHolidaysDTO> getHolidaysDTOList() {
        return holidaysDTOList;
    }
}
