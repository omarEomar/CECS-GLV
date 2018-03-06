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
 * This Class Manipulate the Persistence Methods of SystemSettings Entity.
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
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "SystemSettingsEntity.findAll", 
                             query = "select o from SystemSettingsEntity o order by o.syssettingName")
        , 
        @NamedQuery(name = "SystemSettingsEntity.findNewId", query = "select MAX(o.syssettingCode) from SystemSettingsEntity o")
              , 
        @NamedQuery(name = "SystemSettingsEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.SystemSettingsDTO(o.syssettingCode,o.syssettingName) from SystemSettingsEntity o order by o.syssettingName")
        , 
        @NamedQuery(name = "SystemSettingsEntity.searchByName", query = "select o from SystemSettingsEntity o where o.syssettingName like :syssettingName order by o.syssettingName")
        , 
        @NamedQuery(name = "SystemSettingsEntity.searchByCode", query = "select o from SystemSettingsEntity o where o.syssettingCode=:syssettingCode order by o.syssettingName")
     
        } )
@Table(name = "INF_SYSTEM_SETTINGS")
@IdClass(ISystemSettingsEntityKey.class)
public class SystemSettingsEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SYSSETTING_CODE", nullable = false)
    private Long syssettingCode;
    @Column(name = "SYSSETTING_NAME", nullable = false)
    private String syssettingName;
    @Column(name = "SYSSETTING_VALUE", nullable = false)
    private String syssettingValue;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * SystemSettingsEntity Default Constructor
     */
    public SystemSettingsEntity() {
    }


    /**
     * @return Long
     */
    public Long getSyssettingCode() {
        return syssettingCode;
    }

    /**
     * @return String
     */
    public String getSyssettingName() {
        return syssettingName;
    }

    /**
     * @return String
     */
    public String getSyssettingValue() {
        return syssettingValue;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param syssettingCode
     */
    public void setSyssettingCode(Long syssettingCode) {
        this.syssettingCode = syssettingCode;
    }

    /**
     * @param syssettingName
     */
    public void setSyssettingName(String syssettingName) {
        this.syssettingName = syssettingName;
    }

    /**
     * @param syssettingValue
     */
    public void setSyssettingValue(String syssettingValue) {
        this.syssettingValue = syssettingValue;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
