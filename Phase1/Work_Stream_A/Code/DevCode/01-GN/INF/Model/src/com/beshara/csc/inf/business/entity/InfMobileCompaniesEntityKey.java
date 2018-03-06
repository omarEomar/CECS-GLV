package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */

public class InfMobileCompaniesEntityKey extends EntityKey implements IInfMobileCompaniesEntityKey {

    private static final long serialVersionUID = 1L;

    private Long mobCompanyCode;


    public InfMobileCompaniesEntityKey() {
        super();
    }

    public InfMobileCompaniesEntityKey(Long mobCompanyCode) {
        super(new Object[] { mobCompanyCode });
        this.mobCompanyCode = mobCompanyCode;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getMobCompanyCode() {
        return mobCompanyCode;
    }

}
