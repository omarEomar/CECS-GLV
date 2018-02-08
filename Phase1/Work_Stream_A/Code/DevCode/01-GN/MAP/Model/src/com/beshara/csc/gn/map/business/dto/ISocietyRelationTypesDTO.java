package com.beshara.csc.gn.map.business.dto;

import com.beshara.base.dto.IBasicDTO;


public interface ISocietyRelationTypesDTO extends IBasicDTO {
  
    public Long getReltypeCode() ;

    public void setReltypeCode(Long reltypeCode) ;

    public String getReltypeName() ;

    public void setReltypeName(String reltypeName) ;

    public String getReltypeSymbole() ;

    public void setReltypeSymbole(String reltypeSymbole) ;
  
}
