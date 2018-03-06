<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>


<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:saveState value="#{pageBeanName.success}"/>

<t:panelGroup forceId="true" id="divAddLookup">
    
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <f:verbatim> <br/></f:verbatim>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
        
        <t:panelGrid columns="2" border="0" styleClass="row01" width="100%" rowClasses="row01,row02" cellpadding="0" cellspacing="0">
                
            <h:outputText value="#{resourcesBundle.currency_name}" styleClass="lable01"/> 
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="currName" value="#{pageBeanName.pageDTO.currencyName}" styleClass="textboxlarge" forceId="true" />
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="currName" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{currenciesBean.pageMode==1}"/>
            </t:panelGroup>
          
            <t:outputText value="#{resourcesBundle.currency_abrv}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}"  id="currAbrv" value="#{pageBeanName.pageDTO.currencyAbrv1}" styleClass="textboxlarge" forceId="true" onkeypress="validateCurAbrevAdd();" onkeyup=="validateCurAbrevAdd();"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="currAbrv" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{currenciesBean.pageMode==1}"/>
            </t:panelGroup>
    
    
            <t:outputText value="#{resourcesBundle.currency_abrv2}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}"  id="currAbrv2" value="#{pageBeanName.pageDTO.currencyAbrv2}" styleClass="textboxlarge" forceId="true" onkeypress="validateCurAbrev2Add();" onkeyup=="validateCurAbrev2Add();"/>
            </t:panelGroup>
        </t:panelGrid>
</t:panelGroup>

<t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
            <h:panelGroup>
                <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup" oncomplete="setFocusOnlyOnElement('currName');"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="settingFoucsAtDivAdd();"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:currName','myForm:error','add');foucsAddbuttonOnList();" reRender="editButton,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
            </h:panelGroup>
</t:panelGrid>
        
<f:verbatim><br/></f:verbatim>

    <script type="text/javascript">
      function validateCurAbrevAdd() {
          var abrev = document.getElementById("currAbrv");
          var abrevValue = abrev.value;
          //to make abrevation text accept 4 digit just uncomment this code and comment for rows down
          //                    if(abrevValue.length >= 5){
          //                        abrev.value = abrevValue.slice(0,4);
          //                        return;
          //                    }
          if (abrevValue.length >= 4) {
              abrev.value = abrevValue.slice(0, 3);
              return;
          }
      }

      function validateCurAbrev2Add() {
          var abrev = document.getElementById("currAbrv2");
          var abrevValue = abrev.value;
          if (abrevValue.length >= 2) {
              abrev.value = abrevValue.slice(0, 1);
              return;
          }
      }
    </script>
