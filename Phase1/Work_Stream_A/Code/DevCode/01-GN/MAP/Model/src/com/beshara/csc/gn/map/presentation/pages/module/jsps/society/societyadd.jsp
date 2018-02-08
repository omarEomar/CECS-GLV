<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divAddLookup">

    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    
    
        <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
          <t:panelGroup colspan="2" styleClass="center">
                <t:selectOneRadio styleClass="radioButton_DataTable" forceId="true" id="searchTypeRadio"
                          value="#{pageBeanName.selectedRadioValue}" onkeyup="toggleRadioKeyUp(this);"
                          onmousedown="toggleRadio(this);"  disabled="true">
                 <f:selectItem itemLabel="#{resourcesBundle.soc_from}" itemValue="#{pageBeanName.searchByCenterCode}"/>
                 <f:selectItem itemLabel="#{resourcesBundle.soc_system}" itemValue="#{pageBeanName.searchBySystemCode}"/>
                 <%--<a4j:support event="onclick" actionListener="#{pageBeanName.selectedValueChanged}"
                         reRender="systemPGId_pnl,scriptPanelID"/>--%>
                </t:selectOneRadio>
                
             </t:panelGroup>
             
             <t:panelGroup id="systemPGId_pnl" colspan="2">
               <t:panelGrid columns="2"  forceId="true" id="systemPGId" width="100%" cellpadding="3" cellspacing="0" >
                <h:outputText value="#{globalResources.SearchName}" styleClass="lable01" rendered="#{!pageBeanName.showCenterPanel}"/> 
                <t:panelGroup rendered="#{!pageBeanName.showCenterPanel}" >
                    <t:inputText  onfocus="this.select();"  
                    onchange="trimBorders(add_first_inputTxt);" rendered="#{!pageBeanName.showCenterPanel}" forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_first_inputTxt"
                    value="#{pageBeanName.societyName}" styleClass="textboxlarge"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.showCenterPanel}"/>
                     <c2:requiredFieldValidator componentToValidate="add_first_inputTxt"
                                 active="#{!societyAddBean.showCenterPanel}" errorMessage="#{globalResources.missingField}" display="dynamic"/>
                </t:panelGroup> 
                </t:panelGrid>
              
              <h:outputText value="#{globalResources.SearchName}" styleClass="lable01" rendered="#{pageBeanName.showCenterPanel}"/> 
              <t:panelGroup rendered="#{pageBeanName.showCenterPanel}">
                <t:inputText id="minCodeId" value="#{pageBeanName.ministryCode}" forceId="true" 
                    onkeypress="foucsAtSearchInput();enabelIntegerOnlyWithZero(this);filterById(event ,'minCodeId','ministryAdd','inValidCodeId',getMinByCode);" rendered="#{pageBeanName.showCenterPanel}"           
                    styleClass="textbox" style="margin-left: 7px; width:55px;"/>
                <a4j:jsFunction name="getMinByCode" oncomplete="showvalidate_msg('ministryAdd' ,'inValidCodeId' ,'searchMinistryBtn' );" action="#{pageBeanName.getMinById}" id="functionGetMin" reRender="ministryAdd,inValidCodeId ,divAddLookup" />
                
                <t:inputText forceId="true" id="ministryAdd" value="#{pageBeanName.societyName}"                
                             disabled="true" styleClass="textboxlarge" style="width:400px;" rendered="#{pageBeanName.showCenterPanel}"/>
                 <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.showCenterPanel}"/>                            
                <a4j:commandButton value="..." styleClass="cssButtonSmall" type="button"  action="#{minHelperBeanName.openMinistriesDiv}"
                                   reRender="ministryAdd ,minCodeId,lov_dataT_data_panel,lovLinkStatusPanel,javaScriptCallerOutputText"
                                   id="searchMinistryBtn" rendered="#{pageBeanName.showCenterPanel}"/>
                <t:outputText value="#{resourcesBundle.inavlid_code}" id="inValidCodeId" forceId="true" style="display:none;"  styleClass="mandatoryAsterisk"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <c2:requiredFieldValidator componentToValidate="ministryAdd"
                            active="#{societyAddBean.showCenterPanel}" errorMessage="#{globalResources.missingField}" display="dynamic"/>
            </t:panelGroup>
        </t:panelGroup>
        
  </t:panelGrid>      
</t:panelGroup>
    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.addItem}" onclick="settingFoucsAtDivAdd();clearMsgs();return validatemyForm();"/>            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button"  styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}" onclick="clearMsgs();if(validatemyForm()) {saveAndNew();} else {return false;}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveNewItem}" reRender="divAddLookup,OperationBar" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" action="#{pageBeanName.goBack}" onblur="settingFoucsAtDivAdd();"  styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                
        </t:panelGrid>

<script language="javascript" type="text/javascript">
      function clearMsgs() {
        if(document.getElementById('inValidCodeId') != null) {
            document.getElementById('inValidCodeId').innerHTML = "";
        }
        if(document.getElementById('minCodeId') != null) {
            document.getElementById('minCodeId').innerHTML = "";
        }
    }

            function foucsAtSearchInput(){        
                if(document.getElementById('minCodeId') != null){            
                    document.getElementById('minCodeId').focus();
                }
            }
    
   
    
</script>
