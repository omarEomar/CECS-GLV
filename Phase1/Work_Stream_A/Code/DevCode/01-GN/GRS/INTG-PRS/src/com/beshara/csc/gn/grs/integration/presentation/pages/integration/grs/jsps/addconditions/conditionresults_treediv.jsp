<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<t:saveState value="#{workCentersTreeDivBean.enabledRoot}"/>
<t:saveState value="#{workCentersTreeDivBean.selectionNo}"/>
<t:saveState value="#{workCentersTreeDivBean.searchMode}"/>
<t:saveState value="#{workCentersTreeDivBean.showTreeContent}"/>
<t:saveState value="#{workCentersTreeDivBean.searchType}"/>
<t:saveState value="#{workCentersTreeDivBean.clientValueBinding}"/>
<t:saveState value="#{workCentersTreeDivBean.enabledNotLeaf}"/>
<t:saveState value="#{workCentersTreeDivBean.enableSearchByCode}"/>
<t:saveState value="#{workCentersTreeDivBean.keyIndex}"/>
<t:saveState value="#{workCentersTreeDivBean.cancelSearchMethod}"/>

<t:saveState value="#{workCentersTreeDivBean.goActionOkMethod}"/>
<t:saveState value="#{workCentersTreeDivBean.goActionBackMethod}"/>
<t:saveState value="#{workCentersTreeDivBean.cancelSearchMethod}"/>
<t:saveState value="#{workCentersTreeDivBean.idChangeMethod}"/>
<t:saveState value="#{workCentersTreeDivBean.searchTreeMethod}"/>

<t:saveState value="#{workCentersTreeDivBean.myTableData}"/>
<t:saveState value="#{workCentersTreeDivBean.startTreeLevel}"/>

<t:saveState value="#{workCentersTreeDivBean.searchMode}"/>
<t:saveState value="#{workCentersTreeDivBean.searchResultListSize}"/>
<t:saveState value="#{workCentersTreeDivBean.treeNodeBase}"/>
<t:saveState value="#{workCentersTreeDivBean.usingTreePaging}"/>
<t:saveState value="#{workCentersTreeDivBean.treeSearchPagingRequestDTO}"/>
<t:saveState value="#{workCentersTreeDivBean.treeListPagingRequestDTO}"/>    
<t:saveState value="#{workCentersTreeDivBean.pagedTreeListSize}"/>
<t:saveState value="#{workCentersTreeDivBean.dtoDetails}"/>
<t:saveState value="#{workCentersTreeDivBean.selectedNodeTreeLevelId}"/>
<t:saveState value="#{workCentersTreeDivBean.saveResult}"/>
<t:saveState value="#{workCentersTreeDivBean.minCode}"/>

<t:panelGroup forceId="true" id="ministryTreeDivPnl">
    
    <t:panelGroup forceId="true" id="radioTreeDivPanel">
   <t:panelGrid align="center"  border="0" rendered="#{workCentersTreeDivBean.enableSearchByCode}" >
       <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeDivButton"  value="#{workCentersTreeDivBean.searchType}" >
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{workCentersTreeDivBean.searchTypeCode}"/>
           <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{workCentersTreeDivBean.searchTypeName}"/>
       </t:selectOneRadio>
   </t:panelGrid>
</t:panelGroup>

