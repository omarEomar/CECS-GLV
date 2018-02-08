<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>


<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
<t:panelGroup forceId="true" id="divAddLookup" style="width:750px;">

    <t:panelGrid id="pnlgrd_reg_parent_div" columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
                <!--- Start of Row 1-->
                   <h:outputText value="#{regResources.regulation_References_reg_no}"/>
                    <t:inputText forceId="true"  id="maintain_regNumber" onkeypress="FireButtonClick('myForm:getAvailableSearchButton'); keyPressNumber(false,event);" onkeyup="disableCharacters(this)" maxlength="10" styleClass="textbox" value="#{detailBeanName.searchDto.number}" converter="javax.faces.Long"/>

                    <h:outputText value="#{regResources.regulation_References_type}"/>
                    <t:selectOneMenu forceId="true"  id="maintain_regType" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.regulationType}" converter="javax.faces.Long" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{entry.name}" itemValue="#{entry.code.regtypeCode}" var="entry"/>              
                    </t:selectOneMenu>

                    <h:outputText value="#{regResources.regulation_References_year}" />
                    <t:selectOneMenu forceId="true"  id="maintain_regIssuanceYear" styleClass="textbox" value="#{detailBeanName.searchDto.regulationYear}" converter="javax.faces.Long" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_issuance_year_label}"/>
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{entry.code.yearCode}" itemValue="#{entry.code.yearCode}" var="entry"/>
                    </t:selectOneMenu>

                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                
                     <h:outputText value="#{regResources.regulation_source}" />
                    <t:selectOneMenu forceId="true"  id="maintain_regDecisionMaker" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.decisionMakerType}" converter="javax.faces.Long" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_decision_maker_label}"/>
                        <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{entry.name}" itemValue="#{entry.code.decmkrtypeCode}" var="entry"/>
                        <f:convertNumber type="number"/>
                    </t:selectOneMenu>
                    
                    <h:outputText value="#{regResources.regulation_level}" />
                    <t:selectOneMenu forceId="true"  id="maintain_regScope" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.regulationScopes}" converter="javax.faces.Long" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_scope_label}"/>
                        <t:selectItems value="#{detailBeanName.scopes}" itemLabel="#{entry.name}" itemValue="#{entry.code.regscopeCode}" var="entry"/>
                        <f:convertNumber type="number"/>
                    </t:selectOneMenu>
                    <f:verbatim>  </f:verbatim> <f:verbatim>  </f:verbatim>
                    
                    <h:outputText value="#{regResources.regulation_date_from}"/>
                    <t:panelGroup forceId="true">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.searchDto.dateFrom}" id="clndr_maintain_regDate"
                                    size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
                                    renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_regDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_regDate_divID"/>
                    </t:panelGroup> 
                    
                <!--- End of Row 2-->
                <!--- Start of Row 3-->
                    <h:outputText value="#{regResources.regulation_date_to}"/>
                    <t:panelGroup forceId="true">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.searchDto.dateTo}" id="clndr_maintain_regAppliedDate"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_regAppliedDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_regAppliedDate_divID"/>
                        <c2:compareDateValidator componentToValidate="clndr_maintain_regDate" componentToCompare="clndr_maintain_regAppliedDate" operator="before" display="dynamic" errorMessage="#{regResources.error_message_to_date_must_be_after_from_date}" highlight="false"/>
                    </t:panelGroup>  

                
                   
                    
                    
                
                <!--- End of Row 3-->
                <!--- Start of Row 4-->
                    
                    <h:outputText value="#{regResources.regulation_References_reg_desc}" />
                    <t:panelGroup colspan="3">
                    <t:inputText forceId="true"  id="maintain_regTitle" size="20" maxlength="400" styleClass="textboxtreelarge" value="#{detailBeanName.searchDto.title}" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');" style="width:565px"/>
                   </t:panelGroup>
                <!--- End of Row 4-->                    
                    
                    
              </t:panelGrid>
              
              <t:panelGrid columns="2" align="center" id="search_buttons" forceId="true">
                    <%-- <a4j:commandButton action="#{detailBeanName.searchAvailable}" reRender="divAdds,search_buttons"  styleClass="ManyToManySearchButton" onclick="return validatemyForm();"/> --%>
                    <h:panelGroup>
                            <h:commandButton id="getAvailableSearchButton"  type="button"  onclick="doValidatemyForm=true; return validateSaveAndNewClientValidator();" styleClass="ManyToManySearchButton" >
                                <a4j:jsFunction name="saveAndNew"  action="#{detailBeanName.searchAvailable}" reRender="divAdds,search_buttons"/>
                            </h:commandButton>
                    </h:panelGroup>
                    <a4j:commandButton  action="#{detailBeanName.cancelSearchAvailable}" reRender="divAdds,search_buttons,pnlgrd_reg_parent_div"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchMode}" onclick="ignoremyFormValidation();doValidatemyForm=true;"/>
              </t:panelGrid>
              
            
            

