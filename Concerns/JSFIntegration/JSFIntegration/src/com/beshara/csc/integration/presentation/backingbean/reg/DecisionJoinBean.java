package com.beshara.csc.integration.presentation.backingbean.reg;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


//import com.beshara.csc.nl.reg.business.dto.RegulationSearchDTO;


//The UseCases Used This Bean:
//----------------------------
//Mov-MovExcuteBean
//Emp-HireDecisionListBean
//Prm-PromotionListBean
//Sal-SpecialRewardListBean
//Sal-MerEmpBenListBean

/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class related to Integrate With Decision DIV.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    10-SEP-2008     Created
 * @author       Beshara Group
 * @author       Yassmine Ali Mohamed
 * @version      1.0
 * @since        10/09/2008
 */
public class DecisionJoinBean extends BaseBean {
    //Lists
    private List typeList = new ArrayList();
    private List yearsList = new ArrayList();
    private List decSourceList = new ArrayList();
    private List decSubjectList = new ArrayList();
    private List<IBasicDTO> descionsList = new ArrayList<IBasicDTO>();
    //SearchDTO
    private IRegulationSearchDTO regulationSearchDTO = RegDTOFactory.createRegulationSearchDTO();
    //Constant virtual value
    private Long virtualvalue = ISystemConstant.VIRTUAL_VALUE;
    //DataTable
    private HtmlDataTable descionsTable = new HtmlDataTable();
    private HtmlDataTable viewDescionsTable = new HtmlDataTable();
    //Boolean of searchMode
    private boolean searchModeDesDiv = false;
    //Size of Decision Lists
    private int descionsListSize = 0;
    //SelectedDTO of Decisions
    public IDecisionsDTO decisionsDTO;
    //Name of the save method
    public String okMethodName;
    public String backMethodName;
    private HtmlDataScroller dataScroller = new HtmlDataScroller();

    private Long minCode;
    //constructor

    private Long tabrecSerial;
    private boolean viewInCenter;
    private List<Long> empCivilIdList = new ArrayList<Long>();

    private String decisionText;

    public DecisionJoinBean() {
    }

    //All Gets and Sets

    public void setTypeList(List typeList) {
        this.typeList = typeList;
    }

