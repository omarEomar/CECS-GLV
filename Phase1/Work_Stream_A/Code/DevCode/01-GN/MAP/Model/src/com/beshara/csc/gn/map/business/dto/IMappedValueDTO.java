package com.beshara.csc.gn.map.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;

import java.util.List;


public interface IMappedValueDTO extends ITreeDTO {
    public void setName(String name);

    public String getName();

    public void setStrCode(String strCode);

    public String getStrCode();

    public void setHasMappedValues(boolean hasMappedValues);

    public boolean getHasMappedValues();
    
    public IEntityKey getParentCode() ;

    
    public IBasicDTO getParentObject() ;

    public void setChildrenList(List<IBasicDTO> list);
    
    public List<IBasicDTO> getChildrenList() ;

    
    public Long getChildernNumbers();

    
    public Long getLeafFlag() ;

    
    public boolean isBooleanLeafFlag() ;

    
    public Long getTreeLevel() ;

    public IEntityKey getFirstParent() ;

}
