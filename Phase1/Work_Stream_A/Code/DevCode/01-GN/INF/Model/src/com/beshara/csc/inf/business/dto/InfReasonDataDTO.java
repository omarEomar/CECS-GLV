package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;

public class InfReasonDataDTO extends InfDTO implements IInfReasonDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private String resdatDesc;
    private Long resdatSerial;
    private Long restypeCode;
    
    private Long tabrecSerial;
    
    private IInfReasonTypesDTO reasonTypesDTO ;

    public InfReasonDataDTO() {
    }

    
    public InfReasonDataDTO(Long resdatSerial,Long restypeCode, String resdatDesc) {
        this.setCode(InfEntityKeyFactory.createReasonDataEntityKey(resdatSerial,restypeCode));
        this.setName(resdatDesc);
    }
    
    public void setResdatDesc(String resdatDesc) {
        this.resdatDesc = resdatDesc;
    }

    public String getResdatDesc() {
        return resdatDesc;
    }

    public void setResdatSerial(Long resdatSerial) {
        this.resdatSerial = resdatSerial;
    }

    public Long getResdatSerial() {
        return resdatSerial;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setReasonTypesDTO(IInfReasonTypesDTO reasonTypesDTO) {
        this.reasonTypesDTO = reasonTypesDTO;
    }

    public IInfReasonTypesDTO getReasonTypesDTO() {
        return reasonTypesDTO;
    }
}
