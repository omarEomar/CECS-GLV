<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">
     <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll" style="display:block;width: 100%;overflow:auto;">
        <t:dataTable id="dataT_Delete" var="list"
                     value="#{pageBeanName.selectedDTOS}"
                     forceIdIndexFormula="#{list.code.key}"  rowIndexVar="index"
                     renderedIfEmpty="false" 
                     footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" 
                     dir="#{shared_util.pageDirection}" preserveSort="true" 
                     sortAscending="#{pageBeanName.ascending}" rows="300">
            <t:column width="25%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="code" value="#{globalResources.Code}"/>
                </f:facet>
                <%--<h:outputText id="popup_code" value="#{list.code.key}"/>--%>
                <h:outputText id="lck1" value="#{list.salEmpSettelmentsDTO.guideCode}" />
               
            </t:column>
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.salEmpSettelmentsDTO.guideName}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton type="button" forceId="true" id="OkButtonDelAlertDiv"  styleClass="cssButtonSmall" 
            value="#{globalResources.ok}" onclick="delEmpVacSettlmentElm();">
            <a4j:jsFunction name="delEmpVacSettlmentElm"  action="#{pageBeanName.deleteDiv}"
                 oncomplete="hideLookUpDiv(window.blocker,window.delAlert,null,null);changeVisibilityDiv(window.blocker,window.delConfirm);"
                reRender="delConfirm,OperationBar,dataT_data_panel,paging_panel,TotalSettlmentValue"  />
        </t:commandButton>
        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv"  styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null); return false;"/>
        <%--h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="if(isVisibleComponent('delAlert'))hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
