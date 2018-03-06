package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class GenderReligionEntityKey extends EntityKey implements IGenderReligionEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gentypeCode;
    private Long religionCode;
    public GenderReligionEntityKey() {        super();
    }    public GenderReligionEntityKey(Long gentypeCode, Long religionCode) {        super(new Object[] { gentypeCode, religionCode });
        this.gentypeCode = gentypeCode;
        this.religionCode = religionCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getGentypeCode() {        return gentypeCode;
    }    public Long getReligionCode() {        return religionCode;
    }}
