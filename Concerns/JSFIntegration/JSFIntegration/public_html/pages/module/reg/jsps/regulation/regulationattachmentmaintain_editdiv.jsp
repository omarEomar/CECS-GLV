<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup">

    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successEditLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg"/>
   
    
        <t:panelGrid columns="2" border="0"   rowClasses="row01,row02"   width="100%" cellpadding="3" cellspacing="0" >
          
          <t:outputText value="#{globalResources.Code}" styleClass="lable01"/> 
               <t:panelGroup>
                    <t:inputText id="edit_code_id" forceId="true" value="#{detailBeanName.preEditDTO.code.keys[3] > 0 ? detailBeanName.preEditDTO.code.keys[3] : regResources.unDefineLabel }" styleClass="textboxsmall" disabled="true"/>
                 <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="false"/>
                </t:panelGroup>
          
          
                <t:outputText value="#{regResources.tableName}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onfocus="this.select();" onblur="document.getElementById('SaveButton').focus();" 
                            onchange="trimBorders(edit_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" 
                            id="edit_first_inputTxt" styleClass="textboxlarge"
                            value="#{detailBeanName.preEditDTO.name}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
        </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>  

        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" onclick="return validateInputText('edit_first_inputTxt','myForm:clientErrorMessageEdit',null,null,null);" action="#{detailBeanName.EditChange}"/>
            
            <h:panelGroup>
                <t:commandButton forceId="true" id="CancelButtonEdit" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction"   oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit','edit');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/><%-- action="#{detailBeanName.cancelEdit}" --%>
            </h:panelGroup>
        </t:panelGrid>
        

</t:panelGroup>