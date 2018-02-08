package com.beshara.csc.gn.map.business.dto;


import com.beshara.base.dto.BasicDTO;

public class SocietyRelationTypesDTO extends BasicDTO implements ISocietyRelationTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long reltypeCode;
    private String reltypeName;
    private String reltypeSymbole;
   
    

    public SocietyRelationTypesDTO() {
    }

    public SocietyRelationTypesDTO(Long reltypeCode, String reltypeName, String reltypeSymbole) {
        this.reltypeCode = reltypeCode;
        this.reltypeName = reltypeName;
        this.reltypeSymbole = reltypeSymbole;
    }


    public Long getReltypeCode() {
        return reltypeCode;
    }

    public void setReltypeCode(Long reltypeCode) {
        this.reltypeCode = reltypeCode;
    }

    public String getReltypeName() {
        return reltypeName;
    }

    public void setReltypeName(String reltypeName) {
        this.reltypeName = reltypeName;
    }

    public String getReltypeSymbole() {
        return reltypeSymbole;
    }

    public void setReltypeSymbole(String reltypeSymbole) {
        this.reltypeSymbole = reltypeSymbole;
    }
  
}
