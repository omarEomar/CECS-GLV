package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.entity.bgt.BgtProgramsEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of ApprovalMakers Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Abdul Mejid  30-Oct-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Kamal
 * @version      1.0
 * @since        19/10/2015
 * @EditedBy @author Aly Noor @since 06/26/2014 eidted to extends BasicEntity
 * to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "SpecialPeriodsEntity.findAll", query = "select o from SpecialPeriodsEntity o "),
                 @NamedQuery(name = "SpecialPeriodsEntity.findNewId",
                             query = "select MAX(o.serial) from SpecialPeriodsEntity o"),
                 @NamedQuery(name = "SpecialPeriodsEntity.getAllByTypeAndMinCode",
                             query = "select o from SpecialPeriodsEntity o where o.minCode =:minCode and o.spcprdtypeCode =:spcprdtypeCode"),
                 @NamedQuery(name = "SpecialPeriodsEntity.getAllByTypeAndMinCodeAndYear",
                             query = "select o from SpecialPeriodsEntity o where o.minCode =:minCode and o.spcprdtypeCode =:spcprdtypeCode and o.yearCode=:yearCode ") })
@Table(name = "INF_SPECIAL_PERIODS")
@IdClass(ISpecialPeriodsEntityKey.class)
public class SpecialPeriodsEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "SPCPRDTYPE_CODE", nullable = false,insertable = false,updatable = false)
    private Long spcprdtypeCode;
    @Column(name = "YEAR_CODE", nullable = false)
    private Long yearCode;
    @Column(name = "MIN_CODE")
    private Long minCode;
    @Column(name = "PRG_CODE")
    private String prgCode;
    @Column(name = "PERIOD_DESC")
    private String periodDesc;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE", nullable = false)
    private Date untilDate;
    @Column(name = "STATUS", nullable = false)
    private Long status;

    @ManyToOne
    @JoinColumn(name = "PRG_CODE", referencedColumnName = "PRG_CODE", insertable = false, updatable = false)
    private BgtProgramsEntity bgtProgramsEntity;
    @ManyToOne
    @JoinColumn(name = "SPCPRDTYPE_CODE", referencedColumnName = "SPCPRDTYPE_CODE")
    private SpecialPeriodTypesEntity specialPeriodTypesEntity;

    public SpecialPeriodsEntity() {
    }


    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSpcprdtypeCode(Long spcprdtypeCode) {
        this.spcprdtypeCode = spcprdtypeCode;
    }

    public Long getSpcprdtypeCode() {
        return spcprdtypeCode;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setPrgCode(String prgCode) {
        this.prgCode = prgCode;
    }

    public String getPrgCode() {
        return prgCode;
    }

    public void setPeriodDesc(String periodDesc) {
        this.periodDesc = periodDesc;
    }

    public String getPeriodDesc() {
        return periodDesc;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setBgtProgramsEntity(BgtProgramsEntity bgtProgramsEntity) {
        this.bgtProgramsEntity = bgtProgramsEntity;
    }

    public BgtProgramsEntity getBgtProgramsEntity() {
        return bgtProgramsEntity;
    }

    public void setSpecialPeriodTypesEntity(SpecialPeriodTypesEntity specialPeriodTypesEntity) {
        this.specialPeriodTypesEntity = specialPeriodTypesEntity;
    }

    public SpecialPeriodTypesEntity getSpecialPeriodTypesEntity() {
        return specialPeriodTypesEntity;
    }
}
