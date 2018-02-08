package com.beshara.csc.hr.emp.integration.presentation.utils;


import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.HireStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.shared.EmpUtils;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeServiceNotEndedException;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.EmpSalaryElementsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.IEmpSalaryElementsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeeLastDegNotMokafaaShamlaException;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.io.Serializable;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;


public class EmployeeHelper  implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    public EmployeeHelper() {
        super();
    }

    public static IEmployeesDTO getHiredAndHavePayrollInfoEmp(Long realCivilId) throws SharedApplicationException,
                                                                                       DataBaseException {
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        return getHiredAndHavePayrollInfoEmpInMinstry(realCivilId, minCode);
    }

    public static IEmployeesDTO getMokafaaShamlaEndedServiceEmp(Long realCivilId) throws SharedApplicationException,
                                                                                       DataBaseException {
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        return getMokafaaShamlaEndedServiceEmpInMin(realCivilId, minCode);
    }
    
    public static IEmployeesDTO getMokafaaShamlaEndedServiceEmpInMin(Long realCivilId,
                                                                     Long minCode) throws SharedApplicationException,
                                                                                          DataBaseException {
        System.out.println("getMokafaaShamlaEndedServiceEmpInMin realCivilId = " + realCivilId);
        IEmployeesDTO employeeDTO = null;

        if (realCivilId != null) {
            // check if given real civil id exists
            if (GlobalValidation.isValidCivilId(realCivilId)) {

                employeeDTO =
                        getEmployeeByStatusAndMinistry(Long.valueOf(realCivilId), EmployeeStatus.SERVICE_ENDED, minCode);
                //get employee payroll info
                ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                    getLastPayrollInfo_EndedService(((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId());
                Long salByDecFlag = salEmpSalaryElementsDTO.getSalElementGuidesDTO().getSalByDecesionFlag();
                if (salByDecFlag == null || !salByDecFlag.equals(1L)) {
                    System.out.println(" this Emp has not mokafaa shamla deg");
                    throw new EmployeeLastDegNotMokafaaShamlaException();
                } else
                    employeeDTO.setSalEmpSalaryElementsDTO(salEmpSalaryElementsDTO);
            } else {
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
        }
        return employeeDTO;
    }
    
    public static IEmployeesDTO getHiredAndHavePayrollInfoEmpInMinstry(Long realCivilId,
                                                                       Long minCode) throws SharedApplicationException,
                                                                                            DataBaseException {
        IEmployeesDTO employeeDTO = null;

        if (realCivilId != null) {
            // check if given real civil id exists
            if (GlobalValidation.isValidCivilId(realCivilId)) {
                Long civilId = null;
System.out.println(">>>  getHiredAndHavePayrollInfoEmpInMinstry  :::: realCivilId = "+realCivilId+" , minCode = "+minCode);
                // get employee by real civil id
                employeeDTO = getEmployeeByRealCivilIdAndMinistry(realCivilId, minCode);

                IEmployeesEntityKey employeesEntityKey = (IEmployeesEntityKey)employeeDTO.getCode();
                civilId = employeesEntityKey.getCivilId();
                // check if employee is hired or not
                //checkEmployeeHired(employeesEntityKey);
                Long hireStatusCode = Long.parseLong( ((HireStatusEntityKey)employeeDTO.getHireStatusDTO().getCode() ).getKey() );
                Boolean isEmployeeHired = checkEmployeeHired(hireStatusCode);
                System.out.println(">>>  getHiredAndHavePayrollInfoEmpInMinstry  :::: civilId = "+civilId+" , hireStatusCode = "+hireStatusCode+" , isEmployeeHired = "+isEmployeeHired);
                if(!isEmployeeHired){throw new EmployeeNotHiredException("employee not hired.");}

                //get employee payroll info
                ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = getPayrollInfo(realCivilId);
                employeeDTO.setSalEmpSalaryElementsDTO(salEmpSalaryElementsDTO);


            } else {
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
        }
        return employeeDTO;
    }
    public static IEmployeesDTO getHiredAndHavePayrollInfoEmpInMinstryWithoutDataFilteration(Long realCivilId,
                                                                       Long minCode) throws SharedApplicationException,
                                                                                            DataBaseException {
        IEmployeesDTO employeeDTO = null;

        if (realCivilId != null) {
            // check if given real civil id exists
            if (GlobalValidation.isValidCivilId(realCivilId)) {
                Long civilId = null;
    System.out.println(">>>  getHiredAndHavePayrollInfoEmpInMinstry  :::: realCivilId = "+realCivilId+" , minCode = "+minCode);
                // get employee by real civil id
                employeeDTO = getEmployeeByRealCivilIdAndMinistryWithoutDataFilteration(realCivilId, minCode);

                IEmployeesEntityKey employeesEntityKey = (IEmployeesEntityKey)employeeDTO.getCode();
                civilId = employeesEntityKey.getCivilId();
                // check if employee is hired or not
                //checkEmployeeHired(employeesEntityKey);
                Long hireStatusCode = Long.parseLong( ((HireStatusEntityKey)employeeDTO.getHireStatusDTO().getCode() ).getKey() );
                Boolean isEmployeeHired = checkEmployeeHired(hireStatusCode);
                System.out.println(">>>  getHiredAndHavePayrollInfoEmpInMinstry  :::: civilId = "+civilId+" , hireStatusCode = "+hireStatusCode+" , isEmployeeHired = "+isEmployeeHired);
                if(!isEmployeeHired){throw new EmployeeNotHiredException("employee not hired.");}

                //get employee payroll info
                ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = getPayrollInfo(realCivilId);
                employeeDTO.setSalEmpSalaryElementsDTO(salEmpSalaryElementsDTO);


            } else {
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
        }
        return employeeDTO;
    }
    public static IEmployeesDTO getEmployeeByRealCivilId(Long realCivilId) throws SharedApplicationException {
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();

        return getEmployeeByRealCivilIdAndMinistry(realCivilId, minCode);
    }

    public static IEmployeesDTO getEmployeeByRealCivilIdAndMinistry(Long realCivilId, Long minCode) throws SharedApplicationException {
        List<IEmployeesDTO> empList = null;
        IEmployeesDTO employeeDTO = null;
        boolean isHired = false;
         IEmployeesClient empClient= EmpClientFactory.getEmployeesClient();
        
        try {
            //////////////////////added by Technical Team M.sayed//////////////////////////////
            boolean isValid= empClient.checkFilterDataForEmp(CSCSecurityInfoHelper.getGroupCode(),CSCSecurityInfoHelper.getUserCode(),realCivilId);
            if (!isValid){
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
            ////////////////////////////////////////////////////////////////////////////////////
            empList = empClient.getCurrActiveEmployeesByRealCivilId(realCivilId);
            for (int i = 0; i < empList.size(); i++) {
                employeeDTO = empList.get(i);
                if((IEMPConstants._EMP_ACTIVE_STATUS ).equals( employeeDTO.getActiveFlag() ) )
                {
                    isHired = true;
                    if(minCode==null || employeeDTO.getMinCode().equals(minCode) ){
                        return employeeDTO;
                    }
                }
            }
            //employeeDTO = EmpClientFactory.getEmployeesClient().getCurrActiveEmpByRealCivilId(realCivilId, minCode);
        } catch (Exception e) {
            throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
        }
        if(isHired)throw new EmployeeNotHiredInMinException("Employee Not Hired In Min");
        else{throw new EmployeeNotHiredException("employee not hired.");}
    }
    public static IEmployeesDTO getEmployeeByRealCivilIdAndMinistryWithoutDataFilteration(Long realCivilId, Long minCode) throws SharedApplicationException {
        List<IEmployeesDTO> empList = null;
        IEmployeesDTO employeeDTO = null;
        boolean isHired = false;
         IEmployeesClient empClient= EmpClientFactory.getEmployeesClient();
        
        try {
            //////////////////////added by Technical Team M.sayed//////////////////////////////
            /*boolean isValid= empClient.checkFilterDataForEmp(CSCSecurityInfoHelper.getGroupCode(),CSCSecurityInfoHelper.getUserCode(),realCivilId);
            if (!isValid){
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }*/
            ////////////////////////////////////////////////////////////////////////////////////
            empList = empClient.getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(realCivilId);
            for (int i = 0; i < empList.size(); i++) {
                employeeDTO = empList.get(i);
                if((IEMPConstants._EMP_ACTIVE_STATUS ).equals( employeeDTO.getActiveFlag() ) )
                {
                    isHired = true;
                    if(minCode==null || employeeDTO.getMinCode().equals(minCode) ){
                        return employeeDTO;
                    }
                }
            }
            //employeeDTO = EmpClientFactory.getEmployeesClient().getCurrActiveEmpByRealCivilId(realCivilId, minCode);
        } catch (Exception e) {
            throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
        }
        if(isHired)throw new EmployeeNotHiredInMinException("Employee Not Hired In Min");
        else{throw new EmployeeNotHiredException("employee not hired.");}
    }
    public static IEmployeesDTO getEmployeeByRealCivilIdInAllMinistries(Long realCivilId) throws SharedApplicationException {
        List<IEmployeesDTO> empList = null;
        IEmployeesDTO employeeDTO = null;
        boolean isHired = false;
         IEmployeesClient empClient= EmpClientFactory.getEmployeesClient();
        
        try {
            //////////////////////added by Technical Team M.sayed//////////////////////////////
            boolean isValid= empClient.checkFilterDataForEmp(CSCSecurityInfoHelper.getGroupCode(),CSCSecurityInfoHelper.getUserCode(),realCivilId);
            if (!isValid){
                throw new EmployeeCivilIdNotExistException(IEMPConstants.DATA_FILTERATION_EXCEPTION_MSG);
            }
            ////////////////////////////////////////////////////////////////////////////////////
            empList = empClient.getCurrActiveEmployeesByRealCivilId(realCivilId);
            for (int i = 0; i < empList.size(); i++) {
                employeeDTO = empList.get(i);
                if((IEMPConstants._EMP_ACTIVE_STATUS ).equals( employeeDTO.getActiveFlag() ) )
                {
                    isHired = true;
                    return employeeDTO;                    
                }
            }
            //employeeDTO = EmpClientFactory.getEmployeesClient().getCurrActiveEmpByRealCivilId(realCivilId, minCode);
        } catch (Exception e) {
            throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
        }
        if(isHired)throw new EmployeeNotHiredInMinException("Employee Not Hired In Min");
        else{throw new EmployeeNotHiredException("employee not hired.");}
    }
    

    private static Boolean checkEmployeeHired(Long hireStatusCode) throws SharedApplicationException{
        try {

            String hireStatusStr = EmpUtils.getStatusForHire();
            String statusArr[] = hireStatusStr.split(",");
            System.out.println("checkEmployeeHired >>>>  statusArr.length = "+statusArr.length+" , hireStatusStr = "+hireStatusStr+" , hireStatusCode = "+hireStatusCode);
            for (int i = 0; i < statusArr.length; i++) {
                if(hireStatusCode.equals(Long.parseLong(statusArr[i]) ) ){
                    return Boolean.TRUE;
                } 
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeeNotHiredException("employee not hired.");
        }

    }
    
//    private static void checkEmployeeHired(IEmployeesEntityKey employeesEntityKey) throws SharedApplicationException,
//                                                                                          DataBaseException {
//        try {
//
//            boolean empHired = EmpClientFactory.getEmployeesClient().isEmployeeHired(employeesEntityKey);
//            System.out.println("checkEmployeeHired >>>>  empHired = "+empHired);
//            if (!empHired) {
//                throw new EmployeeNotHiredException("employee not hired.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new EmployeeNotHiredException("employee not hired.");
//        }
//
//    }

    public static IEmployeesDTO getEmployeeByStatus(Long realCivilId,
                                                    EmployeeStatus empStatus) throws SharedApplicationException,
                                                                                     DataBaseException {
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        return getEmployeeByStatusAndMinistry(realCivilId, empStatus, minCode);

    }

    public static IEmployeesDTO getEmployeeByStatusAndMinistry(Long realCivilId, EmployeeStatus empStatus,
                                                               Long minCode) throws SharedApplicationException,
                                                                                    DataBaseException {
        IEmployeesDTO employeeDTO = null;
        if (realCivilId != null) {
            // check if given real civil id exists
            if (GlobalValidation.isValidCivilId(realCivilId)) {
                Long civilId = null;

                // get employee by real civil id
                employeeDTO = getEmployeeByRealCivilIdAndMinistry(realCivilId, minCode);

                IEmployeesEntityKey employeesEntityKey = (IEmployeesEntityKey)employeeDTO.getCode();
                civilId = employeesEntityKey.getCivilId();

                if (empStatus.equals(EmployeeStatus.HIRED)) {

                    //checkEmployeeHired(employeesEntityKey);
                    Long hireStatusCode = Long.parseLong( ((HireStatusEntityKey)employeeDTO.getHireStatusDTO().getCode() ).getKey() );
                    Boolean isEmployeeHired = checkEmployeeHired(hireStatusCode);
                    if(!isEmployeeHired){throw new EmployeeNotHiredException("employee not hired.");}

                } else if (empStatus.equals(EmployeeStatus.SERVICE_ENDED)) {
                    Long hireStatus =
                        ((HireStatusEntityKey)employeeDTO.getHireStatusDTO().getCode()).getHirstatusCode();
                    if (!hireStatus.equals(IEMPConstant.EMP_STATUS_END_SERVICE)) {
                        throw new EmployeeServiceNotEndedException("employee status not service ended.");
                    }
                }
                else if (empStatus.equals(EmployeeStatus.ALL)) {}


            } else {
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
        }
        return employeeDTO;

    }

    public static ISalEmpSalaryElementsDTO getPayrollInfo(Long civilId) throws SharedApplicationException, DataBaseException {
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = null;
        try {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<    getPayrollInfo >>>>>>>>>>>>>>>>>>>>>>  civilId : "+civilId);
            salEmpSalaryElementsDTO =
                    (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(civilId,
                                                                                               ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
            if (salEmpSalaryElementsDTO == null) {
                throw new EmployeePayrollInfoNotExistException("payroll info not exist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeePayrollInfoNotExistException("payroll info not exist.");
        }
        return salEmpSalaryElementsDTO;
    }

    public static ISalEmpSalaryElementsDTO getLastPayrollInfo_EndedService(Long civilIdSerial) throws SharedApplicationException, DataBaseException {
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = null;
        try {
            System.out.println("<<<<<<<<<<<<    getLastPayrollInfo_EndedService >>>>>>  civilId : "+civilIdSerial);
            IEmpSalaryElementsSearchCriteriaDTO searchDTO = new EmpSalaryElementsSearchCriteriaDTO();
            searchDTO.setCivilId(civilIdSerial);
            searchDTO.setElmGuideType(ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
            salEmpSalaryElementsDTO =
                    (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getLastPayrollInfo_EndedService(searchDTO);
            if (salEmpSalaryElementsDTO == null) {
                throw new EmployeePayrollInfoNotExistException("payroll info not exist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeePayrollInfoNotExistException("payroll info not exist.");
        }
        return salEmpSalaryElementsDTO;
    }
    
    public static IEmployeesDTO getEmployeePayrollInfo(Long civilId) throws SharedApplicationException,
                                                                            DataBaseException {
        IEmployeesDTO employeeDTO = null;
        try {
            employeeDTO = (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeeInfoByElmType(civilId);
            if (employeeDTO == null) {
                throw new EmployeePayrollInfoNotExistException("payroll info not exist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeePayrollInfoNotExistException("payroll info not exist.");
        }

        return employeeDTO;

    }

    public static IEmployeesDTO getLastActiveEmp(Long realCivilId) throws SharedApplicationException,
                                                                                       DataBaseException {
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        return getLastActiveEmpInMin(realCivilId, minCode);
    }
    
    public static IEmployeesDTO getLastActiveEmpInMin(Long realCivilId,
                                                                     Long minCode) throws SharedApplicationException,
                                                                                          DataBaseException {
        System.out.println("getLastActiveEmpInMin realCivilId = " + realCivilId);
        IEmployeesDTO employeeDTO = null;

        if (realCivilId != null) {
            // check if given real civil id exists
            if (GlobalValidation.isValidCivilId(realCivilId)) {

                employeeDTO =
                        getEmployeeByStatusAndMinistry(Long.valueOf(realCivilId), EmployeeStatus.ALL, minCode);
                //get employee payroll info
                ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                    getLastPayrollInfo_EndedService(((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId());
                
                    employeeDTO.setSalEmpSalaryElementsDTO(salEmpSalaryElementsDTO);
            } else {
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
        }
        return employeeDTO;
    }
    
    public static IEmployeesDTO getByRealCivilForFTQ(Long realCivilId) throws SharedApplicationException,
                                                                                       DataBaseException {
        IEmployeesDTO employeeDTO = null;
        IEmployeesClient employeesClient = EmpClientFactory.getEmployeesClient();
        System.out.println("getByRealCivilForFTQ ");
        try {
//            employeeDTO = employeesClient.getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(realCivilId).get(0);
            List<IEmployeesDTO> empList = employeesClient.getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(realCivilId);
            for (int i = 0; i < empList.size(); i++) {
                employeeDTO = empList.get(i);
                System.out.println("employeeDTO.getActiveFlag() = "+employeeDTO.getActiveFlag()+" , ((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId() = "+((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId() );
                if((IEMPConstants._EMP_ACTIVE_STATUS ).equals( employeeDTO.getActiveFlag() ) )
                {
                    System.out.println(" *2* employeeDTO.getActiveFlag() = "+employeeDTO.getActiveFlag()+" , ((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId() = "+((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId() );
            //get employee payroll info
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                getLastPayrollInfo_EndedService(((IEmployeesEntityKey)employeeDTO.getCode()).getCivilId());
            
            employeeDTO.setSalEmpSalaryElementsDTO(salEmpSalaryElementsDTO);
                    return employeeDTO;
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
        }
        return employeeDTO;
    }
    
    public static IEmployeesDTO getByRealCivilId(Long realCivilId) throws SharedApplicationException {
        List<IEmployeesDTO> empList = null;
        IEmployeesDTO employeeDTO = null;
        IEmployeesClient empClient = EmpClientFactory.getEmployeesClient();
        try {
            //////////////////////added by Technical Team M.sayed//////////////////////////////
            boolean isValid =
                empClient.checkFilterDataForEmp(CSCSecurityInfoHelper.getGroupCode(), CSCSecurityInfoHelper.getUserCode(),
                                                realCivilId);
            if (!isValid) {
                throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
            }
            ////////////////////////////////////////////////////////////////////////////////////
            empList = empClient.getCurrActiveEmployeesByRealCivilId(realCivilId);
            for (int i = 0; i < empList.size(); i++) {

                if ((IEMPConstants._EMP_ACTIVE_STATUS).equals(empList.get(i).getActiveFlag())) {
                    employeeDTO = empList.get(i);
                    break;
                }
            }
        } catch (Exception e) {
            throw new EmployeeCivilIdNotExistException("employee civil id not exists.");
        }
        return employeeDTO;
    }


/********************************************************************************/
    private String realCivilId;
    private boolean empHired = true;
    private boolean empHiredInMin = true;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean empEndedService = true;
    private boolean empLastDegNotMokafaaShamla = true;
    private boolean payrollInfoExist = true;
    private boolean showPayrollInfo = false;
    private boolean showBankAccData = false;
    private boolean checkNat = false;
    private String civilId;
    IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    private String payrollRMargin = "margin-right: 11px;";

    private String resetButtonMethod;
    private String backBeanNameFrom;
    /* added by A.Nour 21-02-2016 story CSC-16776 AOE */
    private String empHireStatus; // null for hired Emp , 99 for ended services Emp , 111 for all , 
                                  // 222 for any in any min (FTQ)
    
    public IEmployeesDTO filterByCivilId() {

        resetMessages();
        System.out.println("EmployeeHelper filterByCivilId empHireStatus = "+empHireStatus);
        //The used civilId is the realCivilId
        if (realCivilId != null && !realCivilId.equals("")) {

            //getHighlightedDTOS().clear();
//            IEmployeesDTO empDTO = null;

            try {
                if(empHireStatus != null && empHireStatus.equals("99") ) {
                    System.out.println("filterByCivilId empHireStatus = "+ empHireStatus);
                    employeesDTO = getMokafaaShamlaEndedServiceEmp(Long.valueOf(realCivilId) );
                        
//                        getEmployeeByStatus(Long.valueOf(realCivilId) , EmployeeStatus.SERVICE_ENDED);
//                    //get employee payroll info
//                    ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = getLastPayrollInfo_EndedService( ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId() );
//                    Long salByDecFlag = salEmpSalaryElementsDTO.getSalElementGuidesDTO().getSalByDecesionFlag();
//                    if(salByDecFlag == null || !salByDecFlag.equals(1L) ){
//                        System.out.println(" this Emp has not mokafaa shamla deg");
//                        throw new EmployeeLastDegNotMokafaaShamlaException();
//                    }
//                    else employeesDTO.setSalEmpSalaryElementsDTO(salEmpSalaryElementsDTO);
                    
                }else if(empHireStatus != null && empHireStatus.equals("111") ) { /* ALL */
                    employeesDTO = getLastActiveEmp(Long.valueOf(realCivilId) );
                } else if(empHireStatus != null && empHireStatus.equals("222") ) { /* Any Min , FTQ status */
                    employeesDTO = getByRealCivilForFTQ(Long.valueOf(realCivilId) );
                }
                else
                    employeesDTO = getHiredAndHavePayrollInfoEmp(Long.valueOf(realCivilId));

                //((ISalEmpStopSalariesDTO)getPageDTO()).setEmployeesDTO(empDTO);
                civilExist = true;
                validCivilId = true;
                return employeesDTO;
                //getAll();

            }catch (EmployeeNotHiredInMinException e) {
                empHiredInMin = false;
                e.printStackTrace();
            } catch (EmployeeNotHiredException e) {
                empHired = false;
                e.printStackTrace();
            } catch (EmployeePayrollInfoNotExistException e) {
                payrollInfoExist = false;
                e.printStackTrace();
            } catch (EmployeeCivilIdNotExistException e) {
                civilExist = false;
                validCivilId = false;
                e.printStackTrace();
            } catch (EmployeeServiceNotEndedException e) {
                civilExist = false;
                empEndedService = false;
                e.printStackTrace();
            } catch (EmployeeLastDegNotMokafaaShamlaException e) {
                civilExist = false;
                empLastDegNotMokafaaShamla = false;
                e.printStackTrace();
            } catch (Exception e) {
                civilExist = false;
                empHired = false;
                e.printStackTrace();
            }

        }
        return null;
    }

    private void resetMessages() {
        empHired = true;
        empEndedService = true;
        empLastDegNotMokafaaShamla = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;
    }
    
    public void reSetData() { // for remov warinig ActionEvent ae
        //ae = null;
        setEmployeesDTO(EmpDTOFactory.createEmployeesDTO());
        setCivilId(null);
        realCivilId = null;
        civilExist = false;
        empHired = true;
        empEndedService = true;
        empLastDegNotMokafaaShamla = true;
        empHiredInMin = true;
        validCivilId = true;
        payrollInfoExist = true;
        if (resetButtonMethod != null)
            executeMethodBinding(getBackBeanNameFrom()+"."+getResetButtonMethod(),null );
    }

    public Object executeMethodBinding(String methodBindingExepression, Object[] paramList) {

        MethodBinding methodBinding = null;
        if (paramList != null) {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", new Class[] { });
        } else {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", null);
        }
        return methodBinding.invoke(FacesContext.getCurrentInstance(), paramList);

    }
    
    public void setRealCivilId(String realCivilId) {
        this.realCivilId = realCivilId;
    }

    public String getRealCivilId() {
        return realCivilId;
    }

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setPayrollInfoExist(boolean payrollInfoExist) {
        this.payrollInfoExist = payrollInfoExist;
    }

    public boolean isPayrollInfoExist() {
        return payrollInfoExist;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setResetButtonMethod(String resetButtonMethod) {
        this.resetButtonMethod = resetButtonMethod;
    }

    public String getResetButtonMethod() {
        return resetButtonMethod;
    }

    public void setBackBeanNameFrom(String backBeanNameFrom) {
        this.backBeanNameFrom = backBeanNameFrom;
    }

    public String getBackBeanNameFrom() {
        return backBeanNameFrom;
    }

    public void setShowPayrollInfo(boolean showPayrollInfo) {
        this.showPayrollInfo = showPayrollInfo;
    }

    public boolean isShowPayrollInfo() {
        return showPayrollInfo;
    }

    public void setPayrollRMargin(String payrollRMargin) {
        this.payrollRMargin = payrollRMargin;
    }

    public String getPayrollRMargin() {
        return payrollRMargin;
    }

    public void setShowBankAccData(boolean showBankAccData) {
        this.showBankAccData = showBankAccData;
    }

    public boolean isShowBankAccData() {
        return showBankAccData;
    }

    public void setCheckNat(boolean checkNat) {
        this.checkNat = checkNat;
    }

    public boolean isCheckNat() {
        return checkNat;
    }

    public void setEmpHireStatus(String empHireStatus) {
        this.empHireStatus = empHireStatus;
    }

    public String getEmpHireStatus() {
        return empHireStatus;
    }

    public void setEmpEndedService(boolean empEndedService) {
        this.empEndedService = empEndedService;
    }

    public boolean isEmpEndedService() {
        return empEndedService;
    }

    public void setEmpLastDegNotMokafaaShamla(boolean empLastDegNotMokafaaShamla) {
        this.empLastDegNotMokafaaShamla = empLastDegNotMokafaaShamla;
    }

    public boolean isEmpLastDegNotMokafaaShamla() {
        return empLastDegNotMokafaaShamla;
    }
}
