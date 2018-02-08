package com.beshara.csc.nl.reg.presentation.backingbean.copiesdata;

import com.beshara.csc.gn.sec.business.dto.SecWorkCenterUsersDTO;
import com.beshara.csc.inf.business.client.IInfReasonDataClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IInfReasonDataDTO;
import com.beshara.csc.inf.business.dto.IInfReasonTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.InfReasonDataDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IOrgCatsEntityKey;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegSubjectCopiesToDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.dto.RegSubjectCopiesToDTO;
import com.beshara.csc.nl.reg.business.entity.ISubjectsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegSubjectCopiesToEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class CopiesDataDataList extends LookUpBaseBean {
    private List<IInfReasonTypesDTO> reasonType;
    private long selectedModulesKeyDufalut = -100L;
    private long reasontypecode = selectedModulesKeyDufalut;

    public CopiesDataDataList() {
        setClient(RegClientFactory.getregSubjectCopiesClient());
        setDivMainContent("listDcisionMainContent");
       setSaveSortingState(true);
   }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowpaging(true);
        return app;
    }

    public void getAll() throws DataBaseException {

        try {
            setMyTableData(RegClientFactory.getregSubjectCopiesClientForCenter().getAllBySubjectCode(Long.parseLong(getPageDTO().getCode().getKey())));
        } catch (DataBaseException db) {
            setMyTableData(new ArrayList());
            db.printStackTrace();
        } catch (Exception e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        }
        this.reinitializeSort();
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }

    }

    public String gToAddCopiesData() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        CopiesDataDataAddBean copiesDataDataAddBean = 
            (CopiesDataDataAddBean)app.createValueBinding("#{copiesDataDataAddBean}").getValue(fc);
               try {
                    copiesDataDataAddBean.setEdit(false);
                  copiesDataDataAddBean.setPageDTO(RegDTOFactory.createRegSubjectCopiesToDTO());
                    copiesDataDataAddBean.setSelectedPageDTO(RegDTOFactory.createRegSubjectCopiesToDTO());
                    ((IRegSubjectCopiesToDTO)copiesDataDataAddBean.getPageDTO()).setSubjectsDTO((ISubjectsDTO)getPageDTO());
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return "CopiesDataDataAdd";
    }

    public String gToEditCopiesData() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        CopiesDataDataAddBean copiesDataDataAddBean = 
            (CopiesDataDataAddBean)app.createValueBinding("#{copiesDataDataAddBean}").getValue(fc);
               try {
                  copiesDataDataAddBean.setEdit(true);
                  copiesDataDataAddBean.setSelectedPageDTO(getSelectedDTOS().get(0));
                    ((IRegSubjectCopiesToDTO)copiesDataDataAddBean.getSelectedPageDTO()).setSubjectsDTO((ISubjectsDTO)getPageDTO());
                    if( ((IRegSubjectCopiesToDTO)copiesDataDataAddBean.getSelectedPageDTO()).getSecWorkCenterUsersDTO().getKwtCitizensResidentsDTO() !=null )
                    {
                    (((IRegSubjectCopiesToDTO)copiesDataDataAddBean.getSelectedPageDTO()).getSecWorkCenterUsersDTO()).setCivilname(((KwtCitizensResidentsDTO)((SecWorkCenterUsersDTO)((RegSubjectCopiesToDTO)getSelectedDTOS().get(0)).getSecWorkCenterUsersDTO()).getKwtCitizensResidentsDTO()).getFullName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return "CopiesDataDataAdd";
    }

    public void setReasontypecode(long reasontypecode) {
        this.reasontypecode = reasontypecode;
    }

    public long getReasontypecode() {
        return reasontypecode;
    }

    public void setSelectedModulesKeyDufalut(long selectedModulesKeyDufalut) {
        this.selectedModulesKeyDufalut = selectedModulesKeyDufalut;
    }

    public long getSelectedModulesKeyDufalut() {
        selectedModulesKeyDufalut = -100L;
        return selectedModulesKeyDufalut;
    }
    public String back() {
    return "backToSubList";
    }
//    public String back() {
//        Long subjectCode = null;
//        Long categoryCode = null;
//        ISubjectsDTO subjectsDTO= ((ISubjectsDTO)getPageDTO());
//
//        if (subjectsDTO != null) {
//            if (subjectsDTO.getSubjectsDTOList() != null) {
//                subjectCode = 
//                        ((ISubjectsEntityKey)subjectsDTO.getCode()).getSubjectCode();
////                setSelectedMinistryryId(subjectCode.toString());
////
////                if (IWorkCentersDTO.getMinistriesDTO().getCatsDTO() != null) {
////                    categoryCode = 
////                            ((IOrgCatsEntityKey)IWorkCentersDTO.getMinistriesDTO().getCatsDTO().getCode()).getCatCode();
////                }
//                setSelectedCategoryId(categoryCode.toString());
//            }
//        }
//        return "backToSubList";
    
}


