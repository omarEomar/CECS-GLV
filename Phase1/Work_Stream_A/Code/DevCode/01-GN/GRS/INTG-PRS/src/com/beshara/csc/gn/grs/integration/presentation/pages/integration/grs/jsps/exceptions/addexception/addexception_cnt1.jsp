<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
 .filteration_input_text{
   width: 45px !important;
 }
</htm:style>
<t:inputHidden value="#{pageBeanName.resultTableStyleClass}" forceId="true" id="pageStyleClass"/>
<t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingMaxHeight('hideDivImg', 'searchingPnl', 'resultsPnl',document.getElementById('pageStyleClass').value,'dataT-With-collapse')" rendered="#{exceptionsAddBean.searchEmpResult == 0}"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;text-align:center;">
    <t:outputLabel id="caterrorCodeId" value="#{grsIntgResources.CatMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="minerrorCodeId" value="#{grsIntgResources.MinMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="cadererrorCodeId" value="#{grsIntgResources.caderMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="grouperrorCodeId" value="#{grsIntgResources.groupMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="degreeerrorCodeId" value="#{grsIntgResources.degreeMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="bptprogramerrorCodeId" value="#{grsIntgResources.bgtProgramMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="bgttypeerrorCodeId" value="#{grsIntgResources.bgttypeMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <t:outputLabel id="conditionerrorCodeId" value="#{grsIntgResources.conditionMessageForWrongCode}" forceId="true"
                   styleClass="error" style="display:none"/>
    <h:outputText value="" id="conditionResultsErrMsg" styleClass="errMsg"/>
