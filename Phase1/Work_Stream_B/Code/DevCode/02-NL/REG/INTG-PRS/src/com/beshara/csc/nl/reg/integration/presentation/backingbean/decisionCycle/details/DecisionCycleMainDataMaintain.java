package com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionCycle.details;


import com.beshara.base.dto.IBasicDTO;
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
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.ITypesDTO;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decision.EditorBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.fileupload.UploadedFile;


public class DecisionCycleMainDataMaintain extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List<IBasicDTO> types;
    private List<IYearsDTO> issuanceYears;
    private List<IRegulationStatusDTO> regStatusDTOList;
    private List<IBasicDTO> subjects;
    private List<IDecisionMakerTypesDTO> decisionMakers;
    private List<IBasicDTO> templates;

    private String yearsDTOKey;
    private String typesDTOKey;
    private String statusDTOKey;
    private UploadedFile decImageUploadedFile;
    
    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private String itemSelectionRequiredValueString = 
        itemSelectionRequiredValue.toString();
    private String fakeImageString;

    private boolean invalidImageType;
    private boolean uploadingError;

    private boolean dateDisabled;

    private boolean showLovDivFlag = false;

    private boolean validEntityKey = true;
    private boolean showMatrialMsg;
    private String returnFromEditor;
    
    private ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO=  SecDTOFactory.createSecWorkCenterUsersSearchDTO();
