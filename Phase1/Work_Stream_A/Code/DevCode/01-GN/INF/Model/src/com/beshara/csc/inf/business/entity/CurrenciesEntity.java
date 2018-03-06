package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of Currencies Entity.
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
@NamedQueries( { @NamedQuery(name = "CurrenciesEntity.findAll",
                             query = "select o from CurrenciesEntity o order by o.currencyCode"),
                 @NamedQuery(name = "CurrenciesEntity.findNewId",
                             query = "select MAX(o.currencyCode) from CurrenciesEntity o"),
                 @NamedQuery(name = "CurrenciesEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.CurrenciesDTO(o.currencyCode,o.currencyName) from CurrenciesEntity o order by o.currencyName"),
                 @NamedQuery(name = "CurrenciesEntity.searchByName",
                             query = "select o from CurrenciesEntity o where o.currencyName like :currencyName order by o.currencyName"),
                 @NamedQuery(name = "CurrenciesEntity.searchByCode",
                             query = "select o from CurrenciesEntity o where o.currencyCode=:currencyCode order by o.currencyCode"),
                 @NamedQuery(name = "CurrenciesEntity.getByName",
                             query = "select o from CurrenciesEntity o where o.currencyName =:name") })


@Table(name = "INF_CURRENCIES")
@IdClass(ICurrenciesEntityKey.class)
public class CurrenciesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CURRENCY_CODE", nullable = false)
    private Long currencyCode;
    @Column(name = "CURRENCY_NAME", nullable = false)
    private String currencyName;
    @Column(name = "CURRENCY_ABRV_1", nullable = false)
    private String currencyAbrv1;
    @Column(name = "CURRENCY_ABRV_2", nullable = true)
    private String currencyAbrv2;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    /**
     * CurrenciesEntity Default Constructor
     */
    public CurrenciesEntity() {
    }


    /**
     * @return Long
     */
    public Long getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @return String
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * @return String
     */
    public String getCurrencyAbrv1() {
        return currencyAbrv1;
    }

    /**
     * @return String
     */
    public String getCurrencyAbrv2() {
        return currencyAbrv2;
    }


    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param currencyCode
     */
    public void setCurrencyCode(Long currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @param currencyName
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * @param currencyAbrv1
     */
    public void setCurrencyAbrv1(String currencyAbrv1) {
        this.currencyAbrv1 = currencyAbrv1;
    }

    /**
     * @param currencyAbrv2
     */
    public void setCurrencyAbrv2(String currencyAbrv2) {
        this.currencyAbrv2 = currencyAbrv2;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
