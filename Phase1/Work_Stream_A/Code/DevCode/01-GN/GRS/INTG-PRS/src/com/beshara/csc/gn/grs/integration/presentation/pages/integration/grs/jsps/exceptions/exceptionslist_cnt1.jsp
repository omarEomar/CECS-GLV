<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.col1 {
    width: 43px;
}
</htm:style>
  
  <t:panelGroup forceId="true" id="grsIntgHiddenValues">
                <t:inputHidden forceId="true" id="fullURLId" value="#{pageBeanName.viewDetailsPageURL}"></t:inputHidden>
                <t:inputHidden forceId="true" id="grsLineWindowTitleId" value="aaaaa"/>
            </t:panelGroup>
  
<t:panelGroup forceId="true" id="hideDivImgssss" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingCstmHeightHere('hideDivImg', 'filterPanelGrp', 'myDataTableId')"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="filterPanelGrp">
   <%-- <h:panelGrid columns="1" align="center">
        <t:outputText id="jobFieldValError" forceId="true" styleClass="errMsg" value="#{jobResources.inavlid_job_val}"
                      binding="#{pageBeanName.errorMsgBind}" style="#{pageBeanName.showMsg}"/>
    </h:panelGrid>--%>
    
    
    <t:panelGrid columns="4" align="right"  id="grsFilter" forceId="true"
                 columnClasses="col1,col2" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
       <t:panelGroup colspan="4">
          <t:selectOneRadio styleClass="radioButton_DataTable" forceId="true" id="exceptionTypeRadio"
                          value="#{pageBeanName.selectedRadioValue}" onkeyup="toggleRadioKeyUp(this);"
                          onmousedown="toggleRadio(this);" disabled="#{pageBeanName.viewMode}"  >
                 <f:selectItem itemLabel="#{grsIntgResources.exceptionByCondition}" itemValue="#{pageBeanName.exceptionByCondition}"/>
                 <f:selectItem itemLabel="#{grsIntgResources.exceptionByLine}" itemValue="#{pageBeanName.exceptionByLine}"/>
                   <a4j:support event="onclick" actionListener="#{pageBeanName.radioValueChanged}"
                         reRender="linesPanelId ,viewTablePN ,OperationBar"/>
                </t:selectOneRadio>
                
                
                
       </t:panelGroup>
        <h:outputText styleClass="lable01 " value="#{grsIntgResources.system}" rendered="#{pageBeanName.appModuleKey ==0}"/>
        <t:panelGroup colspan="3" rendered="#{pageBeanName.appModuleKey ==0}" >
        <t:inputText forceId="true" id="systemFilterationId" styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                        onkeypress=" filterationInputOnKeyPress(event,this,'systemListId','errorCodeId1',changeAppModule,'operationFilterationId');"
                        onblur=" filterationInputOnBlur(event,this,'systemListId','errorCodeId1',changeAppModule,'operationFilterationId');"
                        onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedAppModuleKey}" disabled="#{pageBeanName.viewMode}" >
                  </t:inputText>
        <t:selectOneMenu forceId="true" id="systemListId"  value="#{pageBeanName.selectedAppModuleKey}" 
                         onchange="filterationDropboxOnChange(event,this,'systemFilterationId','errorCodeId1',changeAppModule);" styleClass="DropdownboxMedium3" disabled="#{pageBeanName.viewMode}">
            <f:selectItem itemValue="" itemLabel="#{grsIntgResources.select}"/>
            <t:selectItems value="#{pageBeanName.appModuleList}" itemLabel="#{system.name}" itemValue="#{system.code.key}" var="system"/>
            <a4j:jsFunction name="changeAppModule" action="#{pageBeanName.appModuleChanged}"  
                         reRender="operationListId,errorCodeId1,conditionFilterationId,conditionListId,linesPanelId,errorCodeId3,okLoveButton2,okLoveButton3 ,viewTablePN ,content_details,grsIntgHiddenValues,OperationBar"/>
        </t:selectOneMenu>
        
         <h:outputText value="*" styleClass="mandatoryAsterisk"  />
         <t:commandButton type="button"  onclick="goSearchDiv1();" id="okLoveButton1"  styleClass="cssButtonSmall" value="..." disabled="#{pageBeanName.viewMode}">

             <a4j:jsFunction name="goSearchDiv1"  action="#{pageBeanName.openSearchDiv1}" reRender="lov_dataT_data_panel,lov_paging_panel,lovDiv_btnsPnlGrd,lov_searchPanel,lov_searchPanel_radio,viewTablePN,content_details ,OperationBar" 

                             oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
             </t:commandButton>
           
             
             
             
         <f:verbatim><br/></f:verbatim>
         <t:outputLabel id="errorCodeId1" value="#{grsIntgResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none"/>
     </t:panelGroup>
     
      <h:outputText styleClass="lable01 " value="#{grsIntgResources.operation}"/>
      <t:panelGroup colspan="3">
        
        <t:selectOneMenu forceId="true" id="operationListId" value="#{pageBeanName.selectedTransactionKey}"  styleClass="DropdownboxMedium3" disabled="#{pageBeanName.viewMode}" 
        style="width:288px;" onchange="changeTransaction();">
            <f:selectItem itemValue="" itemLabel="#{grsIntgResources.select}"/>
            <t:selectItems value="#{pageBeanName.transactionList}" itemLabel="#{oper.name}" itemValue="#{oper.name}" var="oper"/>
             
            <%--<a4j:support event="onchange" 
                         reRender="submit,jobFieldValError,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"/>--%>
        </t:selectOneMenu>
        <a4j:jsFunction name="changeTransaction" action="#{pageBeanName.transactionChanged}"  
                         reRender="conditionListId,linesPanelId,conditionFilterationId,errorCodeId3,okLoveButton3,viewTablePN,content_details,grsIntgHiddenValues,OperationBar"/>
         <h:outputText value="*" styleClass="mandatoryAsterisk" />
         <t:commandButton type="button"  onclick="goSearchDiv2();" id="okLoveButton2"  styleClass="cssButtonSmall" value="..."  disabled="#{!pageBeanName.transactionsButtonsEnabled || pageBeanName.viewMode}">
         <a4j:jsFunction name="goSearchDiv2"  action="#{pageBeanName.openSearchDiv2}" reRender="lov_dataT_data_panel,lov_paging_panel,lovDiv_btnsPnlGrd,lov_searchPanel,lov_searchPanel_radio ,viewTablePN,content_details ,OperationBar" 

                             oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
             </t:commandButton>
     </t:panelGroup>
     
     
      <h:outputText styleClass="lable01 " value="#{grsIntgResources.condition}"/>
      <t:panelGroup colspan="3">
        <t:inputText forceId="true" id="conditionFilterationId" styleClass="textboxsmall" style="width:35px; margin-left:4px;"
                        onkeypress=" filterationInputOnKeyPress(event,this,'conditionListId','errorCodeId3',changeCondition,'');"
                        onblur=" filterationInputOnBlur(event,this,'conditionListId','errorCodeId3',changeCondition,'');"
                        onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedConditionsKey}"  disabled="#{pageBeanName.viewMode}">
                  </t:inputText>
                  
                  
                  
    
                  
        <t:selectOneMenu forceId="true" id="conditionListId" value="#{pageBeanName.selectedConditionsKey}" 
                         onchange="filterationDropboxOnChange(event,this,'conditionFilterationId','errorCodeId3',changeCondition);" styleClass="DropdownboxMedium3"  disabled="#{pageBeanName.viewMode}">
            <f:selectItem itemValue="" itemLabel="#{grsIntgResources.select}"/>
            
            <t:selectItems value="#{pageBeanName.conditionsList}" itemLabel="#{cond.name}" itemValue="#{cond.code.key}" var="cond" />
            <%--<a4j:support event="onchange" 
                         reRender="submit,jobFieldValError,jobFieldVal,levelGroupVal,levelGroupField,levelsVal,levelsField,childTypeVal,childTypeField,workTypeNameVal,workTypeNameField,jobTypeVal,jobTypeFieldMenu,joblevelVal,jobLevelSearch,jobfreezeField"/>--%>
       
       <a4j:jsFunction name="changeCondition" action="#{pageBeanName.resetLines}"  
                         reRender="linesPanelId ,viewTablePN ,content_details,grsIntgHiddenValues ,OperationBar"/>
        </t:selectOneMenu>
        
      
        
         <h:outputText value="*" styleClass="mandatoryAsterisk" />
         <t:commandButton type="button"  onclick="goSearchDiv3();" id="okLoveButton3"  styleClass="cssButtonSmall" value="..." disabled="#{!pageBeanName.conditionsButtonsEnabled || pageBeanName.viewMode}" >

             <a4j:jsFunction name="goSearchDiv3"  action="#{pageBeanName.openSearchDiv3}" reRender="lov_dataT_data_panel,lov_paging_panel,lovDiv_btnsPnlGrd,lov_searchPanel,lov_searchPanel_radio ,viewTablePN,content_details ,OperationBar" 

                             oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
             </t:commandButton>
             
          <%--<t:commandButton type="button"  onclick="goview();" id="viewConditionDetails"  styleClass="cssButtonSmall" value="#{grsIntgResources.viewCondDetails}" 
                              disabled="#{!pageBeanName.linesButtonsEnabled}">
             <a4j:jsFunction name="goview"  action="#{pageBeanName.goView}" reRender="lov_dataT_data_panel,lov_paging_panel,lovDiv_btnsPnlGrd,lov_searchPanel" 
                             oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
             </t:commandButton>--%>
              <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="#{grsIntgResources.conditionDetails}"
                        actionListener="#{jcHelperBeanName.viewConditionDetails}"  disabled="#{!pageBeanName.linesButtonsEnabled }"
                        reRender="grsIntgHiddenValues" oncomplete="openGrsIntgConditionDetailsWindow();">
                     <f:attribute name="conditionCode" value="#{jcHelperBeanName.listSize == 0 ? null : list.conditionsDTO.code.key }"/>
                </a4j:commandButton>
             
             
         <t:outputLabel id="errorCodeId3" value="#{grsIntgResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none"/>
     </t:panelGroup>
    <t:panelGroup forceId="true" id="linesPanelId" colspan="4">
     <t:panelGrid  columns="4" width="100%" border="0" rendered="#{pageBeanName.showLines}" >
      <h:outputText styleClass="lable01 " value="#{grsIntgResources.line}"/>
           <t:panelGroup forceId="true" id="availbleLinPNG">
                <t:selectManyMenu forceId="true" id="availableLinesList" ondblclick="cAddOneFunction();"
                                  value="#{pageBeanName.toBeAddedLinesList}" styleClass="textboxlarge"
                                    style="width: 376px; min-height: 80px; margin-right: 10px;" disabled="#{pageBeanName.viewMode}">
                    <t:selectItems value="#{pageBeanName.availableLinesList}" var="types"
                                   itemValue="#{types.code.key}" itemLabel="#{types.name}"/>
                </t:selectManyMenu>
                
            </t:panelGroup>
            <t:panelGroup style="display: block; margin-left: 3px;" >
              
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cAddOneButton" type="button" disabled="#{pageBeanName.availableLinesListSize <= 1 || pageBeanName.viewMode}"
                                     onclick="cAddOneFunction();" styleClass="cssButtonSmaller" value=">"/>
                   <a4j:jsFunction name="cAddOneFunction" action="#{pageBeanName.addSelectedElements}"
                                    reRender="filterPanelGrp,availableLinesList,msgShow,OperationBar" oncomplete="changeVisibilityMsg();"/>
                </t:panelGroup>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cRemoveOneButton" type="button" disabled="#{pageBeanName.addedLinesListSize <= 1 || pageBeanName.viewMode}"
                                     onclick="cRemoveOneFunction();" styleClass="cssButtonSmaller" value="&lt;"/>
                    <a4j:jsFunction name="cRemoveOneFunction" action="#{pageBeanName.removeSelectedElements}"
                                    reRender="filterPanelGrp,OperationBar"/>
                </t:panelGroup>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="cRemoveAllButton" type="button" disabled="#{pageBeanName.addedLinesListSize <= 1 || pageBeanName.viewMode}"
                                     onclick="cRemoveAllFunction();" styleClass="cssButtonSmaller" value="&lt;&lt;"/>
                    <a4j:jsFunction name="cRemoveAllFunction" action="#{pageBeanName.removeAllElements}"
                                    reRender="filterPanelGrp,OperationBar"/>
                </t:panelGroup>
            </t:panelGroup>
            <t:panelGroup >
                <t:selectManyMenu forceId="true" id="selectedLinesList" ondblclick="cRemoveOneFunction();"
                                  value="#{pageBeanName.toBeRemovedLinesList}" styleClass="textboxlarge"
                                   style="width: 384px; min-height: 80px;"   disabled="#{pageBeanName.viewMode}">
                    <t:selectItems value="#{pageBeanName.addedLinesList}" var="selected"
                                   itemValue="#{selected.code.key}" itemLabel="#{selected.name}"/>
                </t:selectManyMenu>
            </t:panelGroup>
     </t:panelGrid>
  </t:panelGroup>
