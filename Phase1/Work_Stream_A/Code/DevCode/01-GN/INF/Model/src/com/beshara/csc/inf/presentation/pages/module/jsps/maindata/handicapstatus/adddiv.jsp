<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divAddLookup" style="width:100%">
    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <t:panelGrid columns="2" border="0" styleClass="row01" width="100%">
        <h:outputText value="#{globalResources.SearchName}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" forceId="true" id="add_first_inputTxt"
                         value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge"
                         onblur="document.getElementById('handicapRatioXy').focus();"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="add_first_inputTxt" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{handicapStatusListBean.pageMode==1}"/>
        </t:panelGroup>
        <%--<h:outputText value="#{resourcesBundle.INF_HandicapStatusDTO_handicapRatio}" styleClass="lable01"/>--%>
        <%--<t:panelGroup>
            <t:inputText id="handicapRatioXy" value="#{pageBeanName.pageDTO.handicapRatio}" styleClass="textboxsmall"
                         onkeypress="enabelFloatOnly(this);" onkeyup="enabelFloatOnly(this);" forceId="true"/>
            <h:outputText value="%" styleClass="lable01"/>
            <c:regularExpressionValidator componentToValidate="handicapRatioXy"
                                          pattern="/^(\\d{0,2}(\\.\\d{0,3})?$)|(100(\\.0{0,3})?)$/" display="dynamic"
                                          errorMessage="#{resourcesBundle.from_to_percentage_msg}" highlight="false"
                                          active="#{handicapStatusListBean.pageMode==1}"/>
        </t:panelGroup>--%>
    </t:panelGrid>
    <t:panelGrid columns="3" border="0" align="center">
        <%-- Start By css used to spreate button--%>
        <t:commandButton styleClass="cssButtonSmall" forceId="true" id="SaveButton"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.save}"
                         onclick="return validatemyForm();"/>
        <h:panelGroup>
            <t:commandButton type="button" onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall"
                             forceId="true" id="SaveNewButton" value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup"
                            oncomplete="setFocusOnlyOnElement('add_first_inputTxt');"/>
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();"
                             onclick="backJsFunction(); return false;" styleClass="cssButtonSmall"
                             value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}"
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');"
                            reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,scriptPanelGroup"/>
            <%-- a4j:commandButton value="#{globalResources.back}" styleClass="cssButtonSmall"
                 oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');"
                 action="#{pageBeanName.cancelAdd}"
                 reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,scriptPanelGroup"/--%>
        </t:panelGroup>
        <%-- End By css used to spreate button--%>
    </t:panelGrid>
</t:panelGroup>
<f:verbatim>
    <br/>
</f:verbatim>