<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:saveState value="#{pageBeanName.bnkBrnchNameMsg}" />
<t:saveState value="#{pageBeanName.coordinatorExistBefore}" />

<t:panelGroup forceId="true" id="branch_coordinators_panel"  colspan="6">
<t:outputText id="civilInList" forceId="true" value="#{integrationBundle.civilInListError}" styleClass="errMsg" rendered="#{pageBeanName.civilExist}" />
<t:panelGrid id="masterPanel" align="center" columns="4" border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >

    <h:outputText value="#{integrationBundle.branch_bank_code}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="5" forceId="true" id="bankCode" value="#{pageBeanName.bankDTO.code.key}" styleClass="textbox"  />
    
    <h:outputText value="#{integrationBundle.banks_name_label}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="coBankName" value="#{pageBeanName.bankDTO.name}" disabled="true"/>
    

</t:panelGrid>

<t:panelGrid id="citizenPanel" align="center" columns="4" border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >
    <h:outputText value="#{integrationBundle.civil_id}" styleClass="divtext" />
    
    <t:panelGroup colspan="3" rendered="#{pageBeanName.divMode == 1}">
    <t:inputText onkeypress="FireButtonClick('filterByCivilButton')" disabled="#{pageBeanName.validCivilId}" maxlength="12" 
                forceId="true" id="CivilIdAdd" value="#{pageBeanName.civilId}" styleClass="textbox"  />
    <t:outputText id="civilTxt" forceId="true" value="#{globalResources.missingField}" styleClass="errMsg" style="display:none;"/>
    <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>    
            <t:commandButton  forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}" styleClass="cssButtonSmall"
                                onclick="searchAndvalidate();" rendered="#{!newBranchListBean.validCivilId}"/>
            <a4j:jsFunction name="searchLines" action="#{newBranchListBean.filterByCivilId}" reRender="branch_coordinators_panel,citizenPanel,buttonsGrid"/>
        <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}" oncomplete="setFocusFirstElem();" 
                        rendered="#{pageBeanName.validCivilId}"  styleClass="cssButtonSmall"  actionListener="#{newBranchListBean.reCoordinatorSetData}"  
                        reRender="branch_coordinators_panel,citizenPanel,buttonsGrid,scriptGenerator,scriptPanelID"/>
        <f:verbatim>&nbsp;</f:verbatim>    
       

        <c:requiredFieldValidator  componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="CivilIdAddUniqueId"/>        
        <c:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/" errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false" display="dynamic"/>        
        <f:verbatim><br/></f:verbatim>
        <t:outputText id="coordinatorExistBeforeID" forceId="true" value="#{pageBeanName.bnkBrnchNameMsg}" styleClass="errMsg" rendered="#{newBranchListBean.coordinatorExistBefore}"/>
    </t:panelGroup>
    
    <t:panelGroup colspan="3" rendered="#{pageBeanName.divMode == 2}">
    <t:inputText disabled="true" maxlength="12" 
                forceId="true" id="CivilIdAdd2" value="#{pageBeanName.civilId}" styleClass="textbox"  />
    </t:panelGroup>
    
    <h:outputText value="#{integrationBundle.citizen_name}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="5" forceId="true" id="citizenName" value="#{pageBeanName.kwtCitizenDTO.fullName}" styleClass="textboxmedium"  />
    
    <h:outputText value="#{integrationBundle.citizen_gender}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="5" forceId="true" id="citizenGender" value="#{pageBeanName.kwtCitizenDTO.genderTypesDTO.gentypeName}" styleClass="textbox"  />    
    
    <h:outputText value="#{integrationBundle.citizen_email}" styleClass="divtext"/>
    <t:panelGroup>
    <t:inputText forceId="true" id="txtCitizenEmail" value="#{pageBeanName.civilEmail}" styleClass="textbox"  />
    <t:outputText id="civilMailExample" forceId="true" value="#{integrationBundle.emailFormat}" styleClass="warningClass_cust1"/>
    <f:verbatim><br/></f:verbatim>
    <t:outputText id="citizenMailError" forceId="true" value="#{integrationBundle.emailErrorMsg}" styleClass="errMsg" style="display:none;"/>    
    </t:panelGroup>
    
    <h:outputText value="#{integrationBundle.citizen_tel}" styleClass="divtext"/>
    <t:panelGroup>
    <t:inputText forceId="true" id="txtCitizenTel" value="#{pageBeanName.civilTel}" styleClass="textbox"  />            
    </t:panelGroup>
    
    <h:outputText value="#{integrationBundle.citizen_mob}" styleClass="divtext"/>
    <t:inputText forceId="true" id="txtCitizenMob" value="#{pageBeanName.civilMobile}" styleClass="textbox"  />                
    
    <h:outputText value="#{integrationBundle.citizen_fac}" styleClass="divtext"/>
    <t:inputText forceId="true" id="txtCitizenFax" value="#{pageBeanName.civilFax}" styleClass="textbox"  />                    
</t:panelGrid>
    
</t:panelGroup>

    <t:panelGrid id="buttonsGrid" forceId="true" styleClass="lovDiv_btnsPnlGrd" columns="3" align="center">
        <t:commandButton forceId="true" id="saveCoordinator" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" disabled="#{!pageBeanName.enableSave || newBranchListBean.coordinatorExistBefore}" action="#{pageBeanName.AddBranchCoordinator}" onclick="return saveAndClose();"/>
        <h:panelGroup rendered="#{pageBeanName.divMode == 1}">
            <t:commandButton forceId="true" id="saveAndNewCoordinator" type="button" onclick="return saveAndHold();" disabled="#{!pageBeanName.enableSave || newBranchListBean.coordinatorExistBefore}" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
            <a4j:jsFunction name="saveAndNewCoordinator" action="#{pageBeanName.saveAndNewCoordinator}" reRender="branch_coordinators_panel,civilInList,buttonsGrid,dataT_data_panel,OperationBar" />
        </h:panelGroup>
        <t:commandButton forceId="true" 
        id="backButtonCustomDiv1" styleClass="cssButtonSmall" 
        value="#{globalResources.back}" onclick="hideCustomDiv();"/>
    </t:panelGrid>
    
<script language="javascript" type="text/javascript">

    function saveAndClose() {
    
    if (!validatemyForm()) {
            return false;
    }else{
        if(!ValidateEmail('txtCitizenEmail','citizenMailError')){
            return false;
        }else{
            block();
            return true;   
        }
    }
    }
    function saveAndHold() {
        if(!validatemyForm()){
            return false;
        }else{
                if(!ValidateEmail('txtCitizenEmail','citizenMailError')){
                    return false;
                }else{
                    saveAndNewCoordinator();
                    return true;
                }        
        }
            
    } 
    
    function ValidateEmail(inputTextID,errorTextID)  
    {  
        var reWhitespace = /^\s+$/;
        if(document.getElementById(inputTextID)!=null){
            inputTextValue= document.getElementById(inputTextID).value;
            if((inputTextValue == null) || (inputTextValue.length == 0) || reWhitespace.test(inputTextValue)) {
            document.getElementById(errorTextID).style.display = "none";
            return true;
            }
        }   
        else{
            document.getElementById(errorTextID).style.display = "none";
            return true;
        }
            
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
        if(inputTextValue.match(mailformat))  
        {  
            document.getElementById(errorTextID).style.display = "none";
            return true;  
        }  
        else {
            document.getElementById(errorTextID).style.display = "inline";
            document.getElementById(inputTextID).focus();
            return false;  
        }
    }     
</script>
