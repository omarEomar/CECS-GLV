<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration"
                  var="resourcesBundle"/>
    <h:form id="myForm" binding="#{trainingCoursesBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{trainingCoursesBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{trainingCoursesBean}">
                <t:saveState value="#{govEmpMaintainBean.realCivilId}"/>
                <t:saveState value="#{govEmpMaintainBean.civilID}"/>
                <t:saveState value="#{govEmpMaintainBean.empName}"/>
                <t:saveState value="#{govEmpMaintainBean.preSelectedCivilId}"/>
                <t:saveState value="#{govEmpMaintainBean.enableTabs}"/>
                <t:saveState value="#{govEmpMaintainBean.empHireStatusList}"/>
                <t:saveState value="#{govEmpMaintainBean.bundleName}"/>
                <t:saveState value="#{govEmpMaintainBean.statusMsgKey}"/>
                <t:saveState value="#{govEmpMaintainBean.titlePageKey}"/>
                <t:saveState value="#{govEmpMaintainBean.ministryCode}"/>
                 <t:saveState value="#{trainingCoursesBean.trainingCoursesTableLst}"/>
                <tiles:insert definition="trainingCoursesList.page" flush="false">
                    <tiles:put name="titlepage" type="string" value="${govEmpMaintainBean.titlePageKey}"></tiles:put>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptGenGroup">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>