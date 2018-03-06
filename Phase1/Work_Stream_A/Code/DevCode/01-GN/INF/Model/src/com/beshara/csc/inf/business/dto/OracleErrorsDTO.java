package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.OracleErrorsEntity;
import com.beshara.csc.inf.business.entity.OracleErrorsEntityKey;
import com.beshara.csc.inf.business.entity.SystemSettingsEntityKey;

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
public class OracleErrorsDTO extends InfDTO implements IOracleErrorsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long errCode;
    private Long errNum;
    private String errEMsg;
    private String errAMsg;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * OracleErrorsDTO Default Constructor */
    public OracleErrorsDTO() {        super();
    }    /** 
     * @param oracleErrorsEntity 
     */
         
     public OracleErrorsDTO(Long errNum , String errAMsg)
     {     
         this.setCode(new OracleErrorsEntityKey(errNum));
         this.setName(errAMsg);
         
     }
         
    public OracleErrorsDTO(OracleErrorsEntity oracleErrorsEntity) {        
        this.errNum = oracleErrorsEntity.getErrNum();
        this.errCode = oracleErrorsEntity.getErrCode();
        this.errEMsg = oracleErrorsEntity.getErrEMsg();
        this.errAMsg = oracleErrorsEntity.getErrAMsg();
        this.errNum=oracleErrorsEntity.getErrNum();
        this.setCode(new OracleErrorsEntityKey(oracleErrorsEntity.getErrNum()));
        this.setName(oracleErrorsEntity.getErrAMsg()); 
         this.setCreatedBy(oracleErrorsEntity.getCreatedBy());
        this.setCreatedDate(oracleErrorsEntity.getCreatedDate());
        this.setLastUpdatedBy(oracleErrorsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(oracleErrorsEntity.getLastUpdatedDate());
        this.auditStatus = oracleErrorsEntity.getAuditStatus();
        this.tabrecSerial = oracleErrorsEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getErrNum() {        return errNum;
    }    /** 
     * @return String 
     */
    public String getErrEMsg() {        return errEMsg;
    }    /** 
     * @return String 
     */
    public String getErrAMsg() {        return errAMsg;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param errNum 
     */
    public void setErrNum(Long errNum) {        this.errNum = errNum;
    }    /** 
     * @param errEMsg 
     */
    public void setErrEMsg(String errEMsg) {        this.errEMsg = errEMsg;
    }    /** 
     * @param errAMsg 
     */
    public void setErrAMsg(String errAMsg) {        this.errAMsg = errAMsg;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }

    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }

    public Long getErrCode() {
        return errCode;
    }
}
