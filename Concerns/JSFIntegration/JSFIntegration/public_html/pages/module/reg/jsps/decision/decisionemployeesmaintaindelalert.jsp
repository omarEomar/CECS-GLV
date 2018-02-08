<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>


                <t:panelGrid columns="1" width="100%">
                    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
                        <t:dataTable id="dataT_Delete" var="list" value="#{detailBeanName.selectedCurrentDetails}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                                     styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top" rowIndexVar="index" dir="rtl">
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="code" value="#{regResources.civil_id}"/>
                                </f:facet>
                                <h:outputText value="#{list.code.civilId}"/>
                            </t:column>
                            <t:column width="75%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="name" value="#{regResources.employee_name}"/>
                                </f:facet>
                                <h:outputText id="popup_name" value="#{list.citizensResidentsDTO.fullName}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    <t:panelGrid columns="2" align="center">
                        <t:commandButton id="del_ok_decision_btn" onblur="document.getElementById('CancelButtonDelAlertDiv').focus();" value="#{globalResources.ok}" action="#{detailBeanName.delete}" styleClass="cssButtonSmall" forceId="true"/>
                        <t:commandButton id="CancelButtonDelAlertDiv"  onblur="document.getElementById('del_ok_decision_btn').focus();" forceId="true" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/>
                    </t:panelGrid>
                </t:panelGrid>
            
