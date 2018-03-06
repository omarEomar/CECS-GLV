package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.base.dto.*;
import java.util.List;
public class ResponseDTO extends ClientDTO implements IResponseDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long count;
    private IRequestDTO requestDTO;
    private List<IBasicDTO> basicDTOList;
    public ResponseDTO() {    }    public void setCount(Long count) {        this.count = count;
    }    public Long getCount() {        return count;
    }    public void setRequestDTO(IRequestDTO requestDTO) {        this.requestDTO = requestDTO;
    }    public IRequestDTO getRequestDTO() {        return requestDTO;
    }    public void setBasicDTOList(List<IBasicDTO> basicDTOList) {        this.basicDTOList = basicDTOList;
    }    public List<IBasicDTO> getBasicDTOList() {        return basicDTOList;
    }}
