<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}"> 
    <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="scpIntegrationBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions"  var="globalExceptions"/>
    <h:form id="myForm" binding="#{empVacSettlmentEnquiryHelperBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{empVacSettlmentEnquiryHelperBean}">
        
            <tiles:insert flush="false" definition="empvacsettlmentenquirylist.page"/>
            
            
              
            <t:saveState value="#{pageBeanName.validCivilId}"/>
            <t:saveState value="#{pageBeanName.civilExist}"/>
            <t:saveState value="#{pageBeanName.dateDisable}"/>
            <t:saveState value="#{pageBeanName.civilId}"/>
            <t:saveState value="#{pageBeanName.civilIdNotReal}"/>
            <t:saveState value="#{pageBeanName.empHired}"/>
            <t:saveState value="#{pageBeanName.empHiredInMin}"/>
            <t:saveState value="#{pageBeanName.payrollInfoExist}"/>
            <t:saveState value="#{pageBeanName.degree}"/>
            <t:saveState value="#{pageBeanName.salEmpSalaryElementsDTO}"/>
            <t:saveState value="#{pageBeanName.months}"/>
            <t:saveState value="#{pageBeanName.years}"/>
            <t:saveState value="#{pageBeanName.month}"/>
            <t:saveState value="#{pageBeanName.year}"/>
            <t:saveState value="#{pageBeanName.enableCancelSetlment}"/>
            <t:saveState value="#{pageBeanName.employeeHelper}" id="employeeHelper1"/>
            <t:saveState value="#{pageBeanName.vacList}"/>
            <t:saveState value="#{pageBeanName.vacSelectedRadio}"/>
            <t:saveState value="#{pageBeanName.selSalEmpSettelmentsDTO}"/>
            <t:saveState value="#{pageBeanName.comeFromRetroactiveSettDiv}"/>
            <t:saveState value="#{pageBeanName.sharedNavigationBack}"/>
            
<t:saveState value="#{pageBeanName.checkAllFlag}"/>
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
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
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<t:saveState value="#{pageBeanName.totalSettlmentValue}"  />
<!-- End Paging -->

        </t:aliasBean>
    </h:form>
     <script type="text/javascript">

     </script>
     
     <t:panelGroup forceId="true" id="scriptGenerator">
        <c:scriptGenerator form="myForm" />
    </t:panelGroup>
</f:view>