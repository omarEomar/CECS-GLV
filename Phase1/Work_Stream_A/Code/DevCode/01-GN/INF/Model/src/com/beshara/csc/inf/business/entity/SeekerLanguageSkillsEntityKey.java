package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class SeekerLanguageSkillsEntityKey extends EntityKey implements ISeekerLanguageSkillsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long languageCode;
    public SeekerLanguageSkillsEntityKey() {        super();
    }    public SeekerLanguageSkillsEntityKey(Long civilId, Long languageCode) {        super(new Object[] { civilId, languageCode });
        this.civilId = civilId;
        this.languageCode = languageCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }    public Long getLanguageCode() {        return languageCode;
    }}
