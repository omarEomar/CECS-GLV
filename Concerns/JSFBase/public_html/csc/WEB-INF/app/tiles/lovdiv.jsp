<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:saveState value="#{pageBeanName.lovBaseBean.codeTypeString}"/>

    <htm:table border="0" cellpadding="0" cellspacing="0" width="100%">
        <htm:tr>
            <htm:td align="center" valign="top">
                <htm:table border="0" width="99%" id="lovtable5" cellspacing="0" cellpadding="0">
                    
                    <htm:tr>
                        <htm:td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" styleClass="grightbox"></htm:td>
                        <htm:td valign="top" bgcolor="#FFFFFF" styleClass="paddingbox">
                           <t:panelGrid align="center">
                           <t:panelGroup>
                            <%-- t:outputText value="#{pageBeanName.errorMsgValue}" forceId="true" id="lov_errorConsole" styleClass="errMsgNoHeight" /--%>
                            <t:outputText value="#{pageBeanName.lovBaseBean.errorMsgValue}" forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight" />
                            </t:panelGroup>
                            
                            <t:selectOneRadio forceId="true" id="lov_searchRadioBtn" value="#{pageBeanName.lovBaseBean.searchTyp}" styleClass="divtext" onblur="settingFoucsAtLovDiv();">
                             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                             <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
                            </t:selectOneRadio>
                           </t:panelGrid> 
                            
                            <t:panelGrid align="center" style="text-align:center;" columns="2" width="100%" forceId="true" id="lov_searchPanel">
                            <t:outputText styleClass="lable01" value="#{pageBeanName.lovBaseBean.lovDivLabelSearch}" rendered="#{!empty pageBeanName.lovBaseBean.lovDivLabelSearch}"/>
                             <t:panelGroup >
                                    <t:inputText maxlength="#{pageBeanName.lovBaseBean.nameMaxLength}" forceId="true" id="lov_searchText"  value="#{pageBeanName.lovBaseBean.searchQuery}" styleClass="textboxmedium"  onkeypress="if(document.getElementById('lov_search_btn') != null){ FireButtonClickOldStyle(event,'lov_search_btn');}else{FireButtonClickOldStyle(event,'lov_search_btn_code_string');}"/>
                                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                    <t:commandButton rendered="#{!pageBeanName.lovBaseBean.codeTypeString}" type="button" id="lov_search_btn" forceId="true" onclick="if(validateCodeNameSrchParamter('lov_searchRadioBtn','lov_searchText','','errorMessag_lov')){ lov_search();}" styleClass="ManyToManySearchButton" />
                                    <t:commandButton rendered="#{pageBeanName.lovBaseBean.codeTypeString}" type="button" id="lov_search_btn_code_string" forceId="true" onclick="if(validateCodeNameSrchParamter('lov_searchRadioBtn','lov_searchText','true','errorMessag_lov')){ lov_search();}" styleClass="ManyToManySearchButton" />
                                    <a4j:jsFunction name="lov_search" id="lov_search" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.lovBaseBean.searchLovDiv}" reRender="lov_dataT_data_panel,lov_searchPanel,errorMessag_lov,lov_paging_panel,lovDiv_btnsPnlGrd"  />
                                    <f:verbatim>&nbsp;</f:verbatim>
                                    <a4j:commandButton id="lov_cancelSearch" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.lovBaseBean.cancelSearchLovDiv}" reRender="lov_searchText,lov_dataT_data_panel,lov_searchPanel,errorMessag_lov,lov_paging_panel,lov_searchRadioBtn,lovDiv_btnsPnlGrd"  styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.lovBaseBean.searchMode}" />
                             </t:panelGroup>
                            </t:panelGrid>
                           
                           
                        </htm:td>
                        <htm:td valign="top" style="background-repeat: repeat-y" styleClass="gleftbox"/>
                    </htm:tr>
                    
                </htm:table>
            </htm:td>
        </htm:tr>
        <htm:tr>
            <htm:td valign="top">
        
            
            <t:saveState value="#{pageBeanName.lovBaseBean.searchMode}" />           
            
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="80%" align="center" >
    <t:panelGroup forceId="true" id="lov_dataT_data_panel" styleClass="lovDivScroll5">
        <t:dataTable id="lov_dataT_data" var="list" value="#{pageBeanName.lovBaseBean.myTableData}" rowStyleClass="#{ pageBeanName.lovBaseBean.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.lovBaseBean.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.lovBaseBean.sortColumn}" sortAscending="#{pageBeanName.lovBaseBean.ascending}">
     
       <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{!pageBeanName.lovBaseBean.multiSelect}">
        <f:facet name="header">
        </f:facet>
        <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.lovBaseBean.selectedRadio}" onkeyup="toggleRadioKeyUp(this);" onmousedown="toggleRadio(this);">
         <f:selectItem itemLabel="" itemValue="#{list.code.key}" />
         <a4j:support event="onclick"  actionListener="#{pageBeanName.lovBaseBean.selectedRadioButton}" oncomplete="document.getElementById('lovDivOkTBtn').disabled=false;document.getElementById('lovDivOkTBtn').focus();document.getElementById('lovDivOkTBtn').focus();" reRender="lovDiv_btnsPnlGrd"/>
        </t:selectOneRadio>
       </t:column> 
       
       
       
       <t:column id="check_Second_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.lovBaseBean.multiSelect}">
        <f:facet name="header">
        </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                  <a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedCheckboxs}"
                               oncomplete="checkCheckAll('chk');document.getElementById('lovDivOkTBtn').disabled=false;"
                               reRender="lovDiv_btnsPnlGrd"/>
            </t:selectBooleanCheckbox>
        </t:column>
        
        
            <t:column  sortable="false"  width="20%">
                <f:facet name="header">
                 <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" onclick="return false;">                         
                 </t:commandLink>
                </f:facet>
                <t:outputText id="content_code" forceId="true" value="#{pageBeanName.lovBaseBean.keyIndex==null? list.code.key:list.code.keys[pageBeanName.lovBaseBean.keyIndex]}"/>
           </t:column>  
        
        
            <t:column  sortable="false" width="75%">
                <f:facet name="header">
                 <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" onclick="return false;">
                 </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}">

                </h:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.lovBaseBean.searchMode && pageBeanName.lovBaseBean.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
      
    </t:panelGroup>    
