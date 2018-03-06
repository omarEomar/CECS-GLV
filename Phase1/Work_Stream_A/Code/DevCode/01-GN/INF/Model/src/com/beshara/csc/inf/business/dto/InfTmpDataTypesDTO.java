package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfTmpDataTypesEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class InfTmpDataTypesDTO extends InfDTO implements IInfTmpDataTypesDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long socCode;
private Long datatypCode;
private String name ; 
private String wsUser;
private String wsPass;
private String wsDisk;
private String wsUrl;


    /**
     * InfTmpDataTypesDTO Default Constructor
     */
    public InfTmpDataTypesDTO() {
        super();
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

