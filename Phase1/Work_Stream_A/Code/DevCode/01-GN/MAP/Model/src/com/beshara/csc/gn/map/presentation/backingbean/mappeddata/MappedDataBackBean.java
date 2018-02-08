package com.beshara.csc.gn.map.presentation.backingbean.mappeddata;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.client.IMappedDataClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.UICommand;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class MappedDataBackBean extends TreeDivBean {
    private Long objectTypeId;
    private Long socities2Code;
    private Long socities1Code;
    private String socitiesValue;
    private String searchString;
    private List currentList = new ArrayList();
    private List<IBasicDTO> saveMappedDTO = new ArrayList();
    List<IBasicDTO> mappedDataList = new ArrayList();
    private List elementToBeRemove = new ArrayList();
    private HtmlCommandButton add_ok_btn;
    private Integer pageIndexAdd = 0;
    private Integer listSize;
    private UICommand cancelSearchBtn;
    private HtmlDataScroller dataScroller = new HtmlDataScroller();
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.map.presentation.resources.map_";
    private static final String BACK_BEAN_NAME = "mapped_Data_div";
    private transient HtmlForm frm;
    private String ALL_MENUS_DEFAULT_VALUE = ISystemConstant.VIRTUAL_VALUE.toString();
    public MappedDataBackBean() {
        setPageDTO(MapDTOFactory.createMappedValueDTO()); //set this the page dto 
        super.setSelectedPageDTO(MapDTOFactory.createMappedValueDTO());
        super.setAddDivTitleBindingString("#{mapResources.society_add_new_society_title}"); // pass the title of the add div ex: "# { bundle.key } " 
        super.setEditDivTitleBindingString("#{mapResources.society_update_title}");
        super.setDelAlertTitleBindingString("#{mapResources.society_delete_one_society_title}");
        super.setDelConfirmTitleBindingString("#{mapResources.society_delete_confirm_society_title}"); // 
        setClient(MapClientFactory.getDataClient());
        setEnableSearchByCode(true);
        setRootName("objtypeName");
        setBundleName(BUNDLE_NAME);
        setDto(MapDTOFactory.createMappedValueDTO());
        setDtoDetails(MapDTOFactory.createMappedValueDTO());
        setGoActionOkMethod("mapped_Data_div.mapTree");
        
        //set LoveBaaseBean
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSearchTyp("0");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowLookupAdd(true);
        app.setShowTreediv(true);
        app.setShowSearch(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowLovDiv(true);
        return app;
    }

    public void deleteDiv() throws DataBaseException, ItemNotFoundException {
        System.out.println("deleteManyCheckBoxListener");
        IMappedDataClient client = MapClientFactory.getMappedDataClient();
        setClient(MapClientFactory.getMappedDataClient());
        super.deleteDiv();
        ValueBinding vb = facesUtilsValueBinding("#{mappedDataBean}");
        if (getObjectTypeId() != null && getSocities2Code() != null && 
            getSocities1Code() != null && 
            !getSocitiesValue().equalsIgnoreCase("") && 
            getSocitiesValue() != null) {
            MappedDataBean mainBean = 
                (MappedDataBean)vb.getValue(FacesContext.getCurrentInstance());
            mainBean.getEnableRemoveBtn().setRendered(false);
            mainBean.setMappedValue(BusinessService.buildSocitiesValueToList(getObjectTypeId(), 
                                                                             getSocities2Code(), 
                                                                             getSocities1Code(), 
                                                                             getSocitiesValue()));
            mainBean.getMapTable().setRendered(true);
            mainBean.getMapTable().setFirst(0);
        }
        this.setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.delConfirm ) ; settingFoucsAtDivDeleteConfirm ( ) ; ");
    }

    public void removeElement(List elementToBeRemove) {
    }

    public void testListner(ActionEvent event) throws Exception {
        System.out.println("sdssdsdsdsd");
        if (super.getSelectedDTOS().size() >= 0)
            getSelectedDTOS().clear();
        this.getAll();
        if (getCancelSearchBtn().isRendered()) {
            getCancelSearchBtn().setRendered(false);
        } // ( ( HtmlSelectBooleanCheckbox ) getMyDataTable ( ) .findComponent ( "chk2" ) ) .setSelected ( false ) ; 
        getMyDataTable().setFirst(0);
        getCancelSearchBtn().setRendered(false);
    }

    public void selectedCheckboxsAll(ActionEvent event) throws Exception {
        try { // System.out.println ( super.isCheckAllFlag ( ) + " " ) ; 
            // int first = this.getMyDataTable ( ) .getFirst ( ) ; 
            Set<IBasicDTO> selectedSet = new HashSet<IBasicDTO>();
            selectedSet.addAll(getSelectedDTOS());
            Integer rowsCountPerPage = 
                (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("# { shared_util.noOfTableRows } ").getValue(FacesContext.getCurrentInstance());
            if (rowsCountPerPage == null) {
                throw new Exception("# { shared_util.noOfTableRows } return null");
            }
            int first = this.getMyDataTable().getFirst();
            for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {
                IMappedDataDTO toBeInsertDTO = 
                    BusinessService.buildGraphDTO(getObjectTypeId(), 
                                                  getSocities1Code(), 
                                                  getSocities2Code(), 
                                                  getSocitiesValue());
                this.getMyDataTable().setRowIndex(j);
                if (!this.getMyDataTable().isRowAvailable()) {
                    break;
                }
                IBasicDTO selected = 
                    (IMappedValueDTO)this.getMyDataTable().getRowData();
                System.out.println(selected.getName());
                // if ( super.isCheckAllFlag ( ) == true ) { 
                // boolean exist = false ; 
                // for ( IBasicDTO dto: this.getSelectedDTOS ( ) ) { 
                // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) 
                // exist = true ; 
                // } 
                // if ( !exist ) { 
                // this.getSelectedDTOS ( ) .add ( selected ) ; 
                // 
                // System.out.println ( "adding..." ) ; 
                // System.out.println ( selected.getName ( ) ) ; 
                // } 
                // toBeInsertDTO.setSoc2Value ( ( ( IMappedValueDTO ) selected ) .getStrCode ( ) ) ; 
                // saveMappedDTO.add ( toBeInsertDTO ) ; 
                // } 
                // if ( super.isCheckAllFlag ( ) == false ) { 
                // for ( int i = 0 ; i < this.getSelectedDTOS ( ) .size ( ) ; i++ ) { 
                // 
                // IBasicDTO dto = this.getSelectedDTOS ( ) .get ( i ) ; 
                // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) { 
                // 
                // this.getSelectedDTOS ( ) .remove ( i ) ; 
                // saveMappedDTO.remove ( i ) ; 
                // System.out.println ( "removing..." ) ; 
                // System.out.println ( selected.getName ( ) ) ; 
                // 
                // } 
                // } 
                // } 
                if (this.isCheckAllFlag()) {
                    try {
                        selectedSet.add(selected);
                        toBeInsertDTO.setSoc2Value(((IMappedValueDTO)selected).getStrCode());
                        saveMappedDTO.add(toBeInsertDTO);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    for (IBasicDTO item: selectedSet) {
                        if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                            selectedSet.remove(item);
                            this.getSelectedDTOS().remove(item);
                            saveMappedDTO.remove(item);
                            System.out.println("removing...");
                            System.out.println(selected.getName());
                            break;
                        }
                    }
                }
                getSelectedDTOS().clear();
                getSelectedDTOS().addAll(selectedSet);
                FacesContext ctx = FacesContext.getCurrentInstance();
                ExternalContext ex = ctx.getExternalContext();
                HttpSession session = (HttpSession)ex.getSession(true);
                session.setAttribute("selectedDTOS", this.getSelectedDTOS());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("the size " + this.getSelectedDTOS().size());
        }
    }

    public void selectedCheckboxs(ActionEvent event) throws Exception {
        try {
            super.selectedCheckboxs(event);
            IMappedDataDTO toBeInsertDTO = 
                BusinessService.buildGraphDTO(getObjectTypeId(), 
                                              getSocities1Code(), 
                                              getSocities2Code(), 
                                              getSocitiesValue());
            Set<IBasicDTO> selectedSet = new HashSet<IBasicDTO>();
            selectedSet.addAll(getSelectedDTOS());
            IClientDTO selected = 
                (IMappedValueDTO)this.getMyDataTable().getRowData();
            if (selected.getChecked()) { // boolean exist = false ; 
                // for ( IBasicDTO dto: this.getSelectedDTOS ( ) ) { 
                // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) 
                // exist = true ; 
                // } 
                // if ( !exist ) { 
                // this.getSelectedDTOS ( ) .add ( selected ) ; 
                // 
                // System.out.println ( "adding..." ) ; 
                // System.out.println ( selected.getName ( ) ) ; 
                // } 
                selectedSet.add(selected);
                //use saveDto to fix IMappedDataDTO IiIsIsIuIeI.I 
                toBeInsertDTO.setSoc2Value(selected.getCode().getKey().toString());
                saveMappedDTO.add(toBeInsertDTO);
            } else {
                selected.setChecked(Boolean.TRUE);
                for (IBasicDTO item: selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }
                selected.setChecked(Boolean.FALSE);
            } // if ( ! ( selected.getChecked ( ) ) ) { 
            // 
            // 
            // for ( int i = 0 ; i < this.getSelectedDTOS ( ) .size ( ) ; i++ ) { 
            // 
            // IBasicDTO dto = this.getSelectedDTOS ( ) .get ( i ) ; 
            // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) { 
            // 
            // this.getSelectedDTOS ( ) .remove ( i ) ; 
            // saveMappedDTO.remove ( i ) ; 
            // System.out.println ( "removing..." ) ; 
            // System.out.println ( selected.getName ( ) ) ; 
            // 
            // } 
            // } 
            // 
            // 
            // } 
            getSelectedDTOS().clear();
            getSelectedDTOS().addAll(selectedSet);
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().setErrMsgValue(e.getMessage());
        }
        System.out.println("the size " + this.getSelectedDTOS().size());
    } // public void selectedCheckboxsAll ( ActionEvent event ) throws Exception { 
    // try { 
    // 
    // System.out.println ( super.isCheckAllFlag ( ) + " " ) ; 
    // int first = this.getMyDataTable ( ) .getFirst ( ) ; 
    // for ( int j = first ; j < first + 8 ; j++ ) { 
    // IMappedDataDTO toBeInsertDTO = 
    // BusinessService.buildGraphDTO ( getObjectTypeId ( ) , 
    // getSocities1Code ( ) , 
    // getSocities2Code ( ) , 
    // getSocitiesValue ( ) ) ; 
    // this.getMyDataTable ( ) .setRowIndex ( j ) ; 
    // System.out.println ( " " + this.getMyDataTable ( ) .getRowData ( ) ) ; 
    // IBasicDTO selected = 
    // ( IMappedValueDTO ) this.getMyDataTable ( ) .getRowData ( ) ; 
    // System.out.println ( selected.getName ( ) ) ; 
    // 
    // if ( super.isCheckAllFlag ( ) == true ) { 
    // boolean exist = false ; 
    // for ( IBasicDTO dto: this.getSelectedDTOS ( ) ) { 
    // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) 
    // exist = true ; 
    // } 
    // if ( !exist ) { 
    // this.getSelectedDTOS ( ) .add ( selected ) ; 
    // 
    // System.out.println ( "adding..." ) ; 
    // System.out.println ( selected.getName ( ) ) ; 
    // } 
    // toBeInsertDTO.setSoc2Value ( ( ( IMappedValueDTO ) selected ) .getStrCode ( ) ) ; 
    // saveMappedDTO.add ( toBeInsertDTO ) ; 
    // } 
    // 
    // 
    // if ( super.isCheckAllFlag ( ) == false ) { 
    // for ( int i = 0 ; i < this.getSelectedDTOS ( ) .size ( ) ; i++ ) { 
    // 
    // IBasicDTO dto = this.getSelectedDTOS ( ) .get ( i ) ; 
    // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) { 
    // 
    // this.getSelectedDTOS ( ) .remove ( i ) ; 
    // saveMappedDTO.remove ( i ) ; 
    // System.out.println ( "removing..." ) ; 
    // System.out.println ( selected.getName ( ) ) ; 
    // 
    // } 
    // } 
    // } 
    // 
    // 
    // FacesContext ctx = FacesContext.getCurrentInstance ( ) ; 
    // ExternalContext ex = ctx.getExternalContext ( ) ; 
    // HttpSession session = ( HttpSession ) ex.getSession ( true ) ; 
    // session.setAttribute ( "selectedDTOS" , this.getSelectedDTOS ( ) ) ; 
    // } 
    // } catch ( Exception e ) { 
    // e.printStackTrace ( ) ; 
    // System.out.println ( "the size " + this.getSelectedDTOS ( ) .size ( ) ) ; 
    // } 
    // } 
    // 
    // public void selectedCheckboxs ( ActionEvent event ) throws Exception { 
    // 
    // try { 
    // IMappedDataDTO toBeInsertDTO = 
    // BusinessService.buildGraphDTO ( getObjectTypeId ( ) , 
    // getSocities1Code ( ) , 
    // getSocities2Code ( ) , 
    // getSocitiesValue ( ) ) ; 
    // ClientDTO selected = 
    // ( IMappedValueDTO ) this.getMyDataTable ( ) .getRowData ( ) ; 
    // 
    // if ( selected.getChecked ( ) ) { 
    // 
    // 
    // boolean exist = false ; 
    // for ( IBasicDTO dto: this.getSelectedDTOS ( ) ) { 
    // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) 
    // exist = true ; 
    // } 
    // if ( !exist ) { 
    // this.getSelectedDTOS ( ) .add ( selected ) ; 
    // 
    // System.out.println ( "adding..." ) ; 
    // System.out.println ( selected.getName ( ) ) ; 
    // } 
    // 
    // //use saveDto to fix IMappedDataDTO IiIsIsIuIeI.I 
    // toBeInsertDTO.setSoc2Value ( selected.getCode ( ) .getKey ( ) .toString ( ) ) ; 
    // saveMappedDTO.add ( toBeInsertDTO ) ; 
    // } 
    // 
    // if ( ! ( selected.getChecked ( ) ) ) { 
    // 
    // 
    // for ( int i = 0 ; i < this.getSelectedDTOS ( ) .size ( ) ; i++ ) { 
    // 
    // IBasicDTO dto = this.getSelectedDTOS ( ) .get ( i ) ; 
    // if ( dto.getCode ( ) .getKey ( ) .equals ( selected.getCode ( ) .getKey ( ) ) ) { 
    // 
    // this.getSelectedDTOS ( ) .remove ( i ) ; 
    // saveMappedDTO.remove ( i ) ; 
    // System.out.println ( "removing..." ) ; 
    // System.out.println ( selected.getName ( ) ) ; 
    // 
    // } 
    // } 
    // 
    // 
    // } 
    // 
    // } catch ( Exception e ) { 
    // e.printStackTrace ( ) ; 
    // getSharedUtil ( ) .setErrMsgValue ( e.getMessage ( ) ) ; 
    // } 
    // System.out.println ( "the size " + this.getSelectedDTOS ( ) .size ( ) ) ; 
    // 
    // 
    // } 

    public void scrollerAction(ActionEvent act) {
        System.out.println("----------------------scrollerAction");
        setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.lookupAddDiv ) ; javascript:setFocusOnlyOnElement ( 'Name' ) ; ");
    }

    public String searchAvailable() throws DataBaseException {
        System.out.println("----------------------search" + getSearchString());
        String preparedStr = "";
        if (!getSearchString().equals("")) {
            preparedStr = formatSearchString(getSearchString());
        }else {
            getMyTableData().clear();
                        getCancelSearchBtn().setRendered(true);
        }
        //if (preparedStr.contains("%")) {
        
        //12-001-2014
        setMyTableData(BusinessService.searchDivList(getObjectTypeId(), 
                                                         getSocities2Code(), 
                                                         getSocities1Code(), 
                                                         getSocitiesValue(), 
                                                         preparedStr));

    
        repositionPage(0);
            getCancelSearchBtn().setRendered(true);
//        } else if (preparedStr.equals("%")) {
//            getMyTableData().clear();
//            getCancelSearchBtn().setRendered(true);
//        }
        return "";
    }

    public String cancelSearchAvailable() throws DataBaseException {
        setSearchString("");
        getAll();
        getCancelSearchBtn().setRendered(false);
        return "";
    }
    

//to do
    public String addMappedData() {
        IMappedDataClient client = MapClientFactory.getMappedDataClient();
        System.out.println("ADDDDDDDDDDDDDDDD");
        try {
            ValueBinding vb = 
                facesUtilsValueBinding("#{mappedDataBean}");
            MappedDataBean mainBean = 
                (MappedDataBean)vb.getValue(FacesContext.getCurrentInstance());
            if (mainBean.getRelationTypeCode().equals(mainBean.ONE_TO_ONE_RELATION_TYPE) || mainBean.getRelationTypeCode().equals(mainBean.MANY_TO_ONE_RELATION_TYPE)) {
                if(saveMappedDTO.size()>1){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.map.presentation.resources.map",
                                                                                  "morelink_error_msg"));
                    getAll();
                    setSearchString("");
                    saveMappedDTO.clear();
                    this.getSelectedDTOS().clear();
                    setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');");
                    return "";
                }
            }
            if (mainBean.getRelationTypeCode().equals(mainBean.ONE_TO_ONE_RELATION_TYPE) || mainBean.getRelationTypeCode().equals(mainBean.MANY_TO_ONE_RELATION_TYPE)) {
                
                        if ((mainBean.getMappedValue() != null && mainBean.getMappedValue().size() > 0) || saveMappedDTO.size()>1) {
                            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.map.presentation.resources.map",
                                                                                          "addlink_error_msg"));  
                            getAll();
                            setSearchString("");
                            saveMappedDTO.clear();
                            this.getSelectedDTOS().clear();
                            setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');");
                            return "";                            
                        }
                        
                        
                    
                    }

            if (!mainBean.MANY_TO_ONE_RELATION_TYPE.equals(mainBean.getRelationTypeCode())) {
      
      
               
                
                mappedDataList.clear();
                for(IBasicDTO _mappedDTO: saveMappedDTO){
                    if(client.isValueAlreadyMapped(getObjectTypeId(), getSocities1Code(), getSocities2Code(),
                                                       ((IMappedDataDTO)_mappedDTO).getSoc2Value())){
                        
                        if(saveMappedDTO.size()==1){
                            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.map.presentation.resources.map",
                                                                                          "addlink_val_error_msg"));
                            getAll();
                            setSearchString("");
                            saveMappedDTO.clear();
                            this.getSelectedDTOS().clear();
                            setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');");
                            return "";
                        }
                        

                    
                    
                    }else{
                        mappedDataList.add(_mappedDTO);
                    }
                }
                
                if(mappedDataList.isEmpty()){
                    getAll();
                    setSearchString("");
                    saveMappedDTO.clear();
                    this.getSelectedDTOS().clear();
                    setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');");
                    return "";
                }
            }else{
                mappedDataList.clear();
                mappedDataList.addAll(saveMappedDTO);
            }

            if (client.createMappedDataList(mappedDataList)) {
                
                if (getObjectTypeId() != null && getSocities2Code() != null && 
                    getSocities1Code() != null && 
                    !getSocitiesValue().equalsIgnoreCase("") && 
                    getSocitiesValue() != null) {
                    
                    mainBean.setMappedValue(BusinessService.buildSocitiesValueToList(getObjectTypeId(), 
                                                                                     getSocities2Code(), 
                                                                                     getSocities1Code(), 
                                                                                     getSocitiesValue()));
                    List updatedMappedDataSocities = 
                        BusinessService.buildSocitiesTOValueList(getObjectTypeId(), 
                                                                 getSocities1Code(), 
                                                                 getSocities2Code());
                    IMappedValueDTO oldDTO = null;
                    for (Object o: mainBean.getMapped_data_societies()) {
                        if (((IMappedValueDTO)o).getStrCode().equals(mainBean.getShowedRow())) {
                            oldDTO = (IMappedValueDTO)o;
                            break;
                        }
                    }
                    for (Object o: updatedMappedDataSocities) {
                        if (((IMappedValueDTO)o).getStrCode().equals(mainBean.getShowedRow())) {
                            oldDTO.setHasMappedValues(((IMappedValueDTO)o).getHasMappedValues());
                            break;
                        }
                    } // mainBean.setMapped_data_societies ( BusinessService.buildSocitiesTOValueList ( getObjectTypeId ( ) , 
                    // getSocities1Code ( ) , 
                    // getSocities2Code ( ) ) ) ; 
                    if (mainBean.getMapCancelSearchButton().isRendered()) { //setSearchMode ( false ) ; 
                        mainBean.getMapCancelSearchButton().setRendered(false);
                        // mainBean.disableSocitiesToRelatedControls ( 3L ) ; 
                        mainBean.setMapped_data_societies(BusinessService.buildSocitiesTOValueList(getObjectTypeId(), 
                                                                                                   getSocities1Code(), 
                                                                                                   getSocities2Code()));
                        mainBean.setTotalMapped_data_societiesNo(mainBean.getMapped_data_societies().size());
                        mainBean.setMappedRecordNo(BusinessService.getMappedNo());
                        //mainBean.setTotalMapped_data_societiesNo ( mainBean.getMapped_data_societies ( ) .size ( ) ) ; 
                        //mainBean.setMappedRecordNo ( BusinessService.calculateMappedRecord ( mainBean.getMapped_data_societies ( ) ) ) ; 
                    } else {
                        mainBean.setMappedRecordNo(BusinessService.getMappedNo());
                    } // mainBean.getMapCancelSearchButton ( ) .setRendered ( false ) ; 
                    
                    if(mappedDataList.size()<saveMappedDTO.size()){
                        getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.map.presentation.resources.map",
                                                                                      "somelinks_val_error_msg"));
                    }else{
                        getSharedUtil().setSuccessMsgValue("SuccessAdds");    
                    }
                    
                    
                    getAll();
                    setSearchString("");
                    saveMappedDTO.clear();
                    this.getSelectedDTOS().clear();
                    // ( ( HtmlSelectBooleanCheckbox ) getMyDataTable ( ) .findComponent ( "chk2" ) ) .setSelected ( false ) ; 
                }
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue("FailureInAdd");
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue("FailureInAdd");
            e.printStackTrace();
        } catch (Exception e) {
            getSharedUtil().setErrMsgValue("FailureInAdd");
            e.printStackTrace();
        }
        setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');");
        return "";
    }

    private ValueBinding facesUtilsValueBinding(String valueBinding) {
        Application app = FacesContext.getCurrentInstance().getApplication();
        ValueBinding vb = app.createValueBinding(valueBinding);
        return vb;
    }

    public void getAll() throws DataBaseException {
        System.out.println(getObjectTypeId() != null && 
                           getSocities2Code() != null && 
                           getSocities1Code() != null && 
                           !getSocitiesValue().equalsIgnoreCase("") && 
                           getSocitiesValue() != null);
        if (getObjectTypeId() != null && getSocities2Code() != null && 
            getSocities1Code() != null && 
            !getSocitiesValue().equalsIgnoreCase("") && 
            getSocitiesValue() != null)
//            setMyTableData(BusinessService.buildDivList(getObjectTypeId(), 
//                                                        getSocities2Code(), 
//                                                        getSocities1Code(), 
//                                                        getSocitiesValue()));

        //12/01/2015
        try {
            //List list = QulClientFactory.getEducationLevelsClient().getAll();
           
           List<IMappedValueDTO> list;
            if(!isDisplayTreeFlag()){
            list = BusinessService.buildDivList(getObjectTypeId(), 
                                                            getSocities2Code(), 
                                                            getSocities1Code(), 
                                                            getSocitiesValue());
                setMyTableData(list);
                treeMap.clear();
            }else{
                list = BusinessService.buildDivTree(getObjectTypeId(), 
                                                            getSocities2Code(), 
                                                            getSocities1Code(), 
                                                            getSocitiesValue());
                setMyTableData(list);
                
                
                clearAndFillTreeMap(list);
                buildTree();   
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setSocities2Code(Long socities2Code) {
        this.socities2Code = socities2Code;
    }

    public Long getSocities2Code() {
        return socities2Code;
    }

    public void setSocities1Code(Long socities1Code) {
        this.socities1Code = socities1Code;
    }

    public Long getSocities1Code() {
        return socities1Code;
    }

    public void setSocitiesValue(String socitiesValue) {
        this.socitiesValue = socitiesValue;
    }

    public String getSocitiesValue() {
        return socitiesValue;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setCurrentList(List currentList) {
        this.currentList = currentList;
    }

    public List getCurrentList() {
        return currentList;
    }

    public void setElementToBeRemove(List elementToBeRemove) {
        this.elementToBeRemove = elementToBeRemove;
    }

    public List getElementToBeRemove() {
        return elementToBeRemove;
    }

    public void setAdd_ok_btn(HtmlCommandButton add_ok_btn) {
        this.add_ok_btn = add_ok_btn;
    }

    public HtmlCommandButton getAdd_ok_btn() {
        return add_ok_btn;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public Integer getListSize() throws DataBaseException {
        return super.getListSize();
    }

    public void setCancelSearchBtn(UICommand cancelSearchBtn) {
        this.cancelSearchBtn = cancelSearchBtn;
    }

    public UICommand getCancelSearchBtn() {
        return cancelSearchBtn;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setPageIndexAdd(Integer pageIndexAdd) {
        this.pageIndexAdd = pageIndexAdd;
    }

    public Integer getPageIndexAdd() {
        if (dataScroller != null) {
            pageIndexAdd = dataScroller.getPageIndex();
        }
        return pageIndexAdd;
    }

    public void setSaveMappedDTO(List saveMappedDTO) {
        this.saveMappedDTO = saveMappedDTO;
    }

    public List getSaveMappedDTO() {
        return saveMappedDTO;
    }

    public void setFrm(HtmlForm frm) {
        this.frm = frm;
    }


    public HtmlForm getFrm() {

        if (getUsingPortal()) {
            if (this.appMainLayoutPortalBuilder() != null) {
                setCurrentApplictionMainLayoutPortal(this.appMainLayoutPortalBuilder());
            }
        } else {
            if (this.appMainLayoutBuilder() != null) {
                setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
            }
        }

        return frm;
    }
    
    
    private Boolean displayTreeFlag;
    
    private boolean isDisplayTreeFlag() {
        try{
            setDisplayTreeFlag(MapClientFactory.getDataClient().DisplayTreeFlag(getObjectTypeId(),getSocities2Code()));    
        }catch(DataBaseException e){
            e.printStackTrace();
            setDisplayTreeFlag(false);
        } catch (SharedApplicationException e) {
        }

        return displayTreeFlag;
    }


    public void idChange(ValueChangeEvent e) {
        super.idChange(e);
        IMappedValueDTO selectedDTO = (IMappedValueDTO)getDtoDetails();
        if(selectedDTO != null && selectedDTO.getCode()!= null){
            setEnabledNotLeaf(ISystemConstant.LEAF_FLAG.equals(selectedDTO.getLeafFlag()));
        }else{
            setEnabledNotLeaf(false);
        }
        
    }

    public String mapTree(){
        saveMappedDTO.clear();
        IMappedValueDTO selectedDto = (IMappedValueDTO)getDtoDetails();
        if(selectedDto!= null && selectedDto.getCode()!= null && ISystemConstant.LEAF_FLAG.equals(selectedDto.getLeafFlag())){
            IMappedDataDTO toBeInsertDTO = 
                BusinessService.buildGraphDTO(getObjectTypeId(), 
                                              getSocities1Code(), 
                                              getSocities2Code(), 
                                              getSocitiesValue());
            toBeInsertDTO.setSoc2Value(((IMappedValueDTO)selectedDto).getStrCode());
            saveMappedDTO.add(toBeInsertDTO);
           return addMappedData();
            
        }
        return "";
    }
    private static Map<IEntityKey,IMappedValueDTO> treeMap = new HashMap<IEntityKey,IMappedValueDTO>();
    
    private void clearAndFillTreeMap(List<IMappedValueDTO> list){
        treeMap.clear();
        for(IMappedValueDTO dto: list){
            treeMap.put(dto.getCode(), dto);
        }
    }
    
    public IBasicDTO preEdit() {

        if (this.getClientValueBinding() != null) {
            setClient((IBasicClient)evaluateValueBinding(this.getClientValueBinding()));
        }

            SharedUtilBean shared_util = getSharedUtil();
            try {
                return treeMap.get((getEntityKey()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
      
        return null;

    }

    public void setDisplayTreeFlag(Boolean displayTreeFlag) {
        this.displayTreeFlag = displayTreeFlag;
    }

    public Boolean getDisplayTreeFlag() {
        return displayTreeFlag;
    }
    
    /*
     * show search Lov Div
     */
    public void showSearchListOfValuesDiv() {
        int x=1;
        getLovBaseBean().setCodeTypeString(true);
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
                  getLovBaseBean().setSearchTyp(searchType.toString());
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
       
        MappedDataBean mappedDataBean = 
         (MappedDataBean)super.evaluateValueBinding("mappedDataBean");
         mappedDataBean.setSelectedObjectTypeId(dto.getCode().getKey());
        setSelectedRadio("");
        setSearchMode(true);
        return "";
    }
}
