<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGrid align="center">
    <t:selectOneRadio forceId="true" id="module_div_searchRadioBtn" value="#{pageBeanName.moduleSearchType}"
                      styleClass="divtext">
        <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
        <f:selectItem itemLabel="#{resourcesBundle.module_name}" itemValue="1"/>
    </t:selectOneRadio>
</t:panelGrid>
<t:panelGrid id="search_module_container" columns="1" border="0" cellspacing="0" cellpadding="3" align="center">
    
    <%-- <h:outputText value="#{resourcesBundle.system_lbl}" styleClass="lable01"/>--%>
    <t:inputText forceId="true" id="moduleSearchValueID"  onkeypress="searchWithEnterKeyModules(event);" styleClass="textboxlarge" align="center"  value="#{pageBeanName.moduleSearchValue}"/>        
        
       <htm:center>
        <t:outputLabel id="searchModulesError"  forceId="true" styleClass="error" />
        </htm:center>
</t:panelGrid>
<t:panelGrid columns="2" align="center">
    <t:panelGroup id="search_module_container_btns">
        <t:commandButton type="button" forceId="true" id="module_div_search_btn" onclick="goSearchModules('#{globalResources.missingField}' , '#{resourcesBundle.not_anumber_msg}' );" styleClass="ManyToManySearchButton"/>
        <a4j:jsFunction name="searchmodulesFunc" action="#{pageBeanName.searchModules}"
                        reRender="cancel_search_module_pnl,modules_div_cancelSearch,search_module_container,search_module_container_btns,modules_dataT_data_panel,_modules_dataT_data,module_div_paging_panel"/>
    </t:panelGroup>
    <t:panelGroup id="cancel_search_module_pnl">
        <a4j:commandButton id="modules_div_cancelSearch" action="#{pageBeanName.cancelSearchModules}"
                           styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.moduleSearchMode}"
                           reRender="search_module_container,search_module_container_btns,modules_dataT_data_panel,_modules_dataT_data,cancel_search_module_pnl,module_div_paging_panel"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="modules_dataT_data_panel" styleClass="lovDivScroll5"
                  style="width:100% !important;max-height:150px!important;">
        <t:dataTable id="_modules_dataT_data" var="list" value="#{pageBeanName.modulesDTOS}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.modulesDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk"
                                  value="#{pageBeanName.selectedModuleRadio}" onmousedown="toggleRadio(this);"
                                  onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedModuleRadioButton}"
                                 reRender="module_div_btns_pnl"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" />
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="32%">
                <f:facet name="header">
                     <t:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.module_name}"/>
                
                </f:facet>
                <h:inputText id="content_name" value="#{list.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.modulesDTOS}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    </t:panelGroup>
</t:panelGrid>
<!-- paging ------->
<t:panelGrid>
    <t:panelGroup forceId="true" id="module_div_paging_panel">
        <t:panelGrid align="center" id="module_div_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}"
                     styleClass="scroller" width="550px" rendered="true">
            <t:dataScroller id="module_div_scroll_a1" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                        binding="#{pageBeanName.modulesScroller}" paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
                            fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage"
                            lastStyleClass="textpage" nextStyleClass="textpage" previousStyleClass="textpage"
                            paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                            paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                            styleClass="textpage" immediate="false" for="_modules_dataT_data"
                            renderFacetsIfSinglePage="false" actionListener="#{pageBeanName.loadModulesDivPagingData}">
                <f:facet name="first">
                    <h:panelGroup id="module_div_jobs_list_panelGrp_first">
                        <t:graphicImage id="module_div_jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="module_div_jobs_list_img_firstOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <h:panelGroup id="module_div_jobs_list_panelGrp_last">
                        <t:graphicImage id="module_div_jobs_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="module_div_jobs_list_img_lastOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <h:panelGroup id="module_div_jobs_list_panelGrp_prv">
                        <t:graphicImage id="module_div_jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="module_div_jobs_list_img_prvOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="module_div_jobs_list_panelGrp_nxt">
                        <t:graphicImage id="module_div_jobs_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="module_div_jobs_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="module_div_jobs_list_panelGrp_ffrwrd">
                        <t:graphicImage id="module_div_obs_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="module_div_jobs_list_img_ffrwrdOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="module_div_jobs_list_panelGrp_frwnd">
                        <t:graphicImage id="module_div_jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="module_div_lov_jobs_list_img_frwndOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
            </t:dataScroller>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<!-- end paging ------>
<t:panelGrid columns="2" align="center" id="module_div_btns_pnl" forceId="true">
    <t:panelGroup>
    <t:commandButton type="button" id="module_ok_btn" value="#{globalResources.ok}" onclick="okJsFunction(); return false;" 
        styleClass="cssButtonSmall" disabled="#{empty pageBeanName.selectedModulesDTOS}"/>
    <%-- oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);" rerender="systempOrmachine_pnld"--%>
    <a4j:jsFunction name="okJsFunction" oncomplete=" hideLookUpDiv(window.blocker,window.customDiv1); "
                    reRender="systempOrmachine_pnld,coord_pnl" action="#{pageBeanName.onCloseModulesDiv}"/>
    </t:panelGroup>
    
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" onclick="backJsFunctionMod(); return false;"
        styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="backJsFunctionMod" oncomplete="hideLookUpDiv(window.blocker,window.customDiv1); "/>
    </h:panelGroup>
</t:panelGrid>
<!-- added by nora to enable single selection -->
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<!-- Start Paging -->
<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
<script type="text/javascript">
  foucsAddbuttonOnList();

  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }

      function FireButton(buttonId) {
          var button = document.getElementById(buttonId);
          button.click();

      }
      
      
      function goSearchModules(message , messageForLetters) {
  var txt = document.getElementById('moduleSearchValueID');
  var errorMSG = document.getElementById('searchModulesError');
   var radio = document['forms']['myForm']['module_div_searchRadioBtn'];
    if(txt.value== null || txt.value =="" || txt.value == '' )
    {
    errorMSG.innerHTML = message ;
   return false
    }
  
   if(radio[0].checked && isNaN(txt.value))
   {
  
    errorMSG.innerHTML = messageForLetters ;
    return false ;
   }

     searchmodulesFunc();
    }
    
    function searchWithEnterKeyModules(e) {
     var btn = document.getElementById('module_div_search_btn');
    var event = e || window.event;
    if (event.keyCode == 13) {
    event.preventDefault();
     btn.click();
      return false ;
    }
    }
    
   
</script>