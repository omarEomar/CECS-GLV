package com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle;


//import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionExcute.DecisionExcuteListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionRevision.DecisionRevisionListBean;

//import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionExcute.DecisionExcuteListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionRevision.DecisionRevisionListBean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.exception.NoBankAccountData;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.IRegulationStatusClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.exception.DecionNumberYearTypeAlreadyAddedBefor;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle.details.DecisionCycleEmployeesMaintain;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle.details.DecisionCycleMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


public class DecisionCycleMaintainBean extends ManyToManyMaintainBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BUNDLE = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
    private static final String BEAN_NAME = "financeDecisionCycleMaintainBean";
    private static final String INTEGRATION_PAGE_SUFFIX = "-integration";
    private Map<String, Object> detailsSavedStates;
    String finishNavigationCase = null;
    private boolean cancelDecisionMode;
    private boolean copyDecisionWithEmployeesMode = false;
//    private boolean _reviewMode = false;
    private String lisBeanName = null;
    private Long currentStag = 1L;
    private boolean showOnly = false;
    private int decnumyearTypeAddedBefor=0;
    private int noBankAccountData=0;
    private List months;
    private String month;
    private List years;
    private String year;

    SharedUtilBean sharedUtil = getSharedUtil();
   
    private String backNavCase = null;
    private Object[] savedDataObjects = new Object[1];
    private String backMethodName;
    
    private String multiOrSingleEmps = OneElementMultiEmps;
    public static final String OneElementMultiEmps = "1";
    public static final String OneEmpMultiElements = "0";
    
    public DecisionCycleMaintainBean() {
        //TODO change clientName
        super.setClient(RegClientFactory.getDecisionsClient());

    
            setCurrentStep("financedecisionadd");
            
            setNextNavigationCase("financedecisioncycleemployeesmaintain");
            setFinishNavigationCase("financialdecisionslist");
            //            setFinishNavigationCase("decisionlist");
    
    }

    public static DecisionCycleMaintainBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (DecisionCycleMaintainBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
    }
   
    public static boolean isIntegrationPage() {
        try {
            String url =
                ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
            return url != null && url.contains(INTEGRATION_PAGE_SUFFIX);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNavigationCase(String mapKey) {
        String nc = super.getNavigationCase(mapKey);
        if (isCancelDecisionMode()) {
            //nc += "cancel";
        }
        if (getMaintainMode() == 1) {
            if (isIntegrationPage()) {
                nc = nc.substring(0, nc.indexOf("-")) + "edit" + INTEGRATION_PAGE_SUFFIX;
            } else {
                nc += "edit";
            }
        }


        return nc;
    }

    public boolean isRenderFinish() {
//        if (copyDecisionWithEmployeesMode)
//            return true;
//        else
            return true;
    }

    public boolean isCancelDecisionMode() {

        return false;
//
//        if (getPageDTO() != null &&
//            (((IDecisionsDTO)getPageDTO()).getDecisionsDTOList() != null && ((IDecisionsDTO)getPageDTO()).getDecisionsDTOList().size() !=
//             0)) {
//
//            DecisionListBean decisionListBean = (DecisionListBean)evaluateValueBinding("decisionListBean");
//
//            if (decisionListBean.isCancelDescisionFlag() == false) {
//
//                setCurrentStep("financedecisionadd");
//                setNextNavigationCase("decisionemployeesmaintain");
//            } else if (decisionListBean.isCancelDescisionFlag() == true && cancelDecisionMode == false) {
//                setCurrentStep("canceldecisionadd");
//                setNextNavigationCase("decisionreferencesmaintain");
//                setFinishNavigationCase("decisionlist");
//                cancelDecisionMode = true;
//            }
//
//            //                if (getCurrentStep().equals("canceldecisionadd")){
//            //                    if(isIntegrationPage()){
//            //                        setNextNavigationCase("decisionreferencesmaintain"+INTEGRATION_PAGE_SUFFIX);
//            //                    }else{
//            //                        setNextNavigationCase("decisionreferencesmaintain");
//            //                    }
//            //                    cancelDecisionMode = true;
//            //                }
//
//            //                if (!getCurrentStep().equals("cancelreferencesadd")){
//            //                    setCurrentStep("canceldecisionadd");
//            //                    if(isIntegrationPage()){
//            //                        setNextNavigationCase("decisionreferencesmaintain"+INTEGRATION_PAGE_SUFFIX);
//            //                    }else{
//            //                        setNextNavigationCase("decisionreferencesmaintain");
//            //                    }
//            //                    cancelDecisionMode = true;
//            //                } else{
//            //                    setNextNavigationCase(null);
//            //                    if(isIntegrationPage()){
//            //                        setPreviousNavigationCase("decisionmaindatacancel"+INTEGRATION_PAGE_SUFFIX);
//            //                    }else{
//            //                        setPreviousNavigationCase("decisionmaindatacancel");
//            //                    }
//            //                }
//
//        }
//
//        return cancelDecisionMode;
    }



    public boolean isAddDecisionMode() {
//        return getMaintainMode() == 0 && !isCancelDecisionMode();
        return true;
    }

    public IntegrationBean getIntegrationBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IntegrationBean)app.createValueBinding("#{integrationBean}").getValue(ctx);
    }

    public String finish() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        
        DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
            (DecisionCycleEmployeesMaintain)evaluateValueBinding("financeDecisionCycleEmployeesMaintainBean");
        if(decisionCycleEmployeesMaintain.validate()){
        if (getPageDTO().getCode() == null) {
            getPageDTO().setCode(RegEntityKeyFactory.createDecisionsEntityKey());
        }
        
        
        IDecisionsDTO dtoForSave = (IDecisionsDTO)getPageDTO();
            dtoForSave.setViewFlag(Long.parseLong(multiOrSingleEmps));
            Calendar c = Calendar.getInstance();
            c.setTime(dtoForSave.getDecMerToDate());
            int merMonth = c.get(c.MONTH) + 1;
            int merYear = c.get(c.YEAR);
        
        if(getMaintainMode() == this.MAINTAIN_MODE_ADD){
        dtoForSave.setEmpDecisionsDTOList(decisionCycleEmployeesMaintain.getCurrentDisplayDetails());



                for (IBasicDTO dto : dtoForSave.getEmpDecisionsDTOList()) {
                    IEmpDecisionsDTO empDecisionDto = (IEmpDecisionsDTO)dto;

                    empDecisionDto.setSalElmGuideDTOList(decisionCycleEmployeesMaintain.getCurrentSalVariedElmGuideDTOList());

                    if (multiOrSingleEmps.equals(OneEmpMultiElements)) {
                        empDecisionDto.setValueAsFloat(decisionCycleEmployeesMaintain.getTotalValue());
                    } else {
                        empDecisionDto.setValueAsFloat(calcValue(empDecisionDto.getDenarValue(),
                                                                 empDecisionDto.getFelsValue()));
                        ISalElementGuidesEntityKey ek =
                            SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(decisionCycleEmployeesMaintain.getSelectedElementGuideCode()));
                        ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
                        salDto.setCode(ek);
                        salDto.setValue(empDecisionDto.getValueAsFloat());
                        empDecisionDto.setElementGuidesDTO(salDto);
                    }

                    empDecisionDto.setYear(Long.valueOf(String.valueOf(merYear)));
                    empDecisionDto.setMonth(Long.valueOf(String.valueOf(merMonth)));
                    //            empDecisionDto.setYear(Long.valueOf(decisionCycleEmployeesMaintain.getYear()));
                    //            empDecisionDto.setMonth(Long.valueOf(decisionCycleEmployeesMaintain.getMonth()));
                    // Long vCivilId = ((IEmployeesEntityKey)empDecisionDto.getEmployeesDTO().getCode()).getCivilId();
                    //            try{
                    //                if(vCivilId != null){
                    //                    IEmployeesDTO employeesDTO =
//                                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(vCivilId);    
                    //                    empDecisionDto.setMinCode(employeesDTO.getMinCode());
                    //                }
//                
                    //            }
                    //            catch(Exception e){
                    //                e.printStackTrace();
//            } 
                }
                setPageDTO(dtoForSave);
            }
        else if(getMaintainMode() == this.MAINTAIN_MODE_EDIT){
                dtoForSave.setEmpDecisionsDTOList(decisionCycleEmployeesMaintain.getCurrentDetails());        
                
                for(IBasicDTO dto: dtoForSave.getEmpDecisionsDTOList()){
                IEmpDecisionsDTO empDecisionDto = (IEmpDecisionsDTO)dto;
                    
                empDecisionDto.setSalElmGuideDTOList(decisionCycleEmployeesMaintain.getCurrentSalVariedElmGuideDTOList());
                
                    

                    if (multiOrSingleEmps.equals(OneEmpMultiElements)) {
                        empDecisionDto.setValueAsFloat(decisionCycleEmployeesMaintain.getTotalValue());
                        empDecisionDto.setStatusFlag(IConstants.UPDATED_ITEM);
                    } else {
                        Float value=calcValue(empDecisionDto.getDenarValue() ,empDecisionDto.getFelsValue());
                        Float originalValue=calcValue(empDecisionDto.getDenarValueOriginal() ,empDecisionDto.getFelsValueOriginal());  
                        if (empDecisionDto.getStatusFlag()==null  &&
                            !value.equals(originalValue)) {
                            empDecisionDto.setStatusFlag(IConstants.UPDATED_ITEM);
                        }
                        empDecisionDto.setValueAsFloat(calcValue(empDecisionDto.getDenarValue(),
                                                                 empDecisionDto.getFelsValue()));
                        if( decisionCycleEmployeesMaintain.getSelectedElementGuideCode() != null && !decisionCycleEmployeesMaintain.getSelectedElementGuideCode().isEmpty()){
                            ISalElementGuidesEntityKey ek = SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(decisionCycleEmployeesMaintain.getSelectedElementGuideCode()));
                            ISalElementGuidesDTO salDto = SalDTOFactory.createSalElementGuidesDTO();
                            salDto.setCode(ek);
                            salDto.setValue(empDecisionDto.getValueAsFloat());
                            try {
                                dtoForSave.setElemGuideCode(Long.parseLong(salDto.getCode().getKey()));
                            } catch (NumberFormatException nfe) {
                                // TODO: Add catch code
                                nfe.printStackTrace();
                            }
                        }
                       
                        Long newElmGuideCode = dtoForSave.getElemGuideCode();
                        Long oldElmGuideCode = dtoForSave.getOldElemGuideCode();
                        System.out.println("finish() newElmGuideCode = "+newElmGuideCode+" , oldElmGuideCode = "+oldElmGuideCode);
                        
                    }

                
//                    System.out.println(empDecisionDto.getDenarValue());
//                    System.out.println(empDecisionDto.getFelsValue());
                    
                    
//                empDecisionDto.setElementGuidesDTO(salDto);
   //                 System.out.println("F:"+value);
//                empDecisionDto.setValueAsFloat(value);
                    
                empDecisionDto.setYear(Long.valueOf(String.valueOf(merYear)));
                empDecisionDto.setMonth(Long.valueOf(String.valueOf(merMonth)));   
//                empDecisionDto.setYear(Long.valueOf(decisionCycleEmployeesMaintain.getYear()));
//                empDecisionDto.setMonth(Long.valueOf(decisionCycleEmployeesMaintain.getMonth()));
              //  Long vCivilId = ((IEmpDecisionsEntityKey)empDecisionDto.getCode()).getCivilId();
//                try{
//                    if(vCivilId != null){
//                        IEmployeesDTO employeesDTO =
//                                            (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(vCivilId);
//                        if (employeesDTO != null) {
//                            empDecisionDto.setMinCode(employeesDTO.getMinCode());
//                        }
//                    }
//                    
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                } 
                }
           } 
            return defaultAddDecisoin();
        }

        return null;
    }
    
    public void changeEmployeeElementsMode(){
        DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
            (DecisionCycleEmployeesMaintain)evaluateValueBinding("financeDecisionCycleEmployeesMaintainBean");
        decisionCycleEmployeesMaintain.setSelectedElementGuideCode("");
        decisionCycleEmployeesMaintain.setSelectedElementGuideName("");
        decisionCycleEmployeesMaintain.setDinarValue("");
        decisionCycleEmployeesMaintain.setFelsValue("");
        decisionCycleEmployeesMaintain.setTotalValue(0F);
        decisionCycleEmployeesMaintain.setEmpFullName("");
        decisionCycleEmployeesMaintain.setCivilID(null);
        decisionCycleEmployeesMaintain.setCurrentSalVariedElmGuideDTOList(new ArrayList());
        decisionCycleEmployeesMaintain.setCurrentDisplayedSalVariedElmGuideDTOList(new ArrayList());
        decisionCycleEmployeesMaintain.reSetData();
    }
    
    public String savePartially() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        
        DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
            (DecisionCycleEmployeesMaintain)evaluateValueBinding("financeDecisionCycleEmployeesMaintainBean");
        if(decisionCycleEmployeesMaintain.validate()){
        if (getPageDTO().getCode() == null) {
            getPageDTO().setCode(RegEntityKeyFactory.createDecisionsEntityKey());
        }
        
        
        IDecisionsDTO dtoForSave = (IDecisionsDTO)getPageDTO();
        dtoForSave.setViewFlag(Long.parseLong(multiOrSingleEmps));
            Calendar c = Calendar.getInstance();
            c.setTime(dtoForSave.getDecMerToDate());
            int merMonth = c.get(c.MONTH) + 1;
            int merYear = c.get(c.YEAR);
        
        if(getMaintainMode() == this.MAINTAIN_MODE_ADD){
        dtoForSave.setEmpDecisionsDTOList(decisionCycleEmployeesMaintain.getCurrentDisplayDetails());
//        ISalElementGuidesEntityKey ek = SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(decisionCycleEmployeesMaintain.getSelectedElementGuideCode()));
//        ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
//        salDto.setCode(ek);
        for(IBasicDTO dto: dtoForSave.getEmpDecisionsDTOList()){
            IEmpDecisionsDTO empDecisionDto = (IEmpDecisionsDTO)dto;
            //            empDecisionDto.setElementGuidesDTO(salDto);
            //            empDecisionDto.setValueAsFloat(calcValue(empDecisionDto.getDenarValue() ,empDecisionDto.getFelsValue()));
            empDecisionDto.setSalElmGuideDTOList(decisionCycleEmployeesMaintain.getCurrentDisplayedSalVariedElmGuideDTOList());
            if (decisionCycleEmployeesMaintain.getCurrentDisplayedSalVariedElmGuideDTOList() != null &&
                decisionCycleEmployeesMaintain.getCurrentDisplayedSalVariedElmGuideDTOList().size() > 1) {
                empDecisionDto.setValueAsFloat(decisionCycleEmployeesMaintain.getTotalValue());
            } else {
                empDecisionDto.setValueAsFloat(calcValue(empDecisionDto.getDenarValue(),
                                                         empDecisionDto.getFelsValue()));
            }
           
            empDecisionDto.setYear(Long.valueOf(String.valueOf(merYear)));
            empDecisionDto.setMonth(Long.valueOf(String.valueOf(merMonth)));
//            empDecisionDto.setYear(Long.valueOf(decisionCycleEmployeesMaintain.getYear()));
//            empDecisionDto.setMonth(Long.valueOf(decisionCycleEmployeesMaintain.getMonth()));
           // Long vCivilId = ((IEmployeesEntityKey)empDecisionDto.getEmployeesDTO().getCode()).getCivilId();
    //            try{
    //                if(vCivilId != null){
    //                    IEmployeesDTO employeesDTO =
    //                                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(vCivilId);
    //                    empDecisionDto.setMinCode(employeesDTO.getMinCode());
    //                }
    //
    //            }
    //            catch(Exception e){
    //                e.printStackTrace();
    //            }
        }
          setPageDTO(dtoForSave);
        }
        else if(getMaintainMode() == this.MAINTAIN_MODE_EDIT){

                dtoForSave.setEmpDecisionsDTOList(decisionCycleEmployeesMaintain.getCurrentDetails());
                
                ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
                
                if (decisionCycleEmployeesMaintain.getSelectedElementGuideCode() != null &&
                    !decisionCycleEmployeesMaintain.getSelectedElementGuideCode().isEmpty()) {

                    ISalElementGuidesEntityKey ek =
                        SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(decisionCycleEmployeesMaintain.getSelectedElementGuideCode()));
                    salDto.setCode(ek);

                    try {
                        dtoForSave.setElemGuideCode(Long.parseLong(decisionCycleEmployeesMaintain.getSelectedElementGuideCode()));
                    } catch (NumberFormatException nfe) {
                        // TODO: Add catch code
                        nfe.printStackTrace();
                    }
                }
               Long newElmGuideCode = dtoForSave.getElemGuideCode();
               Long oldElmGuideCode = dtoForSave.getOldElemGuideCode();
               System.out.println("savePartially() newElmGuideCode = "+newElmGuideCode+" , oldElmGuideCode = "+oldElmGuideCode);
                
//                ISalElementGuidesEntityKey ek = SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(decisionCycleEmployeesMaintain.getSelectedElementGuideCode()));
//                ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
//                salDto.setCode(ek);
                
//                Long oldYearCode = dtoForSave.getSalYearCode();
//               Long oldMonCode = dtoForSave.getSalMonthCode();
//               Long oldElmGuide = dtoForSave.getElemGuideCode();
                for(IBasicDTO dto: dtoForSave.getEmpDecisionsDTOList()){
                IEmpDecisionsDTO empDecisionDto = (IEmpDecisionsDTO)dto;
                empDecisionDto.setSalElmGuideDTOList(decisionCycleEmployeesMaintain.getCurrentDisplayedSalVariedElmGuideDTOList());
                    
                if (multiOrSingleEmps.equals(OneEmpMultiElements)) {
                    empDecisionDto.setValueAsFloat(decisionCycleEmployeesMaintain.getTotalValue());
                } else {
                    Float value=calcValue(empDecisionDto.getDenarValue() ,empDecisionDto.getFelsValue());
                    Float originalValue=calcValue(empDecisionDto.getDenarValueOriginal() ,empDecisionDto.getFelsValueOriginal());  
                        if (empDecisionDto.getStatusFlag()==null  &&
                            !value.equals(originalValue)) {
                            empDecisionDto.setStatusFlag(IConstants.UPDATED_ITEM);
                        }
                    empDecisionDto.setValueAsFloat(calcValue(empDecisionDto.getDenarValue(),
                                                             empDecisionDto.getFelsValue()));
                    empDecisionDto.setValueAsFloat(value);
                }   
               
//                if (empDecisionDto.getStatusFlag()==null &&
//                    ((oldElmGuide != null && !oldElmGuide.equals(ek.getElmguideCode())) &&
//                    !value.equals(originalValue))) {
//                    empDecisionDto.setStatusFlag(IConstants.UPDATED_ITEM_AND_FIN_DEC_CHANGE_ELEM_GUIDE);
//                }else if (empDecisionDto.getStatusFlag()==null &&
//                    ((oldElmGuide != null && !oldElmGuide.equals(ek.getElmguideCode())) 
//                    )) {
//                    empDecisionDto.setStatusFlag(IConstants.FIN_DEC_CHANGE_ELEM_GUIDE);
//                }else 
               
                
    //                    System.out.println(empDecisionDto.getDenarValue());
    //                    System.out.println(empDecisionDto.getFelsValue());
                    
                    
                empDecisionDto.setElementGuidesDTO(salDto);
    //                 System.out.println("F:"+value);
                empDecisionDto.setYear(Long.valueOf(String.valueOf(merYear)));
                empDecisionDto.setMonth(Long.valueOf(String.valueOf(merMonth)));
                    
//                empDecisionDto.setYear(Long.valueOf(decisionCycleEmployeesMaintain.getYear()));
//                empDecisionDto.setMonth(Long.valueOf(decisionCycleEmployeesMaintain.getMonth()));
              //  Long vCivilId = ((IEmpDecisionsEntityKey)empDecisionDto.getCode()).getCivilId();
    //                try{
    //                    if(vCivilId != null){
    //                        IEmployeesDTO employeesDTO =
    //                                            (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(vCivilId);
    //                        if (employeesDTO != null) {
    //                            empDecisionDto.setMinCode(employeesDTO.getMinCode());
    //                        }
    //                    }
    //
    //                }
    //                catch(Exception e){
    //                    e.printStackTrace();
    //                }
                }
           } 
            return defaultAddDecisoinPartially();
        }

       return null; 
    }
    public float calcValue(String denar ,String fels ){
        Float calculatedValue = null;
        fels=fels!=null?fels:"0";
        String value ="";
        if( denar != null && fels != null){
            value = denar +"."+ fels;
        }
        if(value!=null && !value.isEmpty())
            calculatedValue = Float.parseFloat(value);
        else
            calculatedValue = 0F;
        return calculatedValue;
    }

    public String partiallySave() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
        decisionsDTO.setRegStatusFlage(IRegConstants.REGULATION_STATUS_SUGGESTION);
        ((IDecisionsDTO)getPageDTO()).setCurrentStage(IConstants.REG_SUGGESTION_STAGE);
        return finish();
    }

    public String totallySave() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        ((IDecisionsDTO)getPageDTO()).setCurrentStage(IConstants.REG_SUGGESTION_STAGE);
        IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
        decisionsDTO.setRegStatusFlage(IConstants.REG_SUGGESTED_STATUS);
        IRegulationStatusClient regulationStatusClient = RegClientFactory.getRegulationStatusClient();
        IRegulationStatusDTO regStatusDTO =
            (IRegulationStatusDTO)regulationStatusClient.getById(RegEntityKeyFactory.createRegulationStatusEntityKey(IRegConstants.REGULATION_STATUS_READY_REVISION));
        decisionsDTO.setRegStatusDTO(regStatusDTO);
        decisionsDTO.setUserCodeSugg(CSCSecurityInfoHelper.getUserCode());
        decisionsDTO.setSuggDate(SharedUtils.getSqlDate());
        return finish();
    }

    public String defaultAddDecisoin() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        ((IDecisionsDTO)getPageDTO()).setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        String currentstepId = getCurrentStep();
        try {
            if (getFinishButtonOverride(currentstepId) == 1) {
                setRenderFinish(getFinishButtonStatus(currentstepId));
            } else if (getFinishButtonOverride(currentstepId) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }
            if (validateStep(currentstepId, null)) {
                if (this.getMaintainMode() == 0) {
                    this.add();
                    if (decnumyearTypeAddedBefor == 1 || noBankAccountData == 1) {
                        return "financialdecisionslist";
                    }
                } else if (this.getMaintainMode() == 1) {

                    this.edit();
                    
                    
                    //   setPageDTO(super.getClient().getById(getPageDTO().getCode()));
                }

                updateStepData(currentstepId);

            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (getFinishNavigationCase() != null && !isShowErrorMsg()) {


            if (getFinishNavigationCase() != null && !isShowErrorMsg()) {
                DecisionCycleMaintainBean maintainBean =
                    (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
                if (maintainBean.getMaintainMode() == 0) {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    Application app = ctx.getApplication();
                    FinancialDecisionsListBean decisionListBean =
                        (FinancialDecisionsListBean)app.createValueBinding("#{financialDecisionsListBean}").getValue(ctx);
//                    decisionListBean.setSearchMode(false);
//                    decisionListBean.fillDataTable();
                    decisionListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                    decisionListBean.getHighlightedDTOS().add(getPageDTO());
                    decisionListBean.getSharedUtil().setSuccessMsgValue("SuccessAdds");
                    
                    
                }
                highlighDataTable("financialDecisionsListBean");
            }

        }
        return "financialdecisionslist";
    }
    
    public String defaultAddDecisoinPartially() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        ((IDecisionsDTO)getPageDTO()).setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
     
        try {

                if (this.getMaintainMode() == 0) {
                this.addPartially();               
            } else if (this.getMaintainMode() == 1) {
                this.editPartially();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

//        if (!isShowErrorMsg()) {
//                DecisionCycleMaintainBean maintainBean =
//                    (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());               
//                    maintainBean.getSharedUtil().setSuccessMsgValue("SuccessAdds");
//        }
        return null;
    }

    public void edit() throws DataBaseException {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            if (multiOrSingleEmps.equals(OneEmpMultiElements)) {
                setPageDTO(((IDecisionsClient)getClient()).updateOneEmpPersonMultiElementsDecision(getPageDTO(),true ));
//                setPageDTO(((IDecisionsClient)getClient()).updateEntitlementDecision(getPageDTO(),true ));
            } else {
                 setPageDTO(((IDecisionsClient)getClient()).updateEntitlementDecision(getPageDTO(),true ));
            }

            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            FinancialDecisionsListBean financialDecisionsListBean =
                (FinancialDecisionsListBean)app.createValueBinding("#{financialDecisionsListBean}").getValue(ctx);

            if (financialDecisionsListBean.isUsingPaging()) {

                financialDecisionsListBean.setUpdateMyPagedDataModel(true);
                financialDecisionsListBean.setRepositionTable(true);
                financialDecisionsListBean.getPagingBean().clearDTOS();
                if (financialDecisionsListBean.getPagingRequestDTO() == null) {
                    financialDecisionsListBean.setPagingRequestDTO(new PagingRequestDTO());
                }
                financialDecisionsListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

            } else {
                financialDecisionsListBean.getAll();
                financialDecisionsListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            }

            financialDecisionsListBean.setSearchQuery("");
            financialDecisionsListBean.setSearchType(0);
            financialDecisionsListBean.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            financialDecisionsListBean.getSelectedDTOS().clear();

            financialDecisionsListBean.getPagingBean().cancelSearch();
            financialDecisionsListBean.setSorting(true);
            //            financialDecisionsListBean.setBsnSortingColumnName("o.decyearCode");
            financialDecisionsListBean.setRepositionTable(true);
            financialDecisionsListBean.setSortAscending(false);
            financialDecisionsListBean.setSearchMode(false);
            financialDecisionsListBean.setRecordAdded(true);

            IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();

            financialDecisionsListBean.setSelectedRepTemplateKey(financialDecisionsListBean.RECOMMENDED_DEC_WITH_DATE+ "");
            financialDecisionsListBean.getSelectedDTOS().clear();
            financialDecisionsListBean.getSelectedDTOS().add(decisionsDTO) ;
            financialDecisionsListBean.loadReportTemplates();
           // financialDecisionsListBean.setRegDate(decisionsDTO.getDecisionDate());
            decisionsDTO.setDecisionAppliedDate(decisionsDTO.getDecisionDate());
            financialDecisionsListBean.openReport(decisionsDTO);
            

            Long rowNum = 0L;
            try {

                rowNum = getClient().getRowNum((IDecisionsDTO)getPageDTO());
            } catch (DataBaseException dbe) {
                // TODO: Add catch code
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                // TODO: Add catch code
                sae.printStackTrace();
            }

            financialDecisionsListBean.setCurrentPageIndex(rowNum.intValue());


        } catch (DataBaseException e) {
                sharedUtil.handleException(e);
            } catch (ItemNotFoundException item) {
                sharedUtil.handleException(item);
            } catch (SharedApplicationException e) {
                sharedUtil.handleException(e);
            } catch (Exception e) {
                sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
            }
        setSelectedRadio("");
    }
    public void addPartially() throws DataBaseException {      
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            setPageDTO(((IDecisionsClient)getClient()).addDecision(getPageDTO(), true));
           
            this.setMaintainMode(this.MAINTAIN_MODE_EDIT);
            DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
                (DecisionCycleEmployeesMaintain)evaluateValueBinding("financeDecisionCycleEmployeesMaintainBean");
//            List<IBasicDTO> empDecisionsDTOList = ((IDecisionsDTO)getPageDTO()).getEmpDecisionsDTOList();
//            for (IBasicDTO dto : empDecisionsDTOList) {
//                ((IEmpDecisionsDTO)dto).setStatusFlag(null);
//            }
            IBasicDTO decisionDTO =((IDecisionsClient)getClient()).getByIdForFinMerit(getPageDTO().getCode());
            setPageDTO(decisionDTO);
            
            IDecisionsDTO dto = (IDecisionsDTO)getPageDTO();
            if(dto.getViewFlag().equals(Long.parseLong(OneEmpMultiElements)) && dto.getEmpDecisionsDTOList()!= null && !dto.getEmpDecisionsDTOList().isEmpty()){
                IEmpDecisionsDTO empDecDTO = (IEmpDecisionsDTO)dto.getEmpDecisionsDTOList().get(0);
                if(empDecDTO.getEmployeesDTO()!= null){
                        dto.setName(empDecDTO.getEmployeesDTO().getCode().getKey());//Emp CivilID Or Person RealCivilID
                        dto.setEngName(((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)empDecDTO.getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId().toString());//RealCivil
                }
            }
            decisionCycleEmployeesMaintain.setCurrentDetails(((IDecisionsDTO)decisionDTO).getEmpDecisionsDTOList());
            decisionCycleEmployeesMaintain.getTotal_Value();
            sharedUtil.setSuccessMsgValue("SuccessAdds");    
    
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
            }catch(DecionNumberYearTypeAlreadyAddedBefor e){
                DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = DecisionCycleMainDataMaintain.getInstance();
                decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"DecNumberYearTypeAlreadyAddedBefor"));
                e.printStackTrace();
                decnumyearTypeAddedBefor=1;
                }catch(NoBankAccountData e){
                    DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = DecisionCycleMainDataMaintain.getInstance();
                    decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"noBankAccountData"));
                    e.printStackTrace();
                    noBankAccountData=1;
                }
        
           catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        
    }

    public void editPartially() throws DataBaseException {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            
            if (multiOrSingleEmps.equals(OneEmpMultiElements)) {
                setPageDTO(((IDecisionsClient)getClient()).updateOneEmpPersonMultiElementsDecision(getPageDTO(),true ));
            } else {
                 setPageDTO(((IDecisionsClient)getClient()).updateEntitlementDecision(getPageDTO(),true ));
            }
            DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
                (DecisionCycleEmployeesMaintain)evaluateValueBinding("financeDecisionCycleEmployeesMaintainBean");
            //            List<IBasicDTO> empDecisionsDTOList = ((IDecisionsDTO)getPageDTO()).getEmpDecisionsDTOList();
            //            for (IBasicDTO dto : empDecisionsDTOList) {
            //                ((IEmpDecisionsDTO)dto).setStatusFlag(null);
            //            }
            IBasicDTO decisionDTO =((IDecisionsClient)getClient()).getByIdForFinMerit(getPageDTO().getCode());
            setPageDTO(decisionDTO);
            IDecisionsDTO dto = (IDecisionsDTO)getPageDTO();
            if(dto.getEmpDecisionsDTOList()!= null && !dto.getEmpDecisionsDTOList().isEmpty()){
                IEmpDecisionsDTO empDecDTO = (IEmpDecisionsDTO)dto.getEmpDecisionsDTOList().get(0);
                if(empDecDTO.getEmployeesDTO()!= null){
                        dto.setName(empDecDTO.getEmployeesDTO().getCode().getKey());//Emp CivilID Or Person RealCivilID
                        dto.setEngName(((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)empDecDTO.getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId().toString());//RealCivil
                }
            }
            decisionCycleEmployeesMaintain.setCurrentDetails(((IDecisionsDTO)decisionDTO).getEmpDecisionsDTOList());
            decisionCycleEmployeesMaintain.getTotal_Value();
            sharedUtil.setSuccessMsgValue("SuccessAdds");

        } catch (DataBaseException e) {
                sharedUtil.handleException(e);
            } catch (ItemNotFoundException item) {
                sharedUtil.handleException(item);
            } catch (SharedApplicationException e) {
                sharedUtil.handleException(e);
            } catch (Exception e) {
                sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
            }
       
    }
    public String back() {
        
        
        if(backMethodName !=null && !backMethodName.equals("") ){
                 executeMethodBindingWithParams(backMethodName, savedDataObjects, new Class[] { Object.class });
        return backNavCase;
        }
        String finishNavigationCase = super.back();

        if (finishNavigationCase != null) {
          //  DecisionCycleMaintainBean maintainBean =
           //     (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
                    FinancialDecisionsListBean decisionListBean =
                        (FinancialDecisionsListBean)app.createValueBinding("#{financialDecisionsListBean}").getValue(ctx);
                   // decisionListBean.setSearchMode(true);
//                    decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    //  decisionListBean.setStringSearchType("false");
//                    decisionListBean.setSelectedRadio("");
//                    decisionListBean.resetPageIndex();
//                    //decisionListBean.unCheck();
//                    if (decisionListBean.getSelectedDTOS() != null)
//                        decisionListBean.getSelectedDTOS().clear();
//                    if (decisionListBean.getHighlightedDTOS() != null)
//                        decisionListBean.getHighlightedDTOS().clear();
//                    decisionListBean.repositionPage(0);
//                    //  decisionListBean.setStringSearchType("false");
//                    decisionListBean.setSelectedRadio("");
                    //                    decisionListBean.getGetAllDTO().setCurrentStage(3L);
                    //                    decisionListBean.getGetAllDTO().setRegStatusFlage(3L);
                    //                    decisionListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

//                    try {
//                        decisionListBean.getAll();
//                    } catch (DataBaseException e) {
//                        // TODO
//                    }        
//            if (maintainBean.getCurrentStag() == 2) {
//                DecisionRevisionListBean decisionListBean =
//                    (DecisionRevisionListBean)app.createValueBinding("#{decisionRevisionListBean}").getValue(ctx);
//                if (!decisionListBean.isShowAllFlag()) {
//                    decisionListBean.setSearchMode(true);
//                    decisionListBean.getDecisionSearchDTO().setCurrentStage(2L);
//                    decisionListBean.getDecisionSearchDTO().setRegStatusFlage(0L);
//                    try {
//                        if (decisionListBean.getDecisionSearchDTO() != null) {
//                            if (decisionListBean.getDecisionSearchDTO().getName() != null) {
//                                if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
//                                    decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
//                                }
//                            }
//
//                            decisionListBean.generatePagingRequestDTO("decisionRevisionListBean", "searchWithPaging");
//                            decisionListBean.setStringSearchType("false");
//                            decisionListBean.setSelectedRadio("");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    decisionListBean.setSearchMode(true);
//                    decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
//                    decisionListBean.setStringSearchType("false");
//                    decisionListBean.setSelectedRadio("");
//                    decisionListBean.getGetAllDTO().setCurrentStage(2L);
//                    decisionListBean.getGetAllDTO().setRegStatusFlage(0L);
//                    try {
//                        decisionListBean.getAll();
//                    } catch (DataBaseException e) {
//                        // TODO
//                    }
//                }
//                decisionListBean.resetPageIndex();
//                decisionListBean.unCheck();
//                if (decisionListBean.getSelectedDTOS() != null)
//                    decisionListBean.getSelectedDTOS().clear();
//                if (decisionListBean.getHighlightedDTOS() != null)
//                    decisionListBean.getHighlightedDTOS().clear();
//                decisionListBean.repositionPage(0);
//                decisionListBean.setStringSearchType("false");
//                decisionListBean.setSelectedRadio("");
//            }
//            if (maintainBean.getCurrentStag() == 3) {

               
//                if (!decisionListBean.isShowAllFlag()) {
//                    decisionListBean.setSearchMode(true);
//                    decisionListBean.getDecisionSearchDTO().setCurrentStage(3L);
//                    decisionListBean.getDecisionSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
//                    decisionListBean.getDecisionSearchDTO().setRegStatusFlage(3L);
//
//                    try {
//                        if (decisionListBean.getDecisionSearchDTO() != null) {
//                            if (decisionListBean.getDecisionSearchDTO().getName() != null) {
//                                if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
//                                    decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
//                                }
//                            }
//                            if (decisionListBean.isUsingPaging()) {
//                                decisionListBean.generatePagingRequestDTO("decisionExcuteListBean",
//                                                                          "searchWithPaging");
//                                decisionListBean.setStringSearchType("false");
//                                decisionListBean.setSelectedRadio("");
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
                   
                }
              
//            }

            return "financialdecisionslist";
       
       
    }

    public void add() throws DataBaseException {
       // handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();

        try {
            setPageDTO(((IDecisionsClient)getClient()).addDecision(getPageDTO(), true));
            
//            
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            FinancialDecisionsListBean financialDecisionsListBean =
                (FinancialDecisionsListBean)app.createValueBinding("#{financialDecisionsListBean}").getValue(ctx);
            if (financialDecisionsListBean.isUsingPaging()) {

                financialDecisionsListBean.getPagingBean().clearDTOS();

                financialDecisionsListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

            } else {
                financialDecisionsListBean.getAll();
                financialDecisionsListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            }

            financialDecisionsListBean.setSearchQuery("");
            financialDecisionsListBean.setSearchType(0);
            financialDecisionsListBean.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");
            financialDecisionsListBean.getPagingBean().cancelSearch();
            financialDecisionsListBean.setSorting(true);
//            financialDecisionsListBean.setBsnSortingColumnName("o.decyearCode");
            financialDecisionsListBean.setRepositionTable(true);
            financialDecisionsListBean.setSortAscending(false);
            financialDecisionsListBean.setSearchMode(false);
            financialDecisionsListBean.setRecordAdded(true);


            Long rowNum = 0L;
            try {

                rowNum = getClient().getRowNum(getPageDTO());
            } catch (DataBaseException dbe) {
                // TODO: Add catch code
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                // TODO: Add catch code
                sae.printStackTrace();
            }
         
            financialDecisionsListBean.setCurrentPageIndex(rowNum.intValue());
//            getSelectedDTOS().clear();
//            getSelectedDTOS().add(getPageDTO());
            IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
//            String decisionDoneNumber = decisionsDTO.getRegAutoNumber();
        financialDecisionsListBean.setSelectedRepTemplateKey(financialDecisionsListBean.RECOMMENDED_DEC_WITH_DATE+ "");
            financialDecisionsListBean.getSelectedDTOS().clear();
            financialDecisionsListBean.getSelectedDTOS().add(decisionsDTO) ;
            financialDecisionsListBean.loadReportTemplates();
           // financialDecisionsListBean.setRegDate(decisionsDTO.getDecisionDate());
            decisionsDTO.setDecisionAppliedDate(decisionsDTO.getDecisionDate());
            financialDecisionsListBean.openReport(decisionsDTO);
  
  
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);

            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInAdd");
            sharedUtil.handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
            }catch(DecionNumberYearTypeAlreadyAddedBefor e){
                DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = DecisionCycleMainDataMaintain.getInstance();
                decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"DecNumberYearTypeAlreadyAddedBefor"));
                e.printStackTrace();
                decnumyearTypeAddedBefor=1;
                }catch(NoBankAccountData e){
                    DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = DecisionCycleMainDataMaintain.getInstance();
                    decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"noBankAccountData"));
                    e.printStackTrace();
                    noBankAccountData=1;
                }
        
           catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }


