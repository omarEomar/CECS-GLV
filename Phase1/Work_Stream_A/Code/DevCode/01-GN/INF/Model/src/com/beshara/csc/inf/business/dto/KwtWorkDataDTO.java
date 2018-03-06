package com.beshara.csc.inf.business.dto;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.ITrxTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.ITrxTypesEntityKey;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntity;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.sql.Date;

import java.util.GregorianCalendar;


public class KwtWorkDataDTO extends InfDTO implements IKwtWorkDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serial;
    private Long allowNomAgain;
    private Long auditStatus;
    private Long tabrecSerial;
    private Date untilDate;
    private Date fromDate;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    private IMinistriesDTO ministriesDTO;
    private IJobsDTO jobsDTO;
    private boolean allowNomAgainBoolean;

    private Long experienceFlag;
    private int serviceDays;
    private int serviceMonths;
    private int serviceYears;

    private Long firstParent;
    private Long leafFlag;
    private Long treeLevel;
    private Long actualExpYears;
    private Long actualExpMonths;
    private Long actualExpDays;
    private Long PerFlag;
    private Long PisFlag;

    private ITrxTypesDTO trxTypesDTO;

    private IJobsDTO otherJobsDTO;

    private IWorkCentersDTO workCentersDTO;

    private String extraMinistry;

    private String extraJob;

    private IKwtWorkDataDTO kwtWorkDataDTO;

    private String catName;
    private String minName;
    private String wrkName;
    private String prgName;
    
    private Long culOfficeCode;
    private String culOfficeName;
    private Long jobHistoryStatus;
    
    public KwtWorkDataDTO() {
    }

    public KwtWorkDataDTO(KwtWorkDataEntity kwtWorkDataEntity) {
        if (kwtWorkDataEntity != null) {

            if (kwtWorkDataEntity.getAllowNomAgain() != null) {
                this.setAllowNomAgain(kwtWorkDataEntity.getAllowNomAgain());
            }
            this.setAuditStatus(kwtWorkDataEntity.getAuditStatus());
            this.setCode(new KwtWorkDataEntityKey(kwtWorkDataEntity.getSerial()));
            this.setCreatedBy(kwtWorkDataEntity.getCreatedBy());
            this.setCreatedDate(kwtWorkDataEntity.getCreatedDate());
            if (kwtWorkDataEntity.getJobCode() != null && !kwtWorkDataEntity.getJobCode().equals("")) {
                IJobsEntityKey entityKey = JobEntityKeyFactory.createJobsEntityKey(kwtWorkDataEntity.getJobCode());
                try {
                    this.setJobsDTO((IJobsDTO)JobClientFactory.getJobsClient().getById(entityKey));
                } catch (Exception e) {
                    this.setJobsDTO(null);
                }
            }
            //        if (kwtWorkDataEntity.getJobsEntity() != null)
            //            this.setJobsDTO(JobDTOFactory.createJobsDTO(kwtWorkDataEntity.getJobsEntity()));
            if (kwtWorkDataEntity.getKwtCitizensResidentsEntity() != null)
                this.setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO(kwtWorkDataEntity.getKwtCitizensResidentsEntity()));
            this.setLastUpdatedBy(kwtWorkDataEntity.getLastUpdatedBy());
            this.setLastUpdatedDate(kwtWorkDataEntity.getLastUpdatedDate());
            //        if (kwtWorkDataEntity.getMinistriesEntity() != null)
            //            this.setMinistriesDTO(OrgDTOFactory.createMinistriesDTO(kwtWorkDataEntity.getMinistriesEntity()));
            if (kwtWorkDataEntity.getMinCode() != null) {
                IMinistriesEntityKey entityKey =
                    OrgEntityKeyFactory.createMinistriesEntityKey(kwtWorkDataEntity.getMinCode());
                try {
                    this.setMinistriesDTO((IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(entityKey));
                } catch (Exception e) {
                    this.setMinistriesDTO(null);
                }
            }
            this.setTabrecSerial(kwtWorkDataEntity.getTabrecSerial());
            this.setUntilDate(kwtWorkDataEntity.getUntilDate());
            this.setFromDate(kwtWorkDataEntity.getFromDate());
            this.setPisFlag(kwtWorkDataEntity.getPisFlag());
            this.setPerFlag(kwtWorkDataEntity.getPerFlag());
            this.setFirstParent(kwtWorkDataEntity.getFirstParent());
            this.setLeafFlag(kwtWorkDataEntity.getLeafFlag());
            this.setTreeLevel(kwtWorkDataEntity.getTreeLevel());
            this.setActualExpDays(kwtWorkDataEntity.getActualExpDays());
            this.setActualExpMonths(kwtWorkDataEntity.getActualExpMonths());
            this.setActualExpYears(kwtWorkDataEntity.getActualExpYears());
            // ......................
            //      if (kwtWorkDataEntity.getTrxTypesEntity() != null)
            //        this.setTrxTypesDTO(EmpDTOFactory.createTrxTypesDTO(kwtWorkDataEntity.getTrxTypesEntity()));
            if (kwtWorkDataEntity.getTrxtypeCode() != null) {
                ITrxTypesEntityKey entityKey =
                    EmpEntityKeyFactory.createTrxTypesEntityKey(kwtWorkDataEntity.getTrxtypeCode());
                try {
                    this.setTrxTypesDTO((ITrxTypesDTO)EmpClientFactory.getTrxTypesClient().getById(entityKey));
                } catch (Exception e) {
                    this.setTrxTypesDTO(null);
                }
            }
            if (kwtWorkDataEntity.getJobCode() != null && !kwtWorkDataEntity.getJobCode().equals("")) {
                IJobsEntityKey entityKey = JobEntityKeyFactory.createJobsEntityKey(kwtWorkDataEntity.getJobCode());
                try {
                    this.setOtherJobsDTO((IJobsDTO)JobClientFactory.getJobsClient().getById(entityKey));
                } catch (Exception e) {
                    this.setOtherJobsDTO(null);
                }
            }
            //        if (kwtWorkDataEntity.getOtherJobsEntity() != null)
            //        this.setOtherJobsDTO(JobDTOFactory.createJobsDTO(kwtWorkDataEntity.getOtherJobsEntity()));
            //        if (kwtWorkDataEntity.getWorkCentersEntity() != null)
            //        this.setWorkCentersDTO(OrgDTOFactory.createWorkCentersDTO(kwtWorkDataEntity.getWorkCentersEntity()));
            if (kwtWorkDataEntity.getWrkMinCode() != null && kwtWorkDataEntity.getWrkCode() != null &&
                !kwtWorkDataEntity.getWrkCode().equals("")) {
                IWorkCentersEntityKey entityKey =
                    OrgEntityKeyFactory.createWorkCentersEntityKey(kwtWorkDataEntity.getWrkMinCode(),
                                                                   kwtWorkDataEntity.getWrkCode());
                try {
                    this.setWorkCentersDTO((IWorkCentersDTO)OrgClientFactory.getWorkCentersClient().getById(entityKey));
                } catch (Exception e) {
                    this.setWorkCentersDTO(null);
                }
            }
            this.setExtraMinistry(kwtWorkDataEntity.getExtraMinistry());
            this.setExtraJob(kwtWorkDataEntity.getExtraJob());
            this.setKwtWorkDataDTO(InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity.getKwtWorkData()));

        }
        if (getFromDate() != null) {
            calcServiceDaysPerRow();
        }
    }

    private void calcServiceDaysPerRow() {
        GregorianCalendar fromCal = new GregorianCalendar();
        fromCal.setTime(getFromDate());
        GregorianCalendar untilCal = new GregorianCalendar();
        if (getUntilDate() == null) {
            untilCal.setTime(SharedUtils.getCurrentDate());
        } else {
            untilCal.setTime(getUntilDate());
        }
        long diffInMillis = untilCal.getTimeInMillis() - fromCal.getTimeInMillis();
        int ONE_DAY = 24 * 60 * 60 * 1000;
        Long totaldays = (diffInMillis / ONE_DAY);
        if (totaldays >= 365) {
            Long years = totaldays / 365;
            totaldays = totaldays % 365;
            setServiceYears(years.intValue());
        }
        if (totaldays >= 30) {
            Long months = totaldays / 30;
            totaldays = totaldays % 30;
            setServiceMonths(months.intValue());
        }
        setServiceDays(totaldays.intValue());
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

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO) {
        this.ministriesDTO = ministriesDTO;
    }

    public IMinistriesDTO getMinistriesDTO() {
        return ministriesDTO;
    }

    public void setJobsDTO(IJobsDTO jobsDTO) {
        this.jobsDTO = jobsDTO;
    }

    public IJobsDTO getJobsDTO() {
        return jobsDTO;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setAllowNomAgainBoolean(boolean allowNomAgainBoolean) {
        if (allowNomAgainBoolean)
            this.allowNomAgain = ICRSConstant.ALLOW_NOM_AGAIN;
        else
            this.allowNomAgain = ICRSConstant.NOT_ALLOW_NOM_AGAIN;
    }

    public boolean isAllowNomAgainBoolean() {
        if (allowNomAgain != null && allowNomAgain.equals(ICRSConstant.ALLOW_NOM_AGAIN))
            return true;
        else
            return false;
    }

    public void setExperienceFlag(Long experienceFlag) {
        this.experienceFlag = experienceFlag;
    }

    public Long getExperienceFlag() {
        return experienceFlag;
    }

    public void setServiceDays(int serviceDays) {
        this.serviceDays = serviceDays;
    }

    public int getServiceDays() {
        return serviceDays;
    }

    public void setServiceMonths(int serviceMonths) {
        this.serviceMonths = serviceMonths;
    }

    public int getServiceMonths() {
        return serviceMonths;
    }

    public void setServiceYears(int serviceYears) {
        this.serviceYears = serviceYears;
    }

    public int getServiceYears() {
        return serviceYears;
    }

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

    public void setAllowNomAgainBoolean1(boolean allowNomAgainBoolean) {
        this.allowNomAgainBoolean = allowNomAgainBoolean;
    }

    public boolean isAllowNomAgainBoolean1() {
        return allowNomAgainBoolean;
    }

    public void setTrxTypesDTO(ITrxTypesDTO trxTypesDTO) {
        this.trxTypesDTO = trxTypesDTO;
    }

    public ITrxTypesDTO getTrxTypesDTO() {
        return trxTypesDTO;
    }

    public void setOtherJobsDTO(IJobsDTO otherJobsDTO) {
        this.otherJobsDTO = otherJobsDTO;
    }

    public IJobsDTO getOtherJobsDTO() {
        return otherJobsDTO;
    }

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO) {
        this.workCentersDTO = workCentersDTO;
    }

    public IWorkCentersDTO getWorkCentersDTO() {
        return workCentersDTO;
    }

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

    public void setKwtWorkDataDTO(IKwtWorkDataDTO kwtWorkDataDTO) {
        this.kwtWorkDataDTO = kwtWorkDataDTO;
    }

    public IKwtWorkDataDTO getKwtWorkDataDTO() {
        return kwtWorkDataDTO;
    }


    public void setCatName(String catName) {
        this.catName = catName;
}

    public String getCatName() {
        return catName;
    }

    public void setMinName(String minName) {
        this.minName = minName;
    }

    public String getMinName() {
        return minName;
    }

    public void setWrkName(String wrkName) {
        this.wrkName = wrkName;
    }

    public String getWrkName() {
        return wrkName;
    }

    public void setPrgName(String prgName) {
        this.prgName = prgName;
    }

    public String getPrgName() {
        return prgName;
    }

    public void setCulOfficeCode(Long culOfficeCode) {
        this.culOfficeCode = culOfficeCode;
    }

    public Long getCulOfficeCode() {
        return culOfficeCode;
    }

    public void setCulOfficeName(String culOfficeName) {
        this.culOfficeName = culOfficeName;
    }

    public String getCulOfficeName() {
        return culOfficeName;
    }

    public void setJobHistoryStatus(Long jobHisoryStatus) {
        this.jobHistoryStatus = jobHisoryStatus;
}

    public Long getJobHistoryStatus() {
        return jobHistoryStatus;
    }
}
