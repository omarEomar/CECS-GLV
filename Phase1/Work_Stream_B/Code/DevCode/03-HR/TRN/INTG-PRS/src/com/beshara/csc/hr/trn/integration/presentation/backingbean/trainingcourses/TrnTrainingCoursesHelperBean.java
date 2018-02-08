package com.beshara.csc.hr.trn.integration.presentation.backingbean.trainingcourses;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.trn.business.client.TrnClientFactory;
import com.beshara.csc.hr.trn.business.dto.ITrnTrainingCoursesDTO;
import com.beshara.csc.hr.trn.business.dto.ITrnTrainingCoursesSearchCriteriaDTO;
import com.beshara.csc.hr.trn.business.dto.TrnDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;


/**
 * #Integration Mode
 *
 * @version 1.1
 * @author Heba.Ahmed
 * @since 15/09/2014
 */


public class TrnTrainingCoursesHelperBean extends IntegrationBaseBean {


    /**************************************  SearchDTO & search criteria  **********************************************************/

    private ITrnTrainingCoursesSearchCriteriaDTO trnTrainingCoursesSearchCriteriaDTO =
        TrnDTOFactory.createTrnTrainingCoursesSearchCriteriaDTO();

    private String selectedCourseTypeCode = ISystemConstant.VIRTUAL_VALUE.toString();
    private String courseTypeCode = ISystemConstant.VIRTUAL_VALUE.toString();
    private List<ITrnTrainingCoursesDTO> execludedList = new ArrayList<ITrnTrainingCoursesDTO>();
    private List<IBasicDTO> coursesTypeList = new ArrayList<IBasicDTO>();

    private Boolean renderDivComponents = Boolean.FALSE;

    private final String SEARCH_BY_CODE = "0";
    private final String SEARCH_BY_Name = "1";
    private String searchTypeForAddDiv = SEARCH_BY_CODE;
    private String searchString;
    private String okCommandButtonRerender;
    /**************************************  Constructors & initial Methods **********************************************************/

    public TrnTrainingCoursesHelperBean(String containerBeanNameBindingKey, String intDivName) {
        super();
        getSharedUtil().init();
        setContainerBeanName(containerBeanNameBindingKey);
        setDivId(intDivName);
        setOkCommandButtonDefaultRerenderValues();
    }


    public SharedUtilBean getSharedUtil() {
        return SharedUtilBean.getInstance();
    }


    /**************************************  Search Methods **********************************************************/

    public void search() throws DataBaseException, NoResultException {

        setCheckAllFlagAvailable(false);
        setIntgSearchMode(true);
        setAvailableDetails(getTotalList());
        if (getSelectedDTOSList() != null) {
            getSelectedDTOSList().clear();
        }
    }


    public List getTotalList() {

        List totalList = null;

        try {
            totalList = getSearchResults();
        } catch (SharedApplicationException ne) {
            ne.printStackTrace();
            totalList = new ArrayList();
            ne.printStackTrace();
        } catch (DataBaseException db) {
            totalList = new ArrayList();
            db.printStackTrace();
            //  getSharedUtil().handleException(db);
        } catch (Exception e) {
            totalList = new ArrayList();
            e.printStackTrace();
            //getSharedUtil().handleException(e);
        }
        return totalList;
    }


