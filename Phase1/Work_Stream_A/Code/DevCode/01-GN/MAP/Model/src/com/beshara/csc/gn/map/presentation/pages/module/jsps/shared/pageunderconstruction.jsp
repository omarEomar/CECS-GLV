<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view locale="#{shared_util.locale}">
      <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global_ar" var="globalResources"/>
      <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/css/${globalResources.photoFolder}/Style.css"/>
      <h:form id="myForm">
            <t:panelGrid align="center">
                  <h:graphicImage value="../../images/PageunderConstruction.jpg" height="160" width="395"/>
                  <t:panelGrid align="center">
                        <t:commandButton type="button" value="#{globalResources.back}" onclick="history.go(-1)" styleClass="cssButtonSmall"/>
                  </t:panelGrid>
            </t:panelGrid>
      </h:form>
</f:view>