<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>


<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
<t:panelGroup forceId="true" id="divAddLookup">
<f:verbatim>
<table>
<tr>
<td align="center">
     <table border="0" width="600px" id="table5" cellspacing="0" cellpadding="0"></f:verbatim><f:verbatim>
        <tr>
            <td valign="bottom" width="19"></f:verbatim><f:verbatim>
                  <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td></f:verbatim><f:verbatim>
                 <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td></f:verbatim><f:verbatim>
                  <td valign="bottom" width="12"></f:verbatim><f:verbatim>
                  <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                 </tr>
                 </f:verbatim>
                 <f:verbatim>
            <tr>
               <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td></f:verbatim><f:verbatim>
                <td valign="top" bgcolor="#FFFFFF" class="paddingbox">               
    </f:verbatim>     
    <t:panelGrid id="pnlgrd_decision_ref_div" forceId="true" columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
                <!--- Start of Row 0 , now in row 3 after removing the status-->
                    <h:outputText value="#{regResources.regulation_References_reg_no}"/>
                    <t:inputText forceId="true" id="maintain_decision_number_div" onkeypress="javascript : FireButtonClick('myForm:searchButton'); keyPressNumber(false,event); "  onkeyup="disableCharacters(this)" maxlength="10" styleClass="textbox" value="#{detailBeanName.searchDto.number}" converter="javax.faces.Long" />
                    <h:outputText value="#{regResources.type}"/>
                    <t:selectOneMenu forceId="true"  id="maintain_regType_div" onkeypress="FireButtonClick('myForm:searchButton');" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.regulationType}" converter="javax.faces.Long">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{entry.name}" itemValue="#{entry.code.regtypeCode}" var="entry"/>              
                    </t:selectOneMenu>
                <!--- End of Row 0-->
                <!--- Start of Row 1-->
                    <h:outputText value="#{regResources.issuance_year}" />
                    <t:selectOneMenu forceId="true" onkeypress="FireButtonClick('myForm:searchButton');" id="maintain_regIssuanceYear_div" styleClass="Dropdownbox" value="#{detailBeanName.searchDto.regulationYear}" converter="javax.faces.Long">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_issuance_year_label}"/>
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{entry.code.key}" itemValue="#{entry.code.yearCode}" var="entry"/>
                    </t:selectOneMenu>
                    
                    <h:outputText value="#{regResources.regulation_decision_maker}"/>
                    <t:selectOneMenu onkeypress="FireButtonClick('myForm:searchButton');" forceId="true"  id="maintain_decision_maker_div" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.decisionMakerType}" converter="javax.faces.Long">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_decision_maker_label}"/>
                        <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{entry.name}" itemValue="#{entry.code.decmkrtypeCode}" var="entry"/>
                        <f:convertNumber type="number"/>
                    </t:selectOneMenu>
                    
                     
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    
                    <h:outputText value="#{regResources.regulation_date_from}"/>
                    <t:panelGroup forceId="true">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.searchDto.dateFrom}" id="decision_ref_from_date_div"
                                    size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
                                    renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="decision_ref_from_date_div" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="decision_ref_from_date_divID"/>
                    </t:panelGroup>
                    
                    <h:outputText value="#{regResources.regulation_date_to}"/>
                    <t:panelGroup forceId="true">
                        <t:inputCalendar  title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.searchDto.dateTo}" id="decision_ref_to_date_div"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="decision_ref_to_date_div" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="decision_ref_to_date_divID"/>
                        <c2:compareDateValidator componentToValidate="decision_ref_from_date_div" componentToCompare="decision_ref_to_date_div" operator="before" display="dynamic" errorMessage="#{regResources.error_message_to_date_must_be_after_from_date}" highlight="false"/>
                    </t:panelGroup>
                    
                    
                <!--- End of Row 2-->
                    
                    <%--h:outputText value="#{regResources.status}"/>
                    <t:selectOneMenu forceId="true" id="maintain_status_div" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.regulationStatus}" converter="javax.faces.Long">
                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.regulation_status_label}"/>
                        <t:selectItems value="#{detailBeanName.status}" itemLabel="#{status.name}" itemValue="#{status.code.regstatusCode}" var="status"/>
                    </t:selectOneMenu--%>
                
                <!--- End of Row 3-->

                    
                    
              </t:panelGrid>
              <t:panelGrid id="searchBtns_pnlgrp" columns="2" align="center">
                <%--a4j:commandButton action="#{detailBeanName.searchAvailable}" onclick="return validatemyForm()" reRender="searchBtns_pnlgrp,divAdds,btn_pnlgrp" styleClass="ManyToManySearchButton"/--%>
                <h:panelGroup>
                    <h:commandButton  id="searchButton" type="button"  onclick="return validateSaveAndNewClientValidator();doValidatemyForm=true;" styleClass="ManyToManySearchButton" >
                    <a4j:jsFunction name="saveAndNew" action="#{detailBeanName.searchAvailable}" reRender="searchBtns_pnlgrp,divAdds,dataScrollar_id,btn_pnlgrp"/>
                    </h:commandButton>
                </h:panelGroup>
                <a4j:commandButton action="#{detailBeanName.cancelSearchAvailable}" onclick="ignoremyFormValidation();doValidatemyForm=true;" rendered="#{detailBeanName.searchMode}" reRender="pnlgrd_decision_ref_div,searchBtns_pnlgrp,divAdds,dataScrollar_id,btn_pnlgrp" styleClass="ManyToManyCancelSearchButton"/>
                <%--h:panelGroup>
                    <h:commandButton  type="button"  disabled="#{!detailBeanName.searchMode}"  onclick="return validateSaveAndNewClientValidator();" styleClass="ManyToManyCancelSearchButton">
                    <a4j:jsFunction name="cancelSearch" action="#{detailBeanName.cancelSearchAvailable}" reRender="searchBtns_pnlgrp,divAdds,btn_pnlgrp"/>
                    </h:commandButton>
                </h:panelGroup--%>
              </t:panelGrid>
              
            <f:verbatim>
             </td>
               <td valign="top" style="background-repeat: repeat-y" class="gleftbox"></td>
             </tr>
            </f:verbatim>
            <f:verbatim>
             <tr>
                  <td valign="top" width="19"></f:verbatim><f:verbatim>
                    <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td></f:verbatim><f:verbatim>
                       <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td></f:verbatim><f:verbatim>
                                    <td valign="top"></f:verbatim><f:verbatim>
                                          <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td></f:verbatim><f:verbatim>
                                                        </tr>
                                                   </table>
                                               </td>
            </tr>
     </f:verbatim>
            
 <f:verbatim>
 </table>
 </f:verbatim>
