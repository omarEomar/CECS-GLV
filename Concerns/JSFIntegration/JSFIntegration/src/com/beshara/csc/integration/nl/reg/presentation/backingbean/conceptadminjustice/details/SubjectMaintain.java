package com.beshara.csc.nl.reg.presentation.backingbean.conceptadminjustice.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGAdminJusticeDTO;
import com.beshara.csc.nl.reg.business.dto.IREGAdminJusticeSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.REGAdminJusticeSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.dto.SubjectsDTO;
import com.beshara.csc.nl.reg.business.entity.IREGAdminJusticeEntityKey;
import com.beshara.csc.nl.reg.business.entity.ISubjectsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.TreeManyToManyDetails;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;


public class SubjectMaintain extends TreeManyToManyDetails {

    private Long deleted = ISystemConstant.DELEDTED_ITEM;
    private Long added = ISystemConstant.ADDED_ITEM;
    TreeDivBean treepageDivBean;
    public SubjectMaintain() {
        setLookupAddDiv("divSheardStyle1");
        treepageDivBean = (TreeDivBean)evaluateValueBinding("TreePaggedDivBean");
        treepageDivBean.setBundleName("com.beshara.csc.nl.reg.presentation.resources.reg_");
        treepageDivBean.setMyTableData(new ArrayList());
        treepageDivBean.setRootName("reg_subjects");
        treepageDivBean.setClient(RegClientFactory.getSubjectsClientForCenter());
        treepageDivBean.setPageDTO(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setDto(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setDtoDetails(RegDTOFactory.createSubjectsDTO());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        return app;
    }

    public void initTreeDivBean() {
            // to avoid getall by the tree client
            try {
                setAvailableDetails(RegClientFactory.getSubjectsClient().getFirstLevel());
                treepageDivBean.setMyTableData(getAvailableDetails());
                if (getCurrentDetails() != null && availableDetails != null)
                    for (int j = 0; j < getCurrentDetails().size(); j++) {
                        IBasicDTO dto = getCurrentDetails().get(j);
                        IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);
                        for (int i = 0; i < availableDetails.size(); i++) {
                            IBasicDTO availableDTO = availableDetails.get(i);
                            if(((ISubjectsDTO)codeNameDTO).getParentCode()!=null){
                if (((ISubjectsDTO)codeNameDTO).getParentCode().getKey().equals(availableDTO.getCode().getKey())) {
                    int z =((ISubjectsDTO)availableDetails.get(i)).getChildernNumbers().intValue();
                    if (z != 0) {
                        ((ISubjectsDTO)availableDetails.get(i)).setChildernNumbers(new Long(z-1));
                    }
                }
                            }    }}
            } catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList());
                e.printStackTrace();
            } catch (Exception e) {
                setAvailableDetails(new ArrayList());
                e.printStackTrace();
            }
            treepageDivBean.setTreeNodeBase(null);
            //the add button in the div will invoke manytomany add function
            MethodBinding mb =evaluateMethodBinding("subjectMaintain" + ".add",null);
            treepageDivBean.getOkCommandButton().setAction(mb);
            treepageDivBean.getOkCommandButton().setReRender("dataT_data_panel,searchText,OperationBar,okPanel2");
            ValueBinding vb = 
                FacesContext.getCurrentInstance().getApplication().createValueBinding("#{(!TreePaggedDivBean.enabledRoot )&& (TreePaggedDivBean.selectionNo==1)}");
            treepageDivBean.getOkCommandButton().setValueBinding("rendered", vb);
             MethodBinding mb2 = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{subjectMaintain.cancelTreeSearch}",null);
            treepageDivBean.getCancelSearchCommandButton().setAction(mb2);
            //loading customized initialization so if you extends this class you must overrid following method 
            try {
                treepageDivBean.cancelSearchTree();
            } catch (Exception e) {
               e.printStackTrace(); // TODO
            }

    }

    public void initializeTreeDiv() {
        treepageDivBean = (TreeDivBean)evaluateValueBinding("TreePaggedDivBean");
        treepageDivBean.setBundleName("com.beshara.csc.nl.reg.presentation.resources.reg_");
        treepageDivBean.setMyTableData(new ArrayList());
        treepageDivBean.setRootName("reg_subjects");
        treepageDivBean.setClient(RegClientFactory.getSubjectsClient());
        treepageDivBean.setPageDTO(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setDto(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setDtoDetails(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setSelectionNo(0);
        treepageDivBean.setReadCenter(true);
        treepageDivBean.setSelectedNodeId("");
        treepageDivBean.setSearchMode(false);
        treepageDivBean.setShowTreeContent(true);
        treepageDivBean.setSearchQuery("");
        treepageDivBean.setSerachResult(false);
        treepageDivBean.setDtoDetails(new SubjectsDTO());

    }

    public void cancelTreeSearch() {

        treepageDivBean.setSearchQuery("");
        treepageDivBean.setSerachResult(false);
        treepageDivBean.setSearchMode(false);
        treepageDivBean.setSelectionNo(0);
        treepageDivBean.setSelectedNodeId("");
        treepageDivBean.setMyTableData(new ArrayList());
        treepageDivBean.setTreeNodeBase(null);
        treepageDivBean.getHtmlTree().collapseAll();
        treepageDivBean.setReadCenter(true);
        treepageDivBean.setDtoDetails(RegDTOFactory.createSubjectsDTO());
        try {
            treepageDivBean.cancelSearchTree();
        } catch (Exception e) {
           e.printStackTrace(); // TODO
        }
        treepageDivBean.setSearchType(1);
    }

    public void openSubjectAddDiv() {
        try {
            treepageDivBean.setSearchMode(false);
           treepageDivBean.setSearchQuery("");
            treepageDivBean.setSerachResult(false);
            treepageDivBean.getHtmlTree().collapseAll();
            treepageDivBean.setReadCenter(true);
            treepageDivBean.setSelectedNodeId("");
            treepageDivBean.setSelectionNo(0);
             treepageDivBean.setEnableSearchByCode(true);
         } catch (Exception e) {
            e.printStackTrace();
        }
        initTreeDivBean();
        treepageDivBean.setSearchType(1);
    }


    public void cancelSearchTree() throws DataBaseException, RemoteException, 
                                          Exception {
        treepageDivBean = (TreeDivBean)evaluateValueBinding("TreePaggedDivBean");
        treepageDivBean.cancelSearchTree();
        treepageDivBean.getHtmlTree().collapseAll();
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return ((IREGAdminJusticeSubjectsDTO)dto).getSubjectsDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        IREGAdminJusticeSubjectsDTO adminJusticeSubjectsDTO = RegDTOFactory.createREGAdminJusticeSubjectsDTO();
        Long adminJusticCode = null;
        IREGAdminJusticeDTO adminJusticeDTO = null;
        Long subjectsCode = null;
        if (this.getMasterEntityKey() != null) {
            adminJusticCode = 
                    ((IREGAdminJusticeEntityKey)this.getMasterEntityKey()).getAdmjusticeCode();
            adminJusticeDTO =RegDTOFactory.createREGAdminJusticeDTO();
            adminJusticeDTO.setCode(this.getMasterEntityKey());
            adminJusticeSubjectsDTO.setAdminJusticeDTO(adminJusticeDTO);
        }
        adminJusticeSubjectsDTO.setSubjectsDTO((ISubjectsDTO)dto);
        subjectsCode = ((ISubjectsEntityKey)dto.getCode()).getSubjectCode();
        adminJusticeSubjectsDTO.setCode(RegEntityKeyFactory.createREGAdminJusticeSubjectsEntityKey(adminJusticCode, 
                                                                             subjectsCode));

        return adminJusticeSubjectsDTO;
    }


    public void removeCurrentFromAvailable() {

        System.out.println("removing current");
        if (getCurrentDetails() != null && availableDetails != null)
            for (int j = 0; j < getCurrentDetails().size(); j++) {
                IBasicDTO dto = getCurrentDetails().get(j);
                IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);
                boolean exist = false;
                for (int i = 0; i < availableDetails.size(); i++) {
                    IBasicDTO availableDTO = availableDetails.get(i);

                    if (codeNameDTO.getCode().getKey().equals(availableDTO.getCode().getKey())) {
                        exist = true;
                        if (dto.getStatusFlag() != null &&dto.getStatusFlag().longValue() != deleted.longValue()) {
                            if (((IREGAdminJusticeSubjectsDTO)dto).getSubjectsDTO().getLeafFlag() ==1) {
                                availableDetails.remove(availableDTO);
                            }
                        }

                        if (dto.getStatusFlag() == null &&
                        ((IREGAdminJusticeSubjectsDTO)dto).getSubjectsDTO().getLeafFlag() == 1) {
                            availableDetails.remove(availableDTO);
                        }
                    }
                }

                if (!exist && dto.getStatusFlag() != null && 
                    dto.getStatusFlag().longValue() == deleted.longValue()) {

                    if (isSearchMode() == true && 
                        containSubString(codeNameDTO.getName())) {
                        availableDetails.add(codeNameDTO);

                    } else if (isSearchMode() == false) {

                        availableDetails.add(codeNameDTO);


                    }
                }

            }
    }

    public List<IBasicDTO> getAvailableDetails() throws DataBaseException, 
                                                        SharedApplicationException {

        //if null get it from the database
        if ((availableDetails == null || availableDetails.size() == 0) && 
            isSearchMode() == false) {
            try {
                this.getListAvailable();
                //   rebuildTree();
            } //user override to get avilable list from the databse
            catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList<IBasicDTO>(0));
            }
        }
        // null reinitialize
        if (availableDetails == null)
            availableDetails = new ArrayList<IBasicDTO>(0);

        //after getting from db remove previously selected items from this list
        removeCurrentFromAvailable();

        return availableDetails;
    }
    public void add() {

        // added for tree operation
        if (getSelectedAvailableDetails() == null)
            setSelectedAvailableDetails(new ArrayList<IBasicDTO>(0));

        if (!isEnableNotLeaf()) {

            if (getSelectedAvailableDetails() != null && 
                getSelectedAvailableDetails().size() > 0)
                getSelectedAvailableDetails().clear();

            if (treepageDivBean.getDtoDetails() != null && 
                treepageDivBean.getDtoDetails().getCode() != null)
                getSelectedAvailableDetails().add(treepageDivBean.getDtoDetails());

        }
        // if selected node is parent has childern 
        else {
            //TODO

        }
        // filtering part of selected node 
        if (getSelectedAvailableDetails() != null)
            for (int i = 0; i < getSelectedAvailableDetails().size(); i++) {
                IBasicDTO dto = getSelectedAvailableDetails().get(i);

                boolean exist = false;

                for (int j = 0; j < getCurrentDetails().size(); j++) {
                    IBasicDTO current = getCurrentDetails().get(j);
                    IBasicDTO mappedCurrent = this.mapToCodeNameDTO(current);

                    if (mappedCurrent.getCode().getKey().equals(dto.getCode().getKey())) {

                        exist = true;
                        if (current.getStatusFlag() != null && 
                            current.getStatusFlag().longValue() == 
                            deleted.longValue()) {
                            current.setStatusFlag(null);
                            this.resetDetailDTO(current);
                            availableDetails.remove(dto);

                            getSelectedAvailableDetails().remove(i);
                            i--;
                        }
                    }
                    System.out.println("add inner loop");
                }

                if (!exist) {

                    IBasicDTO mdto = this.mapToDetailDTO(dto);
                    mdto.setStatusFlag(added);

                    getCurrentDetails().add(mdto);
                    availableDetails.remove(dto);

                    getSelectedAvailableDetails().remove(i);
                    i--;
                }
                System.out.println("add end");

            }
        this.resetSelection();

        if (treepageDivBean.isSearchMode())
            this.cancelSearchAvailable();

    }
    public void delete() {
        if (getCurrentDetails() == null)
            setCurrentDetails(new ArrayList<IBasicDTO>());
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() > 0) {


            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);
                if (getCurrentSelectedDetail(selected).getStatusFlag() == 
                    null) {
                    getCurrentSelectedDetail(selected).setStatusFlag(deleted);
                    //((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    getSelectedCurrentDetails().remove(i);
                    i--;
                }
                if (getCurrentSelectedDetail(selected).getStatusFlag().longValue() == 
                    added.longValue()) {
                    getCurrentDetails().remove(getCurrentSelectedDetail(selected));
                    //bugs
                    // ((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    availableDetails.add(selected);

                    getSelectedCurrentDetails().remove(i);
                    i--;
                }

            }


        }
        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                         this.getCurrentDetails());
        this.resetSelection();

        if (treepageDivBean.isSearchMode())
           {      
            treepageDivBean.setMyTableData(this.availableDetails);
            treepageDivBean.setTreeNodeBase(null);
            treepageDivBean.setShowTreeContent(true);
            treepageDivBean.setSearchQuery("");
            treepageDivBean.setSearchMode(false);}

    }

}
