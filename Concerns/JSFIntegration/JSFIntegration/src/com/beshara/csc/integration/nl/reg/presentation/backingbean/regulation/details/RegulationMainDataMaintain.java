package com.beshara.csc.nl.reg.presentation.backingbean.regulation.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.gn.sec.business.dto.ISecWorkCenterUsersDTO;
import com.beshara.csc.gn.sec.business.dto.ISecWorkCenterUsersSearchDTO;
import com.beshara.csc.gn.sec.business.dto.SecDTOFactory;
import com.beshara.csc.gn.sec.business.entity.ISecWorkCenterUsersEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.WorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGRegulationMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.ITypesDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.regulation.EditorBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulation.RegulationMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.fileupload.UploadedFile;


public class RegulationMainDataMaintain extends ManyToManyDetailsMaintain {
    private List<IBasicDTO> types;
    private List<IYearsDTO> issuanceYears;
    private List<IBasicDTO> status;
    private List<IBasicDTO> scopes;
    private List<IDecisionMakerTypesDTO> decisionMakers;
    private List<IBasicDTO> templates;
    private String yearsDTOKey;
    private String typesDTOKey;
    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private String itemSelectionRequiredValueString =itemSelectionRequiredValue.toString();
    private String selectedParentRegKey;
    private String fakeImageString;
    private UploadedFile regImageUploadedFile;
    private IRegulationSearchDTO searchDto = RegDTOFactory.createRegulationSearchDTO();
    private boolean invalidImageType;
    private boolean uploadingError;
    private boolean canceledRegulation;
    private boolean disableStatusMenu;
    private boolean invalidRegulationKey;
    private boolean disableSaveButton;
    private String returnFromEditor;
    private boolean showMatrialMsg;
    private ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO=SecDTOFactory.createSecWorkCenterUsersSearchDTO();
    private List<WorkCentersDTO> workCentersDTOList;
    private IMinistriesDTO pageDtoBuffer;
    private boolean divflag;
    