//    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
//        String decisionDoneNumber = null;
// //       handleDecisionImage();
//
//
//        try {
//            if (getCurrentStag() != 3L) {
//                ((IDecisionsClient)getClient()).updateDecison(getPageDTO());
//            } else {
//
//                IDecisionsDTO dtoForEdit = (IDecisionsDTO)getPageDTO();
//                dtoForEdit.setApprovalDate(SharedUtils.getSqlDate());
//                dtoForEdit.setUserCodeApproval(CSCSecurityInfoHelper.getUserCode());
//                decisionDoneNumber = setRegNumber(dtoForEdit);
//                ((IDecisionsClient)getClient()).excuteDecison(dtoForEdit);
//            }
//            cancelSearch();
//
//            if (isUsingPaging()) {
//
//                setUpdateMyPagedDataModel(true);
//                setRepositionTable(true);
//                getPagingBean().clearDTOS();
//                if (getPagingRequestDTO() == null) {
//                    setPagingRequestDTO(new PagingRequestDTO());
//                }
//                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());
//
//            }
//
//            if (getHighlightedDTOS() != null) {
//                getHighlightedDTOS().add(getPageDTO());
//            }
//
//            if (getCurrentStag() != 3L) {
//                sharedUtil.setSuccessMsgValue("SuccesUpdated");
//            } else {
//                String message =
//                    sharedUtil.messageLocator("com.beshara.csc.nl.reg.integration.presentation.resources.integration", "SuccesExcute");
//                getSharedUtil().setSuccessMsgValue(message + " " + decisionDoneNumber);
//            }
//            this.getSelectedDTOS().clear();
//
//        } catch (DataBaseException e) {
//            sharedUtil.handleException(e);
//        } catch (ItemNotFoundException item) {
//            sharedUtil.handleException(item);
//        } catch (SharedApplicationException e) {
//            sharedUtil.handleException(e);
//        } catch (Exception e) {
//            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
//        }
//        //Added By Yassmine to reset the value of radio button after Edit
//        setSelectedRadio("");
//    }
    
    public String navigateToStep(String targetStep) {

        String nCase = getNavigationCase(targetStep);
        this.updateStepDependancies(getCurrentStep());

        setNextNavigationCase(getNextNavigationCase(targetStep));
        setPreviousNavigationCase(getNavigationCase(getCurrentStep()));
        setValidated(getCurrentStep());
        setVisited(getCurrentStep());
        setVisited(targetStep);

        if (getFinishButtonOverride(targetStep) == 1) {
            setRenderFinish(getFinishButtonStatus(targetStep));
        } else {
            setRenderFinish(true);
        }


        setCurrentStep(targetStep);
        getWizardBar().setCurrentStep(targetStep);

        return nCase;

    }

    public String saveDecision() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        //handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();

