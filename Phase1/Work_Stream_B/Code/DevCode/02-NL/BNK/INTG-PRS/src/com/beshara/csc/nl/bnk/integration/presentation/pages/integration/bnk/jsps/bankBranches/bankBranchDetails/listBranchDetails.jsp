<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
<h:form id="myForm" binding="#{branchDetailsListBean.frm}">
    <f:loadBundle basename="com.beshara.csc.nl.bnk.integration.presentation.resources.integration"
                  var="integrationBundle"/>
    <t:aliasBean alias="#{pageBeanName}" value="#{branchDetailsListBean}">
    
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
            
            <t:saveState value="#{pageBeanName.branchDetailsList}" id ="branchDetailsList"/>
            <t:saveState value="#{pageBeanName.bankBranchDTO}" id ="bankBranchDTO"/>

            
            <t:saveState value="#{branchesListBean.branchesList}" id ="branchesList"/>
            <t:saveState value="#{branchesListBean.bankDTO}" id ="bankDTO"/>
            <t:saveState value="#{branchesListBean.myTableData}"/>
            
            <t:saveState value="#{branchesListBean.selectedRadio}" id ="bselectedRadio"/>
            <t:saveState value="#{branchesListBean.selectedListSize}"/>
            <t:saveState value="#{branchesListBean.selectedDTOS}"/>
            
            <t:saveState value="#{pageBeanName.civilId}" id ="civilId"/>
            <t:saveState value="#{pageBeanName.civilEmail}" id ="civilEmail"/>
            <t:saveState value="#{pageBeanName.civilTel}" id ="civilTel"/>
            <t:saveState value="#{pageBeanName.civilMobile}" id ="civilMobile"/>
            <t:saveState value="#{pageBeanName.civilFax}" id ="civilFax"/>
            <t:saveState value="#{pageBeanName.kwtCitizenDTO}" id ="kwtCitizenDTO"/>
            <t:saveState value="#{pageBeanName.validCivilId}" id ="validCivilId"/>     
            <t:saveState value="#{pageBeanName.enableSave}" id ="enableSave"/>     
                        
            <tiles:insert flush="false" definition="branchDetails.page">
            </tiles:insert>            
            
    </t:aliasBean>
      <t:panelGroup forceId="true" id="scriptPanelID">
      <c2:scriptGenerator form="myForm"/>
</t:panelGroup>    
</h:form>
</f:view>
