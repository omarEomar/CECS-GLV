<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


            
    <f:view locale="#{shared_util.locale}">
        <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="resourcesBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
        <h:form id="myForm" binding="#{socialInsurPeroidCalcJobCategoryBean.frm}">
            <t:aliasBean alias="#{pageBeanName}" value="#{socialInsurPeroidCalcJobCategoryBean}">
                <tiles:insert flush="false" definition="socialInsurPeroidCalcJobCategory.page"/>

               

                <%-- Start SaveStates of this Tab --%>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.myTableData}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.sortColumn}"/>
                <t:saveState   value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.fullColumnName}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.sortAscending}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.currentPageIndex}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.oldPageIndex}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.sorting}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.realCivilId}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.totalServiceDays}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.totalServiceMonths}"/>
                <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.totalServiceYears}"/>


               <t:saveState   value="#{socialInsurPeroidCalcJobCategoryBean.filterPnlCollapsed}"/>

                <%-- End SaveStates of this Tab --%>
                
           

            </t:aliasBean>
            <t:panelGroup forceId="true" id="scriptGenerator">
                <c:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </f:view>
