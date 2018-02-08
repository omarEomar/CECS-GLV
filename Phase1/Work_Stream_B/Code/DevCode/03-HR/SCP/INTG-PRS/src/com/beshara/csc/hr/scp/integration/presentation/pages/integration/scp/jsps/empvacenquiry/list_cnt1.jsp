<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="integrationBundle"/>

<t:messages showDetail="true"/>
<t:panelGrid forceId="true" id="cnt1Panel" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="2">
    
    <%--<jsp:include page="/integration/emp/jsps/empData/empDataCivilName.jsp"/>--%>
    
    <h:outputText value="#{integrationBundle.CivilID}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText rendered="#{!pageBeanName.civilExist}" onblur="document.getElementById('civil_exist_btn').focus();"
                     maxlength="12" forceId="true" id="CivilIdAdd" styleClass="textbox" value="#{pageBeanName.realCivilId}"
                     onkeypress="FireButtonClickOldStyle(event,'civil_exist_btn')"/>
        <t:inputText styleClass="textbox" value="#{pageBeanName.realCivilId}" disabled="true"
                     rendered="#{pageBeanName.civilExist}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{!pageBeanName.civilExist}" id="civil_exist_btn" forceId="true"
                         onclick="resetMsgInAdd(); return validatemyForm('civilValidations');" value="#{globalResources.Available}"
                         styleClass="cssButtonSmall" action="#{pageBeanName.filterByCivilId}"/>
        <a4j:commandButton type="submit" value="#{globalResources.reSetButton}" rendered="#{pageBeanName.civilExist && !pageBeanName.comeFromSalReveiw}"
                           styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}" id="reSetData"
                           reRender="dataT_data_panel,headerMonSalGroup,cnt1Panel,mainDataEmpPanel,displayBtnPanel,paging_panel,monsalGroupId,OperationBar"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                           oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                           action="#{pageBeanName.showSearchForEmployeeDiv}"
                           reRender="lovEmp,mainDataEmpPanel,displayBtnPanel" rendered="#{!pageBeanName.civilExist}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator active="#{!empVacMonSalEnquiryListBean.civilExist}" componentToValidate="CivilIdAdd"
                                 group="civilValidations"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:regularExpressionValidator active="#{!empVacMonSalEnquiryListBean.civilExist}" componentToValidate="CivilIdAdd"
                                       pattern="/^[0-9]{12}$/" group="civilValidations"
                                       errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                       display="dynamic"/>
        <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                      rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHired" value="#{integrationBundle.emp_not_hired}"
                      rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="payrollInfoExist" value="#{integrationBundle.emp_payroll_info_not_exist}"
                      rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{scpIntgResources.emp_not_hired_in_min}"
                          rendered="#{!pageBeanName.empHiredInMin}" styleClass="errMsg"/>
    </t:panelGroup>
    <h:outputText value="#{integrationBundle.EmpName}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" forceId="true" disabled="true" id="EmpName"  style="width: 358px;"
                 value="#{pageBeanName.salEmpSalaryElementsDTO.employeesDTO.citizensResidentsDTO.fullName}"/>
    
    <h:outputText value="#{integrationBundle.Degree}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText styleClass=" textboxXlarge" forceId="true" id="degree" disabled="true"
                     value="#{pageBeanName.degree}" style="width: 774px !important;"/>
    </t:panelGroup>
    
    <h:outputText value="#{integrationBundle.WorkCenter}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" disabled="true" forceId="true" id="WorkCenter"
                 value="#{pageBeanName.salEmpSalaryElementsDTO.employeesDTO.workCenterDTO.name}"/>
                 
    <h:outputText value="#{integrationBundle.budgetProgram}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetProgram" value="#{pageBeanName.salEmpSalaryElementsDTO.employeesDTO.bgtProgramsDTO.name}"  style="width: 358px;"/>
    
    <h:outputText value="#{integrationBundle.budgetType}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetType" value="#{pageBeanName.salEmpSalaryElementsDTO.employeesDTO.bgtTypesDTO.name}" />
    </t:panelGroup>
    
     
   
    
    
    
</t:panelGrid>

<script type="text/javascript">
    function myValidateDate(dayRadio) {
        //alert("dayRadio = "+dayRadio);
        if(dayRadio == '0'){
            //alert("0");
            return validatemyForm('dateValidation');
        }
        else {
            //alert("1");
         return true;
        }
    }
  function resetMsgInAdd() {
      if (document.getElementById('invalCivilID') != null) {
          document.getElementById('invalCivilID').innerHTML = '';
      }
      if (document.getElementById('empHired') != null) {
          document.getElementById('empHired').innerHTML = '';
      }
      if (document.getElementById('payrollInfoExist') != null) {
          document.getElementById('payrollInfoExist').innerHTML = '';
      }
  }

  /*function doOnBlur() {

      if (isVisibleComponent("divLov")) {
          settingFoucsAtLovDiv();
      }
      else {
          document.getElementById('CivilIdAdd').focus();
      }
  }*/

  //foucsAddbuttonOnList();

  /*function foucsAddbuttonOnList() {
      if (document.getElementById('CivilIdAdd') != null) {
          document.getElementById('CivilIdAdd').focus();
      }
  }*/
</script>
