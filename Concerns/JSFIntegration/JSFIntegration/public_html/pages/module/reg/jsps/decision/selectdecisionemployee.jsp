<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/> 
 
   
 <h:form id="myForm" binding="#{decisionEmployeesMaintainBean.frm}">    
   <t:aliasBean alias="#{pageBeanName}" value="#{decisionMaintainBean}">
   <t:aliasBean alias="#{detailBeanName}" value="#{decisionEmployeesMaintainBean}">

    <t:saveState value="#{wizardbar.wizardSteps}"/>
    <t:saveState value="#{wizardbar.configurationId}"/>
    <t:saveState value="#{pageBeanName.pageDTO}"/>
    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
    <t:saveState value="#{pageBeanName.currentStep}"/>
    <t:saveState value="#{pageBeanName.maintainMode}"/>
    <t:saveState value="#{pageBeanName.cancelDecisionMode}"/>
    
    <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
    
    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
    
    <t:saveState value="#{detailBeanName.currentDetails}"/>
    <t:saveState value="#{detailBeanName.availableDetails}"/>
    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
    <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
    <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
    <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
    
    <t:saveState value="#{detailBeanName.employeeSearchDTO}"/>    
    <t:saveState value="#{detailBeanName.maritalStatusTyps}"/>
    <t:saveState value="#{detailBeanName.genderTyps}"/>
    <t:saveState value="#{detailBeanName.relgionTyps}"/>
    <t:saveState value="#{detailBeanName.governments}"/>
    <t:saveState value="#{detailBeanName.capTyps}"/>
    <t:saveState value="#{detailBeanName.specialCaseTyps}"/>
    <t:saveState value="#{detailBeanName.categories}"/>
    <t:saveState value="#{detailBeanName.ministries}"/>
    <t:saveState value="#{detailBeanName.hireTypes}"/>
    <t:saveState value="#{detailBeanName.hireStatuses}"/>
    <t:saveState value="#{detailBeanName.hireCurrentStatuses}"/>
    <t:saveState value="#{detailBeanName.technicalJobs}"/>
    <t:saveState value="#{detailBeanName.banks}"/>
    <t:saveState value="#{detailBeanName.branches}"/>
    <t:saveState value="#{detailBeanName.jobCategories}"/>
    <t:saveState value="#{detailBeanName.jobGroupes}"/>
    <t:saveState value="#{detailBeanName.currentDegrees}"/>
    <t:saveState value="#{detailBeanName.budgetTypes}"/>   
    
    <t:saveState value="#{detailBeanName.nationalties}"/>
    <t:saveState value="#{detailBeanName.resdientTypes}"/>
    <t:saveState value="#{detailBeanName.categoryID}"/>
    <t:saveState value="#{detailBeanName.ministryID}"/>
    <t:saveState value="#{detailBeanName.bankID}"/>
    <t:saveState value="#{detailBeanName.kuwityType}"/>
    <t:saveState value="#{detailBeanName.searchMode}"/>    
    
    <t:saveState value="#{detailBeanName.content1Div}"/>
    <t:saveState value="#{detailBeanName.divMainContentMany}"/>
    <t:saveState value="#{detailBeanName.pageIndexAdd}"/>
    
    <t:saveState value="#{pageBeanName.cancelDecisionMode}"/>
<f:verbatim>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/module/js/SpryTabbedPanels.js"></script>
    <link href="${shared_util.contextPath}/module/media/css/ar/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
</f:verbatim>    
    <tiles:insert definition="selectdecisionemployee.page" flush="false">
            
        
    
   
        
        </tiles:insert>    
    </t:aliasBean>
    </t:aliasBean>
  <c2:scriptGenerator form="myForm"/>
 </h:form>
</f:view>