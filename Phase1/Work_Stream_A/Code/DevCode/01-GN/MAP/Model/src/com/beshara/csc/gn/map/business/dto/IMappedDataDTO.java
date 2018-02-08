package com.beshara.csc.gn.map.business.dto;

import com.beshara.csc.gn.map.business.entity.MappedDataEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;

import java.io.Serializable;

import com.beshara.base.dto.*;

public interface IMappedDataDTO extends IMapClientDTO {
    public String getSoc1Value();

    public String getSoc2Value();

    public Long getMapStatus();

    public void setSoc1Value(String soc1Value);

    public void setSoc2Value(String soc2Value);

    public void setMapStatus(Long mapStatus);

    public void setObjtype1Code(IObjectTypesDTO objtype1Code);

    public IObjectTypesDTO getObjtype1Code();

    public void setSoc1Code(ISocietiesDTO soc1Code);

    public ISocietiesDTO getSoc1Code();

    public void setObjtype2Code(IObjectTypesDTO objtype2Code);

    public IObjectTypesDTO getObjtype2Code();

    public void setSoc2Code(ISocietiesDTO soc2Code);

    public ISocietiesDTO getSoc2Code();

    public void setBooleanMapStatus(boolean booleanMapStatus);

    public boolean isBooleanMmapStatus();

}
