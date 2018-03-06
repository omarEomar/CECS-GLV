package com.beshara.csc.inf.presentation.backingbean.maindata.gradetypes;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IInfGradeTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.InfGradeTypesDTO;
import com.beshara.csc.inf.business.dto.InfGradeValuesDTO;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.faces.event.ActionEvent;


public class ListBean extends LookUpBaseBean {
    private boolean renderOption;
    private String gradeName;
    private Long GradeTypeCode;
    private String formula;
    private Long minValue;
    private Long maxValue;
    private String gradeTypeValType = "";
    private String viewOption = "";
    private IInfGradeTypesDTO gradeTypesDTO;
    //    private String value;
    //    private Long percentageValue;
    private List numGradeList = new ArrayList();
    private int numGradeListSize = 0;
    AppMainLayout app;
    private boolean validMode;
    InfGradeValuesDTO infGradeValuesDTO = new InfGradeValuesDTO();
    private boolean localErrorMsg;
    private boolean successMsg;
    private static final String BUNDLE_NAME = "com.beshara.csc.inf.presentation.resources.inf";
    private InfGradeTypesDTO selectedGradeTypesDTO = new InfGradeTypesDTO();

    public ListBean() {
        setClient(InfClientFactory.getGradeTypesClient());
        setSelectedPageDTO(InfDTOFactory.createGradeTypesDTO());
        setPageDTO(InfDTOFactory.createGradeTypesDTO());
        setSingleSelection(true);
        setSaveSortingState(true);
    }


    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void save() {
        super.save();

    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createGradeTypesDTO());
    }

    public void preAdd() {
        setGradeTypeValType("2");
        setRenderOption(false);
        super.preAdd();
        ((IInfGradeTypesDTO)getPageDTO()).setMinValue(null);
        ((IInfGradeTypesDTO)getPageDTO()).setMaxValue(null);
        ((IInfGradeTypesDTO)getPageDTO()).setFormula(null);
        setValidMode(false);
    }


    public AppMainLayout appMainLayoutBuilder() {
        app = super.appMainLayoutBuilder();
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        return app;
    }


    public void showGradeDetails() {
        try {
            getHighlightedDTOS().clear();
            if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
                setGradeName(((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getGradeTypeName());
                GradeTypeCode =
                        ((InfGradeTypesEntityKey)((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getCode()).getGradeTypeCode();

                List<IBasicDTO> basicDTOList =
                    InfClientFactory.getInfGradeValuesClient().getAllByTypeCode(GradeTypeCode);
                if (basicDTOList != null && basicDTOList.size() != 0) {
                    for (IBasicDTO dto : basicDTOList) {
                        InfGradeValuesDTO infGradeValuesDTO = (InfGradeValuesDTO)dto;
                        numGradeList.add(infGradeValuesDTO);
                    }
                    setNumGradeListSize(numGradeList.size());
                    setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");

                } else {
                    this.setSearchQuery("");
                    this.setSearchType(0);
                    this.setSearchMode(false);
                    throw new Exception();

                }
            } else {
                // throw new Exception();
            }
        } catch (RemoteException e) {
        } catch (FinderException e) {
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            setLocalErrorMsg(false);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.global_ar",
                                            "global_noTableResults");
            this.setErrorMsg(getSharedUtil().getErrMsgValue());
            setSelectedRadio("");
        }
    }

    public void saveAndNew() {
        try {
            super.saveAndNew();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        ((IInfGradeTypesDTO)getPageDTO()).setMinValue(null);
        ((IInfGradeTypesDTO)getPageDTO()).setMaxValue(null);
        ((IInfGradeTypesDTO)getPageDTO()).setFormula(null);
        setGradeTypeValType(gradeTypeValType);
    }

    public void addGradeDetails() {
        setSuccess(false);
        setPageMode(3);
        getHighlightedDTOS().clear();
        setInfGradeValuesDTO(new InfGradeValuesDTO());
        setSelectedPageDTO(InfDTOFactory.createGradeTypesDTO());
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            setGradeName(((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getGradeTypeName());
        }
    }

    public void saveGradeValues() {
        getPageMode();
        boolean existFlag = false;
        List<IBasicDTO> basicDTOList = new ArrayList<IBasicDTO>();
        try {
            GradeTypeCode =
                    ((InfGradeTypesEntityKey)((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getCode()).getGradeTypeCode();
            try {
                basicDTOList = InfClientFactory.getInfGradeValuesClient().getAllByTypeCode(GradeTypeCode);
            } catch (Exception e) {
                basicDTOList = new ArrayList<IBasicDTO>();

            }
            if (basicDTOList != null && basicDTOList.size() != 0) {
                for (IBasicDTO obj : basicDTOList) {
                    InfGradeValuesDTO dto = (InfGradeValuesDTO)obj;
                    if (dto.getValue().equals(infGradeValuesDTO.getValue())) {
                        existFlag = true;
                        setErrorMsg("value_added_before");
                        getSharedUtil().setErrMsgValue(getSharedUtil().getResourceBundle(BUNDLE_NAME).getString("value_added_before"));

                    } else if (dto.getPercentageValue().equals(infGradeValuesDTO.getPercentageValue())) {
                        existFlag = true;
                        setErrorMsg("percentage_added_before");
                        getSharedUtil().setErrMsgValue(getSharedUtil().getResourceBundle(BUNDLE_NAME).getString("percentage_added_before"));
                    } else {
                        existFlag = false;

                    }
                }
            }
            if (existFlag == false) {
                infGradeValuesDTO.setGradeTypeCode(((InfGradeTypesEntityKey)((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getCode()).getGradeTypeCode());
                //        dto.setGradeTypesDTO(gradeTypesDTO);
                infGradeValuesDTO.setGradeTypesDTO((IInfGradeTypesDTO)getSelectedDTOS().get(0));
                IBasicDTO addGradeDTO = InfClientFactory.getInfGradeValuesClient().add(infGradeValuesDTO);
                setMyTableData(this.getTotalList());
                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().add((IInfGradeTypesDTO)getSelectedDTOS().get(0));
                }

                this.setSearchQuery("");
                this.setSearchType(0);
                this.setSearchMode(false);
                getSharedUtil().setSuccessMsgValue("SuccessAdds");
            }
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            setLocalErrorMsg(true);
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf_ar", getErrorMsg());
        }


        setSelectedRadio("");

    }

    public void saveAndNewGradeValues() {
        setSuccess(false);
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            setGradeName(((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getGradeTypeName());
        }
        boolean existFlag = false;
        List<IBasicDTO> basicDTOList = new ArrayList<IBasicDTO>();
        try {
            GradeTypeCode =
                    ((InfGradeTypesEntityKey)((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getCode()).getGradeTypeCode();
            try {
                basicDTOList = InfClientFactory.getInfGradeValuesClient().getAllByTypeCode(GradeTypeCode);
            } catch (Exception e) {
                basicDTOList = new ArrayList<IBasicDTO>();
            }
            if (basicDTOList != null && basicDTOList.size() != 0) {
                for (IBasicDTO obj : basicDTOList) {
                    InfGradeValuesDTO dto = (InfGradeValuesDTO)obj;
                    if (dto.getValue().equals(infGradeValuesDTO.getValue())) {
                        existFlag = true;
                        setErrorMsg(getSharedUtil().getResourceBundle(BUNDLE_NAME).getString("value_added_before"));
                        break;
                    } else if (dto.getPercentageValue().equals(infGradeValuesDTO.getPercentageValue())) {
                        existFlag = true;
                        setErrorMsg(getSharedUtil().getResourceBundle(BUNDLE_NAME).getString("percentage_added_before"));
                        break;
                    } else {
                        existFlag = false;
                    }
                }
            }
            if (existFlag == false) {
                infGradeValuesDTO.setGradeTypeCode(((InfGradeTypesEntityKey)((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getCode()).getGradeTypeCode());
                infGradeValuesDTO.setGradeTypesDTO((IInfGradeTypesDTO)getSelectedDTOS().get(0));
                IBasicDTO addGradeDTO = InfClientFactory.getInfGradeValuesClient().add(infGradeValuesDTO);
                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().add((IInfGradeTypesDTO)getSelectedDTOS().get(0));
                }
                this.setSearchQuery("");
                this.setSearchType(0);
                this.setSearchMode(false);
                setSuccess(true);
                setInfGradeValuesDTO(new InfGradeValuesDTO());
                setSelectedPageDTO(InfDTOFactory.createGradeTypesDTO());
                if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
                    setGradeName(((IInfGradeTypesDTO)getSelectedDTOS().get(0)).getGradeTypeName());
                }
            }

        } catch (Exception e) {
            setSuccess(false);
            this.setShowErrorMsg(true);
            setLocalErrorMsg(true);
        }
        setSelectedRadio("");

    }

    public void showEditDiv() {
        setValidMode(false);
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            //            selectedPageDTO=this.getSelectedDTOS().get(0);
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                setSelectedPageDTO(dto);
                if (((IInfGradeTypesDTO)getSelectedPageDTO()).getGradeTypeValType().equals("1")) {
                    setRenderOption(true);
                } else {
                    setRenderOption(false);
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }
            selectedGradeTypesDTO = new InfGradeTypesDTO();
            selectedGradeTypesDTO.setName(getSelectedPageDTO().getName());
            selectedGradeTypesDTO.setGradeTypeValType(((InfGradeTypesDTO)getSelectedPageDTO()).getGradeTypeValType());
            selectedGradeTypesDTO.setMinValue(((InfGradeTypesDTO)getSelectedPageDTO()).getMinValue());
            selectedGradeTypesDTO.setMaxValue(((InfGradeTypesDTO)getSelectedPageDTO()).getMaxValue());
            selectedGradeTypesDTO.setFormula(((InfGradeTypesDTO)getSelectedPageDTO()).getFormula());

            setShowEdit(true);
            //javaScriptCaller = "changeVisibilityDiv(window.blocker,window.lookupEditDiv);";
        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);
        }
        if (!lock()) {
            return;
        }
        setPageMode(2);
        reIntializeMsgs();
    }

    @Override
    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        getSelectedPageDTO().setName(selectedGradeTypesDTO.getName());
        ((InfGradeTypesDTO)getSelectedPageDTO()).setGradeTypeValType(selectedGradeTypesDTO.getGradeTypeValType());
        ((InfGradeTypesDTO)getSelectedPageDTO()).setMinValue(selectedGradeTypesDTO.getMinValue());
        ((InfGradeTypesDTO)getSelectedPageDTO()).setMaxValue(selectedGradeTypesDTO.getMaxValue());
        ((InfGradeTypesDTO)getSelectedPageDTO()).setFormula(selectedGradeTypesDTO.getFormula());
        super.edit();
        selectedGradeTypesDTO = new InfGradeTypesDTO();
    }

    public void selectedRadioButton(ActionEvent event) throws Exception {
        setGradeTypeValType(((IInfGradeTypesDTO)this.getMyDataTable().getRowData()).getGradeTypeValType());
        super.selectedRadioButton(event);
    }

    public void updateCase() {
        if (((IInfGradeTypesDTO)getPageDTO()).getGradeTypeValType().equals("1")) {
            setRenderOption(true);
        } else {
            setRenderOption(false);
        }

    }

    public void cancelLink() {
        setPageMode(0);
        setValidMode(false);
        setGradeTypeValType("");
        setSelectedRadio("");
        reIntializeMsgs();
    }

    public void setRenderOption(boolean renderOption) {
        this.renderOption = renderOption;
    }

    public boolean isRenderOption() {
        return renderOption;
    }

    public void setGradeTypeValType(String gradeTypeValType) {
        ((IInfGradeTypesDTO)getPageDTO()).setGradeTypeValType(gradeTypeValType);
        this.gradeTypeValType = gradeTypeValType;
    }

    public String getGradeTypeValType() {
        return gradeTypeValType;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setViewOption(String viewOption) {
        this.viewOption = viewOption;
    }

    public String getViewOption() {
        return viewOption;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setNumGradeList(List numGradeList) {
        this.numGradeList = numGradeList;
    }

    public List getNumGradeList() {
        return numGradeList;
    }


    public void setGradeTypesDTO(IInfGradeTypesDTO gradeTypesDTO) {
        this.gradeTypesDTO = gradeTypesDTO;
    }

    public IInfGradeTypesDTO getGradeTypesDTO() {
        return gradeTypesDTO;
    }

    public void setNumGradeListSize(int numGradeListSize) {
        this.numGradeListSize = numGradeListSize;
    }

    public int getNumGradeListSize() {
        return numGradeListSize;
    }

    public void setGradeTypeCode(Long GradeTypeCode) {
        this.GradeTypeCode = GradeTypeCode;
    }

    public Long getGradeTypeCode() {
        return GradeTypeCode;
    }

    public void setValidMode(boolean validMode) {
        this.validMode = validMode;
    }

    public boolean isValidMode() {
        return validMode;
    }

    public void setInfGradeValuesDTO(InfGradeValuesDTO infGradeValuesDTO) {
        this.infGradeValuesDTO = infGradeValuesDTO;
    }

    public InfGradeValuesDTO getInfGradeValuesDTO() {
        return infGradeValuesDTO;
    }

    public void setLocalErrorMsg(boolean localErrorMsg) {
        this.localErrorMsg = localErrorMsg;
    }

    public boolean isLocalErrorMsg() {
        return localErrorMsg;
    }

    public void setSelectedGradeTypesDTO(InfGradeTypesDTO selectedGradeTypesDTO) {
        this.selectedGradeTypesDTO = selectedGradeTypesDTO;
    }

    public InfGradeTypesDTO getSelectedGradeTypesDTO() {
        return selectedGradeTypesDTO;
    }
}
