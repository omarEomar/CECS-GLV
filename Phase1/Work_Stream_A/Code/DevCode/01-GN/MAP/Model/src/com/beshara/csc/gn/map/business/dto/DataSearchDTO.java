package com.beshara.csc.gn.map.business.dto;

import com.beshara.base.paging.IPagingRequestDTO;

public class DataSearchDTO extends MapClientDTO implements IDataSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long objtypeCode; 
    private Long soc2Code; 
    private Long soc1Code; 
    private String soc1Value; 
    private String param;
    private IPagingRequestDTO requestDTO;
    private String codeValue;
    
    public DataSearchDTO() {
    }

    public void setObjtypeCode(Long objtypeCode) {
        this.objtypeCode = objtypeCode;
    }

    public Long getObjtypeCode() {
        return objtypeCode;
    }

    public void setSoc2Code(Long soc2Code) {
        this.soc2Code = soc2Code;
    }

    public Long getSoc2Code() {
        return soc2Code;
    }

    public void setSoc1Code(Long soc1Code) {
        this.soc1Code = soc1Code;
    }

    public Long getSoc1Code() {
        return soc1Code;
    }

    public void setSoc1Value(String soc1Value) {
        this.soc1Value = soc1Value;
    }

    public String getSoc1Value() {
        return soc1Value;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setRequestDTO(IPagingRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    public IPagingRequestDTO getRequestDTO() {
        return requestDTO;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeValue() {
        return codeValue;
    }
}
