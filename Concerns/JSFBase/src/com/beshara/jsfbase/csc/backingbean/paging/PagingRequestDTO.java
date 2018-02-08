package com.beshara.jsfbase.csc.backingbean.paging;

import com.beshara.base.dto.IBasicDTO;

import java.io.Serializable;


public class PagingRequestDTO implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private IBasicDTO searchDTO;
    private String beanName;
    private String methodName;
    private String repositionKey; // for getPageIndex() in Add and Edit.
    private Object[] params;

    private String bsnSortingColumnName;
    private boolean sortAscending;

    public PagingRequestDTO() {
    }

    public PagingRequestDTO(String methodName) {
        this.methodName = methodName;
    }

    public PagingRequestDTO(String beanName, String methodName) {
        this.methodName = methodName;
        this.beanName = beanName;
    }

    public PagingRequestDTO(String beanName, String methodName, 
                            IBasicDTO searchDTO) {
        this.methodName = methodName;
        this.beanName = beanName;
        this.searchDTO = searchDTO;
    }

    public void setSearchDTO(IBasicDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IBasicDTO getSearchDTO() {
        return searchDTO;
    }

    public void setBeanName(String searchBeanName) {
        this.beanName = searchBeanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setMethodName(String searchMethodName) {
        this.methodName = searchMethodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setRepositionKey(String repositionKey) {
        this.repositionKey = repositionKey;
    }

    public String getRepositionKey() {
        return repositionKey;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public void setSortAscending(boolean sortDescending) {
        this.sortAscending = sortDescending;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setBsnSortingColumnName(String bsnSortingColumnName) {
        this.bsnSortingColumnName = bsnSortingColumnName;
    }

    public String getBsnSortingColumnName() {
        return bsnSortingColumnName;
    }
}
