package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class IdentificationTypesEntityKey extends EntityKey implements IIdentificationTypesEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long idtypeCode;


    public IdentificationTypesEntityKey() {
        super();
    }

    public IdentificationTypesEntityKey(Long idtypeCode) {
        super(new Object[] { idtypeCode });
        this.idtypeCode = idtypeCode;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getIdtypeCode() {
        return idtypeCode;
    }

}
