<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<%-- edit by m.elsaied (fix issue key : NL-279 - 4. edit001.JPG)--%>
<%-- <center><br /><h:outputText value="#{pageBeanName.editDivTitle}" style="font-weight:bold"/></center>
     f:verbatim><br/></f:verbatim> removed by sherif.omar we no longer need succesfully message because MSG appeare.
     <h:outputText id="successEdit" value="#{globalResources.SuccessAdds}" styleClass="save"
     rendered="#{pageBeanName.success}"/--%>
<t:panelGroup forceId="true" id="divEditLookup" style="width:100%">
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error"
                  rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    <%-- <f:verbatim><br/></f:verbatim>--%>
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg"/>
    <%-- <f:verbatim><br/></f:verbatim>--%>
    <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>
            <h:outputText value="#{globalResources.SearchName}" styleClass="lable01"/>
            <h:panelGroup>
                <t:inputText id="edit_first_inputTxt" value="#{pageBeanName.selectedPageDTO.name}"
                             styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true"
                             onblur="document.getElementById('handicapRatioEdit').focus();"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="edit_first_inputTxt" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{handicapStatusListBean.pageMode==2}"/>
            </h:panelGroup>
            <%--<h:outputText value="#{resourcesBundle.INF_HandicapStatusDTO_handicapRatio}" styleClass="lable01"/>--%>
            <%--<t:panelGroup>
                <t:inputText id="handicapRatioEdit" value="#{pageBeanName.selectedPageDTO.handicapRatio}"
                             onkeypress="enabelFloatOnly(this);" onkeyup="enabelFloatOnly(this);"
                             styleClass="textboxsmall" forceId="true"/>
                <h:outputText value="%" styleClass="lable01"/>
                <c:regularExpressionValidator componentToValidate="handicapRatioEdit"
                                              pattern="/^(\\d{0,2}(\\.\\d{0,3})?$)|(100(\\.0{0,3})?)$/"
                                              display="dynamic" errorMessage="#{resourcesBundle.from_to_percentage_msg}"
                                              highlight="false" active="#{handicapStatusListBean.pageMode==2}"/>
            </t:panelGroup>--%>
        </h:panelGrid>
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton styleClass="cssButtonSmall" forceId="true" id="SaveButtonEdit"
                             value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}"
                             onclick="return validatemyForm();"/>
            <t:panelGroup>
                <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall"
                                 value="#{globalResources.back}" onblur="settingFoucsAtDivEdit();"/>
                <a4j:jsFunction name="cancelEditJs" action="#{pageBeanName.cancelEdit}"
                                oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'myForm:NameEdit','myForm:errorEdit');"
                                reRender="scriptPanelGroup"/>
            </t:panelGroup>
            <%-- a4j:commandButton styleClass="cssButtonSmall" id="CancelButtonEdit" value="#{globalResources.back}"
                 action="#{pageBeanName.cancelEdit}"
                 oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'myForm:NameEdit','myForm:errorEdit');"
                 reRender="scriptPanelGroup"/--%>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>