<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
<htm:style>

.divContent1Dynamic {
    border-radius: 6px;
    box-shadow: 0 0 3px #5D9BC5;
    margin: 8px 0 3px 4px;
    overflow-x: hidden;
    overflow-y: auto;
    padding: 0;
    width:728px !important;
}

.empListOfValuesStyle{
    background-clip: padding-box;
    background-color: #2779B2;
    background-image: url("../../images/popup_bg.jpg");
    background-position: right top;
    background-repeat: repeat-x;
    border: 1px solid #2779B2;
    border-radius: 6px;
    box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
    direction: rtl;
    left: 13%;
    outline: 0 none;
    position: absolute;
    top: 30px !important;
    visibility: hidden;
    width: 550px !important;
    z-index: 100;
}
</htm:style>

<t:panelGroup forceId="true" id="hideDivImgId" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingCustHeight('hideDivImgId', 'pg_main_Employee_Details', 'tablesPanel')"></t:panelGroup>
</t:panelGroup>
<t:panelGroup>
<t:panelGrid forceId="true" id="pg_main_Employee_Details" columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" border="0" >
    <%--- Start of Row 1--%>
      <h:outputText value="#{intgResources.typeDecisionLabel}" />
      <t:inputText   styleClass="textboxmedium" value="#{pageBeanName.pageDTO.typesDTO.name}" disabled="true"/>
      
      <h:outputText value="#{intgResources.issuance_year}" />
      <t:inputText  styleClass="textbox" value="#{pageBeanName.pageDTO.yearsDTO.code.key}" disabled="true"/>
      
    <%--- Start of Row 2--%>
      <h:outputText value="#{intgResources.decision_number}" rendered="#{pageBeanName.maintainMode!=0}"/>
  
      <%--<t:inputText rendered="#{pageBeanName.maintainMode==0}" forceId="true" disabled="true" id="decision_ref_decisionNo" styleClass="textbox" value="#{pageBeanName.pageDTO.decisionNumber}"/>--%>
      <t:inputText rendered="#{pageBeanName.maintainMode!=0}" forceId="true" disabled="true" id="decision_ref_decisionNoEdit" styleClass="textbox" value="#{pageBeanName.pageDTO.regNum!=null? pageBeanName.pageDTO.regNum : pageBeanName.pageDTO.regAutoNumber}"/>
      <t:panelGroup colspan="2" rendered="#{pageBeanName.maintainMode==0}"/> 
      
      <h:outputText value="#{intgResources.dec_title}" />
      <t:panelGroup colspan="5">
        <t:inputText disabled="true"  styleClass="TextBoxLarge2" style="width: 642px;" value="#{pageBeanName.pageDTO.decisionTitle}"/>
      </t:panelGroup>
      
      
       <h:outputText value="#{intgResources.civil_id}"  />  
       <t:panelGroup >
       
            <t:inputText  styleClass="textboxsmall2" value="#{detailBeanName.civilID}" maxlength="12" forceId="true" id="civilIdInTxt"  disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode==2}"
                converter="javax.faces.Long" onkeyup="disableCharacters(this);" onkeypress="FireButtonClick('myForm:ava');" tabindex="1" />
                
                 <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode==0}"/>
                 <f:verbatim>&nbsp;&nbsp; </f:verbatim>
                 <t:commandButton id="ava" value="#{globalResources.addButton}" styleClass="cssButtonSmall" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode==2}"
                                   action="#{settlementDecisionCycleEmployeesMaintainBean.findEmployeeByCivilIDFromEmployees}"  
                                   onclick="return(validateRequiredElement('civilIdInTxt','#{intgResources.missingCivilID}','errorCivilMessage') && checkExactLength('12','civilIdInTxt','errorCivilMessage','#{intgResources.civilLengthError}'));" >
                 </t:commandButton> 
               
               <f:verbatim>&nbsp;&nbsp; </f:verbatim>
               <t:outputText id="errorCivilMessage1" forceId="true" styleClass="errMsg" value="#{detailBeanName.civil_exist}"/>
                <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!detailBeanName.validCivilId}" styleClass="errMsg"/>
                <t:outputText  forceId="true" id="empHired"   value="#{intgResources.civil_not_Employee}"  rendered="#{!detailBeanName.empHired}" styleClass="errMsg"/>
                <t:outputText  forceId="true" id="empHiredInMin"   value="#{intgResources.emp_not_hired_in_min}"  rendered="#{!detailBeanName.empHiredInMin}" styleClass="errMsg"/>
                <t:outputText  forceId="true" id="payrollInfoExist"   value="#{intgResources.civil_notHas_PayrollInfo}"  rendered="#{!detailBeanName.payrollInfoExist}" styleClass="errMsg"/>
                <t:outputText  forceId="true" id="empAddedBefore"   value="#{intgResources.employee_added_before}"  rendered="#{detailBeanName.empAddedBefore}" styleClass="errMsg"/>
               <t:outputText id="errorCivilMessage" forceId="true" styleClass="errMsg"/>
                                
            </t:panelGroup>
            
            
            <%--h:outputText value="#{intgResources.decision_employees_emp_name}" />
            <t:panelGroup colspan="3">
            <t:inputText forceId="true" id="empNameId"  styleClass="textboxmedium" value="#{detailBeanName.empName}" disabled="true"/>
        </t:panelGroup--%>
        
        
         
</t:panelGrid>
 <%--<t:panelGrid id="buttonsPanel" columns="5" align="center"
               dir="# {shared_util.pageDirection}">--%>
          <%--<a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                           oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"
                           action="#{specialExtraDecisionCycleEmployeesMaintainBean.showSearchForEmployeeDiv}" id="searchLovDiv"
                           reRender="lovEmpPaging,dataT_data_panel"/>--%>
     <%--<a4j:commandButton  type="button"
                         value="#{globalResources.SearchButton}" 
                         styleClass="cssButtonSmall"
                         oncomplete="changeVisibilityDiv(window.blocker,window.lovEmpPaging);settingFoucsAtEmpLovDiv();setFocusOnlyOnElement('civil_div_searchText');" 
                         action="#{decisionCycleEmployeesMaintainBean.showSearchForEmployeeDiv}"
                         reRender="lovEmpPaging"
                         tabindex="2"  disabled="#{pageBeanName.showOnly}"/>--%>
      <%--<t:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmall" rendered="#{!pageBeanName.civilExist}"
                                   action="#{pageBeanName.getEmpInfo}" onclick="clearOutputText('errorId');return validatemyForm();" />--%>
                                   
      <%--<a4j:commandButton value="#{globalResources.reSetButton}" styleClass="cssButtonSmall" rendered="#{pageBeanName.civilExist}"
                                  action="#{pageBeanName.reSetData}" reRender="empInquiryCnt1Pnl,OperationBar,dataT_data_panel"/>--%>                   
                      
     <%--<a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                           oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"
                           action="#{pageBeanName.showSearchForEmployeeDiv}" id="searchLovDiv"
                           reRender="resetBtn,lovEmp,mainDataEmpPanel,displayBtnPanel"/>--%>
     <%--<t:commandButton value="#{globalResources.advancedsearch}"
                       styleClass="cssButtonSmall"
                       action="#{decisionCycleEmployeesMaintainBean.navigateAdd}"
                       tabindex="3" disabled="#{pageBeanName.showOnly}"/>--%>
     <%--</t:panelGrid>--%>
  </t:panelGroup>

