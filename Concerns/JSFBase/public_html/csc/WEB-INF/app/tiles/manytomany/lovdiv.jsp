<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<%--t:inputText forceId="true" id="lov_searchText"  value="#{detailBeanName.lovBaseBean.searchQuery}" styleClass="textboxmedium" /--%>
<t:panelGrid align="center">  <!-- updated by Ashraf Gaber 03/01/2011 HR-1172 point 21,22 -->
    <t:selectOneRadio forceId="true" id="lov_searchRadioBtn" value="#{detailBeanName.lovBaseBean.searchTyp}" styleClass="divtext">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
    </t:selectOneRadio>
   
  </t:panelGrid>

<t:panelGrid columns="1" width="100%" cellpadding="2"  id="lov_searchPanel" align="center" dir="#{shared_util.pageDirection}" forceId="true" style="text-align:center;">
     <t:panelGroup>
     <t:inputText forceId="true" id="lov_searchText"  value="#{detailBeanName.lovBaseBean.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:lov_search_btn_many');"/>
     <h:commandButton type="button" id="lov_search_btn_many" onclick="if(validateCodeNameSrchParamter('lov_searchRadioBtn','lov_searchText','','lov_errorConsole')){ lov_search_many();}" styleClass="ManyToManySearchButton" />
          <a4j:jsFunction name="lov_search_many" id="lov_search_many" action="#{detailBeanName.lovBaseBean.searchLovDiv}" reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lovDiv_btnsPnlGrd" oncomplete="setFocusOnlyOnElement('lov_searchText');" />
     <f:verbatim>&nbsp;&nbsp;</f:verbatim>
     <a4j:commandButton id="lov_cancelSearch" action="#{detailBeanName.lovBaseBean.cancelSearchLovDiv}" reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lov_searchRadioBtn"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.lovBaseBean.searchMode}" oncomplete="setFocusOnlyOnElement('lov_searchText');"/>
     
     </t:panelGroup>
      <t:outputText value="" forceId="true" id="lov_errorConsole" styleClass="errMsgNoHeight" />             
 </t:panelGrid>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="80%" align="center" >
    <t:panelGroup forceId="true" id="lov_dataT_data_panel" styleClass="lovDivScroll5" >
        <t:dataTable id="lov_dataT_data" var="list" value="#{detailBeanName.lovBaseBean.myTableData}" rowStyleClass="#{ detailBeanName.lovBaseBean.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{detailBeanName.lovBaseBean.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{detailBeanName.lovBaseBean.sortColumn}" sortAscending="#{detailBeanName.lovBaseBean.ascending}">
       <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
        <f:facet name="header">
        </f:facet>
        <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{detailBeanName.lovBaseBean.selectedRadio}" onmousedown="toggleRadio(this);">
         <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
         <a4j:support event="onclick" actionListener="#{detailBeanName.lovBaseBean.selectedRadioButton}" reRender="lovDiv_btnsPnlGrd" oncomplete="if(document.getElementById('myForm')!=null){document.getElementById('myForm:okLoveButton').disabled=false;document.getElementById('myForm:okLoveButton').focus();document.getElementById('myForm:okLoveButton').focus()}else{document.getElementById('treeForm:okLoveButton').disabled=false;document.getElementById('treeForm:okLoveButton').focus();document.getElementById('treeForm:okLoveButton').focus()};"/>
        </t:selectOneRadio>
       </t:column>
       
            <t:column sortable="false"  width="20%">
                <f:facet name="header">
                 <t:outputLabel id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" onclick="return false;" />                         
                        <%--t:graphicImage  value="#{(detailBeanName.lovBaseBean.sortAscending&&detailBeanName.lovBaseBean.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!detailBeanName.lovBaseBean.sortAscending&&detailBeanName.lovBaseBean.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink--%>

                </f:facet>
                <h:outputText id="content_code" value="#{detailBeanName.lovBaseBean.keyIndex==null? list.code.key:list.code.keys[detailBeanName.lovBaseBean.keyIndex]}"/>
            </t:column>
            
            <t:column  sortable="false" width="75%">
                <f:facet name="header">
                   <t:outputLabel id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" onclick="return false;"/>
                        <%--t:graphicImage  value="#{(detailBeanName.lovBaseBean.sortAscending&&detailBeanName.lovBaseBean.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!detailBeanName.lovBaseBean.sortAscending&&detailBeanName.lovBaseBean.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink--%>
                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ detailBeanName.lovBaseBean.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{detailBeanName.lovBaseBean.reloadList}" binding="#{detailBeanName.lovBaseBean.reloadField}"/>
    </t:panelGroup>    
