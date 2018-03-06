package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.PersonDataChangesEntity;
import java.io.Serializable;
import java.sql.Timestamp;
/** 
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ; 
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ; 
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group 
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany 
 * @version 1.0 
 * @since 03/09/2007 
 */
public class PersonDataChangesDTO extends InfDTO implements IPersonDataChangesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long parameterCode;
    private Timestamp changeDatetime;
    private Long dmlstatypeCode;
    private String oldValue;
    private String newValue;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * PersonDataChangesDTO Default Constructor */
    public PersonDataChangesDTO() {        super();
    }    /** 
     * @param personDataChangesEntity 
     */
    public PersonDataChangesDTO(PersonDataChangesEntity personDataChangesEntity) {        this.civilId = personDataChangesEntity.getCivilId();
        this.parameterCode = personDataChangesEntity.getParameterCode();
        this.changeDatetime = personDataChangesEntity.getChangeDatetime();
        this.dmlstatypeCode = personDataChangesEntity.getDmlstatypeCode();
        this.oldValue = personDataChangesEntity.getOldValue();
        this.newValue = personDataChangesEntity.getNewValue();
        this.setCreatedBy(personDataChangesEntity.getCreatedBy());
        this.setCreatedDate(personDataChangesEntity.getCreatedDate());
        this.setLastUpdatedBy(personDataChangesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(personDataChangesEntity.getLastUpdatedDate());
        this.auditStatus = personDataChangesEntity.getAuditStatus();
        this.tabrecSerial = personDataChangesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getParameterCode() {        return parameterCode;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getChangeDatetime() {        return changeDatetime;
    }    /** 
     * @return Long 
     */
    public Long getDmlstatypeCode() {        return dmlstatypeCode;
    }    /** 
     * @return String 
     */
    public String getOldValue() {        return oldValue;
    }    /** 
     * @return String 
     */
    public String getNewValue() {        return newValue;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param civilId 
     */
    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    /** 
     * @param parameterCode 
     */
    public void setParameterCode(Long parameterCode) {        this.parameterCode = parameterCode;
    }    /** 
     * @param changeDatetime 
     */
    public void setChangeDatetime(Timestamp changeDatetime) {        this.changeDatetime = changeDatetime;
    }    /** 
     * @param dmlstatypeCode 
     */
    public void setDmlstatypeCode(Long dmlstatypeCode) {        this.dmlstatypeCode = dmlstatypeCode;
    }    /** 
     * @param oldValue 
     */
    public void setOldValue(String oldValue) {        this.oldValue = oldValue;
    }    /** 
     * @param newValue 
     */
    public void setNewValue(String newValue) {        this.newValue = newValue;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
