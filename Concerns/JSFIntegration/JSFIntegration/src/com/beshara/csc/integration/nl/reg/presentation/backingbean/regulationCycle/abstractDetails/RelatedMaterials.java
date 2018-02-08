package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.nl.reg.business.client.IREGMaterialRelationTypesClient;
import com.beshara.csc.nl.reg.business.client.IREGRegulationMaterialsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGMaterialRelationTypesDTO;
import com.beshara.csc.nl.reg.business.dto.IREGRegulationMaterialRelDTO;
import com.beshara.csc.nl.reg.business.dto.IREGRegulationMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IREGRegulationMaterialRelEntityKey;
import com.beshara.csc.nl.reg.business.entity.IREGRegulationMaterialsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.AbstractRegulationsListBean;
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

import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class RelatedMaterials extends BaseBean {

    private AbstractRegulationsListBean regulationListBean;
    private List<IREGRegulationMaterialsDTO> regMaterialsList;
    private List<IREGRegulationMaterialRelDTO> relatedMaterialsList;
    private List<IREGRegulationMaterialRelDTO> initialRelatedMaterialsList;
    private List<IREGRegulationMaterialsDTO> selectedMaterialsList = 
        new ArrayList<IREGRegulationMaterialsDTO>();
    private List<IREGRegulationMaterialsDTO> selectedRelMaterialsList = 
        new ArrayList<IREGRegulationMaterialsDTO>();

    private HtmlDataTable relatedDataTable = new HtmlDataTable();
    private RegulationMaterialsMaintain regulationMaterialsMaintainBean;
    private IREGRegulationMaterialsDTO currentMaterial;

    private Map relatedKeys = new HashMap();

    private IRegulationsDTO selectedRegulation;

    private boolean searchedForReg;

    private List<IREGMaterialRelationTypesDTO> relationTypes;
    
    private int relatedMaterialSize=0;
    /////////////////////
     private boolean searchActive;

    public RelatedMaterials() {

        regulationListBean = 
                (AbstractRegulationsListBean)evaluateValueBinding("regulationsAbstractListBean");

        if (currentMaterial == null) {
            regulationMaterialsMaintainBean = 
                    (RegulationMaterialsMaintain)evaluateValueBinding("regulationCycleMaterialsMaintainBean");

            currentMaterial = 
                    (IREGRegulationMaterialsDTO)regulationMaterialsMaintainBean.getPageDTO();

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
        setClient(RegClientFactory.getREGRegulationMaterialsClient());

        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList());
        setSearchActive(false);
    }
    
    public void filterCurrentRelatedMaterial(List<IREGRegulationMaterialRelDTO> currentRelMaterialsList) {

        try {
            List addedList=new ArrayList();
            for (IREGRegulationMaterialRelDTO seledtedMaterial: currentRelMaterialsList) {
                if (seledtedMaterial.getStatusFlag() ==null || seledtedMaterial.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)){ 
                          addedList.add(seledtedMaterial);
                  }
            }
            relatedMaterialsList =addedList;
            addRelatedKeys(addedList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
    public List getSearchResult() {
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {

            if (regulationListBean.getRegulationsSearchDTO() != null) {
                if (regulationListBean.getRegulationsSearchDTO().getName() != null) {
                    if (!(regulationListBean.getRegulationsSearchDTO().getName().equals(""))) {
                        regulationListBean.getRegulationsSearchDTO().setName(formatSearchString(regulationListBean.getRegulationsSearchDTO().getName()));
                    }
                }
            }

            result =  RegClientFactory.getRegulationsClient().searchCenter(regulationListBean.getRegulationsSearchDTO());
        } catch (Exception db) {
            regulationListBean.setStringSearchType("false");
        }
        return result;
    }

    public void search() {
        regulationListBean.getRegulationsSearchDTO().setRegulationStatus(1l);
        List<IRegulationsDTO> regsList = getSearchResult();
        RegulationCycleMaintainBean regulationMaintainBean = 
            (RegulationCycleMaintainBean)evaluateValueBinding("regulationCycleMaintainBean");
        IRegulationsDTO selectedRegulationsDTO = 
            (IRegulationsDTO)regulationMaintainBean.getPageDTO();
        if (selectedRegulationsDTO.getCode() != null) {
            for (IRegulationsDTO regDTO: regsList) {
                if (regDTO.getCode().getKey().equals(selectedRegulationsDTO.getCode().getKey())) {
                    regsList.remove(regDTO);
                    break;
                }
            }
        }

        getLovBaseBean().setMyTableData(regsList);
        getLovBaseBean().setSelectedRadio("");
        searchedForReg = true;
        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtLovDiv();");
    }

    public void resetSearch() {

        getLovBaseBean().setMyTableData(null);
        regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().repositionPage(0);
        searchedForReg = false;   
        setSearchActive(true);
        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtLovDiv();");
        
    }

    public void loadMaterials() {

        List selectedItems = getLovBaseBean().getSelectedDTOS();

        if (selectedItems != null && selectedItems.size() > 0) {

            selectedRegulation = (IRegulationsDTO)selectedItems.get(0);

            try {
                regMaterialsList = 
                        (List)((IREGRegulationMaterialsClient)getClient()).getByRegulation(selectedRegulation.getCode());
            } catch (SharedApplicationException e) {
                regMaterialsList = new ArrayList<IREGRegulationMaterialsDTO>();
                
            } catch (DataBaseException e) {
                regMaterialsList = new ArrayList<IREGRegulationMaterialsDTO>();
            }

            excludeOldMaterials(regMaterialsList);
        }
        setSearchActive(false);
        setJavaScriptCaller("ignoremyFormValidation();javascript:hideLookUpDiv(window.blocker,window.divSearch,null,null);");
         }

    public String back() {
        currentMaterial.setRelatedMaterials(initialRelatedMaterialsList);
        return goToMaterials();
    }

    public String goToMaterials() {
        RegulationCycleMaintainBean regulationMaintainBean = 
            (RegulationCycleMaintainBean)evaluateValueBinding("regulationCycleMaintainBean");

        return regulationMaintainBean.goToStep("regMaterialsKey");
    }

    public void moveSelectedMaterialsToRelated() {

        if (relatedMaterialsList == null) {
            relatedMaterialsList = 
                    new ArrayList<IREGRegulationMaterialRelDTO>();
        }
        for (IREGRegulationMaterialsDTO dto: selectedMaterialsList) {
            IREGRegulationMaterialRelDTO relMat = getNewRelatedMaterial(dto);
            relMat.getRelatedMaterialDTO().setRegulationsDTO(selectedRegulation);
            relatedMaterialsList.add(relMat);
            dto.setChecked(false);
        }

        regMaterialsList.removeAll(selectedMaterialsList);
        
        addKeys(selectedMaterialsList);
        
        selectedMaterialsList.clear();

        

    }

    public void moveAllMaterialsToRelated() {

        if (relatedMaterialsList == null) {
            relatedMaterialsList = new ArrayList<IREGRegulationMaterialRelDTO>();
        }

        for (IREGRegulationMaterialsDTO dto: regMaterialsList) {
            IREGRegulationMaterialRelDTO relMat = getNewRelatedMaterial(dto);
            relMat.getRelatedMaterialDTO().setRegulationsDTO(selectedRegulation);
            relatedMaterialsList.add(relMat);
        }
        addKeys(regMaterialsList);
        regMaterialsList.clear();
        
    }

    public void moveSelectedRelatedToMaterials() {

        if (regMaterialsList == null) {
            regMaterialsList = new ArrayList<IREGRegulationMaterialsDTO>();
        }

        /*List<IREGRegulationMaterialsDTO> seledtedMaterials = 
            deepCopyList(selectedMaterialsList);*/

        for (IREGRegulationMaterialsDTO seledtedMaterial: selectedRelMaterialsList) {
            if (validRelatedMaterialForMoving(seledtedMaterial)) {
                regMaterialsList.add(seledtedMaterial);
            }
        }

        removeSelectedRelatedMaterial(selectedRelMaterialsList);

        

        removeKeys(selectedRelMaterialsList);
        selectedRelMaterialsList.clear();
    }

    public void moveAllRelatedToMaterials() {

        if (regMaterialsList == null) {
            regMaterialsList = new ArrayList<IREGRegulationMaterialsDTO>();
        }

        for (IREGRegulationMaterialRelDTO seledtedMaterial: 
             relatedMaterialsList) {
            if (validRelatedMaterialForMoving(seledtedMaterial.getRelatedMaterialDTO())) {
                regMaterialsList.add(seledtedMaterial.getRelatedMaterialDTO());
            }
        }

        relatedMaterialsList.clear();
        relatedKeys.clear();
    }

    public void selectedMaterialsCheckboxs(ActionEvent event) throws Exception {
        selectedCheckboxs(getMyDataTable(), RegDTOFactory.createREGRegulationMaterialsDTO());
    }

    /*public void selectedMaterialsCheckboxsAll(ActionEvent event) throws Exception {
        selectedCheckboxsAll(getMyDataTable(), 
                             RegDTOFactory.createREGRegulationMaterialsDTO());
    }*/

    public void selectedRelatedMaterialsCheckboxs(ActionEvent event) throws Exception {
        selectedCheckboxs(relatedDataTable, RegDTOFactory.createREGRegulationMaterialRelDTO());
    }

    /*public void selectedRelatedMaterialsCheckboxsAll(ActionEvent event) throws Exception {
        selectedCheckboxsAll(relatedDataTable, 
                             RegDTOFactory.createREGRegulationMaterialRelDTO());
    }*/

    public void selectedCheckboxs(HtmlDataTable dataTable, 
                                  Object obj) throws Exception {

        try {

            Set<IREGRegulationMaterialsDTO> selectedSet = 
                new HashSet<IREGRegulationMaterialsDTO>();

            IREGRegulationMaterialsDTO selected = null;
            if (obj instanceof IREGRegulationMaterialsDTO) {
                selectedSet.addAll(selectedMaterialsList);
                selected = (IREGRegulationMaterialsDTO)dataTable.getRowData();
            } else {
                selectedSet.addAll(selectedRelMaterialsList);
                IREGRegulationMaterialRelDTO relatedDTO = 
                    (IREGRegulationMaterialRelDTO)dataTable.getRowData();
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

            if (obj instanceof IREGRegulationMaterialsDTO) {
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

    /*public void selectedCheckboxsAll(HtmlDataTable dataTable, 
                                     Object obj) throws Exception {

        try {

            Set<IREGRegulationMaterialsDTO> selectedSet = 
                new HashSet<IREGRegulationMaterialsDTO>();

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

                IREGRegulationMaterialsDTO selected = null;
                if (obj instanceof IREGRegulationMaterialsDTO) {
                    selected = 
                            (IREGRegulationMaterialsDTO)dataTable.getRowData();
                } else {
                    selected = 
                            ((IREGRegulationMaterialRelDTO)dataTable.getRowData()).getRelatedMaterialDTO();
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

    }*/

    public String saveRelatedMaterials() {
        if(relatedMaterialsList == null){
            relatedMaterialsList = new ArrayList();
        }
        int count=relatedMaterialsList.size();
        currentMaterial.setRelatedMaterialsCount(new Long(count));
        updateRelatedMaterialsFlags();
        currentMaterial.setRelatedMaterials(relatedMaterialsList);

        return goToMaterials();
    }

    public void reOpenSearchDiv(ActionEvent ae) {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divSearch);");

        //Paging
        if (isUsingPaging()) {
            super.changePageIndex(ae);
        }

    }

    public void setRelatedMaterialsList(List<IREGRegulationMaterialRelDTO> relatedMaterialsList) {
        this.relatedMaterialsList = relatedMaterialsList;
    }

    public List<IREGRegulationMaterialRelDTO> getRelatedMaterialsList() {
        return relatedMaterialsList;
    }

    public void setRegMaterialsList(List<IREGRegulationMaterialsDTO> regMaterialsList) {
        this.regMaterialsList = regMaterialsList;
    }

    public List<IREGRegulationMaterialsDTO> getRegMaterialsList() {
        return regMaterialsList;
    }

    public void setRelatedDataTable(HtmlDataTable relatedDataTable) {
        this.relatedDataTable = relatedDataTable;
    }

    public HtmlDataTable getRelatedDataTable() {
        return relatedDataTable;
    }

    public void setSelectedMaterialsList(List<IREGRegulationMaterialsDTO> selectedMaterialsList) {
        this.selectedMaterialsList = selectedMaterialsList;
    }

    public List<IREGRegulationMaterialsDTO> getSelectedMaterialsList() {
        return selectedMaterialsList;
    }

    public void setRelatedKeys(Map relatedKeys) {
        this.relatedKeys = relatedKeys;
    }

    public Map getRelatedKeys() {
        return relatedKeys;
    }

    public void setCurrentMaterial(IREGRegulationMaterialsDTO currentMaterial) {
        this.currentMaterial = currentMaterial;
    }

    public IREGRegulationMaterialsDTO getCurrentMaterial() {
        return currentMaterial;
    }

    public void setSelectedRegulation(IRegulationsDTO selectedReg) {
        this.selectedRegulation = selectedReg;
    }

    public IRegulationsDTO getSelectedRegulation() {
        return selectedRegulation;
    }

    public void setInitialRelatedMaterialsList(List<IREGRegulationMaterialRelDTO> initialRelatedMaterialsList) {
        this.initialRelatedMaterialsList = initialRelatedMaterialsList;
    }

    public List<IREGRegulationMaterialRelDTO> getInitialRelatedMaterialsList() {
        return initialRelatedMaterialsList;
    }

    // **************************** START PRIVATE METHODS **********************

    private List deepCopyList(List list) {

        List copiedList = new ArrayList();

        if (list != null && list.size() > 0) {
            for (IClientDTO dto: (List<IClientDTO>)list) {
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

    private void excludeOldMaterials(List<IREGRegulationMaterialsDTO> regMaterialsList) {

        List<IREGRegulationMaterialsDTO> newList = 
            new ArrayList<IREGRegulationMaterialsDTO>();

        for (IREGRegulationMaterialsDTO dto: regMaterialsList) {
            String key = dto.getCode().getKey().toString();
            if (!relatedKeys.containsKey(key)) {
                newList.add(dto);
            }
        }

        regMaterialsList.clear();
        regMaterialsList.addAll(newList);
    }

    private boolean validRelatedMaterialForMoving(IREGRegulationMaterialsDTO dto) {

        if (selectedRegulation != null) {
            String regKey = 
                dto.getRegtypeCode() + "*" + dto.getRegyearCode() + "*" + 
                dto.getRegulationNumber();

            if (regKey.equals(selectedRegulation.getCode().getKey())) {
                return true;
            }
        }

        return false;

    }

    private void addRelatedKeys(List<IREGRegulationMaterialRelDTO> seledtedMaterials) {

        List<IREGRegulationMaterialsDTO> toBeAddedMaterials = 
            new ArrayList<IREGRegulationMaterialsDTO>();
        for (IREGRegulationMaterialRelDTO dto: seledtedMaterials) {
            toBeAddedMaterials.add(dto.getRelatedMaterialDTO());
        }

        addKeys(toBeAddedMaterials);
    }

    private void addKeys(List<IREGRegulationMaterialsDTO> seledtedMaterials) {
        for (IREGRegulationMaterialsDTO dto: seledtedMaterials) {
            String key = dto.getCode().getKey().toString();
            if (!relatedKeys.containsKey(key)) {
                relatedKeys.put(key, key);
            }
        }
    }

    private void removeSelectedRelatedMaterial(List<IREGRegulationMaterialsDTO> seledtedMaterials) {

        List<IREGRegulationMaterialRelDTO> toBeRemoved = 
            new ArrayList<IREGRegulationMaterialRelDTO>();

        for (IREGRegulationMaterialsDTO mat: seledtedMaterials) {
            for (IREGRegulationMaterialRelDTO relatedMat: 
                 relatedMaterialsList) {
                if (mat.getCode().getKey().equals(relatedMat.getRelatedMaterialDTO().getCode().getKey())) {
                    toBeRemoved.add(relatedMat);
                }
            }
        }
          
        for (IREGRegulationMaterialRelDTO relMat: toBeRemoved) {
            relatedMaterialsList.remove(relMat);
        }
    }

    private void removeKeys(List<IREGRegulationMaterialsDTO> seledtedMaterials) {
        for (IREGRegulationMaterialsDTO dto: seledtedMaterials) {
            String key = dto.getCode().getKey().toString();
            if (relatedKeys.containsKey(key)) {
                relatedKeys.remove(key);
            }
        }
    }

    private void removeRelatedKeys(List<IREGRegulationMaterialRelDTO> seledtedRelatedMaterials) {
        List<IREGRegulationMaterialsDTO> seledtedMaterials = 
            new ArrayList<IREGRegulationMaterialsDTO>();
        for (IREGRegulationMaterialRelDTO dto: seledtedRelatedMaterials) {
            seledtedMaterials.add(dto.getRelatedMaterialDTO());
        }
        removeKeys(seledtedMaterials);
    }

    private IREGRegulationMaterialRelDTO getNewRelatedMaterial(IREGRegulationMaterialsDTO toBeLinkedMaterial) {

        IREGRegulationMaterialRelDTO relatedMaterial = 
            RegDTOFactory.createREGRegulationMaterialRelDTO();
        try {
            IREGRegulationMaterialsEntityKey materialKey=RegEntityKeyFactory.createREGRegulationMaterialsEntityKey(currentMaterial.getRegmaterialCode(),currentMaterial.getRegtypeCode(),currentMaterial.getRegyearCode(),currentMaterial.getRegulationNumber());
            IREGRegulationMaterialsEntityKey relmaterialKey = (IREGRegulationMaterialsEntityKey)toBeLinkedMaterial.getCode();
            relatedMaterial.setRegtypeCode(currentMaterial.getRegtypeCode());
            relatedMaterial.setRegyearCode(currentMaterial.getRegyearCode());
            relatedMaterial.setRegulationNumber(currentMaterial.getRegulationNumber());
            relatedMaterial.setRegmaterialCode(currentMaterial.getRegmaterialCode());
    
            relatedMaterial.setRelatedMaterialDTO(toBeLinkedMaterial);
    
            
            IREGRegulationMaterialRelEntityKey relMaterialKey=RegEntityKeyFactory.createREGRegulationMaterialRelEntityKey(materialKey.getRegtypeCode(),materialKey.getRegyearCode(),materialKey.getRegulationNumber(),materialKey.getRegmaterialCode(), 
                                                                                                      relmaterialKey.getRegtypeCode(), 
                                                                                                      relmaterialKey.getRegyearCode(), 
                                                                                                      relmaterialKey.getRegulationNumber(), 
                                                                                                      relmaterialKey.getRegmaterialCode());
                                                                                                      
            relatedMaterial.setCode(relMaterialKey);                                                                                          
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return relatedMaterial;
    }

    private void updateRelatedMaterialsFlags() {

        Map initialKeys = new HashMap();
        if(initialRelatedMaterialsList != null) {
            for (IREGRegulationMaterialRelDTO dto: initialRelatedMaterialsList) {
                String key = 
                    dto.getRelatedMaterialDTO().getCode().getKey().toString();
                if (!initialKeys.containsKey(key)) {
                    initialKeys.put(key, key);
                }
            }
        }

        //TODO flag Added items
        if(relatedMaterialsList != null) {
            for (IREGRegulationMaterialRelDTO dto: relatedMaterialsList) {
                String key = 
                    dto.getRelatedMaterialDTO().getCode().getKey().toString();
                if (!initialKeys.containsKey(key)) {
                    dto.setStatusFlag(ISystemConstant.ADDED_ITEM);
                }
            }
         }

        //TODO flag Removed items
        if(initialRelatedMaterialsList != null) {
            for (IREGRegulationMaterialRelDTO dto: initialRelatedMaterialsList) {
                String key = 
                    dto.getRelatedMaterialDTO().getCode().getKey().toString();
                if (!relatedKeys.containsKey(key)) {
                    if(dto.getStatusFlag()==null){
                        dto.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                        relatedMaterialsList.add(dto);
                    }
                }
            }
        }
    }
    // **************************** END PRIVATE METHODS ************************

    public void setSearchedForReg(boolean searchedForReg) {
        this.searchedForReg = searchedForReg;
    }

    public boolean isSearchedForReg() {
        return searchedForReg;
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

    public void setSelectedRelMaterialsList(List<IREGRegulationMaterialsDTO> selectedRelMaterialsList) {
        this.selectedRelMaterialsList = selectedRelMaterialsList;
    }

    public List<IREGRegulationMaterialsDTO> getSelectedRelMaterialsList() {
        return selectedRelMaterialsList;
    }

    public void setRelatedMaterialSize(int relatedMaterialSize) {
        this.relatedMaterialSize = relatedMaterialSize;
    }

    public int getRelatedMaterialSize() {
        if (getRelatedMaterialsList() != null && getRelatedMaterialsList().size() > 0)
            return getRelatedMaterialsList().size();
        return 0;
    }
    
    public void backSearch(){
        getLovBaseBean().setMyTableData(null);
        regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().repositionPage(0);
        searchedForReg = false;   
        setSearchActive(true);
        
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
            regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
            getLovBaseBean().setSelectedRadio("");
            getLovBaseBean().repositionPage(0);
            searchedForReg = false;   
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
