<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<%-- ##################START################# Strat of Join Condition Section #################SHEKA############### --%>
<%--htm:center>
    <t:outputText value="#{regIntgResources.joinDecisionDivTitle}" styleClass="TitlePage"/>
    <f:verbatim>
        <br/>
    </f:verbatim>
</htm:center--%>
<t:panelGroup forceId="true" id="decIntgHideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingMaxHeight('decIntgHideDivImg', 'decIntgSearchPnl', 'decIntgSearchResultTblPnl')"/>
</t:panelGroup>

<t:panelGroup forceId="true" id="decIntgSearchPnl">
    <t:panelGroup forceId="true" id="decIntgSearchCriteriaPnl">
        <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
        
            <t:outputLabel value="#{regIntgResources.type}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="decIntgSearchRegulationType" styleClass="textbox"
                    value="#{jdHelperBeanName.searchDTO.regulationType}" onchange="handleDecIntgSearchBtn();"
                    onblur="setFocusOnlyOnElement('decIntgSearchRegulationYear');"
                    onkeypress="FireDecIntgSearchBtn();" converter="javax.faces.Long">
                <f:selectItem itemLabel="#{regIntgResources.select}" itemValue="#{jdHelperBeanName.virtualLongValue}"/>
                <t:selectItems value="#{jdHelperBeanName.typeList}" var="item" itemLabel="#{item.name}" itemValue="#{item.code.regtypeCode}" />
            </t:selectOneMenu>
            
            <t:outputLabel value="#{regIntgResources.reg_year}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="decIntgSearchRegulationYear" styleClass="textbox"
                    value="#{jdHelperBeanName.searchDTO.regulationYear}" onchange="handleDecIntgSearchBtn();"
                    onkeypress="FireDecIntgSearchBtn();" converter="javax.faces.Long">
                <f:selectItem itemLabel="#{regIntgResources.select}" itemValue="#{jdHelperBeanName.virtualLongValue}"/>
                <t:selectItems value="#{jdHelperBeanName.yearsList}" var="item" itemLabel="#{item.code.yearCode}" itemValue="#{item.code.yearCode}" />
            </t:selectOneMenu>
            
            <t:outputLabel value="#{regIntgResources.dec_number}" styleClass="lable01"/>
            <t:inputText forceId="true" id="decIntgSearchNumber" styleClass="textbox" maxlength="14"
                    value="#{jdHelperBeanName.searchDTO.number}"
                    onkeypress="FireDecIntgSearchBtn();" 
                    onkeyup="handleDecIntgSearchBtn();"/>
            
            <t:outputLabel value="#{regIntgResources.decision_description}" styleClass="lable01"/>
            <t:inputText forceId="true" id="decIntgSearchTitle" styleClass="textbox"
                    value="#{jdHelperBeanName.searchDTO.title}"
                    onkeypress="FireDecIntgSearchBtn();"
                    onkeyup="trimBorders(this);handleDecIntgSearchBtn();"/>
                    
            <t:outputLabel value="#{regIntgResources.decisions_decision_maker}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="decIntgSearchRegulationMakerType" styleClass="textbox"
                    value="#{jdHelperBeanName.searchDTO.decisionMakerType}" onchange="handleDecIntgSearchBtn();"
                    onkeypress="FireDecIntgSearchBtn();" converter="javax.faces.Long">
                <f:selectItem itemLabel="#{regIntgResources.select}" itemValue="#{jdHelperBeanName.virtualLongValue}"/>
                <t:selectItems value="#{jdHelperBeanName.decSourceList}" var="item" itemLabel="#{item.name}" itemValue="#{item.code.decmkrtypeCode}" />
            </t:selectOneMenu>
            
            <t:outputLabel value="#{regIntgResources.dec_subject}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="decIntgSearchRegulationSubject" styleClass="textbox"
                    value="#{jdHelperBeanName.searchDTO.subjectCode}" onchange="handleDecIntgSearchBtn();"
                    onkeypress="FireDecIntgSearchBtn();" converter="javax.faces.Long">
                <f:selectItem itemLabel="#{regIntgResources.select}" itemValue="#{jdHelperBeanName.virtualLongValue}"/>
                <t:selectItems value="#{jdHelperBeanName.decSubjectList}" var="item" itemLabel="#{item.name}" itemValue="#{item.code.subjectCode}" />
            </t:selectOneMenu>
            
            <t:outputLabel value="#{regIntgResources.dec_date_from}" styleClass="lable01"/>
            <t:panelGroup styleClass="integratereq_start_date">
                <t:inputCalendar forceId="true" id="decIntgSearchDateFrom" title="#{globalResources.inputCalendarHelpText}" 
                        popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                        popupDateFormat="dd/MM/yyyy" size="20" maxlength="10" 
                        styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" 
                        align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                        renderPopupButtonAsImage="true" onchange="handleDecIntgSearchBtn();"
                        value="#{jdHelperBeanName.searchDTO.dateFrom}" onkeypress="FireDecIntgSearchBtn();">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                <htm:br />
                <c:dateFormatValidator group="decIntg" active="true" highlight="false" componentToValidate="decIntgSearchDateFrom" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
            </t:panelGroup>
            
            <t:outputLabel value="#{regIntgResources.dec_date_to}" styleClass="lable01"/>
            <t:panelGroup styleClass="integratereq_start_date">
                <t:inputCalendar forceId="true" id="decIntgSearchDateTo" title="#{globalResources.inputCalendarHelpText}" 
                        popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                        popupDateFormat="dd/MM/yyyy" size="20" maxlength="10" 
                        styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" 
                        align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                        renderPopupButtonAsImage="true" onchange="handleDecIntgSearchBtn();"
                        value="#{jdHelperBeanName.searchDTO.dateTo}" onkeypress="FireDecIntgSearchBtn();">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                <htm:br />
                <c:dateFormatValidator group="decIntg" active="true" highlight="false" componentToValidate="decIntgSearchDateTo" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
                <c:compareDateValidator group="decIntg" componentToValidate="decIntgSearchDateFrom" componentToCompare="decIntgSearchDateTo" operator="before" display="dynamic" errorMessage="#{globalResources.error_message_to_date_must_be_after_from_date}" active="true"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center" border="0" forceId="true" id="decIntgSearchBtnsPnl">
        <t:commandButton forceId="true" id="decIntgSearchCondBtn" value="" styleClass="ManyToManySearchButton" onclick="if(validatemyForm('decIntg')){decIntgSearchFunction();}" type="button">
            <a4j:jsFunction name="decIntgSearchFunction" action="#{jdHelperBeanName.search}" reRender="decIntgSearchBtnsPnl, decIntgSearchResultPnl"
                    oncomplete="document.getElementById('decIntgSearchCondBtn').focus();"/>
        </t:commandButton>
    
        <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton" rendered="#{jdHelperBeanName.searchMode}"
                action="#{jdHelperBeanName.cancelSearch}" reRender="decIntgSearchPnl, decIntgSearchResultPnl"
                oncomplete="handleDecIntgSearchBtn();"/>
    </t:panelGrid>
    
