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
 * @author Aly Noor 
 * @version      2.0 
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "GenderTypesEntity.findAll", 
                             query = "select o from GenderTypesEntity o order by o.gentypeCode")
        , 
        @NamedQuery(name = "GenderTypesEntity.findNewId", query = "select MAX(o.gentypeCode) from GenderTypesEntity o")
        , 
        @NamedQuery(name = "GenderTypesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.GenderTypesDTO(o.gentypeCode,o.gentypeName) from GenderTypesEntity o order by o.gentypeName")
        , 
        @NamedQuery(name = "GenderTypesEntity.searchByName", query = "select o from GenderTypesEntity o where o.gentypeName like :genderTypeName order by o.gentypeName")
        , 
        @NamedQuery(name = "GenderTypesEntity.searchByCode", query = "select o from GenderTypesEntity o where o.gentypeCode=:genderTypeCode order by o.gentypeName")
        ,
        @NamedQuery(name = "GenderTypesEntity.getByName", query = "select o from GenderTypesEntity o where o.gentypeName =:name")          
        } )
@Table(name = "INF_GENDER_TYPES")
@IdClass(IGenderTypesEntityKey.class)
public class GenderTypesEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "GENTYPE_CODE", nullable = false)
    private Long gentypeCode;
    @Column(name = "GENTYPE_NAME", nullable = false)
    private String gentypeName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * GenderTypesEntity Default Constructor
     */
    public GenderTypesEntity() {
    }


    /**
     * @return Long
     */
    public Long getGentypeCode() {
        return gentypeCode;
    }

    /**
     * @return String
     */
    public String getGentypeName() {
        return gentypeName;
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
     * @param gentypeName
     */
    public void setGentypeName(String gentypeName) {
        this.gentypeName = gentypeName;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
