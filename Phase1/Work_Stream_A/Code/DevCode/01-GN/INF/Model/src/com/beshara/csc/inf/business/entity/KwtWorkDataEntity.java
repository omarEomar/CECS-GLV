package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.entity.org.InfMinistriesEntity;

import java.io.Serializable;

import java.sql.Date;
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


@Entity
@NamedQueries( { @NamedQuery(name = "KwtWorkDataEntity.findAll", query = "select o from KwtWorkDataEntity o"),
                 @NamedQuery(name = "KwtWorkDataEntity.findNewId",
                             query = "select MAX(o.serial) from KwtWorkDataEntity o"),
                 @NamedQuery(name = "KwtWorkDataEntity.findAllResignationByCivilId",
                             query = "select o from KwtWorkDataEntity o WHERE o.civilId=:civilId AND o.untilDate IS NOT NULL AND o.ministriesEntity.catsEntity.govFlag=:govFlag"),
                 @NamedQuery(name = "KwtWorkDataEntity.findAllResignationByCivilIdForCRS",
            // query = "select o from KwtWorkDataEntity o WHERE o.civilId=:civilId AND o.trxTypesEntity.trxtypeCode=:endServiceConst AND o.untilDate IS NOT NULL AND o.ministriesEntity.catsEntity.govFlag=:govFlag  ORDER BY o.ministriesEntity.minCode , o.untilDate DESC "),
            query =
                "select o from KwtWorkDataEntity o WHERE o.civilId = :civilId AND o.trxtypeCode = :endServiceConst AND o.untilDate IS NOT NULL AND o.untilDate = (select Max(k.untilDate) from KwtWorkDataEntity k where o.ministriesEntity.minCode = k.ministriesEntity.minCode AND k.civilId=:civilId AND K.trxtypeCode=:endServiceConst) ORDER BY o.untilDate DESC"),
        @NamedQuery(name = "KwtWorkDataEntity.getByCivilId",
                    query = "select o from KwtWorkDataEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId",
                    hints = { @QueryHint(name = "toplink.refresh", value = "true") }),
        @NamedQuery(name = "KwtWorkDataEntity.checkResignedMinsAllowNomination",
                    query = "select count(o.civilId) from KwtWorkDataEntity o WHERE o.civilId = :civilId AND o.untilDate IS NOT NULL AND o.ministriesEntity.minCode = :minsCode and o.allowNomAgain = :notAllowNomAgain"),
        @NamedQuery(name = "KwtWorkDataEntity.countNumOfExperiences",
                    query = "select count(o.civilId) from KwtWorkDataEntity o WHERE o.kwtCitizensResidentsEntity.civilId = :civilId"),
        @NamedQuery(name = "KwtWorkDataEntity.getLastByCivilAndMinistry",
                    query = "select o from KwtWorkDataEntity o WHERE o.civilId = :civilId and o.ministriesEntity.minCode=:minCode order by o.fromDate desc"),
        @NamedQuery(name = "KwtWorkDataEntity.getByCivilIdOrderByDate",
                    query = "select o from KwtWorkDataEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId  and o.trxtypeCode NOT IN (80,90,280,290) order by o.fromDate asc "),
        @NamedQuery(name = "KwtWorkDataEntity.getWorkCenterGroupping",
                    query = "select o from KwtWorkDataEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId and o.trxtypeCode NOT IN (80,90,280,290) and o.minCode =:minCode and o.jobCode =:jobCode  and o.fromDate >= :fromDate and  (o.untilDate <= :untilDate or :untilDate is null) order by o.fromDate "),
        @NamedQuery(name = "KwtWorkDataEntity.getWorkCenterGrouppingCount",
                    query = "select count(o.serial) from KwtWorkDataEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId and o.trxtypeCode NOT IN (80,90,280,290) and o.minCode =:minCode and o.jobCode =:jobCode and o.wrkCode is not null  and o.fromDate >= :fromDate and  (o.untilDate <= :untilDate or :untilDate is null) order by o.fromDate "),
        @NamedQuery(name = "KwtWorkDataEntity.getJobGroupping",
                    query = "select o from KwtWorkDataEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId and o.trxtypeCode NOT IN (80,90,280,290) and o.minCode =:minCode and o.fromDate >= :fromDate and  (o.untilDate <= :untilDate or :untilDate is null)  order by o.fromDate "),
        @NamedQuery(name = "KwtWorkDataEntity.findKwtWorkDataForMov",
                    query = "select o from KwtWorkDataEntity o WHERE o.civilId = :realCivilId AND o.trxtypeCode = :ndbType AND o.fromDate IS NOT NULL AND o.fromDate =:movingDate"),
        @NamedQuery(name = "KwtWorkDataEntity.findKwtWorkDataByMovingDate",
                    query = "select o from KwtWorkDataEntity o WHERE o.civilId = :realCivilId AND  o.fromDate IS NOT NULL AND o.fromDate =:movingDate"),
        @NamedQuery(name = "KwtWorkDataEntity.findKwtWorkDataForInternalMovAfterExc",
                    query = "select o from KwtWorkDataEntity o WHERE o.civilId = :realCivilId AND  o.untilDate IS NOT NULL AND o.untilDate =:movingDate") })
