package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;

import javax.persistence.Column;
import javax.persistence.Id;

public class UnitsOfMeasureDTO extends InfDTO implements IUnitsOfMeasureDTO {
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
  
    private Long unitCode;
 
    private String untitArabicName;  

    private String untitEnglishName;  

    private Long defaultUnit; 
    
    private Long unitUse; 

    private String convertFunToDefault; 
    
    public UnitsOfMeasureDTO() {
        super();
    }
    
    public UnitsOfMeasureDTO(Long code, String name) {        
        this.setCode(InfEntityKeyFactory.createUnitsOfMeasureEntityKey(code));
        this.setName(name);
    }

    public void setUnitCode(Long unitCode) {
        this.unitCode = unitCode;
    }

    public Long getUnitCode() {
        return unitCode;
    }

    public void setUntitArabicName(String untitArabicName) {
        this.untitArabicName = untitArabicName;
    }

    public String getUntitArabicName() {
        return untitArabicName;
    }

    public void setUntitEnglishName(String untitEnglishName) {
        this.untitEnglishName = untitEnglishName;
    }

    public String getUntitEnglishName() {
        return untitEnglishName;
    }

    public void setDefaultUnit(Long defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public Long getDefaultUnit() {
        return defaultUnit;
    }

    public void setUnitUse(Long unitUse) {
        this.unitUse = unitUse;
    }

    public Long getUnitUse() {
        return unitUse;
    }

    public void setConvertFunToDefault(String convertFunToDefault) {
        this.convertFunToDefault = convertFunToDefault;
    }

    public String getConvertFunToDefault() {
        return convertFunToDefault;
    }
}
