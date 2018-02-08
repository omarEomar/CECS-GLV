<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
<t:outputLabel forceId="true" id="testConditionDivTitle" value="#{resourcesBundle.test}"/>
<f:verbatim>
    <br/>
</f:verbatim>
<t:panelGrid forceId="true" id="testCondDiv" columnClasses="center" align="center" width="100%">
    <t:panelGroup>
        <h:outputText value="" id="testDivErrMsg" styleClass="errMsg"/>
        <h:outputText value="#{pageBeanName.testConditionErrMsg}" id="testConditionErrMsg" styleClass="errMsg"/>
        <h:outputText styleClass="group_title" id="testConditionMessage" value="#{pageBeanName.testConditionMessage}"/>
    </t:panelGroup>
    <t:panelGrid columns="2" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0"
                 onkeypress="FireButtonClickOldStyle(event,'testConditionButton');">
        <h:outputText id="civilID" value="#{globalResources.globalCivilId}" styleClass="lable01 nowrap_txt"/>
        <t:panelGroup>
            <t:inputText onkeyup="enabelIntegerOnly(this);" forceId="true" id="civil_div_searchText"
                         value="#{pageBeanName.testCivilId}" styleClass="textboxlarge"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <h:outputText id="name" value="#{globalResources.globalName}" styleClass="lable01"/>
        <t:inputText disabled="true" forceId="true" id="employeeName" value="#{pageBeanName.employeeName}"
                     styleClass="textboxlarge"/>
    </t:panelGrid>
    <t:panelGroup colspan="1" forceId="true" id="CompareConditionsResultPnl" styleClass="lovDivScroll2"
                  rendered="#{pageBeanName.compareResultDTOListSize != 0}">
        <t:dataTable id="compareResultDT" var="list" value="#{pageBeanName.compareResultDTOList}"
                     preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column sortable="false" width="60%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{resourcesBundle.conditionName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
            </t:column>
            <t:column sortable="false" width="40%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="status" value="#{globalResources.Status}"/>
                </f:facet>
                <h:outputText styleClass="errMsg" rendered="#{list.status!= 1}"
                              value="#{(list.status == 0) ? resourcesBundle.test_condition_match_no:resourcesBundle.ErrorCondtionNotFound}"/>
                <h:outputText styleClass="sucessMsg2" rendered="#{list.status== 1}"
                              value="#{resourcesBundle.test_condition_match}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid align="center" columns="4" id="testConditionsbuttonsPanel">
        <t:commandButton forceId="true" id="testConditionButton" type="button"
                         onclick="validateMyCivilIdJS('#{globalResources.civil_no_not_less_than_12}','#{globalResources.civiliderror}');"
                         styleClass="cssButtonSmall" value="#{resourcesBundle.test}"/>
        <a4j:jsFunction name="testConditionJS" action="#{pageBeanName.testCondition}"
                        reRender="testCondDiv, myForm:testConditionMessage"/>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" styleClass="cssButtonSmall"
                         value="#{globalResources.back}" onblur="setFocusAtMyTestDiv();"
                         onclick="clearConditionTestDivMsg();hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false;"/>
    </t:panelGrid>
</t:panelGrid>
<script type="text/javascript">
 function reset() {
      document.getElementById('employeeName').innerHtml = '';
      document.getElementById('myForm:testConditionMessage').innerHtml = '';
  }

  function validateMyCivilIdJS(emptyMsg, msg) {
      clearConditionTestDivMsg();

      var valid = true;
      if (document.getElementById('civil_div_searchText').value.length != 12) {
          document.getElementById('myForm:testDivErrMsg').innerHTML = emptyMsg;
          valid = false;
      }
      else {
          valid = isValidCivilId('civil_div_searchText');
          if (!valid) {
              document.getElementById('myForm:testDivErrMsg').innerHTML = msg;
          }
      }

      if (valid) {
          testConditionJS();
      }

  }

  function clearConditionTestDivMsg() {
      document.getElementById('myForm:testDivErrMsg').innerHTML = '';
      document.getElementById('myForm:testConditionMessage').innerHTML = '';
      document.getElementById('myForm:testConditionErrMsg').innerHTML = '';
  }
</script>
