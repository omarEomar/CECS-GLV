<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions"
                  var="globalexceptions"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration"
                  var="grsIntgResources"/>
    <h:form id="myForm" binding="#{exceptionsAddBean.frm}">
        <t:aliasBean alias="#{wcIntegrationBeanName}" value="#{exceptionsAddBean.wcIntegrationBean}">
            <t:aliasBean alias="#{pageBeanName}" value="#{exceptionsAddBean}">
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.deleteStatusDTOS}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.searchEmpResult}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.searchTypeFlag}"/>
                <t:saveState value="#{pageBeanName.resultTableStyleClass}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.conditionsList}"/>
                <t:saveState value="#{pageBeanName.selectedCategoryId}"/>
                <t:saveState value="#{pageBeanName.categoriesList}"/>
                <t:saveState value="#{pageBeanName.selectedMinistryId}"/>
                <t:saveState value="#{pageBeanName.ministriesList}"/>
                <t:saveState value="#{pageBeanName.selectedWorkCenterId}"/>
                <t:saveState value="#{pageBeanName.selectedWorkCenterName}"/>
                <t:saveState value="#{pageBeanName.selectedCaderId}"/>
                <t:saveState value="#{pageBeanName.caderList}"/>
                <t:saveState value="#{pageBeanName.selectedGroupId}"/>
                <t:saveState value="#{pageBeanName.groupsList}"/>
                <t:saveState value="#{pageBeanName.selectedDegreeId}"/>
                <t:saveState value="#{pageBeanName.degreesList}"/>
                <t:saveState value="#{pageBeanName.selectedRaiseId}"/>
                <t:saveState value="#{pageBeanName.raisesList}"/>
                <t:saveState value="#{pageBeanName.selectedBgtProgramId}"/>
                <t:saveState value="#{pageBeanName.bgtProgramsList}"/>
                <t:saveState value="#{pageBeanName.selectedBgtTypeId}"/>
                <t:saveState value="#{pageBeanName.bgtTypesList}"/>
                <t:saveState value="#{pageBeanName.selectedExeReasonKey}"/>
                <t:saveState value="#{pageBeanName.exceptionReasonList}"/>
                <t:saveState value="#{pageBeanName.selectedAppModuleKey}"/>
                <t:saveState value="#{pageBeanName.linesList}"/>
                <t:saveState value="#{pageBeanName.showLines}"/>
                <t:saveState value="#{pageBeanName.selectedExceptionTypeId}"/>
                <t:saveState value="#{pageBeanName.wcIntegrationBean}"/>
                <t:saveState value="#{pageBeanName.searchResult}"/>
                <t:saveState value="#{pageBeanName.reEnterCivilId}"/>
                <%-- <t:saveState value="#{pageBeanName.allPassedCond}"/>--%>
                <t:saveState value="#{pageBeanName.filterBy}"/>
                <t:saveState value="#{pageBeanName.selectedConditionDTO}"/>
                <t:saveState value="#{pageBeanName.selectedOperationKey}"/>
                <t:saveState value="#{pageBeanName.disableExceptionType}"/>
                
                <t:saveState value="#{grsExceptionHelperBean.selectedAppModuleKey}"/>
                <t:saveState value="#{grsExceptionHelperBean.selectedTransactionKey}"/>
                <t:saveState value="#{grsExceptionHelperBean.selectedConditionsKey}"/>
                <t:saveState value="#{grsExceptionHelperBean.appModuleList}"/>
                <t:saveState value="#{grsExceptionHelperBean.transactionList}"/>
                <t:saveState value="#{grsExceptionHelperBean.conditionsList}"/>
                <t:saveState value="#{grsExceptionHelperBean.selectedRadioValue}"/>
                <t:saveState value="#{grsExceptionHelperBean.linesButtonsEnabled}"/>
                <t:saveState value="#{grsExceptionHelperBean.transactionsButtonsEnabled}"/>
                <t:saveState value="#{grsExceptionHelperBean.conditionsButtonsEnabled}"/>
                <t:saveState value="#{grsExceptionHelperBean.availableLinesList}"/>
                <t:saveState value="#{grsExceptionHelperBean.addedLinesList}"/>
                <t:saveState value="#{grsExceptionHelperBean.showLines}"/>
                <t:saveState value="#{grsExceptionHelperBean.linesButtonsEnabled}"/>
                <t:saveState value="#{grsExceptionHelperBean.enableViewButton}"/>
                <t:saveState value="#{grsExceptionHelperBean.viewMode}"/>
                <t:saveState value="#{grsExceptionHelperBean.myTableData}"/>
                <tiles:insert definition="grsExceptionsAddIntegration.page" flush="false">
                    <tiles:put name="titlepage" type="string" value="addException"/>
                </tiles:insert>
            </t:aliasBean>
            <t:panelGroup forceId="true" id="scriptGeneratorPanelID">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </t:aliasBean>
    </h:form>
</f:view>