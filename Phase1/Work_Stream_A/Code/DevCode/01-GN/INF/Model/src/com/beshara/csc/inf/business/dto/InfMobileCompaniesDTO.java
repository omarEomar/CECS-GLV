package com.beshara.csc.inf.business.dto;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */


public class InfMobileCompaniesDTO extends InfDTO implements IInfMobileCompaniesDTO {

    private static final long serialVersionUID = 1L;

    private Long mobCompanyCode;
    private String mobCompanyName;

    public InfMobileCompaniesDTO() {
        super();
    }

    public void setMobCompanyCode(Long mobCompanyCode) {
        this.mobCompanyCode = mobCompanyCode;
    }

    public Long getMobCompanyCode() {
        return mobCompanyCode;
    }

    public void setMobCompanyName(String mobCompanyName) {
        this.mobCompanyName = mobCompanyName;
    }

    public String getMobCompanyName() {
        return mobCompanyName;
    }
}

