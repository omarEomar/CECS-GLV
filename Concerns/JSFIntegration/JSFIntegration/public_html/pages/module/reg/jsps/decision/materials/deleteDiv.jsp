<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">

    <t:panelGroup rendered="#{detailBeanName.delDivShown}">
    
        <t:dataTable id="dataT_delete" var="list" value="#{detailBeanName.selectedCurrentDetails}"     
             rowIndexVar="index"
             renderedIfEmpty="false"
             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');"
             footerClass="grid-footer" styleClass="grid-footer"
             headerClass="standardTable_Header" 
             rowClasses="standardTable_Row1,standardTable_Row2"
             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
             width="100%" align="center" dir="#{shared_util.pageDirection}">
             
            <t:column id="code_column"  width="20%">
                <f:facet name="header">
                    <t:outputText id="header_code" value="#{globalResources.Code}"/>
                </f:facet>
              <t:outputText id="content_code" value="#{list.code.keys[3] > 0 ? list.code.keys[3] : regResources.unDefineLabel }"/>
            </t:column>
            
            <t:column id="name_column"  width="75%">
                <f:facet name="header">
                    <t:outputText id="header_name" value="#{globalResources.name}"/>
                </f:facet>
                <t:outputText id="content_name" value="#{list.decmaterialHeader}"/>
            </t:column>
        </t:dataTable>
    
    </t:panelGroup>

</t:panelGroup>

<t:panelGroup>
    <%-- Start css modification add two break line to UI --%>
    <f:verbatim><br/></f:verbatim>
    <%-- End css modification add two break line to UI --%>
    
    <t:commandButton forceId="true" id="OkButtonDelAlertDiv" onblur="document.getElementById('CancelButtonDelAlertDiv').focus();" value="#{globalResources.ok}" action="#{detailBeanName.delete}" styleClass="cssButtonSmall"  onclick="ignoreClientSideValidation();" /> <f:verbatim>&nbsp; </f:verbatim>
    <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" onblur="document.getElementById('OkButtonDelAlertDiv').focus();" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall" action="#{detailBeanName.hideDelDiv}"/>

</t:panelGroup>