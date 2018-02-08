<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="integrationBundle"/>

<%--<t:saveState value="#{pageBeanName.salEmpSalaryElementsDTO}"/>--%>
<%--<t:saveState value="#{pageBeanName.kwtCitizen}"/>--%>
<%--
<t:saveState value="#{pageBeanName.enableEmpLovDiv}"/>
<t:saveState value="#{pageBeanName.civilIdNotReal}"/>--%>

<%--t:panelGrid columns="4" width="100%"  rowClasses="row01,row02" cellpadding="0" cellspacing="0" forceId="true" id="cnt1Panel"  --%>
         
    <h:outputText value="#{resourcesBundle.CivilID}" styleClass="divtext"/>
   
    <t:panelGroup  id="emp_panel" forceId="true">
        <t:inputText rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist :!pageBeanName.employeeHelper.civilExist}" tabindex="1"   onkeyup="disableCharacters(this)" 
              maxlength="12" forceId="true"  onkeypress="myFireButtonClick(event, 'civil_exist_btn');"
              id="CivilIdAdd" styleClass="textbox"  value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}" 
              style="#{  (appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showPayrollInfo) ? '' : ' margin-right: 6px;' ) : (pageBeanName.employeeHelper.showPayrollInfo ) ? '' : ' margin-right: 6px;' }"
              /><%--keyPressNumberOrEnter(false);--%>
         <t:inputText disabled="true"  styleClass="textbox"  
         value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}" 
         rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"
         style="#{  (appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showPayrollInfo) ? 'margin-right: -16px' : '' ) : (pageBeanName.employeeHelper.showPayrollInfo ) ? 'margin-right: -16px' : '' }"/>
        
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist : !pageBeanName.employeeHelper.civilExist}" id="civil_exist_btn" forceId="true" onclick="resetMsgInAdd(); return validatemyForm('empData');" tabindex="2" 
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{appMainLayout.manyToMany ? detailBeanName.filterByCivilId : pageBeanName.filterByCivilId}"/>

        <t:commandButton rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}" 
            id="reset_civil_btn" forceId="true" onclick="resetMsgInAdd(); return validatemyForm('empData');" tabindex="2" 
            value="#{globalResources.reSetButton}"  styleClass="cssButtonSmall"  action="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.reSetData : pageBeanName.employeeHelper.reSetData}"/>
      
        <%--<a4j:commandButton value="#{globalResources.reSetButton}" 
            rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"  
            styleClass="cssButtonSmall"  actionListener="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.reSetData : pageBeanName.employeeHelper.reSetData}"  
            reRender="empPayrollInfoGrp,cnt1Panel,scriptGenerator,scriptpanel,dataT_data_panel,OperationBar,paging_panel,emp_panel,EmpName,emp_validation"/>--%><%--oncomplete="foucsAddbuttonOnList();foucsAddbuttonOnList();" --%>
        <f:verbatim> &nbsp; </f:verbatim>
        <a4j:commandButton type="button"  value="#{globalResources.SearchButton}" styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();" action="#{appMainLayout.manyToMany ? detailBeanName.showSearchForEmployeeDiv : pageBeanName.showSearchForEmployeeDiv}"  tabindex="3"
               reRender="lovEmp,mainDataEmpPanel" rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist : !pageBeanName.employeeHelper.civilExist}" />        
        <f:verbatim ><br/></f:verbatim>
        <c2:requiredFieldValidator  group="empData"   componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/><%--active="#{!stopSalaryListBean.civilExist}"--%>
        <c2:regularExpressionValidator group="empData"  componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic"/><%--active="#{!stopSalaryListBean.civilExist}"--%>

        <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.validCivilId : !pageBeanName.employeeHelper.validCivilId}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empHired"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empHired : !pageBeanName.employeeHelper.empHired}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.payrollInfoExist : !pageBeanName.employeeHelper.payrollInfoExist}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                          rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empHiredInMin : !pageBeanName.employeeHelper.empHiredInMin}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empEndedService"   value="#{resourcesBundle.emp_not_ended_service}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empEndedService : !pageBeanName.employeeHelper.empEndedService}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empLastDegNotMokafaaShamla"   value="#{resourcesBundle.empLastDegNotMokafaaShamla}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empLastDegNotMokafaaShamla : !pageBeanName.employeeHelper.empLastDegNotMokafaaShamla}" styleClass="errMsg"/>
        <%--<t:outputText forceId="true" id="invalNat" value="#{pageBeanName.natMsg}"
                          rendered="#{(appMainLayout.manyToMany ? detailBeanName.employeeHelper.checkNat : pageBeanName.employeeHelper.checkNat ) && pageBeanName.invalidNationality}" styleClass="errMsg"/>--%>
    </t:panelGroup> 

                          
    <h:outputText value="#{resourcesBundle.EmpName}" styleClass="divtext"/>                         
        
    <t:panelGroup>
        
        <t:inputText styleClass="textboxmedium2" style="margin-right: 3px;" disabled="true" forceId="true" id="EmpName" value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.citizensResidentsDTO.fullName : pageBeanName.employeeHelper.employeesDTO.citizensResidentsDTO.fullName}"/> 

