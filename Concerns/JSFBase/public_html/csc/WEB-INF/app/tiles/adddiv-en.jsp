<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divAddLookup">

    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
   
    
        <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
          
                <h:outputText value="#{globalResources.SearchName}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onkeypress="FireButtonClick('SaveButton')" onfocus="this.select();" onblur="document.getElementById('add_second_inputTxt').focus();" onchange="trimBorders(add_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_first_inputTxt" value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
                
                <h:outputText value="#{globalResources.englishName}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onkeypress="FireButtonClick('SaveButton')" onfocus="this.select();" onblur="document.getElementById('SaveButton').focus();" onchange="trimBorders(add_second_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_second_inputTxt" value="#{pageBeanName.pageDTO.engName}" styleClass="textboxlarge" dir="ltr"/>
                    <h:outputText value="  " styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
        </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>  
</t:panelGroup>
    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="settingFoucsAtDivAdd();return validateInputTextAndBlock();"/>            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="clearMsgs();settingFoucsAtDivAdd();validateSaveAndNew('add_first_inputTxt','myForm:clientErrorMessage',null,null,'successAddLockup');" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,OperationBar" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>

<script language="javascript" type="text/javascript">
    function clearMsgs() {
        if(document.getElementById('myForm:error') != null) {
            document.getElementById('myForm:error').innerHTML = "";
        }
        
        if(document.getElementById('myForm:clientErrorMessage') != null) {
            document.getElementById('myForm:clientErrorMessage').innerHTML = "";
        }
        
        if(document.getElementById('successAddLockup') != null) {
            document.getElementById('successAddLockup').innerHTML = "";
        }
    }
    
    function validateInputTextAndBlock()
    {
        if(validateInputText('add_first_inputTxt','myForm:clientErrorMessage',null,null,'successAddLockup')== true)
        {
        block();
        return true;
        }
        else
        {
        return false;
        }
    }
    
</script>
