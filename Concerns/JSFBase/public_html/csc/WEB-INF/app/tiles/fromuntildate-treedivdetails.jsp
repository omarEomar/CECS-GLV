<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<tiles:importAttribute scope="request"/>
<t:saveState value="#{pageBeanName.dtoDetails}"/>
<t:saveState value="#{pageBeanName.enableEdit}"/>
<t:div styleClass="#{pageBeanName.divTreeDetails}" forceId="true" id="divTreeDetails">
    <t:div styleClass="divDetailsHead">
        <t:commandButton id="divTreeDetailsclose" forceId="true"
                         onclick="hideLookUpDiv(window.blocker,window.divTreeDetails,'','','');cancelEditFunction();return false;"
                         styleClass="close"/>
    </t:div>
    <t:div styleclass="popup_body">
        <t:panelGroup id="treeViewAndEditDiv" forceId="true" style="width:400px;display:block">
            <t:panelGrid id="treeDetailsDiv" forceId="true" cellpadding="0" cellspacing="0" style="width: inherit;">
                <t:outputText id="errorID" value="#{globalResources.missingField}" styleClass="errMsg"
                              rendered="#{pageBeanName.founded}" style="margin-right: 125px;"/>
                <t:outputText id="errorID2" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                              rendered="#{pageBeanName.showErrorMsg}" style="margin-right: 125px;"/>
                <t:panelGrid width="100%" columns="4" cellpadding="0" cellspacing="0" columnClasses="col1,col2"
                             rowClasses="row01,row02">
                    <t:outputText value="#{globalResources.Code}" styleClass="lable01"/>
                    <t:panelGroup colspan="3">
                        <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.code.key}"
                                     styleClass="textboxsmall"/>
                    </t:panelGroup>
                    <t:outputText value="#{globalResources.SearchName}" styleClass="lable01"/>
                    <t:panelGroup colspan="3">
                        <t:inputText disabled="#{!pageBeanName.enableEdit}" value="#{pageBeanName.dtoDetails.name}"
                                     styleClass="textboxtreelarge" id="nameInputText" forceId="true"/>
                        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <c:requiredFieldValidator componentToValidate="nameInputText" display="dynamic"
                                                  errorMessage="#{globalResources.missingField}"/>
                    </t:panelGroup>
                    <t:outputText value="#{globalResources.from_Date}" styleClass="lable01"/>
                    <t:panelGroup colspan="3">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                         disabled="#{!pageBeanName.enableEdit}"
                                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                         popupDateFormat="dd/MM/yyyy" forceId="true" id="validFromDateEditId" size="20"
                                         maxlength="200" styleClass="textboxmedium" style="width:188px;"
                                         currentDayCellClass="currentDayCell"
                                         value="#{pageBeanName.dtoDetails.validFromDate}" renderAsPopup="true"
                                         align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                         renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                         <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <c:dateFormatValidator componentToValidate="validFromDateEditId"
                                               errorMessage="#{globalResources.messageErrorForAdding}"
                                               display="dynamic"/>
                    </t:panelGroup>
                    <t:outputText value="#{globalResources.until_date}" styleClass="lable01"/>
                    <t:panelGroup colspan="3">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                         disabled="#{!pageBeanName.enableEdit}"
                                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                         popupDateFormat="dd/MM/yyyy" forceId="true" id="validUntilDateEditId" size="20"
                                         maxlength="200" styleClass="textboxmedium" style="width:188px;"
                                         currentDayCellClass="currentDayCell"
                                         value="#{pageBeanName.dtoDetails.validUntilDate}" renderAsPopup="true"
                                         align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                         renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                        </t:inputCalendar>
                         <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <c:dateFormatValidator componentToValidate="validUntilDateEditId"
                                               errorMessage="#{globalResources.messageErrorForAdding}"
                                               display="dynamic"/>
                        <c:compareDateValidator componentToValidate="validUntilDateEditId"
                                                componentToCompare="validFromDateEditId" display="dynamic"
                                                errorMessage="#{globalResources.duration_error}" operator="afterOrEqual"
                                                highlight="false"/>
                    </t:panelGroup>
                    <t:outputText value="#{globalResources.valid}" styleClass="lable01" rendered="#{!pageBeanName.enableEdit}"/>
                    <t:panelGroup colspan="3" rendered="#{!pageBeanName.enableEdit}">
                        <t:selectBooleanCheckbox forceId="true" id="checkValidDateId"
                                                 value="#{pageBeanName.dtoDetails.validDateFlag}"
                                                 disabled="#{!pageBeanName.enableEdit}" />
                    </t:panelGroup>
                </t:panelGrid>
            </t:panelGrid>
            <t:panelGrid columns="3" border="0" align="center" id="buttonsEditDiv" forceId="true">
                <%-- save button--%>
                <t:commandButton rendered="#{pageBeanName.enableEdit}" type="button" forceId="true" id="SaveButtonID"
                                 styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                                 onclick="if(validatetreeform()){refreshFunction();editFunction();}"></t:commandButton>
                <%-- edit button--%>
                <t:commandButton rendered="#{!pageBeanName.enableEdit}" forceId="true" id="SaveButtonEdit"
                                 styleClass="cssButtonSmall" value="#{globalResources.update}"
                                 onclick="enableEditFunction();return false;">
                    <a4j:jsFunction action="#{pageBeanName.enableEdit}" name="enableEditFunction"
                                    reRender="treeViewAndEditDiv,SaveButtonID"/>
                </t:commandButton>
                <%-- cancel edit button--%>
                <t:commandButton forceId="true" type="button" id="cancelButton" rendered="#{pageBeanName.enableEdit}"
                                 styleClass="cssButtonSmall" value="#{globalResources.CancelButton}"
                                 onclick="cancelEditFunction();"/>
            </t:panelGrid>
            <a4j:jsFunction name="refreshFunction" reRender="treeViewAndEditDiv"/>
            <a4j:jsFunction action="#{pageBeanName.edit}" name="editFunction" reRender="treeViewAndEditDiv,treeList"/>
            <a4j:jsFunction action="#{pageBeanName.cancelEdit}" name="cancelEditFunction"
                            reRender="treeViewAndEditDiv"/>
        </t:panelGroup>
    </t:div>
</t:div>