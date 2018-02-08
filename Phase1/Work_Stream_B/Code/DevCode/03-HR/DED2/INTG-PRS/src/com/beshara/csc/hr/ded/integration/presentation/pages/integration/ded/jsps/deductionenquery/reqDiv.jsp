<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<style type="text/css">
#header_customDIV{width : 790px !important;}
#customDiv{width : 800px !important;margin-right:45px !important;}
</style>

<t:panelGroup id="header_customDIV" forceId="true" styleClass="customDiv_header">
    <t:outputText value="#{resourcesBundle.dedReqAsResultOfTitle}" rendered="#{dedEnquiryRequestBean.pageMode==1}"/>
    <t:outputText value="#{resourcesBundle.reqsBasedOndedTitle}" rendered="#{dedEnquiryRequestBean.pageMode==2}"/>
</t:panelGroup>
<t:panelGroup styleClass="customDiv_body" style="width: 740px;">
    <t:panelGroup styleClass="customDiv_body_contents">
        <t:panelGroup forceId="true" id="reqDiv">
            <t:panelGroup forceId="true" id="ReqPanelGroup"
                          rendered="#{dedEnquiryRequestBean.pageMode==2}">
                <t:dataTable id="reqDed_datatable" forceId="true" var="item"
                             value="#{dedEnquiryRequestBean.requestList}" preserveDataModel="false"
                             forceIdIndexFormula="#{item.id}" rowIndexVar="Index"
                             binding="#{dedEnquiryRequestBean.myDataTable}" footerClass="grid-footer"
                             styleClass="table table-bordered table-hover" headerClass="table_header"
                             rowClasses="raw_odd,raw_even"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                             align="top" dir="rtl" rows="#{constantsbean.minNoOfPages}" renderedIfEmpty="false"  rowOnMouseOver="javascript:addRowHighlight('myForm:my_datatable');">
                    <t:column id="div_datatable_check_column" width="4%">
                        <f:facet name="header"></f:facet>
                        <t:selectOneRadio id="div_datatable_chk" onkeyup="dataTableSelectOneRadio(this);"
                                          onmousedown="dataTableSelectOneRadio(this);">
                            <f:selectItem itemLabel="" itemValue="#{item.key}"/>
                            <a4j:support event="onclick" id="radiobtnReqDiv"
                                         actionListener="#{dedEnquiryRequestBean.obtainSelectedRow}"
                                         reRender="buttonsGridId"/>
                        </t:selectOneRadio>
                    </t:column>
                    <t:column id="div_datatable_reqDed" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_reqDedlabel" value="#{resourcesBundle.reqCode}"/>
                        </f:facet>
                        <t:outputText value="#{item.id}" id="reqDed"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_id" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_idlabel" value="#{resourcesBundle.requesttype}"/>
                        </f:facet>
                        <t:outputText value="#{item.requestTypeName}" id="div_datatable_dedIdValue"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_dedName" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_dedNamelabel" value="#{resourcesBundle.reasonId}"/>
                        </f:facet>
                        <t:outputText value="#{item.reasonName}" id="div_datatable_dedNameValue"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_totalDeducted" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_totalDeductedlabel" value="#{resourcesBundle.requestDate}"/>
                        </f:facet>
                        <t:outputText value="#{item.requestDate}" id="totalDeducted"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_installmentValue" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_installmentValuelabel"
                                          value="#{resourcesBundle.makerMinistryName}"/>
                        </f:facet>
                        <t:outputText value="#{item.issuerCategoryName}/#{item.issuerMinistryName}"
                                      id="installmentValue"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_installmentsCount" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_installmentsCountlabel"
                                          value="#{resourcesBundle.requestStatus}"/>
                        </f:facet>
                        <t:outputText value="#{dedEnquiryRequestBean.statusValue}" id="installmentsCount"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_dedStartDate" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_dedStartDatelabel"
                                          value="#{resourcesBundle.executionDate}"/>
                        </f:facet>
                        <t:outputText value="#{item.executionDate}" id="dedStartDate"></t:outputText>
                    </t:column>
                    <t:column id="div_datatable_ignoreDate" styleClass="column_medium">
                        <f:facet name="header">
                            <t:outputText id="div_datatable_ignoreDatelabel" value="#{resourcesBundle.ignoreDate}"/>
                        </f:facet>
                        <t:outputText value="#{item.ignoreDate}" id="ignoreDate"></t:outputText>
                    </t:column>
                </t:dataTable>
                <t:panelGrid id="_grid_msg"
                             rendered="#{empty dedEnquiryRequestBean.requestList && (dedEnquiryRequestBean.pageMode==2) }"
                             styleClass="empty_table_msg" forceId="true">
                    <t:outputText id="div_datatable_empty_msg" value="#{resourcesBundle.noresultfound}" forceId="true"/>
                </t:panelGrid>
            </t:panelGroup>
            <t:panelGroup style="width:500px" forceId="true" id="divDatascrollGroup"
                          rendered="#{dedEnquiryRequestBean.pageMode==2}">
                <t:dataScroller id="div_paging_scroll_paging" fastStep="5" pageCountVar="pageCount"
                                pageIndexVar="pageIndex" paginator="true"
                                binding="#{dedEnquiryRequestBean.dataScroller}"
                                actionListener="#{dedEnquiryRequestBean.changePageIndex}" paginatorMaxPages="5"
                                fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="pagination_item"
                                lastStyleClass="pagination_item pagination_item_last" nextStyleClass="pagination_item"
                                previousStyleClass="pagination_item" paginatorColumnClass="pagination_item"
                                paginatorActiveColumnClass="pagination_item item_active"
                                styleClass="pagination-centered" immediate="false" for="reqDed_datatable"
                                renderFacetsIfSinglePage="false" style="margin-right:250px">
                    <f:facet name="first">
                        <t:panelGroup id="div_paging_panelGrp_first_paging">
                            <t:graphicImage id="div_list_img_firstOn_paging" rendered="#{pageIndex > 1}" title="الأول"
                                            url="/resources/images/first_page.png" border="0"/>
                            <t:graphicImage id="div_paging_list_img_firstOff_paging" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/resources/images/first_page_disabled.png" border="0"
                                            styleClass="pagination_item_disabled"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="last">
                        <t:panelGroup id="div_paging_panelGrp_last_paging">
                            <t:graphicImage id="div_list_img_lastOn_paging" rendered="#{pageIndex &lt; pageCount}"
                                            title="الأخير" url="/resources/images/last_page.png" border="0"/>
                            <t:graphicImage id="div_paging_list_img_lastOff_paging" onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/resources/images/last_page_disabled.png" border="0"
                                            styleClass="pagination_item_disabled"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="previous">
                        <t:panelGroup id="div_paging_list_panelGrp_prv_paging">
                            <t:graphicImage id="div_paging_list_img_prvOn_paging" rendered="#{pageIndex > 1}"
                                            title="السابق" url="/resources/images/prev_page.png" border="0"/>
                            <t:graphicImage id="div_paging_list_img_prvOff_paging" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}"
                                            url="/resources/images/prev_page_disabled.png" border="0"
                                            styleClass="pagination_item_disabled"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="next">
                        <t:panelGroup id="div_paging_list_panelGrp_nxt_paging">
                            <t:graphicImage id="div_paging_list_img_nxtOn_paging" rendered="#{pageIndex &lt; pageCount}"
                                            title="التالى" url="/resources/images/next_page.png" border="0"/>
                            <t:graphicImage id="div_paging_list_img_nxtOff_paging" rendered="#{pageIndex >= pageCount}"
                                            url="/resources/images/next_page_disabled.png" border="0"
                                            styleClass="pagination_item_disabled"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="fastforward">
                        <t:panelGroup id="div_paging_list_panelGrp_ffrwrd_paging">
                            <t:graphicImage id="div_paging_list_img_ffrwrdOn_paging"
                                            rendered="#{pageIndex &lt; pageCount}" title="" url="" border="0"/>
                            <t:graphicImage id="div_paging_list_img_ffrwrdOff_paging" onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}" url="" border="0"/>
                        </t:panelGroup>
                    </f:facet>
                    <f:facet name="fastrewind">
                        <t:panelGroup id="div_paging_list_panelGrp_frwnd_paging">
                            <t:graphicImage id="div_paging_list_img_frwndOn_paging" rendered="#{pageIndex > 1}" title=""
                                            url="" border="0"/>
                            <t:graphicImage id="div_paging_list_img_frwndOff_paging" onclick="return false;"
                                            rendered="#{pageIndex &lt;= 1}" url="" border="0"/>
                        </t:panelGroup>
                    </f:facet>
                </t:dataScroller>
            </t:panelGroup>
            <t:panelGrid columns="4" id="deducationResGrid2" width="100%"
                         rendered="#{dedEnquiryRequestBean.pageMode==1}" cellpadding="3" cellspacing="0" rowClasses="row01,row02">
                <!-- first row -->
                <t:outputText value="#{resourcesBundle.requesttype}" styleClass="output_label nowraped_txt"/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.requestTypeName}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.reasonId}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.reasonName}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.reqCode}" styleClass="output_label nowraped_txt"/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.id}" disabled="true" styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.requestDate}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.requestDate}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.makerCategoryName}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.issuerCategoryName}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <!-- Third row -->
                <t:outputText value="#{resourcesBundle.makerMinistryName}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.issuerMinistryName}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.requestStatus}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.requestStatusName}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.executionDate}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.executionDate}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.realDeductionRef}" styleClass="output_label "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.transactionNo}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.makerRef}" styleClass="output_label nowrap_txt"/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.issuerReference}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.collectiveDedRefLabel}" styleClass="output_label nowrap_txt"/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.groupReference}" disabled="true"
                                 styleClass="margin_t5"/>
                </t:panelGroup>
                <t:outputText value="#{resourcesBundle.requestAsBuildOnRequestTitle}" styleClass="output_label nowrap_txt "/>
                <t:panelGroup>
                    <t:inputText value="#{dedEnquiryRequestBean.dto.reqRef}" disabled="true" styleClass="margin_t5"/>
                </t:panelGroup>
            </t:panelGrid>
            <t:panelGrid columns="2" style="margin-right:auto;margin-left:auto;margin-top:10px;margin-bottom:10px;"
                         forceId="true" id="buttonsGridId">
                <t:commandButton value="#{resourcesBundle.requestEnquiry}"
                                 action="#{dedEnquiryRequestBean.redirectRequestViewDetailsPage}"
                                 disabled="#{dedEnquiryRequestBean.reqID == null || dedEnquiryRequestBean.reqID ==''}"
                                 styleClass="btn btn-info"/>
                <t:commandButton value="#{resourcesBundle.return}" action="" styleClass="btn btn-info"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGroup>
