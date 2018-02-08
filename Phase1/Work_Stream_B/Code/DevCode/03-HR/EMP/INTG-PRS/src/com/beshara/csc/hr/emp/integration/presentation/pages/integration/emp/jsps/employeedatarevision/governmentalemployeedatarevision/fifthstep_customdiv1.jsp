<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid align="center">
    <h:outputText value="#{resourcesBundle.benifitsAndRewards}" styleClass="TitlePage"/>
</t:panelGrid>
<f:verbatim>
    <br/>
</f:verbatim>
<h:outputText value="#{resourcesBundle.brType}" styleClass="divText"/>
<t:panelGroup forceId="true" id="salElementTypePnlGrp">
    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
    <t:inputText value="sheka"
                 onfocus="if(isVisibleComponent('customDiv1')){document.getElementById('salElementType').focus();}"
                 forceId="true" id="fakeInput" styleClass="fakeInputForFocus"/>
    <t:selectOneMenu forceId="true" id="salElementType" styleClass="Dropdownbox" value="#{pageBeanName.salElementType}"
                     converter="javax.faces.Long">
        <a4j:support event="onchange" action="#{pageBeanName.salElementTypeChanged}" reRender="customDiv1PnlGrp"/>
        <f:selectItem itemValue="#{pageBeanName.salElementTypeBenefits}" itemLabel="#{resourcesBundle.benifits}"/>
        <f:selectItem itemValue="#{pageBeanName.salElementTypeRewards}" itemLabel="#{resourcesBundle.rewards}"/>
    </t:selectOneMenu>
</t:panelGroup>
<f:verbatim>
    <br/>
    <br/>
</f:verbatim>
<t:panelGroup forceId="true" id="customDiv1PnlGrp" styleClass="delDivScroll" style="height:200px;width:100%">
    <t:dataTable id="dataT_customDiv1" var="list" value="#{pageBeanName.benifitsAndRewardsList}"
                 preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                 width="100%" align="center" rowIndexVar="index">
        <t:column sortable="false" width="8%">
            <f:facet name="header">
                <h:outputText id="brCode" value="#{resourcesBundle.brCode}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="popup_brCode" value="#{list.salElementGuidesDTO.code.key}"/>
        </t:column>
        <t:column sortable="false" width="50%">
            <f:facet name="header">
                <h:outputText id="brName" value="#{resourcesBundle.brName}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="popup_brName" value="#{list.salElementGuidesDTO.name}"/>
        </t:column>
        <t:column sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="fromDate" value="#{resourcesBundle.fromDate}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="popup_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
        </t:column>
        <t:column sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="toDate" value="#{resourcesBundle.toDate}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="popup_toDate" value="#{list.untilDate}" converter="SqlDateConverter"/>
        </t:column>
        <t:column sortable="false" width="12%">
            <f:facet name="header">
                <h:outputText id="ministry" value="#{resourcesBundle.brValue}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="popup_ministry" value="#{list.elementValue}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{empty pageBeanName.benifitsAndRewardsList}" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>
<f:verbatim>
    <br/>
</f:verbatim>
<t:commandButton forceId="true" id="backButtonCustomDiv1" type="button"
                 onblur="if(isVisibleComponent('customDiv1')){document.getElementById('fakeInput').focus();}"
                 value="#{globalResources.back}"
                 onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);"
                 styleClass="cssButtonSmall"/>
<f:verbatim>
    <br/>
    <br/>
</f:verbatim>
<script type="text/javascript">
  function hideSalElementTypeCombo() {
      var salElementTypeCombo = document.getElementById('salElementType');
      for (i == 0;i < salElementTypeCombo.options.length;i++) {
          salElementTypeCombo.removeNode(i);
      }
  }
</script>