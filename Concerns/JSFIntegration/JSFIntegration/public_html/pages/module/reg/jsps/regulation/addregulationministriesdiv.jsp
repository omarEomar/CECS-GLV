<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
<t:panelGroup forceId="true" id="divAddLookup">
<f:verbatim>
<table>
    </f:verbatim><f:verbatim>
    <tr>
        <td align="center">
             <table border="0" width="580px" id="table5" cellspacing="0" cellpadding="0">
                </f:verbatim><f:verbatim>
                <tr>
                    <td valign="bottom" width="19">
                        </f:verbatim><f:verbatim>
                        <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                    <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                    </f:verbatim><f:verbatim>
                    <td valign="bottom" width="12">
                        </f:verbatim><f:verbatim>
                        <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                </tr>
                </f:verbatim><f:verbatim>
                <tr>
                    <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                    </f:verbatim><f:verbatim>
                    <td valign="top" bgcolor="#FFFFFF" class="paddingbox">   
                    </f:verbatim>
        
                        <t:panelGrid id="pnlgrd_reg_parent_div" columns="3"  align="center" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
                                <!--- Start of Row 1-->
                                <%--t:panelGrid width="70px"></t:panelGrid>
                                <f:verbatim></f:verbatim>
                                    <t:selectOneRadio id="radioButton" value="#{detailBeanName.govType}" converter="javax.faces.Long">
                                        <a4j:support event="onclick" action="#{detailBeanName.fillCategories}" reRender="divAdds,maintain_regType"/>
                                        <f:selectItem  itemLabel="#{regResources.regulation_ministries_gov}" itemValue="#{detailBeanName.categoryGov}"/>
                                        <f:selectItem  itemLabel="#{regResources.regulation_ministries_non_gov}" itemValue="#{detailBeanName.categoryNonGov}"/>
                                    </t:selectOneRadio--%>
                                <!--- End of Row 1-->
                                <!--- Start of Row 2-->
                                    <t:panelGrid width="70px"></t:panelGrid>
                                    <h:outputText value="#{regResources.regulation_ministries_f2eah}"/>
                                    <t:selectOneMenu forceId="true" id="maintain_regType" styleClass="DropdownboxMedium2" value="#{detailBeanName.searchDto.code1}" converter="javax.faces.Long" >
                                        <a4j:support event="onchange" action="#{detailBeanName.searchAvailable}" reRender="divAdds,okBtn"/>
                                        <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValue}" itemLabel="#{regResources.select}"/>
                                        <t:selectItems value="#{detailBeanName.categories}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                                    </t:selectOneMenu>
                                <!--- End of Row 2-->
                          </t:panelGrid>
                          <%--t:panelGrid columns="1" align="center" id="search_buttons" forceId="true">
                                <a4j:commandButton action="#{detailBeanName.searchAvailable}" reRender="divAdds,search_buttons,okBtn"  styleClass="ManyToManySearchButton" onclick="ignoremyFormValidation();" disabled="#{detailBeanName.searchDto.code1 == detailBeanName.itemSelectionRequiredValue}"/>
                          </t:panelGrid--%>
                      
                    <f:verbatim>
                    </td>
                    <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                </tr>
                </f:verbatim><f:verbatim>
                <tr>
                    <td valign="top" width="19">
                        </f:verbatim><f:verbatim>
                        <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
                    <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                    </f:verbatim><f:verbatim>
                    <td valign="top">
                        <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
                </tr>
            </table>
        </td>
    </tr>
    </f:verbatim><f:verbatim>
</table>
</f:verbatim>
<t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsg" />
<t:panelGroup forceId="true" id="divAdds" styleClass="delDivSearchScroll">
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
            <t:selectBooleanCheckbox forceId="true" id="chk2Add" value="#{list.checked}"  onkeypress="FireButtonClick('myForm:ok');">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add')" reRender="selectedAvailableListSize,okBtn" />
            </t:selectBooleanCheckbox>
        </t:column>
        
        <t:column id="minName_column" width="95%">
            <f:facet name="header">
                <%--t:commandSortHeader id="minNameColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet--%>
                    <h:outputText id="header_minName" value="#{regResources.regulation_ministries_minName}"/>
                <%--/t:commandSortHeader--%>
            </f:facet>
          <h:outputText id="content_minName" value="#{list.name}"/>
        </t:column>
    </t:dataTable>
    <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
              
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
<%-- Start css modification add two break line to UI --%>
<f:verbatim>
<br/>
</f:verbatim>
<%-- End css modification add two break line to UI --%>
    <t:panelGroup forceId="true" id="okBtn"> 
        <h:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.selectedAvailableListSize > 0}" /> <f:verbatim>&nbsp; </f:verbatim>
    </t:panelGroup>
    <t:commandButton forceId="true" id="backButtonAddDiv" onblur="setFocusAtMyAddDiv();" type="button" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');doValidatemyForm=true;" styleClass="cssButtonSmall" />
</t:panelGroup>
