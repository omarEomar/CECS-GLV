package com.beshara.csc.inf.business.entity.org;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author      A.ElMasry
 * @version      1.0
 * @since        10/11/2014
 */
@Entity
@Table(name = "NL_ORG_MINISTRIES")
public class InfMinistriesEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MIN_CODE", nullable = false)
    private Long minCode;
    @Column(name = "MIN_NAME", nullable = false)
    private String minName;


    @Column(name = "CAT_CODE", insertable = true, updatable = true)
    private Long catCode;
    @ManyToOne
    @JoinColumn(name = "CAT_CODE", referencedColumnName = "CAT_CODE", insertable = false, updatable = false)
    private InfOrgCatsEntity catsEntity;


    public InfMinistriesEntity() {
    }


    /**
     *
     * @return minCode
     */
    public Long getMinCode() {
        return minCode;
    }

    /**
     *
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     *
     * @return minName
     */
    public String getMinName() {
        return minName;
    }

    /**
     *
     * @param minName
     */
    public void setMinName(String minName) {
        this.minName = minName;
    }


    /**
     *
     * @return catsEntity
     */
    public InfOrgCatsEntity getCatsEntity() {
        return catsEntity;
    }

    /**
     *
     * @param catsEntity
     */
    public void setCatsEntity(InfOrgCatsEntity catsEntity) {
        this.catsEntity = catsEntity;
    }


    //    public void setSalDeductToMinistriesEntityList(List<OrgSalDeductToMinistriesEntity> salDeductToMinistriesEntityList) {
    //        this.salDeductToMinistriesEntityList = salDeductToMinistriesEntityList;
    //    }
    //
    //    public List<OrgSalDeductToMinistriesEntity> getSalDeductToMinistriesEntityList() {
    //        return salDeductToMinistriesEntityList;
    //    }


    //    public void setParentMinistriesEntity(MinistriesEntity parentMinistriesEntity) {
    //        this.parentMinistriesEntity = parentMinistriesEntity;
    //    }
    //
    //    public MinistriesEntity getParentMinistriesEntity() {
    //        return parentMinistriesEntity;
    //    }

    //    public void setMinistriesEntityList(List<MinistriesEntity> ministriesEntityList) {
    //        this.ministriesEntityList = ministriesEntityList;
    //    }
    //
    //    public List<MinistriesEntity> getMinistriesEntityList() {
    //        return ministriesEntityList;
    //    }


    //
    //    public void setFirstParentEntity(MinistriesEntity firstParentEntity) {
    //        this.firstParentEntity = firstParentEntity;
    //    }
    //
    //    public MinistriesEntity getFirstParentEntity() {
    //        return firstParentEntity;
    //    }
    //
    //    public void setMinistriesEntityList(List<MinistriesEntity> ministriesEntityList) {
    //        this.ministriesEntityList = ministriesEntityList;
    //    }
    //
    //    public List<MinistriesEntity> getMinistriesEntityList() {
    //        return ministriesEntityList;
    //    }


    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public Long getCatCode() {
        return catCode;
    }


}
