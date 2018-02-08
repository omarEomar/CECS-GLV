<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

  <t:panelGrid align="center">  
                            <t:selectOneRadio forceId="true" id="machine_div_searchRadioBtn" value="#{pageBeanName.machineSearchType}" 
                                    styleClass="divtext" onblur="settingFoucsAtEmpLovDiv();"> 
                             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"  />
                             <f:selectItem itemLabel="#{resourcesBundle.supmachine_name}" itemValue="1" />
                            </t:selectOneRadio>
                           </t:panelGrid> 
                           
<t:panelGrid id="search_machine_container" columns="1" border="0" cellspacing="0"  cellpadding="3" align="center">
   
  <%--<h:outputText value="#{resourcesBundle.system_lbl}" styleClass="lable01"/>--%>
   
  <t:inputText forceId="true" onkeypress="searchWithEnterKey(event)" id="machineSearchValueID"  styleClass="textboxlarge" align="center" value="#{pageBeanName.machineSearchValue}" />
        
          
       <htm:center>
        <t:outputLabel id="searchError"  forceId="true" styleClass="error" />
        </htm:center>
      
</t:panelGrid>
<t:panelGrid columns="2" align="center">

<t:panelGroup id="search_machine_container_btns">
  <t:commandButton forceId="true" type="button" id="machine_div_search_btn"  onclick="goSearch('#{globalResources.missingField}' , '#{resourcesBundle.not_anumber_msg}' );" styleClass="ManyToManySearchButton"/>
 <a4j:jsFunction name="searchMachinesFunc"   action="#{pageBeanName.searchMachines}" reRender="cancel_search_machine_pnl,machines_div_cancelSearch,search_machine_container,search_machine_container_btns,machines_dataT_data_panel,_machines_dataT_data,machine_div_paging_panel"/>
</t:panelGroup>

<t:panelGroup id="cancel_search_machine_pnl">
<a4j:commandButton id="machines_div_cancelSearch" action="#{pageBeanName.cancelSearchMachines}"   styleClass="ManyToManyCancelSearchButton"
rendered="#{pageBeanName.machineSearchMode}" 
reRender="search_machine_container,search_machine_container_btns,machines_dataT_data_panel,_machines_dataT_data,cancel_search_machine_pnl,machine_div_paging_panel" />
   </t:panelGroup>                    
</t:panelGrid>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="machines_dataT_data_panel" styleClass="lovDivScroll5" style="width:100% !important;max-height:150px!important;">
        
        <t:dataTable id="_machines_dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.machinesList}"
                     
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myMachinesDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
         
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedMachineRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedMachinesRadioButton}" rerender="machine_div_btns_pnl"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="32%">
                <f:facet name="header">
                   <t:outputText id="sort_name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.supmachine_name}"
                         />
                </f:facet>
                <h:inputText id="content_name" value="#{list.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
            
            
             <t:column id="type_column" sortable="false" width="25%">
                <f:facet name="header">
                    <t:outputText id="supMachineTypesDTo-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.machine_type}"/>
                </f:facet>
                <h:inputText id="content_supMachineTypesDTo-name" value="#{list.supMachineTypesDTo.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
       
            <t:column id="model_column" sortable="false" width="25%">
                <f:facet name="header">
                   <t:outputText id="sort_supMachineModelsDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.machine_model}"/>
                </f:facet>
                <h:inputText id="content_supMachineModelsDTO-name" value="#{list.supMachineModelsDTO.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.machinesList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    </t:panelGroup>
</t:panelGrid>



<!-- paging ------->
<t:panelGrid>

                       
                
<t:panelGroup forceId="true" id="machine_div_paging_panel" >
        <h:panelGrid id="machine_div_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="450px" rendered="true" >
           <t:dataScroller id="machine_div_scroll_1" 
                    binding="#{pageBeanName.machinesScroller}"
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
                    for="_machines_dataT_data"
                    renderFacetsIfSinglePage="false"
                    actionListener="#{pageBeanName.loadPagingData}">
                <f:facet name="first" >                            
                    <h:panelGroup id="machine_div_list_panelGrp_first">
                        <t:graphicImage id="machine_div_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="machine_div_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="machine_div_list_panelGrp_last">
                            <t:graphicImage id="machine_div_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="machine_div_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="machine_div_list_panelGrp_prv">
                            <t:graphicImage id="machine_div_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="machine_div_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="machine_div_list_panelGrp_nxt">
                            <t:graphicImage id="machine_div_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="machine_div_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="machine_div_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jmachine_div_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="machine_div_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="machine_div_list_panelGrp_frwnd">
                            <t:graphicImage id="machine_div_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="machine_div_jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                          
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        
</t:panelGroup>


</t:panelGrid>

            
 
<!-- end paging ------>

<t:panelGrid columns="2" align="center">
 <a4j:commandButton id="machine_div_btns_pnl" value="#{globalResources.ok}" action="#{pageBeanName.onCloseMachinesDiv}" styleClass="cssButtonSmall" 
 oncomplete="hideLookUpDiv(window.blocker,window.customDiv2);" rerender="systempOrmachine_pnld,coord_pnl"
  disabled="#{empty pageBeanName.selectedMachiesDTOS}"/>

 <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonCustomDiv2"  onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction"  oncomplete="hideLookUpDiv(window.blocker,window.customDiv2, null ,'myForm:error',null); " reRender="OperationBar"/>
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
  
  
  function goSearch(message , messageForLetters) {
  var txt = document.getElementById('machineSearchValueID');
  var errorMSG = document.getElementById('searchError');
  var radio = document['forms']['myForm']['machine_div_searchRadioBtn'];
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
     searchMachinesFunc();
    }
  
      function searchWithEnterKey(e) {
     var btn = document.getElementById('machine_div_search_btn');
   
    var event = e || window.event;
    if (event.keyCode == 13) {
    event.preventDefault();
     btn.click();
      return false ;
    }
    }
</script>