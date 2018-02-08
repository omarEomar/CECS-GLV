<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>

<t:panelGrid align="center" id="confirmMsgPanel" forceId="true" >
  <t:outputText styleClass="msg" value="#{regResources.CONFIRM_MSG_1}  ( #{regulationRecordsBean.selectedListSize} ) #{regResources.CONFIRM_MSG_2}"/>
 </t:panelGrid>
 
  <t:panelGrid align="center" columns="2" >
        <a4j:commandButton  value="#{globalResources.ok}"  action="#{regulationRecordsBean.delete}" styleClass="cssButtonSmall" reRender="dataT_data_panel,OperationBar,currentListSizeID" oncomplete="hideLookUpDiv(window.blocker,window.divMsg,null,null,null);"/>
        
        <t:commandButton forceId="true" id="jsfBase_msgDiv_backBtn" type="button" onclick="hideLookUpDiv(window.blocker,window.divMsg,null,null,null);" styleClass="cssButtonSmall" value="#{globalResources.cancel}" onblur="setFocusOnlyOnElement('myForm:accept_confitm_btn');"/>                
        <a4j:jsFunction name="saveAndNew"  oncomplete="setFocusAtMyFirstElement();"/>
    </t:panelGrid>