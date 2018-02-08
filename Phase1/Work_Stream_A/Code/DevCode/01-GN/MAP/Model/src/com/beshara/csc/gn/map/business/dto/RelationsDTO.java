package com.beshara.csc.gn.map.business.dto;


public class RelationsDTO extends MapClientDTO implements IRelationsDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long objtypeCode;
    private Long soc1Code;
    private Long soc2Code;
    private Long reltypeCode;
    private SocietyRelationTypesDTO relTypesDto;
    private SocietiesDTO soc1DTO ;
    private SocietiesDTO soc2DTO ;
    private ObjectTypesDTO objectTypesDTO ;
    
    public RelationsDTO() {
    }

    public RelationsDTO( Long objtypeCode, Long reltypeCode,
                           Long soc1Code, Long soc2Code) {
        this.objtypeCode = objtypeCode;
        this.reltypeCode = reltypeCode;
        
        this.soc1Code = soc1Code;
        this.soc2Code = soc2Code;
        
    }

    
    public Long getObjtypeCode() {
        return objtypeCode;
    }

    public void setObjtypeCode(Long objtypeCode) {
        this.objtypeCode = objtypeCode;
    }


    public Long getSoc1Code() {
        return soc1Code;
    }

    public void setSoc1Code(Long soc1Code) {
        this.soc1Code = soc1Code;
    }

    public Long getSoc2Code() {
        return soc2Code;
    }

    public void setSoc2Code(Long soc2Code) {
        this.soc2Code = soc2Code;
    }

    public void setRelTypesDto(SocietyRelationTypesDTO relTypesDto) {
        this.relTypesDto = relTypesDto;
    }

    public SocietyRelationTypesDTO getRelTypesDto() {
        return relTypesDto;
    }

    public void setReltypeCode(Long reltypeCode) {
        this.reltypeCode = reltypeCode;
    }

    public Long getReltypeCode() {
        return reltypeCode;
    }

    public void setSoc1DTO(SocietiesDTO societiesDTO) {
        this.soc1DTO = societiesDTO;
    }

    public SocietiesDTO getSoc1DTO() {
        return soc1DTO;
    }

    public void setObjectTypesDTO(ObjectTypesDTO objectTypesDTO) {
        this.objectTypesDTO = objectTypesDTO;
    }

    public ObjectTypesDTO getObjectTypesDTO() {
        return objectTypesDTO;
    }

    public void setSoc2DTO(SocietiesDTO soc2DTO) {
        this.soc2DTO = soc2DTO;
    }

    public SocietiesDTO getSoc2DTO() {
        return soc2DTO;
    }
}
