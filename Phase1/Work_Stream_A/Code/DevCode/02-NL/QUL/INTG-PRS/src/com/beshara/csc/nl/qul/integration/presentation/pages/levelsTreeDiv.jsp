<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.dtoDetails}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.searchMode}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.showTreeContent}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.searchType}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.clientValueBinding}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.enabledNotLeaf}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.enableSearchByCode}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.keyIndex}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.selectionNo}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.cancelSearchMethod}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.goActionOkMethod}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.goActionBackMethod}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.cancelSearchMethod}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.idChangeMethod}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.searchTreeMethod}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.myTableData}"/>
<t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.startTreeLevel}"/>
<t:panelGroup forceId="true" id="treeDivPnlGrp">
    <t:panelGroup>
        <t:panelGroup forceId="true" id="radioTreeDivPanelLevels">
            <t:panelGrid align="center" border="0" rendered="#{detailBeanName.eduLevelsTreeDivBean.enableSearchByCode}">
                <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeDivButtonLevels"
                                  value="#{detailBeanName.eduLevelsTreeDivBean.searchType}">
                    <f:selectItem itemLabel="#{globalResources.Code}"
                                  itemValue="#{detailBeanName.eduLevelsTreeDivBean.searchTypeCode}"/>
                    <f:selectItem itemLabel="#{globalResources.name}"
                                  itemValue="#{detailBeanName.eduLevelsTreeDivBean.searchTypeName}"/>
                </t:selectOneRadio>
            </t:panelGrid>
        </t:panelGroup>
        <h:panelGrid columns="3" align="center">
            <t:outputLabel rendered="#{!detailBeanName.eduLevelsTreeDivBean.enableSearchByCode}"
                           value="#{globalResources.name}" styleClass="treepage-link"/>
            <t:inputText forceId="true" id="searchTextLevels" value="#{detailBeanName.eduLevelsTreeDivBean.searchQuery}"
                         styleClass="textboxmedium"
                         onkeypress="FireButtonClickOldStyle(event,'myForm:searchTreeDivButtonIDLevels'); "/>
            <t:panelGrid columns="2" align="center">
                <a4j:commandButton action="#{detailBeanName.eduLevelsTreeDivBean.searchTreeFromSpecificChar}"
                                   reRender="treeDivListLevels,cancelsearchpanelLevels,noResultLevels,okPanelLevels"
                                   oncomplete="focusOnSearchResultDetails();" styleClass="ManyToManySearchButton"
                                   id="searchTreeDivButtonIDLevels"/>
                <h:panelGroup id="cancelsearchpanelLevels">
                    <f:verbatim>&nbsp;</f:verbatim>
                    <a4j:commandButton action="#{detailBeanName.eduLevelsTreeDivBean.cancelSearchTree}"
                                       reRender="treeDivListLevels,searchTextLevels,cancelsearchpanelLevels,noResultLevels,clientErrorMessage,okPanelLevels,radioTreeDivPanelLevels"
                                       rendered="#{detailBeanName.eduLevelsTreeDivBean.searchMode == true}"
                                       styleClass="ManyToManyCancelSearchButton"
                                       binding="#{detailBeanName.eduLevelsTreeDivBean.cancelSearchCommandButton}"/>
                </h:panelGroup>
            </t:panelGrid>
        </h:panelGrid>
        <t:panelGroup id="noResultLevels">
            <t:panelGrid align="center" rendered="#{detailBeanName.eduLevelsTreeDivBean.serachResult}">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="errMsg"
                              id="noresultmessageLevels" forceId="true"/>
            </t:panelGrid>
        </t:panelGroup>
        <h:panelGrid columns="1" styleClass="TreeDivScrol">
            <t:panelGroup id="treeDivPanelLevels" onkeypress="FireButtonClick('myForm:treeDivOkBtnLevels')"
                          style="width:700px;height:225px;overflow-y:auto;">
                <a4j:jsFunction name="updatetree" reRender="theSelectedNodeIdLevels,okPanelLevels"></a4j:jsFunction>
                <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeDivListLevels" forceId="true">
                    <t:saveState value="#{detailBeanName.eduLevelsTreeDivBean.treeNodeBase}"/>
                    <t:tree2 id="clientTreeLevels" value="#{detailBeanName.eduLevelsTreeDivBean.treeNodeBase}"
                             var="node" imageLocation="/app/media/images/ar/tree2" varNodeToggler="t"
                             binding="#{detailBeanName.eduLevelsTreeDivBean.htmlTree}">
                        <f:facet name="foo-folder">
                            <h:panelGroup>
                                <f:facet name="expand">
                                    <t:graphicImage value="/app/media/images/folder-open.gif"
                                                    rendered="#{t.nodeExpanded}" alt="" border="0"/>
                                </f:facet>
                                <f:facet name="collapse">
                                    <t:graphicImage value="/app/media/images/folder-closed.gif"
                                                    rendered="#{!t.nodeExpanded}" border="0"/>
                                </f:facet>
                                <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                               onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivListLevels','label'); document.getElementById('theSelectedNodeIdLevels').value ='#{node.treeId}';"
                                               value="[#{node.privateCode}] #{node.description}"
                                               styleClass="treepage-link"></t:outputLabel>
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
                                                    rendered="#{!t.nodeExpanded}" alt="Can't find the image"
                                                    border="0"/>
                                </f:facet>
                                <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                               onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivListLevels','label'); document.getElementById('theSelectedNodeIdLevels').value ='#{node.treeId}';"
                                               value="[#{node.privateCode}] #{node.description}"
                                               styleClass="HigthLighttreepage-link"></t:outputLabel>
                                <t:inputText id="nodedivfocusDetails" forceId="true" styleClass="textboxnodefocus"/>
                            </h:panelGroup>
                        </f:facet>
                    </t:tree2>
                </t:panelGrid>
                <%-- This part responsibility to highlight the node when selected--%>
                <t:inputHidden forceId="true" id="theSelectedNodeIdLevels"
                               value="#{detailBeanName.eduLevelsTreeDivBean.selectedNodeId}"
                               valueChangeListener="#{detailBeanName.eduLevelsTreeDivBean.idChange}"/>
                <t:inputHidden forceId="true" value="0" id="rootIDLevels"/>
                <t:inputHidden forceId="true" id="lastNodeLevels" value=""/>
                <t:inputHidden forceId="true"
                               value="#{detailBeanName.eduLevelsTreeDivBean.treeNodeNameForCollapseExpand}"
                               id="treeNodeNameForCollapseExpandLevels"/>
            </t:panelGroup>
            <f:verbatim>
                <br/>
            </f:verbatim>
        </h:panelGrid>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <h:panelGroup id="okPanelLevels">
            <a4j:commandButton id="treeDivOkBtnLevels" binding="#{detailBeanName.eduLevelsTreeDivBean.okCommandButton}"
                               value="#{globalResources.ok}" styleClass="cssButtonSmall"
                               actionListener="#{detailBeanName.eduLevelsTreeDivBean.goActionOk}"
                               oncomplete="hidTreeDiv(null,window.blocker,window.divLov,null);focusAfterBackFromTreeDiv();"/>
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="lov_cancel"
                             onblur="if(isVisibleComponent('divLov')){settingFoucsAtTreeDiv();}"
                             onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}"
                             styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeDivJs"
                            actionListener="#{detailBeanName.eduLevelsTreeDivBean.goActionBack}"
                            oncomplete="hidTreeDiv(null,window.blocker,window.divLov,null);focusAfterBackFromTreeDiv();"
                            reRender="treeDivPnlGrp"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function focusOnSearchResult() {
      if (document.getElementById("nodedivfocus") != null) {
          setTimeout("document.getElementById('nodedivfocus').focus()", 400);
          setTimeout("document.getElementById('nodedivfocus').select()", 400);
      }
  }

  function focusOnSearchResultDetails() {
      if (document.getElementById("nodedivfocusDetails") != null) {
          setTimeout("document.getElementById('nodedivfocusDetails').focus()", 400);
          setTimeout("document.getElementById('nodedivfocusDetails').select()", 400);
      }
  }

  function focusAfterBackFromTreeDiv() {
      if (typeof (setFocusAfterBackFromTreeDiv) != 'undefined') {
          setFocusAfterBackFromTreeDiv();
      }
  }
</script>
