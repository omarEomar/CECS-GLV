<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<f:view locale="#{shared_util.locale}">
  <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.hr.eos.presentation.resources.eos" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
 
 <h:form id="myForm" binding="#{employeesAddBean.frm}">
        
        <t:saveState value="#{employeesAddBean.backBtnAction}"/>
        <t:saveState value="#{employeesAddBean.backBeanName}"/>
         <t:saveState value="#{employeesAddBean.returnMethod}"/>
        <t:saveState value="#{employeesAddBean.savedData}"/>
        <t:saveState value="#{employeesAddBean.singleSelection}"/>
        <t:saveState value="#{employeesAddBean.tempCodeKeyStr}" id="tempCodeKey"/>
        <t:saveState value="#{employeesAddBean.singleSelection}"/>
        <t:saveState value="#{employeesAddBean.selectedDTOS}"/>
        
     <t:aliasBean alias="#{pageBeanName}" value="#{employeesNoSignMaintainBean}" >
     <t:aliasBean alias="#{detailBeanName}" value="#{employeesAddBean}">
              <t:saveState value="#{detailBeanName.availableDataTable}" id="availableDataTable"/>
              <t:saveState value="#{pageBeanName.pageDTO}" id="dutypagedto"/>
              <t:saveState value="#{pageBeanName.selectedPageDTO}" id="selectedpagedto"/>
              <t:saveState value="#{pageBeanName.nextNavigationCase}" id="nextncase"/>
              <t:saveState value="#{pageBeanName.previousNavigationCase}" id="prencase"/>
              <t:saveState value="#{pageBeanName.finishNavigationCase}"   id="finishncase"/>
              <t:saveState value="#{pageBeanName.currentNavigationCase}" id="currentncase"/>
              <t:saveState value="#{pageBeanName.currentStep}" id="currentstep"/>
              <t:saveState value="#{pageBeanName.maintainMode}"/>
              <t:saveState value="#{detailBeanName.currentDetails}" id="cdetails"/>
              <t:saveState value="#{detailBeanName.availableDetails}" id="adetails"/>
              <t:saveState value="#{detailBeanName.selectedCurrentDetails}" id="scdetails"/>
              <t:saveState value="#{detailBeanName.selectedAvailableDetails}" id="sadetails"/>
              <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>
               
              
              <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
           <%--   <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>--%>
              <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
              <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/> 
              
               <t:saveState value="#{detailBeanName.searchMode}"/>
               <t:saveState value="#{detailBeanName.employeeSearchDTO}"/>
              <t:saveState value="#{pageBeanName.pageIndexAdd}"/>
              
              <t:saveState value="#{detailBeanName.categoryID}"/>
              <t:saveState value="#{detailBeanName.ministryID}"/>
               <t:saveState value="#{detailBeanName.hireStatuses}"/>
              <t:saveState value="#{detailBeanName.categories}"/>
              <t:saveState value="#{detailBeanName.ministries}" />
              <t:saveState value="#{detailBeanName.workMinistries}"/>
              <t:saveState value="#{detailBeanName.hireCurrentStatuses}"/>
              <t:saveState value="#{detailBeanName.technicalJobs}"/>
              <t:saveState value="#{detailBeanName.bankID}"/>
              <t:saveState value="#{detailBeanName.banks}"/>
              <t:saveState value="#{detailBeanName.branches}"/>
              <t:saveState value="#{detailBeanName.jobCategories}"/>
              <t:saveState value="#{detailBeanName.currentDegrees}"/>
              <t:saveState value="#{detailBeanName.budgetTypes}"/>
              <t:saveState value="#{detailBeanName.kuwityType}" />
              <t:saveState value="#{detailBeanName.relgionTyps}" />
              <t:saveState value="#{detailBeanName.hireTypes}" />
              
              
       <tiles:insert definition="employeessearch.page" flush="false">
        </tiles:insert>  
        </t:aliasBean>
     </t:aliasBean>
     
  </h:form>
</f:view>  