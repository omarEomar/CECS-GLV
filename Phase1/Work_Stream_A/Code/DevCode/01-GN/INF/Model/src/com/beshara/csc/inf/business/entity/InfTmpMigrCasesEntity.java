package com.beshara.csc.inf.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.beshara.base.entity.BasicEntity;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Entity
@NamedQueries( { @NamedQuery(name = "InfTmpMigrCasesEntity.findAll", query = "select o from InfTmpMigrCasesEntity o"),
                 @NamedQuery(name = "InfTmpMigrCasesEntity.findNewId",
                             query = "select MAX(o.datatypCode) from InfTmpMigrCasesEntity o"),
                 @NamedQuery(name = "InfTmpMigrCasesEntity.getAllByDataTypeCode",
                             query = "select o from InfTmpMigrCasesEntity o where o.datatypCode =:datatypCode and o.needUpdate = 1 order by o.caseCode"),
                 @NamedQuery(name = "InfTmpMigrCasesEntity.searchByName",
                             query = "select o from InfTmpMigrCasesEntity o where o.name like :name order by o.name") })
@Table(name = "INF_TMP_MIGR_CASES")
@IdClass(IInfTmpMigrCasesEntityKey.class)

public class InfTmpMigrCasesEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "DATATYP_CODE", nullable = false)
    private Long datatypCode;
    @Id
    @Column(name = "CASE_CODE", nullable = false)
    private Long caseCode;
    @Column(name = "CASE_NAME", nullable = false)
    private String name;
    @Column(name = "NEED_UPDATE", nullable = false)
    private Long needUpdate;
    //@ManyToOne
    //@JoinColumn(name="DATATYP_CODE", referencedColumnName="DATATYP_CODE")
    //private InfTmpDataTypesEntity infTmpDataTypesEntity;


    /**
     * InfTmpMigrCasesEntity Default Constructor
     */
    public InfTmpMigrCasesEntity() {
    }


    /**
     * @return Long
     */
    public Long getDatatypCode() {
        return datatypCode;
    }

    /**
     * @return Long
     */
    public Long getCaseCode() {
        return caseCode;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return Long
     */
    public Long getNeedUpdate() {
        return needUpdate;
    }


    /**
     * @param datatypCode
     */
    public void setDatatypCode(Long datatypCode) {
        this.datatypCode = datatypCode;
    }

    /**
     * @param caseCode
     */
    public void setCaseCode(Long caseCode) {
        this.caseCode = caseCode;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param needUpdate
     */
    public void setNeedUpdate(Long needUpdate) {
        this.needUpdate = needUpdate;
    }


}
