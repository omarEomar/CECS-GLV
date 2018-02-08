<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">

 <htm:link rel="SHORTCUT ICON" href="#{shared_util.contextPath}/app/media/images/favicon.ico" type="image/x-icon"/>        
    <htm:link rel="stylesheet" href="../../../reg/media/css/ar/csc_emp_data_report_ar.css" type="text/css" />	    
<htm:link rel="stylesheet" href="../../../reg/media/css/ar/Style.css" type="text/css" />	        
    
    <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
   
    <h:form  id="myForm">
        <t:aliasBean alias="#{pageBeanName}" value="#{viewDecisionDetails}"> 
                    <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
                    <t:saveState value="#{pageBeanName.day}" id="day"/>
                    <t:saveState value="#{pageBeanName.month}" id="month"/>
                    <t:saveState value="#{pageBeanName.year}" id="year"/>
            <t:panelGroup styleClass="report_contents">
            <t:panelGrid styleClass="report_header" width="100%">
		<t:panelGroup styleClass="header_data"/>
            </t:panelGrid>
            <%--<t:outputText value="test" styleClass="report_title"/>--%>
            <t:panelGrid styleClass="reoprt_body"> 
                <t:panelGrid columns="2" align="center" width="848" rowClasses="row02,row01" cellpadding="5" cellspacing="0">
                   
                    <%--<t:outputLabel value="#{pageBeanName.pageDTO.code.key}"/>--%>
                   <t:panelGroup style="display: block; text-align: right; padding-right: 15px;">
                     <t:outputText value="#{regResources.decisionNumber}" styleClass="report_td_key_att"/>
                    <f:verbatim>&nbsp; (</f:verbatim>
                    <t:outputText value="#{pageBeanName.pageDTO.code.decisionNumber}" styleClass="report_td_value" />
                     <f:verbatim>) &nbsp;</f:verbatim>
                     <t:outputText value="#{regResources.forYear}" styleClass="report_td_key_att td_width120"/>
                     <f:verbatim>&nbsp; </f:verbatim>
                    <t:outputText value="#{pageBeanName.pageDTO.code.decyearCode}" styleClass="report_td_value"/>
                     <f:verbatim>&nbsp; م</f:verbatim>
                   </t:panelGroup>
                   
                   <t:panelGroup style="display: block; text-align: right; padding:0 410 0 15px;">
                     <t:outputText value="#{regResources.date}" styleClass="report_td_key_att td_width120"/>
                     <f:verbatim>&nbsp; : &nbsp;</f:verbatim>
                    <t:outputText value="#{pageBeanName.day}" styleClass="report_td_value" />
                    <t:outputText value="#{regResources.from}" styleClass="report_td_key_att td_width120"/>
                     <t:outputText value="#{pageBeanName.month}" styleClass="report_td_value" />
                     <t:outputText value="#{regResources.forYear}" styleClass="report_td_key_att td_width120"/>
                      <t:outputText value="#{pageBeanName.year}" styleClass="report_td_value" />
                    </t:panelGroup>
                    
                    <t:panelGroup colspan="2" style="display: block; text-align: right; padding:0 621 0 15px;">
                      <t:outputText value="#{regResources.autoNumber}" styleClass="report_td_key_att td_width120"/>
                     <f:verbatim>&nbsp; : &nbsp;</f:verbatim>
                    <t:outputText value="#{pageBeanName.pageDTO.regAutoNumber}" styleClass="report_td_value"/>
                   </t:panelGroup>
                     
                    
                     
                      <%--<t:outputText value="#{regResources.procedures}" styleClass="td_key td_width120"/>--%>
                    <t:panelGroup colspan="2" style="display: block; text-align: center; padding: 0 15px;" styleClass="report_title">
                    <t:outputText value="#{pageBeanName.pageDTO.subjectsDTO.parentObject.name}"  />
                     
                     
                     <f:verbatim>&nbsp; / &nbsp;</f:verbatim>
                     <%--<t:outputText value="#{regResources.procedureDecision}" styleClass="td_key td_width120"/>--%>
                   
                    <t:outputText value="#{pageBeanName.pageDTO.subjectsDTO.name}" />
                    </t:panelGroup>
                    
                    
                    <t:panelGroup colspan="2" style="display: block; text-align: right; padding: 0 15px;">
                    <t:outputText value="#{pageBeanName.pageDTO.decisionText}" escape="false" styleClass="report_td_value"/>
                    </t:panelGroup>
                    
                    <t:panelGroup colspan="2" style="display: block; text-align: right; padding: 0 15px;">
                      <t:outputText value="#{regResources.decisionMaking}" styleClass="report_td_key_att td_width120" style="text-decoration: underline;"/>
                      </t:panelGroup>
                    
                     <t:panelGroup colspan="2"  style="display: block; text-align: right; padding: 0 45px;">
                          <t:dataList id="datalist" styleClass="list1"
                              var="item"
                              value="#{pageBeanName.pageDTO.regDecisionMaterialsDTOList}"
                              rowCountVar="rowCount"
                              rowIndexVar="rowIndex">
                              <htm:div styleClass="box">
                              <t:outputText styleClass="header-box" value="#{item.decmaterialHeader}"/>
                               
                              <br/><t:outputText value="#{item.decmaterialText}"/>
                              </htm:div>
                              
                            </t:dataList>
                    
                    </t:panelGroup>  
                    
                    
                    <t:panelGroup colspan="2" style="display: block; text-align: end; padding-left: 50px;">
                      <t:outputText value="#{regResources.ministryW}" styleClass="report_td_key_att td_width120"/>
                     <f:verbatim><br/></f:verbatim>
                    <t:outputText value="#{pageBeanName.pageDTO.signBy}" styleClass="report_td_value" />
                    </t:panelGroup>
                    
                    
                      <t:panelGroup colspan="2"  style="display: block; text-align: right; padding: 0 45px;">
                          <t:dataList id="datalist2" styleClass="list1"
                              var="item"
                              value="#{pageBeanName.pageDTO.decisionCopyDTOList}"
                              rowCountVar="rowCount"
                              rowIndexVar="rowIndex"
                              layout="unorderedList">
                              <t:outputText value="#{item.deccopyDesc}"/>
                            </t:dataList>
                    
                    </t:panelGroup>   
                    
                    
                    </t:panelGrid>
            <t:panelGroup styleClass="footer" style="display:block;">
                <t:outputText styleClass="copyrights" value="#{regResources.copyrights}"/>
            </t:panelGroup>
            </t:panelGrid>
            </t:panelGroup>
            </t:aliasBean> 
        <c2:scriptGenerator form="myForm"/>
    </h:form>
</f:view>