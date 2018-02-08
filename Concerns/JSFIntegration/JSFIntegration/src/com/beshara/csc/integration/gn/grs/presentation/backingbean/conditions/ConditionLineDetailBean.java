package com.beshara.csc.gn.grs.presentation.backingbean.conditions;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionDetailsClient;
import com.beshara.csc.gn.grs.business.client.IJoinConditionsClient;
import com.beshara.csc.gn.grs.business.client.ILinesClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionDetailsDTO;
import com.beshara.csc.gn.grs.business.dto.IJoinConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.ILinesDTO;
import com.beshara.csc.gn.grs.business.dto.IParameterValuesDTO;
import com.beshara.csc.gn.grs.business.entity.ConditionDetailsEntityKey;
import com.beshara.csc.gn.grs.business.entity.JoinConditionsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyBaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ValueChangeEvent;


public class ConditionLineDetailBean extends ManyToManyDetailsMaintain {

    private List joinConditions;
    private static long inc = 1;
    private HtmlCommandButton readLineBtn;
    private String joinConditionSelectedValue;
    private String queryOverview = null;
    private ILinesDTO linesDTODetails = GrsDTOFactory.createLinesDTO();
    private IConditionDetailsClient iConditionDetailsClient = 
        GrsClientFactory.getConditionDetailsClient();
    private ILinesClient ILinesClient = GrsClientFactory.getLinesClient();
    private String paraName;
    private String lineSign;
    private String overViewLine = "";

    private String lineValue = "";
    private List<IParameterValuesDTO> lineValueslist = 
        new ArrayList<IParameterValuesDTO>();
    IConditionDetailsDTO details = GrsDTOFactory.createConditionDetailsDTO();


    public ConditionLineDetailBean() {

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
        app.setShowSearch(false);
        return app;
    }

    void finialize() {
        inc = 0;
    }

