<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.sss label{display:block;height:16px;}</htm:style>
<tiles:importAttribute scope="request"/>
<%-- <f:loadBundle basename="com.beshara.csc.hr.sal.presentation.resources.sal" var="integrateResources"/>--%>
<!-- Added By Yassmine  -->
<f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="integrateResources"/>
<%-- Added By Yassmine, Make this bean as an attribute in the pageBeanName so i comment aliasBean line and use
     pageBeanName.regulationJoinBean directly <t:aliasBean alias="#{conditionBeanName}" value="#{conditionDivService}" >--%>
<htm:center>
    <t:outputText value="#{integrateResources.choose_Condition_Title_Div}" styleClass="TitlePage"/>
</htm:center>
<t:panelGroup id="correctDiv" forceId="true">
    <%-- t:saveState value="#{pageBeanName.linesMenuList}"/> <t:saveState value="#{pageBeanName.parentName}"/>
         <t:saveState value="#{pageBeanName.success}"/> <t:saveState value="#{pageBeanName.fixingErrorDivShowen}"/--%>
    <%-- edit by m.elsaied (fix issue key : NL-279 - 2. add 001.JPG)--%>
    <%-- <f:verbatim><br /></f:verbatim>--%>
    <%-- <center><h:outputText value="#{pageBeanName.addDivTitle}" style="font-weight:bold" /></center>--%>
    <%-- <f:verbatim><br /></f:verbatim>--%>
    <t:saveState value="#{pageBeanName.conditionDivService.linesMenuList}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.searchConditionsDTO}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.myTableData}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.selectedDTOS}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.containerBeanName}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.okButtonMethod}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.resetButtonMethod}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.okButtonForCancelMethod}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.externalSearchMethodBinding}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.searchInCenter}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.searchMode}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.backMethodName}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.appModuleCode}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.status}"/>
    <t:saveState value="#{pageBeanName.conditionDivService.exculdedConditionList}"/>
    <%-- Commented By Yassmine, <t:saveState value="#{pageBeanName.dtoDetails}"/> <t:saveState
         value="#{pageBeanName.dto}"/>--%>
    <%-- t:saveState value="#{pageBeanName.conditionDivService.conditionDivServiceDTO}" /--%>
    <t:outputText id="successMsgCorrectDiv" forceId="true" value="#{globalResources.SuccessAdds}" styleClass="errMsg"
                  rendered="#{pageBeanName.conditionDivService.success}"/>
    <f:verbatim>
        <span id="msg" class="errMsg"/>
    </f:verbatim>
    <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
        <t:outputLabel value="#{integrateResources.divInfoConditionCode}" styleClass="lable01"/>
        <t:inputText forceId="true" id="info_condition_code"
                     value="#{pageBeanName.conditionDivService.searchConditionsDTO.conditionCode}" styleClass="textbox"
                     onblur="if(isVisibleComponent('customDiv1'))setFocusOnlyOnElement('info_condition_name');"
                     onkeypress="FireButtonClick('conditionSearch')" maxlength="6" onkeyup="disableCharacters(this);"/>
        <t:outputLabel value="#{integrateResources.divInfoConditionName}" styleClass="lable01"/>
        <t:inputText forceId="true" id="info_condition_name"
                     value="#{pageBeanName.conditionDivService.searchConditionsDTO.conditionName}"
                     styleClass="textboxmedium" onkeypress="FireButtonClick('conditionSearch')"/>
        <t:outputLabel value="#{integrateResources.divInfoConditionLine}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu forceId="true" id="selectedLineValueMenu"
                             value="#{pageBeanName.conditionDivService.selectedLineValue}"
                             styleClass="DropdownboxTooLarge" style="width:412px"
                             onkeypress="FireButtonClick('conditionSearch')">
                <f:selectItem itemLabel="#{integrateResources.select}"
                              itemValue="#{pageBeanName.conditionDivService.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.conditionDivService.linesMenuList}" itemLabel="#{line.name}"
                               itemValue="#{line.code.key}" var="line"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="2" align="center" border="0">
        <t:panelGroup>
            <t:commandButton value="" onclick="conditionSearchJS();return false;" forceId="true" id="conditionSearch"
                             styleClass="ManyToManySearchButton"/>
            <a4j:jsFunction name="conditionSearchJS" oncomplete="document.getElementById('conditionSearch').focus();"
                            reRender="correctDiv,cancelSearchButto"
                            action="#{pageBeanName.conditionDivService.searchConditionsAction}"/>
        </t:panelGroup>
        <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton"
                           action="#{pageBeanName.conditionDivService.cancelSearch}" reRender="correctDiv"
                           rendered="#{pageBeanName.conditionDivService.searchMode}" id="cancelSearchButto"/>
    </t:panelGrid>
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="join_condition_dataT_data_panel" styleClass="dataT-With-6-row-filter" >
            <t:dataTable id="Con_dataT_data" var="list" value="#{pageBeanName.conditionDivService.myTableData}"
                         rowStyleClass="#{ pageBeanName.conditionDivService.selected ? 'standardTable_RowHighlighted' : null}"
                         forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                         renderedIfEmpty="false" binding="#{pageBeanName.conditionDivService.myDataTable}"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                         styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                         sortColumn="#{pageBeanName.conditionDivService.sortColumn}"
                         sortAscending="#{pageBeanName.conditionDivService.ascending}">
                <t:column id="check_column" styleClass="standardTable_Header2 sss" width="5%">
                    <h:selectOneRadio styleClass="radioButton_DataTable"
                                      onkeypress="FireButtonClick('saveButtonCustomDiv1')"
                                      valueChangeListener="#{pageBeanName.conditionDivService.selectedRadioButton}"
                                      onclick="toggleRadio(this); document.getElementById('saveButtonCustomDiv1').disabled=false;document.getElementById('saveButtonCustomDiv1').focus();document.getElementById('saveButtonCustomDiv1').focus();">
                        <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                    </h:selectOneRadio>
                    <%-- t:selectOneRadio id="chkCond" valueChangeListener="{conditionDivService.radioSelectedEvent}">
                         <f:selectItem itemLabel=" " itemValue="#{list.code.key}" /> <%-- a4j:support event="onclick"
                         action="#{pageBeanName.conditionDivService.selectedRadioButton}" /--%>
                    <%-- /t:selectOneRadio--%>
                </t:column>
                <t:column id="code_column" sortable="false" width="20%">
                    <f:facet name="header">
                        <%-- t:commandSortHeader id="codeColumn" columnName="code" arrow="false"
                             styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending">
                             <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                             rendered="true" border="0"/> </f:facet> <f:facet name="descending"> <t:graphicImage
                             id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true"
                             border="0"/> </f:facet--%>
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                        <%-- /t:commandSortHeader--%>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.key}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="75%">
                    <f:facet name="header">
                        <%-- t:commandSortHeader id="nameColumn" columnName="name" arrow="false"
                             styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending">
                             <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                             rendered="true" border="0"/> </f:facet> <f:facet name="descending"> <t:graphicImage
                             id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true"
                             border="0"/> </f:facet--%>
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                        <%-- /t:commandSortHeader--%>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ pageBeanName.conditionDivService.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <!--t:panelGrid columns="1" forceId="true" id="conditionDiv_scrollerPanel"-->
    <t:panelGrid id="conditionDiv_panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}"
                 styleClass="scroller" width="300px" rendered="#{pageBeanName.conditionDivService.listSize > 3}">
        <t:dataScroller id="conditionDiv_scroll_1add"
                        actionListener="#{payrollListBean.conditionDivService.scrollerAction}" fastStep="5"
                        pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                        paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                        firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                        previousStyleClass="textpage" paginatorColumnClass="textpage"
                        paginatorActiveColumnClass="paging"
                        paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                        styleClass="textpage" immediate="false" for="Con_dataT_data" renderFacetsIfSinglePage="false">
            <f:facet name="first">
                <t:panelGroup id="conditionDiv_req_list_panelGrp_first">
                    <t:graphicImage id="conditionDiv_req_list_img_firstOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_first}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                    <t:graphicImage id="conditionDiv_req_list_img_firstOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="last">
                <t:panelGroup id="conditionDiv_req_list_panelGrp_last">
                    <t:graphicImage id="conditionDiv_req_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_last}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                    <t:graphicImage id="conditionDiv_req_list_img_lastOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="previous">
                <t:panelGroup id="conditionDiv_req_list_panelGrp_prv">
                    <t:graphicImage id="conditionDiv_req_list_img_prvOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_previous}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                    <t:graphicImage id="conditionDiv_req_list_img_prvOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="next">
                <t:panelGroup id="conditionDiv_req_list_panelGrp_nxt">
                    <t:graphicImage id="conditionDiv_req_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_next}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                    <t:graphicImage id="conditionDiv_req_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="fastforward">
                <t:panelGroup id="conditionDiv_req_list_panelGrp_ffrwrd">
                    <t:graphicImage id="conditionDiv_req_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_fastforward}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                    <t:graphicImage id="conditionDiv_req_list_img_ffrwrdOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="fastrewind">
                <t:panelGroup id="conditionDiv_req_list_panelGrp_frwnd">
                    <t:graphicImage id="conditionDiv_req_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_fastrewind}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                    <t:graphicImage id="conditionDiv_req_list_img_frwndOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
        </t:dataScroller>
    </t:panelGrid>
    <!--/t:panelGrid-->
    <t:panelGrid columns="1" border="0" width="100%" columnClasses="center">
        <%-- h:commandButton value="#{globalResources.SaveButton}"
             action="#{pageBeanName.conditionDivService.invokeApplication}" id="saveCondtionDiv"
             styleClass="cssButtonSmall"/--%>
        <t:panelGroup>
            <t:commandButton value="#{globalResources.ok}" styleClass="cssButtonSmall"
                             onclick="hidTreeDiv('add',window.blocker,window.customDiv1,'successMsgTreeCorrect');"
                             action="#{pageBeanName.conditionDivService.saveCondition}"
                             binding="#{pageBeanName.conditionDivService.okCommandButton}" disabled="true"
                             forceId="true" id="saveButtonCustomDiv1"/>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <t:commandButton forceId="true" type="button" onclick="foucsFirstElementOnCustomDiv();backLovJsFunction();"
                             id="backButtonCustomDiv1"
                             onblur="if(isVisibleComponent('customDiv1')){foucsFirstElementOnCustomDiv();setFocusOnlyOnElement('info_condition_code');document.getElementById('info_condition_code').focus();}"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backLovJsFunction"
                            oncomplete="hidTreeDiv('add',window.blocker,window.customDiv1,'successMsgTreeCorrect');focusAfterBackFromGrsDiv();"
                            action="#{pageBeanName.conditionDivService.backFromCondition}"
                            reRender="correctDiv,OperationBar"/>
        </t:panelGroup>
        <%-- t:commandButton
             onblur="foucsFirstElementOnCustomDiv();setFocusOnlyOnElement('info_condition_code');document.getElementById('info_condition_code').focus();" /--%>
        <%-- <a4j:commandButton styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"
             action="#{pageBeanName.addAndNew}" reRender="treeAddDiv"
             onclick="validateInputText('name_panel','msg',null,null)"/>--%>
        <%-- modifiy width by sherif.omar--%>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  foucsFirstElementOnCustomDiv();

  function foucsFirstElementOnCustomDiv() {

      if (isVisibleComponent('customDiv1')) {
          document.getElementById('info_condition_code').focus();
          document.getElementById('info_condition_code').focus();
      }
  }

      function focusAfterBackFromGrsDiv() {
          if (typeof (setFocusAfterBackFromGrsDiv) != 'undefined') {
              setFocusAfterBackFromGrsDiv();
          }
      }
</script>
