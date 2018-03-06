package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.HolidayTypesEntity;
import com.beshara.csc.inf.business.entity.HolidayTypesEntityKey;
import com.beshara.csc.inf.business.entity.HolidaysEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import com.beshara.base.dto.*;

public interface IHolidayTypesDTO extends IInfDTO {
    public Long getHoltypeDays();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setHoltypeDays(Long holtypeDays);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setHolidaysDTOList(List<IHolidaysDTO> holidaysDTOList);

    public List<IHolidaysDTO> getHolidaysDTOList();

}