<h:panelGrid columns="2" align="center" style="text-align:center;" width="100%">
    <t:outputLabel rendered="#{!workCentersTreeDivBean.enableSearchByCode}" value="#{globalResources.name}" styleClass="treepage-link"/>
    <t:panelGroup >
        <t:inputText forceId="true" id="searchText"  value="#{workCentersTreeDivBean.searchQuery}" styleClass="textboxmedium" onkeypress="if(event.keyCode == 13){doClick('searchTreeDivButtonID');event.preventDefault();}"/>
        <t:commandButton type="button" forceId="true" id="searchTreeDivButtonID" onclick="searchTreeDivButtonJs();" styleClass="ManyToManySearchButton"/>
        <a4j:jsFunction name="searchTreeDivButtonJs" action="#{workCentersTreeDivBean.searchinTree}" reRender="treeList,cancelsearchpanel,noResult,okPanel,theSelectedNodeId" oncomplete="focusOnSearchResult();"/>
        <h:panelGroup id="cancelsearchpanel">
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton action="#{workCentersTreeDivBean.cancelSearchTree}" reRender="treeDivList,searchText,cancelsearchpanel,noResult,clientErrorMessage,okPanel,radioTreeDivPanel,theSelectedNodeId" rendered="#{workCentersTreeDivBean.searchMode == true}"
                              styleClass="ManyToManyCancelSearchButton" binding="#{workCentersTreeDivBean.cancelSearchCommandButton}" oncomplete="document.getElementById('theSelectedNodeId').value ='';"/>
        </h:panelGroup>
    </t:panelGroup>
    </h:panelGrid>
    
<t:panelGrid width="100%">
   
    <t:panelGroup forceId="true" id="noResult">
        <t:panelGrid align="center" rendered="#{workCentersTreeDivBean.searchMode && workCentersTreeDivBean.searchResultListSize == 0}" >
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeList" forceId="true" >       
        <t:panelGroup id="treeDivPanel" forceId="true"  styleClass="lovDivScroll">  
 
            <a4j:jsFunction name="updatetree" reRender="treeList,OperationBar,treeAlertDelete,treeDetailsDiv,theSelectedNodeId,okPanel,noResult" oncomplete="foucsAtServerTree();focusHighlightedNode();" ></a4j:jsFunction>
  
                <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="treeDivList">
 
                    <t:tree2 id="clientTree" value="#{workCentersTreeDivBean.treeNodeBase}"
                        var="node" imageLocation="/app/media/images/ar/tree2" 
                        varNodeToggler="t" binding="#{workCentersTreeDivBean.htmlTree}"
                        clientSideToggle="false" showNav="false">

                        <f:facet name="foo-folder">
                            <t:panelGroup>
                                <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{workCentersTreeDivBean.expand}"
                                    reRender="treeList,cancelsearchpanel"  oncomplete="foucsAtServerTree();" 
                                    onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');setCmdTreeId(this);reStyle(this,'treeDivList','label');"
                                    onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);" rendered="#{node.hasChild && workCentersTreeDivBean.listSize >0}">

                                    <t:graphicImage value="/app/media/images/minus_open_Root.gif"
                                        rendered="#{node.parent == null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                        rendered="#{node.parent == null && !node.expanded}" border="0"/>                                        
                                    <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                        rendered="#{node.parent != null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                        rendered="#{node.parent != null && !node.expanded}" border="0"/>
                                </a4j:commandLink>

                                <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{!node.hasChild || workCentersTreeDivBean.listSize == 0}" border="0"/>                                
                                <t:commandLink id="hiddenLink" value="" />
                                <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                    onmousedown="reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';document.getElementById('selectedNodeTreeLevelId').value ='#{node.path}';javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');"
                                    value="[#{node.treeId}] #{node.description}" styleClass="#{workCentersTreeDivBean.selectedStyleClass}"/> <%-- #{node.treeNodeLevelsID}--%>
                                <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>    
                            </t:panelGroup>

                        </f:facet>

                        <f:facet name="person-highlight" >
                                <t:panelGroup>
                                    <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{workCentersTreeDivBean.expand}"
                                    reRender="treeList" onmousedown="setCmdTreeId(this);" oncomplete="foucsAtServerTree();" 
                                    onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);" rendered="#{node.hasChild}">
                                    
                                    <t:graphicImage value="/app/media/images/minus_open_Root.gif"
                                        rendered="#{node.parent == null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                        rendered="#{node.parent == null && !node.expanded}" border="0"/>                                        
                                    <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                        rendered="#{node.parent != null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                        rendered="#{node.parent != null && !node.expanded}" border="0"/>
                                </a4j:commandLink>
                                
                                <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{!node.hasChild}" border="0"/>
                                  <t:commandLink id="hiddenLink"  value=""  />
                                   <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                       onmousedown="reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';document.getElementById('selectedNodeTreeLevelId').value ='#{node.path}';javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');"
                                        value="[#{node.treeId}] #{node.description}" styleClass="#{workCentersTreeDivBean.searchSelectedStyleClass}"/> <%-- #{node.treeNodeLevelsID}--%>
                                    <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>
                                     <t:inputText id="nodedivfocus" forceId="true" styleClass="textboxnodefocus"/>
                                </t:panelGroup>
                            </f:facet>
                        
                    </t:tree2>
                    
                </t:panelGrid>
               
            </t:panelGroup>
                        
                    
        </t:panelGrid>
                    <!-- This part responsibility to highlight the node when selected-->
                    <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{workCentersTreeDivBean.selectedNodeId}"  valueChangeListener="#{workCentersTreeDivBean.idChange}"/>
                    <t:inputHidden forceId="true" value="0" id="rootID"/>
                    <t:inputHidden forceId="true" id="lastNode" value=""/>
                    <t:inputHidden forceId="true" value="#{workCentersTreeDivBean.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/>
                    <t:inputHidden forceId="true" id="selectedNodeTreeLevelId" value="#{workCentersTreeDivBean.selectedNodeTreeLevelId}"/>
                          <t:panelGrid columns="2" align="center">
        <h:panelGroup id="okPanel">
            <a4j:commandButton id="treeDivOkBtn" binding="#{workCentersTreeDivBean.okCommandButton}"  value="#{globalResources.ok}" styleClass="cssButtonSmall" actionListener="#{workCentersTreeDivBean.goActionOk}"
                               oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();" /> 
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="backButtonTreeDiv" onblur="if(isVisibleComponent('divTree')){settingFoucsAtTreeDiv();}" onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeDivJs" actionListener="#{workCentersTreeDivBean.goActionBack}" oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/>
        </t:panelGroup>
    </t:panelGrid>
                    </t:panelGrid>
