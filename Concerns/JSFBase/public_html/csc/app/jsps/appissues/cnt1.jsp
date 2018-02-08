<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.csc.gn.sup.presentation.resources.sup" var="resourcesBundle"/>

<style>
.divContent1Dynamic {
  margin-top: -35px;
  max-height: 440px ! important;
}

.fileUploadFakeInputText {
    right: 0;
    left: auto;
}
.fileUploadFakeButton {
  left: auto;
  right: 205px;
}
        .fileUploadFakeButton {
            right: 285px;
        }


.fileUploadComponent {
    width: 283px;
}

</style>
<t:panelGrid id="contenet1_container" columns="4" border="0" width="100%" cellspacing="0" rowClasses="row01,row02"
             cellpadding="3">
    <!-- select category -->
    <h:outputText id="category_name" value="#{resourcesBundle.category}" styleClass="lable01"/>
    <t:panelGroup  style="display: block; width: 309px ! important;">
        <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text" value="#{pageBeanName.catCode}"
                     onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                     onblur="filterationInputOnBlur(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                     disabled="#{pageBeanName.disableMinCat}" onkeyup="enabelIntegerOnly(this);"
                     style="margin-left:5px;">
            <a4j:jsFunction name="changeCategoryVal" actionListener="#{pageBeanName.filterByCategory}"
                            oncomplete="resetMinistry();"
                            reRender="radio_type_group,MainDataPanel111,minGroupPanel,errorCodePanelId,errorCodePanelId2,systempOrmachine_pnl,systempOrmachine_pnld,scriptGenerator"/>
        </t:inputText>
        <t:selectOneMenu id="categoryList" forceId="true" styleClass="textboxmedium2" value="#{pageBeanName.catCode}"
                         disabled="#{pageBeanName.disableMinCat}"
                         onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.choose_an_element}"/>
            <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList"
                           itemValue="#{categoryList.code.catCode}" itemLabel="#{categoryList.name}"/>
            <a4j:jsFunction name="changeCategoryValD" actionListener="#{pageBeanName.filterByCategory}"
                            oncomplete="resetMinistry();"
                            reRender="radio_type_group,minGroupPanel,errorCodePanelId,errorCodePanelId2,systempOrmachine_pnl,systempOrmachine_pnld,scriptGenerator"/>
        </t:selectOneMenu>
        <t:panelGroup forceId="true" id="errorCodePanelId">
            <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="error" style="display:none"/>
        </t:panelGroup>
    </t:panelGroup>
    <!-- select ministry -->
    <h:outputText value="#{resourcesBundle.ministry}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="minGroupPanel" style="display: block; width: 325px ! important;">
        <t:inputText forceId="true" id="minId" styleClass="filteration_input_text" value="#{pageBeanName.minCode}"
                     disabled="#{pageBeanName.catCode == null ||  pageBeanName.disableMinCat }"
                     onkeypress="filterationInputOnKeyPress(event,this,'minListID','minListErrCodeId',focusOnPeriority);"
                     onblur="filterationInputOnBlur(event,this,'minListID','minListErrCodeId',focusOnPeriority);"
                     onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            <a4j:jsFunction name="focusOnPeriority" oncomplete="setFocusOnlyOnElement('problem_importanceID');"
                            action="#{pageBeanName.changeMinistry}"
                            reRender="radio_type_group,systempOrmachine_pnl,systempOrmachine_pnld,scriptGenerator,coord_pnl"/>
        </t:inputText>
        <t:panelGroup>
            <t:selectOneMenu id="minListID" forceId="true" styleClass="textboxmedium2" value="#{pageBeanName.minCode}"
                             disabled="#{pageBeanName.catCode == null ||  pageBeanName.disableMinCat }"
                             onchange="filterationDropboxOnChange(event,this,'minId','minListErrCodeId',focusOnPeriority);">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.ministryList}" var="ministryList"
                               itemValue="#{ministryList.code.minCode}" itemLabel="#{ministryList.name}"/>
                <a4j:jsFunction action="#{pageBeanName.changeMinistry}" name="changeMinValD"
                                reRender="radio_type_group,systempOrmachine_pnl,systempOrmachine_pnld,scriptGenerator,coord_pnl"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="errCodePanelId2" colspan="3">
            <t:outputLabel id="minListErrCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="error" style="display:none"/>
        </t:panelGroup>
        <c:requiredFieldValidator componentToValidate="minListID" highlight="false" display="dynamic"
                                  errorMessage="#{globalResources.missingField}" group="addValidation"/>
    </t:panelGroup>
    <!-- select problem importance (periority) -->
    <%--<h:outputText value="#{resourcesBundle.problem_importance}" styleClass="lable01"/>--%>
    <%--<t:panelGroup forceId="true" id="problem_importancePanel">
        <t:inputText forceId="true" id="problem_importanceID" styleClass="filteration_input_text"
                     value="#{pageBeanName.problemImportanceCode}"
                     onkeypress="filterationInputOnKeyPress(event,this,'problem_importanceListID','errCodeId3',changeImportanceVal);"
                     onblur="filterationInputOnBlur(event,this,'problem_importanceListID','errCodeId3',changeImportanceVal);"
                     onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            <a4j:jsFunction name="focusOnCats" oncomplete="setFocusOnlyOnElement('problem_catID');"/>
        </t:inputText>
        <t:selectOneMenu id="problem_importanceListID" forceId="true" styleClass="textboxmedium2"
                         value="#{pageBeanName.problemImportanceCode}"
                         onchange="filterationDropboxOnChange(event,this,'problem_importanceID','errCodeId3',changeImportanceVal);">
            --%><%-- <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>--%><%--
            <t:selectItems value="#{pageBeanName.problemImportanceList}" var="ministryList"
                           itemValue="#{ministryList.code.plmpriortyCode}" itemLabel="#{ministryList.name}"/>
            <a4j:jsFunction name="changeImportanceVal"/>
        </t:selectOneMenu>
        <t:panelGroup forceId="true" id="errCodePanelId3" style="display:block;width:250px;">
            <t:outputLabel id="errCodeId3" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="error" style="display:none"/>
        </t:panelGroup>
    </t:panelGroup>--%>
    <!-- select problem cat -->
    <h:outputText value="#{resourcesBundle.problem_cat}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="problem_catPanel" >
        <t:inputText forceId="true" id="problem_catID" styleClass="filteration_input_text"
                     value="#{pageBeanName.problemCatCode}"
                     onkeypress="filterationInputOnKeyPress(event,this,'problem_catListID','errCodeId4',changecatsVal);"
                     onblur="filterationInputOnBlur(event,this,'problem_catListID','errCodeId4',changecatsVal);"
                     onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"></t:inputText>
        <t:selectOneMenu id="problem_catListID" forceId="true" styleClass="textboxmedium2"
                         value="#{pageBeanName.problemCatCode}"
                         onchange="filterationDropboxOnChange(event,this,'problem_catID','errCodeId4',changecatsVal);">
            <%-- <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>--%>
            <t:selectItems value="#{pageBeanName.problemCatList}" var="catsList" itemValue="#{catsList.plmcatCode}"
                           itemLabel="#{catsList.name}"/>
            <a4j:jsFunction name="changecatsVal"/>
        </t:selectOneMenu>
        <t:panelGroup forceId="true" id="errCodePanelId4" style="display:block;width:250px;">
            <t:outputLabel id="errCodeId4" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="error" style="display:none"/>
        </t:panelGroup>
    </t:panelGroup>
    <!-- select problem type radio -->
    <h:outputText value="#{resourcesBundle.problem_type_label}" styleClass="lable01"/>
    <t:panelGroup  id="radio_type_group" forceId="true">
        <t:selectOneRadio id="options" value="#{pageBeanName.problemType}" style="align:center;"
                          disabled="#{pageBeanName.minCode == null}">
            <f:selectItem itemValue="#{pageBeanName.SYSTEM_PROBLRM}" itemLabel="#{resourcesBundle.systems}"/>
            <f:selectItem itemValue="#{pageBeanName.MACHINE_PROBLRM}" itemLabel="#{resourcesBundle.machines}"/>
            <f:selectItem itemValue="#{pageBeanName.NETWORK_PROBLRM}" itemLabel="#{resourcesBundle.networks}"/>
            <a4j:support event="onclick" actionListener="#{pageBeanName.changeProblemType}"
                         reRender="myForm:content1Div,systempOrmachine_pnl,systempOrmachine_pnld,scriptGenerator,coord_pnl"/>
        </t:selectOneRadio>
    </t:panelGroup>
    <!---- options panel -->
    <t:panelGroup forceId="true" id="systempOrmachine_pnl">
        <!---- SYSTEMS------------------------>
        <!---- MACHINES------------------------>
        <!---- N------------------------>
        <h:outputText value="#{resourcesBundle.system_lbl}" styleClass="lable01"
                      rendered="#{pageBeanName.problemType == pageBeanName.SYSTEM_PROBLRM}"/>
        <h:outputText value="#{resourcesBundle.machine_lbl}" styleClass="lable01"
                      rendered="#{pageBeanName.problemType == pageBeanName.MACHINE_PROBLRM }"/>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="systempOrmachine_pnld" colspan="3">
        <t:inputText forceId="true" id="filterModuleID" styleClass="filteration_input_text"
                     value="#{pageBeanName.selectedModuleCode}" onkeyup="enabelIntegerOnly(this);"
                     style="margin-left:5px;" disabled="#{pageBeanName.minCode == null}"
                     onkeypress="filterDivInputOnKeyPress(event,this,filterModules,null);"
                     rendered="#{pageBeanName.problemType ==  pageBeanName.SYSTEM_PROBLRM}"
                     onblur="filterDivInput('filterModuleID',filterModules,null);">
            <a4j:jsFunction name="filterModules" actionListener="#{pageBeanName.getModuleNodeByCode}"
                            reRender="myForm:content1Div,systempOrmachine_pnld,coord_pnl,scriptGenerator"/>
        </t:inputText>
        <t:inputText forceId="true" id="system_name" disabled="true" styleClass="textboxlarge"
                     rendered="#{pageBeanName.problemType ==  pageBeanName.SYSTEM_PROBLRM}"
                     value="#{pageBeanName.selectedModuleDesc}"/>
        <t:inputText forceId="true" id="filterMchID" styleClass="filteration_input_text"
                     value="#{pageBeanName.selectedMachineCode}" onkeyup="enabelIntegerOnly(this);"
                     style="margin-left:5px;" onkeypress="filterDivInputOnKeyPress(event,this,filterMachines,null);"
                     disabled="#{pageBeanName.minCode == null}"
                     rendered="#{pageBeanName.problemType == pageBeanName.MACHINE_PROBLRM}"
                     onblur="filterDivInput('filterMchID',filterMachines,null);">
            <a4j:jsFunction name="filterMachines" actionListener="#{pageBeanName.getMachineByCode}"
                            reRender="coord_pnl, systempOrmachine_pnld"/>
        </t:inputText>
        <t:inputText forceId="true" id="machine_name" disabled="true" styleClass="textboxlarge"
                     rendered="#{pageBeanName.problemType == pageBeanName.MACHINE_PROBLRM}"
                     value="#{pageBeanName.selectedMachineDesc}"/>
        <a4j:commandButton value="#{globalResources.Available}" id="showmachines_btn" styleClass="cssButtonSmaller"
                           disabled="#{pageBeanName.minCode == null}"
                           rendered="#{pageBeanName.problemType == pageBeanName.MACHINE_PROBLRM}"
                           reRender="customDiv2,machines_dataT_data_panel,machines_dataT_data_panel"
                           action="#{pageBeanName.showMachinesDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.customDiv2);"/>
        <%--<a4j:commandButton value="#{globalResources.Available}" id="showSystems_btn" styleClass="cssButtonSmaller"
                           disabled="#{pageBeanName.minCode == null}"
                           rendered="#{pageBeanName.problemType ==  pageBeanName.SYSTEM_PROBLRM}"
                           reRender="customDiv1,machines_dataT_data_panel,machines_dataT_data_panel"
                           action="#{pageBeanName.showModulesDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>--%>
        <a4j:commandButton value="#{globalResources.Available}"  id="showSystems_btn"  styleClass="cssButtonSmaller"
                           disabled="#{pageBeanName.minCode == null}"
                           rendered="#{pageBeanName.problemType ==  pageBeanName.SYSTEM_PROBLRM}"
                           reRender="divTree,machines_dataT_data_panel,machines_dataT_data_panel"
                           action="#{pageBeanName.openTreeDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.divTree);"/>
       <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{pageBeanName.problemType == pageBeanName.MACHINE_PROBLRM || pageBeanName.problemType ==  pageBeanName.SYSTEM_PROBLRM }"/>
         <t:outputLabel id="errCodessId4" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                       styleClass="error"
                       rendered="#{(pageBeanName.problemType == pageBeanName.MACHINE_PROBLRM && pageBeanName.wrongMachineSerial) || (pageBeanName.problemType ==  pageBeanName.SYSTEM_PROBLRM && pageBeanName.wrongModuleSerial) }"/>
        
        <c:requiredFieldValidator componentToValidate="filterMchID" highlight="false" display="dynamic"
                                  active="#{ addAppProblemBean.problemType == addAppProblemBean.MACHINE_PROBLRM }"
                                  errorMessage="#{globalResources.missingField}" group="addValidation"/>
       <c:requiredFieldValidator componentToValidate="filterModuleID" highlight="false" display="dynamic"
             active="#{ addAppProblemBean.problemType == addAppProblemBean.SYSTEM_PROBLRM }"
             errorMessage="#{globalResources.missingField}" group="addValidation"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.diwaan_cord}" styleClass="lable01"/>
    <t:panelGroup colspan="1" id="coord_pnl" forceId="true">
        <%--t:inputText forceId="true" id="coord_input" disabled="true" styleClass="textboxmedium" value="#{pageBeanName.coordinatorName}"/--%>
   
      <t:selectOneMenu id="ministrytypevalue" value="#{pageBeanName.selectedCoordCode}" styleClass="textboxmedium" 
     disabled="#{empty pageBeanName.coordList}" style="width:130px">
     <t:selectItems value="#{pageBeanName.coordList}" var="coordlist"
                           itemValue="#{coordlist.code.key}" itemLabel="#{coordlist.name}"/>
     <a4j:support event="onchange" actionListener="#{pageBeanName.changeCoord}"
                         reRender="myForm:content1Div,scriptGenerator,coord_pnl"/>   
                         
    </t:selectOneMenu>
    </t:panelGroup>
    
    <t:panelGroup colspan="2" id="pnlGrp" forceId="true" rendered="#{pageBeanName.disableMinCat}">
    </t:panelGroup>
   
    <t:outputLabel value="#{resourcesBundle.problemSource_Label}" styleClass="lable01" rendered="#{!pageBeanName.disableMinCat}"/>
        <t:panelGroup id="problemSourcesPnl" forceId="true" style="display: block; width: 325px ! important;" rendered="#{!pageBeanName.disableMinCat}">
            <t:selectOneMenu id="problemSourcesListId" forceId="true" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedProblemSourceCode}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.choose_an_element}"/>
                <t:selectItems value="#{pageBeanName.problemSourcesList}" var="problemSourcesList"
                               itemValue="#{problemSourcesList.plmsourceCode}" itemLabel="#{problemSourcesList.name}"/>
            </t:selectOneMenu>
            <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
            <%--<f:verbatim>
                <br/>
            </f:verbatim>--%>
            <%--<c:requiredFieldValidator componentToValidate="problemSourcesListId" highlight="false" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" group="problemSourceValidation"/>--%>
        </t:panelGroup>    
        
      
        
        <h:outputText value="#{resourcesBundle.crd_phone}" styleClass="divtext"/>
    <t:panelGroup >
    <t:inputText   forceId="true" id="coordinatorPhone_edit" value="#{pageBeanName.coordinatorDTO.phone}" 
   styleClass="textboxmedium" disabled="true"/>
    </t:panelGroup>  

