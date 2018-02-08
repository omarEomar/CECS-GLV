<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

                <t:panelGrid columns="1" width="100%">
                    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
                        <t:dataTable id="dataT_Delete" var="list" value="#{detailBeanName.selectedCurrentDetails}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                                     styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="center" rowIndexVar="index" dir="rtl">
                            
                            <t:column width="30%">
                                <f:facet name="header">
                                    <h:outputText id="del_regulation_References_type" value="#{regResources.regulation_References_type}"/>
                                </f:facet>
                                <h:outputText id="popup_regulation_References_type" value="#{list.typesDto.name}"/>
                            </t:column>
                            <t:column width="10%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="del_regulation_References_year" value="#{regResources.regulation_References_year}"/>
                                </f:facet>
                                <h:outputText id="popup_regulation_References_year" value="#{list.yearsDto.code.key}"/>
                            </t:column>
                            <t:column width="15%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="del_regulation_References_reg_no" value="#{regResources.regulation_References_reg_no}"/>
                                </f:facet>
                                <h:outputText id="popup_regulation_References_reg_no" value="#{list.code.regulationNumber}"/>
                            </t:column>
                            <t:column width="45%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                                </f:facet>
                                <h:outputText id="popup_name" value="#{list.regulationTitle}"/>
                            </t:column>                            
                        </t:dataTable>
                    </t:panelGroup>
                    <t:panelGrid columns="2" align="center">
                        <t:commandButton forceId="true" id="OkButtonDelAlertDiv" onblur="if(isVisibleComponent('delAlert'))document.getElementById('CancelButtonDelAlertDiv').focus();"  value="#{globalResources.ok}" action="#{detailBeanName.delete}" styleClass="cssButtonSmall"/>
                        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" onblur="if(isVisibleComponent('delAlert'))document.getElementById('OkButtonDelAlertDiv').focus();" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);setFocusAtMyFirstElement();" styleClass="cssButtonSmall"/>
                    </t:panelGrid>
                </t:panelGrid>
            
