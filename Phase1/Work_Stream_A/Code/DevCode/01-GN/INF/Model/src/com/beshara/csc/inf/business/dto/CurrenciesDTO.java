package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.CurrenciesEntity;
import com.beshara.csc.inf.business.entity.CurrenciesEntityKey;
import java.sql.Timestamp;
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
public class CurrenciesDTO extends InfDTO implements ICurrenciesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long currencyCode;
    private String currencyName;
    private String currencyAbrv1;
    private String currencyAbrv2;
    private Long createdBy;
    private Timestamp createdDate;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * CurrenciesDTO Default Constructor */
    public CurrenciesDTO() {        super();
    }    public CurrenciesDTO(Long currencyCode, String currencyName) {        this.setCode(new CurrenciesEntityKey(currencyCode));
        this.setName(currencyName);
    }    /** 
     * @param currenciesEntity 
     */
    public CurrenciesDTO(CurrenciesEntity currenciesEntity) {        this.currencyCode = currenciesEntity.getCurrencyCode();
        this.currencyName = currenciesEntity.getCurrencyName();
        this.setCode(new CurrenciesEntityKey(currenciesEntity.getCurrencyCode()));
        this.setName(currenciesEntity.getCurrencyName());
        this.currencyAbrv1 = currenciesEntity.getCurrencyAbrv1();
        this.currencyAbrv2 = currenciesEntity.getCurrencyAbrv2();
        this.createdBy = currenciesEntity.getCreatedBy();
        this.createdDate = currenciesEntity.getCreatedDate();
        this.lastUpdatedBy = currenciesEntity.getLastUpdatedBy();
        this.lastUpdatedDate = currenciesEntity.getLastUpdatedDate();
        this.auditStatus = currenciesEntity.getAuditStatus();
        this.tabrecSerial = currenciesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCurrencyCode() {        return currencyCode;
    }    /** 
     * @return String 
     */
    public String getCurrencyName() {        return currencyName;
    }    /** 
     * @return String 
     */
    public String getCurrencyAbrv1() {        return currencyAbrv1;
    }    /** 
     * @return String 
     */
    public String getCurrencyAbrv2() {        return currencyAbrv2;
    }    /** 
     * @return Long 
     */
    public Long getCreatedBy() {        return createdBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getCreatedDate() {        return createdDate;
    }    /** 
     * @return Long 
     */
    public Long getLastUpdatedBy() {        return lastUpdatedBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getLastUpdatedDate() {        return lastUpdatedDate;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param currencyCode 
     */
    public void setCurrencyCode(Long currencyCode) {        this.currencyCode = currencyCode;
    }    /** 
     * @param currencyName 
     */
    public void setCurrencyName(String currencyName) {        this.currencyName = currencyName;
    }    /** 
     * @param currencyAbrv1 
     */
    public void setCurrencyAbrv1(String currencyAbrv1) {        this.currencyAbrv1 = currencyAbrv1;
    }    /** 
     * @param currencyAbrv2 
     */
    public void setCurrencyAbrv2(String currencyAbrv2) {        this.currencyAbrv2 = currencyAbrv2;
    }    /** 
     * @param createdBy 
     */
    public void setCreatedBy(Long createdBy) {        this.createdBy = createdBy;
    }    /** 
     * @param createdDate 
     */
    public void setCreatedDate(Timestamp createdDate) {        this.createdDate = createdDate;
    }    /** 
     * @param lastUpdatedBy 
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {        this.lastUpdatedBy = lastUpdatedBy;
    }    /** 
     * @param lastUpdatedDate 
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {        this.lastUpdatedDate = lastUpdatedDate;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
