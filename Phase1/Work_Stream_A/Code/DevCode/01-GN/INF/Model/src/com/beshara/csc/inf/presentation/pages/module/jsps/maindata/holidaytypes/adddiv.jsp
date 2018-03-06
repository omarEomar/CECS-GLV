<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:saveState value="#{pageBeanName.success}"/>
<t:panelGroup forceId="true" id="divAddLookup">
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <t:panelGrid columns="2" border="0" styleClass="row01" width="100%" rowClasses="row01,row02" cellpadding="0"
                 cellspacing="0">
        <h:outputText value="#{resourcesBundle.holtype_Name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="holtypeName" value="#{pageBeanName.pageDTO.name}"
                         styleClass="textboxlarge" forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="holtypeName" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{holidayTypesBean.pageMode==1}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.holtypeDays}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="2" id="holtypeDays" value="#{pageBeanName.pageDTO.holtypeDays}"
                         onkeypress="enabelFloatOnly(this);" onkeyup="enabelFloatOnly(this);" styleClass="textboxlarge"
                         forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="holtypeDays" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{holidayTypesBean.pageMode==1}"/>
            <c:regularExpressionValidator componentToValidate="holtypeDays" pattern="/^[0-9]/" display="dynamic"
                                          errorMessage="#{resourcesBundle.holtype_Validate_Integer_Message}"
                                          highlight="false" active="#{holidayTypesBean.pageMode==1}"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center" style="margin-top:10px;">
    <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                     action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
    <h:panelGroup>
        <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="validateSaveAndNewClientValidator();"
                         styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
        <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup"
                        oncomplete="setFocusOnlyOnElement('holtypeName');"/>
    </h:panelGroup>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"
                         onblur="setFocusOnlyOnElement('holtypeName');"/>
        <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}"
                        oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:holtypeName','myForm:error','add');"
                        reRender="editButton,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
    </h:panelGroup>
</t:panelGrid>
<script type="text/javascript">
 < 
</script>