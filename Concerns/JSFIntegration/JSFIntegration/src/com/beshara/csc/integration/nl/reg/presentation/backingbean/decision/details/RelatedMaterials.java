package com.beshara.csc.nl.reg.presentation.backingbean.decision.details;

import com.beshara.base.dto.ClientDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.client.IREGMaterialRelationTypesClient;
import com.beshara.csc.nl.reg.business.client.IRegDecisionMaterialsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IREGMaterialRelationTypesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialRelsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegDecisionMaterialRelsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegDecisionMaterialsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.DecisionMaterialsMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class RelatedMaterials extends BaseBean {

    private DecisionListBean decisionListBean;
    private List<IRegDecisionMaterialsDTO> decMaterialsList;
    private List<IRegDecisionMaterialRelsDTO> relatedMaterialsList;
    private List<IRegDecisionMaterialRelsDTO> initialRelatedMaterialsList;
    private List<IRegDecisionMaterialsDTO> selectedMaterialsList = 
        new ArrayList<IRegDecisionMaterialsDTO>();
    private List<IRegDecisionMaterialsDTO> selectedRelMaterialsList = 
        new ArrayList<IRegDecisionMaterialsDTO>();

    private HtmlDataTable relatedDataTable = new HtmlDataTable();
    private DecisionMaterialsMaintain decisionMaterialsMaintainBean;
    private IRegDecisionMaterialsDTO currentMaterial;

    private Map relatedKeys = new HashMap();

    private IDecisionsDTO selectedDecision;

    private boolean searchedForDec;

    private List<IREGMaterialRelationTypesDTO> relationTypes;

    private int relatedMaterialSize = 0;
    /////////////////////
     private boolean searchActive;

    public RelatedMaterials() {

        decisionListBean = 
                (DecisionListBean)evaluateValueBinding("decisionListBean");

        if (currentMaterial == null) {
            decisionMaterialsMaintainBean = 
                    (DecisionMaterialsMaintain)evaluateValueBinding("decisionMaterialsMaintainBean");

            currentMaterial = 
                    (IRegDecisionMaterialsDTO)decisionMaterialsMaintainBean.getPageDTO();

            if (currentMaterial != null && 
                currentMaterial.getRelatedMaterials() != null) {

                filterCurrentRelatedMaterial(currentMaterial.getRelatedMaterials());


            }

            if (initialRelatedMaterialsList == null) {
                initialRelatedMaterialsList = 
                        deepCopyList(currentMaterial.getRelatedMaterials());
            }
        }

        setDivMainContent("divContent1Dynamic2");
        setDivSearch("materialDivSearch");
        setClient(RegClientFactory.getRegDecisionMaterialsClient());

        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList());
        setSearchActive(false);
    }

    public AppMainLayout appMainLayoutBuilder() {

        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowSearch(true);
        app.setShowsteps(false);
        app.setShowWizardBar(false);

        return app;
    }

    public void search() {
        decisionListBean.getDecisionSearchDTO().setRegulationStatus(1l);
        List<IDecisionsDTO> decsList = 
            (List<IDecisionsDTO>)decisionListBean.getSearchResult();

        DecisionMaintainBean decisionMaintainBean = 
            (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
        IDecisionsDTO selectedIDecisionsDTO = 
            (IDecisionsDTO)decisionMaintainBean.getPageDTO();
        if (selectedIDecisionsDTO.getCode() != null) {
            for (IDecisionsDTO regDTO: decsList) {
                if (regDTO.getCode().getKey().equals(selectedIDecisionsDTO.getCode().getKey())) {
                    decsList.remove(regDTO);
                    break;
                }
            }
        }

        getLovBaseBean().setMyTableData(decsList);
        getLovBaseBean().setSelectedRadio("");
        searchedForDec = true;
        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtLovDiv();");
    }

    public void resetSearch() {

        getLovBaseBean().setMyTableData(null);
        decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().repositionPage(0);
        searchedForDec = false;
        setSearchActive(true);
        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtLovDiv();");
         }

    public void loadMaterials() {

        List selectedItems = getLovBaseBean().getSelectedDTOS();

        if (selectedItems != null && selectedItems.size() > 0) {

            selectedDecision = (IDecisionsDTO)selectedItems.get(0);

            try {
                decMaterialsList = 
                        (List)((IRegDecisionMaterialsClient)getClient()).getByDecision(selectedDecision.getCode());
            } catch (SharedApplicationException e) {
                decMaterialsList = new ArrayList<IRegDecisionMaterialsDTO>();
            } catch (DataBaseException e) {
                decMaterialsList = new ArrayList<IRegDecisionMaterialsDTO>();
            }

            excludeOldMaterials(decMaterialsList);
        }
        setSearchActive(false);
        setJavaScriptCaller("ignoremyFormValidation();javascript:hideLookUpDiv(window.blocker,window.divSearch,null,null);");
        
    }

    public String back() {
        currentMaterial.setRelatedMaterials(initialRelatedMaterialsList);
        return goToMaterials();
    }

    public String goToMaterials() {
        DecisionMaintainBean decisionMaintainBean = 
            (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");

        return decisionMaintainBean.goToStep("decMaterialsKey");
    }

    public void moveSelectedMaterialsToRelated() {

        if (relatedMaterialsList == null) {
            relatedMaterialsList = 
                    new ArrayList<IRegDecisionMaterialRelsDTO>();
        }
        for (IRegDecisionMaterialsDTO dto: selectedMaterialsList) {
            IRegDecisionMaterialRelsDTO relMat = getNewRelatedMaterial(dto);
            relMat.getRelatedMaterialDTO().setDecisionsDTO(selectedDecision);
            relatedMaterialsList.add(relMat);
            dto.setChecked(false);
        }

        decMaterialsList.removeAll(selectedMaterialsList);
        addKeys(selectedMaterialsList);

        selectedMaterialsList.clear();


    }

    public void moveAllMaterialsToRelated() {

        if (relatedMaterialsList == null) {
            relatedMaterialsList = 
                    new ArrayList<IRegDecisionMaterialRelsDTO>();
        }

        for (IRegDecisionMaterialsDTO dto: decMaterialsList) {
            IRegDecisionMaterialRelsDTO relMat = getNewRelatedMaterial(dto);
            relMat.getRelatedMaterialDTO().setDecisionsDTO(selectedDecision);
            relatedMaterialsList.add(relMat);
        }
        addKeys(decMaterialsList);
        decMaterialsList.clear();

    }

    public void moveSelectedRelatedToMaterials() {

        if (decMaterialsList == null) {
            decMaterialsList = new ArrayList<IRegDecisionMaterialsDTO>();
        }

        /*List<IRegDecisionMaterialsDTO> seledtedMaterials =
            deepCopyList(selectedMaterialsList);*/

        for (IRegDecisionMaterialsDTO seledtedMaterial: 
             selectedRelMaterialsList) {
            if (validRelatedMaterialForMoving(seledtedMaterial)) {
                decMaterialsList.add(seledtedMaterial);
            }
        }

        removeSelectedRelatedMaterial(selectedRelMaterialsList);
        removeKeys(selectedRelMaterialsList);
        selectedRelMaterialsList.clear();
    }


    public void filterCurrentRelatedMaterial(List<IRegDecisionMaterialRelsDTO> currentRelMaterialsList) {

        try {
            List addedList = new ArrayList();
            for (IRegDecisionMaterialRelsDTO seledtedMaterial: 
                 currentRelMaterialsList) {

                if (seledtedMaterial.getStatusFlag() == null || 
                    seledtedMaterial.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                    addedList.add(seledtedMaterial);
                }


            }
            relatedMaterialsList = addedList;
            addRelatedKeys(addedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveAllRelatedToMaterials() {

        if (decMaterialsList == null) {
            decMaterialsList = new ArrayList<IRegDecisionMaterialsDTO>();
        }

        for (IRegDecisionMaterialRelsDTO seledtedMaterial: 
             relatedMaterialsList) {
            if (validRelatedMaterialForMoving(seledtedMaterial.getRelatedMaterialDTO())) {
                decMaterialsList.add(seledtedMaterial.getRelatedMaterialDTO());
            }
        }

        relatedMaterialsList.clear();
        relatedKeys.clear();
    }

    public void selectedMaterialsCheckboxs(ActionEvent event) throws Exception {
        selectedCheckboxs(getMyDataTable(), 
                          RegDTOFactory.createRegDecisionMaterialsDTO());
    }

    public void selectedMaterialsCheckboxsAll(ActionEvent event) throws Exception {
        selectedCheckboxsAll(getMyDataTable(), 
                             RegDTOFactory.createRegDecisionMaterialsDTO());
    }

    public void selectedRelatedMaterialsCheckboxs(ActionEvent event) throws Exception {
        selectedCheckboxs(relatedDataTable, 
                          RegDTOFactory.createRegDecisionMaterialRelsDTO());
    }

    public void selectedRelatedMaterialsCheckboxsAll(ActionEvent event) throws Exception {
        selectedCheckboxsAll(relatedDataTable, 
                             RegDTOFactory.createRegDecisionMaterialRelsDTO());
    }

    public void selectedCheckboxs(HtmlDataTable dataTable, 
                                  Object obj) throws Exception {

        try {
            Set<IRegDecisionMaterialsDTO> selectedSet = 
                new HashSet<IRegDecisionMaterialsDTO>();

            IRegDecisionMaterialsDTO selected = null;

            if (obj instanceof IRegDecisionMaterialsDTO) {
                selectedSet.addAll(selectedMaterialsList);
                selected = (IRegDecisionMaterialsDTO)dataTable.getRowData();
            } else {
                selectedSet.addAll(selectedRelMaterialsList);
                IRegDecisionMaterialRelsDTO relatedDTO = 
                    (IRegDecisionMaterialRelsDTO)dataTable.getRowData();
                selected = relatedDTO.getRelatedMaterialDTO();
                selected.setChecked(relatedDTO.getChecked());
            }

            if (selected.getChecked()) {

                selected.setChecked(false);
                try {
                    selectedSet.add(selected);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                selected.setChecked(Boolean.TRUE);

                for (IBasicDTO item: selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }

                selected.setChecked(Boolean.FALSE);

            }

            if (obj instanceof IRegDecisionMaterialsDTO) {
                selectedMaterialsList.clear();
                selectedMaterialsList.addAll(selectedSet);
            } else {
                selectedRelMaterialsList.clear();
                selectedRelMaterialsList.addAll(selectedSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectedCheckboxsAll(HtmlDataTable dataTable, 
                                     Object obj) throws Exception {

        try {

            Set<IRegDecisionMaterialsDTO> selectedSet = 
                new HashSet<IRegDecisionMaterialsDTO>();

            selectedSet.addAll(selectedMaterialsList);

            Integer rowsCountPerPage = 
                (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());

            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");

            }

            int first = dataTable.getFirst();

            for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {

                dataTable.setRowIndex(j);

                if (!dataTable.isRowAvailable()) {
                    break;
                }

                IRegDecisionMaterialsDTO selected = null;
                if (obj instanceof IRegDecisionMaterialsDTO) {
                    selected = 
                            (IRegDecisionMaterialsDTO)dataTable.getRowData();
                } else {
                    selected = 
                            ((IRegDecisionMaterialRelsDTO)dataTable.getRowData()).getRelatedMaterialDTO();
                }

                if (this.isCheckAllFlag()) {

                    try {

                        selectedSet.add(selected);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    for (IBasicDTO item: selectedSet) {
                        if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                            selectedSet.remove(item);
                            break;
                        }
                    }

                }

            }

            selectedMaterialsList.clear();
            selectedMaterialsList.addAll(selectedSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String saveRelatedMaterials() {
        if(relatedMaterialsList == null){
            relatedMaterialsList = new ArrayList();
        }
        int count = relatedMaterialsList.size();
        currentMaterial.setRelatedMaterialsCount(new Long(count));
        updateRelatedMaterialsFlags();
        currentMaterial.setRelatedMaterials(relatedMaterialsList);

        return goToMaterials();
    }

    public void reOpenSearchDiv(ActionEvent ae) {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divSearch);");


        if (isUsingPaging()) {
            super.changePageIndex(ae);
        }

    }

    public void setRelatedMaterialsList(List<IRegDecisionMaterialRelsDTO> relatedMaterialsList) {
        this.relatedMaterialsList = relatedMaterialsList;
    }

    public List<IRegDecisionMaterialRelsDTO> getRelatedMaterialsList() {
        return relatedMaterialsList;
    }

    public void setDecMaterialsList(List<IRegDecisionMaterialsDTO> regMaterialsList) {
        this.decMaterialsList = regMaterialsList;
    }

    public List<IRegDecisionMaterialsDTO> getDecMaterialsList() {
        return decMaterialsList;
    }

    public void setRelatedDataTable(HtmlDataTable relatedDataTable) {
        this.relatedDataTable = relatedDataTable;
    }

    public HtmlDataTable getRelatedDataTable() {
        return relatedDataTable;
    }

    public void setSelectedMaterialsList(List<IRegDecisionMaterialsDTO> selectedMaterialsList) {
        this.selectedMaterialsList = selectedMaterialsList;
    }

    public List<IRegDecisionMaterialsDTO> getSelectedMaterialsList() {
        return selectedMaterialsList;
    }

    public void setRelatedKeys(Map relatedKeys) {
        this.relatedKeys = relatedKeys;
    }

    public Map getRelatedKeys() {
        return relatedKeys;
    }

    public void setCurrentMaterial(IRegDecisionMaterialsDTO currentMaterial) {
        this.currentMaterial = currentMaterial;
    }

    public IRegDecisionMaterialsDTO getCurrentMaterial() {
        return currentMaterial;
    }

    public void setSelectedDecision(IDecisionsDTO selectedReg) {
        this.selectedDecision = selectedReg;
    }

    public IDecisionsDTO getSelectedDecision() {
        return selectedDecision;
    }

    public void setInitialRelatedMaterialsList(List<IRegDecisionMaterialRelsDTO> initialRelatedMaterialsList) {
        this.initialRelatedMaterialsList = initialRelatedMaterialsList;
    }

    public List<IRegDecisionMaterialRelsDTO> getInitialRelatedMaterialsList() {
        return initialRelatedMaterialsList;
    }

    // **************************** START PRIVATE METHODS **********************

    private List deepCopyList(List list) {

        List copiedList = new ArrayList();

        if (list != null && list.size() > 0) {
            for (ClientDTO dto: (List<ClientDTO>)list) {
                try {
                    dto.setChecked(false);
                    copiedList.add(SharedUtilBean.deepCopyObject(dto));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return copiedList;
    }

    private void excludeOldMaterials(List<IRegDecisionMaterialsDTO> regMaterialsList) {

        List<IRegDecisionMaterialsDTO> newList = 
            new ArrayList<IRegDecisionMaterialsDTO>();

        for (IRegDecisionMaterialsDTO dto: regMaterialsList) {
            String key = dto.getCode().getKey().toString();
            if (!relatedKeys.containsKey(key)) {
                newList.add(dto);
            }
        }

        regMaterialsList.clear();
        regMaterialsList.addAll(newList);
    }

    private boolean validRelatedMaterialForMoving(IRegDecisionMaterialsDTO dto) {

        if (selectedDecision != null) {
            String regKey = 
                dto.getDectypeCode() + "*" + dto.getDecyearCode() + "*" + 
                dto.getDecisionNumber();

            if (regKey.equals(selectedDecision.getCode().getKey())) {
                return true;
            }
        }

        return false;

    }

    private void addRelatedKeys(List<IRegDecisionMaterialRelsDTO> seledtedMaterials) {

        List<IRegDecisionMaterialsDTO> toBeAddedMaterials = 
            new ArrayList<IRegDecisionMaterialsDTO>();
        for (IRegDecisionMaterialRelsDTO dto: seledtedMaterials) {
            toBeAddedMaterials.add(dto.getRelatedMaterialDTO());
        }

        addKeys(toBeAddedMaterials);
    }

    private void addKeys(List<IRegDecisionMaterialsDTO> seledtedMaterials) {
        for (IRegDecisionMaterialsDTO dto: seledtedMaterials) {
            String key = dto.getCode().getKey().toString();
            if (!relatedKeys.containsKey(key)) {
                relatedKeys.put(key, key);
            }
        }
    }

    private void removeSelectedRelatedMaterial(List<IRegDecisionMaterialsDTO> seledtedMaterials) {

        List<IRegDecisionMaterialRelsDTO> toBeRemoved = 
            new ArrayList<IRegDecisionMaterialRelsDTO>();

        for (IRegDecisionMaterialsDTO mat: seledtedMaterials) {
            for (IRegDecisionMaterialRelsDTO relatedMat: 
                 relatedMaterialsList) {
                if (mat.getCode().getKey().equals(relatedMat.getRelatedMaterialDTO().getCode().getKey())) {
                    toBeRemoved.add(relatedMat);
                }
            }
        }

        for (IRegDecisionMaterialRelsDTO relMat: toBeRemoved) {
            relatedMaterialsList.remove(relMat);
        }
    }

    private void removeKeys(List<IRegDecisionMaterialsDTO> seledtedMaterials) {
        for (IRegDecisionMaterialsDTO dto: seledtedMaterials) {
            String key = dto.getCode().getKey().toString();
            if (relatedKeys.containsKey(key)) {
                relatedKeys.remove(key);
            }
        }
    }

    private void removeRelatedKeys(List<IRegDecisionMaterialRelsDTO> seledtedRelatedMaterials) {
        List<IRegDecisionMaterialsDTO> seledtedMaterials = 
            new ArrayList<IRegDecisionMaterialsDTO>();
        for (IRegDecisionMaterialRelsDTO dto: seledtedRelatedMaterials) {
            seledtedMaterials.add(dto.getRelatedMaterialDTO());
        }
        removeKeys(seledtedMaterials);
    }

    private IRegDecisionMaterialRelsDTO getNewRelatedMaterial(IRegDecisionMaterialsDTO toBeLinkedMaterial) {

        IRegDecisionMaterialRelsDTO relatedMaterial = 
            RegDTOFactory.createRegDecisionMaterialRelsDTO();
        IRegDecisionMaterialsEntityKey materialKey=RegEntityKeyFactory.createRegDecisionMaterialsEntityKey(currentMaterial.getDectypeCode(),currentMaterial.getDecyearCode(),currentMaterial.getDecisionNumber(),currentMaterial.getDecmaterialCode());
        IRegDecisionMaterialsEntityKey relmaterialKey = (IRegDecisionMaterialsEntityKey)toBeLinkedMaterial.getCode();
        relatedMaterial.setDectypeCode(currentMaterial.getDectypeCode());
        relatedMaterial.setDecyearCode(currentMaterial.getDecyearCode());
        relatedMaterial.setDecisionNumber(currentMaterial.getDecisionNumber());
        relatedMaterial.setDecmaterialCode(currentMaterial.getDecmaterialCode());

        relatedMaterial.setRelatedMaterialDTO(toBeLinkedMaterial);
        IRegDecisionMaterialRelsEntityKey materialRelsEntityKey=RegEntityKeyFactory.createRegDecisionMaterialRelsEntityKey(materialKey.getDectypeCode(),materialKey.getDecyearCode(),materialKey.getDecisionNumber(),materialKey.getDecmaterialCode(), 
                                                                                                    relmaterialKey.getDecisionNumber(),relmaterialKey.getDecyearCode(),relmaterialKey.getDectypeCode(),relmaterialKey.getDecmaterialCode());        
        relatedMaterial.setCode(materialRelsEntityKey);
        return relatedMaterial;
    }

    private void updateRelatedMaterialsFlags() {

        Map initialKeys = new HashMap();
        if (initialRelatedMaterialsList != null) {
            for (IRegDecisionMaterialRelsDTO dto: 
                 initialRelatedMaterialsList) {
                String key = 
                    dto.getRelatedMaterialDTO().getCode().getKey().toString();
                if (!initialKeys.containsKey(key)) {
                    initialKeys.put(key, key);
                }
            }
        }

        //TODO flag Added items
        if (relatedMaterialsList != null) {
            for (IRegDecisionMaterialRelsDTO dto: relatedMaterialsList) {
                String key = 
                    dto.getRelatedMaterialDTO().getCode().getKey().toString();
                if (!initialKeys.containsKey(key)) {
                    dto.setStatusFlag(ISystemConstant.ADDED_ITEM);
                }
            }
        }

        //TODO flag Removed items
        if (initialRelatedMaterialsList != null) {
            for (IRegDecisionMaterialRelsDTO dto: 
                 initialRelatedMaterialsList) {
                String key = 
                    dto.getRelatedMaterialDTO().getCode().getKey().toString();
                if (!relatedKeys.containsKey(key)) {
                    if (dto.getStatusFlag() == null) {
                        dto.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                        relatedMaterialsList.add(dto);
                    }
                }
            }
        }
    }
    // **************************** END PRIVATE METHODS ************************

    public void setSearchedForDec(boolean searchedForReg) {
        this.searchedForDec = searchedForReg;
    }

    public boolean isSearchedForDec() {
        return searchedForDec;
    }

    public void setRelationTypes(List<IREGMaterialRelationTypesDTO> relationTypes) {
        this.relationTypes = relationTypes;
    }

    public List<IREGMaterialRelationTypesDTO> getRelationTypes() {

        if (relationTypes == null) {
            IREGMaterialRelationTypesClient typesClient = 
                RegClientFactory.getREGMaterialRelationTypesClient();
            try {
                relationTypes = 
                        (List)typesClient.getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }

        return relationTypes;
    }

    public void setSelectedRelMaterialsList(List<IRegDecisionMaterialsDTO> selectedRelMaterialsList) {
        this.selectedRelMaterialsList = selectedRelMaterialsList;
    }

    public List<IRegDecisionMaterialsDTO> getSelectedRelMaterialsList() {
        return selectedRelMaterialsList;
    }

    public void setRelatedMaterialSize(int relatedMaterialSize) {
        this.relatedMaterialSize = relatedMaterialSize;
    }

    public int getRelatedMaterialSize() {
        if (getRelatedMaterialsList() != null && 
            getRelatedMaterialsList().size() > 0)
            return getRelatedMaterialsList().size();
        return 0;
    }
    public void openSearchDecision(){
        try {
        
            getLovBaseBean().cancelSearch();
            setLovBaseBean(new LOVBaseBean());
            getLovBaseBean().setMyTableData(new ArrayList());
            getLovBaseBean().setMyTableData(null);
            decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
            getLovBaseBean().setSelectedRadio("");
            getLovBaseBean().repositionPage(0);
            searchedForDec = false;
            setSearchActive(true);
               setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);");
               
           } catch (DataBaseException e) {
            e.printStackTrace();// TODO
        }
        
        }
             public void setSearchActive(boolean searchActive) {
                 this.searchActive = searchActive;
             }

             public boolean isSearchActive() {
                 return searchActive;
             }
             public void  activateValidation(){
                setSearchActive(true);
                 try {
                     getLovBaseBean().cancelSearch();
                     setLovBaseBean(new LOVBaseBean());
                     getLovBaseBean().setMyTableData(new ArrayList());
                     getLovBaseBean().setMyTableData(null);
                     decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                     getLovBaseBean().setSelectedRadio("");
                     getLovBaseBean().repositionPage(0);
                     searchedForDec = false;
                     setSearchActive(true);
                     
                     
                 } catch (DataBaseException e) {
                     e.printStackTrace();// TODO
                 }
                 
                 setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtLovDiv();");
             }
             public void  deactivateValidation(){
                setSearchActive(false);
               setJavaScriptCaller("ignoremyFormValidation();javascript:hideLookUpDiv(window.blocker,window.divSearch,null,null);");
                          }
    
    }

