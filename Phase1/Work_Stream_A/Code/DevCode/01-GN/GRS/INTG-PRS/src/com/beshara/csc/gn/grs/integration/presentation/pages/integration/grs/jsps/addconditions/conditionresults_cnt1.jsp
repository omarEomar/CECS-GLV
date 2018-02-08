<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
 
 .dataTCivilExcepand{
    display: block;
    overflow: auto;
    height: 285px !important;
 }
 .dataTCollapse{
  display: block;
    overflow: auto;
  height: 402px !important;
 }
 .dataTBulkExcepand{
    display: block;
    overflow: auto;
 height: 190px !important;
 }
 
 .dataT-With-6-row-filter-and-collapse {
    height: 190px !important;
}
 
</htm:style>
<t:inputHidden value="#{pageBeanName.resultTableStyleClass}" forceId="true" id="pageStyleClass"/>
<t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingMaxHeight('hideDivImg', 'searchingPnl', 'resultsPnl',document.getElementById('pageStyleClass').value,'dataTCollapse')"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;text-align:center;">
    <t:outputLabel id="caterrorCodeId" value="#{resourcesBundle.CatMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="minerrorCodeId" value="#{resourcesBundle.MinMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="cadererrorCodeId" value="#{resourcesBundle.caderMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="grouperrorCodeId" value="#{resourcesBundle.groupMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="degreeerrorCodeId" value="#{resourcesBundle.degreeMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="bptprogramerrorCodeId" value="#{resourcesBundle.bgtProgramMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="bgttypeerrorCodeId" value="#{resourcesBundle.bgttypeMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="conditionerrorCodeId" value="#{resourcesBundle.conditionMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <h:outputText value="" id="conditionResultsErrMsg" styleClass="errMsg"/>
