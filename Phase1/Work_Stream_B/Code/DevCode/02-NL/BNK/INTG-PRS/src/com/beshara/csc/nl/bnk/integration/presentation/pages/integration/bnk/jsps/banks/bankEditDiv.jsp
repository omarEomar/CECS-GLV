<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>  
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
   
     <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>                     
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>                     
        
            <h:outputText value="#{integrationBundle.banks_name_label}" styleClass="lable01" />
            <t:panelGroup>
                <t:inputText  onkeypress="FireButtonClick('SaveButtonEdit')" 
                    onchange="trimBorders(edit_first_inputTxt);" readonly="true"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" 
                    id="edit_first_inputTxt" value="#{pageBeanName.selectedPageDTO.name}" styleClass="textboxmedium"/>                               
                <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
                 <%--<f:verbatim><br/></f:verbatim>--%>
                <%--<h:outputText id="error1" value="#{integrationBundle[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>--%>
                <%--<c:requiredFieldValidator group="editGroup" componentToValidate="edit_first_inputTxt"  display="dynamic" 
                    errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="editfirst_inputTxt"/>--%>

                <%--<h:outputText id="clientErrorMessage1" styleClass="errMsg" />--%>            
            </t:panelGroup>
            <h:outputText value="#{integrationBundle.short_bnk_name}" styleClass="lable01"/> 
          
        <t:panelGroup>
           <t:inputText  forceId="true" id="edit_short_name" maxlength="4" value="#{pageBeanName.selectedPageDTO.shortName}" styleClass="textboxsmall"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>&nbsp;</f:verbatim> 
                <c:requiredFieldValidator  group ="editGroup" componentToValidate="edit_short_name"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="EditShortName"/>        
                 <t:outputText id="editShortNameError" forceId="true" value="#{integrationBundle.shortNameErrorMsg}" styleClass="errMsg" style="display:none;"/>    
        </t:panelGroup>
       <h:outputText value="#{integrationBundle.bank_email}" styleClass="lable01"/> 
       <t:panelGroup>
            <t:inputText id="edit_second_inputTxt" readonly="true" onchange="trimBorders(edit_second_inputTxt);"  value="#{pageBeanName.selectedPageDTO.email}" styleClass="textboxmedium" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" onfocus="this.select();"/>
            
            <%--<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>--%>
            <%--<t:outputText id="bankMailExample1" forceId="true" value="#{integrationBundle.emailFormat}" styleClass="warningClass_cust1"/>--%>
            <%--<f:verbatim><br/></f:verbatim>--%>
            <%--<t:outputText id="bankMailError1" forceId="true" value="#{integrationBundle.emailErrorMsg}" styleClass="errMsg" style="display:none;"/>--%>    
         </t:panelGroup> 
       
        </h:panelGrid>        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" 
                action="#{pageBeanName.edit}" onclick="clearMsgs(); return validateInputTextAndBlock1();"/>            
            <%--t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" /--%>
            <%-- TODO locking code: we converted this button to be ajax to call the unlock method --%>
            <t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();" styleClass="cssButtonSmall" value="#{globalResources.back}">
                <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" reRender="msgShow"/>
            </t:commandButton>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>

<script language="javascript" type="text/javascript">
  function validateInputTextAndBlock1() {

      var validform = validatemyForm('editGroup');

      if (validform) {

          var shortNameResult = checkShortName('edit_short_name', 'editShortNameError');

          //  var  emailResult = ValidateEmail('edit_second_inputTxt', 'bankMailError1');
          //if (shortNameResult && emailResult) {
          if (shortNameResult) {
              return true;
          } else {
              return false;
          }
      }else {
          return false;
      }

  }
</script>


