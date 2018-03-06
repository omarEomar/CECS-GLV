package com.beshara.csc.inf.business.dto;

public interface IInfReasonDataDTO extends IInfDTO {

    public void setResdatDesc(String resdatDesc) ;

    public String getResdatDesc() ;

    public void setResdatSerial(Long resdatSerial) ;
    
    public Long getResdatSerial() ;

    public void setRestypeCode(Long restypeCode) ;

    public Long getRestypeCode() ;
    
    public void setTabrecSerial(Long tabrecSerial) ;

    public Long getTabrecSerial() ;
    
    public void setReasonTypesDTO(IInfReasonTypesDTO reasonTypesDTO);
    
    public IInfReasonTypesDTO getReasonTypesDTO() ;
}
