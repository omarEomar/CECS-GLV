package com.beshara.csc.inf.business.dto;


public class SpecialPeriodTypesDTO extends InfDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private Long spcprdtypeCode;
    private String spcprdtypeName;

    public SpecialPeriodTypesDTO() {
        super();
    }

    public SpecialPeriodTypesDTO(Long spcprdtypeCode, String spcprdtypeName) {
        super();
        this.spcprdtypeCode = spcprdtypeCode;
        this.spcprdtypeName = spcprdtypeName;
    }

    public void setSpcprdtypeCode(Long spcprdtypeCode) {
        this.spcprdtypeCode = spcprdtypeCode;
    }

    public Long getSpcprdtypeCode() {
        return spcprdtypeCode;
    }

    public void setSpcprdtypeName(String spcprdtypeName) {
        this.spcprdtypeName = spcprdtypeName;
    }

    public String getSpcprdtypeName() {
        return spcprdtypeName;
    }
}
