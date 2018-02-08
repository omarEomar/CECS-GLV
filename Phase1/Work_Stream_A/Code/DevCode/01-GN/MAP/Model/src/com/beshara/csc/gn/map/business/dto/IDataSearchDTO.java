package com.beshara.csc.gn.map.business.dto;

import com.beshara.base.paging.IPagingRequestDTO;

public interface IDataSearchDTO extends IMapClientDTO {
    public void setObjtypeCode(Long objtypeCode);

    public Long getObjtypeCode();

    public void setSoc2Code(Long soc2Code);

    public Long getSoc2Code();

    public void setSoc1Code(Long soc1Code);

    public Long getSoc1Code();

    public void setSoc1Value(String soc1Value);

    public String getSoc1Value();

    public void setParam(String param);

    public String getParam();
    public void setCodeValue(String codeValue) ;

    public String getCodeValue() ;

    public void setRequestDTO(IPagingRequestDTO requestDTO);

    public IPagingRequestDTO getRequestDTO();
}
