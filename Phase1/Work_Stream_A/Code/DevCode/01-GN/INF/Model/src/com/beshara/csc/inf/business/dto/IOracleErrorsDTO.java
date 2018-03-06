package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.OracleErrorsEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IOracleErrorsDTO extends IInfDTO {
    public Long getErrNum();

    public String getErrEMsg();

    public String getErrAMsg();

    public Long getAuditStatus();

    public Long getTabrecSerial();
    
    public Long getErrCode();

    public void setErrNum(Long errNum);
    

    public void setErrEMsg(String errEMsg);

    public void setErrAMsg(String errAMsg);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);
    
    public void setErrCode(Long errCode);

}
