package com.beshara.csc.gn.map.presentation.backingbean.mappeddata;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.dto.IRelationsDTO;
import com.beshara.csc.gn.map.business.entity.IRelationsEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UICommand;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Bean Handle the main operations in current module I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif.Omar Created * <br> * * @author Beshara Group
 * @version 1.0
 * @since
 */
public class MappedDataBean extends BaseBean {
    private List objectType = null;
    private List soc_to = new ArrayList();
    private List soc_from = null;
    private String ALL_MENUS_DEFAULT_VALUE = ISystemConstant.VIRTUAL_VALUE.toString();
    private List mDataFrom;
    private List mDataTo;
    private HtmlForm form1;
    private HtmlCommandButton commandButton1;
    private String selectedObjectTypeId;
    private String selectedSocity2Id;
    private String selectedSocityFrom;
    private String socValue;
    private String javaScriptCaller;
    //start first datatable attrbiute
    private String showedRow = "";
    private HtmlDataTable mappDataTable;
    private Boolean showdetails = false;
    private List mapped_data_societies;
    private Boolean show_mapped_data_societies = false;
    //Start second dataTable
    private HtmlDataTable mapTable;
    private List mappedValue;
    private MappedDataBackBean divBackBean;
    //Search Section
    private String searchQuery; //hold the search data entered by user
    private Integer searchType = new Integer(0); //to hold search type selected by user code ( 0 ) , name ( 1 )
    private String socitiy2valuecode;
    private HtmlSelectOneMenu objectTypeElement;
    private HtmlSelectOneMenu mappedTypeElement;
    private HtmlSelectOneMenu socToControl;
    private HtmlSelectOneMenu socFromControl;
    //////////////////
    private HtmlCommandButton enableRemoveBtn; //= Boolean.FALSE ;
    private UICommand searchHTMLBtn;
    private UICommand mapCancelSearchButton;
    private String mapping_title = "";
    private boolean renderMapping_title = false;
    private String moduleName = "";
    private boolean controlsDisabled = false;
    private Integer totalMapped_data_societiesNo = 0;
    private boolean showLabel = false;
    private HtmlPanelGrid recordsPanel;
    private int mappedRecordNo = 0;
    private HtmlDataScroller many_dataT_data_scroll;
    private Long searchTypeLongVal = 0L;

    private String firstRelationType;
    private String secondRelationType;
    private Long relationTypeCode;
    private Boolean allowAdd;
    private String mappedFilter;
    
    private final String ONE_TO_ONE_RELATION_TYPE_HINT = "chooseOneOnly";
    private final String ONE_TO_MANY_RELATION_TYPE_HINT = "chooseOneOrMore";

    public final Long ONE_TO_ONE_RELATION_TYPE = 1l;
    public final Long ONE_TO_MANY_RELATION_TYPE = 2l;
    public final Long MANY_TO_ONE_RELATION_TYPE = 3l;



    private final static Boolean MAPPED_DATA = true;
    private final static Boolean UN_MAPPED_DATA = false;
    private final static String MAPPED_DATA_VALUE = "1";
    private final static String UN_MAPPED_DATA_VALUE = "0";



