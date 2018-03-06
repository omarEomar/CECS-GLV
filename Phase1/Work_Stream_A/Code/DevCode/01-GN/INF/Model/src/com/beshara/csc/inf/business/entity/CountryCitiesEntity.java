package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of CountryCities Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated 
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *     
 *     
 * @author       Beshara Group   
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0   
 * @since        03/09/2007   
 */
@Entity
@NamedQueries( { @NamedQuery(name = "CountryCitiesEntity.findAll", 
                             query = "select o from CountryCitiesEntity o"), 
		        @NamedQuery(name = "CountryCitiesEntity.findNewId", 
                            query = "select MAX(o.cntrycityCode) from CountryCitiesEntity o"), 
		        @NamedQuery(name = "CountryCitiesEntity.getCities", 
							query = "select o from CountryCitiesEntity o where o.cntryCode =:countryCode order by o.capitalFlag"), 
		        @NamedQuery(name = "CountryCitiesEntity.getCapital", 
							query = "select o from CountryCitiesEntity o where o.cntryCode =:countryCode and o.capitalFlag = :capitalFlag "), 
		        @NamedQuery(name = "CountryCitiesEntity.searchCountryByCode", 
							query = "select o from CountryCitiesEntity o where o.cntryCode =:cntryCode and o.cntrycityCode=:cntrycityCode"), 
		        @NamedQuery(name = "CountryCitiesEntity.searchCountryByName", 
							query = "select o from CountryCitiesEntity o where o.cntryCode =:cntryCode and o.cntrycityName like :cntrycityName"),
                 @NamedQuery(name = "CountryCitiesEntity.getByName",
                             query = "select o from CountryCitiesEntity o where o.cntrycityName =:name") })

@Table(name = "INF_COUNTRY_CITIES")
@IdClass(ICountryCitiesEntityKey.class)

public class CountryCitiesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CNTRY_CODE", nullable = false, insertable = false, updatable = false)
    private Long cntryCode;
    @Id
    @Column(name = "CNTRYCITY_CODE", nullable = false)
    private Long cntrycityCode;
    @Column(name = "CNTRYCITY_NAME", nullable = false)
    private String cntrycityName;
    @Column(name = "CAPITAL_FLAG", nullable = false)
    private Long capitalFlag;
    @Column(name = "LONGITUDE", nullable = true)
    private Long longitude;
    @Column(name = "LATITUDE", nullable = true)
    private Long latitude;
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "CNTRY_CODE", referencedColumnName = "CNTRY_CODE")
    private CountriesEntity countriesEntity;


    /**
     * CountryCitiesEntity Default Constructor
     */
    public CountryCitiesEntity() {
    }


    /**
     * @return Long
     */
    public Long getCntryCode() {
        return cntryCode;
    }

    /**
     * @return Long
     */
    public Long getCntrycityCode() {
        return cntrycityCode;
    }

    /**
     * @return String
     */
    public String getCntrycityName() {
        return cntrycityName;
    }

    /**
     * @return Long
     */
    public Long getCapitalFlag() {
        return capitalFlag;
    }

    /**
     * @return Long
     */
    public Long getLongitude() {
        return longitude;
    }

    /**
     * @return Long
     */
    public Long getLatitude() {
        return latitude;
    }

    /**
     * @return Long
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @return Long
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
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
     * @param cntryCode
     */
    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    /**
     * @param cntrycityCode
     */
    public void setCntrycityCode(Long cntrycityCode) {
        this.cntrycityCode = cntrycityCode;
    }

    /**
     * @param cntrycityName
     */
    public void setCntrycityName(String cntrycityName) {
        this.cntrycityName = cntrycityName;
    }

    /**
     * @param capitalFlag
     */
    public void setCapitalFlag(Long capitalFlag) {
        this.capitalFlag = capitalFlag;
    }

    /**
     * @param longitude
     */
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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


    public void setCountriesEntity(CountriesEntity countriesEntity) {
        this.countriesEntity = countriesEntity;
    }

    public CountriesEntity getCountriesEntity() {
        return countriesEntity;
    }
}
