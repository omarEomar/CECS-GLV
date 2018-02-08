<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:saveState value="#{treeDivBean.dtoDetails}"/>
<t:saveState value="#{treeDivBean.searchMode}"/>
<t:saveState value="#{treeDivBean.showTreeContent}"/>
<t:saveState value="#{treeDivBean.searchType}"/>
<t:saveState value="#{treeDivBean.clientValueBinding}"/>
<t:saveState value="#{treeDivBean.enabledNotLeaf}"/>
<t:saveState value="#{treeDivBean.enableSearchByCode}"/>
<t:saveState value="#{treeDivBean.keyIndex}"/>
<t:saveState value="#{treeDivBean.selectionNo}"/>
<t:saveState value="#{treeDivBean.cancelSearchMethod}"/>
<t:saveState value="#{treeDivBean.myTableData}"/>
<a4j:jsFunction name="jsFunctionSearchTree" action="#{treeDivBean.searchTree}" reRender="treeDivList,cancelsearchpanel,noResult,okPanel" oncomplete="focusOnSearchResult();" id="jsFunctionSearchTree" />
<t:panelGroup forceId="true" id="addRegulationSubject_Add">
<t:panelGrid columns="3" style="vertical-align:top;">
    <t:outputLabel value="#{globalResources.name}" styleClass="treepage-link"/>
    <t:inputText forceId="true" id="searchText" onblur="document.getElementById('searchTreeDivButtonID').focus();" value="#{treeDivBean.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('searchTreeDivButtonID');"/>
    <t:panelGrid columns="2" align="center">
        <t:commandButton type="button" value="" forceId="true" styleClass="ManyToManySearchButton" id="searchTreeDivButtonID" onclick="return checkSearchTxtValidation(); setFocusOnlyOnElement('searchText');"/>
        <t:panelGroup id="cancelsearchpanel">
            <a4j:commandButton action="#{treeDivBean.cancelSearchTree}" reRender="treeDivList,searchText,cancelsearchpanel,noResult,clientErrorMessage,okPanel" rendered="#{treeDivBean.searchMode == true}" styleClass="ManyToManyCancelSearchButton" binding="#{treeDivBean.cancelSearchCommandButton}" oncomplete="setFocusOnlyOnElement('searchText');"  id="cancelSearchTreeDivButtonID"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup/>
    <t:outputText forceId="true" id="requirdFildId" value="" styleClass="errMsg"/>         
    <t:panelGroup/>
</t:panelGrid>
<t:panelGroup id="noResult">
    <t:panelGrid align="center" rendered="#{treeDivBean.serachResult}">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="errMsg" id="noresultmessage" forceId="true"/>
    </t:panelGrid>


</t:panelGroup>

<h:panelGrid columns="1">
    <h:panelGroup id="treeDivPanel" style="width:500px;height:180px;overflow-y:auto;">
        <a4j:jsFunction name="updatetree" reRender="theSelectedNodeId,okPanel" oncomplete="setFocusOnlyOnElement('myForm:tree_ok_id');"></a4j:jsFunction>
        <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeDivList" forceId="true" >
            <t:saveState value="#{treeDivBean.treeNodeBase}"/>
            <t:tree2 id="clientTree" value="#{treeDivBean.treeNodeBase}" var="node" imageLocation="/app/media/images/ar/tree2" varNodeToggler="t" binding="#{treeDivBean.htmlTree}">
                <f:facet name="foo-folder">
                    <h:panelGroup>
                        <f:facet name="expand">
                            <t:graphicImage value="/app/media/images/folder-open.gif" rendered="#{t.nodeExpanded}" alt="" border="0"/>
                        </f:facet>
                        <f:facet name="collapse">
                            <t:graphicImage value="/app/media/images/folder-closed.gif" rendered="#{!t.nodeExpanded}" border="0"/>
                        </f:facet>
                        <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                       onmousedown="javascript:reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';"
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
                        <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                       onmousedown="javascript:reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';"
                                       value="[#{node.treeId}] #{node.description}" styleClass="HigthLighttreepage-link"></t:outputLabel>
                                 <t:inputText id="nodedivfocus" forceId="true" styleClass="textboxnodefocus"/>
                    </h:panelGroup>
                </f:facet>
            </t:tree2>
        </t:panelGrid>
        <!-- This part responsibility to highlight the node when selected-->
        <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{treeDivBean.selectedNodeId}" valueChangeListener="#{treeDivBean.idChange}"/>
        <t:inputHidden forceId="true" value="0" id="rootID"/>
        <t:inputHidden forceId="true" id="lastNode" value=""/>
        <t:saveState value="#{treeDivBean.selectedNodeId}"/>
    </h:panelGroup>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="2" align="center">
    <h:panelGroup id="okPanel">
        <t:panelGroup id="okPanel2" rendered="#{treeDivBean.selectionNo == 1}" forceId="true">
            <a4j:commandButton binding="#{treeDivBean.okCommandButton}" disabled="#{treeDivBean.dtoDetails.validitiesDTO.code.validityCode==2}"  value="#{globalResources.ok}" styleClass="cssButtonSmall" actionListener="#{treeDivBean.goActionOk}"
                               oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,null,null,null);changeVisibilityMsg();" id="tree_ok_id"/>
        </t:panelGroup>
        </h:panelGroup>
        <t:panelGroup>
        <t:commandButton  forceId="true" id="backButtonAddDiv" value="#{globalResources.back}" styleClass="cssButtonSmall" type="button" onblur="setFocusOnlyOnElement('searchText');" onclick="tree_div_back_function();"/>
          <a4j:jsFunction name="tree_div_back_function" actionListener="#{treeDivBean.goActionBack}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,null,null,null);setFocusAfterBackFromTreeDiv();" />
        </t:panelGroup>
    </t:panelGrid>
</h:panelGrid>
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


function checkSearchTxtValidation(){
    document.getElementById('requirdFildId').innerHTML ="";
    trimBorders('searchText');
    var trimeString = document.getElementById('searchText').value;
    if(trimeString== '') {
        document.getElementById('requirdFildId').innerHTML ="ØªØ§ÙƒØ¯ Ù…Ù† Ù…Ù„Ø£ Ø§Ù„Ø­Ù‚Ù„";
        return false;
    }
    
    jsFunctionSearchTree();
    return true;
}



</script>