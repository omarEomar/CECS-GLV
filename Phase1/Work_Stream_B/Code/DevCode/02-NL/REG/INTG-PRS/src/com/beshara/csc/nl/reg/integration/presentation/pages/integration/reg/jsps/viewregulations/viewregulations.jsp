<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="integrateResources"/>
<htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/integration/reg/js/integration.js"/>
<t:messages showDetail="true"/>
<t:panelGroup forceId="true" id="Reg_dataTableDetailPanel" styleClass="fullWidthScroll270 ">
    <t:dataTable id="Reg_dataT_dataDetail" var="list" binding="#{pageBeanName.viewRegulationsBean.myDataTable}"
                 value="#{appMainLayout.manyToMany ? detailBeanName.viewRegulationsBean.myTableData : pageBeanName.viewRegulationsBean.myTableData}"
                 forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="false" rows="4">
        <t:column id="Reg_code_Detail_column" sortable="false" width="7%">
            <f:facet name="header">
                <h:outputText id="Reg_header_Detail_code" value="#{integrateResources.join_reg_type}"/>
            </f:facet>
            <h:outputText id="Reg_content_Detail_code" value="#{list.typesDto.name}"/>
        </t:column>
        <t:column id="Reg_name_Detail_column" sortable="false" width="7%">
            <f:facet name="header">
                <h:outputText id="Reg_header_Detail_name" value="#{integrateResources.join_reg_issuance_year}"/>
            </f:facet>
            <h:outputText id="Reg_content_Detail_name" value="#{list.yearsDto.code.key}"/>
        </t:column>
        <t:column id="Reg_regulation_number_Detail_column" sortable="false" width="7%">
            <f:facet name="header">
                <h:outputText id="Reg_header_regulation_number"
                              value="#{integrateResources.join_reg_regulation_number}"/>
            </f:facet>
            <h:outputText id="Reg_content_regulation_number" value="#{list.code.regulationNumber}"/>
        </t:column>
        <t:column id="Reg_regulation_description_Detail_column" sortable="false" width="29%">
            <f:facet name="header">
                <h:outputText id="Reg_header_regulation_description"
                              value="#{integrateResources.join_reg_regulation_title}"/>
            </f:facet>
            <h:outputText id="Reg_content_regulation_description" value="#{list.regulationTitle}"/>
        </t:column>
        <t:column id="Reg_regulation_decision_maker_Detail_column" sortable="false" width="20%">
            <f:facet name="header">
                <h:outputText id="Reg_header_regulation_decision_maker"
                              value="#{integrateResources.join_reg_regulation_decision_maker}"/>
            </f:facet>
            <h:outputText id="Reg_content_regulation_decision_maker" value="#{list.decisionMakerDTO.name}"/>
        </t:column>
        <t:column id="Reg_reg_date_Detail_column" sortable="false" width="10%">
            <f:facet name="header">
                <h:outputText id="Reg_header_Reg_reg_date" value="#{integrateResources.join_reg_reg_date}"/>
            </f:facet>
            <h:outputText id="Reg_content_Reg_reg_date" converter="TimeStampConverter" value="#{list.regulationDate}"/>
        </t:column>
        <t:column id="Reg_apply_date_Detail_column" sortable="false" width="10%">
            <f:facet name="header">
                <h:outputText id="Reg_content_Reg_apply_date" value="#{integrateResources.join_reg_apply_date}"/>
            </f:facet>
            <h:outputText id="Reg_header_Reg_apply_date" converter="TimeStampConverter"
                          value="#{list.regulationAppliedDate}"/>
        </t:column>
        <t:column id="Reg_view_details_btn_column" sortable="false" width="10%">
            <f:facet name="header">
                <h:outputText id="view_details_btn" value="#{integrateResources.join_reg_view_details}"/>
            </f:facet>
            <a4j:commandButton styleClass="cssButtonSmall" id="view_regulation_details" value="..." 
                               actionListener="#{regDetails.openRegDetails}" style="min-width: 35px ! important;"
                               reRender="view_regIntgHiddenValues" oncomplete="openRegulationDivWindow('viewRegIntgFullURLId','viewRegIntgWindowTitleId');">
                
                <f:param name="regType" value="#{(list.code != null && list.code.regtypeCode != null) ? list.code.regtypeCode : '' }"/>
                <f:param name="regYear" value="#{(list.code != null && list.code.regyearCode != null) ? list.code.regyearCode : '' }"/>
                <f:param name="regNum" value="#{(list.code != null && list.code.regulationNumber != null) ? list.code.regulationNumber : '' }"/>
                
            </a4j:commandButton>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1"
                 rendered="#{appMainLayout.manyToMany ? empty detailBeanName.viewRegulationsBean.myTableData : empty pageBeanName.viewRegulationsBean.myTableData}"
                 align="center">
        <t:outputText value="#{globalResources.NO_RELATED_REGULATIONS_MSG}" styleClass="msg"/>
    </t:panelGrid>
    <t:panelGrid columns="1" forceId="true" id="viewIntegratescrollerPanel" style="text-align:center; width: 100%;">
            <t:panelGrid id="viewIntegratepanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}"
                         styleClass="scroller" width="300px"
                         rendered="#{appMainLayout.manyToMany ? detailBeanName.viewRegulationsBean.listSize > 2 : pageBeanName.viewRegulationsBean.listSize > 2}">
                <t:dataScroller id="viewIntegratescroll_1add"
                                actionListener="#{appMainLayout.manyToMany ? detailBeanName.viewRegulationsBean.scrollerAction : pageBeanName.viewRegulationsBean.scrollerAction}"
                                fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true"
                                paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage"
                                fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                                nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage"
                                paginatorActiveColumnClass="paging"
                                paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                                styleClass="textpage" immediate="false" for="Reg_dataT_dataDetail"
                                renderFacetsIfSinglePage="false">
                    <f:facet name="first">
                        <t:panelGroup id="viewIntegratereq_list_panelGrp_first">
                            <t:graphicImage id="viewIntegratereq_list_img_firstOn" rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_first}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                            border="0"/>
                            <t:graphicImage id="viewIntegratereq_list_img_firstOff" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                            border="0"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="last">
                        <t:panelGroup id="viewIntegratereq_list_panelGrp_last">
                            <t:graphicImage id="viewIntegratereq_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="viewIntegratereq_list_img_lastOff" onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="previous">
                        <t:panelGroup id="viewIntegratereq_list_panelGrp_prv">
                            <t:graphicImage id="viewIntegratereq_list_img_prvOn" rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="viewIntegratereq_list_img_prvOff" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="next">
                        <t:panelGroup id="viewIntegratereq_list_panelGrp_nxt">
                            <t:graphicImage id="viewIntegratereq_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="viewIntegratereq_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="fastforward">
                        <t:panelGroup id="viewIntegratereq_list_panelGrp_ffrwrd">
                            <t:graphicImage id="viewIntegratereq_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="viewIntegratereq_list_img_ffrwrdOff" onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="fastrewind">
                        <t:panelGroup id="viewIntegratereq_list_panelGrp_frwnd">
                            <t:graphicImage id="viewIntegratereq_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="viewIntegratereq_list_img_frwndOff" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                        </t:panelGroup>
                    </f:facet>
                </t:dataScroller>
            </t:panelGrid>
        </t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="1" id="btnPnl" align="center">
    <t:commandButton type="button" id="backButtonIntegrationDiv1" forceId="true"
                       onclick="hidTreeDiv('add',window.blocker,window.integrationDiv1,'successMsgTreeCorrect');"
                       value="#{globalResources.back}" styleClass="cssButtonSmall"/>
</t:panelGrid>
<t:panelGroup forceId="true" id="view_regIntgHiddenValues">
    <t:inputHidden forceId="true" id="viewRegIntgFullURLId"
                   value="#{regDetails.fullURL}"/>
    <t:outputText forceId="true" id="viewRegIntgWindowTitleId" value="#{integrateResources.reg_dtls_title}"
                  style="display:none;"/>
</t:panelGroup>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.viewRegulationsBean.myTableData : pageBeanName.viewRegulationsBean.myTableData}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.viewRegulationsBean.beanName : pageBeanName.viewRegulationsBean.beanName}"/>
