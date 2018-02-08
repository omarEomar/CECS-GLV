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
<t:saveState value="#{treeDivBean.treeNodeBase}"/>

<!-- Start Paging -->
<t:saveState value="#{treeDivBean.usingTreePaging}"/>
<t:saveState value="#{treeDivBean.treeSearchPagingRequestDTO}"/>
<t:saveState value="#{treeDivBean.treeListPagingRequestDTO}"/>

<t:saveState value="#{treeDivBean.currentPageIndex}"/>
<t:saveState value="#{treeDivBean.oldPageIndex}"/>
<t:saveState value="#{treeDivBean.sorting}"/>
<t:saveState value="#{treeDivBean.usingPaging}"/>
<t:saveState value="#{treeDivBean.updateMyPagedDataModel}"/>
<t:saveState value="#{treeDivBean.resettedPageIndex}"/>

<t:saveState value="#{treeDivBean.pagingRequestDTO}"/>
<t:saveState value="#{treeDivBean.selectedEntityKeyMethod}"/>
<t:saveState value="#{treeDivBean.pagingBean.totalListSize}"/>
<t:saveState value="#{treeDivBean.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{treeDivBean.pagingBean.preUpdatedDataModel}"/>

<t:saveState value="#{treeDivBean.selectedRadio}"/>
<!-- End Paging -->

<t:panelGroup forceId="true" id="pagedTreeDiv">
    
    <t:panelGroup style="display: block; text-align: center; width: 490px !important;">
        
        <h:panelGrid columns="3" style="text-align: center; margin: 0 auto !important;">
            <h:outputText value="#{globalResources.name}" styleClass="treepage-link"/>
            <t:inputText forceId="true" id="searchText" value="#{treeDivBean.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:searchTreeBtn')"/>
            <t:panelGrid columns="2" align="center">
                <a4j:commandButton id="searchTreeBtn" action="#{treeDivBean.searchTree}" reRender="pagedTreeDiv,cancelsearchpanel,noResult" oncomplete="focusOnSearchResult();" styleClass="ManyToManySearchButton"/>
                <t:panelGroup id="cancelsearchpanel">
                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <a4j:commandButton action="#{treeDivBean.cancelSearchTree}" reRender="pagedTreeDiv,searchText,cancelsearchpanel,noResult,clientErrorMessage" rendered="#{treeDivBean.searchMode == true}"
                        styleClass="ManyToManyCancelSearchButton"/>
                </t:panelGroup>
            </t:panelGrid>
        </h:panelGrid>
        
        <h:panelGrid columns="1" style="text-align: center;" width="100%">
            
            <t:panelGroup id="treeDivPanel" style="width:500px;height:150px;overflow-y:auto;" rendered="#{!treeDivBean.searchMode}">
                
                <a4j:jsFunction name="updatetree" reRender="theSelectedNodeId"></a4j:jsFunction>
                
                <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="treeDivList" style="display: block;height: 280px;Overflow-x: hidden;overflow-y: auto;width: 488px !important;">                    
                    <t:tree2 id="clientTree" value="#{treeDivBean.treeNodeBase}" 
                        var="node" imageLocation="/app/media/images/ar/tree2" 
                        varNodeToggler="t" binding="#{treeDivBean.htmlTree}"
                        clientSideToggle="false" showNav="false">
                        
                        <f:facet name="foo-folder">
                            <t:panelGroup>                                
                                <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{treeDivBean.expand}"
                                    reRender="pagedTreeDiv" onclick="updateScrollXYValue();" onmousedown="resetCmdTreeId();" oncomplete="resetScrollPosition();foucsAtServerTree();" 
                                    onkeypress="collapseExpandServerTree(this);">
                                    
                                    <t:graphicImage value="/app/media/images/new_expand_f.gif"
                                        rendered="#{!node.hasChild || (node.hasChild && node.childCount > 0 && !node.expanded)}" border="0"/>
                                    <t:graphicImage value="/app/media/images/new_expand_uf.gif"
                                        rendered="#{node.hasChild && node.childCount > 0 && node.expanded}" border="0"/>
                                    
                                </a4j:commandLink>
                                
                                <t:graphicImage value="/app/media/images/dots.gif" rendered="#{node.hasChild && node.childCount == 0}" border="0"/>
                                
                                <a4j:commandLink id="openCloseCmdLink" actionListener="#{treeDivBean.expand}"
                                    reRender="pagedTreeDiv" onclick="updateScrollXYValue();" onmousedown="resetCmdTreeId();" oncomplete="resetScrollPosition();foucsAtServerTree();"
                                    onkeypress="collapseExpandServerTree(this);">
                                        
                                    <t:graphicImage value="/app/media/images/folder-open.gif"
                                        rendered="#{node.hasChild && node.childCount > 0 && node.expanded}"
                                        border="0"/>
                                    
                                    <t:graphicImage value="/app/media/images/folder-closed.gif"
                                        rendered="#{!node.hasChild || (node.hasChild && node.childCount > 0 && !node.expanded)}" border="0"/>
                                    
                                </a4j:commandLink>
                                
                                <t:outputText id="commandDescription2" onclick="updatetree();"
                                    onmousedown="reStyle(this,'treeDivList','label'); document.getElementById('theSelectedNodeId').value='#{node.treeId}';enableOkBtnOnSelection();"
                                    value="[#{node.treeId}] #{node.description}" styleClass="treepage-link"/>
                                <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>    
                            </t:panelGroup>
                                
                        </f:facet>
                        
                    </t:tree2>
                    
                </t:panelGrid>
               
            </t:panelGroup>
            
            <t:panelGroup forceId="true" id="dataTableSearchPanel" style="width:500px;height:150px;overflow-y:auto;" rendered="#{treeDivBean.searchMode}">
