package com.beshara.csc.nl.qul.integration.presentation.backingbean.centers;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.ICentersDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.TreeDivBeanChild;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ValueChangeEvent;


public class QulCentersIntegrationBean extends LookUpBaseBean {


    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private TreeDivBeanChild treeCentersDivBean;


    private String selectedCountryId;
    private String cancelSearchMethod;
    private Boolean enableSearch = true;
    private String returnMethodName;
    private String reRenderFields;
    private String intDivName;
    private String preOpenMethodName;
    private String containerBeanName;

    public QulCentersIntegrationBean(String containerBeanName, String intDivName) {
        super();
        treeCentersDivBean = (TreeDivBeanChild)evaluateValueBinding("treeDivBeanChild");
        this.containerBeanName = containerBeanName;
        this.intDivName = intDivName;
        initTreeDivBean();
    }

    public void initTreeDivBean() {
        // treeCentersDivBean.setBundleName("com.beshara.csc.hr.crs.presentation.resources.crs_");
        treeCentersDivBean.setBundle(ResourceBundle.getBundle("com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration"));
        initializeTreeDiv();
    }

    public void initializeTreeDiv() {
        treeCentersDivBean.setRootName("ListingCentersPage");
        treeCentersDivBean.setClient(QulClientFactory.getCentersClient());
        treeCentersDivBean.setPageDTO(QulDTOFactory.createCentersDTO());
        treeCentersDivBean.setDto(QulDTOFactory.createCentersDTO());
        treeCentersDivBean.setDtoDetails(QulDTOFactory.createCentersDTO());
        treeCentersDivBean.setSelectedPageDTO(QulDTOFactory.createCentersDTO());

    }

    public void cancelTreeDivSearch() {
        getTree();
        resetTreeDivSearch();
        treeCentersDivBean.setSearchMode(false);
        treeCentersDivBean.setSearchQuery("");
        treeCentersDivBean.getHtmlTree().collapsePath(new String[] { "0" });
    }

    public void getTree() {
        try {
            if (selectedCountryId != null) {
                treeCentersDivBean.setMyTableData(null);
                ICountriesEntityKey key =
                    InfEntityKeyFactory.createCountriesEntityKey(Long.valueOf(selectedCountryId));
                List temp = QulClientFactory.getCentersClient().getAllByCountryInCenter(key);
                treeCentersDivBean.setMyTableData(temp);

            }
        } catch (NoResultException ne) {
            treeCentersDivBean.setMyTableData(new ArrayList());
        } catch (DataBaseException db) {
            treeCentersDivBean.setMyTableData(new ArrayList());
            getSharedUtil().handleException(db);
        } catch (SharedApplicationException e) {
            treeCentersDivBean.setMyTableData(new ArrayList());
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            treeCentersDivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } 
        treeCentersDivBean.setTreeNodeBase(null);
        treeCentersDivBean.setShowTreeContent(true);
        try {
            treeCentersDivBean.buildTree();
        } catch (DataBaseException e) {
        }
        treeCentersDivBean.getHtmlTree().collapsePath(new String[] { "0" });
    }

    public void resetTreeDivSearch() {
        treeCentersDivBean.setSelectionNo(0);
        treeCentersDivBean.setSearchQuery("");
        treeCentersDivBean.setSearchType(0);
        treeCentersDivBean.setSearchMode(false);
    }

