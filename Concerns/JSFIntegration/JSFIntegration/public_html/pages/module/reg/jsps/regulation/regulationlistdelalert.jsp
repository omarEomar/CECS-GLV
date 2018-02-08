<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
 
                <t:panelGrid columns="1" width="100%">
                    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
                        <t:dataTable id="dataT_Delete" var="list" value="#{pageBeanName.selectedDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                                     styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="type" value="#{regResources.reg_type}"/>
                                </f:facet>
                                <h:outputText value="#{list.typesDto.name}"/>
                            </t:column>
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="regYear" value="#{regResources.reg_year}"/>
                                </f:facet>
                                <h:outputText id="year_text" value="#{list.yearsDto.name}"/>
                            </t:column>
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="code" value="#{regResources.reg_number}"/>
                                </f:facet>
                                <h:outputText value="#{list.code.regulationNumber}"/>
                            </t:column>
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="name" value="#{regResources.reg_title}"/>
                                </f:facet>
                                <h:outputText id="name_text" value="#{list.regulationTitle}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    <t:panelGrid columns="2" align="center">
                        <h:commandButton id="ok" value="#{globalResources.ok}" action="#{pageBeanName.deleteDiv}" onclick="ignoremyFormValidation();" styleClass="cssButtonSmall"/>
                        <h:commandButton id="cancel" type="button" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/>
                    </t:panelGrid>
                </t:panelGrid>
            
