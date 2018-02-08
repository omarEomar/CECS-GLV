<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

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

<t:panelGroup forceId="true" id="radioTreeDivPanelaa">
   <t:panelGrid align="center"  border="0" rendered="#{treeDivBean.enableSearchByCode}" >
   <t:outputText value="" forceId="true" id="errorMessag_lovaa" styleClass="errMsgNoHeight" />
       <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeDivButton2" value="#{treeDivBean.searchType}" >
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{treeDivBean.searchTypeCode}"/>
           <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{treeDivBean.searchTypeName}"/>
       </t:selectOneRadio>
   </t:panelGrid>
</t:panelGroup>

<h:panelGrid columns="4" align="center" border="0">
    <h:outputText value="#{globalResources.name}" styleClass="treepage-link" rendered="#{!treeDivBean.enableSearchByCode}" />
    <t:panelGroup >
        <t:inputText forceId="true" id="searchText"  value="#{treeDivBean.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('searchTreeDivButtonID');"/>
        <t:outputText value="#{globalResources.missingField}" forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight" style="display:none;"/>
        <t:outputText value="#{intgBundle.not_anumber_msg}" forceId="true" id="errorMessag_lov_code" styleClass="errMsgNoHeight" style="display:none;"/>

    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <t:commandButton  type="button" id="searchTreeDivButtonID" forceId="true" onclick="if(validateSearchInput()){ tree_search();}" styleClass="ManyToManySearchButton" />
        <%--<a4j:commandButton onclick="if(validateCodeNameSrchParamter('radioTreeDivButton','searchText','','errorMessag_lov')){ lov_search();}" action="#{treeDivBean.searchTreeFromSpecificChar}" reRender="treeDivList,cancelsearchpanel,noResult,okPanel" oncomplete="focusOnSearchResult();" styleClass="ManyToManySearchButton" id="searchTreeDivButtonID"/>--%>
        <h:panelGroup id="cancelsearchpanel">
        
            <f:verbatim>&nbsp;</f:verbatim>
            <a4j:commandButton action="#{treeDivBean.cancelSearchTree}" reRender="div_panel,treeDivList,searchText,cancelsearchpanel,noResult,clientErrorMessage,okPanel,radioTreeDivPanel" rendered="#{treeDivBean.searchMode == true}"
                              styleClass="ManyToManyCancelSearchButton" binding="#{treeDivBean.cancelSearchCommandButton}"/>
    <a4j:jsFunction name="tree_search" id="tree_search" action="#{treeDivBean.searchTree}" reRender="div_panel,treeDivList,cancelsearchpanel,noResult,okPanel" oncomplete="focusOnSearchResult();"  />
        </h:panelGroup>
    </t:panelGrid>
</h:panelGrid>

