package com.beshara.csc.gn.map.business.dto;


public interface IRelationsDTO extends IMapClientDTO {
   
    public Long getObjtypeCode();

    public void setObjtypeCode(Long objtypeCode) ;


    public Long getSoc1Code() ;

    public void setSoc1Code(Long soc1Code) ;

    public Long getSoc2Code() ;

    public void setSoc2Code(Long soc2Code) ;
    void setRelTypesDto(SocietyRelationTypesDTO relTypesDto);

    public SocietyRelationTypesDTO getRelTypesDto();
    void setReltypeCode(Long reltypeCode);
    public Long getReltypeCode() ;
    
    public void setSoc1DTO(SocietiesDTO soc1DTO) ;

    public SocietiesDTO getSoc1DTO() ;

    public void setObjectTypesDTO(ObjectTypesDTO objectTypesDTO);

    public ObjectTypesDTO getObjectTypesDTO();
    
    public void setSoc2DTO(SocietiesDTO soc2DTO) ;

    public SocietiesDTO getSoc2DTO() ;

}
