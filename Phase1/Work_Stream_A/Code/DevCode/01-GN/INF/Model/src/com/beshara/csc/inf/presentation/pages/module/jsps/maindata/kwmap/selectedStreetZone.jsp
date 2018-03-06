<!--%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <t:panelGrid align="center" rendered="#{pageBeanName.selectedStreetZoneListSize != 0 }">
        <t:outputText forceId="true" id="titleLinkId1" styleClass="TitlePage" value="#{resourcesBundle.streetZones}  "/>
    </t:panelGrid>
    <t:panelGrid columns="4" border="0" width="100%" cellpadding="3" cellspacing="0" rowClasses="row01,row02">
        <t:outputText forceId="true" id="bank_code_title1" value="#{resourcesBundle.related_street_code}"
                      styleClass="lable01"/>
        <t:inputText forceId="true" id="bank_code1" disabled="true" value=" #{pageBeanName.selecteMapCode}"
                     styleClass="textboxsmall2"/>
        <t:outputText forceId="true" id="bank_Name_title1" value="#{resourcesBundle.related_street_name}"
                      styleClass="lable01"/>
        <t:inputText forceId="true" id="bank_Name1" disabled="true" value="#{pageBeanName.selecteMapName}"
                     styleClass="textboxtreelarge" style="width:255px"/>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="lov_dataT_data_panel1" rendered="#{pageBeanName.selectedStreetZoneListSize != 0 }"
                  style="display: block;max-height: 255px;min-height: 60px;overflow: auto;">
        <t:dataTable id="lov_dataT_data1" var="list" value="#{pageBeanName.lovBaseBean.myTableData}"
                     rowStyleClass="#{ pageBeanName.lovBaseBean.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.lovBaseBean.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="center" dir="false">
            <t:column id="check_lov_column1" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAllLov1"
                                             value="#{pageBeanName.lovBaseBean.checkAllFlag}">
                        <f:attribute name="checkAllLov" value="true"/>
                        <f:attribute name="listSizeLov" value="#{pageBeanName.selectedStreetZoneListSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedCheckboxsAll}"
                                     oncomplete="setAllDiv('checkAllLov1','chkLov1','listSizeLov','pageIndexLov1','noOfTableRowLov1');"
                                     reRender="lovDiv_btnsPnlGrd1"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chkLov1" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedCheckboxs}"
                                 oncomplete="checkCheckAllDiv('checkAllLov1','chkLov1','listSizeLov','pageIndexLov1','noOfTableRowLov1');"
                                 reRender="lovDiv_btnsPnlGrd1"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column>
                <f:facet name="header">
                    <t:outputText id="branch_code1" forceId="true" value="#{globalResources.Code}"></t:outputText>
                </f:facet>
                <t:outputText id="content_code1" value="#{list.code.streetCode}"/>
            </t:column>
            <t:column>
                <f:facet name="header">
                    <t:outputText id="branch_name1" forceId="true" value="#{globalResources.name}"></t:outputText>
                </f:facet>
                <t:outputText id="content_name1" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="lov_paging_panel1">
        <h:panelGrid id="lov_panelGrd_scroller1" columns="1" dir="#{shared_util.pageDirection}" styleclass="scroller"
                     rendered="#{pageBeanName.selectedStreetZoneListSize > shared_util.noOfTableRows}">
            <t:dataScroller id="lov_scroll_11" fastStep="5" pageCountVar="pageCount"
                            actionListener="#{pageBeanName.openCustomeLovDiv}" pageIndexVar="pageIndex" paginator="true"
                            paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage"
                            fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                            nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage"
                            paginatorActiveColumnClass="paging" paginatorActiveColumnStyle="font-weight:bold"
                            styleClass="textpage" immediate="false" for="lov_dataT_data1"
                            renderFacetsIfSinglePage="false">
                <f:facet name="first">
                    <t:panelGroup id="lov_jobs_list_panelGrp_first1">
                        <t:graphicImage id="lov_jobs_list_img_firstOn1" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_firstOff1" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <t:panelGroup id="lov_jobs_list_panelGrp_last1">
                        <t:graphicImage id="lov_jobs_list_img_lastOn1" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_lastOff1" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <t:panelGroup id="lov_jobs_list_panelGrp_prv1">
                        <t:graphicImage id="lov_jobs_list_img_prvOn1" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_prvOff1" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <t:panelGroup id="lov_jobs_list_panelGrp_nxt1">
                        <t:graphicImage id="lov_jobs_list_img_nxtOn1" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_nxtOff1" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <t:panelGroup id="lov_jobs_list_panelGrp_ffrwrd1">
                        <t:graphicImage id="jlov_obs_list_img_ffrwrdOn1" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_ffrwrdOff1" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <t:panelGroup id="lov_jobs_list_panelGrp_frwnd1">
                        <t:graphicImage id="lov_jobs_list_img_frwndOn1" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="lov_lov_jobs_list_img_frwndOff1" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        <t:inputHidden forceId="true" id="pageIndexLov1"
                       rendered="#{pageBeanName.selectedStreetZoneListSize >   shared_util.noOfTableRows}"/>
        <t:inputHidden forceId="true" id="noOfTableRowLov1" value="#{shared_util.noOfTableRows}"/>
    </t:panelGroup>
    <t:panelGrid columns="1" rendered="#{pageBeanName.selectedStreetZoneListSize == 0 }" styleClass="msg msg_no_icon"
                 align="center">
        <t:outputText value="#{globalResources.global_noTableResults}"/>
    </t:panelGrid>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonID1" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.removeStreetZone}"
                         onclick="return validatemyForm();"/>
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButton_LinkID1" onclick="backJsFunctionAdd(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunctionAdd" action="#{pageBeanName.cancelLink}"
                            oncomplete="hideLookUpDiv(window.blocker,window.customDiv2);"
                            reRender="divLinkLookup1,dataT_data_panel1,paging_panel1"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showSelectedLinkDiv}"/>
<t:saveState value="#{pageBeanName.selectedStreetZoneList}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.myTableData}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.selecteMapCode}"/>
<t:saveState value="#{pageBeanName.selecteMapName}"/>
<t:saveState value="#{pageBeanName.dtoDetails}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/-->