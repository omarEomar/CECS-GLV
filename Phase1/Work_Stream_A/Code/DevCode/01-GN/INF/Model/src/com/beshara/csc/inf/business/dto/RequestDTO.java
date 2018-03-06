package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.ClientDTO;

import java.util.List;

public class RequestDTO extends ClientDTO implements IRequestDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long firstRowNumber;
    private Long maxNoOfRecords;
    private Long pageNum;
    private List<String> sortColumnList;
    private boolean sortAscending;
    private boolean countRequired;

    public RequestDTO() {
    }

    public RequestDTO(Long firstRowNumber, List<String> sortColumnList) {
        this.firstRowNumber = firstRowNumber;
        this.sortColumnList = sortColumnList;
    }

    public void setFirstRowNumber(Long firstRowNumber) {
        this.firstRowNumber = firstRowNumber;
    }

    public Long getFirstRowNumber() {
        return firstRowNumber;
    }

    public void setSortColumnList(List<String> sortColumnList) {
        this.sortColumnList = sortColumnList;
    }

    public List<String> getSortColumnList() {
        return sortColumnList;
    }

    public void setMaxNoOfRecords(Long maxNoOfRecords) {
        this.maxNoOfRecords = maxNoOfRecords;
    }

    public Long getMaxNoOfRecords() {
        return maxNoOfRecords;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setCountRequired(boolean countRequired) {
        this.countRequired = countRequired;
    }

    public boolean isCountRequired() {
        return countRequired;
    }
}
