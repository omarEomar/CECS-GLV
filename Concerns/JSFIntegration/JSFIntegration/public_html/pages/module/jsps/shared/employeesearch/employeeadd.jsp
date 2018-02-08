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
        <t:saveState value="#{advanceEmployeesAddBean.returnMethod}" id="returnMethod1"/>
        <t:saveState value="#{advanceEmployeesAddBean.savedData}" id="saveDataID1"/>
        <t:saveState value="#{advanceEmployeesAddBean.singleSelection}" id="singleSelection1"/>
        <t:saveState value="#{advanceEmployeesAddBean.tempCodeKeyStr}" id="tempCodeKeyStr1"/>
        <t:saveState value="#{advanceEmployeesAddBean.selectedDTOS}" id="selectedDTOS1"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{advanceEmployeesAddBean}" id="pageBeanName1">
            <t:aliasBean alias="#{detailBeanName}" value="#{advanceEmployeesAddBean}" id="detailBeanName1">
                <t:saveState value="#{detailBeanName.divMode}" id="lovDivMode"/>
                <t:saveState value="#{detailBeanName.availableDataTable}" id="availableDataTable1"/>
                <t:saveState value="#{detailBeanName.currentDetails}" id="cdetails1"/>
                <t:saveState value="#{detailBeanName.availableDetails}" id="adetails1"/>
                <t:saveState value="#{detailBeanName.selectedCurrentDetails}" id="scdetails1"/>
                <t:saveState value="#{detailBeanName.selectedAvailableDetails}" id="sadetails1"/>
                <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey1"/>
                <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode41"/>
                <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode51"/>
                <t:saveState value="#{detailBeanName.searchMode}" id="smmmmm1"/>
                <t:saveState value="#{detailBeanName.employeeSearchDTO}" id="esssss1"/>
                <t:saveState value="#{detailBeanName.categoryID}" id="categoryIDM1"/>
                <t:saveState value="#{detailBeanName.ministryID}" id="ministryIDIDM1"/>
                <t:saveState value="#{detailBeanName.hireStatuses}" id="hireStatuses1"/>
                <t:saveState value="#{detailBeanName.categories}" id="categories11"/>
                <t:saveState value="#{detailBeanName.ministries}" id="ministries11"/>
                <t:saveState value="#{detailBeanName.workMinistries}" id="workMinistries1"/>
                <t:saveState value="#{detailBeanName.hireCurrentStatuses}" id="hireCurrentStatuses11"/>
                <t:saveState value="#{detailBeanName.technicalJobs}" id="technicalJobs11"/>
                <t:saveState value="#{detailBeanName.bankID}" id="bankID11"/>
                <%--<t:saveState value="#{detailBeanName.banks}" id="banks11"/>--%>
                <t:saveState value="#{detailBeanName.branches}" id="branches11"/>
                <t:saveState value="#{detailBeanName.jobCategories}" id="jobCategories11"/>
                <t:saveState value="#{detailBeanName.currentDegrees}" id="currentDegrees11"/>
                <t:saveState value="#{detailBeanName.budgetTypes}" id="budgetTypes11"/>
                <t:saveState value="#{detailBeanName.kuwityType}" id="kuwityType11"/>
                <t:saveState value="#{detailBeanName.relgionTyps}" id="relgionTyps11"/>
                <t:saveState value="#{detailBeanName.hireTypes}" id="hireTypes11"/>
                <t:saveState value="#{detailBeanName.showResultWithinPage}" id="showResultWithinPage11"/>
                <t:saveState value="#{detailBeanName.searchMethod}" id="searchMethod11"/>
                <t:saveState value="#{detailBeanName.cancelSimpleSearchMethod}" id="SimpleSearchMethod11"/>
                <t:saveState value="#{detailBeanName.showMinistryCB}" id="showMinistryCB11"/>
                <t:saveState value="#{detailBeanName.showCategoryCB}" id="showCategoryCB11"/>
                <t:saveState value="#{detailBeanName.showWorkEndDate}" id="showWorkEndDate11"/>
                <t:saveState value="#{detailBeanName.showHireStatus}" id="showHireStatus11"/>
                <t:saveState value="#{detailBeanName.showCurrentHireStatus}" id="showCurrentHireStatus11"/>
                <t:saveState value="#{detailBeanName.showWorkCenterLovDiv}" id="showWorkCenterLovDiv11"/>
                <t:saveState value="#{detailBeanName.showJobLovDiv}" id="showJobLovDiv11"/>
                <t:saveState value="#{detailBeanName.selectedCaderCode}" id="selectedCaderCode11"/>
                <t:saveState value="#{detailBeanName.selectedGroupCode}" id="selectedGroupCode11"/>
                <t:saveState value="#{detailBeanName.jobGroupes}" id="jobGroupes11"/>
                <t:saveState value="#{detailBeanName.backMethod}" id="backMethodID11"/>
                <t:saveState value="#{detailBeanName.usingBsnPaging}"/>
                <t:saveState value="#{detailBeanName.usingPaging}"/>
                <t:saveState value="#{detailBeanName.currentPageIndex}"/>
                <t:saveState value="#{detailBeanName.oldPageIndex}"/>
                <t:saveState value="#{detailBeanName.sorting}"/>
                <t:saveState value="#{detailBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{detailBeanName.resettedPageIndex}"/>
                <t:saveState value="#{detailBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{detailBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{detailBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{detailBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{detailBeanName.availableListSizeBuffer}"/>
                <t:saveState value="#{detailBeanName.searchInf}"/>
                <t:saveState value="#{detailBeanName.pageDTO}"/>
                <tiles:insert definition="advanceEmployeessearch.page" flush="false"></tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
</f:view>
