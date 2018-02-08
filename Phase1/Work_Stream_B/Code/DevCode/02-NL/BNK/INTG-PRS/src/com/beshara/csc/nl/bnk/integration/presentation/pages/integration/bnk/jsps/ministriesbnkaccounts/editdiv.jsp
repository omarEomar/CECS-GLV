<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<t:panelGrid forceId="true" id="divEditLookup" width="100%" cellpadding="3" cellspacing="0" align="center">
<t:panelGrid columns="4" width="100%" cellpadding="3" cellspacing="0" rowClasses="row02,row01">

       
        <h:outputText value="#{integrationBundle.category}" styleClass="label01"/>
        <t:inputText disabled="true" forceId="true" id="edit_selected_cat_name" value="#{pageBeanName.selectedCatName}"
                     styleClass="textbox" style="width:220px;"/>
      
        <h:outputText value="#{integrationBundle.ministry}" styleClass="label01" style="width:85px;"/>
        <t:inputText disabled="true" forceId="true" id="edit_selected_min_name" value="#{pageBeanName.selectedMiniName}"
                     styleClass="textbox"  style="width:220px;"/>
                     
                     
        <%--h:outputText value="#{integrationBundle.bank_name}" styleClass="label01"/>
        <t:panelGroup colspan="3">
            <t:inputText disabled="true" forceId="true" id="bank_name" value="#{integrationBundle.center_bank_name}"
                         styleClass="textbox" style="width:185px;"/>
        </t:panelGroup--%>

        <h:outputText value="#{integrationBundle.bank_name}" styleClass="label01"/>
         <t:selectOneMenu forceId="true" id="bank_name_combo_edit" value="#{pageBeanName.selectedBankId}" disabled="true"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="bank" itemLabel="#{bank.name}" itemValue="#{bank.code.bankCode}"
                           value="#{pageBeanName.banksList}"/>
        </t:selectOneMenu>
             

        <h:outputText value="#{integrationBundle.branch_name}" styleClass="label01"/>
         <t:selectOneMenu forceId="true" id="bank_branch_name_combo_edit" value="#{pageBeanName.selectedBankBranchId}" disabled="true"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;" >
            <t:selectItems var="bankBranch" itemLabel="#{bankBranch.name}" itemValue="#{bankBranch.code.bnkbranchCode}"
                           value="#{pageBeanName.bankBranchesList}"/>
        </t:selectOneMenu>
     
        <h:outputText value="#{integrationBundle.account_number}" styleClass="label01 nowrap_txt" />
        <t:panelGroup colspan="3">
          <t:inputText forceId="true" disabled="true" id="edit_accountNo9" value="#{pageBeanName.minsAccountNumber[8]}" styleClass="textboxsmall" style="width:47px; text-align:center;"/>                             
          <f:verbatim >&nbsp;</f:verbatim>
          <t:inputText forceId="true" disabled="true" id="edit_accountNo8" value="#{pageBeanName.minsAccountNumber[7]}" styleClass="textboxsmall" style="width:52px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" disabled="true" id="edit_accountNo7"  value="#{pageBeanName.minsAccountNumber[6]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>
          <t:inputText forceId="true" disabled="true" id="edit_accountNo6" value="#{pageBeanName.minsAccountNumber[5]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" disabled="true" id="edit_accountNo5" value="#{pageBeanName.minsAccountNumber[4]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" disabled="true" id="edit_accountNo4" value="#{pageBeanName.minsAccountNumber[3]}" styleClass="textboxsmall" style="width:62px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" disabled="true" id="edit_accountNo3" value="#{pageBeanName.minsAccountNumber[2]}" styleClass="textboxsmall" style="width:63px; text-align:center;"/>
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" disabled="true" id="edit_accountNo2" value="#{pageBeanName.minsAccountNumber[1]}" styleClass="textboxsmall" style="width:47px; text-align:center;"/>                     
          <f:verbatim >&nbsp;</f:verbatim>          
          <t:inputText forceId="true" disabled="true" id="edit_accountNo1" value="#{pageBeanName.minsAccountNumber[0]}"styleClass="textboxsmall" style="width:48px; text-align:center;"/>          
        </t:panelGroup>    
        
        <h:outputText value="#{integrationBundle.account_name}" styleClass="label01 nowrap_txt"/>
        <t:panelGroup>
            <t:inputText onfocus="this.select();" 
                         onchange="trimBorders(accountName);" forceId="true"
                         maxlength="#{pageBeanName.nameMaxLength}" id="edit_accountName"
                         value="#{pageBeanName.selectedPageDTO.accountName}" styleClass="textbox"  style="width:220px;"/>
           
        </t:panelGroup>
        
      
        <h:outputText value="#{integrationBundle.account_type}" styleClass="label01 nowrap_txt"/>
        <t:panelGroup>
         <t:selectOneMenu forceId="true" id="edit_account_purpose_combo" value="#{pageBeanName.bankAccountTypeId}"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="purpose" itemLabel="#{purpose.name}" itemValue="#{purpose.code.bnkacttypeCode}"
                           value="#{pageBeanName.accountPurposesList}"/>
        </t:selectOneMenu>
          <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim ><br/></f:verbatim>      
          <c:compareValidator componentToValidate="edit_account_purpose_combo" componentToCompare="virtualvalueId" operator="not" active="#{ministriesBnkAccountsBean.pageMode==2}"
                            errorMessage="#{integrationBundle.select_one_item}" highlight="false" display="dynamic"/>
        </t:panelGroup>
        
        
        <h:outputText value="#{integrationBundle.account_status}" styleClass="label01"/>
       
         <t:panelGroup>
            <t:selectOneMenu forceId="true" id="edit_account_status_combo" value="#{pageBeanName.selectedPageDTO.status}"
                         styleClass="Dropdownbox" converter="javax.faces.Long"  style="width:220px;">
                <f:selectItem itemLabel="#{integrationBundle.ACCOUNT_VALID_STATUS}" itemValue="#{pageBeanName.statusList[0]}"/>
                <f:selectItem itemLabel="#{integrationBundle.ACCOUNT_NOT_VALID_STATUS}" itemValue="#{pageBeanName.statusList[1]}"/>
                <f:selectItem itemLabel="#{integrationBundle.ACCOUNT_FREEZE_STATUS}" itemValue="#{pageBeanName.statusList[2]}"/>
            </t:selectOneMenu>
        </t:panelGroup>
     
</t:panelGrid>

</t:panelGrid>



    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="EditButton" styleClass="cssButtonSmall" 
                value="#{globalResources.update}" action="#{pageBeanName.edit}" onclick="return validatemyForm();" />            
            
            <h:panelGroup>
                <t:commandButton forceId="true" id="CancelButtonEdit" onblur="settingFoucsAtDivAdd();" onclick="backEditJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backEditJsFunction" action="#{pageBeanName.cancelEdit}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " reRender="scriptGeneratorPanel,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>
