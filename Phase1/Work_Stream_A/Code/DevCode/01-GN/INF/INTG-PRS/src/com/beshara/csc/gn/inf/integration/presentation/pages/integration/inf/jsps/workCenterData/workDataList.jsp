<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{workDataListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infIntegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{workDataListBean}">
            <t:aliasBean alias="#{workDataListBeanName}" value="#{workDataListBean}">
                <tiles:insert flush="false" definition="workDataList.page">
                    <tiles:put name="titlepage" type="string" value="${'experiance'}"></tiles:put>
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
                    <t:saveState value="#{workDataListBeanName.kwtWorkDataDTOList}"/>
                    <t:saveState value="#{workDataListBeanName.pageType}"/>
                    <t:saveState value="#{workDataListBeanName.listPageinWizardBar}"/>
                    <t:saveState value="#{workDataListBeanName.addExperIntegrationpage}"/>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>