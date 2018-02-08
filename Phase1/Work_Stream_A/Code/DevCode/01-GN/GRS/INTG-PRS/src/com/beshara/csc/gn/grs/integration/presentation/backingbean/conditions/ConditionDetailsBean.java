package com.beshara.csc.gn.grs.integration.presentation.backingbean.conditions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionDetailsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;


public class ConditionDetailsBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public static final String PARAM_KEY_CONDITION_KEY = "conditionKey";
    public static final String PARAM_KEY_DISPLAY_RELATED_MODULES = "displayRelatedModules";
    private static final String PAGE_URI = "/integration/grs/jsps/conditions/conditionDetails.jsf";
    
    private String conditionKey;
    private boolean displayRelatedModules;
    private String queryOverview = null;
    private List<IBasicDTO> joinConditions;

    public ConditionDetailsBean() {
        conditionKey = (String)JSFHelper.getRequestParameter(PARAM_KEY_CONDITION_KEY);
        if(JSFHelper.getRequestParameter(PARAM_KEY_DISPLAY_RELATED_MODULES)!=null){
            displayRelatedModules = Boolean.valueOf(JSFHelper.getRequestParameter(PARAM_KEY_DISPLAY_RELATED_MODULES).toString());    
        }
     if (conditionKey != null && !conditionKey.equals("")) {
            initPageDTOFromKey(conditionKey);
        }
    }

    public static String constructPageURL(String conditionCode, boolean displayRelatedModules) {
        StringBuilder uri = new StringBuilder(PAGE_URI);
        uri.append("?");
        uri.append(ConditionDetailsBean.PARAM_KEY_CONDITION_KEY).append("=").append(conditionCode);
        uri.append("&");
        uri.append(ConditionDetailsBean.PARAM_KEY_DISPLAY_RELATED_MODULES).append("=").append(displayRelatedModules);
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
            IConditionsEntityKey condCode = GrsEntityKeyFactory.createConditionsEntityKey(Long.parseLong(condKey));
            IConditionsDTO condDTO = null;
            try {
                condDTO = (IConditionsDTO)GrsClientFactory.getConditionsClient().getById(condCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (condDTO != null) {
                setPageDTO(condDTO);
            }
        }
    }
    
    private String constructLinesOverview() {
        queryOverview = "";
        String newQueryOverview = "";
        List<IBasicDTO> conditionDetailsDTOList = ((IConditionsDTO)getPageDTO()).getConditionDetailsDTOList();
        IConditionDetailsDTO dto = null;
        for (IBasicDTO iBasicDTO: conditionDetailsDTOList) {
            dto = (IConditionDetailsDTO)iBasicDTO;
            newQueryOverview = "";
            if (dto.getRightArcs() != null && !"".equals(dto.getRightArcs()))
                newQueryOverview += dto.getRightArcs();

            if (dto.getLinesDTO() != null) {
                newQueryOverview += dto.getLinesDTO().getName();

            }
            if (dto.getLeftArcs() != null && !"".equals(dto.getLeftArcs()))
                newQueryOverview += dto.getLeftArcs();

            queryOverview += "\n" + newQueryOverview;
            
            if (dto.getJonconditionSign() != null) {
                for (IBasicDTO joinConditionsDTO: this.getJoinConditions()) {
                    if (joinConditionsDTO.getCode().getKey().toString().equals(dto.getJonconditionSign()))
                        queryOverview += "\n" + joinConditionsDTO.getName();
                }

            }
        }
        return null;
    }
    

    public void setConditionKey(String conditionKey) {
        this.conditionKey = conditionKey;
    }

    public String getConditionKey() {
        return conditionKey;
    }

    public void setQueryOverview(String queryOverview) {
        this.queryOverview = queryOverview;
    }

    public String getQueryOverview() {
        constructLinesOverview();
        return queryOverview;
    }

    public void setJoinConditions(List<IBasicDTO> joinConditions) {
        this.joinConditions = joinConditions;
    }

    public List<IBasicDTO> getJoinConditions() {
        if (joinConditions == null) {
            try {
                joinConditions = GrsClientFactory.getJoinConditionsClient().getCodeName();
            } catch (Exception e){
                e.printStackTrace();
                joinConditions = new ArrayList<IBasicDTO>(0);
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
}    
 