<t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="dataTableDivList" styleClass="lovDivScroll2">                
                <t:dataTable id="dataT_data" var="list" 
                    value="#{treeDivBean.usingPaging ? treeDivBean.pagingBean.myPagedDataModel : treeDivBean.myTableData}" 
                    rowStyleClass="#{ treeDivBean.selected ? 'standardTable_RowHighlighted' : null}"
                    forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                    renderedIfEmpty="false" binding="#{treeDivBean.myDataTable}"
                    rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" 
                    styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2" 
                    columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
                    width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" >
                    
                    <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                        <f:facet name="header"/>
                        <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk"
                            value="#{treeDivBean.selectedRadio}" onkeypress="FireButtonClick('myForm:okBtn')"
                            onmousedown="toggleRadio(this);" onkeydown="toggleRadio(this);">
                            
                            <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                            <a4j:support event="onclick" onsubmit="document.getElementById('theSelectedNodeId').value ='#{list.code.key}';"/>
                        </t:selectOneRadio>
                    </t:column>
                    
                    <t:column id="code_column"  sortable="false" width="10%">         
                        <f:facet name="header" >
                            <t:outputText value="#{globalResources.Code}" id="content_code_label" forceId="true"/>
                        </f:facet>
                        <h:outputText id="content_code" value="#{list.code.elmguideCode}"/>
                    </t:column>
            
                    <t:column id="name_column" sortable="false" width="35%">             
                        <f:facet name="header">
                            <t:outputText value="#{globalResources.name}" id="content_name_label" forceId="true"/>
                        </f:facet>
                        <t:outputText id="content_name" value="#{list.name}"/>
                    </t:column>
                
                </t:dataTable>
              </t:panelGrid>  
                <t:panelGroup id="noResult">
                    <t:panelGrid align="center" rendered="#{treeDivBean.listSize == 0}">
                        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg msg_no_icon" id="noresultmessage" forceId="true"/>
                    </t:panelGrid>
                </t:panelGroup>
               
            </t:panelGroup>
            
            <t:panelGroup forceId="true" id="tree_div_paging_panel">
                
                <t:panelGrid id="search_tree_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" 
                    styleClass="scroller" width="300px" align="center"
                    rendered="#{treeDivBean.searchMode && treeDivBean.listSize > shared_util.noOfTableRows}">
                    
                  <t:dataScroller id="tree_div_scroll" 
                            fastStep="5" pageCountVar="pageCount" 
                            pageIndexVar="pageIndex"
                            paginator="true"
                            paginatorMaxPages="5"
                            paginatorTableClass="scroller"
                            fastfStyleClass="textpage"
                            fastrStyleClass="textpage"
                            firstStyleClass="textpage"
                            lastStyleClass="textpage"
                            nextStyleClass="textpage"
                            previousStyleClass="textpage"
                            paginatorColumnClass="textpage"
                            paginatorActiveColumnClass="paging"
                            paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                            styleClass="textpage"
                            immediate="false"
                            for="dataT_data"
                            renderFacetsIfSinglePage="false"
                            actionListener="#{treeDivBean.changePageIndex}">
                        <f:facet name="first" >                            
                            <h:panelGroup id="search_tree_panelGrp_first">
                                <t:graphicImage id="search_tree_img_firstOn"
                                                        rendered="#{pageIndex > 1}"
                                                        title="#{globalResources.scroller_first}"
                                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                        border="0"/>
                                <t:graphicImage id="search_tree_img_firstOff"
                                                        onclick="return false;"
                                                        rendered="#{pageIndex <= 1}"
                                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                        border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="last">                            
                            <h:panelGroup id="search_tree_panelGrp_last">
                                    <t:graphicImage id="search_tree_img_lastOn"
                                                    rendered="#{pageIndex < pageCount}"
                                                    title="#{globalResources.scroller_last}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                                    border="0"/>
                                    <t:graphicImage id="search_tree_img_lastOff"
                                                    onclick="return false;"
                                                    rendered="#{pageIndex >= pageCount}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                                    border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="previous">                            
                            <h:panelGroup id="search_tree_panelGrp_prv">
                                    <t:graphicImage id="search_tree_img_prvOn"
                                                    rendered="#{pageIndex > 1}"
                                                    title="#{globalResources.scroller_previous}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                                    border="0"/>
                                    <t:graphicImage id="search_tree_img_prvOff"
                                                    onclick="return false;"
                                                    rendered="#{pageIndex <= 1}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                                    border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="next">                            
                            <h:panelGroup id="search_tree_panelGrp_nxt">
                                    <t:graphicImage id="search_tree_img_nxtOn"
                                                    rendered="#{pageIndex < pageCount}"
                                                    title="#{globalResources.scroller_next}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                                    border="0"/>
                                    <t:graphicImage id="search_tree_img_nxtOff"
                                                    rendered="#{pageIndex >= pageCount}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                                    border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastforward">
                            <h:panelGroup id="search_tree_panelGrp_ffrwrd">
                                    <t:graphicImage id="search_tree_img_ffrwrdOn"
                                                    rendered="#{pageIndex < pageCount}"
                                                    title="#{globalResources.scroller_fastforward}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                                    border="0"/>
                                    <t:graphicImage id="search_tree_img_ffrwrdOff"
                                                    onclick="return false;"
                                                    rendered="#{pageIndex >= pageCount}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                                    border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastrewind">
                            <h:panelGroup id="search_tree_panelGrp_frwnd">
                                    <t:graphicImage id="search_tree_img_frwndOn"
                                                    rendered="#{pageIndex > 1}"
                                                    title="#{globalResources.scroller_fastrewind}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                                    border="0"/>
                                    <t:graphicImage id="search_tree_img_frwndOff"
                                                    onclick="return false;"
                                                    rendered="#{pageIndex <= 1}"
                                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                                    border="0"/>
                                  
                            </h:panelGroup>
                            
                        </f:facet>
                    </t:dataScroller>
                
                </t:panelGrid>
                
            </t:panelGroup>
            
            <t:panelGrid columns="2" align="center">
                <t:panelGroup forceId="true" id="okPanel">
                    <a4j:commandButton id="okBtn" binding="#{treeDivBean.okCommandButton}" value="#{globalResources.ok}" 
                        styleClass="cssButtonSmall" actionListener="#{treeDivBean.goActionOk}" 
                        oncomplete="hidTreeDiv(null,window.blocker,window.pagedDivTree,null);"/>
                </t:panelGroup>
                <t:commandButton forceId="true" id="backButtonPagedTreeDiv" onclick="backJsFunction();return false;" onblur="focusOnSearchText();" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" actionListener="#{treeDivBean.goActionBack}" oncomplete="hidTreeDiv(null,window.blocker,window.pagedDivTree,null);" reRender="pagedTreeDiv"/>
                 
            </t:panelGrid>
            
        </h:panelGrid>
    
    </t:panelGroup>
    
    <%-- This part responsibility to highlight the node when selected--%>
    <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{treeDivBean.selectedNodeId}" valueChangeListener="#{treeDivBean.idChange}"/>
    <t:inputHidden forceId="true" value="0" id="rootID"/>
    <t:inputHidden forceId="true" id="lastNode" value=""/>
    
    <t:inputHidden forceId="true" id="scrollX" value="#{treeDivBean.scrollX}"/>
    <t:inputHidden forceId="true" id="scrollY" value="#{treeDivBean.scrollY}"/>
    
</t:panelGroup>

<f:verbatim>
    
    <script language="javascript" type="text/javascript">
        
        function focusOnSearchResult() {
            if(document.getElementById("nodedivfocus") != null){
                setTimeout("document.getElementById('nodedivfocus').focus()",400);
                document.getElementById('nodedivfocus').focus();
            }
        }
        
        function focusOnSearchText() {
            if(document.getElementById("searchText") != null){
                setTimeout("document.getElementById('searchText').focus()",400);
                document.getElementById('searchText').focus();
            }
        }
        
        function updateScrollXYValue() {
        
            var x = document.getElementById('treeDivList').scrollLeft;
            document.getElementById('scrollX').value = x;
            
            var y = document.getElementById('treeDivList').scrollTop;
            document.getElementById('scrollY').value = y;
            
        }
        
        function resetScrollPosition() {
        
            var x = document.getElementById('scrollX').value;
            document.getElementById('treeDivList').scrollLeft = x;
            
            var y = document.getElementById('scrollY').value;
            document.getElementById('treeDivList').scrollTop = y;
            
        }
        
         
        
    </script>

</f:verbatim>
