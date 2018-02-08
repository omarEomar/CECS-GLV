package com.beshara.csc.gn.grs.presentation.backingbean.parameters;

import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IParametersClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IParametersDTO;
import com.beshara.csc.inf.business.entity.IFieldTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import javax.faces.context.FacesContext;


public class ParameterListBean extends LookUpBaseBean {
    public ParameterListBean() {
        setPageDTO(GrsDTOFactory.createParametersDTO());
        setSelectedPageDTO(GrsDTOFactory.createParametersDTO());
        setClient((IParametersClient)GrsClientFactory.getParametersClient());
    }

    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideParameterName() {
        showHideColumn("name_column");
    }

    public void showHideParameterDesc() {
        showHideColumn("desc_column");
    }

    public void showHideRelatedToDate() {
        showHideColumn("relatedToDate_column");
    }

    public void showHideUsingInCalc() {
        showHideColumn("useInCalc_column");
    }

    public void search() throws DataBaseException, NoResultException {
        if (this.getSearchType().intValue() == 0){
            Long code=ISystemConstant.VIRTUAL_VALUE;
            try {
                code=new Long(this.getSearchQuery());
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
            super.setSearchEntityObj(code);
            }
        super.search();
    }

    public String goParameterAdd() {
        return "Parameter_Add_Page";
    }

    public String goParameterEdit() {
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                setSelectedPageDTO(getClient().getById(dto.getCode()));
            } catch (Exception e) {
                System.out.println(" baase bean::showedutediv::exception=" + 
                                   e);
            }
        } else {
            setSelectedPageDTO(new BasicDTO());
        }
        IParametersDTO parameterDTO = (IParametersDTO)getSelectedPageDTO();
        ParameterEditBean parameterEditBean = 
            (ParameterEditBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{parameterEditBean}").getValue(FacesContext.getCurrentInstance());
        parameterEditBean.setSelectedPageDTO(parameterDTO);
        if (parameterDTO.getFiledTypesDTO() != null && 
            ((IFieldTypesEntityKey)parameterDTO.getFiledTypesDTO().getCode()) != 
            null)
            parameterEditBean.setFieldType(((IFieldTypesEntityKey)parameterDTO.getFiledTypesDTO().getCode()).getFldtypeCode().toString());
        if (parameterDTO.getStatus() != null)
            parameterEditBean.setStatusValue(parameterDTO.getStatus().toString());
        return "Parameter_Edit_Page";
    }
}
