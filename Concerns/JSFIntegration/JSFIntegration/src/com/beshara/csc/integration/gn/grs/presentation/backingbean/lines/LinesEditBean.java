package com.beshara.csc.gn.grs.presentation.backingbean.lines;

import com.beshara.base.dto.ClientDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.ILinesClient;
import com.beshara.csc.gn.grs.business.client.IOperatorsClient;
import com.beshara.csc.gn.grs.business.client.IParametersClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ILineValuesDTO;
import com.beshara.csc.gn.grs.business.dto.ILinesDTO;
import com.beshara.csc.gn.grs.business.dto.IOperatorsDTO;
import com.beshara.csc.gn.grs.business.dto.IParameterValuesDTO;
import com.beshara.csc.gn.grs.business.dto.IParametersDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IParametersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.Helper;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class LinesEditBean extends LookupMaintainBaseBean {

    private List parametersList = new ArrayList();

    private List operatorsList = new ArrayList();

    private int availableListSize;

    private int pageIndexAdd = 0;

    private int selectedAvailableListSize;

    private boolean validSQL = false;

    private boolean tybeRadio = false;

    private Long mulitValue = -100L;

    List lineValueList = new ArrayList();

    private HtmlDataTable availableDataTable = new HtmlDataTable();

    private IParametersClient parametrClient = 
        GrsClientFactory.getParametersClient();

    private IOperatorsClient operatersClient = 
        GrsClientFactory.getOperatorsClient();

    protected List<IBasicDTO> availableValues = new ArrayList<IBasicDTO>();

    private HtmlDataScroller dataScroller = new HtmlDataScroller();

    private List<IBasicDTO> selectedAvailableValues = new ArrayList<IBasicDTO>();

    IParametersDTO parametersDTO =GrsDTOFactory.createParametersDTO();

    private IOperatorsDTO operatorsDTO =GrsDTOFactory.createOperatorsDTO();

    private boolean sucesseadding;

    private boolean failAdding;

    private String parameterCode;

    private String operationCode;

    private String value = "";

    private String overview = "";

    private boolean showValueDiv = false;

    private String lineOverView = "";

    private boolean checkAllFlagAvailable;


    public LinesEditBean() {
        try {
            setPageDTO(GrsDTOFactory.createLinesDTO()); //set this the page dto
            setClient((ILinesClient)GrsClientFactory.getLinesClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setUsingPaging(true);
        setContent1Div("divContent1Line");
    }

    public void setParametersList(List parametersList) {
        this.parametersList = parametersList;
    }

    public List getParametersList() throws DataBaseException, 
                                           SharedApplicationException {
        try {

            parametersList = parametrClient.getCodeName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parametersList;
    }


    public void setParameterCode(String parameterCode) {
        System.out.println("calling setParameterCode");
        this.parameterCode = parameterCode;
    }

    public String getParameterCode() {
        System.out.println("calling getParameterCode");
        return parameterCode;
    }

    public String updateParameterDTO() {
        value = "";
        checkAllFlagAvailable = false;
        selectedAvailableValues = new ArrayList();
        if ((parameterCode != null) && !parameterCode.equals("")) {

            IEntityKey parameterEntityKey =  GrsEntityKeyFactory.createParametersEntityKey();
            try {
                parameterEntityKey = 
                        Helper.getEntityKey(parametersList, parameterCode);
                parametersDTO = 
                        (IParametersDTO)parametrClient.getById(parameterEntityKey);

                availableValues = 
                        parametrClient.getParameterValues(parametersDTO);

            } catch (SharedApplicationException e) {
                availableValues = new ArrayList();
            } catch (DataBaseException e) {
                availableValues = new ArrayList();
            } catch (Exception e) {
                availableValues = new ArrayList();
            }
        }

        return null;
    }


    public String saveLine() throws DataBaseException, Exception {
       
        if (getPageDTO() != null) {
            IParametersDTO parametersDTO =GrsDTOFactory.createParametersDTO();
            IOperatorsDTO operatorsDTO =GrsDTOFactory.createOperatorsDTO();

            if ((operationCode != null) && !operationCode.equals("")) {
                IEntityKey operatorEntityKey = GrsEntityKeyFactory.createParametersEntityKey();
                operatorEntityKey = 
                        Helper.getEntityKey(operatorsList, operationCode);
                operatorsDTO.setCode(operatorEntityKey);
                ((ILinesDTO)getPageDTO()).setOperatorsDTO(operatorsDTO);
            }
            if ((parameterCode != null) && !parameterCode.equals("")) {

                IEntityKey parameterEntityKey =GrsEntityKeyFactory.createParametersEntityKey();
                parameterEntityKey = 
                        Helper.getEntityKey(parametersList, parameterCode);
                parametersDTO.setCode(parameterEntityKey);

            }
            ILineValuesDTO lnValue =GrsDTOFactory.createLineValuesDTO();
            if (!((IParametersEntityKey)(((IParametersDTO)(((ILinesDTO)getPageDTO()).getParametersDTO())).getCode())).getParameterCode().equals(((IParametersEntityKey)parametersDTO.getCode()).getParameterCode())) {

                if (!validSQL) {
                    lineValueList = new ArrayList();
                    String[] lineValuesArray = value.split(",");


                    try {

                        if (mulitValue.intValue() == 0 || 
                            mulitValue.intValue() == -100) {
                            lnValue =GrsDTOFactory.createLineValuesDTO();
                            lnValue.setLnevalue(lineValuesArray[0]);
                            lineValueList.add(lnValue);
                            System.out.println("lineValueList.sixe" + 
                                               lineValueList.size());
                        }

                        else {
                            System.out.println("lineValueList.sixe" + 
                                               lineValueList.size());
                            System.out.println("in elseeeeeeeeeeeee");
                            for (String value: lineValuesArray) {
                                lnValue =GrsDTOFactory.createLineValuesDTO();
                                lnValue.setLnevalue(value);
                                lineValueList.add(lnValue);
                            }
                        }
                        System.out.println("lineValueList.sixe" + 
                                           lineValueList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("lineValueList.sixe" + 
                                       lineValueList.size());
                }

                ((ILinesDTO)getPageDTO()).setLineValuesList(lineValueList);
            }

            else {
                if (!validSQL) {
                    lineValueList = new ArrayList();
                    String[] lineValuesArray = value.split(",");


                    try {

                        if (mulitValue.intValue() == 0 || 
                            mulitValue.intValue() == -100) {
                            lnValue =GrsDTOFactory.createLineValuesDTO();
                            lnValue.setLnevalue(lineValuesArray[0]);
                            lineValueList.add(lnValue);
                            System.out.println("lineValueList.sixe" + 
                                               lineValueList.size());
                        }

                        else {
                            System.out.println("lineValueList.sixe" + 
                                               lineValueList.size());
                            System.out.println("in elseeeeeeeeeeeee");
                            for (String value: lineValuesArray) {
                                lnValue =GrsDTOFactory.createLineValuesDTO();
                                lnValue.setLnevalue(value);
                                lineValueList.add(lnValue);
                            }
                        }
                        System.out.println("lineValueList.sixe" + 
                                           lineValueList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("lineValueList.sixe" + 
                                       lineValueList.size());
                } else {
                    lineValueList = new ArrayList();
                    ILineValuesDTO lineValueDTO;
                    for (IBasicDTO basic: selectedAvailableValues) {
                        lineValueDTO = GrsDTOFactory.createLineValuesDTO();
                        lineValueDTO.setLnevalue(((IParameterValuesDTO)basic).getStrCode());
                        lineValueList.add(lineValueDTO);
                    }
                }
                if (tybeRadio) {

                    int oldListSize = 0;
                    
                    if (((ILinesDTO)getPageDTO()).getLineValuesList() == null)
                        ((ILinesDTO)getPageDTO()).setLineValuesList(new ArrayList());
                    
                    oldListSize = 
                            ((ILinesDTO)getPageDTO()).getLineValuesList().size();
                            
                    int count = 0;
                    for (int i = 0; i < oldListSize; i++) {


                        if (!((ILineValuesDTO)(((ILinesDTO)getPageDTO()).getLineValuesList().get(i))).getLnevalue().equals(((ILineValuesDTO)lineValueList.get(0)).getLnevalue())) {
                            count++;
                            lnValue = 
                                    (ILineValuesDTO)(((ILinesDTO)getPageDTO()).getLineValuesList()).get(i);
                                    
                            lnValue.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                            ((ILinesDTO)getPageDTO()).getLineValuesList().set(i, 
                                                                             lnValue);

                        }


                    }
                    if (count == oldListSize || oldListSize == 0) {
                        lnValue = ((ILineValuesDTO)lineValueList.get(0));
                        lnValue.setStatusFlag(ISystemConstant.ADDED_ITEM);
                        ((ILinesDTO)getPageDTO()).getLineValuesList().add(lnValue);

                    }

                }

                else {

                    int oldListSize = 0;
                    
                    if (((ILinesDTO)getPageDTO()).getLineValuesList() == null)
                        ((ILinesDTO)getPageDTO()).setLineValuesList(new ArrayList());
                    
                    oldListSize = 
                            ((ILinesDTO)getPageDTO()).getLineValuesList().size();
                    
                    int newListSize = lineValueList.size();
                    boolean found = false;
                    for (int i = 0; i < oldListSize; i++) {
                        found = false;
                        for (int j = 0; j < newListSize; j++) {
                            try {

                                if ((((ILineValuesDTO)(((ILinesDTO)getPageDTO()).getLineValuesList().get(i))).getLnevalue()).equals(((ILineValuesDTO)lineValueList.get(j)).getLnevalue())) {
                                    found = true;

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (!found) {
                            lnValue = 
                                    (ILineValuesDTO)(((ILinesDTO)getPageDTO()).getLineValuesList()).get(i);
                            lnValue.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                            ((ILinesDTO)getPageDTO()).getLineValuesList().set(i, 
                                                                             lnValue);
                        }

                    }

                    for (int j = 0; j < newListSize; j++) {
                        found = false;
                        for (int i = 0; i < oldListSize; i++) {
                            if ((((ILineValuesDTO)(((ILinesDTO)getPageDTO()).getLineValuesList().get(i))).getLnevalue()).equals(((ILineValuesDTO)lineValueList.get(j)).getLnevalue())) {
                                found = true;
                                ((ILineValuesDTO)((ILinesDTO)getPageDTO()).getLineValuesList().get(i)).setStatusFlag(null);
                            }


                        }

                        if (!found) {
                            lnValue = ((ILineValuesDTO)lineValueList.get(j));
                            lnValue.setStatusFlag(ISystemConstant.ADDED_ITEM);
                            ((ILinesDTO)getPageDTO()).getLineValuesList().add(lnValue);

                        }
                    }
                }


            }

            ((ILinesDTO)getPageDTO()).setParametersDTO(parametersDTO);

            super.setSelectedPageDTO(getPageDTO());
        }

        super.edit();

        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        LinesListBean linesList = 
            (LinesListBean)app.createValueBinding("#{linesListBean}").getValue(fc);
        
        if (isUsingPaging()) {
            
            if (linesList != null) {
                
                linesList.setRepositionTable(true);
                linesList.setSortedTable(false);
                
                if (getPageDTO() != null && getPageDTO().getCode() != null) {
                
                    if (linesList.getHighlightedDTOS() != null) {
                        linesList.getHighlightedDTOS().clear();
                        linesList.getHighlightedDTOS().add(getPageDTO());
                    }
                    
                    linesList.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

                }
                
            }

        } else {
        
            super.setMyTableData(linesList.getMyTableData());
            super.setMyDataTable(linesList.getMyDataTable());
    
    
            if (getPageDTO() != null && getPageDTO().getCode() != null) {
    
                linesList.getHighlightedDTOS().add(getPageDTO());
                setMyTableData(linesList.getMyTableData());
                super.getPageIndex((String)getPageDTO().getCode().getKey());
    
            }
            
        }
        
        return "successedit";
        
    }

    public void saveLineNew() throws Exception {
        
        if (getPageDTO() != null) {
            
            IParametersDTO parametersDTO =GrsDTOFactory.createParametersDTO();
            IOperatorsDTO operatorsDTO =GrsDTOFactory.createOperatorsDTO();
            
            if ((parameterCode != null) && !parameterCode.equals("")) {

                IEntityKey parameterEntityKey =GrsEntityKeyFactory.createParametersEntityKey();
                parameterEntityKey = 
                        Helper.getEntityKey(parametersList, parameterCode);
                parametersDTO.setCode(parameterEntityKey);
                ((ILinesDTO)getPageDTO()).setParametersDTO(parametersDTO);


            }
            
            if ((operationCode != null) && !operationCode.equals("")) {
                IEntityKey operatorEntityKey = GrsEntityKeyFactory.createParametersEntityKey();
                operatorEntityKey = 
                        Helper.getEntityKey(operatorsList, operationCode);
                operatorsDTO.setCode(operatorEntityKey);
                ((ILinesDTO)getPageDTO()).setOperatorsDTO(operatorsDTO);
            }
            
            ((ILinesDTO)getPageDTO()).setLineValuesList(lineValueList);

        }
        
        super.saveAndNew();
        
        if (isUsingPaging()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            Application app = fc.getApplication();
            LinesListBean linesList = 
                (LinesListBean)app.createValueBinding("#{linesListBean}").getValue(fc);
            
            if(linesList != null) {
                linesList.setUpdateMyPagedDataModel(true);
                linesList.resetPageIndex();
                linesList.getHighlightedDTOS().clear();
                linesList.getHighlightedDTOS().add(getPageDTO());
            }
        }

        if (getPageDTO() == null || 
            (getPageDTO() != null && getPageDTO().getCode() == null)) {
            setFailAdding(true);
            setSucesseadding(false);
        } else if (getPageDTO() != null && getPageDTO().getCode() != null) {
            setFailAdding(false);
            setSucesseadding(true);
        }
        
        getSharedUtil().setErrMsgValue(null);
        getSharedUtil().setSuccessMsgValue(null);
        
        super.setPageDTO(GrsDTOFactory.createLinesDTO());
        
        parameterCode = "";
        operationCode = "";
        lineOverView = "";
        value = "";

    }

    public void selectedAvailableAll(ActionEvent event) throws Exception {
        try {
            selectedAvailableValues = new ArrayList();
            IClientDTO selected;
            System.out.println(isCheckAllFlag() + " ");
            int first = this.getAvailableDataTable().getFirst();
            for (int j = first; j < first + 8; j++) {

                this.getAvailableDataTable().setRowIndex(j);
                System.out.println(" " + 
                                   this.getAvailableDataTable().getRowData());
                selected = 
                        (IClientDTO)this.getAvailableDataTable().getRowData();

                System.out.println(selected.getName());

                if (checkAllFlagAvailable == true) {

                    IClientDTO checkedDTO;

                    for (int i = 0; i < availableValues.size(); i++) {
                        checkedDTO = new ClientDTO();
                        if (((IParameterValuesDTO)availableValues.get(i)).getStrCode().equals(((IParameterValuesDTO)selected).getStrCode())) {
                            checkedDTO = (IClientDTO)availableValues.get(i);
                            checkedDTO.setChecked(true);
                            availableValues.set(i, checkedDTO);
                            selectedAvailableValues.add(checkedDTO);
                        }
                    }


                }
                if (checkAllFlagAvailable == false) {

                    IClientDTO checkedDTO;

                    for (int i = 0; i < availableValues.size(); i++) {
                        checkedDTO = new ClientDTO();
                        if (((IParameterValuesDTO)availableValues.get(i)).getStrCode().equals(((IParameterValuesDTO)selected).getStrCode())) {
                            checkedDTO = (IClientDTO)availableValues.get(i);
                            checkedDTO.setChecked(false);
                            availableValues.set(i, checkedDTO);
                            try {
                                selectedAvailableValues.remove(checkedDTO);
                            } catch (Exception e) {
                            }
                        }
                    }

                }


            }


        } catch (Exception e) {
        }


    }


    public void changeOverViewByValueField(ActionEvent ae) {
        if (!validSQL) {
            if (value != null)
                lineOverView += value;
        }
    }

    public void reInitializePageDTO() {

    }

    public void setSucesseadding(boolean sucesseadding) {
        this.sucesseadding = sucesseadding;
    }

    public boolean isSucesseadding() {
        return sucesseadding;
    }

    public void setOperatorsList(List operatorsList) {
        this.operatorsList = operatorsList;
    }

    public List getOperatorsList() {
        try {
            operatorsList = operatersClient.getCodeName();
        } catch (DataBaseException e) {
            // TODO
        }
        return operatorsList;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setAvailableValues(List<IBasicDTO> availableValues) {
        this.availableValues = availableValues;
    }

    public List<IBasicDTO> getAvailableValues() throws DataBaseException {

        //if null get it from the database
        if ((availableValues == null || availableValues.size() == 0)) {
            try {
                availableValues = 
                        parametrClient.getParameterValues(parametersDTO);

            } catch (Exception e) {
                setAvailableValues(new ArrayList());
            }
        }
        if (availableValues == null)
            availableValues = new ArrayList();

        try {
            IClientDTO checkedDTO;
            if (selectedAvailableValues != null && 
                selectedAvailableValues.size() != 0)
                for (int i = 0; i < availableValues.size(); i++) {
                    for (IBasicDTO basicSelected: selectedAvailableValues) {
                        if (((IParameterValuesDTO)basicSelected).getStrCode().equals(((IParameterValuesDTO)availableValues.get(i)).getStrCode())) {
                            checkedDTO = new ClientDTO();
                            checkedDTO = (IClientDTO)availableValues.get(i);
                            checkedDTO.setChecked(true);
                            availableValues.set(i, checkedDTO);
                            break;
                        }

                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //         selectedAvailableValues=new ArrayList();
        return availableValues;
    }

    public void radioChanged(ActionEvent ae) throws Exception {


        IClientDTO selected = 
            (IClientDTO)this.getAvailableDataTable().getRowData();
        selectedAvailableValues = new ArrayList();
        this.getSelectedAvailableValues().add(selected);

    }

    public String chooseCheckOrRadio() {
        value = "";
        checkAllFlagAvailable = false;

        if ((operationCode != null) && !operationCode.equals("")) {

            IEntityKey operatorEntityKey =GrsEntityKeyFactory.createParametersEntityKey();
            try {
                operatorEntityKey = 
                        Helper.getEntityKey(operatorsList, operationCode);
                operatorsDTO = 
                        (IOperatorsDTO)operatersClient.getById(operatorEntityKey);

                if (parametersDTO != null) {
                    checkAllFlagAvailable = false;
                    selectedAvailableValues = new ArrayList();
                    availableValues = 
                            parametrClient.getParameterValues(parametersDTO);
                }
                if (operatorsDTO.getMultiValues().intValue() == 
                    ISystemConstant.OPERATOR_SINGLE_VALUED) {
                    tybeRadio = true;
                    mulitValue = 0L;
                } else {

                    tybeRadio = false;
                    if (operatorsDTO.getMultiValues().intValue() == 
                        ISystemConstant.OPERATOR_DOUBLE_VALUED)
                        mulitValue = 2L;
                    else
                        mulitValue = 3L;
                }
            } catch (SharedApplicationException e) {
                // TODO
            } catch (DataBaseException e) {
                // TODO
            } catch (Exception e) {
                // TODO
            }
        }
        //        else{
        //            validSQL=false;
        //        }
        return null;
    }

    public void clearList(ActionEvent ae) {

        selectedAvailableValues = new ArrayList();
    }

    public void selectedAvailable(ActionEvent event) throws Exception {
        try {
            selectedAvailableValues = new ArrayList();

            IClientDTO checkedDTO;

            IClientDTO selected = 
                (IClientDTO)this.getAvailableDataTable().getRowData();

            for (int i = 0; i < availableValues.size(); i++) {
                checkedDTO = new ClientDTO();
                if (((IParameterValuesDTO)availableValues.get(i)).getStrCode().equals(((IParameterValuesDTO)selected).getStrCode())) {
                    checkedDTO = (IClientDTO)availableValues.get(i);
                    checkedDTO.setChecked(selected.getChecked());


                    availableValues.set(i, checkedDTO);
                }
            }


            for (int i = 0; i < availableValues.size(); i++) {
                checkedDTO = new ClientDTO();
                checkedDTO = (IClientDTO)availableValues.get(i);
                if (checkedDTO.getChecked() != null && checkedDTO.getChecked())
                    selectedAvailableValues.add(checkedDTO);

            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }

    public void addValues() {
        value = "";
        ILineValuesDTO lineValueDTO;
        lineValueList = new ArrayList();
        int i=0;
        for (IBasicDTO basic: selectedAvailableValues) {
            
            lineValueDTO = GrsDTOFactory.createLineValuesDTO();
            value += basic.getName();
            lineValueDTO.setLnevalue(((IParameterValuesDTO)basic).getStrCode());
            lineValueList.add(lineValueDTO);
            
        if(i<selectedAvailableValues.size()-1)    
            {
            value +=",";
            i++;
            }
        
        }
    }

    public String showDiv() {
        showValueDiv = true;
        return null;
    }

    public void setAvailableDataTable(HtmlDataTable availableDataTable) {
        this.availableDataTable = availableDataTable;
    }

    public HtmlDataTable getAvailableDataTable() {
        return availableDataTable;
    }

    public void setSelectedAvailableValues(List<IBasicDTO> selectedAvailableValues) {
        this.selectedAvailableValues = selectedAvailableValues;
    }

    public List<IBasicDTO> getSelectedAvailableValues() {
        return selectedAvailableValues;
    }

    public void setShowValueDiv(boolean showValueDiv) {
        this.showValueDiv = showValueDiv;
    }

    public boolean isShowValueDiv() {
        return showValueDiv;
    }

    public void scrollerAction(ActionEvent ae) {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);");
        //pageIndexAdd=((HtmlDataScroller)ae.getComponent()).getPageIndex();

    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setAvailableListSize(int availableListSize) {
        this.availableListSize = availableListSize;
    }

    public int getAvailableListSize() throws DataBaseException {
        if (availableValues != null)
            availableListSize = getAvailableValues().size();
        return availableListSize;
    }

    public void setParametersDTO(IParametersDTO parametersDTO) {
        this.parametersDTO = parametersDTO;
    }

    public IParametersDTO getParametersDTO() {
        return parametersDTO;
    }

    public void setValidSQL(boolean validSQL) {
        this.validSQL = validSQL;
    }

    public boolean isValidSQL() {
        if (parameterCode != null && !parameterCode.equals("") && 
            parametersDTO.isValidSQL() && operationCode != null && 
            !operationCode.equals(""))
            validSQL = true;
        else
            validSQL = false;
        return validSQL;
    }

    public void setTybeRadio(boolean tybeRadio) {
        this.tybeRadio = tybeRadio;
    }

    public boolean isTybeRadio() {
        if (operatorsDTO != null && operatorsDTO.getMultiValues() != null)
            if (operatorsDTO.getMultiValues().intValue() == 
                ISystemConstant.OPERATOR_SINGLE_VALUED) {
                tybeRadio = true;
                mulitValue = 0L;
            } else {

                tybeRadio = false;
                if (operatorsDTO.getMultiValues().intValue() == 
                    ISystemConstant.OPERATOR_DOUBLE_VALUED)
                    mulitValue = 2L;
                else
                    mulitValue = 3L;
            }
        return tybeRadio;
    }

    public void setLineValueList(List lineValueList) {
        this.lineValueList = lineValueList;
    }

    public List getLineValueList() {
        return lineValueList;
    }

    public String changeLineOverView(ActionEvent ae) {
        lineOverView = "";
        IParametersDTO parameterDTO = null;
        IOperatorsDTO operatorDTO = null;

        IEntityKey entityKey =GrsEntityKeyFactory.createParametersEntityKey();

        if ((parameterCode != null) && !parameterCode.equals("")) {


            try {
                entityKey = Helper.getEntityKey(parametersList, parameterCode);
                parameterDTO = 
                        (IParametersDTO)(parametrClient.getById(entityKey));

            } catch (ItemNotFoundException e) {
                // TODO
            } catch (Exception e) {
                // TODO
            }
        }
        if (parameterDTO != null) {
            lineOverView += parameterDTO.getName();
        }
        if ((operationCode != null) && !operationCode.equals("")) {
            try {
                entityKey = Helper.getEntityKey(operatorsList, operationCode);
                operatorDTO = (IOperatorsDTO)operatersClient.getById(entityKey);

            } catch (ItemNotFoundException e) {
                // TODO
            } catch (Exception e) {
                // TODO
            }
        }
        if (operatorDTO != null) {
            lineOverView += '/' + operatorDTO.getName();
        }
        if (value != null) {
            lineOverView += value;
        }

        return null;
    }

    public void setLineOverView(String lineOverView) {
        this.lineOverView = lineOverView;
    }

    public String getLineOverView() {
        lineOverView = "";
        IParametersDTO parameterDTO = null;
        IOperatorsDTO operatorDTO = null;

        IEntityKey entityKey = GrsEntityKeyFactory.createParametersEntityKey();

        if ((parameterCode != null) && !parameterCode.equals("")) {


            try {
                entityKey = Helper.getEntityKey(parametersList, parameterCode);
                parameterDTO = 
                        (IParametersDTO)(parametrClient.getById(entityKey));

            } catch (ItemNotFoundException e) {
                // TODO
            } catch (Exception e) {
                // TODO
            }
        }
        if (parameterDTO != null) {
            lineOverView += parametersDTO.getName();
        }
        if ((operationCode != null) && !operationCode.equals("")) {
            try {
                entityKey = Helper.getEntityKey(operatorsList, operationCode);
                operatorDTO = (IOperatorsDTO)operatersClient.getById(entityKey);

            } catch (ItemNotFoundException e) {
                // TODO
            } catch (Exception e) {
                // TODO
            }
        }
        if (operatorDTO != null) {
            lineOverView += ' ' + operatorDTO.getName();
        }
        if (value != null) {
            lineOverView += ' ' + value;
        }

        return lineOverView;
    }

    public void setMulitValue(Long mulitValue) {
        this.mulitValue = mulitValue;
    }

    public Long getMulitValue() {
        return mulitValue;
    }

    public void setFailAdding(boolean failAdding) {
        this.failAdding = failAdding;
    }

    public boolean isFailAdding() {
        return failAdding;
    }

    public void setCheckAllFlagAvailable(boolean checkAllFlagAvailable) {
        if (availableListSize == selectedAvailableListSize && 
            availableListSize != 0)
            this.checkAllFlagAvailable = true;
        else
            this.checkAllFlagAvailable = checkAllFlagAvailable;
    }

    public boolean isCheckAllFlagAvailable() {
        if (availableListSize == selectedAvailableListSize && 
            availableListSize != 0)
            return true;
        return checkAllFlagAvailable;
    }

    public void setOperatorsDTO(IOperatorsDTO operatorsDTO) {
        this.operatorsDTO = operatorsDTO;
    }

    public IOperatorsDTO getOperatorsDTO() {
        return operatorsDTO;
    }

    public void setSelectedAvailableListSize(int selectedAvailableListSize) {
        this.selectedAvailableListSize = selectedAvailableListSize;
    }

    public int getSelectedAvailableListSize() {
        if (selectedAvailableValues != null)
            selectedAvailableListSize = selectedAvailableValues.size();
        return selectedAvailableListSize;
    }

    public void setPageIndexAdd(int pageIndexAdd) {
        this.pageIndexAdd = pageIndexAdd;
    }

    public int getPageIndexAdd() {
        if (dataScroller != null) {
            pageIndexAdd = dataScroller.getPageIndex();
        }
        return pageIndexAdd;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowLookupEdit(true);
        app.setShowNavigation(true);
        return app;
    }
}
