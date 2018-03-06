package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfCitizensPassportsEntity;
import com.beshara.csc.inf.business.entity.InfCitizensPassportsEntityKey;

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
public class InfCitizensPassportsDTO extends BasicDTO implements IInfCitizensPassportsDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long civilId;
    private String passportNo;
    private Long issueCountry;
    private Date issueDate;
    private Date expDate;
    private Long auditStatus;
    private Long tabrecSerial;

    /**
     * InfCitizensPassportsDTO Default Constructor */
    public InfCitizensPassportsDTO() {
        super();
    }

    /**
     * @param infCitizensPassportsEntity
     */
    public InfCitizensPassportsDTO(InfCitizensPassportsEntity infCitizensPassportsEntity) {
        this.setCode(new InfCitizensPassportsEntityKey(infCitizensPassportsEntity.getSerialNo()));
        this.civilId = infCitizensPassportsEntity.getCivilId();
        this.passportNo = infCitizensPassportsEntity.getPassportNo();
        this.issueCountry = infCitizensPassportsEntity.getIssueCountry();
        this.issueDate = infCitizensPassportsEntity.getIssueDate();
        this.expDate = infCitizensPassportsEntity.getExpDate();
        setCreatedBy(infCitizensPassportsEntity.getCreatedBy());
        setCreatedDate(infCitizensPassportsEntity.getCreatedDate());
        setLastUpdatedBy(infCitizensPassportsEntity.getLastUpdatedBy());
        setLastUpdatedDate(infCitizensPassportsEntity.getLastUpdatedDate());
        this.auditStatus = infCitizensPassportsEntity.getAuditStatus();
        this.tabrecSerial = infCitizensPassportsEntity.getTabrecSerial();
    }

    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return Long
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @return Long
     */
    public Long getIssueCountry() {
        return issueCountry;
    }

    /**
     * @return Date
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * @return Date
     */
    public Date getExpDate() {
        return expDate;
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
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param passportNo
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @param issueCountry
     */
    public void setIssueCountry(Long issueCountry) {
        this.issueCountry = issueCountry;
    }

    /**
     * @param issueDate
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @param expDate
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
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
