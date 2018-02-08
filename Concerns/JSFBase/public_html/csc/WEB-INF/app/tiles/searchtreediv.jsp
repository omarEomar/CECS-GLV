<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:saveState value="#{pageBeanName.searchTreeDivBean.codeTypeString}"/>
<t:panelGroup forceId="true" id="conterid">
    <htm:table border="0" cellpadding="0" cellspacing="0" width="100%">
        <htm:tr>
            <htm:td align="center" valign="top">
                <htm:table border="0" width="99%" id="lov2table5" cellspacing="0" cellpadding="0">
                    
                    <htm:tr>
                        <htm:td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" styleClass="grightbox"></htm:td>
                        <htm:td valign="top" bgcolor="#FFFFFF" styleClass="paddingbox">
                           <t:panelGrid align="center">
                           <t:panelGroup>
                            <t:outputText value="#{pageBeanName.searchTreeDivBean.errorMsgValue}" forceId="true" id="errorMessag_lov2" styleClass="errMsgNoHeight" />
                            </t:panelGroup>
                            
                            <t:selectOneRadio forceId="true" id="lov2_searchRadioBtn" value="#{pageBeanName.searchTreeDivBean.searchTyp}" styleClass="divtext" onblur="settingFoucsAtLovDiv();">
                             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                             <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
                            </t:selectOneRadio>
                           </t:panelGrid> 
                            
                            <t:panelGrid align="center" style="text-align:center;" columns="2" width="100%" forceId="true" id="lov2_searchPanel">
                            <t:outputText styleClass="lable01" value="#{pageBeanName.searchTreeDivBean.lovDivLabelSearch}" rendered="#{!empty pageBeanName.searchTreeDivBean.lovDivLabelSearch}"/>
                             <t:panelGroup >
                                    <t:inputText forceId="true" id="lov2_searchText"  value="#{pageBeanName.searchTreeDivBean.searchQuery}" styleClass="textboxmedium"  onkeypress="if(document.getElementById('lov2_search_btn') != null){ FireButtonClick('lov2_search_btn');}else{FireButtonClick('lov2_search_btn_code_string');}"/>
                                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                    <t:commandButton id="lov2_search_btn" forceId="true" 
                                            rendered="#{!pageBeanName.searchTreeDivBean.codeTypeString}" 
                                            type="button" 
                                            onclick="if(validateCodeNameSrchParamter('lov2_searchRadioBtn','lov2_searchText','','errorMessag_lov2')){ lov2_search();}" 
                                            styleClass="ManyToManySearchButton" />
                                            
                                    <t:commandButton id="lov2_search_btn_code_string" forceId="true" 
                                            rendered="#{pageBeanName.searchTreeDivBean.codeTypeString}" 
                                            type="button" 
                                            onclick="if(validateCodeNameSrchParamter('lov2_searchRadioBtn','lov2_searchText','','errorMessag_lov2')){ lov2_search();}" 
                                            styleClass="ManyToManySearchButton" />
                                            
                                    <a4j:jsFunction name="lov2_search" id="lov2_search" 
                                            oncomplete="settingFoucsAtLovDiv();" 
                                            
                                            action="#{pageBeanName.searchTreeDivBean.searchLovDiv}" 
                                            reRender="lov2_dataT_data_panel,lov2_searchPanel,errorMessag_lov2,lov2_paging_panel,lovDiv_btnsPnlGrd"/>
                                    <f:verbatim>&nbsp;</f:verbatim>
                                    <a4j:commandButton id="lov2_cancelSearch" 
                                            oncomplete="settingFoucsAtLovDiv();" 
                                            action="#{pageBeanName.searchTreeDivBean.cancelSearchLovDiv}" 
                                            reRender="lov2_searchText,lov2_dataT_data_panel,lov2_searchPanel,errorMessag_lov2,lov2_paging_panel,lov2_searchRadioBtn,lovDiv_btnsPnlGrd"  
                                            styleClass="ManyToManyCancelSearchButton" 
                                            rendered="#{pageBeanName.searchTreeDivBean.searchMode}"/>
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
        
            
                       
            
                <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="80%" align="center" >
                    <t:panelGroup forceId="true" id="lov2_dataT_data_panel" styleClass="lovDivScroll5">
                        <t:dataTable id="lov2_dataT_data" var="list" value="#{pageBeanName.searchTreeDivBean.myTableData}" rowStyleClass="#{ pageBeanName.searchTreeDivBean.selected ? 'standardTable_RowHighlighted' : null}"
                                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.searchTreeDivBean.myDataTable}"
                                     rowOnMouseOver="javascript:addRowHighlight('treeform:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.searchTreeDivBean.sortColumn}" sortAscending="#{pageBeanName.searchTreeDivBean.ascending}">
                     
                       <t:column id="check_column" 
                            styleClass="standardTable_Header3" 
                            width="5%" 
                            rendered="#{!pageBeanName.searchTreeDivBean.multiSelect}">
                        <f:facet name="header">
                        </f:facet>
                        <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.searchTreeDivBean.selectedRadio}" onkeyup="toggleRadioKeyUpVersionTwo(this,event);" onmousedown="toggleRadio(this);">
                         <f:selectItem itemLabel="" itemValue="#{list.code.key}" />
                         <a4j:support event="onclick"  actionListener="#{pageBeanName.searchTreeDivBean.selectedRadioButton}"
                         oncomplete="if(document.getElementById('treeform:lov2_ok')!=null){document.getElementById('treeform:lov2_ok').disabled=false;
                         document.getElementById('treeform:lov2_ok').focus();
                         document.getElementById('treeform:lov2_ok').focus()}
                         else{document.getElementById('treeform:lov2_ok').disabled=false;document.getElementById('treeform:lov2_ok').focus();document.getElementById('treeform:lov2_ok').focus()};" reRender="lovDiv_btnsPnlGrd"/>
                        </t:selectOneRadio>
                       </t:column> 
                       
                       
                       
                       <%--<t:column id="check_Second_column" 
                            styleClass="standardTable_Header3" 
                            width="5%" rendered="#{pageBeanName.searchTreeDivBean.multiSelect}">
                        <f:facet name="header">
                        </f:facet>
                            <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                                  <a4j:support event="onclick" actionListener="#{pageBeanName.searchTreeDivBean.selectedCheckboxs}"
                                               oncomplete="checkCheckAll('chk');
                                               document.getElementById('treeform:lov2_ok').disabled=false;"
                                               reRender="lovDiv_btnsPnlGrd"/>
                            </t:selectBooleanCheckbox>
                        </t:column>--%>
                        
                        
                            <t:column  sortable="false"  width="20%">
                                <f:facet name="header">
                                 <t:commandLink id="sort_code" forceId="true" 
                                        styleClass="headerLink" 
                                        value="#{globalResources.Code}" 
                                        onclick="return false;">                         
                                 </t:commandLink>
                                </f:facet>
                                <t:outputText id="content_code" forceId="true" value="#{list.code.key}"/>
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
                        <t:panelGrid columns="1" rendered="#{ pageBeanName.searchTreeDivBean.listSize == 0 }" align="center">
                            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
                        </t:panelGrid>
                      
                    </t:panelGroup>    
                </t:panelGrid>

            </htm:td></htm:tr></htm:table>
                
                
