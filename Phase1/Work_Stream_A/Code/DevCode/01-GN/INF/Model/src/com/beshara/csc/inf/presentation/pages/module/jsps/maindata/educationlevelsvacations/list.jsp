<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
<f:loadBundle basename="com.beshara.csc.gn.inf.presentation.resources.inf" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{educationLevelsVacations.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{educationLevelsVacations}">
            <tiles:insert flush="false" definition="educationLevelsVacations.page">
                <!--Basic saveState-->
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.myDataTable}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.ascending}"/>
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.bsnSortingColumnName}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.sortedTable}"/>
                <t:saveState value="#{pageBeanName.usingBsnPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{pageBeanName.indexArray}"/>
                <t:saveState value="#{pageBeanName.selectedRadio}"/>
                <!-- Page save state -->
                <t:saveState value="#{pageBeanName.specialPeriodsDTO}"/>
                <t:saveState value="#{pageBeanName.displayButtonDisabled}"/>
                <t:saveState value="#{pageBeanName.periodTypeID}"/>
                <t:saveState value="#{pageBeanName.periodTypesList}"/>
                <t:saveState value="#{pageBeanName.resetData}"/>
                <t:saveState value="#{pageBeanName.categories}"/>
                <t:saveState value="#{pageBeanName.ministries}"/>
                <t:saveState value="#{pageBeanName.categoryID}"/>
                <t:saveState value="#{pageBeanName.ministryID}"/>
                <t:saveState value="#{pageBeanName.selectedYear}"/>
                <t:saveState value="#{pageBeanName.yearList}"/>
                <t:saveState value="#{pageBeanName.bgtProgramsDTOList}"/>
                <t:saveState value="#{pageBeanName.selCategoyName}"/>
                <t:saveState value="#{pageBeanName.selMinistryName}"/>
                <t:saveState value="#{pageBeanName.selperiodName}"/>
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>