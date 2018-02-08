<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
 
 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
 
 <h:form id="myForm" binding="#{settlementDecisionCycleEmployeesMaintainBean.frm}">
  
  <t:aliasBean alias="#{pageBeanName}" value="#{settlementDecisionCycleMaintainBean}">
   <t:aliasBean alias="#{detailBeanName}" value="#{settlementDecisionCycleEmployeesMaintainBean}">
    
    <tiles:insert definition="settlementdecisioncycleemployeesmaintain.page" flush="false">
     <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 ? 'addSettlementDecisions' : pageBeanName.maintainMode==1 ? 'editSettlementDecisions' : pageBeanName.maintainMode==2 ? 'settlementDecision' : ''}"></tiles:put>
        
        <t:saveState value="#{pageBeanName.pageDTO}"/>
        <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
        <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
        <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
        <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
        <t:saveState value="#{pageBeanName.currentStep}"/>
        <t:saveState value="#{pageBeanName.maintainMode}"/>
        <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
        <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
        <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{pageBeanName.dataLoadedBefore}"/> 
        <t:saveState value="#{pageBeanName.lisBeanName}"/>
        <t:saveState value="#{pageBeanName.backMethodName}"/> 
         <t:saveState value="#{pageBeanName.backNavCase}"/>
         <t:saveState value="#{pageBeanName.showOnly}"/>
        <%--<t:saveState value="#{pageBeanName.cancelDecisionMode}"/>--%>
        <%--<t:saveState value="#{specialExtraDecisionCycleMaintainBean.showOnly}"/>--%>
        <t:saveState value="#{detailBeanName.resetButton}"/>
        <%--<t:saveState value="#{detailBeanName.currentDetails}"/>--%>
        <%--<t:saveState value="#{detailBeanName.availableDetails}"/>--%>
        <%--<t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>--%>
        <%--<t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>--%>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>
        <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
        <t:saveState value="#{detailBeanName.isSearchMode}"/>
        <t:saveState value="#{detailBeanName.content1Div}"/>
        <t:saveState value="#{detailBeanName.divMainContentMany}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.typesDTOKey}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.typesDTOKey}"/> 
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.decisionMakers}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
        <%--<t:saveState value="#{specialExtraDecisionCycleEmployeesMaintainBean.advanceEmployeesAddBean.savedData}"/>--%>
        
        <%--<t:saveState value="#{detailBeanName.fullColumnName}"/>--%>
        <%--<t:saveState value="#{detailBeanName.sortAscending}"/>--%>
        <%--<t:saveState value="#{detailBeanName.currentPageIndex}"/>--%>
        <%--<t:saveState value="#{detailBeanName.oldPageIndex}"/>--%>
        <%--<t:saveState value="#{detailBeanName.sorting}"/>--%>
        <%--<t:saveState value="#{detailBeanName.usingPaging}"/>--%>
        <%--<t:saveState value="#{detailBeanName.resettedPageIndex}"/>--%>
       
        
        <%--<t:saveState value="#{detailBeanName.pagingRequestDTO}"/>--%>
        <t:saveState value="#{detailBeanName.civil_exist}"/>
        <%--<t:saveState value="#{detailBeanName.pagingBean.totalListSize}"/>--%>
        <%--<t:saveState value="#{detailBeanName.pagingBean.myPagedDataModel}"/>--%>
        <%--<t:saveState value="#{detailBeanName.currentDisplayDetails}"/>--%>
        <%--<t:saveState value="#{detailBeanName.pagingBean.preUpdatedDataModel}"/>--%>
        
        <t:saveState value="#{wizardbar.wizardSteps}"/>
        <t:saveState value="#{wizardbar.configurationId}"/>
        <t:saveState value="#{wizardbar.currentStep}"/>   
        
        <t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>
        
   
       
       
            <%--t:saveState value="#{detailBeanName.yearsList}"/>
            <t:saveState value="#{detailBeanName.monthsList}"/>
            <t:saveState value="#{detailBeanName.monthKey}"/>
            <t:saveState value="#{detailBeanName.yearsKey}"/>
            <t:saveState value="#{detailBeanName.settMonthKey}"/>
            <t:saveState value="#{detailBeanName.settYearsKey}"/>
            <t:saveState value="#{detailBeanName.editSearchDate}"/>
             <t:saveState value="#{detailBeanName.benifitList2}"/>
             <t:saveState value="#{detailBeanName.benfitsList}"/>
             <t:saveState value="#{detailBeanName.deductionsList}"/>
             <t:saveState value="#{detailBeanName.deductionsList2}"/>
             <t:saveState value="#{detailBeanName.benfitsListSize}"/>
             <t:saveState value="#{detailBeanName.deductionsListSize}"/>
              <t:saveState value="#{detailBeanName.currentLoadedDecision}"/>
              <t:saveState value="#{detailBeanName.benfitsTotalValue}"/>
             <t:saveState value="#{detailBeanName.dedectionsTotalValue}"/>
             <t:saveState value="#{detailBeanName.totalSalary}"/>
             <t:saveState value="#{detailBeanName.removedSalElementGuideList}"/>
             <t:saveState value="#{detailBeanName.disableMonthAndYear}"/>
             <t:saveState value="#{detailBeanName.dataReadyToBeRemoved}"/>
             
             <t:saveState value="#{detailBeanName.elmGuidesInsurRatiosMap}"/>
             
             
             

             <t:saveState value="#{detailBeanName.initialListForBenefitsAndDeductions}"/--%>
             
             
             
             <t:saveState value="#{detailBeanName.employeesDTO}"/>
             <t:saveState value="#{detailBeanName.civilID}"/>
            
           
             <t:saveState value="#{detailBeanName.showPagingBar}"/>
             <t:saveState value="#{detailBeanName.empName}"/>
             <t:saveState value="#{detailBeanName.validCivilId}"/>
             <t:saveState value="#{detailBeanName.civilExist}"/>
             <t:saveState value="#{detailBeanName.empHired}"/>
             <t:saveState value="#{detailBeanName.empHiredInMin}"/>
             <t:saveState value="#{detailBeanName.payrollInfoExist}"/>
            
             
            <%--<t:saveState  value="#{settlementDecisionsListBean.fullColumnName}" />
        <t:saveState  value="#{settlementDecisionsListBean.sortAscending}" />
        <t:saveState  value="#{settlementDecisionsListBean.saveSortingState}" />
        <t:saveState  value="#{settlementDecisionsListBean.sortColumn}" />
        <t:saveState value="#{settlementDecisionsListBean.cancelDescisionFlag}"/>--%>
        <%--<t:saveState value="#{settlementDecisionsListBean.selectedRegTypeKey}"/>--%>
       <%--<t:saveState value="#{settlementDecisionsListBean.decisionSearchDTO}"/>--%>
       <%--<t:saveState value="#{settlementDecisionsListBean.selectedTypeKey}"/>--%>
       <%--<t:saveState value="#{settlementDecisionsListBean.myTableData}"/>--%>
       <t:saveState value="#{pageBeanName.currentStag}"/>
       
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
       
        <%--<t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.formsList}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.selectedForm}"/>
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.selectedFormRadio}" />
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.formsSearchMode}" />
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.ascendingForm}" />
        <t:saveState value="#{settlementDecisionCycleMainDataMaintainBean.sortFormColumn}" />--%>
        
        
    </tiles:insert>
   </t:aliasBean>
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
   var valid = false ;
   var missingValue =  '${resourcesBundle.missingCivilID}'; 
   var civilLengthError ='${resourcesBundle.civilLengthError}';
   valid =  validateRequiredElement('civilIdInTxt',missingValue,'errorCivilMessage') && checkExactLength('12','civilIdInTxt','errorCivilMessage',civilLengthError); 
   
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
