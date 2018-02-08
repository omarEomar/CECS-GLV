<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"  >
                  <!--- Start of Row 1-->
                      <t:outputText value="#{regResources.type}" />
                      <t:inputText   styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDTO.name}" disabled="true"/>
                      
                      <t:outputText value="#{regResources.issuance_year}" />
                      <t:inputText  styleClass="textbox" value="#{pageBeanName.pageDTO.yearsDTO.code.key}" disabled="true"/>
                      
                 <!--- Start of Row 2-->
                      <t:outputText value="#{regResources.decision_number}" />
                  
                      <t:inputText rendered="#{pageBeanName.maintainMode==0}" forceId="true" disabled="true" id="decision_ref_decisionNo" styleClass="textbox" value="#{pageBeanName.pageDTO.decisionNumber}"/>
                      <t:inputText rendered="#{pageBeanName.maintainMode==1}" forceId="true" disabled="true" id="decision_ref_decisionNoEdit" styleClass="textbox" value="#{pageBeanName.pageDTO.code.decisionNumber}"/>
                    
                                    
                      <t:outputText value="#{regResources.decision_description}" />
                      <t:panelGroup colspan="5">
                      <t:inputText disabled="true"  styleClass="desTiTleTextBoxInHeader" value="#{pageBeanName.pageDTO.decisionTitle}"/>
                      </t:panelGroup>

              </t:panelGrid>

<t:messages/>