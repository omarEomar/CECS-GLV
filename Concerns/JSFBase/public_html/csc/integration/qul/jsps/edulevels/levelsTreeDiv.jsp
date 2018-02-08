<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.dtoDetails}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchMode}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.showTreeContent}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchType}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.clientValueBinding}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.enabledNotLeaf}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.enableSearchByCode}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.keyIndex}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.selectionNo}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.cancelSearchMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.goActionOkMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.goActionBackMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.cancelSearchMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.idChangeMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchTreeMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.myTableData}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.startTreeLevel}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.enableSearch}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.selectedCenterId}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.cancelSearchMethod}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.returnMethodName}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.reRenderFields}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.intDivName}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.preOpenMethodName}"/>
<t:saveState value="#{qulEduLevelsIntegrationBean.containerBeanName}"/>
<t:panelGroup forceId="true" id="eduLevelTeeDiv">
    <t:panelGroup>
        <t:panelGroup forceId="true" id="radioEduLevelTreeDivPanel">
            <t:panelGrid align="center" border="0"
                         rendered="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.enableSearchByCode}">
                <t:outputText value="" forceId="true" id="errorEduLevelMessag_lov" styleClass="errMsgNoHeight"/>
                <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeEduLevelDivButton"
                                  value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchType}">
                    <f:selectItem itemLabel="#{globalResources.Code}"
                                  itemValue="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchTypeCode}"/>
                    <f:selectItem itemLabel="#{globalResources.name}"
                                  itemValue="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchTypeName}"/>
                </t:selectOneRadio>
            </t:panelGrid>
        </t:panelGroup>
        <t:panelGrid columns="3" align="center">
            <t:inputText forceId="true" id="searchEduLevelText"
                         value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchQuery}"
                         styleClass="textboxmedium" onkeypress="searchLevelsWhenEnter(event);"/>
            <h:panelGroup id="searchEduLevelpanel">
                <t:commandButton type="button" id="searchTreeEduLevelDivButtonID" forceId="true"
                                 onclick="if(validateCodeNameSrchParamter('radioTreeEduLevelDivButton','searchEduLevelText','','errorEduLevelMessag_lov')){ eduLeveltree_search();}"
                                 styleClass="ManyToManySearchButton"/>
                <a4j:jsFunction name="eduLeveltree_search" id="eduLeveltree_search"
                                action="#{qulEduLevelsIntegrationBean.searchTreeFromSpecificChar}"
                                reRender="noEduLevelResultmessage,errorEduLevelMessag_lov,treeEduLevelDivList,cancelsearchEduLevelpanel,noEduLevelResult,okEduLevelPanel"
                                oncomplete="focusOnSearchResult();"/>
            </h:panelGroup>
            <h:panelGroup id="cancelsearchEduLevelpanel">
                <f:verbatim>&nbsp;</f:verbatim>
                <a4j:commandButton action="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.cancelSearchTree}"
                                   reRender="treeEduLevelDivList,searchEduLevelText,cancelsearchEduLevelpanel,noEduLevelResult,clientErrorMessage,okEduLevelPanel,radioTreeDivPanel"
                                   rendered="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.searchMode == true}"
                                   styleClass="ManyToManyCancelSearchButton"
                                   binding="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.cancelSearchCommandButton}"/>
            </h:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGroup id="noEduLevelResult">
        <t:panelGrid align="center" rendered="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.serachResult}">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="errMsg"
                          id="noEduLevelResultmessage" forceId="true"/>
        </t:panelGrid>
    </t:panelGroup>
    <h:panelGrid columns="1" styleClass="TreeDivScrol">
        <t:panelGroup id="treeEduLevelDivPanel" onkeypress="FireButtonClick('myForm:treeEduLevelDivOkBtn')"
                      style="width:700px;height:225px;overflow-y:auto;">
            <a4j:jsFunction name="updatetree" reRender="theSelectedEduLevelNodeId,okEduLevelPanel"></a4j:jsFunction>
            <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeEduLevelDivList" forceId="true">
                <t:saveState value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.treeNodeBase}"/>
                <t:tree2 id="clientEduLevelTree"
                         value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.treeNodeBase}" var="node"
                         imageLocation="/app/media/images/ar/tree2" varNodeToggler="t"
                         binding="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.htmlTree}">
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
                                           onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeEduLevelDivList','label');document.getElementById('theSelectedEduLevelNodeId').value ='#{node.treeId}';"
                                           value="[#{node.privateCode}] #{node.description}" styleClass="treepage-link"></t:outputLabel>
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
                                           onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeEduLevelDivList','label');document.getElementById('theSelectedEduLevelNodeId').value ='#{node.treeId}';"
                                           value="[#{node.privateCode}] #{node.description}"
                                           styleClass="HigthLighttreepage-link"></t:outputLabel>
                            <t:inputText id="nodedivfocusDetails" forceId="true" styleClass="textboxnodefocus"/>
                        </h:panelGroup>
                    </f:facet>
                </t:tree2>
            </t:panelGrid>
            <!-- This part responsibility to highlight the node when selected-->
            <t:inputHidden forceId="true" id="theSelectedEduLevelNodeId"
                           value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.selectedNodeId}"
                           valueChangeListener="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.idChange}"/>
            <t:inputHidden forceId="true" value="0" id="rootEduLevelID"/>
            <t:inputHidden forceId="true" id="lastEduLevelNode" value=""/>
            <t:inputHidden forceId="true"
                           value="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.treeNodeNameForCollapseExpand}"
                           id="treeNodeEduLevelNameForCollapseExpand"/>
        </t:panelGroup>
        <f:verbatim>
            <br/>
        </f:verbatim>
    </h:panelGrid>
    <t:panelGrid columns="2" align="center">
        <h:panelGroup id="okEduLevelPanel">
            <a4j:commandButton id="treeEduLevelDivOkBtn"
                               binding="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.okCommandButton}"
                               value="#{globalResources.ok}" styleClass="cssButtonSmall"
                               reRender="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.reRender}"
                               actionListener="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.goActionOk}"
                               oncomplete="hidTreeDiv(null,window.blocker,window.#{qulEduLevelsIntegrationBean.intDivName},null);"/>
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="backButtonIntegrationDiv2"
                             onclick="backButtonTreeEduLevelDivJs();block(); " value="#{globalResources.back}"
                             styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeEduLevelDivJs"
                            actionListener="#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.goActionBack}"
                            oncomplete="hidTreeDiv(null,window.blocker,window.#{qulEduLevelsIntegrationBean.intDivName},null);"/>
        </t:panelGroup>
    </t:panelGrid>
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

  function searchLevelsWhenEnter(event) {
      if (event.keyCode == 13) {
          if (validateCodeNameSrchParamter('radioTreeEduLevelDivButton', 'searchEduLevelText', '', 'errorEduLevelMessag_lov')) {
              eduLeveltree_search();
          }
      }
      return;
  }
</script>