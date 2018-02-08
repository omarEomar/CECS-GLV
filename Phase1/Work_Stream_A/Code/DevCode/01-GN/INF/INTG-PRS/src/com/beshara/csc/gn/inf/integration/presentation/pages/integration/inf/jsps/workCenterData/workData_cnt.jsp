<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.BoxColor{position:absolute;bottom:7px;}.tree-description {width: 180px;}
.BoxColor-in-tabs {position:absolute;bottom: 7px;}
</htm:style>
<t:panelGroup id="_noResults_continer_for_TreeList" forceId="true"></t:panelGroup>
<t:panelGrid columns="2" align="right" dir="#{jobResource.align}" id="treeList" forceId="true" width="100%">
    <t:panelGroup id="treeDivPanel" forceId="true" styleClass="#{workDataListBeanName.panelGroupStyleClass}" style="display: block;position:relative;">
        <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="treeDivList"
                     style="padding-bottom:30px;">
            <t:tree2 id="clientTree" value="#{workDataListBeanName.treeNodeBase}" var="node"
                     imageLocation="/app/media/images/ar/tree2" varNodeToggler="t" binding="#{workDataListBeanName.htmlTree}"
                     clientSideToggle="false" showNav="false">
                <f:facet name="foo-folder">
                    <t:panelGroup>
                        <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{workDataListBeanName.expand}"
                                         reRender="treeList" oncomplete="foucsAtServerTree();"
                                         onmousedown="setCmdTreeId(this);"
                                         rendered="#{node.kwtWorkDataTreeDTO.leafFlag !=0 && workDataListBeanName.treeListSize != 0}"
                                         onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);">
                            <t:graphicImage value="/app/media/images/minus_open_Root.gif"
                                            rendered="#{node.parent == null && node.expanded}" border="0" style="float:right;"/>
                            <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                            rendered="#{node.parent == null && !node.expanded}" border="0" style="float:right;"/>
                            <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                            rendered="#{node.parent != null && node.expanded}" border="0" style="#{node.kwtWorkDataTreeDTO.treeLevel=='2' ? 'float:right;margin-top: 0px;':'float:right;margin-top: 15px;'}"/>
                            <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                            rendered="#{node.parent != null && !node.expanded}" border="0" style="#{node.kwtWorkDataTreeDTO.treeLevel=='2' ? 'float:right;margin-top: 0px;':'float:right;margin-top: 15px;'}"/>
                        </a4j:commandLink>
                        <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{node.kwtWorkDataTreeDTO.leafFlag ==0}" border="0" style="float:right;margin-top: 15px;"/>
                        <t:commandLink id="hiddenLink" value=""/>
                        <t:outputText id="commandDescription2" onclick="updatetree();"
                                      value="[#{node.treeId}] #{node.description}"
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-description" style="float:right;"/>
                        <t:outputText id="fromDate1" rendered="#{node.parent !=null}"
                                      value="  #{infIntegrationBundle.fromDate} : #{node.kwtWorkDataTreeDTO.fromDate}"
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-from-date" style="display: block;float: right;margin-right: 20px !important;width: 110px !important; white-space: nowrap;"/>
                        <t:outputText id="untilDate1"
                                      rendered="#{node.parent !=null && node.kwtWorkDataTreeDTO.untilDate != null}"
                                      value=" #{infIntegrationBundle.untilDate} : #{node.kwtWorkDataTreeDTO.untilDate}"
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-until-date" style="display: block;float: right;margin-right: 20px !important;width: 110px !important; white-space: nowrap;"/>
                        <t:outputText id="untilDateEmpty"
                                      rendered="#{node.parent !=null && node.kwtWorkDataTreeDTO.untilDate == null}"
                                      value=" #{infIntegrationBundle.untilDate} :  #{infIntegrationBundle.tillNow}  "
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-until-date" style="display: block;float: right;margin-right: 20px !important;width: 110px !important; white-space: nowrap;"/>
                        <t:outputText id="timeCost" rendered="#{node.parent !=null}"
                                      value="#{infIntegrationBundle.duration}: #{infIntegrationBundle.year_count}  #{node.kwtWorkDataTreeDTO.serviceYears}   #{infIntegrationBundle.durationAnd}  #{infIntegrationBundle.month_count} #{node.kwtWorkDataTreeDTO.serviceMonths}   #{infIntegrationBundle.durationAnd}  #{infIntegrationBundle.day_count}  #{node.kwtWorkDataTreeDTO.serviceDays} "
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-duration-date" style="display: block;float: right;margin-right: 20px;width: 110px !important; white-space: nowrap;"/>
                        <%-- #{node.treeNodeLevelsID}--%>
                        <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>
                    </t:panelGroup>
                </f:facet>
                <f:facet name="person-highlight">
                    <t:panelGroup>
                            <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{workDataListBeanName.expand}"
                                             reRender="treeList" onmousedown="setCmdTreeId(this);"
                                             oncomplete="foucsAtServerTree();"
                                             onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);"
                                             rendered="#{node.kwtWorkDataTreeDTO.leafFlag !=0}">
                                <t:graphicImage value="/app/media/images/minus_open_Root.gif"
                                                rendered="#{node.parent == null && node.expanded}" border="0" style="float:right;"/>
                                <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                                rendered="#{node.parent == null && !node.expanded}" border="0" style="float:right;"/>
                                <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                                rendered="#{node.parent != null && node.expanded}" border="0" style="float:right;margin-top: 15px;"/>
                                <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                                rendered="#{node.parent != null && !node.expanded}" border="0" style="float:right;margin-top: 15px;"/>
                            </a4j:commandLink>
                        <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{node.kwtWorkDataTreeDTO.leafFlag==0}" border="0" style="float:right;margin-top: 15px;"/>
                        <t:commandLink id="hiddenLink" value=""/>
                        <t:outputText id="commandDescription2" onclick="updatetree();"
                                      onmousedown="setCmdTreeId(); document.getElementById('theSelectedNodeId').value='#{node.detailId}';showDivTreeDetails(this,'#{node.detailId}',window.divTreeDetails);"
                                      value="[#{node.treeId}] #{node.description}"
                                      styleClass="HigthLighttreepage-link" style="float:right"/>
                        <%-- #{node.treeNodeLevelsID}--%>
                        <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>
                    </t:panelGroup>
                </f:facet>
            </t:tree2>
            <htm:table styleClass="BoxColorArea" style="background: none repeat scroll 0 0 #ffffff;bottom: 3px;left: 10px;">
                <htm:tr>
                    <htm:td style="width: 25px;">
                        <htm:span styleClass="BoxColor#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''} BoxColor02#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}"></htm:span>
                         
                        <%-- <t:panelGroup styleClass= "BoxColor BoxColor01"></t:panelGroup>--%>
                    </htm:td>
                    <htm:td styleClass="BoxColorText#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}">
                        <f:verbatim>فترات وظيفية</f:verbatim>
                    </htm:td>
                    <htm:td style="width: 25px;">
                        <htm:span styleClass="BoxColor#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''} BoxColor01#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}"></htm:span>
                    </htm:td>
                    <htm:td styleClass="BoxColorText#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}">
                        <f:verbatim>تدرج وظيفي</f:verbatim>
                    </htm:td>
                    <htm:td style="width: 25px;">
                        <htm:span styleClass="BoxColor#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''} BoxColor06#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}"></htm:span>
                    </htm:td>
                    <htm:td styleClass="BoxColorText#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}">
                        <f:verbatim>خبرات</f:verbatim>
                    </htm:td>
                </htm:tr>
            </htm:table>
        </t:panelGrid>
    </t:panelGroup>
    <t:inputHidden forceId="true" value="0" id="rootID"/>
    <t:inputHidden forceId="true" id="lastNode" value=""/>
    <t:inputHidden forceId="true" value="#{workDataListBeanName.treeNodeNameForCollapseExpand}"
                   id="treeNodeNameForCollapseExpand"/>
    <t:inputHidden forceId="true" id="selectedNodeTreeLevelId" value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
</t:panelGrid>
<f:verbatim></f:verbatim>