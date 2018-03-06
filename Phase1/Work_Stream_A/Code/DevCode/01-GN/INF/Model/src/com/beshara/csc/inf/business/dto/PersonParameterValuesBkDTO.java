package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.PersonParameterValuesBkEntity;
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
public class PersonParameterValuesBkDTO extends InfDTO implements IPersonParameterValuesBkDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long parameterCode;
    private String paramValue;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * PersonParameterValuesBkDTO Default Constructor */
    public PersonParameterValuesBkDTO() {        super();
    }    /** 
     * @param personParameterValuesBkEntity 
     */
    public PersonParameterValuesBkDTO(PersonParameterValuesBkEntity personParameterValuesBkEntity) {        this.civilId = personParameterValuesBkEntity.getCivilId();
        this.parameterCode = personParameterValuesBkEntity.getParameterCode();
        this.paramValue = personParameterValuesBkEntity.getParamValue();
        this.auditStatus = personParameterValuesBkEntity.getAuditStatus();
        this.tabrecSerial = personParameterValuesBkEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getParameterCode() {        return parameterCode;
    }    /** 
     * @return String 
     */
    public String getParamValue() {        return paramValue;
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
     * @param paramValue 
     */
    public void setParamValue(String paramValue) {        this.paramValue = paramValue;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
