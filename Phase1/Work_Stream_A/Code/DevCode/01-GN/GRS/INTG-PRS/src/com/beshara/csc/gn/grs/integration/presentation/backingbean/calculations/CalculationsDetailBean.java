package com.beshara.csc.gn.grs.integration.presentation.backingbean.calculations;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICalculationDetailsDTO;
import com.beshara.csc.gn.grs.business.dto.ILinesDTO;
import com.beshara.csc.gn.grs.business.dto.IParametersDTO;
import com.beshara.csc.gn.grs.business.entity.CalculationDetailsEntityKey;
import com.beshara.csc.gn.grs.business.shared.IGrsConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


public class CalculationsDetailBean extends ManyToManyDetailsMaintain  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private final static String BEAN_NAME = "calculationsDetailBean";
    protected final static String PAGE_NAV_KEY = "calculation_Details";

    private List<IBasicDTO> joinConditions;
    private static long inc = 1;
    private transient HtmlCommandButton readLineBtn;
    private String joinConditionSelectedValue;
    private String queryOverview = null;
    private ILinesDTO linesDTODetails = GrsDTOFactory.createLinesDTO();

    private String paraName;
    private String lineSign;
    private String overViewLine = "";
    private String lineValue = "";
    private String parameterCode = getVirtualConstValue();
    private List parametersList = new ArrayList();
 //   private List<IParameterValuesDTO> lineValueslist = new ArrayList<IParameterValuesDTO>();
   ICalculationDetailsDTO details = GrsDTOFactory.createCalculationDetailsDTO();
    private Serializable maintainObj;
    private boolean mainLinesDetails  ;
    private boolean alternativeLinesDetails  ;
    private String currentStep;
    private String previousNavigationCase;
    
    private final Long parameterTypeCode = IGrsConstants.PARAM_TYPE_EMPLOYEE;
    private final Long parameterStatus = IGrsConstants.CONDITION_STATUS_ACTIVE;
    
    public String valueRadioType = "0";

    public CalculationsDetailBean() {
        setCurrentStep("calculationDetailsMaintain"); //map key for first step(exist in wizard bar)
        setPreviousNavigationCase("calculationMainDataMaintain"); //map key for second step(exist in wizard bar)
//        setCustomDiv1(getCustomDiv1() + " extraWideDiv");
//        setLookupAddDiv("divSearch");
//        setContent1Div("module_tabs_cont1");
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSearchTyp("0");
        
            
    }

    public static CalculationsDetailBean getInstance() {
        return (CalculationsDetailBean)JSFHelper.getValue(BEAN_NAME);
    }
    public void setValueRadioType(String valueRadioType) {
        this.valueRadioType = valueRadioType;
    }

    public String getValueRadioType() {
        return valueRadioType;
    }
    /**
     * Purpose: this method handle show and hide divs
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowSearch(false);
        app.setShowCustomDiv1(false);
        app.setShowCustomDiv2(true);
        app.setShowLovDiv(true);
        return app;
    }
    
    public String getVirtualConstValue(){
        return "";
    }
    
    public void showSearchListOfValuesDiv() {
        Integer no = (Integer)getReadLineBtn().getAttributes().get("selectedRowNo");
        setRowNo(no);
        getLovBaseBean().setCodeTypeString(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setKeyIndex(1L);
        if (isSearchMode() == true) {
            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().setCleanDataTableFlage(true);

        } else {
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setCleanDataTableFlage(false);
            getLovBaseBean().setSearchQuery("");
            getLovBaseBean().setSearchTyp("0");
        }

        getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnObjectTypeLovDiv");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchObjectTypeLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchObjectTypeLovDiv");
        
        getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + ";focusHighlightedNode();");
        if (getLovBaseBean().isSearchMode() == false) {
            getLovBaseBean().setSearchQuery("");
        }
       
    }
    
     private int rowNo;
    public String searchObjectTypeLovDiv(Object searchType, Object searchQuery) {
       
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                String objectSearchQuery= searchQuery.toString();
                String objectSearchType=searchType.toString();
                if(valueRadioType.equals(objectSearchType) ){
                    result = GrsClientFactory.getParametersClient().getParametersForCalc(Long.valueOf(objectSearchQuery) ,null);
                }else{
                    result = GrsClientFactory.getParametersClient().getParametersForCalc(null ,objectSearchQuery);    
                }
                
                if(result != null && result .size() != 0  ){
                  getLovBaseBean().setMyTableData(result); 
                  getLovBaseBean().setSearchTyp(searchType.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        return "";
    }
    /*
     * Cancel Search
     */
    public String cancelSearchObjectTypeLovDiv() {
        getLovBaseBean().setCodeTypeString(false);
        setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        return "";
    }
    
    /*
     * return method highlight
     * focus ObjectType
     */
    
    
    
    
        public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getRowNo() {
        return rowNo;
    }
        
        
    public String returnObjectTypeLovDiv() {
      getLovBaseBean().setSearchMode(true);
      
      IParametersDTO dto = (IParametersDTO)getLovBaseBean().getSelectedDTOS().get(0);
      
        ICalculationDetailsDTO returnDTO = (ICalculationDetailsDTO)getCurrentDisplayDetails().get(getRowNo());
        
        returnDTO.setParameterCode(dto.getCode().getKey());
        
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        setSelectedRadio("");
        setSearchMode(true);
        return "";
    }
    
    public void initializeRow() {
        details = GrsDTOFactory.createCalculationDetailsDTO();
        details.setStatusFlag(ISystemConstant.ADDED_ITEM);

       
       details.setCode(new CalculationDetailsEntityKey(inc++, 0L));
//        ILinesDTO line = GrsDTOFactory.createLinesDTO();
//        line.setCode(new EntityKey(inc++));
//        details.setCode(new CalculationDetailsEntityKey(inc++, 0L));
//        line.setStatusFlag(ISystemConstant.ADDED_ITEM);
//        line.setName(getTemporaryName());
//        details.setLinesDTO(line);
//        if (details.getJoinConditionsDTO() == null) {
//            IJoinConditionsDTO joinCon = GrsDTOFactory.createJoinConditionsDTO();
//            joinCon.setCode(new JoinConditionsEntityKey(""));
//            details.setJoinConditionsDTO(joinCon);
//        }

    }

    public String previous() {          
       return "calculation_Main_Data";
    }
    public void addNewRow() {
        System.out.println("**************************************************************");
        initializeRow();
        CalculationsMaintainBean calculationsMaintainBean =
            (CalculationsMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{calculationsMaintainBean}").getValue(FacesContext.getCurrentInstance());
        if (calculationsMaintainBean.getMaintainMode() == 1) {
            details.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
            details.setDetailOrder(new Long(getCurrentDetails().size() + 1));
        } else {
  
          details.setDetailOrder(new Long(getCurrentDetails().size() + 1));
        }
        getCurrentDetails().add(details);
    }
    
    public void injectionRow() {
        if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() != 0) {
            initializeRow();
            int index =
                (Integer.parseInt(((ICalculationDetailsDTO)getSelectedCurrentDetails().get(0)).getDetailOrder().toString())) -
                1;
            //details.setCode(new CalculationDetailsEntityKey((Long)((CalculationDetailsDTO)getSelectedCurrentDetails().get(0)).getCode().getKeys()[0], new Long(index+1)));
            details.setDetailOrder(new Long(index + 1));
            getCurrentDetails().add(index, details);
        }
    }
    
   
    
    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return dto;
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        return dto;
    }

    public void overViewPanelLisentner(ValueChangeEvent v) {
        System.out.println("overViewPanelLisentner :" + v);
        overview();
        
    }
    
    public String overview() {
        queryOverview = "";
        List<ICalculationDetailsDTO> temp = (List)getCurrentDetails();
        for (ICalculationDetailsDTO dto : temp) {
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
        SharedUtilBean shared_util = getSharedUtil();
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
        //TODO after add sign
//        if (dto.getJonconditionSign() != null) {
//            List<IJoinConditionsDTO> joinCon;
//            try {
//                joinCon = (List<IJoinConditionsDTO>)getJoinConditions();
//                for (IJoinConditionsDTO join : joinCon) {
//                    if (join.getCode().getKey().toString().equals(dto.getJonconditionSign()))
//                        queryOverview += "\n" +
//                                join.getName();
//                }
//            } catch (SharedApplicationException e) {
//                shared_util.setErrMsgValue("SystemFailureException");
//                e.printStackTrace();
//            } catch (DataBaseException e) {
//                shared_util.setErrMsgValue("SystemFailureException");
//                e.printStackTrace();
//            }
//
//        }
        System.out.println("------" + queryOverview);
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

    public static void setInc(long inc) {
        CalculationsDetailBean.inc = inc;
    }

    public static long getInc() {
        return inc;
    }

    public void setReadLineBtn(HtmlCommandButton readLineBtn) {
        this.readLineBtn = readLineBtn;
    }

    public HtmlCommandButton getReadLineBtn() {
        return readLineBtn;
    }
    

    

    public void setJoinConditionSelectedValue(String joinConditionSelectedValue) {
        this.joinConditionSelectedValue = joinConditionSelectedValue;
    }

    public String getJoinConditionSelectedValue() {
        return joinConditionSelectedValue;
    }

    public void setQueryOverview(String queryOverview) {
        this.queryOverview = queryOverview;
    }

    public String getQueryOverview() {
        if (getCurrentDetails().size() > 0)
            overview();
        return queryOverview;
    }

    public void setLinesDTODetails(ILinesDTO linesDTODetails) {
        this.linesDTODetails = linesDTODetails;
    }

    public ILinesDTO getLinesDTODetails() {
        return linesDTODetails;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaName() {
        return paraName;
    }

    public void setLineSign(String lineSign) {
        this.lineSign = lineSign;
    }

    public String getLineSign() {
        return lineSign;
    }

    public void setOverViewLine(String overViewLine) {
        this.overViewLine = overViewLine;
    }

    public String getOverViewLine() {
        return overViewLine;
    }

    public void setLineValue(String lineValue) {
        this.lineValue = lineValue;
    }

    public String getLineValue() {
        return lineValue;
    }

    public void setMaintainObj(Serializable maintainObj) {
        this.maintainObj = maintainObj;
    }

    public Serializable getMaintainObj() {
        return maintainObj;
    }

    public void setMainLinesDetails(boolean mainLinesDetails) {
        this.mainLinesDetails = mainLinesDetails;
    }

    public boolean isMainLinesDetails() {
        return mainLinesDetails;
    }

    public void setAlternativeLinesDetails(boolean alternativeLinesDetails) {
        this.alternativeLinesDetails = alternativeLinesDetails;
    }

    public boolean isAlternativeLinesDetails() {
        return alternativeLinesDetails;
    }

    
    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getParameterCode() {
        return parameterCode;
    }
    public void setParametersList(List parametersList) {
        this.parametersList = parametersList;
    }

    public List getParametersList() throws DataBaseException, 
                                           SharedApplicationException {
        /* Edit by Amr Abdel Halim*/
        try {
                parametersList = GrsClientFactory.getParametersClient().getParametersForCalc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parametersList;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setPreviousNavigationCase(String previousNavigationCase) {
        this.previousNavigationCase = previousNavigationCase;
    }

    public String getPreviousNavigationCase() {
        return previousNavigationCase;
    }
    
     public void delete() {
        for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
            IBasicDTO selected = getSelectedCurrentDetails().get(i);
            if (selected.getStatusFlag() == null) {
                selected.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                getSelectedCurrentDetails().remove(i);
                i--;
            }
            if (selected.getStatusFlag().longValue() == ISystemConstant.ADDED_ITEM.longValue() ||
                selected.getStatusFlag().longValue() == ISystemConstant.ADDED_LAST_ITEM.longValue()) {
                getCurrentDetails().remove(selected);
                //availableDetails.add(selected);
                getSelectedCurrentDetails().remove(i);
                i--;
            }
        }
        refreshDetailsOrdersAndKeys();
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), this.getCurrentDetails());
        this.resetSelection();
    }
    
    public void refreshDetailsOrdersAndKeys(){
         long newINC = 1;
         for(int i=0;i<getCurrentDetails().size();i++){
             ((ICalculationDetailsDTO)getCurrentDetails().get(i)).setDetailOrder(newINC);
             getCurrentDetails().get(i).setCode(new CalculationDetailsEntityKey(newINC++, 0L));    
         }
         this.inc = newINC;
    }
    
    public boolean validate() {
        int rightArcsLength = 0;
        int leftArcsLength = 0;
        int lineCount = 0;
        SharedUtilBean shared_util = getSharedUtil();
        List<ICalculationDetailsDTO> calculationDetailsDTOList = (List)getCurrentDisplayDetails();
        int calculationDetailsDTOLength = calculationDetailsDTOList.size();
        if(calculationDetailsDTOLength == 0){
            shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustAddOneRow"));
            return false ;
        }
        for (ICalculationDetailsDTO dto : calculationDetailsDTOList) {

            if (dto.getRightArcs() != null) {
                rightArcsLength += dto.getRightArcs().length();
            }
            if (dto.getLeftArcs() != null) {
                leftArcsLength += dto.getLeftArcs().length();
                if (leftArcsLength != rightArcsLength) {
                    shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("bracketsNotEqual"));
                    return false;
                }
            }
            lineCount++;
            
                
            if (dto.getParameterCode().equals("")  && dto.getValue() ==null) {
                    shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustEnterParamOrVal"));
                    return false;
                
            }else if (dto.getValue() != null && !dto.getOperatorSign().equals("") && dto.getParameterCode().equals("")) {
                   shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustEnterPramOrRemoveOperator"));
                   return false;
            }else if (!dto.getParameterCode().equals("") && !dto.getOperatorSign().equals("") && dto.getValue()==null) {
                    shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustEnterValueOrRemoveOperator"));
                    return false;
                    
            }else if (!dto.getParameterCode().equals("") && dto.getValue()!=null) {
                if (dto.getOperatorSign()==null ||dto.getOperatorSign().equals("")) {
                    shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("OperationMustHasoperater"));
                    return false;
                }
            }
            
            if (calculationDetailsDTOLength == lineCount && !"".equals(dto.getOperatorSignBetweenLines())  ) {
                shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustRemoveOperatorFromLastRow"));
                return false;                      
            }else if (calculationDetailsDTOLength != lineCount && "".equals(dto.getOperatorSignBetweenLines())) {
                shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustAddOperatorBetweenOperations"));
                return false;
                    //shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("mustAddOperatorBetweenOperations"));
                    
            }
       
        }
        
       
        if (rightArcsLength != leftArcsLength) {
              shared_util.setErrMsgValue(getSharedUtil().getResourceBundle(CalculationsMaintainBean.BUNDLE_NAME).getString("bracketsNotEqual"));
            return false;
        }
        return super.validate();
    }


    private IBasicDTO getElementByKey(List<IBasicDTO> dataList, String key) {
        for (IBasicDTO basicDTO : dataList) {
            if (basicDTO.getCode().getKey().equals(key)) {
                return basicDTO;
            }
        }
        return null;
    }  
}