</t:panelGroup>
<f:verbatim>
    <script language="javascript" type="text/javascript">
//        if(document.getElementById("nodefocus") != null){
//           setTimeout("document.getElementById('nodefocus').focus()",400);
//       }
      
//    if(document.getElementById("selectedNodeTreeLevelId")!=null)
//       alert(document.getElementById("selectedNodeTreeLevelId").value);

      setTimeout("collapseExpandTreeNode();",400);
      setTimeout("focusHighlightedNode();",400);
      setTimeout("focusOnSearchResult();",400);
       function focusHighlightedNode(){  
           //alert(document.getElementById("selectedNodeTreeLevelId").value);
        
           if(document.getElementById("selectedNodeTreeLevelId")!=null)
               {
                var levelId=document.getElementById("selectedNodeTreeLevelId").value;
                if(levelId != ""){
                    var cmdId="myForm:clientTree:"+levelId+":expandCollapseCmdLink";
                    if(document.getElementById(cmdId)==null){
                        cmdId="myForm:clientTree:"+levelId+":hiddenLink";
                    }     
                    resetScrollPositionAfterSelection(cmdId);
                    document.getElementById(cmdId).focus();
                    document.getElementById(cmdId).focus();
                }
           }
       
       }
        function focusOnSearchResult(){
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
    function resetScrollPositionAfterSelection(cmdId) {
        if(document.getElementById(cmdId)!=null){
            var x =findPosX(cmdId); 
            var y = findPosY(cmdId);
            document.getElementById('treeDivPanel').scrollLeft = x;
            document.getElementById('treeDivPanel').scrollTop = y;
            
        } else {
       // alert('prob');
        }
    }
        
        
    </script>
</f:verbatim>