<t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsg" />
<t:panelGroup forceId="true" id="divAdds" styleClass="descionCancelDivScroll">
<f:verbatim>
        <div style="height: 85px; overflow: auto;" >
    </f:verbatim>
    <h:outputText id="no_data_found" styleClass="msg" rendered="#{detailBeanName.availableListSize == 0}" value="#{globalResources.global_noTableResults}"/>    
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
                           <t:selectBooleanCheckbox onkeypress="FireButtonClick('myForm:ok');" forceId="true" id="checkAllAdd" value="#{detailBeanName.checkAllFlagAvailable}">
                                  <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');" reRender="selectedAvailableListSize,btn_pnlgrp"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>                    
                        <t:selectBooleanCheckbox onkeypress="FireButtonClick('myForm:ok');" forceId="true" id="chk2Add" value="#{list.checked}">
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add')" reRender="selectedAvailableListSize,btn_pnlgrp" />
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
                                <h:outputText id="header_type" value="#{regResources.type}"/>
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
                                <h:outputText id="header_year" value="#{regResources.issuance_year}"/>
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
                                <h:outputText id="header_regNo" value="#{regResources.regulation_number}"/>
                            <%--/t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_decisionNo" value="#{list.code.regulationNumber}"/>
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
                                <h:outputText id="header_desc" value="#{regResources.regulation_description}"/>
                            <%--/t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_desc" value="#{list.regulationTitle}"/>
                    </t:column>
                </t:dataTable>
                <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
     <f:verbatim>
                </div>
            </f:verbatim>  
     
     <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px"  rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" ><%-- rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}"><%-- shared_util.noOfTableRows}"--%>        

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
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrdadd">
                            <t:graphicImage id="jobs_list_img_ffrwrdOnadd"
                                            rendered="#{pageIndexAdd < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOffadd"
                                            onclick="return false;"
                                            rendered="#{pageIndexAdd >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
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

            </t:dataScroller>
        </h:panelGrid>
      <t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}"/><%--shared_util.noOfTableRows}" /--%>
     </t:panelGroup>    
      <h:panelGrid columns="2" id="btn_pnlgrp">
        <h:commandButton id="ok" disabled="#{detailBeanName.selectedAvailableListSize < 1}" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.selectedAvailableListSize > 0}"/><%--detailBeanName.availableListSize > 0}" /--%>
        <%-- modifed by m.elsaied change request
        <h:commandButton id="cancel" rendered="true" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" />
         --%>
        <t:commandButton action="#{detailBeanName.cancelSearchAvailable}" onblur="setFocusAtMyAddDiv();" forceId="true" id="backButtonAddDiv"  value="#{globalResources.back}"  styleClass="cssButtonSmall" onclick="block();"/>
      </h:panelGrid>
</t:panelGroup>
