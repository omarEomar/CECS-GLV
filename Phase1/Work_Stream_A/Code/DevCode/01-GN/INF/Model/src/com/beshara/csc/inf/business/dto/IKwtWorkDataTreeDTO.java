package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.hr.emp.business.dto.ITrxTypesDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;

import java.sql.Date;

public interface IKwtWorkDataTreeDTO extends ITreeDTO {
    public void setAllowNomAgain(Long allowNomAgain);

    public Long getAllowNomAgain();

    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO);

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO();

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO);

    public IMinistriesDTO getMinistriesDTO();

    public void setJobsDTO(IJobsDTO jobsDTO);

    public IJobsDTO getJobsDTO();

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setAllowNomAgainBoolean(boolean allowNomAgainBoolean);

    public boolean isAllowNomAgainBoolean();

    //this column was removed from DB
    //    public void setExperienceFlag(Long experienceFlag);

    public Long getExperienceFlag();

    public void setServiceDays(int serviceDays);

    public int getServiceDays();

    public void setServiceMonths(int serviceMonths);

    public int getServiceMonths();

    public void setServiceYears(int serviceYears);

    public int getServiceYears(); 

    public void setTreeLevel(Long treeLevel);

    public Long getTreeLevel();

    public void setActualExpYears(Long actualExpYears);

    public Long getActualExpYears();

    public void setActualExpMonths(Long actualExpMonths);

    public Long getActualExpMonths();

    public void setActualExpDays(Long actualExpDays);

    public Long getActualExpDays();

    public void setPerFlag(Long PerFlag);

    public Long getPerFlag();

    public void setPisFlag(Long PisFlag);

    public Long getPisFlag();

    public void setSerial(Long serial);

    public Long getSerial();

    public void setTrxTypesDTO(ITrxTypesDTO trxTypesDTO);

    public ITrxTypesDTO getTrxTypesDTO();

    public void setOtherJobsDTO(IJobsDTO otherJobsDTO);

    public IJobsDTO getOtherJobsDTO();

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO);

    public IWorkCentersDTO getWorkCentersDTO();

    public void setExtraMinistry(String extraMinistry);

    public String getExtraMinistry();

    public void setExtraJob(String extraJob);

    public String getExtraJob();

    public void setKwtWorkDataDTO(IKwtWorkDataDTO kwtWorkDataDTO);

    public IKwtWorkDataDTO getKwtWorkDataDTO();

    public void setJobHistoryStatus(Long jobHisoryStatus) ;

    public Long getJobHistoryStatus() ;
}
