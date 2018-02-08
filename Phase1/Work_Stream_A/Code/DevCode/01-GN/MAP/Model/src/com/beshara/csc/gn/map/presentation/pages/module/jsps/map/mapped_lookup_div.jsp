<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:saveState value="#{pageBeanName.objectTypeId}" />
<t:saveState value="#{pageBeanName.socities2Code}" />
<t:saveState value="#{pageBeanName.socities1Code}" />
<t:saveState value="#{pageBeanName.socitiesValue}" />
<t:saveState value="#{pageBeanName.myTableData}" />
<t:saveState value="#{pageBeanName.selectedDTOS}" />
<t:saveState value="#{pageBeanName.saveMappedDTO}" />
<t:messages />
<t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsg" />
<f:verbatim>
<table border="0" width="99%" id="table5" cellspacing="0" cellpadding="0"></f:verbatim>
                                <f:verbatim><tr></f:verbatim>
                                        <f:verbatim><td valign="top" bgcolor="#FFFFFF" class="paddingbox"></f:verbatim>
                                        <t:outputText id="clientErrorMessage" styleClass="errMsg" forceId="true"/>
                                                <h:panelGroup id="srchContianer">
                                                <h:outputText value="#{globalResources.SearchName}" styleClass="lable01"/>
                                                <t:inputText forceId="true" id="Name" value="#{pageBeanName.searchString}" styleClass="textboxmedium" onblur="setFocusOnlyOnElement('myForm:searchAdd');" onkeypress="FireButtonClick('myForm:searchAdd');"/>
                                                <h:outputLabel value="" />
                                                </h:panelGroup>
                                                <t:panelGroup id="btnContiner" forceId="true">
                                                <%--a4j:commandButton action="#{pageBeanName.searchAvailable}" reRender="dataT_available,panelGrd_scrolleradd,srchContianer,divAdd,btnContiner"  value=""  styleClass="MapSearchButton" id="searchAdd" oncomplete="checkAllCheckBoxDiv2('chk2','listSize','checkAll','pageIndexMap','noOfTableRow');"/--%>
                                                <a4j:commandButton action="#{pageBeanName.searchAvailable}" reRender="dataT_available,panelGrd_scrolleradd,srchContianer,divAdd,btnContiner"  value="#{mapResources.Search}"  styleClass="cssButtonSmall" id="searchAdd"/>
                                                <a4j:commandButton binding="#{pageBeanName.cancelSearchBtn}" action="#{pageBeanName.cancelSearchAvailable}" reRender="dataT_available,panelGrd_scrolleradd,srchContianer,divAdd,btnContiner"  value="#{mapResources.cancelSearch}" styleClass="cssButtonSmall" rendered="false"  oncomplete="checkAllCheckBoxDiv('chk2','listSize','checkAll','pageIndexMap','noOfTableRow');"/>
                                                </t:panelGroup>
                                                
                                        <f:verbatim></td></f:verbatim>
                                <f:verbatim></tr></f:verbatim>
<f:verbatim></table></f:verbatim>

                   
<t:panelGroup forceId="true" id="divAdd">
<%-- f:verbatim><div style="overflow:auto;height:130px;width:100%;"></f:verbatim--%>
<h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
    <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
