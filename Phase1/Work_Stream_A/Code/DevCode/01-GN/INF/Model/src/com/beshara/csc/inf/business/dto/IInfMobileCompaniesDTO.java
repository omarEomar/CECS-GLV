package com.beshara.csc.inf.business.dto;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */

public interface IInfMobileCompaniesDTO extends IInfDTO {

    public void setMobCompanyCode(Long mobCompanyCode);

    public Long getMobCompanyCode();

    public void setMobCompanyName(String mobCompanyName);

    public String getMobCompanyName();
}
