<!-- Overrrided CustomDiv2page to allow confirmation message to update qualifications names by B.Zidan -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup2" >
    <h:outputFormat id="test" value="#{qulResources.confirmUpdateMsg}" styleClass="TitlePage">  
        <f:param value="#{pageBeanName.childrenCount}"/>
    </h:outputFormat>
    
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonConfirmEdit" styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.edit}" onclick="block(); return true;"/>
        <t:commandButton forceId="true" id="Cancel" onblur="if(isVisibleComponent('treedivdetails'))settingFoucsAtDivEdit();"
            styleClass="cssButtonSmall" value="#{globalResources.cancel}">
            <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.backFromEdit}" oncomplete="hideLookUpDiv(window.blocker,window.customDiv2,'levelNameInput','myForm:errorEdit');settingFoucsAtListPage();return false;"
            reRender="divTreeDetails"/>
        </t:commandButton>
    </t:panelGrid>
</t:panelGroup>
