<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.map.presentation.resources.map" var="mapResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <h:form id="myForm" binding="#{objectTypeListBean.frm}">
        <%-- <a4j:log popup="false" level="ALL" height="400" width="800"></a4j:log>--%>
        <t:aliasBean alias="#{pageBeanName}" value="#{objectTypeListBean}">
            <tiles:insert definition="objecttypelist.page" flush="false">
                <tiles:put name="titlepage" type="string" value="List_ObjectType_Title"/>
            </tiles:insert>
        </t:aliasBean>
    </h:form>
    <t:saveState value="#{objectTypeListBean.selectedDTOS}"/>
</f:view>