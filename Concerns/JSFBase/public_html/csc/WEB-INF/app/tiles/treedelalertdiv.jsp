<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<t:saveState value="#{pageBeanName.dto}"/>

<t:panelGrid align="center" id="treeAlertDelete" forceId="true" border="0" width="100%" style="text-align:center">
    <t:panelGroup>
        <t:panelGrid columns="2" rowClasses="standardTable_Header,standardTable_Row1" columnClasses="deleteTreeCol1,deleteTreeCol2" styleClass="grid-footer DelTbl">
            <t:outputText value="#{globalResources.Code}"/>
            <t:outputText value="#{globalResources.TableName}"/>
            <t:outputText value="#{pageBeanName.dtoDetails.code.keys[1]}" rendered="#{pageBeanName.dtoDetails.code.keys[1]!=null}"/>
            <t:outputText value="#{pageBeanName.dtoDetails.code.key}" rendered="#{pageBeanName.dtoDetails.code.keys[1]==null}"/>
            <t:outputText value="#{pageBeanName.dtoDetails.name}"/>
        </t:panelGrid>
        
        
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="OkButtonTreeDelAlertDiv" styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.deleteItem}"
                    onkeydown= "preventFocusOut(event,'CancelButtonTreeDelAlertDiv');"/>
                    <!--onblur="if(isVisibleComponent('delAlertTree'))document.getElementById('CancelButtonTreeDelAlertDiv').focus();"-->
        <%--t:commandButton value="#{globalResources.ok}" styleClass="cssButtonSmall" action="#{pageBeanName.deleteItem}"/--%>
        <t:commandButton forceId="true" id="CancelButtonTreeDelAlertDiv" styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hidTreeDiv('delete',window.blocker,window.delAlertTree,null); return false;"
                    onkeydown= "preventFocusOut(event,'OkButtonTreeDelAlertDiv');"/>
                    <!--onblur="if(isVisibleComponent('delAlertTree'))document.getElementById('OkButtonTreeDelAlertDiv').focus();"-->
        <%--t:commandButton  value="#{globalResources.cancel}" type="button" styleClass="cssButtonSmall" onclick="hidTreeDiv('delete',window.blocker,window.delAlertTree,null);"/--%>
        
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  window.onkeydown = function handleTab(e){
      if(e.keyCode == 9 ) {
          /*if(document.getElementById('divTreeDetails').style.visibility == "visible" &&
            document.getElementById('descriptionText').disabled != "disabled") {
           document.getElementById('descriptionText').focus();//levelNameInput is id of first element in edit div
           return;
         }
         if(document.getElementById('divTreeAdd').style.visibility == "visible") {
           document.getElementById('add_first_inputTxt').focus();//add_first_inputTxt is id of first element in Add div
           return;
         }*/
         if(document.getElementById('delAlertTree').style.visibility == "visible") {
           document.getElementById('OkButtonTreeDelAlertDiv').focus();//OkButtonTreeDelAlertDiv is id of first element in Delete div
           return;
         }
         
      }
     
  }
  function preventFocusOut(e,elId) {
    if(e.keyCode == 9) {
        e.cancelBubble = true;
        e.returnValue = false;
        e.preventDefault();
        document.getElementById(elId).focus();//elId is id of element to focus when press tab
        document.getElementById(elId).select();
    }
}
</script>
