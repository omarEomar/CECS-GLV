<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

 
 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
 
<h:form id="myForm"  binding="#{decisionMaterialsMaintainBean.frm}">

    <t:aliasBean alias="#{pageBeanName}" value="#{decisionMaintainBean}" id="alias1">
    <t:aliasBean alias="#{detailBeanName}" value="#{decisionMaterialsMaintainBean}" id="alias2">
       
        <t:saveState value="#{wizardbar.wizardSteps}" id="wizardSteps"/>
        <t:saveState value="#{wizardbar.configurationId}" id="configurationId"/>
        
        <t:saveState value="#{pageBeanName.pageDTO}"/>
        <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
        <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
        <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
        <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
        <t:saveState value="#{pageBeanName.currentStep}"/>
        <t:saveState value="#{pageBeanName.maintainMode}"/>
        <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
        <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
        <t:saveState value="#{pageBeanName.cancelDecisionMode}"/>
        
        <t:saveState value="#{detailBeanName.currentDetails}" />
        <t:saveState value="#{detailBeanName.availableDetails}"/>
        <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
        <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
        <t:saveState value="#{detailBeanName.index}"/>
        <t:saveState value="#{detailBeanName.pageDTO}"/>
        <t:saveState value="#{detailBeanName.selectedPageDTO}"/>
        <t:saveState value="#{detailBeanName.myDataTable}"/>
        <t:saveState value="#{detailBeanName.addDivShown}"/>
        <t:saveState value="#{detailBeanName.editDivShown}"/>
        <t:saveState value="#{detailBeanName.delDivShown}"/>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>
        
        <t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>
        
        <t:saveState  value="#{decisionListBean.fullColumnName}" />
        <t:saveState  value="#{decisionListBean.sortAscending}" />
        <t:saveState  value="#{decisionListBean.saveSortingState}" />
        <t:saveState  value="#{decisionListBean.sortColumn}" />
        <t:saveState value="#{decisionListBean.indexArray}"/>
        <t:saveState value="#{decisionMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>

        <tiles:insert definition="${pageBeanName.cancelDecisionMode ? 'canceldecMaterialsMaintain.page' : 'decMaterialsMaintain.page'}" flush="false">
            <tiles:put name="titlepage" type="string" value="${!pageBeanName.cancelDecisionMode ? 'decision_master_title' : 'decision_cancellation'}"></tiles:put>
        </tiles:insert>
       
    </t:aliasBean>
    </t:aliasBean>
    
    <t:panelGroup forceId="true" id="scriptPanel">
        <c:scriptGenerator form="myForm"/>
    </t:panelGroup>
    <script type="text/javascript"> 
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
            setFocusOnElement('addButton');
        }
    </script>
  </h:form>
</f:view>