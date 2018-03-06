package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntity;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
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
public class SeekerLanguageSkillsDTO extends InfDTO implements ISeekerLanguageSkillsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long languageCode;
    private String skillDegree;
    private Long auditStatus;
    private Long tabrecSerial;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    private ILanguagesDTO languagesDTO;
    /** 
     * SeekerLanguageSkillsDTO Default Constructor */
    public SeekerLanguageSkillsDTO() {        super();
    }    /** 
     * @param seekerLanguageSkillsEntity 
     */
    public SeekerLanguageSkillsDTO(SeekerLanguageSkillsEntity seekerLanguageSkillsEntity) {        this.civilId = seekerLanguageSkillsEntity.getCivilId();
        this.setCode(new SeekerLanguageSkillsEntityKey(seekerLanguageSkillsEntity.getCivilId(),                                                        seekerLanguageSkillsEntity.getLanguageCode()));
        this.languagesDTO =                 new LanguagesDTO(seekerLanguageSkillsEntity.getLanguagesEntity());
        this.languageCode = seekerLanguageSkillsEntity.getLanguageCode();
        this.skillDegree = seekerLanguageSkillsEntity.getSkillDegree();
        this.setCreatedBy(seekerLanguageSkillsEntity.getCreatedBy());
        this.setCreatedDate(seekerLanguageSkillsEntity.getCreatedDate());
        this.setLastUpdatedBy(seekerLanguageSkillsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(seekerLanguageSkillsEntity.getLastUpdatedDate());
        this.auditStatus = seekerLanguageSkillsEntity.getAuditStatus();
        this.tabrecSerial = seekerLanguageSkillsEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getLanguageCode() {        return languageCode;
    }    /** 
     * @return String 
     */
    public String getSkillDegree() {        return skillDegree;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param civilId 
     */
    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    /** 
     * @param languageCode 
     */
    public void setLanguageCode(Long languageCode) {        this.languageCode = languageCode;
    }    /** 
     * @param skillDegree 
     */
    public void setSkillDegree(String skillDegree) {        this.skillDegree = skillDegree;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {        return kwtCitizensResidentsDTO;
    }    public void setLanguagesDTO(ILanguagesDTO languagesDTO) {        this.languagesDTO = languagesDTO;
    }    public ILanguagesDTO getLanguagesDTO() {        return languagesDTO;
    }}
