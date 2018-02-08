<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

   <t:panelGrid forceId="true" width="100%" border="0" columns="2" cellpadding="3" cellspacing="0" rowClasses="row02,row01" id="pnId" styleClass="center">
        <t:panelGroup  style="display: block; margin-right: 15px; margin-left: -33px;">
        <h:outputText value="#{globalResources.type}" styleClass="lable01" /> 
        </t:panelGroup>
         <t:selectOneMenu forceId="true" id="typeId" styleClass="Dropdownbox" 
                           value="#{pageBeanName.selectedTypeCode}" onchange="fillTable();">
               <f:selectItem itemLabel="#{resourcesBundle.soc_from}" itemValue="#{pageBeanName.centerCode}"/>
               <f:selectItem itemLabel="#{resourcesBundle.soc_system}" itemValue="#{pageBeanName.systemCode}"/>
               <f:selectItem itemLabel="#{resourcesBundle.other_ministries}" itemValue="#{pageBeanName.otherMinistriesCode}"/>
         <a4j:jsFunction name="fillTable" action="#{pageBeanName.fillDataTable}" reRender="dataT_data_panel,OperationBar,paging_panel_group "/>
         </t:selectOneMenu> 
         
    </t:panelGrid>

