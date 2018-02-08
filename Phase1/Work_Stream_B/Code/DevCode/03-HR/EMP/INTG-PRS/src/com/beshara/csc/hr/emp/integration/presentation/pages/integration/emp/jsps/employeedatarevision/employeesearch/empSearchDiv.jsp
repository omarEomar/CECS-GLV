<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<t:panelGrid id="msg" align="center" columns="1" dir="#{shared_util.pageDirection}">
    <t:outputText styleClass="errMsg" forceId="true" id="errorMessage"/>
</t:panelGrid>
<t:panelGroup forceId="true" id="civil_div_searchPanel">
    <t:selectOneRadio layout="spread" styleClass="divtext" forceId="true" id="radioBtn"
                      value="#{pageBeanName.employeeSearchBean.searchTyp}">
        <f:selectItem itemLabel="#{globalResources.globalCivilId}" itemValue="1"/>
        <f:selectItem itemLabel="#{globalResources.globalName}" itemValue="0"/>
    </t:selectOneRadio>
    <t:panelGrid id="radioPanel" align="center" columns="2" dir="#{shared_util.pageDirection}" styleClass="divtext">
        <t:radio for="radioBtn" index="0"/>
        <t:radio for="radioBtn" index="1"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="4" align="center" dir="#{shared_util.pageDirection}" id="searchBtnPanel" border="0">
    <t:inputText forceId="true" styleClass="mastertextboxmedium" id="searchQueryID"
                 value="#{pageBeanName.employeeSearchBean.searchQuery}" onkeypress="FireButtonClick('myForm:civil_div_search');"/>
    <h:commandButton type="button" id="civil_div_search"
                     onclick="if(checkRadio_CivilId('radioBtn','searchQueryID','#{globalResources.civil_no_not_less_than_12}')){ searchEmpListOfVal();}"
                     styleClass="OB_search"/>
    <a4j:jsFunction name="searchEmpListOfVal" oncomplete="document.getElementById('myForm:cancelSearch').focus();"
                    action="#{pageBeanName.employeeSearchBean.searchEmployees}"
                    reRender="searchBtnPanel,cancelSearch,searchEmployeesBtn,advancedsearchBtn,civil_div_dataT_data_panel,civil_div_searchPanel,errorMessage,civil_div_paging_panel,civil_div_paging_panel,civil_div_ok"/>
    <t:commandButton value="" styleClass="OB_advanced_search"
                     action="#{pageBeanName.employeeSearchBean.goToAdvancedSearch}" id="advancedsearchBtn"
                     rendered="#{!pageBeanName.employeeSearchBean.searchMode}"/>
    <a4j:commandButton styleClass="OB_cancleSearch" rendered="#{pageBeanName.employeeSearchBean.searchMode}"
                       action="#{pageBeanName.employeeSearchBean.cancelSearchLovDiv}"
                       reRender="searchBtnPanel,advancedsearchBtn,searchEmployeesBtn,civil_div_dataT_data_panel,civil_div_searchPanel,errorMessage,civil_div_paging_panel,civil_div_paging_panel,civil_div_ok"/>
</t:panelGrid>
<!-- Data Table----------------------------------------------------------------------------------------------------->
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="civil_div_dataT_data_panel" styleClass="lovDivScroll5">
        <t:dataTable id="civil_div_dataT_data" var="list"
                     value="#{pageBeanName.employeeSearchBean.usingPaging ? pageBeanName.employeeSearchBean.pagingBean.myPagedDataModel : pageBeanName.employeeSearchBean.myTableData}"
                     rowStyleClass="#{ pageBeanName.employeeSearchBean.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.employeeSearchBean.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.employeeSearchBean.sortColumn}"
                     sortAscending="#{pageBeanName.employeeSearchBean.ascending}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="8%">
                <f:facet name="header"></f:facet>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk"
                                  value="#{pageBeanName.employeeSearchBean.selectedRadio}"
                                  onmousedown="toggleRadio(this);"
                                  onkeyup="if(window.event && window.event.keyCode != 13){toggleRadioKeyUp(this);}"
                                  onkeypress="FireButtonClick('myForm:civil_div_ok');">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.employeeSearchBean.selectedRadioButton}"
                                 reRender="okBackPanel,civil_div_ok"/>
                </t:selectOneRadio>
            </t:column>
            <t:column sortable="false" width="25%">
                <f:facet name="header">
                    <t:outputLabel id="sort_code" forceId="true" styleClass="headerLink"
                                   value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.realCivilId}"/>
            </t:column>
            <t:column sortable="false" width="70%">
                <f:facet name="header">
                    <t:outputLabel id="sort_name" forceId="true" styleClass="headerLink"
                                   value="#{globalResources.globalName}"/>
                </f:facet>
                <h:outputText id="emp_name" value="#{list.citizensResidentsDTO.fullName}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.employeeSearchBean.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg msg_no_icon"
                          rendered="#{!pageBeanName.employeeSearchBean.cantLocateSession}"/>
            <t:outputText value="#{globalResources.global_cantLocateSession}" styleClass="errmsg"
                          rendered="#{pageBeanName.employeeSearchBean.cantLocateSession}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<!-- =====================================paging data panel ======================================================== -->
