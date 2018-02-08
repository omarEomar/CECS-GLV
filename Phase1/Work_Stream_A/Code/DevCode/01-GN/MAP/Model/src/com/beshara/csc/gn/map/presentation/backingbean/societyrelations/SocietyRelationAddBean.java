package com.beshara.csc.gn.map.presentation.backingbean.societyrelations;


//import com.beshara.csc.gn.map.presentation.backingbean.mappeddata.MappedDataBackBean;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.IRelationsDTO;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.IRelationsEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.presentation.backingbean.mappeddata.BusinessService;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Bean Handle the main operations in current module I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif.Omar Created * <br> * * @author Beshara Group
 * @version 1.0
 * @since
 */
public class SocietyRelationAddBean extends LookUpBaseBean{
    private List objectType = null;
    private List soc_to = new ArrayList();
    private List soc_from = null;
    private List soc_rel_types = null;
    private String ALL_MENUS_DEFAULT_VALUE = "";
    private List mDataFrom;
    private List mDataTo;
    private HtmlForm form1;
    private HtmlCommandButton commandButton1;
    private String selectedObjectTypeId;
    private String selectedSocity2Id;
    private String selectedSocityFrom;
    private String selectedRelationTypeCode;
    private String socValue;
    private String javaScriptCaller;
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.map.presentation.resources.map";
    private String socitiy2valuecode;
    private static final String BACK_BEAN_NAME = "societyRelationAddBean";
   public SocietyRelationAddBean() throws DataBaseException, 
                                   SharedApplicationException {
            setSelectedObjectTypeId(getALL_MENUS_DEFAULT_VALUE());
            setSelectedSocityFrom(getALL_MENUS_DEFAULT_VALUE());
            setSelectedSocity2Id(getALL_MENUS_DEFAULT_VALUE());
            fillRelationTypeList();
            setPageDTO(MapDTOFactory.createRelationsDTO());
            setClient(MapClientFactory.getRelationsClient());
        //set LoveBaaseBean
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSearchTyp("0");
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowSearch(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowLovDiv(true);
        return app;
    }

    public String getMapMessages(String key, Locale loc) {
        ResourceBundle bundle = 
            ResourceBundle.getBundle("com.beshara.csc.gn.map.presentation.resources.map", 
                                     loc);
        return bundle.getString(key);
    }



    public void setMDataFrom(List mDataFrom) {
        this.mDataFrom = mDataFrom;
    }

    public List getMDataFrom() {
        return mDataFrom;
    }

    public void setMDataTo(List mDataTo) {
        this.mDataTo = mDataTo;
    }

    public List getMDataTo() {
        return mDataTo;
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }

    public void setCommandButton1(HtmlCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public HtmlCommandButton getCommandButton1() {
        return commandButton1;
    }

    public void setObjectType(List objectType) {
        this.objectType = objectType;
    }

    public List getObjectType() {
        if (objectType == null) {
            IObjectTypesClient oClient = 
                MapClientFactory.getObjectTypesClient();
            try {
                objectType = new ArrayList();
//                IBasicDTO selectItem = createSelectItemLabel();
//                objectType.add(selectItem);
                objectType.addAll(oClient.getCodeName());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objectType;
    }

    private IBasicDTO createSelectItemLabel() {
        IBasicDTO selectItem = new BasicDTO();
        selectItem.setName(getMapMessages("selectItem", 
                                          FacesContext.getCurrentInstance().getViewRoot().getLocale()));
        IEntityKey entity = new EntityKey(ALL_MENUS_DEFAULT_VALUE);
        entity.setKeys(new Object[] { ALL_MENUS_DEFAULT_VALUE });
        selectItem.setCode(entity);
        return selectItem;
    }

    public void setSoc_to(List soc_to) {
        this.soc_to = soc_to;
    }


    public List getSoc_to() {
        if (this.getSelectedSocityFrom().equals(ALL_MENUS_DEFAULT_VALUE)) {
            this.soc_to = new ArrayList();    
        }else{
            try {
                ArrayList<ISocietiesDTO> preParedList = 
                    (ArrayList)((ArrayList)getSoc_from()).clone();
               // preParedList.remove(0);
                setSoc_to(BusinessService.buildSocitiesListTo(preParedList, 
                                                              getSelectedSocityFrom()));
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
//        IBasicDTO selectItem = createSelectItemLabel();
//        this.soc_to.add(0, selectItem);
        return soc_to;
        
    }
    
    public void setJavaScriptCaller(String javaScriptCaller) {
        this.javaScriptCaller = javaScriptCaller;
    }

    public String getJavaScriptCaller() {
        return javaScriptCaller;
    }

    public void setSoc_from(List soc_from) {
        this.soc_from = soc_from;
    }

    public List getSoc_from() {
        
        if (this.getSelectedObjectTypeId().equals(ALL_MENUS_DEFAULT_VALUE)) {
            this.soc_from = new ArrayList();    
        }else{
            try {
                setSoc_from(BusinessService.buildSocitiesList(Long.parseLong(getSelectedObjectTypeId())));
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
//        IBasicDTO selectItem = createSelectItemLabel();
//        this.soc_from.add(0, selectItem);
        return soc_from;
    }

    public void setALL_MENUS_DEFAULT_VALUE(String aLL_MENUS_DEFAULT_VALUE) {
        this.ALL_MENUS_DEFAULT_VALUE = aLL_MENUS_DEFAULT_VALUE;
    }

    public String getALL_MENUS_DEFAULT_VALUE() {
        return ALL_MENUS_DEFAULT_VALUE;
    }


    public void setSelectedObjectTypeId(String selectedObjectTypeId) {
        this.selectedObjectTypeId = selectedObjectTypeId;
    }

    public String getSelectedObjectTypeId() {
        return selectedObjectTypeId;
    }

    public void setSelectedSocity2Id(String selectedSocity2Id) {
        this.selectedSocity2Id = selectedSocity2Id;
    }

    public String getSelectedSocity2Id() {
        return selectedSocity2Id;
    }


    public void setSelectedSocityFrom(String selectedSocityFrom) {
        this.selectedSocityFrom = selectedSocityFrom;
    }

    public String getSelectedSocityFrom() {
        return selectedSocityFrom;
    }


    public void setSocValue(String socValue) {
        this.socValue = socValue;
    }

    public String getSocValue() {
        return socValue;
    }

    public void setSocitiy2valuecode(String socitiy2valuecode) {
        this.socitiy2valuecode = socitiy2valuecode;
    }

    public String getSocitiy2valuecode() {
        return socitiy2valuecode;
    }


    public void setSoc_rel_types(List soc_rel_types) {
        this.soc_rel_types = soc_rel_types;
    }

    public List getSoc_rel_types() {
        return soc_rel_types;
    }

    public void setSelectedRelationTypeCode(String selectedRelationTypeCode) {
        this.selectedRelationTypeCode = selectedRelationTypeCode;
    }

    public String getSelectedRelationTypeCode() {
        return selectedRelationTypeCode;
    }

    private void fillRelationTypeList() {
        if(soc_rel_types == null){
            try {
                setSoc_rel_types(MapClientFactory.getSocietyRelationTypesClient().getAll());
//                IBasicDTO selectItem = createSelectItemLabel();
//                this.soc_rel_types.add(0, selectItem);
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
    }
    public String goBack(){
        return "socrelationlist";
    }
    public String addItem(){
        //this parameter to Differentiates save and saveAndNew
        //if false , so addMode
        //else saveAndNewMode
        if(checkRelation()){
            this.add(false);
            return "socrelationlist";
        }else{
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                          "alreadyAddedRelation"));    
        }
        
      return ""; 
    }
    public void saveNewItem(){
        reIntializeMsgs();
        if(checkRelation()){
            this.add(true);
            setSelectedObjectTypeId(getALL_MENUS_DEFAULT_VALUE());
            setSelectedSocityFrom(getALL_MENUS_DEFAULT_VALUE());
            setSelectedSocity2Id(getALL_MENUS_DEFAULT_VALUE());
            setSelectedRelationTypeCode(getALL_MENUS_DEFAULT_VALUE());
        }else{
            setShowErrorMsg(true);
            setErrorMsg(getSharedUtil().messageLocator(BUNDLE_NAME,"alreadyAddedRelation"));
        }
        
       
        
    }
    public void add(boolean saveAndNewMode){
        ListBean listBean =
                     (ListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{listBean}").getValue(FacesContext.getCurrentInstance());
        IRelationsDTO dtoForAdd = ((IRelationsDTO)getPageDTO()) ;        
        dtoForAdd.setSoc1Code(Long.valueOf(getSelectedSocityFrom()));
        dtoForAdd.setSoc2Code(Long.valueOf(getSelectedSocity2Id()));
        dtoForAdd.setObjtypeCode(Long.valueOf(getSelectedObjectTypeId()));
        dtoForAdd.setReltypeCode(Long.valueOf(getSelectedRelationTypeCode()));

        try {
            IBasicDTO basicDTO =getClient().add(dtoForAdd);  
             listBean.getAll();
            IRelationsDTO relationDTO = (IRelationsDTO)basicDTO;
            if(relationDTO.getObjtypeCode()!= null && relationDTO.getSoc1Code()!=null && relationDTO.getSoc2Code() != null){
                IRelationsEntityKey entityKey = MapEntityKeyFactory.createRelationsEntityKey(relationDTO.getObjtypeCode(), relationDTO.getSoc1Code(), relationDTO.getSoc2Code());
                basicDTO.setCode(entityKey);
             listBean.getPageIndex(basicDTO.getCode().getKey());
            }
             if (listBean.getHighlightedDTOS() != null) {
                 listBean.getHighlightedDTOS().clear();
                 listBean.getHighlightedDTOS().add(basicDTO);
             }
            if(!saveAndNewMode){
                listBean.getSharedUtil().setSuccessMsgValue("SuccessAdds");
            }else{
                this.setSuccess(true);
            }
        } catch (DataBaseException e) {
            listBean.getSharedUtil().handleException(e, "", "failure_in_add");
        } catch (SharedApplicationException e) {
            listBean.getSharedUtil().handleException(e, "", "failure_in_add");
        }
      
    }
    private Boolean checkRelation(){
       
        if(getSelectedObjectTypeId()!= null && getSelectedSocityFrom()!= null && getSelectedSocity2Id()!= null){
            Long objectType = Long.valueOf(getSelectedObjectTypeId());
            Long soc1Code = Long.valueOf(getSelectedSocityFrom());
            Long soc2Code = Long.valueOf(getSelectedSocity2Id());
          if(checkRelationExist(objectType ,soc1Code ,soc2Code )){
              return false ; 
          }else if( checkRelationExist(objectType ,soc2Code ,soc1Code )){
            return false ;
          }
        }
        return true ;
    }
    private Boolean checkRelationExist(Long objectType , Long soc1Code , Long soc2Code){
        IRelationsEntityKey ek = MapEntityKeyFactory.createRelationsEntityKey(objectType ,soc1Code ,soc2Code );
        IRelationsDTO selectedRelationsDTO = null;
        try {
        selectedRelationsDTO = (IRelationsDTO)MapClientFactory.getRelationsClient().getById(ek);
        if(selectedRelationsDTO != null){
            return true;
        }
        } catch (DataBaseException e) {
                return false;
        } catch (SharedApplicationException e) {
            return false;
        }
        return false;
    }
    
    
    public String editItem(){
            ListBean listBean =
                         (ListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{listBean}").getValue(FacesContext.getCurrentInstance());
            IRelationsDTO dtoForUpdate = MapDTOFactory.createRelationsDTO() ;        
            Long objCode =Long.valueOf(getSelectedObjectTypeId());
            Long soc1Code = Long.valueOf(getSelectedSocityFrom());
            Long soc2Code = Long.valueOf(getSelectedSocity2Id());
            Long relCode = Long.valueOf(getSelectedRelationTypeCode());
            dtoForUpdate.setSoc1Code(soc1Code);
            dtoForUpdate.setSoc2Code(soc2Code);
            dtoForUpdate.setObjtypeCode(objCode);
            dtoForUpdate.setReltypeCode(relCode);

            try {
                IRelationsEntityKey entityKey = 
                    MapEntityKeyFactory.createRelationsEntityKey(objCode, soc1Code, soc2Code);
                dtoForUpdate.setCode(entityKey);
               getClient().update(dtoForUpdate);  
                 listBean.getAll();
                 listBean.getPageIndex(dtoForUpdate.getCode().getKey());
                
                 if (listBean.getHighlightedDTOS() != null) {
                     listBean.getHighlightedDTOS().clear();
                     listBean.getHighlightedDTOS().add(dtoForUpdate);
                 }
               
                    listBean.getSharedUtil().setSuccessMsgValue("SuccesUpdated");
                
            } catch (DataBaseException e) {
                listBean.getSharedUtil().handleException(e, "", "failure_in_add");
            } catch (SharedApplicationException e) {
                listBean.getSharedUtil().handleException(e, "", "failure_in_add");
            }
          
        
        return "socrelationlist";
    }
     
     /*
      * show search Lov Div
      */
     public void showSearchListOfValuesDiv() {
         getLovBaseBean().setCodeTypeString(false);
         getLovBaseBean().setKeyIndex(1L);
         if (isSearchMode() == true) {
             getLovBaseBean().setSearchMode(true);
             getLovBaseBean().setCleanDataTableFlage(true);

         } else {
             getLovBaseBean().setSearchMode(false);
             getLovBaseBean().setCleanDataTableFlage(false);
             getLovBaseBean().setSearchQuery("");
             getLovBaseBean().setSearchTyp("0");
         }

         getLovBaseBean().setReturnMethodName(BACK_BEAN_NAME + ".returnObjectTypeLovDiv");
         getLovBaseBean().setSearchMethod(BACK_BEAN_NAME + ".searchObjectTypeLovDiv");
         getLovBaseBean().setCancelSearchMethod(BACK_BEAN_NAME + ".cancelSearchObjectTypeLovDiv");
         
         getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + ";focusHighlightedNode();");
         if (getLovBaseBean().isSearchMode() == false) {
             getLovBaseBean().setSearchQuery("");
         }
        
     }
     /*
      * search Action
      */
     public String searchObjectTypeLovDiv(Object searchType, Object searchQuery) {
         List<IBasicDTO> result = new ArrayList<IBasicDTO>();
         try {
             if (searchQuery != null && !searchQuery.equals("")) {
                 String objectSearchQuery=searchQuery.toString();
                 String objectSearchType=searchType.toString();
                 result = MapClientFactory.getObjectTypesClient().getObjectTypeSearchCrieteria(objectSearchQuery,objectSearchType);
                 if(result != null && result .size() != 0  ){
                   getLovBaseBean().setMyTableData(result); 
                 }
             }
         } catch (DataBaseException e) {
             e.printStackTrace();
             getLovBaseBean().setMyTableData(new ArrayList(0));
         } catch (Exception e) {
             e.printStackTrace();
             getLovBaseBean().setMyTableData(new ArrayList(0));
         }

         return "";
     }
     /*
      * Cancel Search
      */
     public String cancelSearchObjectTypeLovDiv() {
         getLovBaseBean().setCodeTypeString(false);
         setSearchMode(false);
         getLovBaseBean().setSearchQuery("");
         getLovBaseBean().getSearchQuery();
         getLovBaseBean().setSelectedRadio("");
         getLovBaseBean().setSearchTyp("0");
         getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
         getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
         return "";
     }
     /*
      * return method highlight
      * focus ObjectType
      */
     public String returnObjectTypeLovDiv() {
         getLovBaseBean().setSearchMode(true);
       List<IBasicDTO> objectTypeList = getLovBaseBean().getSelectedDTOS();
       IObjectTypesDTO dto = (IObjectTypesDTO)objectTypeList.get(0);
      selectedObjectTypeId=dto.getCode().getKey();
         getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
         getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
         setSelectedRadio("");
         setSearchMode(true);
         return "";
     }
}
