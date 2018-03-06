<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGrid columns="2" styleClass="row01" width="100%">
    <t:outputText value="#{resourcesBundle.country_label}   " styleClass="lable01"/>
    <t:inputText value="#{pageBeanName.masterDTO.name}" styleClass="textboxlarge" readonly="true"/>
</t:panelGrid>