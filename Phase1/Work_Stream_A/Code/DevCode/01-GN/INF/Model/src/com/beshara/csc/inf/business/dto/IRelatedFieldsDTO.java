package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.dto.FieldsDTO;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntity;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntityKey;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IRelatedFieldsDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setReferOrder(Long referOrder);

    public Long getReferOrder();

    public void setFieldsDTO(IFieldsDTO fieldsDTO);

    public IFieldsDTO getFieldsDTO();

    public void setFieldsReferencedDTO(IFieldsDTO fieldsReferencedDTO);

    public IFieldsDTO getFieldsReferencedDTO();

}
