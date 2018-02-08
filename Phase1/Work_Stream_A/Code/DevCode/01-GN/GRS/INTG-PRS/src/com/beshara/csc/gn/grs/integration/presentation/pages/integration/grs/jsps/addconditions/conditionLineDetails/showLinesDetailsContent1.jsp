<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
<t:panelGrid columns="2" width="100%" forceId="true" id="savePanel" rowClasses="row01,row02" cellpadding="2"
             cellspacing="0">
    <h:outputLabel value="#{grsResources.ParameterType}" styleClass="lable01"/>
    <t:inputText maxlength="240" forceId="true" id="paramTypeText"
                 value="#{linesEditBean.parameterType}" styleClass="textboxlarge" onkeyup="trimBorders(this);" disabled="true"/>
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
    <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>
    <t:panelGrid cellpadding="0" columns="2">
        <t:panelGroup>
            <t:inputText value="#{linesEditBean.pageDTO.code.key}" styleClass="textboxlarge" disabled="true"/>
        </t:panelGroup>
    </t:panelGrid>
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
    <h:outputText value="#{grsResources.Name}" styleClass="lable01"/>
    <t:panelGrid cellpadding="0" columns="2">
        <t:panelGroup>
            <t:inputText maxlength="240" forceId="true" id="Name" value="#{linesEditBean.pageDTO.name}"
                         styleClass="textboxlarge" onkeyup="trimBorders(this);" disabled="true"/>
        </t:panelGroup>
    </t:panelGrid>
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
    <h:outputText value="#{grsResources.chooseParameters}" styleClass="lable01"/>
    <t:panelGrid cellpadding="0" columns="2">
        <t:panelGroup>
            <t:inputText forceId="true" id="parameterId" styleClass="filteration_input_text"
                         onkeypress="filterationInputOnKeyPress(event,this,'parameters','parameterError',changeParameterVal);"
                         onblur="filterationInputOnBlur(event,this,'parameters','parameterError',changeParameterVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;" disabled="true">
            </t:inputText>
            <t:selectOneMenu forceId="true" id="parameters" value="#{linesEditBean.parameterCode}"
                             styleClass="textboxlarge" onchange="filterationDropboxOnChange(event,this,'parameterId','parameterError',changeParameter);" disabled="true">
                <f:selectItem itemLabel="#{grsResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems var="parametersList" itemLabel="#{parametersList.name}"
                               itemValue="#{parametersList.code.key}" value="#{linesEditBean.parametersList}"/>
                <a4j:jsFunction name="changeParameter"
                             reRender="comparedParameters,valueText,loadValueButton,divAdds,scrollerPanel,buttonPanel,validSql"
                             action="#{linesEditBean.updateParameterDTO}" oncomplete="evaluateOverview()"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
    <h:outputText value="#{grsResources.chooseOperations}" styleClass="lable01"/>
    <t:panelGrid cellpadding="0" columns="2">
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="operators" value="#{linesEditBean.operationCode}"
                             styleClass="textboxlarge" disabled="true">
                <f:selectItem itemLabel="#{grsResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems var="operatorsList" itemLabel="#{operatorsList.name}"
                               itemValue="#{operatorsList.code.key}" value="#{linesEditBean.operatorsList}"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
    <h:outputText/>
    <t:selectOneRadio forceId="true" id="valueRadioBtn" value="#{linesEditBean.valueRadioType}" styleClass="lable01" disabled="true">
        <f:selectItem itemLabel="#{grsResources.value}" itemValue="0"/>
        <f:selectItem itemLabel="#{grsResources.comparedParameter}" itemValue="1"/>
    
    </t:selectOneRadio>
    <h:outputText value="#{grsResources.value}" styleClass="lable01"/>
    <t:panelGrid cellpadding="0" columns="3" id="valueTextPG">
        <t:panelGroup>
            <t:inputText maxlength="240" forceId="true" disabled="true" id="valueText"
                         value="#{linesEditBean.value}" styleClass="textboxlarge" onchange="evaluateOverview()"
                          ></t:inputText>
        </t:panelGroup>
       
    </t:panelGrid>
    <h:outputText value="#{grsResources.comparedParameter}" styleClass="lable01"/>
    <t:panelGrid cellpadding="0" columns="2" id="comparedParametersPG">
        <t:panelGroup>
            <t:inputText forceId="true" id="parameter2Id" styleClass="filteration_input_text"
                         onkeypress="filterationInputOnKeyPress(event,this,'comparedParameters','parameter2Error',changeParameter2Val);"
                         onblur="filterationInputOnBlur(event,this,'comparedParameters','parameter2Error',changeParameter2Val);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="true">
                <%--<a4j:jsFunction name="changeParameter2Val" reRender="parameters,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>--%>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="comparedParameters" value="#{linesEditBean.comparedParameterCode}"
                             styleClass="textboxlarge"
                             disabled="true"
                             onchange="evaluateOverview();filterationDropboxOnChange(event,this,'parameter2Id','parameter2Error',null);">
                <f:selectItem itemLabel="#{grsResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems var="comparedParametersList" itemLabel="#{comparedParametersList.name}"
                               itemValue="#{comparedParametersList.code.key}"
                               value="#{linesEditBean.comparedParametersList}"/>
            </t:selectOneMenu>
            <%--<h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{linesEditBean.disableValueElement}"/>--%>
            <%--<t:outputLabel id="parameter2Error" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>--%>
        </t:panelGroup>
        <%--<c2:compareValidator componentToCompare="virtualConstValue" componentToValidate="comparedParameters"
                             operator="not" errorMessage="#{grsResources.selectOneItem}" display="dynamic"
                             active="#{linesEditBean.disableValueElement}"/>--%>
    </t:panelGrid>
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
    <h:outputText value="#{grsResources.overview}" styleClass="lable01"/>
    <t:inputTextarea disabled="true" forceId="true" id="overview" value="#{linesEditBean.lineOverView}"
                     styleClass="masterinput_hooma" style="width: 315px;" />
    <%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
