<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>


                <t:panelGrid columns="1" width="100%">
                    <t:panelGroup forceId="true" id="divDeleteConfirm" styleClass="delDivScroll">
                        <t:dataTable id="dataT_DeleteConf" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
                            
                            <t:column sortable="false" width="40%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="name_del_confirm" value="#{globalResources.TableName}"/>
                                </f:facet>
                                <h:outputText id="popup_name_del_confirm" value="#{list.currentObject.decisionTitle}"/>
                            </t:column>
                            
                            <t:column sortable="false" width="35%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="status" value="#{globalResources.Status}"/>
                                </f:facet>
                                <h:outputText id="popup_status" value="#{globalResources[list.status]}" rendered="#{list.status=='NotDeleted'}" styleClass="errMsg"/>
                                <h:outputText id="popup_status2" value="#{globalResources[list.status]}" rendered="#{list.status=='Deleted'}" styleClass="sucessMsg"/>
                            </t:column>
                            
                        </t:dataTable>
                    </t:panelGroup>
                    <t:panelGrid align="center">
                        <h:commandButton id="cancelconfirm" type="button" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/>
                    </t:panelGrid>
                </t:panelGrid>
