package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.math.BigDecimal;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Entity
@NamedQueries( { @NamedQuery(name = "IdentificationTypesEntity.findAll", query = "select o from IdentificationTypesEntity o"),
                 @NamedQuery(name = "IdentificationTypesEntity.findNewId",
                             query = "select MAX(o.idtypeCode) from IdentificationTypesEntity o"),
                 @NamedQuery(name = "IdentificationTypesEntity.searchByCode",
                             query = "select o from IdentificationTypesEntity o where o.idtypeCode=:code"),

        @NamedQuery(name = "IdentificationTypesEntity.searchByName",
                    query = "select o from IdentificationTypesEntity o where o.name like :name order by o.name") })
@Table(name = "INF_IDNTIFICATION_TYPES")
@IdClass(IIdentificationTypesEntityKey.class)

public class IdentificationTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "IDTYPE_CODE", nullable = false)
    private Long idtypeCode;
    @Column(name = "IDTYPE_NAME", nullable = false)
    private String name;

    @Column(name = "FROM_DATE", nullable = true)
    private Timestamp validFromDate;

    @Column(name = "UNTIL_DATE", nullable = true)
    private Timestamp validUntilDate;

    @Column(name = "NOTES", nullable = true)
    private String notes;
    
    @Column(name = "TABREC_SERIAL", nullable = true)
    private BigDecimal tabRecSerail;

    /**
     * IdentificationTypesEntity Default Constructor
     */
    public IdentificationTypesEntity() {
    }


    /**
     * @return Long
     */
    public Long getIdtypeCode() {
        return idtypeCode;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * @param idtypeCode
     */
    public void setIdtypeCode(Long idtypeCode) {
        this.idtypeCode = idtypeCode;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setValidFromDate(Timestamp validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Timestamp getValidFromDate() {
        return validFromDate;
    }

    public void setValidUntilDate(Timestamp validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public Timestamp getValidUntilDate() {
        return validUntilDate;
    }

    public void setTabRecSerail(BigDecimal tabRecSerail) {
        this.tabRecSerail = tabRecSerail;
    }

    public BigDecimal getTabRecSerail() {
        return tabRecSerail;
    }
}