    public void OpenTreeDiv() {
        initializeTreeDiv();
        resetTreeDivSearch();
        if (cancelSearchMethod != null && !cancelSearchMethod.isEmpty()) {
            treeCentersDivBean.setCancelSearchMethod(cancelSearchMethod);
        } else {
            treeCentersDivBean.setCancelSearchMethod("qulCentersIntegrationBean.cancelTreeDivSearch");
        }
        treeCentersDivBean.setEnableSearchByCode(enableSearch);
        MethodBinding mb =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding(getReturnMethodName(), null);
        treeCentersDivBean.setIdChangeMethod("qulCentersIntegrationBean.idChange");
        treeCentersDivBean.getOkCommandButton().setAction(mb);
        treeCentersDivBean.getOkCommandButton().setReRender(getReRenderFields());
        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulCentersIntegrationBean.treeCentersDivBean.selectionNo == 0 || !qulCentersIntegrationBean.treeCentersDivBean.enabledNotLeaf}");
        treeCentersDivBean.getOkCommandButton().setValueBinding("disabled", vb);
        getTree();
    }

    public void openIntDiv() {
        OpenTreeDiv();
        String ret = "";
        if (getPreOpenMethodName() != null && !getPreOpenMethodName().equals("")) {
            ret = (String)executeMethodBinding(getPreOpenMethodName(), null);
        }
    }

    public String closeIntDiv() {
        String ret = "";
        if (getReturnMethodName() != null && !getReturnMethodName().equals("")) {
            ret = (String)executeMethodBinding(getReturnMethodName(), null);
        }
        return ret;
    }

    public void setSelectedCountryId(String selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }

    public String getSelectedCountryId() {
        return selectedCountryId;
    }

    public void setCancelSearchMethod(String cancelSearchMethod) {
        this.cancelSearchMethod = cancelSearchMethod;
    }

    public String getCancelSearchMethod() {
        return cancelSearchMethod;
    }

    public void setEnableSearch(Boolean enableSearch) {
        this.enableSearch = enableSearch;
    }

    public Boolean getEnableSearch() {
        return enableSearch;
    }

    public void setReturnMethodName(String returnMethodName) {
        this.returnMethodName = returnMethodName;
    }

    public String getReturnMethodName() {
        return returnMethodName;
    }

    public void setReRenderFields(String reRenderFields) {
        this.reRenderFields = reRenderFields;
    }

    public String getReRenderFields() {
        return reRenderFields;
    }

    public void setIntDivName(String intDivName) {
        this.intDivName = intDivName;
    }

    public String getIntDivName() {
        return intDivName;
    }

    public void setContainerBeanName(String containerBeanName) {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName() {
        return containerBeanName;
    }

    public void setPreOpenMethodName(String preOpenMethodName) {
        this.preOpenMethodName = preOpenMethodName;
    }

    public String getPreOpenMethodName() {
        return preOpenMethodName;
    }

    protected BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }


    public ICentersDTO getSelectedCenterDTO() {
        return (ICentersDTO)treeCentersDivBean.getDtoDetails();
    }

    public void setTreeCentersDivBean(TreeDivBeanChild treeCentersDivBean) {
        this.treeCentersDivBean = treeCentersDivBean;
    }

    public TreeDivBeanChild getTreeCentersDivBean() {
        return treeCentersDivBean;
    }

    public void idChange(ValueChangeEvent e) {

        String value = (String)e.getNewValue();
        if (value != null && !value.equals("")) {
            treeCentersDivBean.getDtoDetails().setCode(null);
            treeCentersDivBean.getDtoDetails().setParentObject(null);
            treeCentersDivBean.getDtoDetails().setParentCode(null);
            treeCentersDivBean.setSelectionNo(0);
            String nodeCode = (String)e.getNewValue();
            if (treeCentersDivBean.getKeyIndex() > -1 && !treeCentersDivBean.isUsingTreePaging()) {
                try {
                    nodeCode =
                            treeCentersDivBean.getFullEntityKey(treeCentersDivBean.getTreeNodeBase().getChildren(), nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    if (treeCentersDivBean.isUsingTreePaging()) {
                        setEntityKey(treeCentersDivBean.getSelectedEntityKey(nodeCode));
                    } else {
                        setEntityKey(QulEntityKeyFactory.createCentersEntityKey(Long.valueOf(nodeCode)));
                    }

                    this.setAlreadyDeleted(false);

                    if (treeCentersDivBean.getDtoDetails() == null ||
                        treeCentersDivBean.getDtoDetails().getCode() == null ||
                        (!value.equals(treeCentersDivBean.getDtoDetails().getCode().getKey().toString()))) {

                        treeCentersDivBean.setDtoDetails((ITreeDTO)treeCentersDivBean.getClient().getByIdInCenter(getEntityKey()));
                    }

                    if (treeCentersDivBean.getDtoDetails() != null) {
                        if (treeCentersDivBean.getDtoDetails().isBooleanLeafFlag()) {
                            treeCentersDivBean.setEnabledNotLeaf(true);
                        } else {
                            treeCentersDivBean.setEnabledNotLeaf(false);
                        }
                        if (treeCentersDivBean.getDtoDetails().getChildernNumbers() != 0) {
                            treeCentersDivBean.setEnabledNotHasChlid(true);
                        } else {
                            treeCentersDivBean.setEnabledNotHasChlid(false);
                        }
                    }

                    if (treeCentersDivBean.getDtoDetails() != null &&
                        treeCentersDivBean.getDtoDetails().getParentCode() == null) {
                        treeCentersDivBean.getDtoDetails().setParentCode(getEntityKey());
                    }
                    treeCentersDivBean.setRenderEdit(true);
                    treeCentersDivBean.setParentName(treeCentersDivBean.getDtoDetails().getName());

                    treeCentersDivBean.setParentLevel(nodeCode);

                } catch (Exception ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                }
            } else {
                treeCentersDivBean.setParentName(treeCentersDivBean.getBundle().getString(treeCentersDivBean.getRootName()));
                treeCentersDivBean.setParentLevel(treeCentersDivBean.getVirtualLevelCode().toString());
                treeCentersDivBean.setEnabledRoot(true);
                treeCentersDivBean.setEnabledNotLeaf(false);
                treeCentersDivBean.setEnabledNotHasChlid(true);
            }
            treeCentersDivBean.setSelectionNo(1);
        }
    }

    public List getTotalList() {
        return new ArrayList();
    }
}
