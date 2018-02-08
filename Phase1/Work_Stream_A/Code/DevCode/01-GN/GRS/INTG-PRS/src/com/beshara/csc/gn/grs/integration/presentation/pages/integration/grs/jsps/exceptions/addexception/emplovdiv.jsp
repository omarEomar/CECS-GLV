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
                            <t:selectOneRadio forceId="true" id="civil_div_searchRadioBtn" value="#{pageBeanName.empListOfValues.searchTyp}" 
                                    styleClass="divtext" onblur="settingFoucsAtEmpLovDiv();"> 
                             <f:selectItem itemLabel="#{globalResources.globalCivilId}" itemValue="0"/>
                             <f:selectItem itemLabel="#{globalResources.globalName}" itemValue="1"/>
                            </t:selectOneRadio>
                           </t:panelGrid> 
                            
                            <t:panelGrid align="center" columns="4" border="0" width="50%" forceId="true" id="civil_div_searchPanel">
                             <t:inputText onkeypress="FireButtonClick('myForm:civil_div_search');" forceId="true" id="civil_div_searchText"  value="#{pageBeanName.empListOfValues.searchQuery}" styleClass="textboxmedium" />
                             <t:panelGroup>
                             <h:commandButton  type="button" id="civil_div_search"  onclick="if(checkRadio_CivilId('civil_div_searchRadioBtn','civil_div_searchText','#{globalResources.civil_no_not_less_than_12}')){ searchEmpListOfVal();}" styleClass="ManyToManySearchButton"/>
                             <a4j:jsFunction name="searchEmpListOfVal" oncomplete="document.getElementById('myForm:civil_div_cancelSearch').focus();"  action="#{pageBeanName.empListOfValues.searchLovDiv}" reRender="civil_div_dataT_data_panel,civil_div_searchPanel,errorMessage,civil_div_paging_panel,civil_div_paging_panel,civil_div_ok"/>
                             
                             </t:panelGroup>
                             <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                             <a4j:commandButton id="civil_div_cancelSearch" action="#{pageBeanName.cancelSearchDiv}" reRender="civil_div_ok,civil_div_searchText,civil_div_dataT_data_panel,civil_div_searchPanel,errorMessage,civil_div_paging_panel,civil_div_searchRadioBtn,civil_div_ok"  styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.empListOfValues.searchMode}" />
                             <t:outputText value="&nbsp;" escape="false" rendered="#{!pageBeanName.empListOfValues.searchMode}" />
                             <t:panelGroup  colspan="4">
                             <htm:center>
                             <t:outputText value="#{pageBeanName.empListOfValues.errorMsgValue}" forceId="true" id="errorMessage" styleClass="errMsgNoHeight" />
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
        
            
            <t:saveState value="#{pageBeanName.empListOfValues.searchMode}" />           
            
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">

    <t:panelGroup forceId="true" id="civil_div_dataT_data_panel"  styleClass="lovDivScroll5">
    
        <t:dataTable id="civil_div_dataT_data" var="list" value="#{pageBeanName.empListOfValues.usingPaging ? pageBeanName.empListOfValues.pagingBean.myPagedDataModel : pageBeanName.empListOfValues.myTableData}" rowStyleClass="#{ pageBeanName.empListOfValues.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.empListOfValues.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.empListOfValues.sortColumn}" sortAscending="#{pageBeanName.empListOfValues.ascending}">
       
       <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
        <f:facet name="header">
        </f:facet>
        <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" 
            value="#{pageBeanName.empListOfValues.selectedRadio}" 
            onmousedown="toggleRadio(this);" onkeyup="if(window.event && window.event.keyCode != 13){toggleRadioKeyUp(this);}"
            onkeypress="FireButtonClick('myForm:civil_div_ok');">
         <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
         <a4j:support event="onclick" actionListener="#{pageBeanName.empListOfValues.selectedRadioButton}" reRender="civil_div_ok ,civilIdInTxt , nameId"/>
        </t:selectOneRadio>
       </t:column> 
       
       
         <%--<t:column id="check_column33" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header">
                  
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk33" value="#{list.checked}" onkeypress="FireButtonClick('myForm:civil_div_ok');" styleClass="radioButton_DataTable" >
                  <a4j:support event="onclick" actionListener="#{pageBeanName.selectedEmpLOVDivCheckboxs}" reRender="civil_div_ok ,civilIdInTxt , nameId"/>
                  
                  
                </t:selectBooleanCheckbox>
           
              </t:column>--%>
       
            <t:column  sortable="false"  width="20%">
                <f:facet name="header">
                 <t:outputLabel id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            
            <t:column  sortable="false" width="75%">
                <f:facet name="header">
                 <t:outputLabel id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.globalName}"/>
                 
                </f:facet>
                 
                <h:outputText id="emp_name" value="#{list.fullName}" />
            </t:column>
        </t:dataTable>
        
         <t:panelGrid columns="1" rendered="#{ pageBeanName.empListOfValues.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg msg_no_icon" rendered="#{!pageBeanName.empListOfValues.cantLocateSession}"/>
            <t:outputText value="#{globalResources.global_cantLocateSession}" styleClass="errmsg" rendered="#{pageBeanName.empListOfValues.cantLocateSession}"/>
        </t:panelGrid>
      
    </t:panelGroup>
     
      
