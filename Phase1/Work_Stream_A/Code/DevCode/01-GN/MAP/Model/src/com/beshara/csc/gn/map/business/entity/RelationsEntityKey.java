package com.beshara.csc.gn.map.business.entity;


import com.beshara.base.entity.EntityKey;


public class RelationsEntityKey  extends EntityKey implements IRelationsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long objtypeCode;
    public Long soc1Code;
    public Long soc2Code;

    public RelationsEntityKey() {
    }

    public RelationsEntityKey(Long objtypeCode, Long soc1Code, Long soc2Code) {
        super(new Object[] { objtypeCode, soc1Code ,soc2Code  });
        this.objtypeCode = objtypeCode;
        this.soc1Code = soc1Code;
        this.soc2Code = soc2Code;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getObjtypeCode() {
        return objtypeCode;
    }

    public void setObjtypeCode(Long objtypeCode) {
        this.objtypeCode = objtypeCode;
    }

    public Long getSoc1Code() {
        return soc1Code;
    }

    public void setSoc1Code(Long soc1Code) {
        this.soc1Code = soc1Code;
    }

    public Long getSoc2Code() {
        return soc2Code;
    }

    public void setSoc2Code(Long soc2Code) {
        this.soc2Code = soc2Code;
    }
}
