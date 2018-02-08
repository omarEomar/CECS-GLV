<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions" var="globalExceptions"/>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>

<t:panelGrid columns="2" forceId="true" id="testID"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" width="490">
      <t:outputText value="#{resourcesBundle.LineName}" styleClass="divtext" />         
    <t:inputText readonly="true" value="#{conditionLineDetailBean.linesDTODetails.name}"  styleClass="textboxlarge"  style="width:410px"/>
    <t:outputText value="#{resourcesBundle.Condition_Parameters}" styleClass="divtext" />         
    <t:inputText readonly="true" value="#{conditionLineDetailBean.paraName}"   styleClass="textboxlarge"  style="width:410px" />
    <t:outputText value="#{resourcesBundle.Condition_operator}" styleClass="divtext" />         
    <t:inputText readonly="true" value="#{conditionLineDetailBean.lineSign}"  styleClass="textboxlarge"  style="width:410px" />
    <t:outputText value="#{resourcesBundle.Condition_LineValues}" styleClass="divtext"  rendered="#{conditionLineDetailBean.linesDTODetails.parameterLineValuesList == null}" />           
    <t:inputText value="#{conditionLineDetailBean.lineValue}"  styleClass="textboxlarge" readonly="true" rendered="#{conditionLineDetailBean.linesDTODetails.parameterLineValuesList == null}" />
  <t:panelGroup colspan="3" style="display: block; height: 67px; overflow-x: hidden; overflow-y: auto; width: 479px;" >
  <t:dataTable  id="dataT_availabledetail" var="list"  value="#{conditionLineDetailBean.lineValueslist}"  rowIndexVar="index"
                 renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_availabledetail');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                 dir="#{shared_util.pageDirection}" preserveSort="true">
            <t:column id="code_column" sortable="true" width="25%">
            <f:facet name="header">
                <t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_code" value="#{globalResources.Code}"/>
                </t:commandSortHeader>
            </f:facet>
            <h:outputText id="content_code" value="#{list.strCode}"/>
        </t:column>
        <t:column id="name_column" sortable="true" width="75%">
            <f:facet name="header">
                <t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_name" value="#{globalResources.name}"/>
                </t:commandSortHeader>
            </f:facet>
            <h:outputText id="content_name" value="#{list.name}"/>
        </t:column>
    </t:dataTable>
  </t:panelGroup>
  <h:outputText value="#{resourcesBundle.overview}" styleClass="divtext" />
<t:panelGroup colspan="3" >
   <t:inputTextarea  style="width:410px" rows="2" readonly="true" forceId="true" id="overview" value="#{conditionLineDetailBean.overViewLine}" styleClass="textarealarge" disabled="true"/>
</t:panelGroup>                       

</t:panelGrid>
<t:commandButton forceId="true" id="backButtonAddDiv" type="button" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv);return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"  />

                  
                  
        
        
             
