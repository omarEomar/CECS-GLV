<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

 <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton value="#{globalResources.ok}" action="#{pageBeanName.navigateTOLinesTab}" styleClass="cssButtonSmall"/>
        <t:commandButton forceId="true" id="BackButtonManyToMany" onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};"  value="#{globalResources.cancel}" action="#{pageBeanName.backToLinesTab}" styleClass="cssButtonSmall"/>
</t:panelGrid>
