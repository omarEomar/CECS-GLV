<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}" >
    <htm:link rel="SHORTCUT ICON" href="#{shared_util.contextPath}/app/media/images/favicon.ico" type="image/x-icon"/>        
    <htm:link rel="stylesheet" href="../../../shared/media/css/ar/csc_report_ar.css" type="text/css" />	    
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="grsIntgResources"/>
        <f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>

    <h:form id="myForm">
        <t:aliasBean alias="#{pageBeanName}" value="#{conditionDetailsBean}">
            <t:saveState value="#{pageBeanName.conditionKey}"/>
            <t:saveState value="#{pageBeanName.displayRelatedModules}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            
            
            
            <t:panelGroup styleClass="report_contents">
            <t:commandButton value="طباعة" onclick="window.print();" styleClass="print_btn" title="طباعة" style="top:90px"/>
            <t:panelGrid styleClass="report_header">
		<t:panelGroup styleClass="header_data"/>
            </t:panelGrid>
            <t:outputText value="#{resourcesBundle.cond_details}" styleClass="report_title"/>
            <t:panelGrid styleClass="reoprt_body"> 
                <t:panelGrid columns="2" align="center" width="100%" rowClasses="row02,row01" cellpadding="5" cellspacing="0">
                    <t:outputText value="#{grsIntgResources.ConditionRepCode}" styleClass="td_key td_width120"/>
                    <t:inputText value="#{pageBeanName.pageDTO.code.key}" styleClass="td_value" disabled="true"/>
                    <t:outputText value="#{grsIntgResources.conditionName}" styleClass="td_key"/>
                    <t:panelGroup>
                        <t:inputText value="#{pageBeanName.pageDTO.name}" styleClass="td_value" id="conditionName"
                                     forceId="true" disabled="true" style="width:450px;"/>
                    </t:panelGroup>
    
                     <t:outputText value="#{grsIntgResources.specialConditionName}" styleClass="td_key"/>
                    <t:panelGroup>
                        <t:outputText  value="#{pageBeanName.queryOverview}" styleClass="td_value cond_txt" id="specialConditionName" style="width:450px;display:block"/>
                    </t:panelGroup>
                    
                     <t:outputText value="#{grsIntgResources.specialConditionsof}" styleClass="td_key"/>
                    <t:panelGroup>
                        <t:inputText value="#{pageBeanName.pageDTO.parameterTypesDTO.name}" styleClass="td_value" id="specialConditionsof"
                                     forceId="true" disabled="true"/>
                    </t:panelGroup>
    
                    <t:outputText value="#{grsIntgResources.conditionDesc}" styleClass="td_key" rendered="#{pageBeanName.pageDTO.conditionDesc !=null}"/>
                    <t:outputText value="#{pageBeanName.pageDTO.conditionDesc}" styleClass="td_value cond_txt" style="width:450px;display:block"  rendered="#{pageBeanName.pageDTO.conditionDesc !=null}"/>
                                     
                     <t:panelGroup colspan="2" rendered="#{pageBeanName.displayRelatedModules}">
                        <t:outputText value="#{grsIntgResources.systemRelatedWithCondidtion}" styleClass="sector_title"/>
                    </t:panelGroup>
                    <t:panelGroup colspan="2" rendered="#{pageBeanName.displayRelatedModules}">
                             <t:dataList id="datalist" styleClass="list1"
                              var="item"
                              value="#{pageBeanName.pageDTO.conditionAppModulesDTOList}"
                              rowCountVar="rowCount"
                              rowIndexVar="rowIndex"
                              layout="unorderedList">
                              <t:outputText value="#{item.appModulesDTO.name}"/>
                            </t:dataList>
                    
                    </t:panelGroup>                 
                </t:panelGrid>
                
               
            </t:panelGrid>
            <t:panelGroup styleClass="footer" style="display:block;">
                <t:outputText styleClass="copyrights" value="#{grsIntgResources.copyrights}"/>
            </t:panelGroup>
            </t:panelGroup>
            
            
            
            
        </t:aliasBean>
    </h:form>
</f:view>