//        DecisionEmployeesModel decisionEmployeesModelSessionBean =
//            (DecisionEmployeesModel)evaluateValueBinding("financedecisionEmployeesModel");

        String decisionText = ((IDecisionsDTO)getPageDTO()).getDecisionText();
        
        if (decisionText == null || decisionText.trim().equals("")) {
            String beanName = getWizardStep("financedecisionadd").getStepBeanName();
            DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =
                (DecisionCycleMainDataMaintain)evaluateValueBinding(beanName);
            decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,
                                                                                                        "decision_text_error"));
            return navigateToStep("financedecisionadd");
        }

        try {
        
          //  decisionEmployeesModelSessionBean.sendListtoBussiens();
        
            ((IDecisionsClient)getClient()).updateDecison(getPageDTO());

            cancelSearch();

            if (isUsingPaging()) {

                setUpdateMyPagedDataModel(true);
                setRepositionTable(true);
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO() == null) {
                    setPagingRequestDTO(new PagingRequestDTO());
                }
                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
            //            sharedUtil.handleException(item,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
        } catch (SharedApplicationException e) {
            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");
      //  decisionEmployeesModelSessionBean.resetSessionData();
        return getFinishNavigationCase();
    }

    public void setDetailsSavedStates(Map<String, Object> detailsSavedStates) {
        this.detailsSavedStates = detailsSavedStates;
    }

    public Map<String, Object> getDetailsSavedStates() {
        if (detailsSavedStates == null) {
            detailsSavedStates = new HashMap<String, Object>();
        }
        return detailsSavedStates;
    }

