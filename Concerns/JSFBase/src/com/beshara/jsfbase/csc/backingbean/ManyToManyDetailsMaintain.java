package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.paging.PagingBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.column.HtmlSimpleColumn;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class ManyToManyDetailsMaintain extends ManyToManyBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    //  private IBasicClient client;
    private IEntityKey masterEntityKey;
    protected List<IBasicDTO> availableDetails = new ArrayList<IBasicDTO>();
    protected List<IBasicDTO> currentDetails = new ArrayList<IBasicDTO>();
    protected List<IBasicDTO> currentDisplayDetails = new ArrayList<IBasicDTO>();
    private transient HtmlDataTable availableDataTable = new HtmlDataTable();
    private List<IBasicDTO> selectedAvailableDetails = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> selectedCurrentDetails = new ArrayList<IBasicDTO>();
    private transient HtmlDataTable currentDataTable = new HtmlDataTable();
    private int currentListSize = 0;
    private int availableListSize;
    private List<IResultDTO> deleteStatusDTOS = new ArrayList<IResultDTO>();
    protected Long deleted = ISystemConstant.DELEDTED_ITEM;
    protected Long added = ISystemConstant.ADDED_ITEM;
    private String searchString;
    private int saveButtonOverride =
        1; //1 if you won't override the button status //2 set it with false //3 set it to true
    private int finishButtonOverride = 1;
    private int selectedAvailableListSize = 0;
    private boolean checkAllFlagAvailable;
    private int pageIndexAdd = 0;
    private transient HtmlDataScroller dataScroller = new HtmlDataScroller();

    public ManyToManyDetailsMaintain() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);
        setLookupAddDiv("m2mAddDivClass");
        setLookupEditDiv("m2mEditDivClass");
    }


    //this to search in available details

    /**
     * Purpose:
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  3-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: Modification
     */
    public void searchAvailable() throws DataBaseException, SharedApplicationException {
        //       setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");
        setSearchMode(true);
        this.removeCurrentFromAvailable();

        // this.restoreDetailsTablePosition(this.getAvailableDataTable(),availableDetails);
        this.goFirstPage(this.getAvailableDataTable());


        // added clear seleted dtos and to make check all box uncheck
        if (getSelectedAvailableDetails() != null && getSelectedAvailableDetails().size() > 0)
            getSelectedAvailableDetails().clear();

        setCheckAllFlagAvailable(false);
    }


    public void showHideColumn(String columnName) {
        HtmlSimpleColumn column = (HtmlSimpleColumn)getCurrentDataTable().findComponent(columnName);
        if (column.isRendered()) {
            column.setRendered(false);
        } else {
            column.setRendered(true);
        }
    }

    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
        this.getListAvailable();
        setSearchMode(false);
        setSearchString("");
        this.removeCurrentFromAvailable();

        // added clear seleted dtos and to make check all box uncheck
        if (getSelectedAvailableDetails() != null && getSelectedAvailableDetails().size() > 0)
            getSelectedAvailableDetails().clear();

        setCheckAllFlagAvailable(false);
        this.goFirstPage(this.getAvailableDataTable());
    }


    //to handle selection in the available details

    public void selectedRadioButton(ValueChangeEvent event) throws Exception {
        if (event.getNewValue() != null) {
            setSelectedDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = (IClientDTO)this.getAvailableDataTable().getRowData();
            this.getSelectedDTOS().add(selected);
        }
    }

    public void selectedAvailableDetails(ValueChangeEvent event) throws Exception {
        event = null; // unused
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
        event = null; // unused
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
        try {
            for (IBasicDTO dto : currentDetails) {
                if (selected.getCode().getKey().equals(this.mapToCodeNameDTO(dto).getCode().getKey())) {
                    return dto;
                    //break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (selectedCurrentDetails != null && selectedCurrentDetails.size() > 0) {


            for (int i = 0; i < selectedCurrentDetails.size(); i++) {
                IBasicDTO selected = selectedCurrentDetails.get(i);
                if (getCurrentSelectedDetail(selected).getStatusFlag() == null) {
                    getCurrentSelectedDetail(selected).setStatusFlag(deleted);
                    //((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    selectedCurrentDetails.remove(i);
                    i--;
                }
                if (getCurrentSelectedDetail(selected).getStatusFlag().longValue() == added.longValue()) {
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
        this.restoreDetailsTablePosition(this.getCurrentDataTable(), this.getCurrentDetails());
        this.resetSelection();
    }


    /**
     * Purpose: this method created to add the selected items from the availabledetails list to the currentdetails list
     * this method will loop the seleted items in the available details and
     * if the item already exist in the currentdetails list and marked for delete its status will be set to null else the item will be added to the currentdetails list
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */

    public void add() {

        if (currentDetails == null)
            currentDetails = new ArrayList<IBasicDTO>();
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
                            current.getStatusFlag().longValue() == deleted.longValue()) {
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
        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getAvailableDataTable(), availableDetails);
        this.resetSelection();

        try {
            this.cancelSearchAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void resetSelection() {

        for (int j = 0; j < currentDetails.size(); j++) {
            IClientDTO current = (IClientDTO)currentDetails.get(j);
            ((IClientDTO)this.mapToCodeNameDTO(current)).setChecked(false);
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

        checkAllFlagAvailable = false;
        setCheckAllFlag(false);
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        dto = null;
        return dto;

    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        dto = null;
        return dto;

    }

    /**
     * Purpose: this method will call the business method which will return the items that haven't been saved under the current master
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void getListAvailable() throws DataBaseException, SharedApplicationException {

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
                        if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() != deleted.longValue()) {
                            availableDetails.remove(availableDTO);
                        }

                        if (dto.getStatusFlag() == null) {

                            availableDetails.remove(availableDTO);
                        }


                    }

                }

                if (!exist && dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == deleted.longValue()) {

                    if (isSearchMode() == true && containSubString(codeNameDTO.getName()))
                        availableDetails.add(codeNameDTO);
                    else if (isSearchMode() == false)
                        availableDetails.add(codeNameDTO);
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
    public List<IBasicDTO> getAvailableDetails() throws DataBaseException, SharedApplicationException {

        //if null get it from the database
        if ((availableDetails == null || availableDetails.size() == 0) && isSearchMode() == false) {
            try {
                this.getListAvailable();
            } //user override to get avilable list from the databse
            catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList<IBasicDTO>(0));
            }
        }

        //after getting from db remove previously selected items from this list
        removeCurrentFromAvailable();
        if (availableDetails == null)
            availableDetails = new ArrayList<IBasicDTO>(0);
        return availableDetails;
    }

    public boolean validateStep(String stepKey) {
//        System.out.println("From the validate -------------- " + stepKey);
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
        if (isUsingPaging()) {

            PagingBean pagingBean = getPagingBean();

            try {
                if (pagingBean.getMyPagedDataModel() == null) {

                    pagingBean.getMyPagedDataModel();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            currentListSize = pagingBean.getTotalListSize();

        } else {
            if (this.getCurrentDisplayDetails() != null) {
                currentListSize = this.getCurrentDisplayDetails().size();
            }
        }

        return currentListSize;
    }

    public void setDeleteStatusDTOS(List<IResultDTO> deleteStatusDTOS) {
        this.deleteStatusDTOS = deleteStatusDTOS;
    }

    public List<IResultDTO> getDeleteStatusDTOS() {
        return deleteStatusDTOS;
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
            } catch (Exception e) {
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
        if (isSaveSortingState() && !isSortedTable()) {
            sort(getSortColumn(), sortAscending);
        }
        if (currentDetails != null) {
            for (IBasicDTO dto : currentDetails) {
                if (dto.getStatusFlag() == null)
                    currentDisplayDetails.add(dto);
                if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == added.longValue())
                    currentDisplayDetails.add(dto);
            }
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


    //    public void selectedAvailableAll(ActionEvent event) throws Exception {
    //        try {
    //            System.out.println(isCheckAllFlag() + " ");
    //            int first = this.getAvailableDataTable().getFirst();
    //            for (int j = first; j < first + 8; j++) {
    //                this.getAvailableDataTable().setRowIndex(j);
    //                System.out.println(" " +
    //                                   this.getAvailableDataTable().getRowData());
    //                IBasicDTO selected =
    //                    (IBasicDTO)this.getAvailableDataTable().getRowData();
    //                System.out.println(selected.getName());
    //
    //                if (checkAllFlagAvailable == true) {
    //                    boolean exist = false;
    //                    for (IBasicDTO dto: this.getSelectedAvailableDetails()) {
    //                        if (dto.getCode().getKey().equals(selected.getCode().getKey()))
    //                            exist = true;
    //                    }
    //                    if (!exist) {
    //                        this.getSelectedAvailableDetails().add(selected);
    //
    //                        System.out.println("adding...");
    //                        System.out.println(selected.getName());
    //                    }
    //                }
    //
    //
    //                if (checkAllFlagAvailable == false) {
    //                    for (int i = 0;
    //                         i < this.getSelectedAvailableDetails().size(); i++) {
    //
    //                        IBasicDTO dto =
    //                            this.getSelectedAvailableDetails().get(i);
    //                        if (dto.getCode().getKey().equals(selected.getCode().getKey())) {
    //
    //                            this.getSelectedAvailableDetails().remove(i);
    //                            System.out.println("removing...");
    //                            System.out.println(selected.getName());
    //
    //                        }
    //                    }
    //                }
    //
    //
    //            }
    //
    //
    //        } catch (Exception e) {
    //        }
    //
    //
    //    }
    //
    //
    //    public void selectedAvailable(ActionEvent event) throws Exception {
    //        //       System.out.println("only one time");
    //        //     System.out.println("semo");
    //        try {
    //            IClientDTO selected =
    //                (IClientDTO)this.getAvailableDataTable().getRowData();
    //
    //            if (selected.getChecked()) {
    //
    //
    //                boolean exist = false;
    //                for (IBasicDTO dto: this.getSelectedAvailableDetails()) {
    //                    if (dto.getCode().getKey().equals(selected.getCode().getKey()))
    //                        exist = true;
    //                }
    //                if (!exist) {
    //                    this.getSelectedAvailableDetails().add(selected);
    //
    //                    System.out.println("adding...");
    //                    System.out.println(selected.getName());
    //                }
    //            }
    //
    //            if (!(selected.getChecked())) {
    //
    //
    //                for (int i = 0; i < this.getSelectedAvailableDetails().size();
    //                     i++) {
    //
    //                    IBasicDTO dto = this.getSelectedAvailableDetails().get(i);
    //                    if (dto.getCode().getKey().equals(selected.getCode().getKey())) {
    //
    //                        this.getSelectedAvailableDetails().remove(i);
    //                        System.out.println("removing...");
    //                        System.out.println(selected.getName());
    //
    //                    }
    //                }
    //
    //
    //            }
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
    //
    //
    //    }
    //
    //
    //    public void selectedCurrentAll(ActionEvent event) throws Exception {
    //        try {
    //
    //            System.out.println("------------------starting check all----------------------------");
    //            System.out.println("checkallflag=" + isCheckAllFlag());
    //            int first = this.getCurrentDataTable().getFirst();
    //            int rowsCount = 0;
    //            if (this.getCurrentDisplayDetails() != null)
    //                rowsCount =
    //                        this.getCurrentDisplayDetails().size(); //.getRowCount();
    //            //            int count = 8;
    //            //            if (rowsCount < 8)
    //            int count = rowsCount;
    //            for (int j = first; j < first + count; j++) {
    //                this.getCurrentDataTable().setRowIndex(j);
    //                System.out.println(" " +
    //                                   this.getCurrentDataTable().getRowData());
    //                IBasicDTO selected =
    //                    (IBasicDTO)this.getCurrentDataTable().getRowData();
    //                System.out.println(selected.getName());
    //
    //                if (isCheckAllFlag() == true) {
    //                    boolean exist = false;
    //                    for (IBasicDTO dto: this.getSelectedCurrentDetails()) {
    //                        if (dto.getCode().getKey().equals(this.mapToCodeNameDTO(selected).getCode().getKey()))
    //                            exist = true;
    //                    }
    //                    if (!exist) {
    //                        this.getSelectedCurrentDetails().add(this.mapToCodeNameDTO(selected));
    //
    //                        System.out.println("adding...");
    //                        System.out.println(this.mapToCodeNameDTO(selected).getName());
    //                    }
    //                }
    //
    //
    //                if (isCheckAllFlag() == false) {
    //                    for (int i = 0;
    //                         i < this.getSelectedCurrentDetails().size(); i++) {
    //
    //                        IBasicDTO dto = this.getSelectedCurrentDetails().get(i);
    //                        if (dto.getCode().getKey().equals(this.mapToCodeNameDTO(selected).getCode().getKey())) {
    //
    //                            System.out.println("removing...");
    //                            System.out.println(this.mapToCodeNameDTO(selected).getName());
    //
    //                            this.getSelectedCurrentDetails().remove(i);
    //
    //                        }
    //                    }
    //                }
    //
    //
    //            }
    //            System.out.println("------------------Ending check all----------------------------");
    //
    //        } catch (Exception e) {
    //
    //            System.out.println(e.toString());
    //
    //        }
    //
    //
    //    }
    //
    //
    //    public void selectedCurrent(ActionEvent event) throws Exception {
    //        //       System.out.println("only one time");
    //        //     System.out.println("semo");
    //        try {
    //            IClientDTO selected =
    //                (IClientDTO)this.getCurrentDataTable().getRowData();
    //
    //            if (selected.getChecked()) {
    //
    //
    //                boolean exist = false;
    //                for (IBasicDTO dto: this.getSelectedCurrentDetails()) {
    //                    if (dto.getCode().getKey().equals(this.mapToCodeNameDTO(selected).getCode().getKey()))
    //                        exist = true;
    //                }
    //                if (!exist) {
    //                    this.getSelectedCurrentDetails().add(this.mapToCodeNameDTO(selected));
    //
    //                    System.out.println("adding...");
    //                    System.out.println(selected.getName());
    //                }
    //            }
    //
    //            if (!(selected.getChecked())) {
    //
    //
    //                for (int i = 0; i < this.getSelectedCurrentDetails().size();
    //                     i++) {
    //
    //                    IBasicDTO dto = this.getSelectedCurrentDetails().get(i);
    //                    if (dto.getCode().getKey().equals(this.mapToCodeNameDTO(selected).getCode().getKey())) {
    //
    //                        this.getSelectedCurrentDetails().remove(i);
    //                        System.out.println("removing...");
    //                        System.out.println(selected.getName());
    //
    //                    }
    //                }
    //
    //
    //            }
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
    //
    //
    //    }


    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module
    // public void selectedAvailableAll(ActionEvent event) throws Exception {
    //     try {
    //
    //         Set s=new HashSet();
    //         s.addAll( this.getSelectedAvailableDetails());
    //
    //         Integer rowsCountPerPage=(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());
    //
    //         if(rowsCountPerPage==null){
    //
    //             throw new Exception("#{shared_util.noOfTableRows} return null");
    //         }
    //
    //         int first = this.getAvailableDataTable().getFirst();
    //         for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {
    //             this.getAvailableDataTable().setRowIndex(j);
    //             if(!this.getAvailableDataTable().isRowAvailable())
    //             break;
    //             IBasicDTO selected =
    //                 (IBasicDTO)this.getAvailableDataTable().getRowData();
    //
    //             if (checkAllFlagAvailable == true) {
    //
    //                 s.add(selected);
    //
    //             }
    //
    //
    //             if (checkAllFlagAvailable == false) {
    //
    //
    //                 s.remove(selected);
    //
    //
    //             }
    //
    //
    //         }
    //        this.getSelectedAvailableDetails().clear();
    //        this.getSelectedAvailableDetails().addAll(s);
    //
    //
    //     } catch (Exception e) {
    //     }
    //
    //
    // }
    //updated by yasmine

    public void selectedAvailableAll(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
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
                IBasicDTO selected = (IBasicDTO)this.getAvailableDataTable().getRowData();

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

    /**
     * Purpose:Method  used to filter the list sent as parameter (fullList)
     *                 from Deleted items to return only list with added item or Non-Deleted ones
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  22-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:usage example , I used this method in business validation in manytomany that one of the list in DTO shown at atab is required
     */
    public List getFilteredList(List fullList) {
        List listToOrder = new ArrayList();
        //fullList = (ArrayList<IBasicDTO>)fullList;
        for (Object dto : fullList) {

            if (((IBasicDTO)dto).getStatusFlag() == null) {
                listToOrder.add(dto);
            }

            if (((IBasicDTO)dto).getStatusFlag() != null && ((IBasicDTO)dto).getStatusFlag().longValue() == 0) {
                listToOrder.add(dto);
            }

        }

        return listToOrder;
    }

    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module

    public void selectedAvailable(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedAvailableDetails());

            IClientDTO selected = (IClientDTO)this.getAvailableDataTable().getRowData();
            selected.setChecked( !( s.contains(selected) ) );
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
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
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

                IBasicDTO selected = (IBasicDTO)this.getCurrentDataTable().getRowData();

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
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            IClientDTO selected = (IClientDTO)this.getCurrentDataTable().getRowData();


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

    public int getSelectedAvailableListSize() throws DataBaseException, SharedApplicationException {
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

    public void restoreDetailsTablePosition(HtmlDataTable dataTable, List list) {

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

    public void getDetailsPageIndex(HtmlDataTable dataTable, List list, String key) throws DataBaseException {
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
        if (dataScroller != null) {
            pageIndexAdd = dataScroller.getPageIndex();
        }
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
            PropertyResourceBundle p = (PropertyResourceBundle)ResourceBundle.getBundle(bundleBase, this.getLocale());
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

            String formattedQuery = searchQuery.substring(1, searchQuery.length() - 1);
            if (dtoName.contains(formattedQuery) || dtoName.equals(formattedQuery))
                return true;


        } else if (searchQuery.endsWith("%")) {

            String formattedQuery = searchQuery.substring(0, searchQuery.length() - 1);
            if (dtoName.endsWith(formattedQuery))
                return true;

        } else if (searchQuery.startsWith("%")) {

            String formattedQuery = searchQuery.substring(1, searchQuery.length());
            if (dtoName.startsWith(formattedQuery))
                return true;

        } else {
            if (dtoName.contains(searchQuery) || dtoName.equals(searchQuery))
                return true;


        }


        return false;

    }

    public void generateMenuIndexes(ModuleObject currentObj, String currentMenuItemIndex) {
        //String ret = "";
        ModuleObject obj = null;
        List<ModuleObject> list = currentObj.getChildsList();
        if (list.size() == 0) {
            if (currentObj.getObjPage() != null) {
                allIndexes += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
            }
            if (currentObj.getSelectionFlag().equals("1") && currentObj.getObjPage() != null) {
                casesArray[1] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
            }
            if (currentObj.getSelectionFlag().equals("2") && currentObj.getObjPage() != null) {
                casesArray[1] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
                casesArray[2] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
            }
            if (currentObj.getSelectionFlag().equals("0") && currentObj.getObjPage() != null) {
                casesArray[0] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
                casesArray[1] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
                casesArray[2] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
            }
        }


        for (int i = 0; i < list.size(); i++) {
            obj = list.get(i);
            // if( obj != null && obj.getChildsList() != null && obj.getChildsList().size() > 0 ){
            int index = i + 5;
            generateMenuIndexes(obj, currentMenuItemIndex + "-" + index);
            //}
        }
    }

    public String[] getCasesArray() {

        objects = tableHeaderBean.getObjects();

        if (casesArray == null) {
            casesArray = new String[10];
            for (int i = 0; i < casesArray.length; i++) {
                casesArray[i] = "";
            }

        }
        int countParent = -1;
        // int countChild = 4;
//        System.out.println("objects.size();  " + objects.size());
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getObjType().equals("menu")) {
                countParent++;
                generateMenuIndexes(objects.get(i), "" + countParent + "");
            }
        }
        //            System.out.println("Yassminee ");
        //            System.out.println("casesArray[0]  "+casesArray[0]);
        //            System.out.println("casesArray[1]  "+casesArray[1]);
        //            System.out.println("casesArray[3]  "+casesArray[2]);
        return casesArray;
    }

    public String[] getCasesArrayButton() {
        objects = tableHeaderBean.getObjects();
        if (casesArrayButton == null) {
            casesArrayButton = new String[10];
            for (int i = 0; i < casesArrayButton.length; i++) {
                casesArrayButton[i] = "";
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getObjType().equals("button")) { //BUTTONS
                allIndexesButton += objects.get(i).getObjCode() + ",";
                if (objects.get(i).getSelectionFlag().equals("0")) {
                    casesArrayButton[0] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[2] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("1")) {

                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("2")) {

                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[2] += objects.get(i).getObjCode() + ",";
                }
            }
        }
        //            System.out.println("casesArrayButton  "+casesArrayButton[0]);
        //            System.out.println("casesArrayButton  "+casesArrayButton[1]);
        //            System.out.println("casesArrayButton  "+casesArrayButton[2]);
        //            System.out.println("allIndexesButton  "+allIndexesButton);
        return casesArrayButton;
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
        this.initiateBeanOnce();
        return frm;
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        // added by nora to hide paging as it was hold only save states  so will move them(save states ) to common data
        app.setShowpaging(false);
        return app;
    }

    public AppMainLayoutPortal appMainLayoutPortalBuilder() {

        AppMainLayoutPortal app = new AppMainLayoutPortal();

        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        app.setShowpaging(false);

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
            if (((IClientDTO)getCurrentDisplayDetails().get(i)).getChecked() != null &&
                ((IClientDTO)getCurrentDisplayDetails().get(i)).getChecked().booleanValue()) {
                ((IClientDTO)getCurrentDisplayDetails().get(i)).setChecked(Boolean.valueOf(false));
            }
        }

        if (getSelectedCurrentDetails() != null) {
            getSelectedCurrentDetails().clear();
        }
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }

    public Long getDeleted() {
        return deleted;
    }

    public void setAdded(Long added) {
        this.added = added;
    }

    public Long getAdded() {
        return added;
    }

    /**
     * Purpose:add selected row to SelectedAvailableDetails list ,used when your column is radio not check box
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   20-10-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void selectedAvailableRadio(ActionEvent event) throws Exception {
        event = null; // unused
        try {
            IClientDTO selected = (IClientDTO)this.getAvailableDataTable().getRowData();

            if (getSelectedAvailableDetails() == null)
                setSelectedAvailableDetails(new ArrayList<IBasicDTO>(0));

            if (getSelectedAvailableDetails().size() > 0)
                getSelectedAvailableDetails().clear();

            getSelectedAvailableDetails().add(selected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sort(String columnName, boolean sortAscending) {
        List<IBasicDTO> totalList = new ArrayList<IBasicDTO>();
        setSortColumn(columnName);

        if (isUsingPaging()) {
            PagingResponseDTO responseDTO = getPagingBean().getPagingResponse();
            if (responseDTO != null && responseDTO.getResultList() != null) {
                totalList = responseDTO.getResultList();
            }
        } else {
            totalList = this.getCurrentDetails();
        }
        totalList = (List<IBasicDTO>)sort(totalList, columnName, sortAscending, false);

        if (isUsingPaging()) {
            getPagingBean().updateMyPadgedDataModel(totalList);
        } else {
            getCurrentDetails().clear();
            getCurrentDetails().addAll(totalList);
        }
        currentDataTable.setSortAscending(sortAscending);
    }

    // new style for the shortcut div
    private String lookupshortCutDiv = "divSearch";

    public void setLookupshortCutDiv(String lookupshortCutDiv) {
        this.lookupshortCutDiv = lookupshortCutDiv;
    }

    public String getLookupshortCutDiv() {
        return lookupshortCutDiv;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    /**
     * ***********************  START SORTING DATA TABLE ***********************
     *                                                                         *
     * @Desc Moving Up/Down of Data Table records by arrows in a column of it. *
     *                                                                         *
     * @Author MLotfy                                                          *
     *                                                                         *
     * *************************************************************************
     **/
    public void moveUp() {

//        System.out.println("Moving up data tabel element...........");

        int rowIndex = getCurrentDataTable().getRowIndex();
        if (rowIndex > 0) {
            swap(rowIndex, rowIndex - 1);
        }
    }

    public void moveDown() {

//        System.out.println("Moving down data tabel element.........");

        int rowIndex = getCurrentDataTable().getRowIndex();
        try {
            if (rowIndex < getCurrentDisplayDetails().size() - 1) {
                swap(rowIndex, rowIndex + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveFirst() {

//        System.out.println("Moving data tabel element to first...........");

        int rowIndex = getCurrentDataTable().getRowIndex();
        if (rowIndex > 0) {
            List<IBasicDTO> dataList = getCurrentDisplayDetails();
            for (int i = rowIndex; i > 0; --i) {
                swap(i, i - 1, dataList);
            }
            reOrderList(dataList);
            reSetCurrentDetails(dataList);
        }
    }

    public void moveLast() {

//        System.out.println("Moving data tabel element to last...........");

        int rowIndex = getCurrentDataTable().getRowIndex();

        List<IBasicDTO> dataList = getCurrentDisplayDetails();

        int listSize = dataList.size();

        if (rowIndex < listSize - 1) {

            for (int i = rowIndex; i < listSize - 1; ++i) {
                swap(i, i + 1, dataList);
            }
            reOrderList(dataList);
            reSetCurrentDetails(dataList);
        }
    }

    protected void swap(int index1, int index2) {

        List<IBasicDTO> dataList = getCurrentDisplayDetails();

        swap(index1, index2, dataList);
        reOrderList(dataList);
        reSetCurrentDetails(dataList);

    }

    private void reSetCurrentDetails(List<IBasicDTO> dataList) {
        List<IBasicDTO> deletedItems = new ArrayList<IBasicDTO>();
        for (IBasicDTO dto : getCurrentDetails()) {
            if (dto.getStatusFlag() != null && dto.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {

                deletedItems.add(dto);
            }
        }
        setCurrentDetails(dataList);
        for (IBasicDTO dto : deletedItems) {
            getCurrentDetails().add(dto);
        }
    }

    /***************************  END SORTING DATA TABLE **********************/
}
