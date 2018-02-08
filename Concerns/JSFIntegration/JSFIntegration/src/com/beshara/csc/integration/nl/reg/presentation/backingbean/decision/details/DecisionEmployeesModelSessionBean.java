package com.beshara.csc.nl.reg.presentation.backingbean.decision.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.entity.EmpDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IEmpDecisionsEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DecisionEmployeesModelSessionBean extends ManyToManyDetailsMaintain {

    private Map<Long, List> originalMap = new HashMap();
    private Map<Long, IBasicDTO> addElementMap = new HashMap();
    private int newCurrentListSize=0;
    boolean exist = false;

    public DecisionEmployeesModelSessionBean() {
        setClient(RegClientFactory.getEmpDecisionsClient());
    }

    public void navigateToNextPage(Long pageNo) {
        DecisionEmployeesMaintain decisionEmployeesMaintainBean =  (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
        DecisionMaintainBean decisionBean = (DecisionMaintainBean)BeansUtil.getValue("decisionMaintainBean");
        if (getOriginalMap().containsKey(pageNo)) {
            List objList = getOriginalMap().get(pageNo);
            for (int i = 0; i < objList.size(); i++) {
                getAddElementMap().put(((IEmpDecisionsEntityKey)((IEmpDecisionsDTO)objList.get(i)).getCode()).getCivilId(),(IEmpDecisionsDTO)objList.get(i));
                decisionEmployeesMaintainBean.setCurrentDetails(objList);
            }
            decisionEmployeesMaintainBean.setCurrentPageIndex(pageNo.intValue());
            decisionEmployeesMaintainBean.setUpdateMyPagedDataModel(true);
        } else {
            try {
                Long indexOfDB = (pageNo - 1) * 11;
                List list =RegClientFactory.getDecisionsClient().getEmpDecisionsDTOListByPage(decisionBean.getPageDTO().getCode(),indexOfDB);
                if (list != null) {
                    getOriginalMap().put(pageNo, list);
                    for (int i = 0; i < list.size(); i++) {
                        getAddElementMap().put(((IEmpDecisionsEntityKey)((IEmpDecisionsDTO)list.get(i)).getCode()).getCivilId(),(IEmpDecisionsDTO)list.get(i));
                        decisionEmployeesMaintainBean.setCurrentDetails(list);
                    }
                    decisionEmployeesMaintainBean.setCurrentPageIndex(pageNo.intValue());
                    decisionEmployeesMaintainBean.setUpdateMyPagedDataModel(true);
                }
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
    }

    public int determineNoOfPaqe() {
        DecisionEmployeesMaintain decisionEmployeesMaintainBean =  (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
        int newPage = (decisionEmployeesMaintainBean.getCurrentListSize() / 11) + 1;
        return newPage;
    }

    public boolean checkExistData(IBasicDTO dto) {
        
        DecisionMaintainBean decisionBean = (DecisionMaintainBean)BeansUtil.getValue("decisionMaintainBean");
        Long civil = new Long(dto.getCode().getKey());
        if (getAddElementMap().containsKey(civil)) {
            exist = true;
            return exist;
        } else {
            try {
                exist = RegClientFactory.getEmpDecisionsClient().checkEixstEmpInDecision(decisionBean.getPageDTO().getCode(),((IEmployeesEntityKey)((IEmployeesDTO)dto).getCode()).getCivilId());
            } catch (SharedApplicationException e) {
                exist=false;
                e.printStackTrace();
            } catch (DataBaseException e) {
                exist=false;
                e.printStackTrace();
            }
            return exist;
        }
    }

    public void addNewElement(IBasicDTO dto) {
        int newPage = 0;
        DecisionEmployeesMaintain decisionEmployeesMaintainBean = (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
        DecisionMaintainBean decisionMaintainBean = (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");        
        newPage = determineNoOfPaqe();
        navigateToNextPage(new Long(newPage));
        List objList = new ArrayList();
        if (getOriginalMap().containsKey(new Long(newPage))) {
            objList = getOriginalMap().get(new Long(newPage));
            objList.add(dto);
            getOriginalMap().put(new Long(newPage), objList);
            getAddElementMap().put(new Long(((EmpDecisionsEntityKey)dto.getCode()).getCivilId()),dto);
        } else {
            objList.add(dto);
            getAddElementMap().put(new Long(((EmpDecisionsEntityKey)dto.getCode()).getCivilId()),dto);
        }
        getOriginalMap().put(new Long(newPage), objList);
        if(decisionMaintainBean.getMaintainMode()==1L)
        {
        setNewCurrentListSize(decisionEmployeesMaintainBean.getCurrentListSize() + 1);
        }
        else
        {
        setNewCurrentListSize(getNewCurrentListSize()+1);
        }
        decisionEmployeesMaintainBean.setCurrentDetails(objList);
        decisionEmployeesMaintainBean.setCurrentPageIndex(newPage);
        decisionEmployeesMaintainBean.setUpdateMyPagedDataModel(true);
    }

    public void sendListtoBussiens() throws Exception {
        DecisionMaintainBean decisionBean =  (DecisionMaintainBean)BeansUtil.getValue("decisionMaintainBean");
        DecisionEmployeesMaintain decisionEmployeesMaintainBean = (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
        DecisionListBean decisionListBean =(DecisionListBean)BeansUtil.getValue("decisionListBean");
        List list = new ArrayList();
        List empList = new ArrayList();
        for (int i = 0; i < determineNoOfPaqe(); i++) {
            Long index = new Long(i + 1);
            list = getOriginalMap().get(index);
            if (list != null) {
                for (int y = 0; y < list.size(); y++) {
                    if (((IEmpDecisionsDTO)list.get(y)).getStatusFlag() ==  null) {
                        empList.add((IEmpDecisionsDTO)list.get(y));
                    }
                    if (((IEmpDecisionsDTO)list.get(y)).getStatusFlag() != null) {
                        if (((IEmpDecisionsDTO)list.get(y)).getStatusFlag() != 4L && ((IEmpDecisionsDTO)list.get(y)).getStatusFlag() != 5L) {
                            empList.add((IEmpDecisionsDTO)list.get(y));
                        }
                    }
                }
            }
        }
        int deleteItem=0;
        for(int z=0;z<empList.size();z++)
        {
          if(((IEmpDecisionsDTO)empList.get(z)).getStatusFlag()!=null)
          {
            if(((IEmpDecisionsDTO)empList.get(z)).getStatusFlag()==1L || ((IEmpDecisionsDTO)empList.get(z)).getStatusFlag()==5L )
            {
                deleteItem=deleteItem+1;
            }
          }
        }
        if(deleteItem==decisionEmployeesMaintainBean.getCountOfAllEmpDecision())
        {
            if(decisionListBean.isCancelDescisionFlag()==false)
            {
            getSharedUtil().handleException(new Exception(), 
                                            "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                            "no_employess_Added");
            throw new Exception();
            }
        }
        ((IDecisionsDTO)decisionBean.getPageDTO()).setEmpDecisionsDTOList(empList);
    }

    public void resetSessionData() {
        setOriginalMap(new HashMap());
        setAddElementMap(new HashMap());
        setNewCurrentListSize(0);
        setExist(false);
    }

    public void setOriginalMap(Map<Long, List> originalMap) {
        this.originalMap = originalMap;
    }

    public Map<Long, List> getOriginalMap() {
        return originalMap;
    }

    public void setNewCurrentListSize(int newCurrentListSize) {
        this.newCurrentListSize = newCurrentListSize;
    }

    public int getNewCurrentListSize() {
        return newCurrentListSize;
    }
    public void setAddElementMap(Map<Long, IBasicDTO> addElementMap) {
        this.addElementMap = addElementMap;
    }

    public Map<Long, IBasicDTO> getAddElementMap() {
        return addElementMap;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean isExist() {
        return exist;
    }
}
