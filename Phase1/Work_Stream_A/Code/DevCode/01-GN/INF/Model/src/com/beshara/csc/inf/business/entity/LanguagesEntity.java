package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of Languages Entity.
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
 * @author Aly Noor 
 * @version      2.0 
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "LanguagesEntity.findAll", 
                             query = "select o from LanguagesEntity o order by o.languageName")
        , 
        @NamedQuery(name = "LanguagesEntity.findNewId", query = "select MAX(o.languageCode) from LanguagesEntity o")
        , 
        @NamedQuery(name = "LanguagesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.LanguagesDTO(o.languageCode,o.languageName) from LanguagesEntity o order by o.languageName")
        , 
        @NamedQuery(name = "LanguagesEntity.searchByName", query = "select o from LanguagesEntity o where o.languageName like :languageName order by o.languageName")
        , 
        @NamedQuery(name = "LanguagesEntity.searchByCode", query = "select o from LanguagesEntity o where o.languageCode=:languageCode order by o.languageName")
                ,
        @NamedQuery(name = "LanguagesEntity.getByName", query = "select o from LanguagesEntity o where o.languageName =:name")          
        } )
@Table(name = "INF_LANGUAGES")
@IdClass(ILanguagesEntityKey.class)
public class LanguagesEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private Long languageCode;
    @Column(name = "LANGUAGE_NAME", nullable = false)
    private String languageName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * LanguagesEntity Default Constructor
     */
    public LanguagesEntity() {
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
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