<h:outputText value="#{resourcesBundle.crd_email}" styleClass="divtext"/>
    <t:panelGroup >
    <t:inputText   forceId="true" id="coordinatorEmail_edit" value="#{pageBeanName.coordinatorDTO.email}" 
    styleClass="textboxmedium" disabled="true"/>
    </t:panelGroup>  


<h:outputText value="#{resourcesBundle.crd_fax}" styleClass="divtext"/>
    <t:panelGroup >
    <t:inputText   forceId="true" id="coordinatorFax_edit" value="#{pageBeanName.coordinatorDTO.fax}" 
     styleClass="textboxmedium" disabled="true"/>
    </t:panelGroup>  
    
        
<h:outputText value="#{resourcesBundle.other_info}" styleClass="divtext"/>
    <t:panelGroup  colspan="3">
    <t:inputText   forceId="true" id="coordinatorOtherInfo_edit" disabled="true" value="#{pageBeanName.coordinatorDTO.otherInfo}" 
    styleClass="textboxmedium"/>
    </t:panelGroup>  
        
      
    <h:outputText value="#{resourcesBundle.problem_title_Label}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
            <t:inputTextarea forceId="true" id="req_Title" style=" width: 700px;"
             styleClass="textboxmedium" value="#{pageBeanName.pageDTO.problemTitle}"/>
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="req_Title" highlight="false" display="dynamic"
                                  errorMessage="#{globalResources.missingField}" group="addValidation"/>
    </t:panelGroup>

    <h:outputText value="#{resourcesBundle.req_desc}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
            <t:inputTextarea forceId="true" id="req_desc" style=" width: 700px; height: 130px;" value="#{pageBeanName.pageDTO.problemDesc}"/>
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="req_desc" highlight="false" display="dynamic"
                                  errorMessage="#{globalResources.missingField}" group="addValidation"/>
    </t:panelGroup>
    
    <t:outputText value="#{resourcesBundle.applicant_Name}" styleClass="lable01"/>
    
         <t:inputText disabled="true" value="#{pageBeanName.applicantName}" styleClass="textboxmedium"/>
    
    
    <h:outputText value="#{resourcesBundle.applicant_phone_no}" styleClass="lable01"/>
 
        <t:inputText forceId="true" id="phone_num" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.contactDetails}"/>
    </t:panelGrid>
    

