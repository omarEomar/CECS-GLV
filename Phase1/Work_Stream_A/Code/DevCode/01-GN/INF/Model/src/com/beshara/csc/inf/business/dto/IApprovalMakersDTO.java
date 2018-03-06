package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.ApprovalMakersEntity;
import com.beshara.csc.inf.business.entity.ApprovalMakersEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IApprovalMakersDTO extends IInfDTO {
    void setAprmakerCode(Long aprmakerCode);
    Long getAprmakerCode();
    void setAprmakerName(String aprmakerName);
    String getAprmakerName();
    void setDynamicFlag(String dynamicFlag);
    String getDynamicFlag();
    void setFunctionName(String functionName);
}
