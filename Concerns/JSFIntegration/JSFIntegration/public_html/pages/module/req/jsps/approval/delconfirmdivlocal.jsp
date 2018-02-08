<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>

<t:panelGrid columns="1" width="100%">
 <t:panelGroup forceId="true" id="localdivDeleteConfirm" styleClass="delDivScroll">
  <t:dataTable id="dataT_DeleteConf" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
               styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
               columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
   <t:column sortable="false" width="40%" defaultSorted="true">
    <f:facet name="header">
     <h:outputText id="name" value="#{globalResources.TableName}"/>
    </f:facet>
    <h:outputText id="popup_name" value="#{list.currentObject.requestsDTO.typeDTO.name}"/>
   </t:column>
   <t:column sortable="false" width="35%" defaultSorted="true">
    <f:facet name="header">
     <h:outputText id="status" value="#{globalResources.Status}"/>
    </f:facet>
    <h:outputText id="popup_status" value="#{(pageBeanName.rejected)?globalexceptions.FailureInReject:globalexceptions.FailureInApprove}" rendered="#{list.status=='NotAdded'}" styleClass="errMsg"/>
    <h:outputText id="popup_status2" value="#{(pageBeanName.rejected)?globalResources.SuccessReject:globalResources.SuccessApprove}" rendered="#{list.status=='Added'}" styleClass="sucessMsg"/>
   </t:column>
  </t:dataTable>
 </t:panelGroup>
 <t:panelGrid align="center">
    <t:commandButton forceId="true" id="CancelButtonDelConfirmDiv" type="button" onclick="localcancelconfirmJs();" onblur="settingFoucsAtDivDeleteConfirm();" value="#{globalResources.back}" styleClass="cssButtonSmall"/>
    <a4j:jsFunction name="localcancelconfirmJs" reRender="dataT_data_panel" oncomplete="hideLookUpDiv(window.blocker,window.delConfirm,null,null);"/>
 </t:panelGrid>
</t:panelGrid>


















<%--<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%-- <%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>--%>
<%-- <script> </script>--%>
<%--<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>

<%-- edit by m.elsaied (fix issue key : NL-279 - 3. Delete status001.JPG)--%>
<%-- <center><h:outputText value="#{pageBeanName.delConfirmTitle}" style="font-weight:bold"/></center>--%>
<%--<f:verbatim>
 <br/>
 <br/>
</f:verbatim>
<t:panelGroup forceId="true" id="divDeleteConfirm">
 <t:dataTable id="dataT_DeleteConf" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
              styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
              columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index">
  <%-- width has been modified by css person--%>
  <%-- <t:column id="popup_index_column" width="5%"> <f:facet name="header"/> <h:outputText value="#{index}"/> </t:column>--%>
  <%-- <t:column sortable="false" width="25%" defaultSorted="true"> <f:facet name="header"> <h:outputText id="code" value="#{globalResources.Code}"/> </f:facet> <h:outputText id="popup_code"
       value="#{list.currentObject.code.key}"/> </t:column>--%>
  <%--<t:column sortable="false" width="50%" defaultSorted="true">
   <f:facet name="header">
    <h:outputText id="name" value="#{globalResources.TableName}"/>
   </f:facet>
   <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
  </t:column>
  <t:column sortable="false" width="50%" defaultSorted="true">
   <f:facet name="header">
    <h:outputText id="status" value="#{resourcesBundle.Status}"/>
   </f:facet>
   <%-- <h:outputText id="popup_status" value="#{resourcesBundle[list.status]}"/>--%>
   <%--<h:outputText id="popup_status" value="#{resourcesBundle[list.status]}" rendered="#{list.status=='NotDeleted'}" styleClass="errMsg"/>
   <h:outputText id="popup_status2" value="#{resourcesBundle[list.status]}" rendered="#{list.status=='Deleted'}" styleClass="sucessMsg"/>
  </t:column>
 </t:dataTable>
</t:panelGroup>
<%-- Start css modification add two break line to UI--%>
<%--<f:verbatim>
 <br/>
 <br/>
</f:verbatim>
<%-- End css modification add two break line to UI--%>
<%-- edit by m.elsaied (fix issue key : NL-279 - 3. Delete status001.JPG)--%>
<%--<h:commandButton id="cancelconfirm" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/>
<%-- <h:commandButton id="cancelconfirm" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/>--%>

