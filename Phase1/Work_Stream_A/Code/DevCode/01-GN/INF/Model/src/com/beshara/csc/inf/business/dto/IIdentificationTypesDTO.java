package com.beshara.csc.inf.business.dto;

import java.math.BigDecimal;

import java.sql.Timestamp;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IIdentificationTypesDTO extends IInfDTO {

    /**
     * @return Long
     */
    Long getIdtypeCode();

    /**
     * @return String
     */
    String getName();


    /**
     * @param idtypeCode
     */
    public void setIdtypeCode(Long idtypeCode);

    /**
     * @param name
     */
    public void setName(String name);
    
    public void setValidFromDate(Timestamp fromDate) ;
    
    public Timestamp getValidFromDate() ;
    
    public void setValidUntilDate(Timestamp untilDate) ;
    
    public Timestamp getValidUntilDate() ;
    
    public void setNotes(String notes) ;
    
    public String getNotes();
    
    public void setValidDateFlag(Boolean validDateFlag) ;

    public Boolean getValidDateFlag();
    
    public void setTabRecSerial(BigDecimal tabRecSerial);

    public BigDecimal getTabRecSerial();

}
