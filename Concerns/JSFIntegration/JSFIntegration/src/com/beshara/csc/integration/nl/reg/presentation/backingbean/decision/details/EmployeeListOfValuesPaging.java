package com.beshara.csc.nl.reg.presentation.backingbean.decision.details;

import com.beshara.base.client.ServiceNotAvailableException;
import com.beshara.base.deploy.SessionBeanProviderException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.IResponseDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.datascroller.ScrollerActionEvent;


public class EmployeeListOfValuesPaging extends LOVBaseBean {
    private String empListOfValuesStyle = "empListOfValuesStyle";
    private boolean searchAtAllEmployees;
    private Long ministryCode;
    private boolean cantLocateSession;

    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;

    private IEmpEmployeeSearchDTO empEmployeeSearchDTO = 
        EmpDTOFactory.createEmpEmployeeSearchDTO();
    private boolean searchInfCenter;
    public EmployeeListOfValuesPaging() {

        super.setMyTableData(new ArrayList());
        super.setSelectedDTOS(new ArrayList<IBasicDTO>());
        super.setUsingBsnPaging(true);
        super.setUsingPaging(true);
         super.setSelectedRadio("");
        super.setSearchTyp("1");
        super.setSearchQuery("");
        super.setSearchMode(false);
       
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(new ArrayList());
    }

    public void openLovDiv(ActionEvent ae) {
        Boolean manyToMany = 
            (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }

        BaseBean currentBaseBean = 
            (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lovEmpPaging);");


        if (isUsingPaging() && ae != null && 
            ae instanceof ScrollerActionEvent) {
            super.changePageIndex(ae);
        }
    }

    /**by Ashraf Gaber to reset LOV attributes*/
    public void resetData() {
        super.resetData();
        setCantLocateSession(false);
    }

    public String searchLovDiv() {
        setSearchInfCenter(false);
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        Object[] params = new Object[2];
        params[0] = getSearchTyp();
        params[1] = getSearchQuery();
        setSearchMode(true);
        resetPageIndex();
        if (isUsingPaging()) {
            setUpdateMyPagedDataModel(true);
        }


        if (!"".equals(getSearchMethod()) && getSearchMethod() != null) {
            return super.searchLovDiv();
        } else {
            searchForEmployee(getSearchTyp(), getSearchQuery());
            return "";
        }

    }

    public String cancelSearchLovDiv() {
        setSearchInfCenter(false);
        setSearchMode(false);
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setSearchQuery("");
        setSearchTyp("1");
        setCantLocateSession(false);
        if (isUsingPaging()) {
            getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        }

        if (!"".equals(getCancelSearchMethod()) && 
            getCancelSearchMethod() != null) {
            return (String)executeMethodBinding(getCancelSearchMethod(), null);
        } else {
            super.setMyTableData(new ArrayList());
            super.setSelectedDTOS(new ArrayList<IBasicDTO>());
        }
        return "";
    }


