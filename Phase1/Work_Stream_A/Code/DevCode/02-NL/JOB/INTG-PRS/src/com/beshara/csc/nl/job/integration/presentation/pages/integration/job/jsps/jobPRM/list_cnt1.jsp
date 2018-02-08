<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingCstmHeightHere('hideDivImg', 'filterPanelGrp', 'dataT_data_panel')"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="filterPanelGrp">
    <%--<h:panelGrid columns="1" align="center">
        <t:outputText id="jobFieldValError" forceId="true" styleClass="errMsg" value="#{jobResources.inavlid_job_val}"
                      binding="#{pageBeanName.errorMsgBind}" style="#{pageBeanName.showMsg}"/>
    </h:panelGrid>--%>

    <t:panelGrid columns="6" align="right" dir="#{jobIntgResources.align}" id="mapFilter" forceId="true"
                 columnClasses="col1,col2" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
        <t:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.cat_job}"/>
        
        <t:inputText forceId="true" id="jobFieldVal" value="#{pageBeanName.selectedJobCode}" disabled="#{!pageBeanName.filterMode}"
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'jobField','errorCodeID1',changeJobVal,null);"
                        onblur=" filterationInputOnBlur(event,this,'jobField','errorCodeID1',changeJobVal,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="jobField" value="#{pageBeanName.selectedJobCode}" disabled="#{!pageBeanName.filterMode}" styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'jobFieldVal','errorCodeID1',changeJobVal); clearInputs(['levelGroupVal','levelsVal','childTypeVal','workTypeNameVal','jobTypeVal','joblevelVal']);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.jobFieldsList}" itemLabel="#{jobGroup.name}"
                           itemValue="#{jobGroup.code.key}" var="jobGroup"/>
        
        </t:selectOneMenu>
            <a4j:jsFunction action="#{pageBeanName.jobCodeChanged}" name="changeJobVal" 
            oncomplete="setFocusOnElement('levelGroupVal');"
            reRender="viewEmployeesId,levelGroupField,levelGroupVal,jobTypeFieldMenu,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,joblevelVal,jobLevelField" 
                            />
        <t:outputLabel id="errorCodeID1" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
        
        <t:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.job_cat}"/>
        <t:inputText forceId="true" id="levelGroupVal" value="#{pageBeanName.selectedLevelGroupCode}"  
                    disabled="#{!pageBeanName.filterMode || pageBeanName.selectedJobCode == null || pageBeanName.selectedJobCode == '' }" 
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'levelGroupField','errorCodeID2',changeGroupJobVal,null);"
                        onblur=" filterationInputOnBlur(event,this,'levelGroupField','errorCodeID2',changeGroupJobVal,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="levelGroupField" value="#{pageBeanName.selectedLevelGroupCode}" 
                         disabled="#{!pageBeanName.filterMode || pageBeanName.selectedJobCode == null || pageBeanName.selectedJobCode == ''}"  styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'levelGroupVal','errorCodeID2',changeGroupJobVal); clearInputs(['levelsVal','childTypeVal','workTypeNameVal','jobTypeVal','joblevelVal']);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.catsGroupList}" itemLabel="#{levelGroup.name}"
                           itemValue="#{levelGroup.code.key}" var="levelGroup"/>
        
        </t:selectOneMenu>
        <a4j:jsFunction action="#{pageBeanName.levelGroupCodeChanged}" name="changeGroupJobVal"
                            reRender="viewEmployeesId,jobFieldValError,jobField,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"
                            oncomplete="setFocusOnElement('levelsVal');"/>
        <t:outputLabel id="errorCodeID2" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
        <h:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.categories}"/>
        <t:inputText forceId="true" id="levelsVal" value="#{pageBeanName.selectedLevelsCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedLevelGroupCode == null || pageBeanName.selectedLevelGroupCode == ''}"
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'levelsField','errorCodeID3',changeLevels,null);"
                        onblur=" filterationInputOnBlur(event,this,'levelsField','errorCodeID3',changeLevels,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="levelsField" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedLevelGroupCode == null || pageBeanName.selectedLevelGroupCode == ''}" value="#{pageBeanName.selectedLevelsCode}" styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'levelsVal','errorCodeID3',changeLevels); clearInputs(['childTypeVal','workTypeNameVal','jobTypeVal','joblevelVal']);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.catsSubList}" itemLabel="#{subCat.name}"
                           itemValue="#{subCat.code.key}" var="subCat"/>
        
        </t:selectOneMenu>
        <a4j:jsFunction action="#{pageBeanName.levelsCodeChanged}" name="changeLevels"
                            reRender="viewEmployeesId,jobFieldValError,jobField,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"
                            oncomplete="setFocusOnElement('childTypeVal');"/>
        <t:outputLabel id="errorCodeID3" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
        
        <h:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.sup_job_type}"/>
        <t:inputText forceId="true" id="childTypeVal" value="#{pageBeanName.selectedChildTypeCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedLevelsCode == null || pageBeanName.selectedLevelsCode == ''}"
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'childTypeField','errorCodeID4',changeChildType,null);"
                        onblur=" filterationInputOnBlur(event,this,'childTypeField','errorCodeID4',changeChildType,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="childTypeField" value="#{pageBeanName.selectedChildTypeCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedLevelsCode == null || pageBeanName.selectedLevelsCode == ''}" styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'childTypeVal','errorCodeID4',changeChildType); clearInputs(['workTypeNameVal','jobTypeVal','joblevelVal']);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.childTypeFieldsList}" itemLabel="#{child.name}"
                           itemValue="#{child.code.key}" var="child"/>
        
        </t:selectOneMenu>
        <a4j:jsFunction action="#{pageBeanName.childTypeCodeChanged}" name="changeChildType"
                            reRender="viewEmployeesId,jobFieldValError,jobField,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"
                            oncomplete="setFocusOnElement('workTypeNameVal');"/>
        <t:outputLabel id="errorCodeID4" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
        
        
          <t:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.workTypeName}"/>
        <t:inputText forceId="true" id="workTypeNameVal" value="#{pageBeanName.selectedWorkTypeNameCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedChildTypeCode == null || pageBeanName.selectedChildTypeCode == ''}"
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'workTypeNameField','errorCodeID5',changeWorkTypeName,null);"
                        onblur=" filterationInputOnBlur(event,this,'workTypeNameField','errorCodeID5',changeWorkTypeName,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="workTypeNameField" value="#{pageBeanName.selectedWorkTypeNameCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedChildTypeCode == null || pageBeanName.selectedChildTypeCode == ''}" styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'workTypeNameVal','errorCodeID5',changeWorkTypeName); clearInputs(['jobTypeVal','joblevelVal']);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.workTypeNameFieldsList}" itemLabel="#{wrknameType.name}"
                           itemValue="#{wrknameType.code.key}" var="wrknameType"/>
        
        </t:selectOneMenu>
        <a4j:jsFunction action="#{pageBeanName.workTypeNameCodeChanged}" name="changeWorkTypeName"
                            reRender="viewEmployeesId,jobFieldValError,jobField,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"
                            oncomplete="setFocusOnElement('jobTypeVal');"/>
        <t:outputLabel id="errorCodeID5" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
        
        
        
        <t:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.job_typeGroup}"/>
        <t:inputText forceId="true" id="jobTypeVal" value="#{pageBeanName.selectedJobTypeCode}"  disabled="#{!pageBeanName.filterMode || pageBeanName.selectedJobCode == null || pageBeanName.selectedJobCode == ''}"
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'jobTypeFieldMenu','errorCodeID6',changeJobType,null);"
                        onblur=" filterationInputOnBlur(event,this,'jobTypeFieldMenu','errorCodeID6',changeJobType,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="jobTypeFieldMenu" value="#{pageBeanName.selectedJobTypeCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedJobCode == null || pageBeanName.selectedJobCode == ''}" styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'jobTypeVal','errorCodeID6',changeJobType); clearInputs(['joblevelVal']);"  >
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.jobTypesList}" itemLabel="#{jobType.name}"
                           itemValue="#{jobType.code.abilityCode}" var="jobType"/>
            
        </t:selectOneMenu>
        <a4j:jsFunction action="#{pageBeanName.jobTypeCodeChanged}" name="changeJobType"
                            reRender="viewEmployeesId,jobLevelField,jobFieldValError,jobField,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"
                            oncomplete="setFocusOnElement('joblevelVal');"/>
        <t:outputLabel id="errorCodeID6" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
        
         <t:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.job_level}"/>
        <t:inputText forceId="true" id="joblevelVal"  value="#{pageBeanName.jobCatLevelsScaleCode}" disabled="#{!pageBeanName.filterMode || pageBeanName.selectedJobCode == null || pageBeanName.selectedJobCode == ''}"
                     styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                     onkeypress=" filterationInputOnKeyPress(event,this,'jobLevelField','errorCodeID7',jobCatLevelsScaleCodeChanged,null);"
                        onblur=" filterationInputOnBlur(event,this,'jobLevelField','errorCodeID7',jobCatLevelsScaleCodeChanged,null);"
                        onkeyup="enabelIntegerOnly(this);"/>
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="jobLevelField" value="#{pageBeanName.jobCatLevelsScaleCode}"
                         styleClass="DropdownboxMedium3"  disabled="#{!pageBeanName.filterMode || pageBeanName.selectedJobCode == null || pageBeanName.selectedJobCode == ''}"
                         onchange="filterationDropboxOnChange(event,this,'joblevelVal','errorCodeID7',jobCatLevelsScaleCodeChanged);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.jobCatLevelsList}" itemLabel="#{jobCatLvl.levelsDTO.name}"
                           itemValue="#{jobCatLvl.levelsDTO.code.levelCode}" var="jobCatLvl"/>
        
        
        </t:selectOneMenu>
        <a4j:jsFunction  name="jobCatLevelsScaleCodeChanged"  reRender="viewEmployeesId,joblevelVal" oncomplete="setFocusOnElement('viewEmployeesId');" />
        <t:outputLabel id="errorCodeID7" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>
        
        
    </t:panelGrid>
    <t:panelGroup id="viewEmployeesId" forceId="true">
    <a4j:commandButton id="viewEmployeesBtn" action="#{pageBeanName.fillDataTable}" value="#{jobIntgResources[pageBeanName.buttonValue]}"
                        disabled="#{pageBeanName.jobCatLevelsScaleCode == null }"
                       styleClass="cssButtonSmall"
                       reRender="dataT_data_panel,myForm:executePRM,filterPanelGrp,submit,paging_panel,OperationBar,dataT_data_panel_Grid,mapFilter"
                       style="margin-right:412px;"/>
    </t:panelGroup>
</t:panelGroup>
<f:verbatim>

</f:verbatim>
 <script language="javascript" type="text/javascript">
 
function clearInputs(inputIds){
    for (i = 0; i < inputIds.length ; i++) { 
        document.getElementById(inputIds[i]).value = "";
    }        
}

function toggleDivUsingCstmHeightHere(togglerId, cnt1DivId, cntDivId) {
    var collapsedHeight;
    var displayedHeight;
    if (document.getElementById(cnt1DivId).style.display == 'none') {
        document.getElementById(cnt1DivId).style.display = 'block';
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).style.height;
        document.getElementById(togglerId).className = 'hideDivPnl';
        document.getElementById(cntDivId).style.height = '245';
    } else {
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).offsetHeight;
        document.getElementById(cnt1DivId).style.display = 'none';
        document.getElementById(togglerId).className = 'showDivPnl';
        document.getElementById(cntDivId).style.height = '400';
    }
}
 </script>