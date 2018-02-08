package com.beshara.csc.gn.grs.integration.presentation.backingbean.calculations;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICalculationsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionAppModulesDTO;
import com.beshara.csc.gn.grs.business.dto.sec.IGrsSecApplicationModulesDTO;
import com.beshara.csc.gn.grs.business.entity.sec.IGrsSecApplicationModulesEntityKey;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;


public class CalculationsMainDataMaintainBean extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   
    private List<IBasicDTO> appModulesList;
    private List<IBasicDTO> availableModulesList;
    private List<String> toBeAddedModulesList = new ArrayList<String>(0);
    private List<String> toBeRemovedModulesList = new ArrayList<String>(0);
    private List<IBasicDTO> allAvailableModulesList = new ArrayList<IBasicDTO>(0);
  //  private List<IBasicDTO> parameterTypesList;
 //   private String parametertype;
    private String searchNameCritria;
    

    public CalculationsMainDataMaintainBean() {
        setContent1Div("module_tabs_cont");
    }

  
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.showAddeditPage();
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        app.setShowbar(false);
        return app;
    }
 
 
    public boolean validate() {
        if (getPageDTO().getName() == null && getPageDTO().getName().equals("")) {
        } else {
            return false;
        }
        return true;
    }

    public void addAllElements() {
      //  List<IBasicDTO> conditionAppModulesDTOList = this.getConditionAppModulesDTOList();
        for (IBasicDTO basicDTO : availableModulesList) {
            allAvailableModulesList.add(this.mapToDetailDTO(basicDTO));
        }
        this.toBeAddedModulesList.clear();
        this.toBeRemovedModulesList.clear();
        reFillDetailList();
        this.setAvailableModulesList(null);
        setSearchNameCritria("");
    }

    public void addSelectedElements() {
       // List<IBasicDTO> conditionAppModulesDTOList = this.getConditionAppModulesDTOList();
        IBasicDTO basicDTO = null;
        for (String key : toBeAddedModulesList) {
            basicDTO = this.getElementByKey(availableModulesList, key);
            allAvailableModulesList.add(this.mapToDetailDTO(basicDTO));
        }

        this.toBeAddedModulesList.clear();
        this.toBeRemovedModulesList.clear();
        reFillDetailList();
        this.setAvailableModulesList(null);
        setSearchNameCritria("");
    }

    public void removeSelectedElements() {
       // List<IBasicDTO> conditionAppModulesDTOList = this.getConditionAppModulesDTOList();
        IBasicDTO basicDTO = null;
        for (String key : toBeRemovedModulesList) {
            basicDTO = this.getElementByKey(allAvailableModulesList, key);
            allAvailableModulesList.remove(basicDTO);
        }

        this.toBeAddedModulesList.clear();
        this.toBeRemovedModulesList.clear();
        this.setAvailableModulesList(null);
        setSearchNameCritria("");
    }

    public void removeAllElements() {
//        List<IBasicDTO> conditionAppModulesDTOList = this.getConditionAppModulesDTOList();
                for(IBasicDTO basicDTO : allAvailableModulesList){
                    availableModulesList.add(mapToCodeNameDTO(basicDTO));
                }
        allAvailableModulesList.clear();

        this.toBeAddedModulesList.clear();
        this.toBeRemovedModulesList.clear();
        this.setAvailableModulesList(null);
        setSearchNameCritria("");
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO grsSecApplicationModulesDTO) {
        Long conditionCode = 20984L;
//        ICalculationsDTO mainPageDTO = getMainPageDTO();
//        if (mainPageDTO != null && mainPageDTO.getCode() != null) {
//            conditionCode = ((IConditionsEntityKey)mainPageDTO.getCode()).getConditionCode();
//        }
        Long appModuleCode =
            ((IGrsSecApplicationModulesEntityKey)grsSecApplicationModulesDTO.getCode()).getAppModuleCode();
        IConditionAppModulesDTO dto = GrsDTOFactory.createConditionAppModulesDTO(conditionCode, appModuleCode);
        dto.setAppModulesDTO(grsSecApplicationModulesDTO);
        return dto;
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO conditionAppModulesDTO) {
        IGrsSecApplicationModulesDTO grsSecApplicationModulesDTO =
            (IGrsSecApplicationModulesDTO)((IConditionAppModulesDTO)conditionAppModulesDTO).getAppModulesDTO();
        return grsSecApplicationModulesDTO;
    }

    private IBasicDTO getElementByKey(List<IBasicDTO> dataList, String key) {
        for (IBasicDTO basicDTO : dataList) {
            if (basicDTO.getCode().getKey().equals(key)) {
                return basicDTO;
            }
        }
        return null;
    }

//    private List<IBasicDTO> getConditionAppModulesDTOList() {
//        List<IBasicDTO> conditionAppModulesDTOList = getMainPageDTO().getConditionAppModulesDTOList();
//        if (conditionAppModulesDTOList == null) {
//            conditionAppModulesDTOList = new ArrayList<IBasicDTO>(0);
//            ((ICalculationsDTO)CalculationsMaintainBean.getInstance().getPageDTO()).setConditionAppModulesDTOList(conditionAppModulesDTOList);
//        }
 //       return conditionAppModulesDTOList;
