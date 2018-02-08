<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>


                <t:panelGrid columns="1" width="100%">
                    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
                        <t:dataTable id="dataT_Delete" var="list" value="#{pageBeanName.selectedDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                                     styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                     width="100%" align="center" rowIndexVar="index" dir="rtl">
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="type" value="#{regResources.type}"/>
                                </f:facet>
                                <h:outputText value="#{list.typesDTO.name}" id="NameTXT"/>
                            </t:column>
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="issuance_year" value="#{regResources.issuance_year}"/>
                                </f:facet>
                                <h:outputText id="title_text" value="#{list.code.decyearCode}"/>
                            </t:column>
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="decision_number" value="#{regResources.auto_number}"/>
                                </f:facet>
                                <h:outputText value="#{list.regAutoNumber}" id="regAutoNumTXT"/>
                            </t:column>
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="decision_description" value="#{regResources.decision_description}"/>
                                </f:facet>
                                <h:outputText id="description" value="#{list.decisionTitle}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    <t:panelGrid columns="2" align="center">
                        <h:commandButton id="ok" value="#{globalResources.ok}" action="#{pageBeanName.deleteDiv}" onclick="ignoremyFormValidation();" styleClass="cssButtonSmall"/>
                        <%-- modifed by m.elsaied change request
                        <h:commandButton id="cancel" type="button" value="#{globalResources.CancelButton}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/>
                         --%>
                        <%--<h:commandButton id="CancelButtonDelAlertDiv" type="button" value="#{globalResources.cancel}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/>--%>
                        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" onblur="if(isVisibleComponent('delAlert'))document.getElementById('OkButtonDelAlertDiv').focus();" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall" />
                    </t:panelGrid>
                </t:panelGrid>
            