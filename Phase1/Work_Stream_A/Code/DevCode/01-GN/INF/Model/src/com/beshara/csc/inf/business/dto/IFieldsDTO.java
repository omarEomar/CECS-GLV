package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.req.business.dto.IRequestDataDTO;
import com.beshara.csc.hr.mis.business.dto.IMisOperationDetailsDTO;

import java.util.List;


public interface IFieldsDTO extends IInfDTO {

    public void setFldDesc(String fldDesc);

    public String getFldDesc();

    public void setSqlStatement(String sqlStatement);

    public String getSqlStatement();

    public void setFldtypeCode(Long fldtypeCode);

    public Long getFldtypeCode();

    public void setFieldTypesDTO(IFieldTypesDTO fieldTypesDTO);

    public IFieldTypesDTO getFieldTypesDTO();

    public void setRequestDataDTOList(List<IRequestDataDTO> requestDataDTOList);

    public List<IRequestDataDTO> getRequestDataDTOList();

    public void setDisplayedType(Long displayedType);

    public Long getDisplayedType();

    public void setRelatedFieldsDTOList(List<IRelatedFieldsDTO> relatedFieldsDTOList);

    public List<IRelatedFieldsDTO> getRelatedFieldsDTOList();

    public void setHasRelatedFields(boolean hasRelatedFields);

    public boolean isHasRelatedFields();

    public void setFieldValueDTOList(List<IFieldValueDTO> fieldValueDTOList);

    public List<IFieldValueDTO> getFieldValueDTOList();

    public void setMisOperationDetailsDTOList(List<IMisOperationDetailsDTO> misOperationDetailsDTOList);

    public List<IMisOperationDetailsDTO> getMisOperationDetailsDTOList();
}
