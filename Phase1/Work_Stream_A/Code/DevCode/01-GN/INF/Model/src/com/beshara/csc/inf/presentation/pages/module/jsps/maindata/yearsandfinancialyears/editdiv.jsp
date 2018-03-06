<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divEditLookup">
    <t:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error"
                  rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    <t:outputText id="clientErrorMessageEdit" styleClass="errMsg"/>
    <t:panelGrid columns="4" rendered="#{pageBeanName.showEdit}" width="100%" cellpadding="0" cellspacing="0"
                 rowClasses="row01,row02" columnClasses="divCol1,divCol2,divCol1,divCol2">
        <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxmediumB" disabled="true"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.year_name}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText id="year_Name" value="#{pageBeanName.selectedPageDTO.yearName}" styleClass="textboxmediumB"
                         maxlength="#{pageBeanName.nameMaxLength}" forceId="true" disabled="true"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.start_date}" styleClass="lable01"/>
        <t:inputText id="startFrom" forceId="true" value="#{pageBeanName.selectedPageDTO.startDate}" disabled="true"
                     styleClass="textboxmediumB"/>
        <%-- <t:inputCalendar id="startFrom" maxlength="10" value="#{pageBeanName.selectedPageDTO.startDate}"
             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass" dayCellClass="dayCellClass"
             currentDayCellClass="currentDayCellClass" popupTodayString="#{resourcesBundle.today} :" disabled="true"
             popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true" popupDateFormat="yyyy/MM/dd"
             alt="Calendar" title="Calendar" forceId="true" styleClass="textboxsmall"> <f:convertDateTime
             pattern="yyyy/MM/dd" timeZone="GMT+2" /> </t:inputCalendar>--%>
        <t:outputText value="#{resourcesBundle.end_date}" styleClass="lable01"/>
        <t:inputText id="endDate" forceId="true" value="#{pageBeanName.selectedPageDTO.endDate}" disabled="true"
                     styleClass="textboxmediumB"/>
        <%-- <t:inputCalendar id="endDate" maxlength="10" value="#{pageBeanName.selectedPageDTO.endDate}"
             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass" dayCellClass="dayCellClass"
             currentDayCellClass="currentDayCellClass" popupTodayString="#{resourcesBundle.today} :" disabled="true"
             popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true" popupDateFormat="yyyy/MM/dd"
             alt="Calendar" title="Calendar" forceId="true" styleClass="textboxsmall" > <f:convertDateTime
             pattern="yyyy/MM/dd" timeZone="GMT+2" /> </t:inputCalendar>--%>
    </t:panelGrid>
    <htm:fieldset id="financial_Year">
        <htm:legend>
            <h:outputText value="#{resourcesBundle.financial_year}" styleClass="lable01"/>
        </htm:legend>
        <t:panelGrid columns="4" rendered="#{pageBeanName.showEdit}" width="100%" cellpadding="0" cellspacing="0"
                     rowClasses="row01," columnClasses="divCol1,divCol2,divCol1,divCol2">
            <t:outputText value="#{resourcesBundle.start_date}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar id="f_startFrom" maxlength="10" value="#{pageBeanName.budgetStartDate}"
                                 monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                                 dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                                 popupTodayString="#{resourcesBundle.today} :" popupWeekString="Wk" renderAsPopup="true"
                                 renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy" alt="Calendar"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" title="Calendar"
                                 forceId="true" styleClass="textboxmediumB"></t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            </t:panelGroup>
            <t:outputText value="#{resourcesBundle.end_date}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar id="f_endDate" maxlength="10" value="#{pageBeanName.budgetEndDate}"
                                 monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                                 dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                 popupTodayString="#{resourcesBundle.today} :" popupWeekString="Wk" renderAsPopup="true"
                                 renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy" alt="Calendar"
                                 title="Calendar" forceId="true" styleClass="textboxmediumB"></t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            </t:panelGroup>
            <t:outputText/>
            <t:panelGroup>
                <c:requiredFieldValidator componentToValidate="f_startFrom" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{yearsBean.pageMode==2}"/>
                <c:dateFormatValidator componentToValidate="f_startFrom" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                       active="#{yearsBean.pageMode==2}"/>
            </t:panelGroup>
            <t:outputText/>
            <t:panelGroup>
                <c:requiredFieldValidator componentToValidate="f_endDate" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{yearsBean.pageMode==2}"/>
                <c:compareDateValidator componentToCompare="f_startFrom" display="dynamic"
                                        componentToValidate="f_endDate" operator="after"
                                        errorMessage="#{resourcesBundle.compare_date}" highlight="false"
                                        active="#{yearsBean.pageMode==2}"/>
                <c:dateFormatValidator componentToValidate="f_endDate" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                       active="#{yearsBean.pageMode==2}"/>
            </t:panelGroup>
        </t:panelGrid>
    </htm:fieldset>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}"
                         onclick="return validatemyForm();"/>
        <t:panelGroup>
            <t:commandButton forceId="true" id="CancelButtonEdit" type="button" onclick="return cancelEditJs();"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"
                             onblur="settingFoucsAtDivEdit();"/>
            <a4j:jsFunction name="cancelEditJs" action="#{pageBeanName.cancelEdit}"
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'f_startFrom','myForm:errorEdit');foucsAddbuttonOnList();"
                            reRender="scriptPanelGroup"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<f:verbatim>
    <br/>
</f:verbatim>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>