<jsp:include page="/app/jsps/appissues/attachmentdiv.jsp"/>   
     
 
    

<t:panelGroup style="display: block;width:100%;text-align:center;" id="add_request_btns_pnl">
    <t:commandButton forceId="true" id="add_request_SaveButton" styleClass="cssButtonSmall"
                     value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="return validatemyForm('addValidation');"/>
       
    <%--<t:commandButton forceId="true" id="add_request_backButton" styleClass="cssButtonSmall"
                     value="#{globalResources.back}" action="#{pageBeanName.back}"/>--%>
</t:panelGroup>
        
 <htm:script type="text/javascript" >
  function resetMinistry() {
      copyDropdownIntoInputtext('minListID', 'minId');
      setFocusOnlyOnElement('minId');
      document.getElementById('minListErrCodeId').style.display = "none";

  }

  function reserrCodessId4() {
      document.getElementById('errCodessId4').style.display = "none";
  }
  
  
  function filterDivInputOnKeyPress(e, inputtext, ajaxFunction, nextFocusId) {
          var event = e || window.event;
          if (event.keyCode == 13) {
              
              event.stopPropagation();
             event.preventDefault();
              trimBorders(inputtext.value);
              if (inputtext.value == '') {
                  if (nextFocusId != null) {
                      setFocusOnElement(nextFocusId);
                  }
                 event.stopPropagation();
                  event.preventDefault();
                  return false;
              }

              if (ajaxFunction != null) {
                  ajaxFunction();
              }
              else {
//                  event.stopPropagation();
//                  event.preventDefault();
              }
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }

          }
      }

      function filterDivInput(inputtextName, ajaxFunction, nextFocusId) {
          inputtext = document.getElementById(inputtextName);
          trimBorders(inputtext.value);
          if (inputtext.value == '') {
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }
              //              event.stopPropagation();
              //              event.preventDefault();
              return false;
          }

          if (ajaxFunction != null) {
              ajaxFunction();
          }
          else {
              //              event.stopPropagation();
              //              event.preventDefault();
          }
          if (nextFocusId != null) {
              setFocusOnElement(nextFocusId);
          }
      }

</htm:script>




<t:saveState value="#{pageBeanName.attachmentList}"/>
<t:saveState value="#{pageBeanName.coordList}"/>
<t:saveState value="#{pageBeanName.selectedCoordCode}"/>