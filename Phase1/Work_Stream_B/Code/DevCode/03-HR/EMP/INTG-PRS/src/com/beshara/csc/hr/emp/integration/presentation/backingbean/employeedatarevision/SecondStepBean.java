package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.mov.business.client.IMovMovingRequestsClient;
import com.beshara.csc.hr.mov.business.client.MovClientFactory;
import com.beshara.csc.hr.mov.business.dto.MovMovingRequestsDTO;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpPayslipsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.inf.business.client.IPersonsInformationClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.client.IPersonBankAccountsClient;
import com.beshara.csc.nl.bnk.business.dto.BnkDTOFactory;
import com.beshara.csc.nl.bnk.business.dto.PersonBankAccountsDTO;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.net.ntp.TimeStamp;


public class SecondStepBean extends LookupMaintainBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "govEmpSecondStepBean";
    private static final String NAVIGATION_KEY = "govempsecondstep";

    private IPersonsInformationDTO personQualificationsDTO = InfDTOFactory.createPersonsInformationDTO();
    private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
    private IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();

    private Date endJobDate;
    private Date firstHireDate;


    private final Long MAL_GENDER = ISystemConstant.GENDER_MALE;
    private final Long KW_NATIONALITY = ISystemConstant.KUWAIT_NATIONALITY;

    private String groupName;
    private String caderName;
    private String degreeName;

    private PersonBankAccountsDTO personBankAccountsDTO;
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

    private MovMovingRequestsDTO movMovingRequestsDTO;

    private String jobGroup;
    private String totalReward;
    private java.sql.Date jobAssignDate;
    private java.sql.Date techJobAssignDate;
    private String hireDegree;
    private Date movingDate;
    private Date currentDegreeDate;
    private Date empHireDateFromInf;
    private Date empHireDateFromSoc;
    public SecondStepBean() {
        setContent1Div("module_tabs_cont");
    }

    public static SecondStepBean getInstance() {
        return (SecondStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        return app;
    }


    private IBasicDTO fillEmpBankAcc() {
        try {
            List<IBasicDTO> list =
                ((IPersonBankAccountsClient)BnkClientFactory.getPersonBankAccountsClient()).getValidPersonBankAccountsByCivilID(maintainBean.getRealCivilId());
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BnkDTOFactory.createPersonBankAccountsDTO();
    }

    public String init() {
        IMovMovingRequestsClient movClient = MovClientFactory.getMovMovingRequestsClient();
        IPersonsInformationClient infClient = InfClientFactory.getPersonsInformationClient();
        Long realCivilId = maintainBean.getRealCivilId();
        Long civilID = GovEmpMaintainBean.getEmpCivilId();
        try {
            
            empHireDateFromInf = InfClientFactory.getKwtWorkDataClient().getEmployeeFirstHireDate(realCivilId);
          
        } catch (DataBaseException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
         empHireDateFromSoc =   EmpClientFactory.getEmployeeExtraDataClient().getFirstHireDateFromSocialInsurance(civilID);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        personBankAccountsDTO = (PersonBankAccountsDTO)fillEmpBankAcc();

        
        try {
            // new change request  as Applied in adc Module CSC-21977
            personQualificationsDTO = (IPersonsInformationDTO)infClient.getQualForEmp(realCivilId, true).get(0);

            // old tech  personQualificationsDTO = (IPersonQualificationsDTO)infClient.getLastPersonQualification(realCivilId);
            movMovingRequestsDTO = (MovMovingRequestsDTO)movClient.getByRealCivilId(realCivilId);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try{
            movingDate=movClient.getMovingDate(realCivilId);
            System.out.print("Welcome To moving DATE");
            System.out.print(movingDate);
            }
         catch (Exception e)
        {
            e.printStackTrace();
            System.out.print("NO DATA FOUND)))))))))))))))))))))))))");
        }
        try {
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
            if (maintainBean.getMinistryCode() != null) {
                salEmpSalaryElementsDTO =
                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmpSalaryElementByCivilId(civilID).get(0);
            } else {
                // salEmpSalaryElementsDTO =(ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalaryElementByCivilId(civilID).get(0);
                salEmpSalaryElementsDTO =
                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getCurrentSalEmpRaiseByCivilAndType(civilID,
                                                                                                                                       ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);

            }
            setCurrentDegreeDate(salEmpSalaryElementsDTO.getCurrentDegreeDate());
            ISalElementGuidesDTO salElementGuidesDTORaise = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO salElementGuidesDTODegree = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO salElementGuidesDTOGroup = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO salElementGuidesDTOCader = SalDTOFactory.createSalElementGuidesDTO();

            if (salEmpSalaryElementsDTO != null) {
                salElementGuidesDTORaise = salEmpSalaryElementsDTO.getSalElementGuidesDTO();
                setDegreeName(salElementGuidesDTORaise.getName());
            }
            if (salElementGuidesDTORaise != null) {
                salElementGuidesDTODegree =
                        (ISalElementGuidesDTO)((ISalElementGuidesDTO)salElementGuidesDTORaise.getParentObject()).getParentObject();
            }
            if (salElementGuidesDTODegree != null) {
                salElementGuidesDTOGroup = (ISalElementGuidesDTO)salElementGuidesDTODegree.getParentObject();
                setGroupName(salElementGuidesDTOGroup.getName());
            }
            if (salElementGuidesDTOGroup != null) {
                salElementGuidesDTOCader = (ISalElementGuidesDTO)salElementGuidesDTOGroup.getParentObject();
                setCaderName(salElementGuidesDTOCader.getName());
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }


        try {
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
            if (maintainBean.getMinistryCode() != null) {
                employeesDTO =
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient(maintainBean.getMinistryCode()).getEmployeByCivilId(civilID).get(0);
            } else {
                employeesDTO =
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeByCivilId(civilID).get(0);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        firstHireDate = getEmpFirstHireDate();
        if (employeesDTO != null && employeesDTO.getCitizensResidentsDTO() != null &&
            ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGenderTypesDTO() != null) {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
            if (kwtCitizensResidentsDTO.getGenderTypesDTO().getGentypeCode().equals(MAL_GENDER) &&
                kwtCitizensResidentsDTO.getNationality().equals(KW_NATIONALITY)) {
                endJobDate = calculateJobEndDate(kwtCitizensResidentsDTO.getBirthDate(), 65);
            } else {
                endJobDate = calculateJobEndDate(kwtCitizensResidentsDTO.getBirthDate(), 60);
            }
        }

        ICatsDTO catDTO = JobDTOFactory.createCatsDTO();
        if (employeesDTO != null) {
            if (employeesDTO.getJobDTO() != null) {
                catDTO = ((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO();
                if (catDTO != null) {
                    while (catDTO.getParentObject() != null) {
                        catDTO = (ICatsDTO)catDTO.getParentObject();
                    }
                    jobGroup = catDTO.getName();
                }
            }
            String jobCode = "", techJobCode = "", civilId = "";
            if (employeesDTO.getJobDTO() != null) {
                jobCode = employeesDTO.getJobDTO().getCode().getKey(); // jobCode
            }
            if (employeesDTO.getTechJobsDTO() != null) {
                techJobCode = employeesDTO.getTechJobsDTO().getCode().getKey();
            }
            civilId = employeesDTO.getCode().getKey();
            try {
                // JobAssignDate [INF]
                jobAssignDate =
                        InfClientFactory.getKwtWorkDataClient().getJobAssignDate(employeesDTO.getRealCivilId(), Long.valueOf(jobCode));
            } catch (Exception e) {
                if(jobCode.isEmpty())
                    System.out.println(".... employeesDTO.getJobDTO() IS NULL ....");
                e.printStackTrace();
            }
            try {
                // TechJobAssignDate [INF]
                techJobAssignDate =
                        InfClientFactory.getKwtWorkDataClient().getTechJobAssignDate(employeesDTO.getRealCivilId(),
                                                                                     Long.valueOf(techJobCode));
            } catch (Exception e) {
                if(techJobCode.isEmpty())
                    System.out.println(".... employeesDTO.getTechJobsDTO() IS NULL ....");
                e.printStackTrace();
            }
            try {
                // HireDegree [SAL]
            ISalElementGuidesDTO salElementGuidesDTO=
                    (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getHireDegree(civilID);

                hireDegree = salElementGuidesDTO.getName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // TotalReward [SAL]
            totalReward = calculateTotalReward(employeesDTO.getRealCivilId()).toString();
        }
        return NAVIGATION_KEY;
    }

    public Float calculateTotalReward(Long realCivilId) {
        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        searchDTO.setCivilId(realCivilId);
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        searchDTO.setSalaryMonth(Long.valueOf(month));
        searchDTO.setYear(Long.valueOf(year));
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        searchDTO.setMinCode(minCode);
        String filterByPayType =
            IScpConstants.MON_SAL_PAY_TYPE_SALARIES.toString() + "," + IScpConstants.MON_SAL_PAY_TYPE_OTHERS.toString();

        String excludePayStatus =
            IScpConstants.NO_CALC_PAYSTATUS_CODE.toString() + "," + IScpConstants.MON_SAL_STATUS_FIN_SINGLE_NOT_SIGNED.toString() +
            "," + IScpConstants.MON_SAL_STATUS_FIN_SINGLE_NOT_PAYED.toString() + "," +
            IScpConstants.MON_SAL_STATUS_FIN_SINGLE_PAYED.toString() + "," +
            IScpConstants.SCP_MER_UNSIGNED.toString() + "," + IScpConstants.SCP_MER__ENTERED.toString() + "," +
            IScpConstants.SAL_PAY_STATUS_SAL_CALC_STOPPED.toString() + "," +
            IScpConstants.MON_SAL_STATUS_FIN_WITH_SALARY_NOT_SIGNED.toString();

        searchDTO.setFilterByPayType(filterByPayType);
        searchDTO.setExcludePayStatus(excludePayStatus);
        searchDTO.setFilterByPayMethod(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE_2.toString() + "," +
                                       IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString());

        searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_MERIT);
        Float totalReward = 0.0f;
        try {
            List meritsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            for (Object dto1 : (ArrayList)meritsList) {
                ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
                totalReward += dto.getValue().floatValue(); // to sum total merits
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalReward;
    }

    public void getAll() throws DataBaseException {
    }

    private Date getEmpFirstHireDate() {
        Date firstHireDate = null;
        try {
            firstHireDate =
                    // old tech  EmpClientFactory.getEmpEmployeeHistoriesClient().getFirstHireDate(maintainBean.getRealCivilId());
                    // CSC-21977 By M.abdelsabour
                    EmpClientFactory.getEmployeesClient().getEmpFirstHireDate(maintainBean.getRealCivilId());
        } catch (Throwable e) {
            System.err.println(e.getMessage());
        }

        return (firstHireDate != null ? firstHireDate : null);
    }

    public Date calculateJobEndDate(Timestamp date, int noOfYears) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            Date birthDate = new Date(date.getTime());
            calendar.setTime(birthDate);
            calendar.add(Calendar.YEAR, noOfYears);
            java.util.Date dateUtil = calendar.getTime();
            return new Date(dateUtil.getTime());
        }
        return null;
    }

    public void setPersonQualificationsDTO(IPersonsInformationDTO personQualificationsDTO) {
        this.personQualificationsDTO = personQualificationsDTO;
    }

    public IPersonsInformationDTO getPersonQualificationsDTO() {
        return personQualificationsDTO;
    }

    public void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO) {
        this.salEmpSalaryElementsDTO = salEmpSalaryElementsDTO;
    }

    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {
        return salEmpSalaryElementsDTO;
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEndJobDate(Date endJobDate) {
        this.endJobDate = endJobDate;
    }

    public Date getEndJobDate() {
        return endJobDate;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setFirstHireDate(Date firstHireDate) {
        this.firstHireDate = firstHireDate;
    }

    public Date getFirstHireDate() {
        return firstHireDate;
    }

    public void setPersonBankAccountsDTO(PersonBankAccountsDTO personBankAccountsDTO) {
        this.personBankAccountsDTO = personBankAccountsDTO;
    }

    public PersonBankAccountsDTO getPersonBankAccountsDTO() {
        return personBankAccountsDTO;
    }

    public void setMovMovingRequestsDTO(MovMovingRequestsDTO movMovingRequestsDTO) {
        this.movMovingRequestsDTO = movMovingRequestsDTO;
    }

    public MovMovingRequestsDTO getMovMovingRequestsDTO() {
        return movMovingRequestsDTO;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setTotalReward(String totalReward) {
        this.totalReward = totalReward;
    }

    public String getTotalReward() {
        return totalReward;
    }

    public void setJobAssignDate(Date jobAssignDate) {
        this.jobAssignDate = jobAssignDate;
    }

    public Date getJobAssignDate() {
        return jobAssignDate;
    }

    public void setTechJobAssignDate(Date techJobAssignDate) {
        this.techJobAssignDate = techJobAssignDate;
    }

    public Date getTechJobAssignDate() {
        return techJobAssignDate;
    }

    public void setHireDegree(String hireDegree) {
        this.hireDegree = hireDegree;
    }

    public String getHireDegree() {
        return hireDegree;
    }


    public void setMovingDate(Date movingDate) {
        this.movingDate = movingDate;
    }

    public Date getMovingDate() {
        return movingDate;
    }

    public void setCurrentDegreeDate(Date currentDegreeDate) {
        this.currentDegreeDate = currentDegreeDate;
    }

    public Date getCurrentDegreeDate() {
        return currentDegreeDate;
    }

    public void setEmpHireDateFromInf(Date empHireDateFromInf) {
        this.empHireDateFromInf = empHireDateFromInf;
    }

    public Date getEmpHireDateFromInf() {
        return empHireDateFromInf;
    }

    public void setEmpHireDateFromSoc(Date empHireDateFromSoc) {
        this.empHireDateFromSoc = empHireDateFromSoc;
    }

    public Date getEmpHireDateFromSoc() {
        return empHireDateFromSoc;
    }
}
