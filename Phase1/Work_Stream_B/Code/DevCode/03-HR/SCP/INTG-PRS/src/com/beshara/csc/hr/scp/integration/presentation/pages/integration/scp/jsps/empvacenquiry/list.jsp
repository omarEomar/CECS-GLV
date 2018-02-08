<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}"> 
    <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="scpIntgResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions"  var="globalExceptions"/>
    <h:form id="myForm" binding="#{empVacMonSalEnquiryListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{empVacMonSalEnquiryListBean}">
        
            <tiles:insert flush="false" definition="empvacmonsalenquiry.page"/>
                <t:saveState value="#{pageBeanName.validCivilId}"/>
                <t:saveState value="#{pageBeanName.civilExist}"/>
                <t:saveState value="#{pageBeanName.realCivilId}"/>
                <t:saveState value="#{pageBeanName.civilIdNotReal}"/>
                <t:saveState value="#{pageBeanName.empHired}"/>
                <t:saveState value="#{pageBeanName.empHiredInMin}"/>
                <t:saveState value="#{pageBeanName.payrollInfoExist}"/>
                <t:saveState value="#{pageBeanName.salEmpSalaryElementsDTO}"/>
                <t:saveState value="#{pageBeanName.degree}"/>
                <t:saveState value="#{pageBeanName.employeeHelper}" id="employeeHelper1"/>
                <t:saveState value="#{pageBeanName.comeFromSalReveiw}"/>
                <t:saveState value="#{pageBeanName.savedDataObjects}"/>
                <t:saveState value="#{pageBeanName.navigationBack}"/>
                <t:saveState value="#{pageBeanName.salaryMonth}"/>
                <t:saveState value="#{pageBeanName.yearCode}"/>
                <t:saveState value="#{pageBeanName.sharedNavigationBack}"/>
             
               <t:saveState value="#{pageBeanName.reducedSettVac}"/>
               <t:saveState value="#{pageBeanName.serviceEnded}"/>
              
          </t:aliasBean>
    </h:form>
     <script type="text/javascript">

     </script>
     
     <t:panelGroup forceId="true" id="scriptGenerator">
        <c:scriptGenerator form="myForm" />
    </t:panelGroup>
</f:view>
