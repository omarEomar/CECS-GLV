package com.beshara.csc.inf.business.dto;

import java.sql.Date;

public interface IKwtBasicWrkDataDTO extends IInfDTO {
    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();

    public void setTrxTypeCode(Long trxTypeCode);

    public Long getTrxTypeCode();

    public void setApplyDate(Date applyDate);

    public Date getApplyDate();

    public void setJobCode(String jobCode);

    public String getJobCode();

    public void setOtherJobCode(String otherJobCode);

    public String getOtherJobCode();

    public void setWrkCode(String wrkCode);

    public String getWrkCode();

    public void setChangeJobCode(boolean changeJobCode) ;
    
    public boolean isChangeJobCode();
    
    public void setChangeOtherJobCode(boolean changeOtherJobCode);
    
    public boolean isChangeOtherJobCode();
    
    public void setChangeWrkCode(boolean changeWrkCode);
    
    public boolean isChangeWrkCode();
    
    public void setChangeType(Long changeType) ;
    
    public Long getChangeType();
    
    public void setMinCode(Long minCode);
    
    public Long getMinCode();
    
    public void setChangeJobCodeAndOtherJobCode(boolean changeJobCodeAndOtherJobCode);
    
    public boolean isChangeJobCodeAndOtherJobCode();
}
