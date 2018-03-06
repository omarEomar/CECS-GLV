<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
  <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
<t:saveState value="#{pageBeanName.pageMode}"/>
<t:saveState value="#{pageBeanName.validMode}"/>
<t:panelGroup forceId="true" id="divLinkLookup">
    <h:messages style="color:red;margin:8px;"/>
    <%--<h:outputText id="errorDetails" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error"
                  rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' && !pageBeanName.localErrorMsg}"/>--%>
    <h:outputText id="errorDetails1" value="#{pageBeanName.errorMsg}" styleClass="error"
                  rendered="#{pageBeanName.errorMsg != null}"/>
    <t:outputText forceId="true" id="successAddLockup1" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="clientErrorMessageDetails" styleClass="errMsg"/>
    <t:panelGrid id="addGradesPnlGrp" align="center" width="100%" cellpadding="3" cellspacing="0"
                 rowClasses="row01,row02" columns="2">
        <h:outputText value="#{resourcesBundle.grade_type_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText id="grade_num_name" value="#{pageBeanName.gradeName}" styleClass="textboxlarge" forceId="true"
                         disabled="true"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.gradeValue}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText id="gradeValue" value="#{pageBeanName.infGradeValuesDTO.value}" styleClass="textboxlarge"
                         forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="gradeValue" display="dynamic"
                                      errorMessage="&lt;br>#{globalResources.missingField}" highlight="false"
                                      active="#{gradeTypesListBean.pageMode==3}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.percentageValue}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText id="gradePercentage" value="#{pageBeanName.infGradeValuesDTO.percentageValue}" maxlength="3"
                         styleClass="textboxlarge" forceId="true" onkeypress="enabelFloatOnly(this);"
                         onkeyup="enabelFloatOnly(this);"/>
            <h:outputText value=" % "/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="gradePercentage" display="dynamic"
                                      errorMessage="&lt;br>#{globalResources.missingField}" highlight="false"
                                      active="#{gradeTypesListBean.pageMode==3}"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveBtn" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                         action="#{pageBeanName.saveGradeValues}" onclick="return
             validatemyForm();refresh();">
         <a4j:jsFunction name="refresh" reRender="OperationBar" oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);"/>
        </t:commandButton>
        <h:panelGroup>
            <t:commandButton type="button" forceId="true" id="SaveNewButton_1"
                             onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall"
                             value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNewGradeValues}"
                            reRender="divLinkLookup,dataT_data,OperationBar" oncomplete="setFocusOnlyOnElement('gradeValue');"/>
        </h:panelGroup>
        <h:panelGroup>
            <t:commandButton forceId="true" id="backButtonCustomDiv1" onclick="backJsFunction(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"
                             onblur="settingFoucsAtAddGradeDiv();"/>
            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelLink}"
                            oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);foucsAddbuttonOnList();"
                            reRender="editButton,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar,dataT_data"/>
        </h:panelGroup>
        <%-- <h:panelGroup> <t:commandButton type="button" forceId="true" id="SaveNewBtn"
             onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall"
             value="#{globalResources.AddNewButton}"/> <a4j:jsFunction name="saveAndNew"
             action="#{pageBeanName.saveAndNew}" reRender="divLinkLookup"
             oncomplete="setFocusOnlyOnElement('aprmakerName');"/> </h:panelGroup>--%>
    </t:panelGrid>
</t:panelGroup>