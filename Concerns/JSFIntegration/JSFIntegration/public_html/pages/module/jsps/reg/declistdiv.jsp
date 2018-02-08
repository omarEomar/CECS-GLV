<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="integrateResources"/>
<t:saveState value="#{pageBeanName.decisionIntegration.descionsList}"/>
<t:saveState value="#{pageBeanName.decisionIntegration.tabrecSerial}"/>

<t:panelGroup id="integrateDecListPanel">

    <t:panelGroup forceId="true" id="integrateDecListdataT_noResultPnlGroup">
        <t:panelGrid rendered="#{empty pageBeanName.decisionIntegration.descionsList}" styleClass="msg" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}"/>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
        <t:panelGroup forceId="true" id="integrateDecListAdds" styleClass="delDivScroll" style="width:100%">

            <t:dataTable  id="integrateDecListdataT_decision" var="declist" value="#{pageBeanName.decisionIntegration.descionsList}" binding="#{pageBeanName.decisionIntegration.viewDescionsTable}" rows="5" rowIndexVar="index" renderedIfEmpty="false"
               rowOnMouseOver="javascript:addRowHighlight('myForm:integrateDecListdataT_decision');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
               rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
               dir="#{shared_util.pageDirection}" preserveSort="true">
                <t:column id="integratecode_column" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_code" value="#{integrateResources.type}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_code" value="#{declist.typesDTO.name}"/>
                </t:column>
                <t:column id="integratename_column" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name" value="#{integrateResources.dec_make_year}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name" value="#{declist.yearsDTO.name}"/>
                </t:column>
                <t:column id="integratename_column3" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name4" value="#{integrateResources.dec_no}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name4" value="#{declist.code.keys[2]}"/>
                </t:column>
                <t:column id="integratename_column6" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name6" value="#{integrateResources.dec_desc}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name5" value="#{declist.decisionTitle}"/>
                </t:column>
                <t:column id="integratename_column7" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name7" value="#{integrateResources.dec_source}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name7" value="#{declist.decisionMakerTypesDTO.name}"/>
                </t:column>
                <t:column id="integratename_column8" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name8" value="#{integrateResources.dec_date_from_date}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name7" value="#{declist.decisionDate}" converter="TimeStampConverter"/>
                </t:column>
                <t:column id="integratename_column9" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name9" value="#{integrateResources.applied_date}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name7" value="#{declist.decisionAppliedDate}" converter="TimeStampConverter"/>
                </t:column>
                  <t:column id="integratename_column10" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateDecListHeader_name10" value="#{integrateResources.dec_subject}" styleClass="standardTable_Header2"/>
                </f:facet>
                <t:outputText id="integrateDecListContent_name10" value="#{declist.subjectsDTO.name}"/>
                </t:column>
                 <t:column id="integratename_column11" sortable="false" width="11%">
                <f:facet name="header">
                 <t:outputText id="integrateheader_name11" value="#{integrateResources.decision_text}" />
                </f:facet>
                <t:commandButton id="integratecontent_name11" value="..." type="button" styleClass="cssButtonSmall" style="min-width: 5px !important;">
                    <a4j:support event="onclick" action="#{pageBeanName.decisionIntegration.getCurrentDecTextView}" reRender="decisionText1 , winNameId1" oncomplete="showDoc('decisionText1' , 'winNameId1');"/>
                </t:commandButton>
               </t:column>
            </t:dataTable>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="1" forceId="true" id="integrateDecListscrollerPanel">
        <t:panelGrid id="integrateDecListpanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.decisionIntegration.descionsListSize > 5}">
          <t:dataScroller id="integrateDecListscroll_1add" actionListener="#{pageBeanName.decisionIntegration.decisionsListScrollerAction}"  
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
                    for="integrateDecListdataT_decision"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <t:panelGroup id="integrateDecList_list_panelGrp_first">
                        <t:graphicImage id="integrateDecList_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="integrateDecList_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <t:panelGroup id="integrateDecList_list_panelGrp_last">
                            <t:graphicImage id="integrateDecList_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="integrateDecList_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <t:panelGroup id="integrateDecList_list_panelGrp_prv">
                            <t:graphicImage id="integrateDecList_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="integrateDecList_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <t:panelGroup id="integrateDecList_list_panelGrp_nxt">
                            <t:graphicImage id="integrateDecList_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="integrateDecList_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <t:panelGroup id="integrateDecList_list_panelGrp_ffrwrd">
                            <t:graphicImage id="integrateDecList_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="integrateDecList_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <t:panelGroup id="integrateDecList_list_panelGrp_frwnd">
                            <t:graphicImage id="integrateDecList_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="integrateDecList_list_img_frwndOff"
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
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonCustomDiv2" 
            type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"/><%--onblur="settingFoucsAtRelatedDecListDiv();" --%>
            <a4j:support  event="onclick" oncomplete="hideLookUpDiv(window.blocker,window.customDiv2)" reRender="customDiv2"/>
            <%--action="#{pageBeanName.decisionIntegration.hideRelatedDecisionsDiv}"--%>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>

<t:inputHidden value="#{pageBeanName.decisionIntegration.decisionText}" id="decisionText1" forceId="true"/>
<t:outputText value="#{integrateResources.decision_text}" id="winNameId1" forceId="true" style="display:none;"/>