<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <!-- Load the resource bundle of the page -->
    <h:form id="myForm" binding="#{govEmpThirdStepBean.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration"
                      var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{govEmpThirdStepBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{govEmpMaintainBean}">
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedListSize}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.passportIssuanceCountry}"/>
                <t:saveState value="#{pageBeanName.streetName}"/>
                <t:saveState value="#{pageBeanName.citizensResidentsDataDTO}"/>
                <t:saveState value="#{pageBeanName.kwtCitizensResidentsDTO}"/>
                <t:saveState value="#{detailBeanName.civilID}"/>
                <t:saveState value="#{detailBeanName.empName}"/>
                <t:saveState value="#{detailBeanName.preSelectedCivilId}"/>
                <t:saveState value="#{detailBeanName.enableTabs}"/>
                <t:saveState value="#{detailBeanName.empHireStatusList}"/>
                <t:saveState value="#{detailBeanName.bundleName}"/>
                <t:saveState value="#{detailBeanName.statusMsgKey}"/>
                <t:saveState value="#{detailBeanName.titlePageKey}"/>
                <t:saveState value="#{detailBeanName.ministryCode}"/>
                <t:saveState value="#{detailBeanName.realCivilId}"/>
                <t:saveState value="#{pageBeanName.residentPeriodYear}"/>
                <t:saveState value="#{pageBeanName.residentPeriodMonth}"/>
                <t:saveState value="#{pageBeanName.residentPeriodDay}"/>
                <tiles:insert flush="false" definition="govempthirdstep.page">
                    <tiles:put name="titlepage" type="string" value="${detailBeanName.titlePageKey}"></tiles:put>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
</f:view>