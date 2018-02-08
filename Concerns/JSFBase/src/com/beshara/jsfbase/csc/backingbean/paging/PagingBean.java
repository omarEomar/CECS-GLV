package com.beshara.jsfbase.csc.backingbean.paging;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.model.DataModel;


public class PagingBean implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private BaseBean baseBean;

    private transient DataModel myPagedDataModel;

    private int totalListSize;

    private boolean preUpdatedDataModel;

    public PagingBean(BaseBean baseBean) {

        this.baseBean = baseBean;

    }

    // ************************ Setters & Getters ******************************

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }

    public void setTotalListSize(int totalListSize) {
        this.totalListSize = totalListSize;
    }

    public int getTotalListSize() {
        return totalListSize;
    }

    public void setPreUpdatedDataModel(boolean preSearched) {
        this.preUpdatedDataModel = preSearched;
    }

    public boolean isPreUpdatedDataModel() {
        return preUpdatedDataModel;
    }

    public void setMyPagedDataModel(DataModel myPagedDataModel) {
        this.myPagedDataModel = myPagedDataModel;
    }

    // ************************* Basic Methods *********************************

    public DataModel getMyPagedDataModel() throws DataBaseException, 
                                                  NoResultException {

//        System.out.println("getMyPagedDataModel...");

        if (baseBean.isUsingPaging() && 
            (baseBean.isUpdateMyPagedDataModel() || myPagedDataModel == 
             null)) {

            if (baseBean.isRepositionTable()) { // In Add an Edit methods.
                setPreUpdatedDataModel(true); // to not clear highlighted DTOs

                if (baseBean.isSaveSortingState()) {
                    baseBean.setSorting(false);
                } else {
                    if (!baseBean.isUsingBsnPaging()) //Nora
                    {
                        resetSorting();
                    }
                }
            }

            PagingRequestDTO pagingRequestDTO = baseBean.getPagingRequestDTO();
            if (pagingRequestDTO == null) {
                baseBean.setPagingRequestDTO(new PagingRequestDTO(null, 
                                                                  "getAllWithPaging"));

            } else if (pagingRequestDTO.getMethodName() == null || 
                       pagingRequestDTO.getMethodName().equals("")) {
                pagingRequestDTO.setMethodName("getAllWithPaging");
            }

            if (baseBean.isSorting() && !baseBean.isUsingBsnPaging()) {
                if (!baseBean.isSortedTable()) {
                    baseBean.sort(baseBean.getFullColumnName(), 
                                  baseBean.isSortAscending());
                }
            } else {
                PagingResponseDTO responseDTO = getPagingResponse();
                List resultList = responseDTO.getResultList();
                if (responseDTO == null || 
                    responseDTO.getResultList() == null) {
                    setTotalListSize(0);
                    if (baseBean.isUsingBsnPaging()) {
                        updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 
                                                                      0));
                    } else {
                        updateMyPadgedDataModel(new ArrayList());
                    }
                } else {
                    if (baseBean.isSaveSortingState() && 
                        !baseBean.isSortedTable()) {
                        //baseBean.setSorting(true);
                        resultList = 
                                baseBean.sort(resultList, baseBean.getFullColumnName(), 
                                              baseBean.isSortAscending(), 
                                              false);
                    }

                    if (baseBean.isRepositionTable()) { // In Add an Edit methods.
                        if (baseBean.isUsingBsnPaging()) {
                            setTotalListSize(responseDTO.getTotalListSize());
                        } else {
                            setTotalListSize(resultList.size());
                        }
                        baseBean.getPageIndex(baseBean.getPagingRequestDTO().getRepositionKey(), 
                                              resultList);
                        baseBean.setRepositionTable(false);
                    }

                    if (baseBean.isUsingBsnPaging()) {
                        updateMyPadgedDataModel(responseDTO);
                    } else {
                        updateMyPadgedDataModel(resultList);
                    }
                }
            }
            baseBean.setUpdateMyPagedDataModel(false);
        }

        return myPagedDataModel;
    }

    public void restoreTablePosition() {

        int pageSize = getSharedUtil().getNoOfTableRows();

        if (totalListSize == 1) {

            // In case the data table has no data after delete, so it has 1 item before delete.

            totalListSize = 0;
            setMyPagedDataModel(new PagedListDataModel(new ArrayList(), 
                                                       totalListSize, 
                                                       pageSize));
            baseBean.getMyDataTable().setRows(0);

        } else {

            baseBean.setCurrentPageIndex(1);
            baseBean.setOldPageIndex(0);

            try {

                getMyPagedDataModel();

            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (NoResultException e) {
                e.printStackTrace();
            }

            baseBean.getMyDataTable().setFirst(0);

            // TODO Will be postponed, it reposition page after delete in the right position
            //
            //            int pageIndex = baseBean.getCurrentPageIndex();
            //    
            //            if ((pageIndex == getPagesCount()) && 
            //                (--totalListSize % pageSize == 0)) {
            //    
            //                baseBean.setCurrentPageIndex(--pageIndex);
            //            }
            //    
            //            try {
            //    
            //                getMyPagedDataModel();
            //                int startIndex = (pageIndex - 1) * pageSize;
            //                baseBean.getMyDataTable().setFirst(startIndex);
            //    
            //            } catch (DataBaseException e) {
            //                e.printStackTrace();
            //            } catch (NoResultException e) {
            //                 e.printStackTrace();
            //            }

        }

    }

    public void clearDTOS() {
        Boolean manyToMany = 
            (Boolean)baseBean.evaluateValueBinding("appMainLayout.manyToMany");
        if (manyToMany != null && manyToMany && 
            (baseBean instanceof ManyToManyDetailsMaintain) && 
            ((ManyToManyDetailsMaintain)baseBean).getSelectedCurrentDetails() != 
            null) {
            ((ManyToManyDetailsMaintain)baseBean).getSelectedCurrentDetails().clear();
        } else if (baseBean.getSelectedDTOS() != null) {
            baseBean.getSelectedDTOS().clear();
        }

        if (baseBean.getHighlightedDTOS() != null) {
            baseBean.getHighlightedDTOS().clear();
        }

    }

    // To clear SelectedDTOS and HighlightedDTOS before search.

    public void preUpdateDataModel() {

        baseBean.setUpdateMyPagedDataModel(true);

        if (!preUpdatedDataModel) {

            clearDTOS();
            preUpdatedDataModel = true;

        }

    }

    public void cancelSearch() {
        preUpdatedDataModel = false;
        baseBean.setUpdateMyPagedDataModel(true);
        baseBean.setPagingRequestDTO(null);

    }

    public void updateMyPadgedDataModel(List totalList) {

        setTotalListSize(totalList.size());

        int pageSize = getSharedUtil().getNoOfTableRows();

        int startIndex = (baseBean.getCurrentPageIndex() - 1) * pageSize;

        List pagedList = new ArrayList();
        if (startIndex + pageSize <= totalListSize) {
            pagedList = 
                    getSubList(totalList, startIndex, startIndex + pageSize);
        } else {
            pagedList = getSubList(totalList, startIndex, totalListSize);
        }

        setMyPagedDataModel(new PagedListDataModel(pagedList, totalListSize, 
                                                   pageSize));

        // check if any item in the pagedList exists in selectedDTOs
        Boolean manyToMany = 
            (Boolean)baseBean.evaluateValueBinding("appMainLayout.manyToMany");
        if (pagedList.size() > 0) {
            if ((manyToMany != null && manyToMany && 
                 (baseBean instanceof ManyToManyDetailsMaintain) && 
                 ((ManyToManyDetailsMaintain)baseBean).getSelectedCurrentDetails().size() > 
                 0) || baseBean.getSelectedDTOS().size() > 0) {
                setCheckedItems(pagedList);
            }
        }
    }

    public void updateMyPadgedDataModel(PagingResponseDTO response) {

        setTotalListSize(response.getTotalListSize());

        int pageSize = getSharedUtil().getNoOfTableRows();

        setMyPagedDataModel(new PagedListDataModel(response.getResultList(), 
                                                   totalListSize, pageSize));

        // check if any item in the pagedList exists in selectedDTOs
        Boolean manyToMany = 
            (Boolean)baseBean.evaluateValueBinding("appMainLayout.manyToMany");

        if (((manyToMany != null && manyToMany && 
              (baseBean instanceof ManyToManyDetailsMaintain) && 
              ((ManyToManyDetailsMaintain)baseBean).getSelectedCurrentDetails().size() > 
              0) || baseBean.getSelectedDTOS().size() > 0) && 
            response.getResultList().size() > 0) {
            setCheckedItems(response.getResultList());
        }

    }

    public int getPagesCount() {

        int pageSize = getSharedUtil().getNoOfTableRows();
        int pagesCount = totalListSize / pageSize;

        if (totalListSize % pageSize > 0) {
            ++pagesCount;
        }

        return pagesCount;

    }

    public PagingResponseDTO getPagingResponse() {

        PagingResponseDTO responseDTO = null;

        PagingRequestDTO pagingRequestDTO = baseBean.getPagingRequestDTO();

        /*if (pagingRequestDTO == null ||
            pagingRequestDTO.getMethodName() == null ||
            pagingRequestDTO.getMethodName().equals("")) {

            responseDTO = baseBean.getAllWithPaging(null);

        } else {
*/

        if (baseBean.isSorting() && baseBean.isUsingBsnPaging()) {

            pagingRequestDTO.setBsnSortingColumnName(baseBean.getBsnSortingColumnName());
            pagingRequestDTO.setSortAscending(baseBean.isSortAscending());

        }

        if (pagingRequestDTO.getBeanName() == null) {

            // calling inner method
            responseDTO = getInnerResponse(pagingRequestDTO);

        } else {

            String methodBindingExpression = 
                pagingRequestDTO.getBeanName() + "." + 
                pagingRequestDTO.getMethodName();

            //Solve problem of clearing selectedDTOS in custom methods()
            List<IBasicDTO> tempSelectedDTOS = new ArrayList<IBasicDTO>();

            List<IBasicDTO> selectedDTOS = null;
            Boolean manyToMany = 
                (Boolean)baseBean.evaluateValueBinding("appMainLayout.manyToMany");
            if (manyToMany != null && manyToMany && 
                (baseBean instanceof ManyToManyDetailsMaintain)) {
                selectedDTOS = 
                        ((ManyToManyDetailsMaintain)baseBean).getSelectedCurrentDetails();
            } else {
                selectedDTOS = baseBean.getSelectedDTOS();
            }
            if (selectedDTOS != null && selectedDTOS.size() > 0) {
                tempSelectedDTOS.addAll(selectedDTOS);
            }
            try {
                responseDTO = 
                        (PagingResponseDTO)executeMethodBindingWithParams(methodBindingExpression, 
                                                                          new Object[] { pagingRequestDTO }, 
                                                                          new Class[] { PagingRequestDTO.class });
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (manyToMany != null && manyToMany && 
                (baseBean instanceof ManyToManyDetailsMaintain)) {
                //    ((ManyToManyDetailsMaintain)baseBean).setSelectedCurrentDetails(tempSelectedDTOS);
            } else {
                baseBean.setSelectedDTOS(tempSelectedDTOS);
            }

            tempSelectedDTOS = null;

        }

        //}

        return responseDTO;

    }

    // *********************** Private Methods *********************************

    private void resetSorting() {

        if (baseBean.isSorting()) {

            baseBean.setSorting(false);
            baseBean.setSortAscending(false);
            baseBean.setFullColumnName(null);

        }

    }

    private PagingResponseDTO getInnerResponse(PagingRequestDTO pagingRequestDTO) {

        PagingResponseDTO responseDTO = null;

        Class baseBeanClass = null;

        Method method = null;

        try {

            baseBeanClass = baseBean.getClass();

            while (baseBeanClass != null) {

                try {

                    method = 
                            baseBeanClass.getDeclaredMethod(pagingRequestDTO.getMethodName(), 
                                                            new Class[] { PagingRequestDTO.class });

                } catch (NoSuchMethodException e) {
                    baseBeanClass = baseBeanClass.getSuperclass();

                }

                if (method != null) {
                    break;
                }

            }

            if (method != null) {

                responseDTO = 
                        (PagingResponseDTO)method.invoke(baseBean, new Class<?>[] { null });

            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return responseDTO;

    }

    private Object executeMethodBindingWithParams(String methodBindingExepression, 
                                                  Object[] paramList, 
                                                  Class[] paramTypesList) {

        MethodBinding methodBinding = null;

        methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       methodBindingExepression + 
                                                                                       "}", 
                                                                                       paramTypesList);

        return methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                    paramList);

    }

    private List getSubList(List totalList, int startIndex, int endIndex) {

        List subList = new ArrayList();

        for (int i = startIndex; i < endIndex; ++i) {
            subList.add(totalList.get(i));
        }

        return subList;

    }

    private void setCheckedItems(List pagedList) {
        List<IBasicDTO> selectedDTOS = null;
        Boolean manyToMany = 
            (Boolean)baseBean.evaluateValueBinding("appMainLayout.manyToMany");
        if (manyToMany != null && manyToMany && 
            (baseBean instanceof ManyToManyDetailsMaintain)) {
            selectedDTOS = 
                    ((ManyToManyDetailsMaintain)baseBean).getSelectedCurrentDetails();
        } else {
            selectedDTOS = baseBean.getSelectedDTOS();
        }

        if (pagedList.size() < selectedDTOS.size()) {

            for (IClientDTO pageItem: (List<IClientDTO>)pagedList) {

                for (IBasicDTO selectedDTO: selectedDTOS) {

                    if ((pageItem.getCode().getKey()).equals(selectedDTO.getCode().getKey())) {

                        pageItem.setChecked(Boolean.TRUE);
                        break;

                    }

                }

            }

        } else {

            for (IBasicDTO selectedDTO: selectedDTOS) {

                for (IClientDTO pageItem: (List<IClientDTO>)pagedList) {

                    if ((pageItem.getCode().getKey()).equals(selectedDTO.getCode().getKey())) {

                        pageItem.setChecked(Boolean.TRUE);
                        break;

                    }

                }

            }

        }

    }

    private static SharedUtilBean getSharedUtil() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();

        return (SharedUtilBean)app.createValueBinding("#{shared_util}").getValue(ctx);

    }

}