</t:panelGroup>
<t:panelGrid forceId="true" id="searchingPnl" columns="1" align="right" width="100%" cellpadding="0" cellspacing="0"
             border="0" rowClasses="row01 center,row02 center" onkeypress="FireButtonClickOldStyle(event,'searchBtn');">
    <t:panelGrid  columns="2"  width="100%" cellpadding="0" cellspacing="0" border="0"  rowClasses="center">
    <t:panelGroup colspan="1" styleClass="center">
        <t:selectOneRadio styleClass="radioButton_DataTable" forceId="true" id="searchTypeRadio"
                          value="#{pageBeanName.searchTypeFlag}" onkeyup="toggleRadioKeyUp(this);"
                          onmousedown="toggleRadio(this);" disabled="#{conditionIntgResultsBean.searchMode}">
            <f:selectItem itemLabel="#{resourcesBundle.searchByCivilIdFlag}"
                          itemValue="#{pageBeanName.searchByCivilIdFlag}"/>
            <f:selectItem itemLabel="#{resourcesBundle.searchByBulkFlag}" itemValue="#{pageBeanName.searchByBulkFlag}"/>
            <a4j:support event="onclick" actionListener="#{pageBeanName.searchTypeChanged}"
                         reRender="errorCodePanelId,pageStyleClass,searchingPnl,resultsPnl,paging_panel"/>
        </t:selectOneRadio>
   
    </t:panelGroup>     
   
    
    
    </t:panelGrid>
    <t:panelGrid rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByCivilIdFlag}"
                 columnClasses="conditionResultsCivilCol1,,,conditionResultsCivilCol4" columns="4" align="center"
                  cellpadding="3" cellspacing="0" border="0" rowClasses="row02">
        <t:panelGroup/>
        <h:outputText id="civilID" value="#{globalResources.globalCivilId}" styleClass="lable01"/>
        <t:panelGroup colspan="1">
            <t:inputText onkeyup="enabelIntegerOnly(this);" forceId="true" id="civil_div_searchText" maxlength="12"
                         onkeypress="clickSearchBtn();" value="#{pageBeanName.civilId}" styleClass="textboxmedium"
                         disabled="#{conditionIntgResultsBean.searchMode}"></t:inputText>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <t:panelGroup colspan="1">
            <h:outputText value="" id="civilIdErrMsg" styleClass="errMsg"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}" columns="6" align="right"
                 width="100%" cellpadding="3" cellspacing="0" border="0" rowClasses="row02,row01">
         <t:panelGroup colspan="6" >
          <t:outputLabel value="#{resourcesBundle.conditionName} :" styleClass="lable01" />
          <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
          <t:outputText value="#{conditionIntgResultsBean.conditionName}" styleClass="lable01" />
         </t:panelGroup>
        
        <t:outputLabel value="#{resourcesBundle.category}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'categoriesList','caterrorCodeId',changecategoryVal,'ministryId');"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'categoriesList','caterrorCodeId',changecategoryVal,'ministryId');"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changecategoryVal" actionListener="#{pageBeanName.selectedCategoryChanged}"
                                oncomplete="clearFilterationInput('ministryId');"
                                reRender="selectedWorkCenter,ministriesList,categoriesList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="categoriesList" value="#{pageBeanName.selectedCategoryId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'categoryId','caterrorCodeId',changeCatValD);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.categoriesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeCatValD" actionListener="#{pageBeanName.selectedCategoryChanged}"
                                oncomplete="clearFilterationInput('ministryId');" reRender="searchingPnl"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.ministry}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="ministryId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'ministriesList','minerrorCodeId',changeministryVal);tabOrder(event,document.getElementById('selectedMinistryId').value);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'ministriesList','minerrorCodeId',changeministryVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changeministryVal" actionListener="#{pageBeanName.selectedMinistryChanged}"
                                oncomplete="setFocusOnElement('workCenterBtn');"
                                reRender="selectedWorkCenter,searchingPnl,ministriesList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="ministriesList" value="#{pageBeanName.selectedMinistryId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onblur="tabOrder(event,document.getElementById('selectedMinistryId').value);"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'ministryId','minerrorCodeId',changeMinValD);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.ministriesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeMinValD" actionListener="#{pageBeanName.selectedMinistryChanged}"
                                reRender="searchingPnl,bgtProgramsList"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.workCenter}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="selectedWorkCenter">
            <t:inputText forceId="true" id="selectedWorkCenterName" value="#{pageBeanName.selectedWorkCenterName}"
                         onmouseout="hideTip()" onmouseover="displayTooltip(event,this)" styleClass="textboxmediumB"
                         style="width:145px" readonly="true"/>
            
            <t:inputHidden forceId="true" id="selectedWorkCenterId" value="#{pageBeanName.selectedWorkCenterId}"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:commandButton disabled="#{pageBeanName.selectedMinistryId == pageBeanName.virtualConstValue || pageBeanName.searchMode}"
                             value="..." action="#{wcIntegrationBeanName.openIntDiv}" styleClass="cssButtonSmall"
                             style="min-width: 35px !important;height:23px" id="workCenterBtn" forceId="true"/>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.cader}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="caderId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'caderList','cadererrorCodeId',changecaderVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'caderList','cadererrorCodeId',changecaderVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changecaderVal" actionListener="#{pageBeanName.selectedCaderChanged}"
                                oncomplete="clearFilterationInput('groupId');clearFilterationInput('degreeId');"
                                reRender="searchingPnl,changecaderVal,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="caderList" value="#{pageBeanName.selectedCaderId}" style="width:155px;"
                             styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'caderId','cadererrorCodeId',changeCaderValD);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.caderList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeCaderValD" actionListener="#{pageBeanName.selectedCaderChanged}"
                                oncomplete="clearFilterationInput('groupId');clearFilterationInput('degreeId');"
                                reRender="searchingPnl"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.group}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="groupId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'groupsList','grouperrorCodeId',changegroupVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'groupsList','grouperrorCodeId',changegroupVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changegroupVal" actionListener="#{pageBeanName.selectedGroupChanged}"
                                oncomplete="clearFilterationInput('degreeId');"
                                reRender="searchingPnl,groupsList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="groupsList" value="#{pageBeanName.selectedGroupId}" style="width:155px;"
                             styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'groupId','grouperrorCodeId',changeGroupValD);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.groupsList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeGroupValD" actionListener="#{pageBeanName.selectedGroupChanged}"
                                oncomplete="clearFilterationInput('degreeId');" reRender="searchingPnl"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.degree}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="degreeId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'degreesList','degreeerrorCodeId',changedegreeVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'degreesList','degreeerrorCodeId',changedegreeVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changedegreeVal" oncomplete="setFocusOnElement('searchBtn');"
                                reRender="searchingPnl,degreesList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="degreesList" value="#{pageBeanName.selectedDegreeId}"
                             style="width:156px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'degreeId','degreeerrorCodeId',null);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.degreesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <%-- a4j:support event="onchange" actionListener="#{pageBeanName.selectedDegreeChanged}"
                     reRender="searchingPnl"/--%>
            </t:selectOneMenu>
        </t:panelGroup>
        <%-- h:outputLabel value="#{resourcesBundle.raise}" styleClass="lable01"/> <t:panelGroup> <t:selectOneMenu
             forceId="true" id="raisesList" value="#{pageBeanName.selectedRaiseId}" styleClass="Dropdownboxmedium2"
             onmouseout="hideTip()" onmouseover="displayTooltip(event,this)"> <f:selectItem
             itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/> <t:selectItems
             value="#{pageBeanName.raisesList}" var="item" itemValue="#{item.code.key}" itemLabel="#{item.name}"/>
             </t:selectOneMenu> </t:panelGroup--%>
        <h:outputLabel value="#{resourcesBundle.bgtProgram}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="bgtProgramId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'bgtProgramsList','bptprogramerrorCodeId',changebgtProgramVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'bgtProgramsList','bptprogramerrorCodeId',changebgtProgramVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changebgtProgramVal" oncomplete="setFocusOnElement('bgtTypeId');"
                                reRender="searchingPnl,bgtProgramsList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="bgtProgramsList" value="#{pageBeanName.selectedBgtProgramId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'bgtProgramId','bptprogramerrorCodeId',null);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.bgtProgramsList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.bgtType}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="bgtTypeId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'bgtTypesList','bgttypeerrorCodeId',changebgtTypeVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'bgtTypesList','bgttypeerrorCodeId',changebgtTypeVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changebgtTypeVal" oncomplete="setFocusOnElement('searchBtn');"
                                reRender="searchingPnl,bgtProgramsList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="bgtTypesList" value="#{pageBeanName.selectedBgtTypeId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'bgtTypeId','bgttypeerrorCodeId',null);"
                             disabled="#{conditionIntgResultsBean.searchMode}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.bgtTypesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.conditions}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="conditionId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'condList','conditionerrorCodeId',changeConditionVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'condList','conditionerrorCodeId',changeConditionVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{conditionIntgResultsBean.searchMode}">
                <a4j:jsFunction name="changeConditionVal" oncomplete="setFocusOnElement('searchBtn');"
                                reRender="searchingPnl,bgtProgramsList,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="condList" value="#{pageBeanName.selectedCondId}" style="width:155px;"
                             styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             disabled="#{conditionIntgResultsBean.searchMode}"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'conditionId','conditionerrorCodeId',null);">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.condtionList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup>
      <t:outputText value="#{resourcesBundle.viewResults}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="abilityTypeListAdd" value="#{pageBeanName.filterBy}" 
            styleClass="textboxlarge" disabled="#{conditionIntgResultsBean.searchMode}" style="margin-right:20px">
                <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <f:selectItem itemLabel="#{resourcesBundle.passCondition}" itemValue="#{pageBeanName.passConditionValue}" />
                <f:selectItem itemLabel="#{resourcesBundle.unpassCondition}" itemValue="#{pageBeanName.nonPassConditionValue}"/>
                <%--<a4j:support action="#{detailBeanName.filterTable}" reRender="dataT_data_panel,dataT_data" event="onchange" />--%> 
            </t:selectOneMenu>
            </t:panelGroup>
    <t:panelGroup colspan="1" style="display: block;background-color:#ffffff;">
        <t:panelGrid width="100%" columns="1" columnClasses="conditionResultsErrMgs," style="text-align:center;">
            <t:panelGroup id="searchBtnsPnl" forceId="true">
                <t:commandButton forceId="true" id="searchBtn" type="button" value="#{globalResources.SearchButton}"
                                 styleClass="cssButtonSmall" rendered="#{!conditionIntgResultsBean.searchMode}"
                                 disabled="#{pageBeanName.selectedMinistryId == '-100' && (!(pageBeanName.searchTypeFlag == pageBeanName.searchByCivilIdFlag ))}"
                                 onclick="validateMySearchCriteria('#{globalResources.missingField}','#{globalResources.civil_no_not_less_than_12}','#{globalResources.civiliderror}','#{resourcesBundle.conditionResultsCriteriaMsg}');"/>
                <a4j:jsFunction name="searchJS" action="#{pageBeanName.search}"
                                reRender="dataT_data_panel,paging_panel,cancelSearchBtn,searchBtnsPnl,searchingPnl,abilityTypeListAdd"/>
                <a4j:commandButton id="cancelSearchBtn" value="#{globalResources.cancelsearch}"
                                   action="#{pageBeanName.cancelSearch}" rendered="#{conditionIntgResultsBean.searchMode}"
                                   oncomplete="clearInputs();" styleClass="cssButtonSmall"
                                   reRender="searchBtnsPnl,searchBtn,resultsPnl,paging_panel,searchingPnl,abilityTypeListAdd"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  function clickSearchBtn() {
      document.getElementById("searchBtn").disabled = false;
      FireButtonClick("searchBtn");
  }

  function clearFilterationInput(inputtextId) {
      var inputtext = document.getElementById(inputtextId);
      inputtext.value = '';
  }

  function clearInputs() {
      clearFilterationInput('categoryId');
      clearFilterationInput('ministryId');
      clearFilterationInput('caderId');
      clearFilterationInput('groupId');
      clearFilterationInput('degreeId');
      clearFilterationInput('bgtProgramId');
      clearFilterationInput('bgtTypeId');
      clearFilterationInput('conditionId');
  }

  function validateMySearchCriteria(emptyCivilMsg, civilIdLengthMsg, civilErrMsg, emptySearchErrMsg) {
      clearMyValidationErrMsg();

      var valid = true;
      var searchTypeFlag = getRadioValue('searchTypeRadio');
      if (searchTypeFlag == '1') {
          var s_civilId = getValue('civil_div_searchText');
          if (s_civilId == '') {
              showMyCivilIdErrMsg(emptyCivilMsg);
              valid = false;
          }
          else {
              if (s_civilId.length != 12) {
                  showMyCivilIdErrMsg(civilIdLengthMsg);
                  valid = false;
              }
              else {
                  valid = isValidCivilId('civil_div_searchText');
                  if (!valid) {
                      showMyCivilIdErrMsg(civilErrMsg);
                  }
              }
          }

      }
      else if (searchTypeFlag == '2') {
          var s_category = getValue('categoriesList');
          var s_ministry = getValue('ministriesList');
          //Commented by Ayman because WorkCenter is not mandetory
          //var s_wcenter = getValue('selectedWorkCenterId');
          //if (s_category == '-100' || s_ministry == '-100' || s_wcenter == '-100') {
          //    showMyValidationErrMsg(emptySearchErrMsg);
          if (s_category == '-100' || s_ministry == '-100') {
              showMyValidationErrMsg(emptySearchErrMsg);
              valid = false;
          }

      }

      if (valid) {
          searchJS();
      }

  }

  function showMyValidationErrMsg(msg) {
      document.getElementById('myForm:conditionResultsErrMsg').innerHTML = msg;
  }

  function showMyCivilIdErrMsg(msg) {
      document.getElementById('myForm:civilIdErrMsg').innerHTML = msg;
  }

  function clearMyValidationErrMsg() {
      try {
          document.getElementById('minerrorCodeId').style.display = "none";
          document.getElementById('cadererrorCodeId').style.display = "none";
          document.getElementById('grouperrorCodeId').style.display = "none";
          document.getElementById('degreeerrorCodeId').style.display = "none";
          document.getElementById('bptprogramerrorCodeId').style.display = "none";
          document.getElementById('bgttypeerrorCodeId').style.display = "none";
          document.getElementById('conditionerrorCodeId	').style.display = "none";
          document.getElementById('caterrorCodeId').style.display = "none";
          document.getElementById('myForm:conditionResultsErrMsg').style.display = "none";
      }
      catch (e) {
      }
      try {
          document.getElementById('myForm:civilIdErrMsg').innerHTML = '';
      }
      catch (e) {
      }
  }
  copyDropdownIntoInputtext('categoriesList', 'categoryId');
  copyDropdownIntoInputtext('ministriesList', 'ministryId');
  copyDropdownIntoInputtext('caderList', 'caderId');
  copyDropdownIntoInputtext('groupsList', 'groupId');
  copyDropdownIntoInputtext('degreesList', 'degreeId');

    //Changed by Ayman to change the tab order after ministry is selected not workcenter
    //function tabOrder(event,selectedWorkCenterId)
  function tabOrder(event,selectedMinistryId) {
      if (selectedMinistryId == '-100') {
            setFocusOnElement("categoryId");
      }
      else{
          setFocusOnElement("searchBtn");
      }
      
  }
  
  function toggleDivUsingMaxHeight(togglerId, cnt1DivId, cntDivId, cntStyleClassWhileCnt1Expanded, cntStyleClassWhileCnt1Collapsed) {
    var collapsedHeight;
    var displayedHeight;
    if (document.getElementById(cnt1DivId).style.display == 'none') {
        document.getElementById(cnt1DivId).style.display = 'inline-table';
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).style.height;
        document.getElementById(togglerId).className = 'hideDivPnl';
        if(cntStyleClassWhileCnt1Expanded != null ){
           // document.getElementById(cntDivId).className = cntStyleClassWhileCnt1Expanded;
            if(cntStyleClassWhileCnt1Expanded =='dataT-With-6-row-filter-and-collapse'){
                document.getElementById('dataT_data_panel').className = 'dataTBulkExcepand';
            }else{
                document.getElementById('dataT_data_panel').className = 'dataTCivilExcepand';
            }
        } 
    } else {
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).offsetHeight;
        document.getElementById(cnt1DivId).style.display = 'none';
        
        document.getElementById(togglerId).className = 'showDivPnl';
        if(cntStyleClassWhileCnt1Collapsed != null ){
           document.getElementById('dataT_data_panel').className = cntStyleClassWhileCnt1Collapsed;
           
        }
    }
}
</script>
<%-- disabled="#{pageBeanName.selectedMinistryId == pageBeanName.virtualConstValue}"--%>
