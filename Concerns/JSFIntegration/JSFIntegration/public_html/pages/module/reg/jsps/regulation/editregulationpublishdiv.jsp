<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>


<f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>

<h:panelGroup id="edit_publish_div" > 
    <f:verbatim><br/></f:verbatim>
    <h:panelGrid id="pnlgrd_reg_parent_div" columns="2" rowClasses="row01,row02" width="500" cellpadding="0" cellspacing="0">
    <h:outputLabel value="#{regResources.regulation_publish_news_name}"/>
    <h:inputText value="#{detailBeanName.editDTO.newspapersDTO.name}" rendered="#{detailBeanName.editDTO!=null}" disabled="true"/>
    
    <h:outputLabel value="#{regResources.regulation_publish_publish_date}"/>
    <h:panelGrid columns="2">

    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.editDTO.publishDate}" id="clndr_maintain_publish_date"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" rendered="#{detailBeanName.editDTO!=null}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
    <f:converter converterId="TimeStampConverter"/>    
    </t:inputCalendar>
    <h:panelGroup >
    <c2:dateFormatValidator componentToValidate="clndr_maintain_publish_date" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="publish_DateFormatID" active="#{regulationPublishMaintainBean.editDTO!=null}"/>
    <c2:requiredFieldValidator componentToValidate="clndr_maintain_publish_date" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="publish_Date_Missing_Field" active="#{regulationPublishMaintainBean.editDTO!=null}"/>
    <c2:compareDateValidator componentToValidate="mainApplyDateID" componentToCompare="clndr_maintain_publish_date" display="dynamic" highlight="false" operator="before" uniqueId="apply_publish_range_validateID" errorMessage="#{regResources.error_message_diff_dates}" active="#{regulationPublishMaintainBean.editDTO!=null}"/>    
    </h:panelGroup>
    </h:panelGrid>
    
    <h:outputLabel value="#{regResources.regulation_publish_until_date}"/>
    <h:panelGrid columns="2">
    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{detailBeanName.editDTO.untilDate}" id="clndr_maintain_until_date"
                                size="20"maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" rendered="#{detailBeanName.editDTO!=null}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
    <f:converter converterId="TimeStampConverter"/>
    </t:inputCalendar> 
    <h:panelGroup >
    <c2:dateFormatValidator componentToValidate="clndr_maintain_until_date"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"
                                    active="#{regulationPublishMaintainBean.editDTO!=null}"/>
    <c2:compareDateValidator componentToValidate="clndr_maintain_publish_date" componentToCompare="clndr_maintain_until_date" display="dynamic" highlight="false" operator="before" uniqueId="untill_publish_range_validateID" errorMessage="#{regResources.error_message_diff_dates}" active="#{regulationPublishMaintainBean.editDTO!=null}"/>    
    </h:panelGroup>
    </h:panelGrid>
                    
    <h:outputLabel value="#{regResources.regulation_publish_number}"/>
    <h:inputText value="#{detailBeanName.editDTO.publishRefrence}" rendered="#{detailBeanName.editDTO!=null}"/>
    
    <h:outputLabel   value="#{regResources.regulation_publish_notes}"/>
    <h:inputTextarea value="#{detailBeanName.editDTO.publishNotes}" rows="3" cols="45" rendered="#{detailBeanName.editDTO!=null}"/>
    
            
    </h:panelGrid>
    <f:verbatim><br/></f:verbatim>
    <h:panelGrid columns="2" >
    <t:commandButton forceId="true" id="okButtonCustomDiv1" value="#{globalResources.ok}" styleClass="cssButtonSmall" onclick="return validatemyForm();" />                    
    <t:commandButton forceId="true" id="backButtonCustomDiv1" onblur="setFocusAtMyCustom1Div();" value="#{globalResources.back}" action="#{detailBeanName.cancelEdit}" styleClass="cssButtonSmall" immediate="true" onclick="ignoremyFormValidation();block();"/>
    </h:panelGrid>
</h:panelGroup>
                  
