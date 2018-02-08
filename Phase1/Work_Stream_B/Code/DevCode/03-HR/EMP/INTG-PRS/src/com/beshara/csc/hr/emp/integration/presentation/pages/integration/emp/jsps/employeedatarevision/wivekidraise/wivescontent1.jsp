<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<style type="text/css">
/*.divSearch { 
    left: 120px !important;
    width: 685px !important;
}*//*to justify kidsdetailsdiv.jsp width*/
.lookupAddDivClass {/*to justify kidsadddiv.jsp width*/
    left: 120px !important;
    width: 600px !important;
}

</style>


<t:panelGroup  forceId="true" style="width:99%" id="emp_query_panel_id">
 <t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
    <t:inputText forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{govEmpMaintainBean.realCivilId}" disabled="true"/>
    
    <h:outputText value="#{resourcesBundle.the_name}" styleClass="divtext"/>
    <t:inputText forceId="true"  id="empName" styleClass="textboxmedium"  value="#{govEmpMaintainBean.empName}" disabled="true"/>
</t:panelGrid>
</t:panelGroup>

<%--
 <t:panelGrid id="panelGrid" align="center" columns="1" border="0" rowClasses="row01,row02" width="100%" style="overflow: scroll"  >

<t:panelGrid columns="4" border="0" width="100%"  forceId="true" id="mainDataEmpPanel" rowClasses="row01,row02" cellpadding="0" cellspacing="0" >
        
                    <h:outputText value="#{resourcesBundle.civil_id}" styleClass="divtext"/>
                    <t:panelGroup>
                        <t:inputText onkeypress="FireButtonClick('filterByCivilButton')" disabled="#{merRaiseMaintainBean.civilExist}" maxlength="12" 
                            forceId="true" id="CivilIdAdd" value="#{merRaiseMaintainBean.civilId}" styleClass="textbox"  />
                        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
                        <f:verbatim>&nbsp;</f:verbatim> 
                        <t:panelGroup>
                            <t:commandButton  forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}" styleClass="cssButtonSmall"
                                onclick="resetMsgInAdd(); searchAndvalidate();" rendered="#{!merRaiseMaintainBean.civilExist}"/>
                            <a4j:jsFunction name="searchLines" action="#{merRaiseMaintainBean.filterByCivilId}" 
                            reRender="mainDataEmpPanel,scriptGenerator,displayBtnPanel, dataT_data_panel, OperationBar ,paging_panel,scriptGenGroup" oncomplete="setFocusFirstElem();"/>
                        </t:panelGroup>
                        <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}" oncomplete="setFocusFirstElem();" 
                                            rendered="#{merRaiseMaintainBean.civilExist}"  styleClass="cssButtonSmall"  actionListener="#{merRaiseMaintainBean.reSetData}"  
                                            reRender="mainDataEmpPanel,scriptGenerator, dataT_data_panel, OperationBar ,paging_panel,scriptPanelID,scriptGenGroup"/>
                        <f:verbatim>&nbsp;</f:verbatim>
                        <a4j:commandButton  type="button"  id="Search"  styleClass="cssButtonSmall" value="#{globalResources.SearchButton}" 
                            oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);setFocusOnlyOnElement('civil_div_searchText');" onblur="setFocusFirstElem();"
                            action="#{merRaiseMaintainBean.showSearchForEmployeeDiv}" reRender="lovEmp" disabled="#{merRaiseMaintainBean.civilExist}"/>
                        <f:verbatim></br></f:verbatim>
                        
                        <c2:requiredFieldValidator  componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="CivilIdAddUniqueId"/>        
                        <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/" errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false" display="dynamic"/>
                        
                        <t:outputLabel value="#{resourcesBundle.notDefinedGenderTypeFlag}" rendered="#{merRaiseMaintainBean.notDefinedGenderTypeFlag}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!merRaiseMaintainBean.validCivilId}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="empHired"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{!merRaiseMaintainBean.empHired}" styleClass="errMsg"/>
                        <t:outputText  forceId="true" id="empHiredInMin"   value="#{resourcesBundle.emp_not_hired_in_min}"  rendered="#{!merRaiseMaintainBean.empHiredInMin}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!merRaiseMaintainBean.payrollInfoExist}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="empValid"   value="#{resourcesBundle.mer_notvalid_citizen}" rendered="#{!merRaiseMaintainBean.empValidNatSal}"  styleClass="errMsg"/>
                    </t:panelGroup>
                    
                    <h:outputText value="#{resourcesBundle.employee_name}" styleClass="divtext"/>
                    <t:inputText styleClass="textboxmedium" forceId="true" id="EmpName" 
                        value="#{merRaiseMaintainBean.employeesDTO.citizensResidentsDTO.fullName}" disabled="true"/>
                    
                    
                    <h:outputText value="#{resourcesBundle.finance_degree}" styleClass="divtext"/>
                    <t:panelGroup colspan="3">
                        <t:inputText styleClass="textboxlarge2" style="width: 637px;" forceId="true" id="degree" 
                         disabled="true" value="#{merRaiseMaintainBean.degree}" />
                    </t:panelGroup>
                    
                    <h:outputText value="#{resourcesBundle.work_center}" styleClass="divtext"/>
                    <t:inputText styleClass="textboxlarge" forceId="true" id="WorkCenter" 
                        disabled="true" value="#{merRaiseMaintainBean.employeesDTO.workCenterDTO.name}" />
                    
                    
                    <h:outputText value="#{resourcesBundle.job_name}" styleClass="divtext"/>
                    <t:inputText styleClass="textboxmedium" forceId="true" id="JobName"   disabled="true"
                         value="#{merRaiseMaintainBean.employeesDTO.jobDTO.name}" /> 
                   
                    <h:outputText value="#{resourcesBundle.gender_type}" styleClass="divtext"/>
                    <t:inputText styleClass="textbox"  forceId="true" id="Type" disabled="true"  
                        value="#{merRaiseMaintainBean.employeesDTO.citizensResidentsDTO.genderTypesDTO.name}" />
                    
                    <h:outputText value="#{resourcesBundle.marital_status}" styleClass="divtext"/>
                        <t:panelGroup> 
                         <t:inputText styleClass="textbox" forceId="true" id="SocialStatus" disabled="true"
                            value="#{merRaiseMaintainBean.employeesDTO.citizensResidentsDTO.maritalStatusDTO.name}" />
                        <t:inputHidden forceId="true" id="civilExistHidden" value="#{merRaiseMaintainBean.civilExist}" />
                    </t:panelGroup>
                    

</t:panelGrid>

</t:panelGrid>
--%>