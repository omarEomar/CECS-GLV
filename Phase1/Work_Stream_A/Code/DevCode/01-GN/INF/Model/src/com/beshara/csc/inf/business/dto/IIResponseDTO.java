package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;

import java.util.List;

import com.beshara.base.dto.*;

public interface IIResponseDTO extends IInfDTO {
    public void setCount(Long count);

    public Long getCount();

    public void setRequestDTO(IRequestDTO requestDTO);

    public IRequestDTO getRequestDTO();

    public void setBasicDTOList(List<IBasicDTO> basicDTOList);

    public List<IBasicDTO> getBasicDTOList();

}
