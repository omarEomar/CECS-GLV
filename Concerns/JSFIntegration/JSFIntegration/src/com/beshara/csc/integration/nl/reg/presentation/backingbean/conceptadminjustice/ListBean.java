package com.beshara.csc.nl.reg.presentation.backingbean.conceptadminjustice;

import com.beshara.csc.nl.reg.business.client.IREGAdminJusticeClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGAdminJusticeDTO;
import com.beshara.csc.nl.reg.business.dto.IRegAdminJusticeSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class ListBean extends ManyToManyListBaseBean {
    private static final String NAVIGATION_CASE = 
        "conceptadminjusticemaintainnavigation";
    private static final String MAINTAIN_BEAN = 
        "conceptAdminJusticeMaintainBean";
    IREGAdminJusticeDTO extraDataDtoList = 
        RegDTOFactory.createREGAdminJusticeDTO();        
    boolean showJudgetext;
    boolean showConceptText;
    int checkIndexOfColum;
    public Integer selectCode = new Integer(0);
    public Integer selectName = new Integer(1);
    private IRegAdminJusticeSearchDTO searchDTO ;
       
   
    public ListBean() {
        setPageDTO(RegDTOFactory.createREGAdminJusticeDTO()); //set this the page dto 
        setSelectedPageDTO(RegDTOFactory.createREGAdminJusticeDTO());
        setSearchDTO(RegDTOFactory.createRegAdminJusticeSearchDTO());
        setClient(RegClientFactory.getREGAdminJusticeClientForCenter());
        setCustomDiv1("divCustom");
        setEditNavigationCase(NAVIGATION_CASE);
        setAddNavigationCase(NAVIGATION_CASE);
        setEditBeanName(MAINTAIN_BEAN);
        setAddBeanName(MAINTAIN_BEAN);
        setIndexArray(new boolean[] { true, true, true, true, false, false, 
                                      false, false, false });
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowCustomDiv1(true);
        return app;
    }

    public void initializeObjectBeforeMaintain() {
        if (((IREGAdminJusticeDTO)getPageDTO()).getAdminJusticeSubjectsList() == 
            null)
            ((IREGAdminJusticeDTO)getPageDTO()).setAdminJusticeSubjectsList(new ArrayList());
    }

    public String searchConcept() throws DataBaseException, NoResultException {
        try {
      ConceptAdminJusticeSearchBean conceptAdminJusticeSearchBean= (ConceptAdminJusticeSearchBean)evaluateValueBinding("conceptAdminJusticeSearchBean");
            setSearchDTO((IRegAdminJusticeSearchDTO)conceptAdminJusticeSearchBean.getPageDTO());
            setMyTableData(((IREGAdminJusticeClient)getClient()).search(getSearchDTO()));
            setSearchMode(true);
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
        return "ADMIN_JUSTIC_LIST";
    }

    public void setExtraDataDtoList(IREGAdminJusticeDTO extraDataDtoList) {
        this.extraDataDtoList = extraDataDtoList;
    }

    public IREGAdminJusticeDTO getExtraDataDtoList() {
        return extraDataDtoList;
    }

    public void setShowJudgetext(boolean showJudgetext) {
        this.showJudgetext = showJudgetext;
    }

    public boolean isShowJudgetext() {
        return showJudgetext;
    }

    public void setShowConceptText(boolean showConceptText) {
        this.showConceptText = showConceptText;
    }

    public boolean isShowConceptText() {
        return showConceptText;
    }

    public void showRuqDitail(ActionEvent event) {
        if (event != null) {
            String[] checkData = event.getComponent().getId().split("_");
            checkIndexOfColum = 
                    Integer.parseInt(checkData[checkData.length - 1]);
            IREGAdminJusticeDTO regAdminJusticeDTO = 
                (IREGAdminJusticeDTO)getMyDataTable().getRowData();
            setExtraDataDtoList(regAdminJusticeDTO);
            if (checkIndexOfColum == 0) {
                setShowConceptText(true);
                setShowJudgetext(false);
            }
            if (checkIndexOfColum == 1) {
                setShowJudgetext(true);
                setShowConceptText(false);
            }
        }
    }

    public void setCheckIndexOfColum(int checkIndexOfColum) {
        this.checkIndexOfColum = checkIndexOfColum;
    }

    public int getCheckIndexOfColum() {
        return checkIndexOfColum;
    }

    public void setSelectName(Integer selectName) {
        this.selectName = selectName;
    }

    public Integer getSelectName() {
        return selectName;
    }

    public void setSelectCode(Integer selectCode) {
        this.selectCode = selectCode;
    }

    public Integer getSelectCode() {
        return selectCode;
    }

    public void setSearchDTO(IRegAdminJusticeSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IRegAdminJusticeSearchDTO getSearchDTO() {
        return searchDTO;
    }

   
    public void cancelSearch() throws DataBaseException {
    setSearchDTO(RegDTOFactory.createRegAdminJusticeSearchDTO());
    super.cancelSearch();
    }
    
    public String backFromSearch()
    {
        if(!isSearchMode())
        {
            try {
                cancelSearch();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
       return "ADMIN_JUSTIC_LIST";
    }
    
    
    public String goEdit() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            ManyToManyMaintainBaseBean maintainBean = 
                (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +this.getAddBeanName() +"}").getValue(FacesContext.getCurrentInstance());

            setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
            maintainBean.setMaintainMode(1);
            this.initializeObjectBeforeMaintain();
            maintainBean.setPageDTO(this.getPageDTO());
            
            //TODO locking code
            if (!lock()) {
                return null;
            }
            maintainBean.setLastLockingAction(getLastLockingAction());
            return getEditNavigationCase();

        }
        return null;
    }
    public String goToSearch() throws DataBaseException, 
                                  SharedApplicationException {     
        ConceptAdminJusticeSearchBean conceptAdminJusticeSearchBean= (ConceptAdminJusticeSearchBean)evaluateValueBinding("conceptAdminJusticeSearchBean");
          conceptAdminJusticeSearchBean.setPageDTO(getSearchDTO());
        return "conceptadminjusticemaintainSearch";
    }
}
