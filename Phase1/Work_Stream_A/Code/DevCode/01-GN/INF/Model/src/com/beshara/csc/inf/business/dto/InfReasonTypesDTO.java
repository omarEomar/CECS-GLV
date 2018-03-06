package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;

import java.sql.Timestamp;

public class InfReasonTypesDTO extends InfDTO implements IInfReasonTypesDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private Long restypeCode;

    private String restypeName;

    private Long tabrecSerial;

    public InfReasonTypesDTO() {
    }
    
    public InfReasonTypesDTO(Long restypeCode,String restypeName){
    this.setCode(InfEntityKeyFactory.createReasonTypesEntityKey(restypeCode));
    this.setName(restypeName);
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setRestypeName(String restypeName) {
        this.restypeName = restypeName;
    }

    public String getRestypeName() {
        return restypeName;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
