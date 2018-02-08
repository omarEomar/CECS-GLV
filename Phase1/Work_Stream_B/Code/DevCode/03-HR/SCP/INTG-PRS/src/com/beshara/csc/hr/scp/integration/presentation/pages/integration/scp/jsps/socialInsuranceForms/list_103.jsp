<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="integrationBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <h:form id="myForm" binding="#{insurrance103FormBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{insurrance103FormBean}">
            <tiles:insert flush="false" definition="socialInsuranceForms_103.page"/>
          
    
                       
         <t:saveState value="#{insuranceFormBaseBean.signature}"/>              
        <t:saveState value="#{insuranceFormBaseBean.pageDTO}"/>
        <t:saveState value="#{insuranceFormBaseBean.months}"/>
        <t:saveState value="#{insuranceFormBaseBean.years}"/>
        <t:saveState value="#{insuranceFormBaseBean.categoryList}"/>
        <t:saveState value="#{insuranceFormBaseBean.ministryList}"/>

            <t:saveState value="#{pageBeanName.pageDTO}"/>
          

            <t:saveState value="#{pageBeanName.selectedRadio}"/>
            
            <t:saveState value="#{pageBeanName.reportUrlLink}"/>
            <t:saveState value="#{pageBeanName.reportWindowName}"/>
       
            <t:saveState value="#{pageBeanName.myTableData}"/>
        
            
        
            <t:saveState value="#{pageBeanName.singleSelection}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <!-- Start Paging -->
            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.oldPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
          
            
</t:aliasBean>

        <t:panelGroup forceId="true" id="scriptGenerator">
                    <c:scriptGenerator form="myForm" />
        </t:panelGroup>
        
    </h:form>
</f:view>
