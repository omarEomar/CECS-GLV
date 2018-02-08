<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup forceId="true" style="width:99%" id="emp_query_panel_id">
    <t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%" rowClasses="row01,row02" cellpadding="3"
                 cellspacing="0" forceId="true" id="cnt1Panel">
        <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
        <t:inputText forceId="true" id="CivilIdAdd" styleClass="textbox" value="#{detailBeanName.realCivilId}"
                     disabled="true"/>
        <h:outputText value="#{resourcesBundle.the_name}" styleClass="divtext"/>
        <t:inputText forceId="true" id="empName" styleClass="textboxmedium" value="#{detailBeanName.empName}"
                     disabled="true"/>
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <f:verbatim>
                <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
                    <tr>
                        <td width="9">
                            <img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </td>
                        <td class="group_title"></td>
                    </tr>
                </table>
            </f:verbatim>
            &nbsp;
            <t:outputLabel value="#{resourcesBundle.jobInformation}" styleClass="lable01"/>
            <f:verbatim>
                <tr>
                    <td colspan="2" height="1">
                        <img src="../../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                    </td>
                </tr>
            </f:verbatim>
        </t:panelGroup>
        <%-- Row 01--%>
        <t:outputText value="#{resourcesBundle.job}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.employeesDTO.jobDTO.name}" styleClass="textboxmedium" disabled="true"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.work_center}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.employeesDTO.workCenterDTO.name}" styleClass="textboxmedium"
                         disabled="true" style=" width: 486px;"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.currentQul}"/>
        <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.qualificationsDTO.name}"
                     styleClass="textboxmedium"/>
        <h:outputText value="#{resourcesBundle.dateQul}"/>
        <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.qualificationDate}"
                     styleClass="textbox" converter="SqlDateConverter"/>
        <h:outputText value="#{resourcesBundle.cader}"/>
        <t:inputText disabled="true" forceId="true" id="kader" styleClass="textboxmedium"
                     value="#{pageBeanName.salEmpSalaryElementsDTO.salElementGuidesDTO.parentObject.parentObject.parentObject.name}"
                     readonly="true"/>
        <h:outputText value="#{resourcesBundle.group}"/>
        <t:inputText disabled="true" forceId="true" id="workCategory" styleClass="textboxmedium"
                     value="#{pageBeanName.salEmpSalaryElementsDTO.salElementGuidesDTO.parentObject.parentObject.name}"
                     readonly="true"/>
        <h:outputText value="#{resourcesBundle.degree}"/>
        <t:inputText disabled="true" forceId="true" id="workLevel" styleClass="textboxmedium"
                     value="#{pageBeanName.salEmpSalaryElementsDTO.salElementGuidesDTO.parentObject.name}"
                     readonly="true"/>
        <h:outputText value="#{resourcesBundle.raise}"/>
        <t:inputText disabled="true" forceId="true" id="employeesDTOWorkCount2" styleClass="textboxmedium"
                     value="#{pageBeanName.salEmpSalaryElementsDTO.salElementGuidesDTO.countGuide}" readonly="true"/>
        <%-- Row 04--%>
        <t:outputText value="#{resourcesBundle.reasonCurrentDegree}"/>
        <t:inputText value="#{pageBeanName.salEmpSalaryElementsDTO.salDegreeReasonsDTO.name}" styleClass="textboxmedium"
                     disabled="true"/>
        <%-- Row 05--%>
        <t:outputText value="#{resourcesBundle.dateDegree}"/>
        <t:inputText value="#{pageBeanName.salEmpSalaryElementsDTO.fromDate}" styleClass="textbox" disabled="true"
                     converter="SqlDateConverter"/>
        <%-- Row 06--%>
        <t:outputText value="#{resourcesBundle.hireDateMinistry}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.hireDate}" styleClass="textbox" disabled="true"
                     converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.hireBeginDate}"/>
        <t:inputText value="#{pageBeanName.firstHireDate}" styleClass="textbox" disabled="true"
                     converter="SqlDateConverter"/>
        <%-- Row 07--%>
        <t:outputText value="#{resourcesBundle.ministryFileNo}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.ministryFileNo}" styleClass="textbox" disabled="true"/>
        <t:outputText value="#{resourcesBundle.signJobEndDate}"/>
        <t:inputText value="#{pageBeanName.endJobDate}" styleClass="textbox" disabled="true"
                     converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.nextRaiseDate}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.dateOfNextRaise}" converter="SqlDateConverter"
                     styleClass="textbox" disabled="true"/>
        <t:outputText value="#{resourcesBundle.budgetProgram}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.bgtProgramsDTO.name}" styleClass="textboxmedium"
                     disabled="true"/>
        <t:outputText value="#{resourcesBundle.budgetType}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.bgtTypesDTO.name}" styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.empBankName}"/>
        <t:inputText value="#{pageBeanName.personBankAccountsDTO.bankBranchesDTO.banksDTO.name}"
                     styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.bankBranch}"/>
        <t:inputText value="#{pageBeanName.personBankAccountsDTO.bankBranchesDTO.name}" styleClass="textboxmedium"
                     disabled="true"/>
        <t:outputText value="#{resourcesBundle.accountNo}"/>
        <t:inputText value="#{pageBeanName.personBankAccountsDTO.accountNo}" styleClass="textboxmedium"
                     style="width:230px;" disabled="true"/>
    </t:panelGrid>
</t:panelGroup>