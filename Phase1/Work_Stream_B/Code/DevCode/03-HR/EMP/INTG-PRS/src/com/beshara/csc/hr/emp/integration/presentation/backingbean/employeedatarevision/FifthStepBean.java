package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.nl.bnk.business.client.IPersonBankAccountsClient;
import com.beshara.csc.nl.bnk.business.client.PersonBankAccountsClientImpl;
import com.beshara.csc.nl.bnk.business.dto.PersonBankAccountsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.util.ArrayList;
import java.util.List;


public class FifthStepBean extends LookupMaintainBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "govEmpFifthStepBean";
    private static final String NAVIGATION_KEY = "govempfifthstep";

    private IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();

    private String degreeWhenHired;

    private Double totalMerits = new Double(0D);
    private Double totalDeductions = new Double(0D);
    private Double netSalary = new Double(0D);

    private Long salElementType = new Long(ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT);
    private Long salElementTypeBenefits = new Long(ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT); //badalat
    private Long salElementTypeRewards = new Long(ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT); //mokafa2at

    private List<IBasicDTO> benifitsAndRewardsList = new ArrayList<IBasicDTO>(); //salEmpSalaryElements
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

    public FifthStepBean() {
        setCustomDiv1("m2mAddDivClass");
        setCustomDiv1TitleKey("empty_label");
        setContent1Div("module_tabs_cont");

    }


    public static FifthStepBean getInstance() {
        return (FifthStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public String init() {
        Long civilID = GovEmpMaintainBean.getEmpCivilId();

        try {
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
            //            if(maintainBean.getMinistryCode() != null){
            //            employeesDTO =(IEmployeesDTO)EmpClientFactory.getEmployeesClient(maintainBean.getMinistryCode()).getEmployeByCivilId(civilID).get(0);
            //            }
            //            else
            //            {
            //                employeesDTO =(IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeByCivilId(civilID).get(0);
            //            }
            //
            IPersonBankAccountsClient personBankAccountsClient = new PersonBankAccountsClientImpl();
            List BankList =
                personBankAccountsClient.getValidPersonBankAccountsByCivilID(maintainBean.getRealCivilId());

            if (maintainBean.getMinistryCode() != null) {

                employeesDTO =
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient(maintainBean.getMinistryCode()).getEmployeByCivilId(civilID).get(0);
            } else {
               
                employeesDTO =
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeByCivilId(civilID).get(0);


            }
            if (BankList.size() > 0) {
                PersonBankAccountsDTO personBankAccountsDTO = (PersonBankAccountsDTO)BankList.get(0);
                employeesDTO.setBankBrancheDTO(personBankAccountsDTO.getBankBranchesDTO());
                employeesDTO.setAccountNo(personBankAccountsDTO.getAccountNo());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {

            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
            if (maintainBean.getMinistryCode() != null) {
                degreeWhenHired =
                        SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmployeeDegreeWhenHired(civilID);
            } else {
                degreeWhenHired = SalClientFactory.getSalEmpSalaryElementsClient().getEmployeeDegreeWhenHired(civilID);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        searchDTO.setCivilId(civilID);
        String[] monthYear = SharedUtils.returnMonthYearFromSqlDate(SharedUtils.getCurrentDate());
        searchDTO.setSalaryMonth(Long.parseLong(monthYear[0]));
        searchDTO.setYear(Long.parseLong(monthYear[1]));

        searchDTO.setSalElementType(ISalConstants.ELEMENT_GUIDE_TYPE_MERIT_ROOT);
        totalMerits = getTotalSalEmpSalaryElementsValue(searchDTO);
        searchDTO.setSalElementType(ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT_ROOT);
        totalDeductions = getTotalSalEmpSalaryElementsValue(searchDTO);

        netSalary = totalMerits - totalDeductions;
        if (netSalary < 0) {
            netSalary = new Double(0D);
        }
        return NAVIGATION_KEY;
    }

    public void getAll() throws DataBaseException {
    }

    private Double getTotalSalEmpSalaryElementsValue(ISalEmpMonSearchCriteriaDTO searchDTO) {
        Double totalValue = 0D;
        List<IBasicDTO> salEmpSalaryElementsDTOList = null;
        try {
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
            if (maintainBean.getMinistryCode() != null) {
                salEmpSalaryElementsDTOList =
                        SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmployeeElementsByTypeInPeriode(searchDTO);
            } else {
                salEmpSalaryElementsDTOList =
                        SalClientFactory.getSalEmpSalaryElementsClient().getEmployeeElementsByTypeInPeriode(searchDTO);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (salEmpSalaryElementsDTOList != null) {
            for (IBasicDTO basicDTO : salEmpSalaryElementsDTOList) {
                Float value = ((ISalEmpSalaryElementsDTO)basicDTO).getElementValue();
                if (value != null)
                    totalValue += value;
            }
        }
        return totalValue;
    }

    public void showBenefitsRewardsDiv() {
        try {
            salElementType = ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT;
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
            if (maintainBean.getMinistryCode() != null) {
                benifitsAndRewardsList =
                        SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmpBenefitsByCivil(GovEmpMaintainBean.getEmpCivilId());
            } else {
                benifitsAndRewardsList =
                        SalClientFactory.getSalEmpSalaryElementsClient().getEmpBenefitsByCivil(GovEmpMaintainBean.getEmpCivilId());
            }

        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void salElementTypeChanged() {
        benifitsAndRewardsList = new ArrayList<IBasicDTO>();
        try {
            if (salElementType.equals(salElementTypeBenefits)) {
                ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT
                if (maintainBean.getMinistryCode() != null) {
                    benifitsAndRewardsList =
                            SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmpBenefitsByCivil(GovEmpMaintainBean.getEmpCivilId());
                } else {
                    benifitsAndRewardsList =
                            SalClientFactory.getSalEmpSalaryElementsClient().getEmpBenefitsByCivil(GovEmpMaintainBean.getEmpCivilId());
                }
            } else {
                if (maintainBean.getMinistryCode() != null) {
                    benifitsAndRewardsList =
                            SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmpRewardsByCivil(GovEmpMaintainBean.getEmpCivilId());
                } else {
                    benifitsAndRewardsList =
                            SalClientFactory.getSalEmpSalaryElementsClient().getEmpRewardsByCivil(GovEmpMaintainBean.getEmpCivilId());
                }
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setTotalMerits(Double totalMerits) {
        this.totalMerits = totalMerits;
    }

    public Double getTotalMerits() {
        return totalMerits;
    }

    public void setTotalDeductions(Double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public Double getTotalDeductions() {
        return totalDeductions;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setSalElementType(Long salElementType) {
        this.salElementType = salElementType;
    }

    public Long getSalElementType() {
        return salElementType;
    }

    public void setSalElementTypeBenefits(Long salElementTypeBenefits) {
        this.salElementTypeBenefits = salElementTypeBenefits;
    }

    public Long getSalElementTypeBenefits() {
        return salElementTypeBenefits;
    }

    public void setSalElementTypeRewards(Long salElementTypeRewards) {
        this.salElementTypeRewards = salElementTypeRewards;
    }

    public Long getSalElementTypeRewards() {
        return salElementTypeRewards;
    }

    public void setDegreeWhenHired(String degreeWhenHired) {
        this.degreeWhenHired = degreeWhenHired;
    }

    public String getDegreeWhenHired() {
        return degreeWhenHired;
    }

    public void setBenifitsAndRewardsList(List<IBasicDTO> benifitsAndRewardsList) {
        this.benifitsAndRewardsList = benifitsAndRewardsList;
    }

    public List<IBasicDTO> getBenifitsAndRewardsList() {
        return benifitsAndRewardsList;
    }
}
