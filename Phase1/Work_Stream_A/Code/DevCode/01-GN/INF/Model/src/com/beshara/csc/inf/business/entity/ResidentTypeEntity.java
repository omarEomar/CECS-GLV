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
 * This Class Manipulate the Persistence Methods of GroupCountries Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 *     
 * @author       Beshara Group   
 * @version      1.0   
 *
 * @author Aly Noor 
 * @version      2.0 
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */


@Entity
@NamedQueries( { @NamedQuery(name = "ResidentTypeEntity.findAll", 
                             query = "select o from ResidentTypeEntity o order by o.restypeName")
                , 
        @NamedQuery(name = "ResidentTypeEntity.findNewId", query = "select MAX(o.restypeCode) from ResidentTypeEntity o")
        , 
        @NamedQuery(name = "ResidentTypeEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.ResidentTypeDTO(o.restypeCode,o.restypeName) from ResidentTypeEntity o order by o.restypeName")
        , 
        @NamedQuery(name = "ResidentTypeEntity.searchByName", query = "select o from ResidentTypeEntity o where o.restypeName like :restypeName order by o.restypeName")
        , 
        @NamedQuery(name = "ResidentTypeEntity.searchByCode", query = "select o from ResidentTypeEntity o where o.restypeCode=:restypeCode order by o.restypeName")         
        ,
        @NamedQuery(name = "ResidentTypeEntity.getByName", query = "select o from ResidentTypeEntity o where o.restypeName =:name") 
        
        } )
@Table(name = "INF_RESIDENT_TYPE")
@IdClass(IResidentTypeEntityKey.class)
public class ResidentTypeEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RESTYPE_CODE", nullable = false)
    private Long restypeCode;
    @Column(name = "RESTYPE_NAME")
    private String restypeName;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    public ResidentTypeEntity() {
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public String getRestypeName() {
        return restypeName;
    }

    public void setRestypeName(String restypeName) {
        this.restypeName = restypeName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