    public void searchForEmployee(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && 
            searchType != null && !searchType.equals("")) {
            super.setSearchMode(true);
            empEmployeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            empEmployeeSearchDTO.setEmployment(!searchAtAllEmployees); // employment default value was true so i negated the searchAtAllEmployees attribute
            if (searchType.toString().equals("0"))
                empEmployeeSearchDTO.setCivilId(Long.valueOf(searchQuery.toString()));
            else if (searchType.toString().equals("1"))
                empEmployeeSearchDTO.setEmpName(searchQuery.toString());
            try {

                if (isUsingPaging()) {
                    setUpdateMyPagedDataModel(true);
                    setPagingRequestDTO(new PagingRequestDTO("filterSearchByEmpWithPaging"));
                    setOldPageIndex(0);
                    setCurrentPageIndex(1);

                } else {
                    if (ministryCode != null) {
                        cantLocateSession = false;
                        super.setMyTableData(EmpClientFactory.getEmployeesClient(ministryCode).simpleSearchBasic(empEmployeeSearchDTO));
                    } else {
                        super.setMyTableData(EmpClientFactory.getEmployeesClient().simpleSearchBasic(empEmployeeSearchDTO));
                    }
                }
            } catch (SessionBeanProviderException e) {
                e.printStackTrace();
                super.setMyTableData(new ArrayList());

                cantLocateSession = true;
                super.setSearchMode(false);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                super.setMyTableData(new ArrayList());


                super.setSearchMode(false);
            } catch (DataBaseException e) {
                e.printStackTrace();
                super.setMyTableData(new ArrayList());
                super.setSearchMode(false);
            }
        } else {
            super.setErrorMsgValue(messageLocator("com.beshara.jsfbase.csc.msgresources.global", 
                                                  "missingField"));
            super.setSearchMode(false);
        }
        super.repositionPage(0);
        super.setSelectedDTOS(new ArrayList<IBasicDTO>(0));
    }

    public PagingResponseDTO filterSearchByEmpWithPaging(PagingRequestDTO pagingRequest) {
        IPagingResponseDTO bsnResponseDTO;
        if (searchInfCenter) {
            bsnResponseDTO = getSearchByInfWithPaging(pagingRequest);
        }
        else
        {
            bsnResponseDTO = getSearchByEmpWithPaging(pagingRequest);
        }
        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = 
                    new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else {
                pagingResponseDTO.setTotalListSize(((Long)getPagingRequestDTO().getParams()[0]).intValue());
            }
        }
        return pagingResponseDTO;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getSearchByEmpWithPaging(PagingRequestDTO pagingRequestDTO) {
        int pageIndex = getCurrentPageIndex();
        DecisionEmployeesMaintain decisionEmployeesMaintain = (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean =(DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO = 
            new com.beshara.base.paging.impl.PagingRequestDTO();
        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));
        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }
        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            //add ur sorting columns
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        try {
                bsnPagingResponseDTO =(com.beshara.base.paging.impl.PagingResponseDTO)(EmpClientFactory.getEmployeesClient()).simpleSearchWithPaging(empEmployeeSearchDTO,bsnPagingRequestDTO);
                List<IBasicDTO> result = bsnPagingResponseDTO.getBasicDTOList();
                for (int y =0; y<result.size();y++) {
                    decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData((IEmployeesDTO)result.get(y)));
                    if(decisionEmployeesModelSessionBean.isExist())
                    {
                         ((IEmployeesDTO)result.get(y)).setDisableIfFound(true);
                    }
                }
            bsnPagingResponseDTO.setBasicDTOList(result);
            decisionEmployeesMaintain.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lovEmpPaging);");
            } catch(NoResultException ne) {
            setSearchInfCenter(true);
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
               decisionEmployeesMaintain.setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lovEmpPaging,null,null);changeVisibilityDiv(window.blocker,window.divLov);");
        } catch (ServiceNotAvailableException e) {
              getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            setSearchInfCenter(true);
              bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }
        return bsnPagingResponseDTO;
    }


    public String messageLocator(String basename, String key) {
        ResourceBundle bundle = 
            ResourceBundle.getBundle(basename, FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }


    public void setEmpListOfValuesStyle(String empListOfValuesStyle) {
        this.empListOfValuesStyle = empListOfValuesStyle;
    }

    public String getEmpListOfValuesStyle() {
        return empListOfValuesStyle;
    }

    public void setSearchAtAllEmployees(boolean searchAtAllEmployees) {
        this.searchAtAllEmployees = searchAtAllEmployees;
    }

    public boolean isSearchAtAllEmployees() {
        return searchAtAllEmployees;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setCantLocateSession(boolean cantLocateSession) {
        this.cantLocateSession = cantLocateSession;
    }

    public boolean isCantLocateSession() {
        return cantLocateSession;
    }

    public void setEmpEmployeeSearchDTO(IEmpEmployeeSearchDTO empEmployeeSearchDTO) {
        this.empEmployeeSearchDTO = empEmployeeSearchDTO;
    }

    public IEmpEmployeeSearchDTO getEmpEmployeeSearchDTO() {
        return empEmployeeSearchDTO;
    }

    public void setSearchInfCenter(boolean searchInfCenter) {
        this.searchInfCenter = searchInfCenter;
    }

    public boolean isSearchInfCenter() {
        return this.searchInfCenter;
    }
    
    private com.beshara.base.paging.impl.PagingResponseDTO getSearchByInfWithPaging(PagingRequestDTO pagingRequestDTO) {
        
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean =(DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        IResponseDTO responseDTO=InfDTOFactory.createResponseDTO();
        int pageIndex = getCurrentPageIndex();
        IKwtCitizensResidentsSearchDTO kwtCitizensResidentsSearchDTO = InfDTOFactory.createKwtCitizensResidentsSearchDTO();
        kwtCitizensResidentsSearchDTO.setRequestDTO(InfDTOFactory.createRequestDTO());
        kwtCitizensResidentsSearchDTO.getRequestDTO().setPageNum(new Long(pageIndex));
        kwtCitizensResidentsSearchDTO.getRequestDTO().setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            kwtCitizensResidentsSearchDTO.getRequestDTO().setCountRequired(true);
        }
        if (isSorting()) {
            kwtCitizensResidentsSearchDTO.getRequestDTO().setSortAscending(pagingRequestDTO.isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            //add ur sorting columns
            kwtCitizensResidentsSearchDTO.getRequestDTO().setSortColumnList(sortingColumnList);
        }
        if(getEmpEmployeeSearchDTO().getCivilId()!=null)
        kwtCitizensResidentsSearchDTO.setCivilId(getEmpEmployeeSearchDTO().getCivilId());
        if(getEmpEmployeeSearchDTO().getEmpName()!=null)
            kwtCitizensResidentsSearchDTO.setName(getEmpEmployeeSearchDTO().getEmpName());
        try {
            responseDTO = 
                    InfClientFactory.getKwtCitizensResidentsClientForCenter().searchCitizens(kwtCitizensResidentsSearchDTO);
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            bsnPagingResponseDTO.setCount(responseDTO.getCount());
            com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =  new com.beshara.base.paging.impl.PagingRequestDTO();
            bsnPagingRequestDTO.setPageNum(responseDTO.getRequestDTO().getPageNum());
            bsnPagingRequestDTO.setMaxNoOfRecords(responseDTO.getRequestDTO().getMaxNoOfRecords());
            if (pageIndex == 1) {
                bsnPagingRequestDTO.setCountRequired(true);
            }
            if (isSorting()) {
                bsnPagingRequestDTO.setSortAscending(responseDTO.getRequestDTO().isSortAscending());
                List<String> sortingColumnList = new ArrayList<String>();
                //add ur sorting columns
                bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
            }
            bsnPagingResponseDTO.setRequestDTO(bsnPagingRequestDTO);
            List<IBasicDTO> result = responseDTO.getBasicDTOList();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (IBasicDTO kwDTO: result) {
                IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
                employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwDTO.getCode()).getCivilId()));
                employeesDTO.setCitizensResidentsDTO((IKwtCitizensResidentsDTO)kwDTO);
                employeesDTO.setAuditStatus(kwDTO.getAuditStatus());
                employeesDTO.setCreatedBy(kwDTO.getCreatedBy());
                employeesDTO.setCreatedDate(kwDTO.getCreatedDate());
                employeesDTO.setLastUpdatedBy(kwDTO.getLastUpdatedBy());
                employeesDTO.setLastUpdatedDate(kwDTO.getLastUpdatedDate());
                employeesDTO.setTabrecSerial(((IKwtCitizensResidentsDTO)kwDTO).getTabrecSerial());
                IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
                hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
                employeesDTO.setHireStatusDTO(hireStatusDTO);
                decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(employeesDTO));
                
                if(decisionEmployeesModelSessionBean.isExist())
                {
                 employeesDTO.setDisableIfFound(true);
                }
                listDTO.add(employeesDTO);
            }
            bsnPagingResponseDTO.setBasicDTOList(listDTO);

        } catch (NoResultException ne) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
            DecisionEmployeesMaintain x = 
                (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
            x.setJavaScriptCaller("hideLookUpDiv(window.blocker,window.divLov,null,null);changeVisibilityDiv(window.blocker,window.lovEmpPaging);");

               } catch (ServiceNotAvailableException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }
       
        return bsnPagingResponseDTO;
    }
    public void noButton() {
        setReturnMethodName("decisionEmployeesMaintainBean.returnMethodAction");
        setUsingBsnPaging(true);
        setUsingPaging(true);
    }
    public String searchLovDivCenter() {
        setSearchInfCenter(true);
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        Object[] params = new Object[2];
        params[0] = getSearchTyp();
        params[1] = getSearchQuery();
        setSearchMode(true);
        resetPageIndex();
        if (isUsingPaging()) {
            setUpdateMyPagedDataModel(true);
        }
        if (!"".equals(getSearchMethod()) && getSearchMethod() != null) {
            return super.searchLovDiv();
        } else {
            searchForEmployee(getSearchTyp(), getSearchQuery());
            return "";
        }

    }

}