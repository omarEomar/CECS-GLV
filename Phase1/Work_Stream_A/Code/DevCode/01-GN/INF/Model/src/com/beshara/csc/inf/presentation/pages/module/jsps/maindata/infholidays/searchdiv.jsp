<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<t:panelGroup id="searchArea" forceId="true">
    <t:panelGrid columns="4" width="100%" cellpadding="3" rowClasses="row01,row02" cellspacing="0">
        <t:outputText value="#{resourcesBundle.hol_from_date}" styleClass="lable01"/> 
<t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="fromDateSearchId" size="20" maxlength="200" styleClass="textboxmedium"
                         currentDayCellClass="currentDayCell" value="#{pageBeanName.fromDate}"
                         renderAsPopup="true" align="#{globalResources.align}" 
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="TimeStampConverter"/>
        </t:inputCalendar>
            <f:verbatim><br/></f:verbatim>
            <c:dateFormatValidator componentToValidate="fromDateSearchId"
                                errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic" group="serachDivVG"/>
 </t:panelGroup>
<t:outputText value="#{resourcesBundle.hol_until_date}" styleClass="lable01"/> 
<t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="untilDateSearchId" size="20" maxlength="200" styleClass="textboxmedium"
                         currentDayCellClass="currentDayCell" value="#{pageBeanName.untilDate}"
                         renderAsPopup="true" align="#{globalResources.align}" 
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="TimeStampConverter"/>
        </t:inputCalendar>
            <f:verbatim><br/></f:verbatim>
            <c:dateFormatValidator componentToValidate="untilDateSearchId"
                                errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic" group="serachDivVG"/>
 </t:panelGroup>
<t:outputText value="#{resourcesBundle.hol_holiday_desc}" styleClass="lable01"/> 
<t:panelGroup colspan="3">
            <t:inputText forceId="true" id="holidayDescSearchId" value="#{pageBeanName.holidayDesc}"
                         styleClass="textboxlarge" />
 </t:panelGroup>

    </t:panelGrid>
    <%-- *********************** Start Buttons ****************************--%>
    <t:panelGrid id="buttonsPanel" columns="3" align="center">
        <t:commandButton id="searchDiv_btn" forceId="true" value="#{globalResources.SearchButton}" onclick="return validatemyForm('serachDivVG');"
                         action="#{pageBeanName.search}" styleClass="cssButtonSmall"/>
        <t:commandButton type="button" id="customSearchBackBtn" forceId="true" value="#{globalResources.back}"
                         styleClass="cssButtonSmall" onclick="hideLookUpDiv(window.blocker,window.divSearch);"/>
        
    </t:panelGrid>
    <%-- ************************* End Buttons ****************************--%>
</t:panelGroup>

