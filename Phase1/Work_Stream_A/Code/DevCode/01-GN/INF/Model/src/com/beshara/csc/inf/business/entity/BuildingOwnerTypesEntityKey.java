package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class BuildingOwnerTypesEntityKey extends EntityKey implements IBuildingOwnerTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long owntypeCode;
    public BuildingOwnerTypesEntityKey(Long buildingOwnerCode1) {        super(new Object[] { buildingOwnerCode1 });
        this.owntypeCode = buildingOwnerCode1;
    }    public Long getOwntypeCode() {        return owntypeCode;
    }}