    public String getMessages(String resourceBundle, String key, Locale loc) {
        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle, loc);
        return bundle.getString(key);
    }

    public void up() {
        System.out.println(getCurrentDataTable().getRowIndex());

        int oldIndex = getCurrentDataTable().getRowIndex();
        int newIndex = getCurrentDataTable().getRowIndex() - 1;

        IBasicDTO newDto = (IBasicDTO)getCurrentDataTable().getRowData();
        IBasicDTO oldDto = (IBasicDTO)getCurrentDataTable().getRowData();

        newDto.setStatusFlag(ISystemConstant.ADDED_ITEM);
        getCurrentDetails().remove(oldIndex);
        getCurrentDetails().add(newIndex, newDto);


        //oldDto.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
        availableDetails.add(oldDto);
        //getSelectedCurrentDetails().remove(oldIndex);
        this.resetSelection();

    }

    public void initializeRow() {
        details = GrsDTOFactory.createConditionDetailsDTO();
        details.setStatusFlag(ISystemConstant.ADDED_ITEM);
        ILinesDTO line = GrsDTOFactory.createLinesDTO();
        line.setCode(new EntityKey(inc++));
        details.setCode(new ConditionDetailsEntityKey(inc++, 0L));
        line.setStatusFlag(ISystemConstant.ADDED_ITEM);
        String choose = 
            getMessages("com.beshara.csc.gn.grs.presentation.resources.grs", 
                        "select", 
                        FacesContext.getCurrentInstance().getViewRoot().getLocale());
        choose += " ";
        choose += 
                getMessages("com.beshara.csc.gn.grs.presentation.resources.grs", 
                            "LineName", 
                            FacesContext.getCurrentInstance().getViewRoot().getLocale());
        line.setName(choose);
        details.setLinesDTO(line);
        if (details.getJoinConditionsDTO() == null) {
            IJoinConditionsDTO joinCon = 
                GrsDTOFactory.createJoinConditionsDTO();
            joinCon.setCode(new JoinConditionsEntityKey(""));
            details.setJoinConditionsDTO(joinCon);
        }
    }

    public void addNewRow() {
        System.out.println("**************************************************************");
        initializeRow();
        ConditionsMaintainBean conditionsMaintainBean = 
            (ConditionsMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{conditionsMaintainBean}").getValue(FacesContext.getCurrentInstance());
        if (conditionsMaintainBean.getMaintainMode() == 1) {
            details.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
            //details.setCode(new ConditionDetailsEntityKey(new Long(conditionsMaintainBean.getPageDTO().getCode().getKey().toString()), new Long(getCurrentDetails().size()+1)));                   
            details.setDetailOrder(new Long(getCurrentDetails().size() + 1));
        } else {
            //details.setCode(new ConditionDetailsEntityKey(null, new Long(getCurrentDetails().size()+1)));                   
            details.setDetailOrder(new Long(getCurrentDetails().size() + 1));
        }
        getCurrentDetails().add(details);
    }

    public void injectionRow() {
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() != 0) {
            initializeRow();
            int index = 
                (Integer.parseInt(((IConditionDetailsDTO)getSelectedCurrentDetails().get(0)).getDetailOrder().toString())) - 
                1;
            //details.setCode(new ConditionDetailsEntityKey((Long)((ConditionDetailsDTO)getSelectedCurrentDetails().get(0)).getCode().getKeys()[0], new Long(index+1)));                
            details.setDetailOrder(new Long(index + 1));
            getCurrentDetails().add(index, details);
        }
    }

    public List<IBasicDTO> getCurrentDisplayDetails() {
        List<IBasicDTO> currentDisplayDetails = new ArrayList();
        for (IBasicDTO dto: getCurrentDetails()) {
            if (dto.getStatusFlag() == null)
                currentDisplayDetails.add(dto);
            if (dto.getStatusFlag() != null && 
                (dto.getStatusFlag().longValue() == 
                 ISystemConstant.ADDED_ITEM.longValue() || 
                 dto.getStatusFlag().longValue() == 
                 ISystemConstant.ADDED_LAST_ITEM.longValue()))
                currentDisplayDetails.add(dto);
        }
        setCurrentDisplayDetails(currentDisplayDetails);
        return currentDisplayDetails;
    }
    //}

    public String overview() {
        queryOverview = "";
        List<IConditionDetailsDTO> temp = 
          //  (List<IConditionDetailsDTO>)getCurrentDetails();
          (List)getCurrentDetails();
        for (IConditionDetailsDTO dto: temp) {
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

    private void queryOverviewStringFactory(IConditionDetailsDTO dto) {
        String newQueryOverview = "";
        if (queryOverview == null)
            queryOverview = "";

        SharedUtilBean shared_util = getSharedUtil();
        if (dto.getRightArcs() != null && !"".equals(dto.getRightArcs()))
            newQueryOverview += dto.getRightArcs();

        if (dto.getLinesDTO() != null) {
            if (!dto.getLinesDTO().getName().equals("إختر إسم السطر"))
                newQueryOverview += ((IBasicDTO)dto.getLinesDTO()).getName();

        }

        if (dto.getLeftArcs() != null && !"".equals(dto.getLeftArcs()))
            newQueryOverview += dto.getLeftArcs();

        queryOverview += "\n" + newQueryOverview;
        if (dto.getJonconditionSign() != null) {
            List<IJoinConditionsDTO> joinCon;
            try {
                joinCon = (List<IJoinConditionsDTO>)getJoinConditions();
                for (IJoinConditionsDTO join: joinCon) {
                    if (join.getCode().getKey().toString().equals(dto.getJonconditionSign()))
                        queryOverview += "\n" + join.getName();
                }
            } catch (SharedApplicationException e) {
                shared_util.setErrMsgValue("SystemFailureException");
                e.printStackTrace();
            } catch (DataBaseException e) {
                shared_util.setErrMsgValue("SystemFailureException");
                e.printStackTrace();
            }

        }
        System.out.println("------" + queryOverview);
    }

    public void populateJoinConditionBean() {
        List<IConditionDetailsDTO> conditionDetailsDTOS = 
//            (List<IConditionDetailsDTO>)getCurrentDetails();
        (List)getCurrentDetails();
        for (IConditionDetailsDTO curretnDTO: conditionDetailsDTOS) {
            if (curretnDTO.getJoinConditionsDTO() == null) {
                IJoinConditionsDTO joinCon = 
                    GrsDTOFactory.createJoinConditionsDTO();
                joinCon.setCode(new JoinConditionsEntityKey(""));
                curretnDTO.setJoinConditionsDTO(joinCon);
            }

        }
    }


    public boolean validate() {
        SharedUtilBean shared_util = getSharedUtil();
        List<IConditionDetailsDTO> conditionDetailsDTOs = 
            //(List<IConditionDetailsDTO>)getCurrentDisplayDetails();
            (List)getCurrentDisplayDetails();
        for (IConditionDetailsDTO dto: conditionDetailsDTOs) {
            if (dto.getLinesDTO().getName().equals("إختر إسم السطر")) {
                shared_util.setErrMsgValue("chooseLinevalue");
                return false;
            }
        }
        return super.validate();
    }

    public void overViewPanelLisentner(ValueChangeEvent v) {
        System.out.println("------------------------");
        overview();
        //        ConditionDetailsDTO conditionDetailsDTO= ((ConditionDetailsDTO)getCurrentDataTable().getRowData());
        //        queryOverviewStringFactory(conditionDetailsDTO);
    }

    public void delete() {


        for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
            IBasicDTO selected = getSelectedCurrentDetails().get(i);
            if (selected.getStatusFlag() == null) {
                selected.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                getSelectedCurrentDetails().remove(i);
                i--;
            }
            if (selected.getStatusFlag().longValue() == 
                ISystemConstant.ADDED_ITEM.longValue() || 
                selected.getStatusFlag().longValue() == 
                ISystemConstant.ADDED_LAST_ITEM.longValue()) {
                getCurrentDetails().remove(selected);
                availableDetails.add(selected);
                getSelectedCurrentDetails().remove(i);
                i--;
            }
        }
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                         this.getCurrentDetails());
        this.resetSelection();

    }

    public void removeCurrentFromAvailable() {
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return dto;
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        return dto;
    }

    public String readLine() {
        ValueBinding vb = 
            getAppliction().createValueBinding("#{conditionLineSub}");
        ConditionLineSub bean = 
            (ConditionLineSub)vb.getValue(FacesContext.getCurrentInstance());
        Integer no = 
            (Integer)getReadLineBtn().getAttributes().get("selectedRowNo");
        bean.setRowNo(no);
        vb.setValue(FacesContext.getCurrentInstance(), bean);
        return "condition_line_sub";
    }

    public static Application getAppliction() {
        return FacesContext.getCurrentInstance().getApplication();
    }

    public void removeRow() {

    }

    public void setJoinConditions(List joinConditions) {
        this.joinConditions = joinConditions;
    }

    public List getJoinConditions() throws DataBaseException, 
                                           SharedApplicationException {
        if (joinConditions == null) {
            IJoinConditionsClient conditionClient = 
                GrsClientFactory.getJoinConditionsClient();
            return conditionClient.getCodeName();
        }
        return joinConditions;
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
        populateJoinConditionBean();
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


    private boolean isViewInCenter() {
        ConditionListBean listPage = 
            (ConditionListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{conditionListBean}").getValue(FacesContext.getCurrentInstance());
        return listPage.isViewInCenter();
    }

    public void selectedRadioButton(ValueChangeEvent event) throws Exception {
        if (event.getNewValue() != null) {
            setSelectedDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = 
                (IClientDTO)this.getCurrentDataTable().getRowData();
            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().add(selected);
        }
    }

    public String showLineDetails() {
        //selectedCurrent()
        System.out.println("showLineDetails");
        IConditionDetailsDTO conditionDetailsDTO = 
            GrsDTOFactory.createConditionDetailsDTO();
        linesDTODetails = GrsDTOFactory.createLinesDTO();
        lineValueslist = new ArrayList<IParameterValuesDTO>();

        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() != 0) {
            IBasicDTO basic = getSelectedCurrentDetails().get(0);
            conditionDetailsDTO = (IConditionDetailsDTO)basic;
            boolean viewIncenter = isViewInCenter();
            try {
                if (basic.getCode() != null && !basic.getCode().equals("")) {
                    if (viewIncenter) {
                        conditionDetailsDTO = 
                                (IConditionDetailsDTO)iConditionDetailsClient.getByIdInCenter(basic.getCode());
                    } else {
                        conditionDetailsDTO = 
                                (IConditionDetailsDTO)iConditionDetailsClient.getById(basic.getCode());
                    }
                }
            } catch (SharedApplicationException e) {
                //super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                //e.printStackTrace();
                ;
            } catch (DataBaseException e) {
                //                super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                //                e.printStackTrace();
                ;
            }
            if (conditionDetailsDTO.getLinesDTO() != null && 
                ((ILinesDTO)conditionDetailsDTO.getLinesDTO()).getCode() != 
                null) {
                try {
                    if (viewIncenter) {
                        linesDTODetails = 
                                (ILinesDTO)ILinesClient.getByIdInCenter((EntityKey)(conditionDetailsDTO.getLinesDTO().getCode()));
                    } else {
                        linesDTODetails = 
                                (ILinesDTO)ILinesClient.getById((EntityKey)(conditionDetailsDTO.getLinesDTO().getCode()));
                    }

                    overViewLine += linesDTODetails.getName();
                    paraName = linesDTODetails.getParametersDTO().getName();
                    overViewLine += ' ' + paraName;
                    lineSign = linesDTODetails.getOperatorsDTO().getName();
                    overViewLine += ' ' + lineSign;
                    if (linesDTODetails != null && 
                        linesDTODetails.getParameterLineValuesList() != null) {
                        lineValueslist = 
                                linesDTODetails.getParameterLineValuesList();
                        for (int i = 0; i < lineValueslist.size(); i++) {
                            overViewLine += 
                                    '\n' + lineValueslist.get(i).getStrCode() + 
                                    ' ' + lineValueslist.get(i).getName();
                        }
                    } else {
                        lineValue = 
                                linesDTODetails.getLineValuesList().get(0).getName();
                    }
                }

                catch (SharedApplicationException sae) {
                    //                    super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                    //                    sae.printStackTrace();
                    ;

                } catch (DataBaseException db) {
                    //                    super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                    //                    db.printStackTrace();
                    ;
                }

            }
            FacesContext fc = FacesContext.getCurrentInstance();
            Application app = fc.getApplication();
            ManyToManyBaseBean manyToManyBaseBean = 
                (ManyToManyBaseBean)app.createValueBinding("#{pageBeanName}").getValue(fc);
            manyToManyBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");

        }
        return null;
    }

    public void setLineValueslist(List<IParameterValuesDTO> lineValueslist) {
        this.lineValueslist = lineValueslist;
    }

    public List<IParameterValuesDTO> getLineValueslist() {
        return lineValueslist;
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

    public void unCheck() {
        for (int i = 0; i < getCurrentDisplayDetails().size(); i++) {
            if (((IClientDTO)getCurrentDisplayDetails().get(i)).getChecked() != 
                null && 
                ((IClientDTO)getCurrentDisplayDetails().get(i)).getChecked().booleanValue()) {
                ((IClientDTO)getCurrentDisplayDetails().get(i)).setChecked(Boolean.valueOf(false));
                // checkControlsHeaderStatus();
            }
        }

        if (getSelectedCurrentDetails() != null) {
            getSelectedCurrentDetails().clear();
        }
    }

    //    public String getLineOverView() {
    //        lineOverView = "";
    //        ParametersDTO parameterDTO = null;
    //        OperatorsDTO operatorDTO = null;
    //
    //        EntityKey entityKey = new EntityKey();
    //
    //        if ((parameterCode != null) && !parameterCode.equals("")) {
    //
    //
    //            try {
    //                entityKey = Helper.getEntityKey(parametersList, parameterCode);
    //                parameterDTO = 
    //                        (ParametersDTO)(parametrClient.getById(entityKey));
    //
    //            } catch (ItemNotFoundException e) {
    //                // TODO
    //            } catch (Exception e) {
    //                // TODO
    //            }
    //        }
    //        if (parameterDTO != null) {
    //            lineOverView += parametersDTO.getName();
    //        }
    //        if ((operationCode != null) && !operationCode.equals("")) {
    //            try {
    //                entityKey = Helper.getEntityKey(operatorsList, operationCode);
    //                operatorDTO = (OperatorsDTO)operatersClient.getById(entityKey);
    //
    //            } catch (ItemNotFoundException e) {
    //                // TODO
    //            } catch (Exception e) {
    //                // TODO
    //            }
    //        }
    //        if (operatorDTO != null) {
    //            lineOverView += ' ' + operatorDTO.getName();
    //        }
    //        if (value != null) {
    //            lineOverView += ' ' + value;
    //        }
    //
    //        return lineOverView;
    //    }

    public void setLineValue(String lineValue) {
        this.lineValue = lineValue;
    }

    public String getLineValue() {
        return lineValue;
    }

    public void setLinesDTODetails(ILinesDTO linesDTODetails) {
        this.linesDTODetails = linesDTODetails;
    }

    public ILinesDTO getLinesDTODetails() {
        return linesDTODetails;
    }
}