</t:panelGroup>
<t:panelGrid forceId="true" id="searchingPnl" columns="6" align="right" width="100%" cellpadding="3" cellspacing="0"
             border="0" rowClasses="row01,row02" onkeypress="FireButtonClickOldStyle(event,'searchBtn');">
    <t:panelGroup colspan="6" styleClass="center" rendered="#{exceptionsAddBean.searchEmpResult == 0 ||exceptionsAddBean.searchEmpResult==5}">
        <t:selectOneRadio styleClass="radioButton_DataTable" forceId="true" id="searchTypeRadio"
                          value="#{pageBeanName.searchTypeFlag}" onkeyup="toggleRadioKeyUp(this);" 
                          onmousedown="toggleRadio(this);" disabled="#{exceptionsAddBean.searchMode}" >
            <f:selectItem itemLabel="#{grsIntgResources.employeeExeption}"        itemValue="#{pageBeanName.searchByCivilIdFlag}" />
            <f:selectItem itemLabel="#{grsIntgResources.groupOfEmployeeExeption}" itemValue="#{pageBeanName.searchByBulkFlag}" />
            <a4j:support event="onclick" actionListener="#{pageBeanName.searchTypeChanged}"
                         reRender="errorCodePanelId,pageStyleClass,searchingPnl,resultsPnl,paging_panel,myForm:SaveButton, exeptionReasonFilterationId,exeptionReasonId,fromDateId,exeptionTypeId"/>
        </t:selectOneRadio>
   
    </t:panelGroup>     
   
     <t:panelGroup forceId="true" id="civilidButton" colspan="3" rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByCivilIdFlag}">
        <h:outputText id="civilID" value="#{globalResources.globalCivilId}" styleClass="lable01" style="margin-left:35px;"/>
        <t:inputText  styleClass="textboxsmall2" value="#{pageBeanName.civilId}" maxlength="12" forceId="true" id="civilIdInTxt" converter="javax.faces.Long" onkeyup="disableCharacters(this);" onkeypress="FireButtonClick('ava');" tabindex="1"  style="width:95px;" disabled="#{pageBeanName.reEnterCivilId}"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                 <t:commandButton forceId="true" id="ava"  value="#{globalResources.Available}" styleClass="cssButtonSmall"  rendered="#{!pageBeanName.reEnterCivilId}"
                                   action="#{pageBeanName.loadData}"  onclick="return(validateRequiredElement('civilIdInTxt','#{grsIntgResources.missingCivilID}','errorCivilMessage1') && checkExactLength('12','civilIdInTxt','errorCivilMessage','#{grsIntgResources.civilLengthError}'));loadName();" >
                   <a4j:jsFunction name="loadName" oncomplete="changeVisibilityMsg();" reRender="namePG ,buttonsPG,msgShow ,civilidButton"/>                
                 </t:commandButton>
                 <a4j:commandButton id="cancelAva"  action="#{pageBeanName.resetEmpData}" value="#{grsIntgResources.reEnter}" styleClass="cssButtonSmall" rendered="#{pageBeanName.reEnterCivilId}"
                 reRender="civilidButton,namePG ,buttonsPG,msgShow">
                 </a4j:commandButton>
                 <t:commandButton type="button" onclick="goSearchDiv2();" id="okLoveButton2" styleClass="cssButtonSmall" value="#{grsIntgResources.search}">
                    <a4j:jsFunction name="goSearchDiv2" action="#{pageBeanName.showSearchForEmployeeDiv}"
                             oncomplete="javascript:changeVisibilityDiv(window.blocker,window.customDiv1);"       />
                </t:commandButton>
           <t:outputText id="errorCivilMessage1" forceId="true" styleClass="errMsg" />
            <t:outputText id="errorCivilMessage" forceId="true" styleClass="errMsg"/>
        </t:panelGroup>
        
        <t:panelGroup forceId="true" id="namePG"  colspan="3" rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByCivilIdFlag}">
           <h:outputText value="#{grsIntgResources.name}" style="margin-left:5px;" />
            <t:inputText forceId="true" id="nameId" style="width: 285px;"  styleClass="textboxmedium" value="#{pageBeanName.civilName}" disabled="true"/>         
        </t:panelGroup>

         <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <t:outputLabel value="#{grsIntgResources.category}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'categoriesList','caterrorCodeId',changecategoryVal,'ministryId');"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'categoriesList','caterrorCodeId',changecategoryVal,'ministryId');"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changecategoryVal" actionListener="#{pageBeanName.selectedCategoryChanged}"
                                oncomplete="clearFilterationInput('ministryId');"
                                reRender="selectedWorkCenter,ministriesList,categoriesList,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="categoriesList" value="#{pageBeanName.selectedCategoryId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'categoryId','caterrorCodeId',changeCatValD);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.categoriesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeCatValD" actionListener="#{pageBeanName.selectedCategoryChanged}"
                                oncomplete="clearFilterationInput('ministryId');" reRender="searchingPnl"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.ministry}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="ministryId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'ministriesList','minerrorCodeId',changeministryVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'ministriesList','minerrorCodeId',changeministryVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changeministryVal" actionListener="#{pageBeanName.selectedMinistryChanged}"
                                oncomplete="setFocusOnElement('workCenterBtn');"
                                reRender="selectedWorkCenter,searchingPnl,ministriesList,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="ministriesList" value="#{pageBeanName.selectedMinistryId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onblur="tabOrder(event,document.getElementById('ministryId').value);"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'ministryId','minerrorCodeId',changeMinValD);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.ministriesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeMinValD" actionListener="#{pageBeanName.selectedMinistryChanged}"
                                reRender="searchingPnl,bgtProgramsList"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.workCenter}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="selectedWorkCenter" rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="selectedWorkCenterName" value="#{pageBeanName.selectedWorkCenterName}"
                         onmouseout="hideTip()" onmouseover="displayTooltip(event,this)" styleClass="textboxmediumB"
                         style="width:145px" readonly="true"/>
            
            <t:inputHidden forceId="true" id="selectedWorkCenterId" value="#{pageBeanName.selectedWorkCenterId}"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:commandButton disabled="#{pageBeanName.selectedMinistryId == pageBeanName.virtualConstValue || pageBeanName.searchMode}"
                             value="..." action="#{wcIntegrationBeanName.openIntDiv}" styleClass="cssButtonSmall"
                             style="min-width: 35px !important;height:23px" id="workCenterBtn" forceId="true"/>
        </t:panelGroup>
        <%-- 2 --%>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.cader}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="caderId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'caderList','cadererrorCodeId',changecaderVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'caderList','cadererrorCodeId',changecaderVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changecaderVal" actionListener="#{pageBeanName.selectedCaderChanged}"
                                oncomplete="clearFilterationInput('groupId');clearFilterationInput('degreeId');"
                                reRender="searchingPnl,changecaderVal,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="caderList" value="#{pageBeanName.selectedCaderId}" style="width:155px;"
                             styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'caderId','cadererrorCodeId',changeCaderValD);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.caderList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeCaderValD" actionListener="#{pageBeanName.selectedCaderChanged}"
                                oncomplete="clearFilterationInput('groupId');clearFilterationInput('degreeId');"
                                reRender="searchingPnl"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.group}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="groupId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'groupsList','grouperrorCodeId',changegroupVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'groupsList','grouperrorCodeId',changegroupVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changegroupVal" actionListener="#{pageBeanName.selectedGroupChanged}"
                                oncomplete="clearFilterationInput('degreeId');"
                                reRender="searchingPnl,groupsList,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="groupsList" value="#{pageBeanName.selectedGroupId}" style="width:155px;"
                             styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'groupId','grouperrorCodeId',changeGroupValD);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.groupsList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <a4j:jsFunction name="changeGroupValD" actionListener="#{pageBeanName.selectedGroupChanged}"
                                oncomplete="clearFilterationInput('degreeId');" reRender="searchingPnl"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.degree}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="degreeId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'degreesList','degreeerrorCodeId',changedegreeVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'degreesList','degreeerrorCodeId',changedegreeVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px; width:40px; "
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changedegreeVal" oncomplete="setFocusOnElement('searchBtn');"
                                reRender="searchingPnl,degreesList,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="degreesList" value="#{pageBeanName.selectedDegreeId}"
                             style="width:156px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'degreeId','degreeerrorCodeId',null);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.degreesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
                <%-- a4j:support event="onchange" actionListener="#{pageBeanName.selectedDegreeChanged}"
                     reRender="searchingPnl"/--%>
            </t:selectOneMenu>
        </t:panelGroup>
        <%--3--%>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.bgtProgram}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="bgtProgramId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'bgtProgramsList','bptprogramerrorCodeId',changebgtProgramVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'bgtProgramsList','bptprogramerrorCodeId',changebgtProgramVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changebgtProgramVal" oncomplete="setFocusOnElement('bgtTypeId');"
                                reRender="searchingPnl,bgtProgramsList,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="bgtProgramsList" value="#{pageBeanName.selectedBgtProgramId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'bgtProgramId','bptprogramerrorCodeId',null);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.bgtProgramsList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
        <h:outputLabel value="#{grsIntgResources.bgtType}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup colspan="3" rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
            <t:inputText forceId="true" id="bgtTypeId" styleClass="filteration_input_text"
                         onkeypress="clearMyValidationErrMsg();filterationInputOnKeyPress(event,this,'bgtTypesList','bgttypeerrorCodeId',changebgtTypeVal);"
                         onblur="clearMyValidationErrMsg();filterationInputOnBlur(event,this,'bgtTypesList','bgttypeerrorCodeId',changebgtTypeVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"
                         disabled="#{exceptionsAddBean.searchMode}">
                <a4j:jsFunction name="changebgtTypeVal" oncomplete="setFocusOnElement('searchBtn');"
                                reRender="searchingPnl,bgtProgramsList,dataT_data_panel,paging_panel,listSize,divSearch"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="bgtTypesList" value="#{pageBeanName.selectedBgtTypeId}"
                             style="width:155px;" styleClass="Dropdownboxmedium2" onmouseout="hideTip()"
                             onmouseover="displayTooltip(event,this)"
                             onchange="clearMyValidationErrMsg();filterationDropboxOnChange(event,this,'bgtTypeId','bgttypeerrorCodeId',null);"
                             disabled="#{exceptionsAddBean.searchMode}">
                <f:selectItem itemLabel="#{grsIntgResources.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.bgtTypesList}" var="item" itemValue="#{item.code.key}"
                               itemLabel="#{item.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
    
    
      <t:panelGroup colspan="6" style="display: block; background-color: rgb(255, 255, 255); text-align: center; margin: 0px auto;" id="searchBtnsPnl" forceId="true"  rendered="#{pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag}">
                <t:commandButton forceId="true" id="searchBtn" type="button" value="#{globalResources.SearchButton}"
                                 styleClass="cssButtonSmall" rendered="#{!exceptionsAddBean.searchMode}"
                                 disabled="#{pageBeanName.selectedMinistryId == '-100'}"
                                 onclick="validateMySearchCriteria('#{globalResources.missingField}','#{globalResources.civil_no_not_less_than_12}','#{globalResources.civiliderror}','#{grsIntgResources.conditionResultsCriteriaMsg}');"/>
                <a4j:jsFunction name="searchJS" action="#{pageBeanName.search}"
                                reRender="dataT_data_panel,paging_panel,cancelSearchBtn,searchBtnsPnl,searchingPnl,errorCodePanelId"/>
           
                <a4j:commandButton id="cancelSearchBtn" value="#{globalResources.cancelsearch}"
                                   action="#{pageBeanName.cancelSearch}" rendered="#{exceptionsAddBean.searchMode}"
                                   oncomplete="clearInputs();" styleClass="cssButtonSmall"
                                   reRender="searchBtnsPnl,searchBtn,resultsPnl,paging_panel,searchingPnl,errorCodePanelId"/>
        
      </t:panelGroup>
     </t:panelGrid>
     
 
           
            
    
    <t:panelGrid columns="6" align="right" width="100%" cellpadding="3" cellspacing="0"
             border="0" rowClasses="row01,row02">
      <t:panelGroup colspan="6" style="background-color:#ffffff;" >
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{grsIntgResources.exeptionDetails}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
                </t:panelGroup>
            <%----------------------------- start exception details---------------------%>
            <t:outputLabel value="#{grsIntgResources.operation}" styleClass="lable01"/>
            <t:panelGroup colspan="5">
                <t:inputText disabled="true" forceId="true" id="operationId" styleClass="textboxlarge4" style="width:666px;" value="#{pageBeanName.selectedOperationKey}"></t:inputText>
            </t:panelGroup>
                <t:outputLabel value="#{grsIntgResources.condition}" styleClass="lable01"/>
            <t:panelGroup colspan="5">
                <t:inputText disabled="true" forceId="true" id="conditionId" styleClass="textboxlarge4" style="width:666px;" value="#{pageBeanName.selectedConditionDTO.name}"></t:inputText>
            </t:panelGroup>
            <t:panelGroup  rendered="#{pageBeanName.showLines}">
              <t:outputLabel value="#{grsIntgResources.lines}" styleClass="lable01"/>
              </t:panelGroup>
              <t:panelGroup colspan="5" rendered="#{pageBeanName.showLines}">
              <t:selectManyMenu forceId="true" id="linesListId"  disabled="true" styleClass="textboxlarge"
                                  style="width:666px;min-height:90px;">
                    <t:selectItems value="#{pageBeanName.linesList}" var="types"
                                   itemValue="#{types.code.key}" itemLabel="#{types.name}"/>
                </t:selectManyMenu>
                  
              </t:panelGroup>
           
                <t:outputLabel value="#{grsIntgResources.exeptionReason}" styleClass="lable01"/>
            <t:panelGroup colspan="5">
                <t:inputText forceId="true" id="exeptionReasonFilterationId" styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                             onkeypress=" filterationInputOnKeyPress(event,this,'exeptionReasonId','errorCodeId1',null,'okLoveButton1');"
                             onblur=" filterationInputOnBlur(event,this,'exeptionReasonId','errorCodeId1','null');"
                             onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedExeReasonKey}"></t:inputText>
                <t:selectOneMenu forceId="true" id="exeptionReasonId" value="#{pageBeanName.selectedExeReasonKey}"
                                 onchange="filterationDropboxOnChange(event,this,'exeptionReasonFilterationId','errorCodeId1',null);"
                                 styleClass="DropdownboxMedium3">
                    <f:selectItem itemValue="" itemLabel="#{grsIntgResources.select}"/>
                    <t:selectItems value="#{pageBeanName.exceptionReasonList}" itemLabel="#{exe.name}" itemValue="#{exe.code.key}" var="exe"/>
                </t:selectOneMenu>
                 <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <t:commandButton type="button" onclick="goSearchDiv1();" id="okLoveButton1" styleClass="cssButtonSmall" value="...">
                    <a4j:jsFunction name="goSearchDiv1" action="#{pageBeanName.openSearchDiv1}"
                                    reRender="lov_dataT_data_panel,lov_paging_panel,lovDiv_btnsPnlGrd,lov_searchPanel,lov_searchPanel_radio,viewTablePN,content_details,resultsPnl"
                                    oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
                </t:commandButton>
                <c2:requiredFieldValidator componentToValidate="exeptionReasonId" errorMessage="#{globalResources.missingField}"
                                   display="dynamic"/>
                 <t:outputLabel id="errorCodeId1" value="#{grsIntgResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none"/>
            </t:panelGroup>
                <h:outputText value="#{grsIntgResources.fromDate}" styleClass="lable01"/>
            <t:panelGroup colspan="2">
                    <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                     forceId="true" id="fromDateId" value="#{pageBeanName.pageDTO.fromDate}" maxlength="#{detailBeanName.calendarTextLength}"
                                     onblur="return validateInputCalenderFormate('birthDateClientId','null','null');"
                                     styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true"
                                     align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                     renderPopupButtonAsImage="true">
                        <f:converter converterId="SqlDateConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <c2:dateFormatValidator componentToValidate="fromDateId"
                                    display="dynamic" 
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
                    <c2:requiredFieldValidator componentToValidate="fromDateId" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="fromDateID"/>
            </t:panelGroup>
                 <t:outputLabel value="#{grsIntgResources.exceptionType}" styleClass="lable01"/>
                 <t:panelGroup colspan="2">
                <t:selectOneMenu forceId="true" id="exeptionTypeId" value="#{pageBeanName.selectedExceptionTypeId}"
                                 styleClass="DropdownboxMedium3" disabled="#{pageBeanName.disableExceptionType}">
                    <f:selectItem itemValue="" itemLabel="#{grsIntgResources.select}"/>
                     <f:selectItem itemValue="1" itemLabel="#{grsIntgResources.exceptionTypeValid}"/>
                     <f:selectItem itemValue="2" itemLabel="#{grsIntgResources.exceptionTypeInValid}"/>
                </t:selectOneMenu>
                 <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                  <c2:requiredFieldValidator componentToValidate="exeptionTypeId" errorMessage="#{globalResources.missingField}"
                                   display="dynamic"/>
            </t:panelGroup>
            
