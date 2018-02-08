<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divAddLookup">

    <t:panelGroup rendered="#{detailBeanName.addDivShown}">
        
        <t:saveState value="#{pageBeanName.success}"/>
        
        <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{detailBeanName.success}"/>
        <t:outputText forceId="true" id="errorTxt" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{detailBeanName.showErrorMsg}"/>
        <htm:br rendered="#{detailBeanName.success || detailBeanName.showErrorMsg}"/>
        <t:outputText id="clientErrorMessage" styleClass="errMsg"/>
        
        <t:panelGrid columns="2" border="0" rowClasses="row01,row02"  width="100%" cellpadding="3" cellspacing="0" >
          
                <t:outputText value="#{regResources.SubjectName}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onblur="document.getElementById('regmaterialText').focus();" onchange="trimBorders(add_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_first_inputTxt" styleClass="textboxlarge" value="#{detailBeanName.pageDTO.regmaterialHeader}"/>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <htm:br/> 
                    <c2:requiredFieldValidator componentToValidate="add_first_inputTxt" highlight="false" display="dynamic" errorMessage="#{globalResources.missingField}" active="#{regulationMaterialsMaintainBean.addDivShown}"/>
                </t:panelGroup>

                    
                
                
                <t:outputText value="#{regResources.SubjectTextArea}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputTextarea  value="#{detailBeanName.pageDTO.regmaterialText}" forceId="true"  id="regmaterialText" rows="4" cols="32"/>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <htm:br/> 
                    <c2:requiredFieldValidator componentToValidate="regmaterialText" highlight="false" display="dynamic" errorMessage="#{globalResources.missingField}" active="#{regulationMaterialsMaintainBean.addDivShown}"/>
                </t:panelGroup>
        </t:panelGrid>
        
        <t:outputLabel  style="font-size: 4pt;height:3px"/>  

        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{detailBeanName.add}" onclick="clearMsgs(); return validatemyForm();"/>
            <t:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button"  onclick="clearMsgs();validateSaveAndNewClientValidator();" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{detailBeanName.saveAndNew}" reRender="divAddLookup,dataT_data_panel,scriptPanel" oncomplete="settingFoucsAtDivAdd();"/>
            </t:panelGroup>
            <t:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{detailBeanName.hideAddDiv}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','errorTxt','add');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </t:panelGroup>
        </t:panelGrid>
            
    </t:panelGroup>

</t:panelGroup>

<script type="text/javascript">
    
    function clearMsgs() {
        if(document.getElementById('successAddLockup') != null) {
            document.getElementById('successAddLockup').innerHTML = '';
        }
        if(document.getElementById('errorTxt') != null) {
            document.getElementById('errorTxt').innerHTML = '';
        }
    }
</script>