package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

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
 * This Class Manipulate the Persistence Methods of GroupCountries Entity.
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
 * @author Aly Noor 
 * @version      2.0 
 * @since 06/26/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "GroupCountriesEntity.findAll", 
                             query = "select o from GroupCountriesEntity o ")
        , 
        @NamedQuery(name = "GroupCountriesEntity.findNewId", query = "select MAX(o.cntrygrpCode) from GroupCountriesEntity o")
        , 
        @NamedQuery(name = "GroupCountriesEntity.getCountriesByGroup", query = 
                    "select o from GroupCountriesEntity o where o.cntrygrpCode=:cntrygrpCode")
        , 
        @NamedQuery(name = "GroupCountriesEntity.searchByCode", query = "select o from GroupCountriesEntity o where o.countryGroupsEntity.cntrygrpCode =:groupCode and o.countriesEntity.cntryCode =:countryCode")
        , 
        @NamedQuery(name = "GroupCountriesEntity.searchByName", 
                    query = "select o from GroupCountriesEntity o where o.countryGroupsEntity.cntrygrpCode =:groupCode and o.countriesEntity.cntryName like :countryName")
        , 
        @NamedQuery(name = "GroupCountriesEntity.getAvailableCountries", query = 
                    "select new com.beshara.csc.inf.business.dto.CountriesDTO(o.cntryCode,o.cntryName) from CountriesEntity o where o.cntryCode NOT IN(select gc.cntryCode from GroupCountriesEntity gc where gc.countryGroupsEntity.parentCntrygrp =:catCode) order by o.cntryName")
        , 
        @NamedQuery(name = "GroupCountriesEntity.searchAvailableCountriesByCode", 
                    query = 
                    "select new com.beshara.csc.inf.business.dto.CountriesDTO(o.cntryCode,o.cntryName) from CountriesEntity o where o.cntryCode =:countryCode AND o.cntryCode NOT IN(select gc.cntryCode from GroupCountriesEntity gc where gc.countryGroupsEntity.parentCntrygrp =:catCode) order by o.cntryName")
        , 
        @NamedQuery(name = "GroupCountriesEntity.searchAvailableCountriesByName", 
                    query = 
                    "select new com.beshara.csc.inf.business.dto.CountriesDTO(o.cntryCode,o.cntryName) from CountriesEntity o where o.cntryName like :countryName AND o.cntryCode NOT IN(select gc.cntryCode from GroupCountriesEntity gc where gc.countryGroupsEntity.parentCntrygrp =:catCode) order by o.cntryName")
        } )
@Table(name = "INF_GROUP_COUNTRIES")
@IdClass(IGroupCountriesEntityKey.class)
public class GroupCountriesEntity   extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CNTRYGRP_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long cntrygrpCode;
    @Id
    @Column(name = "CNTRY_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long cntryCode;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "CNTRY_CODE", referencedColumnName = "CNTRY_CODE")
    private CountriesEntity countriesEntity;
    @ManyToOne
    @JoinColumn(name = "CNTRYGRP_CODE", referencedColumnName = "CNTRYGRP_CODE")
    private CountryGroupsEntity countryGroupsEntity;


    /**
     * GroupCountriesEntity Default Constructor
     */
    public GroupCountriesEntity() {
    }


    /**
     * @return Long
     */
    public Long getCntrygrpCode() {
        return cntrygrpCode;
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
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param cntrygrpCode
     */
    public void setCntrygrpCode(Long cntrygrpCode) {
        this.cntrygrpCode = cntrygrpCode;
    }

    /**
     * @param cntryCode
     */
    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
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

    public void setCountryGroupsEntity(CountryGroupsEntity countryGroupsEntity) {
        this.countryGroupsEntity = countryGroupsEntity;
    }

    public CountryGroupsEntity getCountryGroupsEntity() {
        return countryGroupsEntity;
    }
}
