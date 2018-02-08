<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"
           prefix="c"%>
<f:view locale="#{shared_util.locale}">
 <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration"
               var="resourcesBundle"/>
  <h:form id="myForm" binding="#{raiseKidsBean.frm}">
  <t:aliasBean alias="#{pageBeanName}" value="#{raiseKidsBean}">
  <t:aliasBean alias="#{detailBeanName}" value="#{raiseKidsBean}">
    
   <tiles:insert definition="kidsDetail.page" flush="false">
   
        <t:saveState value="#{govEmpMaintainBean.realCivilId}"/>
     <t:saveState value="#{govEmpMaintainBean.civilID}"/>
     <t:saveState value="#{govEmpMaintainBean.empName}"/>
     <t:saveState value="#{govEmpMaintainBean.preSelectedCivilId}"/>
     <t:saveState value="#{govEmpMaintainBean.enableTabs}"/>
     <t:saveState value="#{govEmpMaintainBean.empHireStatusList}"/>
     <t:saveState value="#{govEmpMaintainBean.bundleName}"/>
     <t:saveState value="#{govEmpMaintainBean.statusMsgKey}"/>
     <t:saveState value="#{govEmpMaintainBean.titlePageKey}"/>
     <t:saveState value="#{govEmpMaintainBean.ministryCode}"/>

     <!-- base contents savestate-->
    <t:saveState value="#{merRaiseMaintainBean.civilExist}"/>
    <t:saveState value="#{merRaiseMaintainBean.civilId}"/>
    <t:saveState value="#{merRaiseMaintainBean.validCivilId}"/>
    <t:saveState value="#{merRaiseMaintainBean.empHired}"/>
    <t:saveState value="#{merRaiseMaintainBean.empHiredInMin}"/>
    <t:saveState value="#{merRaiseMaintainBean.empValidNatSal}"/>
    <t:saveState value="#{merRaiseMaintainBean.payrollInfoExist}"/>
    <t:saveState value="#{merRaiseMaintainBean.degree}"/>
    <t:saveState value="#{merRaiseMaintainBean.employeesDTO}"/>
     <t:saveState value="#{merRaiseMaintainBean.detailFilterMethod}"/>
       <t:saveState value="#{merRaiseMaintainBean.femaleGender}"/>
    <t:saveState value="#{raiseKidsBean.civilExist}"/>
    <t:saveState value="#{raiseKidsBean.civilId}"/>
    <t:saveState value="#{raiseKidsBean.validCivilId}"/>
    <t:saveState value="#{raiseKidsBean.empHired}"/>
    <t:saveState value="#{raiseKidsBean.empHiredInMin}"/>
    <t:saveState value="#{raiseKidsBean.payrollInfoExist}"/>
    <t:saveState value="#{raiseKidsBean.degree}"/>
    <t:saveState value="#{raiseKidsBean.employeesDTO}"/>
    <t:saveState value="#{raiseKidsBean.empWives}"/>
    <t:saveState value="#{raiseKidsBean.selMother}"/>
    <t:saveState value="#{raiseKidsBean.newChildMother}"/>
    <t:saveState value="#{raiseKidsBean.newChildMotherForEdit}"/>
    <t:saveState value="#{raiseKidsBean.disableEdit}"/>
    
    
    <!--t:saveState value="#{raiseKidsAddDecisionBean.salEmpChildrenDTO}"/-->
    <%--<t:saveState value="#{raiseKidsAddDecisionBean.civilId}"/>--%>
    
    <t:saveState value="#{raiseKidsBean.searchQuery}"/>
    <t:saveState value="#{raiseKidsBean.searchTyp}"/>

    <t:saveState value="#{raiseKidsBean.singleSelection}"/>
    <t:saveState value="#{raiseKidsBean.myTableData}"/>
    <t:saveState value="#{raiseKidsBean.highlightedDTOS}"/>
    <t:saveState value="#{raiseKidsBean.searchMode}"/>
    <t:saveState value="#{raiseKidsBean.selectedDTOS}"/>
    <t:saveState value="#{raiseKidsBean.fullColumnName}"/>
    <t:saveState value="#{raiseKidsBean.sortAscending}"/>
    <t:saveState value="#{raiseKidsBean.pageDTO}"/>
    <t:saveState value="#{raiseKidsBean.pageMode}"/>
    <t:saveState value="#{raiseKidsBean.selectedRadio}"/>
    <t:saveState value="#{raiseKidsBean.divValidCivil}"/>
    <t:saveState value="#{raiseKidsBean.divCivilExist}"/>
    <t:saveState value="#{raiseKidsBean.hasSameParentID}"/>
    <t:saveState value="#{merRaiseMaintainBean.detailResetMethod}"/>
    <t:saveState value="#{merRaiseMaintainBean.notDefinedGenderTypeFlag}"/>
    <t:saveState value="#{merRaiseMaintainBean.maritalStatusSingle}"/>
    <t:saveState value= "#{raiseKidsBean.showSuspendPartAddDiv}" />
    <t:saveState value="#{raiseKidsBean.divCivilId}" />
    <t:saveState value="#{raiseKidsBean.handicapStatusList}" />
    
    <t:saveState value="#{maritalStatusBean.maritalStatusSingle}"/>
   <t:messages showDetail="true"/>
   <tiles:put name="titlepage" type="string" value="${govEmpMaintainBean.titlePageKey}"></tiles:put>
   </tiles:insert>
  
  </t:aliasBean>
  </t:aliasBean>
  <t:panelGroup forceId="true" id="scriptPanelID">
      <c:scriptGenerator form="myForm"/>
