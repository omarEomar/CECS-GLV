<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:verbatim>
    <script>
        function updateTreeValue(){
        
             try{
                 var treeCodeId = document.getElementById("treeCodeClientId").value ;
                 var treeNameId = document.getElementById("treeNameClientId").value ;
                 
                 
                // alert('full Path Displaying'+document.getElementById('displayFullTreePath').value);
                   
                    
                 //alert('name'+document.getElementById('selectedNodeName').value);  
                  if(document.getElementById('theSelectedNodeId') !=null)
                       document.getElementById(treeCodeId).value =document.getElementById('theSelectedNodeId').value;
                    
                  
                   
                   if(document.getElementById('selectedNodeName') !=null)
                      {document.getElementById(treeNameId).value =document.getElementById('selectedNodeName').value;
                   
                   }
                    
             }catch(exception) {
             ;
            }
            document.getElementById("treeCodeClientId").value='' ;
            document.getElementById("treeNameClientId").value='' ;
        }
         function clearTreeValue(x,y){
    
             var elems =  document.myForm.elements;
             try{
            for(var i=0; i<elems.length; i++) {
                if(elems[i].name != null){    
                    
                    if(elems[i].name.substring(elems[i].name.lastIndexOf(':')) == ":"+y) {
                        elems[i].value= '';
                    }
                    else if(elems[i].name.substring(elems[i].name.lastIndexOf(':')) == ":"+x){
                        elems[i].value= '';
                    }
                }
            }
             }catch(exception) {
             ;
            }
        }
        
         function enableDisableOKButton(){
         
             var selectedNodeId=document.getElementById('theSelectedNodeId').value;
             var treeSelectionStatus=document.getElementById('treeSelectionStatus').value;
             var booleanLeafNode =document.getElementById('booleanLeafNode').value;
             
             if(selectedNodeId !=null &&  selectedNodeId=='0')
                    document.getElementById('OkTreeButton').disabled=true;
           
             else if(treeSelectionStatus !=null && treeSelectionStatus=='1')
             {
                    if(booleanLeafNode !=null && booleanLeafNode=='false')
                        document.getElementById('OkTreeButton').disabled=true;
                    else 
                        document.getElementById('OkTreeButton').disabled=false;
             }
           else 
             document.getElementById('OkTreeButton').disabled=false;
            
         }
    </script>

</f:verbatim>



<t:panelGroup forceId="true"  id="TreeDivPanelID" style="width:600px;">

 
 <t:saveState value="#{pageBeanName.treeSelectionStatus}"/> 
 <t:saveState value="#{pageBeanName.treeCodeClientId}"/>  
 <t:saveState value="#{pageBeanName.treeNameClientId}"/> 
 <t:saveState value="#{pageBeanName.selectedTreeId}" />
 <t:saveState value="#{pageBeanName.selectedTreeName}" />
 
<t:saveState value="#{treeDivReqBean.myTableData}"/>   
<t:saveState value="#{treeDivReqBean.searchQuery}"/>
<t:saveState value="#{treeDivReqBean.searchMode}"/>

<t:saveState value= "#{treeDivReqBean.searchType}"/>
<t:saveState value="#{treeDivReqBean.searchTypeCode}"/>
<t:saveState value="#{treeDivReqBean.searchTypeName}"/>

<t:saveState value="#{pageBeanName.displayFullTreeName}"/>

<htm:table>
        <htm:tr>
            <htm:td align="center">
                        <htm:table border="0" id="treedivSearchFrame" cellspacing="0" cellpadding="0" style="width:100%">
                <htm:tr>
                    <htm:td valign="bottom" width="19"><htm:img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"/></htm:td>
                    <htm:td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" styleClass="gtopbox"></htm:td>
                    <htm:td valign="bottom" width="12"><htm:img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"/></htm:td>
                </htm:tr>
                
                <htm:tr>
                    <htm:td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" styleClass="grightbox"></htm:td>
                    <htm:td valign="top" bgcolor="#FFFFFF" styleClass="paddingbox" align="center">
                        
 <t:panelGroup style="width:50%" forceId="true" id="searchTReePanelPart">