//    private List<IBasicDTO> pageDtoBufferlist;
    private List<WorkCentersDTO> workCentersDTOList;
    private IMinistriesDTO pageDtoBuffer;
    private boolean divflag;
    private String editorReturn=null;

    
    public DecisionCycleMainDataMaintain() {
        setClient(RegClientFactory.getDecisionsClient());
        //        BeansUtil.setValue("decisionMaintainBean.content1Div","divContent1Fixed");
        setContent1Div("divContent1Fixed");
        // added by nora for adding lov div 
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList());
        setLookupEditDiv("ConditionLovDivClass");
    }
    

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();

        app.showAddeditPage();
        app.setShowLookupAdd(true);
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        // added by nora for Lov Div
        if (showLovDivFlag)
            app.setShowLovDiv(true);
            
        app.setShowLookupEdit(true);
        return app;
    }
    /**
     * Purpose:called when user open decision types love div
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void openDecisionTypes() {

        try {
            getLovBaseBean().setMyTableData(getTypes());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        getLovBaseBean().getOkCommandButton().setReRender("maintain_regTypeAdd");
        getLovBaseBean().setReturnMethodName("decisionCycleMainDataMaintainBean.addDecisionType");
        getLovBaseBean().setSearchMethod("decisionCycleMainDataMaintainBean.searchDecisionType");
        getLovBaseBean().setSelectedDTOS(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("decisionCycleMainDataMaintainBean.cancelSearchLovDiv");
    }

    /**
     * Purpose:called when  user search by name or  code  @ decision type
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void searchDecisionType(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && 
            searchType != null && !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);
            try {

                if (searchType.toString().equals("0"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().searchByCode(Long.valueOf(searchQuery.toString())));
                else if (searchType.toString().equals("1"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().searchByName(searchQuery.toString()));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (Exception e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        }
    }

    /**
     * Purpose:called when  user search by name or  code  @ decision subject 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void searchDecisionSubject(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && 
            searchType != null && !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);
            try {

                if (searchType.toString().equals("0"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getSubjectsClient().searchByCode(Long.valueOf(searchQuery.toString())));
                else if (searchType.toString().equals("1"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getSubjectsClient().searchByName(searchQuery.toString()));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (Exception e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        }
    }


    /**
     * Purpose:called when user open decision subjects love div
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void openDecisionSubjects() {
        try {
            getLovBaseBean().setMyTableData(getSubjects());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        getLovBaseBean().getOkCommandButton().setReRender("maintain_decisionSubject");
        getLovBaseBean().setReturnMethodName("decisionCycleMainDataMaintainBean.addDecisionSubject");
        getLovBaseBean().setSearchMethod("decisionCycleMainDataMaintainBean.searchDecisionSubject");
        getLovBaseBean().setSelectedDTOS(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
    }

    /**
     * Purpose:called when user select one row from type lov div and click ok
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void addDecisionType() {
        if (getLovBaseBean().getSelectedDTOS() != null && 
            getLovBaseBean().getSelectedDTOS().get(0) != null && 
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                setTypesDTOKey(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Purpose:called when user select one row from subject lov div and click ok
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void addDecisionSubject() {

        if (getLovBaseBean().getSelectedDTOS() != null && 
            getLovBaseBean().getSelectedDTOS().get(0) != null && 
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                DecisionCycleMaintainBean decisionMaintainBean = 
                    (DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
                if (decisionMaintainBean.getPageDTO() != null)
                    ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).setSubjectsDTOKey(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

   

    public boolean validate() {

        System.out.println("from main data Validate");
        boolean valid = true;
        if (decImageUploadedFile != null) {

            String tempFileExtension = null;
            String tempFilePath = null;
            InputStream inStream = null;
            try {
                inStream = decImageUploadedFile.getInputStream();
            } catch (IOException e) {
                uploadingError = true;
                return false;
            }
            tempFileExtension = 
                    BeansUtil.mapImageTypeToExtension(decImageUploadedFile.getContentType());
            if (tempFileExtension == null) {
                invalidImageType = true;
                return false;
            }
            try {
                tempFilePath = 
                        BeansUtil.saveToTempFile(decImageUploadedFile.getInputStream(), 
                                                 tempFileExtension);
            } catch (IOException e) {
                uploadingError = true;
                return false;
            }
            Map<String, Object> detailsSavedStates = 
                (Map<String, Object>)BeansUtil.getValue("decisionCycleMaintainBean.detailsSavedStates");
            detailsSavedStates.put(BeansUtil.UPLOADED_IMAGE_BINDING_KEY, 
                                   tempFilePath);
        }
 return valid;
    }

    /**
     * Purpose: cancel search
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  31-DEC-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String cancelSearchLovDiv() {
        try {
            getLovBaseBean().setMyTableData(getTypes());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        return "";
    }


    public void setTypes(List<IBasicDTO> types) {
        this.types = types;
    }

    public List<IBasicDTO> getTypes() throws DataBaseException {
        if (types == null || types.size() == 0) {
            types = 
                    RegClientFactory.getTypesClient().getCodeName(ISystemConstant.REGULATION_VALIDITY_DECISION);
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
            try {
                issuanceYears = InfClientFactory.getYearsClient().getCodeName();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (issuanceYears == null) {
                issuanceYears = new ArrayList<IYearsDTO>();
            }
        }
        return issuanceYears;
    }

    public void setSubjects(List<IBasicDTO> subjects) {
        this.subjects = subjects;
    }

    public List<IBasicDTO> getSubjects() throws DataBaseException {
        if (subjects == null || subjects.size() == 0) {
            subjects = RegClientFactory.getSubjectsClient().getCodeName();
            if (subjects == null)
                subjects = new ArrayList<IBasicDTO>();
        }
        return subjects;
    }

    public void setDecisionMakers(List<IDecisionMakerTypesDTO> decisionMakers) {
        this.decisionMakers = decisionMakers;
    }

    public List<IDecisionMakerTypesDTO> getDecisionMakers() throws DataBaseException {
        if (decisionMakers == null || decisionMakers.size() == 0) {
            try {
                decisionMakers =
                    (List)InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
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
            templates = RegClientFactory.getTemplatesClient().getCodeName();
            if (templates == null)
                templates = new ArrayList<IBasicDTO>();
        }
        return templates;
    }

    public void setDecImageUploadedFile(UploadedFile decImageUploadedFile) {
        this.decImageUploadedFile = decImageUploadedFile;
    }

    public UploadedFile getDecImageUploadedFile() {
        return decImageUploadedFile;
    }

    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

    public void setItemSelectionRequiredValueString(String itemSelectionRequiredValueString) {
        this.itemSelectionRequiredValueString = 
                itemSelectionRequiredValueString;
    }

    public String getItemSelectionRequiredValueString() {
        return itemSelectionRequiredValueString;
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

    public void setFakeImageString(String fakeImageString) {
        this.fakeImageString = fakeImageString;
        if (fakeImageString != null && fakeImageString.length() > 1) {
            Map<String, Object> detailsSavedStates = 
                (Map<String, Object>)BeansUtil.getValue("decisionCycleMaintainBean.detailsSavedStates");
            detailsSavedStates.put("fakeImageString", fakeImageString);
        }
    }

    public String getFakeImageString() {
        //        if(fakeImageString == null){
        if (!(isInvalidImageType() || isUploadingError())) {
            Map<String, Object> detailsSavedStates = 
                (Map<String, Object>)BeansUtil.getValue("decisionCycleMaintainBean.detailsSavedStates");
            fakeImageString = 
                    (String)detailsSavedStates.get("fakeImageString");
        }
        if (fakeImageString == null || 
            (isInvalidImageType() || isUploadingError())) {
            //            if(fakeImageString == null){
            fakeImageString = 
                    BeansUtil.getResource("com.beshara.csc.nl.reg.integration.presentation.resources.integration", 
                                          "image_unAvailable");
            String imageName = 
                (String)BeansUtil.getValue("decisionCycleMaintainBean.pageDTO.decisionImage");
            if (imageName != null && imageName.length() > 1) {
                fakeImageString = 
                        BeansUtil.getResource("com.beshara.csc.nl.reg.integration.presentation.resources.integration", 
                                              "image_available");
            }
        }
        //        }
        return fakeImageString;
    }

    public void setTypesDTOKey(String typesDTOKey) throws DataBaseException {
        try {
            if ((Boolean)BeansUtil.getValue("decisionCycleMaintainBean.maintainMode==1")) {
                return;
            }
            ITypesDTO typesDto = null;
            if (!(typesDTOKey == null || typesDTOKey.length() == 0 || 
                  typesDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
                for (IBasicDTO type: getTypes()) {
                    if (type.getCode().getKey().equals(typesDTOKey)) {
                        typesDto = (ITypesDTO)type;
                        BeansUtil.setValue("decisionCycleMaintainBean.pageDTO.typesDTO", 
                                           typesDto);
                        break;
                    }
                }
            }
            this.typesDTOKey = typesDTOKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTypesDTOKey() {
        return typesDTOKey;
    }

    public void setYearsDTOKey(String yearsDTOKey) throws DataBaseException {
        if ((Boolean)BeansUtil.getValue("decisionCycleMaintainBean.maintainMode==1")) {
            return;
        }
        IYearsDTO yearsDto = null;
        if (!(yearsDTOKey == null || yearsDTOKey.length() == 0 || 
              yearsDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
            for (IYearsDTO year: getIssuanceYears()) {
                if (year.getCode().getKey().equals(yearsDTOKey)) {
                    yearsDto = year;
                    BeansUtil.setValue("decisionCycleMaintainBean.pageDTO.yearsDTO", 
                                       yearsDto);
                    break;
                }
            }
        }
        this.yearsDTOKey = yearsDTOKey;
    }

    public String getYearsDTOKey() {
        return yearsDTOKey;
    }

    public void setDateDisabled(boolean dateDisabled) {
        this.dateDisabled = dateDisabled;
    }

    public boolean isDateDisabled() {
        if (getIntegrationBean() != null && 
            getIntegrationBean().getModuleFrom() != null && 
            getIntegrationBean().getModuleFrom().equals("prm")) {
            dateDisabled = true;
        }
        return dateDisabled;
    }

    public void setShowLovDivFlag(boolean showLovDivFlag) {
        this.showLovDivFlag = showLovDivFlag;
    }

    public boolean isShowLovDivFlag() {
        return showLovDivFlag;
    }


    public void setValidEntityKey(boolean validEntityKey) {
        this.validEntityKey = validEntityKey;
    }

    public boolean isValidEntityKey() {
        return validEntityKey;
    }
    
    
    
//////////////////////////////////////////// Start Editor Action By Amr Galal  ///////////////////////////////////////////////////////   
   
    public String returnEditorAction()
      {
      return getEditorReturn();
      }
    
      public String openEditor()
      {
          DecisionCycleMaintainBean decisionMaintainBean =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean"); 
          IDecisionsDTO decisionsDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
          EditorBean editorBean = (EditorBean)evaluateValueBinding("editorDecitionBean");
          editorBean.setData(decisionsDTO.getDecisionText());
         return "goToEditorDecitionCylcleList";
      }
      
      
      public void addMaterialInEditorView()
      {
          DecisionCycleMaintainBean decisionMaintainBean =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean"); 
          DecisionCycleMaterialsMaintain decisionMaterialsMaintain =(DecisionCycleMaterialsMaintain)evaluateValueBinding("decisionCycleMaterialsMaintainBean");
          IDecisionsDTO decisionsDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
          String material="";
          for(int i=0 ; i<decisionMaterialsMaintain.getCurrentDetails().size();i++)
          {
              if(decisionMaterialsMaintain.getCurrentDetails().get(i).getStatusFlag()!=null)
              {            
                  if(decisionMaterialsMaintain.getCurrentDetails().get(i).getStatusFlag()!=1L)
                  {
                  material=material+ "<p><span style=font-size:small><strong>"+((IRegDecisionMaterialsDTO)decisionMaterialsMaintain.getCurrentDetails().get(i)).getDecmaterialText()+"</span></strong></p>";
                  }
                  else
                  {
                  material="";
                  }
              }
              else
              {
                  material=material+ "<p><span style=font-size:small><strong>"+((IRegDecisionMaterialsDTO)decisionMaterialsMaintain.getCurrentDetails().get(i)).getDecmaterialText()+"</span></strong></p>";
              }
          }
          
          if(material.equals(""))
          {
          getSharedUtil().handleException(new Exception(),"com.beshara.csc.nl.reg.integration.presentation.resources.integration","noMaterialErrorMsg");
         // showMatrialMsg=true;
          }
          else
          {
          decisionsDTO.setDecisionText(material);
         // showMatrialMsg=false;
          }
      }
      
      public String parseEditorToText()
      {
            
      DecisionCycleMaintainBean decisionMaintainBean =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean"); ;
      EditorBean editorBean = (EditorBean)evaluateValueBinding("editorDecitionBean");
      IDecisionsDTO decisionsDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
     // System.out.println(editorBean.getData());
      setReturnFromEditor(editorBean.getData());
      String hTMLString = editorBean.getData();
    //    hTMLString=hTMLString.replaceAll("&nsb;", "");
    //    hTMLString=hTMLString.replaceAll("&nbsp;", "");
      decisionsDTO.setDecisionText(hTMLString);
      return hTMLString;
      }
      
      public String parseEditorAction()
      {
      parseEditorToText();
      return getEditorReturn();
      }

   
    public void setShowMatrialMsg(boolean showMatrialMsg) {
        this.showMatrialMsg = showMatrialMsg;
    }

    public boolean isShowMatrialMsg() {
        return showMatrialMsg;
    }

    public void setReturnFromEditor(String returnFromEditor) {
        this.returnFromEditor = returnFromEditor;
    }

    public String getReturnFromEditor() {
        return returnFromEditor;
    }
    
    
    //////////////////////////////////////////// End Editor Action By Amr Galal  ///////////////////////////////////////////////////////  

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
         DecisionCycleMaintainBean decisionMaintainBean = 
             (DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
        if (getSelectedDTOS() != null && getSelectedDTOS().get(0) != null) {
                 ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setUserName(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getUserName());
                 ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setCivilname(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getCivilname());
                 ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setUserCode(((ISecWorkCenterUsersEntityKey)(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getCode())).getUserCode());
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

    public void setRegStatusDTOList(List<IRegulationStatusDTO> regStatusDTOList) {
        this.regStatusDTOList = regStatusDTOList;
    }

    public List<IRegulationStatusDTO> getRegStatusDTOList() throws DataBaseException, 
                                                                   SharedApplicationException {
        if(regStatusDTOList==null || regStatusDTOList.size()==0){
            regStatusDTOList= (List)RegClientFactory.getRegulationStatusClient().getAll();
            if(regStatusDTOList== null){
               regStatusDTOList =new ArrayList<IRegulationStatusDTO>();
            }
        }
        return regStatusDTOList;
    }
    
    public void setStatusDTOKey(String statusDTOKey) throws DataBaseException, 
                                                            SharedApplicationException {
        if (!(statusDTOKey == null || statusDTOKey.length() == 0 || 
              statusDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
            for (IRegulationStatusDTO status: getRegStatusDTOList()) {
                if (status.getCode().getKey().equals(statusDTOKey)) {
                    BeansUtil.setValue("decisionCycleMaintainBean.pageDTO.regStatusDTO", 
                                       status);
                    break;
                }
            }
        }
        this.statusDTOKey = statusDTOKey;

    }

    public String getStatusDTOKey() {
    if(statusDTOKey==null || statusDTOKey.length()==0 || 
            statusDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + "")){
        statusDTOKey=(String)BeansUtil.getValue("decisionCycleMaintainBean.pageDTO.regStatusDTO.code.key");                
    }
        return statusDTOKey;
    }

    public void setEditorReturn(String editorReturn) {
        this.editorReturn = editorReturn;
    }

    public String getEditorReturn() {
        return editorReturn;
    }
}