    public MappedDataBean() throws DataBaseException, SharedApplicationException {
        setModuleName(getParamter("from"));
        if (!getModuleName().equals("")) {
            setSelectedObjectTypeId(getParamter("objectType"));
            prepareMenuForIntegration(getObjectType(), getSelectedObjectTypeId());
            setSelectedSocityFrom(getParamter("society"));
            setSoc_from(BusinessService.buildSocitiesList(Long.parseLong(getSelectedObjectTypeId())));
            ArrayList preParedList = (ArrayList)((ArrayList)getSoc_from()).clone();
            setSoc_to(BusinessService.buildSocitiesListTo(preParedList, getSelectedSocityFrom()));
            prepareMenuForIntegration(getSoc_from(), getSelectedSocityFrom());
          
        }
        initMappedFilter();
        resetRelations();
    }
    private void initMappedFilter(){
        if(getMappedFilter()==null)
            if(mappedTypeElement != null)
                getMappedTypeElement().setValue(ALL_MENUS_DEFAULT_VALUE);
            setMappedFilter(ALL_MENUS_DEFAULT_VALUE);
    }
    public String removeSelectedCheckBox() {
        ((HtmlSelectBooleanCheckbox)getMapTable().findComponent("checkDelBox")).setSelected(false);
        if (getEnableRemoveBtn().isRendered())
            getEnableRemoveBtn().setRendered(false);
        try {
            getDivBackBean().testListner(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("public String removeSelectedCheckBox ( ) \t" + getEnableRemoveBtn().isRendered());
//        if (getRelationTypeCode().equals(ONE_TO_ONE_RELATION_TYPE)) {
//            if (getMappedValue() != null && getMappedValue().size() > 0) {
//                getSharedUtil().setErrMsgValue("addlink_error_msg");
//            }
//
//        } else {
//            getDivBackBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusOnlyOnElement('Name');");
//        }
        return "";
    }

    public void repositionPage(int firstPos, HtmlDataTable myDataTable) {
        if (myDataTable != null)
            myDataTable.setFirst(firstPos);
    }

    private void prepareMenuForIntegration(List toPrepare, String selectedElement) {
        IBasicDTO selectObjectTypeDTO = (IBasicDTO)getObjectFromList(toPrepare, selectedElement);
        toPrepare.clear();
        toPrepare.add(selectObjectTypeDTO);
    }

    public String getParamter(String name) {
        if (name == null || "".equals(name)) {
            return ALL_MENUS_DEFAULT_VALUE;
        }
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);
    }

    
    public void populatePageComponents(ValueChangeEvent e) throws DataBaseException, SharedApplicationException {
        String comId = ((HtmlSelectOneMenu)e.getSource()).getId();
        Long selectedValue = Long.parseLong(e.getNewValue().toString());
        try {
            if (comId.equals("objectType")) {
                disableObjectTypeRelatedControls(selectedValue);
            }
            if ((comId.equals("soc_from"))) {
                disableSocitiesFromRelatedControls(selectedValue);
            }
            if ((comId.equals("soc_to"))) {
                disableSocitiesToRelatedControls(selectedValue);
            }
            if ((comId.equals("mapped_filter"))) {
                filterByMapped(selectedValue);
            }
            repositionPage(0, getMappDataTable());
            repositionPage(0, getMapTable());
        } catch (NoResultException no) {
            disableObjectTypeRelatedControls(Long.parseLong("-100"));
            no.printStackTrace();
        } catch (SharedApplicationException f) {
            disableObjectTypeRelatedControls(Long.parseLong("-100"));
            f.printStackTrace();
        } catch (DataBaseException f) {
            disableObjectTypeRelatedControls(Long.parseLong("-100"));
            f.printStackTrace();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void disableObjectTypeRelatedControls(Long selectedValue) throws DataBaseException,
                                                                             SharedApplicationException {
        if (this.soc_from != null)
            getSoc_from().clear();
        IBasicDTO selectItem = createSelectItemLabel();
        getSoc_from().add(0, selectItem);
        getSocFromControl().setValue(ALL_MENUS_DEFAULT_VALUE);
        if (!String.valueOf(selectedValue).equals(ALL_MENUS_DEFAULT_VALUE)) {
            List add = BusinessService.buildSocitiesList(selectedValue);
            add.add(0, selectItem);
            setSoc_from(add);
        } ///////////////////////////////////////////////
        getSoc_to().clear();
        getSoc_to().add(0, selectItem);
        getSocToControl().setValue(ALL_MENUS_DEFAULT_VALUE);
        getSocToControl().setDisabled(true);
        getMappedTypeElement().setValue(ALL_MENUS_DEFAULT_VALUE);
        setTotalMapped_data_societiesNo(0);
        setMappedRecordNo(0);
        //////////////////////DataTable Section
        if (getMapped_data_societies() != null)
            getMapped_data_societies().clear();
        ///////////////////////////////////////////////////
        if (getMappedValue() != null)
            getMappedValue().clear();
        ////////////////////////////////////////////////////
        setShowdetails(false); // add and remove panel
        if (getEnableRemoveBtn().isRendered())
            getEnableRemoveBtn().setRendered(false);
        /////////////////////////////Search Panel
        getSearchHTMLBtn().setRendered(false);
        getMapCancelSearchButton().setRendered(false);
        setMapping_title("");
        resetRelations();
        // getRecordsPanel ( ) .setRendered ( false ) ;
    }

    private void disableSocitiesFromRelatedControls(Long selectedValue) throws DataBaseException,
                                                                               SharedApplicationException {
        IBasicDTO selectItem = createSelectItemLabel();
        if (String.valueOf(selectedValue).equals(ALL_MENUS_DEFAULT_VALUE)) { ////////////////////Start comboSection
            ///////////////////////////////////////////////
            // getRecordsPanel ( ) .setRendered ( false ) ;
            getSoc_to().clear();
            getSoc_to().add(0, selectItem);
            getSocToControl().setValue(ALL_MENUS_DEFAULT_VALUE);
            getSocToControl().setDisabled(true);
        } else {
            List soc_to_list = new ArrayList();
            soc_to_list = (ArrayList)((ArrayList)getSoc_from()).clone(); //to save soc_from old values
            soc_to_list.remove(0);
            setSoc_to(BusinessService.buildSocitiesListTo(soc_to_list, selectedValue.toString()));
            getSocToControl().setDisabled(false);
            getSocToControl().setValue(ALL_MENUS_DEFAULT_VALUE);
        } //////////////////////DataTable Section
        if (getMapped_data_societies() != null)
            getMapped_data_societies().clear();
        ///////////////////////////////////////////////////
        if (getMappedValue() != null)
            getMappedValue().clear();
        ////////////////////////////////////////////////////
        setShowdetails(false); // add and remove panel
        if (getEnableRemoveBtn().isRendered())
            getEnableRemoveBtn().setRendered(false);
        /////////////////////////////Search Panel
        getSearchHTMLBtn().setRendered(false);
        getMapCancelSearchButton().setRendered(false);
        setTotalMapped_data_societiesNo(0);
        setMappedRecordNo(0);
        setMapping_title("");
        resetRelations();
    }

    protected void disableSocitiesToRelatedControls(Long selectedValue) throws DataBaseException,
                                                                               SharedApplicationException {
        setShowedRow("");
        if (String.valueOf(selectedValue).equals(ALL_MENUS_DEFAULT_VALUE)) { //////////////////////DataTable Section
            if (getMapped_data_societies() != null)
                getMapped_data_societies().clear();
            ///////////////////////////////////////////////////
            if (getMappedValue() != null)
                getMappedValue().clear();
            ////////////////////////////////////////////////////
            setShowdetails(false); // add and remove panel
            if (getEnableRemoveBtn().isRendered())
                getEnableRemoveBtn().setRendered(false);
            /////////////////////////////Search Panel
            getSearchHTMLBtn().setRendered(false);
            getMapCancelSearchButton().setRendered(false);
            setTotalMapped_data_societiesNo(0);
            setMappedRecordNo(0);
            resetRelations();
        } else {
            getSearchHTMLBtn().setRendered(true);

            //TODO get relation type
            //todo add parameter data type
            getRelationTypes(selectedValue);

            Boolean mapped =
               getMappedTypeElement().getValue().equals(MAPPED_DATA_VALUE) ? MAPPED_DATA :getMappedTypeElement().getValue().equals(UN_MAPPED_DATA_VALUE) ?
                                                                            UN_MAPPED_DATA : null;
                setMapped_data_societies(BusinessService.buildSocitiesTOValueList(Long.parseLong(getSelectedObjectTypeId()),
                                                                                  Long.parseLong(getSelectedSocityFrom()),
                                                                                  selectedValue,mapped));
           
            
            setTotalMapped_data_societiesNo(getMapped_data_societies().size());
            setMappedRecordNo(BusinessService.getMappedNo());
            if (getMapCancelSearchButton().isRendered())
                getMapCancelSearchButton().setRendered(false);
            if (getMappedValue() != null)
                getMappedValue().clear();
            setShowdetails(false); // add and remove panel
            if (getEnableRemoveBtn().isRendered())
                getEnableRemoveBtn().setRendered(false);
        }
        setMapping_title("");
    }



    protected void filterByMapped(Long selectedValue) throws DataBaseException,
                                                                               SharedApplicationException {
        setShowedRow("");
        if (getSelectedSocity2Id().equals(ALL_MENUS_DEFAULT_VALUE)) { //////////////////////DataTable Section
            if (getMapped_data_societies() != null)
                getMapped_data_societies().clear();
            ///////////////////////////////////////////////////
            if (getMappedValue() != null)
                getMappedValue().clear();
            ////////////////////////////////////////////////////
            setShowdetails(false); // add and remove panel
            if (getEnableRemoveBtn().isRendered())
                getEnableRemoveBtn().setRendered(false);
            /////////////////////////////Search Panel
            getSearchHTMLBtn().setRendered(false);
            getMapCancelSearchButton().setRendered(false);
            setTotalMapped_data_societiesNo(0);
            setMappedRecordNo(0);
            resetRelations();
        } else {
            getSearchHTMLBtn().setRendered(true);

            //TODO get relation type
            //todo add parameter data type
            getRelationTypes(Long.parseLong(getSelectedSocity2Id()));

            Boolean mapped =
                (String.valueOf(selectedValue)).equals(MAPPED_DATA_VALUE) ? MAPPED_DATA : (String.valueOf(selectedValue)).equals(UN_MAPPED_DATA_VALUE) ?
                                                                            UN_MAPPED_DATA : null;
                setMapped_data_societies(BusinessService.buildSocitiesTOValueList(Long.parseLong(getSelectedObjectTypeId()),
                                                                                  Long.parseLong(getSelectedSocityFrom()),
                                                                                  selectedValue,mapped));
            setTotalMapped_data_societiesNo(getMapped_data_societies().size());
            setMappedRecordNo(BusinessService.getMappedNo());
            if (getMapCancelSearchButton().isRendered())
                getMapCancelSearchButton().setRendered(false);
            if (getMappedValue() != null)
                getMappedValue().clear();
            setShowdetails(false); // add and remove panel
            if (getEnableRemoveBtn().isRendered())
                getEnableRemoveBtn().setRendered(false);
        }
        setMapping_title("");
    }
    
    
    public void filterByMapped() {
        setShowedRow("");
   //     setSelectedSocityFrom(ALL_MENUS_DEFAULT_VALUE);
   //     setSelectedSocity2Id(ALL_MENUS_DEFAULT_VALUE); //////////////////////DataTable Section
            if (getMapped_data_societies() != null)
                getMapped_data_societies().clear();
            ///////////////////////////////////////////////////
            if (getMappedValue() != null)
                getMappedValue().clear();
            ////////////////////////////////////////////////////
                    setShowdetails(false); // add and remove panel
            if (getEnableRemoveBtn().isRendered())
                getEnableRemoveBtn().setRendered(false);
            /////////////////////////////Search Panel
            getSearchHTMLBtn().setRendered(false);
            getMapCancelSearchButton().setRendered(false);
            setTotalMapped_data_societiesNo(0);
            setMappedRecordNo(0);
            resetRelations();
        filterDataByMapped(Long.valueOf(getSelectedSocity2Id()));
        setMapping_title("");
    }
    public void filterDataByMapped(Long selectedValue){

        try {
            disableSocitiesToRelatedControls(selectedValue);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        repositionPage(0, getMappDataTable());
        repositionPage(0, getMapTable());
    }
    public void search() {
        Long objectType = Long.parseLong(getSelectedObjectTypeId());
        if (getSearchType() == 1)
            setSearchQuery(getDivBackBean().formatSearchString(getSearchQuery()));
        setMapped_data_societies(BusinessService.buildSocityValueFromFilterd(objectType,
                                                                             Long.parseLong(getSelectedSocityFrom()),
                                                                             Long.parseLong(getSelectedSocity2Id()),
                                                                             getSearchQuery(), getSearchType()));
        System.out.println("I am in Search ++++++++++++++++++++++++++++++++");
        System.out.println("preparedStr" + getSearchQuery());
        setTotalMapped_data_societiesNo(getMapped_data_societies().size());
        setMappedRecordNo(BusinessService.calculateMappedRecord(getMapped_data_societies()));
        getMapTable().setRendered(false);
        getMany_dataT_data_scroll().setRendered(false);
        setSearchQuery("");
        getMappDataTable().setFirst(0);
        getMapCancelSearchButton().setRendered(true);
    }

    public String getDetailsAction() throws DataBaseException {
        
        getDivBackBean().setJavaScriptCaller("hideLookUpDiv(window.blocker,window.delConfirm,null,null);setFocusOnlyOnElement('objectType');");
        
        getEnableRemoveBtn().setRendered(false);
        System.out.println("----------------------------------------*****************************");
        IMappedValueDTO objectTypeTO = (IMappedValueDTO)mappDataTable.getRowData();
        this.setShowdetails(true);
        setShow_mapped_data_societies(true);
        this.setShowedRow(objectTypeTO.getStrCode());
        setSocValue(objectTypeTO.getStrCode());
        setMappedValue(BusinessService.buildSocitiesValueToList(Long.parseLong(getSelectedObjectTypeId()),
                                                                Long.parseLong(getSelectedSocity2Id()),
                                                                Long.parseLong(getSelectedSocityFrom()),
                                                                objectTypeTO.getStrCode()));
        if (getMappedValue().size() == 0)
            getEnableRemoveBtn().setRendered(false);
        String msg = getMapMessages("mapping_title", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        msg +=
" " + ((IBasicDTO)getObjectFromList(getObjectType(), getSelectedObjectTypeId())).getName() + " " + objectTypeTO.getName();
        setRenderMapping_title(true);
        setMapping_title(msg);
        System.out.println("objectTypeTO.getStrCode ( ) ---------------------------------------" +
                           objectTypeTO.getStrCode());
        setSocitiy2valuecode(objectTypeTO.getStrCode());
        startDivOperations(objectTypeTO.getStrCode());
        getMapTable().setRendered(true);
        getMany_dataT_data_scroll().setRendered(true);
        getMapTable().setFirst(0);
        return null;
    }

    public Object getObjectFromList(List menuContent, String selectId) {
        Iterator it = menuContent.iterator();
        IBasicDTO dto = new BasicDTO();
        while (it.hasNext()) {
            dto = (IBasicDTO)it.next();
            if (dto.getCode().getKey().toString().equals(selectId))
                break;
        }
        return dto;
    }

    private void startDivOperations(String strCode) throws DataBaseException {
        if (getDivBackBean().getSelectedDTOS().size() >= 1)
            getDivBackBean().getSelectedDTOS().clear();
        getDivBackBean().setObjectTypeId(Long.parseLong(getSelectedObjectTypeId()));
        getDivBackBean().setSocities2Code(Long.parseLong(getSelectedSocity2Id()));
        getDivBackBean().setSocities1Code(Long.parseLong(getSelectedSocityFrom()));
        getDivBackBean().setSocitiesValue(strCode);
        if (!getSelectedSocityFrom().equals(ALL_MENUS_DEFAULT_VALUE.toString()) ||
            !getSelectedSocity2Id().equals(ALL_MENUS_DEFAULT_VALUE.toString()) ||
            !getSelectedObjectTypeId().equals(ALL_MENUS_DEFAULT_VALUE.toString())) {
            getDivBackBean().getAll();
        }
        getDivBackBean().setLookupAddDiv("addDivStyle");
    }

    public String disableCtrls() {
        getMapTable().setRendered(false);
        setShowdetails(false);
        return "";
    }

    public String cancelSearchAvailable() {
        setMapped_data_societies(BusinessService.buildSocitiesTOValueList(Long.parseLong(getSelectedObjectTypeId()),
                                                                          Long.parseLong(getSelectedSocityFrom()),
                                                                          Long.parseLong(getSelectedSocity2Id())));
        setTotalMapped_data_societiesNo(getMapped_data_societies().size());
        setMappedRecordNo(BusinessService.calculateMappedRecord(getMapped_data_societies()));
        setShowdetails(false);
        setMapping_title("");
        getMapTable().setRendered(false);
        getMany_dataT_data_scroll().setRendered(false);
        getMapCancelSearchButton().setRendered(false);
        setShowedRow("");
        setSearchTypeLongVal(0L);
        setSearchQuery("");
        return "";
    }

    public String getMapMessages(String key, Locale loc) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.beshara.csc.gn.map.presentation.resources.map", loc);
        return bundle.getString(key);
    }

    public String removeBall() {
        System.out.println("---+removeBall ( ) removeBall ( ) removeBall ( ) removeBall ( ) ");
        List updatedMappedDataSocities =
            BusinessService.buildSocitiesTOValueList(Long.parseLong(getSelectedObjectTypeId()),
                                                     Long.parseLong(getSelectedSocityFrom()),
                                                     Long.parseLong(getSelectedSocity2Id()));
        IMappedValueDTO oldDTO = null;
        for (Object o : getMapped_data_societies()) {
            if (((IMappedValueDTO)o).getStrCode().equals(getShowedRow())) {
                oldDTO = (IMappedValueDTO)o;
                break;
            }
        }
        for (Object o : updatedMappedDataSocities) {
            if (((IMappedValueDTO)o).getStrCode().equals(getShowedRow())) {
                oldDTO.setHasMappedValues(((IMappedValueDTO)o).getHasMappedValues());
                break;
            }
        }
        if (getMapCancelSearchButton().isRendered()) {
            getMapCancelSearchButton().setRendered(false);
            setMapped_data_societies(updatedMappedDataSocities);
            setTotalMapped_data_societiesNo(updatedMappedDataSocities.size());
            setMappedRecordNo(BusinessService.calculateMappedRecord(updatedMappedDataSocities));
        } else {
            setMappedRecordNo(BusinessService.getMappedNo());
        }
        return "";
    }

    public void deleteSelectedRows(ActionEvent event) { //old Name setdeletedJobList
        IMappedValueDTO checkedElement = (IMappedValueDTO)mapTable.getRowData();
        IMappedDataDTO toBeRemove =
            BusinessService.buildGraphDTO(Long.parseLong(getSelectedObjectTypeId()), Long.parseLong(getSelectedSocityFrom()),
                                          Long.parseLong(getSelectedSocity2Id()), getSocValue());
        toBeRemove.setName(checkedElement.getName());
        IEntityKey entity = new EntityKey(checkedElement.getStrCode()); // to fix change lib issue
        entity.setKeys(new Object[] { checkedElement.getStrCode() }); // to fix change lib issue
        toBeRemove.setCode(entity);
        toBeRemove.setSoc2Value(checkedElement.getStrCode());
        // by sherif.omar used to fix show hide delete button issue bug GN-48-07 ;
        if (checkedElement.getChecked()) {
            getDivBackBean().getSelectedDTOS().add(toBeRemove);
            getEnableRemoveBtn().setRendered(true);
            System.out.println("value change listener");
            System.out.println("elementToBeRemove size=" + getDivBackBean().getElementToBeRemove().size());
            System.out.println("selected----------:" + checkedElement.getName());
        } else {
            IMappedDataDTO dto =
                (IMappedDataDTO)getObjectFromList(getDivBackBean().getSelectedDTOS(), toBeRemove.getCode().getKey().toString());
            if (getDivBackBean().getSelectedDTOS().remove(dto))
                System.out.println("Done-------------------");
            if (getDivBackBean().getSelectedDTOS().size() == 0)
                getEnableRemoveBtn().setRendered(false);
        }
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
            IObjectTypesClient oClient = MapClientFactory.getObjectTypesClient();
            try {
                objectType = new ArrayList();
                IBasicDTO selectItem = createSelectItemLabel();
                objectType.add(selectItem);
                objectType.addAll(oClient.getCodeName());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objectType;
    }

    public IBasicDTO createSelectItemLabel() {
        IBasicDTO selectItem = new BasicDTO();
        selectItem.setName(getMapMessages("selectItem", FacesContext.getCurrentInstance().getViewRoot().getLocale()));
        IEntityKey entity = new EntityKey(ALL_MENUS_DEFAULT_VALUE);
        entity.setKeys(new Object[] { ALL_MENUS_DEFAULT_VALUE });
        selectItem.setCode(entity);
        return selectItem;
    }

    public void setSoc_to(List soc_to) {
        this.soc_to = soc_to;
    }

    public List getSoc_to() {
        return this.soc_to;
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
        if (this.soc_from == null) {
            IBasicDTO selectItem = createSelectItemLabel();
            this.soc_from = new ArrayList();
            this.soc_from.add(0, selectItem);
        }
        return soc_from;
    }

    public void setALL_MENUS_DEFAULT_VALUE(String aLL_MENUS_DEFAULT_VALUE) {
        this.ALL_MENUS_DEFAULT_VALUE = aLL_MENUS_DEFAULT_VALUE;
    }

    public String getALL_MENUS_DEFAULT_VALUE() {
        return ALL_MENUS_DEFAULT_VALUE;
    }

    public void setShowedRow(String showedRow) {
        this.showedRow = showedRow.trim();
    }

    public String getShowedRow() {
        return showedRow;
    }

    public void setMappDataTable(HtmlDataTable mappDataTable) {
        this.mappDataTable = mappDataTable;
    }

    public HtmlDataTable getMappDataTable() {
        return mappDataTable;
    }

    public void setShowdetails(Boolean showdetails) {
        this.showdetails = showdetails;
    }

    public Boolean getShowdetails() {
        return showdetails;
    }

    public void setMapped_data_societies(List mapped_data_societies) {
        if (mapped_data_societies != null) {
            setShow_mapped_data_societies(true);
            this.mapped_data_societies = mapped_data_societies;
        }
    }

    public List getMapped_data_societies() {
        return mapped_data_societies;
    }

    public void setShow_mapped_data_societies(Boolean show_mapped_data_societies) {
        this.show_mapped_data_societies = show_mapped_data_societies;
    }

    public Boolean getShow_mapped_data_societies() {
        return show_mapped_data_societies;
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

    public void setMapTable(HtmlDataTable mapTable) {
        this.mapTable = mapTable;
    }

    public HtmlDataTable getMapTable() {
        return mapTable;
    }

    public void setMappedValue(List mappedValue) {
        if (mappedValue != null) {
            this.mappedValue = mappedValue;
        }
    }

    public List getMappedValue() {
        return mappedValue;
    }

    public void setSelectedSocityFrom(String selectedSocityFrom) {
        this.selectedSocityFrom = selectedSocityFrom;
    }

    public String getSelectedSocityFrom() {
        return selectedSocityFrom;
    }

    public void setDivBackBean(MappedDataBackBean divBackBean) {
        this.divBackBean = divBackBean;
    }

    public MappedDataBackBean getDivBackBean() {
        return divBackBean;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSocValue(String socValue) {
        this.socValue = socValue;
    }

    private void getRelationTypes(Long SelectedSocity2Id) {
        if (!getSelectedObjectTypeId().equals(getALL_MENUS_DEFAULT_VALUE()) &&
            !getSelectedSocityFrom().equals(getALL_MENUS_DEFAULT_VALUE()) &&
            !String.valueOf(SelectedSocity2Id).equals(ALL_MENUS_DEFAULT_VALUE)) {

            if (!checkFirstDirectionRelation(SelectedSocity2Id)) {
                if (!checkSecondDirectionRelation(SelectedSocity2Id)) {
                    // Default case
                    setRelationTypeCode(ONE_TO_ONE_RELATION_TYPE);
                    setFirstRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                    setSecondRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                }
            }

        } else {
            resetRelations();
        }
    }

    private void resetRelations() {
        setRelationTypeCode(ONE_TO_ONE_RELATION_TYPE);
        setFirstRelationType("");
        setSecondRelationType("");
      
    }

    private Boolean checkFirstDirectionRelation(Long selectedToCode) {
        IRelationsEntityKey ek =
            getRelationsEntityKey(Long.valueOf(getSelectedObjectTypeId()), Long.valueOf(getSelectedSocityFrom()),
                                  selectedToCode);
        IRelationsDTO selectedRelationsDTO = null;
        try {
            selectedRelationsDTO = (IRelationsDTO)MapClientFactory.getRelationsClient().getById(ek);
            if (selectedRelationsDTO != null) {
                setRelationTypeCode(selectedRelationsDTO.getReltypeCode());
                if (ONE_TO_ONE_RELATION_TYPE.equals(selectedRelationsDTO.getReltypeCode())) {
                    setFirstRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                    setSecondRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                } else if (ONE_TO_MANY_RELATION_TYPE.equals(selectedRelationsDTO.getReltypeCode())) {
                    setFirstRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                    setSecondRelationType(ONE_TO_MANY_RELATION_TYPE_HINT);
                }
                return true;
            }
        } catch (DataBaseException e) {
            return false;
        } catch (SharedApplicationException e) {
            return false;
        }
        return false;
    }


    private Boolean checkSecondDirectionRelation(Long selectedToCode) {
        IRelationsEntityKey ek =
            getRelationsEntityKey(Long.valueOf(getSelectedObjectTypeId()), selectedToCode, Long.valueOf(getSelectedSocityFrom()));
        IRelationsDTO selectedRelationsDTO = null;
        try {
            selectedRelationsDTO = (IRelationsDTO)MapClientFactory.getRelationsClient().getById(ek);
            if (selectedRelationsDTO != null) {
                setRelationTypeCode(MANY_TO_ONE_RELATION_TYPE); // in this case it is always "one to one"
                if (ONE_TO_ONE_RELATION_TYPE.equals(selectedRelationsDTO.getReltypeCode())) {
                    setFirstRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                    setSecondRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                } else if (ONE_TO_MANY_RELATION_TYPE.equals(selectedRelationsDTO.getReltypeCode())) {
                    setFirstRelationType(ONE_TO_MANY_RELATION_TYPE_HINT);
                    setSecondRelationType(ONE_TO_ONE_RELATION_TYPE_HINT);
                }
                return true;
            }
        } catch (DataBaseException e) {
            return false;
        } catch (SharedApplicationException e) {
            return false;
        }
        return false;
    }

    private IRelationsEntityKey getRelationsEntityKey(Long objtypeCode, Long soc1Code, Long soc2Code) {
        return MapEntityKeyFactory.createRelationsEntityKey(objtypeCode, soc1Code, soc2Code);
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

    public void setObjectTypeElement(HtmlSelectOneMenu objectTypeElement) {
        this.objectTypeElement = objectTypeElement;
    }

    public HtmlSelectOneMenu getObjectTypeElement() {
        return objectTypeElement;
    }

    public void setSocToControl(HtmlSelectOneMenu socToControl) {
        this.socToControl = socToControl;
    }

    public HtmlSelectOneMenu getSocToControl() {
        return socToControl;
    }

    public void setSocFromControl(HtmlSelectOneMenu socFromControl) {
        this.socFromControl = socFromControl;
    }

    public HtmlSelectOneMenu getSocFromControl() {
        return socFromControl;
    }

    public void setEnableRemoveBtn(HtmlCommandButton enableRemoveBtn) {
        this.enableRemoveBtn = enableRemoveBtn;
    }

    public HtmlCommandButton getEnableRemoveBtn() {
        return enableRemoveBtn;
    }

    public void setMapping_title(String mapping_title) {
        this.mapping_title = mapping_title;
    }

    public String getMapping_title() {
        return mapping_title;
    }

    public void setRenderMapping_title(boolean renderMapping_title) {
        this.renderMapping_title = renderMapping_title;
    }

    public boolean isRenderMapping_title() {
        return renderMapping_title;
    }

    public void setMapCancelSearchButton(UICommand mapCancelSearchButton) {
        this.mapCancelSearchButton = mapCancelSearchButton;
    }

    public UICommand getMapCancelSearchButton() {
        return mapCancelSearchButton;
    }

    public void setModuleName(String moduleName) {
        if (moduleName == null) {
            moduleName = "";
        }
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setControlsDisabled(boolean controlsDisabled) {
        this.controlsDisabled = controlsDisabled;
    }

    public boolean isControlsDisabled() {
        return controlsDisabled;
    }

    public void setSearchHTMLBtn(UICommand searchHTMLBtn) {
        this.searchHTMLBtn = searchHTMLBtn;
    }

    public UICommand getSearchHTMLBtn() {
        return searchHTMLBtn;
    }

    public Integer getTotalMapped_data_societiesNo() {
        return totalMapped_data_societiesNo;
    }

    public void setTotalMapped_data_societiesNo(Integer totalMapped_data_societiesNo) {
        this.totalMapped_data_societiesNo = totalMapped_data_societiesNo;
    }

    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
    }

    public boolean getShowLabel() {
        return showLabel;
    }

    public void setMappedRecordNo(int mappedRecordNo) {
        this.mappedRecordNo = mappedRecordNo;
    }

    public int getMappedRecordNo() {
        return mappedRecordNo;
    }

    public void setRecordsPanel(HtmlPanelGrid recordsPanel) {
        this.recordsPanel = recordsPanel;
    }

    public HtmlPanelGrid getRecordsPanel() {
        return recordsPanel;
    }

    public void setMany_dataT_data_scroll(HtmlDataScroller many_dataT_data_scroll) {
        this.many_dataT_data_scroll = many_dataT_data_scroll;
    }

    public HtmlDataScroller getMany_dataT_data_scroll() {
        return many_dataT_data_scroll;
    }

    public void setSearchTypeLongVal(Long searchTypeLongVal) {
        setSearchType(searchTypeLongVal.intValue());
        this.searchTypeLongVal = searchTypeLongVal;
    }

    public Long getSearchTypeLongVal() {
        searchTypeLongVal = new Long(getSearchType().intValue());
        return searchTypeLongVal;
    }


    public void setRelationTypeCode(Long relationTypeCode) {
        this.relationTypeCode = relationTypeCode;
    }

    public Long getRelationTypeCode() {
        return relationTypeCode;
    }

    public void setAllowAdd(Boolean allowAdd) {
        this.allowAdd = allowAdd;
    }

    public Boolean getAllowAdd() {
        return allowAdd;
    }

    public void setFirstRelationType(String firstRelationType) {
        this.firstRelationType = firstRelationType;
    }

    public String getFirstRelationType() {
        return firstRelationType;
    }

    public void setSecondRelationType(String secondRelationType) {
        this.secondRelationType = secondRelationType;
    }

    public String getSecondRelationType() {
        return secondRelationType;
    }

    public void setMappedFilter(String mappedFilter) {
        this.mappedFilter = mappedFilter;
    }

    public String getMappedFilter() {
        return mappedFilter;
    }

    public void setMappedTypeElement(HtmlSelectOneMenu mappedTypeElement) {
        this.mappedTypeElement = mappedTypeElement;
    }

    public HtmlSelectOneMenu getMappedTypeElement() {
        return mappedTypeElement;
    }
}
