package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.*;

import java.util.List;

public interface IResponseDTO {
    public void setCount(Long count);

    public Long getCount();

    public void setRequestDTO(IRequestDTO requestDTO);

    public IRequestDTO getRequestDTO();

    public void setBasicDTOList(List<IBasicDTO> basicDTOList);

    public List<IBasicDTO> getBasicDTOList();
}
