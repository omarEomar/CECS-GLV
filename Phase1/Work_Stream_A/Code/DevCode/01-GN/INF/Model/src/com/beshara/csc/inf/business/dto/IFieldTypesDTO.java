package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.ClientDTO;
import com.beshara.csc.inf.business.entity.FieldTypesEntity;
import com.beshara.csc.inf.business.entity.FieldTypesEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.*;

public interface IFieldTypesDTO extends IInfDTO {
    public void setFieldsDTOList(List<IFieldsDTO> fieldsDTOList);

    public List<IFieldsDTO> getFieldsDTOList();

}
