<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
    <!--- Start of Row 1-->
    <t:outputText value="#{regResources.type}" styleClass="lable01"/>
    <t:inputText styleClass="textboxmedium" value="#{pageBeanName.pageDTO.typesDTO.name}" disabled="true"/>
    <t:outputText value="#{regResources.issuance_year}" styleClass="lable01"/>
    <t:inputText styleClass="textbox" value="#{pageBeanName.pageDTO.yearsDTO.code.key}" disabled="true"/>
    <!--- Start of Row 2-->
    <%--<t:outputText value="#{regResources.decision_number}" styleClass="lable01"/>--%>
    <%--<t:inputText forceId="true" disabled="true" id="decision_ref_decisionNoEdit" styleClass="textbox"
                 value="#{pageBeanName.pageDTO.decisionNumber}"/>--%>
    <t:outputText value="#{regResources.decision_description}" styleClass="lable01"/>
    <t:panelGroup colspan="5">
        <t:inputText disabled="true" styleClass="textboxlarge3" value="#{pageBeanName.pageDTO.decisionTitle}"/>
    </t:panelGroup>
</t:panelGrid>
<t:messages/>