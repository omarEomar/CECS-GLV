<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<tiles:importAttribute scope="request"/>
<t:panelGroup id="treeAddDiv" forceId="true">
    <t:panelGroup>
        <t:outputText id="successMsgTreeAdd" forceId="true" value="#{globalResources.SuccessAdds}"
                      styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
        <h:outputText id="error" value="#{globalexceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                      rendered="#{pageBeanName.showErrorMsg}"/>
        <f:verbatim>
            <center>
                <span id="msg" class="errMsg"/>
            </center>
        </f:verbatim>
        <t:panelGrid columns="2" border="0" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
            <t:outputLabel value="#{globalResources.SearchName}" id="outputLabelName" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText forceId="true" id="add_first_inputTxt" value="#{pageBeanName.levelName}"
                             maxlength="#{pageBeanName.nameMaxLength}" styleClass="textboxlarge" tabindex="1"
                             onkeydown="onKeyDownFirstElement(event,'valityNames1','backButtonTreeAddDiv')"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                 <f:verbatim>&nbsp;</f:verbatim>
                <c:requiredFieldValidator componentToValidate="add_first_inputTxt" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" group="addDiv"/>
            </t:panelGroup>
            <t:outputText value="#{globalResources.from_Date}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="validFromDateId" size="20" maxlength="200"
                                 styleClass="textboxmedium" style="width:188px;" currentDayCellClass="currentDayCell"
                                 value="#{pageBeanName.dto.validFromDate}" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                 <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <c:dateFormatValidator componentToValidate="validFromDateId" group="addDiv"
                                       errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
            </t:panelGroup>
            <t:outputText value="#{globalResources.until_date}" styleClass="lable01"/>
            <t:panelGroup colspan="3">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="validUntilDateId" size="20" maxlength="200"
                                 styleClass="textboxmedium" style="width:188px;" currentDayCellClass="currentDayCell"
                                 value="#{pageBeanName.dto.validUntilDate}" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                 <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <c:dateFormatValidator componentToValidate="validUntilDateId" group="addDiv"
                                       errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
                <c:compareDateValidator componentToValidate="validUntilDateId" componentToCompare="validFromDateId"
                                        display="dynamic" errorMessage="#{globalResources.duration_error}"
                                        operator="afterOrEqual" highlight="false" group="addDiv"/>
            </t:panelGroup>
            <%--<t:outputText value="#{globalResources.valid}" styleClass="lable01"/>--%>
            <%--<t:panelGroup colspan="3">
                <t:selectBooleanCheckbox forceId="true" id="validFlagAddId" value="#{pageBeanName.dto.validDateFlag}"/>
            </t:panelGroup>--%>
        </t:panelGrid>
        <t:panelGrid align="center" columns="3">
            <h:commandButton value="#{globalResources.SaveButton}" action="#{pageBeanName.addNew}" id="save"
                             onclick=" return validatetreeform('addDiv'); validateInputText('add_first_inputTxt','msg',null,null,'successMsgTreeAdd');"
                             styleClass="cssButtonSmall" tabindex="4"/>
            <h:panelGroup>
                <t:commandButton forceId="true" type="button"
                                 onclick="if(validatetreeform('addDiv')){validateSaveAndNew('add_first_inputTxt','msg',null,null,'successMsgTreeAdd');}"
                                 styleClass="cssButtonSmall" id="SaveNewButton" value="#{globalResources.AddNewButton}"
                                 tabindex="5"/>
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.addAndNew}"
                                reRender="treeList,treeDivList,treeAddDiv,treeDivPanel"
                                oncomplete="setFocusOnElement('add_first_inputTxt');"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonTreeAddDiv" styleClass="cssButtonSmall"
                                 value="#{globalResources.back}" tabindex="6"
                                 onclick="backJsFunctionTreeAddDiv(); return false;"
                                 onkeydown="onKeyDownLastElement(event,'SaveNewButton','add_first_inputTxt')"/>
                <a4j:jsFunction name="backJsFunctionTreeAddDiv" action="#{pageBeanName.cancelAddTree}"
                                oncomplete="hidTreeDiv('add',window.#{divTreeDetails},window.divTreeAdd,'successMsgTreeAdd');hidTreeDiv('add',window.#{divTreeDetails},window.#{divTreeDetails},null); foucsAddbuttonOnList();"
                                reRender="treeAddDiv,treeList,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>