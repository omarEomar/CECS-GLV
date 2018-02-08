<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %> 
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<tiles:importAttribute scope="request"/>


<t:panelGroup id="treeAddDiv"  forceId="true">
    <t:saveState value="#{pageBeanName.parentLevel}"/>
    <t:saveState value="#{pageBeanName.parentName}"/>
    <t:saveState value="#{pageBeanName.success}"/>
    
    <t:outputText id="successMsgTreeAdd" forceId="true" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
    <t:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br id="brMsgTreeAdd" rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <f:verbatim><span id="msg" class="errMsg"/></f:verbatim>
   
    <t:panelGrid columns="2" border="0" width="100%" columnClasses="col1,col2" rowClasses="row02,row01,row02" cellpadding="3" cellspacing="0">
        <t:outputLabel value="#{resourcesBundle[name]}" id="outputLabelLevelName"  styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" onchange="trimBorders(add_first_inputTxt);" id="add_first_inputTxt" value="#{pageBeanName.levelName}"  maxlength="#{pageBeanName.nameMaxLength}" styleClass="textboxtreelarge" tabindex="1" onblur="setFocusOnlyOnElement('checkBoxLeaf');"/>
            <h:outputLabel value="*" styleClass="mandatoryAsterisk" />        
        </t:panelGroup>
        
        <t:outputLabel value="#{resourcesBundle[parent]}"  styleClass="lable01"/>        
        <t:inputText forceId="true" value="#{pageBeanName.parentName}" styleClass="textboxmedium" disabled="true"  onblur="setFocusOnElement('add_first_inputTxt');"/>
      
        <t:outputLabel value="#{resourcesBundle[leaf]}" id="outputLabelLeaf"  styleClass="lable01"/>
        <t:selectBooleanCheckbox forceId="true" value="#{pageBeanName.booleanLeafFlag}"  id="checkBoxLeaf" tabindex="2"/>
        
      </t:panelGrid>
      
     <t:outputLabel  style="font-size: 4pt;height:3px"/>
     <t:panelGrid columns="3" border="0" align="center" >
        <t:commandButton forceId="true" id="saveButtonTreeAddDiv" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.addNew}" onclick="return validateInputTextAndBlock();" tabindex="3"/>
        <h:panelGroup>
            <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="settingFoucsAtDivAdd();validateSaveAndNew('add_first_inputTxt','msg',null,null,'successMsgTreeAdd');" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}" tabindex="4"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.addAndNew}" reRender="treeAddDiv" oncomplete="settingFoucsAtDivAdd();"/>
        </h:panelGroup>
        
        <h:panelGroup>
            <t:commandButton onblur="settingFoucsAtDivAdd();" forceId="true" id="backButtonTreeAddDiv" onclick="backJsFunctionTreeAddDiv(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}" tabindex="5"/>
            <a4j:jsFunction name="backJsFunctionTreeAddDiv" action="#{pageBeanName.cancelAddTree}" oncomplete="hidTreeDiv('add',window.blocker,window.divTreeAdd,'successMsgTreeAdd');" reRender="treeAddDiv,treeList,noOfRecords,listSize,OperationBar"/>
        </h:panelGroup>
    
    </t:panelGrid>
</t:panelGroup>

<script language="javascript" type="text/javascript">
      function validateInputTextAndBlock()
        {
            if(validateInputText('add_first_inputTxt','msg',null,null,'successMsgTreeAdd','errorMsgTreeAdd')== true)
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
