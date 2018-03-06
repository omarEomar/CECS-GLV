package com.beshara.csc.inf.business.dto;

public interface ICountriesSearchDTO extends IInfDTO{
    public void setCntryCode(Long cntryCode);

    public Long getCntryCode();

    public void setCtyclassCode(Long ctyclassCode);

    public Long getCtyclassCode();
}