</h:panelGrid> 
 <t:panelGroup  styleClass="fullWidthScroll190">
                        <t:dataTable id="dataT_available" var="list" value="#{pageBeanName.myTableData}"     
                            rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                            binding="#{pageBeanName.myDataTable}"
                            rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                            footerClass="grid-footer" styleClass="grid-footer"
                            headerClass="standardTable_Header" 
                            rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                            width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                            renderedIfEmpty="false"
                            
                            >
                                             
                       <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                        <f:facet name="header">
                            <%-- t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                                <f:attribute name="checkAll" value="true"/>
                                <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAllDiv('checkAll','chk2','listSize','pageIndexMap','noOfTableRow');"  reRender="divDeleteAlert,divEditLookup,selectedNumber,searchMode,checkedAttrSize"/>
                            </t:selectBooleanCheckbox--%>
                        </f:facet>
                        
                        <t:selectBooleanCheckbox forceId="true" id="chk2" onkeypress="FireButtonClick('add_ok');"  value="#{list.checked}">
                            <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}" oncomplete="checkCheckAllDiv('chk2','listSize','checkAll','pageIndexMap','noOfTableRow');setFocusOnlyOnElement('add_ok');" reRender="add_ok_group,divDeleteAlert,divEditLookup,selectedNumber,searchMode,checkedAttrSize" />
                            
                        </t:selectBooleanCheckbox>
                    </t:column>
                      <%-- 
                    <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" valueChangeListener="#{pageBeanName.selectedAvailableDetails}" >
                      
                       oncomplete="updateButtonsStatusTable();updateMenuItemsStatusTable('myForm_myMenu_menu',myForm_myMenu_menu,'hbl');checkAllCheckBox();" 
                       
                       reRender="divDeleteAlert,divEditLookup,selectedNumber,searchMode">
                           <a4j:support event="onclick"  rendered="add_ok_group"  />
                       </t:selectBooleanCheckbox--%>
                       
                       
                       
                   <%--t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                       <f:facet name="header">
                       
                       
                        <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                                <f:attribute name="checkAll" value="true"/>
                                <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,divEditLookup,selectedNumber,searchMode"/>
                            </t:selectBooleanCheckbox>
                       
                          <%-- t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                                <f:attribute name="checkAll" value="true"/>
                                <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,divEditLookup,selectedNumber,searchMode"/>
                            </t:selectBooleanCheckbox>
                       </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" valueChangeListener="#{pageBeanName.selectedAvailableDetails}" --%>
                      <%--
                       oncomplete="updateButtonsStatusTable();updateMenuItemsStatusTable('myForm_myMenu_menu',myForm_myMenu_menu,'hbl');checkAllCheckBox();" 
                       
                       reRender="divDeleteAlert,divEditLookup,selectedNumber,searchMode">
                           <a4j:support event="onclick"  rendered="add_ok_group" oncomplete="checkCheckAll('chk2');"  />
                       </t:selectBooleanCheckbox>
                   </t:column--%>
 
            <t:column id="code_column" sortable="true" width="20%">
                      <f:facet name="header">
                          <t:commandSortHeader actionListener="#{pageBeanName.scrollerAction}" id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                              <f:facet name="ascending">
                                  <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <f:facet name="descending">
                                  <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <h:outputText id="header_code" value="#{globalResources.Code}"/>
                          </t:commandSortHeader>
                      </f:facet>
                    <h:outputText id="content_code" value="#{list.code.key}"/>
                  </t:column>
                 
                  <t:column id="name_column" sortable="true" width="75%">
                      <f:facet name="header">
                          <t:commandSortHeader actionListener="#{pageBeanName.scrollerAction}" id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                              <f:facet name="ascending">
                                  <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <f:facet name="descending">
                                  <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <h:outputText id="header_name" value="#{globalResources.name}"/>
                          </t:commandSortHeader>
                      </f:facet>
                      <h:outputText id="content_name" value="#{list.name}"/>
                  </t:column>
                   
 
               </t:dataTable>
               </t:panelGroup>
               <h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
               
    <h:outputText value="#{jobResources.No_Result_Found}" style="font-family: Tahoma; font-size: 8pt; color: #EB8E3B; font-weight: 600;" />