    public List getTypeList() {
        if (typeList.isEmpty()) {
            try {

                typeList =
                        RegClientFactory.getTypesClient().getCodeNameByMinistry(ISystemConstant.REGULATION_VALIDITY_DECISION,
                                                                                CSCSecurityInfoHelper.getLoggedInMinistryCode());

            } catch (DataBaseException e) {
                e.printStackTrace();
                typeList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                typeList = new ArrayList();
            } finally {
                try {
                    if (typeList.isEmpty()) {
                        typeList =
                                RegClientFactory.getTypesClient().getCodeName(ISystemConstant.REGULATION_VALIDITY_DECISION);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    typeList = new ArrayList();
                }
            }
        }
        return typeList;
    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() {
        if (yearsList.isEmpty()) {
            try {
                yearsList = InfClientFactory.getYearsClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                yearsList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                yearsList = new ArrayList();
            }
        }
        return yearsList;
    }

    public void setDecSourceList(List decSourceList) {
        this.decSourceList = decSourceList;
    }

    public List getDecSourceList() {
        if (decSourceList.isEmpty()) {
            try {
                decSourceList =
                        InfClientFactory.getDecisionMakerTypesClient().getCodeNameByMin(CSCSecurityInfoHelper.getLoggedInMinistryCode());


            } catch (DataBaseException e) {
                e.printStackTrace();
                decSourceList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                decSourceList = new ArrayList();
            } finally {
                if (decSourceList.isEmpty()) {
                    try {
                        decSourceList = InfClientFactory.getDecisionMakerTypesClient().getCodeName();
                    } catch (Exception e) {
                        e.printStackTrace();
                        decSourceList = new ArrayList();
                    }
                }
            }
        }
        return decSourceList;
    }

    public void setDecSubjectList(List decSubjectList) {
        this.decSubjectList = decSubjectList;
    }

    public List getDecSubjectList() {
        if (decSubjectList != null && decSubjectList.isEmpty()) {
            try {
                System.out.println("CSCSecurityInfoHelper.getModuleCode() :::" +
                                   CSCSecurityInfoHelper.getModuleCode());
                decSubjectList =
                        RegClientFactory.getSubjectsClient().getCodeNameByModuleCode(new Long(ISystemConstant.REGULATION_VALIDITY_DECISION),
                                                                                     CSCSecurityInfoHelper.getModuleCode());
            } catch (DataBaseException e) {
                e.printStackTrace();
                decSubjectList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                decSubjectList = new ArrayList();
            }
        }
        return decSubjectList;
    }

    public void setRegulationSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
        this.regulationSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getRegulationSearchDTO() {
        return regulationSearchDTO;
    }


    public void setVirtualvalue(Long virtualvalue) {
        this.virtualvalue = virtualvalue;

    }

    public Long getVirtualvalue() {
        return virtualvalue;
    }


    public void setDescionsTable(HtmlDataTable descionsTable) {
        this.descionsTable = descionsTable;
    }

    public HtmlDataTable getDescionsTable() {
        return descionsTable;
    }

    public void setDescionsList(List<IBasicDTO> descionsList) {
        this.descionsList = descionsList;
    }

    public List getDescionsList() {
        return descionsList;
    }

    public void setDescionsListSize(int descionsListSize) {
        this.descionsListSize = descionsListSize;
    }

    public int getDescionsListSize() {
        if (descionsList != null) {
            descionsListSize = descionsList.size();
        }
        return descionsListSize;
    }

    public void setSearchModeDesDiv(boolean searchModeDesDiv) {
        this.searchModeDesDiv = searchModeDesDiv;
    }

    public boolean isSearchModeDesDiv() {
        return searchModeDesDiv;
    }

    //All Methods

    /**
     * Purpose: get decisions list using EmpDecisionTabRecSerialRef for pageBeanName.selectedDTOS()[0]
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  3-Feb-2010
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void showRelatedDecisionsDiv() {
        if (tabrecSerial == null) {
            try {
                tabrecSerial = (Long)evaluateValueBinding("pageBeanName.selectedDTOS[0].tabrecSerial");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        fillDecisionsListByTabRecSerialRef();
    }

    /**
     * Purpose: get decisions list using EmpDecisionTabRecSerialRef
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  3-Feb-2010
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    private void fillDecisionsListByTabRecSerialRef() {
        if (tabrecSerial == null) {
            descionsList = new ArrayList<IBasicDTO>();
            return;
        }
        try {
            //if (viewInCenter) { // no need for this flag according to Amir Nasr
                descionsList =
                        RegClientFactory.getDecisionsClient().getDecisionsByTabRecSerialRef(tabrecSerial);
//            } else {
//                descionsList = RegClientFactory.getDecisionsClient().getDecisionsByTabRecSerialRef(tabrecSerial);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            descionsList = new ArrayList<IBasicDTO>();
        }
        if (getDescionsTable() != null) {
            getDescionsTable().setFirst(0);
        }
    }

    /**
     * Purpose: hide decisions list div
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  3-Feb-2010
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void hideRelatedDecisionsDiv() {
        tabrecSerial = null;
        descionsList = new ArrayList<IBasicDTO>();
    }

    /**
     * Purpose:Action Method of Paging
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void decisionsListScrollerAction(ActionEvent ae) {

        ae = null; //unused
        BaseBean pageBeanName = (BaseBean)evaluateValueBinding("pageBeanName");
        pageBeanName.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
    }

    /**
     * Purpose: Method of Search
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchDecisionIntegrate(ActionEvent event) {
        event = null; // unused
        setSearchModeDesDiv(true);
        getRegulationSearchDTO().setIntegrationMode(Boolean.TRUE);
        getRegulationSearchDTO().setAppModuleCode(CSCSecurityInfoHelper.getModuleCode());
        getRegulationSearchDTO().setLoggedInMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        if (getEmpCivilIdList().size() > 0)
            getRegulationSearchDTO().setEmpCivilIdList(getEmpCivilIdList());

        // if (isSearchModeDesDiv() == true) {
        try {
            if (minCode == null) {
                descionsList = RegClientFactory.getDecisionsClient().searchNonCancelledDecision(regulationSearchDTO);
            } else {
                descionsList =
                        RegClientFactory.getDecisionsClientForMinistry(minCode).searchNonCancelledDecision(regulationSearchDTO);
            }

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            descionsList = new ArrayList<IBasicDTO>();
        } catch (Exception e) {
            e.printStackTrace();
            descionsList = new ArrayList<IBasicDTO>();
        }
        //        } else {
        //            descionsList = new ArrayList<IBasicDTO>();
        //        }
        if (getDescionsTable() != null) {
            getDescionsTable().setFirst(0);
        }

    }

    /**
     * Purpose: Method of cancel Search
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void cancelSearchDecisionIntegrate(ActionEvent event) {
        event = null; // unused
        descionsList = new ArrayList<IBasicDTO>();
        setSearchModeDesDiv(false);

        regulationSearchDTO = RegDTOFactory.createRegulationSearchDTO();
    }

    /**
     * Purpose: ValueChangeListner of radio button
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void radioCheckedDecision(ValueChangeEvent event) {
        event = null; // unused
        decisionsDTO = (IDecisionsDTO)descionsTable.getRowData();
    }

    /**
     * Purpose: ValueChangeListner of radio button
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void radioCheckedDecisionChanged() {
        decisionsDTO = (IDecisionsDTO)descionsTable.getRowData();
    }

    /**
     * Purpose: Method That invokes "save method"  in each bean uses SharedDecisionRelate Bean
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: User in his bean set okMethodName with beanName.MethodName
     */
    public void saveSelectedDecision() {
        executeMethodBinding(okMethodName, null);
    }
    
     /**
      * Purpose: get decision text from selected DTO
      * Creation/Modification History :Creation
      * 1.1 - Developer Name: Ali Agamy
      * 1.2 - Date:  31-Mar-2014
      * 1.3 - Creation/Modification:Creation
      */
    public void getCurrentDecText() {
        IDecisionsDTO dto = (IDecisionsDTO)getDescionsTable().getRowData();
        setDecisionText(dto.getDecisionText());
    }
     
    public void getCurrentDecTextView() {
        IDecisionsDTO dto = (IDecisionsDTO)getViewDescionsTable().getRowData();
        setDecisionText(dto.getDecisionText());
    }

    public void backFromDecision() {
        if (backMethodName != null && !backMethodName.equals("")) {
            executeMethodBinding(backMethodName, null);
        }
    }

    /**
     * Purpose:Action Method of Paging
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  10-Sep-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void scrollerAction(ActionEvent ae) {

        ae = null; //unused
        BaseBean pageBeanName = (BaseBean)evaluateValueBinding("pageBeanName");
        pageBeanName.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.masterDetailDiv);");
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setDecisionsDTO(IDecisionsDTO decisionsDTO) {
        this.decisionsDTO = decisionsDTO;
    }

    public IDecisionsDTO getDecisionsDTO() {
        return decisionsDTO;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setViewInCenter(boolean viewInCenter) {
        this.viewInCenter = viewInCenter;
    }

    public boolean isViewInCenter() {
        return viewInCenter;
    }


    public void setEmpCivilIdList(List<Long> empCivilIdList) {
        this.empCivilIdList = empCivilIdList;
    }

    public List<Long> getEmpCivilIdList() {
        return empCivilIdList;
    }

    public void setDecisionText(String decisionText) {
        this.decisionText = decisionText;
    }

    public String getDecisionText() {
        return decisionText;
    }

    public void setViewDescionsTable(HtmlDataTable viewDescionsTable) {
        this.viewDescionsTable = viewDescionsTable;
    }

    public HtmlDataTable getViewDescionsTable() {
        return viewDescionsTable;
    }
}

