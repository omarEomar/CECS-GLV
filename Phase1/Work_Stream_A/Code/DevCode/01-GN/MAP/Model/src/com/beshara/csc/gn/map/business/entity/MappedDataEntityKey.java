package com.beshara.csc.gn.map.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class MappedDataEntityKey extends EntityKey implements IMappedDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long objtype1Code;
    public Long objtype2Code;
    public Long soc1Code;
    public String soc1Value;
    public Long soc2Code;
    public String soc2Value;
    public MappedDataEntityKey() {    }    public MappedDataEntityKey(Long objtype1Code, Long objtype2Code,                                Long soc1Code, String soc1Value, Long soc2Code,                                String soc2Value) {        super(new Object[] { objtype1Code, objtype2Code, soc1Code, soc1Value,                              soc2Code, soc2Value });
        this.objtype1Code = objtype1Code;
        this.objtype2Code = objtype2Code;
        this.soc1Code = soc1Code;
        this.soc1Value = soc1Value;
        this.soc2Code = soc2Code;
        this.soc2Value = soc2Value;
        //key= ( objtype1Code+"*"+objtype2Code+"*"+soc1Code+"*"+soc1Value+"*"+soc2Code+"*"+soc2Value ) .toString ( ) ; 
    }    public Long getObjtype1Code() {        return objtype1Code;
    }    public Long getObjtype2Code() {        return objtype2Code;
    }    public Long getSoc1Code() {        return soc1Code;
    }    public String getSoc1Value() {        return soc1Value;
    }    public Long getSoc2Code() {        return soc2Code;
    }    public String getSoc2Value() {        return soc2Value;
    }}
