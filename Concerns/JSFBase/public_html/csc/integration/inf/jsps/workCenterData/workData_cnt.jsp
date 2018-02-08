<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
<t:saveState value="#{workDataListBeanName.usingTreePaging}"/>
<t:saveState value="#{workDataListBeanName.treeListPagingRequestDTO}"/>
<t:saveState value="#{workDataListBeanName.pagedTreeListSize}"/>
<t:saveState value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
<t:saveState value="#{workDataListBeanName.htmlTree}"/>
<t:saveState value="#{workDataListBeanName.myTableData}"/>
<t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
<t:saveState value="#{workDataListBeanName.dtoDetails}"/>
<t:saveState value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
<t:saveState value="#{workDataListBeanName.htmlTree}"/>
<t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
<t:saveState value="#{workDataListBeanName.searchResultSize}"/>
<t:saveState value="#{workDataListBeanName.entityKey}"/>
<t:saveState value="#{workDataListBeanName.treeListSize}"/>
<t:saveState value="#{workDataListBeanName.treeList}"/>
<t:panelGroup id="_noResults_continer_for_TreeList" forceId="true"></t:panelGroup>
<t:panelGrid columns="2" align="right" dir="#{jobResource.align}" id="treeList" forceId="true" width="100%">
    <t:panelGroup id="treeDivPanel" forceId="true" styleClass="tree-area-tabs-With-1-row-filter" style="display: block;">
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
                                            rendered="#{node.parent == null && node.expanded}" border="0"/>
                            <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                            rendered="#{node.parent == null && !node.expanded}" border="0"/>
                            <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                            rendered="#{node.parent != null && node.expanded}" border="0"/>
                            <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                            rendered="#{node.parent != null && !node.expanded}" border="0"/>
                        </a4j:commandLink>
                        <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{node.kwtWorkDataTreeDTO.leafFlag ==0}" border="0"/>
                        <t:commandLink id="hiddenLink" value=""/>
                        <t:outputText id="commandDescription2" onclick="updatetree();"
                                      value="[#{node.treeId}] #{node.description}"
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-description"/>
                        <t:outputText id="fromDate1" rendered="#{node.parent !=null}"
                                      value="  #{resourcesBundle.fromDate} : #{node.kwtWorkDataTreeDTO.fromDate}"
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-from-date"/>
                        <t:outputText id="untilDate1"
                                      rendered="#{node.parent !=null && node.kwtWorkDataTreeDTO.untilDate != null}"
                                      value=" #{resourcesBundle.untilDate} : #{node.kwtWorkDataTreeDTO.untilDate}"
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-until-date"/>
                        <t:outputText id="untilDateEmpty"
                                      rendered="#{node.parent !=null && node.kwtWorkDataTreeDTO.untilDate == null}"
                                      value=" #{resourcesBundle.untilDate} :  #{resourcesBundle.tillNow}  "
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-until-date"/>
                        <t:outputText id="timeCost" rendered="#{node.parent !=null}"
                                      value="#{resourcesBundle.duration}: #{resourcesBundle.year_count}  #{node.kwtWorkDataTreeDTO.serviceYears}   #{resourcesBundle.durationAnd}  #{resourcesBundle.month_count} #{node.kwtWorkDataTreeDTO.serviceMonths}   #{resourcesBundle.durationAnd}  #{resourcesBundle.day_count}  #{node.kwtWorkDataTreeDTO.serviceDays} "
                                      styleClass="#{workDataListBeanName.selectedStyleClass} tree-duration-date"/>
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
                                            rendered="#{node.parent == null && node.expanded}" border="0"/>
                            <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                            rendered="#{node.parent == null && !node.expanded}" border="0"/>
                            <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                            rendered="#{node.parent != null && node.expanded}" border="0"/>
                            <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                            rendered="#{node.parent != null && !node.expanded}" border="0"/>
                        </a4j:commandLink>
                        <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{node.kwtWorkDataTreeDTO.leafFlag==0}" border="0"/>
                        <t:commandLink id="hiddenLink" value=""/>
                        <t:outputText id="commandDescription2" onclick="updatetree();"
                                      onmousedown="setCmdTreeId(); document.getElementById('theSelectedNodeId').value='#{node.detailId}';showDivTreeDetails(this,'#{node.detailId}',window.divTreeDetails);"
                                      value="[#{node.treeId}] #{node.description}"
                                      styleClass="HigthLighttreepage-link"/>
                        <%-- #{node.treeNodeLevelsID}--%>
                        <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>
                    </t:panelGroup>
                </f:facet>
            </t:tree2>
            <htm:table styleClass="BoxColorArea#{workDataListBeanName.fromTabs == true ? '-in-tabs' : ''}" style="background: none repeat scroll 0 0 #ffffff;">
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