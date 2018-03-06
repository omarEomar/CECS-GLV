package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class LanguagesEntityKey extends EntityKey implements ILanguagesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long languageCode;

    public LanguagesEntityKey() {
        super();
    }

    public LanguagesEntityKey(Long languageCode) {
        super(new Object[] { languageCode });
        this.languageCode = languageCode;
    } //  public int hashCode() {        return super.hashCode();
    public Long getLanguageCode() {        return languageCode;
    }}