<t:panelGrid id="radioTreeDivPanel"  align="center"   border="0" >
   <t:selectOneRadio   styleClass="divtext" forceId="true" id="radioTreeDivButton"  value="#{treeDivReqBean.searchType}" >
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{treeDivReqBean.searchTypeCode}"/>
           <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{treeDivReqBean.searchTypeName}"/>
   </t:selectOneRadio>
</t:panelGrid>   

<t:panelGrid  align="center" columns="3" width="90%">
    
    <t:panelGroup>
    <t:inputText forceId="true" id="searchText" onblur="document.getElementById('myForm:searchTreeDivButtonID').focus();" value="#{treeDivReqBean.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:searchTreeDivButtonID');"/>
    <a4j:commandButton action="#{treeDivReqBean.searchTree}" reRender="treeDivList,cancelsearchpanel,noResult,okPanel" oncomplete="focusOnSearchResult();" styleClass="ManyToManySearchButton" id="searchTreeDivButtonID"/>
    </t:panelGroup>
   
    <t:panelGroup id="cancelsearchpanel">
    <a4j:commandButton action="#{treeDivReqBean.cancelSearchTree}" reRender="treeDivList,searchText,cancelsearchpanel,noResult,clientErrorMessage,okPanel,radioTreeDivPanel" rendered="#{treeDivReqBean.searchMode == true}"
                   styleClass="ManyToManyCancelSearchButton"  oncomplete="setFocusOnlyOnElement('searchText');"/>
    </t:panelGroup>
    
</t:panelGrid>
</t:panelGroup>
 </htm:td>
<htm:td valign="top" style="background-repeat: repeat-y" styleClass="gleftbox"></htm:td>
</htm:tr>

<htm:tr>
    <htm:td valign="top" width="19">
            <htm:img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"/>
    </htm:td>
    <htm:td valign="top" style="background-repeat: repeat-x" styleClass="gbottombox"></htm:td>
    <htm:td valign="top">
        <htm:img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"/>
    </htm:td>
</htm:tr>
    
</htm:table>

</htm:td>
</htm:tr>
</htm:table>
<t:panelGroup id="noResult" forceId="true" >
    <t:panelGrid align="center" rendered="#{treeDivReqBean.serachResult}">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="errMsg" id="noresultmessage" forceId="true"/>
    </t:panelGrid>
</t:panelGroup>

