
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<t:panelGroup forceId="true" id="paging_panel_group">
    <t:panelGroup forceId="true" id="paging_panel" rendered="#{ (OperationBar.recordsCount >= 0) ? true : false }">
        <t:outputText dir="ltr" forceId="true" id="listSize" style="width:100%"
                      styleClass="noOfRecords"
                      value="#{globalResources.noOfRecords} : #{OperationBar.recordsCount}"
                      rendered="#{OperationBar.recordsCountRendered}"/>
       <h:panelGrid id="panelGrd_scroller" columns="2" dir="#{shared_util.pageDirection}" styleClass="scroller"  rendered="#{pageBeanName.listSize > shared_util.noOfTableRows}" >
           
         <t:dataScroller id="scroll_1" 
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
                   renderFacetsIfSinglePage="false" rendered="#{!pageBeanName.usingPaging}">
                   
               <f:facet name="first" >                            
                   <h:panelGroup id="jobs_list_panelGrp_first">
                       <t:graphicImage id="jobs_list_img_firstOn"
                                               rendered="#{pageIndex > 1}"
                                               title="#{globalResources.scroller_first}"
                                               url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                               border="0"/>
                       <t:graphicImage id="jobs_list_img_firstOff"
                                               onclick="return false;"
                                               rendered="#{pageIndex <= 1}"
                                               url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                               border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="last">                            
                   <h:panelGroup id="jobs_list_panelGrp_last">
                           <t:graphicImage id="jobs_list_img_lastOn"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_last}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_lastOff"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="previous">                            
                   <h:panelGroup id="jobs_list_panelGrp_prv">
                           <t:graphicImage id="jobs_list_img_prvOn"
                                           rendered="#{pageIndex > 1}"
                                           title="#{globalResources.scroller_previous}"
                                           url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_prvOff"
                                           onclick="return false;"
                                           rendered="#{pageIndex <= 1}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="next">                            
                   <h:panelGroup id="jobs_list_panelGrp_nxt">
                           <t:graphicImage id="jobs_list_img_nxtOn"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_next}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_nxtOff"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="fastforward">
                   <h:panelGroup id="jobs_list_panelGrp_ffrwrd">
                           <t:graphicImage id="jobs_list_img_ffrwrdOn"
                                           rendered="#{pageIndex < pageCount}"
                                           title="#{globalResources.scroller_fastforward}"
                                           url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_ffrwrdOff"
                                           onclick="return false;"
                                           rendered="#{pageIndex >= pageCount}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                           border="0"/>
                   </h:panelGroup>
               </f:facet>
               <f:facet name="fastrewind">
                   <h:panelGroup id="jobs_list_panelGrp_frwnd">
                           <t:graphicImage id="jobs_list_img_frwndOn"
                                           rendered="#{pageIndex > 1}"
                                           title="#{globalResources.scroller_fastrewind}"
                                           url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                           border="0"/>
                           <t:graphicImage id="jobs_list_img_frwndOff"
                                           onclick="return false;"
                                           rendered="#{pageIndex <= 1}"
                                           url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                           border="0"/>
                           
                           <t:inputHidden forceId="true" id="pageIndexWithoutPaging" value="#{pageIndex}" 
                               valueChangeListener="#{shared_util.changePageIndex}"/>
                           
                   </h:panelGroup>
                   
               </f:facet>
           </t:dataScroller>
               
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
                   actionListener="#{pageBeanName.changePageIndex}" rendered="#{pageBeanName.usingPaging}">
                   
               <f:facet name="first" >                            
                   <h:panelGroup id="jobs_list_panelGrp_first_paging" rendered="#{!pageBeanName.usingBsnPaging}">
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
                   <h:panelGroup id="jobs_list_panelGrp_last_paging" rendered="#{!pageBeanName.usingBsnPaging}">
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
               <f:facet  name="fastrewind">
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
           
           <t:panelGroup styleClass="footer_info_pnl" style=" float: left;
    left: 35px;
    margin-left: 5px;
    margin-top: -8px;
    position: absolute;">
        <t:panelGrid align="left" columns="4" rendered="#{pageBeanName.listSize > 0 && pageBeanName.filterBy==-100}">
            <h:outputText styleClass="footer_info" value="#{resourcesBundle.ConditionsPassed}" 
            style=" color: #333333;display: block;float: right;font-family: arial;font-size: 12px;font-weight: bold;
    margin-right: 5px;margin-top: -5px;text-align: right;"/>
            <h:outputText styleClass="footer_info" value="(#{pageBeanName.allPassedCond})" style=" color: #333333;display: block;float: right;font-family: arial;font-size: 12px;font-weight: bold;
    margin-right: 5px;margin-top: -5px;text-align: right;"/>
            <h:outputText styleClass="footer_info" value="#{resourcesBundle.ConditionsNotPassed}" style=" color: #333333;display: block;float: right;font-family: arial;font-size: 12px;font-weight: bold;
    margin-right: 5px;margin-top: -5px;text-align: right;"/>
            <h:outputText styleClass="footer_info"
                          value="(#{pageBeanName.listSize - pageBeanName.allPassedCond })" style=" color: #333333;display: block;float: right;font-family: arial;font-size: 12px;font-weight: bold;
    margin-right: 5px;margin-top: -5px;text-align: right;"/>
        </t:panelGrid>
        
        </t:panelGroup>
           
           <t:inputHidden forceId="true" id="pageIndex" value=""/>
       </h:panelGrid>
       
       <t:inputHidden forceId="true" id="noOfTableRows" value="#{shared_util.noOfTableRows}"/>
       <t:inputHidden forceId="true" id="arrayId" value=""/>
    </t:panelGroup>
     
</t:panelGroup>
<!-- Start Paging -->
<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>

<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>

<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
