package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.NewspapersEntity;
import com.beshara.csc.inf.business.entity.NewspapersEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface INewspapersDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setPaperLocation(String paperLocation);

    public String getPaperLocation();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();
    String getPaperName ();
    void setPaperId ( Long paperId );
    Long getPaperId ( ) ;
    void setPaperName ( String paperName );

}
