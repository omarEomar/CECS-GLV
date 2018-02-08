<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.warning{
    height:auto !important;
    display:table !important;
    padding-left: 45px !important;
    padding-top: 55px !important;
    margin-top: -20px !important;
    }
#customDiv2.divSearch {
    left: 180px;
    width: 536px !important;
}
</htm:style>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="deActivateCalculation" styleClass="msg warning" style="width: 350px ! important;">
    <h:outputText id="valid" value="#{resourcesBundle.noAppModuleConfirmation}"/>
    <htm:br/>
    <htm:br/>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton  forceId="true" action="#{pageBeanName.finishCalculation}" id="changeStatus" 
                         styleClass="cssButtonSmall" value="#{globalResources.ok}" >
       <a4j:support event="oncomplete" reRender="OperationBar,dataT_data"  />                  
       </t:commandButton>                  
        <t:commandButton forceId="true" id="backButtonCustomDiv2" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv2); return false;"/>
    </t:panelGrid>
</t:panelGroup>
