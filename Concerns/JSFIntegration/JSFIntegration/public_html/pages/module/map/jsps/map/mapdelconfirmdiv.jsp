<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%">
 <t:panelGroup forceId="true" id="divDeleteConfirm" styleClass="delDivScroll">
  <t:dataTable id="dataT_DeleteConf" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
               styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
               columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
   <t:column sortable="false" width="40%" defaultSorted="true">
    <f:facet name="header">
     <h:outputText id="name" value="#{globalResources.TableName}"/>
    </f:facet>
    <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
   </t:column>
   <t:column sortable="false" width="35%" defaultSorted="true">
    <f:facet name="header">
     <h:outputText id="status" value="#{qulResources.Status}"/>
    </f:facet>
    <h:outputText id="popup_status" value="#{globalResources[list.status]}" rendered="#{list.status=='NotDeleted'}" styleClass="errMsg"/>
    <h:outputText id="popup_status2" value="#{globalResources[list.status]}" rendered="#{list.status=='Deleted'}" styleClass="sucessMsg"/>
   </t:column>
  </t:dataTable>
 </t:panelGroup>
 <t:panelGrid align="center">
 
  <%--a4j:commandButton id="cancelconfirm"  action="#{mappedDataBean.removeBall}" type="button" value="#{globalResources.back}" reRender="data_tableRendering,totalNo" onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/--%>
   <t:panelGroup>
        <t:commandButton onclick="delConfirmButtonJs();" id="CancelButtonDelConfirmDiv" forceId="true" type="button" 
                        value="#{globalResources.back}" onblur="settingFoucsAtDivDeleteConfirm();"
                        styleClass="cssButtonSmall" />
        <a4j:jsFunction name="delConfirmButtonJs"  action="#{mappedDataBean.removeBall}" 
                    reRender="data_tableRendering,totalNo,btnSearch" oncomplete="hideLookUpDiv(window.blocker,window.delConfirm,null,null);setFocusOnlyOnElement('objectType');"/>
    </t:panelGroup>   
 </t:panelGrid>
</t:panelGrid>
          
















<%--<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<script>
 
</script>

               <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.qul.presentation.resources.qualifications" var="qulResources"/>
<center><h:outputText value="#{pageBeanName.delConfirmTitle}" style="font-weight:bold"/></center><f:verbatim><br/></f:verbatim>
               
               <t:panelGroup forceId="true" id="divDeleteConfirm">
                        <t:dataTable id="dataT_DeleteConf" var="list"
                                 value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false"
                                 renderedIfEmpty="false" 
                                 footerClass="grid-footer" sortable="false"
                                 styleClass="grid-footer"
                                 headerClass="standardTable_Header"
                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                                 rowIndexVar="index"
                                 ><%-- width has been modified by css person--%>
                            <%--<t:column id="popup_index_column" width="5%">
                                <f:facet name="header"/>
                                <h:outputText value="#{index}"/>
                            </t:column>
                            <t:column sortable="false" width="25%" defaultSorted="true">
                                 <f:facet name="header">
                                      <h:outputText id="code" value="#{globalResources.Code}"/>
                                 </f:facet>
                                <h:outputText id="popup_code" value="#{list.currentObject.code.key}"/>
                            </t:column>
                            <t:column sortable="false" width="40%" defaultSorted="true">
                                 <f:facet name="header">
                                      <h:outputText id="name" value="#{globalResources.TableName}"/>
                                 </f:facet>
                                <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
                            </t:column>
                            <t:column sortable="false" width="30%" defaultSorted="true">
                                 <f:facet name="header">
                                      <h:outputText id="status" value="#{qulResources.Status}"/>
                                 </f:facet>
                                <h:outputText id="popup_status" value="#{qulResources[list.status]}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    
                    
                   <%-- Start css modification add two break line to UI --%>
                     <%--<f:verbatim>
                    <br/>
                    <br/>
                   </f:verbatim>
                   <%-- End css modification add two break line to UI --%>
                    <%--<h:commandButton id="cancelconfirm" type="button" value="#{globalResources.CancelButton}"  
onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/>--%>
