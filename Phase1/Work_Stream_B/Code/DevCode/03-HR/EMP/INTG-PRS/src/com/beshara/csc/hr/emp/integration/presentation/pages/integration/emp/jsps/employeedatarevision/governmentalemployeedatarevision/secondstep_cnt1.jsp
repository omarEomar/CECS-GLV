<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup forceId="true" style="width:99%" id="emp_query_panel_id">
    <t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%" rowClasses="row01,row02" cellpadding="2"
                 cellspacing="0" forceId="true" id="cnt1Panel">
        <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
        <t:inputText forceId="true" id="CivilIdAdd" styleClass="textboxmedium" value="#{detailBeanName.realCivilId}"
                     disabled="true"/>
        <h:outputText value="#{resourcesBundle.the_name}" styleClass="divtext"/>
        <t:inputText forceId="true" id="empName" styleClass="textboxmedium" value="#{detailBeanName.empName}"
                     disabled="true"/>
                     
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <f:verbatim>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                    <tr>
                        <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
                        <td class="group_title">&nbsp;</f:verbatim> <t:outputLabel value="#{resourcesBundle.jobInformation}" styleClass="lable01"/> <f:verbatim></td>
                    </tr>
                    <tr>
                        <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
                    </tr>
                </table>
            </f:verbatim>
        </t:panelGroup>
        <%-- Row 01--%>
        <t:outputText value="#{resourcesBundle.work_center}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.employeesDTO.workCenterDTO.code.keys[1]}" styleClass="textboxsmall"
                         disabled="true" style=" width: 70px;"/>
            <t:inputText value="#{pageBeanName.employeesDTO.workCenterDTO.name}" styleClass="textboxmedium"
                         disabled="true" style=" width: 486px; margin-right: 10px;"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.JobGroup}"/>
        <t:panelGroup colspan="3">
            <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.jobGroup}"/>
        </t:panelGroup>
        
        <t:outputText value="#{resourcesBundle.job}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.employeesDTO.jobDTO.jobKey}" styleClass="textboxsmall" disabled="true"
                         style="width : 130px ;"/>
            <t:inputText value="#{pageBeanName.employeesDTO.jobDTO.name}" styleClass="textboxmedium" disabled="true"
                         style="margin-right: 10px; width:425px"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.JobDate}"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.jobAssignDate}" converter="SqlDateConverter"/>
       </t:panelGroup>
        
        <t:outputText value="#{resourcesBundle.TechJob}"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.employeesDTO.techJobsDTO.jobKey}" style="width : 130px ;" />
        <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.employeesDTO.techJobsDTO.name}" style="margin-right: 10px; width : 425px;  "/>
        </t:panelGroup>
        
        
        <t:outputText value="#{resourcesBundle.TechJobDate}"/>
        <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.techJobAssignDate}" converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.hireBeginDate}"/>
        <t:inputText value="#{pageBeanName.firstHireDate}" styleClass="textboxmedium" disabled="true"
                     converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.hire_date}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.hireDate}" styleClass="textboxmedium" disabled="true"
                     converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.StartWorkDate}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.startWorkingDate}" styleClass="textboxmedium" disabled="true"
                     converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.moving_Date}"/>
        <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.movingDate}" converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.ministryFileNo}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.ministryFileNo}" styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.signJobEndDate}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.endJobDate}" styleClass="textboxmedium" disabled="true"
                         converter="SqlDateConverter"/>
         </t:panelGroup>
        <%-- <h:outputText value="#{resourcesBundle.currentQul}"/>--%>
        <%-- <t:panelGroup colspan="3"> <t:inputTextarea disabled="true"
             value="#{pageBeanName.personQualificationsDTO.qualificationsDTO.name}" styleClass="textboxmedium"
             style="width:566px;height: 40px;"/> </t:panelGroup>--%>
        <%-- <h:outputText value="#{resourcesBundle.dateQul}"/>--%>
        <%-- <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.qualificationDate}"
             styleClass="textboxmedium" converter="SqlDateConverter"/>--%>
             <t:panelGroup colspan="4" style="background-color:#ffffff;" rendered="#{pageBeanName.movMovingRequestsDTO != null}">
            <f:verbatim>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                    <tr>
                        <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
                        <td class="group_title">&nbsp;</f:verbatim> <t:outputLabel value="#{resourcesBundle.currentQul}" styleClass="lable01"/> <f:verbatim></td>
                    </tr>
                    <tr>
                        <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
                    </tr>
                </table>
            </f:verbatim>
        </t:panelGroup>
        <t:outputLabel value="#{resourcesBundle.cnt_qul_name}" styleClass="lable02"/>
        <t:panelGroup colspan="3">
           
           <t:inputText disabled="true" styleClass="textboxmedium" value="#{pageBeanName.personQualificationsDTO.personQualificationsDTO.qualificationsDTO.qualificationDtlCode}" style="width : 130px ;"/> />
             
            <t:inputText disabled="true" styleClass="textboxmedium2" id="qulNameId_Val" forceId="true"
                         style="width: 425px; margin-right: 10px; "
                         value="#{pageBeanName.personQualificationsDTO.personQualificationsDTO.qualificationsDTO.qualificationName}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.country}"/>
        <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.cntryName}"
                     styleClass="textboxmedium"/>
        <h:outputText value="#{resourcesBundle.emission_side}"/>
        <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.centerName}"
                     styleClass="textboxmedium"/>
        <h:outputText value="#{resourcesBundle.qul_name}"/>
        <t:panelGroup colspan="3">
            <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.cntqualificationName}"
                         styleClass="textboxmedium" style="width: 566px;"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.grade_Value}"/>
        <t:inputText disabled="true" value="#{pageBeanName.personQualificationsDTO.gradeValue}"
                     styleClass="textboxmedium"/>
        <t:outputLabel value="#{resourcesBundle.qul_untilDate}" styleClass="lable02"/>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" value="#{pageBeanName.personQualificationsDTO.untilDate}" id="untilDateID"
                             size="20" maxlength="200" currentDayCellClass="currentDayCell" styleClass="textboxmedium"
                             renderAsPopup="true" align="#{globalResources.align}" disabled="true"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
        </t:panelGroup>
        
        <t:panelGroup colspan="4" style="background-color:#ffffff;" rendered="#{pageBeanName.movMovingRequestsDTO != null}">
            <f:verbatim>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                    <tr>
                        <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
                        <td class="group_title">&nbsp;</f:verbatim> <t:outputLabel value="#{resourcesBundle.ndbInformation}" styleClass="lable01"/> <f:verbatim></td>
                    </tr>
                    <tr>
                        <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
                    </tr>
                </table>
            </f:verbatim>
        </t:panelGroup>
        
        <t:outputText value="#{resourcesBundle.ndbStartDate}" rendered="#{pageBeanName.movMovingRequestsDTO != null}"/>
        <t:inputText value="#{pageBeanName.movMovingRequestsDTO.movingDate}" styleClass="textboxmedium" disabled="true"
                     rendered="#{pageBeanName.movMovingRequestsDTO != null}" converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.ndbEndDate}" rendered="#{pageBeanName.movMovingRequestsDTO != null}"/>
        <t:inputText value="#{pageBeanName.movMovingRequestsDTO.untilDate}" styleClass="textboxmedium" disabled="true"
                     rendered="#{pageBeanName.movMovingRequestsDTO != null}" converter="SqlDateConverter"/>
        <t:outputText value="#{resourcesBundle.ndbJob}" rendered="#{pageBeanName.movMovingRequestsDTO != null}"/>
        <t:panelGroup colspan="3" rendered="#{pageBeanName.movMovingRequestsDTO != null}">
            <t:inputText value="#{pageBeanName.movMovingRequestsDTO.jobsDTO.jobKey}" styleClass="textboxsmall"
                         disabled="true" style="width : 130px ;"/>
            <t:inputText value="#{pageBeanName.movMovingRequestsDTO.jobsDTO.name}" styleClass="textboxmedium"
                         disabled="true" style="width : 420px ; margin-right: 10px;"/>
        </t:panelGroup>
        
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <f:verbatim>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                    <tr>
                        <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
                        <td class="group_title">&nbsp;</f:verbatim> <t:outputLabel value="#{resourcesBundle.FinancialInformation}" styleClass="lable01"/> <f:verbatim></td>
                    </tr>
                    <tr>
                        <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
                    </tr>
                </table>
            </f:verbatim>
        </t:panelGroup>
        
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
        <t:inputText value="#{pageBeanName.salEmpSalaryElementsDTO.currentDegreeDate}" styleClass="textboxmedium" disabled="true"
                     converter="SqlDateConverter"/>
        <%-- Row 06--%>
        <t:outputText value="#{resourcesBundle.hireDegree}"/>
        <t:inputText value="#{pageBeanName.hireDegree}" styleClass="textboxmedium" disabled="true"/>
        
        <t:outputText value="#{resourcesBundle.TotalReward}"/>
        <t:inputText value="#{pageBeanName.totalReward}" styleClass="textboxmedium" disabled="true"/>
        <%-- Row 07--%>
        <t:outputText value="#{resourcesBundle.nextRaiseDate}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.dateOfNextRaise}" converter="SqlDateConverter"
                     styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.budgetProgram}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.bgtProgramsDTO.name}" styleClass="textboxmedium"
                     disabled="true"/>
        <t:outputText value="#{resourcesBundle.budgetType}"/>
        <t:inputText value="#{pageBeanName.employeesDTO.bgtTypesDTO.name}" styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.empBankName}"/>
        <t:inputText value="#{pageBeanName.personBankAccountsDTO.bankBranchesDTO.banksDTO.name}"
                     styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.bankBranch}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.personBankAccountsDTO.bankBranchesDTO.name}" styleClass="textboxmedium"
                         disabled="true"/>
         </t:panelGroup>
        <t:outputText value="#{resourcesBundle.accountNo}"/>
        <t:panelGroup colspan="3">
            <t:inputText value="#{pageBeanName.personBankAccountsDTO.accountNo}" styleClass="textboxmedium"
                         style="width:230px;" disabled="true"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.SocialInsurNo}" />
         <t:panelGroup colspan="3">
        <t:inputText value="#{pageBeanName.employeesDTO.socialInsurNo}" 
                     styleClass="textboxmedium" disabled="true"/>       
      </t:panelGroup>
        
         <t:panelGroup colspan="2">
        <t:outputText value="#{resourcesBundle.emp_start_working_date_from_inf}" />
       </t:panelGroup>
        <t:panelGroup colspan="2">
        <t:inputText value="#{pageBeanName.empHireDateFromInf}" converter="SqlDateConverter"
                     styleClass="textboxmedium" disabled="true"/>
       </t:panelGroup>
       <t:panelGroup colspan="2">
      <t:outputText value="#{resourcesBundle.emp_start_working_date_from_Ins}" />
       </t:panelGroup>
          <t:panelGroup colspan="2">
        <t:inputText value="#{pageBeanName.empHireDateFromSoc}" converter="SqlDateConverter"
                     styleClass="textboxmedium" disabled="true"/>       
      
        </t:panelGroup>
        
        
    </t:panelGrid>
</t:panelGroup>
