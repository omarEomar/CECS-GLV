<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{societyAddBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.map.presentation.resources.map" var="resourcesBundle"/>
         <f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration" var="orgIntgResources"/>
         <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{societyAddBean}">
        <t:aliasBean alias="#{minHelperBeanName}" value="#{societyAddBean.ministryHelper}">
            <tiles:insert flush="false" definition="societyAddListPage.page">
              <t:saveState value="#{societyAddBean.ministryHelper}"/>
              <t:saveState value="#{societyAddBean.showCenterPanel}"/>
              <t:saveState value="#{societyAddBean.ministriesDTO}"/>
              <t:saveState value="#{societyAddBean.ministryCode}"/>
              <t:saveState value="#{societyAddBean.societyName}"/>
              <t:saveState value="#{societyAddBean.selectedRadioValue}"/>
              <t:saveState value="#{societiesListBean.selectedTypeCode}"/>
              <t:saveState value="#{societiesListBean.myTableData}"/>
            </tiles:insert>
            <t:panelGroup forceId="true" id="scriptPanelID">
                   <c2:scriptGenerator form="myForm"/>
                </t:panelGroup>
        </t:aliasBean>
        </t:aliasBean>
    </h:form>
</f:view>