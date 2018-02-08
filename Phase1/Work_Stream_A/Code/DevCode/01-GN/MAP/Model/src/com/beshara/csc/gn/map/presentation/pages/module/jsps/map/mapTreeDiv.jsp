<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{pageBeanName.dtoDetails}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<%--<t:saveState value="#{pageBeanName.showTreeContent}"/>--%>
<t:saveState value="#{pageBeanName.searchType}"/>
<%--<t:saveState value="#{pageBeanName.clientValueBinding}"/>--%>
<t:saveState value="#{pageBeanName.enabledNotLeaf}"/>
<t:saveState value="#{pageBeanName.enableSearchByCode}"/>
<t:saveState value="#{pageBeanName.keyIndex}"/>
<t:saveState value="#{pageBeanName.selectionNo}"/>
<t:saveState value="#{pageBeanName.cancelSearchMethod}"/>

<t:saveState value="#{pageBeanName.goActionOkMethod}"/>
<t:saveState value="#{pageBeanName.goActionBackMethod}"/>
<t:saveState value="#{pageBeanName.cancelSearchMethod}"/>
<t:saveState value="#{pageBeanName.idChangeMethod}"/>
<t:saveState value="#{pageBeanName.searchTreeMethod}"/>

<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.startTreeLevel}"/>

<t:panelGroup forceId="true" id="radioTreeDivPanel">
   <t:panelGrid align="center"  border="0" rendered="#{pageBeanName.enableSearchByCode}" >
   <t:outputText value="" forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight" />
       <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeDivButton"  value="#{pageBeanName.searchType}" >
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{pageBeanName.searchTypeCode}"/>
           <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{pageBeanName.searchTypeName}"/>
       </t:selectOneRadio>
   </t:panelGrid>
</t:panelGroup>

<h:panelGrid columns="3" align="center" border="0">
    <t:outputLabel rendered="#{!pageBeanName.enableSearchByCode}" value="#{globalResources.name}" styleClass="treepage-link"/>
    <t:inputText forceId="true" id="searchText"  value="#{pageBeanName.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:searchTreeDivButtonID');"/>
    <t:panelGrid columns="2" align="center">
        <t:commandButton  type="button" id="searchTreeDivButtonID" forceId="true" onclick="if(validateCodeNameSrchParamter('radioTreeDivButton','searchText','','errorMessag_lov')){ tree_search();}" styleClass="ManyToManySearchButton" />
        <%--<a4j:commandButton onclick="if(validateCodeNameSrchParamter('radioTreeDivButton','searchText','','errorMessag_lov')){ lov_search();}" action="#{pageBeanName.searchTreeFromSpecificChar}" reRender="treeDivList,cancelsearchpanel,noResult,okPanel" oncomplete="focusOnSearchResult();" styleClass="ManyToManySearchButton" id="searchTreeDivButtonID"/>--%>
        <h:panelGroup id="cancelsearchpanel">
        
            <f:verbatim>&nbsp;</f:verbatim>
            <a4j:commandButton action="#{pageBeanName.cancelSearchTree}" reRender="treeDivList,searchText,cancelsearchpanel,noResult,clientErrorMessage,okPanel,radioTreeDivPanel" rendered="#{pageBeanName.searchMode == true}"
                              styleClass="ManyToManyCancelSearchButton" binding="#{pageBeanName.cancelSearchCommandButton}"/>
    <a4j:jsFunction name="tree_search" id="tree_search" action="#{pageBeanName.searchTreeFromSpecificChar}" reRender="treeDivList,cancelsearchpanel,noResult,okPanel" oncomplete="focusOnSearchResult();"  />
        </h:panelGroup>
    </t:panelGrid>
</h:panelGrid>
<t:panelGroup id="noResult">
    <t:panelGrid align="center" rendered="#{pageBeanName.serachResult}" >
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="errMsg" id="noresultmessage" forceId="true"/>
    </t:panelGrid>
</t:panelGroup>
<h:panelGrid columns="1"  >
    <t:panelGroup id="treeDivPanel" styleClass="TreeDivScrol" onkeypress="FireButtonClick('myForm:treeDivOkBtn')">
        <a4j:jsFunction name="updatetree" reRender="theSelectedNodeId,okPanel"></a4j:jsFunction>
        <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeDivList" forceId="true">
            <t:saveState value="#{pageBeanName.treeNodeBase}"/>
            <t:tree2 id="clientTree" value="#{pageBeanName.treeNodeBase}" var="node" imageLocation="/app/media/images/ar/tree2" varNodeToggler="t" binding="#{pageBeanName.htmlTree}">
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
        <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{pageBeanName.selectedNodeId}" valueChangeListener="#{pageBeanName.idChange}"/>
        <t:inputHidden forceId="true" value="0" id="rootID"/>
        <t:inputHidden forceId="true" id="lastNode" value=""/>
        <t:inputHidden forceId="true" value="#{pageBeanName.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/>
    </t:panelGroup>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="2" align="center">
        <h:panelGroup id="okPanel">
            <a4j:commandButton id="treeDivOkBtn" binding="#{pageBeanName.okCommandButton}"  value="#{globalResources.ok}" 
            styleClass="cssButtonSmall" actionListener="#{pageBeanName.goActionOk}"
                               oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();changeVisibilityMsg();"
                               reRender="data_tableRendering,second_dataT_group,divMsg"
                                disabled="#{!pageBeanName.enabledNotLeaf}"                              
                               />
                               
                               
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="backButtonTreeDiv" onblur="if(isVisibleComponent('divTree')){settingFoucsAtTreeDiv();}" onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeDivJs" actionListener="#{pageBeanName.goActionBack}" oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/>
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
