<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

 <t:panelGrid align="center" >
    <t:commandButton forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}" onclick="ignoremyFormValidation();" action="#{pageBeanName.goBack}" styleClass="cssButtonSmall" />
  </t:panelGrid>
            