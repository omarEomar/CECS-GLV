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
 * This Class Manipulate the Persistence Methods of DmlStatmentTypes Entity.
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
@NamedQueries( { @NamedQuery(name = "DmlStatmentTypesEntity.findAll", 
                             query = "select o from DmlStatmentTypesEntity o  order by o.dmlstatypeName")
        , 
        @NamedQuery(name = "DmlStatmentTypesEntity.findNewId", query = "select MAX(o.dmlstatypeCode) from DmlStatmentTypesEntity o")
        ,
                 @NamedQuery(name = "DmlStatmentTypesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.DmlStatmentTypesDTO(o.dmlstatypeCode,o.dmlstatypeName) from DmlStatmentTypesEntity o order by o.dmlstatypeName")
        , 
        @NamedQuery(name = "DmlStatmentTypesEntity.searchByName", query = "select o from DmlStatmentTypesEntity o where o.dmlstatypeName like :dmlstatypeName order by o.dmlstatypeName")
        , 
        @NamedQuery(name = "DmlStatmentTypesEntity.searchByCode", query = "select o from DmlStatmentTypesEntity o where o.dmlstatypeCode=:dmlstatypeCode order by o.dmlstatypeName")
     
        } )
@Table(name = "INF_DML_STATMENT_TYPES")
@IdClass(IDmlStatmentTypesEntityKey.class)
public class DmlStatmentTypesEntity  extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "DMLSTATYPE_CODE", nullable = false)
    private Long dmlstatypeCode;
    @Column(name = "DMLSTATYPE_NAME", nullable = false)
    private String dmlstatypeName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * DmlStatmentTypesEntity Default Constructor
     */
    public DmlStatmentTypesEntity() {
    }


    /**
     * @return Long
     */
    public Long getDmlstatypeCode() {
        return dmlstatypeCode;
    }

    /**
     * @return String
     */
    public String getDmlstatypeName() {
        return dmlstatypeName;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param dmlstatypeCode
     */
    public void setDmlstatypeCode(Long dmlstatypeCode) {
        this.dmlstatypeCode = dmlstatypeCode;
    }

    /**
     * @param dmlstatypeName
     */
    public void setDmlstatypeName(String dmlstatypeName) {
        this.dmlstatypeName = dmlstatypeName;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
