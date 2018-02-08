<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>

 
 <h:form id="myForm" binding="#{settlementDecisionCycleMainDataMaintainBean.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{settlementDecisionCycleMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{settlementDecisionCycleMainDataMaintainBean}">
    
    <tiles:insert definition="settlementdecisioncyclemaindatamaintainsuggestion.page" flush="false">
        
        <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 ? 'addSettlementDecisions' : pageBeanName.maintainMode==1 ? 'editSettlementDecisions' : pageBeanName.maintainMode==2 ? 'settlementDecision' : ''}"></tiles:put>        
        <t:saveState value="#{detailBeanName.invalidDateErr}"/>
       
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
        <t:saveState value="#{pageBeanName.dataLoadedBefore}"/> 
        <t:saveState value="#{pageBeanName.lisBeanName}"/>
        <t:saveState value="#{pageBeanName.backMethodName}"/> 
         <t:saveState value="#{pageBeanName.backNavCase}"/>
         <t:saveState value="#{pageBeanName.showOnly}"/>
        <%--<t:saveState value="#{pageBeanName.cancelDecisionMode}"/>--%>
        <%--<t:saveState value="#{decisionCycleMaintainBean.showOnly}"/>--%>
        
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
        <t:saveState value="#{detailBeanName.statusDTOKey}"/> 
        <t:saveState value="#{detailBeanName.firstSubject}"/>
        <t:saveState value="#{detailBeanName.issuanceYears}" id="issuanceYears"/>
        <t:saveState value="#{detailBeanName.subjects}" id="subjects"/>
        <t:saveState value="#{detailBeanName.decisionMakers}" id="decisionMakers"/>
        <%--<t:saveState value="#{detailBeanName.templates}" id="templates"/>--%>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValue}" id="itemSelectionRequiredValue"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValueString}" id="itemSelectionRequiredValueString"/>
        <t:saveState value="#{detailBeanName.searchMode}" id="searchMode"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.typesDTOKey}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
        
        
        <t:saveState value="#{detailBeanName.returnFromEditor}"/>
        
        <t:saveState value="#{detailBeanName.secWorkCenterUsersSearchDTO}"/>
      
        <t:saveState value="#{detailBeanName.workCentersDTOList}"/> 
        <%--<t:saveState value="#{detailBeanName.pageDtoBuffer}"/>--%> 
        <t:saveState value="#{detailBeanName.divflag}"/> 
        <%--<t:saveState value="#{decisionCycleMainDataMaintainBean.editorReturn}" />--%>
        

<t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.availableDetails}"/>
<t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.selectedCurrentDetails}"/>
<t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.selectedAvailableDetails}"/>

        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.currentDetails}"/> 
       
        <%--t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.currentLoadedDecision}"/> 
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.benfitsList}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.benifitList2}"/>
            <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.deductionsList}"/>
            <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.deductionsList2}"/>
            <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.benfitsListSize}"/>
            <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.deductionsListSize}"/>
            
            
                     <%--t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.benfitsTotalValue}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.dedectionsTotalValue}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.totalSalary}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.removedSalElementGuideList}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.disableMonthAndYear}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.dataReadyToBeRemoved}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.editSearchDate}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.initialListForBenefitsAndDeductions}"/>
             
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.monthKey}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.yearsKey}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.settMonthKey}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.settYearsKey}"/--%>
            
            <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.employeesDTO}"/>
            <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.civilID}"/>

             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.showPagingBar}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.empName}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.validCivilId}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.civilExist}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.empHired}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.empHiredInMin}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.payrollInfoExist}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.resetButton}"/>

        <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{settlementDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{settlementDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{settlementDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{settlementDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{settlementDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{settlementDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{settlementDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{settlementDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{settlementDecisionsListBean.sorting}"/>
            <t:saveState value="#{settlementDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{settlementDecisionsListBean.indexArray}"/>
            <t:saveState value="#{settlementDecisionsListBean.ascending}"/>
            <t:saveState value="#{settlementDecisionsListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{settlementDecisionsListBean.selectedTypeKey}"/>
            <t:saveState value="#{settlementDecisionsListBean.searchMode}"/>
        <%-- // End Apply Business_Pagging ..--%>  
       

            <t:saveState value="#{detailBeanName.formsList}"/>
            <t:saveState value="#{detailBeanName.selectedForm}"/>
            <t:saveState value="#{detailBeanName.selectedFormRadio}" />
            <t:saveState value="#{detailBeanName.formsSearchMode}" />
            <t:saveState value="#{detailBeanName.ascendingForm}" />
            <t:saveState value="#{detailBeanName.sortFormColumn}" />
        
       
         <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.civil_exist}"/>
            
             

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
