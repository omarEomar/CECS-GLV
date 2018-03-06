package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.TrxStatusEntity;
import com.beshara.csc.inf.business.entity.TrxStatusEntityKey;

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
public class TrxStatusDTO extends InfDTO implements ITrxStatusDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * TrxStatusDTO Default Constructor */
    public TrxStatusDTO() {    }    /** 
     * @param trxStatusEntity 
     */
    public TrxStatusDTO(TrxStatusEntity trxStatusEntity) {        
        this.setCode(new TrxStatusEntityKey(trxStatusEntity.getTrxstatusCode()));
        this.setName(trxStatusEntity.getTrxstatusName());
        this.setCreatedBy(trxStatusEntity.getCreatedBy());
        this.setCreatedDate(trxStatusEntity.getCreatedDate());
        this.setLastUpdatedBy(trxStatusEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(trxStatusEntity.getLastUpdatedDate());
    }    /** 
     * @param trxstatusCode 
     * @param trxstatusName 
     */
    public TrxStatusDTO(String trxstatusCode, String trxstatusName) {        
        this.setCode(new TrxStatusEntityKey(trxstatusCode));
        this.setName(trxstatusName);
    }    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    public Long getAuditStatus() {        return auditStatus;
    }    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public Long getTabrecSerial() {        return tabrecSerial;
    }}
