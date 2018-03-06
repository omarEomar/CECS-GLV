package com.beshara.csc.inf.business.entity.org;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author       A.ElMasry
 * @version      1.0
 * @since        10/11/2014
 */
@Entity
@Table(name = "NL_ORG_CATS")
public class InfOrgCatsEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CAT_CODE", nullable = false)
    private Long catCode;
    @Column(name = "CAT_NAME", nullable = false)
    private String catName;


    @Column(name = "GOV_FLAG", nullable = false)
    private Long govFlag;


    public InfOrgCatsEntity() {
    }

    /**
     *
     * @return catCode
     */
    public Long getCatCode() {
        return catCode;
    }

    /**
     *
     * @param catCode
     */
    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    /**
     *
     * @return catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     *
     * @param catName
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     *
     * @return govFlag
     */
    public Long getGovFlag() {
        return govFlag;
    }

    /**
     *
     * @param govFlag
     */
    public void setGovFlag(Long govFlag) {
        this.govFlag = govFlag;
    }


}
