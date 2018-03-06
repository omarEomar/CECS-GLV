<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divEditLookup">
    <h:messages style="color:red;margin:8px;"/>
    <%-- <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}"
         styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>--%>
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error"
                  rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    <%-- <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
         rendered="#{pageBeanName.showErrorMsg}"/>--%>
    <%-- <h:outputText id="clientErrorMessage" styleClass="errMsg" />--%>
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg"/>
    <h:panelGrid columns="2" rendered="#{pageBeanName.showEdit}" rowClasses="row01,row02" width="100%" cellpadding="3"
                 cellspacing="0" border="0">
        <h:outputText value="#{resourcesBundle.grade_type_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="grade_name1"
                         value="#{pageBeanName.selectedGradeTypesDTO.name}" styleClass="textboxlarge" forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="grade_name1" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{gradeTypesListBean.pageMode==2}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.grade_type_value_type}" styleClass="lable01"/>
        <t:panelGroup>
            <h:selectOneRadio value="#{pageBeanName.selectedGradeTypesDTO.gradeTypeValType}" styleClass="lable01"
                              disabled="true">
                <f:selectItem itemValue="2" itemLabel="#{resourcesBundle.character}"/>
                <f:selectItem itemValue="1" itemLabel="#{resourcesBundle.numerical}"/>
            </h:selectOneRadio>
            <h:inputHidden id="radioValueedit" forceId="true" value="#{pageBeanName.renderOption}"/>
        </t:panelGroup>
    </h:panelGrid>
    <t:panelGroup id="numircalPanelGrp2" forceId="true" rendered="#{pageBeanName.renderOption}" colspan="2">
        <t:panelGrid columns="2" border="0" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{resourcesBundle.min_val}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="grade_min_val1" value="#{pageBeanName.selectedGradeTypesDTO.minValue}" maxlength="3"
                             styleClass="textboxlarge" forceId="true" onkeypress="enabelFloatOnly(this);"
                             onkeyup="enabelFloatOnly(this);"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="grade_min_val1" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{gradeTypesListBean.pageMode==2 && gradeTypesListBean.renderOption}"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.max_val}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="grade_max_val1" value="#{pageBeanName.selectedGradeTypesDTO.maxValue}" maxlength="3"
                             styleClass="textboxlarge" forceId="true" onkeypress="enabelFloatOnly(this);"
                             onkeyup="enabelFloatOnly(this);"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="grade_max_val1" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{gradeTypesListBean.pageMode==2 && gradeTypesListBean.renderOption}"/>
                <c:compareValidator active="#{gradeTypesListBean.pageMode==2 && gradeTypesListBean.renderOption}"
                                    highlight="false" componentToValidate="grade_max_val1"
                                    componentToCompare="grade_min_val1" operator="greEq" display="dynamic"
                                    errorMessage="&lt;br>#{resourcesBundle.min_max_value_error_msg}"/>
            </t:panelGroup>
            <h:outputText id="grade1_equ_label" value="#{resourcesBundle.grade_equation}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="grade1_equation" value="#{pageBeanName.selectedGradeTypesDTO.formula}"
                             styleClass="textboxlarge" forceId="true"/>
                <%-- <a4j:jsFunction name="evalEq" action="#{pageBeanName.validateEq}" reRender="formulaErrorMessage"/>--%>
                <t:outputText forceId="true" id="formulaErrorMessageForEdit" styleClass="errMsgNoHeight"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}"
                         onclick="return validatemyForm1();"/>
        <t:panelGroup>
            <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall"
                             value="#{globalResources.back}" onblur="settingFoucsAtDivEdit();"
                             onclick="cancelEditJs()"  type="button"/>
            <a4j:jsFunction name="cancelEditJs" action="#{pageBeanName.cancelLink}"
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'myForm:grade_name1','myForm:errorEdit');foucsAddbuttonOnList();"
                            />
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>