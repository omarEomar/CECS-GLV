<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
    <htm:table border="0"   cellpadding="0" cellspacing="0" width="100%">
        <htm:tr>
            <htm:td align="center">
                <htm:table border="0" width="99%" id="emplovtable5" cellspacing="0" cellpadding="0">
                    <htm:tr>
                        <htm:td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" styleClass="grightbox"></htm:td>
                        <htm:td valign="top" bgcolor="#FFFFFF" styleClass="paddingbox">
                           <t:panelGrid align="center">  
                            <t:selectOneRadio forceId="true" id="civil_div_searchRadioBtn" value="#{detailBeanName.empListOfValues.searchTyp}" 
                                    styleClass="divtext" onblur="settingFoucsAtEmpLovDiv();"> 
                             <f:selectItem itemLabel="#{globalResources.globalCivilId}" itemValue="0"/>
                             <f:selectItem itemLabel="#{globalResources.globalName}" itemValue="1"/>
                            </t:selectOneRadio>
                           </t:panelGrid> 
                            
                            <t:panelGrid align="center" columns="4" border="0" width="50%" forceId="true" id="civil_div_searchPanel">
                             <t:inputText onkeypress="FireButtonClick('myForm:civil_div_search');" forceId="true" id="civil_div_searchText"  value="#{detailBeanName.empListOfValues.searchQuery}" styleClass="textboxmedium" />
                             <t:panelGroup>
                             <h:commandButton  type="button" id="civil_div_search"  onclick="if(checkRadio_CivilId('civil_div_searchRadioBtn','civil_div_searchText','#{globalResources.civil_no_not_less_than_12}')){ searchEmpListOfVal();}" styleClass="ManyToManySearchButton"/>
                             <a4j:jsFunction name="searchEmpListOfVal" oncomplete="document.getElementById('myForm:civil_div_cancelSearch').focus();"  action="#{detailBeanName.empListOfValues.searchLovDiv}" reRender="civil_div_dataT_data_panel,civil_div_searchPanel,errorMessage,civil_div_paging_panel,civil_div_paging_panel,civil_div_ok"/>
                             
                             </t:panelGroup>
                             <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                             <a4j:commandButton id="civil_div_cancelSearch" action="#{detailBeanName.empListOfValues.cancelSearchLovDiv}" reRender="civil_div_ok,civil_div_searchText,civil_div_dataT_data_panel,civil_div_searchPanel,errorMessage,civil_div_paging_panel,civil_div_searchRadioBtn,civil_div_ok"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.empListOfValues.searchMode}" />
                             <t:outputText value="&nbsp;" escape="false" rendered="#{!detailBeanName.empListOfValues.searchMode}" />
                             <t:panelGroup  colspan="4">
                             <htm:center>
                             <t:outputText value="#{detailBeanName.empListOfValues.errorMsgValue}" forceId="true" id="errorMessage" styleClass="errMsgNoHeight" />
                             </htm:center>
                             </t:panelGroup>
                            </t:panelGrid>
                           
                        </htm:td>
                        <htm:td valign="top" style="background-repeat: repeat-y" styleClass="gleftbox"/>
                    </htm:tr>
                </htm:table>
            </htm:td>
        </htm:tr>
        <htm:tr>
            <htm:td>
        
            
            <t:saveState value="#{detailBeanName.empListOfValues.searchMode}" />           
            
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">

    <t:panelGroup forceId="true" id="civil_div_dataT_data_panel"  styleClass="lovDivScroll5" style="width:100% !important;max-height:150px!important;">
    
        <t:dataTable id="civil_div_dataT_data" var="list" value="#{detailBeanName.empListOfValues.usingPaging ? detailBeanName.empListOfValues.pagingBean.myPagedDataModel : detailBeanName.empListOfValues.myTableData}" rowStyleClass="#{ detailBeanName.empListOfValues.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{detailBeanName.empListOfValues.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{detailBeanName.empListOfValues.sortColumn}" sortAscending="#{detailBeanName.empListOfValues.ascending}">
       <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
        <f:facet name="header">
        </f:facet>
        <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" 
            value="#{detailBeanName.empListOfValues.selectedRadio}" 
            onmousedown="toggleRadio(this);" onkeyup="if(window.event && window.event.keyCode != 13){toggleRadioKeyUp(this);}"
            onkeypress="FireButtonClick('myForm:civil_div_ok');">
         <f:selectItem    itemLabel="" itemValue="#{list.citizensResidentsDTO.code.key}" />
         <a4j:support event="onclick" actionListener="#{detailBeanName.empListOfValues.selectedRadioButton}" reRender="civil_div_ok"/>
        </t:selectOneRadio>
       </t:column> 
       
            <t:column  sortable="false"  width="20%">
                <f:facet name="header">
                 <h:outputText id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.citizensResidentsDTO.code.key}"/>
            </t:column>
            
            <t:column  sortable="false" width="75%">
                <f:facet name="header">
                 <h:outputText id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.globalName}"/>
                 
                </f:facet>
                 
                <h:outputText id="emp_name" value="#{list.citizensResidentsDTO.fullName}" />
            </t:column>
        </t:dataTable>
        
         <t:panelGrid columns="1" rendered="#{ detailBeanName.empListOfValues.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg msg_no_icon" rendered="#{!detailBeanName.empListOfValues.cantLocateSession}"/>
            <t:outputText value="#{globalResources.global_cantLocateSession}" styleClass="errmsg" rendered="#{detailBeanName.empListOfValues.cantLocateSession}"/>
        </t:panelGrid>
      
    </t:panelGroup>
     
      