<t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsg" />
<t:panelGroup forceId="true" id="divAdds" styleClass="regulationReferenceAddDivScroll">
    <f:verbatim>
        <div style="height: 70px; overflow: auto;" >
    </f:verbatim>
    <h:outputText styleClass="msg" rendered="#{detailBeanName.availableListSize == 0}" value="#{globalResources.global_noTableResults}"/>
                         <t:dataTable id="dataT_available" var="list" value="#{detailBeanName.availableDetails}"     
                             rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                             binding="#{detailBeanName.availableDataTable}" renderedIfEmpty="false"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header"
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" >
                        
                    <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                        <f:facet name="header">
                           <t:selectBooleanCheckbox forceId="true" id="checkAllAdd" value="#{detailBeanName.checkAllFlagAvailable}" onkeypress="FireButtonClick('myForm:ok');">
                                  <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');" reRender="selectedAvailableListSize,okBtn"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>                       
                        <t:selectBooleanCheckbox forceId="true" onkeypress="FireButtonClick('myForm:ok');" id="chk2Add" value="#{list.checked}">
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}"   oncomplete="checkCheckAllAdd('chk2Add')" reRender="selectedAvailableListSize,okBtn" />
                        </t:selectBooleanCheckbox>
                    </t:column>
                    
                    <t:column id="type_column" sortable="false" width="20%">
                        <f:facet name="header">
                            <%--t:commandSortHeader id="typeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet--%>
                                <h:outputText id="header_type" value="#{regResources.regulation_References_type}"/>
                            <%--/t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content_type" value="#{list.typesDto.name}"/>
                    </t:column>
                    
                    <t:column id="year_column" sortable="false" width="20%">
                        <f:facet name="header">
                            <%--t:commandSortHeader id="yearColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet--%>
                                <h:outputText id="header_year" value="#{regResources.regulation_References_year}"/>
                            <%--/t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_year" value="#{list.yearsDto.code.key}"/>
                    </t:column>
                    
                    <t:column id="regNo_column" sortable="false" width="20%">
                        <f:facet name="header">
                            <%--t:commandSortHeader id="regNoColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet--%>
                                <h:outputText id="header_regNo" value="#{regResources.regulation_References_reg_no}"/>
                            <%--/t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_regNo" value="#{list.code.regulationNumber}"/>
                    </t:column>
                    
                    <t:column id="desc_column" sortable="false" width="75%">
                        <f:facet name="header">
                            <%--t:commandSortHeader id="descColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet--%>
                                <h:outputText id="header_desc" value="#{regResources.regulation_References_reg_desc}"/>
                            <%--/t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_desc" value="#{list.regulationTitle}"/>
                    </t:column>
                </t:dataTable>
                <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
          <f:verbatim>
                </div>
            </f:verbatim>      
  
            <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
            
              <t:dataScroller id="scroll_1add"     actionListener="#{pageBeanName.scrollerAction}" binding="#{pageBeanName.dataScroller}"
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
                    for="dataT_available"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <h:panelGroup id="req_list_panelGrp_first">
                        <t:graphicImage id="req_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="req_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="req_list_panelGrp_last">
                            <t:graphicImage id="req_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="req_list_panelGrp_prv">
                            <t:graphicImage id="req_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="req_list_panelGrp_nxt">
                            <t:graphicImage id="req_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="req_list_panelGrp_ffrwrd">
                            <t:graphicImage id="req_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="req_list_panelGrp_frwnd">
                            <t:graphicImage id="req_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
                
              
            </t:dataScroller>
        </h:panelGrid>
        <t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
        </t:panelGroup>
          <t:panelGrid forceId="true" columns="2" align="center" id="okBtn"> 
                <h:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.selectedAvailableListSize > 0}"/>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="setFocusAtMyAddDiv();" type="button" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error'); setFocusAtMyFirstElement();" styleClass="cssButtonSmall" />

          </t:panelGrid>

                    <%-- Start css modification add two break line to UI --%>
                   <%-- End css modification add two break line to UI --%>
</t:panelGroup>