//    private void handleDecisionImage() {
//        String tempFilePath = (String)getDetailsSavedStates().get(BeansUtil.UPLOADED_IMAGE_BINDING_KEY);
//        if (tempFilePath != null) {
//            String imageFileName = getCurrentDecisionKey();
//            try {
//                String relativeFileName =
//                    BeansUtil.saveRegImage(tempFilePath, RegConfig.getInstance().getDecisionUploadedImagesPath(),
//                                           imageFileName);
//                ((IDecisionsDTO)getPageDTO()).setDecisionImage(relativeFileName);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private String getCurrentDecisionKey() {
//        String decisionNumber = "";
//        String decisionTypeCode = "";
//        String issuanceYearCode = "";
//
//        if (getMaintainMode() == 0) { // add
//            decisionNumber = ((IDecisionsDTO)getPageDTO()).getDecisionNumber().toString();
//        } else if (getMaintainMode() == 1) { // edit
//            decisionNumber = ((IDecisionsEntityKey)(getPageDTO().getCode())).getDecisionNumber().toString();
//        }
//        decisionTypeCode = ((IDecisionsDTO)getPageDTO()).getTypesDTO().getCode().getKey().toString();
//        issuanceYearCode = ((IDecisionsDTO)getPageDTO()).getYearsDTO().getCode().getKey().toString();
//
//        return decisionNumber + "_" + decisionTypeCode + "_" + issuanceYearCode;
//    }