</t:panelGrid>
<t:panelGrid columns="2" border="0" align="center">
     
     <%--<t:commandButton action="list"  forceId="true" id="BackButtonManyToMany"   onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};" styleClass="cssButtonSmall"  value="#{globalResources.back}"/>--%>
     <h:panelGroup>
             <t:commandButton forceId="true" id="BackButtonManyToMany" onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};"  value="#{globalResources.back}" action="#{conditionIntgLineSub.backToLinesTab}" styleClass="cssButtonSmall" rendered="#{conditionIntgLineDetailBean.mainLinesDetails}"/>
             <t:commandButton forceId="true" id="BackToLineSub" onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};"  value="#{globalResources.back}" action="#{conditionIntgLineSub.backToLineSub}" styleClass="cssButtonSmall" rendered="#{conditionIntgLineDetailBean.alternativeLinesDetails}"/>
    </h:panelGroup>
 </t:panelGrid>
<%-- ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~ ~~~~~~~~~~--%>
<t:inputHidden id="virtualConstValue" forceId="true" value="#{linesEditBean.virtualConstValue}"/>
<t:inputHidden id="MultiValue" forceId="true" value="#{linesEditBean.mulitValue}"/>
<t:inputHidden id="validSql" forceId="true" value="#{linesEditBean.validSQL}"/>
<t:inputHidden forceId="true" id="noOfTableRows" value="#{shared_util.noOfTableRows}"/>
<t:inputHidden forceId="true" id="arrayId" value=""/>
<t:inputHidden forceId="true" id="arrayIdAdd" value=""/>
<t:inputHidden value="#{linesEditBean.availableListSize}" forceId="true" id="listSizeAdd"/>
<t:inputHidden value="#{linesEditBean.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/>

