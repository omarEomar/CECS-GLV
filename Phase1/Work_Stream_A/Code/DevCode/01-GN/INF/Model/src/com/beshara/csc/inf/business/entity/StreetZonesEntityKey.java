package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class StreetZonesEntityKey extends EntityKey implements IStreetZonesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String mapCode;
    private Long streetCode;
    public StreetZonesEntityKey() {        super();
    }    public StreetZonesEntityKey(String mapCode, Long streetCode) {        super(new Object[] { mapCode, streetCode });
        this.mapCode = mapCode;
        this.streetCode = streetCode;
    }    public int hashCode() {        return super.hashCode();
    }    public String getMapCode() {        return mapCode;
    }    public Long getStreetCode() {        return streetCode;
    }}
