<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>






<t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsg" />
<t:panelGroup forceId="true" id="divAdds" styleClass="delDivScroll">
<h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" rendered="#{detailBeanName.availableListSize == 0}" />
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
                        <f:facet name="header">
                           <t:selectBooleanCheckbox forceId="true" id="checkAllAdd" value="#{detailBeanName.checkAllFlagAvailable}">
                                  <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');" reRender="selectedAvailableListSize,btn_pnlgrp"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>                       
                        <t:selectBooleanCheckbox forceId="true" id="chk2Add" value="#{list.checked}">
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add')" reRender="selectedAvailableListSize,btn_pnlgrp" />
                        </t:selectBooleanCheckbox>
                    </t:column>
                    
                    <t:column id="civilid_column" sortable="true" width="20%">
                        <f:facet name="header">
                            <%--t:commandSortHeader id="typeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet--%>
                                <h:outputText id="header_type" value="#{regResources.civil_id}"/>
                            <%--/<%--t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content_civilid" value="#{list.employeesDTO.code.key}"/>
                    </t:column>
                    
                    <t:column id="year_column" sortable="true" width="75%">
                        <f:facet name="header">
                            <%--t:commandSortHeader id="yearColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet--%>
                                <h:outputText id="header_year" value="#{regResources.employee_name}"/>
                            <%--/<%--t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.employeesDTO.citizensResidentsDTO.firstName} #{list.employeesDTO.citizensResidentsDTO.secondName} #{list.employeesDTO.citizensResidentsDTO.thirdName} #{list.employeesDTO.citizensResidentsDTO.lastName}"/>
                    </t:column>
                    
                </t:dataTable>
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
              
            </t:dataScroller>
        </h:panelGrid>
      <t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
                    </t:panelGroup>
                    <%-- Start css modification add two break line to UI --%>
                    <f:verbatim>
                    <br/>
                   </f:verbatim>
                   <%-- End css modification add two break line to UI --%>
                   <t:panelGroup id="btn_pnlgrp" forceId="true">
                    <h:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.selectedAvailableListSize > 0}" /> <f:verbatim>&nbsp; </f:verbatim>
                    <h:commandButton id="cancel" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" />
                  </t:panelGroup>
                  

