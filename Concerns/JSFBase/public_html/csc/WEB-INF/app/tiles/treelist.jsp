<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<tiles:importAttribute scope="request"/>
<t:saveState value="#{pageBeanName.dtoDetails}"/>
<t:saveState value="#{pageBeanName.keyIndex}"/>
<t:saveState value="#{pageBeanName.treeNodeNameForCollapseExpand}"/>
<t:saveState value="#{pageBeanName.searchResultListSize}"/>
 <a4j:jsFunction name="updatetree" reRender="treeAlertDelete,treeDetailsDiv,OperationBar" >
 </a4j:jsFunction>
          <t:outputText dir="ltr" forceId="true" id="listSize" style="width:100%"
                      styleClass="noOfRecords RNTree"
                      value="#{globalResources.noOfRecords} : #{OperationBar.recordsCount}"
                      rendered="#{OperationBar.recordsCountRendered}"/>              
 <t:panelGroup id="_noResults_continer_for_TreeList" forceId="true">
<t:panelGrid align="center" rendered="#{pageBeanName.serachResult && pageBeanName.searchMode}">
    <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
</t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="2" align="right" dir="#{jobResource.align}" id="treeList" forceId="true">
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.treeNodeBase}"/>
                    <t:tree2 id="clientTree" value="#{pageBeanName.treeNodeBase}" var="node"  imageLocation="/app/media/images/ar/tree2"  varNodeToggler="t" binding="#{pageBeanName.htmlTree}">
                         <f:facet name="foo-folder" >
                            <h:panelGroup>
                                <f:facet name="expand">
                                  <t:graphicImage value="/app/media/images/folder-open.gif"
                                                  rendered="#{t.nodeExpanded}"
                                                  alt="" border="0"/>
                                </f:facet>
                                <f:facet name="collapse">
                                  <t:graphicImage value="/app/media/images/folder-closed.gif"
                                                  rendered="#{!t.nodeExpanded}" border="0"/>
                                </f:facet>
                                <t:outputLabel id="commandDescription2" onclick="updatetree()" value="[#{node.treeId}] #{node.description}" styleClass="treepage-link" onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');showDivTreeDetails(this,'#{node.treeId}',window.#{divTreeDetails});">
                                        
                                    </t:outputLabel>
                          </h:panelGroup>
                        </f:facet>
                        <f:facet name="person-highlight">
                            <h:panelGroup>
                                <f:facet name="expand">
                                    <t:graphicImage value="/app/media/images/blue-folder-open.gif"
                                                  rendered="#{t.nodeExpanded}"
                                                  alt="Can't find the image" border="0"/>
                                </f:facet>
                                <f:facet name="collapse">
                                    <t:graphicImage value="/app/media/images/blue-folder-closed.png"
                                                  rendered="#{!t.nodeExpanded}"
                                                  alt="Can't find the image" border="0"/>
                                </f:facet>
                                <t:outputLabel id="commandDescription2" onclick="updatetree()" value="[#{node.treeId}] #{node.description}" styleClass="HigthLighttreepage-link" 
                                    onmousedown="javascript:showDivTreeDetails(this,'#{node.treeId}',window.#{divTreeDetails});" />
                                    <t:inputText id="nodefocus" forceId="true" styleClass="textboxnodefocus"/>
                            </h:panelGroup>
                        </f:facet>
                    </t:tree2>
                </t:panelGrid> 
                
                <!-- This part responsibility to highlight the node when selected-->
                <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{pageBeanName.selectedNodeId}" valueChangeListener="#{pageBeanName.idChange}"  /> 
                <t:inputHidden forceId="true" value="0" id="rootID"/>
                <t:inputHidden forceId="true" id="lastNode" value=""/>
                <t:inputHidden forceId="true" value="#{pageBeanName.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/>
                <f:verbatim>
                <script type="text/javascript"> 
                    if(document.getElementById("nodefocus") != null){
                       setTimeout("document.getElementById('nodefocus').focus()",400);
                   }
                   
                       foucsAddbuttonOnList();
                        function foucsAddbuttonOnList(){        
                            if(document.getElementById('searchButton') != null){            
                                document.getElementById('searchButton').focus();
                            }
                        }
                </script>
            </f:verbatim>