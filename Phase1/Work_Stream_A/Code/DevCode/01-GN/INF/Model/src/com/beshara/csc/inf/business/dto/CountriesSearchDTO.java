package com.beshara.csc.inf.business.dto;

public class CountriesSearchDTO extends InfDTO implements ICountriesSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntryCode;
    private Long ctyclassCode;

    public CountriesSearchDTO() {
    }

    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    public Long getCntryCode() {
        return cntryCode;
    }

    public void setCtyclassCode(Long ctyclassCode) {
        this.ctyclassCode = ctyclassCode;
    }

    public Long getCtyclassCode() {
        return ctyclassCode;
    }
}
