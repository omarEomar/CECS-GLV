<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<htm:style>

</htm:style>

<t:messages showDetail="true" />

<t:panelGrid columns="2" width="100%" forceId="true" id="currentempsalinfoPanel" rowClasses="row01,row02" cellpadding="0" cellspacing="0" >
    <%--<jsp:include page="/integration/emp/jsps/empData/empDataCivilName.jsp"/>--%>
    
    <h:outputText value="#{resourcesBundle.civil_id}"  styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText disabled="true" maxlength="12" 
            forceId="true" id="CivilIdDiv"  value="#{pageBeanName.realCivilId}" styleClass="textbox"  />
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.employee_name}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" forceId="true" id="EmpNameDiv" 
        disabled="true" value="#{pageBeanName.employeesDTO.citizensResidentsDTO.fullName}" style="width: 380px;"/>
    
    
    <h:outputText value="#{resourcesBundle.finance_degree}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText styleClass=" textboxlarge"  style="width: 380px;" disabled="true" forceId="true" id="degreeDiv" value="#{pageBeanName.degree}" />
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.work_center}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" style="width: 380px;" id="WorkCenterDiv" value="#{pageBeanName.employeesDTO.workCenterDTO.name}" />
    
    <h:outputText value="#{resourcesBundle.budgetProgram}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" style="width: 380px;" forceId="true" id="budgetProgramDiv" value="#{pageBeanName.empDTO.bgtProgramsDTO.name}" />
    
    <h:outputText value="#{resourcesBundle.budgetType}" styleClass="divtext"/>
    <t:inputText disabled="true" style="width: 380px;" styleClass="textboxlarge" forceId="true" id="budgetTypeDiv" value="#{pageBeanName.empDTO.bgtTypesDTO.name}" />
    
    <h:outputText value="#{resourcesBundle.job_name}" styleClass="divtext"/>
    <t:inputText disabled="true"  styleClass="textboxlarge" style="width: 380px;" forceId="true" id="JobNameDiv"  value="#{pageBeanName.employeesDTO.jobDTO.name}" /> 
    
        <%--<h:outputText value="#{resourcesBundle.sal_calc_date}" styleClass="divtext"/>--%>
       <%--<t:panelGroup>
       <t:inputText disabled="true" styleClass="textbox" forceId="true" id="salCalcDateDiv" value="#{pageBeanName.monSalDTO.calcDate}" >
        <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2" />
        </t:inputText>
          <h:outputText style="display: inline; margin: 0px 29px;" value="#{resourcesBundle.sheet_no}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox"  forceId="true" id="sheet_noDiv" value="#{paySlipMaintainQueryBean.sheetCode}" />
       </t:panelGroup>--%> 
        <%--<h:outputText value="#{resourcesBundle.sal_status}" styleClass="divtext"/>--%>
        <%--<t:inputText disabled="true" styleClass="textbox"  forceId="true" style="width: 380px;" id="salStatusDiv" value="#{pageBeanName.monSalDTO.paymentStatusDTO.name}" />--%>

        
        
        <h:outputText value="#{resourcesBundle.bank_name}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText disabled="true" styleClass="textbox"  forceId="true" id="bankNameDiv" value="#{pageBeanName.currBank}" />
   
        <h:outputText value="#{resourcesBundle.branch_name}" styleClass="divtext" style="display: inline; margin: 0px 29px 0px 63px;"/>
        <t:inputText disabled="true" styleClass="textbox" forceId="true" id="branchNameDiv" value="#{pageBeanName.currBranch}" />
        
    </t:panelGroup>
        <h:outputText value="#{resourcesBundle.account_no}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText disabled="true" styleClass="textbox" style="width: 380px;"  forceId="trueDiv" id="accountNoDiv" value="#{pageBeanName.currAccountNo}" />
        </t:panelGroup>
    
    <%--<h:outputText value="#{resourcesBundle.gender_type}" styleClass="divtext"/>--%>
    <%--<t:panelGroup>
    <t:inputText disabled="true"  styleClass="textbox"  forceId="true" id="TypeDiv"  
        value="#{pageBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.name}" />
       
    --%><%--t:outputText styleClass="textbox"  forceId="true" id="Type"  value="#{pageBeanName.kwtCitizen.genderTypesDTO.name}"/--%><%--
    
    --%><%--<h:outputText value="#{resourcesBundle.marital_status}" styleClass="divtext" style="display: inline; margin: 0px 29px 0px 5px;"/>--%><%--
       
    --%><%--<t:inputText disabled="true"  styleClass="textbox" forceId="true" id="SocialStatusDiv"  value="#{pageBeanName.employeesDTO.citizensResidentsDTO.maritalStatusDTO.name}" />--%><%--
        
    </t:panelGroup>--%>
    
</t:panelGrid>
<t:commandButton forceId="true" id="backButtonCustomDiv1" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false;"/>


