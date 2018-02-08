<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}" >
    <htm:link rel="SHORTCUT ICON" href="#{shared_util.contextPath}/app/media/images/favicon.ico" type="image/x-icon"/>        
    <htm:link rel="stylesheet" href="../../media/css/ar/csc_emp_data_report_ar.css" type="text/css" />	  
    <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration" var="empIntgResources"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    
    <h:form id="myForm">
        <t:aliasBean alias="#{pageBeanName}" value="#{empListBean.empListHelper}">
            <t:saveState value="#{pageBeanName}"/>
            
            <%-----------------%>
            
        <htm:div  id="report_main_container" styleClass="report_main_container">
        
        
        <htm:div styleClass="report_header">
		<htm:div styleClass="header_data">
			<htm:p style="float:right;">
                            <h:outputText value="#{pageBeanName.moduleTitle}"/>
                        </htm:p>
			<htm:p style="float:left;">
                            <t:outputText style="margin-left:10px;" value="#{empIntgResources.printed_in} : #{pageBeanName.date}"></t:outputText>
                            <t:outputText style="margin-left:10px;" value="#{empIntgResources.hour} : #{pageBeanName.hour}"></t:outputText>
                            <t:outputText style="margin-left:10px;" value="#{empIntgResources.by} : #{pageBeanName.empName}"></t:outputText>
                            
                        </htm:p>
		</htm:div>
	</htm:div>
        <htm:div styleClass="middle_part">
        <htm:div styleClass="report_contents">
        <htm:div styleClass="report_title">
            <h:outputText value="#{pageBeanName.pageSavedTitle}"/>
        </htm:div>
        <htm:div styleClass="reoprt_body">
        <t:outputText styleClass="emp_rec_no" value="#{empIntgResources.emp_count} : #{pageBeanName.empCount}"></t:outputText>
        <htm:span styleClass="report_table_panel" id="dataT_data_panel">
        
        
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.empIntgList}" rowStyleClass=""
             forceIdIndexFormula="#{list.code.key}"   renderedIfEmpty="false"
             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
             rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
             width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true">
                 
                   
                <t:column id="min_name" sortable="false"  width="" >
                    <f:facet name="header">
                        <t:commandLink  id="sort_minName" forceId="true" styleClass="headerLink" value="#{empIntgResources.min_name}" >  
                        </t:commandLink>
    
                    </f:facet>
                    <h:outputText id="content_min_name" value="#{list.minName}"/>
                </t:column>
                
                <t:column id="real_civil_id" sortable="false" width="">
                    <f:facet name="header">
    
                        <t:commandLink id="sort_realCivilId" forceId="true" styleClass="headerLink" value="#{empIntgResources.real_civil_id}">      
                        </t:commandLink>
                       
                    </f:facet>
                    <h:outputText id="content_real_civil_id" value="#{list.realCivilId}" styleClass="outputTextForDataTable"/>
                </t:column>
                
                <t:column id="name" sortable="false" width="">
                    <f:facet name="header">
    
                        <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{empIntgResources.emp_name}" >            
                        </t:commandLink>
                       
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"   styleClass="outputTextForDataTable"/>
                </t:column>
                
                
                <t:column id="wrk_name" sortable="false" width="">
                    <f:facet name="header">
    
                        <t:commandLink id="sort_wrk_name" forceId="true" styleClass="headerLink" value="#{empIntgResources.wrk_name}">        
                        </t:commandLink>
                       
                    </f:facet>
                    <h:outputText id="content_wrk_name" value="#{list.wrkName}"  />
                </t:column>
                
                
                <t:column id="job_name" sortable="false" width="">
                    <f:facet name="header">
    
                        <t:commandLink id="sort_job_name" forceId="true" styleClass="headerLink" value="#{empIntgResources.job_name}" >             
                        </t:commandLink>
                       
                    </f:facet>
                    <h:outputText id="content_job_name" value="#{list.jobName}"  />
                </t:column>
                
                
                <t:column id="qul_name" sortable="false" width="">
                    <f:facet name="header">
    
                        <t:commandLink id="sort_qul_name" forceId="true" styleClass="headerLink" value="#{empIntgResources.qul_name}">             
                        </t:commandLink>
                       
                    </f:facet>
                    <h:outputText id="content_qul_name" value="#{list.qulName}"  />
                </t:column>
                
                <t:column id="hire_date" sortable="false" width="">
                    <f:facet name="header">
                        <t:commandLink id="sort_hire_date" forceId="true" styleClass="headerLink" value="#{empIntgResources.hire_date}">            
                        </t:commandLink>
                    </f:facet>
                    <h:outputText id="content_hire_date" value="#{list.hireDate}"  />
                </t:column>
                
            </t:dataTable>
            
        </htm:span>
        </htm:div>
        </htm:div>
        </htm:div>    
        
            <htm:div styleClass="report_footer">
                    <t:outputText styleClass="copyrights" value="#{empIntgResources.copyrights}"></t:outputText>
            </htm:div>
        
        </htm:div>
        <%-----------------%>
        
        
        </t:aliasBean>
    </h:form>
</f:view>