</t:panelGrid>

                </htm:td></htm:tr></htm:table>
                
                
<t:panelGroup forceId="true" id="lov_paging_panel" style="height: 31px;">
        <h:panelGrid id="lov_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.lovBaseBean.listSize > shared_util.noOfTableRows}" >
            
          <t:dataScroller id="lov_scroll_1" 
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
                    actionListener="#{pageBeanName.lovBaseBean.openLovDiv}">
                <f:facet name="first" >                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_first">
                        <t:graphicImage id="lov_jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_last">
                            <t:graphicImage id="lov_jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_prv">
                            <t:graphicImage id="lov_jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_nxt">
                            <t:graphicImage id="lov_jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="lov_jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jlov_obs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="lov_jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="lov_jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_lov_jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                          
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        
</t:panelGroup>
<t:panelGrid columns="3" align="center" forceId="true" id="lovDiv_btnsPnlGrd">
<%-- edit by i.elnahrawy -   issue no   HR-701  10. 010.gif add disable  --%>
<t:panelGroup>
    <t:commandButton forceId="true" id="lovDivOkTBtn" binding="#{pageBeanName.lovBaseBean.okBtnLovDiv}" disabled="true" value="#{globalResources.ok}" type="button" styleClass="cssButtonSmall" onclick="clearOutputText('errorMessag_lov'); return searchLovFunName();">
      <a4j:jsFunction id="lovDivOkTBtnJSFFID" name="searchLovFunName" action="#{pageBeanName.lovBaseBean.closeLovDiv}" oncomplete="#{pageBeanName.lovBaseBean.onCompleteList}" reRender="#{pageBeanName.lovBaseBean.renderedDropDown}"/>
    </t:commandButton>
</t:panelGroup>

<t:panelGroup>
    <t:commandButton onclick="clearOutputText('errorMessag_lov');" forceId="true" id="lov_cancel" onblur="if(isVisibleComponent('divLov')){document.getElementById('lov_searchRadioBtn').focus();}" type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:support action="#{pageBeanName.lovBaseBean.hideLovDiv}" event="onclick"  oncomplete="hideLookUpDiv(window.blocker,window.divLov)" reRender="divSearch"/>
</t:panelGroup>

<%--t:commandButton forceId="true" id="lov_cancel" onblur="settingFoucsAtLovDiv();" onclick="backLovJsFunction();  return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
<a4j:jsFunction name="backLovJsFunction" action="#{pageBeanName.lovBaseBean.hideLovDiv}"  oncomplete="hideLookUpDiv(window.blocker,window.divLov,null,null,null)" reRender="divSearch"/--%>


<t:inputHidden id="indexOfSelectedDataInDataTableId" forceId="true" value="#{pageBeanName.lovBaseBean.indexOfSelectedDataInDataTable}"/>   

</t:panelGrid>

<t:saveState value="#{pageBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.fullColumnName}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.sortAscending}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.keyIndex}"/>
<t:saveState id="lovBaseBean" value="#{pageBeanName.lovBaseBean.myTableData}" />
<t:saveState id="lovSearchType" value="#{pageBeanName.lovBaseBean.searchTyp}" />
<t:saveState id="lovSearchQuery" value="#{pageBeanName.lovBaseBean.searchQuery}" />
<t:saveState id="lovRenderedDropDown" value="#{pageBeanName.lovBaseBean.renderedDropDown}" />
<t:saveState id="lovsearchMode"  value="#{pageBeanName.lovBaseBean.searchMode}"/>
<t:saveState id="lovreturnMethodName" value="#{pageBeanName.lovBaseBean.returnMethodName}"/>
<t:saveState id="lovsearchMethod" value="#{pageBeanName.lovBaseBean.searchMethod}" />
<t:saveState id="lovcancelSearchMethod" value="#{pageBeanName.lovBaseBean.cancelSearchMethod}"/>
<t:saveState id="lovcselectedRadio" value="#{pageBeanName.lovBaseBean.selectedRadio}"/>
<t:saveState id="lovMultiselectedRadio" value="#{pageBeanName.lovBaseBean.multiSelect}"/>
<t:saveState id="onCompleteListId" value="#{pageBeanName.lovBaseBean.onCompleteList}"/>
<t:saveState id="cleanDataTableFlageId" value="#{pageBeanName.lovBaseBean.cleanDataTableFlage}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.resetDivAfterClose}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.resetDivAfterHide}"/>

<t:saveState value="#{pageBeanName.dataTableSearchResult}"/>

<t:saveState value="#{pageBeanName.currentSelectedSearchIndex}"/>

<t:saveState value="#{pageBeanName.lovBaseBean.indexOfSelectedDataInDataTable}"/>
