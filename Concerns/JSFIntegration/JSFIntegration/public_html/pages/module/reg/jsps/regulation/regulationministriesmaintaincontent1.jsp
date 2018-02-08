<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

            
<t:panelGrid columns="8" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
<!--- Start of Row 1-->
    <h:outputText value="#{regResources.regulation_subjects_reg_type}" />
    <t:inputText forceId="true"  id="add_regType" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDto.name}" disabled="true"/>
    
    <h:outputText value="#{regResources.regulation_subjects_reg_year}" />
    <t:inputText forceId="true"  id="add_regYear" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.yearsDto.code.key}" disabled="true"/>
    
    <h:outputText value="#{regResources.regulation_subjects_reg_number}" />
    <t:inputText  rendered="#{pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true}" forceId="true" id="add_regNoAdd" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.regulationNumber}" disabled="true"/>
    <t:inputText  rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false}" forceId="true" id="add_regNoEdit" styleClass="textboxsmall2" value="#{detailBeanName.masterEntityKey.regulationNumber}" disabled="true"/>
    
    <h:outputText value="#{regResources.reg_applay_date}"/>
    <t:inputText value="#{pageBeanName.pageDTO.regulationAppliedDate}"  styleClass="textboxsmall2" disabled="true">
        <f:converter converterId="TimeStampConverter"/>
    </t:inputText>
<!--- Start of Row 2-->
    <h:outputText value="#{regResources.regulation_description}"/>
    <t:panelGroup colspan="7">
        <t:inputText disabled="true" styleClass="regTiTleTextBoxInMinistriesHeader" value="#{pageBeanName.pageDTO.regulationTitle}"/>
    </t:panelGroup>
<!--- Start of Row 3-->
    <t:outputLabel value="#{regResources.regulation_ministries_apply_date}" rendered="#{pageBeanName.maintainMode!=2}" />
    <t:panelGroup colspan="3">
    <%--t:panelGrid columns="3" cellpadding="0" cellspacing="0" border="0" rendered="#{pageBeanName.maintainMode!=2}"--%>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="masterApplyDate_clndrId" size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                             currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             value="#{detailBeanName.masterApplyDate}" rendered="#{pageBeanName.maintainMode!=2}">
                <f:converter converterId="TimeStampConverter"/>
            </t:inputCalendar>
        </t:panelGroup>
        <c2:dateFormatValidator componentToValidate="masterApplyDate_clndrId" active="#{regulationMaintainBean.maintainMode!=2}"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
       <%-- <c2:compareDateValidator componentToValidate="mainApplyDateID"
                                                            componentToCompare="masterApplyDate_clndrId"
                                                            display="dynamic"
                                                            errorMessage="#{regResources.must_be_after_reg_applay_date}"
                                                            operator="before"
                                                            highlight="false"/>--%>
    <%--/t:panelGrid--%>
    </t:panelGroup>

    <t:outputLabel value="#{regResources.regulation_ministries_cancel_date}" rendered="#{pageBeanName.maintainMode!=2}"/>
    <t:panelGroup colspan="3">
        <%--t:panelGrid columns="3" cellpadding="0" cellspacing="0" border="0" rendered="#{pageBeanName.maintainMode!=2}"--%>
            <t:panelGroup>
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="masterCancelDate_clndrId" size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                 value="#{detailBeanName.masterCancelDate}" rendered="#{pageBeanName.maintainMode!=2}">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
            </t:panelGroup>
            <t:panelGroup colspan="2">
                <c2:dateFormatValidator componentToValidate="masterCancelDate_clndrId"
                                            display="dynamic" active="#{regulationMaintainBean.maintainMode!=2}"
                                            errorMessage="#{globalResources.messageErrorForAdding}"
                                            highlight="false"/>
                <c2:compareDateValidator componentToValidate="masterApplyDate_clndrId" componentToCompare="masterCancelDate_clndrId" operator="before" display="dynamic" errorMessage="#{regResources.must_be_after_applay_date}" active="#{regulationMaintainBean.maintainMode!=2}"/>
            </t:panelGroup>
        <%--/t:panelGrid--%>
        </t:panelGroup>
    
    
   <%-- <t:panelGroup colspan="2">
        <t:commandButton onclick="return validatemyForm();" value="#{regResources.apply_on_all_ministries}" styleClass="cssButtonMedium" action="#{detailBeanName.applyOnAllMinistries}"/>
    </t:panelGroup>--%>
</t:panelGrid>

<t:messages/>