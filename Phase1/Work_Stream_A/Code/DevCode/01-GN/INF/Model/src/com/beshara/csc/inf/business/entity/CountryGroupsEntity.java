package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of CountryGroups Entity.
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
 * @EditedBy @author Aly Noor @since 06/26/2014 eidted to extends BasicEntity
 * to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "CountryGroupsEntity.findAll", 
                             query = "select o from CountryGroupsEntity o order by o.cntrygrpName")
        , 
        @NamedQuery(name = "CountryGroupsEntity.findNewId", query = "select MAX(o.cntrygrpCode) from CountryGroupsEntity o")
        , 
        @NamedQuery(name = "CountryGroupsEntity.getCats", query = "select o from CountryGroupsEntity o where o.parentCntrygrp is null order by o.cntrygrpName")
        , 
        @NamedQuery(name = "CountryGroupsEntity.getGroups", query = "select o from CountryGroupsEntity o where o.parentCntrygrp =:catCode order by o.cntrygrpName")
        , 
        @NamedQuery(name = "CountryGroupsEntity.searchCatsByCode", query = "select o from CountryGroupsEntity o where o.parentCntrygrp is null and o.cntrygrpCode=:catCode")
        , 
        @NamedQuery(name = "CountryGroupsEntity.searchCatsByName", query = "select o from CountryGroupsEntity o where  o.parentCntrygrp is null and o.cntrygrpName like :catName order by o.cntrygrpName")
        , 
        @NamedQuery(name = "CountryGroupsEntity.searchGroupsByCode", query = "select o from CountryGroupsEntity o where o.parentCntrygrp=:catCode and o.cntrygrpCode=:gpCode")
        , 
        @NamedQuery(name = "CountryGroupsEntity.searchGroupsByName", query = "select o from CountryGroupsEntity o where o.parentCntrygrp=:catCode and o.cntrygrpName like :catName order by o.cntrygrpName")
        ,
        @NamedQuery(name = "CountryGroupsEntity.getByNameForRoot", query = "select o from CountryGroupsEntity o where o.cntrygrpName =:name and o.parentCntrygrp is Null")
         ,
         @NamedQuery(name = "CountryGroupsEntity.getByNameForChildren", query = "select o from CountryGroupsEntity o where o.cntrygrpName =:name and o.parentCntrygrp= :parentCntrygrp")
          

        
        } )

@Table(name = "INF_COUNTRY_GROUPS")
@IdClass(ICountryGroupsEntityKey.class)
public class CountryGroupsEntity  extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CNTRYGRP_CODE", nullable = false)
    private Long cntrygrpCode;
    @Column(name = "CNTRYGRP_NAME", nullable = false)
    private String cntrygrpName;
    @Column(name = "PARENT_CNTRYGRP", nullable = true, insertable = false, 
            updatable = false)
    private Long parentCntrygrp;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "PARENT_CNTRYGRP", 
                referencedColumnName = "CNTRYGRP_CODE")
    private CountryGroupsEntity countryGroupsEntity;


    /**
     * CountryGroupsEntity Default Constructor
     */
    public CountryGroupsEntity() {
    }


    /**
     * @return Long
     */
    public Long getCntrygrpCode() {
        return cntrygrpCode;
    }

    /**
     * @return String
     */
    public String getCntrygrpName() {
        return cntrygrpName;
    }

    /**
     * @return Long
     */
    public Long getParentCntrygrp() {
        return parentCntrygrp;
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
     * @param cntrygrpName
     */
    public void setCntrygrpName(String cntrygrpName) {
        this.cntrygrpName = cntrygrpName;
    }

    /**
     * @param parentCntrygrp
     */
    public void setParentCntrygrp(Long parentCntrygrp) {
        this.parentCntrygrp = parentCntrygrp;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


    public void setCountryGroupsEntity(CountryGroupsEntity countryGroupsEntity) {
        this.countryGroupsEntity = countryGroupsEntity;
    }

    public CountryGroupsEntity getCountryGroupsEntity() {
        return countryGroupsEntity;
    }
}
