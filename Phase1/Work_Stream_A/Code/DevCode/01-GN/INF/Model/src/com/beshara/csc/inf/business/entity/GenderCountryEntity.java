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
 * This Class Manipulate the Persistence Methods of GenderCountry Entity.
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
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
@Entity
@NamedQueries( { @NamedQuery(name = "GenderCountryEntity.findAll", 
                             query = "select o from GenderCountryEntity o")
        , 
        @NamedQuery(name = "GenderCountryEntity.findNewId", query = "select MAX(o.gentypeCode) from GenderCountryEntity o")
        , 
        @NamedQuery(name = "GenderCountryEntity.getGenderNames", query = "select o from GenderCountryEntity o where o.cntryCode =:countryCode order by o.gentypeCode")
        ,
        @NamedQuery(name = "GenderCountryEntity.getCountriesByGenderType", query = "select o from GenderCountryEntity o where o.gentypeCode =:gentypeCode")         
        } )
@Table(name = "INF_GENDER_COUNTRY")
@IdClass(IGenderCountryEntityKey.class)
public class GenderCountryEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "GENTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long gentypeCode;
    @Id
    @Column(name = "CNTRY_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long cntryCode;
    @Column(name = "GENCNTRY_NAME", nullable = false)
    private String gencntryName;
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
    @ManyToOne
    @JoinColumn(name = "GENTYPE_CODE", referencedColumnName = "GENTYPE_CODE")
    private GenderTypesEntity genderTypesEntity;


    /**
     * GenderCountryEntity Default Constructor
     */
    public GenderCountryEntity() {
    }


    /**
     * @return Long
     */
    public Long getGentypeCode() {
        return gentypeCode;
    }

    /**
     * @return Long
     */
    public Long getCntryCode() {
        return cntryCode;
    }

    /**
     * @return String
     */
    public String getGencntryName() {
        return gencntryName;
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
     * @param gentypeCode
     */
    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }

    /**
     * @param cntryCode
     */
    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    /**
     * @param gencntryName
     */
    public void setGencntryName(String gencntryName) {
        this.gencntryName = gencntryName;
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

    public void setGenderTypesEntity(GenderTypesEntity genderTypesEntity) {
        this.genderTypesEntity = genderTypesEntity;
    }

    public GenderTypesEntity getGenderTypesEntity() {
        return genderTypesEntity;
    }
}