<t:panelGrid columns="1" width="100%" >
    <t:panelGroup id="treeDivPanel" style="width:580px;height:150px;overflow-y:auto;">
        <a4j:jsFunction name="updatetree" reRender="theSelectedNodeId,selectedNodeName" oncomplete="setFocusOnlyOnElement('OkTreeButton');"></a4j:jsFunction>
        <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeDivList" forceId="true" >
            <t:saveState value="#{treeDivReqBean.treeNodeBase}"/>
            <t:tree2 id="clientTree" value="#{treeDivReqBean.treeNodeBase}" var="node" imageLocation="/app/media/images/ar/tree2" varNodeToggler="t" binding="#{treeDivReqBean.htmlTree}">
                <f:facet name="foo-folder">
                    <h:panelGroup>
                        <f:facet name="expand">
                            <t:graphicImage value="/app/media/images/folder-open.gif" rendered="#{t.nodeExpanded}" alt="" border="0"/>
                        </f:facet>
                        <f:facet name="collapse">
                            <t:graphicImage value="/app/media/images/folder-closed.gif" rendered="#{!t.nodeExpanded}" border="0"/>
                        </f:facet>
                        <t:outputLabel id="commandDescription2" onclick="updatetree();enableDisableOKButton();"
                                       onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}'; if(document.getElementById('displayFullTreePath').value == '1') {document.getElementById('selectedNodeName').value ='#{node.fullPathNodeName}';}else {document.getElementById('selectedNodeName').value ='#{node.description}';} document.getElementById('booleanLeafNode').value ='#{node.booleanLeaf}';"
                                       value="[#{node.treeId}] #{node.description}" styleClass="treepage-link"></t:outputLabel>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="person-highlight">
                    <h:panelGroup>
                        <f:facet name="expand">
                            <t:graphicImage value="/app/media/images/blue-folder-open.gif" rendered="#{t.nodeExpanded}" alt="Can't find the image" border="0"/>
                        </f:facet>
                        <f:facet name="collapse">
                            <t:graphicImage value="/app/media/images/blue-folder-closed.png" rendered="#{!t.nodeExpanded}" alt="Can't find the image" border="0"/>
                        </f:facet>
                        <t:outputLabel id="commandDescription2" onclick="updatetree();enableDisableOKButton();"
                                       onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';if(document.getElementById('displayFullTreePath').value == '1') {document.getElementById('selectedNodeName').value ='#{node.fullPathNodeName}';}else {document.getElementById('selectedNodeName').value ='#{node.description}';} document.getElementById('booleanLeafNode').value ='#{node.booleanLeaf}';"
                                       value="[#{node.treeId}] #{node.description}" styleClass="HigthLighttreepage-link"></t:outputLabel>
                                       <t:inputText id="nodedivfocus" forceId="true" styleClass="textboxnodefocus"/>
                    </h:panelGroup>
                </f:facet>
            </t:tree2>
        </t:panelGrid>

        <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{pageBeanName.selectedTreeId}" valueChangeListener="#{pageBeanName.updateTreeRelatedField}" />
        <t:inputHidden forceId="true" id="selectedNodeName"  value="#{pageBeanName.selectedTreeName}"/>
        <t:inputHidden value="#{pageBeanName.treeCodeClientId}" forceId="true" id="treeCodeClientId"/>
        <t:inputHidden value="#{pageBeanName.treeNameClientId}" forceId="true" id="treeNameClientId"/>
        <t:inputHidden  value="#{pageBeanName.treeSelectionStatus}" forceId="true" id="treeSelectionStatus"/>
        <t:inputHidden  forceId="true" id="displayFullTreePath" value="#{pageBeanName.displayFullTreeName}"/>
        <t:inputHidden forceId="true" id="booleanLeafNode"  />
         
        <t:inputHidden forceId="true" value="0" id="rootID"/>
        <t:inputHidden forceId="true" id="lastNode" value=""/>
    </t:panelGroup>
    <f:verbatim>
        <br/>
    </f:verbatim>
    
  <t:panelGrid columns="3" align="center" forceId="true" id="okPanel" style="width:30px"  border="0" cellpadding="0" cellspacing="0">
           <t:panelGroup>
            <t:commandButton  value="#{globalResources.ok}" styleClass="cssButtonSmall"  onclick="updateTreeName();" forceId="true"  id="OkTreeButton" disabled="true" type="button"/> 
               <a4j:jsFunction name="updateTreeName" oncomplete="updateTreeValue();hidTreeDiv(null,window.blocker,window.divTree,null);" reRender="groupObject"/>
            </t:panelGroup>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:panelGroup> 
                <t:commandButton type="button" forceId="true" id="backButtonTreeDiv" onblur="setFocusOnlyOnElement('searchText');" onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}" styleClass="cssButtonSmall" />
               <a4j:jsFunction name="backButtonTreeDivJs" oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/>
            </t:panelGroup>
    </t:panelGrid>

</t:panelGrid>
 


</t:panelGroup>


  
    
<script type="text/javascript">
    function focusOnSearchResult(){
       //alert();
       if(document.getElementById("nodedivfocus") != null){
           setTimeout("document.getElementById('nodedivfocus').focus()",400);
       }
   }
   function focusAfterBackFromTreeDiv(){
        if(typeof(setFocusAfterBackFromTreeDiv)!='undefined'){
            setFocusAfterBackFromTreeDiv();
        }
   }
</script>



