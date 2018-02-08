<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<tiles:importAttribute scope="request"/>

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

<t:panelGroup id="searchArea" forceId="true">
    
    <t:panelGrid columns="4" width="95%" cellpadding="3" rowClasses="row01,row02"  cellspacing="0" onkeypress="FireButtonClick('myForm:searchActionBtn');">
        
        <t:outputLabel value="#{regResources.dec_number}"  styleClass="lable01"/>
    <t:inputText  styleClass="textbox" maxlength="10" value="#{decisionListBean.decisionSearchDTO.number}" onkeyup="disableCharacters(this)" converter="javax.faces.Long"  disabled="#{pageBeanName.searchedForDec}"/>
    
    <t:outputLabel value="#{regResources.type}"  styleClass="lable01"/>

    <t:selectOneMenu id="regulationTypeID" value="#{decisionListBean.decisionSearchDTO.regulationType}"  styleClass="DropdownboxMedium2" forceId="true" disabled="#{pageBeanName.searchedForDec}">
        <f:selectItem itemLabel="#{regResources.select_type}" itemValue="" />
        <t:selectItems var="type" value="#{decisionListBean.typesList}" itemLabel="#{type.name}" itemValue="#{type.code.regtypeCode}"/>
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.reg_year}"  styleClass="lable01" />

    <t:selectOneMenu id="YesrSearch" value="#{decisionListBean.decisionSearchDTO.regulationYear}"  styleClass="Dropdownbox" disabled="#{pageBeanName.searchedForDec}">
        <f:selectItem itemLabel="#{regResources.select_year}" itemValue="" />
        <t:selectItems var="year" value="#{decisionListBean.yearsList}" itemLabel="#{year.code.key}" itemValue="#{year.code.yearCode}"/>
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.dec_decision_maker}"  styleClass="lable01"/>

<t:selectOneMenu id="DecisionMakerSearch" value="#{decisionListBean.decisionSearchDTO.decisionMakerType}"  styleClass="DropdownboxMedium2" disabled="#{pageBeanName.searchedForDec}">
        <f:selectItem itemLabel="#{regResources.select_decision_maker}" itemValue="" />
        <t:selectItems var="decisionMaker" value="#{decisionListBean.decisionMakersList}" itemLabel="#{decisionMaker.name}" itemValue="#{decisionMaker.code.decmkrtypeCode}"/>
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.status}"  styleClass="lable01"/>

    <t:selectOneMenu id="StatusSearch" value="#{decisionListBean.decisionSearchDTO.decisionStatus}" styleClass="Dropdownbox" disabled="#{pageBeanName.searchedForDec}">
        <f:selectItem itemLabel="#{regResources.select_status}" itemValue="" />
        <f:selectItem itemLabel="#{regResources.decision_status_text_valid}" itemValue="#{decisionListBean.statusValidCode}"/>
        <f:selectItem itemLabel="#{regResources.decision_status_text_canceled}" itemValue="#{decisionListBean.statusCanceledCode}"/>
        <f:selectItem itemLabel="#{regResources.decision_status_text_cancel}" itemValue="#{decisionListBean.statusCancelDecisionCode}"/>                        
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.decision_subject}"  styleClass="lable01"/>
    <t:selectOneMenu id="SubjectSearch" value="#{decisionListBean.decisionSearchDTO.subjectCode}"  styleClass="DropdownboxMedium2" disabled="#{pageBeanName.searchedForDec}">
        <f:selectItem itemLabel="#{regResources.select_decision_subject}" itemValue="" />
        <t:selectItems var="subject" value="#{decisionListBean.subjectsList}" itemLabel="#{subject.name}" itemValue="#{subject.code.subjectCode}"/>
    </t:selectOneMenu>


