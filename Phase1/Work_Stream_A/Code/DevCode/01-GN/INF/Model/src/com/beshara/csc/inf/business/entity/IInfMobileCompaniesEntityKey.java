package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.IEntityKey;

/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */

public interface IInfMobileCompaniesEntityKey extends IEntityKey {

    int hashCode();

    Long getMobCompanyCode();

}