</t:panelGroup>
<t:panelGroup forceId="true" id="decIntgSearchResultPnl">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="decIntgSearchResultTblPnl" styleClass="fullWidthScroll110">
            <t:dataTable id="decIntgSearchResultTbl" var="list" value="#{jdHelperBeanName.myTableData}"
                    forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                    renderedIfEmpty="false" binding="#{jdHelperBeanName.myDataTable}"
                    rowOnMouseOver="javascript:addRowHighlight('myForm:decIntgSearchResultTbl');addRowHighlight('treeform:decIntgSearchResultTbl');" 
                    styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                    rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                    width="100%" align="top" dir="#{shared_util.pageDirection}">
                    
                <t:column id="check_column" styleClass="standardTable_Header2 noText" width="5%">
                    <h:selectOneRadio styleClass="radioButton_DataTable"
                                      onkeypress="FireDecIntgSaveBtn();"
                                      onclick="toggleRadio(this); decIntgUpdateSelectedDecision('#{list.code.key}');">
                        <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                    </h:selectOneRadio>
                </t:column>
                <t:column id="type_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_type" value="#{regIntgResources.type}"/>
                    </f:facet>
                    <h:outputText id="content_type" value="#{list.typesDTO.name}"/>
                </t:column>
                <t:column id="year_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_year" value="#{regIntgResources.reg_year}"/>
                    </f:facet>
                    <h:outputText id="content_year" value="#{list.yearsDTO.code.key}"/>
                </t:column>
                <t:column id="number_column" sortable="false" width="15%">
                    <f:facet name="header">
                        <h:outputText id="header_number" value="#{regIntgResources.dec_number}"/>
                    </f:facet>
                    <h:outputText id="content_number" value="#{list.code.decisionNumber}"/>
                </t:column>
                <t:column id="title_column" sortable="false" width="45%">
                    <f:facet name="header">
                        <h:outputText id="header_title" value="#{regIntgResources.decision_description}"/>
                    </f:facet>
                    <h:outputText id="content_title" value="#{list.decisionTitle}"/>
                </t:column>
                <%--t:column id="source_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_source" value="#{regIntgResources.dec_source}"/>
                    </f:facet>
                    <h:outputText id="content_source" value="#{list.decisionMakerTypesDTO.name}"/>
                </t:column--%>
                <t:column id="dateFrom_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_dateFrom" value="#{regIntgResources.decision_date}"/>
                    </f:facet>
                    <h:outputText id="content_dateFrom" value="#{list.decisionDate}" converter="TimeStampConverter"/>
                </t:column>
                <%--t:column id="applyDate_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_applyDate" value="#{regIntgResources.applied_date}"/>
                    </f:facet>
                    <h:outputText id="content_applyDate" value="#{list.decisionAppliedDate}" converter="TimeStampConverter"/>
                </t:column>
                <t:column id="subject_column" sortable="false" width="15%">
                    <f:facet name="header">
                        <h:outputText id="header_subject" value="#{regIntgResources.dec_subject}"/>
                    </f:facet>
                    <h:outputText id="content_subject" value="#{list.subjectsDTO.name}"/>
                </t:column--%>
                <t:column id="decDetails" sortable="false" width="5%">
                    <f:facet name="header">
                        <h:outputText id="header_details" value="#{regIntgResources.details}"/>
                    </f:facet>
                    <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="#{globalResources.Available}"
                            actionListener="#{jdHelperBeanName.viewDecisionDetails}" 
                            reRender="decIntgHiddenValues" oncomplete="openRegIntgDecisionDetailsWindow();">
                         <f:attribute name="decisionKey" value="#{list.code.key}"/>
                    </a4j:commandButton>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ jdHelperBeanName.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid id="decIntgSearchResultScrollPnl" columns="1" dir="#{shared_util.pageDirection}"
                 styleClass="scroller" width="300px" rendered="#{jdHelperBeanName.listSize > shared_util.noOfTableRows}">
        <t:dataScroller id="decIntgSearchResultScroll"
                        actionListener="#{jdHelperBeanName.scrollerAction}" fastStep="5"
                        pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                        paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                        firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                        previousStyleClass="textpage" paginatorColumnClass="textpage"
                        paginatorActiveColumnClass="paging"
                        paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                        styleClass="textpage" immediate="false" for="decIntgSearchResultTbl" renderFacetsIfSinglePage="false">
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
<t:inputHidden forceId="true" id="selectedDecisionCode" value="#{jdHelperBeanName.selectedDecisionCode}"/>
<f:verbatim>
    
