<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:messages showDetail="true"/>
<t:panelGrid forceId="true" id="cnt1Panel" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="2">
    <%-- <jsp:include page="/integration/emp/jsps/empData/empDataCivilName.jsp"/>--%>
    <h:outputText value="#{integrationBundle.CivilID}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText rendered="#{!pageBeanName.civilExist}" onblur="document.getElementById('civil_exist_btn').focus();"
                     maxlength="12" forceId="true" id="CivilIdAdd" styleClass="textbox"
                  onkeyup="enabelIntegerOnly(this);"    value="#{pageBeanName.realCivilId}" onkeypress="FireButtonClickOldStyle(event,'civil_exist_btn')"/>
        <t:inputText styleClass="textbox" value="#{pageBeanName.realCivilId}" disabled="true"
                     rendered="#{pageBeanName.civilExist}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{!pageBeanName.civilExist}" id="civil_exist_btn" forceId="true"
                         onclick="resetMsgInAdd(); return validatemyForm('civilValidations');"
                         value="#{globalResources.Available}" styleClass="cssButtonSmall"
                         action="#{pageBeanName.filterByCivilId}"/>
        <a4j:commandButton type="submit" value="#{globalResources.reSetButton}" rendered="#{pageBeanName.civilExist}"
                           styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}" id="reSetData"
                           reRender="period_panel,print_panel,dataT_data_panel,headerMonSalGroup,cnt1Panel,mainDataEmpPanel,displayBtnPanel,paging_panel,monsalGroupId,OperationBar"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                           oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                           action="#{pageBeanName.showSearchForEmployeeDiv}"
                           reRender="lovEmp,mainDataEmpPanel,displayBtnPanel" rendered="#{!pageBeanName.civilExist}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator active="#{!exchangeRequestFormBean.civilExist}" componentToValidate="CivilIdAdd"
                                   group="civilValidations" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:regularExpressionValidator active="#{!exchangeRequestFormBean.civilExist}" componentToValidate="CivilIdAdd"
                                       pattern="/^[0-9]{12}$/" group="civilValidations"
                                       errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                       display="dynamic"/>
        <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                      rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHired" value="#{integrationBundle.emp_not_hired}"
                      rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="payrollInfoExist" value="#{integrationBundle.emp_payroll_info_not_exist}"
                      rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{integrationBundle.emp_not_hired_in_min}"
                      rendered="#{!pageBeanName.empHiredInMin}" styleClass="errMsg"/>
    </t:panelGroup>
    <h:outputText value="#{integrationBundle.EmpName}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" forceId="true" disabled="true" id="EmpName" style="width: 358px;"
                 value="#{pageBeanName.employeeDTO.citizensResidentsDTO.fullName}"/>
    <!--value="#{pageBeanName.salEmpSalaryElementsDTO.employeesDTO.citizensResidentsDTO.fullName}"/>
    -->
    <h:outputText value="#{integrationBundle.value_to_be_exchanged}" styleClass="divtext"/>
    <t:panelGroup colspan="5">
                <t:selectOneRadio id="lawPermitButton" value="#{pageBeanName.law_permit}"   styleClass="lable01" style="float: right; margin-left: 10px;">
                        <f:selectItem  itemLabel="#{integrationBundle.law_permit}" itemValue="1"/>
                        <f:selectItem  itemLabel="#{integrationBundle.exchange_value}" itemValue="0"/>
        <a4j:support event="onclick" actionListener="#{pageBeanName.changeExchangeValueWay}" reRender="scriptGenerator,lawPermitPnl,lawPermit"/>

         </t:selectOneRadio>  
    <t:panelGroup id="lawPermitPnl" forceId="true"  >
      
     <t:inputText rendered="#{pageBeanName.law_permit==0}" forceId="true" id="lawPermit" styleClass="textbox"
                    onkeyup="enabelIntegerOnly(this);"  value="#{pageBeanName.exchangeValue}"/>
  <c2:requiredFieldValidator active="#{exchangeRequestFormBean.law_permit == 0 }" componentToValidate="lawPermit" 
   display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
  </t:panelGroup>

</t:panelGrid>
<t:panelGrid  columns="1" width="100%" rowClasses="row01,row02" cellspacing="0" cellpadding="2" forceId="true" id="periods_panel">

<t:panelGrid  columns="6" width="50%" rowClasses="row01,row02" cellspacing="0" cellpadding="2" forceId="true" id="period_panel">
    <h:outputText value="#{integrationBundle.dma2em_period} #{integrationBundle.days_for_dma2em}" styleClass="divtext"/>
   <t:inputText  forceId="true" id="days_count" styleClass="textboxSmall"
                    onkeyup="enabelIntegerOnly(this);"  value="#{pageBeanName.day}"/> 


  <h:outputText value="#{integrationBundle.months_for_dma2em}" styleClass="divtext"/>
   <t:inputText  forceId="true" id="month_count" styleClass="textboxSmall"
                  onkeyup="enabelIntegerOnly(this);"    value="#{pageBeanName.month}"/> 


  <h:outputText value="#{integrationBundle.years_for_dma2em}" styleClass="divtext"/>
   <t:inputText  forceId="true" id="year_count" styleClass="textboxSmall"
                  onkeyup="enabelIntegerOnly(this);"    value="#{pageBeanName.year}"/> 


</t:panelGrid>
</t:panelGrid>
<t:panelGroup style="display:block;text-align:center;" forceId="true" id="print_panel">
    <t:commandButton value="#{integrationBundle.print_btn}" styleClass="cssButtonSmall" type="button"
                 onclick="if(validatemyForm()){okAction();} else { return false;}" disabled="#{!pageBeanName.civilExist}">
        <a4j:jsFunction name="okAction" action="#{pageBeanName.printExchangeRequestForm}" reRender="reportUrlId"
                     oncomplete="openReportWindow('reportUrlId');"/>
    </t:commandButton>
</t:panelGroup>
<t:inputHidden id="reportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>

<script type="text/javascript">
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
      if (document.getElementById('empHiredInMin') != null) {
          document.getElementById('empHiredInMin').innerHTML = '';
      }
  }
</script>