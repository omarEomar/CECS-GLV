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
    <h:form id="myForm" binding="#{socialInsuranceFormsBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{socialInsuranceFormsBean}">
            <tiles:insert flush="false" definition="socialInsuranceForms.page"/>
           <t:saveState value="#{pageBeanName.pageDTO}"/>
         <t:saveState value="#{insuranceFormBaseBean.signature}"/>    
         <t:saveState value="#{insuranceFormBaseBean.pageDTO}"/>
         <t:saveState value="#{insuranceFormBaseBean.months}"/>
         <t:saveState value="#{insuranceFormBaseBean.years}"/>
         <t:saveState value="#{insuranceFormBaseBean.categoryList}"/>
         <t:saveState value="#{insuranceFormBaseBean.ministryList}"/>

            <t:saveState value="#{pageBeanName.salarySheets}"/>
            <t:saveState value="#{pageBeanName.salSalarySheetsDTO}"/>
            <t:saveState value="#{pageBeanName.selectedRadio}"/>
            
            <t:saveState value="#{pageBeanName.reportUrlLink}"/>
            <t:saveState value="#{pageBeanName.reportWindowName}"/>
            <%--<t:saveState value="#{pageBeanName.paymentOrderDetailsList}"/>--%>
            <t:saveState value="#{pageBeanName.totalEmpsCount}"/>
            <t:saveState value="#{pageBeanName.totalEmpsMoney}"/>
            <%--<t:saveState value="#{pageBeanName.paymentOrderDetailsListSize}"/>--%>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.finalMessage}"/>
            
            <t:saveState value="#{pageBeanName.accountNo}"/>
            <t:saveState value="#{pageBeanName.accountNoList}"/>
            <%--<t:saveState value="#{pageBeanName.selDedToMinCode}"/>--%>
            <%--<t:saveState value="#{pageBeanName.selDedToMin}"/>--%>
            <%--<t:saveState value="#{pageBeanName.reciever}"/>--%>
            <%--<t:saveState value="#{pageBeanName.signature}"/>--%>
            <t:saveState value="#{pageBeanName.signatureStr}"/>
            <t:saveState value="#{pageBeanName.signature1}"/>
            <t:saveState value="#{pageBeanName.signatureStr1}"/>
            <!-- added by nora to enable single selection -->
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
            <t:saveState value="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}"/>
            
</t:aliasBean>

        <t:panelGroup forceId="true" id="scriptGenerator">
                    <c:scriptGenerator form="myForm" />
        </t:panelGroup>
        
    </h:form>
</f:view>
