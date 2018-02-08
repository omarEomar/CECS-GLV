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
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
   
    
        <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row01,row02">
        
                <h:outputText value="#{regResources.regulation_subjects_symbol}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText value="#{detailBeanName.preEditDTO.code.keys[4] > 0 ? detailBeanName.preEditDTO.code.keys[4] : regResources.unDefineLabel}" styleClass="textbox" disabled="true"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
          
                <h:outputText value="#{regResources.columnName}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onblur="setFocusOnElement('dataTypesEdit');" 
                            onchange="trimBorders(edit_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" 
                            id="edit_first_inputTxt" value="#{detailBeanName.preEditDTO.name}" styleClass="textboxlarge"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
                
                 <h:outputText value="#{regResources.regulation_References_type}" styleClass="lable01"/> 
                <t:selectOneMenu forceId="true" id="dataTypesEdit" styleClass="Dropdownbox" onchange="clearErrMsgs();" value="#{detailBeanName.preEditDTO.destabcolumnType}">
                    <f:selectItem itemValue="#{detailBeanName.STRING_DATATYPE}" itemLabel="#{regResources.str_value}"/>
                    <f:selectItem itemValue="#{detailBeanName.INTEGER_DATATYPE}" itemLabel="#{regResources.int_value}"/>
                </t:selectOneMenu>
        </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>  

        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" onclick="return validateInputText('edit_first_inputTxt','myForm:clientErrorMessageEdit',null,null,null);" action="#{detailBeanName.EditChange}"/>
            <h:panelGroup>
                <t:commandButton forceId="true" id="CancelButtonEdit" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('edit_first_inputTxt');"/>
                <a4j:jsFunction name="backJsFunction"   oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit','edit');" reRender="divEditLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/><%-- action="#{detailBeanName.cancelEdit}" --%>
            </h:panelGroup>
        </t:panelGrid>
        

</t:panelGroup>