<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="intResourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <h:form id="myForm" binding="#{advanceEmployeesAddBean.frm}">
        <t:saveState value="#{advanceEmployeesAddBean.returnMethod}" id="returnMethod2"/>
        <t:saveState value="#{advanceEmployeesAddBean.savedData}" id="saveDataID2"/>
        <t:saveState value="#{advanceEmployeesAddBean.singleSelection}" id="singleSelection2"/>
        <t:saveState value="#{advanceEmployeesAddBean.tempCodeKeyStr}" id="tempCodeKey2"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{advanceEmployeesAddBean}" id="pageBeanName2">
            <t:aliasBean alias="#{detailBeanName}" value="#{advanceEmployeesAddBean}" id="detailBeanName2">
                <t:saveState value="#{detailBeanName.availableDataTable}" id="availableDataTable2"/>
                <t:saveState value="#{detailBeanName.currentDetails}" id="cdetailsx2"/>
                <t:saveState value="#{detailBeanName.availableDetails}" id="adetailsx2"/>
                <t:saveState value="#{detailBeanName.selectedCurrentDetails}" id="scdetailsx2"/>
                <t:saveState value="#{detailBeanName.selectedAvailableDetails}" id="sadetailsx2"/>
                <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykeyx2"/>
                <t:saveState value="#{advanceEmployeesAddBean.selectedDTOS}" id="selectedDTOS2"/>
                <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4x2"/>
                <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5x2"/>
                <t:saveState value="#{detailBeanName.searchMode}" id="smmmmm2"/>
                <t:saveState value="#{detailBeanName.employeeSearchDTO}" id="esssss2"/>
                <t:saveState value="#{detailBeanName.categoryID}" id="categoryIDM2"/>
                <t:saveState value="#{detailBeanName.ministryID}" id="ministryIDIDM2"/>
                <t:saveState value="#{detailBeanName.hireStatuses}" id="hireStatusesID2"/>
                <t:saveState value="#{detailBeanName.categories}" id="xyzID2"/>
                <t:saveState value="#{detailBeanName.ministries}" id="qqqqID2"/>
                <t:saveState value="#{detailBeanName.workMinistries}" id="mmmID2"/>
                <t:saveState value="#{detailBeanName.hireCurrentStatuses}" id="hiID2"/>
                <t:saveState value="#{detailBeanName.technicalJobs}" id="technicalJobs2"/>
                <t:saveState value="#{detailBeanName.bankID}" id="bankID2"/>
                <t:saveState value="#{detailBeanName.banks}" id="banks2"/>
                <t:saveState value="#{detailBeanName.branches}" id="branches2"/>
                <t:saveState value="#{detailBeanName.jobCategories}" id="jobCategories2"/>
                <t:saveState value="#{detailBeanName.currentDegrees}" id="currentDegrees2"/>
                <t:saveState value="#{detailBeanName.budgetTypes}" id="budgetTypes2"/>
                <t:saveState value="#{detailBeanName.kuwityType}" id="kuwityType2"/>
                <t:saveState value="#{detailBeanName.relgionTyps}" id="relgionTyps2"/>
                <t:saveState value="#{detailBeanName.hireTypes}" id="hireTypes2"/>
                <t:saveState value="#{detailBeanName.showResultWithinPage}" id="showResultWithinPage2"/>
                <t:saveState value="#{detailBeanName.searchMethod}" id="searchMethod2"/>
                <t:saveState value="#{detailBeanName.cancelSimpleSearchMethod}" id="SimpleSearchMethod22"/>
                <t:saveState value="#{detailBeanName.showMinistryCB}" id="showMinistryCB2"/>
                <t:saveState value="#{detailBeanName.showCategoryCB}" id="showCategoryCB2"/>
                <t:saveState value="#{detailBeanName.showWorkEndDate}" id="showWorkEndDate2"/>
                <t:saveState value="#{detailBeanName.showHireStatus}" id="showHireStatus2"/>
                <t:saveState value="#{detailBeanName.showCurrentHireStatus}" id="showCurrentHireStatus2"/>
                <t:saveState value="#{detailBeanName.showWorkCenterLovDiv}" id="showWorkCenterLovDiv2"/>
                <t:saveState value="#{detailBeanName.showJobLovDiv}" id="showJobLovDiv2"/>
                <t:saveState value="#{detailBeanName.selectedCaderCode}" id="selectedCaderCode2"/>
                <t:saveState value="#{detailBeanName.selectedGroupCode}" id="selectedGroupCode2"/>
                <t:saveState value="#{detailBeanName.jobGroupes}" id="jobGroupes2"/>
                <t:saveState value="#{detailBeanName.backMethod}" id="backMethodID2"/>
                <t:saveState value="#{detailBeanName.availableListSizeBuffer}"/>
                <t:saveState value="#{detailBeanName.searchInf}"/>
                <!-- Start Paging -->
                <t:saveState value="#{detailBeanName.usingBsnPaging}"/>
                <t:saveState value="#{detailBeanName.currentPageIndex}"/>
                <t:saveState value="#{detailBeanName.oldPageIndex}"/>
                <t:saveState value="#{detailBeanName.sorting}"/>
                <t:saveState value="#{detailBeanName.usingPaging}"/>
                <t:saveState value="#{detailBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{detailBeanName.resettedPageIndex}"/>
                <t:saveState value="#{detailBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{detailBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{detailBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{detailBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{detailBeanName.pagingBean.pageDTO}"/>
                <tiles:insert definition="advanceEmployeeresultssearch.page" flush="false"></tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
</f:view>