//    public void setCancelDecisionMode(boolean cancelDecisionMode) {
//        this.cancelDecisionMode = cancelDecisionMode;
//    }
//
    public void setCopyDecisionWithEmployeesMode(boolean copyDecisionWithEmployeesMode) {
        this.copyDecisionWithEmployeesMode = copyDecisionWithEmployeesMode;
    }

    public boolean isCopyDecisionWithEmployeesMode() {
        return copyDecisionWithEmployeesMode;
    }


    public void setLisBeanName(String lisBeanName) {
        this.lisBeanName = lisBeanName;
    }

    public String getLisBeanName() {
        return lisBeanName;
    }

//    public void setCurrentStag(Long currentStag) {
//        this.currentStag = currentStag;
//    }
//
//    public Long getCurrentStag() {
//        return currentStag;
//    }

    public void setShowOnly(boolean showOnly) {
        this.showOnly = showOnly;
    }

    public boolean isShowOnly() {
        return showOnly;
    }

    public void navigate(ActionEvent actionEvent) {
        String stepKey = actionEvent.getComponent().getId();
        String currentStepKey = getCurrentStep();
        this.updateStepDependancies(currentStepKey);
        String nCase = null;
        if (validateStep(currentStepKey, stepKey) && checkStepRelevants(stepKey)) {
            //added for finish button status
            setValidated(currentStepKey);
            setVisited(currentStepKey);
            setVisited(stepKey);
            //set the finish button status
            setCurrentStep(stepKey);
            getWizardBar().setCurrentStep(stepKey);
            if (getFinishButtonOverride(currentStepKey) == 1) {
                setRenderFinish(getFinishButtonStatus(stepKey));
            } else if (getFinishButtonOverride(currentStepKey) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            //set the save button status


            //
            nCase = getNavigationCase(stepKey);
            setNextNavigationCase(getNextNavigationCase(stepKey));
            setPreviousNavigationCase(getPreviousNavigationCase(stepKey));
            System.out.println(stepKey);
        }

        if (nCase != null)
            handleNavigation(nCase);
    }

    public void openConfirmSave() {

    }


   


//    private String setRegNumber(IDecisionsDTO decisionsDTO) throws RemoteException {
//
//        StringBuffer decisionDoneNumber = new StringBuffer("");
//        Long decTypeCode = ((IDecisionsEntityKey)decisionsDTO.getCode()).getDectypeCode();
//        Long decYear = ((IDecisionsEntityKey)decisionsDTO.getCode()).getDecyearCode();
//        Long minCode = 13l;
//
//        // return serial number
//        String regSerial =
//            ((IDecisionsClient)getClient()).getSerialByMinAndYearAndType(decTypeCode, decYear, minCode).toString();
//
//        String yearCode = (decYear.toString()).substring(2);
//
//        decisionDoneNumber.append(yearCode);
//        decisionDoneNumber.append(minCode);
//        decisionDoneNumber.append(decTypeCode);
//        decisionDoneNumber.append("/");
//        decisionDoneNumber.append(regSerial);
//        decisionsDTO.setRegNum(decisionDoneNumber.toString());
//
//        return decisionDoneNumber.toString();
//    }

//    public void setReviewMode(boolean _reviewMode) {
//        this._reviewMode = _reviewMode;
//    }
//
//    public boolean isReviewMode() {
//        return _reviewMode;
//    }

    public void setCurrentStag(Long currentStag) {
        this.currentStag = currentStag;
    }

    public Long getCurrentStag() {
        return currentStag;
    }


    public void setDecnumyearTypeAddedBefor(int decnumyearTypeAddedBefor) {
        this.decnumyearTypeAddedBefor = decnumyearTypeAddedBefor;
    }

    public int getDecnumyearTypeAddedBefor() {
        return decnumyearTypeAddedBefor;
    }

    public void setNoBankAccountData(int noBankAccountData) {
        this.noBankAccountData = noBankAccountData;
    }

    public int getNoBankAccountData() {
        return noBankAccountData;
    }

    public void setMonths(List months) {
        this.months = months;
    }

    public List getMonths() {
        if (months == null) {
            months = new ArrayList();
            List<IBasicDTO> monthsList;
            try {
                monthsList = InfClientFactory.getMonthsClient().getAll();
                months.addAll(monthsList);
            } catch (DataBaseException e) {
                months = new ArrayList();
            } catch (SharedApplicationException e) {
                months = new ArrayList();
            }
        }
        return months;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setYears(List years) {
        this.years = years;
    }

    public List getYears() {
        if (years == null) {
            years = new ArrayList();
            try {
                IYearsClient client = InfClientFactory.getYearsClient();
                setYears(client.getCodeName());
                years = client.getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return years;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public int getViewMode() {
        return this.MAINTAIN_MODE_VIEW_ONLY;
    }


    public void setBackNavCase(String backNavCase) {
        this.backNavCase = backNavCase;
    }

    public String getBackNavCase() {
        return backNavCase;
    }

      

       public void setBackMethodName(String backMethodName) {
           this.backMethodName = backMethodName;
       }

       public String getBackMethodName() {
           return backMethodName;
       }
       
       
       
       
       public void init(Object param, String navCase, String returnMethodName) {
           savedDataObjects = new Object[1];
           savedDataObjects[0] = param;
           this.backNavCase = navCase;
           this.backMethodName = returnMethodName;
          
       }

       public void setSavedDataObjects(Object[] savedDataObjects) {
           this.savedDataObjects = savedDataObjects;
       }

       public Object[] getSavedDataObjects() {
           return savedDataObjects;
       }
       
    public String previous() {

    //        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
        
        String nCase = getPreviousNavigationCase();
        String currentStepId=getCurrentStep();
        this.updateStepDependancies(currentStepId);
        //
       
        nCase = getNavigationCase(this.getAvailablePreviousStep(currentStepId));
        //
       

        String targetStep = getPreviousStep(currentStepId);
        if (targetStep != null)
//            if (validateStep(currentStep, targetStep) && 
//                checkStepRelevants(targetStep)) 
//            
            {

                setNextNavigationCase(getNavigationCase(getCurrentStep()));
                setPreviousNavigationCase(getPreviousNavigationCase(getPreviousStep(currentStepId)));

                // setNextNavigationCase(getNavigationCase(this.getAvailableNextStep(targetStep)));
                // setPreviousNavigationCase(getNavigationCase(this.getAvailablePreviousStep(targetStep)));


                setVisited(getPreviousStep(currentStepId));
                setValidated(currentStepId);
                setVisited(currentStepId);
                if (getFinishButtonOverride(getPreviousStep(currentStepId)) == 
                    1) {
                    setRenderFinish(getFinishButtonStatus(getPreviousStep(currentStepId)));
                } else if (getFinishButtonOverride(getPreviousStep(currentStepId)) == 
                           2) {
                    setRenderFinish(false);
                } else {
                    setRenderFinish(true);
                }


                setCurrentStep(getPreviousStep(currentStepId));
                getWizardBar().setCurrentStep(currentStepId);
    //                System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
                
                return nCase;
            }
    //        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
        
        return null;
    }

    public void setMultiOrSingleEmps(String multiOrSingleEmps) {
        this.multiOrSingleEmps = multiOrSingleEmps;
    }

    public String getMultiOrSingleEmps() {
        return multiOrSingleEmps;
    }
}

