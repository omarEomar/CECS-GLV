package com.beshara.csc.gn.map.presentation.backingbean.societyrelations;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IRelationsDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;


public class ListBean extends LookUpBaseBean {
    private static final String relationTypeCode = "2";
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.map.presentation.resources.map";

    public ListBean() {
        setPageDTO(MapDTOFactory.createRelationsDTO());
        setSelectedPageDTO(MapDTOFactory.createRelationsDTO());
        setClient(MapClientFactory.getRelationsClient());
        //  setSingleSelection(true);
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        return app;
    }

    public String goToAdd() {
        return "socrelationaddPage";
    }

    public String goToEdit() {
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        SocietyRelationAddBean societyRelationAddBean =
            (SocietyRelationAddBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{societyRelationAddBean}").getValue(FacesContext.getCurrentInstance());
        try {
            if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
                IRelationsDTO dto = (IRelationsDTO)getSelectedDTOS().get(0);
                Long objectTypeCode = dto.getObjtypeCode();
                Long soc1Code = dto.getSoc1Code();
                Long soc2Code = dto.getSoc2Code();
                Long relationCode = dto.getReltypeCode();
                list =
MapClientFactory.getDataClient().ListByTypeAndSoc1AndSoc2AndMappedFilter(objectTypeCode, soc1Code, soc2Code, true);
                if (relationTypeCode.equals(relationCode.toString()) && list != null && list.size() > 0) {
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "conNotEditRelation"));
                    return "";
                } else {
                    societyRelationAddBean.setSelectedObjectTypeId(objectTypeCode.toString());
                    societyRelationAddBean.setSelectedSocityFrom(soc1Code.toString());
                    societyRelationAddBean.setSelectedSocity2Id(soc2Code.toString());
                    societyRelationAddBean.setSelectedRelationTypeCode(relationCode.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "socrelationEditPage";
    }

    public void delete() throws DataBaseException, ItemNotFoundException {
        SharedUtilBean shared_util = getSharedUtil();
        IRelationsDTO dto = (IRelationsDTO)getSelectedDTOS().get(0);
        List calculateMappedValue;
        try {
            calculateMappedValue =
                    MapClientFactory.getDataClient().ListByTypeAndSoc1AndSoc2AndMappedFilter(dto.getObjtypeCode(),
                                                                                             dto.getSoc1Code(),
                                                                                             dto.getSoc2Code(), true);

            if (calculateMappedValue.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(getSelectedDTOS().get(0));
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                getDeleteStatusDTOS().add(resultDTO);
            } else {

                System.out.println("Calling delete()...");
                FacesContext ctx = FacesContext.getCurrentInstance();
                ExternalContext ex = ctx.getExternalContext();
                HttpSession session = (HttpSession)ex.getSession(true);
                if (getSelectedDTOS() == null)
                    setSelectedDTOS(loadSelectedDTOS());
                try {
                    setDeleteStatusDTOS(MapClientFactory.getRelationsClient().remove(getSelectedDTOS()));
                    if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                        getMyDataTable().setFirst(0);
                    }
                } catch (DataBaseException db) {
                    getSharedUtil().handleException(db);
                } catch (ItemNotFoundException item) {
                    shared_util.setErrMsgValue("ItemCanNotBeDeletedException");
                } catch (SharedApplicationException e) {
                    getSharedUtil().handleException(e);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                setSelectedDTOS(new ArrayList());
                //session.setAttribute("selectedDTOS", null);
                cancelSearch();
                restoreTablePosition();

            }
        } catch (SharedApplicationException e) {
        }
    }

    public void selectedRadioButton(ActionEvent event) throws Exception {
        setSelectedDTOS(new ArrayList());
        setIndexOfSelectedDataInDataTable(getMyDataTable().getRowIndex());
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
    }
    
}

