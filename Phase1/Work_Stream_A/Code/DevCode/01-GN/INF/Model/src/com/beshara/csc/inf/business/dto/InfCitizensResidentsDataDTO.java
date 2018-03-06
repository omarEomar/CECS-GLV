package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntity;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntityKey;
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
public class InfCitizensResidentsDataDTO extends BasicDTO implements IInfCitizensResidentsDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long residentNo;
    private Date issueDate;
    private Date expDate;
    private Long restypeCode;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * InfCitizensResidentsDataDTO Default Constructor */
    public InfCitizensResidentsDataDTO() {        super();
    }    /** 
     * @param infCitizensResidentsDataEntity 
     */
    public InfCitizensResidentsDataDTO(InfCitizensResidentsDataEntity infCitizensResidentsDataEntity) {        setCode(new InfCitizensResidentsDataEntityKey(infCitizensResidentsDataEntity.getSerial()));
        this.civilId = infCitizensResidentsDataEntity.getCivilId();
        this.residentNo = infCitizensResidentsDataEntity.getResidentNo();
        this.issueDate = infCitizensResidentsDataEntity.getIssueDate();
        this.expDate = infCitizensResidentsDataEntity.getExpDate();
        this.restypeCode = infCitizensResidentsDataEntity.getRestypeCode();
        setCreatedBy(infCitizensResidentsDataEntity.getCreatedBy());
        setCreatedDate(infCitizensResidentsDataEntity.getCreatedDate());
        setLastUpdatedBy(infCitizensResidentsDataEntity.getLastUpdatedBy());
        setLastUpdatedDate(infCitizensResidentsDataEntity.getLastUpdatedDate());
        this.auditStatus = infCitizensResidentsDataEntity.getAuditStatus();
        this.tabrecSerial = infCitizensResidentsDataEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getResidentNo() {        return residentNo;
    }    /** 
     * @return Date 
     */
    public Date getIssueDate() {        return issueDate;
    }    /** 
     * @return Date 
     */
    public Date getExpDate() {        return expDate;
    }    /** 
     * @return Long 
     */
    public Long getRestypeCode() {        return restypeCode;
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
     * @param residentNo 
     */
    public void setResidentNo(Long residentNo) {        this.residentNo = residentNo;
    }    /** 
     * @param issueDate 
     */
    public void setIssueDate(Date issueDate) {        this.issueDate = issueDate;
    }    /** 
     * @param expDate 
     */
    public void setExpDate(Date expDate) {        this.expDate = expDate;
    }    /** 
     * @param restypeCode 
     */
    public void setRestypeCode(Long restypeCode) {        this.restypeCode = restypeCode;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