</t:panelGrid>

                </htm:td></htm:tr></htm:table>
                
                
<t:panelGroup forceId="true" id="civil_div_paging_panel">
        <h:panelGrid id="civil_div_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.empListOfValues.listSize > shared_util.noOfTableRows}" >
            
          <t:dataScroller id="civil_div_scroll_1" 
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
                    for="civil_div_dataT_data"
                    renderFacetsIfSinglePage="false"
                    actionListener="#{detailBeanName.empListOfValues.openLovDiv}">
                <f:facet name="first" >                            
                    <h:panelGroup id="civil_div_jobs_list_panelGrp_first">
                        <t:graphicImage id="civil_div_jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="civil_div_jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="civil_div_jobs_list_panelGrp_last">
                            <t:graphicImage id="civil_div_jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="civil_div_jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="civil_div_jobs_list_panelGrp_prv">
                            <t:graphicImage id="civil_div_jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="civil_div_jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="civil_div_jobs_list_panelGrp_nxt">
                            <t:graphicImage id="civil_div_jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="civil_div_jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="civil_div_jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jcivil_div_obs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="civil_div_jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="civil_div_jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="civil_div_jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="civil_div_lov_jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                          
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        
</t:panelGroup>
<t:panelGrid columns="2" align="center">
    <t:commandButton forceId="true" id="civil_div_ok" styleClass="cssButtonSmall" value="#{globalResources.ok}"
    action="#{detailBeanName.empListOfValues.closeLovDiv}" binding="#{detailBeanName.empListOfValues.okCommandButton}" 
     disabled="#{empty detailBeanName.empListOfValues.selectedDTOS}" onclick="hideLookUpDiv(window.blocker,window.lovEmp);changeVisibilityMsg();" />
 <%--<a4j:commandButton id="civil_div_ok" value="#{globalResources.ok}" 
     action="#{detailBeanName.empListOfValues.closeLovDiv}" 
     styleClass="cssButtonSmall" oncomplete="hideLookUpDiv(window.blocker,window.lovEmp);changeVisibilityMsg();" 
     reRender="#{detailBeanName.empListOfValues.renderedDropDown},divMsg" 
     binding="#{detailBeanName.empListOfValues.okCommandButton}" 
     disabled="#{empty detailBeanName.empListOfValues.selectedDTOS}" />--%>
 