<t:outputLabel value="#{regResources.decision_date_from}"  styleClass="lable01"/>

    <t:panelGroup>
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/app/media/images/icon_calendar.jpg" disabled="#{pageBeanName.searchedForDec}"
                popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                forceId="true"
                id="search_regDateFrom_dec" 
                maxlength="10"
                currentDayCellClass="currentDayCell"
                value="#{decisionListBean.decisionSearchDTO.dateFrom}"
                renderAsPopup="true"
                align="#{globalResources.align}"
                popupLeft="#{shared_util.calendarpopupLeft}"
                renderPopupButtonAsImage="true">
            
            <f:converter converterId="TimeStampConverter"/>
        
        </t:inputCalendar>
        <f:verbatim> <br/> </f:verbatim>
        <%--c2:dateFormatValidator componentToValidate="search_regDateFrom_dec"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/--%>
    </t:panelGroup>

    <t:outputLabel value="#{regResources.regulation_date_to}"  styleClass="lable01"/>

    <t:panelGroup>
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/app/media/images/icon_calendar.jpg" disabled="#{pageBeanName.searchedForDec}"
                popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                forceId="true"
                id="search_regDateTo_dec" 
                maxlength="10"
                currentDayCellClass="currentDayCell"
                value="#{decisionListBean.decisionSearchDTO.dateTo}"
                renderAsPopup="true"
                align="#{globalResources.align}"
                popupLeft="#{shared_util.calendarpopupLeft}"
                renderPopupButtonAsImage="true">
        
            <f:converter converterId="TimeStampConverter"/>
        
        </t:inputCalendar>
        <f:verbatim><br/></f:verbatim>
        <%--c2:dateFormatValidator componentToValidate="search_regDateTo_dec"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
        
        <c2:compareDateValidator componentToValidate="search_regDateFrom_dec" 
            componentToCompare="search_regDateTo_dec" 
            operator="before" display="dynamic" highlight="false"
            errorMessage="#{regResources.dec_from_to_date}" /--%>
    </t:panelGroup>

   
    
    
    <t:outputLabel value="#{regResources.dec_title}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
            <t:inputText maxlength="400" value="#{decisionListBean.decisionSearchDTO.title}"  styleClass="textboxDecisionSearch2" disabled="#{pageBeanName.searchedForDec}"/>
    </t:panelGroup>
        
        <t:panelGroup colspan="4">
            <t:panelGrid forceId="true" id="searchGrd" align="center" dir="#{shared_util.pageDirection}" style="height:20px;">
                <a4j:commandButton id="searchActionBtn" value="#{globalResources.SearchButton}" action="#{pageBeanName.search}" styleClass="cssButtonSmall" reRender="searchArea" rendered="#{!pageBeanName.searchedForDec}"/>
                <a4j:commandButton value="#{globalResources.cancelsearch}" action="#{pageBeanName.resetSearch}" styleClass="cssButtonSmall" reRender="searchArea" rendered="#{pageBeanName.searchedForDec}"/>
            </t:panelGrid>
        </t:panelGroup>

    </t:panelGrid>
    
    <f:verbatim><br/></f:verbatim>
    
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" style="text-align:center">
        
        <t:panelGroup forceId="true" id="lov_dataT_data_panel" styleClass="relatedMaterialsSearchDivScroll"  style="width:95%">
            
            <t:dataTable forceId="true" id="lov_dataT_data" var="list" value="#{pageBeanName.lovBaseBean.myTableData}" rowStyleClass="#{ pageBeanName.lovBaseBean.selected ? 'standardTable_RowHighlighted' : null}"
                forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.lovBaseBean.myDataTable}"
                rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.lovBaseBean.sortColumn}" sortAscending="#{pageBeanName.lovBaseBean.ascending}">
         
                <t:column id="single_select_column" styleClass="standardTable_Header3" width="5%" rendered="#{!pageBeanName.lovBaseBean.multiSelect}">
                    <f:facet name="header">
                    </f:facet>
                    <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.lovBaseBean.selectedRadio}" onkeyup="toggleRadioKeyUp(this);" onmousedown="toggleRadio(this);">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedRadioButton}" oncomplete="if(document.getElementById('myForm')!=null){document.getElementById('myForm:lov_ok').focus();document.getElementById('myForm:lov_ok').focus()}else{document.getElementById('treeForm:lov_ok').focus();document.getElementById('treeForm:lov_ok').focus()};" reRender="lovDiv_btnsPnlGrd"/>
                    </t:selectOneRadio>
                </t:column>
                
                <%--t:column id="multi_select_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.lovBaseBean.multiSelect}">
                    <f:facet name="header">
                    </f:facet>
                    <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                        <a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedCheckboxs}"
                            oncomplete="checkCheckAll('chk');"
                            reRender="lovDiv_btnsPnlGrd"/>
                    </t:selectBooleanCheckbox>
                </t:column--%>
                
                <t:column  sortable="false"  width="15%">
                    <f:facet name="header">
                        <t:commandLink id="sort_type" forceId="true" styleClass="headerLink" value="#{regResources.type}" onclick="return false;">                         
                        </t:commandLink>
                    </f:facet>
                    <t:outputText id="content_type" forceId="true" value="#{list.typesDTO.name}"/>
                </t:column>
                <t:column  sortable="false"  width="15%">
                    <f:facet name="header">
                        <t:commandLink id="sort_issuance_year" forceId="true" styleClass="headerLink" value="#{regResources.issuance_year}" onclick="return false;">                         
                        </t:commandLink>
                    </f:facet>
                    <t:outputText id="content_issuance_year" forceId="true" value="#{list.yearsDTO.code.key}"/>
                </t:column>
                <t:column  sortable="false"  width="15%">
                    <f:facet name="header">
                        <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{regResources.dec_number}" onclick="return false;">                         
                        </t:commandLink>
                    </f:facet>
                    <t:outputText id="content_code" forceId="true" value="#{list.code.keys[2]}"/>
                </t:column>
                
                <t:column  sortable="false" width="50%">
                    <f:facet name="header">
                        <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" onclick="return false;">
                        </t:commandLink>                   
                    </f:facet>
                    <t:outputText id="content_name" value="#{list.decisionTitle}"/>
                </t:column>
            </t:dataTable>
            
            <t:panelGrid columns="1" rendered="#{empty pageBeanName.lovBaseBean.myTableData && pageBeanName.searchedForDec}" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
          
        </t:panelGroup>
        
    </t:panelGrid>
    
    <t:panelGroup forceId="true" id="lov_paging_panel">
        <t:panelGrid id="lov_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.lovBaseBean.listSize > shared_util.noOfTableRows}" >
            
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
                    actionListener="#{pageBeanName.reOpenSearchDiv}">
                <f:facet name="first" >                            
                    <t:panelGroup id="lov_jobs_list_panelGrp_first">
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
                    </t:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <t:panelGroup id="lov_jobs_list_panelGrp_last">
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
                    </t:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <t:panelGroup id="lov_jobs_list_panelGrp_prv">
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
                    </t:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <t:panelGroup id="lov_jobs_list_panelGrp_nxt">
                            <t:graphicImage id="lov_jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <t:panelGroup id="lov_jobs_list_panelGrp_ffrwrd">
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
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <t:panelGroup id="lov_jobs_list_panelGrp_frwnd">
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
                          
                    </t:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </t:panelGrid>
        
    </t:panelGroup>
    
    <%-- *********************** Start Buttons ****************************  --%>
    <t:panelGrid forceId="true" id="lovDiv_btnsPnlGrd" columns="3" align="center" dir="#{shared_util.pageDirection}">
        <a4j:commandButton id="lov_ok" value="#{globalResources.ok}" action="#{pageBeanName.loadMaterials}" styleClass="cssButtonSmall" oncomplete="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.divSearch,null,null);" reRender="dataTableGrp, relatedDataGrd, addMatBtn, addAllMatBtn" disabled="#{empty pageBeanName.lovBaseBean.selectedDTOS}"/>
        <t:panelGroup>
            <t:commandButton forceId="true" id="customSearchBackBtn" onblur="setFocusAtMySearchDiv();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" oncomplete="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.divSearch,null,null);" reRender="divSearch"/>
        </t:panelGroup>

    </t:panelGrid>
    <%-- ************************* End Buttons ****************************  --%>
    
</t:panelGroup>