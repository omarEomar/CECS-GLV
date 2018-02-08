<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"
           prefix="c2"%>
<f:view locale="#{shared_util.locale}">
 <f:loadBundle basename="com.beshara.csc.hr.emp.integration.presentation.resources.integration"
               var="resourcesBundle"/>
  <h:form id="myForm" binding="#{raiseWivesBean.frm}">
  <t:aliasBean alias="#{pageBeanName}" value="#{raiseWivesBean}">
  <t:aliasBean alias="#{detailBeanName}" value="#{raiseWivesBean}">

    
   <tiles:insert definition="wivesDetail.page" flush="false">
   
   
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

    <%--<t:saveState value="#{raiseKidsBean.pageDTO}"/>--%>
    <%--<t:saveState value="#{raiseKidsBean.selectedDTOS}"/>--%>
    
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
     
    <t:saveState value="#{raiseWivesBean.civilExist}"/>
    <t:saveState value="#{raiseWivesBean.marriageDate}"/>
    <t:saveState value="#{raiseWivesBean.civilId}"/>
    <t:saveState value="#{raiseWivesBean.validCivilId}"/>
    <t:saveState value="#{raiseWivesBean.empHired}"/>
     <t:saveState value="#{raiseWivesBean.empHiredInMin}"/>
    <t:saveState value="#{raiseWivesBean.payrollInfoExist}"/>
    <t:saveState value="#{raiseWivesBean.degree}"/>
    <t:saveState value="#{raiseWivesBean.employeesDTO}"/>
    <t:saveState value="#{pageBeanName.myTableData}"/>
    <t:saveState value="#{pageBeanName.salEmpWifesDTO}"/>
    
    <t:saveState value="#{pageBeanName.showEdit}"/>
    <t:saveState value="#{raiseWivesBean.selectedPageDTO}" id="wifeselectedPageDTO"/>
    <t:saveState value="#{pageBeanName.pageDTO}"/>
   <t:saveState value="#{raiseWivesBean.highlightedDTOS}" id="wifehighlightedDTOS"/>
   <t:saveState value="#{pageBeanName.searchMode}"/>
   <t:saveState value="#{pageBeanName.selectedDTOS}"/>
   <t:saveState value="#{pageBeanName.fullColumnName}"/>
   <t:saveState value="#{pageBeanName.sortAscending}"/>
   <t:saveState value="#{pageBeanName.searchMode}"/>
   <t:saveState value="#{raiseWivesBean.selectedRadio}" id="wifeselectedRadio"/>
   <t:saveState value="#{detailBeanName.wivesSuspensionReason}" />
   <t:saveState value="#{raiseWivesBean.pageMode}" />
   <t:saveState value="#{raiseWivesBean.divValidCivil}"/>
   <t:saveState value="#{merRaiseMaintainBean.detailResetMethod}"/>
   <t:saveState value="#{merRaiseMaintainBean.notDefinedGenderTypeFlag}"/>
   <t:saveState value="#{merRaiseMaintainBean.maritalStatusSingle}"/>
   <t:saveState value="#{maritalStatusBean.maritalStatusSingle}"/>
   <t:saveState value="#{detailBeanName.kwtCitizensResidentsDTO}" />
   <t:saveState value="#{raiseWivesBean.addWifeFemalFlag}"/>
    <t:saveState value="#{raiseWivesBean.divCivilExist}"/>
    <t:saveState value="#{raiseWivesBean.showSuspendPartAddDiv}"/>
    <t:saveState value="#{detailBeanName.pageDTO}" />
    <t:saveState value="#{raiseWivesBean.divCivilId}" />
    <t:saveState value="#{raiseWivesBean.wifeSonInfoDTO}" />
    <t:saveState value="#{merRaiseMaintainBean.femaleGender}"/>

   <t:messages/>
   <tiles:put name="titlepage" type="string" value="${govEmpMaintainBean.titlePageKey}"></tiles:put>
   </tiles:insert>
  
  </t:aliasBean>
  </t:aliasBean>
    <t:panelGroup forceId="true" id="scriptGenGroup">
      <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
      
        <script type="text/javascript"> 
        
            setFocusFirstElem();
            
            function setFocusFirstElem(){
                if( (!isVisibleComponent('delConfirm')) && (!isVisibleComponent('delAlert')) 
                        && (!isVisibleComponent('lookupAddDiv')) && (!isVisibleComponent('lookupEditDiv')) && (!isVisibleComponent('customDiv1')) 
                        && (!isVisibleComponent('lovEmp'))){
                    if(document.getElementById('myForm:resetData_btn_id')!=null)
                        setFocusOnlyOnElement('myForm:resetData_btn_id'); 
                    else 
                        setFocusOnlyOnElement('CivilIdAdd');
                }
            }
            
            function setFocusAtAdd(){
                if( (!isVisibleComponent('delConfirm')) && (!isVisibleComponent('delAlert')) 
                        && (!isVisibleComponent('lookupAddDiv')) && (!isVisibleComponent('lookupEditDiv')) && (!isVisibleComponent('customDiv1')) 
                        && (!isVisibleComponent('lovEmp'))){
                    if(document.getElementById('myForm:addButton')!=null)
                        setFocusOnlyOnElement('myForm:addButton'); 
                    else 
                        setFocusOnlyOnElement('resetData_btn_id');
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
            
            
            
            function susAvailable(){
                changeVisibilityDiv(window.blocker,window.customDiv1);
                setFocusOnlyOnElement('selectedKidsSuspenionElements');   
            }
            
            
            
            function susNotAvailable(){
                
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
            
        </script>      
   </h:form>
</f:view>