//    }

    private ICalculationsDTO getMainPageDTO() {
        return (ICalculationsDTO)CalculationsMaintainBean.getInstance().getPageDTO();
    }

    private void reFillDetailList() {
        try {
            List<IBasicDTO> dataList = new ArrayList<IBasicDTO>(0);
        //    List<IBasicDTO> conditionAppModulesDTOList = getConditionAppModulesDTOList();
//            if (conditionAppModulesDTOList != null && conditionAppModulesDTOList.size() != 0) {
//                for (IBasicDTO codeNameDTO : getAppModulesList()) {
//                    for (IBasicDTO detailDTO : conditionAppModulesDTOList) {
//                        IBasicDTO dto = mapToCodeNameDTO(detailDTO);
//                        if (codeNameDTO.getCode().getKey().equals(dto.getCode().getKey())) {
//                            dataList.add(detailDTO);
//                            break;
//                        }
//                    }
//                }
//                conditionAppModulesDTOList.clear();
//                conditionAppModulesDTOList.addAll(dataList);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void setParameterTypesList(List<IBasicDTO> parameterTypesList) {
//        this.parameterTypesList = parameterTypesList;
//    }
//
//    public List<IBasicDTO> getParameterTypesList() {
//        if (parameterTypesList == null) {
//            try {
//                parameterTypesList = GrsClientFactory.getParameterTypesClient().getCodeName();
//            } catch (Exception e) {
//                e.printStackTrace();
//                parameterTypesList = new ArrayList<IBasicDTO>();
//            }
//        }
//        return parameterTypesList;
//    }

//    public void setParametertype(String parametertype) {
//        this.parametertype = parametertype;
//    }
//
//    public String getParametertype() {
//        if (parametertype == null) {
//            IBasicDTO basicDTO = CalculationsMaintainBean.getInstance().getPageDTO();
//            if (basicDTO != null) {
//                IBasicDTO paramTypeDTO = ((ICalculationsDTO)basicDTO).getParameterTypesDTO();
//                if (paramTypeDTO != null) {
//                    parametertype = paramTypeDTO.getCode().getKey();
//                } else {
//                    parametertype = CalculationsListBean.getInstance().getSelectedParameterType();
//                }
//            }
//        }
//        if (parametertype == null) {
//            parametertype = getVirtualConstValue();
//        }
//        return parametertype;
//    }


    public void setAvailableModulesList(List<IBasicDTO> availableModulesList) {
        this.availableModulesList = availableModulesList;
    }

    public List<IBasicDTO> getAvailableModulesList() {
        if (availableModulesList == null) {
            try {
                availableModulesList = new ArrayList<IBasicDTO>(0);
                List<IBasicDTO> destinationDTOList = getAllAvailableModulesList();
                if (destinationDTOList != null && destinationDTOList.size() != 0) {
                    boolean exist = false;
                    for (IBasicDTO codeNameDTO : getAppModulesList()) {
                        exist = false;
                        for (IBasicDTO detailDTO : destinationDTOList) {
                            IBasicDTO dto = mapToCodeNameDTO(detailDTO);
                            if (codeNameDTO.getCode().getKey().equals(dto.getCode().getKey())) {
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            availableModulesList.add(codeNameDTO);
                        }
                    }
                } else {
                    availableModulesList.addAll(getAppModulesList());
                }
            } catch (Exception e) {
                e.printStackTrace();
                availableModulesList = new ArrayList<IBasicDTO>();
            }
        }
        return availableModulesList;
    }

    public void setToBeAddedModulesList(List<String> toBeAddedModulesList) {
        this.toBeAddedModulesList = toBeAddedModulesList;
    }

    public List<String> getToBeAddedModulesList() {
        return toBeAddedModulesList;
    }

    public void setToBeRemovedModulesList(List<String> toBeRemovedModulesList) {
        this.toBeRemovedModulesList = toBeRemovedModulesList;
    }

    public List<String> getToBeRemovedModulesList() {
        return toBeRemovedModulesList;
    }

    public void setAppModulesList(List<IBasicDTO> appModulesList) {
        this.appModulesList = appModulesList;
    }

    public List<IBasicDTO> getAppModulesList() {
        if (appModulesList == null) {
            try {
                appModulesList = GrsClientFactory.getConditionAppModulesClient().listAvailableAppModules(null);
            } catch (Exception e) {
                e.printStackTrace();
                appModulesList = new ArrayList<IBasicDTO>();
            }
        }
        return appModulesList;
    }

    public int getAvailableModulesListSize() {
        if (availableModulesList != null) {
            return availableModulesList.size();
        }
        return 0;
    }

    public int getConditionAppModulesDTOListSize() {
//        List<IBasicDTO> conditionAppModulesDTOList = getConditionAppModulesDTOList();
//        if (conditionAppModulesDTOList != null) {
//            return conditionAppModulesDTOList.size();
//        }
        return 0;
    }
    
    public int getAllAvailableModulesListSize() {
            if (allAvailableModulesList != null) {
                return allAvailableModulesList.size();
            }
        return 0;
    }

    public void searchAvailableModByName() {
        availableModulesList = null;
        getAvailableModulesList();
        List<IBasicDTO> searchList = new ArrayList();
        if (getSearchNameCritria() != null && !getSearchNameCritria().trim().isEmpty()) {
            for (int i = 0; i < availableModulesList.size(); i++) {
                if (availableModulesList.get(i).getName().contains(getSearchNameCritria())) {
                    searchList.add(availableModulesList.get(i));
                }
            }
            availableModulesList.clear();
            availableModulesList.addAll(searchList);
        }
    }

    public void setSearchNameCritria(String searchNameCritria) {
        this.searchNameCritria = searchNameCritria;
    }

    public String getSearchNameCritria() {
        return searchNameCritria;
    }

    public void setAllAvailableModulesList(List<IBasicDTO> allAvailableModulesList) {
        this.allAvailableModulesList = allAvailableModulesList;
    }

    public List<IBasicDTO> getAllAvailableModulesList() {
        return allAvailableModulesList;
    }
}
