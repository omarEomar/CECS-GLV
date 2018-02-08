<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="resourcesBundle"/>
 
 <h:form id="myForm" binding="#{financeDecisionCycleEmployeesMaintainBean.frm}">
  
  <t:aliasBean alias="#{pageBeanName}" value="#{financeDecisionCycleMaintainBean}">
   <t:aliasBean alias="#{detailBeanName}" value="#{financeDecisionCycleEmployeesMaintainBean}">
    
    <tiles:insert definition="financedecisioncycleemployeesmaintain.page" flush="false">
        <t:messages showDetail="true" forceId="true" id="errorsMsgsId"/>
        
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
        <%--<t:saveState value="#{pageBeanName.cancelDecisionMode}"/>--%>
        <t:saveState value="#{pageBeanName.showOnly}"/>
        <t:saveState value="#{detailBeanName.enableElementGuideCombo}"/>
        <t:saveState value="#{detailBeanName.currentDetails}"/>
        <t:saveState value="#{detailBeanName.availableDetails}"/>
        <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
        <t:saveState value="#{detailBeanName.highlightedDTOS}"/>
        <t:saveState value="#{detailBeanName.month}"/>
        <t:saveState value="#{detailBeanName.year}"/>
        <t:saveState value="#{pageBeanName.months}"/>
        <t:saveState value="#{pageBeanName.years}"/>
        <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>
        <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
        <t:saveState value="#{detailBeanName.isSearchMode}"/>
        <t:saveState value="#{detailBeanName.content1Div}"/> 
        <t:saveState value="#{detailBeanName.tempResponse}"/> 
        <t:saveState value="#{detailBeanName.divMainContentMany}"/>
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.typesDTOKey}"/>
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.yearsDTOKey}"/>
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.decisionMakers}"/>
        <t:saveState value="#{pageBeanName.multiOrSingleEmps}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.civilID}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentDisplayedSalVariedElmGuideDTOList}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentSalVariedElmGuideDTOList}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.salVariedElmGuidesDataTable}"/>
        <%--<t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.advanceEmployeesAddBean.savedData}"/>--%>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.empFullName}"/>
        <t:saveState value="#{detailBeanName.fullColumnName}"/>
        <t:saveState value="#{detailBeanName.sortAscending}"/>
        <t:saveState value="#{detailBeanName.currentPageIndex}"/>
        <t:saveState value="#{detailBeanName.oldPageIndex}"/>
        <t:saveState value="#{detailBeanName.sorting}"/>
        <t:saveState value="#{detailBeanName.usingPaging}"/>
        <t:saveState value="#{detailBeanName.resettedPageIndex}"/>
        
        
        <t:saveState value="#{detailBeanName.civil_exist}"/>
       
            
      
        <t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
        <%--<t:saveState value="#{detailBeanName.pagingBean.preUpdatedDataModel}"/>--%>
        <t:saveState value="#{detailBeanName.elementGuideCode}"/>
        <t:saveState value="#{detailBeanName.selectedElementGuideCode}"/>
        <t:saveState value="#{detailBeanName.elementGuideTypeList}"/>
        <t:saveState value="#{detailBeanName.elementGuideList}"/>
        <t:saveState value="#{detailBeanName.selectedElementGuideName}"/>
        <t:saveState value="#{detailBeanName.validElmguide}"/>
        <t:saveState value="#{detailBeanName.showErrorMsg1}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.countOfAllEmpDecision}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentListSize}"/>
      
        
        <t:saveState value="#{wizardbar.wizardSteps}"/>
        <t:saveState value="#{wizardbar.configurationId}"/>
        <t:saveState value="#{wizardbar.currentStep}"/>   
        
        <t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>
        
   
        <%--<t:saveState value="#{decisionCycleMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>--%>
        
        <%--t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.valueNum}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.valueChr}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.valueDate}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.empInfTypeCode}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.booleanInformEmpFlag}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.informEmpFlag}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.informEmpDate}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.informTypeList}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.notes}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.empDecisionsDTOSlelected}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.activeValidationOnAdd}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.activeValidationOnEdit}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.addedEmpSuccessfully}"/>
        <t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.countOfAllEmpDecision}"/--%>
        
        <%--<t:saveState value="#{advanceEmployeesAddBean.usingBsnPaging}" />--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.usingPaging}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.currentPageIndex}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.oldPageIndex}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.updateMyPagedDataModel}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.resettedPageIndex}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.pagingRequestDTO}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.pagingBean.totalListSize}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.pagingBean.myPagedDataModel}"/>--%>
        <%--<t:saveState value="#{advanceEmployeesAddBean.pagingBean.preUpdatedDataModel}"/>--%>
         <%--<t:saveState value="#{financeDecisionCycleEmployeesMaintainBean.currentDetails}"/>--%> 
         <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.editorReturn}" />
            <t:saveState  value="#{financialDecisionsListBean.fullColumnName}" />
        <t:saveState  value="#{financialDecisionsListBean.sortAscending}" />
        <t:saveState  value="#{financialDecisionsListBean.saveSortingState}" />
        <t:saveState  value="#{financialDecisionsListBean.sortColumn}" />
        <t:saveState value="#{financialDecisionsListBean.cancelDescisionFlag}"/>
        <%--<t:saveState value="#{financialDecisionsListBean.selectedRegTypeKey}"/>--%>
       <t:saveState value="#{financialDecisionsListBean.decisionSearchDTO}"/>


            <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{financialDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{financialDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{financialDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.sorting}"/>
            <t:saveState value="#{financialDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{financialDecisionsListBean.indexArray}"/>
            <t:saveState value="#{financialDecisionsListBean.ascending}"/>
            <t:saveState value="#{financialDecisionsListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.selectedTypeKey}"/>
            <t:saveState value="#{financialDecisionsListBean.searchMode}"/>
        <%-- // End Apply Business_Pagging ..--%>

       <t:saveState value="#{pageBeanName.currentStag}"/>
       
       
       
        <%--t:saveState value="#{financeDecisionCycleMainDataMaintainBean.formsList}"/>
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.selectedForm}"/>
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.selectedFormRadio}" />
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.formsSearchMode}" />
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.ascendingForm}" />
        <t:saveState value="#{financeDecisionCycleMainDataMaintainBean.sortFormColumn}" /--%>
        
        <t:saveState value="#{financialDecisionsListBean.pagingBean.myPagedDataModel}"/>
        <t:saveState value="#{detailBeanName.fullColumnName}"/>
        <t:saveState value="#{detailBeanName.sortAscending}"/>
        <t:saveState value="#{pageBeanName.currentStag}"/>
        <t:saveState value="#{pageBeanName.currentStag}"/>
        <t:saveState value="#{pageBeanName.pageDTO.empDecisionsDTOList}"/>
        
        
        

        <t:saveState value="#{detailBeanName.currentPageIndex}"/>
        <t:saveState value="#{detailBeanName.oldPageIndex}"/>
        <t:saveState value="#{detailBeanName.sorting}"/>
        <t:saveState value="#{detailBeanName.usingPaging}"/>
        <t:saveState value="#{detailBeanName.updateMyPagedDataModel}"/>
        <t:saveState value="#{detailBeanName.resettedPageIndex}"/>
        
        <t:saveState value="#{detailBeanName.pagingRequestDTO}"/>
        
        <t:saveState value="#{detailBeanName.pagingBean.totalListSize}"/>
        <t:saveState value="#{detailBeanName.pagingBean.myPagedDataModel}"/>
        <t:saveState value="#{detailBeanName.pagingBean.preUpdatedDataModel}"/>
        
        
        <t:saveState value="#{detailBeanName.totalValue}"/>
         <t:saveState value="#{detailBeanName.felsValue}"/>
          <t:saveState value="#{detailBeanName.dinarValue}"/>
           <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{financialDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{financialDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{financialDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{financialDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{financialDecisionsListBean.sorting}"/>
            <t:saveState value="#{financialDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{financialDecisionsListBean.indexArray}"/>
            <t:saveState value="#{financialDecisionsListBean.ascending}"/>
            <t:saveState value="#{financialDecisionsListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{detailBeanName.civilExist}"/>
            <t:saveState value="#{detailBeanName.elmGuideExist}"/>
        <%-- // End Apply Business_Pagging ..--%>
     <tiles:put name="titlepage" type="string" value="${ pageBeanName.maintainMode == 0 ? 'addRegVariedDecisions' : pageBeanName.maintainMode == 1 ? 'editRegVariedDecisions' : ''}"></tiles:put>
     <tiles:put name="content1" value="${ pageBeanName.multiOrSingleEmps == 0 ? '/integration/reg/jsps/decisionFinanceCycle/decisionemployeesmaintaincontent_1.jsp' : '/integration/reg/jsps/decisionFinanceCycle/decisionemployeesmaintaincontent1.jsp' }"></tiles:put>
        
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
    
/*moved from  by a.Nour*/
  document.getElementById('myForm:ava').focus();
   function setFocusAtMyFirstElement(){
       document.getElementById("myForm:content1Div").scrollTop = 0;
       if(document.getElementById('civilIdInTxt') !=null)
             setFocusOnlyOnElement('civilIdInTxt');
     }   
//  function validateRequiredElement(listOfControls,message,listOfMessageId){    
//    var tempx = new Array();    
//    var errx = new Array();    
//    flag=true;
//    tempx = listOfControls.split('#');
//    errx = listOfMessageId.split('#');
//    for (i=0;i<errx.length;i++){
//        document.getElementById(errx[i]).innerHTML = '';
//        if(document.getElementById(tempx[i]).value==''){
//            flag=false;
//            document.getElementById(errx[i]).innerHTML = message;
//        }
//    }    
//    return flag;
//}

function disableCharacters(field) {
    //alert("field.value = "+field.value);
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g,"");
    }
}

  function validateRequiredGuideElement(listOfControls,message,listOfMessageId){ 
//    var tempx = new Array();    
//    var errx = new Array();    
    flag=true;
//    tempx = listOfControls;
//    errx = listOfMessageId;
//alert(document.getElementById('empCountId'));

    var empCount = document.getElementById('empCountId').value;
  //  alert(empCount);
    for (i=0;i<empCount;i++){
        document.getElementById(listOfMessageId+"["+i+"]").innerHTML = '';
        var id = listOfControls+"["+i+"]";
        var obj = document.getElementById(id);
        //alert(i);
      //  alert(obj);
      //  alert(obj.value);
        if(typeof (obj) == 'undefined' || obj == null || (obj!= null && obj.value=='') || obj.value==0){
            flag=false;
            document.getElementById(listOfMessageId+"["+i+"]").innerHTML = message;
        }
    }    
    return flag;
}

function stepValidation(){
 var returnFromCmpareDates=validateTwoDatesInDataTable();
 
 if(returnFromCmpareDates)
    {
        //if(typeof doValidatemyForm != 'undefined'){
            if(doValidatemyForm){
             var missingValue =  '${resourcesBundle.missingReqField}'; 
             if(validateRequiredGuideElement('denar_number',missingValue,'errorValueMessage')){
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

</script>
 </h:form>
</f:view>
