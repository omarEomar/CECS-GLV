<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

        <h:outputText id="clientErrorMessage" styleClass="errMsg" />
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>

<t:panelGrid forceId="true" id="divAddLookup" width="100%" cellpadding="3" cellspacing="0" align="center">
<t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="1" border="0" align="center">
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{pageBeanName.newErrMsgValue}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
</t:panelGrid>

<t:panelGrid columns="4" width="100%" cellpadding="3" cellspacing="0" rowClasses="row02,row01">

       
        <h:outputText value="#{integrationBundle.category}" styleClass="label01"/>
        <t:inputText disabled="true" forceId="true" id="selected_cat_name" value="#{pageBeanName.selectedCatName}"
                     styleClass="textbox" style="width:220px;"/>
      
        <h:outputText value="#{integrationBundle.ministry}" styleClass="label01" style="width:85px;"/>
        <t:inputText disabled="true" forceId="true" id="selected_min_name" value="#{pageBeanName.selectedMiniName}"
                     styleClass="textbox"  style="width:220px;"/>
                     
                     
        <%--h:outputText value="#{integrationBundle.bank_name}" styleClass="label01"/>
        <t:panelGroup colspan="3">
            <t:inputText disabled="true" forceId="true" id="bank_name" value="#{integrationBundle.center_bank_name}"
                         styleClass="textbox" style="width:185px;"/>
        </t:panelGroup--%>

        <h:outputText value="#{integrationBundle.bank_name}" styleClass="label01"/>
        <t:panelGroup>
         <t:selectOneMenu forceId="true" id="bank_name_combo" value="#{pageBeanName.selectedBankId}"
            onchange="changeBank();" disabled="#{pageBeanName.selectedTypeId == pageBeanName.govFlag}"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="bank" itemLabel="#{bank.name}" itemValue="#{bank.code.bankCode}"
                           value="#{pageBeanName.banksList}"/>
                <a4j:jsFunction name="changeBank" actionListener="#{pageBeanName.updateBankBranchesList}"
                            reRender="bank_branch_name_combo,accountNo3"/>
        </t:selectOneMenu>
        
          <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim ><br/></f:verbatim>      
          <c:compareValidator componentToValidate="bank_name_combo" componentToCompare="virtualvalueId" operator="not" active="#{ministriesBnkAccountsBean.pageMode==1}"
                            errorMessage="#{integrationBundle.select_one_item}" highlight="false" display="dynamic"/>
        </t:panelGroup>
        

        <h:outputText value="#{integrationBundle.branch_name}" styleClass="label01"/>
        <t:panelGroup>
         <t:selectOneMenu forceId="true" id="bank_branch_name_combo" value="#{pageBeanName.selectedBankBranchId}"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;" onchange="setFocus('accountNo2');">
            <%--<f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>--%>
            <t:selectItems var="bankBranch" itemLabel="#{bankBranch.name}" itemValue="#{bankBranch.code.bnkbranchCode}"
                           value="#{pageBeanName.bankBranchesList}"/>
        </t:selectOneMenu>
        
          <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim ><br/></f:verbatim>      
          <c:requiredFieldValidator componentToValidate="bank_branch_name_combo" active="#{ministriesBnkAccountsBean.pageMode==1}"
                            errorMessage="#{integrationBundle.select_one_item}" highlight="false" display="dynamic"/>
        </t:panelGroup>

        
        <h:outputText value="#{integrationBundle.account_number}" styleClass="label01 nowrap_txt" />
        <t:panelGroup colspan="3">
          <t:inputText onfocus="this.select();" tabindex="9"
                     onchange="trimBorders(accountNo9);" forceId="true" maxlength="2" id="accountNo9" onkeyup="disableCharacters(this)"
                     value="#{pageBeanName.minsAccountNumber[8]}" styleClass="textboxsmall" style="width:47px; text-align:center;"/>                             
          <f:verbatim >&nbsp;</f:verbatim>
          <t:inputText onfocus="this.select();"  tabindex="8"
                     onchange="trimBorders(accountNo8);" forceId="true" maxlength="4" id="accountNo8" onkeyup="disableCharacters(this);jumpToNextAccNo(this,4,'accountNo9');"
                     value="#{pageBeanName.minsAccountNumber[7]}" styleClass="textboxsmall" style="width:52px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText onfocus="this.select();"  tabindex="7"
                     onchange="trimBorders(accountNo7);" forceId="true" maxlength="4" id="accountNo7"  onkeyup="disableCharacters(this);jumpToNextAccNo(this,4,'accountNo8');"
                     value="#{pageBeanName.minsAccountNumber[6]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>
          <t:inputText onfocus="this.select();"  tabindex="6"
                     onchange="trimBorders(accountNo6);" forceId="true" maxlength="4" id="accountNo6" onkeyup="disableCharacters(this);jumpToNextAccNo(this,4,'accountNo7');"
                     value="#{pageBeanName.minsAccountNumber[5]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText onfocus="this.select();"  tabindex="5"
                     onchange="trimBorders(accountNo5);" forceId="true" maxlength="4" id="accountNo5" onkeyup="disableCharacters(this);jumpToNextAccNo(this,4,'accountNo6');"
                     value="#{pageBeanName.minsAccountNumber[4]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText onfocus="this.select();"  tabindex="4"
                     onchange="trimBorders(accountNo4);" forceId="true" maxlength="4" id="accountNo4" onkeyup="disableCharacters(this);jumpToNextAccNo(this,4,'accountNo5');"
                     value="#{pageBeanName.minsAccountNumber[3]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText onfocus="this.select();"  align="center"
                     onchange="trimBorders(accountNo3);" forceId="true" maxlength="4" id="accountNo3"
                     value="#{pageBeanName.minsAccountNumber[2]}" disabled="true" styleClass="textboxsmall" style="width:63px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText onfocus="this.select();"  tabindex="3"
                     onchange="trimBorders(accountNo2);" forceId="true" maxlength="2" id="accountNo2" onkeyup="disableCharacters(this);jumpToNextAccNo(this,2,'accountNo4');"
                     value="#{pageBeanName.minsAccountNumber[1]}" styleClass="textboxsmall" style="width:47px; text-align:center;"/>                     
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" maxlength="2" id="accountNo1"
                     value="#{pageBeanName.minsAccountNumber[0]}" disabled="true" styleClass="textboxsmall" style="width:48px; text-align:center;"/>          
           <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim ><br/></f:verbatim>            
            <%--<c:requiredFieldValidator componentToValidate="accountNo1" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{ministriesBnkAccountsBean.pageMode==1}"/>--%>
            <t:outputText id="accountNoError" forceId="true" value="#{integrationBundle.account_number_incompelete}" styleClass="errMsg" style="display:none;"/>    
        </t:panelGroup>            
        <%-- onkeypress="FireButtonClick('SaveButton')"--%>
        <h:outputText value="#{integrationBundle.account_name}" styleClass="label01 nowrap_txt"/>
        <t:panelGroup>
            <t:inputText onfocus="this.select();" 
                         onchange="trimBorders(accountName);" forceId="true"
                         maxlength="#{pageBeanName.nameMaxLength}" id="accountName"
                         value="#{pageBeanName.pageDTO.accountName}" styleClass="textbox"  style="width:220px;"/>
           
        </t:panelGroup>
        
      
        <h:outputText value="#{integrationBundle.account_type}" styleClass="label01 nowrap_txt"/>
        <t:panelGroup>
         <t:selectOneMenu forceId="true" id="account_purpose_combo" value="#{pageBeanName.selectedPurposeId}"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="purpose" itemLabel="#{purpose.name}" itemValue="#{purpose.code.bnkacttypeCode}"
                           value="#{pageBeanName.accountPurposesList}"/>
        </t:selectOneMenu>
          <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim ><br/></f:verbatim>      
          <c:compareValidator componentToValidate="account_purpose_combo" componentToCompare="virtualvalueId" operator="not" active="#{ministriesBnkAccountsBean.pageMode==1}"
                            errorMessage="#{integrationBundle.select_one_item}" highlight="false" display="dynamic"/>
        </t:panelGroup>
        
        
        <h:outputText value="#{integrationBundle.account_status}" styleClass="label01"/>
       
         <t:panelGroup>
            <t:selectOneMenu forceId="true" id="account_status_combo" value="#{pageBeanName.pageDTO.status}"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;">
                <f:selectItem itemLabel="#{integrationBundle.ACCOUNT_VALID_STATUS}" itemValue="#{pageBeanName.statusList[0]}"/>
                <f:selectItem itemLabel="#{integrationBundle.ACCOUNT_NOT_VALID_STATUS}" itemValue="#{pageBeanName.statusList[1]}"/>
                <f:selectItem itemLabel="#{integrationBundle.ACCOUNT_FREEZE_STATUS}" itemValue="#{pageBeanName.statusList[2]}"/>
            </t:selectOneMenu>
          <%--<t:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
        </t:panelGroup>
     
