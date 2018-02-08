package com.beshara.csc.gn.map.business.dto;


public interface IDataDTO extends IMapClientDTO {
    public Long getObjtypeCode();

    public Long getSocCode();

    public String getSqlStatement();

    public void setObjtypeCode(Long objtypeCode);

    public void setSocCode(Long socCode);

    public void setSqlStatement(String sqlStatement);

    public void setSocietiesDTO(ISocietiesDTO societiesDTO);

    public ISocietiesDTO getSocietiesDTO();

    public void setObjectTypesDTO(IObjectTypesDTO objectTypesDTO);

    public IObjectTypesDTO getObjectTypesDTO();

}
