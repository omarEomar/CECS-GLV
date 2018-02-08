<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>



<t:panelGrid columnClasses="center" align="center" width="100%">
    <t:panelGroup>
        <t:outputText value="" forceId="true" id="searchDivErrMsg" styleClass="errMsgNoHeight"/>
        <t:outputLabel id="sErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
    </t:panelGroup>
    <t:panelGrid columns="3" width="100%" rowClasses="row01,row02" columnClasses="conditionSearchCol1,,conditionSearchCol3" cellpadding="3" cellspacing="0" forceId="true" id="searchGridID"  border="0">
     
        <h:outputText id="Condition_Code" value="#{resourcesBundle.Condition_Code}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText onkeyup="enabelIntegerOnly(this);" value="#{conditionListBean.searchConditionsDTO.conditionCode}" styleClass="textboxlarge5" forceId="true" id="conditionCodeID"/>
        </t:panelGroup>
        <h:outputText id="condition_Name" value="#{resourcesBundle.conditionName}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText onkeyup="trimBorders(this);" forceId="true" id="conditionName" value="#{conditionListBean.searchConditionsDTO.conditionName}" styleClass="textboxlarge5"/>
        </t:panelGroup>
        <h:outputText id="status" value="#{resourcesBundle.status}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="conditionStatusId" styleClass="filteration_input_text" value=""
                onkeypress="clearConditionSearchDivMsg();filterationInputOnKeyPress(event,this,'conditionStatus','conditionStatusErrorCodeId',null,'operationsListId');"
                onblur="clearConditionSearchDivMsg();filterationInputOnBlur(event,this,'conditionStatus','conditionStatusErrorCodeId',null,'operationsListId');"
                onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            </t:inputText>
            <t:selectOneMenu id="conditionStatus" forceId="true" styleClass="DropdownboxLarge1" value="#{conditionListBean.conditionStatus}"
                 onchange="clearConditionSearchDivMsg();filterationDropboxOnChange(event,this,'conditionStatusId','conditionStatusErrorCodeId');">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.all}"/>
                <f:selectItem itemValue="#{pageBeanName.activeFlag}" itemLabel="#{resourcesBundle.active}"/>
                <f:selectItem itemValue="#{pageBeanName.pendingFlag}" itemLabel="#{resourcesBundle.pending}"/>
                <f:selectItem itemValue="#{pageBeanName.freezedFlag}" itemLabel="#{resourcesBundle.freezed}"/>
            </t:selectOneMenu>
             <t:outputLabel id="conditionStatusErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
      </t:panelGroup>
       
           
        
        <h:outputText id="operation" value="#{resourcesBundle.operation}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="operationsListId" styleClass="filteration_input_text" value=""
                onkeypress="clearConditionSearchDivMsg();filterationInputOnKeyPress(event,this,'operationsList','operationsListErrorCodeId',null,'parametersId');"
                onblur="clearConditionSearchDivMsg();filterationInputOnBlur(event,this,'operationsList','operationsListErrorCodeId',null,'parametersId');"
                onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            </t:inputText>
            <t:selectOneMenu id="operationsList" forceId="true" styleClass="DropdownboxLarge1" value="#{conditionListBean.selectedOperationId}"
                onchange="clearConditionSearchDivMsg();filterationDropboxOnChange(event,this,'operationsListId','operationsListErrorCodeId');">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.all}"/>
                <t:selectItems value="#{conditionListBean.operationsList}" var="linesList" itemValue="#{linesList.code.key}" itemLabel="#{linesList.name}"/>
            </t:selectOneMenu>
               <t:outputLabel id="operationsListErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
          </t:panelGroup>
       
        <h:outputText id="Condition_Parameters" value="#{resourcesBundle.Condition_Parameters}" styleClass="lable01" />
        <t:panelGroup colspan="2">
        <t:panelGrid columns="2">
         <t:outputText id="seachParameter" value="#{resourcesBundle.search}" styleClass="lable01" />
         <t:inputText forceId="true" id="searchParametersName" 
                             styleClass="textboxlarge"
                             value="#{conditionListBean.parameterSearchName}"
                             onkeypress="goSearchParametersList(event);"/>
       
         <t:inputText forceId="true" id="parametersId" styleClass="filteration_input_text" value=""
                onkeypress="clearConditionSearchDivMsg();filterationInputOnKeyPress(event,this,'parameters','parametersErrorCodeId',changeParametersId,'valuesId');"
                onblur="clearConditionSearchDivMsg();filterationInputOnBlur(event,this,'parameters','parametersErrorCodeId',changeParametersId,'valuesId');"
                onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                <a4j:jsFunction name="changeParametersId" actionListener="#{conditionListBean.selectedParameterChanged}" reRender="searchDivErrMsg,valuesErrorCodeId,lineListErrorCodeId,valuesId,values,lineList,lineListId" 
                oncomplete="clearInputText('valuesId');clearInputText('lineListId');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="parameters" value="#{conditionListBean.parameterCode}" styleClass="DropdownboxLarge1"
                onchange="clearConditionSearchDivMsg();filterationDropboxOnChange(event,this,'parametersId','parametersErrorCodeId',changeParametersVal);">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.all}"/>
                <t:selectItems var="parametersList" itemLabel="#{parametersList.name}" itemValue="#{parametersList.code.key}" value="#{conditionListBean.parametersList}"/>
                <a4j:jsFunction name="changeParametersVal" actionListener="#{conditionListBean.selectedParameterChanged}" reRender="searchDivErrMsg,parametersErrorCodeId,valuesErrorCodeId,lineListErrorCodeId,valuesId,values,lineList,lineListId"
                oncomplete="clearInputText('valuesId');clearInputText('lineListId');"/>
            </t:selectOneMenu> 
         </t:panelGrid>
            <t:outputLabel id="parametersErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
        </t:panelGroup>
        
        <h:outputText id="Condition_LineValues" value="#{resourcesBundle.Condition_LineValues}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="valuesId" styleClass="filteration_input_text" value=""
                onkeypress="clearConditionSearchDivMsg();filterationInputOnKeyPress(event,this,'values','valuesErrorCodeId',changeValuesId,'lineListId');"
                onblur="clearConditionSearchDivMsg();filterationInputOnBlur(event,this,'values','valuesErrorCodeId',changeValuesId,'lineListId');"
                style="margin-left:5px;">
                <a4j:jsFunction name="changeValuesId" actionListener="#{conditionListBean.selectedLineValueChanged}" reRender="searchDivErrMsg,lineListErrorCodeId,lineList,lineListId"
                oncomplete="clearInputText('lineListId');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="values" value="#{conditionListBean.linevalue}" styleClass="DropdownboxLarge1"
                onchange="clearConditionSearchDivMsg();filterationDropboxOnChange(event,this,'valuesId','valuesErrorCodeId',changeValuesVal);">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.all}"/>
                <t:selectItems var="lineValuesList" itemLabel="#{lineValuesList.name}" itemValue="#{lineValuesList.strCode}" value="#{conditionListBean.lineValuesList}"/>
                <a4j:jsFunction name="changeValuesVal"  actionListener="#{conditionListBean.selectedLineValueChanged}" reRender="searchDivErrMsg,valuesErrorCodeId,lineListErrorCodeId,lineList,lineListId"
                oncomplete="clearInputText('lineListId');"/>
            </t:selectOneMenu>
            <t:outputLabel id="valuesErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
        </t:panelGroup>
         
        <h:outputText id="Condition_Line" value="#{resourcesBundle.line}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="lineListId" styleClass="filteration_input_text" value=""
                onkeypress="clearConditionSearchDivMsg();filterationInputOnKeyPress(event,this,'lineList','lineListErrorCodeId');"
                onblur="clearConditionSearchDivMsg();filterationInputOnBlur(event,this,'lineList','lineListErrorCodeId');"
                onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            </t:inputText>
            <t:selectOneMenu id="lineList" forceId="true" styleClass="DropdownboxLarge1" value="#{conditionListBean.lineCodeText}"
                onchange="clearConditionSearchDivMsg();filterationDropboxOnChange(event,this,'lineListId','lineListErrorCodeId');">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.all}"/>
                <t:selectItems value="#{conditionListBean.linesList}" var="linesList" itemValue="#{linesList.code.key}" itemLabel="#{linesList.name}"/>
            </t:selectOneMenu>
            <t:outputLabel id="lineListErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
        </t:panelGroup>
     <!-- modules -->
     <h:outputText id="module" value="#{resourcesBundle.relatedModules}" styleClass="lable01" />
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="modulesListId" styleClass="filteration_input_text" value=""
                onkeypress="clearConditionSearchDivMsg();filterationInputOnKeyPress(event,this,'modulesList','modulesListErrorCodeId');"
                onblur="clearConditionSearchDivMsg();filterationInputOnBlur(event,this,'modulesList','modulesListErrorCodeId');"
                onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            </t:inputText>
            <t:selectOneMenu id="modulesList" forceId="true" styleClass="DropdownboxLarge1" value="#{conditionListBean.moduleCodeText}"
                onchange="clearConditionSearchDivMsg();filterationDropboxOnChange(event,this,'modulesListId','modulesListErrorCodeId');">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.all}"/>
                <t:selectItems value="#{conditionListBean.appModulesList}" var="modulesList" itemValue="#{modulesList.code.key}" itemLabel="#{modulesList.name}"/>
            </t:selectOneMenu>
            <t:outputLabel id="modulesListErrorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
        </t:panelGroup>
     </t:panelGrid>
     
     <t:panelGrid  align="center" columns="2" id="buttonsPanel">
        <t:commandButton forceId="true" id="customSearchOkBtn" value="#{globalResources.SearchButton}" onclick="return validateConditionSearchDiv('#{globalResources.selectOneRadio}');" action="#{conditionListBean.search}" styleClass="cssButtonSmall"/>
        <t:panelGroup>
            <t:commandButton value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="clearConditionSearchDivMsg();hideLookUpDiv(window.blocker,window.divSearch,null,null,null);" type="button"  forceId="true"  id="customSearchBackBtn" onblur="setFocusAtMySearchDiv();" />
        </t:panelGroup>               
    </t:panelGrid>
     <a4j:jsFunction name="searchParametersName" action="#{conditionListBean.searchParameters}"
                     reRender="parameters,parametersErrorCodeId"/>
</t:panelGrid>
<script type="text/javascript"> 

function clearConditionSearchDivMsg(){
    document.getElementById('searchDivErrMsg').innerHTML = '';
}
function validateConditionSearchDiv(msg){
    clearConditionSearchDivMsg();
    var valid = true;
    if(document.getElementById('conditionCodeID').value == '' 
        && document.getElementById('conditionName').value == ''
        && document.getElementById('conditionStatus').value == '-100'
        && document.getElementById('operationsList').value == '-100'
        && document.getElementById('parameters').value == '-100'
        && document.getElementById('values').value == '-100'
        && document.getElementById('lineList').value == '-100'
        && document.getElementById('modulesList').value == '-100'
        ){            
        valid = false;
    }
    if(!valid){
        document.getElementById('searchDivErrMsg').innerHTML = msg;
    }
    return valid;
}

function goSearchParametersList(e) {
      if (e.keyCode == 13) {
          document.getElementById('parametersId').value='';
          document.getElementById('parameters').selectedIndex=0;
          searchParametersName();
          e.preventDefault();
      }
     
  }
 
</script>
