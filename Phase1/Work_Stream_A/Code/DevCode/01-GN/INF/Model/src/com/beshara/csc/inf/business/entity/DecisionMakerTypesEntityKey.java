package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class DecisionMakerTypesEntityKey extends EntityKey implements IDecisionMakerTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long decmkrtypeCode;
  public DecisionMakerTypesEntityKey() {        super();
   }
    public DecisionMakerTypesEntityKey(Long decmkrtypeCode) {        super(new Object[] { decmkrtypeCode });
        this.decmkrtypeCode = decmkrtypeCode;
    }public int hashCode() {        return super.hashCode();
    }    public Long getDecmkrtypeCode() {        return decmkrtypeCode;
    }}
