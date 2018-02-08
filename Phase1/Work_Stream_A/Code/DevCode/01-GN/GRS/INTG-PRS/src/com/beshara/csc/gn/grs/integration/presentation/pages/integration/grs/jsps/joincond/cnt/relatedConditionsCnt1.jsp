<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid width="100%" columns="4" align="center" columnClasses="dataTableColumn1 padding5H,,dataTableColumn1," rowClasses="row01,row02" cellpadding="0" cellspacing="0">
    <t:outputText value="#{globalResources.Code}"  styleClass="lable01"/>
    <t:inputText id="cnt1_code" value="#{jcHelperBeanName.displayedCode}" styleClass="textboxsmall" style="width:75px;" disabled="true" readonly="true"/>
    <t:outputText value="#{globalResources.name}"  styleClass="lable01"/>
    <t:inputText id="cnt1_name" value="#{jcHelperBeanName.displayedName}" styleClass="textboxXlargeOnly" disabled="true" readonly="true"/>
</t:panelGrid>
