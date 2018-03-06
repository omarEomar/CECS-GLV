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
    <h:messages style="color:red;margin:8px;"/>
    <t:panelGrid columns="2" border="0" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
        <h:outputText value="#{resourcesBundle.grade_type_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="grade_name" value="#{pageBeanName.pageDTO.name}"
                         styleClass="textboxlarge" forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="grade_name" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{gradeTypesListBean.pageMode==1}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.grade_type_value_type}" styleClass="lable01"/>
        <t:panelGroup>
            <h:selectOneRadio value="#{pageBeanName.pageDTO.gradeTypeValType}" styleClass="lable01" id="gradeTypeValTypechek_id"  >
                <f:selectItem itemValue="2" itemLabel="#{resourcesBundle.character}"/>
                <f:selectItem itemValue="1" itemLabel="#{resourcesBundle.numerical}"/>
                <a4j:support event="onclick" action="#{pageBeanName.updateCase}"
                             reRender="divAddLookup,scriptPanelGroup"/>
            </h:selectOneRadio>
            <h:inputHidden id="radioValue" forceId="true" value="#{pageBeanName.renderOption}"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup id="numircalPanelGrp" forceId="true" rendered="#{pageBeanName.renderOption}" colspan="2">
        <t:panelGrid columns="2" border="0" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{resourcesBundle.min_val}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="grade_min_val" value="#{pageBeanName.pageDTO.minValue}" maxlength="3"
                             styleClass="textboxlarge" forceId="true" onkeypress="enabelFloatOnly(this);"
                             onkeyup="enabelFloatOnly(this);"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="grade_min_val" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{gradeTypesListBean.pageMode==1 && gradeTypesListBean.renderOption}"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.max_val}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="grade_max_val" value="#{pageBeanName.pageDTO.maxValue}" maxlength="3"
                             styleClass="textboxlarge" forceId="true" onkeypress="enabelFloatOnly(this);"
                             onkeyup="enabelFloatOnly(this);"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="grade_max_val" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{gradeTypesListBean.pageMode==1 && gradeTypesListBean.renderOption}"/>
                <c:compareValidator active="#{gradeTypesListBean.pageMode==1 && gradeTypesListBean.renderOption}"
                                    highlight="false" componentToValidate="grade_max_val"
                                    componentToCompare="grade_min_val" operator="greEq" display="dynamic"
                                    errorMessage="&lt;br>#{resourcesBundle.min_max_value_error_msg}"/>
            </t:panelGroup>
            <h:outputText id="grade_equ_label" value="#{resourcesBundle.grade_equation}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="grade_equation" value="#{pageBeanName.pageDTO.formula}" styleClass="textboxlarge"
                             forceId="true"/>
               
            </t:panelGroup>
        </t:panelGrid>
         <t:outputText forceId="true" id="formulaErrorMessageForAdd" styleClass="errMsgNoHeight"/>
    </t:panelGroup>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButton" onclick="return validatemyForm2();" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.save}"/>
        <h:panelGroup>
            <t:commandButton type="button" forceId="true" id="SaveNewButton"
                             onclick="if(validatemyForm2()){validateSaveAndNewClientValidator();}else{return false;}"
                             styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup"
                            oncomplete="setFocusOnlyOnElement('grade_name');"/>
        </h:panelGroup>
        <h:panelGroup>
            <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"
                             onblur="settingFoucsAtDivAdd();"/>
            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}"
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:aprmakerName','myForm:error','add');foucsAddbuttonOnList();"
                            reRender="editButton,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
        </h:panelGroup>
    </t:panelGrid>
    <!--<script type="text/javascript">
          checkGradeRange();

          function checkGradeRange() {
              if (document.getElementById('grade_min_val').value != null ) {
                  document.getElementById('grade_max_val').value;
              }
          }
        </script>-->
</t:panelGroup>
