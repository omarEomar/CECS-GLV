package com.beshara.csc.inf.business.dto;


import java.util.List;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IInfTmpDataTypesDTO extends IInfDTO {

    /**
* @return Long
*/
Long getSocCode() ;
/**
* @return Long
*/
Long getDatatypCode() ;
/**
* @return String
*/
String getName();
/**
* @return String
*/
String getWsUser() ;
/**
* @return String
*/
String getWsPass() ;
/**
* @return String
*/
String getWsDisk() ;
/**
* @return String
*/
String getWsUrl() ;

    
    /**
* @param socCode
*/
public void setSocCode(Long socCode) ;
/**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) ;
/**
* @param name
*/
public void setName( String name );
/**
* @param wsUser
*/
public void setWsUser(String wsUser) ;
/**
* @param wsPass
*/
public void setWsPass(String wsPass) ;
/**
* @param wsDisk
*/
public void setWsDisk(String wsDisk) ;
/**
* @param wsUrl
*/
public void setWsUrl(String wsUrl) ;

    
}
