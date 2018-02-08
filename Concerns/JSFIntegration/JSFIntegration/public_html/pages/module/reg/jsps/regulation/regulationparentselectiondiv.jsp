<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<t:panelGroup forceId="true" id="regulationParentSelectionDiv" style="width:750px;">

              
              <t:panelGrid id="div_pnlgrd_reg_parent_div" columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" border="0" cellspacing="0">
                <!--- Start of Row 1-->
                  
                  <h:outputText value="#{regResources.regulation_number}" />
                    <t:inputText forceId="true"  id="div_maintain_regNumber" onkeypress="keyPressNumber(false,event);FireButtonClick('getAvailableSearchButton');" onkeyup="disableCharacters(this)" converter="javax.faces.Long" maxlength="10" styleClass="textbox" value="#{detailBeanName.searchDto.number}"/>
                    
                    <h:outputText value="#{regResources.type}"/>
                    <t:selectOneMenu forceId="true" id="div_maintain_regType" converter="javax.faces.Long" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.regulationType}" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{entry.name}" itemValue="#{entry.code.regtypeCode}" var="entry"/>
                    </t:selectOneMenu>
                    
                    <h:outputText value="#{regResources.issuance_year}" />
                    <t:selectOneMenu forceId="true" id="div_maintain_regIssuanceYear" converter="javax.faces.Long" styleClass="textbox" value="#{detailBeanName.searchDto.regulationYear}" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_issuance_year_label}"/>
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{entry.code.yearCode}" itemValue="#{entry.code.yearCode}" var="entry"/>
                    </t:selectOneMenu>
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                 
                
                    <h:outputText value="#{regResources.regulation_decision_maker}" />
                    <t:selectOneMenu forceId="true"  id="div_maintain_regDecisionMaker" converter="javax.faces.Long" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.decisionMakerType}" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_decision_maker_label}"/>
                        <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{entry.name}" itemValue="#{entry.code.decmkrtypeCode}" var="entry"/>
                    </t:selectOneMenu>
                    
            <h:outputText value="#{regResources.regulation_scope}" />
                <t:panelGroup colspan="3">
                    <t:selectOneMenu forceId="true"  id="div_maintain_regScope" converter="javax.faces.Long" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.regulationScopes}" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_scope_label}"/>
                        <t:selectItems value="#{detailBeanName.scopes}" itemLabel="#{entry.name}" itemValue="#{entry.code.regscopeCode}" var="entry"/>
                    </t:selectOneMenu>
                </t:panelGroup>
                
                    
                    <h:outputText value="#{regResources.regulation_date_from}"/>
                    <t:panelGroup forceId="true">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.searchDto.dateFrom}" id="div_clndr_maintain_regDate"
                                    size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
                                    renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                        <f:verbatim>
                        <br/>
                        </f:verbatim>
                        <c2:dateFormatValidator componentToValidate="div_clndr_maintain_regDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="div_clndr_maintain_regDateID"/>
                    </t:panelGroup>  

                <!--- End of Row 2-->
                <!--- Start of Row 3-->
                
                    <h:outputText value="#{regResources.regulation_date_to}"/>
                    <t:panelGroup forceId="true">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.searchDto.dateTo}" id="div_clndr_maintain_regAppliedDate"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                        <f:verbatim>
                        <br/>
                        </f:verbatim>
                        <c2:dateFormatValidator componentToValidate="div_clndr_maintain_regAppliedDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="div_clndr_maintain_regAppliedDateID"/>
                        <c2:compareDateValidator componentToValidate="div_clndr_maintain_regDate" componentToCompare="div_clndr_maintain_regAppliedDate" operator="before" display="dynamic" errorMessage="#{regResources.error_message_to_date_must_be_after_from_date}" highlight="false"/>
                    </t:panelGroup>
                    
                
                    

                   
                <!--- End of Row 3-->
                <!--- Start of Row 4-->
                    
                    <h:outputText value="#{regResources.regulation_description}"/>
                    <t:panelGroup colspan="3">
                       <t:inputText forceId="true"  id="div_maintain_regTitle" maxlength="400" styleClass="textboxtreelarge" value="#{detailBeanName.searchDto.title}" onkeypress="FireButtonClick('myForm:getAvailableSearchButton');" style="width:565px"/>
                    </t:panelGroup>
                <!--- End of Row 4-->

              </t:panelGrid>
              <t:panelGrid id="searchBtns_pnlgrp" align="center" columns="2">
                <a4j:commandButton id="getAvailableSearchButton" action="#{detailBeanName.searchAvailable}" onclick="ignoremyFormValidation()" oncomplete="doValidatemyForm=true;" reRender="searchBtns_pnlgrp,divAdds,btn_pnlgrp" styleClass="ManyToManySearchButton"/>

                <a4j:commandButton action="#{detailBeanName.cancelSearchAvailable}" onclick="ignoremyFormValidation()" oncomplete="doValidatemyForm=true;" rendered="#{detailBeanName.searchMode}" reRender="searchBtns_pnlgrp,divAdds,btn_pnlgrp" styleClass="ManyToManyCancelSearchButton"/>
             

              </t:panelGrid>
            
            
 