<t:panelGroup forceId="true" id="lov2_paging_panel">
        <h:panelGrid id="lov2_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.searchTreeDivBean.listSize > shared_util.noOfTableRows}" >
            
          <t:dataScroller id="lov2_scroll_1" 
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
                    paginatorActiveColumnStyle="font-weight:bold"
                    styleClass="textpage"
                    immediate="false"
                    for="lov2_dataT_data"
                    renderFacetsIfSinglePage="false"
                    actionListener="#{pageBeanName.searchTreeDivBean.openSearchTreeDiv}">
                <f:facet name="first" >                            
                    <h:panelGroup id="lov2_jobs_list_panelGrp_first">
                        <t:graphicImage id="lov2_jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="lov2_jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="lov2_jobs_list_panelGrp_last">
                            <t:graphicImage id="lov2_jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov2_jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="lov2_jobs_list_panelGrp_prv">
                            <t:graphicImage id="lov2_jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov2_jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="lov2_jobs_list_panelGrp_nxt">
                            <t:graphicImage id="lov2_jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov2_jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="lov2_jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jlov2_obs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov2_jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="lov2_jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="lov2_jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov2_lov2_jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                          
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        
</t:panelGroup>
<t:panelGrid columns="3" align="center" forceId="true" id="lov2Div_btnsPnlGrd">
<%-- edit by i.elnahrawy -   issue no   HR-701  10. 010.gif add disable  --%>
<t:panelGroup>
    <t:commandButton id="lov2_ok" disabled="#{pageBeanName.searchTreeDivBean.selectedRadio == null || pageBeanName.searchTreeDivBean.selectedRadio == ''}" value="#{globalResources.ok}" action="#{pageBeanName.searchTreeDivBean.closeLovDiv}" styleClass="cssButtonSmall" onclick="clearOutputText('errorMessag_lov2');">
     <%--<a4j:jsFunction id="lovDivOkTBtnJS" name="searchLovFunName2" action="#{pageBeanName.searchTreeDivBean.closeLovDiv}" oncomplete="hideLookUpDiv(window.blocker,window.divLov2);focusHighlightedNode();" reRender="#{centerListBean.searchTreeDivBean.renderedDropDown}"/>--%>
    </t:commandButton>
