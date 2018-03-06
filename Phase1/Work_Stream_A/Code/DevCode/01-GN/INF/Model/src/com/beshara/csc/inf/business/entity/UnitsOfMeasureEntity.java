package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@NamedQueries( { @NamedQuery(name = "UnitsOfMeasureEntity.findAll",
                             query = "select o from UnitsOfMeasureEntity o order by o.unitCode"),
                            @NamedQuery(name = "UnitsOfMeasureEntity.findUnitsOfMeasureCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.UnitsOfMeasureDTO(o.unitCode,o.untitArabicName) from UnitsOfMeasureEntity o order by o.unitCode ")
                })
@Table(name = "INF_UNITS_OF_MEASURE")
@IdClass(IUnitsOfMeasureEntityKey.class)

public class UnitsOfMeasureEntity extends BasicEntity{
    
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UNIT_CODE", nullable = false)
    private Long unitCode;
    
    
    @Column(name = "UNIT_A_NAME", nullable = false)
    private String untitArabicName;  
    
    
    @Column(name = "UNIT_E_NAME")
    private String untitEnglishName;  
    
    @Column(name = "DEFAULT_UNIT", nullable = false)
    private Long defaultUnit; 
    
    @Column(name = "UNITUSED", nullable = false)
    private Long unitUse; 
    
    @Column(name = "CONVERT_FUN_TO_DEFAULT")
    private String convertFunToDefault; 
    
    
        
    public UnitsOfMeasureEntity() {
        super();
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
