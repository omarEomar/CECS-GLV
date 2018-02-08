<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
<t:saveState value="#{regulationModulesMaintainBean.columns}"/>
<t:saveState value="#{regulationModulesMaintainBean.columnNameSelected}"/>
<t:saveState value="#{regulationModulesMaintainBean.searchStatus}"/>
<t:saveState value="#{regulationModulesMaintainBean.columnNamesValue}"/>
<t:saveState value="#{regulationModulesMaintainBean.selectedModuleKey}"/>    
    <t:saveState value="#{regulationModulesMaintainBean.modules}"/>    
    <t:saveState value="#{regulationModulesMaintainBean.selectedTableKey}"/>    
    <t:saveState value="#{regulationModulesMaintainBean.tables}"/>    
    <t:saveState value="#{regulationModulesMaintainBean.customAvailableDetails}"/>    
    <t:saveState value="#{regulationModulesMaintainBean.selectedAvailableDetailsRowIndices}"/> 
    <t:saveState value="#{regulationModulesMaintainBean.customAvailableDetailsSize}"/> 
    <t:saveState value="#{regulationModulesMaintainBean.moduleRelations}"/> 
    <t:saveState value="#{regulationModulesMaintainBean.customCurrentDetails}"/> 
    <t:saveState value="#{decisionEmployeesMaintainBean.empDecisionsDTOSlelected}"/>
    <t:saveState value="#{decisionEmployeesMaintainBean.empDecisionsDTOSlelectedList}"/>
<t:panelGroup id="addregmodule" forceId="true">
<t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"  columnClasses="Column1,Column2,Column1,Column2">     
    <!--- Start of Row 1-->
        <t:outputText value="#{regResources.regulation_modules_system_name}"/>
        <t:selectOneMenu forceId="true"  id="system_name" styleClass="DropdownboxMedium2" onblur="setFocusOnlyOnElement('system_tables');"  value="#{regulationModulesMaintainBean.selectedModuleKey}" >
            <a4j:support event="onchange" action="#{regulationModulesMaintainBean.fillTables}" reRender="system_tables,data_table_panel_group,okCancelBtn,SearchWithPanal"/>        
            <f:selectItem itemValue="" itemLabel="#{regResources.select}"/>
            <t:selectItems value="#{regulationModulesMaintainBean.modules}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>
        </t:selectOneMenu>
        <t:outputText value="#{regResources.regulation_modules_table}"/>            
        <t:selectOneMenu forceId="true"  id="system_tables" styleClass="DropdownboxMedium2" value="#{regulationModulesMaintainBean.selectedTableKey}">
            <a4j:support event="onchange" action="#{regulationModulesMaintainBean.fillData}" reRender="data_table_panel_group,okCancelBtn,system_tables_col"/>        
            <f:selectItem itemValue="" itemLabel="#{regResources.select}"/>
            <t:selectItems value="#{regulationModulesMaintainBean.tables}" itemLabel="#{entry.tableDesc}" itemValue="#{entry.code.key}" var="entry"/>              
    </t:selectOneMenu>
    <t:outputText value="#{globalResources.SearchWith}"/>
    <t:panelGroup colspan="3" id="SearchWithPanal" forceId="true">
        <t:selectOneMenu forceId="true"  id="system_tables_col" styleClass="DropdownboxMedium2" value="#{regulationModulesMaintainBean.columnNameSelected}">
            <f:selectItem itemValue="" itemLabel="#{regResources.select}"/>
            <t:selectItems value="#{regulationModulesMaintainBean.columns}" itemLabel="#{entry.columnDesc}" itemValue="#{entry.columnName}" var="entry"/>              
        </t:selectOneMenu>
        <t:outputText value="   " />
        <t:inputText value="#{regulationModulesMaintainBean.columnNamesValue}" styleClass="textbox"/>
        <t:outputText value="      " />
        <t:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall" >
            <a4j:support event="onclick" action="#{regulationModulesMaintainBean.setSearchMode}" reRender="data_table_panel_group,okCancelBtn,system_tables_col,SearchWithPanal"/>
        </t:commandButton>
        <t:outputText value="   " />
        <t:commandButton type="button" value="#{globalResources.cancelsearch}" styleClass="cssButtonSmall" rendered="#{regulationModulesMaintainBean.searchStatus}">
            <a4j:support event="onclick" action="#{regulationModulesMaintainBean.cancelSearchMode}" reRender="data_table_panel_group,okCancelBtn,system_tables_col,SearchWithPanal"/>
        </t:commandButton>
    </t:panelGroup>
    <!--- End of Row 2-->
       
</t:panelGrid>
<t:panelGroup forceId="true" id="data_table_panel_group" style="width:100%">
<f:verbatim>
        <div style="height: 110px; overflow: auto;" >
</f:verbatim>
<h:outputText styleClass="msg" rendered="#{regulationModulesMaintainBean.customAvailableDetailsSize == 0}" value="#{globalResources.global_noTableResults}"/>
  
   <t:dataTable id="dataT_available" var="list" value="#{regulationModulesMaintainBean.customAvailableDetails}"     
                             rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                              binding="#{regulationModulesMaintainBean.availableDataTable}" renderedIfEmpty="false"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header" 
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" >
                                       
                    <t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                        <f:facet name="header">
                           <%--t:selectBooleanCheckbox forceId="true" id="checkAllAdd" value="#{regulationModulesMaintainBean.checkAllFlagAvailable}">
                                  <a4j:support event="onclick" actionListener="#{regulationModulesMaintainBean.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');" reRender="okCancelBtn"/>
                            </t:selectBooleanCheckbox--%>
                        </f:facet>                       
                        <t:selectBooleanCheckbox forceId="true" id="chk2Add" value="#{list[0]}">
                            <a4j:support event="onclick" actionListener="#{regulationModulesMaintainBean.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add')" reRender="okCancelBtn" />
                        </t:selectBooleanCheckbox>
                    </t:column>
                    
                    <%--t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                        <f:facet name="header">
                          <h:outputText id="test_column" value="c1" />
                        </f:facet>                       
                          <h:outputText id="test_column_data" value="#{list.name}" />                       
                    </t:column--%>
                           
            </t:dataTable>  
			<h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            <f:verbatim>
                </div>
            </f:verbatim>
             <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{regulationModulesMaintainBean.customAvailableDetailsSize > shared_util.noOfTableRows}">
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
        <t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{regulationModulesMaintainBean.customAvailableDetailsSize > shared_util.noOfTableRows}" />
      
            </t:panelGroup>
            <t:panelGrid forceId="true" id="okCancelBtn" columns="2">
            
            <t:panelGroup forceId="true" id="okBtn" rendered="#{regulationModulesMaintainBean.selectedAvailableDetailsCount == 1}" >
                <h:commandButton id="ok_" value="#{globalResources.ok}" action="#{decisionEmployeesMaintainBean.empDecisionsDTOModule}" styleClass="cssButtonSmall" onclick="ignoremyFormValidation();ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" /> 
            </t:panelGroup>
            
            <%-- modifed by m.elsaied change request
            <h:commandButton id="cancel_" type="button" value="#{globalResources.CancelButton}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" />
             --%>
            <h:commandButton id="cancel_" type="button" onblur="setFocusOnlyOnElement('system_name');" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" />
            </t:panelGrid>
</t:panelGroup>



  
