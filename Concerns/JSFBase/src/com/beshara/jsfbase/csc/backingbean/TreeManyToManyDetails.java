package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public abstract class TreeManyToManyDetails extends ManyToManyBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private IBasicClient client;
    private IEntityKey masterEntityKey;
    protected List<IBasicDTO> availableDetails = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> currentDetails = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> currentDisplayDetails = new ArrayList<IBasicDTO>();
    private HtmlDataTable availableDataTable = new HtmlDataTable();
    private List<IBasicDTO> selectedAvailableDetails = 
        new ArrayList<IBasicDTO>();
    private List<IBasicDTO> selectedCurrentDetails = 
        new ArrayList<IBasicDTO>();
    private HtmlDataTable currentDataTable = new HtmlDataTable();
    private int currentListSize = 0;
    private int availableListSize;
    private List<IResultDTO> deleteStatusDTOS = new ArrayList<IResultDTO>();
    private Long deleted = ISystemConstant.DELEDTED_ITEM;
    private Long added = ISystemConstant.ADDED_ITEM;
    private String searchString;
    private int saveButtonOverride = 
        1; //1 if you won't override the button status //2 set it with false //3 set it to true
    private int finishButtonOverride = 1;
    private int selectedAvailableListSize = 0;
    private boolean checkAllFlagAvailable;
    private int pageIndexAdd = 0;
    private boolean enableNotLeaf = false;

    // new adeded part to handle add div as tree

    ////////////tree implementation
    protected TreeDivBean treedivBean;
    private String TreeTabManagedBeanName;

    public TreeManyToManyDetails() {

        // initTreeDivBean();

    }


    //this to search in available details

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
        //       setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");
        setSearchMode(true);
        this.removeCurrentFromAvailable();

        // this.restoreDetailsTablePosition(this.getAvailableDataTable(),availableDetails);
        this.goFirstPage(this.getAvailableDataTable());
    }


    //to handle selection in the available details

    public void selectedRadioButton(ValueChangeEvent event) throws Exception {
        if (event.getNewValue() != null) {
            setSelectedDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = 
                (IClientDTO)this.getAvailableDataTable().getRowData();
            this.getSelectedDTOS().add(selected);
        }
    }

    public void selectedAvailableDetails(ValueChangeEvent event) throws Exception {
        event = null; //unused

        //        try {
        //
        //
        //            String newValue = String.valueOf(event.getNewValue());
        //            String oldValue = String.valueOf(event.getOldValue());
        //            System.out.println("Row Index--------------" + 
        //                               this.getAvailableDataTable().getRowIndex());
        //            IBasicDTO selected = 
        //                (IBasicDTO)this.getAvailableDataTable().getRowData();
        //
        //            if (event.getNewValue() != null)
        //                if (!newValue.equals(oldValue)) {
        //                    if ("True".equalsIgnoreCase(newValue)) {
        //
        //
        //                        boolean exist = false;
        //                        for (IBasicDTO dto: 
        //                             this.getSelectedAvailableDetails()) {
        //                            if (dto.getCode().getKey().equals(selected.getCode().getKey()))
        //                                exist = true;
        //                        }
        //                        if (!exist) {
        //                            this.getSelectedAvailableDetails().add(selected);
        //
        //                            System.out.println("adding...");
        //                            System.out.println(selected.getName());
        //                        }
        //                    }
        //
        //                    if ("false".equalsIgnoreCase(newValue)) {
        //
        //
        //                        for (int i = 0; 
        //                             i < this.getSelectedAvailableDetails().size(); 
        //                             i++) {
        //
        //                            IBasicDTO dto = 
        //                                this.getSelectedAvailableDetails().get(i);
        //                            if (dto.getCode().getKey().equals(selected.getCode().getKey())) {
        //
        //                                this.getSelectedAvailableDetails().remove(i);
        //                                System.out.println("removing...");
        //                                System.out.println(selected.getName());
        //
        //                            }
        //                        }
        //
        //
        //                    }
        //
        //
        //                }
        //
        //
        //        } catch (Exception e) {
        //            ErrorDisplay errorDisplay = 
        //                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
        //            errorDisplay.setErrorMsgKey(e.getMessage());
        //            errorDisplay.setSystemException(true);
        //            throw new Exception();
        //
        //        }


    }


    //to handle selection in the current details

    public void selectedCurrentDetails(ValueChangeEvent event) throws Exception {
        event = null; //unused

        //        try {
        //
        //
        //            String newValue = String.valueOf(event.getNewValue());
        //            String oldValue = String.valueOf(event.getOldValue());
        //            System.out.println("Row Index--------------" + 
        //                               this.getCurrentDataTable().getRowIndex());
        //            IBasicDTO selected = 
        //                (IBasicDTO)this.getCurrentDataTable().getRowData();
        //
        //            if (event.getNewValue() != null)
        //                if (!newValue.equals(oldValue)) {
        //                    if ("True".equalsIgnoreCase(newValue)) {
        //
        //
        //                        boolean exist = false;
        //                        for (IBasicDTO dto: this.getSelectedCurrentDetails()) {
        //
        //
        //                            if (dto.getCode().getKey().equals(this.mapToCodeNameDTO(selected).getCode().getKey()))
        //                                exist = true;
        //
        //                        }
        //                        if (!exist) {
        //                            this.getSelectedCurrentDetails().add(this.mapToCodeNameDTO(selected));
        //
        //                            System.out.println("adding...");
        //                            System.out.println(selected.getName());
        //                        }
        //                    }
        //
        //                    if ("false".equalsIgnoreCase(newValue)) {
        //
        //
        //                        for (int i = 0; 
        //                             i < this.getSelectedCurrentDetails().size(); 
        //                             i++) {
        //
        //                            IBasicDTO dto = 
        //                                this.getSelectedCurrentDetails().get(i);
        //
        //                            if (dto.getCode().getKey().equals(this.mapToCodeNameDTO(selected).getCode().getKey())) {
        //
        //                                this.getSelectedCurrentDetails().remove(i);
        //                                System.out.println("removing...");
        //                                System.out.println(selected.getName());
        //
        //                            }
        //                        }
        //
        //
        //                    }
        //
        //
        //                }
        //
        //
        //        } catch (Exception e) {
        //            ErrorDisplay errorDisplay = 
        //                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
        //            errorDisplay.setErrorMsgKey(e.getMessage());
        //            errorDisplay.setSystemException(true);
        //            throw new Exception();
        //
        //        }


    }

    //this function to delete from the details

    public IBasicDTO getCurrentSelectedDetail(IBasicDTO selected) {
        for (IBasicDTO dto: currentDetails) {
            if (selected.getCode().getKey().equals(this.mapToCodeNameDTO(dto).getCode().getKey())) {
                return dto;
                //break;
            }
        }
        return null;
    }

    /**
     * Purpose: this method created to delete the selected items from current details (if statusflag=0 this item will be deleted from the list)
     * if statusflag=null this item will be marked for delete by setting statusflag=1  the item deleted or marked for delete will be added to the available details list
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void delete() {
        if (currentDetails == null)
            currentDetails = new ArrayList<IBasicDTO>();
        if (selectedCurrentDetails != null && 
            selectedCurrentDetails.size() > 0) {


            for (int i = 0; i < selectedCurrentDetails.size(); i++) {
                IBasicDTO selected = selectedCurrentDetails.get(i);
                if (getCurrentSelectedDetail(selected).getStatusFlag() == 
                    null) {
                    getCurrentSelectedDetail(selected).setStatusFlag(deleted);
                    //((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    selectedCurrentDetails.remove(i);
                    i--;
                }
                if (getCurrentSelectedDetail(selected).getStatusFlag().longValue() == 
                    added.longValue()) {
                    currentDetails.remove(getCurrentSelectedDetail(selected));
                    //bugs
                    // ((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    availableDetails.add(selected);

                    selectedCurrentDetails.remove(i);
                    i--;
                }

            }


        }
        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                         this.getCurrentDetails());
        this.resetSelection();

        if (treedivBean.isSearchMode())
            this.cancelSearchAvailable();

    }


    public void resetSelection() {

        for (int j = 0; j < currentDetails.size(); j++) {
            IClientDTO current = (IClientDTO)currentDetails.get(j);
            ((IClientDTO)this.mapToCodeNameDTO(current)).setChecked(false);
			setCheckAllFlag(false);
            current.setChecked(false);
        }

        for (int j = 0; j < availableDetails.size(); j++) {
            IClientDTO current = (IClientDTO)availableDetails.get(j);
            current.setChecked(false);
        }
        if (selectedCurrentDetails != null)
            selectedCurrentDetails.clear();
        if (selectedAvailableDetails != null)
            selectedAvailableDetails.clear();


    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
//        System.out.println(dto);
        return null;

    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
//        System.out.println(dto);
        return null;
    }

    /**
     * Purpose: this method will call the business method which will return the items that haven't been saved under the current master  
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void getListAvailable() throws DataBaseException, 
                                          SharedApplicationException {

    }

    //this method to filter the available details and clear those added by user before saving to the database

    /**
     * Purpose: this method created to filter the available details and clear those added by user before saving to the database
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void removeCurrentFromAvailable() {

//        System.out.println("removing current");
        if (currentDetails != null && availableDetails != null)
            for (int j = 0; j < currentDetails.size(); j++) {
                IBasicDTO dto = currentDetails.get(j);
                IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);

                boolean exist = false;
                for (int i = 0; i < availableDetails.size(); i++) {
                    IBasicDTO availableDTO = availableDetails.get(i);

                    if (codeNameDTO.getCode().getKey().equals(availableDTO.getCode().getKey())) {
                        exist = true;
                        if (dto.getStatusFlag() != null && 
                            dto.getStatusFlag().longValue() != 
                            deleted.longValue()) {
                            availableDetails.remove(availableDTO);

                        }

                        if (dto.getStatusFlag() == null) {

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

    public void setAvailableDetails(List<IBasicDTO> availableDetails) {
        this.availableDetails = availableDetails;
    }

    /**
     * Purpose: the getter of the availabledetails list (calling getlistavailable and removecurrentfromavailable) 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
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

        if (!treedivBean.isSearchMode())
            rebuildTree();

        return availableDetails;
    }

    public boolean validateStep(String stepKey) {
//        System.out.println("From the validate ------------------------" + 
//                           stepKey);
        return true;
    }


    public void setCurrentDetails(List<IBasicDTO> currentDetails) {
//        if (currentDetails != null) {
//            System.out.println("from the detail in the setter ");
//            System.out.println(currentDetails.size());
//        }
        this.currentDetails = currentDetails;
    }

    public List<IBasicDTO> getCurrentDetails() {
        return currentDetails;
    }

    public void setAvailableDataTable(HtmlDataTable availableDataTable) {
        this.availableDataTable = availableDataTable;
    }

    public HtmlDataTable getAvailableDataTable() {
        return availableDataTable;
    }

    public void setSelectedAvailableDetails(List<IBasicDTO> selectedAvailableDetails) {
        this.selectedAvailableDetails = selectedAvailableDetails;
    }

    public List<IBasicDTO> getSelectedAvailableDetails() {
        return selectedAvailableDetails;
    }

    public void setSelectedCurrentDetails(List<IBasicDTO> selectedCurrentDetails) {
        this.selectedCurrentDetails = selectedCurrentDetails;
    }

    public List<IBasicDTO> getSelectedCurrentDetails() {
        return selectedCurrentDetails;
    }

    public void setCurrentDataTable(HtmlDataTable currentDataTable) {
        this.currentDataTable = currentDataTable;
    }

    public HtmlDataTable getCurrentDataTable() {
        return currentDataTable;
    }

    public void setCurrentListSize(int listSize) {

        this.currentListSize = listSize;
    }

    public int getCurrentListSize() {

        if (this.getCurrentDisplayDetails() != null) {

            currentListSize = this.getCurrentDisplayDetails().size();

        }

        return currentListSize;
    }

    public void setDeleteStatusDTOS(List<IResultDTO> deleteStatusDTOS) {
        this.deleteStatusDTOS = deleteStatusDTOS;
    }

    public List<IResultDTO> getDeleteStatusDTOS() {
        return deleteStatusDTOS;
    }

    public void setClient(IBasicClient client) {
        this.client = client;
    }

    public IBasicClient getClient() {
        return client;
    }

    public void setMasterEntityKey(IEntityKey masterEntityKey) {
        this.masterEntityKey = masterEntityKey;
    }

    public IEntityKey getMasterEntityKey() {
        return masterEntityKey;
    }

    public void setAvailableListSize(int availableListSize) {
        this.availableListSize = availableListSize;
    }

    public int getAvailableListSize() {
        if (availableDetails != null) {
            try {
                availableListSize = this.getAvailableDetails().size();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return availableListSize;
    }

    public void setCurrentDisplayDetails(List<IBasicDTO> currentDisplayDetails) {
        this.currentDisplayDetails = currentDisplayDetails;
    }

    public List<IBasicDTO> getCurrentDisplayDetails() {
        currentDisplayDetails = new ArrayList<IBasicDTO>(0);
        for (IBasicDTO dto: currentDetails) {
            if (dto.getStatusFlag() == null)
                currentDisplayDetails.add(dto);
            if (dto.getStatusFlag() != null && 
                dto.getStatusFlag().longValue() == added.longValue())
                currentDisplayDetails.add(dto);
        }
        return currentDisplayDetails;

    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSaveButtonOverride(int saveButtonOverride) {
        this.saveButtonOverride = saveButtonOverride;
    }

    public int getSaveButtonOverride() {
        return saveButtonOverride;
    }

    public void setFinishButtonOverride(int finishButtonOverride) {
        this.finishButtonOverride = finishButtonOverride;
    }

    public int getFinishButtonOverride() {
        return finishButtonOverride;
    }


    public void selectedAvailableAll(ActionEvent event) throws Exception {
        event = null; //unused

        try {

            Set s = new HashSet();
            s.addAll(this.getSelectedAvailableDetails());

            Integer rowsCountPerPage = 
                (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());

            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }

            int first = this.getAvailableDataTable().getFirst();
            for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {
                this.getAvailableDataTable().setRowIndex(j);
                if (!this.getAvailableDataTable().isRowAvailable())
                    break;
                IBasicDTO selected = 
                    (IBasicDTO)this.getAvailableDataTable().getRowData();

                if (checkAllFlagAvailable == true) {
                    ((IClientDTO)selected).setChecked(true);
                    s.add(selected);

                }


                if (checkAllFlagAvailable == false) {
                    ((IClientDTO)selected).setChecked(false);
                    s.remove(selected);


                }


            }
            this.getSelectedAvailableDetails().clear();
            this.getSelectedAvailableDetails().addAll(s);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module

    public void selectedAvailable(ActionEvent event) throws Exception {
        event = null; //unused

        try {

            Set s = new HashSet();
            s.addAll(this.getSelectedAvailableDetails());

            IClientDTO selected = 
                (IClientDTO)this.getAvailableDataTable().getRowData();

            if (selected.getChecked()) {


                try {
                    s.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            if (!(selected.getChecked())) {


                selected.setChecked(Boolean.TRUE);
                s.remove(selected);
                selected.setChecked(Boolean.FALSE);


            }

            this.getSelectedAvailableDetails().clear();
            this.getSelectedAvailableDetails().addAll(s);


        } catch (Exception e) {
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }

    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module

    public void selectedCurrentAll(ActionEvent event) throws Exception {
        event = null; //unused

        try {

            Set s = new HashSet();
            s.addAll(this.getSelectedCurrentDetails());


            int first = this.getCurrentDataTable().getFirst();

            //Integer rowsCountPerPage=(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());
            Integer rowsCountPerPage = getCurrentDataTable().getRowCount();
            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int count = rowsCountPerPage.intValue();
            for (int j = first; j < first + count; j++) {
                this.getCurrentDataTable().setRowIndex(j);
                if (!this.getCurrentDataTable().isRowAvailable())
                    break;

                IBasicDTO selected = 
                    (IBasicDTO)this.getCurrentDataTable().getRowData();

                if (isCheckAllFlag() == true) {
                    s.add(this.mapToCodeNameDTO(selected));

                }


                if (isCheckAllFlag() == false) {
                    s.remove(this.mapToCodeNameDTO(selected));
                }


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {

            System.out.println(e.toString());

        }


    }

    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module

    public void selectedCurrent(ActionEvent event) throws Exception {
        event = null; //unused

        try {

            Set s = new HashSet();
            s.addAll(this.getSelectedCurrentDetails());


            IClientDTO selected = 
                (IClientDTO)this.getCurrentDataTable().getRowData();


            if (selected.getChecked()) {


                try {
                    s.add(this.mapToCodeNameDTO(selected));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            if (!(selected.getChecked())) {


                s.remove(this.mapToCodeNameDTO(selected));


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }


    public int getSelectedListSize() {
        if (selectedCurrentDetails != null)
            return selectedCurrentDetails.size();
        return 0;


    }


    //QCBug the deleted details added with old data

    public void resetDetailDTO(IBasicDTO dto) {
//        System.out.println(dto);
    }

    public void setSelectedAvailableListSize(int selectedAvailableListSize) {
        this.selectedAvailableListSize = selectedAvailableListSize;
    }

    public int getSelectedAvailableListSize() throws DataBaseException, 
                                                     SharedApplicationException {
        if (getSelectedAvailableDetails() != null) {
            return getSelectedAvailableDetails().size();
        }
        return selectedAvailableListSize;
    }

    public void setCheckAllFlagAvailable(boolean checkAllFlagAvailable) {
        this.checkAllFlagAvailable = checkAllFlagAvailable;
    }

    public boolean isCheckAllFlagAvailable() {
        return checkAllFlagAvailable;
    }

    //resetting and uupdating table position after database operations

    public void restoreDetailsTablePosition(HtmlDataTable dataTable, 
                                            List list) {

        try {
            int beforeIndex = dataTable.getFirst();
            int afterIndex = beforeIndex - dataTable.getRows();
            if (beforeIndex != 0 && list.size() % beforeIndex == 0) {
                if (afterIndex > 0) {
                    dataTable.setFirst(afterIndex);
                } else {
                    dataTable.setFirst(0);
                }
            }
        } catch (Exception e) {
            dataTable.setFirst(0);
        }

    }

    public void getDetailsPageIndex(HtmlDataTable dataTable, List list, 
                                    String key) throws DataBaseException {
        int noOfPages;
        int rows = getSharedUtil().getNoOfTableRows();

        if (list != null && dataTable != null) {
            noOfPages = ((list.size() - 2) / rows) + 1;
            // int index = newList.size()-1;
            int index = 0;
            for (int b = 0; b < list.size(); b++) {
                IBasicDTO basicDTO = (IBasicDTO)list.get(b);
                if (basicDTO.getCode().getKey().equals(key)) {
                    index = b;
                }
            }
            for (int i = 0; i < noOfPages; i++) {
                if (index == rows * i) {
                    dataTable.setFirst(rows * i);
                } else if (index == (rows * (i + 1))) {
                    dataTable.setFirst(rows * (i + 1));
                } else if (index > rows * i && index < rows * (i + 1)) {
                    dataTable.setFirst(rows * i);
                }
            }
        }
    }


    public void setPageIndexAdd(Integer pageIndexAdd) {
        this.pageIndexAdd = pageIndexAdd;
    }

    public Integer getPageIndexAdd() {
        return pageIndexAdd;
    }

    public void goFirstPage(HtmlDataTable table) {
        if (table != null)
            table.setFirst(0);

    }

    public boolean validate() {
//        System.out.println("from validate------");
        this.unCheck();
        return true;
    }

    public boolean validateTarget(String targetStep) {

//        System.out.println("from validateTarget------" + targetStep);

        return true;

    }

    public String getLocalizedMessage(String bundleBase, String bundleKey) {
        try {
            PropertyResourceBundle p = 
                (PropertyResourceBundle)ResourceBundle.getBundle(bundleBase, 
                                                                 this.getLocale());
            System.out.print(this.getLocale().getLanguage());
            return p.getString(bundleKey);

        } catch (Exception e) {
            ;
        }

        return "";

    }

    public Locale getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();

    }

    public boolean containSubString(String dtoName) {

        String searchQuery = searchString;
        if (searchQuery.startsWith("%") && searchQuery.endsWith("%")) {

            String formattedQuery = 
                searchQuery.substring(1, searchQuery.length() - 1);
            if (dtoName.contains(formattedQuery) || 
                dtoName.equals(formattedQuery))
                return true;


        } else if (searchQuery.endsWith("%")) {

            String formattedQuery = 
                searchQuery.substring(0, searchQuery.length() - 1);
            if (dtoName.endsWith(formattedQuery))
                return true;

        } else if (searchQuery.startsWith("%")) {

            String formattedQuery = 
                searchQuery.substring(1, searchQuery.length());
            if (dtoName.startsWith(formattedQuery))
                return true;

        } else {
            if (dtoName.contains(searchQuery) || dtoName.equals(searchQuery))
                return true;


        }


        return false;

    }


    public void setFrm(HtmlForm frm) {

        this.frm = frm;
    }

    public HtmlForm getFrm() {
        if (this.appMainLayoutBuilder() != null)
            setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
        this.initiateBeanOnce();
        return frm;
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        return app;
    }

    /**
     * Purpose:Action Method of Uncheck records.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void unCheck() {
        for (int i = 0; i < getCurrentDisplayDetails().size(); i++) {
            if (((IClientDTO)getCurrentDisplayDetails().get(i)).getChecked() != 
                null && 
                ((IClientDTO)getCurrentDisplayDetails().get(i)).getChecked().booleanValue()) {
                ((IClientDTO)getCurrentDisplayDetails().get(i)).setChecked(Boolean.valueOf(false));
            }
        }

        if (getSelectedCurrentDetails() != null) {
            getSelectedCurrentDetails().clear();
        }
    }


    //======================================Start Part of Tree Handling operation ===========================================================

    /**
     * Purpose: this method created to load user customized setting of the tree div (should be implemented by use)
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public abstract void initializeTreeDiv();

    /**
     * Purpose:
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   23-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void initTreeDivBean() {

        if (TreeTabManagedBeanName != null) {

            treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");

            // to avoid getall by the tree client
            treedivBean.setMyTableData(new ArrayList());
            //the add button in the div will invoke manytomany add function
            MethodBinding mb = 
                evaluateMethodBinding(TreeTabManagedBeanName + ".add", null);
            treedivBean.getOkCommandButton().setAction(mb);
            treedivBean.getOkCommandButton().setReRender("dataT_data_panel,searchText");

            if (!enableNotLeaf) {
                ValueBinding vb = 
                    FacesContext.getCurrentInstance().getApplication().createValueBinding("#{treeDivBean.enabledNotLeaf}");
                treedivBean.getOkCommandButton().setValueBinding("rendered", 
                                                                 vb);
            }
            //loading customized initialization so if you extends this class you must overrid following method 
            initializeTreeDiv();
        }

    }


    /**
     * Purpose:rebuild the div tree after each operation(add/delete) to generate el tree after filtering using availbleDetails list 
     * Creation/Modification History :
     * 1.1 - Developer Name: Aboelhassan Hamed
     * 1.2 - Date:   26-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    private void rebuildTree() {
        treedivBean.setMyTableData(this.availableDetails);
        treedivBean.setTreeNodeBase(null);
        treedivBean.setShowTreeContent(true);
    }

    /**
     * Purpose:called when user press add button to add node ,rerender the div and open it.
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   6/8/2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void openTreeDiv() throws DataBaseException {
        //cancel search button will call our cancel search available 
        MethodBinding cancelMethodBinding = 
            evaluateMethodBinding(TreeTabManagedBeanName + 
                                  ".cancelSearchAvailable", null);
        treedivBean.getCancelSearchCommandButton().setAction(cancelMethodBinding);

    }


    /**
     * Purpose:called when user press cancel search @ tree add div 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   20-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void cancelSearchAvailable() {
        rebuildTree();
        treedivBean.setSearchQuery("");
        treedivBean.setSearchMode(false);

    }


    /**
     * Purpose: this method created to add the selected node from the tree add div  to the currentdetails list
     * this method will search the  selected node in the available details and 
     * if the item already exist in the currentdetails list and marked for delete its status will be set to null else the item will be added to the currentdetails list
     * now it handle only adding leaf node not parent node 
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void add() {

        // added for tree operation
        if (getSelectedAvailableDetails() == null)
            setSelectedAvailableDetails(new ArrayList<IBasicDTO>(0));

        if (!enableNotLeaf) {

            if (getSelectedAvailableDetails() != null && 
                getSelectedAvailableDetails().size() > 0)
                getSelectedAvailableDetails().clear();

            if (treedivBean.getDtoDetails() != null && 
                treedivBean.getDtoDetails().getCode() != null)
                getSelectedAvailableDetails().add(treedivBean.getDtoDetails());


        }
        // if selected node is parent has childern 
        else {
            //TODO

        }
        // filtering part of selected node 
        if (selectedAvailableDetails != null)
            for (int i = 0; i < selectedAvailableDetails.size(); i++) {
                IBasicDTO dto = selectedAvailableDetails.get(i);

                boolean exist = false;

                for (int j = 0; j < currentDetails.size(); j++) {
                    IBasicDTO current = currentDetails.get(j);
                    IBasicDTO mappedCurrent = this.mapToCodeNameDTO(current);

                    if (mappedCurrent.getCode().getKey().equals(dto.getCode().getKey())) {

                        exist = true;
                        if (current.getStatusFlag() != null && 
                            current.getStatusFlag().longValue() == 
                            deleted.longValue()) {
                            current.setStatusFlag(null);
                            this.resetDetailDTO(current);
                            availableDetails.remove(dto);

                            selectedAvailableDetails.remove(i);
                            i--;
                        }
                    }
//                    System.out.println("add inner loop");
                }

                if (!exist) {

                    IBasicDTO mdto = this.mapToDetailDTO(dto);
                    mdto.setStatusFlag(added);

                    currentDetails.add(mdto);
                    availableDetails.remove(dto);

                    selectedAvailableDetails.remove(i);
                    i--;
                }
//                System.out.println("add end");

            }
        this.resetSelection();

        if (treedivBean.isSearchMode())
            this.cancelSearchAvailable();

    }


    public void setTreeTabManagedBeanName(String managedBeanName) {
        this.TreeTabManagedBeanName = managedBeanName;
    }

    public String getTreeTabManagedBeanName() {
        return TreeTabManagedBeanName;
    }


    public void setEnableNotLeaf(boolean enableNotLeaf) {
        this.enableNotLeaf = enableNotLeaf;
    }

    public boolean isEnableNotLeaf() {
        return enableNotLeaf;
    }

    //======================================End Part of Tree Handling operation ===========================================================

}
