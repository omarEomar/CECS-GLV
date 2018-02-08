package com.beshara.csc.nl.reg.presentation.backingbean.regulation.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.gn.sec.business.dto.ISecSystemTabColumnsDTO;
import com.beshara.csc.gn.sec.business.dto.ISecSystemTablesDTO;
import com.beshara.csc.gn.sec.business.entity.SecSystemTablesEntityKey;
import com.beshara.csc.nl.reg.business.client.IModuleRelationsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.ISearchColumnsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IModuleRelationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.entity.ITypesEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.nl.reg.presentation.backingbean.util.TableInfo;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.component.UIColumn;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.component.html.ext.HtmlInputHidden;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;


public class RegulationModulesMaintain extends ManyToManyDetailsMaintain {

    private String selectedModuleKey;
    private List<IBasicDTO> modules;
    private List toBeDeleteedRows = new ArrayList();
    private boolean forceId = true;
    private static final String forceIdExpression = 
        "#{regulationModulesMaintainBean.forceId}";
    private String selectedTableKey;
    
    private List<IBasicDTO> tables;
    private List<ISecSystemTabColumnsDTO> availableColumns;
    private List customAvailableDetails;

    private int customAvailableDetailsSize;
    private List<Integer> selectedAvailableDetailsRowIndices;

    private List<IModuleRelationsDTO> moduleRelations = 
        new ArrayList<IModuleRelationsDTO>();

    private List<TableInfo> customCurrentDetails;

    private List<String> columnNames = new ArrayList<String>();
    private List<ISecSystemTabColumnsDTO> columns = 
        new ArrayList<ISecSystemTabColumnsDTO>();
    private String columnNameSelected = "";
    private String columnNamesValue = "";
    private boolean searchStatus = false;
    private List<ISearchColumnsDTO> searchColumnsDTOList = 
        new ArrayList<ISearchColumnsDTO>();

    private HtmlDataTable actualDataDataTable = new HtmlDataTable();
    private List<Vector<IClientDTO>> actualDataList = new ArrayList();
    private int totalSize = 0;


    public RegulationModulesMaintain() {
        setClient(RegClientFactory.getModuleRelationsClient());
        setDivMainContentMany("divREGMainContentMany");
        setLookupAddDiv("moduleDiv");
        setLookupEditDiv("relatedSysdivSearch");
//        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        app.setShowLookupEdit(true);
        return app;
    }

