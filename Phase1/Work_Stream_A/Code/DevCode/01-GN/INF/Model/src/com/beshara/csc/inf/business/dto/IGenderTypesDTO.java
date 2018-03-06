package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.base.dto.*;

public interface IGenderTypesDTO extends IInfDTO {
    public Long getGentypeCode();

    public String getGentypeName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setGentypeCode(Long gentypeCode);

    public void setGentypeName(String gentypeName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