<script type="text/javascript">
    handleDecIntgSearchBtn();
    function handleDecIntgSearchBtn(){
        document.getElementById('decIntgSearchCondBtn').disabled=true;
        document.getElementById('decIntgSearchCondBtn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
        
        var type = document.getElementById("decIntgSearchRegulationType").value;
        var year = document.getElementById("decIntgSearchRegulationYear").value;
        var decNo = document.getElementById("decIntgSearchNumber").value;
        var title = document.getElementById("decIntgSearchTitle").value;
        var makerType = document.getElementById("decIntgSearchRegulationMakerType").value;
        var subject = document.getElementById("decIntgSearchRegulationSubject").value;
        var dateFrom = document.getElementById("decIntgSearchDateFrom").value;
        var dateTo = document.getElementById("decIntgSearchDateTo").value;
        
        if(type != '-100' || year !='-100' || decNo.length != 0 ||
           title.length != 0 || makerType !='-100' || subject !='-100' || 
           dateFrom.length != 0 || dateTo.length != 0){
           
            document.getElementById('decIntgSearchCondBtn').disabled=false;
            document.getElementById('decIntgSearchCondBtn').className = 'ManyToManySearchButton';
        }
    }
    
    function FireDecIntgSearchBtn() {
        handleDecIntgSearchBtn();
        FireButtonClick('decIntgSearchCondBtn');
    }
    
    function decIntgUpdateSelectedDecision(selectedCode){
        document.getElementById('selectedDecisionCode').value = selectedCode;
        
        document.getElementById('decIntgJoinSaveBtn').disabled=false;
        document.getElementById('decIntgJoinSaveBtn').focus();
        document.getElementById('decIntgJoinSaveBtn').focus();
    }
    
    function FireDecIntgSaveBtn(){
        FireButtonClick('decIntgJoinSaveBtn');
    }
    
    var decIntgDivHidden = false;

    function decIntgToggleDiv() {
        if (decIntgDivHidden) {
              document.getElementById('decIntgSearchPnl').style.display = 'block';
              document.getElementById('decIntgSearchResultTblPnl').className = 'fullWidthScroll110';
              decIntgDivHidden = false;
              document.getElementById('decIntgHideDivImg').className = 'hideDivPnl';
          }
          else {
              document.getElementById('decIntgSearchPnl').style.display = 'none';
              document.getElementById('decIntgSearchResultTblPnl').className = 'fullWidthScroll250';
              decIntgDivHidden = true;
              document.getElementById('decIntgHideDivImg').className = 'showDivPnl';
          }
      }
</script>
</f:verbatim>
