<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<t:panelGroup  style="width:100%" styleClass="delDivScroll">
    <f:verbatim>
        <div id="TabbedPanels1" class="TabbedPanels">
            <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" tabindex="0">${regResources.select_decision_employees_main_data}</li>
                <li class="TabbedPanelsTab" tabindex="1">${regResources.select_decision_employees_admin_emp_data}</li>
                <li class="TabbedPanelsTab" tabindex="2">${regResources.select_decision_employees_currency_job_data}</li>
    
            </ul>
            <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
    </f:verbatim>
    <%--h:outputText value="#{regResources.select_decision_employees_main_data}"  styleClass="lable01" /--%>
    <t:panelGrid id="pnlgrd_dec_radio" columns="1" rowClasses="row02" width="100%" cellpadding="0" cellspacing="0" align="center">
        <t:selectOneRadio id="decRadioButton" value="#{detailBeanName.kuwityType}" converter="javax.faces.Long" style="font-family: Tahoma; font-size: 10pt;">
            <a4j:support event="onclick" action="#{detailBeanName.changeKuwityType}" reRender="pnlgrd_dec_frist"/>
            <f:selectItem  itemLabel="#{regResources.select_decision_employees_kwt}" itemValue="#{detailBeanName.kuwity}"/>
            <f:selectItem  itemLabel="#{regResources.select_decision_employees_non_kwt}" itemValue="#{detailBeanName.nonKuwity}"/>
        </t:selectOneRadio>
    </t:panelGrid>

            <t:panelGrid id="pnlgrd_dec_frist" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" forceId="true">
                <!--- Start of Row 1-->
                    <h:outputText value="#{regResources.select_decision_employees_civilId}"/>
                    <t:inputText forceId="true"  id="employees_civilId" size="20" onkeypress="keyPressNumber(false,event);" onkeyup="disableCharacters(this)" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.civilId}" converter="javax.faces.Long"/>
                  <!--- Non Kuwity -->
                    <h:outputText value="#{regResources.select_decision_employees_passportNo}" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}"/>
                    <t:inputText forceId="true"  id="employees_passportNo" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.passportNo}" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}"/>

                  <!--- Kuwity -->
                    <h:outputText value="   " rendered="#{detailBeanName.kuwityType == detailBeanName.kuwity}"/>
                    <h:outputText value="   " rendered="#{detailBeanName.kuwityType == detailBeanName.kuwity}"/>
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    <h:outputText value="#{regResources.select_decision_employees_first_name}"/>
                    <t:inputText forceId="true"  id="employees_first_name" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.firstName}"/>

                    <h:outputText value="#{regResources.select_decision_employees_second_name}"/>
                    <t:inputText forceId="true"  id="employees_second_name" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.secondName}"/>
                <!--- End of Row 2-->
                <!--- Start of Row 3-->
                    <h:outputText value="#{regResources.select_decision_employees_third_name}"/>
                    <t:inputText forceId="true"  id="employees_third_name" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.thirdName}"/>
                    
                    <h:outputText value="#{regResources.select_decision_employees_last_name}"/>
                    <t:inputText forceId="true"  id="employees_last_name" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.lastName}"/>
                <!--- End of Row 3-->
                <!--- Start of Row 4-->
                    <h:outputText value="#{regResources.select_decision_employees_nickName}"/>
                    <t:inputText forceId="true"  id="employees_nickName" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.familyName}"/>
                    
                    <h:outputText value="#{regResources.select_decision_employees_eng_name}"/>
                    <t:inputText forceId="true"  id="employees_eng_name" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.englishName}"/>
                <!--- End of Row 4-->
              <%--/t:panelGrid>  
              <f:verbatim><br/></f:verbatim>
              <t:panelGrid id="pnlgrd_dec_second" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"--%>
                <!--- Start of Row 1-->
                <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                    <h:outputText value="#{regResources.select_decision_employees_birthDate}"/>
                    <t:panelGroup>
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.employeeSearchDTO.birthDate}" id="clndr_maintain_employees_birthDate"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_employees_birthDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_employees_birthDate_divID"/>
                    </t:panelGroup> 
                    
                    <h:outputText value="#{regResources.select_decision_employees_social_status}"/>
                    <t:selectOneMenu forceId="true"  id="employees_social_status" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.maritalStatusCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.maritalStatusTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.marstatusCode}" var="entry"/>              
                    </t:selectOneMenu>                    
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    <h:outputText value="#{regResources.select_decision_employees_gender}"/>
                    <t:selectOneMenu forceId="true"  id="employees_gender" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.genderTypeCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.genderTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.gentypeCode}" var="entry"/>              
                    </t:selectOneMenu>                    
                    
                    <h:outputText value="#{regResources.select_decision_employees_reliogn}"/>
                    <t:selectOneMenu forceId="true"  id="employees_reliogn" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.religionCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.relgionTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.religionCode}" var="entry"/>              
                    </t:selectOneMenu>                    
                <!--- End of Row 2-->
                <!--- Start of Row 3-->
                    <h:outputText value="#{regResources.select_decision_employees_phoneNo}"/>
                    <t:inputText forceId="true"  id="employees_phoneNo" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.phonesNo}"/>
                    
                    <h:outputText value="#{regResources.select_decision_employees_mobile}"/>
                    <t:inputText forceId="true"  id="employees_mobile" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.mobileNo}"/>
                <!--- End of Row 3-->                                  
              <%--/t:panelGrid>
              <f:verbatim><br/></f:verbatim>
              <t:panelGroup id="pnlgrp_dec_secondNonKuwity" forceId="true">
                  <t:panelGrid id="pnlgrd_dec_secondNonKuwity" forceId="true" columns="4" rowClasses="row01,row02" width="98%" cellpadding="0" cellspacing="0" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}">
                  --%>
                    <!--- Start of Row 1-->
                    <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                        <h:outputText value="#{regResources.select_decision_employees_nationalty}"/>
                        <t:selectOneMenu forceId="true" id="employees_nationalty" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.nationality}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                            <t:selectItems value="#{detailBeanName.nationalties}" itemLabel="#{entry.name}" itemValue="#{entry.code.cntryCode}" var="entry"/>              
                        </t:selectOneMenu>
                        <h:outputText value=""/>
                        <h:outputText value=""/>
                    <!--- End of Row 1-->
                    <!--- Start of Row 2-->
                        <h:outputText value="#{regResources.select_decision_employees_resdientType}"/>
                        <t:selectOneMenu forceId="true"  id="employees_resdientType" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.residentTypeCode}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                            <t:selectItems value="#{detailBeanName.resdientTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.restypeCode}" var="entry"/>              
                        </t:selectOneMenu>
                        
                        <t:outputText value="#{regResources.select_decision_employees_resdient_end_date}"/>
                        <t:panelGroup forceId="true">
                            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.employeeSearchDTO.endResidentDate}" id="clndr_maintain_employees_resdient_end_date"
                                    size="20" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                                    renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                                <f:converter converterId="SqlDateConverter"/>                        
                            </t:inputCalendar>
                            <f:verbatim><br/></f:verbatim>
                            <c2:dateFormatValidator componentToValidate="clndr_maintain_employees_resdient_end_date" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_employees_resdient_end_date_divID" />
                        </t:panelGroup>
                    <!--- End of Row 2-->         
                  <%--/t:panelGrid>
                </t:panelGroup>
              <f:verbatim><br/></f:verbatim>
              <t:panelGrid id="pnlgrd_dec_third" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"--%>
                <!--- Start of Row 1-->
                <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                    <h:outputText value="#{regResources.select_decision_employees_government}"/>
                    <t:selectOneMenu forceId="true"  id="employees_government" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.mapCode}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.governments}" itemLabel="#{entry.name}" itemValue="#{entry.code.mapCode}" var="entry"/>              
                    </t:selectOneMenu>
                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    <h:outputText value="#{regResources.select_decision_employees_health_status}"/>
                    <t:selectOneMenu forceId="true"  id="employees_health_status" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.capstatusCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.capTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.capstatusCode}" var="entry"/>
                    </t:selectOneMenu>

                    <h:outputText value="#{regResources.select_decision_employees_special_case}"/>
                    <t:selectOneMenu forceId="true"  id="employees_special_case" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.specialCaseTypeCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.specialCaseTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.spccsetypeCode}" var="entry"/>              
                    </t:selectOneMenu>
                <!--- End of Row 2-->
              </t:panelGrid>
          <f:verbatim>
            </div>
            <div class="TabbedPanelsContent">
          </f:verbatim>

        <%--h:outputText value="#{regResources.select_decision_employees_admin_emp_data}"  styleClass="lable01" /--%>
        <t:panelGrid id="pnlgrd_dec_forth" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
            <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                <!--- Start of Row 1-->
                    <h:outputText value="#{regResources.select_decision_employees_fe2ah}"/>
                    <t:selectOneMenu forceId="true"  id="employees_fe2ah" styleClass="DropdownboxMedium2" value="#{detailBeanName.categoryID}" converter="javax.faces.Long">
                        <a4j:support event="onclick" action="#{detailBeanName.fillMinistries}" reRender="employees_geha" onsubmit="validatemyForm();"/>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.categories}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                    <h:outputText value="#{regResources.select_decision_employees_geha}"/>
                    <t:selectOneMenu forceId="true"  id="employees_geha" styleClass="DropdownboxMedium2" value="#{detailBeanName.ministryID}" converter="javax.faces.Long">
                        <a4j:support event="onclick" action="#{detailBeanName.fillWorkMinistries}" reRender="employees_work_ministry" onsubmit="validatemyForm();"/>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.ministries}" itemLabel="#{entry.name}" itemValue="#{entry.code.minCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                    <h:outputText value="#{regResources.select_decision_employees_work_ministry}"/>
                    <t:selectOneMenu forceId="true"  id="employees_work_ministry" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.workCenterCode}"> <%--..--%>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.workMinistries}" itemLabel="#{entry.name}" itemValue="#{entry.code.wrkCode}" var="entry"/>              
                    </t:selectOneMenu>
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    <h:outputText value="#{regResources.select_decision_employees_geha_fileNo}"/>
                    <t:inputText forceId="true"  id="employees_geha_fileNo" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.ministryFileNo}"/>
                    
                    <h:outputText value="#{regResources.select_decision_employees_dewan_fileNo}"/>
                    <t:inputText forceId="true"  id="employees_dewan_fileNo" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.cscFileNo}"/>
                <!--- End of Row 2-->
                <!--- Start of Row 3-->
                    <h:outputText value="#{regResources.select_decision_employees_hire_date}"/>
                    <t:panelGroup forceId="true"> 
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.employeeSearchDTO.hireDate}" id="clndr_maintain_employees_hire_date"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_employees_hire_date" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_employees_hire_date_divID"/>
                    </t:panelGroup> 
                    
                    <h:outputText value="#{regResources.select_decision_employees_hire_type}"/>
                    <t:selectOneMenu forceId="true"  id="employees_hire_type" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireTypes}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.hireTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirtypeCode}" var="entry"/>              
                    </t:selectOneMenu>
                <!--- End of Row 3-->
                <!--- Start of Row 4-->
                    <h:outputText value="#{regResources.select_decision_employees_start_work_date}"/>
                    <t:panelGroup forceId="true"> 
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.employeeSearchDTO.startWorkingDate}" id="clndr_maintain_employees_start_work_date"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                        <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_employees_start_work_date" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_employees_start_work_date_divID"/>
                    </t:panelGroup> 
                    
                    <h:outputText value="#{regResources.select_decision_employees_end_work_date}"/>
                    <t:panelGroup forceId="true"> 
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.employeeSearchDTO.endResidentDate}" id="clndr_maintain_end_work_date"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"> <%--..--%>
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                         <f:verbatim><br/></f:verbatim>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_end_work_date" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="clndr_maintain_end_work_date_divID"/>
                    </t:panelGroup> 
                <!--- End of Row 4-->  
                <!--- Start of Row 5-->
                    <h:outputText value="#{regResources.select_decision_employees_hire_status}"/>
                    <t:selectOneMenu forceId="true"  id="employees_hire_status" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireStatus}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.hireStatuses}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirstatusCode}" var="entry"/>              
                    </t:selectOneMenu>

                    <h:outputText value="#{regResources.select_decision_employees_hire_current_status}"/>
                    <t:selectOneMenu forceId="true"  id="hire_current_status" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireStages}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.hireCurrentStatuses}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirstageCode}" var="entry"/>              
                    </t:selectOneMenu>                    
                <!--- End of Row 5-->
                <!--- Start of Row 6-->
                    <h:outputText value="#{regResources.select_decision_employees_special_job}"/>
                    <t:selectOneMenu forceId="true"  id="employees_special_job" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.techJobCode}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.technicalJobs}" itemLabel="#{entry.name}" itemValue="#{entry.code.jobCode}" var="entry"/>              
                    </t:selectOneMenu>

                    <h:outputText value="#{regResources.select_decision_employees_job_code}"/>
                    <t:inputText forceId="true"  id="employees_job_code" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.jobCode}"/>
                <!--- End of Row 6-->
              </t:panelGrid>  
         <f:verbatim>
                </div>
                <div class="TabbedPanelsContent">
         </f:verbatim>
         
        <%--h:outputText value="#{regResources.select_decision_employees_currency_job_data}"  styleClass="lable01" /--%>
        <t:panelGrid id="pnlgrd_dec_fifth" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
            <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                <!--- Start of Row 1-->
                    <h:outputText value="#{regResources.select_decision_employees_bank}"/>
                    <t:selectOneMenu forceId="true"  id="employees_bank" styleClass="DropdownboxMedium2" value="#{detailBeanName.bankID}" converter="javax.faces.Long">
                        <a4j:support event="onclick" action="#{detailBeanName.fillBranches}" reRender="employees_bransh" onsubmit="validatemyForm();"/>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.banks}" itemLabel="#{entry.name}" itemValue="#{entry.code.bankCode}" var="entry"/>
                    </t:selectOneMenu>

                    <h:outputText value="#{regResources.select_decision_employees_bransh}"/>
                    <t:selectOneMenu forceId="true"  id="employees_bransh" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankbranchCode}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.branches}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>              
                    </t:selectOneMenu>
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    <h:outputText value="#{regResources.select_decision_employees_bank_account}"/>
                    <t:inputText forceId="true"  id="employees_bank_account" size="20" maxlength="200" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.accountNo}"/>
                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
                <!--- End of Row 2-->              
              <%--/t:panelGrid>  
              
         <f:verbatim><br/></f:verbatim>        

        <t:panelGrid id="pnlgrd_dec_sexth" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"--%>
                <!--- Start of Row 1-->
                <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                    <h:outputText value="#{regResources.select_decision_employees_Job_category}"/>
                    <t:selectOneMenu forceId="true"  id="employees_Job_category" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankCode}" converter="javax.faces.Long"><%--..--%>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.jobCategories}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                    </t:selectOneMenu>

                    <h:outputText value="#{regResources.select_decision_employees_job_group}"/>
                    <t:selectOneMenu forceId="true"  id="employees_job_group" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankCode}" converter="javax.faces.Long"><%--..--%>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.jobGroupes}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                    </t:selectOneMenu>                    
                <!--- End of Row 1-->
                <!--- Start of Row 2-->
                    <h:outputText value="#{regResources.select_decision_employees_current_degree}"/>
                    <t:selectOneMenu forceId="true"  id="employees_current_degree" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankCode}" converter="javax.faces.Long"><%--..--%>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.currentDegrees}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                    </t:selectOneMenu>
                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
                <!--- End of Row 2-->              
              <%--/t:panelGrid>

        <f:verbatim><br/></f:verbatim>
        <t:panelGrid id="pnlgrd_dec_seventh" columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"--%>
                <!--- Start of Row 1-->
                <t:panelGroup colspan="4" style="background-color: #FFFFFF;"/>
                    <h:outputText value="#{regResources.select_decision_employees_budget_type}"/>
                    <t:selectOneMenu forceId="true"  id="employees_budget_type" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankCode}" converter="javax.faces.Long"><%--..--%>
                        <f:selectItem itemValue="" itemLabel="#{regResources.Select_All_listbox}"/>
                        <t:selectItems value="#{detailBeanName.budgetTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                    </t:selectOneMenu>
                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
        </t:panelGrid>
        <f:verbatim>
                </div>
             </div>
           </div>
                  <script type="text/javascript">
            var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
    </script>

        </f:verbatim>
        </t:panelGroup>
        <t:panelGrid columns="2" align="center" id="search_buttons" forceId="true">
            <h:panelGroup>
                  <h:commandButton  type="button"  onclick="return validateSaveAndNewClientValidator();" styleClass="ManyToManySearchButton">
                        <a4j:jsFunction name="saveAndNew"   action="#{detailBeanName.searchAvailable}" reRender="divAdds,search_buttons" oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,null,null);"/>
                  </h:commandButton>                  
            </h:panelGroup>
            <a4j:commandButton  action="#{detailBeanName.cancelSearchAvailable}" reRender="divAdds,search_buttons"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchMode}"  immediate="true"/>
        </t:panelGrid>
     
        