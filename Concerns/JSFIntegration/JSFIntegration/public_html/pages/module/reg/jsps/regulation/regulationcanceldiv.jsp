<%@ page session="false" contentType="text/html;charset=utf-8"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<t:panelGroup forceId="true" id="divCancelingReg" styleClass="delDivScroll">
    
    <t:dataTable id="dataT_data_canceling_reg" var="list" 
                 value="#{regulationListBean.selectedDTOS}"
                 forceIdIndexFormula="#{list.code.key}" 
                 rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                 renderedIfEmpty="false"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data_canceling_reg');"
                 footerClass="grid-footer" styleClass="grid-footer"
                 headerClass="standardTable_Header" 
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                 width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" 
                 sortColumn="#{regulationListBean.sortColumn}" sortAscending="#{regulationListBean.ascending}">
        
        <t:column id="type_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_type" value="#{regResources.reg_type}"/>
                <%--t:commandSortHeader id="typeColumn_div" columnName="typeColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_type" value="#{regResources.reg_type}"/>
                </t:commandSortHeader--%>
            </f:facet>
           <h:outputText id="reg_type" value="#{list.typesDto.name}"/>
        </t:column>
        
        <t:column id="year_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_reg_year" value="#{regResources.reg_year}"/>
                <%--t:commandSortHeader id="yearColumn_div" columnName="yearColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_reg_year" value="#{regResources.reg_year}"/>
                </t:commandSortHeader--%>
            </f:facet>
            <h:outputText id="reg_year" value="#{list.yearsDto.code.key}"/>
        </t:column>
        
        <t:column id="number_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_reg_number" value="#{regResources.reg_number}"/>
                <%--t:commandSortHeader id="numberColumn_div" columnName="numberColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_reg_number" value="#{regResources.reg_number}"/>
                </t:commandSortHeader--%>
            </f:facet>
           <h:outputText id="reg_number" value="#{list.code.regulationNumber}"/>
        </t:column>
        
        <t:column id="title_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_reg_title" value="#{regResources.reg_title}"/>
                <%--t:commandSortHeader id="titleColumn_div" columnName="titleColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_reg_title" value="#{regResources.reg_title}"/>
                </t:commandSortHeader--%>
            </f:facet>
           <h:outputText id="reg_title" value="#{list.regulationTitle}"/>
        </t:column>
        
        <t:column id="decision_maker_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_decision_maker" value="#{regResources.cancel_decision_maker}"/>
                <%--t:commandSortHeader id="decision_makerColumn_div" columnName="decision_makerColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_decision_maker" value="#{regResources.cancel_decision_maker}"/>
                </t:commandSortHeader--%>
            </f:facet>
            <t:selectOneMenu forceId="true" id="DecisionMakerSelect" value="#{list.cancelMakerDTOKey}"  styleClass="textboxmedium">
                <f:selectItem itemLabel="#{regResources.select_decision_maker}" itemValue="" />
                <t:selectItems var="decisionMaker" value="#{regulationListBean.decisionMakersList}" itemLabel="#{decisionMaker.name}" itemValue="#{decisionMaker.code.key}"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:column>
        
        <t:column id="cancel_reason_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_cancel_reason" value="#{regResources.cancellation_reason}"/>
                <%--t:commandSortHeader id="cancel_reasonColumn_div" columnName="cancel_reasonColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_cancel_reason" value="#{regResources.cancellation_reason}"/>
                </t:commandSortHeader--%>
            </f:facet>
            <t:selectOneMenu forceId="true" id="CancelReasonsSelect" value="#{list.cancelReasonDTOKey}"  styleClass="textboxmedium">
                <f:selectItem itemLabel="#{regResources.select_cancel_reason}" itemValue="" />
                <t:selectItems var="cancelReason" value="#{regulationListBean.cancelReasonsList}" itemLabel="#{cancelReason.name}" itemValue="#{cancelReason.code.key}"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:column>
        
        <t:column id="cancel_date_column" width="10%">
            <f:facet name="header">
                <h:outputText id="header_cancel_date" value="#{regResources.cancel_applied_date}"/>
                <%--t:commandSortHeader id="cancel_dateColumn_div" columnName="cancel_dateColumn_sort_div" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>  onchange="markDirty();"
                    <h:outputText id="header_cancel_date" value="#{regResources.cancel_applied_date}"/>
                </t:commandSortHeader--%>
            </f:facet>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/images/icon_calendar.jpg" 
                    popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                    forceId="true"
                    id="regCancelAppliedDate" 
                    size="20"
                    maxlength="#{pageBeanName.calendarTextLength}"
                    currentDayCellClass="currentDayCell"
                    value="#{list.regulationCancelAppliedDate}"
                    renderAsPopup="true"
                    align="#{globalResources.align}"
                    popupLeft="#{shared_util.calendarpopupLeft}"
                    renderPopupButtonAsImage="true">
                
                <f:converter converterId="TimeStampConverter"/>
            
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:dateFormatValidator componentToValidate="regCancelAppliedDate"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
            <%--
            <c2:requiredFieldValidator componentToValidate="regCancelAppliedDate" 
                display="dynamic" errorMessage="#{globalResources.missingField}" 
                highlight="false" uniqueId="regCancelAppliedDateID"/>
            --%>
        </t:column>
        
    </t:dataTable>
    
    <h:panelGrid id="panelGrd_scrollerCancelReg" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
                  
        <t:dataScroller id="scroll_1add"     
            actionListener="#{regulationListBean.scrollerAction}"
            fastStep="5" pageCountVar="pageCount" 
            pageIndexVar="pageIndexAdd"
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
            for="dataT_data_canceling_reg"
            renderFacetsIfSinglePage="false">
            <f:facet name="first" >                            
                <h:panelGroup id="jobs_list_panelGrp_firstadd">
                    <t:graphicImage id="jobs_list_img_firstOnadd"
                                            rendered="#{pageIndexAdd > 1}"
                                            title="#{globalResources.scroller_first}"
                                            url="/images/#{globalResources.photoFolder}/back3.jpg"
                                            border="0"/>
                    <t:graphicImage id="jobs_list_img_firstOffadd"
                                            onclick="return false;"
                                            rendered="#{pageIndexAdd <= 1}"
                                            url="/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                            border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="last">                            
                <h:panelGroup id="jobs_list_panelGrp_lastadd">
                        <t:graphicImage id="jobs_list_img_lastOnadd"
                                        rendered="#{pageIndexAdd < pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/images/#{globalResources.photoFolder}/next3.jpg"
                                        border="0"/>
                        <t:graphicImage id="jobs_list_img_lastOffadd"
                                        onclick="return false;"
                                        rendered="#{pageIndexAdd >= pageCount}"
                                        url="/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="previous">                            
                <h:panelGroup id="jobs_list_panelGrp_prvadd">
                        <t:graphicImage id="jobs_list_img_prvOnadd"
                                        rendered="#{pageIndexAdd > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/images/#{globalResources.photoFolder}/back1.jpg"
                                        border="0"/>
                        <t:graphicImage id="jobs_list_img_prvOffadd"
                                        onclick="return false;"
                                        rendered="#{pageIndexAdd <= 1}"
                                        url="/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="next">                            
                <h:panelGroup id="jobs_list_panelGrp_nxtadd">
                        <t:graphicImage id="jobs_list_img_nxtOnadd"
                                        rendered="#{pageIndexAdd < pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/images/#{globalResources.photoFolder}/next1.jpg"
                                        border="0"/>
                        <t:graphicImage id="jobs_list_img_nxtOffadd"
                                        rendered="#{pageIndexAdd >= pageCount}"
                                        url="/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="fastforward">
                <h:panelGroup id="jobs_list_panelGrp_ffrwrdadd">
                        <t:graphicImage id="jobs_list_img_ffrwrdOnadd"
                                        rendered="#{pageIndexAdd < pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/images/#{globalResources.photoFolder}/next2.jpg"
                                        border="0"/>
                        <t:graphicImage id="jobs_list_img_ffrwrdOffadd"
                                        onclick="return false;"
                                        rendered="#{pageIndexAdd >= pageCount}"
                                        url="/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="fastrewindadd">
                <h:panelGroup id="jobs_list_panelGrp_frwndadd">
                        <t:graphicImage id="jobs_list_img_frwndOnadd"
                                        rendered="#{pageIndexAdd > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/images/#{globalResources.photoFolder}/back2.jpg"
                                        border="0"/>
                        <t:graphicImage id="jobs_list_img_frwndOffadd"
                                        onclick="return false;"
                                        rendered="#{pageIndexAdd <= 1}"
                                        url="/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
         
                </h:panelGroup>
                
            </f:facet>
          
        </t:dataScroller>
            
    </h:panelGrid>
    
</t:panelGroup>

<t:panelGrid columns="2" align="center">
    <h:commandButton id="ok_canceling_reg" value="#{globalResources.ok}" onclick="ignoremyFormValidation();" action="#{regulationListBean.cancelRegulation}" styleClass="cssButtonSmall"/>
    <h:commandButton id="cancel_canceling_reg" type="button" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.customDiv1,null,null);" styleClass="cssButtonSmall"/>
</t:panelGrid>

