<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
        <t:dataTable id="dataT_Delete" var="list" value="#{detailBeanName.selectedCurrentDetails}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                     styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
            
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" id="OkButtonDelAlertDiv" value="#{globalResources.ok}" action="#{detailBeanName.delete}" styleClass="cssButtonSmall" onclick="ignoremyFormValidation();" onblur="setFocusOnlyOnElement('CancelButtonDelAlertDiv');"/>
        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" type="button" value="#{globalResources.cancel}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.delAlert,null,null);setFocusAtMyFirstElement();" styleClass="cssButtonSmall" onblur="setFocusOnlyOnElement('OkButtonDelAlertDiv');"/>
    </t:panelGrid>
</t:panelGrid>











<%--<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<script>
 
</script>

               <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>            
               <%-- edit by m.elsaied (fix issue key : NL-279 - 2.  Delete 001.JPG) --%>
               <%--<center><h:outputText value="#{pageBeanName.delAlertTitle}" styleClass="TitlePage"/></center><f:verbatim><br/></f:verbatim>
               <%--<center><h:outputText value="#{pageBeanName.delAlertTitle}" style="font-weight:bold"/></center><f:verbatim><br/></f:verbatim>--%>   
               
               <%--<t:panelGroup forceId="true" id="divDeleteAlert">
                        <t:dataTable id="dataT_Delete" var="list"
                                 value="#{pageBeanName.selectedDTOS}" preserveDataModel="false"
                                 renderedIfEmpty="false" 
                                 footerClass="grid-footer" sortable="false"
                                 styleClass="grid-footer"
                                 headerClass="standardTable_Header"
                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                                 rowIndexVar="index"
                                 ><%-- width has been modified by css --%>
                            <%--<t:column id="popup_index_column" width="5%">
                                <f:facet name="header"/>
                                <h:outputText value="#{index}"/>
                            </t:column>--%>
                            <%--<t:column  width="25%" defaultSorted="true">
                                 <f:facet name="header">
                                      <h:outputText id="code" value="#{globalResources.Code}"/>
                                 </f:facet>
                                <h:outputText id="popup_code" value="#{list.code.key}"/>
                            </t:column>
                            <t:column width="75%" defaultSorted="true">
                                 <f:facet name="header">
                                      <h:outputText id="name" value="#{globalResources.TableName}"/>
                                 </f:facet>
                                <h:outputText id="popup_name" value="#{list.name}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    <%-- Start css modification add two break line to UI --%>
                    <%--<f:verbatim>
                    <br/>
                   </f:verbatim>
                   <%-- End css modification add two break line to UI --%>
                    <%--<h:commandButton id="ok" value="#{globalResources.ok}" action="#{pageBeanName.deleteDiv}" styleClass="cssButtonSmall" /> <f:verbatim>&nbsp; </f:verbatim>
                    <h:commandButton id="cancel" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall" />
--%>
