package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class MapTypesEntityKey extends EntityKey implements IMapTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long typeCode;
    public MapTypesEntityKey() {        super();
    }    public MapTypesEntityKey(Long typeCode) {        super(new Object[] { typeCode });
        this.typeCode = typeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getTypeCode() {        return typeCode;
    }}
