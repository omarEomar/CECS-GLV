<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">
    <h:outputText styleClass="msg warning" value="#{resourcesBundle.conditionActiveOperationsMsg}"/>
    <t:panelGroup colspan="1" forceId = "true" id="conditionActiveOperationsPanel">
       <t:panelGrid columns="3" align="center" width="100%" cellpadding="2" cellspacing="0" columnClasses="center,center,center">
       
           <t:outputText value="#{resourcesBundle.currentOperations}" styleClass="lable01"/>
           <t:outputText value="" styleClass="lable01"/>
           <t:outputText value="#{resourcesBundle.toBeJoinedOperations}" styleClass="lable01"/>
           
           <t:panelGroup>
               <t:selectManyMenu forceId="true" id="availableOperationsList" ondblclick="cAddOneFunction();" value="#{pageBeanName.toBeAddedOperationsList}" styleClass="textboxlarge" style="min-height:125px">
                    <t:selectItems value="#{pageBeanName.availableOperationsList}" var="types" itemValue="#{types.code.key}" itemLabel="#{types.name}"/>
               </t:selectManyMenu>
           </t:panelGroup>
           
           <t:panelGroup>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cAddAllButton" type="button" disabled="#{pageBeanName.availableOperationsListSize == 0}" onclick="cAddAllFunction();" styleClass="cssButtonSmaller" value=">>"/>                
                    <a4j:jsFunction name="cAddAllFunction" action="#{pageBeanName.addAllElements}" reRender="conditionActiveOperationsPanel"/>
                </t:panelGroup>
                <f:verbatim><br/></f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cAddOneButton" type="button" disabled="#{pageBeanName.availableOperationsListSize == 0}" onclick="cAddOneFunction();" styleClass="cssButtonSmaller" value=">"/>                
                    <a4j:jsFunction name="cAddOneFunction" action="#{pageBeanName.addSelectedElements}" reRender="conditionActiveOperationsPanel"/>
                </t:panelGroup>
                <f:verbatim><br/></f:verbatim>
               <t:panelGroup>
                    <t:commandButton forceId="true" id="cRemoveOneButton" type="button" disabled="#{pageBeanName.toBeJoinedOperationsListSize == 0}" onclick="cRemoveOneFunction();" styleClass="cssButtonSmaller" value="<"/>                
                    <a4j:jsFunction name="cRemoveOneFunction" action="#{pageBeanName.removeSelectedElements}" reRender="conditionActiveOperationsPanel"/>
                </t:panelGroup>
                <f:verbatim><br/></f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cRemoveAllButton" type="button" disabled="#{pageBeanName.toBeJoinedOperationsListSize == 0}" onclick="cRemoveAllFunction();" styleClass="cssButtonSmaller" value="<<"/>                
                    <a4j:jsFunction name="cRemoveAllFunction" action="#{pageBeanName.removeAllElements}" reRender="conditionActiveOperationsPanel"/>
                </t:panelGroup>
           </t:panelGroup>
           
           <t:panelGroup>
               <t:selectManyMenu forceId="true" id="relatedOperationsList" ondblclick="cRemoveOneFunction();" value="#{pageBeanName.toBeRemovedOperationsList}" styleClass="textboxlarge" style="min-height:125px">
                    <t:selectItems value="#{pageBeanName.toBeJoinedOperationsList}" var="types" itemValue="#{types.code.key}" itemLabel="#{types.name}"/>
               </t:selectManyMenu>
           </t:panelGroup>
           
        </t:panelGrid>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="OkButtonCustomDiv1" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.finish}"/>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false;"/>
        <%--h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="if(isVisibleComponent('delAlert'))hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
