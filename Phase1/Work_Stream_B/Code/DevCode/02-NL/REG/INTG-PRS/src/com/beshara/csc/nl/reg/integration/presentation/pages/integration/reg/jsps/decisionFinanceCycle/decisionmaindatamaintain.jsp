<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>

 
 <h:form id="myForm" binding="#{financeDecisionCycleMainDataMaintainBean.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{financeDecisionCycleMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{financeDecisionCycleMainDataMaintainBean}">
    
    <tiles:insert definition="financedecisioncyclemaindatamaintainsuggestion.page" flush="false">
        
       <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 ? 'addRegVariedDecisions' : 'editRegVariedDecisions'}"></tiles:put>       
       
        <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
        <t:saveState value="#{pageBeanName.multiOrSingleEmps}"/>
        <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
        <t:saveState value="#{pageBeanName.previousNavigationCase}"  id="mainpstep"/>
        <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
        <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
        <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
        <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
        <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
        <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
        <%--<t:saveState value="#{pageBeanName.cancelDecisionMode}"/>--%>
        <%--<t:saveState value="#{decisionCycleMaintainBean.showOnly}"/>--%>
          <t:saveState value="#{pageBeanName.showOnly}"/>
        <t:saveState value="#{detailBeanName.typesDTOKey}"/>
        <t:saveState value="#{detailBeanName.yearsDTOKey}"/>
        <t:saveState value="#{detailBeanName.currentDetails}"/>
        <t:saveState value="#{detailBeanName.availableDetails}"/>
        <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
        <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
        <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
       <t:saveState value="#{detailBeanName.decMkrDisabled}" id="DecMkrDisabled"/>    
        <t:saveState value="#{detailBeanName.types}" id="types"/>
        <t:saveState value="#{detailBeanName.issuanceYears}" id="issuanceYears"/>
        <t:saveState value="#{detailBeanName.subjects}" id="subjects"/>
        <t:saveState value="#{detailBeanName.statusDTOKey}"/>
        <t:saveState value="#{detailBeanName.firstSubject}"/>
        <t:saveState value="#{detailBeanName.decisionMakers}" id="decisionMakers"/>
        <%--<t:saveState value="#{detailBeanName.templates}" id="templates"/>--%>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValue}" id="itemSelectionRequiredValue"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValueString}" id="itemSelectionRequiredValueString"/>
        <t:saveState value="#{detailBeanName.searchMode}" id="searchMode"/>
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.typesDTOKey}"/>
            <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
        
        <%--<t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>--%>
        
        <t:saveState  value="#{financialDecisionsListBean.fullColumnName}" />
        <t:saveState  value="#{financialDecisionsListBean.sortAscending}" />
        <t:saveState  value="#{financialDecisionsListBean.saveSortingState}" />
        <t:saveState  value="#{financialDecisionsListBean.sortColumn}" />
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentListSize}"/>
            <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{financialDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{financialDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{financialDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.sorting}"/>
            <t:saveState value="#{financialDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{financialDecisionsListBean.indexArray}"/>
            <t:saveState value="#{financialDecisionsListBean.ascending}"/>
            <t:saveState value="#{financialDecisionsListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.selectedTypeKey}"/>
            <t:saveState value="#{financialDecisionsListBean.searchMode}"/>
            <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.civilID}"/>
            <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.empFullName}"/>
            <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.selectedCurrentDetails}"/>
        <%-- // End Apply Business_Pagging ..--%>
        
        <t:saveState  value="#{financeDecisionCycleEmployeesMaintainBean.elementGuideCode}" />
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentDisplayedSalVariedElmGuideDTOList}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentSalVariedElmGuideDTOList}"/>
        <t:saveState  value="#{financeDecisionCycleEmployeesMaintainBean.salVariedElmGuidesDataTable}"/>
        <%--<t:saveState value="#{financialDecisionsListBean.cancelDescisionFlag}"/>--%>
        <%--<t:saveState value="#{decisionCycleMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>--%>
        <%--<t:saveState value="#{detailBeanName.showMatrialMsg}"/>--%>
        <t:saveState value="#{detailBeanName.returnFromEditor}"/>
        
        <%--t:saveState value="#{detailBeanName.secWorkCenterUsersSearchDTO}"/>
      
        <t:saveState value="#{detailBeanName.workCentersDTOList}"/--%> 
        <%--<t:saveState value="#{detailBeanName.pageDtoBuffer}"/>--%> 
        <t:saveState value="#{detailBeanName.divflag}"/> 
        <%--<t:saveState value="#{decisionCycleMainDataMaintainBean.editorReturn}" />--%> 
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentDetails}"/> 
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.enableElementGuideCombo}"/>
        
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.usingPaging}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.resettedPageIndex}"/>
        
        
      
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.pagingRequestDTO}"/>

        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.pagingBean.totalListSize}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.pagingBean.myPagedDataModel}"/>
        
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentDisplayDetails}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.countOfAllEmpDecision}"/>
  
        
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.civil_exist}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.selectedElementGuideCode}"/>
        <%--<t:saveState value="#{financialDecisionsListBean.selectedRegTypeKey}"/>--%>
        <%--<t:saveState value="#{financialDecisionsListBean.decisionSearchDTO}"/>--%>
            
            

        <%--t:saveState value="#{detailBeanName.formsList}"/>
        <t:saveState value="#{detailBeanName.selectedForm}"/>
        <t:saveState value="#{detailBeanName.selectedFormRadio}" />
        <t:saveState value="#{detailBeanName.formsSearchMode}" />
        <t:saveState value="#{detailBeanName.ascendingForm}" />
        <t:saveState value="#{detailBeanName.sortFormColumn}" /--%>
        
        
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.totalValue}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.selectedElementGuideName}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.validElmguide}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.showErrorMsg1}"/> 
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.selectedElementGuideCode}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.month}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.year}"/>
        
        
        <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{financialDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{financialDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{financialDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.sorting}"/>
            <t:saveState value="#{financialDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{financialDecisionsListBean.indexArray}"/>
            <t:saveState value="#{financialDecisionsListBean.ascending}"/>
            <t:saveState value="#{financialDecisionsListBean.updateMyPagedDataModel}"/>
        <%-- // End Apply Business_Pagging ..--%>
    </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
 <t:panelGroup id="scriptGeneratorId" forceId="true">    
    <c2:scriptGenerator form="myForm"/>
</t:panelGroup>
<script type="text/javascript">   
    
     
 </script>
 
 </h:form>
</f:view>

