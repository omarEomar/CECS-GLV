package com.beshara.csc.inf.business.entity;

import com.beshara.base.enhanced.entity.BaseEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of SeekerLanguageSkills Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated 
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *     
 * @author       Beshara Group   
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0   
 * @since        03/09/2007   
 */
@Entity
@NamedQueries( { @NamedQuery(name = "SeekerLanguageSkillsEntity.findAll", 
                             query = 
                             "select o from SeekerLanguageSkillsEntity o")
        , 
        @NamedQuery(name = "SeekerLanguageSkillsEntity.findNewId", query = "select MAX(o.civilId) from SeekerLanguageSkillsEntity o")
        , 
        @NamedQuery(name = "SeekerLanguageSkillsEntity.getByCivilId", query = "select o from SeekerLanguageSkillsEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId", 
                    hints = 
                    { @QueryHint(name = "toplink.refresh", value = "true")
                    } )
        , 
        @NamedQuery(name = "SeekerLanguageSkillsEntity.listAvailabe", query = "select  new com.beshara.csc.inf.business.dto.LanguagesDTO(o.languageCode,o.languageName) from LanguagesEntity o WHERE o.languageCode NOT IN (SELECT m.languageCode FROM SeekerLanguageSkillsEntity m WHERE m.civilId=:civilId)")
        } )
@Table(name = "INF_SEEKER_LANGUAGE_SKILLS")
@IdClass(ISeekerLanguageSkillsEntityKey.class)
public class

SeekerLanguageSkillsEntity extends BaseEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, 
            updatable = false)
    private Long civilId;
    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long languageCode;
    @Column(name = "SKILL_DEGREE", nullable = true)
    private String skillDegree;
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;
    @ManyToOne
    @JoinColumn(name = "LANGUAGE_CODE", referencedColumnName = "LANGUAGE_CODE")
    private LanguagesEntity languagesEntity;


    /**
     * SeekerLanguageSkillsEntity Default Constructor
     */
    public SeekerLanguageSkillsEntity() {
    }


    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
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
    public String getSkillDegree() {
        return skillDegree;
    }

    /**
     * @return Long
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @return Long
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
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
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param languageCode
     */
    public void setLanguageCode(Long languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @param skillDegree
     */
    public void setSkillDegree(String skillDegree) {
        this.skillDegree = skillDegree;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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


    public void setKwtCitizensResidentsEntity(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        this.kwtCitizensResidentsEntity = kwtCitizensResidentsEntity;
    }

    public KwtCitizensResidentsEntity getKwtCitizensResidentsEntity() {
        return kwtCitizensResidentsEntity;
    }

    public void setLanguagesEntity(LanguagesEntity languagesEntity) {
        this.languagesEntity = languagesEntity;
    }

    public LanguagesEntity getLanguagesEntity() {
        return languagesEntity;
    }
}
