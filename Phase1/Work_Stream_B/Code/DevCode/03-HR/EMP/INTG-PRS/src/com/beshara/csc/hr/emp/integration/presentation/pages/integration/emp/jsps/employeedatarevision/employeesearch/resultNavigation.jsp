<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<t:panelGrid columns="5" forceId="true" id="buttonPanel">
    <t:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.addEmployees}"
                     styleClass="cssButtonSmall" rendered="#{detailBeanName.availableListSize > 0}"
                     disabled="#{detailBeanName.selectedAvailableListSize==0}"/>
    <f:verbatim>&nbsp;</f:verbatim>
    <t:commandButton id="cancelSearch" value="#{globalResources.cancelsearch}" styleClass="cssButtonSmall"
                     action="#{detailBeanName.cancelSearchMethod}"/>
    <f:verbatim>&nbsp;</f:verbatim>
    <t:commandButton id="BackButtonManyToMany" forceId="true" value="#{globalResources.back}"
                     styleClass="cssButtonSmall" action="#{detailBeanName.backFromResultSearch}"/>
</t:panelGrid>