<t:panelGroup forceId="true" id="civil_div_paging_panel">
    <h:panelGrid id="civil_div_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller"
                 width="300" rendered="#{pageBeanName.employeeSearchBean.listSize > shared_util.noOfTableRows}">
        <t:dataScroller id="civil_div_scroll_1" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                        paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage"
                        fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                        nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage"
                        paginatorActiveColumnClass="paging"
                        paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                        styleClass="textpage" immediate="false" for="civil_div_dataT_data"
                        renderFacetsIfSinglePage="false" actionListener="#{pageBeanName.employeeSearchBean.openLovDiv}">
            <f:facet name="first">
                <h:panelGroup id="civil_div_jobs_list_panelGrp_first">
                    <t:graphicImage id="civil_div_jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_first}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                    <t:graphicImage id="civil_div_jobs_list_img_firstOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="last">
                <h:panelGroup id="civil_div_jobs_list_panelGrp_last">
                    <t:graphicImage id="civil_div_jobs_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_last}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                    <t:graphicImage id="civil_div_jobs_list_img_lastOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="previous">
                <h:panelGroup id="civil_div_jobs_list_panelGrp_prv">
                    <t:graphicImage id="civil_div_jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_previous}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                    <t:graphicImage id="civil_div_jobs_list_img_prvOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="next">
                <h:panelGroup id="civil_div_jobs_list_panelGrp_nxt">
                    <t:graphicImage id="civil_div_jobs_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_next}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                    <t:graphicImage id="civil_div_jobs_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="fastforward">
                <h:panelGroup id="civil_div_jobs_list_panelGrp_ffrwrd">
                    <t:graphicImage id="jcivil_div_obs_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_fastforward}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                    <t:graphicImage id="civil_div_jobs_list_img_ffrwrdOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="fastrewind">
                <h:panelGroup id="civil_div_jobs_list_panelGrp_frwnd">
                    <t:graphicImage id="civil_div_jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_fastrewind}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                    <t:graphicImage id="civil_div_lov_jobs_list_img_frwndOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
        </t:dataScroller>
    </h:panelGrid>
</t:panelGroup>
<!--  ok cancel panel ------------------------------------------------------------------------------------------>
<t:panelGrid columns="2" align="center" id="okBackPanel" forceId="true">
    <a4j:commandButton id="civil_div_ok" value="#{globalResources.ok}"
                       action="#{pageBeanName.employeeSearchBean.closeLovDiv}" styleClass="cssButtonSmall"
                       binding="#{pageBeanName.employeeSearchBean.okCommandButton}"
                       oncomplete="hideLookUpDiv(window.blocker,window.customDiv1)"
                       rendered="#{!empty pageBeanName.employeeSearchBean.selectedDTOS}"/>
    <t:commandButton forceId="true" id="backButtonEmpLovDiv" type="button" styleClass="cssButtonSmall"
                     value="#{globalResources.back}"/>
    <a4j:support action="#{pageBeanName.employeeSearchBean.hideLovDiv}" event="onclick"
                 oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);" reRender="customDiv1"/>
</t:panelGrid>
<!--  ========================================save state=========================================== -->
<t:saveState value="#{pageBeanName.employeeSearchBean.searchTyp}"/>
<t:saveState value="#{pageBeanName.employeeSearchBean.searchQuery}"/>
<t:saveState value="#{pageBeanName.employeeSearchBean.searchMode}"/>
<t:saveState value="#{pageBeanName.employeeSearchBean.myTableData}"/>
<t:saveState value="#{pageBeanName.employeeSearchBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.employeeSearchBean.returnMethodName}"/>