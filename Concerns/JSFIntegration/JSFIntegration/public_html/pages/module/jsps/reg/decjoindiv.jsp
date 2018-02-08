<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
 
<f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="integrateResources"/>


<t:saveState value="#{pageBeanName.decisionIntegration.regulationSearchDTO}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.typeList}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.yearsList}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.decSourceList}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.decSubjectList}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.virtualvalue}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.searchModeDesDiv}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.descionsList}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.decisionsDTO}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.minCode}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.empCivilIdList}"/>

<t:panelGroup id="integratedecIntegratePanel" >
 <t:panelGrid columns="5" id="integrateinfoPanel" rowClasses="row02,row01" cellpadding="3" cellspacing="0" style="width:100%;">
  <!-- Row1-->
  <t:outputLabel value="#{integrateResources.type}" styleClass="lable01"/>
  
  <t:selectOneMenu forceId="true" value="#{pageBeanName.decisionIntegration.regulationSearchDTO.regulationType}"  onkeypress="FireButtonClick('myForm:integrateSaveButton');"
    id="integratetype_list" styleClass="textboxmedium" onblur="document.getElementById('integratedec_make_year').focus();">
   <f:selectItem itemLabel="#{integrateResources.select}" itemValue="#{pageBeanName.decisionIntegration.virtualvalue}"/>
   <t:selectItems value="#{pageBeanName.decisionIntegration.typeList}" itemLabel="#{type.name}" itemValue="#{type.code.regtypeCode}" var="type"/>
  </t:selectOneMenu>
  
  <t:outputLabel value="#{integrateResources.dec_make_year}" styleClass="lable01"/>
  <t:panelGroup colspan="2">
  <t:selectOneMenu forceId="true" value="#{pageBeanName.decisionIntegration.regulationSearchDTO.regulationYear}" 
    id="integratedec_make_year" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:integrateSaveButton');">
   <f:selectItem itemLabel="#{integrateResources.select}" itemValue="#{pageBeanName.decisionIntegration.virtualvalue}"/>
   <t:selectItems value="#{pageBeanName.decisionIntegration.yearsList}" itemLabel="#{year.name}" itemValue="#{year.code.yearCode}" var="year"/>
  </t:selectOneMenu>
  </t:panelGroup>
  <!-- Row2-->
  <t:outputLabel value="#{integrateResources.dec_no}" styleClass="lable01"/>
  
  <t:inputText value="#{pageBeanName.decisionIntegration.regulationSearchDTO.number}" maxlength="10" onkeyup="disableCharacters(this);" id="integratedec_no" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:integrateSaveButton');"/>
  
  <t:outputLabel value="#{integrateResources.dec_desc}" styleClass="lable01"/>
  <t:panelGroup colspan="2">
  <t:inputText value="#{pageBeanName.decisionIntegration.regulationSearchDTO.title}" id="integratedec_desc" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:integrateSaveButton');"/>
  </t:panelGroup>
  <!-- Row3                 test-->
  <t:outputLabel value="#{integrateResources.dec_source}" styleClass="lable01"/>
  
  <t:selectOneMenu forceId="true" value="#{pageBeanName.decisionIntegration.regulationSearchDTO.decisionMakerType}" 
    id="integratedec_no_list" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:integrateSaveButton');">
   <f:selectItem itemLabel="#{integrateResources.select}" itemValue="#{pageBeanName.decisionIntegration.virtualvalue}"/>
   <t:selectItems value="#{pageBeanName.decisionIntegration.decSourceList}" itemLabel="#{decSource.name}" itemValue="#{decSource.code.decmkrtypeCode}" var="decSource"/>
  </t:selectOneMenu>
  
  <t:outputLabel value="#{integrateResources.dec_subject}" styleClass="lable01"/>
  <t:panelGroup colspan="2">
  <t:selectOneMenu forceId="true" value="#{pageBeanName.decisionIntegration.regulationSearchDTO.subjectCode}" 
    id="integratedec_subject" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:integrateSaveButton');">
   <f:selectItem itemLabel="#{integrateResources.select}" itemValue="#{pageBeanName.decisionIntegration.virtualvalue}"/>
   <t:selectItems value="#{pageBeanName.decisionIntegration.decSubjectList}" itemLabel="#{decSubject.name}" itemValue="#{decSubject.code.subjectCode}" var="decSubject"/>
  </t:selectOneMenu>
  </t:panelGroup>
  <!-- Row4-->
  <t:outputLabel value="#{integrateResources.dec_date_from}" styleClass="lable01"/>
  
  <t:panelGroup styleClass="integratereq_start_date">
  <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.decisionIntegration.regulationSearchDTO.dateFrom}" popupDateFormat="dd/MM/yyyy" forceId="true" id="integratereq_start_date"
                   size="20" maxlength="200" styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                   title="#{globalResources.inputCalendarHelpText}" onkeypress="FireButtonClick('myForm:integrateSaveButton');"
                   popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
   <f:converter converterId="TimeStampConverter"/>
  </t:inputCalendar>
  <htm:br />
  <c2:dateFormatValidator group="123" active="true" highlight="false" componentToValidate="integratereq_start_date" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
  </t:panelGroup>
  
  <t:outputLabel value="#{integrateResources.dec_date_to}" styleClass="lable01"/>
  <t:panelGroup colspan="2">
  <t:panelGroup styleClass="integratereq_end_date">
  <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.decisionIntegration.regulationSearchDTO.dateTo}" popupDateFormat="dd/MM/yyyy" forceId="true" id="integratereq_end_date"
                   size="20" maxlength="200" styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                   title="#{globalResources.inputCalendarHelpText}"  onkeypress="FireButtonClick('myForm:integrateSaveButton');"
                   popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
   <f:converter converterId="TimeStampConverter"/>
  </t:inputCalendar>
  <htm:br />
   <c2:dateFormatValidator group="123" active="true" highlight="false" componentToValidate="integratereq_end_date" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
  </t:panelGroup>
  <t:panelGroup colspan="4"  style="width:100%; text-align:center; opacity: 0; position: absolute; display: none;">
  <c2:compareDateValidator group="123" componentToValidate="integratereq_start_date" componentToCompare="integratereq_end_date" operator="before" display="dynamic" errorMessage="#{globalResources.error_message_to_date_must_be_after_from_date}" active="true"/>
 </t:panelGroup>
 </t:panelGroup>
 </t:panelGrid>
 <t:panelGrid columns="3" border="0" forceId="true" id="searchbtnPanel" align="center">

  <t:commandButton styleClass="cssButtonSmall" id="integrateSaveButton" value="#{globalResources.SearchButton}" type="button" onclick="if(validatemyForm('123')){doSearch();}"/>
  <a4j:jsFunction name="doSearch" reRender="noResultPnlGroup,searchbtnPanel,integratescrollerPanel,integratedivAdds" actionListener="#{pageBeanName.decisionIntegration.searchDecisionIntegrate}"/>
  <a4j:commandButton value="#{globalResources.cancelsearch}" disabled="#{!pageBeanName.decisionIntegration.searchModeDesDiv}" styleClass="cssButtonSmall" reRender="searchbtnPanel,integrateokbutton,noResultPnlGroup,integratedivAdds,integrateinfoPanel,integratescrollerPanel" actionListener="#{pageBeanName.decisionIntegration.cancelSearchDecisionIntegrate}"/>

 </t:panelGrid>
 
 
  <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
  
 <t:panelGroup forceId="true" id="integratedivAdds" style="width:100%; height:95px;display: block;overflow: auto;">
    <t:panelGroup forceId="true" id="noResultPnlGroup">
  <t:panelGrid rendered="#{empty pageBeanName.decisionIntegration.descionsList}" styleClass="msg" align="center" style="height:auto;padding-right:0px">
   <t:outputText value="#{globalResources.global_noTableResults}"/>
  </t:panelGrid>
  </t:panelGroup>
  <t:dataTable  id="integratedataT_decision" var="declist" value="#{pageBeanName.decisionIntegration.descionsList}" binding="#{pageBeanName.decisionIntegration.descionsTable}" rows="4" rowIndexVar="index" renderedIfEmpty="false"
               rowOnMouseOver="javascript:addRowHighlight('myForm:integratedataT_decision');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
               rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
               dir="#{shared_util.pageDirection}" preserveSort="true">
   <t:column sortable="false" width="4%">
    <f:facet name="headerradioBtnChecked">
     <t:outputText value=""/>
    </f:facet>
    <t:selectOneRadio onmousedown="toggleRadio(this);" id="integratejob_radio_btn" onkeypress="FireButtonClick('integrateokbutton');"
            value="" 
            onkeyup="toggleRadioKeyUpEnableBtn(this,'integrateokbutton');">
     <f:selectItem itemLabel="" itemValue="#{declist.code.key}"/>
     <a4j:support event="onclick" action="#{pageBeanName.decisionIntegration.radioCheckedDecisionChanged}" oncomplete="document.getElementById('integrateokbutton').disabled=false;"/>
    </t:selectOneRadio>
   </t:column>
   <t:column id="integratecode_column" sortable="false" width="12%">
    <f:facet name="header">
     <t:outputText id="integrateheader_code" value="#{integrateResources.type}" />
    </f:facet>
    <t:outputText id="integratecontent_code" value="#{declist.typesDTO.name}"/>
   </t:column>
   <t:column id="integratename_column" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name" value="#{integrateResources.dec_make_year}" />
    </f:facet>
    <t:outputText id="integratecontent_name" value="#{declist.yearsDTO.name}"/>
   </t:column>
   <t:column id="integratename_column3" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name4" value="#{integrateResources.dec_no}" />
    </f:facet>
    <t:outputText id="integratecontent_name4" value="#{declist.code.keys[2]}"/>
   </t:column>
   <t:column id="integratename_column6" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name6" value="#{integrateResources.dec_desc}" />
    </f:facet>
    <t:outputText id="integratecontent_name5" value="#{declist.decisionTitle}"/>
   </t:column>
   <t:column id="integratename_column7" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name7" value="#{integrateResources.dec_source}" />
    </f:facet>
    <t:outputText id="integratecontent_name7" value="#{declist.decisionMakerTypesDTO.name}"/>
   </t:column>
   <t:column id="integratename_column8" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name8" value="#{integrateResources.dec_date_from_date}" />
    </f:facet>
    <t:outputText id="integratecontent_name8" value="#{declist.decisionDate}" converter="TimeStampConverter"/>
   </t:column>
   <t:column id="integratename_column9" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name9" value="#{integrateResources.applied_date}" />
    </f:facet>
    <t:outputText id="integratecontent_name9" value="#{declist.decisionAppliedDate}" converter="TimeStampConverter"/>
   </t:column>
      <t:column id="integratename_column10" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name10" value="#{integrateResources.dec_subject}" />
    </f:facet>
    <t:outputText id="integratecontent_name10" value="#{declist.subjectsDTO.name}"/>
   </t:column>
   
   <t:column id="integratename_column11" sortable="false" width="11%">
    <f:facet name="header">
     <t:outputText id="integrateheader_name11" value="#{integrateResources.decision_text}" />
    </f:facet>
    <t:commandButton id="integratecontent_name11" value="..." type="button" styleClass="cssButtonSmall" style="min-width: 5px !important;">
        <a4j:support event="onclick" action="#{pageBeanName.decisionIntegration.getCurrentDecText}" reRender="decisionText , winNameId" oncomplete="showDoc('decisionText' , 'winNameId');"/>
    </t:commandButton>
   </t:column>
  </t:dataTable>
 </t:panelGroup>
 </t:panelGrid>
 <t:panelGrid columns="1" forceId="true" id="integratescrollerPanel">
  <t:panelGrid id="integratepanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.decisionIntegration.descionsListSize > 3}">
          <t:dataScroller id="integratescroll_1add" actionListener="#{pageBeanName.decisionIntegration.scrollerAction}"  
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
                    for="integratedataT_decision"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <t:panelGroup id="integratereq_list_panelGrp_first">
                        <t:graphicImage id="integratereq_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="integratereq_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <t:panelGroup id="integratereq_list_panelGrp_last">
                            <t:graphicImage id="integratereq_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="integratereq_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <t:panelGroup id="integratereq_list_panelGrp_prv">
                            <t:graphicImage id="integratereq_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="integratereq_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <t:panelGroup id="integratereq_list_panelGrp_nxt">
                            <t:graphicImage id="integratereq_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="integratereq_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <t:panelGroup id="integratereq_list_panelGrp_ffrwrd">
                            <t:graphicImage id="integratereq_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="integratereq_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <t:panelGroup id="integratereq_list_panelGrp_frwnd">
                            <t:graphicImage id="integratereq_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="integratereq_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
            </t:dataScroller>
        </t:panelGrid>

</t:panelGrid>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton styleClass="cssButtonSmall" disabled="true" forceId="true" id="integrateokbutton" value="#{globalResources.ok}" action="#{pageBeanName.decisionIntegration.saveSelectedDecision}"/>
        <t:commandButton id="BackButtonMasterDetailDiv" forceId="true" onblur="settingFoucsAtDecJoinDiv();" value="#{globalResources.back}" action="#{pageBeanName.decisionIntegration.backFromDecision}" styleClass="cssButtonSmall" onclick="hideLookUpDiv(window.blocker,window.masterDetailDiv,null,null,null);"/>
    </t:panelGrid>
</t:panelGroup>
<t:inputHidden value="#{pageBeanName.decisionIntegration.decisionText}" id="decisionText" forceId="true"/>
<t:outputText value="#{integrateResources.decision_text}" id="winNameId" forceId="true" style="display:none;"/>
<script>
function disableCharacters(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g,"");
    }
}


</script>