</t:panelGrid>            
            <%----------------------------- end exception details---------------------%>
            
            <%----------------------------- start buttons ---------------------%>
       <t:panelGrid columns="3" border="0" align="center" forceId="true" id="buttonsPG">
           <t:commandButton styleClass="cssButtonSmall" forceId="true" id="SaveButton" onclick="return validatemyForm();" value="#{globalResources.SaveButton}" action="#{exceptionsAddBean.Add}" disabled="#{!exceptionsAddBean.activeButton}"/>        
           <h:panelGroup>
                <t:commandButton forceId="true" id="BackButtonManyToMany" styleClass="cssButtonSmall" value="#{globalResources.back}" action="#{exceptionsAddBean.back}"/>
                
           </h:panelGroup>
        
        </t:panelGrid>
            <%----------------------------- end buttons ---------------------%>
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
          if (s_category == '' || s_ministry == '') {
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
  function tabOrder(event, selectedMinistryId) {
      if (selectedMinistryId == '') {
          setFocusOnElement("categoryId");
      }
      else {
          setFocusOnElement("searchBtn");
      }

  }

  function toggleDetails(image) {
      var detailsId = image.id.substring(0, image.id.lastIndexOf(':')) + ':details';
      var details = document.getElementById(detailsId);
      if (details.style.display == 'none') {
          details.style.display = 'block';
          //image.src = 'collapse.gif';
      }
      else {
          details.style.display = 'none';
          //image.src = 'expand.gif';
      }
  }

  function validateRequiredElement(listOfControls, message, listOfMessageId) {
      document.getElementById('errorCivilMessage1').innerHTML = '';
      document.getElementById('errorCivilMessage').innerHTML = '';
      var tempx = new Array();
      var errx = new Array();
      flag = true;
      tempx = listOfControls.split('#');
      errx = listOfMessageId.split('#');

      for (i = 0;i < errx.length;i++) {
          document.getElementById(errx[i]).innerHTML = '';
          if (document.getElementById(tempx[i]).value == '') {
              flag = false;
              document.getElementById(errx[i]).innerHTML = message;

          }
      }
      return flag;
  }

  function disableCharacters(field) {
      if (!/^\d*$/.test(field.value)) {
          field.value = field.value.replace(/[^\d]/g, "");
      }
  }
  
function toggleDivUsingMaxHeight(togglerId, cnt1DivId, cntDivId, cntStyleClassWhileCnt1Expanded, cntStyleClassWhileCnt1Collapsed) {
    var collapsedHeight;
    var displayedHeight;
    if (document.getElementById(cnt1DivId).style.display == 'none') {
        document.getElementById(cnt1DivId).style.display = 'block';
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).style.height;
        document.getElementById(togglerId).className = 'hideDivPnl';
        if(cntStyleClassWhileCnt1Expanded != null ){
            //document.getElementById(cntDivId).className = cntStyleClassWhileCnt1Expanded;
            document.getElementById('dataT_data_panel').style.height = "58";
        } else {
            document.getElementById(cntDivId).style = "max-height:sheka;";
            
        }
    } else {
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).offsetHeight;
        document.getElementById(cnt1DivId).style.display = 'none';
        
        document.getElementById(togglerId).className = 'showDivPnl';
        if(cntStyleClassWhileCnt1Collapsed != null ){
            document.getElementById('dataT_data_panel').style.height = "213";
        } else {
           // document.getElementById(cntDivId).style = "max-height:"+(displayedHeight+collapsedHeight-5)+"px;";
             
        }
    }
}
</script>

