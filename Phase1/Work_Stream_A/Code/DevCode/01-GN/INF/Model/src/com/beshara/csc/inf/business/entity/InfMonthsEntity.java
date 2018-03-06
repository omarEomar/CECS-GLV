package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

import java.sql.Timestamp;

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
 * This Class Manipulate the Persistence Methods of GenderTypes Entity.
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
 * @author Aly Noor @since 06/26/2014 @version      2.0  
 * eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "InfMonthsEntity.findAll", 
                             query = "select o from InfMonthsEntity o order by o.monthCode")
        , 
        @NamedQuery(name = "InfMonthsEntity.findNewId", query = "select MAX(o.monthCode) from InfMonthsEntity o")
        , 
        @NamedQuery(name = "InfMonthsEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.InfMonthsDTO(o.monthCode,o.monthName) from InfMonthsEntity o order by o.monthName")
        , 
        @NamedQuery(name = "InfMonthsEntity.searchByName", query = "select o from InfMonthsEntity o where o.monthName like :monthName order by o.monthName")
        , 
        @NamedQuery(name = "InfMonthsEntity.searchByCode", query = "select o from InfMonthsEntity o where o.monthCode=:monthCode order by o.monthName")
        } )
@Table(name = "INF_MONTHS")
@IdClass(IInfMonthsEntityKey.class)
public class

InfMonthsEntity  extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MONTH_CODE", nullable = false)
    private Long monthCode;
    @Column(name = "MONTH_NAME", nullable = false)
    private String monthName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    public InfMonthsEntity() {
    }


    public void setMonthCode(Long monthCode) {
        this.monthCode = monthCode;
    }

    public Long getMonthCode() {
        return monthCode;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getMonthName() {
        return monthName;
    }


    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
