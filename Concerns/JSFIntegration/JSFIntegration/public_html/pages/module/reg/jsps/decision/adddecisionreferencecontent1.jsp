<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

             <t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"  >
                  <!--- Start of Row 1-->
                      <h:outputText value="#{regResources.type}" />
                      <t:inputText   styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDTO.name}" disabled="true"/>
                      
                      <h:outputText value="#{regResources.issuance_year}" />
                      <t:inputText  styleClass="textboxmedium" value="#{pageBeanName.pageDTO.yearsDTO.code.key}" disabled="true"/>
                      
                 <!--- Start of Row 2-->
                      <h:outputText value="#{regResources.decision_number}" />
                  
                      <t:inputText rendered="#{pageBeanName.maintainMode==0}" forceId="true" disabled="true" id="decision_ref_decisionNo" styleClass="textbox" value="#{pageBeanName.pageDTO.decisionNumber}"/>
                      <t:inputText rendered="#{pageBeanName.maintainMode==1}" forceId="true" disabled="true" id="decision_ref_decisionNoEdit" styleClass="textbox" value="#{pageBeanName.pageDTO.code.decisionNumber}"/>
                    
                                    
                      <h:outputText value="#{regResources.decision_description}" />
                      <t:panelGroup colspan="5">
                      <t:inputText disabled="true"  styleClass="desTiTleTextBoxInHeader" value="#{pageBeanName.pageDTO.decisionTitle}"/>
                      </t:panelGroup>

              </t:panelGrid>
        