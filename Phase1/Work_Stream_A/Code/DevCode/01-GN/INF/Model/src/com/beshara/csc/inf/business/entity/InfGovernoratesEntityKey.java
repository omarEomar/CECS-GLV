package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfGovernoratesEntityKey extends EntityKey implements IInfGovernoratesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    //private static final long serialVersionUID = 6312476469341204240L;
    private Long governorateCode;
    public InfGovernoratesEntityKey() {
        super();
    }    
    
    public InfGovernoratesEntityKey(Long governorateCode) {        
        super(new Object[] { governorateCode });
        this.governorateCode = governorateCode;
    }    
    
    public int hashCode() {       
        return super.hashCode();
    }    
    

    @Override
    public Long getGovernorateCode() {
        return  governorateCode;
    }
}
