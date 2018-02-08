<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

 <f:view locale="#{shared_util.locale}">
        <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="resourcesBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
        <h:form id="myForm" binding="#{socialInsurPeroidCalcEmpSalaryBean.frm}">
            <t:aliasBean alias="#{pageBeanName}" value="#{socialInsurPeroidCalcEmpSalaryBean}">
                <tiles:insert flush="false" definition="socialInsurPeroidCalcEmpSalary.page"/>
                    <t:saveState  value="#{pageBeanName.limitedPeroid}"/>
                    <t:saveState  value="#{pageBeanName.civilId}"/>
                    <t:saveState  value="#{pageBeanName.changeTypeDataTable}"/>
                    <t:saveState  value="#{pageBeanName.changeTypeList}"/>
                    <t:saveState  value="#{pageBeanName.selectedChangeTypeList}"/>
                    <t:saveState  value="#{pageBeanName.minCode}"/>
            </t:aliasBean>
            <t:panelGroup forceId="true" id="scriptGenerator">
                <c:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </f:view>