<t:panelGrid columns="1"  id="div_panel" forceId="true" width="100%">
    <t:panelGroup id="treeDivPanel" styleClass="TreeDivScrol" onkeypress="FireButtonClick('myForm:treeDivOkBtn')" rendered="#{!treeDivBean.searchMode}">
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
                                       value="#{node.description}" styleClass="treepage-link"></t:outputLabel>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="person-highlight">
                    <h:panelGroup>
                        <f:facet name="expand">
                            <t:graphicImage value="/app/media/images/folder-open.gif" rendered="#{t.nodeExpanded}" alt="" border="0"/>
                        </f:facet>
                        <f:facet name="collapse">
                            <t:graphicImage value="/app/media/images/folder-closed.gif" rendered="#{!t.nodeExpanded}" border="0"/>
                        </f:facet>
                        <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                       onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';"
                                       value="#{node.description}" styleClass="treepage-link"></t:outputLabel>
                    </h:panelGroup>
                </f:facet>
            </t:tree2>
        </t:panelGrid>
       
    </t:panelGroup>
    
    <t:panelGroup forceId="true" id="dataTableSearchPanel" style="width:500px;height:150px;overflow-y:auto;" rendered="#{treeDivBean.searchMode}">
        <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="dataTableDivList" styleClass="lovDivScroll2">                
                <t:dataTable id="dataT_data1" var="list" 
                    value="#{treeDivBean.usingPaging ? treeDivBean.pagingBean.myPagedDataModel : treeDivBean.myTableData}" 
                    rowStyleClass="#{ treeDivBean.selected ? 'standardTable_RowHighlighted' : null}"
                    forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                    renderedIfEmpty="false" binding="#{treeDivBean.myDataTable}" style="width:480px !important;"
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
                            <a4j:support event="onclick" onsubmit="document.getElementById('theSelectedNodeId').value ='#{list.code.key}';" reRender="okPanel"/>
                        </t:selectOneRadio>
                    </t:column>
                    
                    <t:column id="code_column"  sortable="false" width="30%">         
                        <f:facet name="header" >
                            <t:outputText value="#{globalResources.Code}" id="content_code_label" forceId="true"/>
                        </f:facet>
                        <h:outputText id="content_code" value="#{list.actCode}"/>
                    </t:column>
            
                    <t:column id="name_column" sortable="false" width="65%">             
                        <f:facet name="header">
                            <t:outputText value="#{globalResources.name}" id="content_name_label" forceId="true"/>
                        </f:facet>
                        <t:outputText id="content_name" value="#{list.name}"/>
                    </t:column>
                
                </t:dataTable>
              </t:panelGrid>  
                <t:panelGroup id="noResult">
                    <t:panelGrid align="right" rendered="#{treeDivBean.listSize == 0}">
                        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg msg_no_icon" id="noresultmessage" forceId="true" style="display:block;"/>
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
                            for="dataT_data1"
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
            <!-- This part responsibility to highlight the node when selected-->
        
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="2" align="center">
        <t:panelGroup id="okPanel" forceId="true">
            <a4j:commandButton id="treeDivOkBtn" binding="#{treeDivBean.okCommandButton}"  value="#{globalResources.ok}" styleClass="cssButtonSmall" actionListener="#{treeDivBean.goActionOk}"
                               oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();" disabled="#{treeDivBean.dtoDetails == null || treeDivBean.dtoDetails.code == null}"/> 
        </t:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="backButtonTreeDiv" onblur="if(isVisibleComponent('divTree')){settingFoucsAtTreeDiv();}" onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeDivJs" actionListener="#{treeDivBean.goActionBack}" oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/>
        </t:panelGroup>
    </t:panelGrid>
    
</t:panelGrid>
<t:inputHidden forceId="true" id="theSelectedNodeId" value="#{treeDivBean.selectedNodeId}" valueChangeListener="#{treeDivBean.idChange}"/>
<t:inputHidden forceId="true" value="0" id="rootID"/>
<t:inputHidden forceId="true" id="lastNode" value=""/>
<t:inputHidden forceId="true" value="#{treeDivBean.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/> 
<script type="text/javascript">
  function validateSearchInput() {
      var searchValue = document.getElementById('searchText').value;
      var errMsg = document.getElementById('errorMessag_lov');
      var radio;
      var errMsg2 = document.getElementById('errorMessag_lov_code');

      errMsg.style.display = 'none';
      errMsg2.style.display = 'none';
      if (searchValue == null || searchValue == '') {
          errMsg.style.display = 'block';
          return false;
      }
      else {
          errMsg.style.display = 'none';

          if (document.forms['myForm'] != null) {
              radio = document['forms']['myForm']['radioTreeDivButton2'];

          }
          else if (document.forms['treeform'] != null) {
              radio = document['forms']['treeform']['radioTreeDivButton2'];
          }
          if (radio[0].checked && !radio[1].checked) {
              if (isNaN(searchValue)) {
                  errMsg2.style.display = 'block';
                  return false
              }else
               return true;
          }
          else {

              return true;
          }
      }

  }

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
</script>
