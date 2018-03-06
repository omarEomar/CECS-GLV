package com.beshara.csc.inf.business.dto;

import java.math.BigDecimal;

import java.sql.Timestamp;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */


public class IdentificationTypesDTO extends InfDTO implements IIdentificationTypesDTO {

    @SuppressWarnings("compatibility:-2946515349961281735")
    private static final long serialVersionUID = 1L;
    private Long idtypeCode;
    private String name;

    private Timestamp validFromDate;
    private Timestamp validUntilDate;
    private String notes;
    private Boolean validDateFlag;
    
    private BigDecimal tabRecSerial;
    
    /**
     * InfTypesDTO Default Constructor
     */
    public IdentificationTypesDTO() {
        super();
    }


    /**
     * @return Long
     */
    public Long getIdtypeCode() {
        return idtypeCode;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * @param idtypeCode
     */
    public void setIdtypeCode(Long idtypeCode) {
        this.idtypeCode = idtypeCode;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setValidDateFlag(Boolean validDateFlag) {
        this.validDateFlag = validDateFlag;
    }

    public Boolean getValidDateFlag() {
        return validDateFlag;
    }

    public void setValidFromDate(Timestamp validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Timestamp getValidFromDate() {
        return validFromDate;
    }

    public void setValidUntilDate(Timestamp validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public Timestamp getValidUntilDate() {
        return validUntilDate;
    }

    public void setTabRecSerial(BigDecimal tabRecSerial) {
        this.tabRecSerial = tabRecSerial;
    }

    public BigDecimal getTabRecSerial() {
        return tabRecSerial;
    }
}

