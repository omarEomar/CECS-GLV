<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:messages/>
<%-- ##################START################# Strat of Join Condition Section #################SHEKA############### --%>
<htm:center>
    <t:outputText value="#{grsIntgResources.joinConditionDivTitle}" styleClass="TitlePage"/>
    <f:verbatim>
        <br/>
    </f:verbatim>
</htm:center>
<t:panelGroup forceId="true" id="grsIntgHideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingMaxHeight('grsIntgHideDivImg', 'grsIntgSearchPnl', 'grsIntgSearchResultTblPnl')"/>
</t:panelGroup>

<t:panelGroup forceId="true" id="grsIntgSearchPnl">
    <t:panelGroup forceId="true" id="grsIntgSearchCriteriaPnl">
        <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
            <t:outputLabel value="#{grsIntgResources.divInfoConditionCode}" styleClass="lable01"/>
            <t:inputText forceId="true" id="grsIntgSearchCondCode" styleClass="textbox" maxlength="6"
                         value="#{jcHelperBeanName.searchConditionsDTO.conditionCode}"
                         onkeypress="FireGrsIntgSearchBtn();"
                         onkeyup="disableCharacters(this);handleGrsIntgSearchBtn();"
                         onblur="setFocusOnlyOnElement('grsIntgSearchCondName');"/>
            <t:outputLabel value="#{grsIntgResources.divInfoConditionName}" styleClass="lable01"/>
            <t:inputText forceId="true" id="grsIntgSearchCondName" styleClass="textboxmedium"
                         value="#{jcHelperBeanName.searchConditionsDTO.conditionName}"
                         onkeypress="FireGrsIntgSearchBtn();" onkeyup="trimBorders(this);handleGrsIntgSearchBtn();"/>
            <t:outputLabel value="#{grsIntgResources.divInfoConditionLine}" styleClass="lable01"/>
            <t:panelGroup colspan="3">
                <t:selectOneMenu forceId="true" id="grsIntgSearchLineVal" styleClass="DropdownboxTooLarge"
                                 style="width:407px" value="#{jcHelperBeanName.searchConditionsDTO.lineCode}"
                                 onchange="handleGrsIntgSearchBtn();" onkeypress="FireGrsIntgSearchBtn();"
                                 converter="javax.faces.Long">
                    <f:selectItem itemLabel="#{grsIntgResources.select}"
                                  itemValue="#{jcHelperBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{jcHelperBeanName.linesList}" var="item" itemLabel="#{item.name}"
                                   itemValue="#{item.code.lineCode}"/>
                </t:selectOneMenu>
            </t:panelGroup>
            <t:panelGroup colspan="1">
                <t:outputLabel rendered="#{jcHelperBeanName.renderConditionFilter}"
                               value="#{grsIntgResources.conditionFilterLabel}" styleClass="lable01"/>
            </t:panelGroup>
            <t:panelGroup colspan="3">
                <%-- <t:outputLabel rendered="#{jcHelperBeanName.renderConditionFilter}"
                     value="#{grsIntgResources.conditionFilterLabel}" styleClass="lable01"/>--%>
                <t:selectOneMenu rendered="#{jcHelperBeanName.renderConditionFilter}" forceId="true"
                                 id="conditionFilterMenu" styleClass="DropdownboxMedium" style="width:150px"
                                 value="#{jcHelperBeanName.searchConditionsDTO.conditionFilter}"
                                 onchange="handleGrsIntgSearchBtn();"
                                 onkeypress="FireGrsIntgSearchBtn();">
                    <f:selectItem itemLabel="#{grsIntgResources.allConditions}" itemValue="0"/>
                    <f:selectItem itemLabel="#{grsIntgResources.relatedConditions}" itemValue="1"/>
                    <f:selectItem itemLabel="#{grsIntgResources.unRelatedConditions}" itemValue="2"/>
                </t:selectOneMenu>
                <a4j:jsFunction name="searchForConditions" action="#{jcHelperBeanName.searchForConditions}"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center" border="0" forceId="true" id="grsIntgSearchBtnsPnl">
        <t:commandButton forceId="true" id="grsIntgSearchCondBtn" value="" styleClass="ManyToManySearchButton" type="button">
            <a4j:support event="onclick" action="#{jcHelperBeanName.search}" reRender="grsIntgSearchBtnsPnl, grsIntgSearchResultPnl"
                    oncomplete="document.getElementById('grsIntgSearchCondBtn').focus();"/>
        </t:commandButton>
    
        <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton" rendered="#{jcHelperBeanName.searchMode}"
                action="#{jcHelperBeanName.cancelSearch}" reRender="grsIntgSearchPnl, grsIntgSearchResultPnl"
                oncomplete="handleGrsIntgSearchBtn();"/>
    </t:panelGrid>
    
    
