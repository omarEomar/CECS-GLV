<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1"  align="center">
    <center><h:outputText value="#{resourcesBundle.related_cities}" styleClass="TitlePage"/></center>
</t:panelGrid>
<t:panelGroup forceId="true" id="DivMasterDetail" styleClass="delDivScroll">
    <t:dataTable id="dataT_MasterDetail" var="citiesList"
            value="#{pageBeanName.cities}" preserveDataModel="false"
            renderedIfEmpty="false" 
            footerClass="grid-footer" sortable="false"
            styleClass="grid-footer"
            headerClass="standardTable_Header"
            rowClasses="standardTable_Row1,standardTable_Row2"
            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="center"
            rowIndexVar="index" >
        <t:column  width="15%" >
            <f:facet name="header">
                <h:outputText id="code" value="#{resourcesBundle.INF_CountryCitiesDTO_Code}"/>
            </f:facet>
            <h:outputText id="popup_code" value="#{citiesList.code.cntrycityCode}"/>
        </t:column>
        
        
        <t:column  width="35%">
            <f:facet name="header">
                <h:outputText id="name" value="#{resourcesBundle.INF_CountryCitiesDTO_Name}"/>
            </f:facet>
            <h:outputText id="popup_name" value="#{citiesList.name}"/>
        </t:column>
        
        <!-- -->
              <t:column  width="15%">
            <f:facet name="header">
                <h:outputText id="CountryCitiesDTO_capital" value="#{resourcesBundle.INF_CountriesDTO_countryCitiesDTOList}"/>
            </f:facet>
           <t:graphicImage id="trueFlag" value="/module/media/images/true.png" border="0"
                               rendered="#{citiesList.capitalFlag==1}"  />
           <t:graphicImage id="falseFlag" value="/module/media/images/false.png" border="0"
                               rendered="#{citiesList.capitalFlag!=1}" />

        </t:column>
        
        
        <!-- -->
        
         <t:column  width="15%">
            <f:facet name="header">
                <h:outputText id="CountryCitiesDTO_longitude" value="#{resourcesBundle.INF_CountryCitiesDTO_longitude}"/>
            </f:facet>
            <h:outputText id="popup_longitude" value="#{citiesList.longitude}"/>
        </t:column>
        
         <t:column  width="15%">
            <f:facet name="header">
                <h:outputText id="INF_CountryCitiesDTO_latitude" value="#{resourcesBundle.INF_CountryCitiesDTO_latitude}"/>
            </f:facet>
            <h:outputText id="popup_latitude" value="#{citiesList.latitude}"/>
        </t:column>
        
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{empty pageBeanName.cities}" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>

<t:panelGrid align="center">
    <t:commandButton forceId="true" id="BackButtonMasterDetailDiv" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.masterDetailDiv,null,null); return false;"/>
</t:panelGrid>