    public List getSearchResults() throws DataBaseException, SharedApplicationException {
        resetTrnTrainingCoursesSearchCriteriaDTO();
        List<IBasicDTO> resultList = new ArrayList<IBasicDTO>();
        if (getCourseTypeCode() != null && !getCourseTypeCode().equals(ISystemConstant.VIRTUAL_VALUE.toString())) {
            trnTrainingCoursesSearchCriteriaDTO.setCourseTypeCode(Long.valueOf(getCourseTypeCode()));
        }
        if (isIntgSearchMode() && getSearchString() != null) {
            if (SEARCH_BY_CODE.equals(getSearchTypeForAddDiv())) {
                trnTrainingCoursesSearchCriteriaDTO.setCourseCode(Long.valueOf(getSearchString()));
            } else {
                trnTrainingCoursesSearchCriteriaDTO.setCourseName(getSearchString());
            }

        }
        if (getExecludedList() != null && !getExecludedList().isEmpty()) {
            trnTrainingCoursesSearchCriteriaDTO.setExcludedTrnTrainingCoursesDTO((List)getExecludedList());
        }
        try {
            resultList =
                    TrnClientFactory.getTrnTrainingCoursesClient().searchAvailableCourses(trnTrainingCoursesSearchCriteriaDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * reset Only Basic search criteria
     */
    private void resetTrnTrainingCoursesSearchCriteriaDTO() {
        trnTrainingCoursesSearchCriteriaDTO.setCourseCode(null);
        trnTrainingCoursesSearchCriteriaDTO.setCourseName(null);
        trnTrainingCoursesSearchCriteriaDTO.setCourseTypeCode(null);
        trnTrainingCoursesSearchCriteriaDTO.setExcludedTrnTrainingCoursesDTO(null);
    }

    
    /**************************************  Getters & Setters **********************************************************/


    public void setTrnTrainingCoursesSearchCriteriaDTO(ITrnTrainingCoursesSearchCriteriaDTO trnTrainingCoursesSearchCriteriaDTO) {
        this.trnTrainingCoursesSearchCriteriaDTO = trnTrainingCoursesSearchCriteriaDTO;
    }

    public ITrnTrainingCoursesSearchCriteriaDTO gettrnTrainingCoursesSearchCriteriaDTO() {
        return trnTrainingCoursesSearchCriteriaDTO;
    }


    public void setExecludedList(List<ITrnTrainingCoursesDTO> execludedList) {
        this.execludedList = execludedList;
    }

    public List<ITrnTrainingCoursesDTO> getExecludedList() {
        return execludedList;
    }

    public void resetValues() {
        setCourseTypeCode(ISystemConstant.VIRTUAL_VALUE.toString());
        resetForFilter();
    }

    public void cancelSearch() throws DataBaseException {
        filterByCourseType();
    }


    public void filterByCourseType() {
        resetForFilter();
        if (getCourseTypeCode() != null && !getCourseTypeCode().equals(ISystemConstant.VIRTUAL_VALUE.toString())) {
            setAvailableDetails(getTotalList());
            setRenderDivComponents(Boolean.TRUE);
        }

        getAvailableDataTable().setFirst(0);

    }

    private void resetForFilter() {
        setSearchString(null);
        setIntgSearchMode(false);
        setAvailableDetails(new ArrayList());
        setSearchTypeForAddDiv(SEARCH_BY_CODE);
        setRenderDivComponents(Boolean.FALSE);
        setCheckAllFlagAvailable(false);
        setAvailableDetails(new ArrayList());
        this.setAvailableListSize(0);
        if (getSelectedDTOSList() != null) {
            getSelectedDTOSList().clear();
        }

    }


   

    public void openIntDiv() {
        String ret = "";
        if (getPreOpenMethodName() != null && !getPreOpenMethodName().equals("")) {
            ret = (String)executeMethodBinding(getPreOpenMethodName(), null);
        }

        writeShowDivJScript();
    }

    public String closeIntDiv() {
        String ret = "";
        if (getReturnMethodName() != null && !getReturnMethodName().equals("")) {
            ret = (String)executeMethodBinding(getReturnMethodName(), null);
        }
        if (isResetIntgDivAfterClose()) {
            resetData();
            resetValues();
        }
        return ret;
    }

    public void hideIntDiv() {

        if (getSelectedDTOSList() != null) {
            getSelectedDTOSList().clear();
        }
        if (isResetIntgDivAfterHide()) {
            setAvailableDetails(new ArrayList<IBasicDTO>());
            resetData();
            resetValues();
        }

        if (isTreePage()) {
            getContainerBean().setJavaScriptCaller("treeOperation(window." + getTreeDivDetailsId() +
                                                   ",window.blocker,window." + getDivId() + ",'minIntgDivBackBtn');");
        } else {
            getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + getDivId() +
                                                   ",'minIntgDivBackBtn');");
        }

    }


    /****************************************************************** SETTERS & GETTERS  *************************************************************************/

     public void setCoursesTypeList(List<IBasicDTO> courseList) {
         this.coursesTypeList = courseList;
     }

     public List<IBasicDTO> getCoursesTypeList() throws DataBaseException, SharedApplicationException {
         if (coursesTypeList.size() == 0) {
             this.setCoursesTypeList(TrnClientFactory.getTrnTypesClient().getAllTypesCodeAndName());
         }
         if (coursesTypeList == null) {
             setCoursesTypeList(new ArrayList());
         }
         return coursesTypeList;
     }

    public String getCourseTypeCode() {
        return courseTypeCode;
    }

    public void setSelectedCourseTypeCode(String selectedCourseTypeCode) {
        this.selectedCourseTypeCode = selectedCourseTypeCode;
    }

    public String getSelectedCourseTypeCode() {
        return selectedCourseTypeCode;
    }

    public void setCourseTypeCode(String courseTypeCode) {
        this.courseTypeCode = courseTypeCode;
    }


    public void setRenderDivComponents(Boolean renderDivComponents) {
        this.renderDivComponents = renderDivComponents;
    }

    public Boolean getRenderDivComponents() {
        return renderDivComponents;
    }

    public void setSearchTypeForAddDiv(String searchTypeForAddDiv) {
        this.searchTypeForAddDiv = searchTypeForAddDiv;
    }

    public String getSearchTypeForAddDiv() {
        return searchTypeForAddDiv;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setOkCommandButtonRerender(String okCommandButtonRerender) {
        this.okCommandButtonRerender = okCommandButtonRerender;
        
        if(okCommandButtonRerender != null&& !okCommandButtonRerender.equals("")){
            setOkCommandButtonRerenderValues();
        }
    }

    public String getOkCommandButtonRerender() {
        return okCommandButtonRerender;
    }
    
    private void setOkCommandButtonRerenderValues(){
        StringBuilder _defaultValues = new StringBuilder(getOkCommandButton().getReRender().toString());
        _defaultValues.append(",");
        _defaultValues.append(getOkCommandButtonRerender());
        getOkCommandButton().setReRender(_defaultValues.toString());
    }
    
     private void setOkCommandButtonDefaultRerenderValues(){
            if(getOkCommandButton().getReRender()==null){
                getOkCommandButton().setReRender("trnIntegrationFilterPanel,trnIntegrationSearchPanelGrp,trnIntegrationScrollerPanel");    
            }
        }
}
