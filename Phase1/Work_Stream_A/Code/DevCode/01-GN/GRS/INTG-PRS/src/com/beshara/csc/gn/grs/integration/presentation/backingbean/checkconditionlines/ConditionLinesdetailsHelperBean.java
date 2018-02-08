package com.beshara.csc.gn.grs.integration.presentation.backingbean.checkconditionlines;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICheckCivilIdOnConditionDTO;
import com.beshara.csc.gn.grs.business.dto.ICheckCivilIdOnLineDTO;
import com.beshara.csc.gn.grs.business.dto.IEmployeeLinesDTO;
import com.beshara.csc.gn.grs.business.dto.IExceptionsDTO;
import com.beshara.csc.gn.grs.business.dto.ILinesDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;


public class ConditionLinesdetailsHelperBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String PAGE_URI = "/integration/grs/jsps/checkconditionlines/conditionDetailsPage.jsf";
    private Long civilId;
    private Long tabRecSerial;
    private String divName;
    private String empName;
    private String conditionName;
    private String exceptionType = "";
    private boolean exceptionFromCondition;
    private String containerBeanName;
    private boolean conditionStatus = false;
    private List<IBasicDTO> linesDTOList = new ArrayList<IBasicDTO>();
    private String fullURL;
    private Long conditionCode;

    public ConditionLinesdetailsHelperBean() {
        intiatDataForPopUp();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        return app;
    }

    private void intiatDataForPopUp() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.get("civilId") != null && params.get("trSerial") != null) {
            civilId = Long.valueOf(params.get("civilId"));
            tabRecSerial = Long.valueOf(params.get("trSerial"));
            loadData();
        }


    }
    //TODO move business in BO and reImplement method

    public void loadData() {
        ICheckCivilIdOnConditionDTO checkCivilIdOnConditionDTO = GrsDTOFactory.createCheckCivilIdOnConditionDTO();
        checkCivilIdOnConditionDTO.setCivilId(civilId);
        checkCivilIdOnConditionDTO.setRTabrecSerial(tabRecSerial);
        ICheckCivilIdOnConditionDTO condRelationDTO = null;
        ICheckCivilIdOnLineDTO checkCivilIdOnLineDTO = GrsDTOFactory.createCheckCivilIdOnLineDTO();
        IKwtCitizensResidentsEntityKey entityKey =
            InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(Long.valueOf(civilId));
        setExceptionFromCondition(false);
        //first get employee name by civilId
        try {
            IKwtCitizensResidentsDTO dto =
                (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidents(entityKey);
            setEmpName(dto.getFullName());
        } catch (Exception e) {
            e.printStackTrace();
            setEmpName(null);
        }
        // second check condition on this employee by civilId and tabRecSerila
        try {
            condRelationDTO =
                    GrsClientFactory.getConditionsClient().checkCivilIdOnConditionByRTabrecSerial(checkCivilIdOnConditionDTO);

            if (condRelationDTO != null) {
                // after check condition set condition status
                if (condRelationDTO.getStatus() == 1) {
                    setConditionStatus(true);
                } else {
                    setConditionStatus(false);
                }
                conditionCode = condRelationDTO.getConditionCode();
                // get exception list in this condition
                List<IExceptionsDTO> exceptionList =
                    (List)GrsClientFactory.getExceptionsClient().getByCondCodeParmValAndRtabSer(conditionCode,
                                                                                                tabRecSerial, civilId);
                exceptionType = "";
                if (exceptionList != null && exceptionList.size() != 0) {
                    setExceptionFromCondition(true);
                    if (exceptionList.get(0).getParameterTypesDTO() != null) {
                        exceptionType = exceptionList.get(0).getParameterTypesDTO().getCode().getKey();

                    }
                }
                setConditionName(condRelationDTO.getConditionName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // get lines lis on this condition need to be one method in BO and return one DTO of type IEmployeeLinesDTO
            linesDTOList = GrsClientFactory.getConditionDetailsClient().findConditionDetailsList(conditionCode);
            if (linesDTOList != null && linesDTOList.size() != 0) {
                for (int i = 0; i < linesDTOList.size(); i++) {
                    ICheckCivilIdOnLineDTO resultLineDTO = GrsDTOFactory.createCheckCivilIdOnLineDTO();
                    IEmployeeLinesDTO emplineDTO = ((IEmployeeLinesDTO)linesDTOList.get(i));
                    ILinesDTO lineDTO = emplineDTO.getLinesDTO();
                    // get actual value of employee that make this employee pass condition
                    lineDTO.setActualLineValue(getActualValue(lineDTO.getParametersDTO().getCode().getKey(), civilId));
                    //fil CheckCivilIdOnLineDTO to check line
                    resultLineDTO.setCivilId(civilId);
                    resultLineDTO.setLineCode(Long.valueOf(lineDTO.getCode().getKey()));
                    resultLineDTO.setConditionCode(condRelationDTO.getConditionCode());
                    resultLineDTO.setTabrecSerial(condRelationDTO.getTabrecSerial());
                    // check line
                    try {
                        checkCivilIdOnLineDTO =
                                GrsClientFactory.getLinesClient().checkCivilIdOnLineByRTabrecSerial(resultLineDTO);
                        emplineDTO.setLineStatus(checkCivilIdOnLineDTO.getStatus());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // finally get exception on line
                    emplineDTO.setExceptionDTO(getExceptionForLine(resultLineDTO.getLineCode(), tabRecSerial,
                                                                   civilId));
                    if (emplineDTO.getExceptionDTO() != null) {
                        emplineDTO.setExceptionStatus(emplineDTO.getExceptionDTO().getStatus());
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            linesDTOList = new ArrayList<IBasicDTO>();
        }
        if (getDivName() != null)
            getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + getDivName() + ");");


    }

    public String getActualValue(String parameterCode, Long civilId) {
        String actualValue = "";
        try {
            actualValue =
                    GrsClientFactory.getLinesClient().getActualLineValue(Long.valueOf(parameterCode), civilId.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actualValue;
    }

    public IExceptionsDTO getExceptionForLine(Long lineCode, Long tabRecSerial, Long civilId) {
        IExceptionsDTO exceptionsDTO = GrsDTOFactory.createExceptionsDTO();
        try {
            List<IExceptionsDTO> exceptionList =
                (List)GrsClientFactory.getExceptionsClient().getByLineCodeParmValAndRtabSer(lineCode, tabRecSerial,
                                                                                            civilId);
            if (exceptionList != null && exceptionList.size() != 0) {
                exceptionsDTO.setStatus(1L);
                if (exceptionList.get(0).getParameterTypesDTO() != null) {
                    exceptionsDTO.setExceptionType(Long.valueOf(exceptionList.get(0).getParameterTypesDTO().getCode().getKey()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionsDTO = GrsDTOFactory.createExceptionsDTO();
        }
        return exceptionsDTO;
    }


    protected BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    /************************* getter & setter **************************/

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setTabRecSerial(Long tabRecSerial) {
        this.tabRecSerial = tabRecSerial;
    }

    public Long getTabRecSerial() {
        return tabRecSerial;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getDivName() {
        return divName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionFromCondition(boolean exceptionFromCondition) {
        this.exceptionFromCondition = exceptionFromCondition;
    }

    public boolean isExceptionFromCondition() {
        return exceptionFromCondition;
    }

    public void setLinesDTOList(List<IBasicDTO> linesDTOList) {
        this.linesDTOList = linesDTOList;
    }

    public List<IBasicDTO> getLinesDTOList() {
        return linesDTOList;
    }

    public void setContainerBeanName(String containerBeanName) {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName() {
        return containerBeanName;
    }

    public void setConditionStatus(boolean conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    public boolean isConditionStatus() {
        return conditionStatus;
    }

    public void constructConditionDetailsPagePath(String condCode, String url) {
        if (url == null)
            url = PAGE_URI;
        if (condCode == null & conditionCode != null)
            condCode = String.valueOf(conditionCode);
        fullURL = constructPageURL(condCode, String.valueOf(civilId), String.valueOf(tabRecSerial), url);
        System.out.println(fullURL);
    }

    public static String constructPageURL(String condCode, String civilId, String trSerial, String url) {
        StringBuilder uri = new StringBuilder(url);
        uri.append("?");
        if (condCode != null) {
            uri.append("condCode").append("=").append(condCode);
            uri.append("&");
        }
        uri.append("civilId").append("=").append(civilId);
        uri.append("&");
        uri.append("trSerial").append("=").append(trSerial);
        return uri.toString();
    }

    public void viewConditionlinesDetails() {
        if (JSFHelper.getSession().getAttribute("tabrecSerial") != null &&
            JSFHelper.getSession().getAttribute("civilId") != null) {
            civilId = (Long)JSFHelper.getSession().getAttribute("civilId");
            tabRecSerial = (Long)JSFHelper.getSession().getAttribute("tabrecSerial");
        }
        constructConditionDetailsPagePath(null, null);
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public void setConditionCode(Long conditionCode) {
        this.conditionCode = conditionCode;
    }

    public Long getConditionCode() {
        return conditionCode;
    }

}