</t:panelGroup>
<t:panelGroup forceId="true" id="grsIntgSearchResultPnl">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="grsIntgSearchResultTblPnl" styleClass="fullWidthScroll170">
            <t:dataTable id="grsIntgSearchResultTbl" var="list" value="#{jcHelperBeanName.myTableData}"
                    forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                    renderedIfEmpty="false" binding="#{jcHelperBeanName.myDataTable}"
                    rowOnMouseOver="javascript:addRowHighlight('myForm:grsIntgSearchResultTbl');addRowHighlight('treeform:grsIntgSearchResultTbl');" 
                    styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                    rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                    width="100%" align="top" dir="#{shared_util.pageDirection}">
                    
                <t:column id="check_column" styleClass="standardTable_Header2 noText" width="5%">
                    <h:selectOneRadio styleClass="radioButton_DataTable"
                                      onkeypress="FireGrsIntgSaveBtn();"
                                      onclick="toggleRadio(this); grsIntgUpdateSelectedCondition('#{list.code.key}');">
                        <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                    </h:selectOneRadio>
                </t:column>
                <t:column id="code_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.key}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="70%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
                <t:column id="condDetails" sortable="false" width="15%">
                    <f:facet name="header">
                        <h:outputText id="header_details" value="#{grsIntgResources.details}"/>
                    </f:facet>
                    <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="#{globalResources.Available}"
                            actionListener="#{jcHelperBeanName.viewConditionDetails}" 
                            reRender="grsIntgHiddenValues" oncomplete="openGrsIntgConditionDetailsWindow();">
                         <f:attribute name="conditionCode" value="#{list.code.key}"/>
                    </a4j:commandButton>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ jcHelperBeanName.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid id="grsIntgSearchResultScrollPnl" columns="1" dir="#{shared_util.pageDirection}"
                 styleClass="scroller" width="300px" rendered="#{jcHelperBeanName.listSize > shared_util.noOfTableRows}">
        <t:dataScroller id="grsIntgSearchResultScroll"
                        actionListener="#{jcHelperBeanName.scrollerAction}" fastStep="5"
                        pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                        paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                        firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                        previousStyleClass="textpage" paginatorColumnClass="textpage"
                        paginatorActiveColumnClass="paging"
                        paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                        styleClass="textpage" immediate="false" for="grsIntgSearchResultTbl" renderFacetsIfSinglePage="false">
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
</t:panelGroup>
<t:inputHidden forceId="true" id="selectedConditionCode" value="#{jcHelperBeanName.selectedConditionCode}"/>
<t:inputHidden forceId="true" id="renderConditionFilterValue" value="#{jcHelperBeanName.renderConditionFilter}"/>
<f:verbatim>
    
<script type="text/javascript">
  handleGrsIntgSearchBtn();

  function handleGrsIntgSearchBtn() {
      document.getElementById('grsIntgSearchCondBtn').disabled = true;
      document.getElementById('grsIntgSearchCondBtn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
      
      var conditionCode = document.getElementById("grsIntgSearchCondCode").value;
      var conditionName = document.getElementById("grsIntgSearchCondName").value;
      var lineValueCode = document.getElementById("grsIntgSearchLineVal").value;
      var renderConditionFilterValue= false;
      var conditionFilter = "";
      if(document.getElementById("renderConditionFilterValue") != null){
       renderConditionFilterValue = document.getElementById("renderConditionFilterValue").value;   
      }
       
      
      if(renderConditionFilterValue == "true"){
          conditionFilter = document.getElementById("conditionFilterMenu").value;
      }
      if (conditionCode.length != 0 || conditionName.length != 0 || lineValueCode != '-100'||((conditionFilter.length != 0 || conditionFilter != "") && conditionFilter != "0")) {
          document.getElementById('grsIntgSearchCondBtn').disabled = false;
          document.getElementById('grsIntgSearchCondBtn').className = 'ManyToManySearchButton';
      }
  }

  function FireGrsIntgSearchBtn() {
      handleGrsIntgSearchBtn();
      FireButtonClick('grsIntgSearchCondBtn');
  }

  function grsIntgUpdateSelectedCondition(selectedCode) {
      document.getElementById('selectedConditionCode').value = selectedCode;

      document.getElementById('grsIntgJoinSaveBtn').disabled = false;
      document.getElementById('grsIntgJoinSaveBtn').focus();
      document.getElementById('grsIntgJoinSaveBtn').focus();
  }

      function FireGrsIntgSaveBtn() {
          FireButtonClick('grsIntgJoinSaveBtn');
      }
</script>
</f:verbatim>
