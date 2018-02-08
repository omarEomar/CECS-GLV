package com.beshara.jsfbase.csc.backingbean.paging;

import java.io.Serializable;

import java.util.List;

public class PagingResponseDTO implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private List resultList;
    private int totalListSize;
    
    public PagingResponseDTO() {
    }
    
    public PagingResponseDTO(List resultList) {
        this.resultList = resultList;
        this.totalListSize = resultList.size();
    }
    
    public PagingResponseDTO(List resultList, int totalListSize) {
        this.resultList = resultList;
        this.totalListSize = totalListSize;
    }
    
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public List getResultList() {
        return resultList;
    }

    public void setTotalListSize(int totalListSize) {
        this.totalListSize = totalListSize;
    }

    public int getTotalListSize() {
        return totalListSize;
    }
}