</t:panelGrid>

                </htm:td></htm:tr></htm:table>
                
                
<t:panelGroup forceId="true" id="civil_div_paging_panel">
        <h:panelGrid id="civil_div_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.empListOfValues.listSize > shared_util.noOfTableRows}" >
            
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
                    actionListener="#{pageBeanName.ShowSearchDiv}"
                    >
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
 <a4j:commandButton id="civil_div_ok" value="#{globalResources.ok}" action="#{pageBeanName.empListOfValues.closeLovDiv}" styleClass="cssButtonSmall" oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);showToggle();" reRender="#{pageBeanName.empListOfValues.renderedDropDown}" binding="#{pageBeanName.empListOfValues.okCommandButton}" disabled="#{empty pageBeanName.empListOfValues.selectedDTOS }" />
 
<t:panelGroup>
    <t:commandButton forceId="true" id="backButtonEmpLovDiv" onblur="settingFoucsAtEmpLovDiv();" type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:support action="#{pageBeanName.hideEmpLovDiv}" event="onclick"  oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);if(typeof(setFocusAfterCloseEmpLovDiv)!='undefined'){setFocusAfterCloseEmpLovDiv()};" reRender="divSearch"/>
</t:panelGroup>
 <%-- by Ashraf Gaber to reset date --%>
 <%--t:commandButton onblur="settingFoucsAtEmpLovDiv();" forceId="true" id="backButtonEmpLovDiv" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv1);if(typeof(setFocusAfterCloseEmpLovDiv)!='undefined'){setFocusAfterCloseEmpLovDiv()};" styleClass="cssButtonSmall"/--%>
</t:panelGrid>
<t:saveState value="#{pageBeanName.empListOfValues.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.empListOfValues.fullColumnName}"/>
<t:saveState value="#{pageBeanName.empListOfValues.sortAscending}"/>
<t:saveState id="empListOfValues" value="#{pageBeanName.empListOfValues.myTableData}" />
<t:saveState id="emplovSearchType" value="#{pageBeanName.empListOfValues.searchTyp}" />
<t:saveState id="emplovSearchQuery" value="#{pageBeanName.empListOfValues.searchQuery}" />
<t:saveState id="emplovRenderedDropDown" value="#{pageBeanName.empListOfValues.renderedDropDown}"/>
<t:saveState id="emplovsearchMode"  value="#{pageBeanName.empListOfValues.searchMode}"/>
<t:saveState id="emplovreturnMethodName" value="#{pageBeanName.empListOfValues.returnMethodName}"/>
<t:saveState id="emplovsearchMethod" value="#{pageBeanName.empListOfValues.searchMethod}"/>
<t:saveState id="emplovcancelSearchMethod" value="#{pageBeanName.empListOfValues.cancelSearchMethod}"/>
<t:saveState id="emplovsearchAtAllEmployees" value="#{pageBeanName.empListOfValues.searchAtAllEmployees}"/>
<t:saveState id="emplovministryCode" value="#{pageBeanName.empListOfValues.ministryCode}"/>
<t:saveState id="emplovcantLocateSession" value="#{pageBeanName.empListOfValues.cantLocateSession}"/>
<t:saveState value="#{pageBeanName.empListOfValues.resetDivAfterClose}"/>

<!-- Start Paging -->
<t:saveState value="#{pageBeanName.empListOfValues.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.empListOfValues.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.empListOfValues.sorting}"/>
<t:saveState value="#{pageBeanName.empListOfValues.usingPaging}"/>
<t:saveState value="#{pageBeanName.empListOfValues.usingBsnPaging}"/>
<t:saveState value="#{pageBeanName.empListOfValues.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.empListOfValues.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.empListOfValues.bsnSortingColumnName}"/>

<t:saveState value="#{pageBeanName.empListOfValues.pagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.empListOfValues.useCustomSearch}"/>
<t:saveState value="#{pageBeanName.empListOfValues.empEmployeeSearchDTO}"/>
<t:saveState value="#{pageBeanName.empListOfValues.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.empListOfValues.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.empListOfValues.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
