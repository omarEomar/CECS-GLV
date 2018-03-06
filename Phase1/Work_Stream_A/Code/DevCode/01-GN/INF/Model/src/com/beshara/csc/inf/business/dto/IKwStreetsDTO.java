package com.beshara.csc.inf.business.dto;


public interface IKwStreetsDTO extends IInfDTO {
    public Long getStreetLengthInKm();

    public void setStreetLengthInKm(Long streetLengthInKm);

    public void setCreatedBy(String createdBy);
    
    public void setKwStreetsName(String kwStreetsName) ;

    public String getKwStreetsName() ;

}
