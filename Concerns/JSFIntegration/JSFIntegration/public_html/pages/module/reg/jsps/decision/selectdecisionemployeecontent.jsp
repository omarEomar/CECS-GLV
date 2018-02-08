<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
        
<t:panelGroup forceId="true" id="divAdds" styleClass="delDivSearchScroll">
<f:verbatim>

       <table border="0" width="99%" id="table5" cellspacing="0" cellpadding="0" align="center">
            </f:verbatim><f:verbatim><tr>
                <td valign="bottom" width="19">
                </f:verbatim><f:verbatim>
                <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                </f:verbatim><f:verbatim>
                <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                </f:verbatim><f:verbatim>
                <td valign="bottom" width="12">
                </f:verbatim><f:verbatim>
                <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
            </tr>
            <tr align="center">
                </f:verbatim><f:verbatim>
                <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                </f:verbatim><f:verbatim>
                <td valign="top" bgcolor="#FFFFFF" class="paddingbox">
    <div style="height: 78px; overflow: auto;" align="center">
    
    </f:verbatim>
            <t:panelGroup>
                <%--t:inputHidden forceId="true" id="noOfTableRows" value="#{shared_util.noOfTableRows}"/--%>
                <t:inputHidden forceId="true" id="arrayIdAdd" value=""/>
                <t:inputHidden forceId="true" id="listSizeAdd" value="#{detailBeanName.availableListSize}"/>
                <t:inputHidden forceId="true" id="pageIndexAdd" value="#{detailBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
            </t:panelGroup>
        <h:outputText styleClass="msg" rendered="#{detailBeanName.availableListSize == 0}" value="#{globalResources.global_noTableResults}"/>
                <t:dataTable id="dataT_available" var="list" value="#{detailBeanName.availableDetails}"     
                             rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                             renderedIfEmpty="false" binding="#{detailBeanName.availableDataTable}"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header"
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" >
                       
                    <t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                        <f:facet name="header">
                           <t:selectBooleanCheckbox forceId="true" id="checkAllAdd" value="#{detailBeanName.checkAllFlagAvailable}">
                                  <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');" reRender="selectedAvailableListSize,okBtn"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <t:selectBooleanCheckbox forceId="true" id="chk2Add" value="#{list.checked}">
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add');" reRender="selectedAvailableListSize,okBtn" />
                        </t:selectBooleanCheckbox>
                    </t:column>
                                       
                    <t:column id="code_column" width="30%">
                        <f:facet name="header">
                            <h:outputText id="header_code2" value="#{regResources.civil_id}"/>
                            <%--t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_code2" value="#{regResources.civil_id}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content_code" value="#{list.code.civilId}"/>
                    </t:column>
                    
                    <t:column id="minName_column" width="65%">
                        <f:facet name="header">
                            <h:outputText id="header_minName" value="#{regResources.select_decision_employees_emp_name}"/>
                            <%--t:commandSortHeader id="minNameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_minName" value="#{regResources.select_decision_employees_emp_name}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content_minName" value="#{list.citizensResidentsDTO.fullName}"/>
                    </t:column>
                </t:dataTable>
                <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
                <f:verbatim>
 </div>
         </td>
            <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
        </tr>
        <tr></f:verbatim><f:verbatim>
            <td valign="top" width="19">
            </f:verbatim><f:verbatim>
            <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
            </f:verbatim><f:verbatim>
            <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
            </f:verbatim><f:verbatim>
            <td valign="top">
            <img border="0" alt="" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
        </tr>
   </table>
 </f:verbatim>
        <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
            
          <t:dataScroller id="scroll_1add" binding="#{detailBeanName.dataScroller}"
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
            </t:panelGroup>
            <f:verbatim><br/></f:verbatim>            
            <t:panelGroup forceId="true" id="okBtn">
               <h:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" immediate="true" onclick="return confirmCheckBoxSelection('chk2Add');" /> <f:verbatim>&nbsp; </f:verbatim><%--rendered="#{detailBeanName.selectedAvailableListSize > 0}"--%>
            </t:panelGroup>
            <t:commandButton id="cancel" value="#{globalResources.CancelButton}" action="#{detailBeanName.cancel}" styleClass="cssButtonSmall" immediate="true"/><%--ignoremyFormValidation();submit();--%>
        