<%--<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilId : pageBeanName.employeeHelper.civilId}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.validCivilId : pageBeanName.employeeHelper.validCivilId}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.empHired : pageBeanName.employeeHelper.empHired}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.empHiredInMin : pageBeanName.employeeHelper.empHiredInMin}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollInfoExist : pageBeanName.employeeHelper.payrollInfoExist}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO : pageBeanName.employeeHelper.employeesDTO}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showPayrollInfo : pageBeanName.employeeHelper.showPayrollInfo}"/>--%>
<f:verbatim>
<script type="text/javascript"> 
    
    function resetMsgInAdd()
    {
        if(document.getElementById('invalCivilID') != null)
        {
            document.getElementById('invalCivilID').innerHTML='';
        } 
        if(document.getElementById('empHired')!=null)
        {
            document.getElementById('empHired').innerHTML='';
        }
        if(document.getElementById('payrollInfoExist')!=null)
        {
            document.getElementById('payrollInfoExist').innerHTML='';
        }
        if(document.getElementById('empHiredInMin')!=null)
        {
            document.getElementById('empHiredInMin').innerHTML='';
        }
    }
    
    function doOnBlur() {
        
        if(isVisibleComponent("lovEmp")){
          
            settingFoucsAtEmpLovDiv();
            settingFoucsAtEmpLovDiv();
        } else {
       //    alert('now in list part');
           document.getElementById('CivilIdAdd').focus();
           document.getElementById('CivilIdAdd').focus();
        //   foucsAddbuttonOnList();
        }
    }
    
    function myFireButtonClick(e, buttonId) {
        if (e.keyCode == 13) {
            e.cancelBubble = true;
            e.returnValue = false;
            e.preventDefault();
            var button = document.getElementById(buttonId);
            button.click();
        }
    }
    
 function nextMoveByTab()
    { 
      document.getElementById('civil_exist_btn').focus();
      document.getElementById('civil_exist_btn').focus(); 
    }

</script>
</f:verbatim>
                       
</t:panelGroup>                          

    <t:panelGroup colspan="4"  id="empPayrollInfoGrp" forceId="true" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showPayrollInfo : pageBeanName.employeeHelper.showPayrollInfo}" >
    <t:panelGrid columns="4" rowClasses="row02,row01" width="100%" cellpadding="0" cellspacing="0" id="empPayrollInfoGrid" forceId="true" >
        <h:outputText value="#{integrationBundle.Degree}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:inputText styleClass=" textboxXlarge" forceId="true" id="degree" disabled="true"
                         value="#{appMainLayout.manyToMany ? detailBeanName.degree : pageBeanName.degree}" style="width: 734px !important;#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        </t:panelGroup>
        
        <h:outputText value="#{integrationBundle.WorkCenter}" styleClass="divtext"/>
        <t:inputText styleClass="textboxlarge" disabled="true" forceId="true" id="WorkCenter"
                     value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.workCenterDTO.name : pageBeanName.employeeHelper.employeesDTO.workCenterDTO.name}" style="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
                     

        <h:outputText value="#{integrationBundle.job_name}" styleClass="divtext"/>
        <t:panelGroup >
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="empJob"
                     value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.jobDTO.name : pageBeanName.employeeHelper.employeesDTO.jobDTO.name}"
                     style="width: 280px; #{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
         </t:panelGroup>
         
         <h:outputText value="#{integrationBundle.budgetProgram}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetProgram" 
            value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.workCenterDTO.name : pageBeanName.employeeHelper.employeesDTO.bgtProgramsDTO.name}"  
            style="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        
        <h:outputText value="#{integrationBundle.budgetType}" styleClass="divtext"/>
        <%--<t:panelGroup colspan="#{(appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showBankAccData) ? 1 : 3 ) : (pageBeanName.employeeHelper.showBankAccData ) ? 1 : 3 }">--%>
        <t:panelGroup >
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetType" 
            value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.workCenterDTO.name : pageBeanName.employeeHelper.employeesDTO.bgtTypesDTO.name}"  
            style="width: 280px; #{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        </t:panelGroup>
        

        
    <t:panelGroup colspan="4" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showBankAccData : pageBeanName.employeeHelper.showBankAccData}">
        <h:outputText value="#{resourcesBundle.sal_calc_date}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" forceId="true" id="salCalcDate" value="#{pageBeanName.monSalDTO.calcDate}" >
        <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2" />
        </t:inputText>
        <h:outputText value="#{resourcesBundle.sal_status}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 22px 0px 10px;" forceId="true" id="salStatus" value="#{pageBeanName.monSalDTO.paymentStatusDTO.name}" />
    <%--</t:panelGroup>
    
    <t:panelGroup colspan="2" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showBankAccData : pageBeanName.employeeHelper.showBankAccData}">--%>
          <h:outputText value="#{resourcesBundle.sheet_no}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 25px 0px 10px;" forceId="true" id="sheet_no" value="#{paySlipMaintainQueryBean.sheetCode}" />
        
        <h:outputText value="#{resourcesBundle.bank_name}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 22px 0px 0px;" forceId="true" id="bankName" value="#{pageBeanName.monSalDTO.bankBranchesDTO.banksDTO.name}" />
    </t:panelGroup>
    
    <t:panelGroup colspan="4" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showBankAccData : pageBeanName.employeeHelper.showBankAccData}">
        <h:outputText value="#{resourcesBundle.branch_name}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 70px 0px 0px;" forceId="true" id="branchName" value="#{pageBeanName.monSalDTO.bankBranchesDTO.name}" />
        
        <h:outputText value="#{resourcesBundle.account_no}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText disabled="true" styleClass="textboxlarge" style="margin: 0 13px 0 0; width: 290px;" forceId="true" id="accountNo" value="#{pageBeanName.monSalDTO.accountChekNo}" />
            <t:inputHidden forceId="true" id="civilExistHidden" value="#{pageBeanName.civilExist}" />
        </t:panelGroup>
    </t:panelGroup>

        
        </t:panelGrid>
    </t:panelGroup>
                   

  
<%--/t:panelGrid--%>

