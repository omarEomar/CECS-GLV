<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
    <!-- Load the resource bundle of the page -->
    <h:form id="myForm" binding="#{govEmpSixthStepBean.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{govEmpSixthStepBean}">
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
            
            <t:saveState value="#{pageBeanName.periodicalVac}"/>
            <t:saveState value="#{pageBeanName.sickVac}"/>
            <t:saveState value="#{pageBeanName.casualVac}"/>
            <t:saveState value="#{pageBeanName.periodicalVacPeriod}"/>
            <t:saveState value="#{pageBeanName.sickVacPeriod}"/>
            <t:saveState value="#{pageBeanName.casualVacPeriod}"/>
            <t:saveState value="#{pageBeanName.periodicalVacBalance}"/>
            <t:saveState value="#{pageBeanName.delegateMov}"/>
            <t:saveState value="#{pageBeanName.secondingMov}"/>
            
            <t:saveState value="#{detailBeanName.civilID}"/>
             <t:saveState value="#{detailBeanName.empName}"/>
             <t:saveState value="#{detailBeanName.preSelectedCivilId}"/>
             <t:saveState value="#{detailBeanName.enableTabs}"/>
             <t:saveState value="#{detailBeanName.empHireStatusList}"/>
             <t:saveState value="#{detailBeanName.bundleName}"/>
             <t:saveState value="#{detailBeanName.statusMsgKey}"/>
             <t:saveState value="#{detailBeanName.titlePageKey}"/>
             <t:saveState value="#{detailBeanName.ministryCode}"/>
            <tiles:insert flush="false" definition="govempsixthstep.page">
                <tiles:put name="titlepage" type="string" value="${detailBeanName.titlePageKey}">
                </tiles:put>
            </tiles:insert>            
        </t:aliasBean>
        </t:aliasBean>
        <script type="text/javascript">
        settingFoucsAtListPage();
        </script>
    </h:form>
</f:view>