package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.ClientDTO;

import java.util.List;

import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.*;

public interface IRequestDTO extends IClientDTO {
    public void setFirstRowNumber(Long firstRowNumber);

    public Long getFirstRowNumber();

    public void setSortColumnList(List<String> sortColumnList);

    public List<String> getSortColumnList();

    public void setMaxNoOfRecords(Long maxNoOfRecords);

    public Long getMaxNoOfRecords();

    public void setPageNum(Long pageNum);

    public Long getPageNum();

    public void setSortAscending(boolean sortAscending);

    public boolean isSortAscending();

    public void setCountRequired(boolean countRequired);

    public boolean isCountRequired();

}
