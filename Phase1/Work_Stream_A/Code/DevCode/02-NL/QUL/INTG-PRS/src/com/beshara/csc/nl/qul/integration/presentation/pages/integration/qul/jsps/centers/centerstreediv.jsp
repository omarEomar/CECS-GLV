<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.searchMode}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.showTreeContent}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.searchType}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.clientValueBinding}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.enabledNotLeaf}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.enableSearchByCode}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.keyIndex}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.selectionNo}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.cancelSearchMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.goActionOkMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.goActionBackMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.cancelSearchMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.idChangeMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.searchTreeMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.myTableData}"/>
<t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.startTreeLevel}"/>
<t:saveState value="#{qulCentersIntegrationBean.enableSearch}"/>
<t:saveState value="#{qulCentersIntegrationBean.selectedCountryId}"/>
<t:saveState value="#{qulCentersIntegrationBean.cancelSearchMethod}"/>
<t:saveState value="#{qulCentersIntegrationBean.returnMethodName}"/>
<t:saveState value="#{qulCentersIntegrationBean.reRenderFields}"/>
<t:saveState value="#{qulCentersIntegrationBean.intDivName}"/>
<t:saveState value="#{qulCentersIntegrationBean.preOpenMethodName}"/>
<t:saveState value="#{qulCentersIntegrationBean.containerBeanName}"/>
<t:panelGroup forceId="true" id="centersTeeDiv">
    <t:panelGroup forceId="true" id="radioCentersTreeDivPanel">
        <t:panelGrid align="center" border="0"
                     rendered="#{qulCentersIntegrationBean.treeCentersDivBean.enableSearchByCode}">
            <t:outputText value="" forceId="true" id="errorCenterMessag_lov" styleClass="errMsgNoHeight"/>
            <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeCentersDivButton"
                              value="#{qulCentersIntegrationBean.treeCentersDivBean.searchType}">
                <f:selectItem itemLabel="#{globalResources.Code}"
                              itemValue="#{qulCentersIntegrationBean.treeCentersDivBean.searchTypeCode}"/>
                <f:selectItem itemLabel="#{globalResources.name}"
                              itemValue="#{qulCentersIntegrationBean.treeCentersDivBean.searchTypeName}"/>
            </t:selectOneRadio>
        </t:panelGrid>
    </t:panelGroup>
    <h:panelGrid columns="3" align="center" border="0">
        <t:outputLabel rendered="#{!treeCentersDivBean.enableSearchByCode}" value="#{globalResources.name}"
                       styleClass="treepage-link"/>
        <t:inputText forceId="true" id="searchCentersText"
                     value="#{qulCentersIntegrationBean.treeCentersDivBean.searchQuery}" styleClass="textboxmedium"
                     onkeypress="searchCentersWhenEnter(event);"/>
        <t:panelGrid columns="2" align="center">
            <h:panelGroup id="searchCenterspanel">
                <t:commandButton type="button" id="searchTreeCenterDivButtonID" forceId="true"
                                 onclick="if(validateCodeNameSrchParamter('radioTreeCentersDivButton','searchCentersText','','errorCenterMessag_lov')){ centertree_search();}"
                                 styleClass="ManyToManySearchButton"/>
                <a4j:jsFunction name="centertree_search" id="centertree_search"
                                action="#{qulCentersIntegrationBean.treeCentersDivBean.searchTreeFromSpecificChar}"
                                reRender="noCenterResultmessage,errorCenterMessag_lov,treeCentersDivList,cancelsearchCenterspanel,noCenterResult,okCenterPanel"
                                oncomplete="focusOnSearchResult();"/>
            </h:panelGroup>
      <h:panelGroup id="cancelsearchCenterspanel">
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton action="#{qulCentersIntegrationBean.treeCentersDivBean.cancelSearchTree}"
                           reRender="treeCentersDivList,searchCentersText,cancelsearchCenterspanel,noCenterResult,clientErrorMessage,okCenterPanel,radioTreeDivPanel"
                           rendered="#{qulCentersIntegrationBean.treeCentersDivBean.searchMode == true}"
                           styleClass="ManyToManyCancelSearchButton"
                           binding="#{qulCentersIntegrationBean.treeCentersDivBean.cancelSearchCommandButton}"/>
      </h:panelGroup>
    </t:panelGrid>
    </h:panelGrid>
    <t:panelGroup id="noCenterResult">
        <t:panelGrid align="center" rendered="#{qulCentersIntegrationBean.treeCentersDivBean.serachResult}">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="errMsg"
                          id="noCenterResultmessage" forceId="true"/>
        </t:panelGrid>
    </t:panelGroup>
    <h:panelGrid columns="1">
        <t:panelGroup id="treeCenterDivPanel" styleClass="TreeDivScrol250"
                      onkeypress="FireButtonClick('myForm:treeCenterDivOkBtn')">
            <a4j:jsFunction name="updatetree" reRender="theSelectedCenterNodeId,okCenterPanel"></a4j:jsFunction>
            <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeCentersDivList" forceId="true">
                <t:saveState value="#{qulCentersIntegrationBean.treeCentersDivBean.treeNodeBase}"/>
                <t:tree2 id="clientCenterTree" value="#{qulCentersIntegrationBean.treeCentersDivBean.treeNodeBase}"
                         var="node" imageLocation="/app/media/images/ar/tree2" varNodeToggler="t"
                         binding="#{qulCentersIntegrationBean.treeCentersDivBean.htmlTree}">
                    <f:facet name="foo-folder">
                        <h:panelGroup>
                            <f:facet name="expand">
                                <t:graphicImage value="/app/media/images/folder-open.gif" rendered="#{t.nodeExpanded}"
                                                alt="" border="0"/>
                            </f:facet>
                            <f:facet name="collapse">
                                <t:graphicImage value="/app/media/images/folder-closed.gif"
                                                rendered="#{!t.nodeExpanded}" border="0"/>
                            </f:facet>
                            <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                           onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeCentersDivList','label');document.getElementById('theSelectedCenterNodeId').value ='#{node.treeId}';"
                                           value="[#{node.treeId}] #{node.description}" styleClass="treepage-link"></t:outputLabel>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="person-highlight">
                        <h:panelGroup>
                            <f:facet name="expand">
                                <t:graphicImage value="/app/media/images/blue-folder-open.gif"
                                                rendered="#{t.nodeExpanded}" alt="Can't find the image" border="0"/>
                            </f:facet>
                            <f:facet name="collapse">
                                <t:graphicImage value="/app/media/images/blue-folder-closed.png"
                                                rendered="#{!t.nodeExpanded}" alt="Can't find the image" border="0"/>
                            </f:facet>
                            <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                           onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeCentersDivList','label');document.getElementById('theSelectedCenterNodeId').value ='#{node.treeId}';"
                                           value="[#{node.treeId}] #{node.description}"
                                           styleClass="HigthLighttreepage-link"></t:outputLabel>
                            <t:inputText id="nodedivfocus" forceId="true" styleClass="textboxnodefocus"/>
                        </h:panelGroup>
                    </f:facet>
                </t:tree2>
            </t:panelGrid>
            <!-- This part responsibility to highlight the node when selected-->
            <t:inputHidden forceId="true" id="theSelectedCenterNodeId"
                           value="#{qulCentersIntegrationBean.treeCentersDivBean.selectedNodeId}"
                           valueChangeListener="#{qulCentersIntegrationBean.idChange}"/>
            <t:inputHidden forceId="true" value="0" id="rootCenterID"/>
            <t:inputHidden forceId="true" id="lastCenterNode" value=""/>
            <t:inputHidden forceId="true"
                           value="#{qulCentersIntegrationBean.treeCentersDivBean.treeNodeNameForCollapseExpand}"
                           id="treeNodeCenterNameForCollapseExpand"/>
        </t:panelGroup>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:panelGrid columns="2" align="center">
            <h:panelGroup id="okCenterPanel">
                <a4j:commandButton id="treeCenterDivOkBtn"
                                   binding="#{qulCentersIntegrationBean.treeCentersDivBean.okCommandButton}"
                                   value="#{globalResources.ok}" styleClass="cssButtonSmall"
                                   reRender="#{qulCentersIntegrationBean.treeCentersDivBean.reRender}"
                                   actionListener="#{qulCentersIntegrationBean.treeCentersDivBean.goActionOk}"
                                   oncomplete="hidTreeDiv(null,window.blocker,window.#{qulCentersIntegrationBean.intDivName},null);focusAfterBackFromTreeDiv();"/>
            </h:panelGroup>
            <t:panelGroup>
                <t:commandButton type="button" forceId="true" id="backButtonTreeDiv"
                                 onclick="backButtonTreeCenterDivJs();block(); " value="#{globalResources.back}"
                                 styleClass="cssButtonSmall"/>
                <a4j:jsFunction name="backButtonTreeCenterDivJs"
                                actionListener="#{qulCentersIntegrationBean.treeCentersDivBean.goActionBack}"
                                oncomplete="hidTreeDiv(null,window.blocker,window.#{qulCentersIntegrationBean.intDivName},null);focusAfterBackFromTreeDiv();"/>
            </t:panelGroup>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function focusOnSearchResult() {
      //alert();
      if (document.getElementById("nodedivfocus") != null) {
          setTimeout("document.getElementById('nodedivfocus').focus()", 400);
          setTimeout("document.getElementById('nodedivfocus').select()", 400);
      }
  }

  function focusAfterBackFromTreeDiv() {
      if (typeof (setFocusAfterBackFromTreeDiv) != 'undefined') {
          setFocusAfterBackFromTreeDiv();
      }
  }

  function searchCentersWhenEnter(event) {
      if (event.keyCode == 13) {
          if (validateCodeNameSrchParamter('radioTreeCentersDivButton', 'searchCentersText', '', 'errorCenterMessag_lov')) {
              centertree_search();
          }
          event.preventDefault();
      }
  }
</script>