package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationExcute;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.nl.reg.business.client.ICancelReasonsClient;
import com.beshara.csc.nl.reg.business.client.IRegCancelReasonsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.ICancelReasonsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegCancelReasonsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.ICancelReasonsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegCancelReasonsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class CancelRegulationListBean extends ManyToManyDetailsMaintain {
    private static final String BACK_BEAN_NAME = "cancelRegulationCycleListBean";
    private List cancelReasonsList = new ArrayList();
    private ICancelReasonsClient cancelReasonsClient = 
        RegClientFactory.getCancelReasonsClientForCenter();
    private static final String RESOURCE_BUNDLE_NAME_REG = 
        "com.beshara.csc.nl.reg.presentation.resources.reg";
    private static final String RESOURCE_BUNDLE_KEY_CANCELLATION_SUCCEDED = 
        "cancellation_succeeded";
    private static final String RESOURCE_BUNDLE_KEY_CANCEL_REGULATION = 
        "cancel_regulation";
    private String selectedCanreasonCode = "";

    public CancelRegulationListBean() {
        super.setClient(RegClientFactory.getRegCancelReasonsClientForCenter());
        super.setPageDTO(RegDTOFactory.createRegulationsDTO());
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createRegCancelReasonsDTO());
        setDivMainContentMany("CancelRegulationContent");
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowbar(true);
        app.setShowdatatableContent(true);
        app.setShowNavigation(true);
        app.setShowLookupAdd(true);
        app.setShowDelAlert(true);
        app.setShowWizardBar(false);
        app.setShowsteps(false);
        app.setShowDelConfirm(true);
        app.setManyToMany(true);
        return app;
    }

    public String back() {
        return "executeregulationlist";
    }

    public void getAll() throws DataBaseException {
        setMyTableData(getCurrentDetails());
        this.reinitializeSort();
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }

    }

    public void setCancelReasonsList(List cancelReasonsList) {
        this.cancelReasonsList = cancelReasonsList;
    }

    public List getCancelReasonsList() throws DataBaseException, 
                                              SharedApplicationException {
        setAvailableDetails(cancelReasonsClient.getCodeName());
        cancelReasonsList = getAvailableDetails();
        if (cancelReasonsList == null) {
            cancelReasonsList = new ArrayList();
        }

        return cancelReasonsList;
    }

    public void setSelectedCanreasonCode(String selectedCanreasonCode) {
        this.selectedCanreasonCode = selectedCanreasonCode;
    }

    public String getSelectedCanreasonCode() {
        return selectedCanreasonCode;
    }

    public void getListAvailable() throws DataBaseException, 
                                          SharedApplicationException {

    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {

        ICancelReasonsDTO cancelDTO = RegDTOFactory.createCancelReasonsDTO();
        if (((IRegCancelReasonsEntityKey)dto.getCode()).getCancelReasonsCode() != 
            null) {
            cancelDTO.setCode(RegEntityKeyFactory.createCancelReasonsEntityKey(((IRegCancelReasonsEntityKey)dto.getCode()).getCancelReasonsCode()));
        } else if (((IRegCancelReasonsDTO)dto).getCancelReasonsDTO() != null) {
            cancelDTO = 
                    (ICancelReasonsDTO)((IRegCancelReasonsDTO)dto).getCancelReasonsDTO();
        }
        cancelDTO.setName(((IRegCancelReasonsDTO)dto).getCancelReasonsDTO().getName());
        if (dto.getStatusFlag() != null) {
            cancelDTO.setStatusFlag(dto.getStatusFlag());
        }
        return cancelDTO;

    }

    public void save() {
        setSelectedAvailableDetails(new ArrayList());
        for (int i = 0; i < this.availableDetails.size(); i++) {
            IBasicDTO availableDTO = availableDetails.get(i);
            if (getSelectedCanreasonCode().equals(availableDTO.getCode().getKey())) {
                getSelectedAvailableDetails().add(availableDTO);
            }
        }
        super.add();
        unCheck();
        setCheckAllFlag(false);
        try {
            setSelectedCanreasonCode("");
            getCancelReasonsList();
        } catch (SharedApplicationException e) {
            // TODO
        } catch (DataBaseException e) {
            // TODO
        }
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        //////return regcancel to add to current deatail
        IRegCancelReasonsDTO regcancelDTO = 
            RegDTOFactory.createRegCancelReasonsDTO();
        regcancelDTO.setRegulationsDTO(getPageDTO());
        regcancelDTO.setCancelReasonsDTO(dto);
        regcancelDTO.setCode(RegEntityKeyFactory.createRegCancelReasonsEntityKey(((IRegulationsDTO)getPageDTO()).getRegtypeCode(), 
                                                                                 ((IRegulationsDTO)getPageDTO()).getRegyearCode(), 
                                                                                 ((IRegulationsDTO)getPageDTO()).getRegulationNumber(), 
                                                                                 ((ICancelReasonsDTO)dto).getCanreasonCode()));
        return regcancelDTO;
    }

    public void saveAndNew() {
        save();

    }

    public void cancelAdd() {
        setSelectedCanreasonCode("");
    }

    public void preAdd() {
        System.out.println("Calling preAdd()...");
        setSuccess(false);
        setShowErrorMsg(false);
        setSelectedCanreasonCode("");

    }

    public String cancelRegulation() {
        System.out.println("Calling cancelRegulation()...");
        RegulationsExcuteListBean regulationListBean = 
            (RegulationsExcuteListBean)evaluateValueBinding("regulationsExcuteListBean");
        IRegulationsDTO regulationsDTO = 
            (IRegulationsDTO)regulationListBean.getSelectedDTOS().get(0);
        try {
            if (getCurrentDetails() != null && 
                getCurrentDetails().size() != 0) {
                for (int i = 0; i < getCurrentDetails().size(); i++) {
                    ((IRegCancelReasonsDTO)getCurrentDetails().get(i)).setRegulationsDTO(regulationsDTO);
                    if (((IRegCancelReasonsDTO)getCurrentDetails().get(i)).getCancelReasonsDTO().getCode() == 
                        null) {
                        ((IRegCancelReasonsDTO)getCurrentDetails().get(i)).getCancelReasonsDTO().setCode(RegEntityKeyFactory.createCancelReasonsEntityKey(((ICancelReasonsDTO)((IRegCancelReasonsDTO)getCurrentDetails().get(i)).getCancelReasonsDTO()).getCanreasonCode()));
                    }
                }
                ((IRegCancelReasonsClient)getClient()).cancelRegulation(getCurrentDetails());

            }
            System.out.println("Canceled Regulation...");
            regulationListBean.setSelectedRadio("");
            regulationListBean.cancelSearch();
            regulationListBean.setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
            regulationListBean.setPageDTO(regulationListBean.getClient().getByIdInCenter(regulationsDTO.getCode()));
             if (regulationListBean.getSelectedDTOS() != null) {
                regulationListBean.getSelectedDTOS().clear();
            }
            regulationListBean.setMyTableData(null);
            regulationListBean.getAll();
            regulationListBean.getPageIndex((String)regulationsDTO.getCode().getKey());
            regulationListBean.getHighlightedDTOS().add(regulationsDTO);
            regulationListBean.highlighDataTable("regulationsExcuteListBean");
            getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE_NAME_REG, 
                                          RESOURCE_BUNDLE_KEY_CANCELLATION_SUCCEDED);
        } catch (ItemNotFoundException e) {
            getSharedUtil().handleException(e, RESOURCE_BUNDLE_NAME_REG, 
                                            "error_message_cancelation_failed");
            e.printStackTrace();
            return "executeregulationlist"; // TODO
        } catch (NoResultException e) {
            getSharedUtil().handleException(e, RESOURCE_BUNDLE_NAME_REG, 
                                            "error_message_cancelation_failed");
            e.printStackTrace();
            return "executeregulationlist"; // TODO
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, RESOURCE_BUNDLE_NAME_REG, 
                                            "error_message_cancelation_failed");
            return "executeregulationlist"; // TODO
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e, RESOURCE_BUNDLE_NAME_REG, 
                                            "error_message_cancelation_failed");
            e.printStackTrace();
            return "executeregulationlist"; // TODO
        } catch (TransactionException e) {
            getSharedUtil().handleException(e, RESOURCE_BUNDLE_NAME_REG, 
                                            "error_message_cancelation_failed");
            e.printStackTrace();
            return "executeregulationlist"; // TODO

        } catch (Exception e) {
            getSharedUtil().handleException(e, RESOURCE_BUNDLE_NAME_REG, 
                                            "error_message_cancelation_failed");
            e.printStackTrace();
            return "executeregulationlist"; // TODO

        }
        return "executeregulationlist";
    }

    public IBasicDTO getCurrentSelectedDetail(IBasicDTO selected) {
        try {
            for (IBasicDTO dto: currentDetails) {
                if (((ICancelReasonsEntityKey)selected.getCode()).getCancelReasonsCode().equals(((ICancelReasonsEntityKey)this.mapToCodeNameDTO(dto).getCode()).getCancelReasonsCode())) {
                    ((IRegCancelReasonsDTO)dto).setRegulationsDTO(getPageDTO());
                    return dto;
                    //break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete() {
        if (currentDetails == null)
            currentDetails = new ArrayList<IBasicDTO>();
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() > 0) {


            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);
                IBasicDTO dto = getCurrentSelectedDetail(selected);
                if (dto.getStatusFlag() == null) {
                    dto.setStatusFlag(deleted);
                    //((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    getSelectedCurrentDetails().remove(selected);
                    i--;
                }
                if (dto.getStatusFlag().longValue() == added.longValue()) {
                    for (int j = 0; j < currentDetails.size(); j++) {
                        IBasicDTO currentDetailDtO = currentDetails.get(j);
                        if (((ICancelReasonsEntityKey)mapToCodeNameDTO(currentDetailDtO).getCode()).getCancelReasonsCode().equals(((ICancelReasonsEntityKey)selected.getCode()).getCancelReasonsCode()))
                            currentDetails.remove(j);

                    }
                    //bugs
                    // ((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    availableDetails.add(selected);

                    getSelectedCurrentDetails().remove(selected);
                    i--;
                }

            }


        }
        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                         this.getCurrentDetails());
        this.resetSelection();
    }


    public void selectedCurrent(ActionEvent event) throws Exception {
        super.selectedCurrent(event);
    }

}
