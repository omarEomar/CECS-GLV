package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.dto.IREGRegulationMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IREGRegulationMaterialsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class RegulationRegSubjectsMaintain extends ManyToManyDetailsMaintain {
    String regmaterialText;
    private List<IREGRegulationMaterialsDTO> dataTableValue;
    private List<IBasicDTO> resetList;
    IREGRegulationMaterialsDTO regRegulationMaterialsDTO= RegDTOFactory.createREGRegulationMaterialsDTO();
    private IREGRegulationMaterialsDTO preEditDTO;
    private IREGRegulationMaterialsDTO editDTO;
    private int index;
    private int checkAddOrEdit=0;
    public RegulationRegSubjectsMaintain() {
       
        setDivMainContentMany("divMainContentManyWithContent1ThreeRowsSmall2");
        setSaveSortingState(true);
        setDivMainContent("divContent1Dynamic2");
        setPageDTO(RegDTOFactory.createREGRegulationMaterialsDTO());
        setSelectedPageDTO(RegDTOFactory.createREGRegulationMaterialsDTO());
        setPreEditDTO(RegDTOFactory.createREGRegulationMaterialsDTO());
    
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        app.setShowLookupEdit(true);
        return app;
    }

    public void add() {
        
        
        ((IREGRegulationMaterialsDTO)getPageDTO()).setRegmaterialText(regmaterialText);
        getPageDTO().setStatusFlag(new Long(0));
        getPageDTO().setCode(RegEntityKeyFactory.createREGRegulationMaterialsEntityKey(-getMinId(),new Long(0),new Long(0),new Long(0)));
        getCurrentDetails().add(getPageDTO());
        setPageDTO(new BasicDTO());
        setRegmaterialText(null);
        
    }
    
    public void saveAndNew() {
        this.add();
    }
    
    public Long getMinId() {

        Long maxId = new Long(-1);
        if (this.getCurrentDetails() == null || 
            this.getCurrentDetails().size() == 0) {
            return maxId;
        }
        for (IBasicDTO dto: this.getCurrentDetails()) {
            Long id = 
                ((IREGRegulationMaterialsEntityKey)dto.getCode()).getRegmaterialCode();
            if (id < maxId)
                maxId = id;
        }
        return maxId - 1;
    }

    public void setRegmaterialText(String regmaterialText) {
        this.regmaterialText = regmaterialText;
    }

    public String getRegmaterialText() {
        return regmaterialText;
    }
    
    
    
//    public void EditChange()
//    {
//
//        try {
//            setSelectedPageDTO((REGRegulationMaterialsDTO)SharedUtilBean.deepCopyObject(getPreEditDTO()));
//            
//            ((REGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index)).setCode(getSelectedPageDTO().getCode());
//            ((REGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index)).setName(getSelectedPageDTO().getName());
//            ((REGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index)).setRegmaterialText(((REGRegulationMaterialsDTO)getSelectedPageDTO()).getRegmaterialText());
//        } catch (Exception e) {
//            // TODO
//        }
//    }
//    
//    public void preEditDiv() {
//            
////             preEditDTO=(REGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index);
//        setSelectedPageDTO((REGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index));
//
//        try {
//            setPreEditDTO((REGRegulationMaterialsDTO)SharedUtilBean.deepCopyObject(getSelectedPageDTO()));
//        } catch (Exception e) {
//             e.printStackTrace();
//        }
//    }

 public void EditChange()
    {           
        resetSelection();
        try {
            setEditDTO((IREGRegulationMaterialsDTO)SharedUtilBean.deepCopyObject(getPreEditDTO()));
 //               getCurrentDisplayDetails().set(index,getEditDTO());
            ((IREGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index)).setCode(getEditDTO().getCode());
            ((IREGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index)).setName(getEditDTO().getName());
            ((IREGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index)).setRegmaterialText((getEditDTO().getRegmaterialText()));
        } catch (Exception e) {
            // TODO
        }           
    }
    
    public void preEditDiv() {

     try {
 //            setEditDTO((REGDedignTablesDTO)getCurrentDisplayDetails().get(index));
         editDTO = (IREGRegulationMaterialsDTO)getCurrentDisplayDetails().get(index);
         setPreEditDTO((IREGRegulationMaterialsDTO)SharedUtilBean.deepCopyObject(editDTO));
     } catch (Exception e) {
         // TODO
         e.printStackTrace();
     }
            
     }

    public void delete() {
        
        if (getCurrentDetails() == null)
            setCurrentDetails(new ArrayList<IBasicDTO>());
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() > 0) {
            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);

                    if (selected.getStatusFlag() == null) {
                        selected.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                        getSelectedCurrentDetails().remove(i);
                        i--;
                    }
                    if (selected.getStatusFlag().longValue() == ISystemConstant.ADDED_ITEM.longValue()) {
                        getCurrentDetails().remove(selected);
                        getSelectedCurrentDetails().remove(i);
                        i--;
                    }
                }
        }

        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                         this.getCurrentDetails());
        this.resetSelection();
        
    }
    

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setPreEditDTO(IREGRegulationMaterialsDTO preEditDTO) {
        this.preEditDTO = preEditDTO;
    }

    public IREGRegulationMaterialsDTO getPreEditDTO() {
        return preEditDTO;
    }
    
    
    public void selectedCurrent(ActionEvent event) throws Exception {
         super.selectedCurrent(event);
         dataTableValue = (List<IREGRegulationMaterialsDTO>)getCurrentDataTable().getValue();
         index = getCurrentDataTable().getRowIndex();
     }
     
    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        IREGRegulationMaterialsDTO regRegulationMaterialsDTO = (IREGRegulationMaterialsDTO)dto;
        return regRegulationMaterialsDTO;
    }


    public void setResetList(List<IBasicDTO> resetList) {
        this.resetList = resetList;
    }

    public List<IBasicDTO> getResetList() {
        return resetList;
    }

    public void setCheckAddOrEdit(int checkAddOrEdit) {
        this.checkAddOrEdit = checkAddOrEdit;
    }

    public int getCheckAddOrEdit() {
        return checkAddOrEdit;
    }

    public void setEditDTO(IREGRegulationMaterialsDTO editDTO) {
        this.editDTO = editDTO;
    }

    public IREGRegulationMaterialsDTO getEditDTO() {
        return editDTO;
    }
}
