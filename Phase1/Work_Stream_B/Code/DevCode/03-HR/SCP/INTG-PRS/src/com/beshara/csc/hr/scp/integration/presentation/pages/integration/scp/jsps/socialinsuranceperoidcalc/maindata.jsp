<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>


    <f:view locale="#{shared_util.locale}">
        <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="resourcesBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
        <h:form id="myForm" binding="#{socialInsurPeroidCalcMainDataBean.frm}">
            <t:aliasBean alias="#{pageBeanName}" value="#{socialInsurPeroidCalcMainDataBean}">
            <tiles:insert flush="false" definition="socialInsurPeroidCalcMainData.page"/>
             <t:saveState value="#{socialInsurPeroidCalcMaintainBean.peroidCalcInsurDTO}" />  
                    
            <t:saveState value="#{pageBeanName.employeeHelper}" />
            <t:saveState value="#{pageBeanName.realCivilId}"/>
            <t:saveState value="#{pageBeanName.empDTO}"/>
            <t:saveState value="#{pageBeanName.civilId}"/>    
            
            <t:saveState value="#{pageBeanName.civilExist}" />   
            <t:saveState value="#{pageBeanName.validCivilId}"/>
            
             <t:saveState value="#{pageBeanName.enableEmpLovDiv}" />
            <t:saveState value="#{pageBeanName.payrollInfoExist}"/>
            <t:saveState value="#{pageBeanName.empHired}"/>
            <t:saveState value="#{pageBeanName.regNo}"/>    
            
            <t:saveState value="#{pageBeanName.catCode}" />   
            <t:saveState value="#{pageBeanName.minCode}"/>
            
            <t:saveState value="#{pageBeanName.ministryList}" />   
            <t:saveState value="#{pageBeanName.categoryList}"/>
         
            
            <t:saveState value="#{pageBeanName.fromDate}" />   
            <t:saveState value="#{pageBeanName.todate}"/>
            
             <t:saveState value="#{pageBeanName.calcTemplateType}" />
            <t:saveState value="#{pageBeanName.peroidPerMonth}"/>
            <t:saveState value="#{pageBeanName.yearPerMonth}"/>
            <t:saveState value="#{pageBeanName.tableNo}"/>    
            
            <t:saveState value="#{pageBeanName.yearPeroidInstallmentValue}" />   
            <t:saveState value="#{pageBeanName.installmentValueDK}"/>
            
            <t:saveState value="#{pageBeanName.totalPaymentValue}" />   
         
            <t:saveState value="#{pageBeanName.empHasInsurrance}"/>
     
            
            
            
            
            </t:aliasBean>
            <t:panelGroup forceId="true" id="scriptGenerator">
                <c:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </f:view>

