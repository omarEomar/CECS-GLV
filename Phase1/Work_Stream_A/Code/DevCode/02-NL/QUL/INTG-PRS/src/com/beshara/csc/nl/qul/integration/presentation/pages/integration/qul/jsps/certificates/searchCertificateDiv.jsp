<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration" var="qulintegration"/>
<%--<h:panelGrid columns="1" align="center">
    <t:outputText id="qulIntgJobFieldValError" forceId="true" styleClass="errMsg"
                  value="#{qulintegration.inavlid_Qul_val}" binding="#{pageBeanName.errorMsgBind}"
                  style="#{pageBeanName.showMsg}"/>
</h:panelGrid>--%>
<%--<t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">--%><%--#{pageBeanName.iconPnlStyle}--%><%--
    <t:panelGroup onclick="showHideFilterPnlFn();"/>
</t:panelGroup>--%>
<%--<a4j:jsFunction action="#{pageBeanName.showHideFilterPnl}" name="showHideFilterPnlFn" 
    reRender="hiddenBtnPnl,hideDivImg,qulIntgSearchPnl,qulIntgSearchResultTblPnl" 
    oncomplete="toggleDivUsingMaxHeightVer2('hideDivImg', 'qulIntgSearchPnl', 'qulIntgSearchResultTblPnl',document.getElementById('filterPnlCollapsed').value);"/>--%>
   
<t:panelGroup forceId="true" id="qulIntgSearchPnl" >
<%--style="display:#{pageBeanName.filterPnlDisplay}"--%>
    <t:panelGroup forceId="true" id="qulIntgSearchCriteriaPnl">
        <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="1" cellspacing="0">
            <h:outputLabel styleClass="lable01" value="#{globalResources.Code}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="qulIntgCertificateKey" value="#{pageBeanName.certificateKey}" styleClass="textbox"
                             onchange="trimBorders(qulIntgCertificateKey);" disabled="#{pageBeanName.searchMode}"
                             onkeypress="FireQulIntgSearchBtn();trimBorders(qulIntgCertificateKey);"
                             onkeyup="enabelIntegerOnly(this);handleQulIntgSearchBtn();" style="width:200px;"/>
                <a4j:jsFunction reRender="qulIntgSearchBtnsPnl,searchCertificatesBtn" name="rerendersearchCertificatesBtnButton"
                                oncomplete="setFocusOnElement('qulIntgCertificateName');"/>
            </t:panelGroup>
            <t:outputLabel value="#{globalResources.name}" styleClass="lable01"/>
            <t:panelGroup colspan="1">
                <t:inputText maxlength="250" value="#{pageBeanName.certificateName}" id="qulIntgCertificateName" forceId="true"
                             styleClass="textbox" onchange="trimBorders(qulIntgCertificateName);" style="width:200px;"
                             onkeypress="FireQulIntgSearchBtn();trimBorders(qulIntgCertificateName);"
                             onkeyup="handleQulIntgSearchBtn();" disabled="#{pageBeanName.searchMode}"/>
                <a4j:jsFunction reRender="qulIntgSearchBtnsPnl,searchCertificatesBtn" name="rerendersearchCertificatesBtnButton"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
<t:panelGrid columns="2" align="center" border="0" forceId="true" id="qulIntgSearchBtnsPnl">
    <%-- <a4j:commandButton id="searchCertificatesBtn" value="" styleClass="ManyToManySearchButton"
         action="#{pageBeanName.search}" disabled=""
         reRender="qulIntgSearchBtnsPnl,qulIntgSearchPnl,qulIntgSearchResultPnl,qul_paging_panel_group,qulIntgNavPnl"
         oncomplete="document.getElementById('searchCertificatesBtn').focus();"/>--%>
    <t:commandButton forceId="true" id="searchCertificatesBtn" value="" styleClass="ManyToManySearchButton" type="button"
                     rendered="#{!pageBeanName.searchMode}">
        <a4j:support event="onclick" action="#{pageBeanName.searchCertificates}"
                     reRender="qulIntgSearchBtnsPnl,qulIntgSearchPnl,qulIntgSearchResultPnl,qul_paging_panel_group,qulIntgNavPnl"
                     oncomplete="document.getElementById('searchCertificatesBtn').focus();"/>
    </t:commandButton>
    <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.searchMode}"
                       action="#{pageBeanName.cancelSearch}"
                       reRender="qulIntgSearchBtnsPnl,qulIntgSearchPnl,qulIntgSearchResultPnl,qul_paging_panel_group,qulIntgNavPnl"
                       oncomplete="handleQulIntgSearchBtn();"/>