</t:panelGrid>

</t:panelGrid>



    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" 
                value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="return validateAccountNumber();" />            
            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="if(validateAccountNumber()==true){saveAndNew();}else{return false;}" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}">                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,OperationBar" oncomplete="settingFoucsAtDivAdd();"/>
               </t:commandButton>
            </h:panelGroup>
            
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " reRender="scriptGeneratorPanel,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>

<t:saveState value="#{pageBeanName.selectedTypeId}"/>
<script language="javascript" type="text/javascript">
  function clearMsgs() {
      if (document.getElementById('myForm:error') != null) {
          document.getElementById('myForm:error').innerHTML = "";
      }

      if (document.getElementById('myForm:clientErrorMessage') != null) {
          document.getElementById('myForm:clientErrorMessage').innerHTML = "";
      }

      if (document.getElementById('successAddLockup') != null) {
          document.getElementById('successAddLockup').innerHTML = "";
      }
  }

  function validateAccountNumber() {
    var controlName = "accountNo";
    var reWhitespace = /^\s+$/;
    
    if(!validatemyForm())
        return false;
    
    for(i=1;i<=9;i++){
        elementName = controlName + i.toString();
        if(document.getElementById(elementName) == null){
            document.getElementById('accountNoError').style.display = "inline";
            return false;
        }else{
            accountPart = document.getElementById(elementName).value;
            if((accountPart == null) || (accountPart.length == 0) || reWhitespace.test(accountPart)){
                document.getElementById('accountNoError').style.display = "inline";
                document.getElementById(elementName).focus();
                return false;
            }
        }
    }
    document.getElementById('accountNoError').style.display = "none";
    return true;
  }
      function jumpToNextAccNo(currAccNo,digitsNo,nextAccNoId) {
        //alert(currAccNo);
        var currAccNoValue = currAccNo.value;
        if(digitsNo == currAccNoValue.length){
            var nextAccNoInput = document.getElementById(nextAccNoId);
            nextAccNoInput.focus();
        }
    }
</script>