</t:panelGroup>
<t:saveState value="#{dedEnquiryRequestBean.pageMode}"/>
<t:saveState value="#{dedEnquiryRequestBean.reqID}"/>
<t:saveState value="#{dedEnquiryRequestBean.reqType}"/>
<t:saveState value="#{dedEnquiryRequestBean.issuerGov}"/>
<t:saveState value="#{dedEnquiryRequestBean.enableOnSelect}"/>
<t:saveState value="#{dedEnquiryRequestBean.secandaryGuideId}"/>
<t:saveState value="#{dedEnquiryRequestBean.requestList}"/>
<t:saveState value="#{deductionEnquiryBean.searchCriteria}"/>
<%-- <t:outputText value="#{resourcesBundle.elementGuideId}" styleClass="output_label"/> <t:panelGroup> <t:inputText
     value="#{dedEnquiryRequestBean.dto.elementGuideName}" disabled="true" styleClass="margin_t5" /> </t:panelGroup>
     <t:outputText value="#{resourcesBundle.secandaryGuideId}" styleClass="output_label "/> <t:panelGroup> <t:inputText
     value="#{dedEnquiryRequestBean.dto.name}" disabled="true" styleClass="margin_t5" /> </t:panelGroup> <t:outputText
     value="#{resourcesBundle.empCategoryName}" styleClass="output_label "/> <t:panelGroup> <t:inputText
     value="#{dedEnquiryRequestBean.dto.empCategoryName}" disabled="true" styleClass="margin_t5"/> </t:panelGroup>
     <t:outputText value="#{resourcesBundle.empMinistryName}" styleClass="output_label "/> <t:panelGroup> <t:inputText
     value="#{dedEnquiryRequestBean.dto.empMinistryName}" disabled="true" styleClass="margin_t5" /> </t:panelGroup> <!--
     Fourth row --> <t:outputText value="#{resourcesBundle.decisionNo}" styleClass="output_label "/> <t:panelGroup>
     <t:inputText value="#{dedEnquiryRequestBean.dto.decisionNo}" disabled="true" styleClass="margin_t5" />
     </t:panelGroup> <t:outputText value="#{resourcesBundle.decisionDate}" styleClass="output_label "/> <t:panelGroup >
     <t:inputText value="#{dedEnquiryRequestBean.dto.decisionDate}" disabled="true" styleClass="margin_t5" />
     </t:panelGroup> <t:outputText value="#{resourcesBundle.empCivilId}" styleClass="output_label "/> <t:panelGroup>
     <t:inputText value="#{dedEnquiryRequestBean.dto.empCivilId}" disabled="true" styleClass="margin_t5"/>
     </t:panelGroup> <!-- Fifth row --> <t:outputText value="#{resourcesBundle.empName}" styleClass="output_label "/>
     <t:panelGroup > <t:inputText value="#{dedEnquiryRequestBean.dto.empName}" disabled="true" styleClass="margin_t5" />
     </t:panelGroup>--%>
<%-- <t:outputText value="#{resourcesBundle.notes}" styleClass="output_label "/> <t:panelGroup colspan="5"> <t:inputText
     value="#{dedEnquiryRequestBean.dto.notes}" style="width:369px;" disabled="true" styleClass="margin_t5" />
     </t:panelGroup>--%>
<%-- <t:outputText value="#{resourcesBundle.ignoreDate}" styleClass="output_label "/> <t:panelGroup> <t:inputText
     value="#{dedEnquiryRequestBean.dto.ignoreDate}" disabled="true" styleClass="margin_t5" /> </t:panelGroup>--%>