<t:panelGroup forceId="true" id="divAdds" styleClass="regulationReferenceAddDivScroll">
    <f:verbatim>
        <div style="height: 60px; overflow: auto;" >
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
                        
                    <t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                        <f:facet name="header"></f:facet>                        
                        <t:selectOneRadio id="radioButton" value="#{list.checked}" onkeyup="toggleRadioKeyUp(this);" onmousedown="toggleRadio(this);">
                            <f:selectItem  itemLabel="" itemValue="#{true}"/>
                            <f:converter converterId="javax.faces.Boolean" />
                            <a4j:support event="onclick" actionListener="#{detailBeanName.checkData}" oncomplete="document.getElementById('SaveButton').disabled=false;"  reRender="dataT_available,regulationParentSelectionDiv"/>
                        </t:selectOneRadio> 
                        
        
                        
                    </t:column>
                    
                    <t:column id="type_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_type" value="#{regResources.regulation_References_type}"/>
                            <%--t:commandSortHeader id="typeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_type" value="#{regResources.regulation_References_type}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content_type" value="#{list.typesDto.name}"/>
                    </t:column>
                    
                    <t:column id="year_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_year" value="#{regResources.regulation_References_year}"/>
                            
                        </f:facet>
                        <h:outputText id="content_year" value="#{list.yearsDto.code.key}"/>
                    </t:column>
                    
                    <t:column id="regNo_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_regNo" value="#{regResources.regulation_References_reg_no}"/>
                           
                        </f:facet>
                        <h:outputText id="content_regNo" value="#{list.code.regulationNumber}"/>
                    </t:column>
                    
                    <t:column id="desc_column" width="75%">
                        <f:facet name="header">
                            <h:outputText id="header_desc" value="#{regResources.regulation_References_reg_desc}"/>
                          
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
                    for="dataT_available"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <h:panelGroup id="jobs_list_panelGrp_firstadd">
                        <t:graphicImage id="jobs_list_img_firstOnadd"
                                                rendered="#{pageIndexAdd > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOffadd"
                                                onclick="return false;"
                                                rendered="#{pageIndexAdd <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="jobs_list_panelGrp_lastadd">
                            <t:graphicImage id="jobs_list_img_lastOnadd"
                                            rendered="#{pageIndexAdd < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_lastOffadd"
                                            onclick="return false;"
                                            rendered="#{pageIndexAdd >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="jobs_list_panelGrp_prvadd">
                            <t:graphicImage id="jobs_list_img_prvOnadd"
                                            rendered="#{pageIndexAdd > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_prvOffadd"
                                            onclick="return false;"
                                            rendered="#{pageIndexAdd <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="jobs_list_panelGrp_nxtadd">
                            <t:graphicImage id="jobs_list_img_nxtOnadd"
                                            rendered="#{pageIndexAdd < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_nxtOffadd"
                                            rendered="#{pageIndexAdd >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewindadd">
                    <h:panelGroup id="jobs_list_panelGrp_frwndadd">
                            <t:graphicImage id="jobs_list_img_frwndOnadd"
                                            rendered="#{pageIndexAdd > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_frwndOffadd"
                                            onclick="return false;"
                                            rendered="#{pageIndexAdd <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
             
                    </h:panelGroup>
                    
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrdadd">
                            <t:graphicImage id="jobs_list_img_ffrwrdOnadd"
                                            rendered="flase"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOffadd"
                                            onclick="return false;"
                                            rendered="false"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                
              
            </t:dataScroller>
        
      
        </h:panelGrid>
        <t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
        </t:panelGroup>                

                    
                <t:panelGroup id="btn_pnlgrp" forceId="true">
                    <t:commandButton id="SaveButton" forceId="true" disabled="#{detailBeanName.disableSaveButton==false}" value="#{globalResources.ok}" action="#{detailBeanName.setSelectedRegParent}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.availableListSize > 0}" /> <f:verbatim>&nbsp; </f:verbatim>
                    <t:commandButton id="backButtonAddDiv" forceId="true"  type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" onblur="setFocusOnlyOnElement('div_maintain_regNumber'); "/>
                </t:panelGroup>
                  
</t:panelGroup>
