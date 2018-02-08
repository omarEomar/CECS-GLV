<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <!-- Load the resource bundle of the page -->
    <h:form id="myForm" binding="#{govEmpForthStepBean.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration"
                      var="resourcesBundle"/>
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infIntegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{govEmpForthStepBean}">
            <t:aliasBean alias="#{workDataListBeanName}" value="#{govEmpForthStepBean.workDataListBean}">
                <t:aliasBean alias="#{detailBeanName}" value="#{govEmpMaintainBean}"> 
                        <t:saveState value="#{pageBeanName.myTableData}"/>
                        <t:saveState value="#{detailBeanName.civilID}"/>
                        <t:saveState value="#{detailBeanName.empName}"/>
                        <t:saveState value="#{detailBeanName.preSelectedCivilId}"/>
                        <t:saveState value="#{detailBeanName.enableTabs}"/>
                        <t:saveState value="#{detailBeanName.empHireStatusList}"/>
                        <t:saveState value="#{detailBeanName.realCivilId}"/>
                        <t:saveState value="#{detailBeanName.bundleName}"/>
                        <t:saveState value="#{detailBeanName.statusMsgKey}"/>
                        <t:saveState value="#{detailBeanName.titlePageKey}"/>
                        <t:saveState value="#{detailBeanName.ministryCode}"/>
                        <t:saveState value="#{pageBeanName.workDataListBean.kwtWorkDataDTOList}"/>
                        <t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
                        <t:saveState value="#{workDataListBeanName.usingTreePaging}"/>
                        <t:saveState value="#{workDataListBeanName.treeListPagingRequestDTO}"/>
                        <t:saveState value="#{workDataListBeanName.pagedTreeListSize}"/>
                        <t:saveState value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
                        <t:saveState value="#{workDataListBeanName.htmlTree}"/>
                        <t:saveState value="#{workDataListBeanName.myTableData}"/>
                        <t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
                        <t:saveState value="#{workDataListBeanName.dtoDetails}"/>
                        <t:saveState value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
                        <t:saveState value="#{workDataListBeanName.htmlTree}"/>
                        <t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
                        <t:saveState value="#{workDataListBeanName.searchResultSize}"/>
                        <t:saveState value="#{workDataListBeanName.entityKey}"/>
                        <t:saveState value="#{workDataListBeanName.treeListSize}"/>
                        <t:saveState value="#{workDataListBeanName.treeList}"/>
                        <t:saveState value="#{workDataListBeanName.panelGroupStyleClass}"/>
                        <t:saveState value="#{workDataListBeanName.civilId}"/>
                        <t:saveState value="#{workDataListBeanName.civilExist}"/>
                        <t:saveState value="#{workDataListBeanName.validCivilId}"/>
                        <t:saveState value="#{workDataListBeanName.navigationCase}"/>
                        <t:saveState value="#{workDataListBeanName.showBtn}"/>
                        <t:saveState value="#{workDataListBeanName.hmObjects}"/>
                        <t:saveState value="#{workDataListBeanName.beanName}"/>
                        <t:saveState value="#{workDataListBeanName.backAction}"/>
                        <t:saveState value="#{workDataListBeanName.enableAdd}"/>
                        <t:saveState value="#{workDataListBeanName.enableBack}"/>
                        <t:saveState value="#{workDataListBeanName.saveInDB}"/>
                        <t:saveState value="#{workDataListBeanName.pageType}"/>
                        <t:saveState value="#{workDataListBeanName.listPageinWizardBar}"/>
                        <t:saveState value="#{workDataListBeanName.addExperIntegrationpage}"/>
                        <tiles:insert flush="false" definition="govempforthstep.page">
                            <tiles:put name="titlepage" type="string" value="${detailBeanName.titlePageKey}"></tiles:put>
                        </tiles:insert>
                    </t:aliasBean> 
            </t:aliasBean>
        </t:aliasBean>
        <script type="text/javascript">
          settingFoucsAtListPage();
        </script>
    </h:form>
</f:view>