package com.beshara.csc.inf.business.dto;


import java.util.List;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IInfTmpDataDisksDTO extends IInfDTO {

    /**
* @return Long
*/
Long getDatatypCode() ;
/**
* @return Long
*/
Long getDiskCode() ;
/**
* @return Timestamp
*/
Timestamp getDiskDate() ;

    
    /**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) ;
/**
* @param diskCode
*/
public void setDiskCode(Long diskCode) ;
/**
* @param diskDate
*/
public void setDiskDate(Timestamp diskDate) ;

    
}
