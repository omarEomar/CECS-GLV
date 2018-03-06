<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divLinkLookup">
    <t:panelGrid align="center" rendered="#{pageBeanName.branchesListSize != 0 }">
        <t:outputText forceId="true" id="titleLinkId" styleClass="TitlePage"
                      value="#{resourcesBundle.bank_branches_bank_name}  "/>
    </t:panelGrid>
    <t:panelGrid columns="4" border="0" width="100%" cellpadding="3" cellspacing="0" rowClasses="row01,row02">
        <t:outputText forceId="true" id="bank_code_title" value="#{resourcesBundle.bank_code}" styleClass="lable01"/>
        <t:inputText forceId="true" id="bank_code" disabled="true" value=" #{pageBeanName.selecteBankCode}"
                     styleClass="textboxsmall2"/>
        <t:outputText forceId="true" id="bank_Name_title" value="#{resourcesBundle.selected_bank_name}"
                      styleClass="lable01"/>
        <t:inputText forceId="true" id="bank_Name" disabled="true" value="#{pageBeanName.selecteBankName}"
                     styleClass="textboxtreelarge" style="width:255px"/>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="lov_dataT_data_panel" rendered="#{pageBeanName.branchesListSize != 0 }"
                  style="display: block;max-height: 255px;min-height: 60px;overflow: auto;">
        <t:dataTable id="lov_dataT_data" var="list" value="#{pageBeanName.relatedBranchesList}" binding="#{pageBeanName.branchesDataTable}"
                     rows="#{shared_util.noOfTableRows}" rendered="#{pageBeanName.branchesListSize != 0 }"
                     rowIndexVar="index" renderedIfEmpty="false" styleClass="grid-footer"
                     headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true">
            <t:column sortable="false">
                <f:facet name="header">
                    <t:outputText id="branch_code" forceId="true" styleClass="headerLink"
                                  value="#{globalResources.Code}" onclick="return false;"></t:outputText>
                </f:facet>
                <t:outputText id="content_code" value="#{list.code.keys[1]}"/>
            </t:column>
            <t:column sortable="false">
                <f:facet name="header">
                    <t:outputText id="branch_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.bank_branch_name}" onclick="return false;"></t:outputText>
                </f:facet>
                <t:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="lov_paging_panel">
        <h:panelGrid id="lov_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleclass="scroller"
                     rendered="#{pageBeanName.branchesListSize > shared_util.noOfTableRows}">
            <t:dataScroller id="lov_scroll_1" fastStep="5" pageCountVar="pageCount"
                            actionListener="#{pageBeanName.openLovDiv}" pageIndexVar="pageIndex" paginator="true"
                            paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage"
                            fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                            nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage"
                            paginatorActiveColumnClass="paging" paginatorActiveColumnStyle="font-weight:bold"
                            styleClass="textpage" immediate="false" for="lov_dataT_data"
                            renderFacetsIfSinglePage="false">
                <f:facet name="first">
                    <t:panelGroup id="lov_jobs_list_panelGrp_first">
                        <t:graphicImage id="lov_jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_firstOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <t:panelGroup id="lov_jobs_list_panelGrp_last">
                        <t:graphicImage id="lov_jobs_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_lastOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <t:panelGroup id="lov_jobs_list_panelGrp_prv">
                        <t:graphicImage id="lov_jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_prvOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <t:panelGroup id="lov_jobs_list_panelGrp_nxt">
                        <t:graphicImage id="lov_jobs_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <t:panelGroup id="lov_jobs_list_panelGrp_ffrwrd">
                        <t:graphicImage id="jlov_obs_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_ffrwrdOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <t:panelGroup id="lov_jobs_list_panelGrp_frwnd">
                        <t:graphicImage id="lov_jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="lov_lov_jobs_list_img_frwndOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                    </t:panelGroup>
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
        <t:inputHidden forceId="true" id="pageIndexLov"
                       rendered="#{pageBeanName.branchesListSize >   shared_util.noOfTableRows}"/>
        <t:inputHidden forceId="true" id="noOfTableRowLov" value="#{shared_util.noOfTableRows}"/>
    </t:panelGroup>
    <t:panelGrid columns="1" rendered="#{pageBeanName.branchesListSize == 0 }" styleClass="msg msg_no_icon"
                 align="center">
        <t:outputText value="#{globalResources.global_noTableResults}"/>
    </t:panelGrid>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="3" border="0" align="center">
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonCustomDiv1" onclick="backJsFunctionAdd(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunctionAdd" action="#{pageBeanName.cancelLink}"
                            oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);"
                            reRender="divLinkLookup,dataT_data_panel,paging_panel"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showLinkDiv}"/>
<t:saveState value="#{pageBeanName.relatedBranchesList}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>