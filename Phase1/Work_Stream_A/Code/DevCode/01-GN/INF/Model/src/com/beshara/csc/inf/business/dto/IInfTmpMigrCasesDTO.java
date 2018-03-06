package com.beshara.csc.inf.business.dto;


import java.util.List;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IInfTmpMigrCasesDTO extends IInfDTO {

    /**
* @return Long
*/
Long getDatatypCode() ;
/**
* @return Long
*/
Long getCaseCode() ;
/**
* @return String
*/
String getName();
/**
* @return Long
*/
Long getNeedUpdate() ;

    
    /**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) ;
/**
* @param caseCode
*/
public void setCaseCode(Long caseCode) ;
/**
* @param name
*/
public void setName( String name );
/**
* @param needUpdate
*/
public void setNeedUpdate(Long needUpdate) ;

    
}