</t:panelGrid>
<t:panelGroup forceId="true" id="lov_paging_panel">
        <h:panelGrid id="panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.lovBaseBean.listSize > shared_util.noOfTableRows}" >
            
          <t:dataScroller id="scroll_1" 
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
                    for="lov_dataT_data"
                    renderFacetsIfSinglePage="false"
                    actionListener="#{detailBeanName.lovBaseBean.openLovDiv}">
                <f:facet name="first" >                            
                    <h:panelGroup id="jobs_list_panelGrp_first">
                        <t:graphicImage id="jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="jobs_list_panelGrp_last">
                            <t:graphicImage id="jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="jobs_list_panelGrp_prv">
                            <t:graphicImage id="jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="jobs_list_panelGrp_nxt">
                            <t:graphicImage id="jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jobs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            <t:inputHidden forceId="true" id="pageIndex" value="#{pageIndex}" valueChangeListener="#{shared_util.changePageIndex}"/>
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        
</t:panelGroup>
<t:panelGrid columns="2" align="center" forceId="true" id="lovDiv_btnsPnlGrd">

  <t:commandButton id="okLoveButton" disabled="true" value="#{globalResources.ok}" type="button" styleClass="cssButtonSmall" onclick="clearOutputText('lov_errorConsole');return searchLovFunName();">
      <a4j:jsFunction name="searchLovFunName" action="#{detailBeanName.lovBaseBean.closeLovDiv}" oncomplete="hideLookUpDiv(window.blocker,window.divLov)" reRender="#{detailBeanName.lovBaseBean.renderedDropDown}"/>
    </t:commandButton>
 <%--a4j:commandButton id="okLoveButton" value="#{globalResources.ok}" action="#{detailBeanName.lovBaseBean.closeLovDiv}" binding="#{detailBeanName.lovBaseBean.okCommandButton}" styleClass="cssButtonSmall" oncomplete="hideLookUpDiv(window.blocker,window.divLov)" disabled="#{empty detailBeanName.lovBaseBean.selectedDTOS}"/--%>
 <t:commandButton forceId="true" id="lov_cancel" type="button" value="#{globalResources.back}" onclick="clearOutputText('lov_errorConsole');hideLookUpDiv(window.blocker,window.divLov);" styleClass="cssButtonSmall" onblur="setFocusOnlyOnElement('lov_searchText');"/>
</t:panelGrid>


<%--t:saveState value="#{detailBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.fullColumnName}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.sortAscending}"/>

<t:saveState value="#{detailBeanName.lovBaseBean.myTableData}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.returnMethodName}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.searchMode}" /> 
<t:saveState value="#{detailBeanName.lovBaseBean.searchQuery}" /> 
<t:saveState value="#{detailBeanName.lovBaseBean.searchMethod}" />
<t:saveState value="#{detailBeanName.lovBaseBean.cancelSearchMethod}" /--%>


<t:saveState value="#{detailBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.fullColumnName}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.sortAscending}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.keyIndex}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.myTableData}" />
<t:saveState value="#{detailBeanName.lovBaseBean.searchTyp}" />
<t:saveState value="#{detailBeanName.lovBaseBean.searchQuery}" />
<t:saveState value="#{detailBeanName.lovBaseBean.renderedDropDown}" />
<t:saveState value="#{detailBeanName.lovBaseBean.searchMode}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.returnMethodName}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.searchMethod}" />
<t:saveState value="#{detailBeanName.lovBaseBean.cancelSearchMethod}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.selectedRadio}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.returnMethodName}"/>
<t:saveState value="#{detailBeanName.lovBaseBean.resetDivAfterClose}"/>