    public void getListAvailable() throws DataBaseException, 
                                          SharedApplicationException {

        System.out.println("available");
        //        if (getMasterEntityKey() != null) {
        //            setAvailableDetails(((IPublishClient)getClient()).listAvailableEntitiesCenter(getMasterEntityKey()));
        //
        //        } else {
        //            List<IBasicDTO> chk = 
        //                ((IPublishClient)getClient()).listAvailableEntitiesCenter(new IRegulationsEntityKey());
        //            setAvailableDetails(chk);
        //        }
        //
        //        super.getListAvailable();
    }


    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return dto;
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        return dto;
    }


    public boolean validate() {
        System.out.println("from advantages Validate");
        return true;

    }

    public void resetDetailDTO(IBasicDTO dto) {
        //        ((JobDutiesDTO)dto).setActivateDate(null);
    }


    public void setSelectedModuleKey(String selectedSystemName) {
        this.selectedModuleKey = selectedSystemName;
    }

    public String getSelectedModuleKey() {
        return selectedModuleKey;
    }

    public void setModules(List<IBasicDTO> systemsNames) {
        this.modules = systemsNames;
    }

    public List<IBasicDTO> getModules() {
        if (modules == null) {
            try {
                modules = 
                        SecClientFactory.getSecApplicationModulesClient().getLeavesCodeName();
            } catch (DataBaseException e) {
                System.out.print(e);
                modules = new ArrayList<IBasicDTO>(0);
            } catch (Exception e) {
                System.out.print(e);
                modules = new ArrayList<IBasicDTO>(0);
            }
        }

        return modules;
    }

    public void setSelectedTableKey(String selectedSystemTable) {
        this.selectedTableKey = selectedSystemTable;
    }

    public String getSelectedTableKey() {
        return selectedTableKey;
    }

    public void setTables(List<IBasicDTO> systemTables) {
        this.tables = systemTables;
    }

    public List<IBasicDTO> getTables() {
        return tables;
    }

    public void setCustomAvailableDetails(List customAvailableDetails) {
        this.customAvailableDetails = customAvailableDetails;
    }

    public List getCustomAvailableDetails() {
        if (customAvailableDetails == null) {
            fillData();
        }

        return customAvailableDetails;
    }

    public int getSelectedAvailableDetailsCount() {
        if (selectedAvailableDetailsRowIndices == null) {
            return 0;
        }
        return selectedAvailableDetailsRowIndices.size();
    }

    public void fillTables() {
        customAvailableDetails = null;
        selectedAvailableDetailsRowIndices = null;
        if (selectedModuleKey == null || selectedModuleKey.length() == 0) {
            tables = null;
            selectedTableKey = null;
            columnNameSelected = null;
        } else {
            try {
                tables = 
                        SecClientFactory.getSecSystemTablesClient().getSecSystemTables(Long.parseLong(selectedModuleKey));
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillData() {

        if (selectedTableKey == null || selectedTableKey.length() == 0) {
            customAvailableDetails = new ArrayList();
            selectedAvailableDetailsRowIndices = null;
        } else {
            try {
                columns = 
                        (List)SecClientFactory.getSecSystemTabColumnsClient().getSystemTablesColumns(selectedTableKey, 
                                                                                                                             Long.parseLong(selectedModuleKey));


                for (ISecSystemTabColumnsDTO col: columns) {
                    columnNames.add(col.getColumnName());
                }

                if (!isSearchStatus()) {
                    try {
                        customAvailableDetails = 
                                RegClientFactory.getModuleRelationsClient().getNativeModuleRelationsCenter(selectedTableKey, 
                                                                                                           columnNames);
                    } catch (DataBaseException e) {
                        customAvailableDetails = 
                                RegClientFactory.getModuleRelationsClient().getNativeModuleRelations(selectedTableKey, 
                                                                                                     columnNames);
                    }
                } else {
                    //IModuleRelationsClient.getNativeModuleRelationsWithSearchCenter(String tablename, List<String> columnsNames, List<ISearchColumnsDTO> searchColumnsList)
                    try {
                        customAvailableDetails = 
                                RegClientFactory.getModuleRelationsClient().getNativeModuleRelationsWithSearchCenter(selectedTableKey, 
                                                                                                                     columnNames, 
                                                                                                                     getSearchColumnsDTOList());
                    } catch (DataBaseException e) {
                        customAvailableDetails = 
                                RegClientFactory.getModuleRelationsClient().getNativeModuleRelationsWithSearch(selectedTableKey, 
                                                                                                               columnNames, 
                                                                                                               getSearchColumnsDTOList());
                    }


                }
                TableInfo selectedTable = getSelectedTable();
                int i = 0;
                while (i < customAvailableDetails.size()) {
                    Vector row = (Vector)customAvailableDetails.get(i);
                    Long serial = getRowSerial(row);
                    if (selectedTable != null && 
                        selectedTable.contains(serial)) {
                        customAvailableDetails.remove(i);
                    } else {
                        row.add(0, false);
                        ++i;
                    }
                }

                buildTableColumns(columns);
                selectedAvailableDetailsRowIndices = new ArrayList<Integer>();

            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public void buildTableColumns(List<ISecSystemTabColumnsDTO> columns) {

        List tableColumns = getAvailableDataTable().getChildren();
        while (tableColumns.size() > 1) {
            tableColumns.remove(1);
        }


        for (int i = 0; i < columns.size(); i++) {

            UIColumn col = new UIColumn();

            UIOutput colHeader = new UIOutput();
            colHeader.setValue(columns.get(i).getColumnDesc());
            //getColumnName().add(columns.get(i));
            col.setHeader(colHeader);

            UIOutput colValue = new UIOutput();
            ValueBinding vb = 
                BeansUtil.createValueBinding(String.format("list[%s]", i + 1));
            colValue.setValueBinding("value", vb);
            col.getChildren().add(colValue);

            tableColumns.add(col);
        }

    }


    public void setSelectedAvailableDetailsRowIndices(List<Integer> selectedAvailableDetailsRowIndices) {
        this.selectedAvailableDetailsRowIndices = 
                selectedAvailableDetailsRowIndices;
    }

    public List<Integer> getSelectedAvailableDetailsRowIndices() {
        return selectedAvailableDetailsRowIndices;
    }

    public void selectedAvailable(ActionEvent event) throws Exception {
        int index = getAvailableDataTable().getRowIndex();
        boolean checked = 
            (Boolean)((Vector)getAvailableDataTable().getRowData()).get(0);
        if (checked) {
            selectedAvailableDetailsRowIndices.add((Integer)index);
        } else {
            selectedAvailableDetailsRowIndices.remove((Integer)index);
        }
    }

    public void selectedAvailableAll(ActionEvent event) throws Exception {

        int rowIndex = this.getAvailableDataTable().getFirst();
        int lastRowIndex = 
            rowIndex + this.getAvailableDataTable().getRows() - 1;
        int maxRowIndex = customAvailableDetails.size() - 1;

        if (lastRowIndex > maxRowIndex) {
            lastRowIndex = maxRowIndex;
        }

        boolean checked = super.isCheckAllFlagAvailable();
        if (checked) {
            while (rowIndex <= lastRowIndex) {
                Vector rowData = (Vector)customAvailableDetails.get(rowIndex);
                if (!(Boolean)rowData.get(0)) {
                    rowData.set(0, true);
                    selectedAvailableDetailsRowIndices.add((Integer)rowIndex);
                }
                ++rowIndex;
            }
        } else {
            while (rowIndex <= lastRowIndex) {
                Vector rowData = (Vector)customAvailableDetails.get(rowIndex);
                if ((Boolean)rowData.get(0)) {
                    rowData.set(0, false);
                    selectedAvailableDetailsRowIndices.remove((Integer)rowIndex);
                }
                ++rowIndex;
            }
        }
    }

    public void add() {

        List selectedRows = new ArrayList();
        List<IModuleRelationsDTO> newModuleRelations = 
            new ArrayList<IModuleRelationsDTO>();
        for (int i = 0; i < selectedAvailableDetailsRowIndices.size(); i++) {
            int rowIndex = selectedAvailableDetailsRowIndices.get(i);
            Vector rowData = (Vector)customAvailableDetails.get(rowIndex);
            Long serial = getRowSerial(rowData);

            selectedRows.add(rowData);

            IModuleRelationsDTO moduleRelationsDTO = 
                findModuleRelationBySerial(serial);
            if (moduleRelationsDTO == null) {
                moduleRelationsDTO = RegDTOFactory.createModuleRelationsDTO();

                moduleRelationsDTO.setTabrecSerialRef(serial);
                moduleRelationsDTO.setTableName(selectedTableKey);
                moduleRelationsDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);

                moduleRelations.add(moduleRelationsDTO);
            } else {
                moduleRelationsDTO.setStatusFlag(null);
            }
            newModuleRelations.add(moduleRelationsDTO);
        }

        customAvailableDetails.removeAll(selectedRows);
        updateTablesInfo(customCurrentDetails, newModuleRelations);

        while (selectedAvailableDetailsRowIndices.size() > 0) {
            selectedAvailableDetailsRowIndices.remove(0);
        }
    }

    private static void updateTablesInfo(List<TableInfo> tablesInfo, 
                                         List<IModuleRelationsDTO> moduleRelationsDTOList) {

        Map<String, TableInfo> availableTablesMap = 
            new HashMap<String, TableInfo>();
        for (TableInfo tinfo: tablesInfo) {
            availableTablesMap.put(tinfo.getTableName(), tinfo);
        }

        if (moduleRelationsDTOList != null) {
            for (IModuleRelationsDTO moduleRelationsDTO: 
                 moduleRelationsDTOList) {

                String tableName = moduleRelationsDTO.getTableName();
                if (moduleRelationsDTO.getTabrecSerialRef() == null) {
                    moduleRelationsDTO.setTabrecSerialRef(((IModuleRelationsEntityKey)moduleRelationsDTO.getCode()).getTabrecSerialRef());
                }

                if (availableTablesMap.containsKey(tableName)) {
                    TableInfo tableInfo = availableTablesMap.get(tableName);
                    tableInfo.addRecord(moduleRelationsDTO);
                } else {
                    String tableDesc = "";
                    try {
                        ISecSystemTablesDTO secSystemTablesDTO = 
                            (ISecSystemTablesDTO)SecClientFactory.getSecSystemTablesClient().getById(new SecSystemTablesEntityKey(tableName));
                        tableDesc = secSystemTablesDTO.getTableDesc();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    TableInfo tinfo = 
                        new TableInfo(tableName, tableDesc, moduleRelationsDTO);
                    availableTablesMap.put(tableName, tinfo);
                    tablesInfo.add(tinfo);
                }

            }
        }

    }

    public void setCustomAvailableDetailsSize(int customAvailableDetailsSize) {
        this.customAvailableDetailsSize = customAvailableDetailsSize;
    }

    public int getCustomAvailableDetailsSize() {
        if (customAvailableDetails != null) {
            customAvailableDetailsSize = customAvailableDetails.size();
        }
        return customAvailableDetailsSize;
    }

    public void setModuleRelations(List<IModuleRelationsDTO> moduleRelations) {
        this.moduleRelations = moduleRelations;
    }

    public List<IModuleRelationsDTO> getModuleRelations() {
        return moduleRelations;
    }

    public void setCustomCurrentDetails(List<TableInfo> customCurrentDetails) {
        this.customCurrentDetails = customCurrentDetails;
    }

    public List<TableInfo> getCustomCurrentDetails() {
        if (customCurrentDetails == null) {
            customCurrentDetails = new ArrayList<TableInfo>();
            updateTablesInfo(customCurrentDetails, getModuleRelations());
        }

        return customCurrentDetails;
    }

    public int getCustomCurrentDetailsSize() {
        if (getCustomCurrentDetails() != null) {
            return customCurrentDetails.size();
        }

        return 0;
    }


    private Long getRowSerial(Vector row) {
        return Long.parseLong(row.get(row.size() - 1).toString());
    }

    private TableInfo getSelectedTable() {
        for (TableInfo tinfo: customCurrentDetails) {
            if (tinfo.getTableName().equals(selectedTableKey)) {
                return tinfo;
            }
        }

        return null;
    }

    public void selectedCurrentAll(ActionEvent event) throws Exception {
        try {

            System.out.println("------------------starting check all----------------------------");
            System.out.println("checkallflag=" + isCheckAllFlag());
            int first = this.getCurrentDataTable().getFirst();
            int rowsCount = 0;
            if (this.getCustomCurrentDetails() != null)
                rowsCount = 
                        this.getCustomCurrentDetails().size(); //.getRowCount();
            //            int count = 8;
            //            if (rowsCount < 8)
            int count = rowsCount;
            for (int j = first; j < first + count; j++) {
                this.getCurrentDataTable().setRowIndex(j);
                System.out.println(" " + 
                                   this.getCurrentDataTable().getRowData());
                IBasicDTO selected = 
                    (IBasicDTO)this.getCurrentDataTable().getRowData();
                System.out.println(selected.getName());

                if (isCheckAllFlag() == true) {
                    boolean exist = false;
                    for (IBasicDTO dto: this.getSelectedCurrentDetails()) {
                        if (dto.getName().equals(this.mapToCodeNameDTO(selected).getName()))
                            exist = true;
                    }
                    if (!exist) {
                        this.getSelectedCurrentDetails().add(this.mapToCodeNameDTO(selected));

                        System.out.println("adding...");
                        System.out.println(this.mapToCodeNameDTO(selected).getName());
                    }
                }


                if (isCheckAllFlag() == false) {
                    for (int i = 0; 
                         i < this.getSelectedCurrentDetails().size(); i++) {

                        IBasicDTO dto = this.getSelectedCurrentDetails().get(i);
                        if (dto.getName().equals(this.mapToCodeNameDTO(selected).getName())) {

                            System.out.println("removing...");
                            System.out.println(this.mapToCodeNameDTO(selected).getName());

                            this.getSelectedCurrentDetails().remove(i);

                        }
                    }
                }


            }
            System.out.println("------------------Ending check all----------------------------");

        } catch (Exception e) {

            System.out.println(e.toString());

        }


    }

    public void selectedCurrent(ActionEvent event) throws Exception {
        try {
            IClientDTO selected = 
                (IClientDTO)this.getCurrentDataTable().getRowData();

            if (selected.getChecked()) {


                boolean exist = false;
                for (IBasicDTO dto: this.getSelectedCurrentDetails()) {
                    if (dto.getName().equals(this.mapToCodeNameDTO(selected).getName()))
                        exist = true;
                }
                if (!exist) {
                    this.getSelectedCurrentDetails().add(this.mapToCodeNameDTO(selected));

                    System.out.println("adding...");
                    System.out.println(selected.getName());
                }
            }

            if (!(selected.getChecked())) {


                for (int i = 0; i < this.getSelectedCurrentDetails().size(); 
                     i++) {
                    IBasicDTO dto = this.getSelectedCurrentDetails().get(i);
                    if (dto.getName().equals(this.mapToCodeNameDTO(selected).getName())) {

                        this.getSelectedCurrentDetails().remove(i);
                        System.out.println("removing...");
                        System.out.println(selected.getName());

                    }
                }


            }


        } catch (Exception e) {
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)BeansUtil.getValue("error_dissplay");
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }

    public void delete() {
        customAvailableDetails = null;
        if (customCurrentDetails == null)
            customCurrentDetails = new ArrayList<TableInfo>();
        if (this.getSelectedCurrentDetails() != null && 
            this.getSelectedCurrentDetails().size() > 0) {

            for (IBasicDTO selected: this.getSelectedCurrentDetails()) {
                for (IModuleRelationsDTO m: 
                     (((TableInfo)selected).getModuleRelations())) {
                    if (m.getStatusFlag() == null) {
                        m.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                    } else {
                        moduleRelations.remove(m);
                    }
                }
                customCurrentDetails.remove(selected);
            }
        }

        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                         this.getCustomCurrentDetails());
        this.resetSelection();
    }

    //    public void delete(List selectedList) {
    //        customAvailableDetails = null;
    //        if (customCurrentDetails == null)
    //            customCurrentDetails = new ArrayList<TableInfo>();
    //        if (this.getSelectedCurrentDetails() != null && 
    //            this.getSelectedCurrentDetails().size() > 0) {
    //
    //            for (IBasicDTO selected: selectedList) {
    //                for (IModuleRelationsDTO m: 
    //                     (((TableInfo)selected).getModuleRelations())) {
    //                    if (m.getStatusFlag() == null) {
    //                        m.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
    //                    } else {
    //                        moduleRelations.remove(m);
    //                    }
    //                }
    //                customCurrentDetails.remove(selected);
    //            }
    //        }
    //
    //        // goFirstPage(this.getAvailableDataTable());
    //        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
    //                                         this.getCustomCurrentDetails());
    //        this.resetSelection();
    //    }

    private IModuleRelationsDTO findModuleRelationBySerial(Long serial) {
        for (IModuleRelationsDTO m: moduleRelations) {
            if (m.getTableName().equals(selectedTableKey) && 
                m.getTabrecSerialRef().equals(serial)) {
                return m;
            }
        }

        return null;
    }

    public void setSearchStatus(boolean searchStatus) {
        this.searchStatus = searchStatus;
    }

    public boolean isSearchStatus() {
        return searchStatus;
    }

    /**
     * Purpose: search method
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 27, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void setSearchMode() {

        if (getColumnNameSelected() != null && 
            !getColumnNameSelected().equals("") && 
            getColumnNamesValue() != null && 
            !getColumnNamesValue().equals("")) {
            setSearchStatus(true);
            getSearchColumnsDTOList().clear();
            ISearchColumnsDTO searchColumnsDTO = RegDTOFactory.createSearchColumnsDTO();
            searchColumnsDTO.setColumnName(getColumnNameSelected());
            searchColumnsDTO.setColumnValue(getColumnNamesValue());
            getSearchColumnsDTOList().add(searchColumnsDTO);
            customAvailableDetails = null;
            this.goFirstPage(this.getAvailableDataTable());
        }
    }

    public void preAdd() {
        super.preAdd();
        setSelectedModuleKey("");
        setSelectedTableKey("");
        setColumnNameSelected("");
        setColumnNamesValue("");
        setCustomAvailableDetails(null);
    }

    /**
     * Purpose: cancel method
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 27, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void cancelSearchMode() {
        setSearchStatus(false);
        customAvailableDetails = null;
        setColumnNameSelected("");
        setColumnNamesValue("");
        getSearchColumnsDTOList().clear();
    }


    public void setSearchColumnsDTOList(List<ISearchColumnsDTO> searchColumnsDTOList) {
        this.searchColumnsDTOList = searchColumnsDTOList;
    }

    public List<ISearchColumnsDTO> getSearchColumnsDTOList() {
        return searchColumnsDTOList;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNameSelected(String columnNameSelected) {
        this.columnNameSelected = columnNameSelected;
    }

    public String getColumnNameSelected() {
        return columnNameSelected;
    }

    public void setColumnNamesValue(String columnNamesValue) {
        this.columnNamesValue = columnNamesValue;
    }

    public String getColumnNamesValue() {
        return columnNamesValue;
    }

    public void setColumns(List<ISecSystemTabColumnsDTO> columns) {
        this.columns = columns;
    }

    public List<ISecSystemTabColumnsDTO> getColumns() {
        return columns;
    }

    public void getSelectedTabelActualData() {
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() != 0) {
            TableInfo tableInfo = 
                (TableInfo)getSelectedCurrentDetails().get(0);
            try {
                availableColumns = 
                        (List)SecClientFactory.getSecSystemTabColumnsClient().getSystemTablesColumns(tableInfo.getTableName());
                List<String> columnNames = new ArrayList<String>();
                for (ISecSystemTabColumnsDTO col: availableColumns) {
                    columnNames.add(col.getColumnName());
                }
                List<Long> tabrecSerials = new ArrayList<Long>();
                for (IModuleRelationsDTO col: tableInfo.getModuleRelations()) {
                    tabrecSerials.add(col.getTabrecSerialRef());
                }

                try {
                    actualDataList = 
                            RegClientFactory.getModuleRelationsClient().getNativeActualDataCenter(tableInfo.getTableName(), 
                                                                                                  columnNames, 
                                                                                                  tabrecSerials);
                    for (int i = 0; i < actualDataList.size(); i++) {
                        //if(((Vector)actualDataList.get(i)).get(4).equals("false"))
                        Vector data = (Vector)actualDataList.get(i);
                        totalSize = data.size();
                        data.set(totalSize-2, false);// for boolean checkbox binding

                    }
                } catch (DataBaseException e) {
                    actualDataList = 
                            RegClientFactory.getModuleRelationsClient().getNativeActualData(tableInfo.getTableName(), 
                                                                                            columnNames, 
                                                                                            tabrecSerials);
                }
                int i = 0;
                while (i < actualDataList.size()) {
                    Vector row = (Vector)actualDataList.get(i);
                    System.out.println(row.toArray());
                    i++;
                }

                buildAcualDataTableColumns(availableColumns);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public static ValueBinding createValueBinding(String bindingString) {
        return FacesContext.getCurrentInstance().getApplication().createValueBinding(bindingString);
    }

    public void buildAcualDataTableColumns(List<ISecSystemTabColumnsDTO> columns) {

        List tableColumns = getActualDataDataTable().getChildren();

        HtmlSelectBooleanCheckbox colValuecheckBox = 
            new HtmlSelectBooleanCheckbox();
            HtmlInputHidden hidden = new HtmlInputHidden();
            
        //UIAjaxSupport  uIAjaxSupport = new UIAjaxSupport();
        colValuecheckBox.setOnclick("submit();");
        //         
        colValuecheckBox.setId("chk2edit");
        colValuecheckBox.setValueBinding("forceId", 
                                         createValueBinding(forceIdExpression));
        MethodBinding mb = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{regulationModulesMaintainBean.selectedRows}", 
                                                                                   new Class[] { ValueChangeEvent.class });
        colValuecheckBox.setValueChangeListener(mb);
        String str1 = Integer.valueOf(totalSize-2).toString();
        String str2 = Integer.valueOf(totalSize-1).toString();
        ValueBinding vb1 = 
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{list["+str1+"]}");
        ValueBinding vb2 = 
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{list["+str2+"]}");

        
        colValuecheckBox.setValueBinding("value", vb1);
        hidden.setValueBinding("value",vb2);
        
        UIColumn checkcol = new UIColumn();
        UIOutput checHeader = new UIOutput();
        checHeader.setValue("");
        checkcol.setHeader(checHeader);
        checkcol.getChildren().add(colValuecheckBox);

        //tableColumns.add(checkcol);
        //        Object o = colValuecheckBox.getValueBinding("value");
        //        colValuebtn.setActionListener(mb);
        //colValuebtn.setStyleClass("cssButtonSmall");
        //colValuebtn.setValue("...");

        //check = (HtmlSelectBooleanCheckbox)((HtmlSimpleColumn)tableColumns.get(0)).getChildren();
        while (tableColumns.size() > 0) {
            tableColumns.remove(0);
        }
        tableColumns.add(checkcol);
        tableColumns.add(hidden);
        for (int i = 0; i < columns.size(); i++) {

            UIColumn col = new UIColumn();
            //UIOutput chckHeader = new UIOutput();
            //chckHeader.setValue("");
            //col.setHeader(chckHeader);
            //col.getChildren().add(chckHeader);
            //chckHeader.getChildren().add(colValuecheckBox);

            UIOutput colHeader = new UIOutput();
            colHeader.setValue(columns.get(i).getColumnDesc());
            //            ValueBinding vb1 = 
            //                BeansUtil.createValueBinding("list.checked");
            //            colValuecheckBox.setValueBinding("value",vb1);
            //getColumnName().add(columns.get(i));
            col.setHeader(colHeader);

            UIOutput colValue = new UIOutput();
            ValueBinding vb = 
                BeansUtil.createValueBinding(String.format("list[%s]", i));
            colValue.setValueBinding("value", vb);
            col.getChildren().add(colValue);

            //col.getChildren().add(colValuecheckBox);

            tableColumns.add(col);

            //tableColumns.add(colValuecheckBox);    
        }


    }

    public void selectedRows(ValueChangeEvent event) {

        Boolean flag = 
            (Boolean)((HtmlSelectBooleanCheckbox)event.getComponent()).getValue();
        System.out.println("_________________INSIDE       selectedRows______________________");
        if (flag && toBeDeleteedRows != null) {
            if (getActualDataDataTable().isRowAvailable()) {
                toBeDeleteedRows.add(getActualDataDataTable().getRowData());
                ((HtmlSelectBooleanCheckbox)event.getComponent()).setValue(true);

            } 
        }
        else if(!flag) {
                        toBeDeleteedRows.remove(getActualDataDataTable().getRowData());
                    }
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('jsfOkBtn').focus();");
        //        boolean checked =
        //            (Boolean)((Vector)getAvailableDataTable().getRowData()).get(0);
        //        if (checked) {
        //            selectedAvailableDetailsRowIndices.add((Integer)index);
        //        } else {
        //            selectedAvailableDetailsRowIndices.remove((Integer)index);
        //        }
    }

    public void deleteRecords() {
        TableInfo tableInfo = 
            (TableInfo)getSelectedCurrentDetails().get(0);
        IRegulationsDTO dto = (IRegulationsDTO)evaluateValueBinding("pageBeanName.pageDTO");
        List<IModuleRelationsDTO> keiestoBedeleted = new ArrayList<IModuleRelationsDTO>();
        Long regulationTypeCode = ((ITypesEntityKey)dto.getTypesDto().getCode()).getRegtypeCode();
        Long regulationYearCode = new Long(dto.getYearsDTOKey());
        Long regulationNumber = ((IRegulationsEntityKey)dto.getCode()).getRegulationNumber();
        
        
        
        
        
        
        
    try {
        
        if (((TableInfo)getSelectedCurrentDetails().get(0)).getModuleRelations().size() == 1)
            this.delete();
        else {
            for(int i=0;i<toBeDeleteedRows.size();i++) {
                BigDecimal longvalue = (BigDecimal)((Vector)toBeDeleteedRows.get(i)).get(totalSize-1);    
                
                IModuleRelationsDTO relationDto = RegDTOFactory.createModuleRelationsDTO();
                relationDto.setCode(RegEntityKeyFactory.createModuleRelationsEntityKey(regulationTypeCode,regulationYearCode,regulationNumber,Long.parseLong(longvalue.toString())));
                keiestoBedeleted.add(relationDto);
                
           }
           
           if(keiestoBedeleted.size() > 0)
                ((IModuleRelationsClient)getClient()).removeActualDataCenter(keiestoBedeleted);
        }
            
    }catch(DataBaseException dbe) {
        dbe.printStackTrace();
    }catch(SharedApplicationException e) {
        e.printStackTrace();
    }
        
        
        //updateTablesInfo(getCustomCurrentDetails(), newModuleRelations);
         getCustomCurrentDetails();

//        if (getActualDataList() != null)
//            getActualDataList().clear();
            
        getToBeDeleteedRows().clear();

    }

    public void hideDiv() {

        setJavaScriptCaller("hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null);setFocusAtMyFirstElement(); return false;");

        if (getActualDataList() != null)
            getActualDataList().clear();
        
        totalSize = 0;
        getToBeDeleteedRows().clear();
        //                    this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
        
        //                                  this.getCustomCurrentDetails());
        //                        this.resetSelection();
        //                        unCheck();
        //                    getCustomCurrentDetails();
    }

    public void setActualDataDataTable(HtmlDataTable actualDataDataTable) {
        this.actualDataDataTable = actualDataDataTable;
    }

    public HtmlDataTable getActualDataDataTable() {
        return actualDataDataTable;
    }

    public void setActualDataList(List<Vector<IClientDTO>> actualDataList) {
        this.actualDataList = actualDataList;
    }

    public List<Vector<IClientDTO>> getActualDataList() {
        return actualDataList;
    }

    public void setToBeDeleteedRows(List toBeDeleteedRows) {
        this.toBeDeleteedRows = toBeDeleteedRows;
    }

    public List getToBeDeleteedRows() {
        return toBeDeleteedRows;
    }

    public void setForceId(boolean forceId) {
        this.forceId = forceId;
    }

    public boolean isForceId() {
        return forceId;
    }

    public void setAvailableColumns(List<ISecSystemTabColumnsDTO> availableColumns) {
        this.availableColumns = availableColumns;
    }

    public List<ISecSystemTabColumnsDTO> getAvailableColumns() {
        return availableColumns;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalSize() {
        return totalSize;
    }
}
