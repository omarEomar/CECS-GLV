package com.beshara.csc.inf.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.beshara.base.entity.BasicEntity;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

@Entity
@NamedQueries({
@NamedQuery(name = "InfTmpDataTypesEntity.findAll", query = "select o from InfTmpDataTypesEntity o"),
@NamedQuery(name = "InfTmpDataTypesEntity.findNewId", query = "select MAX(o.datatypCode) from InfTmpDataTypesEntity o")
,
   @NamedQuery(name = "InfTmpDataTypesEntity.searchByCode", query = "select o from InfTmpDataTypesEntity o where o.datatypCode=:code")

,
   @NamedQuery(name = "InfTmpDataTypesEntity.searchByName", query = "select o from InfTmpDataTypesEntity o where o.name like :name order by o.name")

})
@Table(name = "INF_TMP_DATA_TYPES")
@IdClass(IInfTmpDataTypesEntityKey.class)

public class InfTmpDataTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


@Column(name="SOC_CODE", nullable=false)
private Long socCode;
@Id
@Column(name="DATATYP_CODE", nullable=false)
private Long datatypCode;
@Column(name="DATATYP_NAME", nullable=false)
private String name ; 
@Column(name="WS_USER", nullable=true)
private String wsUser;
@Column(name="WS_PASS", nullable=true)
private String wsPass;
@Column(name="WS_DISK", nullable=true)
private String wsDisk;
@Column(name="WS_URL", nullable=true)
private String wsUrl;
//@ManyToOne 
//@JoinColumn(name="SOC_CODE", referencedColumnName="SOC_CODE")
//private InfMapSocietiesEntity infMapSocietiesEntity;
 

    /**
     * InfTmpDataTypesEntity Default Constructor
     */    
    public InfTmpDataTypesEntity() {
    }


/**
* @return Long
*/
public Long getSocCode() {
   return socCode;
}
/**
* @return Long
*/
public Long getDatatypCode() {
   return datatypCode;
}
/**
* @return String 
*/
public String getName(){
   return name ;
}
/**
* @return String
*/
public String getWsUser() {
   return wsUser;
}
/**
* @return String
*/
public String getWsPass() {
   return wsPass;
}
/**
* @return String
*/
public String getWsDisk() {
   return wsDisk;
}
/**
* @return String
*/
public String getWsUrl() {
   return wsUrl;
}


/**
* @param socCode
*/
public void setSocCode(Long socCode) {
   this.socCode=socCode;
}
/**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) {
   this.datatypCode=datatypCode;
}
/**
* @param name
*/
public void setName( String name ){
   this.name = name;
}
/**
* @param wsUser
*/
public void setWsUser(String wsUser) {
   this.wsUser=wsUser;
}
/**
* @param wsPass
*/
public void setWsPass(String wsPass) {
   this.wsPass=wsPass;
}
/**
* @param wsDisk
*/
public void setWsDisk(String wsDisk) {
   this.wsDisk=wsDisk;
}
/**
* @param wsUrl
*/
public void setWsUrl(String wsUrl) {
   this.wsUrl=wsUrl;
}


}
