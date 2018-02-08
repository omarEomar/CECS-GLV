<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<script>
 
</script>

               <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

             

               
               <t:panelGroup forceId="true" id="divDeleteConfirm" styleClass="delDivScroll">
                        <t:dataTable id="dataT_DeleteConf" var="list"
                                 value="#{detailBeanName.deleteStatusDTOS}" preserveDataModel="false"
                                 renderedIfEmpty="false" 
                                 footerClass="grid-footer" sortable="false"
                                 styleClass="grid-footer"
                                 headerClass="standardTable_Header"
                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="75%" align="center"
                                 rowIndexVar="index"
                                 ><%-- width has been modified by css person--%>
                            <t:column id="popup_index_column" width="5%">
                                <f:facet name="header"/>
                                <h:outputText value="#{index}"/>
                            </t:column>
                            <t:column sortable="true" width="25%" defaultSorted="true">
                                 <f:facet name="header">
                                    <t:commandSortHeader columnName="code" immediate="true"  arrow="false" styleClass="standardTable_Header2">
                                       <f:facet name="ascending">
                                          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                       </f:facet>
                                       <f:facet name="descending">
                                          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                       </f:facet>
                                      <h:outputText id="code" value="#{globalResources.Code}"/>
                                    </t:commandSortHeader>
                                 </f:facet>
                                <h:outputText id="popup_code" value="#{list.currentObject.code.key}"/>
                            </t:column>
                            <t:column sortable="true" width="40%" defaultSorted="true">
                                 <f:facet name="header">
                                    <t:commandSortHeader columnName="name"  immediate="true"  arrow="false" styleClass="standardTable_Header2">
                                       <f:facet name="ascending">
                                          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                       </f:facet>
                                       <f:facet name="descending">
                                          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                       </f:facet>
                                      <h:outputText id="name" value="#{globalResources.TableName}"/>
                                    </t:commandSortHeader>
                                 </f:facet>
                                <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
                            </t:column>
                            <t:column sortable="true" width="30%" defaultSorted="true">
                                 <f:facet name="header">
                                    <t:commandSortHeader columnName="name"  immediate="true"  arrow="false" styleClass="standardTable_Header2">
                                       <f:facet name="ascending">
                                          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                       </f:facet>
                                       <f:facet name="descending">
                                          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                       </f:facet>
                                      <h:outputText id="status" value="#{resourcesBundle.Status}"/>
                                    </t:commandSortHeader>
                                 </f:facet>
                                <h:outputText id="popup_status" value="#{resourcesBundle[list.status]}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    
                    
                   <%-- Start css modification add two break line to UI --%>
                     <f:verbatim>
                    <br/>
                    <br/>
                   </f:verbatim>
                   <%-- End css modification add two break line to UI --%>
                    <t:commandButton forceId="true" id="CancelButtonDelConfirmDiv" onblur="settingFoucsAtDivDeleteConfirm();" type="button" value="#{globalResources.ok}"  
onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/>
