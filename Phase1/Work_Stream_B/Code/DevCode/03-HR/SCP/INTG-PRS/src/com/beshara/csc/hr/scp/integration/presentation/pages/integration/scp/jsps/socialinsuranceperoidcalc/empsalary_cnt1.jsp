<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.ContainerTitle { max-width: 245px; line-height: 18px; margin-top: 1px; }</htm:style>
<jsp:include page="/integration/scp/jsps/socialinsuranceperoidcalc/commonsaveddata.jsp"/>
<t:panelGroup forceId="true" colspan="6" id="hideQulDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="showHideFilterPnlFn();"></t:panelGroup>
</t:panelGroup>
<a4j:jsFunction action="#{socialInsurPeroidCalcJobCategoryBean.showHideFilterPnl}" name="showHideFilterPnlFn"
                reRender="hiddenBtnPnl,dataT_data_panel,mainDataEmpPanel,dataT_data_panel,paging_panel"
                oncomplete="togglePageUsingCstmHeight('hideQulDivImg', 'mainPnl', 'dataT_data_panel',83,375,document.getElementById('filterPnlCollapsed').value);"/>
<t:panelGrid id="mainPnl" forceId="true" width="100%">
    <t:panelGrid width="100%" forceId="true" id="mainDataEmpPanel" cellpadding="0" cellspacing="0" border="0">
        <jsp:include page="/integration/scp/jsps/socialinsuranceperoidcalc/maindata_cnt1.jsp"/>
   
    </t:panelGrid>
</t:panelGrid>
<t:panelGroup id="hiddenBtnPnl" forceId="true">
    <t:inputHidden id="filterPnlCollapsed" forceId="true"
                   value="#{socialInsurPeroidCalcJobCategoryBean.filterPnlCollapsed}"/>
</t:panelGroup>
<t:panelGroup>
<t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel5" rowClasses="row01,row02"
                     cellpadding="3" cellspacing="0" border="0">
            <t:selectOneRadio forceId="true" id="limitedPeroid_searchRadioBtn" value="#{pageBeanName.limitedPeroid}"
                              styleClass="divtext">
                <f:selectItem itemLabel="#{resourcesBundle.all_status}" itemValue="0"/>
                <f:selectItem itemLabel="#{resourcesBundle.limited_peroid}" itemValue="1"/>
                <a4j:support event="onchange" actionListener="#{pageBeanName.changePeroid}"
                oncomplete="togglePageUsingCstmHeight('hideQulDivImg', 'mainPnl', 'dataT_data_panel',83,375,document.getElementById('filterPnlCollapsed').value);" 
                             reRender="intervalGroupId,dataT_data_panel,paging_panel,scriptGenerator"/>
            </t:selectOneRadio>
           
            <t:panelGroup colspan="2" forceId="true" id="intervalGroupId">
                <t:panelGrid columns="5" rendered="#{pageBeanName.limitedPeroid == 1}">
                    <h:outputText value="#{resourcesBundle.from_date_param}" styleClass="divtext"/>
                    <t:panelGroup>
                        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                         popupDateFormat="dd/MM/yyyy" forceId="true" id="fromdatecs" size="20"
                                         maxlength="200" styleClass="textboxsmall2" currentDayCellClass="currentDayCell"
                                         renderAsPopup="true" align="#{globalResources.align}"
                                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                         value="#{pageBeanName.intervalFromDate}">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
                        <c:requiredFieldValidator componentToValidate="fromdatecs" display="dynamic"
                                                  group="intervalGroup" errorMessage="#{globalResources.missingField}"
                                                  highlight="false"/>
                        <c:dateFormatValidator componentToValidate="fromdatecs" display="dynamic"
                                               errorMessage="#{globalResources.messageErrorForAdding}"
                                               group="intervalGroup"/>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.to_date_param}" styleClass="divtext"/>
                    <t:panelGroup>
                        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                         popupDateFormat="dd/MM/yyyy" forceId="true" id="todatecs" size="20"
                                         maxlength="200" styleClass="textboxsmall2" currentDayCellClass="currentDayCell"
                                         renderAsPopup="true" align="#{globalResources.align}"
                                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                         value="#{pageBeanName.intervalUnitlDate}">
                            <f:converter converterId="SqlDateConverter"/>
                        </t:inputCalendar>
                        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
                        <c:requiredFieldValidator componentToValidate="todatecs" display="dynamic" group="intervalGroup"
                                                  errorMessage="#{globalResources.missingField}" highlight="false"/>
                        <c:dateFormatValidator componentToValidate="todatecs" display="dynamic"
                                               errorMessage="#{globalResources.messageErrorForAdding}"
                                               group="intervalGroup"/>
                        <c:compareDateValidator componentToValidate="fromdatecs" componentToCompare="todatecs"
                                                display="dynamic"
                                                errorMessage="#{resourcesBundle.EndLessThanStartErrMSG}"
                                                operator="before" group="intervalGroup"/>
                    </t:panelGroup>
                    <t:commandButton value="#{resourcesBundle.show}" styleClass="cssButtonSmaller"
                                     onclick="return validatemyForm('intervalGroup')"
                                     action="#{pageBeanName.viewSalEmpSalaryInterval}"/>
                </t:panelGrid>
            </t:panelGroup>
        
         <t:panelGroup>
            <h:outputText value="#{resourcesBundle.changeType_Label}" styleClass="divtext"/>
            <t:commandButton type="button" value="#{globalResources.Available}" styleClass="cssButtonSmaller"
                             onClick="openCustomDiv1Fn();">
                <a4j:jsFunction name="openCustomDiv1Fn"
                                oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"
                                reRender="customDiv1"/>
            </t:commandButton>
         </t:panelGroup>
        </t:panelGrid>
        </t:panelGroup>
