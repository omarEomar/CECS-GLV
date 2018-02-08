<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib"  prefix="htm"%>
<%--added rendered attribute aboelhassan--%>
<htm:style>
.divContent1Dynamic {
    height: 100%;
    margin-left: 6px;
    width: 885px !important;
}
#navigationDiv {
    bottom: 14px !important;
    left: 295px !important;
    position: absolute !important;
}
</htm:style>
<t:messages/>
<t:panelGroup  forceId="true" id="cnt1" onkeypress="FireButtonClick('searchButton');">
            <t:panelTabbedPane  width="898px" 
                  activeTabStyleClass="activeTab"
                  inactiveTabStyleClass="inactiveTab"
                  serverSideTabSwitch="false" tabContentStyleClass="tabContent" inactiveSubStyleClass="inactiveSubTab" align="center" border="0">
                  
                <%-------================================================================================================================================= --%>  
                 <%--  ------------First Tab-------------- --%>
	        <t:panelTab id="tab1" label="#{intResourcesBundle.Employee_Search_First_Tab}" style="max-height:305px;  display: block; overflow-y: auto; overflow-x: hidden; margin-right:-9px;position: relative;">    
                  <t:panelGrid id="pnlgrd_dec_radio" columns="1" rowClasses="row02" width="100%" cellpadding="0" cellspacing="0"  align="center">
                  
                    <t:selectOneRadio id="decRadioButton" value="#{detailBeanName.kuwityType}" converter="javax.faces.Long"  styleClass="lable01">
                        <a4j:support event="onclick" action="#{detailBeanName.changeKuwityType}" reRender="firstTabPanel"/>
                        <f:selectItem  itemLabel="#{intResourcesBundle.employees_kwt}" itemValue="#{detailBeanName.kuwity}"/>
                        <f:selectItem  itemLabel="#{intResourcesBundle.employees_non_kwt}" itemValue="#{detailBeanName.nonKuwity}"/>
                    </t:selectOneRadio>
                    
                </t:panelGrid>
                
                 <t:panelGrid columns="4" width="100%" forceId="true" id="firstTabPanel" rowClasses="row01,row02" cellpadding="3px" cellspacing="0" >
                
                     <h:outputText value="#{intResourcesBundle.employees_civilId}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_civilId"  styleClass="textboxmedium" onkeyup="disableCharacters(this)"  value="#{detailBeanName.employeeSearchDTO.civilId}" converter="javax.faces.Long" maxlength="12"/>
                     
                     <h:outputText value="#{intResourcesBundle.employees_passportNo}" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_passportNo"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.passportNo}" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}"/>
                    
                     <h:outputText value="   " rendered="#{detailBeanName.kuwityType == detailBeanName.kuwity}"/>
                     <h:outputText value="   " rendered="#{detailBeanName.kuwityType == detailBeanName.kuwity}"/>      

                     <h:outputText value="#{intResourcesBundle.employees_first_name}"  styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_first_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.firstName}"/>

                     <h:outputText value="#{intResourcesBundle.employees_second_name}"  styleClass="lable01"/>
                      <t:inputText forceId="true"  id="employees_second_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.secondName}"/>
                      
                     <h:outputText value="#{intResourcesBundle.employees_third_name}" styleClass="lable01"/>
                      <t:inputText forceId="true"  id="employees_third_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.thirdName}"/>
                      
                     <h:outputText value="#{intResourcesBundle.employees_last_name}" styleClass="lable01"/>
                      <t:inputText forceId="true"  id="employees_last_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.lastName}"/>
                      
                     <h:outputText value="#{intResourcesBundle.employees_nickName}" styleClass="lable01"/>
                      <t:inputText forceId="true"  id="employees_nickName" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.familyName}" />
                      
                     <h:outputText value="#{intResourcesBundle.employees_eng_name}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_eng_name" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.englishName}" />
                     
                     <h:outputText value="#{intResourcesBundle.employees_birthDate}" styleClass="lable01"/>
                     <t:panelGroup styleClass="birthDateClientId">
                       <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="birthDateClientId" value="#{detailBeanName.employeeSearchDTO.birthDate}"
                                maxlength="#{detailBeanName.calendarTextLength}" onblur="return validateInputCalenderFormate('birthDateClientId','null','null');" styleClass="textboxmedium" currentDayCellClass="currentDayCell" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     </t:panelGroup>
                     
                     <%--<h:outputText value="#{intResourcesBundle.employees_social_status}"/>
                     <t:selectOneMenu forceId="true"  id="employees_social_status" styleClass="DropdownboxMedium2"  value="#{detailBeanName.employeeSearchDTO.maritalStatusCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.maritalStatusTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.marstatusCode}" var="entry"/>              
                    </t:selectOneMenu>--%>  
                    
                     <h:outputText value="#{intResourcesBundle.employees_gender}" styleClass="lable01"/>
                     <t:selectOneMenu forceId="true"  id="employees_gender" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.genderTypeCode}"  converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.genderTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.gentypeCode}" var="entry"/>              
                    </t:selectOneMenu> 
                     
                     <h:outputText value="#{intResourcesBundle.employees_reliogn}" styleClass="lable01"/>
                    <t:selectOneMenu forceId="true"  id="employees_reliogn" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.religionCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.relgionTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.religionCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                     <h:outputText value="#{intResourcesBundle.employees_phoneNo}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_phoneNo" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.phonesNo}" />
                     
                     <h:outputText value="#{intResourcesBundle.employees_mobile}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_mobile" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.mobileNo}" />
                     
                      <h:outputText value="#{intResourcesBundle.employees_nationalty}" styleClass="lable01"/>
                      <t:selectOneMenu forceId="true" id="employees_nationalty"  styleClass="DropdownboxMedium2" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}"  value="#{detailBeanName.employeeSearchDTO.nationality}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.nationalties}" itemLabel="#{entry.name}" itemValue="#{entry.code.cntryCode}" var="entry"/>              
                        </t:selectOneMenu>
                     
                       <h:outputText value="#{intResourcesBundle.employees_resdientType}" styleClass="lable01"/>
                     
                     <t:selectOneMenu forceId="true"  id="employees_resdientType" styleClass="DropdownboxMedium2" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}" value="#{detailBeanName.employeeSearchDTO.residentTypeCode}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.resdientTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.restypeCode}" var="entry"/>              
                     </t:selectOneMenu>
                     
                      <h:outputText value="#{intResourcesBundle.employees_resdient_end_date}" styleClass="lable01"/>
                      <t:panelGroup styleClass="employees_resdient_end_dateCLientID10">
                           <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="employees_resdient_end_dateCLientID" 
                                            value="#{detailBeanName.employeeSearchDTO.endResidentDate}" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}"
                                            maxlength="#{detailBeanName.calendarTextLength}" onblur="return validateInputCalenderFormate('employees_resdient_end_dateCLientID','null','null');" styleClass="textboxmedium" currentDayCellClass="currentDayCell" 
                                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                                <f:converter converterId="SqlDateConverter"/>
                            </t:inputCalendar>
                         </t:panelGroup>
                         
                     <h:outputText value="#{intResourcesBundle.employees_government}" styleClass="lable01"/>
                      <t:selectOneMenu forceId="true"  id="employees_government" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.mapCode}">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.governments}" itemLabel="#{entry.name}" itemValue="#{entry.code.mapCode}" var="entry"/>              
                    </t:selectOneMenu>
                     
                     <h:outputText value="#{intResourcesBundle.employees_health_status}" styleClass="lable01"/>
                     <t:selectOneMenu forceId="true"  id="employees_health_status" styleClass="DropdownboxMedium2"  value="#{detailBeanName.employeeSearchDTO.capstatusCode}" converter="javax.faces.Long" >
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.capTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.capstatusCode}" var="entry"/>
                    </t:selectOneMenu>
                     
                     <h:outputText value="#{intResourcesBundle.employees_special_case}" styleClass="lable01"/>
                      <t:selectOneMenu forceId="true"  id="employees_special_case" styleClass="DropdownboxMedium2" converter="javax.faces.Long" value="#{detailBeanName.employeeSearchDTO.specialCaseTypeCode}">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.specialCaseTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.spccsetypeCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                     
                   
                     
                    
                            
                </t:panelGrid>

	        </t:panelTab>
                
                <%-------================================================================================================================================= --%>
                 <%--  ------------Second  Tab-------------- --%>
                 <t:panelTab id="tab2" label="#{intResourcesBundle.Employee_Search_Second_Tab}" style="display: block;margin-right:-9px;position: relative;min-height: 190px;">
                 
	             <t:panelGrid columns="4" width="100%" forceId="true" id="secondTabPanel" rowClasses="row01,row02" cellpadding="3px" cellspacing="0" >
                     <h:outputText value="#{intResourcesBundle.category}" rendered="#{detailBeanName.showCategoryCB}" styleClass="lable01"/>
                     <t:panelGroup rendered="#{detailBeanName.showCategoryCB}">
                         <t:selectOneMenu forceId="true"  id="employees_category" styleClass="DropdownboxMedium2" value="#{detailBeanName.categoryID}" converter="javax.faces.Long">
                            <a4j:support event="onchange" action="#{detailBeanName.fillMinistries}" reRender="employees_ministries, employees_work_ministry, availableMinistriesBtn" />
                            <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.categories}" itemLabel="#{entry.name}" itemValue="#{entry.code}" var="entry"/>              
                        </t:selectOneMenu>
                    </t:panelGroup>
                    
                     <h:outputText value="#{intResourcesBundle.ministry}" rendered="#{detailBeanName.showMinistryCB}" styleClass="lable01"/>
                     <t:panelGroup  rendered="#{detailBeanName.showMinistryCB}" >
                         <t:selectOneMenu forceId="true"  id="employees_ministries" styleClass="DropdownboxMedium2" value="#{detailBeanName.ministryID}" converter="javax.faces.Long" disabled="#{detailBeanName.categoryID==null}">
                            <a4j:support event="onchange" action="#{detailBeanName.fillWorkMinistries}" reRender="employees_work_ministry, availableMinistriesBtn" />
                            <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.ministries}" itemLabel="#{entry.name}" itemValue="#{entry.code}" var="entry"/>              
                        </t:selectOneMenu>
                    </t:panelGroup>
                     <h:outputText value="#{intResourcesBundle.work_center_name}" styleClass="lable01"/>
                     <t:panelGroup>
                     <t:selectOneMenu forceId="true"  id="employees_work_ministry" styleClass="DropdownboxMedium2"  value="#{detailBeanName.employeeSearchDTO.workCenterCode}" 
                                disabled="#{detailBeanName.ministryID==null || detailBeanName.categoryID==null}"> 
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.workMinistries}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>              
                    </t:selectOneMenu>
                     <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                     <t:panelGroup rendered="#{advanceEmployeesAddBean.showWorkCenterLovDiv}" >
                     <a4j:commandButton id="availableMinistriesBtn" type="button"  value="..." styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);document.getElementById('myForm:okLoveButton').disabled = true; setFocusOnlyOnElement('lov_searchText');" action="#{detailBeanName.openWorkCenter}"
                                       disabled="#{detailBeanName.ministryID==null || detailBeanName.categoryID==null}" reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lov_searchRadioBtn"/></t:panelGroup> 
                    </t:panelGroup>
                    
                     <h:outputText value="#{intResourcesBundle.ministry_fileNo}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_geha_fileNo"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.ministryFileNo}"/>
                    
                     <h:outputText value="#{intResourcesBundle.dewan_fileNo}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="dewan_fileNoID"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.cscFileNo}"/>

                     <h:outputText value="#{intResourcesBundle.employees_hire_date}" styleClass="lable01"/>
                      <t:panelGroup styleClass="clndr_maintain_employees_hire_date10">
                       <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="clndr_maintain_employees_hire_date"
                                maxlength="#{detailBeanName.calendarTextLength}" onblur="return validateInputCalenderFormate('clndr_maintain_employees_hire_date','null','null');" styleClass="textboxmedium" currentDayCellClass="currentDayCell" value="#{detailBeanName.employeeSearchDTO.hireDate}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     </t:panelGroup>
                     
                     <h:outputText value="#{intResourcesBundle.employees_hire_type}" styleClass="lable01"/>
                     <t:selectOneMenu forceId="true"  id="employees_hire_type" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireTypes}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.hireTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirtypeCode}" var="entry"/>              
                    </t:selectOneMenu>    
                    
                     <h:outputText value="#{intResourcesBundle.employees_start_work_date}" styleClass="lable01"/>
                     <t:panelGroup styleClass="clndr_maintain_employees_start_work_date10">
                      <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="clndr_maintain_employees_start_work_date"
                                 maxlength="#{detailBeanName.calendarTextLength}" onblur="return validateInputCalenderFormate('clndr_maintain_employees_start_work_date','null','null');"  styleClass="textboxmedium" currentDayCellClass="currentDayCell" value="#{detailBeanName.employeeSearchDTO.startWorkingDate}" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     </t:panelGroup>
                     
                     <h:outputText value="#{intResourcesBundle.employees_end_work_date}" rendered="#{detailBeanName.showWorkEndDate}" styleClass="lable01"/>
                       <t:panelGroup rendered="#{detailBeanName.showWorkEndDate}">
                       <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="clndr_maintain_end_work_date"
                                 maxlength="#{detailBeanName.calendarTextLength}" onblur="return validateInputCalenderFormate('clndr_maintain_end_work_date','null','null');" styleClass="textbox" currentDayCellClass="currentDayCell" value="#{detailBeanName.employeeSearchDTO.endWorkingDate}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"> <%--..--%>
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     
                     </t:panelGroup>
                     
                      <h:outputText value="#{intResourcesBundle.employees_hire_status}" rendered="#{detailBeanName.showHireStatus}" styleClass="lable01"/>
                      <t:panelGroup  rendered="#{detailBeanName.showHireStatus}">
                      <t:selectOneMenu forceId="true"  id="employees_hire_status" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireStatus}" converter="javax.faces.Long">
                         <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                         <t:selectItems value="#{detailBeanName.hireStatuses}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirstatusCode}" var="entry"/>              
                      </t:selectOneMenu>
                     </t:panelGroup>  
                     
                     <h:outputText value="#{intResourcesBundle.employees_hire_current_status}" rendered="#{detailBeanName.showCurrentHireStatus}" styleClass="lable01"/>
                     <t:panelGroup rendered="#{detailBeanName.showCurrentHireStatus}">
                           <t:selectOneMenu forceId="true"  id="hire_current_status" styleClass="DropdownboxMedium2"   value="#{detailBeanName.employeeSearchDTO.empHireStages}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.hireCurrentStatuses}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirstageCode}" var="entry"/>              
                         </t:selectOneMenu> 
                      </t:panelGroup>
                     <h:outputText value="#{intResourcesBundle.job_name}" styleClass="lable01"/>
                   <t:panelGroup>  
                      <t:selectOneMenu forceId="true"  id="employees_special_job" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.techJobCode}">
                        <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.technicalJobs}" itemLabel="#{entry.name}" itemValue="#{entry.code.jobCode}" var="entry"/>              
                      </t:selectOneMenu>
                      <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                     <t:panelGroup rendered="#{advanceEmployeesAddBean.showJobLovDiv}" >
                      <a4j:commandButton type="button"  value="..." styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);document.getElementById('myForm:okLoveButton').disabled = true;setFocusOnlyOnElement('lov_searchText');" action="#{detailBeanName.openJob}"
                      reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lov_searchRadioBtn"/></t:panelGroup> 
                    </t:panelGroup>
                    
                     <h:outputText value="#{intResourcesBundle.employees_job_code}" rendered="#{!advanceEmployeesAddBean.showJobLovDiv}" styleClass="lable01"/>
                     <t:inputText forceId="true"  id="employees_job_codeID"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.jobCode}" rendered="#{!advanceEmployeesAddBean.showJobLovDiv}"/>
                     
                     </t:panelGrid>
                     
	        </t:panelTab>
                
                
               <%-------================================================================================================================================= --%>
                 <%--  ------------Third Tab-------------- --%>
                  <t:panelTab id="tab3" label="#{intResourcesBundle.Employee_Search_Third_Tab}"  style="display: block;margin-right:-9px;position: relative;">
                   <t:messages showDetail="true" showSummary="true" />
                    <t:panelGrid columns="4" width="100%" forceId="true" id="thirdTabPanel" rowClasses="row01,row02" cellpadding="3px" cellspacing="0" >
                   
                        <h:outputText value="#{intResourcesBundle.employees_bank}" styleClass="lable01"/>
                        <t:selectOneMenu forceId="true"  id="employees_bank" styleClass="DropdownboxMedium2" value="#{detailBeanName.bankID}" converter="javax.faces.Long">
                                <a4j:support event="onchange" action="#{detailBeanName.fillBranches}" reRender="employees_bransh" />
                                <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                                <t:selectItems value="#{detailBeanName.banks}" itemLabel="#{entry.name}" itemValue="#{entry.code.bankCode}" var="entry"/>
                        </t:selectOneMenu>
                       
                         <h:outputText value="#{intResourcesBundle.employees_bransh}" styleClass="lable01"/>
                         
                          <t:selectOneMenu forceId="true"  id="employees_bransh" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankbranchCode}" disabled="#{detailBeanName.bankID==null}" >
                           <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.branches}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>              
                        </t:selectOneMenu>
                  
                         <h:outputText value="#{intResourcesBundle.employees_bank_account}" styleClass="lable01"/>
                          <t:inputText forceId="true"  id="employees_bank_account" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.accountNo}"/>

                         <h:outputText value="#{intResourcesBundle.employees_Job_category}" styleClass="lable01"/>
                          <t:selectOneMenu forceId="true"  id="employees_Job_category" styleClass="DropdownboxMedium2" value="#{detailBeanName.selectedCaderCode}" >
                            <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                            <t:selectItems value="#{detailBeanName.jobCategories}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>
                            <a4j:support event="onchange" action="#{detailBeanName.filterByCader}" reRender="employees_job_group,employees_current_degree" />
                         </t:selectOneMenu>
                    
                         <h:outputText value="#{intResourcesBundle.employees_job_group}" styleClass="lable01"/>
                           <t:selectOneMenu forceId="true"  id="employees_job_group" styleClass="DropdownboxMedium2" value="#{detailBeanName.selectedGroupCode}" disabled="#{detailBeanName.selectedCaderCode==null || detailBeanName.selectedCaderCode==''}" >
                             <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                             <t:selectItems value="#{detailBeanName.jobGroupes}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>  
                                <a4j:support event="onchange" action="#{detailBeanName.obtainDegreeByGroup}" reRender="employees_current_degree" />
                         </t:selectOneMenu>   
                    
                        <h:outputText value="#{intResourcesBundle.employees_current_degree}" styleClass="lable01"/>
                        <t:selectOneMenu forceId="true"  id="employees_current_degree" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.degreeCode}" converter="javax.faces.Long" disabled="#{detailBeanName.selectedGroupCode==null || detailBeanName.selectedGroupCode==''}" >
                          <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                          <t:selectItems value="#{detailBeanName.currentDegrees}" itemLabel="#{entry.name}" itemValue="#{entry.code.elmguideCode}" var="entry"/>              
                       </t:selectOneMenu>
                    
                        <h:outputText value="#{intResourcesBundle.employees_budget_type}" styleClass="lable01"/>
                              <%-- converter="javax.faces.Long" value="#{detailBeanName.employeeSearchDTO.bgtTypesCode}" --%>
                         <t:selectOneMenu forceId="true"  id="employees_budget_type" styleClass="DropdownboxMedium2" converter="javax.faces.Long" value="#{detailBeanName.employeeSearchDTO.bgtTypesCode}" >
                          <f:selectItem itemValue="" itemLabel="#{intResourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.budgetTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.typeCode}" var="entry"/>              
                       </t:selectOneMenu>
           
                    </t:panelGrid>
	        </t:panelTab>
	    </t:panelTabbedPane>
            </t:panelGroup>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="birthDateClientId,70,110:employees_resdient_end_dateCLientID,70,110:clndr_maintain_employees_hire_date,70,110:clndr_maintain_employees_start_work_date,70,110:clndr_maintain_end_work_date,90,70" />
               
<script type="text/javascript">   
  setFocusAtMyFirstElement();
   function setFocusAtMyFirstElement(){
       if(document.getElementById('employees_civilId') !=null)
             setFocusOnlyOnElement('employees_civilId');
        } 
 </script>
