<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{countriesIntegrationBean.divSearchType}"/>
<t:saveState value="#{countriesIntegrationBean.searchQuery}"/>
<t:saveState value="#{countriesIntegrationBean.searchMode}"/>
<t:saveState value="#{countriesIntegrationBean.selectedDTOS}"/>
<t:saveState value="#{countriesIntegrationBean.myTableData}"/>
<t:saveState value="#{countriesIntegrationBean.myDataTable}"/>
<t:saveState value="#{countriesIntegrationBean.selectedRadio}"/>
<t:saveState value="#{countriesIntegrationBean.excludedCntryCodeList}"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="cntryIntgDiv">
    <t:panelGroup id="cntryIntgSearchPnl" forceId="true">
        <t:panelGrid columns="1" border="0" align="center">
            <t:selectOneRadio forceId="true" value="#{countriesIntegrationBean.divSearchType}" id="cntry_searchRadioBtn"
                              layout="spread" styleClass="divtext" onblur="setFocusOnlyOnElement('cntry_searchText');">
                <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
            </t:selectOneRadio>
            <t:panelGrid align="center">
                <t:panelGroup id="cntryIntgErrorGroup" forceId="true" colspan="2">
                    <t:outputText forceId="true" id="errorMessag_cntryIntg" styleClass="errMsgNoHeight"/>
                </t:panelGroup>
                <t:panelGrid id="radioPanel" align="center" columns="2" dir="#{shared_util.pageDirection}"
                             styleClass="divtext">
                    <t:radio for="cntry_searchRadioBtn" index="0"/>
                    <t:radio for="cntry_searchRadioBtn" index="1"/>
                </t:panelGrid>
            </t:panelGrid>
            <t:panelGrid align="center" columns="4" width="50%" forceId="true" id="cntryIntgSearchPanel">
                <t:inputText forceId="true" id="cntry_searchText" value="#{countriesIntegrationBean.searchQuery}"
                             styleClass="textboxmedium" onkeypress="return fireButtonClickSearch(event);"
                             onblur="setFocusOnlyOnElement('cntry_searchRadioBtn');"/>
                <t:panelGroup>
                    <t:commandButton type="button" forceId="true" id="cntry_search_btn"
                                     onclick="if(validateCodeNameSrchParamter('cntry_searchRadioBtn','cntry_searchText','','errorMessag_cntryIntg')){ cntry_search();}"
                                     styleClass="ManyToManySearchButton"/>
                    <a4j:jsFunction name="cntry_search" id="cntry_search" action="#{countriesIntegrationBean.search}"
                                    reRender="cntry_cancelSearch,cntryIntgpaging_panel,cntryIntgErrorGroup,cntryIntgNoTableResultsPanelGrid,cntryIntgSearchPnl,cntryIntgSearchResultGroupId,cntryIntgButtonPanel"/>
                </t:panelGroup>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <a4j:commandButton id="cntry_cancelSearch" action="#{countriesIntegrationBean.cancelSearch}"
                                   reRender="cntryIntgSearchPnl,cntryIntgSearchResultGroupId,cntryIntgpaging_panel,cntryIntgButtonPanel"
                                   styleClass="ManyToManyCancelSearchButton"
                                   rendered="#{countriesIntegrationBean.searchMode}"/>
            </t:panelGrid>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="cntryIntgSearchResultGroupId" styleClass="fullWidthScroll150">
        <t:dataTable forceId="true" id="cntryIntgDataT_available" var="list"
                     value="#{countriesIntegrationBean.myTableData}" rows="#{shared_util.noOfTableRows}"
                     rowIndexVar="index" binding="#{countriesIntegrationBean.myDataTable}" renderedIfEmpty="false"
                     rowOnMouseOver="javascript:addRowHighlight('cntryIntgDataT_available');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true">
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chkRadio"
                                  value="#{countriesIntegrationBean.selectedRadio}" onmousedown="toggleRadio(this);"
                                  onkeyup="toggleRadioKeyUp(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{countriesIntegrationBean.selectedRadioButton}"
                                 reRender="cntryIntgButtonPanel, okBtnId"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="header_code" value="#{globalResources.Code}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="75%">
                <f:facet name="header">
                    <h:outputText id="header_name" value="#{globalResources.name}"/>
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid rendered="#{empty countriesIntegrationBean.myTableData }" styleClass="msg" align="center"
                     forceId="true" id="cntryIntgNoTableResultsPanelGrid" style="height:auto; width: 100%;">
            <h:outputText value="#{globalResources.global_noTableResults}"/>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="cntryIntgpaging_panel" style="height: 31px;">
        <h:panelGrid id="cntryIntgpanelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}"
                     styleClass="scroller" width="300"
                     rendered="#{countriesIntegrationBean.listSize > shared_util.noOfTableRows}">
            <t:dataScroller id="cntryIntgscroll_1" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                            paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
                            fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage"
                            lastStyleClass="textpage" nextStyleClass="textpage" previousStyleClass="textpage"
                            paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                            paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                            styleClass="textpage" immediate="false" for="cntryIntgDataT_available"
                            renderFacetsIfSinglePage="false"
                            actionListener="#{countriesIntegrationBean.openCountriesDiv}">
                <f:facet name="previous">
                    <h:panelGroup id="cntryIntg_list_panelGrp_prv">
                        <t:graphicImage id="cntryIntg_list_img_prvOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="cntryIntg_list_img_prvOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="cntryIntg_list_panelGrp_nxt">
                        <t:graphicImage id="cntryIntg_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="cntryIntg_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="cntryIntg_list_panelGrp_ffrwrd">
                        <t:graphicImage id="jcntryIntgobs_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="cntryIntg_list_img_ffrwrdOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="cntryIntg_list_panelGrp_frwnd">
                        <t:graphicImage id="cntryIntg_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="cntryIntgcntryIntg_list_img_frwndOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
    </t:panelGroup>
    <t:panelGrid columns="3" forceId="true" id="cntryIntgButtonPanel" align="center">
        <t:commandButton forceId="true" id="okBtnId" value="#{globalResources.ok}"
                         disabled="#{empty countriesIntegrationBean.selectedDTOS}"
                         onblur="setFocusOnlyOnElement('backButtonIntegrationDiv1');"
                         action="#{countriesIntegrationBean.selectCountry}" styleClass="cssButtonSmall"/>
        <t:commandButton forceId="true" id="backButtonIntegrationDiv1" type="button" value="#{globalResources.back}"
                         styleClass="cssButtonSmall" onclick="cntryIntgDivBackJS();"/>
        <a4j:jsFunction name="cntryIntgDivBackJS" action="#{countriesIntegrationBean.back}" reRender="cntryIntgDiv"
                        oncomplete="hideLookUpDiv();"/>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function searchCountry(event) {
      var unicode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
      if (unicode == 13) {
          if (validateCodeNameSrchParamter('cntry_searchRadioBtn', 'cntry_searchText', '', 'errorMessag_cntryIntg')) {
              cntry_search();
          }
          if (event.preventDefault) {
              event.preventDefault();
          }
          else {
              event.returnValue = false;
          }
      }
  }

      function fireButtonClickSearch(e) {
          if (e.keyCode == 13) {
              document.getElementById('cntry_search_btn').click();
              return false;
          }
      }
</script>