</h:panelGrid>
    
 <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.listSize > shared_util.noOfTableRows}">
           
         <t:dataScroller id="scroll_1add" actionListener="#{pageBeanName.scrollerAction}"
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
                   paginatorActiveColumnStyle="font-weight:bold"
                   styleClass="textpage"
                   immediate="false"
                   binding="#{pageBeanName.dataScroller}"
                   for="dataT_available"
                   renderFacetsIfSinglePage="false">
               <f:facet name="first" >                            
                   <h:panelGroup id="jobs_list_panelGrp_firstadd">
                       <t:graphicImage id="jobs_list_img_firstOnadd"
                                               rendered="#{pageIndex > 1}"
                                               title="#{globalResources.scroller_first}"
                                               url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                               border="0"/>
                       <t:graphicImage id="jobs_list_img_firstOffadd"
                                               onclick="return false;"
                                               rendered="#{pageIndex <= 1}"
                                               url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                               border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="last">                            
                   <h:panelGroup id="jobs_list_panelGrp_lastadd">
                           <t:graphicImage id="jobs_list_img_lastOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_last}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_lastOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="previous">                            
                   <h:panelGroup id="jobs_list_panelGrp_prvadd">
                           <t:graphicImage id="jobs_list_img_prvOnadd"
                                           rendered="#{pageIndex > 1}"
                                           title="#{globalResources.scroller_previous}"
                                           url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_prvOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex <= 1}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="next">                            
                   <h:panelGroup id="jobs_list_panelGrp_nxtadd">
                           <t:graphicImage id="jobs_list_img_nxtOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_next}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_nxtOffadd"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               
               <f:facet name="fastforward">
                   <h:panelGroup id="jobs_list_panelGrp_ffrwrdadd">
                           <t:graphicImage id="jobs_list_img_ffrwrdOnadd"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_fastforward}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_ffrwrdOffadd"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="fastrewind">
                    <h:panelGroup id="panelGrp_frwnd">
                        <t:graphicImage id="img_frwndOn"
                                        rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/arrow-ff.gif"
                                        border="0"/>
                        <t:graphicImage id="img_frwndOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/arrow-ff_gray.gif"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
               
           </t:dataScroller>
       </h:panelGrid>
         
                   </t:panelGroup>
                   <%-- f:verbatim></div></f:verbatim--%>
                   <%-- Start css modification add two break line to UI --%>
                
                  
                  
                  
                  <%-- End css modification add two break line to UI --%>
                  <t:panelGroup id="add_ok_group" forceId="true">
                 <t:commandButton id="add_ok" forceId="true" binding="#{pageBeanName.add_ok_btn}" value="#{globalResources.ok}" onclick="valid =CheckOneCheckBoxSelected(this.form);if(valid){block();}return valid;" action="#{pageBeanName.addMappedData}" styleClass="cssButtonSmall"  /> 
                 </t:panelGroup>
                 <%-- a4j:commandButton id="add_ok" value="#{globalResources.ok}" action="#{pageBeanName.addMappedData}" styleClass="cssButtonSmall" reRender="Many_dataT_data" /--%><f:verbatim>&nbsp; </f:verbatim>
                 <%--a4j:commandButton id="ok" action="#{pageBeanName.searchAvailable}" reRender="dataT_available" value="" styleClass="MapSearchButton" /--%>
                 <%--a4j:commandButton id="add_cancel" actionListener="#{pageBeanName.testListner}" oncomplete="checkCheckAll('chk2');setAll('checkAll', 'chk2');" type="submit" value="#{globalResources.CancelButton}" reRender="divDeleteAlert,divAdd" onclick="removeMsg('errorConsole');hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');" styleClass="cssButtonSmall"/--%>
                 <t:panelGroup>
                    <t:commandButton onclick="cancelAddButtonJs();" id="backButtonAddDiv" forceId="true" type="button" 
                                    styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('Name');"/>
                    <a4j:jsFunction name="cancelAddButtonJs"  actionListener="#{pageBeanName.testListner}"  
                                reRender="divDeleteAlert,divAdd,checkedAttrSize" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'Name','clientErrorMessage');checkCheckAll('chk2');setAll('checkAll', 'chk2');removeMsg('errorConsole');"/>
                </t:panelGroup> 
                 <%--h:commandButton id="add_cancel" type="submit" value="#{globalResources.CancelButton}" onclick="removeMsg('errorConsole');hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" actionListener="#{pageBeanName.selectedCheckboxsAll}"   /--%>
                    <%--a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,divEditLookup,selectedNumber,searchMode"/>
                 </h:commandButton--%>
        <t:inputHidden forceId="true" id="noOfTableRow" value="#{shared_util.noOfTableRows}"/>
        <t:inputHidden forceId="true" id="arrayId" value=""/>
        <t:inputHidden value="#{pageBeanName.listSize}"  forceId="true" id="listSize"/>
        <t:inputHidden forceId="true" id="pageIndexMap" value="#{pageBeanName.pageIndexAdd}" rendered="#{pageBeanName.listSize > shared_util.noOfTableRows}" />
        <t:inputHidden value="#{pageBeanName.selectedListSize}" id="checkedAttrSize" forceId="true" />
        
        <%-- t:inputHidden value="#{pageBeanName.listSize}"  forceId="true" id="listSize"/--%>  
