<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:saveState value="#{detailBeanName.pageIndexAdd}"/>
<f:verbatim>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td align="center">
             </f:verbatim>
                <t:panelGrid columns="5" width="50%" forceId="true" id="searchPanel" align="center">
                    <h:outputText value="#{globalResources.name}"  styleClass="lable01" />
                    <t:inputText forceId="true" onkeypress="FireButtonClick('searchAvailableBtn');" id="searchText"  value="#{detailBeanName.searchString}" styleClass="textboxmedium" onblur="document.getElementById('searchAvailableBtn').focus();"/>
                    <t:panelGroup>  
                        <t:commandButton forceId="true" id="searchAvailableBtn" styleClass="ManyToManySearchButton" onclick="searchAvailableJs();" type="button"/>
                        <a4j:jsFunction name="searchAvailableJs" action="#{detailBeanName.searchAvailable}" reRender="divAdds,buttonPanel,scrollerPanel,searchPanel" oncomplete="setFocusOnlyOnElement('searchText');"/>
                    </t:panelGroup>
<%--a4j:commandButton action="#{detailBeanName.searchAvailable}" reRender="divAdds,buttonPanel,scrollerPanel,searchPanel"  styleClass="ManyToManySearchButton"/--%>
                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <a4j:commandButton action="#{detailBeanName.cancelSearchAvailable}" reRender="divAdds,buttonPanel,scrollerPanel,searchText,searchPanel,errorConsole"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchMode}" />
                </t:panelGrid>
  <f:verbatim>
            </td>
        </tr>
        <tr>
            <td>
        
        
            </f:verbatim>
            <t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsgNoHeight" />
            <t:panelGroup forceId="true" id="divAdds" styleClass="delDivScroll"  style="display:block;overflow:auto;height:150px;width:100%;">
                <t:saveState value="#{detailBeanName.searchMode}" />
               
                <t:panelGrid rendered="#{detailBeanName.availableListSize == 0}" styleClass="msg" align="center" style="width:auto;">
              
                <h:outputText value="#{globalResources.global_noTableResults}"/>
          
                </t:panelGrid>
               
               
                <t:dataTable id="dataT_available" var="list" value="#{detailBeanName.availableDetails}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                                binding="#{detailBeanName.availableDataTable}" renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                                footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                                dir="#{shared_util.pageDirection}" preserveSort="true" >
                    <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAllAdd" value="#{detailBeanName.checkAllFlagAvailable}" onkeypress="FireButtonClick('myForm:ok');">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');setFocusOnlyOnElement('myForm:ok');" reRender="selectedAvailableListSize,buttonPanel"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2Add" value="#{list.checked}" onkeypress="FireButtonClick('myForm:ok');" >
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add');setFocusOnlyOnElement('myForm:ok');" reRender="selectedAvailableListSize,buttonPanel" />
                        </t:selectBooleanCheckbox>
                        </t:column>
                        <t:column id="code_column" sortable="false" width="20%">
                            <f:facet name="header">                           
                                    <h:outputText id="header_code" value="#{globalResources.Code}"/>                          
                            </f:facet>
                          <h:outputText id="content_code" value="#{list.code.key}"/>
                        </t:column>
                        <t:column id="name_column" sortable="false" width="75%">
                            <f:facet name="header">                            
                                    <h:outputText id="header_name" value="#{globalResources.name}" />                          
                            </f:facet>
                            <h:outputText id="content_name" value="#{list.name}"/>
                        </t:column>
                    </t:dataTable>
                    <f:verbatim>
                    </td></tr></table>
                    </f:verbatim>
            </t:panelGroup>


<t:panelGrid columns="1" forceId="true" id="scrollerPanel" align="center">
  <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller"  rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
            
          <t:dataScroller id="scroll_1add"     actionListener="#{detailBeanName.scrollerAction}" binding="#{detailBeanName.dataScroller}"
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
        
        
        
        </t:panelGrid>
     
        
<t:inputHidden forceId="true" id="pageIndexAdd" value="#{detailBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
<%-- Start css modification add two break line to UI --%>
<%-- End css modification add two break line to UI --%>
<t:panelGrid columns="3" forceId="true" id="buttonPanel" align="center">
    <h:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.availableListSize > 0}"  disabled="#{detailBeanName.selectedAvailableListSize == 0}"/> <f:verbatim>&nbsp; </f:verbatim>
    <%-- <h:commandButton id="cancel" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" /> --%>
    <t:panelGroup>
    <t:commandButton forceId="true" id="backButtonAddDiv" value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="add_div_function();" type="button" onblur="document.getElementById('searchText').focus();"/>                      
         <a4j:jsFunction name="add_div_function" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');" action="#{detailBeanName.cancelSearchAvailable}" reRender="divAdds,buttonPanel,scrollerPanel,searchText,searchPanel,errorConsole" />
     </t:panelGroup>                       
</t:panelGrid>
