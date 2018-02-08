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
        <t:aliasBean alias="#{pageBeanName}" value="#{calculationDetailsBean}">
            <t:saveState value="#{pageBeanName.calculationKey}"/>
            <t:saveState value="#{pageBeanName.displayRelatedModules}"/>
            <t:saveState value="#{pageBeanName.parametersList}"/>            
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            
            
            
            <t:panelGroup styleClass="report_contents">
            <t:commandButton value="طباعة" onclick="window.print();" styleClass="print_btn" title="طباعة" style="top:90px"/>
            <t:panelGrid styleClass="report_header">
		<t:panelGroup styleClass="header_data"/>
            </t:panelGrid>
            <t:outputText value="#{grsIntgResources.calc_details}" styleClass="report_title"/>
            <t:panelGrid styleClass="reoprt_body"> 
                <t:panelGrid columns="2" align="center" width="100%" rowClasses="row02,row01" cellpadding="5" cellspacing="0">
                    <t:outputText value="#{grsIntgResources.divInfoCalcCode}" styleClass="td_key td_width120"/>
                    <t:inputText value="#{pageBeanName.pageDTO.code.key}" styleClass="td_value" disabled="true"/>
                    <t:outputText value="#{grsIntgResources.divInfoCalcName}" styleClass="td_key"/>
                    <t:panelGroup>
                        <t:inputTextarea value="#{pageBeanName.pageDTO.name}"  styleClass="td_value" id="calcula_Name"
                                     forceId="true" disabled="true" style="width:450px;"/>
                    </t:panelGroup>
    
                     <t:outputText value="#{grsIntgResources.operation}" styleClass="td_key"/>
                    <t:panelGroup>
                        <t:inputTextarea  value="#{pageBeanName.queryOverview}" disabled="true" styleClass="td_value cond_txt" id="calcul_query" style="width:450px;display:block"/>
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
