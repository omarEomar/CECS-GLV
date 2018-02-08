<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<t:panelGroup  style="width:100%" styleClass="delDivScroll">
           <t:messages showDetail="true" />
            <t:panelTabbedPane  width="100%"
                  activeTabStyleClass="activeTab"
                  inactiveTabStyleClass="inactiveTab"
                  serverSideTabSwitch="false">
                  
                <%-------================================================================================================================================= --%>  
                 <%--  ------------First Tab-------------- --%>
	        <t:panelTab id="tab1" label="#{resourcesBundle.Employee_Search_First_Tab}">
                
                  <t:panelGrid id="pnlgrd_dec_radio" columns="1" rowClasses="row02" width="100%" cellpadding="0" cellspacing="0" align="center">
                  
                    <t:selectOneRadio id="decRadioButton" value="#{detailBeanName.kuwityType}" converter="javax.faces.Long"  styleClass="lable01">
                        <a4j:support event="onclick" action="#{detailBeanName.changeKuwityType}" reRender="firstTabPanel"/>
                        <f:selectItem  itemLabel="#{resourcesBundle.employees_kwt}" itemValue="#{detailBeanName.kuwity}"/>
                        <f:selectItem  itemLabel="#{resourcesBundle.employees_non_kwt}" itemValue="#{detailBeanName.nonKuwity}"/>
                    </t:selectOneRadio>
                    
                </t:panelGrid>
                
                 <t:panelGrid columns="4" width="100%" forceId="true" id="firstTabPanel" rowClasses="row01,row02" cellpadding="1px" cellspacing="0" >
                
                     <h:outputText value="#{resourcesBundle.employees_civilId}"/>
                     <t:inputText forceId="true"  id="employees_civilId"  styleClass="textboxmedium"   value="#{detailBeanName.employeeSearchDTO.civilId}" converter="javax.faces.Long"/>
                     
                     <h:outputText value="#{resourcesBundle.employees_passportNo}" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}"/>
                     <t:inputText forceId="true"  id="employees_passportNo"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.passportNo}" rendered="#{detailBeanName.kuwityType == detailBeanName.nonKuwity}"/>
                    
                     <h:outputText value="   " rendered="#{detailBeanName.kuwityType == detailBeanName.kuwity}"/>
                     <h:outputText value="   " rendered="#{detailBeanName.kuwityType == detailBeanName.kuwity}"/>      

                     <h:outputText value="#{resourcesBundle.employees_first_name}"/>
                     <t:inputText forceId="true"  id="employees_first_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.firstName}"/>

                     <h:outputText value="#{resourcesBundle.employees_second_name}"/>
                      <t:inputText forceId="true"  id="employees_second_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.secondName}"/>
                      
                     <h:outputText value="#{resourcesBundle.employees_third_name}"/>
                      <t:inputText forceId="true"  id="employees_third_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.thirdName}"/>
                      
                     <h:outputText value="#{resourcesBundle.employees_last_name}"/>
                      <t:inputText forceId="true"  id="employees_last_name" styleClass="textboxmedium"  value="#{detailBeanName.employeeSearchDTO.lastName}"/>
                      
                     <h:outputText value="#{resourcesBundle.employees_nickName}"/>
                      <t:inputText forceId="true"  id="employees_nickName" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.familyName}" />
                      
                     <h:outputText value="#{resourcesBundle.employees_eng_name}"/>
                     <t:inputText forceId="true"  id="employees_eng_name" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.englishName}" />
                     
                     <h:outputText value="#{resourcesBundle.employees_birthDate}"/>
                     <t:panelGroup>
                       <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="birthDateClientId" value="#{detailBeanName.employeeSearchDTO.birthDate}"
                                size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     </t:panelGroup>
                     
                     <h:outputText value="#{resourcesBundle.employees_social_status}"/>
                     <t:selectOneMenu forceId="true"  id="employees_social_status" styleClass="DropdownboxMedium2"  value="#{detailBeanName.employeeSearchDTO.maritalStatusCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.maritalStatusTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.marstatusCode}" var="entry"/>              
                    </t:selectOneMenu>  
                    
                     <h:outputText value="#{resourcesBundle.employees_gender}"/>
                     <t:selectOneMenu forceId="true"  id="employees_gender" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.genderTypeCode}"  converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.genderTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.gentypeCode}" var="entry"/>              
                    </t:selectOneMenu> 
                     
                     <h:outputText value="#{resourcesBundle.employees_reliogn}"/>
                    <t:selectOneMenu forceId="true"  id="employees_reliogn" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.religionCode}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.relgionTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.religionCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                     <h:outputText value="#{resourcesBundle.employees_phoneNo}"/>
                     <t:inputText forceId="true"  id="employees_phoneNo" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.phonesNo}" />
                     
                     <h:outputText value="#{resourcesBundle.employees_mobile}"/>
                     <t:inputText forceId="true"  id="employees_mobile" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.mobileNo}" />
                     
                      <h:outputText value="#{resourcesBundle.employees_nationalty}"/>
                      <t:selectOneMenu forceId="true" id="employees_nationalty"  styleClass="DropdownboxMedium2" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}"  value="#{detailBeanName.employeeSearchDTO.nationality}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                            <t:selectItems value="#{detailBeanName.nationalties}" itemLabel="#{entry.name}" itemValue="#{entry.code.cntryCode}" var="entry"/>              
                        </t:selectOneMenu>
                     
                       <h:outputText value="#{resourcesBundle.employees_resdientType}"/>
                     
                     <t:selectOneMenu forceId="true"  id="employees_resdientType" styleClass="DropdownboxMedium2" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}" value="#{detailBeanName.employeeSearchDTO.residentTypeCode}" converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.No_Resdient_Type}"/>
                            <t:selectItems value="#{detailBeanName.resdientTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.restypeCode}" var="entry"/>              
                     </t:selectOneMenu>
                     
                      <h:outputText value="#{resourcesBundle.employees_resdient_end_date}"/>
                      <t:panelGroup>
                           <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="employees_resdient_end_dateCLientID" 
                                            value="#{detailBeanName.employeeSearchDTO.endResidentDate}" disabled="#{detailBeanName.kuwityType == detailBeanName.kuwity}"
                                            size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                                <f:converter converterId="SqlDateConverter"/>
                            </t:inputCalendar>
                         </t:panelGroup>
                         
                     <h:outputText value="#{resourcesBundle.employees_government}"/>
                      <t:selectOneMenu forceId="true"  id="employees_government" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.mapCode}">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.governments}" itemLabel="#{entry.name}" itemValue="#{entry.code.mapCode}" var="entry"/>              
                    </t:selectOneMenu>
                     
                     <h:outputText value="#{resourcesBundle.employees_health_status}"/>
                     <t:selectOneMenu forceId="true"  id="employees_health_status" styleClass="DropdownboxMedium2"  value="#{detailBeanName.employeeSearchDTO.capstatusCode}" converter="javax.faces.Long" >
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.capTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.capstatusCode}" var="entry"/>
                    </t:selectOneMenu>
                     
                     <h:outputText value="#{resourcesBundle.employees_special_case}"/>
                      <t:selectOneMenu forceId="true"  id="employees_special_case" styleClass="DropdownboxMedium2" converter="javax.faces.Long" value="#{detailBeanName.employeeSearchDTO.specialCaseTypeCode}">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.specialCaseTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.spccsetypeCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                     
                   
                     
                    
                            
                </t:panelGrid>

	        </t:panelTab>
                
                <%-------================================================================================================================================= --%>
                 <%--  ------------Second  Tab-------------- --%>
                 <t:panelTab id="tab2" label="#{resourcesBundle.Employee_Search_Second_Tab}">
                 
	             <t:panelGrid columns="4" width="100%" forceId="true" id="secondTabPanel" rowClasses="row01,row02" cellpadding="1px" cellspacing="0" >
                     
                     <h:outputText value="#{resourcesBundle.category}"/>
                     <t:selectOneMenu forceId="true"  id="employees_category" styleClass="DropdownboxMedium2" value="#{detailBeanName.categoryID}" converter="javax.faces.Long">
                        <a4j:support event="onclick" action="#{detailBeanName.fillMinistries}" reRender="employees_ministries" />
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.categories}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                     <h:outputText value="#{resourcesBundle.ministry}"/>
                     <t:selectOneMenu forceId="true"  id="employees_ministries" styleClass="DropdownboxMedium2" value="#{detailBeanName.ministryID}" converter="javax.faces.Long">
                        <a4j:support event="onclick" action="#{detailBeanName.fillWorkMinistries}" reRender="employees_work_ministry" />
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.ministries}" itemLabel="#{entry.name}" itemValue="#{entry.code.minCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                     <h:outputText value="#{resourcesBundle.employees_work_ministry}"/>
                     <t:selectOneMenu forceId="true"  id="employees_work_ministry" styleClass="DropdownboxMedium2"  value="#{detailBeanName.employeeSearchDTO.workCenterCode}"> 
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.workMinistries}" itemLabel="#{entry.name}" itemValue="#{entry.code.wrkCode}" var="entry"/>              
                    </t:selectOneMenu>
                    
                    
                     <h:outputText value="#{resourcesBundle.ministry_fileNo}"/>
                     <t:inputText forceId="true"  id="employees_geha_fileNo"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.ministryFileNo}"/>
                    
                     <h:outputText value="#{resourcesBundle.dewan_fileNo}"/>
                     <t:inputText forceId="true"  id="dewan_fileNoID"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.cscFileNo}"/>

                     <h:outputText value="#{resourcesBundle.employees_hire_date}"/>
                      <t:panelGroup>
                       <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="clndr_maintain_employees_hire_date"
                                size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" value="#{detailBeanName.employeeSearchDTO.hireDate}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     </t:panelGroup>
                     
                     <h:outputText value="#{resourcesBundle.employees_hire_type}"/>
                     <t:selectOneMenu forceId="true"  id="employees_hire_type" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireTypes}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.hireTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirtypeCode}" var="entry"/>              
                    </t:selectOneMenu>    
                    
                     <h:outputText value="#{resourcesBundle.employees_start_work_date}"/>
                     <t:panelGroup>
                      <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="clndr_maintain_employees_start_work_date"
                                size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" value="#{detailBeanName.employeeSearchDTO.startWorkingDate}" 
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     </t:panelGroup>
                     
                     <h:outputText value="#{resourcesBundle.employees_end_work_date}"/>
                       <t:panelGroup>
                       <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="clndr_maintain_end_work_date"
                                size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" value="#{detailBeanName.employeeSearchDTO.endResidentDate}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"> <%--..--%>
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                     
                     </t:panelGroup>
                     
                      <h:outputText value="#{resourcesBundle.employees_hire_status}"/>
                      <t:selectOneMenu forceId="true"  id="employees_hire_status" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.empHireStatus}" converter="javax.faces.Long">
                         <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                         <t:selectItems value="#{detailBeanName.hireStatuses}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirstatusCode}" var="entry"/>              
                      </t:selectOneMenu>
                     
                     <h:outputText value="#{resourcesBundle.employees_hire_current_status}"/>
                       <t:selectOneMenu forceId="true"  id="hire_current_status" styleClass="DropdownboxMedium2"   value="#{detailBeanName.employeeSearchDTO.empHireStages}" converter="javax.faces.Long">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.hireCurrentStatuses}" itemLabel="#{entry.name}" itemValue="#{entry.code.hirstageCode}" var="entry"/>              
                     </t:selectOneMenu> 
                      
                     <h:outputText value="#{resourcesBundle.decision_employees_special_job}"/>
                      <t:selectOneMenu forceId="true"  id="employees_special_job" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.techJobCode}">
                        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.technicalJobs}" itemLabel="#{entry.name}" itemValue="#{entry.code.jobCode}" var="entry"/>              
                      </t:selectOneMenu>
                      
                     <h:outputText value="#{resourcesBundle.employees_job_code}"/>
                     <t:inputText forceId="true"  id="employees_job_codeID"  styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.jobCode}"/>
                     
                     </t:panelGrid>
                     
	        </t:panelTab>
                
                
               <%-------================================================================================================================================= --%>
                 <%--  ------------Third Tab-------------- --%>
                  <t:panelTab id="tab3" label="#{resourcesBundle.Employee_Search_Third_Tab}">
                   <t:messages showDetail="true" showSummary="true" />
                    <t:panelGrid columns="4" width="100%" forceId="true" id="thirdTabPanel" rowClasses="row01,row02" cellpadding="1px" cellspacing="0" >
                   
                         <h:outputText value="#{resourcesBundle.employees_bank}"/>
                         <t:selectOneMenu forceId="true"  id="employees_bank" styleClass="DropdownboxMedium2" value="#{detailBeanName.bankID}" converter="javax.faces.Long">
                                <a4j:support event="onclick" action="#{detailBeanName.fillBranches}" reRender="employees_bransh" />
                                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                                <t:selectItems value="#{detailBeanName.banks}" itemLabel="#{entry.name}" itemValue="#{entry.code.bankCode}" var="entry"/>
                        </t:selectOneMenu>
                       
                         <h:outputText value="#{resourcesBundle.employees_bransh}"/>
                          <t:selectOneMenu forceId="true"  id="employees_bransh" styleClass="DropdownboxMedium2" value="#{detailBeanName.employeeSearchDTO.bankbranchCode}">
                           <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                            <t:selectItems value="#{detailBeanName.branches}" itemLabel="#{entry.name}" itemValue="#{entry.code.bnkbranchCode}" var="entry"/>              
                        </t:selectOneMenu>
                  
                         <h:outputText value="#{resourcesBundle.employees_bank_account}"/>
                          <t:inputText forceId="true"  id="employees_bank_account" styleClass="textboxmedium" value="#{detailBeanName.employeeSearchDTO.accountNo}"/>

                         <h:outputText value="#{resourcesBundle.employees_Job_category}"/>
                          <t:selectOneMenu forceId="true"  id="employees_Job_category" styleClass="DropdownboxMedium2"  converter="javax.faces.Long">
                            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                            <t:selectItems value="#{detailBeanName.jobCategories}" itemLabel="#{entry.name}" itemValue="#{entry.code.catCode}" var="entry"/>              
                         </t:selectOneMenu>
                    
                         <h:outputText value="#{resourcesBundle.employees_job_group}"/>
                          <t:selectOneMenu forceId="true"  id="employees_job_group" styleClass="DropdownboxMedium2"  >
                             <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                             <t:selectItems value="#{detailBeanName.jobGroupes}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>              
                         </t:selectOneMenu>   
                    
                        <h:outputText value="#{resourcesBundle.employees_current_degree}"/>
                        <t:selectOneMenu forceId="true"  id="employees_current_degree" styleClass="DropdownboxMedium2" >
                          <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                          <t:selectItems value="#{detailBeanName.currentDegrees}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>              
                       </t:selectOneMenu>
                    
                        <h:outputText value="#{resourcesBundle.employees_budget_type}"/>
                         <t:selectOneMenu forceId="true"  id="employees_budget_type" styleClass="DropdownboxMedium2"  >
                          <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                        <t:selectItems value="#{detailBeanName.budgetTypes}" itemLabel="#{entry.name}" itemValue="#{entry.code.key}" var="entry"/>              
                       </t:selectOneMenu>
           
                    </t:panelGrid>
	        </t:panelTab>
	    </t:panelTabbedPane>
            </t:panelGroup>
            <f:verbatim><br/></f:verbatim>
             <t:panelGrid columns="40" border="0" align="center" width="100%">
                <t:panelGroup colspan="25"/>
                <h:commandButton styleClass="cssButtonSmall" id="searchButton" value="#{globalResources.SearchButton}" action="#{detailBeanName.searchAvailable}" />
               </t:panelGrid>       