package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.PersonParameterValuesEntity;
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
public class PersonParameterValuesDTO extends InfDTO implements IPersonParameterValuesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long parameterCode;
    private String paramValue;
    private Timestamp paramValueDate;
    private String valueDesc;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * PersonParameterValuesDTO Default Constructor */
    public PersonParameterValuesDTO() {        super();
    }    /** 
     * @param personParameterValuesEntity 
     */
    public PersonParameterValuesDTO(PersonParameterValuesEntity personParameterValuesEntity) {        this.civilId = personParameterValuesEntity.getCivilId();
        this.parameterCode = personParameterValuesEntity.getParameterCode();
        this.paramValue = personParameterValuesEntity.getParamValue();
        this.paramValueDate = personParameterValuesEntity.getParamValueDate();
        this.valueDesc = personParameterValuesEntity.getValueDesc();
        this.auditStatus = personParameterValuesEntity.getAuditStatus();
        this.tabrecSerial = personParameterValuesEntity.getTabrecSerial();
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
     * @return Timestamp 
     */
    public Timestamp getParamValueDate() {        return paramValueDate;
    }    /** 
     * @return String 
     */
    public String getValueDesc() {        return valueDesc;
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
     * @param paramValueDate 
     */
    public void setParamValueDate(Timestamp paramValueDate) {        this.paramValueDate = paramValueDate;
    }    /** 
     * @param valueDesc 
     */
    public void setValueDesc(String valueDesc) {        this.valueDesc = valueDesc;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
