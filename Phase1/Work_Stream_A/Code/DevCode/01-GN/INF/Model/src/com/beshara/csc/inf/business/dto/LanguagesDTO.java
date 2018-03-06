package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.inf.business.entity.LanguagesEntityKey;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class LanguagesDTO extends InfDTO implements ILanguagesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long languageCode;
    private String languageName;
    private Long auditStatus;
    private Long tabrecSerial;

    /**
     * LanguagesDTO Default Constructor */
    public LanguagesDTO() {
        super();
    }

    public LanguagesDTO(Long languageCode, String languageName) {
        this.setCode(new LanguagesEntityKey(languageCode));
        this.setName(languageName);
    }

    /**
     * @param languagesEntity
     */
    public LanguagesDTO(LanguagesEntity languagesEntity) {
        this.languageCode = languagesEntity.getLanguageCode();
        this.setCode(new LanguagesEntityKey(languagesEntity.getLanguageCode()));
        this.setName(languagesEntity.getLanguageName());
        this.languageName = languagesEntity.getLanguageName();
        this.setCreatedBy(languagesEntity.getCreatedBy());
        this.setCreatedDate(languagesEntity.getCreatedDate());
        this.setLastUpdatedBy(languagesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(languagesEntity.getLastUpdatedDate());
        this.auditStatus = languagesEntity.getAuditStatus();
        this.tabrecSerial = languagesEntity.getTabrecSerial();
    }

    /**
     * @return Long
     */
    public Long getLanguageCode() {
        return languageCode;
    }

    /**
     * @return String
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    /**
     * @param languageCode
     */
    public void setLanguageCode(Long languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @param languageName
     */
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
