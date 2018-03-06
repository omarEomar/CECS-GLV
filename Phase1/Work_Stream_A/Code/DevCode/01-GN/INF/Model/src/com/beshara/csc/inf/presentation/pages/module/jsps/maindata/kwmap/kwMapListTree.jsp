<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

    
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.treeNodeBase}"/>
<t:saveState value="#{pageBeanName.usingTreePaging}"/>
<t:saveState value="#{pageBeanName.treeSearchPagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.treeListPagingRequestDTO}"/>    
<t:saveState value="#{pageBeanName.pagedTreeListSize}"/>
<t:saveState value="#{pageBeanName.dto}"/>
<t:saveState value="#{pageBeanName.dtoDetails}"/>
<t:saveState value="#{pageBeanName.selectedNodeTreeLevelId}"/>
<t:saveState value="#{pageBeanName.selectedNodeId}"/>
    
<t:panelGrid width="100%">
   
    <a4j:jsFunction action="#{pageBeanName.viewDetailsDto}" name="updatetree" reRender="treeList,OperationBar,treeAlertDelete,treeDetailsDiv,theSelectedNodeId,okPanel" oncomplete="foucsAtServerTree();" ></a4j:jsFunction>
    
    <t:panelGrid align="center" rendered="#{pageBeanName.serachResult}">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
    
    <t:panelGrid columns="2" align="right" dir="#{jobResource.align}" id="treeList" forceId="true">       

            <t:panelGroup id="treeDivPanel">                

                <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="treeDivList">
 
                    <t:tree2 id="clientTree" value="#{pageBeanName.treeNodeBase}" 
                        var="node" imageLocation="/app/media/images/ar/tree2" 
                        varNodeToggler="t" binding="#{pageBeanName.htmlTree}"
                        clientSideToggle="false" showNav="false">

                        <f:facet name="foo-folder">
                            <t:panelGroup>
                                <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{pageBeanName.expand}"
                                    reRender="treeList"  oncomplete="foucsAtServerTree();" 
                                    onmousedown="setCmdTreeId(this);"
                                    onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);" rendered="#{node.hasChild && pageBeanName.listSize >0}">

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
                                        rendered="#{!node.hasChild || pageBeanName.listSize == 0}" border="0"/>                                
                                <t:commandLink id="hiddenLink" value=""  />
                                <t:outputText id="commandDescription2" onclick="updatetree();"
                                    onmousedown=" setCmdTreeId(); document.getElementById('theSelectedNodeId').value='#{pageBeanName.nodeId}'; showDivTreeDetails(this,'#{pageBeanName.nodeId}',window.divTreeDetails);"
                                    value="[#{node.privateCode}] #{node.description}" styleClass="#{pageBeanName.selectedStyleClass}"/>
                                <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>    
                            </t:panelGroup>

                        </f:facet>

                        <f:facet name="person-highlight">
                                <t:panelGroup>
                                    <a4j:commandLink id="expandCollapseCmdLink1" actionListener="#{pageBeanName.expand}"
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
                                  <t:commandLink id="hiddenLink3"  value=""  />
                                   <t:outputText id="commandDescription23" onclick="updatetree();"
                                        onmousedown="setCmdTreeId(); document.getElementById('theSelectedNodeId').value='#{node.detailId}';showDivTreeDetails(this,'#{node.detailId}',window.divTreeDetails);"
                                        value="[#{node.code}] #{node.description}" styleClass="HigthLighttreepage-link"/> <%-- #{node.treeNodeLevelsID}--%>
                                    <t:inputHidden id="expandCollapseValue3" value="#{node.expanded}"/>
                                </t:panelGroup>
                            </f:facet>
                        
                    </t:tree2>
                    
                </t:panelGrid>
            </t:panelGroup>
        </t:panelGrid>
    <!-- This part responsibility to highlight the node when selected-->
    <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{pageBeanName.selectedNodeId}" valueChangeListener="#{pageBeanName.idChange}"/>
    <t:inputHidden forceId="true" value="0" id="rootID"/>
    <t:inputHidden forceId="true" id="lastNode" value=""/>
    <t:inputHidden forceId="true" value="#{pageBeanName.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/>
    <t:inputHidden forceId="true" id="selectedNodeTreeLevelId" value="#{pageBeanName.selectedNodeTreeLevelId}"/>
</t:panelGrid>

<f:verbatim>
    <script language="javascript" type="text/javascript">
        setTimeout("focusHighlightedNode();",400);
        function focusHighlightedNode() {
           if(document.getElementById("selectedNodeTreeLevelId")!=null) {
                var levelId=document.getElementById("selectedNodeTreeLevelId").value;
                if(levelId != "") {
                    var cmdId="treeform:clientTree:"+levelId+":expandCollapseCmdLink";
                    if(document.getElementById(cmdId)==null){
                        cmdId="treeform:clientTree:"+levelId+":hiddenLink";
                    }
                    resetScrollPositionAfterSelection(cmdId);
                    document.getElementById(cmdId).focus();
                    document.getElementById(cmdId).focus();
                }
           }
        }
       
        function resetScrollPositionAfterSelection(cmdId) {
            if(document.getElementById(cmdId)!=null){
                var x =findPosX(document.getElementById(cmdId)); 
                var y = findPosY(document.getElementById(cmdId));
                document.getElementById('treeform:treeDivPanel').scrollLeft = x;
                document.getElementById('treeform:treeDivPanel').scrollTop = y;
            }
        }
    </script>
</f:verbatim>
