package com.beshara.csc.gn.grs.integration.presentation.backingbean.joincalc;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.ICalculationDetailsDTO;
import com.beshara.csc.gn.grs.business.dto.ICalculationsDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.ICalculationsEntityKey;
import com.beshara.csc.gn.grs.business.shared.IGrsConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;


public class CalculationDetailsBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public static final String PARAM_KEY_CALCULATION_KEY = "calculationKey";
    public static final String PARAM_KEY_DISPLAY_RELATED_MODULES = "displayRelatedModules";
    private static final String PAGE_URI = "/integration/grs/jsps/joincalc/calculationDetails.jsf";
 
    private String calculationKey;
    private boolean displayRelatedModules;
    private String queryOverview = null;
    private List<IBasicDTO> joinConditions;
    private List parametersList = new ArrayList();

    public CalculationDetailsBean() {
        calculationKey = (String)JSFHelper.getRequestParameter(PARAM_KEY_CALCULATION_KEY);
        if(JSFHelper.getRequestParameter(PARAM_KEY_DISPLAY_RELATED_MODULES)!=null){
            displayRelatedModules = Boolean.valueOf(JSFHelper.getRequestParameter(PARAM_KEY_DISPLAY_RELATED_MODULES).toString());    
        }
     if (calculationKey != null && !calculationKey.equals("")) {
            initPageDTOFromKey(calculationKey);
        }
    }

    public static String constructPageURL(String conditionCode, boolean displayRelatedModules) {
        StringBuilder uri = new StringBuilder(PAGE_URI);
        uri.append("?");
        uri.append(CalculationDetailsBean.PARAM_KEY_CALCULATION_KEY).append("=").append(conditionCode);
        uri.append("&");
        uri.append(CalculationDetailsBean.PARAM_KEY_DISPLAY_RELATED_MODULES).append("=").append(displayRelatedModules);
        return uri.toString();
    }

    /**
     * Purpose: to initialize pageDTO 
     * Parameter: String condition key
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Kamal
     * 1.2 - Date:   04-05-2014
     * 1.3 - Creation/Modification:Creation
     */
    public void initPageDTOFromKey(String condKey) {
        if (getPageDTO() == null && condKey != null && !condKey.equals("")) {
            ICalculationsEntityKey calcCode = GrsEntityKeyFactory.createCalculationsEntityKey(Long.parseLong(condKey));
            ICalculationsDTO calcDTO = null;
            try {
                calcDTO = (ICalculationsDTO)GrsClientFactory.getCalculationsClient().getById2(calcCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (calcDTO != null) {
                setPageDTO(calcDTO);
            }
            if(parametersList==null || parametersList.size()==0)
                fillParameters();
            
        }
    }
    public void fillParameters(){       
        try {
                parametersList = GrsClientFactory.getParametersClient().getParametersForCalc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String overview() {
        queryOverview = "";
        List<IBasicDTO> temp = ((ICalculationsDTO)getPageDTO()).getCalculationDetailsDTOList();
        for (IBasicDTO _dto : temp) {
            ICalculationDetailsDTO dto = (ICalculationDetailsDTO)_dto;
            if (dto.getStatusFlag() == null) {
                queryOverviewStringFactory(dto);
            } else {
                if (!dto.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                    queryOverviewStringFactory(dto);
                } else {
                    System.out.println("we will send a dto to be drawen with the status flag of the dto=" +
                                       dto.getStatusFlag());
                }
            }
        }
        return null;
    }
    
    private void queryOverviewStringFactory(ICalculationDetailsDTO dto) {
        String newQueryOverview = "";
        if (queryOverview == null)
            queryOverview = "";        
        if (dto.getRightArcs() != null && !"".equals(dto.getRightArcs())){
            newQueryOverview += dto.getRightArcs();
        }
        if (dto.getParameterCode()!= null && !dto.getParameterCode().equals("")) {
            IBasicDTO basicDTO = this.getElementByKey(parametersList, dto.getParameterCode());
                newQueryOverview += basicDTO.getName();
        }
        if(dto.getOperatorSign() !=null ){
            newQueryOverview += dto.getOperatorSign();
        }
        
        try{

            if(dto.getValue() != null){ 
                String valueAsString = String.valueOf(dto.getValue());
                System.out.println(valueAsString);
                if(valueAsString.endsWith(".0")){
                    int index = valueAsString.indexOf(".");
                    valueAsString = valueAsString.substring(0, index);
                }
                
                newQueryOverview += valueAsString;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if (dto.getLeftArcs() != null && !"".equals(dto.getLeftArcs())){
            newQueryOverview += dto.getLeftArcs();
        }    
        if(dto.getOperatorSignBetweenLines() !=null){
            newQueryOverview += dto.getOperatorSignBetweenLines();
        }
        queryOverview += "\n" +
                newQueryOverview;   
        System.out.println("------" + queryOverview);
    }
    private IBasicDTO getElementByKey(List<IBasicDTO> dataList, String key) {
        for (IBasicDTO basicDTO : dataList) {
            if (basicDTO.getCode().getKey().equals(key)) {
                return basicDTO;
            }
        }
        return null;
    }  

    public void setQueryOverview(String queryOverview) {
        this.queryOverview = queryOverview;
    }

    public String getQueryOverview() {
        overview();
        return queryOverview;
    }

    public void setJoinConditions(List<IBasicDTO> joinConditions) {
        this.joinConditions = joinConditions;
    }

    public List<IBasicDTO> getJoinConditions() {
        
        if (joinConditions == null) {
            try{
               joinConditions = GrsClientFactory.getOperatorsClient().getAllByCalcFlag(IGrsConstants.CALCULATION_STATUS_ACTIVE);
            }catch(Exception e){
                joinConditions = new ArrayList<IBasicDTO>();
            }
        }
        return joinConditions;
    }

    public void setDisplayRelatedModules(boolean displayRelatedModules) {
        this.displayRelatedModules = displayRelatedModules;
    }

    public boolean isDisplayRelatedModules() {
        return displayRelatedModules;
    }
    public void setParametersList(List parametersList) {
        this.parametersList = parametersList;
    }

    public List getParametersList() throws DataBaseException, 
                                           SharedApplicationException {
       
        return parametersList;
    }

    public void setCalculationKey(String calculationKey) {
        this.calculationKey = calculationKey;
    }

    public String getCalculationKey() {
        return calculationKey;
    }
}

