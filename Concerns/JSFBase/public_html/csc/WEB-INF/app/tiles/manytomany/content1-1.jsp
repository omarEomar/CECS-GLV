<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

               
<t:panelGrid columns="4" id="masterdata" rowClasses="row01" width="100%" cellspacing="0" border="0" cellpadding="5" columnClasses="dataTableColumn1,dataTableColumn2,dataTableColumn1,dataTableColumn2">
<h:outputText value="#{globalResources.Code}" rendered="#{!pageBeanName.addMode}"/>
<h:outputText value="#{pageBeanName.pageDTO.code.key}" rendered="#{!pageBeanName.addMode}"/>
<h:outputText value="#{globalResources.name} " />
<h:outputText value="#{pageBeanName.pageDTO.name}" />
</t:panelGrid>
