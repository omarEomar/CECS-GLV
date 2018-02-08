
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<t:panelGroup forceId="true" id="paging_panel">
    <t:panelGroup forceId="true" id="paging_panel_group" rendered="#{ (detailBeanName.currentListSize > 0) ? true : false }">
        <t:outputText dir="ltr" forceId="true" id="listSize1" style="width:100%"
                      styleClass="noOfRecords"
                      value="#{globalResources.noOfRecords} : #{detailBeanName.currentListSize}"/>
        <h:panelGrid id="panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.currentListSize > shared_util.noOfTableRows}" >
                
            <t:dataScroller id="scroll_paging"
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
                    for="dataT_data"
                    renderFacetsIfSinglePage="false"
                    >
                    
                <f:facet name="first" >                            
                    <h:panelGroup id="jobs_list_panelGrp_first_paging">
                        <t:graphicImage id="jobs_list_img_firstOn_paging"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOff_paging"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="jobs_list_panelGrp_last_paging">
                            <t:graphicImage id="jobs_list_img_lastOn_paging"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_lastOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="jobs_list_panelGrp_prv_paging">
                            <t:graphicImage id="jobs_list_img_prvOn_paging"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_prvOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="jobs_list_panelGrp_nxt_paging">
                            <t:graphicImage id="jobs_list_img_nxtOn_paging"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_nxtOff_paging"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd_paging">
                            <t:graphicImage id="jobs_list_img_ffrwrdOn_paging"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd_paging">
                            <t:graphicImage id="jobs_list_img_frwndOn_paging"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_frwndOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
			<t:inputHidden forceId="true" id="pageIndexWithPaging" value="#{pageIndex}"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
            <t:inputHidden forceId="true" id="pageIndex" value=""/>
        </h:panelGrid>
    </t:panelGroup>   
</t:panelGroup>
