<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
    <!-- Load the resource bundle of the page -->
    <h:form id="myForm" binding="#{govEmpFifthStepBean.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{govEmpFifthStepBean}">
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
            
            <t:saveState value="#{pageBeanName.employeesDTO}"/>
            <t:saveState value="#{pageBeanName.degreeWhenHired}"/>
            <t:saveState value="#{pageBeanName.benifitsAndRewardsList}"/>
            <t:saveState value="#{pageBeanName.totalMerits}"/>
            <t:saveState value="#{pageBeanName.totalDeductions}"/>
            <t:saveState value="#{pageBeanName.netSalary}"/>
            
            <t:saveState value="#{detailBeanName.civilID}"/>
             <t:saveState value="#{detailBeanName.empName}"/>
             <t:saveState value="#{detailBeanName.preSelectedCivilId}"/>
             <t:saveState value="#{detailBeanName.enableTabs}"/>
             <t:saveState value="#{detailBeanName.empHireStatusList}"/>
             <t:saveState value="#{detailBeanName.bundleName}"/>
             <t:saveState value="#{detailBeanName.statusMsgKey}"/>
             <t:saveState value="#{detailBeanName.titlePageKey}"/>
             <t:saveState value="#{detailBeanName.ministryCode}"/>
            <tiles:insert flush="false" definition="govempfifthstep.page">
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