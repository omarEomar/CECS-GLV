<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
 
 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
 
 <h:form id="myForm" binding="#{settlementDetailsBean.frm}">
  
 
   <t:aliasBean alias="#{pageBeanName}" value="#{settlementDetailsBean}">
    
    <tiles:insert definition="settDetail.page" flush="false">
     
        
        <t:saveState value="#{settlementDecisionCycleMaintainBean.pageDTO}" id="pageDTO_s"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.nextNavigationCase}" id="nextNavigationCase_s"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.previousNavigationCase}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.finishNavigationCase}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.currentNavigationCase}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.currentStep}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.maintainMode}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.detailsSavedStates}" id="detailsSavedStates"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.renderSave}" id="mainmode2"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.dataLoadedBefore}"/> 
        <t:saveState value="#{settlementDecisionCycleMaintainBean.lisBeanName}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.backMethodName}"/> 
        <t:saveState value="#{settlementDecisionCycleMaintainBean.backNavCase}"/>
        <t:saveState value="#{settlementDecisionCycleMaintainBean.showOnly}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.currentDetails}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.availableDetails}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.selectedCurrentDetails}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.selectedAvailableDetails}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.currentDisplayDetails}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.empAddedBefore}"/>

        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.masterEntityKey}" id="mentitykey"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.finishButtonOverride}" id="mainmode5"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.isSearchMode}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.content1Div}"/>
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.divMainContentMany}"/>
      
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.typesDTOKey}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.typesDTOKey}"/> 
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.decisionMakers}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
     
        <t:saveState value="#{settlementDetailsBean.yearsList}"/>
        <t:saveState value="#{settlementDetailsBean.monthsList}"/>
        <t:saveState value="#{settlementDetailsBean.monthKey}"/>
        <t:saveState value="#{settlementDetailsBean.yearsKey}"/>
        <t:saveState value="#{settlementDetailsBean.settMonthKey}"/>
        <t:saveState value="#{settlementDetailsBean.settYearsKey}"/>
        <t:saveState value="#{settlementDetailsBean.editSearchDate}"/>
         <t:saveState value="#{settlementDetailsBean.maintainMode}"/>
         <t:saveState value="#{settlementDetailsBean.isEmpUnderInsurance}"/>
        
        <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.civil_exist}"/>
       
        
        <t:saveState value="#{wizardbar.wizardSteps}"/>
        <t:saveState value="#{wizardbar.configurationId}"/>
        <t:saveState value="#{wizardbar.currentStep}"/>   
        
        <t:saveState value="#{settlementDecisionCycleMaintainBean.copyDecisionWithEmployeesMode}"/>
        
      
      <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{settlementDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{settlementDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{settlementDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{settlementDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{settlementDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{settlementDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{settlementDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{settlementDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{settlementDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{settlementDecisionsListBean.sorting}"/>
            <t:saveState value="#{settlementDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{settlementDecisionsListBean.indexArray}"/>
            <t:saveState value="#{settlementDecisionsListBean.ascending}"/>
            <t:saveState value="#{settlementDecisionsListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{settlementDecisionsListBean.selectedTypeKey}"/>
            <t:saveState value="#{settlementDecisionsListBean.searchMode}"/>
        <%-- // End Apply Business_Pagging ..--%>  
       
       
             <t:saveState value="#{settlementDetailsBean.benifitList2}"/>
             <t:saveState value="#{settlementDetailsBean.benfitsList}"/>
             <t:saveState value="#{settlementDetailsBean.deductionsList}"/>
             <t:saveState value="#{settlementDetailsBean.deductionsList2}"/>
             <t:saveState value="#{settlementDetailsBean.benfitsListSize}"/>
             <t:saveState value="#{settlementDetailsBean.deductionsListSize}"/>
             <t:saveState value="#{settlementDetailsBean.employeesDTO}"/>
             <t:saveState value="#{settlementDetailsBean.civilID}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.civilID}"/>             
             <t:saveState value="#{settlementDetailsBean.currentLoadedDecision}"/>

             <t:saveState value="#{settlementDetailsBean.showPagingBar}"/>
           
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.validCivilId}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.civilExist}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.empHired}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.empHiredInMin}"/>
             <t:saveState value="#{settlementDecisionCycleEmployeesMaintainBean.payrollInfoExist}"/>
             <t:saveState value="#{settlementDetailsBean.benfitsTotalValue}"/>
             <t:saveState value="#{settlementDetailsBean.dedectionsTotalValue}"/>
             <t:saveState value="#{settlementDetailsBean.totalSalary}"/>
             <t:saveState value="#{settlementDetailsBean.removedSalElementGuideList}"/>
             <t:saveState value="#{settlementDetailsBean.disableMonthAndYear}"/>
             <t:saveState value="#{settlementDetailsBean.dataReadyToBeRemoved}"/>
             <!--/*CSC-15730 A.Nour*/-->
             <t:saveState value="#{settlementDetailsBean.elmGuidesInsurRatiosMap}"/>
             
             <%--<t:saveState value="#{settlementDetailsBean.dataLoadedBefore}"/>--%>
             <t:saveState value="#{settlementDetailsBean.initialListForBenefitsAndDeductions}"/>
             
           
            <t:saveState value="#{settlementDecisionCycleMaintainBean.currentStag}"/>
       
       
       <t:saveState value="#{settlementDetailsBean.dto}"/>
       <t:saveState value="#{settlementDetailsBean.maintainMode}"/>
        
        
    </tiles:insert>
   </t:aliasBean>

 <t:panelGroup  id="scriptPanel" forceId="true">
  <c2:scriptGenerator form="myForm"/>
 </t:panelGroup>
 
  <script type="text/javascript"> 
        setFocusAtMyFirstElement();
      //  checkSaveButtonFirstVisit();
        
        function setFocusAtMyFirstElement(){
            setFocusOnElement('addButton');
        }
        
        function setFocusAtMyAddDiv(){
            setFocusOnlyOnElement('maintain_regType_div'); 
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnlyOnElement('CancelButtonDelAlertDiv');
        }
        
    function checkSaveButtonFirstVisit()
    {
     if(document.getElementById('SaveButton')!=null)
     {
         if(document.getElementById('add_first_inputTxt').value!='' || document.getElementById('add_second_inputTxt').value!='' || document.getElementById('add_third_inputTxt').value!='')
           {
            document.getElementById('SaveButton').disabled=false;
            document.getElementById('financialEmpValueNumId').style.visibility='visible';
            document.getElementById('financialEmpValueCharId').style.visibility='visible';
            document.getElementById('financialEmpValueDateId').style.visibility='visible';
           }
           else
           {
            document.getElementById('financialEmpValueNumId').style.visibility='hidden';
            document.getElementById('financialEmpValueCharId').style.visibility='hidden';
            document.getElementById('financialEmpValueDateId').style.visibility='hidden';
           }
      }
    }
    
    function checkSaveButtonEditFirstVisit()
    {
     if(document.getElementById('SaveButtonEdit')!=null)
     {
         if((document.getElementById('informEmpTypeListMenu').value!='' || document.getElementById('edit_third_inputTxt').value!='' || document.getElementById('edit_first_inputTxt').value!='') || document.getElementById('maintain_informEmpFlag').checked==true  )
           {
            document.getElementById('SaveButtonEdit').disabled=false;
           }
      }
    }

/**
 * @param emptyList = false means :: both list are empty or not exist 
 * @param valid = false means:: civilID is not valid
 * @param flagStatus= false means:: both or one list exist but no value entered
 * @author ALaa.elmasry
 * 12-02-2015
 * */

function validateCivilAndTwoTables(){

 var msgObj = document.getElementById('valueChangeErrorMsg');
   msgObj.style.display='none';
 var mustExistListErrMsg = document.getElementById('emplyeeErrorMsgJS');
   mustExistListErrMsg.style.display='none';
/**
 * check civilId first
 */
   var valid = true ;
   var missingValue =  '${resourcesBundle.missingCivilID}'; 
   var civilLengthError ='${resourcesBundle.civilLengthError}';
  // valid =  validateRequiredElement('civilIdInTxt',missingValue,'errorCivilMessage') && checkExactLength('12','civilIdInTxt','errorCivilMessage',civilLengthError); 
   
/*****************************/   
/**
 * check benifit list and deduction list are exist or not
 */
   var flagBenifitExist = true;
   var flagDeductionExist = true;
   var benifitListCount = document.getElementById('myForm:dataT_data');
   var deductionListCount = document.getElementById('myForm:deductionsList_data');
   if(typeof (benifitListCount) == 'undefined' || benifitListCount== null || benifitListCount.length == 0){
       flagBenifitExist = false;
   }
   if(typeof (deductionListCount) == 'undefined' || deductionListCount== null || deductionListCount.length == 0){
      flagDeductionExist = false;
   }
/**
* check benifit list then check dedeuction list (must change at least one value at on list to add new settelment decision)
*/
  //first case two list are null
  var emptyList = true;
  if(!flagBenifitExist){
      if(!flagDeductionExist){
          //ToDo Message
          mustExistListErrMsg.style.display='inline';
          emptyList = false;
      }
  }
  //second case benifitListis not null 
 
    var flagStatus=true;
    if(flagBenifitExist){
       flagStatus = validateBenifits(benifitListCount);
       //if flagStatus = false it mean no value entered in denar or fels fields
       // then validate second list (deduction list)
       if(!flagStatus && flagDeductionExist ){
       flagStatus = validateDeductions(deductionListCount); 
       }
       // then check Deductionlist if benifit list is null
       //and Deductionlistnot null and flagStatus still false
    }else if( flagDeductionExist) {
       flagStatus = validateDeductions(deductionListCount);
    }
    
    if (!flagStatus){
        msgObj.style.display='block';
    }
    
    //
    return flagStatus && valid && emptyList;
}

function validateBenifits(listCount){ 
//var msgObj = document.getElementById('valueChangeErrorMsg');
//   msgObj.style.display='none';
//  var listCount = document.getElementById('myForm:dataT_data');
    var benifitFlagStatus=false;
        var denarObj;
        var felsObj;
    for (i=0;i< listCount.rows.length;i++){
        denarObj = document.getElementById('denar_number'+"["+i+"]");
        felsObj = document.getElementById('fels_number'+"["+i+"]");
        if( denarObj != null &&  denarObj.value!='' && denarObj.value!='0'){
            benifitFlagStatus=true;
        }
        if( felsObj != null &&  felsObj.value!='' && felsObj.value!='0'){
            benifitFlagStatus=true;
        }
    }
    return benifitFlagStatus ;

}

function validateDeductions(listCount){ 
// var listCount = document.getElementById('myForm:deductionsList_data');
        var deductionFlagStatus=false;
        var denarObj;
        var felsObj;
    for (i=0;i< listCount.rows.length;i++){
     
        denarObj = document.getElementById('deductionDenar_number'+"["+i+"]");
        felsObj = document.getElementById('deductionFels_number'+"["+i+"]");
        
        if( denarObj != null &&  denarObj.value!=''){
            deductionFlagStatus=true;
        }
        if( felsObj != null &&  felsObj.value!=''){
            deductionFlagStatus=true;
        }
    }
    return deductionFlagStatus;
}

function resetErrorMsg(i){
   document.getElementById('valueChangeErrorMsg'+"["+i+"]").innerHTML='';
}

function stepValidation(){
 var returnFromCmpareDates=validateTwoDatesInDataTable();
 
 if(returnFromCmpareDates)
    {
        //if(typeof doValidatemyForm != 'undefined'){
            if(doValidatemyForm){
            
             if(validateCivilAndTwoTables()){
                return validatemyForm();
               }else{
                return false ; 
               }
               
            }
        //}
        return true;   
    }
    
    return false;
 }

 // document.getElementById('myForm:ava').focus();
   function setFocusAtMyFirstElement(){
       document.getElementById("myForm:content1Div").scrollTop = 0;
       if(document.getElementById('civilIdInTxt') !=null)
             setFocusOnlyOnElement('civilIdInTxt');
     }   
  function validateRequiredElement(listOfControls,message,listOfMessageId){        
    document.getElementById('errorCivilMessage1').innerHTML = '';
    var tempx = new Array();    
    var errx = new Array();    
    flag=true;
    tempx = listOfControls.split('#');
    errx = listOfMessageId.split('#');
    
    for (i=0;i<errx.length;i++){
        document.getElementById(errx[i]).innerHTML = '';
        if(document.getElementById(tempx[i]).value==''){
            flag=false;
            document.getElementById(errx[i]).innerHTML = message;
            
        }
    }    
    return flag;
}

function disableCharacters(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g,"");
    }
}
function showSettlementErrorMsg(){
    if(document.getElementById('ErrMsg') != null && document.getElementById('ErrMsg').value != '')
   {
       changeVisibilityDiv(window.blocker,window.divMsg);
   }
}

  function collapse() {
      toggleDivUsingCustHeight('hideDivImgId', 'pg_main_Employee_Details', 'tablesPanel');
  }
      
      function toggleDivUsingCustHeight(togglerId, cnt1DivId, cntDivId) {
          if (document.getElementById(cnt1DivId).style.display == 'none') {
              document.getElementById(cnt1DivId).style.display = 'block';
              document.getElementById(togglerId).className = 'hideDivPnl';
              //alert(111111);
              //document.getElementById(cnt1DivId).style.height = "203px";
              document.getElementById('benfitsList_panel').style.height = "80px";
              document.getElementById('deductionsList_panel').style.height = "80px";
          }
          else {
          //alert(222);
              document.getElementById(cnt1DivId).style.display = 'none';
              document.getElementById(togglerId).className = 'showDivPnl';
              document.getElementById(cntDivId).style.height = "1100px";
              //document.getElementById('benfitsList_panel').style.height = "155px";
              //document.getElementById('deductionsList_panel').style.height = "155px";
              document.getElementById('benfitsList_panel').style.height = "153px";
              document.getElementById('deductionsList_panel').style.height = "153px";
          }
      }
      
      function keepDivUsingCustHeight(togglerId, cnt1DivId, cntDivId) {
          if (document.getElementById(cnt1DivId).style.display == 'none') {
              document.getElementById('benfitsList_panel').style.height = "153px";
              document.getElementById('deductionsList_panel').style.height = "153px";
          }
          else {
              document.getElementById('benfitsList_panel').style.height = "80px";
              document.getElementById('deductionsList_panel').style.height = "80px";
          }
      }

    window.onkeydown = function handleTab(e){
    if(e.keyCode == 13 ) {
      var notsearchMode = (document.getElementById('searchButton') != null) ? true : false;
          if (notsearchMode) {
              document.getElementById('searchButton').click;
          }
      }

    }
</script>
 </h:form>
</f:view>