    public RegulationMainDataMaintain() {
        setClient(RegClientFactory.getRegulationsClient());
        //BeansUtil.setValue("regulationMaintainBean.content1Div","divContent1Fixed");
        setContent1Div("divContent1Fixed");
        //BeansUtil.setValue("regulationMaintainBean.lookupAddDiv","regulationDivSearch");
        setLookupAddDiv("regulationDivSearch");
        setLookupEditDiv("ConditionLovDivClass");
    }
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowbar(false);
        app.setShowdatatableContent(false);
        app.setShowpaging(false);
        app.setShowLookupEdit(true);
        return app;
    }

    public void getListAvailable() throws DataBaseException {
//        setSearchMode(false);
//        if (getMasterEntityKey() != null && isSearchMode()==true) {
//            try {
//                setAvailableDetails(RegClientFactory.getRefrencesClient().listAvailableEntitiesCenter(getMasterEntityKey()));
//            } catch (SharedApplicationException e) {
//                setAvailableDetails(new ArrayList<IBasicDTO>());
//            }
//        } else {
//            try {
//                setAvailableDetails(RegClientFactory.getRefrencesClient().listAvailableEntitiesCenter(RegEntityKeyFactory.createRegulationsEntityKey()));
//                super.getListAvailable();
//            } catch (SharedApplicationException e) {
//                setAvailableDetails(new ArrayList<IBasicDTO>());
//            }
//        }
    }

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
        try {
            searchDto.setMasterCode(getMasterEntityKey() == null ? 
                                    RegEntityKeyFactory.createRegulationsEntityKey() : 
                                    getMasterEntityKey());
            setAvailableDetails(RegClientFactory.getRefrencesClient().filterAvailableEntitiesCenter(searchDto));
            setSearchMode(true);
        } catch (SharedApplicationException e) {
            setSearchMode(true);
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.searchAvailable();
        setDisableSaveButton(false);
    }

    public void setSelectedRegParent() throws DataBaseException, 
                                              SharedApplicationException {
        setSearchMode(false);
        for (IBasicDTO dto: getAvailableDetails()) {
            Boolean checked = ((IClientDTO)dto).getChecked();
            if (checked != null && checked) {
                if (dto.getCode() != null) {
                    dto = (getClient()).getById(dto.getCode());
                }
                BeansUtil.setValue("regulationMaintainBean.pageDTO.parentRegulationDTO", 
                                   dto);
                break;
            }
        }
    }
    
    public void resetRegParent(){
        BeansUtil.setValue("regulationMaintainBean.pageDTO.parentRegulationDTO",null);
    }

    public void cancelSearchAvailable() {
        getAvailableDataTable().setFirst(0);
        resetPageIndex();
        this.setSearchString(null);
        this.setSearchMode(false);
          //  getListAvailable();
            //setAvailableDetails(RegClientFactory.getRefrencesClient().listAvailableEntitiesCenter(RegEntityKeyFactory.createRegulationsEntityKey()));
            setAvailableDetails(new ArrayList());
            
     
      //  this.removeCurrentFromAvailable();
    }

    public void openSearchAvailableModefiedReg(ActionEvent ae){
        disableSaveButton=false;
        this.cancelSearchAvailable();
        setSearchDto(RegDTOFactory.createRegulationSearchDTO());
        //        BeansUtil.setValue("regulationMaintainBean.pageDTO.parentRegulationDTO", 
//                           null);
        
    }

    public boolean validate() {
        System.out.println("from main data Validate");
        
        boolean validKey = isValidRegulationKey();
        boolean imageUploadedSuccessfully = isImageUploadedSuccessfully();
        
        boolean valid = validKey && imageUploadedSuccessfully;        
        return valid;
    }

    //====================================================================Accessors=

    public void setTypes(List<IBasicDTO> types) {
        this.types = types;
    }

    public List<IBasicDTO> getTypes() throws DataBaseException {
        if (types == null || types.size() == 0) {
            types = 
                    RegClientFactory.getTypesClient().getCodeNameCenter(ISystemConstant.REGULATION_VALIDITY_REGULATION);
            if (types == null)
                types = new ArrayList<IBasicDTO>();
        }
        return types;
    }

    public void setIssuanceYears(List<IYearsDTO> issuanceYears) {
        this.issuanceYears = issuanceYears;
    }

    public List<IYearsDTO> getIssuanceYears() throws DataBaseException {
        if (issuanceYears == null || issuanceYears.size() == 0) {
            issuanceYears = InfClientFactory.getYearsClient().getCodeNameInCenter();
            if (issuanceYears == null)
                issuanceYears = new ArrayList<IYearsDTO>();
        }
        return issuanceYears;
    }

    public void setStatus(List<IBasicDTO> status) {
        this.status = status;
    }

    public List<IBasicDTO> getStatus() throws DataBaseException, 
                                             SharedApplicationException {
        if (status == null || status.size() == 0) {
            status = 
                    RegClientFactory.getRegulationStatusClient().getCodeNameCenter();
            if (status != null) {
                boolean addMode = 
                    ((Boolean)BeansUtil.getValue("regulationMaintainBean.maintainMode==0"));
                Object currentRegStatusObj = 
                    BeansUtil.getValue("regulationMaintainBean.pageDTO.statusDTOKey");
                if (addMode || 
                    (currentRegStatusObj != null && new Long(Long.parseLong(currentRegStatusObj.toString())).equals(ISystemConstant.REGULATION_STATUS_SUGGESTED))) {
                    for (IBasicDTO regStatus: status) {
                        if (((IRegulationStatusEntityKey)regStatus.getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_CANCELED)) {
                            status.remove(regStatus);
                            break;
                        }
                    }
                }
            } else { //status == null
                status = new ArrayList<IBasicDTO>();
            }
        }
        return status;
    }

    public void setScopes(List<IBasicDTO> scopes) {
        this.scopes = scopes;
    }

    public List<IBasicDTO> getScopes() throws DataBaseException {
        if (scopes == null || scopes.size() == 0) {
            scopes = 
                    RegClientFactory.getRegulationScopesClient().getCodeNameCenter();
            if (scopes == null)
                scopes = new ArrayList<IBasicDTO>();
        }
        return scopes;
    }

    public void setDecisionMakers(List<IDecisionMakerTypesDTO> decisionMakers) {
        this.decisionMakers = decisionMakers;
    }

    public List<IDecisionMakerTypesDTO> getDecisionMakers() throws DataBaseException {
        
        if (decisionMakers == null || decisionMakers.size() == 0) {
            decisionMakers = (List)InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            if (decisionMakers == null)
                decisionMakers = new ArrayList<IDecisionMakerTypesDTO>();
        }
        return decisionMakers;
    }

    public void setTemplates(List<IBasicDTO> templates) {
        this.templates = templates;
    }

    public List<IBasicDTO> getTemplates() throws DataBaseException {
        if (templates == null || templates.size() == 0) {
            templates = RegClientFactory.getTemplatesClient().getCodeNameCenter();
            if (templates == null)
                templates = new ArrayList<IBasicDTO>();
        }
        return templates;
    }

    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

    public void setRegImageUploadedFile(UploadedFile regImageUploadedFile) {
        this.regImageUploadedFile = regImageUploadedFile;
    }

    public UploadedFile getRegImageUploadedFile() {
        return regImageUploadedFile;
    }

    public void setSearchDto(IRegulationSearchDTO searchDto) {
        this.searchDto = searchDto;
    }

    public IRegulationSearchDTO getSearchDto() {
        return searchDto;
    }

    public void setItemSelectionRequiredValueString(String itemSelectionRequiredValueString) {
        this.itemSelectionRequiredValueString = 
                itemSelectionRequiredValueString;
    }

    public String getItemSelectionRequiredValueString() {
        return itemSelectionRequiredValueString;
    }

    public void setSelectedParentRegKey(String selectedParentRegKey) {
        this.selectedParentRegKey = selectedParentRegKey;
    }

    public String getSelectedParentRegKey() {
        return selectedParentRegKey;
    }

    public void setInvalidImageType(boolean invalidImageType) {
        this.invalidImageType = invalidImageType;
    }

    public boolean isInvalidImageType() {
        return invalidImageType;
    }

    public void setUploadingError(boolean uploadingError) {
        this.uploadingError = uploadingError;
    }

    public boolean isUploadingError() {
        return uploadingError;
    }

    public void setCanceledRegulation(boolean canceledRegulation) {
        this.canceledRegulation = canceledRegulation;
    }

    public boolean isCanceledRegulation() {
        if ((Boolean)BeansUtil.getValue("regulationMaintainBean.maintainMode==1")) { //Edit
            IRegulationsDTO pageDTO = 
                (IRegulationsDTO)BeansUtil.getValue("regulationMaintainBean.pageDTO");
            IRegulationStatusDTO status = pageDTO.getStatusDto();
            if (status != null) {
                canceledRegulation = 
                        ((IRegulationStatusEntityKey)status.getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_CANCELED);
            }
        }
        return canceledRegulation;
    }

    public void setFakeImageString(String fakeImageString) {
        this.fakeImageString = fakeImageString;
        if (fakeImageString != null && fakeImageString.length() > 1) {
            Map<String, Object> detailsSavedStates = 
                (Map<String, Object>)BeansUtil.getValue("regulationMaintainBean.detailsSavedStates");
            if(detailsSavedStates != null)
            detailsSavedStates.put("fakeImageString", fakeImageString);
        }
    }

    public String getFakeImageString() {
        //        if(fakeImageString == null){
        if (!(isInvalidImageType() || isUploadingError())) {
            Map<String, Object> detailsSavedStates = (Map<String, Object>)BeansUtil.getValue("regulationMaintainBean.detailsSavedStates");
            if(detailsSavedStates != null)
            fakeImageString = (String)detailsSavedStates.get("fakeImageString");
        } 
        if(fakeImageString == null || (isInvalidImageType() || isUploadingError())){
            //            if(fakeImageString == null){
            fakeImageString = BeansUtil.getResource("com.beshara.csc.nl.reg.presentation.resources.reg", "image_unAvailable");
            String imageName = (String)BeansUtil.getValue("regulationMaintainBean.pageDTO.regulationImage");
            if (imageName != null && imageName.length() > 1) {
                fakeImageString = BeansUtil.getResource("com.beshara.csc.nl.reg.presentation.resources.reg", "image_available");
            }
        }
        //        }
        return fakeImageString;
    }

    public void setDisableStatusMenu(boolean disableStatusMenu) {
        this.disableStatusMenu = disableStatusMenu;
    }

    public boolean isDisableStatusMenu() {
        disableStatusMenu = false;
        boolean editMode = 
            ((Boolean)BeansUtil.getValue("regulationMaintainBean.maintainMode==1"));
        Object currentRegStatusObj = 
            BeansUtil.getValue("regulationMaintainBean.pageDTO.statusDTOKey");
        if (currentRegStatusObj != null) {
            Long currentRegStatus = 
                Long.parseLong(currentRegStatusObj.toString());
            if (editMode && 
                !currentRegStatus.equals(ISystemConstant.REGULATION_STATUS_SUGGESTED)) {
                disableStatusMenu = true;
            }
        }
        return disableStatusMenu;
    }

    public void setTypesDTOKey(String typesDTOKey) throws DataBaseException {
//        if ((Boolean)BeansUtil.getValue("regulationMaintainBean.maintainMode==1")){
//            return;
//        }
//        TypesDTO typesDto = null;
//        if (!(typesDTOKey == null || typesDTOKey.length() == 0 || typesDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
//                for (BasicDTO type: getTypes()) {
//                    if (type.getCode().getKey().equals(typesDTOKey)) {
//                        typesDto = (TypesDTO)type;
//                        break;
//                    }
//                }
//        } else {
//            typesDto = null;
//        }
//        BeansUtil.setValue("regulationMaintainBean.pageDTO.typesDto",typesDto);
        this.typesDTOKey = typesDTOKey;
    }

    public String getTypesDTOKey() {
        return typesDTOKey;
    }

    public void setYearsDTOKey(String yearsDTOKey) throws DataBaseException {
//        if ((Boolean)BeansUtil.getValue("regulationMaintainBean.maintainMode==1")){
//            return;
//        }
//        YearsDTO yearsDto = null;
//        if (!(yearsDTOKey == null || yearsDTOKey.length() == 0 || yearsDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
//            for (YearsDTO year: getIssuanceYears()) {
//                if (year.getCode().getKey().equals(yearsDTOKey)) {
//                    yearsDto = year;
//                    break;
//                }
//            }
//        } else {
//            yearsDto = null;
//        }
//        BeansUtil.setValue("regulationMaintainBean.pageDTO.yearsDto",yearsDto);
        this.yearsDTOKey = yearsDTOKey;
    }

    public String getYearsDTOKey() {
        return yearsDTOKey;
    }

    public void setInvalidRegulationKey(boolean invalidRegulationKey) {
        this.invalidRegulationKey = invalidRegulationKey;
    }

    public boolean isInvalidRegulationKey() {
        return invalidRegulationKey;
    }

    private boolean isValidRegulationKey() {
        boolean validKey = true;
        RegulationMaintainBean regulationMaintainBean = RegulationMaintainBean.getInstance();
        boolean addMode = regulationMaintainBean.getMaintainMode() == 0;
        if(addMode || regulationMaintainBean.isCopyFlage() ){
            IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
            IRegulationSearchDTO regulationSearchDTO = RegDTOFactory.createRegulationSearchDTO();
            regulationSearchDTO.setNumber(regulationsDTO.getRegulationNumber());
            regulationSearchDTO.setRegulationType(new Long(regulationsDTO.getTypesDTOKey()));
            regulationSearchDTO.setRegulationYear(new Long(regulationsDTO.getYearsDTOKey()));
            
            try {
                RegClientFactory.getRegulationsClient().searchCenter(regulationSearchDTO);
                validKey = false;
                invalidRegulationKey = true;
                
            } catch (Exception e) {
                System.err.println("==================== ID IS READY TO USE ====================");
            }
            if(validKey){
                try{
                    String typeKey = regulationsDTO.getTypesDTOKey();
                    for (IBasicDTO type: getTypes()) {
                        if (type.getCode().getKey().equals(typeKey)) {
                            ITypesDTO typesDto = (ITypesDTO)type;
                            regulationsDTO.setTypesDto(typesDto);
                            break;
                        }
                    }                 
                    String yearKey = regulationsDTO.getYearsDTOKey();
                    for (IBasicDTO year: getIssuanceYears()) {
                        if (year.getCode().getKey().equals(yearKey)) {
                            IYearsDTO yearsDto = (IYearsDTO)year;
                            regulationsDTO.setYearsDto(yearsDto);
                            break;
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return validKey;
    }
    
    private boolean isImageUploadedSuccessfully(){
        if (regImageUploadedFile != null) {

            String tempFileExtension = null;
            String tempFilePath = null;
            InputStream inStream = null;
            try {
                inStream = regImageUploadedFile.getInputStream();
            } catch (IOException e) {
                uploadingError = true;
                return false;
            }
            tempFileExtension = 
                    BeansUtil.mapImageTypeToExtension(regImageUploadedFile.getContentType());
            if (tempFileExtension == null) {
                invalidImageType = true;
                return false;
            }
            try {
                tempFilePath = 
                        BeansUtil.saveToTempFile(regImageUploadedFile.getInputStream(), 
                                                 tempFileExtension);
            } catch (IOException e) {
                uploadingError = true;
                return false;
            }
            Map<String, Object> detailsSavedStates = 
                (Map<String, Object>)BeansUtil.getValue("regulationMaintainBean.detailsSavedStates");
            detailsSavedStates.put(BeansUtil.UPLOADED_IMAGE_BINDING_KEY, 
                                   tempFilePath);
        }
        
        return true;
    }
    
    public String returnEditorAction()
    {
    return "regulationmaindata";
    }
    
    public String openEditor()
    {
        RegulationMaintainBean regulationMaintainBean = RegulationMaintainBean.getInstance();
        IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
        EditorBean editorBean = (EditorBean)evaluateValueBinding("editorBean");
        editorBean.setData(regulationsDTO.getRegulationText());
       return "goToEditorList";
    }
    
    
    public void addMaterialInEditorView()
    {
        RegulationMaintainBean regulationMaintainBean = RegulationMaintainBean.getInstance();
        RegulationMaterialsMaintain regulationMaterialsMaintainBean =(RegulationMaterialsMaintain)evaluateValueBinding("regulationMaterialsMaintainBean");
        IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
        String material="";
        for(int i=0 ; i<regulationMaterialsMaintainBean.getCurrentDetails().size();i++)
        {
            if(regulationMaterialsMaintainBean.getCurrentDetails().get(i).getStatusFlag()!=null)
            {            
                if(regulationMaterialsMaintainBean.getCurrentDetails().get(i).getStatusFlag()!=1L)
                {
                material=material+ "<p><span style=font-size:small><strong>"+((IREGRegulationMaterialsDTO)regulationMaterialsMaintainBean.getCurrentDetails().get(i)).getRegmaterialText()+"</span></strong></p>";
                }
                else
                {
                material="";
                }
            }
            else
            {
                material=material+ "<p><span style=font-size:small><strong>"+((IREGRegulationMaterialsDTO)regulationMaterialsMaintainBean.getCurrentDetails().get(i)).getRegmaterialText()+"</span></strong></p>";
            }
        }
      
        if(material.equals(""))
        {
        getSharedUtil().handleException(new Exception(),"com.beshara.csc.nl.reg.presentation.resources.reg","noMaterialErrorMsg");
        //showMatrialMsg=true;
        }
        else
        {
        regulationsDTO.setRegulationText(material);
        //showMatrialMsg=false;
        }
    }
    
    public String parseEditorToText()
    {
    RegulationMaintainBean regulationMaintainBean = RegulationMaintainBean.getInstance();
    EditorBean editorBean = (EditorBean)evaluateValueBinding("editorBean");
    IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
   // System.out.println(editorBean.getData());
    setReturnFromEditor(editorBean.getData());
    String hTMLString = editorBean.getData();
//    hTMLString=hTMLString.replaceAll("&nsb;", "");
//    hTMLString=hTMLString.replaceAll("&nbsp;", "");
    regulationsDTO.setRegulationText(hTMLString);
    return hTMLString;
    }
    
    public String parseEditorAction()
    {
    parseEditorToText();
    return "regulationmaindata";
    }

public void checkData(ActionEvent event )
{
    disableSaveButton=true;
}


    public void setDisableSaveButton(boolean disableSaveButton) {
        this.disableSaveButton = disableSaveButton;
    }

    public boolean isDisableSaveButton() {
        return disableSaveButton;
    }

    public void setReturnFromEditor(String returnFromEditor) {
        this.returnFromEditor = returnFromEditor;
    }

    public String getReturnFromEditor() {
        return returnFromEditor;
    }

    public void setShowMatrialMsg(boolean showMatrialMsg) {
        this.showMatrialMsg = showMatrialMsg;
    }

    public boolean isShowMatrialMsg() {
        return showMatrialMsg;
    }
    
    
    
    public void openSearchUser()
    {
       cancelSearchDiv();
       setDivflag(true);
       setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; ");
    }

    public String cancelSearchDiv() {
        setSearchQuery("");
        setSearchMode(false);
        setSelectedRadio("");
        getSelectedDTOS().clear();
        setMyTableData(new ArrayList());
        secWorkCenterUsersSearchDTO =  SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        return "";
    }

    public void closeLovDiv() throws DataBaseException {
        RegulationMaintainBean regulationMaintainBean = RegulationMaintainBean.getInstance();
       if (getSelectedDTOS() != null && getSelectedDTOS().get(0) != null) {
                ((IRegulationsDTO)regulationMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setUserName(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getUserName());
                ((IRegulationsDTO)regulationMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setCivilname(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getCivilname());
                ((IRegulationsDTO)regulationMaintainBean.getPageDTO()).setUserCode(((ISecWorkCenterUsersEntityKey)(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getCode())).getUserCode());
        }
        secWorkCenterUsersSearchDTO = SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        divflag = false;
    }
    
    public void openSearchDiv(ActionEvent event)
    {
       setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; ");
    }
    
    public void getAll()
    {
    
    }
    public void hideLovDiv() {
       cancelSearchDiv();
       setDivflag(false);
       setSelectedRadio("");
       if (getSelectedDTOS() != null) {
           getSelectedDTOS().clear();
       }
    }
    //     public void filterDataBygeha() {
    //
    //         if(getSecWorkCenterUsersSearchDTO().getMinCode()==null)
    //         {
    //         getSecWorkCenterUsersSearchDTO().setWrkCode(null);
    //         }
    //         setSearchQuery("");
    //         setSearchMode(false);
    //         getSelectedDTOS().clear();
    //         setMyTableData(new ArrayList());
    //         setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; ");
    //     }
    
    public String searchLovDiv() {
       try {
           List<ISecWorkCenterUsersDTO> Result=SecClientFactory.getSecWorkCenterUsersClientForCenter().SearchByCaretira(getSecWorkCenterUsersSearchDTO());
           setMyTableData(Result);
           setSelectedRadio("");
       } catch (DataBaseException e) {
           e.printStackTrace(); // TODO
            setMyTableData(new ArrayList());
           setSelectedRadio("");
       }
       setDivflag(true);
       setSearchMode(true);
       setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.lookupEditDiv);settingFoucsAtLovDiv();");
      
       return "";
    }
    
    public void setSecWorkCenterUsersSearchDTO(ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO) {
       this.secWorkCenterUsersSearchDTO = secWorkCenterUsersSearchDTO;
    }

    public ISecWorkCenterUsersSearchDTO getSecWorkCenterUsersSearchDTO() {
       return secWorkCenterUsersSearchDTO;
    }

    //    public void setPageDtoBufferlist(List<IBasicDTO> pageDtoBufferlist) {
    //        this.pageDtoBufferlist = pageDtoBufferlist;
    //    }
    //
    //    public List<IBasicDTO> getPageDtoBufferlist() {
    //        try {
    //            pageDtoBufferlist =SecClientFactory.getSecWorkCenterUsersClientForCenter().GetDistnictMinCodeByUserCode(CSCSecurityInfoHelper.getUserCode());
    //        } catch (DataBaseException e) {
    //            pageDtoBufferlist=new ArrayList();
    //            e.printStackTrace();
    //        }
    //        return pageDtoBufferlist;
    //    }

    public void setWorkCentersDTOList(List<WorkCentersDTO> workCentersDTOList) {
       this.workCentersDTOList = workCentersDTOList;
    }

    public List<WorkCentersDTO> getWorkCentersDTOList() {
       try {
           workCentersDTOList =SecClientFactory.getSecWorkCenterUsersClientForCenter().getWorkCentersByMin(CSCSecurityInfoHelper.getLoggedInMinistryCode());
       } catch (DataBaseException e) {
           workCentersDTOList=new ArrayList();
           e.printStackTrace();
       }
       return workCentersDTOList;
    }

    public void setPageDtoBuffer(IMinistriesDTO pageDtoBuffer) {
       this.pageDtoBuffer = pageDtoBuffer;
    }

    public IMinistriesDTO getPageDtoBuffer() {
       try {
           pageDtoBuffer =(IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(CSCSecurityInfoHelper.getLoggedInMinistryCode()));
           getSecWorkCenterUsersSearchDTO().setMinCode(((IMinistriesEntityKey)pageDtoBuffer.getCode()).getMinCode());
       } catch (SharedApplicationException e) {
           e.printStackTrace();
       } catch (DataBaseException e) {
         e.printStackTrace();
       }
       return pageDtoBuffer;
    }

    public void setDivflag(boolean divflag) {
       this.divflag = divflag;
    }

    public boolean isDivflag() {
       return divflag;
    }
    
    
}
