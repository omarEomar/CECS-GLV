<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.divContent1Dynamic{ max-height: 475px !important; }</htm:style>
<%-- <f:verbatim>--%>
<!--<div align="center">
      -->
<%-- </f:verbatim>--%>
<%-- <h:outputText id="msg" value="{mappedDataBean.msg}" rendered="false" styleClass="Msg"/>--%>
<!--br></br-->
<%-- </div--%>
<t:panelGrid id="titleMsg" forceId="true" columns="2" border="0" width="100%" align="center"
             style="text-align: center;">
    <h:outputLabel style="font-family: arial; font-weight: bold; font-size: 14px; color: rgb(0, 0, 0);"
                   value="#{mappedDataBean.mapping_title}"/>
    <%-- a4j:status startStyleClass="progress" /--%>
</t:panelGrid>
<table width="100%" cellpadding="0" border="0" cellspacing="0" class="MapTbl">
    <tr class="row01">
        <td align="right">
            <h:outputLabel value="#{mapResources.mapped_object_name}" styleClass="lable01"/>
        </td>
        <td>
            <!-- add filter text -->
            <t:inputText forceId="true" id="objectTypeText" styleClass="textboxsmall"
                         style="width:35px; margin-left:4px;"
                         onkeypress=" filterationInputOnKeyPress(event,this,'objectType','errorCodeId1',objectTypeChange,'soc_from');"
                         onblur=" filterationInputOnBlur(event,this,'objectType','errorCodeId1',objectTypeChange,'soc_from');"
                         onkeyup="enabelIntegerOnly(this);"></t:inputText>
             
            <t:selectOneMenu forceId="true" id="objectType" binding="#{mappedDataBean.objectTypeElement}"
                             value="#{mappedDataBean.selectedObjectTypeId}"
                             valueChangeListener="#{mappedDataBean.populatePageComponents}"
                             styleClass="DropdownboxMedium"
                            onchange="filterationDropboxOnChange(event,this,'objectTypeText','errorCodeId1',objectTypeChange);">
                <%-- f:convertNumber type="number"/--%>
                <t:selectItems id="object_type_items" itemLabel="#{object_type.name}"
                               itemValue="#{object_type.code.key}" var="object_type"
                               value="#{mappedDataBean.objectType}"/>
                <a4j:jsFunction name="objectTypeChange"
                                reRender="soc_from,soc_to,data_tableRendering,second_dataT_group,btn_continer,btnSearch,titleMsg,totalNo,firstRelationType_pnl,secondRelationType_pnl,mapped_filter"/>
            </t:selectOneMenu>
             
            <!-- search Button -->
            <%--<t:commandButton type="button" onclick="goSearchLov();" id="okLoveButton" styleClass="cssButtonSmall" value="...">
                <a4j:jsFunction name="goSearchLov"  action="#{pageBeanName.showSearchListOfValuesDiv}"
                                reRender="lovDivContainer"
                                oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
            </t:commandButton>--%>
            <t:outputLabel id="errorCodeId1" value="#{mapResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none"/>
        </td>
        <td>
            <h:outputLabel value="نوع العرض" styleClass="lable01"/>
        </td>
        <td>
            <t:selectOneMenu forceId="true" id="mapped_filter" binding="#{mappedDataBean.mappedTypeElement}"
                             value="#{mappedDataBean.mappedFilter}" styleClass="DropdownboxMedium">
                <%-- f:convertNumber type="number"/--%>
                <f:selectItem id="select1" itemLabel="الكل" itemValue="-100"/>
                <f:selectItem id="select2" itemLabel="المتعادلة فقط" itemValue="1"/>
                <f:selectItem id="select3" itemLabel="غير المتعادلة فقط" itemValue="0"/>
                <a4j:support event="onchange" action="#{mappedDataBean.filterByMapped}"
                             reRender="soc_from,soc_to,data_tableRendering,second_dataT_group,btn_continer,btnSearch,titleMsg,totalNo,firstRelationType_pnl,secondRelationType_pnl"/>
            </t:selectOneMenu>
        </td>
    </tr>
     
    <tr class="row02">
        <td>
            <h:outputLabel value="#{mapResources.soc_from}" styleClass="lable01"/>
        </td>
        <td>
            <h:selectOneMenu id="soc_from" binding="#{mappedDataBean.socFromControl}"
                             value="#{mappedDataBean.selectedSocityFrom}" style="width: 159px;" required="false"
                             styleClass="DropdownboxMedium"
                             valueChangeListener="#{mappedDataBean.populatePageComponents}"
                             disabled="#{mappedDataBean.controlsDisabled}">
                <%-- f:selectItem itemValue="#{mappedDataBean.ALL_MENUS_DEFAULT_VALUE}"
                     itemLabel="#{mapResources.selectItem}" /--%>
                <t:selectItems itemLabel="#{soc_from.name}" itemValue="#{soc_from.code.key}" var="soc_from"
                               value="#{mappedDataBean.soc_from}"/>
                <a4j:support event="onchange"
                             reRender="soc_to,data_tableRendering,second_dataT_group,btn_continer,btnSearch,btn_holder,titleMsg,totalNo,firstRelationType_pnl,secondRelationType_pnl"/>
            </h:selectOneMenu>
             
            <t:panelGroup id="btnSearch" forceId="true" rendered="true">
                <t:commandButton value="#{mapResources.Search}" action="#{mappedDataBean.disableCtrls}" rendered="false"
                                 binding="#{mappedDataBean.searchHTMLBtn}" forceId="true" id="searchButton"
                                 type="button" styleClass="cssButtonSmall"
                                 onclick="javascript:changeVisibilityDiv(window.blocker,window.divSearch);setFocusAtMySearchText();">
                    <a4j:support reRender=" btn_continer,second_dataT_group"/>
                </t:commandButton>
                <%-- a4j:commandButton binding="#{mappedDataBean.searchHTMLBtn}" action="#{mappedDataBean.disableCtrls}"
                     rendered="false" type="button" styleClass="MapSearchButton" value=""
                     onclick="javascript:changeVisibilityDiv(window.blocker,window.divSearch);" reRender="
                     btn_continer,second_dataT_group" /--%>
                <t:commandButton onclick="cancelSearchButtonJs();" value="#{mapResources.cancelSearch}" rendered="false"
                                 binding="#{mappedDataBean.mapCancelSearchButton}" forceId="true"
                                 id="cancelSearchButton" type="button" styleClass="cssButtonSmall"/>
                <a4j:jsFunction name="cancelSearchButtonJs"
                                reRender="data_tableRendering,second_dataT_group,btnSearch, btn_continer, titleMsg,totalNo"
                                action="#{mappedDataBean.cancelSearchAvailable}"/>
                <%-- a4j:commandButton binding="#{mappedDataBean.mapCancelSearchButton}"
                     action="#{mappedDataBean.cancelSearchAvailable}" rendered="false"
                     reRender="data_tableRendering,second_dataT_group,btnSearch" value=""
                     styleClass="MapCancelSearchButton" /--%>
            </t:panelGroup>
        </td>
        <td>
            <h:outputLabel value="#{mapResources.soc_to}" styleClass="lable01"/>
        </td>
        <td>
            <t:selectOneMenu id="soc_to" styleClass="DropdownboxMedium" value="#{mappedDataBean.selectedSocity2Id}"
                             forceId="true" valueChangeListener="#{mappedDataBean.populatePageComponents}"
                             binding="#{mappedDataBean.socToControl}">
                <f:selectItem itemValue="#{mappedDataBean.ALL_MENUS_DEFAULT_VALUE}"
                              itemLabel="#{mapResources.selectItem}"/>
                <t:selectItems itemLabel="#{soc_to.name}" itemValue="#{soc_to.code.key}" var="soc_to"
                               value="#{mappedDataBean.soc_to}"/>
                <a4j:support event="onchange"
                             reRender="data_tableRendering,second_dataT_group,btn_continer,btnSearch,btn_holder,titleMsg,totalNo,firstRelationType_pnl,secondRelationType_pnl,addButtonPnl"/>
            </t:selectOneMenu>
        </td>
    </tr>
     
    <tr class="row01">
        <td colspan="2">
            <t:panelGroup id="firstRelationType_pnl" forceId="true"
                          style="width:100%; text-align:center; display: block;">
                <h:outputLabel id="firstRelationType" value="#{mapResources[mappedDataBean.firstRelationType]}"
                               rendered="#{mappedDataBean.firstRelationType!=''}" styleClass="lable01"
                               style="color: green; font-family: arial; font-weight: bold; font-size: 14px;"/>
            </t:panelGroup>
        </td>
        <td colspan="2">
            <t:panelGroup id="secondRelationType_pnl" forceId="true"
                          style="width:100%; text-align:center; display: block;">
                <h:outputLabel id="secondRelationType" value="#{mapResources[mappedDataBean.secondRelationType]}"
                               styleClass="lable01"
                               style="color: green; font-family: arial; font-weight: bold; font-size: 14px;"
                               rendered="#{mappedDataBean.secondRelationType!=''}"/>
                <h:outputText id="relationType" value="#{mappedDataBean.relationTypeCode}" styleClass="lable01"
                              style="display:none;"/>
            </t:panelGroup>
        </td>
    </tr>
     
    <tr class="row02">
        <td colspan="2" width="50%"
            style="background-color: rgb(255, 255, 255); border: 3px solid rgb(221, 237, 253);vertical-align: top;">
            <t:panelGrid columns="1" id="data_tableRendering" width="100%" border="0" cellpadding="0" cellspacing="0">
                <t:dataTable id="data_societies" var="list1" forceId="true" rows="9"
                             value="#{mappedDataBean.mapped_data_societies}" preserveDataModel="false"
                             rowIndexVar="index" border="0" renderedIfEmpty="false"
                             binding="#{mappedDataBean.mappDataTable}" sortable="false"
                             rendered="#{mappedDataBean.show_mapped_data_societies}" width="100%" align="right"
                             footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                             cellpadding="0" cellspacing="0" rowClasses="standardTable_Row1,standardTable_Row2"
                             rowStyleClass="#{(        mappedDataBean.showedRow       ==list1.strCode) ? 'standardTable_RowHighlighted' : null}">
                    <t:column id="popup_radio_column" styleClass="standardTable_Header2" width="10%">
                        <f:facet name="header"></f:facet>
                        <t:graphicImage styleClass="iconDetail" rendered="#{list1.hasMappedValues}" border="0"
                                        url="/module/media/images/green-circle.gif"
                                        onmouseover="doTooltip(event,'&lt;center>#{mapResources.rowHasData}&lt;/center>')"
                                        onmouseout="hideTip()"/>
                    </t:column>
                    <t:column sortable="true" width="9%" rendered="false" defaultSorted="true">
                        <%-- a4j:commandButton value="" rendered="#{list.hasMappedValues}"
                             title="#{Mapped_Resource.soc_value_to}" action="#{mappedDataBean.getDetailsAction}"
                             id="Edit_Button" alt="center" styleClass="iconDetail" reRender="second_dataT_group" /--%>
                        <f:facet name="header"></f:facet>
                        <h:commandButton value="" rendered="#{list1.hasMappedValues}"
                                         title="#{Mapped_Resource.soc_value_to}"
                                         action="#{mappedDataBean.getDetailsAction}" id="Edit_Button" alt="center"
                                         styleClass="iconDetail"/>
                    </t:column>
                    <t:column width="15%">
                        <%-- h:outputLabel value="#{list.strCode}" id="Edit_label_Code" style="text-align:center;"
                             styleClass="iconString" /--%>
                        <f:facet name="header">
                            <t:outputText id="codeId" forceId="true" styleClass="headerLink"
                                          value="#{globalResources.Code}"/>
                        </f:facet>
                        <h:commandButton value="#{list1.strCode}" action="#{mappedDataBean.getDetailsAction}"
                                         id="Edit_Button_Code" alt="right" styleClass="iconString"/>
                    </t:column>
                    <t:column width="75%">
                        <f:facet name="header">
                            <t:outputText id="nameId" forceId="true" styleClass="headerLink"
                                          value="#{globalResources.name}"/>
                        </f:facet>
                        <h:commandButton value="#{list1.name}" alt="right" styleClass="iconString"
                                         action="#{mappedDataBean.getDetailsAction}" id="Edit_Button_Name"
                                         type="submit"/>
                    </t:column>
                </t:dataTable>
                <div style="position:absolute;top:200 ;width:475px;" align="center">
                    <t:dataScroller id="map_socities_scroll" fastStep="6" pageCountVar="pageCount"
                                    pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="6"
                                    paginatorTableClass="paginator" paginatorActiveColumnStyle="font-weight:bold"
                                    paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                                    styleClass="map_scroller2" immediate="false" for="data_societies"
                                    renderFacetsIfSinglePage="false"
                                    style="text-align:center;margin-left: auto;margin-right: auto;">
                        <f:facet name="first">
                            <h:panelGroup>
                                <t:graphicImage id="map_list_img_firstOnadd" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                                <t:graphicImage id="map_list_img_firstOffadd" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="last">
                            <h:panelGroup id="map_list_panelGrp_lastadd">
                                <t:graphicImage id="map_list_img_lastOnadd" rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_last}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                                border="0"/>
                                <t:graphicImage id="map_list_img_lastOffadd" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="previous">
                            <h:panelGroup id="map_list_panelGrp_prvadd">
                                <t:graphicImage id="map_list_img_prvOnadd" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_previous}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                                border="0"/>
                                <t:graphicImage id="map_list_img_prvOffadd" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="next">
                            <h:panelGroup id="map_list_panelGrp_nxtadd">
                                <t:graphicImage id="map_list_img_nxtOnadd" rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_next}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                                border="0"/>
                                <t:graphicImage id="map_list_img_nxtOffadd" rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastforward">
                            <h:panelGroup id="map_list_panelGrp_ffrwrdadd">
                                <t:graphicImage id="map_list_img_ffrwrdOnadd" rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_fastforward}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                                border="0"/>
                                <t:graphicImage id="map_list_img_ffrwrdOffadd" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastrewind">
                            <h:panelGroup id="map_list_panelGrp_frwnd">
                                <t:graphicImage id="map_img_frwndOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_fastrewind}"
                                                url="/app/media/images/#{globalResources.photoFolder}/arrow-ff.gif"
                                                border="0"/>
                                <t:graphicImage id="map_img_frwndOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/arrow-ff_gray.gif"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                    </t:dataScroller>
                </div>
            </t:panelGrid>
        </td>
        <td colspan="2" height="315" width="50%"
            style="background-color: rgb(255, 255, 255); border: 3px solid rgb(221, 237, 253);">
            <t:panelGroup forceId="true" id="second_dataT_group">
                <t:dataTable id="Many_dataT_data" var="list2" style="margin: -153px 0 0;"
                             value="#{mappedDataBean.mappedValue}" preserveDataModel="false" rowIndexVar="index"
                             renderedIfEmpty="false" sortable="false" rendered="#{mappedDataBean.showdetails}" rows="5"
                             width="100%" align="left" binding="#{mappedDataBean.mapTable}" footerClass="grid-footer"
                             styleClass="grid-footer" headerClass="standardTable_Header" cellpadding="0" cellspacing="0"
                             rowClasses="standardTable_Row1,standardTable_Row2">
                    <t:column id="popup_radio_column" styleClass="standardTable_Header2" width="5%">
                        <f:facet name="header"></f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="checkDelBox" value="#{list2.checked}">
                            <a4j:support event="onclick" actionListener="#{mappedDataBean.deleteSelectedRows}"
                                         reRender="btn_holder,divDeleteAlert"/>
                        </t:selectBooleanCheckbox>
                    </t:column>
                    <t:column width="15%">
                        <f:facet name="header">
                            <t:outputText id="code2Id" forceId="true" styleClass="headerLink"
                                          value="#{globalResources.Code}"/>
                        </f:facet>
                        <h:outputText id="popup_code" value="#{list2.strCode}"/>
                    </t:column>
                    <t:column width="80%" defaultSorted="true">
                        <f:facet name="header">
                            <t:outputText id="name2Id" forceId="true" styleClass="headerLink"
                                          value="#{globalResources.name}"/>
                        </f:facet>
                        <h:outputText id="name200Id" forceId="true" value="#{list2.name}"/>
                    </t:column>
                </t:dataTable>
                <t:dataScroller id="Many_dataT_data_scroll" fastStep="6" pageCountVar="pageCount"
                                pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="7"
                                paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                                paginatorActiveColumnStyle="font-weight:bold" paginatorTableClass="paginator"
                                styleClass="map_scroller2" immediate="false" for="Many_dataT_data"
                                binding="#{mappedDataBean.many_dataT_data_scroll}" rendered="false"
                                renderFacetsIfSinglePage="false">
                    <f:facet name="first">
                        <h:panelGroup>
                            <t:graphicImage id="map_Many_dataT_data_img_firstOnadd" rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_first}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                            border="0"/>
                            <t:graphicImage id="map_Many_dataT_data_img_firstOffadd" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                            border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="last">
                        <h:panelGroup id="map_Many_dataT_data_panelGrp_lastadd">
                            <t:graphicImage id="map_Many_dataT_data_img_lastOnadd"
                                            rendered="#{pageIndex &lt; pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="map_Many_dataT_data_img_lastOffadd" onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="previous">
                        <h:panelGroup id="map_Many_dataT_data_panelGrp_prvadd">
                            <t:graphicImage id="map_Many_dataT_data_img_prvOnadd" rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="map_Many_dataT_data_img_prvOffadd" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="next">
                        <h:panelGroup id="map_Many_dataT_data_panelGrp_nxtadd">
                            <t:graphicImage id="map_Many_dataT_data_img_nxtOnadd" rendered="#{pageIndex &lt; pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="map_Many_dataT_data_img_nxtOffadd" rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="fastforward">
                        <h:panelGroup id="map_Many_dataT_data_panelGrp_ffrwrdadd">
                            <t:graphicImage id="map_Many_dataT_data_img_ffrwrdOnadd"
                                            rendered="#{pageIndex &lt; pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="map_Many_dataT_data_img_ffrwrdOffadd" onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="fastrewind">
                        <h:panelGroup id="map_Many_dataT_data_panelGrp_frwnd">
                            <t:graphicImage id="map_Many_dataT_data_frwndOn" rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/arrow-ff.gif"
                                            border="0"/>
                            <t:graphicImage id="map_Many_dataT_data_frwndOff" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/arrow-ff_gray.gif"
                                            border="0"/>
                        </h:panelGroup>
                    </f:facet>
                </t:dataScroller>
            </t:panelGroup>
        </td>
    </tr>
     
    <tr class="row01">
        <td colspan="2">
            <t:panelGroup id="totalNo" forceId="true">
                <t:outputText styleClass="lable01"
                              value="#{mapResources.mappedRecords} : #{mappedDataBean.mappedRecordNo} / #{mappedDataBean.totalMapped_data_societiesNo}"/>
            </t:panelGroup>
        </td>
        <td colspan="2">
            <t:panelGrid id="btn_continer" forceId="true" columns="3" cellpadding="0" cellspacing="0" align="right"
                         border="0" style="height:20px">
                <t:panelGroup forceId="true" id="addButtonPnl">
                    <t:commandButton onclick="addButtonJs();" id="addButton" forceId="true" type="button"
                                     rendered="#{mappedDataBean.showdetails && mappedDataBean.mapped_data_societies != null}"
                                     styleClass="addButtonSmall" title="#{Mapped_Resource.addmapeddetails}"/>
                    <a4j:jsFunction name="addButtonJs" action="#{mappedDataBean.removeSelectedCheckBox}"
                                    rendered="#{!pageBeanName.displayTreeFlag}"
                                    reRender="second_dataT_group,delAlert,btn_holder, btnContiner,divAdd,srchContianer"
                                    oncomplete="javascript:changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusOnlyOnElement('Name');"/>
                    <a4j:jsFunction name="addButtonJs" action="#{mappedDataBean.removeSelectedCheckBox}"
                                    rendered="#{pageBeanName.displayTreeFlag}"
                                    reRender="second_dataT_group,delAlert,btn_holder, btnContiner,divAdd,srchContianer"
                                    oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divTree);setFocusOnlyOnElement('Name');"/>
                </t:panelGroup>
                <t:panelGroup id="btn_holder" forceId="true">
                    <t:commandButton id="deleteButton" forceId="true" binding="#{mappedDataBean.enableRemoveBtn}"
                                     rendered="false" styleClass="removeButtonSmall" type="button"
                                     title="#{Mapped_Resource.removemapeddetails}"
                                     onclick="javascript:changeVisibilityDiv(window.blocker,window.delAlert);setFocusOnlyOnElement('CancelButtonDelAlertDiv');"/>
                </t:panelGroup>
            </t:panelGrid>
        </td>
    </tr>
</table>
<t:inputHidden id="allowSpecialCharInSrchByCode" forceId="true" value="true"/>
<t:saveState value="{mappedDataBean.mappedFilter}"/>