</t:panelGrid>
<t:panelGroup forceId="true" id="qulIntgSearchResultPnl">

    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center" >
        <t:panelGroup forceId="true" id="qulIntgSearchResultTblPnl" style="max-height:100px;" styleClass="fullWidthScroll80"><%--#{pageBeanName.resultPnlHeight}--%>
        
            <t:dataTable id="qulIntgSearchResultTbl" var="list" value="#{pageBeanName.searchCertificatesList}" forceId="true"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                         binding="#{pageBeanName.myDataTable}" rows="12"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:qulIntgSearchResultTbl');addRowHighlight('treeform:qulIntgSearchResultTbl');"
                         styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{pageBeanName.singleSelection}">
                    <f:facet name="header"/>
                    <t:selectOneRadio styleClass="radioButton_DataTable" id="chkRadio"
                                      value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);"
                                      onkeyup="toggleRadioKeyUp(this);">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                     reRender="qulIntgNavPnl"/>
                    </t:selectOneRadio>
                </t:column>
                <t:column id="code_column" sortable="false" width="18%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.cntqualificationCode}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="70%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{empty pageBeanName.searchCertificatesList}" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:panelGroup forceId="true" id="qul_paging_panel_group">
    <t:panelGroup forceId="true" id="paging_panel_popup"
                  rendered="#{ (pageBeanName.searchCertificatesListSize > 12) ? true : false }">
        <%-- <t:outputText dir="ltr" forceId="true" id="listSize1" style="width:100%" styleClass="noOfRecords"
             value="#{globalResources.noOfRecords} : #{pageBeanName.listSize}"/>--%>
        <h:panelGrid id="qul_panelGrd_scroller" columns="1" dir="#{rtl}" styleClass="scroller"
                     rendered="#{pageBeanName.searchCertificatesListSize > 12}">
            <t:dataScroller id="scroll_1" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                            paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
                            fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage"
                            lastStyleClass="textpage" nextStyleClass="textpage" previousStyleClass="textpage"
                            paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                            paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                            styleClass="textpage" immediate="false" for="qulIntgSearchResultTbl"
                            renderFacetsIfSinglePage="false" binding="#{pageBeanName.dataScroller}"
                            actionListener="#{pageBeanName.scrollerAction}">
                <f:facet name="first">
                    <h:panelGroup id="jobs_list_panelGrp_first">
                        <t:graphicImage id="jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <h:panelGroup id="jobs_list_panelGrp_last">
                        <t:graphicImage id="jobs_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_lastOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <h:panelGroup id="jobs_list_panelGrp_prv">
                        <t:graphicImage id="jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_prvOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="jobs_list_panelGrp_nxt">
                        <t:graphicImage id="jobs_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd">
                        <t:graphicImage id="jobs_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_ffrwrdOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd">
                        <t:graphicImage id="jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_frwndOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                        <t:inputHidden forceId="true" id="pageIndexWithoutPaging" value="#{pageIndex}"
                                       valueChangeListener="#{shared_util.changePageIndex}"/>
                    </h:panelGroup>
                </f:facet>
            </t:dataScroller>
            <t:inputHidden forceId="true" id="qulpageIndex" value=""/>
        </h:panelGrid>
        <t:inputHidden forceId="true" id="qulnoOfTableRows" value="12"/>
        <t:inputHidden forceId="true" id="qularrayId" value=""/>
    </t:panelGroup>
</t:panelGroup>
<t:panelGroup id="qulIntgNavPnl" forceId="true">
    <t:commandButton id="getCertificate" action="#{pageBeanName.getCertificate}" value="#{globalResources.ok}"
                     styleClass="cssButtonSmall" disabled="#{empty pageBeanName.selectedDTOS }"/>
    <t:commandButton forceId="true" id="backButtonIntegrationDiv2" value="#{globalResources.back}"
                     onclick="hideLookUpDiv(window.blocker,window.integrationDiv2)" styleClass="cssButtonSmall"
                     action="#{pageBeanName.resetCerDivValues}"/>
