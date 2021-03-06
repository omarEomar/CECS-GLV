<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.divContent1Dynamic{width:730px !important;}/*---- by hooma---*/</htm:style>
<t:panelGrid columns="2" align="center" width="100%" rowClasses="row01,row02" cellpadding="2" cellspacing="0">
    <t:outputText value="#{globalResources.Code}" styleClass="lable01" rendered="#{pageBeanName.editMode}"/>
    <t:inputText value="#{pageBeanName.pageDTO.code.key}" styleClass="textboxsmall2" rendered="#{pageBeanName.editMode}"
                 disabled="true"/>
    <t:outputText value="#{resourcesBundle.ParameterType}" styleClass="lable01"/>
    <t:inputText value="#{pageBeanName.pageDTO.parameterTypesDTO.name}" styleClass="textboxlarge4" style="width: 630px;" disabled="true"/>
    <t:outputText value="#{resourcesBundle.conditionName}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge4" style="width: 625px;" id="conditionName" forceId="true"
                     onblur="trimBorders(this);" readonly="#{pageBeanName.viewOnlyMode}"/>
        <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="conditionName" errorMessage="#{globalResources.missingField}"
                                   display="dynamic" uniqueId="lineDTOBox"/>
    </t:panelGroup>
    <t:panelGroup/>
    <t:panelGroup>
        <t:outputText value="#{resourcesBundle.usedInFilteration}" styleClass="lable01"/>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        <t:selectBooleanCheckbox id="usedAsFilter" value="#{pageBeanName.pageDTO.usedAsFilter}"
                                 disabled="#{pageBeanName.maintainMode==2}"/>
    </t:panelGroup>
    <t:outputText value="#{resourcesBundle.conditionDesc}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputTextarea value="#{pageBeanName.pageDTO.conditionDesc}" styleClass="masterinput_hooma"
                         style="width: 630px;" rows="5" disabled="#{pageBeanName.maintainMode==2}"/>
    </t:panelGroup>
    <%--<t:panelGroup colspan="2" forceId="true" id="appModulesPanel">
        <t:panelGrid columns="3" align="center" width="100%" cellpadding="2" cellspacing="0"
                     columnClasses="center,center,center" style="min-height: 155px;padding-right: 17px;display: block;">
            <t:outputText value="#{resourcesBundle.availableModules}" styleClass="lable01"/>
            <t:outputText value="" styleClass="lable01"/>
            <t:outputText value="#{resourcesBundle.relatedModules}" styleClass="lable01"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="searchAvailableModules" styleClass="textboxlarge"
                             value="#{detailBeanName.searchNameCritria}"
                             onkeypress="goSearchAvailableModulesList(event);"/>
                <a4j:jsFunction name="searchAvailableModulesList" action="#{detailBeanName.searchAvailableModByName}"
                                reRender="availableModulesList,errorCodeId"/>
                <t:selectManyMenu forceId="true" id="availableModulesList" ondblclick="cAddOneFunction();"
                                  value="#{detailBeanName.toBeAddedModulesList}" styleClass="textboxlarge"
                                  style="min-height:117px;">
                    <t:selectItems value="#{detailBeanName.availableModulesList}" var="types"
                                   itemValue="#{types.code.key}" itemLabel="#{types.name}"/>
                </t:selectManyMenu>
                --%><%-- <t:panelGroup colspan="1" forceId="true" id="errorCodePanelId"> <t:outputLabel id="errorCodeId"
                     value="error" forceId="true" styleClass="validation_error" style="display:none"/> </t:panelGroup>--%><%--
            </t:panelGroup>
            <t:panelGroup>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cAddAllButton" type="button"
                                     disabled="#{detailBeanName.availableModulesListSize == 0 || pageBeanName.maintainMode==2}"
                                     onclick="cAddAllFunction();" styleClass="cssButtonSmaller" value=">>"/>
                    <a4j:jsFunction name="cAddAllFunction" action="#{detailBeanName.addAllElements}"
                                    reRender="appModulesPanel"/>
                </t:panelGroup>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cAddOneButton" type="button"
                                     disabled="#{detailBeanName.availableModulesListSize == 0 || pageBeanName.maintainMode==2}"
                                     onclick="cAddOneFunction();" styleClass="cssButtonSmaller" value=">"/>
                    <a4j:jsFunction name="cAddOneFunction" action="#{detailBeanName.addSelectedElements}"
                                    reRender="appModulesPanel"/>
                </t:panelGroup>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cRemoveOneButton" type="button"
                                     disabled="#{detailBeanName.conditionAppModulesDTOListSize == 0 || pageBeanName.maintainMode==2}"
                                     onclick="cRemoveOneFunction();" styleClass="cssButtonSmaller" value="&lt;"/>
                    <a4j:jsFunction name="cRemoveOneFunction" action="#{detailBeanName.removeSelectedElements}"
                                    reRender="appModulesPanel"/>
                </t:panelGroup>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cRemoveAllButton" type="button"
                                     disabled="#{detailBeanName.conditionAppModulesDTOListSize == 0 || pageBeanName.maintainMode==2}"
                                     onclick="cRemoveAllFunction();" styleClass="cssButtonSmaller" value="&lt;&lt;"/>
                    <a4j:jsFunction name="cRemoveAllFunction" action="#{detailBeanName.removeAllElements}"
                                    reRender="appModulesPanel"/>
                </t:panelGroup>
            </t:panelGroup>
            <t:panelGroup>
                <t:selectManyMenu forceId="true" id="relatedModulesList" ondblclick="cRemoveOneFunction();"
                                  value="#{detailBeanName.toBeRemovedModulesList}" styleClass="textboxlarge"
                                  style="min-height:117px">
                    <t:selectItems value="#{pageBeanName.pageDTO.conditionAppModulesDTOList}" var="types"
                                   itemValue="#{types.code.key}" itemLabel="#{types.appModulesDTO.name}"/>
                </t:selectManyMenu>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>--%>
</t:panelGrid>
<t:inputHidden value="#{detailBeanName.virtualConstValue}" forceId="true" id="virtualConstValue"/>
<script type="text/javascript">
  setFocusAtMyFirstElement();

  function setFocusAtMyFirstElement() {
      if (document.getElementById('conditionName') != null)
          setFocusOnlyOnElement('conditionName').focus();
  }

  function goSearchAvailableModulesList(e) {
      if (e.keyCode == 13) {
          searchAvailableModulesList();
          e.preventDefault();
      }
  }
</script>
