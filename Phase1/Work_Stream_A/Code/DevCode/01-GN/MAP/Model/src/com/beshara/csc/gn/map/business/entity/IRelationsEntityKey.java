package com.beshara.csc.gn.map.business.entity;


import com.beshara.base.entity.IEntityKey;


public interface IRelationsEntityKey extends IEntityKey  {
  
    public Long getObjtypeCode();

    public void setObjtypeCode(Long objtypeCode);

    public Long getSoc1Code();

    public void setSoc1Code(Long soc1Code);

    public Long getSoc2Code() ;

    public void setSoc2Code(Long soc2Code);
}
