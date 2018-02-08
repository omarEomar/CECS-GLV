package com.beshara.csc.gn.grs.presentation.backingbean.conditions;

import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.gn.grs.business.client.ILineValuesClient;
import com.beshara.csc.gn.grs.business.client.ILinesClient;
import com.beshara.csc.gn.grs.business.client.IParametersClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.IParametersDTO;
import com.beshara.csc.gn.grs.business.dto.ISearchConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class ConditionListBean extends ManyToManyListBaseBean {

    private List<IBasicDTO> linesList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> parametersList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> lineValuesList = new ArrayList<IBasicDTO>();
    private ILinesClient ilinesClient = GrsClientFactory.getLinesClient();
    private IParametersDTO parametersDTO = GrsDTOFactory.createParametersDTO();
    private String parameterCode;
    private ILineValuesClient ilineValuesClient = 
        GrsClientFactory.getLineValuesClient();
    private IParametersClient iparametersClient = 
        GrsClientFactory.getParametersClient();
    private ISearchConditionsDTO searchConditionsDTO = 
        GrsDTOFactory.createSearchConditionsDTO();
    private String itemSelectionRequiredValueString = 
        ISystemConstant.VIRTUAL_VALUE.toString();
    private String lineCodeText = itemSelectionRequiredValueString;
    private String parameterText = itemSelectionRequiredValueString;
    private boolean renderedBack = false;
    private boolean viewInCenter;

    public ConditionListBean() {
        setClient(GrsClientFactory.getConditionsClient());
        setPageDTO(GrsDTOFactory.createConditionsDTO());
        setSelectedPageDTO(GrsDTOFactory.createConditionsDTO());
        setEditNavigationCase("condition_Main_Data");
        setAddNavigationCase("condition_Main_Data");
        setEditBeanName("conditionsMaintainBean");
        setAddBeanName("conditionsMaintainBean");
    }

    public String goFrom() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        IntegrationBean integrationBean = 
            (IntegrationBean)app.createValueBinding("#{integrationBean}").getValue(ctx);
        integrationBean.setSelectedDTOTo(getSelectedDTOS());
        return integrationBean.goFrom();
    }

    public String goView() throws DataBaseException, 
                                  SharedApplicationException {
        IBasicDTO dto = getIntegrationBean().getSelectedDTOFrom().get(0);
        setSelectedDTOS(getIntegrationBean().getSelectedDTOFrom());
        if (viewInCenter) {
            dto = getClient().getByIdInCenter(dto.getCode());
        } else {
            dto = getClient().getById(dto.getCode());
        }
        setPageDTO(dto);
        ConditionsMaintainBean conditionsMaintainBean = 
            ConditionsMaintainBean.getInstance();
        conditionsMaintainBean.setPageDTO(dto);
        this.initializeObjectBeforeMaintain();
        conditionsMaintainBean.setMaintainMode(2); // view mode

        return getEditNavigationCase();
    }


    public void search() throws DataBaseException, NoResultException {
        System.out.println("search");

        this.setSearchMode(true);

        try {
            if (parameterCode != null && !parameterCode.equals("")) {
                searchConditionsDTO.setParameterCode(Long.parseLong(parameterCode));
            }
            if (searchConditionsDTO.getLnevalue() != null && 
                searchConditionsDTO.getLnevalue().equals("")) {
                searchConditionsDTO.setLnevalue(null);
            }

            if (searchConditionsDTO.getConditionName() != null && 
                !searchConditionsDTO.getConditionName().equals(""))
                searchConditionsDTO.setConditionName(Helper.formatSearchString(searchConditionsDTO.getConditionName()));

            if (getLineCodeText() != null && !getLineCodeText().equals("")) {
                searchConditionsDTO.setLineCode(Long.valueOf(lineCodeText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //setMyTableData((ArrayList<BasicDTO>)((IConditionsClient)getClient()).search(searchConditionsDTO));
            setMyTableData((ArrayList)((IConditionsClient)getClient()).search(searchConditionsDTO));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        }

        //        String JS="hideLookUpDiv(window.blocker,window.divSearch,null,null,null);";
        //        setJavaScriptCaller(JS);
        if (this.getSelectedDTOS() != null)
            this.getSelectedDTOS().clear();
        if (this.getHighlightedDTOS() != null)
            this.getHighlightedDTOS().clear();

        this.repositionPage(0);
    }


    public void initializeObjectBeforeMaintain() {

        IConditionsDTO conditionsDTO = (IConditionsDTO)getPageDTO();
        if (conditionsDTO.getConditionDetailsDTOList() == null)
            conditionsDTO.setConditionDetailsDTOList(new ArrayList());
    }

    public void setLineCodeText(String lineCodeText) {
        this.lineCodeText = lineCodeText;
    }

    public String getLineCodeText() {
        return lineCodeText;
    }

    public void setParameterText(String parameterText) {
        this.parameterText = parameterText;
    }

    public String getParameterText() {
        return parameterText;
    }

    public void setItemSelectionRequiredValueString(String itemSelectionRequiredValueString) {
        this.itemSelectionRequiredValueString = 
                itemSelectionRequiredValueString;
    }

    public String getItemSelectionRequiredValueString() {
        return itemSelectionRequiredValueString;
    }

    public void updateParametrValuesList(ActionEvent ae) {
        System.out.println("calling.......... updateParametrValuesList");
        IParametersClient parametrClient = 
            GrsClientFactory.getParametersClient();
        if ((parameterCode != null) && !parameterCode.equals("")) {

            IEntityKey parameterEntityKey = 
                GrsEntityKeyFactory.createParametersEntityKey();
            try {
                parameterEntityKey = 
                        Helper.getEntityKey(parametersList, parameterCode);
                parametersDTO = 
                        (IParametersDTO)parametrClient.getById(parameterEntityKey);
                if (parametersDTO.isValidSQL())
                    lineValuesList = 
                            parametrClient.getParameterValues(parametersDTO);


            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //   * Method removed by Assmaa Omar 
    //  * to fix bug number (GN-130) 0002.jpg
    //    public void filterByParameterCode(ValueChangeEvent ce) {
    //        this.setSearchMode(true);
    //        String changedValue = (String)ce.getNewValue();
    //        ParametersDTO parametersDTO = new ParametersDTO();
    //        IParametersClient parametrClient = 
    //            GrsClientFactory.getParametersClient();
    //        if ((changedValue != null) && !changedValue.equals("") && 
    //            !changedValue.equals(itemSelectionRequiredValueString)) {
    //
    //            EntityKey parameterEntityKey = new EntityKey();
    //            try {
    //                parameterEntityKey = 
    //                        Helper.getEntityKey(parametersList, changedValue);
    //                parametersDTO = 
    //                        (ParametersDTO)parametrClient.getById(parameterEntityKey);
    //                if (parametersDTO.isValidSQL())
    //                    lineValuesList = 
    //                            parametrClient.getParameterValues(parametersDTO);
    //
    //
    //            } catch (SharedApplicationException e) {
    //                lineValuesList = new ArrayList();
    //                e.printStackTrace();
    //            } catch (DataBaseException e) {
    //                lineValuesList = new ArrayList();
    //                e.printStackTrace();
    //            } catch (Exception e) {
    //                lineValuesList = new ArrayList();
    //                e.printStackTrace();
    //            }
    //        }
    //        String JS = "changeVisibilityDiv(window.blocker,window.divSearch);";
    //        setJavaScriptCaller(JS);
    //
    //    }
    /*
 * Method removed by Sherif.omar
 * to fix bug number (GN-130) 0002.jpg
 *
    public void filterByParameterCode(ValueChangeEvent ce) {
        ParametersDTO selectedParameter = null;
        this.setSearchMode(true);
        String changedValue = (String)ce.getNewValue();
        if (changedValue != null &&
            !(changedValue.equals(itemSelectionRequiredValueString))) {
            selectedParameter = new ParametersDTO();
            parameterText = changedValue;
            selectedParameter.setCode(new ParametersEntityKey(Long.valueOf(parameterText)));

            try {
                lineValuesList =
                        iparametersClient.getParameterValuesForSearch(selectedParameter);
            } catch (SharedApplicationException e) {
                lineValuesList = new ArrayList();
            } catch (DataBaseException e) {
                lineValuesList = new ArrayList();
            }
        } else if (changedValue != null &&
                   (changedValue.equals(itemSelectionRequiredValueString)))
            lineValuesList = new ArrayList();

        String JS = "changeVisibilityDiv(window.blocker,window.divSearch);";
        setJavaScriptCaller(JS);
    }
    */


    public void cancelSearch() throws DataBaseException {
        searchConditionsDTO =GrsDTOFactory.createSearchConditionsDTO();
        parameterText = itemSelectionRequiredValueString;
        lineCodeText = itemSelectionRequiredValueString;
        getAll();
        this.setSearchMode(false);
    }


    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideConditionName() {
        showHideColumn("name_column");
    }

    public void showHideConditionDesc() {
        showHideColumn("desc_column");
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getParameterCode() {
        return parameterCode;
    }


    public void setRenderedBack(boolean renderedBack) {
        this.renderedBack = renderedBack;
    }

    public boolean isRenderedBack() {
        return renderedBack;
    }

    public void setViewInCenter(boolean viewInCenter) {
        this.viewInCenter = viewInCenter;
    }

    public boolean isViewInCenter() {
        return viewInCenter;
    }

    public void setParametersDTO(IParametersDTO parametersDTO) {
        this.parametersDTO = parametersDTO;
    }

    public IParametersDTO getParametersDTO() {
        return parametersDTO;
    }

    public void setLinesList(List<IBasicDTO> linesList) {
        this.linesList = linesList;
    }

    public List<IBasicDTO> getLinesList() throws DataBaseException {
        linesList = ilinesClient.getCodeName();
        return linesList;
    }

    public void setParametersList(List<IBasicDTO> parametersList) {
        this.parametersList = parametersList;
    }

    public List<IBasicDTO> getParametersList() throws DataBaseException {
        parametersList = iparametersClient.getCodeName();
        return parametersList;
    }

    public void setLineValuesList(List<IBasicDTO> lineValuesList) {
        this.lineValuesList = lineValuesList;
    }

    public List<IBasicDTO> getLineValuesList() {
        return lineValuesList;
    }

    public void setSearchConditionsDTO(ISearchConditionsDTO searchConditionsDTO) {
        this.searchConditionsDTO = searchConditionsDTO;
    }

    public ISearchConditionsDTO getSearchConditionsDTO() {
        return searchConditionsDTO;
    }
}
