<%@ page session="false" contentType="text/html;charset=utf-8"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:verbatim>
<div style="overflow-y:auto; height:200">
</f:verbatim>
<t:panelGroup forceId="true" id="divPhoto">
    
    <t:graphicImage forceId="true" id="cancellingPhoto" value="/#{pageBeanName.selectedDTOS[0].decisionImage}" 
        border="0" style="display:none;width:400px;height:400px;"/>
    
    <t:graphicImage forceId="true" id="canceledPhoto" value="/#{pageBeanName.selectedDTOS[0].decisionsDTO.decisionImage}" 
        border="0" style="display:none;width:400px;height:400px;"/>
        
</t:panelGroup>

<f:verbatim>
</div>
</f:verbatim>

<t:panelGrid columns="1" align="center">
    <h:commandButton id="photoBack" value="#{globalResources.back}" onclick="cancellingPhoto.style.display='none';canceledPhoto.style.display='none';hideLookUpDiv(window.blocker,window.customDiv1,null,null);return false;" styleClass="cssButtonSmall"/>
</t:panelGrid>
