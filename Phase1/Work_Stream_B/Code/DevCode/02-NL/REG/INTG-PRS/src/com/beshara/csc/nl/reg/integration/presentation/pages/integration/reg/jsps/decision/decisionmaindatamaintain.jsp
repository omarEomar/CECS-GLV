<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>

 
 <h:form enctype="multipart/form-data" id="myForm" binding="#{decisionMainDataMaintainBean.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{decisionMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{decisionMainDataMaintainBean}">
    
    <tiles:insert definition="${pageBeanName.cancelDecisionMode ? 'canceldecisionmaindatamaintain.page' : 'decisionmaindatamaintain.page'}" flush="false">
        
        <tiles:put name="titlepage" type="string" value="${!pageBeanName.cancelDecisionMode ? 'decision_master_title' : 'modified_decision'}"></tiles:put>        
       
        <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
        <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
        <t:saveState value="#{pageBeanName.previousNavigationCase}"  id="mainpstep"/>
        <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
        <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
        <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
        <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
        <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
        <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
        <t:saveState value="#{pageBeanName.cancelDecisionMode}"/>
        
        <t:saveState value="#{detailBeanName.typesDTOKey}"/>
        <t:saveState value="#{detailBeanName.yearsDTOKey}"/>
        <t:saveState value="#{detailBeanName.currentDetails}"/>
        <t:saveState value="#{detailBeanName.availableDetails}"/>
        <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
        <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
        <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
       
        <t:saveState value="#{detailBeanName.types}" id="types"/>
        <t:saveState value="#{detailBeanName.issuanceYears}" id="issuanceYears"/>
        <t:saveState value="#{detailBeanName.subjects}" id="subjects"/>
        <t:saveState value="#{detailBeanName.decisionMakers}" id="decisionMakers"/>
        <t:saveState value="#{detailBeanName.templates}" id="templates"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValue}" id="itemSelectionRequiredValue"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValueString}" id="itemSelectionRequiredValueString"/>
        <t:saveState value="#{detailBeanName.searchMode}" id="searchMode"/>
        <t:saveState value="#{decisionMainDataMaintainBean.typesDTOKey}"/>
        <t:saveState value="#{decisionMainDataMaintainBean.yearsDTOKey}"/> 
        
        
        <t:saveState value="#{detailBeanName.dateDisabled}"/>
        <t:saveState value="#{decisionEmployeesMaintainBean.showBarMainData}"/>  
        <t:saveState value="#{decisionMainDataMaintainBean.showLovDivFlag}"/>
        <t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>
        
        <t:saveState  value="#{decisionListBean.fullColumnName}" />
        <t:saveState  value="#{decisionListBean.sortAscending}" />
        <t:saveState  value="#{decisionListBean.saveSortingState}" />
        <t:saveState  value="#{decisionListBean.sortColumn}" />
        <t:saveState value="#{decisionListBean.indexArray}"/>
        <t:saveState value="#{decisionMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
    </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
    <c2:scriptGenerator form="myForm"/>
 </h:form>
</f:view>