@Table(name = "INF_KWT_WORK_DATA")
@IdClass(IKwtWorkDataEntityKey.class)
public class KwtWorkDataEntity extends BasicEntity implements Serializable {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "ALLOW_NOM_AGAIN", nullable = false)
    private Long allowNomAgain;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, updatable = false)
    private Long civilId;
    @Column(name = "JOB_CODE", nullable = false)
    private String jobCode;
    @Column(name = "JOB_CODE_OTHER")
    private String jobCodeOther;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Column(name = "UNTIL_DATE")
    private Date untilDate;


    //this column was removed from DB
    //    @Column(name = "EXPERIENCE_FLAG")
    //    private Long experienceFlag;

    //new Coulmns
    @Column(name = "FIRST_PARENT", nullable = false)
    private Long firstParent;
    @Column(name = "LEAF_FLAG", nullable = false)
    private Long leafFlag;
    @Column(name = "TREE_LEVEL", nullable = false)
    private Long treeLevel;
    @Column(name = "ACTUAL_EXP_YEARS", nullable = false)
    private Long actualExpYears;
    @Column(name = "ACTUAL_EXP_MONTHS", nullable = false)
    private Long actualExpMonths;
    @Column(name = "ACTUAL_EXP_DAYS", nullable = false)
    private Long actualExpDays;
    @Column(name = "PER_FLAG", nullable = false)
    private Long PerFlag;
    @Column(name = "PIS_FLAG", nullable = false)
    private Long PisFlag;
    @Column(name = "MIN_CODE")
    private Long minCode;
    @Column(name = "WRKMIN_CODE")
    private Long WrkMinCode;
    @Column(name = "WRK_CODE")
    private String wrkCode;
    @Column(name = "TRXTYPE_CODE", nullable = false)
    private Long trxtypeCode;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;
    @ManyToOne
    @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE", insertable = false, updatable = false)
    private InfMinistriesEntity ministriesEntity;
    //    @ManyToOne
    //    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE")
    //    private JobsEntity jobsEntity;

    // new Attributes mapped to Entity thre is only one column
    // remains not mapped to entity is user_code
    //M.abdelsabour

    //    @ManyToOne
    //    @JoinColumn(name = "TRXTYPE_CODE", referencedColumnName = "TRXTYPE_CODE")
    //    private TrxTypesEntity trxTypesEntity;

    //    @ManyToOne
    //    @JoinColumn(name = "JOB_CODE_OTHER", referencedColumnName = "JOB_CODE")
    //    private JobsEntity otherJobsEntity;


    //    @ManyToOne
    //    @JoinColumns( { @JoinColumn(name = "WRKMIN_CODE", referencedColumnName = "MIN_CODE"),
    //                    @JoinColumn(name = "WRK_CODE", referencedColumnName = "WRK_CODE") })
    //    private WorkCentersEntity workCentersEntity;


    @Column(name = "EXTRA_MINISTRY")
    private String extraMinistry;


    @Column(name = "EXTRA_JOB")
    private String extraJob;


    @ManyToOne
    @JoinColumn(name = "PARENT_SERIAL", referencedColumnName = "SERIAL")
    private KwtWorkDataEntity kwtWorkData;

    @Column(name = "CULOFFICE_CODE", nullable = true)
    private Long culOfficeCode;
    
    //  @ManyToOne
    //  @JoinColumn(name = "USER_CODE", referencedColumnName = "USER_CODE")
    //  private UsersDTO user ;
    
    @Column(name = "JOB_HIST_STATUS", nullable = false)
    private Long jobHistoryStatus;

    public KwtWorkDataEntity() {
    }

    public void setAllowNomAgain(Long allowNomAgain) {
        this.allowNomAgain = allowNomAgain;
    }

    public Long getAllowNomAgain() {
        return allowNomAgain;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setKwtCitizensResidentsEntity(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        this.kwtCitizensResidentsEntity = kwtCitizensResidentsEntity;
    }

    public KwtCitizensResidentsEntity getKwtCitizensResidentsEntity() {
        return kwtCitizensResidentsEntity;
    }

    public void setMinistriesEntity(InfMinistriesEntity ministriesEntity) {
        this.ministriesEntity = ministriesEntity;
    }

    public InfMinistriesEntity getMinistriesEntity() {
        return ministriesEntity;
    }

    //    public void setJobsEntity(JobsEntity jobsEntity) {
    //        this.jobsEntity = jobsEntity;
    //    }
    //
    //    public JobsEntity getJobsEntity() {
    //        return jobsEntity;
    //    }

    //    public void setExperienceFlag(Long experienceFlag) {
    //        this.experienceFlag = experienceFlag;
    //    }
    //
    //    public Long getExperienceFlag() {
    //        return experienceFlag;
    //    }

    public void setFirstParent(Long firstParent) {
        this.firstParent = firstParent;
    }

    public Long getFirstParent() {
        return firstParent;
    }

    public void setLeafFlag(Long leafFlag) {
        this.leafFlag = leafFlag;
    }

    public Long getLeafFlag() {
        return leafFlag;
    }

    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setActualExpYears(Long actualExpYears) {
        this.actualExpYears = actualExpYears;
    }

    public Long getActualExpYears() {
        return actualExpYears;
    }

    public void setActualExpMonths(Long actualExpMonths) {
        this.actualExpMonths = actualExpMonths;
    }

    public Long getActualExpMonths() {
        return actualExpMonths;
    }

    public void setActualExpDays(Long actualExpDays) {
        this.actualExpDays = actualExpDays;
    }

    public Long getActualExpDays() {
        return actualExpDays;
    }

    public void setPerFlag(Long PerFlag) {
        this.PerFlag = PerFlag;
    }

    public Long getPerFlag() {
        return PerFlag;
    }

    public void setPisFlag(Long PisFlag) {
        this.PisFlag = PisFlag;
    }

    public Long getPisFlag() {
        return PisFlag;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getSerial() {
        return serial;
    }

    //    public void setTrxTypesEntity(TrxTypesEntity trxTypesEntity) {
    //        this.trxTypesEntity = trxTypesEntity;
    //    }
    //
    //    public TrxTypesEntity getTrxTypesEntity() {
    //        return trxTypesEntity;
    //    }

    //    public void setOtherJobsEntity(JobsEntity otherJobsEntity) {
    //        this.otherJobsEntity = otherJobsEntity;
    //    }
    //
    //    public JobsEntity getOtherJobsEntity() {
    //        return otherJobsEntity;
    //    }

    //    public void setWorkCentersEntity(WorkCentersEntity workCentersEntity) {
    //        this.workCentersEntity = workCentersEntity;
    //    }
    //
    //    public WorkCentersEntity getWorkCentersEntity() {
    //        return workCentersEntity;
    //    }

    public void setExtraMinistry(String extraMinistry) {
        this.extraMinistry = extraMinistry;
    }

    public String getExtraMinistry() {
        return extraMinistry;
    }

    public void setExtraJob(String extraJob) {
        this.extraJob = extraJob;
    }

    public String getExtraJob() {
        return extraJob;
    }

    public void setKwtWorkData(KwtWorkDataEntity kwtWorkData) {
        this.kwtWorkData = kwtWorkData;
    }

    public KwtWorkDataEntity getKwtWorkData() {
        return kwtWorkData;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    public String getWrkCode() {
        return wrkCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setTrxtypeCode(Long trxtypeCode) {
        this.trxtypeCode = trxtypeCode;
    }

    public Long getTrxtypeCode() {
        return trxtypeCode;
    }

    public void setWrkMinCode(Long WrkMinCode) {
        this.WrkMinCode = WrkMinCode;
    }

    public Long getWrkMinCode() {
        return WrkMinCode;
    }

    public void setJobCodeOther(String jobCodeOther) {
        this.jobCodeOther = jobCodeOther;
    }

    public String getJobCodeOther() {
        return jobCodeOther;
    }

    public void setCulOfficeCode(Long culOfficeCode) {
        this.culOfficeCode = culOfficeCode;
    }

    public Long getCulOfficeCode() {
        return culOfficeCode;
    }

    public void setJobHistoryStatus(Long jobHisoryStatus) {
        this.jobHistoryStatus = jobHisoryStatus;
    }

    public Long getJobHistoryStatus() {
        return jobHistoryStatus;
    }
}