</t:panelGroup>
<script type="text/javascript"> 

setFocusFirstElem();
function setFocusFirstElem(){
    if( (!isVisibleComponent('lovEmp'))){
       if(document.getElementById('myForm:resetData_btn_id')!=null)
            setFocusOnlyOnElement('myForm:resetData_btn_id'); 
       else 
           setFocusOnlyOnElement('CivilIdAdd');
    }
}       


            function addDivFocus(){
//                alert(document.getElementById('add_first_inputTxt'));
//                alert(document.getElementById('add_first_inputTxt').disabled);
                if(document.getElementById('add_first_inputTxt').disabled == false){ 
                    settingFoucsAtDivAdd();        
                }
                else
                {
                    setFocusOnlyOnElement('myForm:resetData_btn_div_id');
                }
            }
    
    function resetMsgInAdd()
    {
        if(document.getElementById('invalCivilID') != null)
        {
            document.getElementById('invalCivilID').innerHTML='';
        } 
        if(document.getElementById('empHired')!=null)
        {
            document.getElementById('empHired').innerHTML='';
        }
        if(document.getElementById('payrollInfoExist')!=null)
        {
            document.getElementById('payrollInfoExist').innerHTML='';
        }
        if (document.getElementById('empHiredInMin') != null) {
            document.getElementById('empHiredInMin').innerHTML = '';
        }
    }
    
   function checkRadio_CivilId2(radioId,searchQueryID,errCivilMsg,emptyMsg,noErrMsg) {
       //alert("checkRadio_CivilId222");
        var radio;
        var searchStr;
        var seachValidationType;
        //radio = document.getElementById('radioId');
        if(document.forms['myForm'] != null){
            radio =document['forms']['myForm'][radioId];
        }
        //alert("2 : radio = "+radio);
        if( !radio[0].checked && !radio[1].checked) {
             if(document.getElementById('errorMessage2')!=null)
                document.getElementById('errorMessage2').innerHTML = selectOneRadio;
            return false;
        }
        if(document.getElementById('errorMessage2')!=null)
           document.getElementById('errorMessage2').innerHTML = '';
           
       if(document.getElementById(searchQueryID)!=null)
         searchStr = document.getElementById(searchQueryID).value;
        //alert("searchStr = "+searchStr);
        if(checkEmpty(searchStr))
        {
            document.getElementById('errorMessage2').innerHTML = emptyMsg;
            return false;
        }
       
       if(radio[0].checked ) {
       //alert("checkNumber(searchStr) = "+checkNumber(searchStr));
              if(checkNumber(searchStr))
               { 
                   //alert("checkNumber(searchStr) = "+checkNumber(searchStr));
                   if(!checkLength(searchQueryID,'12'))
                    {
                        document.getElementById('errorMessage2').innerHTML = errCivilMsg;
                        return false;
                    }
                    else
                    {
                        //alert("search civil id ok");
                        return true;
                    }
               }
               document.getElementById('errorMessage2').innerHTML = noErrMsg;
                return false;      
          }     
        /*else if(radio[1].checked) {
             return checkStringOnly2(searchStr,'errorMessage2',null);
        }*/
        //alert("search name ok");
        return true;
        
    }    
    function hideDiv() {
        try{
            obj = document.getElementById('iFrame');
            obj.style.visibility = 'hidden';
        }catch(exception) {}
        // window.blocker.style.visibility="hidden";
        document.getElementById("divSearch").style.visibility="hidden";
        
        if(document.getElementById('errorMessage2')!=null) 
            document.getElementById('errorMessage2').innerHTML = '';
        
        var cancelSearchArr = document.getElementsByClassName("OB_cancleSearch");
        if (cancelSearchArr.length == 0){
        if(document.getElementById('searchChildrenSearchString')!=null) 
            document.getElementById('searchChildrenSearchString').value='';
        }
          
        window.parent.nav_btn.disabled=false;     
    }
   
</script> 
        
   </h:form>
</f:view>
