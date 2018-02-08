<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<t:panelGroup id="paging_panel_group" forceId="true"  style="width: 100%;">
 <t:panelGroup id="paging_panel" forceId="true" styleClass="paging_panel" rendered="#{deductionEnquiryBean.resultMode}">    
   <t:panelGroup styleClass="noOfRecords" >
        <t:outputText value="#{resourcesBundle.noofrecords} : #{deductionEnquiryBean.noOfRecords}"/>
    </t:panelGroup>
    <t:dataScroller id="paging_scroll_paging"
            fastStep="5" pageCountVar="pageCount" 
            pageIndexVar="pageIndex"
            paginator="true"
            paginatorMaxPages="5"
            fastfStyleClass="textpage"
            fastrStyleClass="textpage"
            firstStyleClass="pagination_item"
            lastStyleClass="pagination_item pagination_item_last"
            nextStyleClass="pagination_item"
            previousStyleClass="pagination_item"
            paginatorColumnClass="pagination_item"
            paginatorActiveColumnClass="pagination_item item_active"
            styleClass="scroller"
            immediate="false"
            for="my_dataTable"
            renderFacetsIfSinglePage="false" rendered="#{pageBeanName.renderOnSelect}">
            
        <f:facet name="first" >                            
            <h:panelGroup id="paging_panelGrp_first_paging">
                <t:graphicImage id="list_img_firstOn_paging"
                                        rendered="#{pageIndex > 1}"
                                        title="#{resourcesBundle.firstBtnPaging}"
                                        url="/resources/images/first_page.png"
                                        border="0"/>
                <t:graphicImage id="paging_list_img_firstOff_paging"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/resources/images/first_page_disabled.png"
                                        border="0" styleClass="pagination_item_disabled"/>
            </h:panelGroup>
        </f:facet>
        <f:facet name="last">                            
            <h:panelGroup id="paging_panelGrp_last_paging">
                    <t:graphicImage id="list_img_lastOn_paging"
                                    rendered="#{pageIndex < pageCount}"
                                    title="#{resourcesBundle.lastBtnPaging}"
                                    url="/resources/images/last_page.png"
                                    border="0"/>
                    <t:graphicImage id="paging_list_img_lastOff_paging"
                                    onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/resources/images/last_page_disabled.png"
                                    border="0" styleClass="pagination_item_disabled"/>
            </h:panelGroup>
        </f:facet>
        <f:facet name="previous">                            
            <h:panelGroup id="paging_list_panelGrp_prv_paging">
                    <t:graphicImage id="paging_list_img_prvOn_paging"
                                    rendered="#{pageIndex > 1}"
                                   title="#{resourcesBundle.previousBtnPaging}"
                                    url="/resources/images/prev_page.png"
                                    border="0"/>
                    <t:graphicImage id="paging_list_img_prvOff_paging"
                                    onclick="return false;"
                                    rendered="#{pageIndex <= 1}"
                                    url="/resources/images/prev_page_disabled.png"
                                    border="0" styleClass="pagination_item_disabled"/>
            </h:panelGroup>
        </f:facet>
        <f:facet name="next">                            
            <h:panelGroup id="paging_list_panelGrp_nxt_paging">
                    <t:graphicImage id="paging_list_img_nxtOn_paging"
                                    rendered="#{pageIndex < pageCount}"
                                   title="#{resourcesBundle.nextBtnPaging}"
                                    url="/resources/images/next_page.png"
                                    border="0"/>
                    <t:graphicImage id="paging_list_img_nxtOff_paging"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/resources/images/next_page_disabled.png"
                                    border="0" styleClass="pagination_item_disabled"/>
            </h:panelGroup>
        </f:facet>
        <f:facet name="fastforward">
            <h:panelGroup id="paging_list_panelGrp_ffrwrd_paging">
                    <t:graphicImage id="paging_list_img_ffrwrdOn_paging"
                                    rendered="#{pageIndex < pageCount}"
                                    title=""
                                    url=""
                                    border="0"/>
                    <t:graphicImage id="paging_list_img_ffrwrdOff_paging"
                                    onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url=""
                                    border="0"/>
            </h:panelGroup>
        </f:facet>
        <f:facet name="fastrewind">
            <h:panelGroup id="paging_list_panelGrp_frwnd_paging">
                    <t:graphicImage id="paging_list_img_frwndOn_paging"
                                    rendered="#{pageIndex > 1}"
                                    title=""
                                    url=""
                                    border="0"/>
                    <t:graphicImage id="paging_list_img_frwndOff_paging"
                                    onclick="return false;"
                                    rendered="#{pageIndex <= 1}"
                                    url=""
                                    border="0"/>
             
                
                
            </h:panelGroup>
            
        </f:facet>
    </t:dataScroller>
    </t:panelGroup>
    
  </t:panelGroup>  

<t:saveState value="#{pageBeanName.pageIndex}"/>    
    
    