<t:panelGroup>
    <t:commandButton forceId="true" id="backButtonEmpLovDiv" onblur="settingFoucsAtEmpLovDiv();" type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:support action="#{detailBeanName.empListOfValues.resetData}" event="onclick"  oncomplete="hideLookUpDiv(window.blocker,window.lovEmp);if(typeof(setFocusAfterCloseEmpLovDiv)!='undefined'){setFocusAfterCloseEmpLovDiv()};" reRender="divSearch"/>
</t:panelGroup>
 <%-- by Ashraf Gaber to reset date --%>
 <%--t:commandButton onblur="settingFoucsAtEmpLovDiv();" forceId="true" id="backButtonEmpLovDiv" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lovEmp);if(typeof(setFocusAfterCloseEmpLovDiv)!='undefined'){setFocusAfterCloseEmpLovDiv()};" styleClass="cssButtonSmall"/--%>
</t:panelGrid>
<t:saveState value="#{detailBeanName.empListOfValues.selectedDTOS}"/>
<t:saveState value="#{detailBeanName.empListOfValues.fullColumnName}"/>
<t:saveState value="#{detailBeanName.empListOfValues.sortAscending}"/>
<t:saveState id="empListOfValues" value="#{detailBeanName.empListOfValues.myTableData}" />
<t:saveState id="emplovSearchType" value="#{detailBeanName.empListOfValues.searchTyp}" />
<t:saveState id="emplovSearchQuery" value="#{detailBeanName.empListOfValues.searchQuery}" />
<t:saveState id="emplovRenderedDropDown" value="#{detailBeanName.empListOfValues.renderedDropDown}"/>
<t:saveState id="emplovsearchMode"  value="#{detailBeanName.empListOfValues.searchMode}"/>
<t:saveState id="emplovreturnMethodName" value="#{detailBeanName.empListOfValues.returnMethodName}"/>
<t:saveState id="emplovsearchMethod" value="#{detailBeanName.empListOfValues.searchMethod}"/>
<t:saveState id="emplovcancelSearchMethod" value="#{detailBeanName.empListOfValues.cancelSearchMethod}"/>
<t:saveState id="emplovsearchAtAllEmployees" value="#{detailBeanName.empListOfValues.searchAtAllEmployees}"/>
<t:saveState id="emplovministryCode" value="#{detailBeanName.empListOfValues.ministryCode}"/>
<t:saveState id="emplovcantLocateSession" value="#{detailBeanName.empListOfValues.cantLocateSession}"/>
<t:saveState value="#{detailBeanName.empListOfValues.resetDivAfterClose}"/>

<!-- Start Paging -->
<t:saveState value="#{detailBeanName.empListOfValues.currentPageIndex}"/>
<t:saveState value="#{detailBeanName.empListOfValues.oldPageIndex}"/>
<t:saveState value="#{detailBeanName.empListOfValues.sorting}"/>
<t:saveState value="#{detailBeanName.empListOfValues.usingPaging}"/>
<t:saveState value="#{detailBeanName.empListOfValues.usingBsnPaging}"/>
<t:saveState value="#{detailBeanName.empListOfValues.updateMyPagedDataModel}"/>
<t:saveState value="#{detailBeanName.empListOfValues.resettedPageIndex}"/>
<t:saveState value="#{detailBeanName.empListOfValues.bsnSortingColumnName}"/>

<t:saveState value="#{detailBeanName.empListOfValues.pagingRequestDTO}"/>
<t:saveState value="#{detailBeanName.empListOfValues.useCustomSearch}"/>
<t:saveState value="#{detailBeanName.empListOfValues.empEmployeeSearchDTO}"/>
<t:saveState value="#{detailBeanName.empListOfValues.pagingBean.totalListSize}"/>
<t:saveState value="#{detailBeanName.empListOfValues.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{detailBeanName.empListOfValues.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
