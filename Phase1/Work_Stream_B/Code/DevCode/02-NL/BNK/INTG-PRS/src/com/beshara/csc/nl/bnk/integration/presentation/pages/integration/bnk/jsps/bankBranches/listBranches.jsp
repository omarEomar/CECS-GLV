<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"
           prefix="c2"%>

<f:view locale="#{shared_util.locale}">
<h:form id="myForm" binding="#{branchesListBean.frm}">
    <f:loadBundle basename="com.beshara.csc.nl.bnk.integration.presentation.resources.integration"
                  var="integrationBundle"/>
    <t:aliasBean alias="#{pageBeanName}" value="#{branchesListBean}">
    
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
            <t:saveState value="#{pageBeanName.errorMsg}" id ="errorMsg"/>
            
            <t:saveState value="#{pageBeanName.branchesList}" id ="branchesList"/>
            
            
            <t:saveState value="#{pageBeanName.bankDTO}" id ="bankDTO"/>
            <t:saveState value="#{pageBeanName.branchDTO}" id ="branchDTO"/>
            
            <t:saveState value="#{banksListBean.selectedRadio}" id ="bselectedRadio"/>
            <t:saveState value="#{banksListBean.myTableData}"/>
            <t:saveState value="#{banksListBean.highlightedDTOS}"/>
            <t:saveState value="#{banksListBean.searchMode}"/>
            <t:saveState value="#{banksListBean.selectedDTOS}"/>
            <t:saveState value="#{banksListBean.pageDTO}"/>    
            <t:saveState value="#{banksListBean.selectedPageDTO}"/>        
            <t:saveState value="#{banksListBean.selectedListSize}"/>
            <t:saveState value="#{banksListBean.fullColumnName}"/>
            <t:saveState value="#{banksListBean.sortAscending}"/>
            
            <tiles:insert flush="false" definition="branches.page">
            </tiles:insert>            
            
    </t:aliasBean>
      <t:panelGroup forceId="true" id="scriptPanelID">
      <c2:scriptGenerator form="myForm"/>
</t:panelGroup>
</h:form>
</f:view>
