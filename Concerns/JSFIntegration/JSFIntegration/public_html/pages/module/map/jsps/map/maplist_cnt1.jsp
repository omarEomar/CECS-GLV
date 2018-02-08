<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

        <f:verbatim>
        
        <div align="center">
      </f:verbatim>
          <h:outputText id="msg" value="{mappedDataBean.msg}" rendered="false"
                        styleClass="Msg"/><!--br></br-->
        <f:verbatim>
        </div>
        <t:panelGrid id="titleMsg" forceId="true" columns="2" border="0" >
        
        <h:outputLabel  style="color:#A77B32;font-family:Tahoma;font-size:12px"  value="#{mappedDataBean.mapping_title}" />
        <%--a4j:status startStyleClass="progress" /--%>
        
        </t:panelGrid>
        
        <table width="100%" cellpadding="0" border="0" cellspacing="0">
          <tr class="row01">
          
            <td colspan="4" height="25px" align="center" width="100%">
            &nbsp;
            &nbsp;
            </f:verbatim>
            
            
            
        <h:outputLabel value="#{mapResources.mapped_object_name}" styleClass="lable01" />
                        <f:verbatim> 
                        &nbsp;    
                        &nbsp;            
                        </f:verbatim>
                        
              
                        <t:selectOneMenu forceId="true" id="objectType" binding="#{mappedDataBean.objectTypeElement}"  value="#{mappedDataBean.selectedObjectTypeId}"       valueChangeListener="#{mappedDataBean.populatePageComponents}" styleClass="DropdownboxLarge" > 
                           <%--f:convertNumber type="number"/--%>
                            
                                        
                                        <t:selectItems id="object_type_items" itemLabel="#{object_type.name}" itemValue="#{object_type.code.key}" var="object_type" value="#{mappedDataBean.objectType}" />
                                        <a4j:support event="onchange" reRender="soc_from,soc_to,data_tableRendering,second_dataT_group,btn_continer,btnSearch,titleMsg,totalNo"/> 
                                        
                        </t:selectOneMenu>              
            <f:verbatim>
            
            </td>
          </tr>
          <tr>
            <td width="100%" colspan="4">
              <%-- ---------------------- the lower part tabels -----------%>
             
              <table width="100%" border="0" cellpadding="2" cellspacing="0"  >
                <tr>
                  <td width="50%">
                    <%-- ---------------------- right hand side -----------%>
                    <table width="100%" border="0" cellpadding="0"  
                           cellspacing="0">
                      <%-- ----------------------right header Panel -----------%>
                      <tr class="gmainbar">
                        <td>
                          <table>
                            <tr>
                            
                              <td align="left">
                                   
                        
                        
                              </f:verbatim>
                              &nbsp;
                              &nbsp;
                                 <h:outputLabel value="#{mapResources.soc_from}" styleClass="lable01" />
                                <f:verbatim>
                              </td>
                              <td align="right">
                             </f:verbatim>
                             
                             <h:selectOneMenu id="soc_from" binding="#{mappedDataBean.socFromControl}" value="#{mappedDataBean.selectedSocityFrom}" required="false" styleClass="DropdownboxLarge" valueChangeListener="#{mappedDataBean.populatePageComponents}" disabled="#{mappedDataBean.controlsDisabled}"  >
                                    <%--f:selectItem itemValue="#{mappedDataBean.ALL_MENUS_DEFAULT_VALUE}" 
                                                  itemLabel="#{mapResources.selectItem}" /--%>
                                    <t:selectItems itemLabel="#{soc_from.name}" itemValue="#{soc_from.code.key}" var="soc_from" value="#{mappedDataBean.soc_from}" />
                                     <a4j:support event="onchange" reRender="soc_to,data_tableRendering,second_dataT_group,btn_continer,btnSearch,btn_holder,titleMsg,totalNo"/> 
                                    
                              </h:selectOneMenu>
                
                
              
              
                                <t:panelGroup id="btnSearch" forceId="true" rendered="true">
                                    <t:commandButton  action="#{mappedDataBean.disableCtrls}"   rendered="false" binding="#{mappedDataBean.searchHTMLBtn}" forceId="true" id="searchButton"  type="button" styleClass="MapSearchButton" value=""  onclick="javascript:changeVisibilityDiv(window.blocker,window.divSearch);setFocusAtMySearchText();">
                                        <a4j:support reRender=" btn_continer,second_dataT_group"/>
                                    </t:commandButton>
                                    <%--a4j:commandButton binding="#{mappedDataBean.searchHTMLBtn}" action="#{mappedDataBean.disableCtrls}" rendered="false" type="button" styleClass="MapSearchButton" value="" onclick="javascript:changeVisibilityDiv(window.blocker,window.divSearch);" reRender=" btn_continer,second_dataT_group"   /--%>
                                    
                                    <t:commandButton onclick="cancelSearchButtonJs();" rendered="false"  binding="#{mappedDataBean.mapCancelSearchButton}" forceId="true" id="cancelSearchButton" type="button" styleClass="MapCancelSearchButton" />
                                    <a4j:jsFunction name="cancelSearchButtonJs"  reRender="data_tableRendering,second_dataT_group,btnSearch, btn_continer, titleMsg,divSearch,totalNo" action="#{mappedDataBean.cancelSearchAvailable}"/>
                                    
                                    <%--a4j:commandButton binding="#{mappedDataBean.mapCancelSearchButton}" action="#{mappedDataBean.cancelSearchAvailable}" rendered="false" reRender="data_tableRendering,second_dataT_group,btnSearch" value="" styleClass="MapCancelSearchButton" /--%>
                                </t:panelGroup>
                                
                                
                                
                                <f:verbatim>
                                
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <%-- ----------------------end right header and start
                           right body-----------%>
                           <%
               
                           System.out.println("inside jsp------------------------->");
                           %>
                         
                      <tr>
                       
                              <td height="192px" valign="top" bgcolor="#F3F3F8"
                            align="right" width="100%">
                          
                            <div style="overflow:auto;height:250px;width:475px;">
                          </f:verbatim>
                          
                            <t:panelGrid columns="1" id="data_tableRendering" width="100%" style="text-align: center;" border="0">
                                 <t:dataTable id="data_societies" var="list1"
                                        forceId="true"
                                        rows="7"
                                         value="#{mappedDataBean.mapped_data_societies}"
                                         preserveDataModel="false"
                                         rowIndexVar="index"
                                         border="0"
                                         renderedIfEmpty="false"
                                         binding="#{mappedDataBean.mappDataTable}"
                                         sortable="false"
                                         rendered="#{mappedDataBean.show_mapped_data_societies}"
                                         width="100%" align="right"
                                         footerClass="grid-footer"
                                         headerClass="row01"
                                         rowClasses="row02,row01"
                                         columnClasses="standardTable_ColumnCentered,standardTable_Column,standardTable_Column"
                                         rowStyleClass="#{(        mappedDataBean.showedRow       ==list1.strCode) ? 'standardTable_RowHighlighted' : null}" 
                                         >
                              
                              
                              <t:column id="popup_radio_column" 
                                        styleClass="standardTable_Header2" 
                                        width="10%" >
                                        
                                        <t:graphicImage styleClass="iconDetail" rendered="#{list1.hasMappedValues}"  border="0"  url="/module/map/media/images/green-circle.gif" onmouseover="doTooltip(event,'<center>#{mapResources.rowHasData}</center>')" onmouseout="hideTip()" />
                                        
                                
                              </t:column>
                            <t:column sortable="true" width="9%" rendered="false" 
                                        defaultSorted="true" >
                                <%-- a4j:commandButton value="" rendered="#{list.hasMappedValues}"
                                  title="#{Mapped_Resource.soc_value_to}"
                                   action="#{mappedDataBean.getDetailsAction}"
                                   id="Edit_Button"
                                   alt="center"
                                   styleClass="iconDetail"
                                   reRender="second_dataT_group"
                                   /--%>        
                                
                                <h:commandButton value="" rendered="#{list1.hasMappedValues}"
                                  title="#{Mapped_Resource.soc_value_to}"
                                   action="#{mappedDataBean.getDetailsAction}"
                                   id="Edit_Button"
                                   alt="center"
                                   styleClass="iconDetail"/>
                              </t:column>
                              
                              <t:column width="15%" >
                                    <%-- h:outputLabel  value="#{list.strCode}"  id="Edit_label_Code"  style="text-align:center;" styleClass="iconString"  /
                        --%>
                                    <h:commandButton  value="#{list1.strCode}" action="#{mappedDataBean.getDetailsAction}" id="Edit_Button_Code" alt="right"  styleClass="iconString" />
                                    
                              </t:column>
                              
                              <t:column  width="75%">
                              
                                        <h:commandButton value="#{list1.name}" alt="right" styleClass="iconString" action="#{mappedDataBean.getDetailsAction}" id="Edit_Button_Name" type="submit" />
                                
                                
                               
                              </t:column>
                              
                            </t:dataTable>
   
                        <div style="position:absolute;top:200 ;width:475px;" align="center">
                  <t:dataScroller id="map_socities_scroll" 
                   fastStep="7" pageCountVar="pageCount" 
                   pageIndexVar="pageIndex"
                    paginator="true"
                   paginatorMaxPages="7"
                   paginatorTableClass="paginator"
                   paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                   paginatorColumnClass="textpage"
                   paginatorActiveColumnClass="paging"
                   styleClass="map_scroller2"
                   paginatorTableStyle="background-color: Red;"
                   immediate="false"
                   for="data_societies"
                   renderFacetsIfSinglePage="false" style="align:center">
               <f:facet name="first" >                            
                   <h:panelGroup>
                       <t:graphicImage id="map_list_img_firstOnadd"
                                               rendered="#{pageIndex > 1}"
                                               title="#{globalResources.scroller_first}"
                                               url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                               border="0"/>
                       <t:graphicImage id="map_list_img_firstOffadd"
                                               onclick="return false;"
                                               rendered="#{pageIndex <= 1}"
                                               url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                               border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="last">                            
                   <h:panelGroup id="map_list_panelGrp_lastadd">
                           <t:graphicImage id="map_list_img_lastOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_last}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_list_img_lastOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="previous">                            
                   <h:panelGroup id="map_list_panelGrp_prvadd">
                           <t:graphicImage id="map_list_img_prvOnadd"
                                           rendered="#{pageIndex > 1}"
                                           title="#{globalResources.scroller_previous}"
                                           url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_list_img_prvOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex <= 1}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="next">                            
                   <h:panelGroup id="map_list_panelGrp_nxtadd">
                           <t:graphicImage id="map_list_img_nxtOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_next}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_list_img_nxtOffadd"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               
               <f:facet name="fastforward">
                   <h:panelGroup id="map_list_panelGrp_ffrwrdadd">
                           <t:graphicImage id="map_list_img_ffrwrdOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_fastforward}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_list_img_ffrwrdOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="fastrewind">
                    <h:panelGroup id="map_list_panelGrp_frwnd">
                        <t:graphicImage id="map_img_frwndOn"
                                        rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/arrow-ff.gif"
                                        border="0"/>
                        <t:graphicImage id="map_img_frwndOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/arrow-ff_gray.gif"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
               
           </t:dataScroller>
           </div>
                            
                            </t:panelGrid>
                            
                            
                        
                        
                            <f:verbatim>
                          </div>
                        </td>
                      </tr>
                         <%------------------------end right body and start right footer-----------%>
                         <tr >
                         <td height="2">
                         </td>
                         </tr>
                                               <tr class="row01">
                                               <td height="27"> &nbsp;
                                               </f:verbatim>
                                               <t:panelGroup id="totalNo" forceId="true" >
                                               
                                                  <t:outputText styleClass="mapped_info_message" value="#{mapResources.mappedRecords} : #{mappedDataBean.mappedRecordNo} / #{mappedDataBean.totalMapped_data_societiesNo}"  />
                                                  </t:panelGroup>
                                                <f:verbatim>
                                               </td>
                                               </tr>
                                                                   <%------------------------end  right footer-----------%>

                    </table> 
                    <%-- ---------------------- end right hand side -----------%>
                  </td>
                  <td width="50%">
                    <%-- -------------------------start left side
                         ---------------------------------%>
                    <table width="100%" border="0" cellpadding="0" align="left"
                           cellspacing="0">
                      <%-- -------------------------start left header
                           ---------------------------------%>
                      <tr class="gmainbar">
                        <td height="30px">
                        &nbsp;
                              
                        </f:verbatim>
                       <h:outputLabel value="#{mapResources.soc_to}" styleClass="lable01" />
                       <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu id="soc_to" styleClass="DropdownboxLarge" value="#{mappedDataBean.selectedSocity2Id}" forceId="true" 
                valueChangeListener="#{mappedDataBean.populatePageComponents}" binding="#{mappedDataBean.socToControl}">
                <f:selectItem itemValue="#{mappedDataBean.ALL_MENUS_DEFAULT_VALUE}" 
                              itemLabel="#{mapResources.selectItem}"/>
                <t:selectItems itemLabel="#{soc_to.name}" itemValue="#{soc_to.code.key}" var="soc_to" value="#{mappedDataBean.soc_to}"  />
                <a4j:support event="onchange" reRender="data_tableRendering,second_dataT_group,btn_continer,btnSearch,btn_holder,titleMsg,totalNo"/>
                
              </t:selectOneMenu>
                          <f:verbatim>
                        </td>
                      </tr>
                      <%-- -------------------------end left header and start
                           table ---------------------------------%>
                      <tr >
                        <td height="190px" valign="top" bgcolor="#F3F3F8"
                            align="left" width="100%">
                          
                            <div style="overflow:auto;height:250px;width:475px;">
                          </f:verbatim> 
                        <t:panelGrid columns="1" id="second_dataT_group" border="0" style="text-align: center;" width="100%" >
                       <t:dataTable id="Many_dataT_data" var="list2"
                                       value="#{mappedDataBean.mappedValue}"
                                       preserveDataModel="false"
                                       rowIndexVar="index"
                                       renderedIfEmpty="false" sortable="false"
                                       rendered="#{mappedDataBean.showdetails}"
                                       rows="7"
                                       
                                       width="100%" align="left"
                                       footerClass="grid-footer"
                                       binding="#{mappedDataBean.mapTable}"
                                       headerClass="row01"
                                       rowClasses="row02,row01"
                                       columnClasses="standardTable_ColumnCentered,standardTable_Column,standardTable_Column">
                            
                           <t:column id="popup_radio_column"
                                      styleClass="standardTable_Header2"
                                      width="5%">
                              <t:selectBooleanCheckbox forceId="true"
                                                       id="checkDelBox" value="#{list2.checked}"
                                                       
                                                       >
                                                       <a4j:support event="onclick" actionListener="#{mappedDataBean.deleteSelectedRows}" reRender="btn_holder,divDeleteAlert"  />
                                                       </t:selectBooleanCheckbox>
                            </t:column>
                            <t:column sortable="true" width="15%"
                                      defaultSorted="true">
                              <h:outputText id="popup_code"
                                            value="#{list2.strCode}"/>
                            </t:column>
                            <t:column sortable="true" width="80%"
                                      defaultSorted="true">
                              <h:outputText value="#{list2.name}"/>
                            </t:column>
                             </t:dataTable>
                             <div style="position:absolute;top:200 ;width:475px;" align="center">
                             
                             
                 <t:dataScroller id="Many_dataT_data_scroll" 
                   fastStep="7" pageCountVar="pageCount" 
                   pageIndexVar="pageIndex"
                   paginator="true"
                   paginatorMaxPages="7"
                   paginatorColumnClass="textpage"
                   paginatorActiveColumnClass="paging"
                   paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                   paginatorTableClass="paginator"
                   
                   styleClass="map_scroller2"
                   immediate="false"
                   for="Many_dataT_data"
                   binding="#{mappedDataBean.many_dataT_data_scroll}"
                   rendered="false"
                   renderFacetsIfSinglePage="false">
               <f:facet name="first" >                            
                   <h:panelGroup>
                       <t:graphicImage id="map_Many_dataT_data_img_firstOnadd"
                                               rendered="#{pageIndex > 1}"
                                               title="#{globalResources.scroller_first}"
                                               url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                               border="0"/>
                       <t:graphicImage id="map_Many_dataT_data_img_firstOffadd"
                                               onclick="return false;"
                                               rendered="#{pageIndex <= 1}"
                                               url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                               border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="last">                            
                   <h:panelGroup id="map_Many_dataT_data_panelGrp_lastadd">
                           <t:graphicImage id="map_Many_dataT_data_img_lastOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_last}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_Many_dataT_data_img_lastOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="previous">                            
                   <h:panelGroup id="map_Many_dataT_data_panelGrp_prvadd">
                           <t:graphicImage id="map_Many_dataT_data_img_prvOnadd"
                                           rendered="#{pageIndex > 1}"
                                           title="#{globalResources.scroller_previous}"
                                           url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_Many_dataT_data_img_prvOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex <= 1}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="next">                            
                   <h:panelGroup id="map_Many_dataT_data_panelGrp_nxtadd">
                           <t:graphicImage id="map_Many_dataT_data_img_nxtOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_next}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_Many_dataT_data_img_nxtOffadd"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               
               <f:facet name="fastforward">
                   <h:panelGroup id="map_Many_dataT_data_panelGrp_ffrwrdadd">
                           <t:graphicImage id="map_Many_dataT_data_img_ffrwrdOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_fastforward}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                           border="0"/>
                           <t:graphicImage id="map_Many_dataT_data_img_ffrwrdOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="fastrewind">
                    <h:panelGroup id="map_Many_dataT_data_panelGrp_frwnd">
                        <t:graphicImage id="map_Many_dataT_data_frwndOn"
                                        rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/arrow-ff.gif"
                                        border="0"/>
                        <t:graphicImage id="map_Many_dataT_data_frwndOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/arrow-ff_gray.gif"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
               
           </t:dataScroller>
