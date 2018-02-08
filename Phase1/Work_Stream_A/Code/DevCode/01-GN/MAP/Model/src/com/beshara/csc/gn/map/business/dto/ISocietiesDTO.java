package com.beshara.csc.gn.map.business.dto;


public interface ISocietiesDTO extends IMapClientDTO {
    
    
    public void setMinCode(Long minCode);

    public Long getMinCode();

    public void setSocietiesStatus(Long societiesStatus);

    public Long getSocietiesStatus();
}
