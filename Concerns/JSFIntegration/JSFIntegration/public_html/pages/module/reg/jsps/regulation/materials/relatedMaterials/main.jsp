<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

 
<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
 
    <h:form id="myForm"  binding="#{relatedMaterialsBean.frm}">
    
        <t:aliasBean alias="#{pageBeanName}" value="#{relatedMaterialsBean}" id="alias1">
    
            <tiles:insert definition="relatedMaterials.page" flush="false">
             <tiles:put name="titlepage" type="string" value="${regulationMaintainBean.maintainMode==0 || regulationMaintainBean.copyFlage==true ? 'linkRelatedMaterials' : (regulationMaintainBean.maintainMode==2 || regulationMaintainBean.copyFlage==false ? 'view_linkRelatedMaterials' : 'linkRelatedMaterials')}"></tiles:put>
                <t:saveState value="#{regulationMaintainBean.pageDTO}" />
                <t:saveState value="#{regulationMaintainBean.nextNavigationCase}" />
                <t:saveState value="#{regulationMaintainBean.previousNavigationCase}" />
                <t:saveState value="#{regulationMaintainBean.finishNavigationCase}" />
                <t:saveState value="#{regulationMaintainBean.currentNavigationCase}" />
                <t:saveState value="#{regulationMaintainBean.currentStep}"/>
                <t:saveState value="#{regulationMaintainBean.maintainMode}"/>
                <t:saveState value="#{regulationMaintainBean.renderSave}" />
                <t:saveState value="#{regulationMaintainBean.renderFinish}" />
                <t:saveState value="#{regulationMaintainBean.detailsSavedStates}" id="detailsSavedStates"/>
                
                <t:saveState value="#{wizardbar.wizardSteps}" id="wizardSteps"/>
                <t:saveState value="#{wizardbar.configurationId}" id="configurationId"/>
                
                <t:saveState value="#{regulationMaterialsMaintainBean.currentDetails}" />
                <t:saveState value="#{regulationMaterialsMaintainBean.availableDetails}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.selectedCurrentDetails}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.selectedAvailableDetails}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.index}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.selectedPageDTO}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.myDataTable}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.addDivShown}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.editDivShown}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.delDivShown}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.masterEntityKey}"/>
                <t:saveState value="#{regulationMaterialsMaintainBean.pageDTO}"/>
                
                <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
                <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
                
                <t:saveState value="#{regulationListBean.fullColumnName}"/>
                <t:saveState value="#{regulationListBean.sortAscending}"/>
                <t:saveState value="#{regulationListBean.saveSortingState}" />
                <t:saveState value="#{regulationListBean.sortColumn}"/>
                <t:saveState value="#{regulationListBean.indexArray}"/>
                <t:saveState value="#{regulationListBean.regulationSearchDTO}"/>
                
                <t:saveState value="#{pageBeanName.regMaterialsList}"/>
                <t:saveState value="#{pageBeanName.relatedMaterialsList}"/>
                <t:saveState value="#{pageBeanName.selectedMaterialsList}"/>
                <t:saveState value="#{pageBeanName.selectedRelMaterialsList}"/>
                <t:saveState value="#{pageBeanName.relatedKeys}"/>
                <t:saveState value="#{pageBeanName.currentMaterial}"/>
                <t:saveState value="#{pageBeanName.selectedRegulation}"/>
                <t:saveState value="#{pageBeanName.initialRelatedMaterialsList}"/>
                <t:saveState value="#{pageBeanName.searchedForReg}"/>
                <t:saveState value="#{pageBeanName.relationTypes}"/>
                <t:saveState value="#{pageBeanName.lovBaseBean.onCompleteList}"/>
                <t:saveState value="#{pageBeanName.lovBaseBean.renderedDropDown}"/>
                <t:saveState value="#{relatedMaterialsBean.relatedMaterialSize}"/>
                
                <t:saveState value="#{regulationMaintainBean.initializeTablesTab}"/> 
                <t:saveState value="#{regulationMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
                <t:saveState value="#{regulationMaintainBean.copyFlage}" id="copyFlageId"/>
                
            </tiles:insert>
           
        </t:aliasBean>
        
        <t:panelGroup forceId="true" id="scriptPanel">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    
    </h:form>
    
</f:view>