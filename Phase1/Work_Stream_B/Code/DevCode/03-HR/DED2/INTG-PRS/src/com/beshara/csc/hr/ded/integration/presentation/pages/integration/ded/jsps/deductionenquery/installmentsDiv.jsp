<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<htm:style>
.fullWidthScroll270 {
    max-height: 360px;
}
</htm:style>

<t:panelGroup forceId="true" styleClass="fullWidthScroll270"
              id="insPanelGroup">
    <t:dataTable id="insDtls_datatable" value="#{pageBeanName.installmentsList}" var="installment"
                    rowStyleClass="#{ empSettEnquiryHelperBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                    forceIdIndexFormula="#{installment.serial}" rowIndexVar="Index" renderedIfEmpty="false"
                    rowOnMouseOver="javascript:addRowHighlight('myForm:my_datatable');" footerClass="grid-footer"
                    styleClass="grid-footer" headerClass="standardTable_Header" forceId="true"
                    rowClasses="standardTable_Row1,standardTable_Row2" preserveDataModel="false"
                    width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true">
        <t:column id="div_datatable_serial" styleClass="column_medium">
            <f:facet name="header">
                <t:outputText id="div_datatable_seriallabel" value="#{intgBundle.serial}"/>
            </f:facet>
            <t:outputText value="#{installment.serial}" id="serial"></t:outputText>
        </t:column>
        <t:column id="div_datatable_insDate" styleClass="column_medium">
            <f:facet name="header">
                <t:outputText id="div_datatable_insDatelabel" value="#{intgBundle.installmentDate}"/>
            </f:facet>
            <t:outputText value="#{installment.installmentDate}" id="div_datatable_insDateValue"
                          converter="BesharaDateConverter"></t:outputText>
        </t:column>
        <t:column id="div_datatable_insValue" styleClass="column_medium">
            <f:facet name="header">
                <t:outputText id="div_datatable_insValuelabel" value="#{intgBundle.installmentValue}"/>
            </f:facet>
            <t:outputText value="#{installment.installmentValue}" id="div_datatable_insValueValue"></t:outputText>
        </t:column>
    </t:dataTable>
   
    <t:panelGrid id="ins_grid_msg" rendered="#{empty pageBeanName.installmentsList }" align="center" forceId="true">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid  align="center" columns="1" cellpadding="3" cellspacing="0">
    <t:commandButton id="backButtonCustomDiv1" forceId="true" value="#{globalResources.back}"
                     onclick="hideLookUpDiv(window.blocker,window.customDiv1);" type="button"
                     styleClass="cssButtonSmall"/>
</t:panelGrid>