</t:panelGroup>

<t:panelGroup>
    <t:commandButton onclick="clearOutputText('errorMessag_lov2');" forceId="true" id="lov2_cancel" onblur="settingFoucsAtLovDiv();" type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:support event="onclick"  oncomplete="hideLookUpDiv(window.blocker,window.divLov2)" reRender="lovDiv_btnsPnlGrd,divSearch"/>
</t:panelGroup>
 
<t:inputHidden id="indexOfSelectedDataInDataTableId" forceId="true" value="#{pageBeanName.searchTreeDivBean.indexOfSelectedDataInDataTable}"/>   

</t:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.searchTreeDivBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.searchTreeDivBean.fullColumnName}"/>
<t:saveState value="#{pageBeanName.searchTreeDivBean.sortAscending}"/>
<t:saveState value="#{pageBeanName.searchTreeDivBean.keyIndex}"/>
<t:saveState id="lov2BaseBean2" value="#{pageBeanName.searchTreeDivBean.myTableData}" />
<t:saveState id="lov2SearchType" value="#{pageBeanName.searchTreeDivBean.searchType}" />
<t:saveState id="lov2SearchQuery" value="#{pageBeanName.searchTreeDivBean.searchQuery}" />
<t:saveState id="lov2RenderedDropDown" value="#{pageBeanName.searchTreeDivBean.renderedDropDown}" />
<t:saveState id="lov2searchMode"  value="#{pageBeanName.searchTreeDivBean.searchMode}"/>
<t:saveState id="lov2returnMethodName" value="#{pageBeanName.searchTreeDivBean.returnMethodName}"/>
<t:saveState id="lov2searchMethod" value="#{pageBeanName.searchTreeDivBean.searchMethod}" />
<t:saveState id="lov2cancelSearchMethod" value="#{pageBeanName.searchTreeDivBean.cancelSearchMethod}"/>
<t:saveState id="lov2cselectedRadio" value="#{pageBeanName.searchTreeDivBean.selectedRadio}"/>
<%--<t:saveState id="lov2MultiselectedRadio" value="#{pageBeanName.searchTreeDivBean.multiSelect}"/>--%>
<t:saveState id="onCompleteListId2" value="#{pageBeanName.searchTreeDivBean.onCompleteList}"/>
<t:saveState id="cleanDataTableFlageId2" value="#{pageBeanName.searchTreeDivBean.cleanDataTableFlage}"/>
<t:saveState value="#{pageBeanName.searchTreeDivBean.indexOfSelectedDataInDataTable}"/>
<t:saveState value="#{pageBeanName.searchTreeDivBean.beanName}"/>
