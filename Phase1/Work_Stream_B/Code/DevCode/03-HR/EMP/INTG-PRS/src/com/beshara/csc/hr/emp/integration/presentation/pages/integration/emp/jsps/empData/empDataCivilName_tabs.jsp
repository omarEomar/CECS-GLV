<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<!--f:view locale="#{shared_util.locale}" -->
    <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration" var="empIntgResources"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    
    <!--h:form id="myForm"-->

            <h:outputText value="#{resourcesBundle.citizenNo}" styleClass="divtext"/>
            <t:panelGroup colspan="1">
                <t:panelGrid columns="1">
                    <t:panelGroup id="emp_panel" forceId="true">
                        <t:inputText onkeypress="FireButtonClickOldStyle(event,'filterByCivilButton')" tabindex="3"
                                     readonly="#{detailBeanName.civilExist}" maxlength="12" forceId="true"
                                     id="CivilIdAdd" value="#{detailBeanName.realCivilId}" styleClass="textbox"
                                     style="margin-right: 3px;"/>
                        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
                        <f:verbatim>&nbsp;</f:verbatim>
                        <t:panelGroup>
                            <t:commandButton forceId="true" id="filterByCivilButton" type="button" tabindex="4"
                                             value="#{globalResources.Available}" styleClass="cssButtonSmall"
                                             onclick="clearMsgs();return searchAndvalidateEmp('civilId');"
                                             rendered="#{!detailBeanName.civilExist}"/>
                            <a4j:jsFunction name="searchLines" action="#{detailBeanName.filterByCivilId}"
                                            reRender="emp_panel,EmpName,emp_validation"
                                            oncomplete="setFocusOnlyOnElement('monthMenu');"/>
                        </t:panelGroup>
                        <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}" tabindex="4"
                                           oncomplete="setFocusOnlyOnElement('CivilIdAdd');"
                                           rendered="#{detailBeanName.civilExist}" styleClass="cssButtonSmall"
                                           actionListener="#{detailBeanName.reSetData}" reRender="emp_panel,EmpName,emp_validation"/>
                        <f:verbatim>&nbsp;</f:verbatim>
                        <a4j:commandButton type="button" id="Search" styleClass="cssButtonSmall" tabindex="5"
                                           value="#{globalResources.SearchButton}"
                                           oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);setFocusOnElement('civil_div_searchText');"
                                           action="#{detailBeanName.showSearchForEmployeeDiv}" reRender="lovEmp"
                                           rendered="#{!detailBeanName.civilExist}"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                    </t:panelGroup>
                    <t:panelGroup>
                        <c:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic"
                                                  errorMessage="#{globalResources.missingField}" highlight="false"
                                                  uniqueId="CivilIdAddUniqueId" group="civilId"
                                                  active="#{salCalcEmpMonthSalarySearchDetailsMaintain.calcPageMode == salCalcEmpMonthSalarySearchDetailsMaintain.oneEmpMode}"/>
                        <c:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"
                                                      errorMessage="#{globalResources.civil_no_not_less_than_12}"
                                                      highlight="false" display="dynamic" group="civilId"
                                                      active="#{salCalcEmpMonthSalarySearchDetailsMaintain.calcPageMode == salCalcEmpMonthSalarySearchDetailsMaintain.oneEmpMode}"/>
                        <t:panelGroup id="emp_validation" forceId="true">
                         <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                                          rendered="#{!detailBeanName.validCivilId}" styleClass="errMsg"/>
                         <t:outputText forceId="true" id="empHired" value="#{resourcesBundle.emp_not_hired}"
                                          rendered="#{!detailBeanName.empHired}" styleClass="errMsg"/>
                         <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                                          rendered="#{!detailBeanName.empHiredInMin}" styleClass="errMsg"/>                 
                         <t:outputText  forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!detailBeanName.payrollInfoExist}" styleClass="errMsg"/>
                             </t:panelGroup>
                    </t:panelGroup>
                </t:panelGrid>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.employee_name}" styleClass="divtext"/>
            <t:inputText styleClass="textboxmedium2" forceId="true" id="EmpName" style="margin-right: 3px;"
                         disabled="true" value="#{detailBeanName.emp.citizensResidentsDTO.fullName}"/>
            

    <%--</h:form>
</f:view>--%>