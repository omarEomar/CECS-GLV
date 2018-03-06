package com.beshara.csc.inf.business.dto;

public interface IUnitsOfMeasureDTO extends IInfDTO {
    public void setUnitCode(Long unitCode);

    public Long getUnitCode();

    public void setUntitArabicName(String untitArabicName);

    public String getUntitArabicName();

    public void setUntitEnglishName(String untitEnglishName);

    public String getUntitEnglishName();

    public void setDefaultUnit(Long defaultUnit);

    public Long getDefaultUnit();

    public void setUnitUse(Long unitUse);

    public Long getUnitUse();

    public void setConvertFunToDefault(String convertFunToDefault);

    public String getConvertFunToDefault();

}
