<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="divAddLookup">
    <t:saveState value="#{pageBeanName.pageDTO}"/>
    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{integrationBundle.bank_account_success}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    
    <t:panelGrid columns="2" border="0" width="100%" cellpadding="3" cellspacing="0" rowClasses="row02,row01">
       
        <h:outputText value="#{integrationBundle.bank_name}" styleClass="lable01"/> 
         <t:panelGroup>
            <t:inputText onkeypress="FireButtonClick('SaveButton')" onfocus="this.select();" onchange="trimBorders(add_first_inputTxt);"  
                forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_first_inputTxt" value="#{pageBeanName.bankName}" styleClass="textboxmedium"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <h:outputText id="error" value="#{integrationBundle[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
            <c:requiredFieldValidator group="addGroup" componentToValidate="add_first_inputTxt"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="addfirst_inputTxt"/>
            <%--<h:outputText id="clientErrorMessage" styleClass="errMsg" />--%>            
         </t:panelGroup>
          <h:outputText value="#{integrationBundle.short_bnk_name}" styleClass="lable01"/> 
          <t:panelGroup>
            <t:inputText  forceId="true" id="short_name" value="#{pageBeanName.pageDTO.shortName}"   maxlength="4" styleClass="textboxsmall" onblur="trimBorders(short_name);"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>&nbsp;</f:verbatim> 
                <c:requiredFieldValidator group="addGroup" componentToValidate="short_name"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="shortName"/>        
                <t:outputText id="shortNameError" forceId="true" value="#{integrationBundle.shortNameErrorMsg}" styleClass="errMsg" style="display:none;"/>    
          </t:panelGroup>
        <h:outputText value="#{integrationBundle.bank_email}" styleClass="lable01"/> 
         <t:panelGroup>
            <t:inputText onkeypress="FireButtonClick('SaveButton')" onfocus="this.select();" onchange="trimBorders(add_second_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_second_inputTxt" value="#{pageBeanName.bankEmail}" styleClass="textboxmedium"/>
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            <t:outputText id="bankMailExample" forceId="true" value="#{integrationBundle.emailFormat}" styleClass="warningClass_cust1"/>
            <f:verbatim><br/></f:verbatim>
            <t:outputText id="bankMailError" forceId="true" value="#{integrationBundle.emailErrorMsg}" styleClass="errMsg" style="display:none;"/>    
         </t:panelGroup>         
    </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>  
</t:panelGroup>
    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="clearMsgs();settingFoucsAtDivAdd(); return validateInputTextAndBlock(); "/>            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="clearMsgs();if(validateInputTextAndBlock()==true){saveAndNew();}else{return false;}" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,OperationBar,shortNameError" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,dataT_data,noOfRecords,paging_panel,listSize,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>

<script language="javascript" type="text/javascript">
  function clearMsgs() {
      if (document.getElementById('myForm:error') != null) {
          document.getElementById('myForm:error').style.display = "none";
      }
      if (document.getElementById('shortNameError') != null) {
          document.getElementById('shortNameError').style.display = "none";
      }
      if (document.getElementById('editShortNameError') != null) {
          document.getElementById('editShortNameError').style.display = "none";
      }
      if (document.getElementById('myForm:clientErrorMessage') != null) {
          document.getElementById('myForm:clientErrorMessage').style.display = "none";
      }

      if (document.getElementById('successAddLockup') != null) {
          document.getElementById('successAddLockup').style.display = "none";
      }
  }

  function validateInputTextAndBlock() {

      /* if (!validatemyForm()) {
            return false;
    }*/
      var validform = validatemyForm('addGroup');
      shortNameResult = checkShortName('short_name', 'shortNameError');
      alert("shortNameResult"+shortNameResult);
      emailResult = ValidateEmail('add_second_inputTxt', 'bankMailError');
      if (shortNameResult && emailResult) {

          // block();                     
          return validform;
      }
      else {
          return false;
      }

      return false;

  }

  function ValidateAll() {
      var valid = validatemyForm();
      if (ValidateEmail('add_second_inputTxt', 'bankMailError') && validateSaveAndNew('add_first_inputTxt', 'myForm:clientErrorMessage', null, null, 'successAddLockup')) {
          return valid;
      }
      return false;

  }

  function ValidateEmail(inputTextID, errorTextID) {
      var reWhitespace = /^\s+$/;
      if (document.getElementById(inputTextID) != null) {
          inputTextValue = document.getElementById(inputTextID).value;
          if ((inputTextValue == null) || (inputTextValue.length == 0) || short.test(inputTextValue)) {
              document.getElementById(errorTextID).style.display = "none";
              return true;
          }
      }
      else {
          document.getElementById(errorTextID).style.display = "none";
          return true;
      }

      var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      if (inputTextValue.match(mailformat)) {
          document.getElementById(errorTextID).style.display = "none";
          return true;
      }
      else {
          document.getElementById(errorTextID).style.display = "inline";
          document.getElementById(inputTextID).focus();
          return false;
      }
  }

  function checkShortName(inputTextID, errorTextID) {
      if (document.getElementById(inputTextID) != null) {
          shortNameValue = document.getElementById(inputTextID).value;

          if ((shortNameValue != null) && (shortNameValue.length > 0)) {
              var shortNameFormat = /^[A-Za-z]{4}$/;
              if (shortNameValue.match(shortNameFormat)) {
                  document.getElementById(errorTextID).style.display = "none";
                  return true;
              }else {
                  document.getElementById(errorTextID).style.display = "inline";
                  document.getElementById(inputTextID).focus();
                  return false;
              }
          }
                alert("out"+shortNameValue);

          return false;
         
      }
      return false;
  }
</script>
