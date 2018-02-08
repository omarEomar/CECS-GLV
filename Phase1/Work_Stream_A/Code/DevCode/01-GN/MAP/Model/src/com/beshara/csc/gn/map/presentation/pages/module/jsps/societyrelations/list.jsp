<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{listBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.map.presentation.resources.map" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{listBean}">
            <tiles:insert flush="false" definition="societyrelationslist.page">
            </tiles:insert>            
        </t:aliasBean>
    </h:form>
</f:view>