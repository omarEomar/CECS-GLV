<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>



<t:panelGroup  forceId="true" style="width:100%" id="emp_query_panel_id">


<t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="lable01"/>
    <t:inputText rendered="#{detailBeanName.preSelectedCivilId}" value="#{detailBeanName.realCivilId}" styleClass="textbox" disabled="true"/>
    <t:panelGroup rendered="#{!detailBeanName.preSelectedCivilId}"  colspan="3">
        <t:inputText onkeyup="disableCharacters(this);" rendered="#{!pageBeanName.civilExist}" onblur="document.getElementById('civil_exist_btn').focus();" maxlength="12" forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{pageBeanName.civilId}" onkeypress="FireButtonClick('civil_exist_btn')"/>
        <t:inputText   styleClass="textbox"  value="#{pageBeanName.civilId}" rendered="#{pageBeanName.civilExist}" />
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{!pageBeanName.civilExist}" id="civil_exist_btn" forceId="true" onclick="resetMsgInAdd();return validatemyForm();"  
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{pageBeanName.init}"/>
       
 <t:commandButton rendered="#{pageBeanName.civilExist}" id="resetButton_id" forceId="true" onclick="foucsFirstElementOnListPage();resetMsgInAdd();return validatemyForm();" 
           value="#{globalResources.reSetButton}"  styleClass="cssButtonSmall"  action="#{pageBeanName.reSetData}"/>
        <f:verbatim> &nbsp; </f:verbatim>
        <a4j:commandButton type="button"  value="#{globalResources.SearchButton}" styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();" onblur="foucsFirstElementOnListPage();" action="#{pageBeanName.showSearchForEmployeeDiv}" 
               reRender="lovEmp,mainDataEmpPanel" rendered="#{!pageBeanName.civilExist}" />       
        <f:verbatim ><br/></f:verbatim>
        <c2:requiredFieldValidator     active="#{!govEmpMaintainBean.preSelectedCivilId && !govEmpFirstStepBean.civilExist}" componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:regularExpressionValidator active="#{!govEmpMaintainBean.preSelectedCivilId && !govEmpFirstStepBean.civilExist}" componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic"/>
        
        <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empHired" value="#{pageBeanName.statusMsgString}"  rendered="#{!pageBeanName.validEmpStatus}" styleClass="errMsg"/>
    </t:panelGroup>
   <t:outputText  value="#{resourcesBundle.personal_information_identity_no}" styleClass="lable01"/>
    
    <t:panelGroup colspan="3">
    <t:inputText value="" styleClass="textbox" disabled="true"/>
    </t:panelGroup>
    
    <t:outputText  value="#{resourcesBundle.employeeName}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
        <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" styleClass="textboxlarge" style="width: 475px;" disabled="true"/>
    </t:panelGroup>
   
   <t:outputText  value="#{resourcesBundle.social_status}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.maritalStatusDTO.name}" styleClass="textbox" disabled="true"/>
   
   
   <t:outputText  value="#{resourcesBundle.nationalityType}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.countriesDTO.name}" styleClass="textbox" disabled="true"/>
   
   
   
   <t:outputText  value="#{resourcesBundle.personal_information_job_name}"  styleClass="lable01"/>
   <t:panelGroup colspan="3">
   <t:inputText value="#{pageBeanName.jobTechName}" styleClass="textboxlarge" disabled="true" style="width: 475px;"/>
   </t:panelGroup>
   
    <t:outputText  value="#{resourcesBundle.gender}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.genderTypesDTO.name}" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.personal_information_religion}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.religionsDTO.name}" styleClass="textbox" disabled="true" forceId="true" id="hireDateTxt"/> 

   
</t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
    
    foucsFirstElementOnListPage();
    function foucsFirstElementOnListPage(){        
        if(isVisibleComponent('lovEmp') || isVisibleComponent('lovEmpPaging')){
          
            settingFoucsAtEmpLovDiv();
            settingFoucsAtEmpLovDiv();
        }else if(document.getElementById('CivilIdAdd') != null){            
            document.getElementById('CivilIdAdd').focus();
            document.getElementById('CivilIdAdd').focus();
           }
           else if(document.getElementById('resetButton_id') != null) {
            document.getElementById('resetButton_id').focus();
            document.getElementById('resetButton_id').focus();
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
    }
    
</script>
