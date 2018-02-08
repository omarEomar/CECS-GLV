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
   
    
        <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row01,row02">
          
                <h:outputText value="#{regResources.columnName}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onfocus="this.select();" onblur="setFocusOnElement('dataTypes');" 
                            onchange="trimBorders(add_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" 
                            id="add_first_inputTxt" value="#{detailBeanName.pageDTO.name}" styleClass="textboxlarge"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
                
                 <h:outputText  value="#{regResources.regulation_References_type}" styleClass="lable01"/> 
                <t:selectOneMenu forceId="true" id="dataTypes" styleClass="Dropdownbox" onchange="clearErrMsgs();" value="#{detailBeanName.pageDTO.destabcolumnType}">
                    <f:selectItem itemValue="#{detailBeanName.STRING_DATATYPE}" itemLabel="#{regResources.str_value}"/>
                    <f:selectItem itemValue="#{detailBeanName.INTEGER_DATATYPE}" itemLabel="#{regResources.int_value}"/>
                </t:selectOneMenu>
        </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>  

        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{detailBeanName.add}" onclick="settingFoucsAtDivAdd();return validateInputText('add_first_inputTxt','myForm:clientErrorMessage',null,null,'successAddLockup');"/>            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="settingFoucsAtDivAdd();validateSaveAndNew('add_first_inputTxt','myForm:clientErrorMessage',null,null,'successAddLockup');" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{detailBeanName.saveAndNew}" reRender="dataT_data_panel,divAddLookup" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{detailBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');setFocusOnlyOnElement('add_first_inputTxt');" reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>
        

</t:panelGroup>