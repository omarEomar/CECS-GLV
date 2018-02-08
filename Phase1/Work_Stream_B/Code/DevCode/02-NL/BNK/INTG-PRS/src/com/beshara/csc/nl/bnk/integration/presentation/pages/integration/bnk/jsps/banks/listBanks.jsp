<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>


<f:view locale="#{shared_util.locale}">
<h:form id="myForm" binding="#{banksListBean.frm}">
    <f:loadBundle basename="com.beshara.csc.nl.bnk.integration.presentation.resources.integration"
                  var="integrationBundle"/>
    <t:aliasBean alias="#{pageBeanName}" value="#{banksListBean}">
    
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>    
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>        
            <t:saveState value="#{pageBeanName.selectedListSize}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            
            <t:saveState value="#{pageBeanName.success}" id="success"/>
            <t:saveState value="#{pageBeanName.showErrorMsg}" id ="showErrorMsg"/>
            <t:saveState value="#{pageBeanName.showErrorMsg}" id ="newErrMsgValue"/>
            
            <t:saveState value="#{pageBeanName.bankName}" id ="bankName"/>
            <t:saveState value="#{pageBeanName.bankEmail}" id ="bankEmail"/>
            
            <tiles:insert flush="false" definition="banks.page">
            </tiles:insert>            
            
    </t:aliasBean>
</h:form>
    <t:panelGroup forceId="true" id="scriptGeneratorPanel">
        <c:scriptGenerator form="myForm"/>
     </t:panelGroup>
</f:view>
