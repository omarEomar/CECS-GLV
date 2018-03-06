package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries


( { @NamedQuery(name = "CountriesEntity.findAll", query = "select o from CountriesEntity o order by o.cntryName"),
    @NamedQuery(name = "CountriesEntity.findNewId", query = "select MAX(o.cntryCode) from CountriesEntity o"),
    @NamedQuery(name = "CountriesEntity.getCodeName",
                query = "select new com.beshara.csc.inf.business.dto.CountriesDTO(o.cntryCode,o.cntryName) from CountriesEntity o order by o.cntryName"),
    @NamedQuery(name = "CountriesEntity.searchByCode",
                query = "select o from CountriesEntity o where o.cntryCode =:cntryCode"),
    @NamedQuery(name = "CountriesEntity.checkDublicateName",
                query = "select o from CountriesEntity o where o.cntryName = :name"),
    @NamedQuery(name = "CountriesEntity.searchByName",
                query = "select o from CountriesEntity o where o.cntryName like :cntryName"),
    @NamedQuery(name = "CountriesEntity.getAllOrderByClass",
                query = "select o from CountriesEntity o order by o.ctyclassCode"),
        @NamedQuery(name = "CountriesEntity.getSingleCodeName",
                query = "select new com.beshara.csc.inf.business.dto.CountriesDTO(o.cntryCode,o.cntryName) from CountriesEntity o where o.cntryCode=:cntryCode")})
@Table(name = "INF_COUNTRIES")
@IdClass(ICountriesEntityKey.class)
// @EditedBy @author Aly Noor @since 06/25/2014 eidted to extends BasicEntity
// to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName

public class CountriesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CNTRY_CODE", nullable = false)
    private Long cntryCode;
    @Column(name = "CNTRY_NAME", nullable = false)
    private String cntryName;
    @Column(name = "LANGUAGE_CODE", insertable = false, updatable = false)
    private Long languageCode;
    @Column(name = "CURRENCY_CODE", insertable = false, updatable = false)
    private Long currencyCode;

    @Column(name = "CTYCLASS_CODE")
    private Long ctyclassCode;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_CODE", referencedColumnName = "LANGUAGE_CODE", insertable = true, updatable = true)
    private LanguagesEntity languagesEntity;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_CODE", referencedColumnName = "CURRENCY_CODE", insertable = true, updatable = true)
    private CurrenciesEntity currenciesEntity;

    @OneToMany(mappedBy = "countriesEntity")
    private List<CountryCitiesEntity> countryCitiesEntityList;

    @OneToMany(mappedBy = "countriesEntity")
    private List<GenderCountryEntity> genderCountryEntityList;


    public CountriesEntity() {
    }

    public Long getCntryCode() {
        return cntryCode;
    }

    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    public String getCntryName() {
        return cntryName;
    }

    public void setCntryName(String cntryName) {
        this.cntryName = cntryName;
    }


    public Long getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(Long languageCode) {
        this.languageCode = languageCode;
    }


    public void setCurrencyCode(Long currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getCurrencyCode() {
        return currencyCode;
    }

    public void setLanguagesEntity(LanguagesEntity languagesEntity) {
        this.languagesEntity = languagesEntity;
    }

    public LanguagesEntity getLanguagesEntity() {
        return languagesEntity;
    }

    public void setCurrenciesEntity(CurrenciesEntity currenciesEntity) {
        this.currenciesEntity = currenciesEntity;
    }

    public CurrenciesEntity getCurrenciesEntity() {
        return currenciesEntity;
    }

    public void setCountryCitiesEntityList(List<CountryCitiesEntity> countryCitiesEntityList) {
        this.countryCitiesEntityList = countryCitiesEntityList;
    }

    public List<CountryCitiesEntity> getCountryCitiesEntityList() {
        return countryCitiesEntityList;
    }

    public void setGenderCountryEntityList(List<GenderCountryEntity> genderCountryEntityList) {
        this.genderCountryEntityList = genderCountryEntityList;
    }

    public List<GenderCountryEntity> getGenderCountryEntityList() {
        return genderCountryEntityList;
    }

    public void setCtyclassCode(Long ctyclassCode) {
        this.ctyclassCode = ctyclassCode;
    }

    public Long getCtyclassCode() {
        return ctyclassCode;
    }
}