</t:panelGroup>

    <%--<t:panelGroup id="hiddenBtnPnl" forceId="true">
        <t:inputHidden id="filterPnlCollapsed" forceId="true" value="#{pageBeanName.filterPnlCollapsed}" />
    </t:panelGroup>--%>

<%-- <t:inputHidden forceId="true" id="searchmode" value="#{pageBeanName.searchMode}"/>--%>
<script language="javascript" type="text/javascript">
    handleQulIntgSearchBtn();
    
    //toggleDivUsingMaxHeightVer2('hideDivImg', 'qulIntgSearchPnl', 'qulIntgSearchResultTblPnl',document.getElementById('filterPnlCollapsed').value);

  function toggleDivUsingMaxHeightVer2(togglerId, cnt1DivId, cntDivId, collapse) {
    var collapsedHeight;
    var displayedHeight;
//alert("toggleDivUsingMaxHeightVer2 collapse= "+collapse);
      if (collapse == 'true' ) {
      //alert("yes true");
          collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
          displayedHeight = document.getElementById(cntDivId).offsetHeight;
          document.getElementById(cnt1DivId).style.display = 'none';
          document.getElementById(togglerId).className = 'showDivPnl';
          document.getElementById(cntDivId).style = "max-height:"+(displayedHeight+collapsedHeight-5)+"px;";
      }
      else {
      //alert("no flase ");
          document.getElementById(cnt1DivId).style.display = 'block';
          document.getElementById(togglerId).className = 'hideDivPnl';
          //document.getElementById(cntDivId).style = "max-height:"+expandedHieght+"px;";
      }
  }  
  
    function handleQulIntgSearchBtn() {
      var qulIntgCertificateKey = document.getElementById("qulIntgCertificateKey").value;
      var qulName = document.getElementById("qulIntgCertificateName").value;
      //var qulIntgMainEduLevelCode = document.getElementById("qulIntgMainEduLevelCombo").value;
      if (qulIntgCertificateKey.length != 0 || qulName.length != 0 ) {
          document.getElementById('searchCertificatesBtn').disabled = false;
          document.getElementById('searchCertificatesBtn').className = 'ManyToManySearchButton';
      }
      else {
          document.getElementById('searchCertificatesBtn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
          document.getElementById('searchCertificatesBtn').disabled = true;
      }
  }

  function FireQulIntgSearchBtn() {
      handleQulIntgSearchBtn();
      FireButtonClick('searchCertificatesBtn');
  }

  function validate(evt) {
      var theEvent = evt || window.event;
      var key = theEvent.keyCode || theEvent.which;
      key = String.fromCharCode(key);
      var regex = /[0-9]|\./;
      if (!regex.test(key)) {
          theEvent.returnValue = false;
          if (theEvent.preventDefault)
              theEvent.preventDefault();
      }
  }
  
</script>
<%--<t:saveState value="#{pageBeanName.okButtonMethod}"/>--%>
<%--<t:saveState value="#{pageBeanName.errorMsgBind}"/>--%>
<%--<t:saveState value="#{pageBeanName.showMsg}"/>--%>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.certificateName}"/>
<t:saveState value="#{pageBeanName.certificateKey}"/>
<%--<t:saveState value="#{pageBeanName.selectedmainEduLevel}"/>--%>
<%--<t:saveState value="#{pageBeanName.mainEduLevelCode}"/>--%>
<%--<t:saveState value="#{pageBeanName.selectedLevelChild}"/>--%>
<%--<t:saveState value="#{pageBeanName.levelChildCode}"/>--%>
<%--<t:saveState value="#{pageBeanName.selectedGeneralSpecs}"/>--%>
<%--<t:saveState value="#{pageBeanName.generalSpecsCode}"/>--%>
<%--<t:saveState value="#{pageBeanName.selectedMajorSpec}"/>--%>
<%--<t:saveState value="#{pageBeanName.majorSpecCode}"/>--%>
<%--<t:saveState value="#{pageBeanName.minorSpecsCode}"/>--%>
<%--<t:saveState value="#{pageBeanName.selectedMinorSpecs}"/>--%>
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
<t:saveState value="#{pageBeanName.searchCertificatesList}"/>
<t:saveState value="#{pageBeanName.searchCertificatesListSize}"/>
