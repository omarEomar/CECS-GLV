package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.dto.MilitaryStatusDTO;

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
 * This Class Manipulate the Persistence Methods of MilitaryStatus Entity.
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
@NamedQueries( { @NamedQuery(name = "MilitaryStatusEntity.findAll", 
                             query = "select o from MilitaryStatusEntity o order by o.mltstatusName")
        , 
        @NamedQuery(name = "MilitaryStatusEntity.findNewId", query = "select MAX(o.mltstatusCode) from MilitaryStatusEntity o")
       
        , 
        @NamedQuery(name = "MilitaryStatusEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.MilitaryStatusDTO(o.mltstatusCode,o.mltstatusName) from MilitaryStatusEntity o order by o.mltstatusName")
        , 
        @NamedQuery(name = "MilitaryStatusEntity.searchByName", query = "select o from MilitaryStatusEntity o where o.mltstatusName like :mltstatusName order by o.mltstatusName")
        , 
        @NamedQuery(name = "MilitaryStatusEntity.searchByCode", query = "select o from MilitaryStatusEntity o where o.mltstatusCode=:mltstatusCode order by o.mltstatusName")
        ,
        @NamedQuery(name = "MilitaryStatusEntity.getByName", query = "select o from MilitaryStatusEntity o where o.mltstatusName =:name") 
        } )
     
@Table(name = "INF_MILITARY_STATUS")
@IdClass(IMilitaryStatusEntityKey.class)
public class MilitaryStatusEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MLTSTATUS_CODE", nullable = false)
    private Long mltstatusCode;
    @Column(name = "MLTSTATUS_NAME", nullable = false)
    private String mltstatusName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * MilitaryStatusEntity Default Constructor
     */
    public MilitaryStatusEntity() {
    }


    /**
     * @return Long
     */
    public Long getMltstatusCode() {
        return mltstatusCode;
    }

    /**
     * @return String
     */
    public String getMltstatusName() {
        return mltstatusName;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param mltstatusCode
     */
    public void setMltstatusCode(Long mltstatusCode) {
        this.mltstatusCode = mltstatusCode;
    }

    /**
     * @param mltstatusName
     */
    public void setMltstatusName(String mltstatusName) {
        this.mltstatusName = mltstatusName;
    }


    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