</div>                             
                            </t:panelGrid>
                                 <f:verbatim>
                           
                        
                        </td>
                      </tr>
                      <%-- -------------------------end left body and start
                           footer ---------------------------------%>
                             <tr >
                         <td height="2">
                         </td>
                         </tr>
                      <tr class="row01">
                        <td height="26">
                          </f:verbatim>
                          
                          <t:panelGrid id="btn_continer" forceId="true" columns="3" cellpadding="0"
                                       cellspacing="0" align="right" border="0" 
                                       style="height:20px" >
                                
                                
                            <t:panelGroup>
                                <t:commandButton onclick="addButtonJs();" id="addButton" forceId="true" type="button" 
                                                rendered="#{mappedDataBean.showdetails && mappedDataBean.mapped_data_societies != null}" 
                                                styleClass="addButtonSmall" title="#{Mapped_Resource.addmapeddetails}"/>
                                <a4j:jsFunction name="addButtonJs"  action="#{mappedDataBean.removeSelectedCheckBox}"  
                                            reRender="second_dataT_group,delAlert,btn_holder, btnContiner,divAdd,srchContianer" oncomplete="javascript:changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusOnlyOnElement('Name');"/>
                            </t:panelGroup>   
                            
                                       
                            <%-- a4j:commandButton id="addBtn" type="button" value=""
                                             rendered="#{mappedDataBean.showdetails && mappedDataBean.mapped_data_societies != null}"
                                             action="#{mappedDataBean.removeSelectedCheckBox}"
                                             styleClass="addButtonSmall"
                                             reRender="second_dataT_group,delAlert,btn_holder"
                                             title="#{Mapped_Resource.addmapeddetails}"
                                             onclick="javascript:changeVisibilityDiv(window.blocker,window.lookupAddDiv);"
                                             /--%>
                                             
                                             
                             <t:panelGroup id="btn_holder" forceId="true" >
                             <t:commandButton id="deleteButton" forceId="true" binding="#{mappedDataBean.enableRemoveBtn}" 
                                             rendered="false" styleClass="removeButtonSmall" type="button" 
                                             title="#{Mapped_Resource.removemapeddetails}" 
                                             onclick="javascript:changeVisibilityDiv(window.blocker,window.delAlert);setFocusOnlyOnElement('CancelButtonDelAlertDiv');"
                                             />
                            <%--h:commandButton id="removeBtn" value=""
                                             binding="#{mappedDataBean.enableRemoveBtn}"
                                             rendered="false"
                                             styleClass="removeButtonSmall"
                                             type="button"
                                             title="#{Mapped_Resource.removemapeddetails}" onclick="javascript:changeVisibilityDiv(window.blocker,window.delAlert);"
                                             /--%>
                            </t:panelGroup>
                            
                          </t:panelGrid>
                          
                          
                          
                          
                          <f:verbatim>
                           
                        </td>
                      </tr>
                      <%-- -------------------------end left footer
                           ---------------------------------%>
                           </div>
                    </table>
                    <%-- -------------------------end left side
                         ---------------------------------%>
                   </td>
                </tr>
              </table>
            </td>
          </tr>
          <!-- tr-->
         </f:verbatim>
           <f:verbatim>
            <!-- td colspan="4" align="center" valign="bottom" height="30">
              
       
            </td--->
          <!-- /tr--->
        </table>
        </f:verbatim>
        <t:inputHidden id="allowSpecialCharInSrchByCode" forceId="true" value="true" />
        