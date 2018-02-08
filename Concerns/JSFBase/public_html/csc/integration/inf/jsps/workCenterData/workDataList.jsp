<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{workDataListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{workDataListBean}">
            <t:aliasBean alias="#{workDataListBeanName}" value="#{workDataListBean}">
                <tiles:insert flush="false" definition="workDataList.page">
                    <tiles:put name="titlepage" type="string" value="${'experiance'}"></tiles:put>
                    <t:saveState value="#{workDataListBeanName.civilId}"/>
                    <t:saveState value="#{workDataListBeanName.civilExist}"/>
                    <t:saveState value="#{workDataListBeanName.validCivilId}"/>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>