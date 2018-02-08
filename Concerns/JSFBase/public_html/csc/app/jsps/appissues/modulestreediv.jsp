<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
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

<t:saveState value="#{treeDivBean.goActionOkMethod}"/>
<t:saveState value="#{treeDivBean.goActionBackMethod}"/>
<t:saveState value="#{treeDivBean.cancelSearchMethod}"/>
<t:saveState value="#{treeDivBean.idChangeMethod}"/>
<t:saveState value="#{treeDivBean.searchTreeMethod}"/>

<t:saveState value="#{treeDivBean.myTableData}"/>
<t:saveState value="#{treeDivBean.startTreeLevel}"/>

<t:panelGroup forceId="true" id="radioTreeDivPanel">
   <t:panelGrid align="center"  border="0" rendered="#{treeDivBean.enableSearchByCode}" >
   <t:outputText value="" forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight" />
       <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeDivButton"  value="#{treeDivBean.searchType}" >
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{treeDivBean.searchTypeCode}"/>
           <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{treeDivBean.searchTypeName}"/>
       </t:selectOneRadio>
   </t:panelGrid>
</t:panelGroup>

<h:panelGrid columns="1"  >
    <t:panelGroup id="treeDivPanel" styleClass="TreeDivScrol" onkeypress="FireButtonClick('myForm:treeDivOkBtn')">
        <a4j:jsFunction name="updatetree" reRender="theSelectedNodeId,okPanel"></a4j:jsFunction>
        <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeDivList" forceId="true">
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
                                       onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';"
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
                                       onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivList','label');                                 document.getElementById('theSelectedNodeId').value ='#{node.treeId}';"
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
        <t:inputHidden forceId="true" value="#{treeDivBean.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/>
    </t:panelGroup>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="2" align="center">
        <h:panelGroup id="okPanel">
            <a4j:commandButton id="treeDivOkBtn" binding="#{treeDivBean.okCommandButton}" 
                                value="#{globalResources.ok}" styleClass="cssButtonSmall"
                                actionListener="#{treeDivBean.goActionOk}" disabled="#{treeDivBean.enabledRoot}"
                                rerender="contenet1_container, add_request_btns_pnl"     
                                oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/> 
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="backButtonTreeDiv" onblur="if(isVisibleComponent('divTree')){settingFoucsAtTreeDiv();}" onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeDivJs" actionListener="#{treeDivBean.goActionBack}" oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/>
        </t:panelGroup>
    </t:panelGrid>
    
</h:panelGrid>
 
<script type="text/javascript">
    function focusOnSearchResult(){
       //alert();
       if(document.getElementById("nodedivfocus") != null){
           setTimeout("document.getElementById('nodedivfocus').focus()",400);
           setTimeout("document.getElementById('nodedivfocus').select()",400);
       }
   }
   function focusAfterBackFromTreeDiv(){
        if(typeof(setFocusAfterBackFromTreeDiv)!='undefined'){
            setFocusAfterBackFromTreeDiv();
        }
   }
</script>