</t:panelGrid>
 <t:panelGroup forceId="true" id="viewTablePN" >
   <a4j:commandButton id="viewTable" action="#{pageBeanName.search}" value="#{grsIntgResources.view}" 
                       styleClass="cssButtonSmall" disabled="#{!pageBeanName.enableViewButton}"
                       style="margin-right:412px;" reRender="myDataTableId,filterPanelGrp ,viewTablePN ,paging_panel" rendered="#{!pageBeanName.viewMode}"/>
  
   <a4j:commandButton id="reEnterTable" action="#{pageBeanName.reEnterAgain}" value="#{grsIntgResources.reEnter}" 
                       styleClass="cssButtonSmall" disabled="#{!pageBeanName.enableViewButton}"
                       style="margin-right:412px;" reRender="myDataTableId,filterPanelGrp ,OperationBar,paging_panel" rendered="#{pageBeanName.viewMode}"/>
  </t:panelGroup>
</t:panelGroup>


  
<f:verbatim>
    <script language="javascript" type="text/javascript">
    // foucsJobFieldVal();
function toggleDivUsingCstmHeightHere(togglerId, cnt1DivId, cntDivId) {
    var collapsedHeight;
    var displayedHeight;
    if (document.getElementById(cnt1DivId).style.display == 'none') {
        document.getElementById(cnt1DivId).style.display = 'block';
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).style.height;
        document.getElementById('myDataTableId').style.height = '215';
        document.getElementById(togglerId).className = 'hideDivPnl';
        
    } else {
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).offsetHeight;
        document.getElementById(cnt1DivId).style.display = 'none';
        document.getElementById('myDataTableId').style.height = '410';
        document.getElementById(togglerId).className = 'showDivPnl';
        
    }
}
//---------------------------------------------------------------------------------
      function goToTable(e) {
          if (e.keyCode == 13) {
              changeJobCodeVal();
              e.preventDefault();
          }
      }

      function goToTablePage(e) {
          changeJobCodeVal();
      }

      function goChangeJobVal(e) {
          if (e.keyCode == 13) {
              changeJobVal();
              e.preventDefault();
          }
      }

      function goChangeLevelGroupVal(e) {
          if (e.keyCode == 13) {
              changeLevelGroup();
              e.preventDefault();
          }
      }

      function goChangeLevelsVal(e) {
          if (e.keyCode == 13) {
              changeLevels();
              e.preventDefault();
          }
      }

      function goChangeChildTypeVal(e) {
          if (e.keyCode == 13) {
              changeChildType();
              e.preventDefault();
          }
      }

      function goChangeWorkTypeNameVal(e) {
          if (e.keyCode == 13) {
              changeWorkTypeName();
              e.preventDefault();
          }
      }

      function goChangeJobTypeVal(e) {
          if (e.keyCode == 13) {
              changeJobType();
              e.preventDefault();
          }
      }

      function goJobLevelsVal(e) {
          if (e.keyCode == 13) {
              changeJobLevels();
              e.preventDefault();
          }
      }

      function ChangeWorkTypeNameValOnBlur() {
          changeWorkTypeName();
      }

      function ChangeChildTypeValOnBlur() {
          changeChildType();
      }

      function ChangeLevelsValOnBlur() {
          changeLevels();
      }

      function ChangeJobValOnBlur() {
          changeJobVal();
      }

      function ChangeGeneralValOnBlur() {
          changeLevelGroup();
      }

      function ChangeJobTypeValOnBlur() {
          changeJobType();
      }

          function ChangeJobLevelsValOnBlur() {
              changeJobLevels();
          }
   
    function foucsJobFieldVal(){        
//        if(document.getElementById('jobFieldVal') != null){            
//            document.getElementById('jobFieldVal').focus();
//        }
    }   
    </script>
</f:verbatim>
