package com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.IRegFinancialSearchDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryQueryDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSettelmentsDTO;
import com.beshara.csc.hr.sal.business.dto.RegFinancialSearchDTO;
import com.beshara.csc.hr.sal.business.dto.SalEGDTO;
import com.beshara.csc.hr.sal.business.dto.SalEmpSalaryQueryDTO;
import com.beshara.csc.hr.sal.business.dto.SalEmpSettelmentsDTO;
import com.beshara.csc.hr.sal.business.entity.ISalEmpSettelmentsEntityKey;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.DecisionCycleMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SettlementDetailsBean extends LookUpBaseBean{

    @SuppressWarnings("compatibility:2198605230753273148")
    private static final long serialVersionUID = 1L;
    private String yearsKey;
    private List<IBasicDTO> yearsList;
    private String monthKey;
    private List<IBasicDTO> monthsList;
    private List<ISalElementGuidesDTO> benfitsList;
    private List<ISalElementGuidesDTO> deductionsList;
    private List<ISalElementGuidesDTO> benefitsListFromDataBase;
    private List<ISalElementGuidesDTO> deductionsListFromDataBase;
    private float benfitsTotalValue;
    private float dedectionsTotalValue;
    private float totalSalary;
    private int benfitsListSize;
    private int deductionsListSize;
    private boolean showErrMsg;
    private List<ISalEmpSettelmentsDTO> initialListForBenefitsAndDeductions;
     private IEmployeesDTO employeesDTO ;
    private Long civilID;
    private boolean showPagingBar;
    /*CSC-15730 A.Nour*/
    Map<IBasicDTO, List<List>> elmGuidesInsurRatiosMap = new HashMap<IBasicDTO, List<List>>();
    String insuranceTypes = getInsuranceTypes();
        
        
    List<IBasicDTO> removedSalElementGuideList;
    private boolean disableMonthAndYear;
    private boolean dataReadyToBeRemoved;
    DecisionCycleMaintainBean decisionMaintainBean;
    
    private IDecisionsDTO currentLoadedDecision ;
    List<IBasicDTO> relatedRecordsToParent;

    private String settMonthKey;
    private String settYearsKey;
    private static final String BUNDLE = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
    private boolean editSearchDate = true;
    
    private IEmpDecisionsDTO dto;
    
    private int maintainMode=0;
    private final static String BACK_NAV_CASE="settlementdecisioncycleemployeesmaintain";
    private Boolean isEmpUnderInsurance = true;
    
    public SettlementDetailsBean() {
        setClient(RegClientFactory.getEmpDecisionsClient());
        decisionMaintainBean = (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        if (monthKey == null && yearsKey == null)
            setSearchCurrentMonthYear();
        
        if (settMonthKey == null && settYearsKey == null)
            setSettCurrentMonthYear();
    }

    public void initData(IEmpDecisionsDTO dto, int mode) {
        this.dto = dto;
        employeesDTO = (IEmployeesDTO)dto.getEmployeesDTO();
        //Long realCivilId = employeesDTO.getRealCivilId();
        civilID = ((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)((IEmpDecisionsDTO)dto).getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId();
        
        //According to last change request, the insurance will be calculated or not
        //will be checked during the save using the new db function
        //CSC-19291: Ayman
        isEmpUnderInsurance = true;
//        try {
//            isEmpUnderInsurance = SalClientFactory.getSalEmpSettelmentsClient().checkEmpUnderInsurance(civilID);
//        } catch (DataBaseException e) {e.printStackTrace();isEmpUnderInsurance = Boolean.FALSE;
//        } catch (SharedApplicationException e) {e.printStackTrace();isEmpUnderInsurance = Boolean.FALSE;
//        }
        maintainMode = mode;
        boolean loadedAlready = dto.isLoadedBefore();
        if (dto.getStatusFlag() == null && !dto.isLoadedBefore()) { 
            if(dto.getSettMonth()!=null)
                settMonthKey=dto.getSettMonth().toString();
            if(dto.getSettYear()!=null)
                settYearsKey=dto.getSettYear().toString();
            loadDataFromDatabase();
            dto.setLoadedBefore(true);
            //decisionMaintainBean.setDataLoadedBefore(true);
        }else{
            benfitsList=dto. getBenList();
            deductionsList=dto.getDedList();
            benifitList2=dto.getBenefitList() ;
            deductionsList2=dto.getDeductList();
            if(dto.getStatusFlag()!=null && dto.getStatusFlag()==0 && !dto.isLoadedBefore())
                dto.setLoadedBefore(true); 
               else{
                yearsKey=String.valueOf(dto.getYear());
                monthKey=String.valueOf(dto.getMonth());
                setSettMonthKey(String.valueOf(dto.getSettMonth()));
                setSettYearsKey(String.valueOf(dto.getSettYear()));
                }
                
            if(loadedAlready) {
                if(isEmpUnderInsurance)createElmGuidesInsurRatiosMapForEdit();
            }
                
            }
        
    }
    private boolean isInsuranceDed(String dedType){
        boolean isInsurance = false;
        if(insuranceTypes.indexOf(dedType) >= 0 )isInsurance = true;
        return isInsurance;
    }
    public String getInsuranceTypes(){
        StringBuilder temp = new  StringBuilder(IScpConstants.DED_$36 );
        temp.append(",");temp.append(IScpConstants.DED_$37);
        temp.append(",");temp.append(IScpConstants.DED_$38);
        temp.append(",");temp.append(IScpConstants.DED_$47);
        String insuranceTypes = temp.toString();
        return insuranceTypes;
    }
    
    public void viewTable() throws ParseException {
        System.out.println("start viewTable :: "+SharedUtils.getCurrentTimestamp().toString());
        setShowErrMsg(false);
        benfitsList = new ArrayList<ISalElementGuidesDTO>();
        deductionsList = new ArrayList<ISalElementGuidesDTO>();
        try {
            // commented by ali agamy 08/12/2015
            //            if (decisionMaintainBean.getMaintainMode() != decisionMaintainBean.MAINTAIN_MODE_EDIT) {
            //                Long rowCount =
            //                    SalClientFactory.getSalEmpMonSalariesClient().checkEmpSalaryCalculated(getCivilID(), Long.valueOf(settYearsKey),
            //                                                                                           Long.valueOf(settMonthKey));
            //                if (rowCount > 0) {
            //                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "settlementAlreadyAdded"));
            //                    return;
            //                }
            //            }
            /*Long rowCount = SalClientFactory.getSalEmpMonSalariesClient().checkEmpSalaryCalculated(getCivilID() ,Long.valueOf(yearsKey) ,Long.valueOf(monthKey));
        if(rowCount > 0){
          getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"settlementAlreadyAdded"));
          return ;
        }else{*/
            ISalEmpSalaryQueryDTO salEmpSalaryQueryDTO = new SalEmpSalaryQueryDTO();
            Long civilIdNotReal = ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId();
            /**updated by A.Nour because HR_SAL_EMP_SALARY_ELEMENTS civilId is not real */
            salEmpSalaryQueryDTO.setCivilId(civilIdNotReal);//getCivilID()); 
            salEmpSalaryQueryDTO.setRealCivilId(getCivilID());
            salEmpSalaryQueryDTO.setFromDate(getFromDate());
            salEmpSalaryQueryDTO.setUntilDate(getUntilDate());
            salEmpSalaryQueryDTO.setType("1");
            salEmpSalaryQueryDTO.setYearCode(Long.valueOf(getYearsKey()));
            salEmpSalaryQueryDTO.setSalaryMonth(Long.valueOf(getMonthKey()));
            salEmpSalaryQueryDTO.setInsuranceTypes(insuranceTypes);
            java.sql.Date settDate = new java.sql.Date(getFromDate().getTime());
            salEmpSalaryQueryDTO.setSettDate(settDate);

            try {

                // benfitsList = SalClientFactory.getSalElementGuidesClient().filterEmpSalaryQueryList(salEmpSalaryQueryDTO);
    //                benfitsList =
    //                        SalClientFactory.getSalElementGuidesClient().filterEmpSalaryQueryListSettelmentDecisionUpdated(salEmpSalaryQueryDTO);
                Map empLists = SalClientFactory.getSalEmpSettelmentsClient().getBenfitsDeductsInsuranceSettListsForSettDec(null , salEmpSalaryQueryDTO);

                if(empLists.get("benfitsList") != null){
                    benfitsList = (List<ISalElementGuidesDTO>)empLists.get("benfitsList");
                }
                if(empLists.get("deductsList") != null){
                    deductionsList = (List<ISalElementGuidesDTO>)empLists.get("deductsList");
                }

                /*CSC-15730 A.Nour*/
                benfitsTotalValue = 0;
                if (benfitsList != null && benfitsList.size() != 0) {
                    setBenfitsListSize(benfitsList.size());
                    setShowPagingBar(true);
                    for (ISalElementGuidesDTO dto : benfitsList) {
                        if (dto.getValue() != null) {
                            benfitsTotalValue += dto.getValue();
                        }
                    }
                   // if (decisionMaintainBean.getMaintainMode() != decisionMaintainBean.MAINTAIN_MODE_EDIT) {
                        createElmGuidesInsurRatiosMapForAdd();
                   // }
                }

            } catch (SharedApplicationException e) {
                benfitsList = new ArrayList<ISalElementGuidesDTO>();
            } catch (DataBaseException e) {
                benfitsList = new ArrayList<ISalElementGuidesDTO>();
            }
    //            try {
    //                ISalEmpSalaryQueryDTO salEmpSalaryQueryDTO = new SalEmpSalaryQueryDTO();
    //                salEmpSalaryQueryDTO.setCivilId(getCivilID());
    //                salEmpSalaryQueryDTO.setFromDate(getFromDate());
    //                salEmpSalaryQueryDTO.setUntilDate(getUntilDate());
                salEmpSalaryQueryDTO.setType("2");
    //                salEmpSalaryQueryDTO.setYearCode(Long.valueOf(getYearsKey()));
    //                salEmpSalaryQueryDTO.setSalaryMonth(Long.valueOf(getMonthKey()));
                dedectionsTotalValue = 0;
    //                deductionsList =
    //                        SalClientFactory.getSalElementGuidesClient().filterEmpSalaryQueryListSettelmentDecisionUpdated(salEmpSalaryQueryDTO);
                // check the Status Heressss
                boolean validForInsurance = false;
                validForInsurance = checkCivilIdForInsurance(getCivilID());
                /* if True */
                if (validForInsurance) {
                    // append the New List to the Deduction List
                    //                    List<Long> insuranceTypesList = new ArrayList<Long>();
                    //                    // add Types
                    //                    insuranceTypesList.add(36L);
                    //                    insuranceTypesList.add(37L);
                    //                    insuranceTypesList.add(38L);
                    //                    insuranceTypesList.add(47L);
    //                    String insuranceTypes = "";
    //                    insuranceTypes = IScpConstants.DED_$36 + ",";
    //                    insuranceTypes += IScpConstants.DED_$37 + ",";
    //                    insuranceTypes += IScpConstants.DED_$38 + ",";
    //                    insuranceTypes += IScpConstants.DED_$47;
    //                    java.sql.Date settDate = new java.sql.Date(getFromDate().getTime());
    //                    List<IBasicDTO> resultForInsurance =
    //                        SalClientFactory.getSalInsurRatioClient().getInsuarnceElementGuideRecordsForREG(insuranceTypes,
    //                                                                                                        settDate);
    //                    for (IBasicDTO elem : resultForInsurance) {
    //                        ISalElementGuidesDTO dto = (SalElementGuidesDTO)elem;
    //                        //Set Value to ZERO till manipulate that in datbase later
    //                        if (dto.getValue() == null)
    //                            dto.setValue(0F);
    //                        deductionsList.add(dto);
    //                    }
                }
                /***********************/
                /*CSC-15730 A.Nour*/
                createBenifitList2();
                /*CSC-15730 A.Nour*/
                createDeductionsList2();
                System.out.println("End viewTable :: "+SharedUtils.getCurrentTimestamp().toString());

                if (deductionsList != null && deductionsList.size() != 0) {
                    setDeductionsListSize(deductionsList.size());
                    setShowPagingBar(true);
                    for (ISalElementGuidesDTO dto : deductionsList) {
                        dedectionsTotalValue += dto.getValue();
                    }
                }
    //            } catch (SharedApplicationException e) {
    //                deductionsList = new ArrayList<ISalElementGuidesDTO>();
    //            } catch (DataBaseException e) {
    //                deductionsList = new ArrayList<ISalElementGuidesDTO>();
    //            }
            totalSalary = benfitsTotalValue - dedectionsTotalValue;
            //}
            
           // updateDetailList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void viewTableForEdit() throws ParseException {
        System.out.println("start viewTableForEdit :: "+SharedUtils.getCurrentTimestamp().toString());
        setShowErrMsg(false);
        try {
                benfitsTotalValue = 0;
                if (benfitsList != null && benfitsList.size() != 0) {
                    setBenfitsListSize(benfitsList.size());
                    setShowPagingBar(true);
                    for (ISalElementGuidesDTO dto : benfitsList) {
                        if (dto.getValue() != null) {
                            benfitsTotalValue += dto.getValue();
                        }
                    }
                }

                dedectionsTotalValue = 0;
                createBenifitList2();
                createDeductionsList2();

                if (deductionsList != null && deductionsList.size() != 0) {
                    setDeductionsListSize(deductionsList.size());
                    setShowPagingBar(true);
                    for (ISalElementGuidesDTO dto : deductionsList) {
                        dedectionsTotalValue += dto.getValue();
                    }
                }
            totalSalary = benfitsTotalValue - dedectionsTotalValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End viewTableForEdit :: "+SharedUtils.getCurrentTimestamp().toString());
    }

    private java.util.Date getFromDate() throws ParseException {
        String fromDateStr = "01/" + monthKey + "/" + yearsKey;
        java.util.Date fromDate = new SimpleDateFormat("dd/M/yyyy").parse(fromDateStr);
        return fromDate;
    }
    private java.util.Date getSett_Date() throws ParseException {
        String settDateStr = "01/" + settMonthKey + "/" + settYearsKey;
        java.util.Date settDate = new SimpleDateFormat("dd/M/yyyy").parse(settDateStr);
        return settDate;
    }
    
    private java.util.Date getUntilDate() throws ParseException {
        String untilDateStr =
            String.valueOf(getlastDateofThisMonth(Integer.valueOf(monthKey), Integer.valueOf(yearsKey))) + "/" +
            monthKey + "/" + yearsKey;
        java.util.Date untilDate = new SimpleDateFormat("dd/M/yyyy").parse(untilDateStr);
        return untilDate;
    }
    public static final int getlastDateofThisMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        if (year > -1) {
            calendar.set(Calendar.YEAR, year);
        }
        if (month > -1) {
            month--;
            calendar.set(Calendar.MONTH, month);
        }
        return calendar.getActualMaximum(Calendar.DATE);
    }
    public void setYearsList(List<IBasicDTO> yearsList) {
        this.yearsList = yearsList;
    }

    public List<IBasicDTO> getYearsList() {
        return yearsList;
    }

    public void setYearsKey(String yearsKey) {
        this.yearsKey = yearsKey;
    }

    public String getYearsKey() {
        return yearsKey;
    }

    public void setMonthKey(String monthKey) {
        this.monthKey = monthKey;
    }

    public String getMonthKey() {
        return monthKey;
    }

    public void setMonthsList(List<IBasicDTO> monthsList) {
        this.monthsList = monthsList;
    }

    public List<IBasicDTO> getMonthsList() {
        return monthsList;
    }

    public void setBenfitsList(List<ISalElementGuidesDTO> benfitsList) {
        this.benfitsList = benfitsList;
    }

    public List<ISalElementGuidesDTO> getBenfitsList() {
        return benfitsList;
    }

    public void setDeductionsList(List<ISalElementGuidesDTO> deductionsList) {
        this.deductionsList = deductionsList;
    }

    public List<ISalElementGuidesDTO> getDeductionsList() {
        return deductionsList;
    }

    public void setBenfitsTotalValue(float benfitsTotalValue) {
        this.benfitsTotalValue = benfitsTotalValue;
    }

    public float getBenfitsTotalValue() {
        return benfitsTotalValue;
    }

    public void setDedectionsTotalValue(float dedectionsTotalValue) {
        this.dedectionsTotalValue = dedectionsTotalValue;
    }

    public float getDedectionsTotalValue() {
        return dedectionsTotalValue;
    }

    public void setTotalSalary(float totalSalary) {
        this.totalSalary = totalSalary;
    }

    public float getTotalSalary() {
        return totalSalary;
    }

    public void setBenfitsListSize(int benfitsListSize) {
        this.benfitsListSize = benfitsListSize;
    }

    public int getBenfitsListSize() {
        return benfitsListSize;
    }

    public void setDeductionsListSize(int deductionsListSize) {
        this.deductionsListSize = deductionsListSize;
    }

    public int getDeductionsListSize() {
        return deductionsListSize;
    }

    private void createBenifitList2() {
        benifitList2.clear();
        for (ISalElementGuidesDTO dto : getBenfitsList()) {
            SalEGDTO _salEGDTO = new SalEGDTO();
            if (dto.getDenarValue() == null || dto.getDenarValue().equals("")) {
                dto.setDenarValue("0");
            }
            if (dto.getFelsValue() == null || dto.getFelsValue().equals("")) {
                dto.setFelsValue("0");
            }
            _salEGDTO.setSalElementGuidesDTO(dto);
            benifitList2.add(_salEGDTO);
        }

    }

    /*CSC-15730 A.Nour*/

    private void createElmGuidesInsurRatiosMapForAdd() {
        List<List> elmGuideInsurElmList = null;
        /*CSC-15730 A.Nour*/
        for (ISalElementGuidesDTO dto : getBenfitsList()) {
            Long insurCode = 0L, dinarValue = 0L, felsValue = 0L;
            Double settValue = 0d, ratio = 0d;
            if (!elmGuidesInsurRatiosMap.containsKey(dto)) {
                String settDate = "01/" + monthKey + "/" + yearsKey;
                System.out.println("createBenifitList2 settDate = " + settDate);
                try {
                    elmGuideInsurElmList =
                            SalClientFactory.getSalEmpSettelmentsClient().getSalElementGuideRatio(dto, settDate);
                    if (elmGuideInsurElmList != null && elmGuideInsurElmList.size() > 0) {
                        elmGuidesInsurRatiosMap.put(dto, elmGuideInsurElmList);
                    }
                } catch (DataBaseException e) {
                } catch (SharedApplicationException e) {
                }
            }
        }
    }

    private void createElmGuidesInsurRatiosMapForEdit() {
        List<List> elmGuideInsurElmList = null;
        /*CSC-15730 A.Nour*/
        for (ISalElementGuidesDTO dto : getBenfitsList()) {
            Long insurCode = 0L, dinarValue = 0L, felsValue = 0L;
            Double settValue = 0d, ratio = 0d;
            if (!elmGuidesInsurRatiosMap.containsKey(dto)) {
                String settDate = "01/" + monthKey + "/" + yearsKey;
                System.out.println("createBenifitList2 settDate = " + settDate);
                try {
                    elmGuideInsurElmList =
                            SalClientFactory.getSalEmpSettelmentsClient().getSalElementGuideRatio(dto, settDate);
                    if (elmGuideInsurElmList != null && elmGuideInsurElmList.size() > 0) {
                        elmGuidesInsurRatiosMap.put(dto, elmGuideInsurElmList);
    //                        try {
    //                            if (dto.getDenarValue() != null && !dto.getDenarValue().equals(""))
    //                                dinarValue = Long.parseLong(dto.getDenarValue());
    //                            if (dto.getFelsValue() != null && !dto.getFelsValue().equals(""))
    //                                felsValue = Long.parseLong(dto.getFelsValue());
    //                            if (!dinarValue.equals(0L) || !felsValue.equals(0L)) {
    //                                settValue = Double.parseDouble(dinarValue + "." + felsValue);
    //                            }
    //                        } catch (NumberFormatException nfe) {
    //                            // TODO: Add catch code
    //                            nfe.printStackTrace();
    //                        }
    //                        if (!settValue.equals(0D)) {
    //                            List elmGuideInsurElm = null;
    //                            for (int i = 0; i < elmGuideInsurElmList.size(); i++) {
    //                                elmGuideInsurElm = (List)elmGuideInsurElmList.get(i);
    //                                ratio = (Double)elmGuideInsurElm.get(0);
    //                                insurCode = (Long)elmGuideInsurElm.get(1);
    //                                applyElmGuidesInsurRatiosMap(insurCode, ratio, BigDecimal.valueOf(settValue));
    //                            }
    //                        }
                    }
                } catch (DataBaseException e) {
                } catch (SharedApplicationException e) {
                }
            }
        }

    }
    /*CSC-15730 A.Nour*/


    private void applyElmGuidesInsurRatiosMap(Long insurCode, Double ratio, BigDecimal settValue) {
        for (ISalElementGuidesDTO dto : getDeductionsList()) {
            if (dto.getCode().getKey().equals(insurCode + "")) {
                BigDecimal autoSett =
                    (dto.getAutoSettelmentValue() != null) ? dto.getAutoSettelmentValue() : BigDecimal.ZERO;
                dto.setAutoSettelmentValue(autoSett.add((settValue.multiply(BigDecimal.valueOf(ratio))).divide(BigDecimal.valueOf(100L))));
            }
        }
    }

    public void applyAllElmGuidesInsurRatiosMaps() {
        
        if(isEmpUnderInsurance) 
        {
            List elmGuideInsurElmList = null, elmGuideInsurElm = null;
            Long insurCode = 0L, dinarValue = 0L, felsValue = 0L, dedCode = 0L;
            Double ratio = 0d;
            BigDecimal settValue = BigDecimal.ZERO;
            for (ISalElementGuidesDTO dto : getDeductionsList()) {
                dto.setAutoSettelmentValue(BigDecimal.ZERO);
                dedCode = Long.parseLong(dto.getCode().getKey());
                for (ISalElementGuidesDTO ben_dto : getBenfitsList()) {
                    settValue = BigDecimal.ZERO;
                    dinarValue = 0L;
                    felsValue = 0L;
                    ratio = 0d;
                    //Default if the settelement is with positive value, so the insurance should be with negative value
                    //as it will be taken from the emp
                    //otherwise if the settelement with negative value it means the emp will pay that value so the
                    //insurance should be payed back for him
                    Long sign = -1L;
                    if(ben_dto.getSignal()!=null && !ben_dto.getSignal().equals("") && ben_dto.getSignal().equals("-"))
                        sign *= -1L;
                    if (elmGuidesInsurRatiosMap.containsKey(ben_dto)) {
                        try {
                            if (ben_dto.getDenarValue() != null && !ben_dto.getDenarValue().equals(""))
                                dinarValue =  sign * Long.parseLong(ben_dto.getDenarValue());
                            if (ben_dto.getFelsValue() != null && !ben_dto.getFelsValue().equals(""))
                                felsValue = Long.parseLong(ben_dto.getFelsValue());
                            if (!dinarValue.equals(0L) || !felsValue.equals(0L)) {
                                settValue = BigDecimal.valueOf(Double.parseDouble(dinarValue + "." + felsValue));
                            }
                        } catch (NumberFormatException nfe) {
                            // TODO: Add catch code
                            nfe.printStackTrace();
                        }
                        if (!settValue.equals(BigDecimal.ZERO)) {
                            elmGuideInsurElmList = elmGuidesInsurRatiosMap.get(ben_dto);
                            for (int i = 0; i < elmGuideInsurElmList.size(); i++) {
                                elmGuideInsurElm = (List)elmGuideInsurElmList.get(i);
                                ratio = (Double)elmGuideInsurElm.get(0);
                                insurCode = (Long)elmGuideInsurElm.get(1);
                                if (dedCode.equals(insurCode)) {
                                    BigDecimal autoSett =
                                        (dto.getAutoSettelmentValue() != null) ? dto.getAutoSettelmentValue() :
                                        BigDecimal.ZERO;
                                    
                                    BigDecimal newAutoSett = BigDecimal.ZERO;
                                    newAutoSett = newAutoSett.add((settValue.multiply(BigDecimal.valueOf(ratio))).divide(BigDecimal.valueOf(100L)));

                                    
                                    
                                    civilID = ((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)((IEmpDecisionsDTO)this.dto).getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId();
                                    String sDate = "01/" + settMonthKey + "/" + settYearsKey;
                                    Double actualInsuranceSett = 0.0;
                                    try {
                                        actualInsuranceSett =
                                                SalClientFactory.getSalEmpSettelmentsClient().getEmpInsuranceValue(civilID, insurCode, sDate, newAutoSett.doubleValue());
                                    } catch (DataBaseException e) {
                                    } catch (SharedApplicationException e) {
                                    }
                                    System.out.println("actualInsuranceSett " + actualInsuranceSett);
                                    autoSett = autoSett.add(BigDecimal.valueOf(actualInsuranceSett));

                                    dto.setAutoSettelmentValue(autoSett);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    List<SalEGDTO> deductionsList2 = new ArrayList();

    private void createDeductionsList2() {
        deductionsList2.clear();
        for (ISalElementGuidesDTO dto : getDeductionsList()) {
            SalEGDTO _salEGDTO = new SalEGDTO();
            if (dto.getDenarValue() == null || dto.getDenarValue().equals("")) {
                dto.setDenarValue("0");
            }
            if (dto.getFelsValue() == null || dto.getFelsValue().equals("")) {
                dto.setFelsValue("0");
            }
            _salEGDTO.setSalElementGuidesDTO(dto);
            deductionsList2.add(_salEGDTO);
        }

    }

    public void setBenifitList2(List<SalEGDTO> benifitList2) {
        this.benifitList2 = benifitList2;
    }

    public List<SalEGDTO> getBenifitList2() {
        return benifitList2;
    }

    public void setDeductionsList2(List<SalEGDTO> deductionsList2) {
        this.deductionsList2 = deductionsList2;
    }

    public List<SalEGDTO> getDeductionsList2() {
        return deductionsList2;
    }

    private boolean checkCivilIdForInsurance(Long civilID) {
        boolean valid = false;
        try {
            valid = SalClientFactory.getSalElementGuidesClient().checkValidCIvilIDForInsuranceREG(civilID);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        return valid;
    }

    private void loadBenefitsDeductionsFromDataBase() {

        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        
      //  com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
         //   (com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.DecisionCycleEmployeesMaintain)evaluateValueBinding("settlementDecisionCycleEmployeesMaintainBean");
        
        benfitsList = new ArrayList<ISalElementGuidesDTO>();
        deductionsList = new ArrayList<ISalElementGuidesDTO>();
        editSearchDate = false;
//        if (currentLoadedDecision != null && currentLoadedDecision.getEmpDecisionsDTOList() != null &&
//            currentLoadedDecision.getEmpDecisionsDTOList().size() != 0) {
    //            IRegFinancialSearchDTO salSearchDTO = null;
            try {
    //                salSearchDTO =
    //                        RegClientFactory.getRegEmpDecisionDtlsClient().getRelatedEmpSalElements(decisionMaintainBean.getPageDTO());
    //                if (salSearchDTO.getTabrecSerials().size() > 0) {
                    IEmpDecisionsDTO empDecisionsDTO = dto;
               
                    Long parentTabrecSerial = empDecisionsDTO.getTabrecSerialRef();
                        
                    IRegFinancialSearchDTO salSearchDTOParent = new RegFinancialSearchDTO();
                    salSearchDTOParent.setCivilId(getCivilID());
                    List<Long> parentTabrecAsList = new ArrayList<Long>();
                    parentTabrecAsList.add(parentTabrecSerial);
                    salSearchDTOParent.setTabrecSerials(parentTabrecAsList);
                    List<IBasicDTO> parentList = null;
    //                        SalClientFactory.getSalEmpSettelmentsClient().getAllByCivilAndTabrecSerials(salSearchDTOParent);
                
                        ISalEmpSalaryQueryDTO salEmpSalaryQueryDTO = new SalEmpSalaryQueryDTO();
                        Long civilIdNotReal = ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId();
                        salEmpSalaryQueryDTO.setCivilId(civilIdNotReal);//getCivilID()); 
                        salEmpSalaryQueryDTO.setRealCivilId(getCivilID()); 
                        salEmpSalaryQueryDTO.setFromDate(getFromDate());
                        salEmpSalaryQueryDTO.setUntilDate(getUntilDate());
                        salEmpSalaryQueryDTO.setType("1");
                        salEmpSalaryQueryDTO.setYearCode(Long.valueOf(getYearsKey()));
                        salEmpSalaryQueryDTO.setSalaryMonth(Long.valueOf(getMonthKey()));
                        salEmpSalaryQueryDTO.setInsuranceTypes(insuranceTypes);
                        java.sql.Date settDate = new java.sql.Date(getFromDate().getTime());
                        //java.sql.Date settDate = new java.sql.Date(getSett_Date().getTime());
                        salEmpSalaryQueryDTO.setSettDate(settDate);
                        salEmpSalaryQueryDTO.setSettYearCode(Long.valueOf(getSettYearsKey()));
                        salEmpSalaryQueryDTO.setSettSalaryMonth(Long.valueOf(getSettMonthKey()));
                        Map empLists = SalClientFactory.getSalEmpSettelmentsClient().getBenfitsDeductsInsuranceSettListsForSettDec(salSearchDTOParent , salEmpSalaryQueryDTO);
                        
                        if(empLists.get("parentSettList") != null){
                            parentList = (List<IBasicDTO>)empLists.get("parentSettList");
                        }

                        if(empLists.get("detailsSettList") != null){
                            relatedRecordsToParent = (List<IBasicDTO>)empLists.get("detailsSettList");
                        }
                        if(empLists.get("benfitsList") != null){
                            benfitsList = (List<ISalElementGuidesDTO>)empLists.get("benfitsList");
                        }
                        if(empLists.get("deductsList") != null){
                            deductionsList = (List<ISalElementGuidesDTO>)empLists.get("deductsList");
                        }
                
                    if (parentList != null && parentList.size() > 0) {
    //                        Long parentSTLTRXCode =
    //                            ((ISalEmpSettelmentsEntityKey)((parentList.get(0)).getCode())).getStltrxCode();
                        java.util.Date stltrxDate = ((SalEmpSettelmentsDTO)parentList.get(0)).getStltrxDate();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(stltrxDate);
                        String stltrxMonthKey = Integer.toString(cal.get(Calendar.MONTH) + 1);
                        String stltrxYearKey = Integer.toString(cal.get(Calendar.YEAR));
                        setSettMonthKey(stltrxMonthKey);
                        setSettYearsKey(stltrxYearKey);

    //                        relatedRecordsToParent =
    //                                SalClientFactory.getSalEmpSettelmentsClient().getAllByParentSTLTRX(parentSTLTRXCode);
                        if (relatedRecordsToParent.size() > 0) {
                            List<Long> relatedRecordsStltrxCodes = new ArrayList<Long>();
                            initialListForBenefitsAndDeductions = new ArrayList<ISalEmpSettelmentsDTO>();
                            for (IBasicDTO elem : relatedRecordsToParent) {
                                ISalEmpSettelmentsDTO salEmpSettelmentsChildDTO = (SalEmpSettelmentsDTO)elem;
                                Double settValue = salEmpSettelmentsChildDTO.getSettelmentValue();
                                List<String> denarFelsSign = convertSettValueToDenarFelsSign(settValue);
                                if (denarFelsSign.size() > 0) {
                                    salEmpSettelmentsChildDTO.getSalElementGuidesDTO().setSignal(denarFelsSign.get(0));
                                    salEmpSettelmentsChildDTO.getSalElementGuidesDTO().setDenarValue(denarFelsSign.get(1));
                                    salEmpSettelmentsChildDTO.getSalElementGuidesDTO().setFelsValue(denarFelsSign.get(2));
                                }
                                //ISalElementGuidesDTO childSalElemntGuide = SalDTOFactory.createSalElementGuidesDTO();
                                //childSalElemntGuide = convertFromSalEmpSettDTOTOElmGuideDTO(childSalElemntGuide, salEmpSettelmentsChildDTO);
                                Long childSTLTRXCode = ((ISalEmpSettelmentsEntityKey)(elem.getCode())).getStltrxCode();

                                relatedRecordsStltrxCodes.add(childSTLTRXCode);
                                initialListForBenefitsAndDeductions.add(salEmpSettelmentsChildDTO);
                            }
                            /*if(relatedRecordsToParent.size() > 0){
                                    //initialListForBenefitsAndDeductions = new ArrayList<ISalEmpSettelmentsDTO>();
                                    //initialListForBenefitsAndDeductions.addAll(relatedRecordsToParent);
                                    updateBenefitsAndDeductionWithDataBaseValues(relatedRecordsToParent);
                                    // Update decuctionsList2 WithDatabaseValues
                                }*/

                            /*salSearchDTO.setStltrxCodeList(relatedRecordsStltrxCodes);
                                List<IBasicDTO> childsDetailsList = SalClientFactory.getSalEmpSettDetailsClient().getAllByStltrxCodeList(salSearchDTO);
                                for(IBasicDTO _dto : childsDetailsList){
                                    ISalEmpSettDetailsDTO EmpSettDetailsElement = (SalEmpSettDetailsDTO) _dto;
                                    //ISalElementGuidesDTO childSalElemntGuide = SalDTOFactory.createSalElementGuidesDTO();
                                    //childSalElemntGuide = convertFromElmGuideDTOToSalEmpSettDTO(salElmGuideDTO, salEmpSettelmentsChildDTO);
                                }*/
                        }
                    }


    //                }
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            } catch (ParseException e) {
            }
       // }

    }
    List<SalEGDTO> benifitList2 = new ArrayList();

    //    private void loadDeductionFromDataBase() {
    //    }

    public void setBenefitsListFromDataBase(List<ISalElementGuidesDTO> benefitsListFromDataBase) {
        this.benefitsListFromDataBase = benefitsListFromDataBase;
    }

    public List<ISalElementGuidesDTO> getBenefitsListFromDataBase() {
        return benefitsListFromDataBase;
    }

    public void setDeductionsListFromDataBase(List<ISalElementGuidesDTO> deductionsListFromDataBase) {
        this.deductionsListFromDataBase = deductionsListFromDataBase;
    }

    public List<ISalElementGuidesDTO> getDeductionsListFromDataBase() {
        return deductionsListFromDataBase;
    }
    
    private List<String> convertSettValueToDenarFelsSign(Double settValue) {
        List result = new ArrayList<String>();
        String _denar, _fels, _sign;
        String valueAsString = String.valueOf(settValue);
        if (valueAsString == null) {
            return result;
        }
        if (valueAsString.contains("-")) {
            _sign = "-";
            valueAsString = valueAsString.substring(1);
        } else {
            _sign = "+";
        }
        if (valueAsString.contains(".")) {
            int indexOfDot = valueAsString.indexOf('.');
            if (indexOfDot == 0) {
                _denar = "0";
                _fels = valueAsString;
            } else {
                _denar = valueAsString.substring(0, indexOfDot);
                _fels = valueAsString.substring(indexOfDot + 1);
            }
        } else {
            _denar = valueAsString;
            _fels = "0";
        }
        result.add(_sign);
        result.add(_denar);
        result.add(_fels);
        return result;

    }

    private void updateBenefitsAndDeductionWithDataBaseValues(List<IBasicDTO> _salEmpSettelmentsList) {
        //Loop on BenefitsList
        for (ISalElementGuidesDTO benefitsElement : benfitsList) {
            String benefitsElementKey = benefitsElement.getCode().getKey();
            for (IBasicDTO _elem : _salEmpSettelmentsList) {
                ISalEmpSettelmentsDTO salEmpSettElement = (SalEmpSettelmentsDTO)_elem;
                String salEmpSettElementKey = salEmpSettElement.getSalElementGuidesDTO().getCode().getKey();

                if (salEmpSettElementKey.equals(benefitsElementKey)) {
                    // Update Values
                    benefitsElement.setSignal(salEmpSettElement.getSalElementGuidesDTO().getSignal());
                    benefitsElement.setDenarValue(salEmpSettElement.getSalElementGuidesDTO().getDenarValue());
                    benefitsElement.setFelsValue(salEmpSettElement.getSalElementGuidesDTO().getFelsValue());
                }
            }
        }

        //Loop on DeductionsList
        ISalEmpSalaryElementsDTO dedSalEmpSalaryElementsDTO = null;
        String dedType = null;
        String deductionsElementKey = null, salEmpSettElementKey = null;
        BigDecimal autoSett = BigDecimal.ZERO;
        for (ISalElementGuidesDTO deductionsElement : deductionsList) {
            deductionsElementKey = deductionsElement.getCode().getKey();
            dedType = (deductionsElement.getSalElementTypesDTO().getCode()).getKey();
            for (IBasicDTO _elem : _salEmpSettelmentsList) {
                ISalEmpSettelmentsDTO salEmpSettElement = (SalEmpSettelmentsDTO)_elem;
                dedSalEmpSalaryElementsDTO = salEmpSettElement.getSalEmpSalaryElementsDTO();
                salEmpSettElementKey = salEmpSettElement.getSalElementGuidesDTO().getCode().getKey();

                if ( ( ( (dedSalEmpSalaryElementsDTO == null &&  isInsuranceDed(dedType) ) || !  isInsuranceDed(dedType) ) ) && salEmpSettElementKey.equals(deductionsElementKey)) {
                    // Update Values
                    deductionsElement.setSignal(salEmpSettElement.getSalElementGuidesDTO().getSignal());
                    deductionsElement.setDenarValue(salEmpSettElement.getSalElementGuidesDTO().getDenarValue());
                    deductionsElement.setFelsValue(salEmpSettElement.getSalElementGuidesDTO().getFelsValue());
                }
                else if ( (dedSalEmpSalaryElementsDTO != null &&  isInsuranceDed(dedType) ) && salEmpSettElementKey.equals(deductionsElementKey)) {
                    autoSett = (deductionsElement.getAutoSettelmentValue() == null) ? BigDecimal.ZERO : deductionsElement.getAutoSettelmentValue();
                    deductionsElement.setAutoSettelmentValue(autoSett.add( BigDecimal.valueOf( salEmpSettElement.getSettelmentValue()) ) );
                }
            }
        }

    }

    private void loadDataFromDatabase() {
        try {
            System.out.println("start loadDataFromDatabase :: "+SharedUtils.getCurrentTimestamp().toString());

            DecisionCycleMaintainBean decisionMaintainBean =
                (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
            //        DecisionEmployeesModel decisionEmployeesModelSessionBean =
            //            (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
            //
            if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT ||
                decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_VIEW_ONLY) {
                // Load Data Here
                //IDecisionsDTO CurrentDecision = (IDecisionsDTO)DAO().getById(decisionDTO.getCode());
                currentLoadedDecision = ((IDecisionsDTO)decisionMaintainBean.getPageDTO());
//                if (currentLoadedDecision != null && currentLoadedDecision.getEmpDecisionsDTOList() != null &&
//                    currentLoadedDecision.getEmpDecisionsDTOList().size() != 0) {
//                    List<IEmpDecisionsDTO> list = (List)currentLoadedDecision.getEmpDecisionsDTOList();
    
                    /** updated by A.Nour because civil come from IEmpDecisionsEntityKey is serial not real civil Id */
                    this.loadBenefitsDeductionsFromDataBase();
                    viewTableForEdit();//this.viewTable();
                    if (relatedRecordsToParent.size() > 0) {
                        //initialListForBenefitsAndDeductions = new ArrayList<ISalEmpSettelmentsDTO>();
                        //initialListForBenefitsAndDeductions.addAll(relatedRecordsToParent);
                        updateBenefitsAndDeductionWithDataBaseValues(relatedRecordsToParent);
                        // Update decuctionsList2 WithDatabaseValues
                    }
                    createElmGuidesInsurRatiosMapForEdit();
                    //this.loadDeductionFromDataBase();

                }
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End loadDataFromDatabase :: "+SharedUtils.getCurrentTimestamp().toString());


    }
    public void updateStatusBeforeModification() {
        if (initialListForBenefitsAndDeductions == null || initialListForBenefitsAndDeductions.size() == 0) {
            return;
        }
        //Loop on BenefitsList
        for (ISalElementGuidesDTO benefitsElement : benfitsList) {
            String benefitsElementKey = benefitsElement.getCode().getKey();
            for (IBasicDTO _elem : initialListForBenefitsAndDeductions) {
                ISalEmpSettelmentsDTO salEmpSettElement = (SalEmpSettelmentsDTO)_elem;
                String salEmpSettElementKey = salEmpSettElement.getSalElementGuidesDTO().getCode().getKey();

                if (salEmpSettElementKey.equals(benefitsElementKey)) {
                    // Update StatusFlag
                    if (benefitsElement.getDenarValue() != null && !benefitsElement.getDenarValue().equals("0")) {
                        benefitsElement.setStatusFlag(IConstants.UPDATED_ITEM);
                    } else if (benefitsElement.getFelsValue() != null && !benefitsElement.getFelsValue().equals("0")) {
                        benefitsElement.setStatusFlag(IConstants.UPDATED_ITEM);
                    } else {
                        // Removed Item
                        benefitsElement.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                    }

                }
            }
        }

        //Loop on DeductionsList
        for (ISalElementGuidesDTO deductionsElement : deductionsList) {
            String deductionsElementKey = deductionsElement.getCode().getKey();
            for (IBasicDTO _elem : initialListForBenefitsAndDeductions) {
                ISalEmpSettelmentsDTO salEmpSettElement = (SalEmpSettelmentsDTO)_elem;
                String salEmpSettElementKey = salEmpSettElement.getSalElementGuidesDTO().getCode().getKey();

                if (salEmpSettElementKey.equals(deductionsElementKey)) {
                    // Update StatusFlag
                    if (deductionsElement.getDenarValue() != null && !deductionsElement.getDenarValue().equals("0")) {
                        deductionsElement.setStatusFlag(IConstants.UPDATED_ITEM);
                    } else if (deductionsElement.getFelsValue() != null &&
                               !deductionsElement.getFelsValue().equals("0")) {
                        deductionsElement.setStatusFlag(IConstants.UPDATED_ITEM);
                    } else {
                        // Removed Item
                        deductionsElement.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                    }
                }
            }
        }
    }
    public void removeAllPreviousSettlements() {
        // remove All Lists
        //add Them First to removed List
        //IDecisionsDTO dtoForSave = (IDecisionsDTO)getPageDTO();
    //        if (removedSalElementGuideList == null || removedSalElementGuideList.size() == 0) {
    //            removedSalElementGuideList = new ArrayList<IBasicDTO>();
    //            removedSalElementGuideList.addAll(getBenfitsList());
    //            removedSalElementGuideList.addAll(getDeductionsList());
            /*benfitsList.clear();
            deductionsList.clear();
            benifitList2.clear();
            deductionsList2.clear();*/
            setBenfitsList(new ArrayList<ISalElementGuidesDTO>());
            setBenifitList2(new ArrayList<SalEGDTO>());
            setDeductionsList(new ArrayList<ISalElementGuidesDTO>());
            setDeductionsList2(new ArrayList<SalEGDTO>());
            setShowPagingBar(false);
            setDataReadyToBeRemoved(true);
            editSearchDate = true;
    //        }
        /*dtoForSave.setSalElmGuidesDTOList(removedSalElementGuideList);
        dtoForSave.setYearCode(getYearsKey());
        dtoForSave.setYearMonth(getMonthKey());*/
    }

 
    public int getCurrentListSize() {
        int currentListSize = 0;

        if (getBenifitList2() != null) {
            currentListSize = getBenifitList2().size();
        }

        return currentListSize;
    }

    public void setShowErrMsg(boolean showErrMsg) {
        this.showErrMsg = showErrMsg;
    }

    public boolean isShowErrMsg() {
        return showErrMsg;
    }

    public void setInitialListForBenefitsAndDeductions(List<ISalEmpSettelmentsDTO> initialListForBenefitsAndDeductions) {
        this.initialListForBenefitsAndDeductions = initialListForBenefitsAndDeductions;
    }

    public List<ISalEmpSettelmentsDTO> getInitialListForBenefitsAndDeductions() {
        return initialListForBenefitsAndDeductions;
    }
    
    
    /*CSC-15730 A.Nour*/

    public void setElmGuidesInsurRatiosMap(Map<IBasicDTO, List<List>> elmGuidesInsurRatiosMap) {
        this.elmGuidesInsurRatiosMap = elmGuidesInsurRatiosMap;
    }

    public Map<IBasicDTO, List<List>> getElmGuidesInsurRatiosMap() {
        return elmGuidesInsurRatiosMap;
    }
    /*CSC-15730 A.Nour*/
    public boolean validateSattlementListExist() {
        if ((benfitsList != null && benfitsList.size() != 0) ||
            (deductionsList != null && deductionsList.size() != 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setShowPagingBar(boolean showPagingBar) {
        this.showPagingBar = showPagingBar;
    }

    public boolean isShowPagingBar() {
        return showPagingBar;
    }
    
    public void setRemovedSalElementGuideList(List<IBasicDTO> removedSalElementGuideList) {
        this.removedSalElementGuideList = removedSalElementGuideList;
    }

    public List<IBasicDTO> getRemovedSalElementGuideList() {
        return removedSalElementGuideList;
    }

    public void setDisableMonthAndYear(boolean disableMonthAndYear) {
        this.disableMonthAndYear = disableMonthAndYear;
    }
    public void setDataReadyToBeRemoved(boolean dataReadyToBeRemoved) {
        this.dataReadyToBeRemoved = dataReadyToBeRemoved;
    }

    public boolean isDataReadyToBeRemoved() {
        return dataReadyToBeRemoved;
    }
    
    public boolean isDisableMonthAndYear() {
        if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT &&
            !isDataReadyToBeRemoved())
            return true;
        else
            return false;
    }
  
    

    private boolean validateSettDate() {
        try {
            Long rCivilId=getEmployeesDTO().getRealCivilId();
            Long rowCount =
                SalClientFactory.getSalEmpMonSalariesClient().checkEmpSalaryCalculated(rCivilId, Long.valueOf(settYearsKey),
                                                                                       Long.valueOf(settMonthKey));
            if (rowCount > 0) {
                String errMsg = getSharedUtil().messageLocator(BUNDLE, "sett_date_err");
                getSharedUtil().setErrMsgValue(errMsg);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }
    
    public void loadYearsList() {
        try {
            yearsList = (List)InfClientFactory.getYearsClient().getCodeName();
        } catch (Exception e) {
            yearsList = new ArrayList<IBasicDTO>();
        }
    }

    public void LoadMonthsList() {
        try {
            monthsList = (List)InfClientFactory.getMonthsClient().getAll();
        } catch (Exception e) {
            monthsList = new ArrayList<IBasicDTO>();
        }
    }

    public void setSearchCurrentMonthYear() {
        Calendar currentDate = Calendar.getInstance();
        monthKey = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        yearsKey = Integer.toString(currentDate.get(Calendar.YEAR));

        System.out.println(monthKey + "--" + yearsKey);
    }

    public void initiateBeanOnce() {
         loadYearsList();
          LoadMonthsList();
    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }    
    public void setEditSearchDate(boolean editSearchDate) {
        this.editSearchDate = editSearchDate;
    }

    public boolean isEditSearchDate() {
        return editSearchDate;
    }
    
    public void setCurrentLoadedDecision(IDecisionsDTO currentLoadedDecision) {
        this.currentLoadedDecision = currentLoadedDecision;
    }

    public IDecisionsDTO getCurrentLoadedDecision() {
        return currentLoadedDecision;
    }


    public void setRelatedRecordsToParent(List<IBasicDTO> relatedRecordsToParent) {
        this.relatedRecordsToParent = relatedRecordsToParent;
    }

    public List<IBasicDTO> getRelatedRecordsToParent() {
        return relatedRecordsToParent;
    }

    public void setSettMonthKey(String settMonthKey) {
        this.settMonthKey = settMonthKey;
    }

    public String getSettMonthKey() {
        return settMonthKey;
    }

    public void setSettYearsKey(String settYearsKey) {
        this.settYearsKey = settYearsKey;
    }
    public String getSettYearsKey() {
        return settYearsKey;
    }
    

    public void setSettCurrentMonthYear() {
        Calendar currentDate = Calendar.getInstance();
        settMonthKey = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        settYearsKey = Integer.toString(currentDate.get(Calendar.YEAR));

        System.out.println(settMonthKey + "--" + settYearsKey);
    }

    public void setDto(IEmpDecisionsDTO dto) {
        this.dto = dto;
    }

    public IEmpDecisionsDTO getDto() {
        return dto;
    }

    public void setMaintainMode(int maintainMode) {
        this.maintainMode = maintainMode;
    }

    public int getMaintainMode() {
        return maintainMode;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app=new AppMainLayout();
        app.showAddeditPage();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowCustomDiv1(true);
        return app;
    }
   
   
    private boolean validate(){
  
        if(!validateSettDate()){
                return false;
        }
        
        
        if(!validateSettlements()){
            return false;
            }
        
        return true;
        } 
    
    
    private boolean validateSettlements() {
        boolean valid = false;
        if (benfitsList != null && !benfitsList.isEmpty()) {
            for (ISalElementGuidesDTO dto : benfitsList) {
                if ((dto.getDenarValue() != null && !dto.getDenarValue().trim().equals("") &&
                     !dto.getDenarValue().trim().equals("0")) ||
                    (dto.getFelsValue() != null && !dto.getFelsValue().trim().equals("") &&
                     !dto.getFelsValue().trim().equals("0"))) {
                    valid = true;
                    break;
                }
            }
        }

        if (!valid && deductionsList != null && !deductionsList.isEmpty()) {
            for (ISalElementGuidesDTO dto : deductionsList) {
                if ((dto.getDenarValue() != null && !dto.getDenarValue().trim().equals("") &&
                     !dto.getDenarValue().trim().equals("0")) ||
                    (dto.getFelsValue() != null && !dto.getFelsValue().trim().equals("") &&
                     !dto.getFelsValue().trim().equals("0"))) {
                    valid = true;
                    break;
                }
            }
        }

        if (!valid) {
            String errMsg = getSharedUtil().messageLocator(BUNDLE, "sett_err_msg");
            getSharedUtil().setErrMsgValue(errMsg);
        }

        return valid;
    }

    private void updateDetailList(){
        
            List<IBasicDTO> allElementGuideList = new ArrayList<IBasicDTO>();
            List<ISalElementGuidesDTO> bnfList = new ArrayList<ISalElementGuidesDTO>();
            List<ISalElementGuidesDTO>   ddList = new ArrayList<ISalElementGuidesDTO>();
            List<SalEGDTO> benList = new ArrayList<SalEGDTO>();
            List<SalEGDTO> dedList = new ArrayList<SalEGDTO>();
            if (benifitList2 != null && !benifitList2.isEmpty()) {
                allElementGuideList.addAll(benfitsList);
                benList = benifitList2;
                bnfList=benfitsList;
               
            
            }
            if (deductionsList2 != null && !deductionsList2.isEmpty()) {

                allElementGuideList.addAll(deductionsList);
                ddList=deductionsList;
             
                dedList = deductionsList2;
            }
            dto.setBenefitList(benList);
            dto.setDeductList(dedList);
            
            dto.setBenList(bnfList);
            dto.setDedList(ddList);
            
            dto.setSalElmGuideDTOList(allElementGuideList);
            dto.setSettMonth(Long.valueOf(settMonthKey));
            dto.setSettYear(Long.valueOf(settYearsKey));
//            if (maintainMode == 0) {
                dto.setMonth(Long.valueOf(monthKey));
                dto.setYear(Long.valueOf(yearsKey));
//            }

            com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
                (com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.DecisionCycleEmployeesMaintain)evaluateValueBinding("settlementDecisionCycleEmployeesMaintainBean");
            decisionCycleEmployeesMaintain.updateCurrentDetail(dto);
        
        }
    public String saveSett() {

        try {
            if(!validate()){
                return null;
                }
            updateDetailList();
            return BACK_NAV_CASE;
        } catch (Exception nfe) {
            // TODO: Add catch code
            nfe.printStackTrace();
        }
        return null;
    }
   
    public String back() {
     
        try {
            //updateDetailList();
            return BACK_NAV_CASE;
        } catch (Exception nfe) {
            // TODO: Add catch code
            nfe.printStackTrace();
        }
        return null;
    }

    public void setIsEmpUnderInsurance(Boolean isEmpUnderInsurance) {
        this.isEmpUnderInsurance = isEmpUnderInsurance;
    }

    public Boolean getIsEmpUnderInsurance() {
        return isEmpUnderInsurance;
    }
}
