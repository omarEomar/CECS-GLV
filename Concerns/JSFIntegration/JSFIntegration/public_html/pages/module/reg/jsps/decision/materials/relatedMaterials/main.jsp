<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

 
<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
 
    <h:form id="myForm"  binding="#{relatedDecisionMaterialsBean.frm}">
    
        <t:aliasBean alias="#{pageBeanName}" value="#{relatedDecisionMaterialsBean}" id="alias1">
            <tiles:insert definition="${decisionMaintainBean.cancelDecisionMode ? 'cancelrelatedDecisionMaterials.page' : 'relatedDecisionMaterials.page'}" flush="false">
            
                <t:saveState value="#{decisionMaintainBean.pageDTO}"/>
                <t:saveState value="#{decisionMaintainBean.nextNavigationCase}"/>
                <t:saveState value="#{decisionMaintainBean.previousNavigationCase}"/>
                <t:saveState value="#{decisionMaintainBean.finishNavigationCase}"/>
                <t:saveState value="#{decisionMaintainBean.currentNavigationCase}"/>
                <t:saveState value="#{decisionMaintainBean.currentStep}"/>
                <t:saveState value="#{decisionMaintainBean.maintainMode}"/>
                <t:saveState value="#{decisionMaintainBean.renderSave}" id="mainmode2"/>
                <t:saveState value="#{decisionMaintainBean.renderFinish}" id="mainmode3"/>
                <t:saveState value="#{decisionMaintainBean.detailsSavedStates}" id="detailsSavedStates"/>
                <t:saveState value="#{decisionMaintainBean.cancelDecisionMode}"/>
                
                <t:saveState value="#{wizardbar.wizardSteps}" id="wizardSteps"/>
                <t:saveState value="#{wizardbar.configurationId}" id="configurationId"/>
                
                <t:saveState value="#{decisionMaterialsMaintainBean.currentDetails}" />
                <t:saveState value="#{decisionMaterialsMaintainBean.availableDetails}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.selectedCurrentDetails}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.selectedAvailableDetails}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.index}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.selectedPageDTO}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.myDataTable}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.addDivShown}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.editDivShown}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.delDivShown}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.masterEntityKey}"/>
                <t:saveState value="#{decisionMaterialsMaintainBean.pageDTO}"/>
                
                
                <t:saveState value="#{decisionMaintainBean.copyDecisionWithEmployeesMode}"/>
                
                <t:saveState  value="#{decisionListBean.fullColumnName}" />
                <t:saveState  value="#{decisionListBean.sortAscending}" />
                <t:saveState  value="#{decisionListBean.saveSortingState}" />
                <t:saveState  value="#{decisionListBean.sortColumn}" />
                <t:saveState value="#{decisionListBean.indexArray}"/>
                
                <t:saveState value="#{pageBeanName.decMaterialsList}"/>
                <t:saveState value="#{pageBeanName.relatedMaterialsList}"/>
                <t:saveState value="#{pageBeanName.selectedMaterialsList}"/>
                <t:saveState value="#{pageBeanName.selectedRelMaterialsList}"/>
                <t:saveState value="#{pageBeanName.relatedKeys}"/>
                <t:saveState value="#{pageBeanName.currentMaterial}"/>
                <t:saveState value="#{pageBeanName.selectedDecision}"/>
                <t:saveState value="#{pageBeanName.initialRelatedMaterialsList}"/>
                <t:saveState value="#{pageBeanName.searchedForDec}"/>
                <t:saveState value="#{pageBeanName.relationTypes}"/>
                <t:saveState value="#{pageBeanName.lovBaseBean.onCompleteList}"/>
                <t:saveState value="#{pageBeanName.lovBaseBean.renderedDropDown}"/>
                <t:saveState value="#{pageBeanName.relatedMaterialSize}"/>
                
                <t:saveState value="#{decisionMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
            </tiles:insert>
           
        </t:aliasBean>
        
        <t:panelGroup forceId="true" id="scriptPanel">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    <script type="text/javascript"> 
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
            setFocusOnlyOnElement('myForm:selectBtn');
        }
    </script>
    </h:form>
    
</f:view>