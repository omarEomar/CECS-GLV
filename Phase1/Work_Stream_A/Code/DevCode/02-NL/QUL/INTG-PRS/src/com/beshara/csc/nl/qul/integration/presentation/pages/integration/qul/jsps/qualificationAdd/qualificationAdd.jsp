<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.colu1{width:106px;}</htm:style>
<f:loadBundle basename="com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration"
              var="qulIntgResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<t:saveState value="#{qualificationAddHelperBean.kwtCitizensResidentsDTO}"/>
<t:saveState value="#{qualificationAddHelperBean.qul_country_list}"/>
<t:saveState value="#{qualificationAddHelperBean.qulDegreeType}"/>
<t:panelGrid id="xyz" columns="6" width="100%" forceId="true" columnClasses=""
             rowClasses="row01,row02," cellpadding="0" cellspacing="0" border="0">
    <t:outputLabel value="#{qulIntgResources.CountryName}"/>
    <t:panelGroup colspan="2">
        <t:inputText id="countryCode" styleClass="textboxsmall" forceId="true"
                     value="#{qualificationAddHelperBean.countryCode}" onkeyup="enabelIntegerOnly(this);"
                     onkeypress="filterationInputOnKeyPress(event,this,'countriesList','countryFieldValError',changeCountryVal);"
                     onblur="filterationInputOnBlur(event,this,'countriesList','countryFieldValError',changeCountryVal);">
            <a4j:jsFunction action="#{qualificationAddHelperBean.filterDataTableByCountry}" name="changeCountryVal"
                            reRender="cer_level,centerTree,certificatePnl,qulPanel,countriesList,treeList,OperationBar,missionTypeNameID,dataT_data_panel,OperationBar,paging_panel,validationGroup,search_Button,hiddenfireComponentFieldID"/>
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" id="countriesList" value="#{qualificationAddHelperBean.selectedCountryId}"
                         styleClass="DropdownboxMedium" onchange="onchangeCountry();">
            <t:selectItems value="#{qualificationAddHelperBean.qul_country_list}" var="countries"
                           itemValue="#{countries.code.key}" itemLabel="#{countries.name}"/>
            <a4j:jsFunction name="onchangeCountry" action="#{qualificationAddHelperBean.filterDataTableByCountry}"
                            reRender="cer_level,centerTree,certificatePnl,qulPanel,centersTeeDiv,countryFieldValError,countryCode,treeList,OperationBar,missionTypeNameID,dataT_data_panel,OperationBar,paging_panel,validationGroup,search_Button,hiddenfireComponentFieldID"/>
        </t:selectOneMenu>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:outputText id="countryFieldValError" value="#{qulIntgResources.inavlid_country_code}"
                      styleClass="mandatoryAsterisk" style="display:none" forceId="true"/>
    </t:panelGroup>
    <t:outputLabel value="#{qulIntgResources.emission_side}"/>
    <t:panelGroup colspan="2" id="centerTree" forceId="true">
        <t:inputText id="missionTypeCode" styleClass="textboxsmall" forceId="true"
                     value="#{qualificationAddHelperBean.qulCenterCode}" onkeyup="enabelIntegerOnly(this);"
                     onblur="changeCenterCode();" onkeypress="keyPressMissionCode(event);">
                     <%--onkeypress="filterationInputOnKeyPress(event,this,'countriesList','countryFieldValError',changeCountryVal);"
                     onblur="filterationInputOnBlur(event,this,'countriesList','countryFieldValError',changeCountryVal);"--%>
            <a4j:jsFunction action="#{qualificationAddHelperBean.addQulCenterByCode}" name="changeCenterCode"
                            reRender="certificatePnl,qulPanel,cer_level,centersTeeDiv,centerTree,treeList,missionTypeNameID,dataT_data_panel,validationGroup,search_Button,hiddenfireComponentFieldID"/>
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText id="missionTypeNameID" forceId="true" value="#{qualificationAddHelperBean.selectedCenterName}"
                     styleClass="textboxlargeB" disabled="true" style="width:245px;"/>
        <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <a4j:commandButton id="add_button" value="..." tabindex="2" styleClass="cssButtonSmallest"
                           action="#{qulCentersIntegrationBean.openIntDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.#{qulCentersIntegrationBean.intDivName});return false;"
                           reRender="centersTeeDiv,scriptPanel"/>
        <htm:br/>
        <t:outputLabel value="#{qulIntgResources.inavlid_Qul_val}" styleClass="errMsg"
                               rendered="#{qualificationAddHelperBean.inValidMissionTypeCode}" id="Wrong_missionTypeCode"
                               forceId="true"/>
        <c2:requiredFieldValidator active="#{qualificationAddBean.selectedQulType==qualificationAddBean.nonCertifiedQul && (qualificationAddBean.qulDegreeType != managedConstantsBean.crsGradeTypeLiteralConstant &&  qualificationAddBean.qulDegreeType != managedConstantsBean.crsGradeTypeLatinConstant) }"
                                   componentToValidate="missionTypeNameID" display="dynamic"
                                   errorMessage="#{globalResources.missingField}"/>
    </t:panelGroup>
    <t:outputLabel value="#{qulIntgResources.qul_level}"/>
    <t:panelGroup id="cer_level" forceId="true" colspan="2">
        <t:inputText styleClass="textboxmedium" disabled="true" value="#{qualificationAddHelperBean.certificateLevel}"
                     id="qul_level" forceId="true" style="width:253px;"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <a4j:commandButton id="open_Level_Div" value="..." styleClass="cssButtonSmallest" tabindex="4"
                           action="#{qulEduLevelsIntegrationBean.openIntDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.#{qulEduLevelsIntegrationBean.intDivName});setFocusOnlyOnElement('searchText');return false;"
                           reRender="certificatePnl,qulPanel,eduLevelTeeDiv,scriptPanel" disabled="#{detailBeanName.selectedCenterName == ''}"/>
        <h:outputText rendered="#{!qualificationAddHelperBean.enableResetData}" id="levelFieldValError"
                      value="#{qualificationAddHelperBean.levelFieldValError}" styleClass="validation_error"/>
        <t:panelGroup id="reset_data_btn_id" rendered="#{qualificationAddHelperBean.enableResetData}" forceId="true">
            <a4j:commandButton value="#{qulIntgResources.reSetButtonErase}" styleClass="cssButtonSmallest"
                               action="#{qualificationAddHelperBean.reSetButtonErase}"
                               reRender="qulPanel,cer_level,certificatePnl,scriptPanel,xyz"/>
        </t:panelGroup>
        <htm:br/>
        <c2:requiredFieldValidator active="true" componentToValidate="qul_level" display="dynamic"
                                   errorMessage="#{globalResources.missingField}"/>
    </t:panelGroup>
    <t:outputLabel value="#{qulIntgResources.certificateName}"/>
    <t:panelGroup id="certificatePnl" forceId="true">
        <t:inputText id="certificateCode" styleClass="textboxsmall" forceId="true"
                     value="#{qualificationAddHelperBean.selectedCertificateId}" onkeyup="enabelIntegerOnly(this);"
                     onkeypress="filterationInputOnKeyPress(event,this,'certificatesList','certificateFieldValError',changeCertificateVal);"
                     onblur="filterationInputOnBlur(event,this,'certificatesList','certificateFieldValError',changeCertificateVal);">
            <a4j:jsFunction action="#{qualificationAddHelperBean.fillQulByCertificate}" name="changeCertificateVal"
                            reRender="qulPanel,certificatesList,treeList,OperationBar,missionTypeNameID,dataT_data_panel,OperationBar,paging_panel,validationGroup,search_Button,hiddenfireComponentFieldID"/>
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" id="certificatesList"
                         value="#{qualificationAddHelperBean.selectedCertificateId}" styleClass="DropdownboxMedium"
                         onchange="fillQul();" style="width:245px;" >
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
            <t:selectItems value="#{qualificationAddHelperBean.certificatesList}" var="certificates"
                           itemValue="#{certificates.code.cntqualificationCode}" itemLabel="#{certificates.name}"/>
            <a4j:jsFunction name="fillQul" action="#{qualificationAddHelperBean.fillQulByCertificate}"
                            reRender="certificateCode,qualificationName2,treeList,OperationBar,missionTypeNameID,dataT_data_panel,OperationBar,paging_panel,validationGroup,search_Button,hiddenfireComponentFieldID"/>
        </t:selectOneMenu>

        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <a4j:commandButton id="open_certificateDiv" value="..." styleClass="cssButtonSmallest" 
                           oncomplete="changeVisibilityDiv(window.blocker,window.integrationDiv2);return false;"
                           reRender="integrationDiv2,scriptPanel" disabled="#{empty qualificationAddHelperBean.certificatesList}"/>
                           <%--action="#{qualificationAddHelperBean.openIntDiv}" --%>
        <f:verbatim> <br/></f:verbatim>
        <t:outputText id="certificateFieldValError" value="#{qulIntgResources.inavlid_Qul_val}"
                      styleClass="mandatoryAsterisk" style="display:none" forceId="true"/>        
        <%--<htm:br/>--%>
        <c2:requiredFieldValidator active="true" componentToValidate="certificatesList" display="dynamic"
                                   errorMessage="#{globalResources.missingField}"/>
    </t:panelGroup>
</t:panelGrid>
<%-- *******************************************************************************************************************--%>
<t:panelGrid cellpadding="0" cellspacing="0" width="100%" forceId="true" id="qulPanel" border="0">
    <%-- ============================= certified qualification ==========================================--%>
    <%-- --%>
    <%-- ============================= Non certified qualification grid ==========================================--%>
    <t:panelGrid columns="2" rowClasses="row01,row02" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0"
                 width="100%"
                 rendered="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul}"
                 border="0">
        <t:outputLabel value="#{qulIntgResources.qualificationname}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText disabled="true" styleClass="textboxmedium" style="width:420px;" forceId="true"
                         id="qualificationName2" value="#{qualificationAddHelperBean.qualificationName}"/>
        </t:panelGroup>
        <t:panelGroup rendered="#{!qualificationAddHelperBean.disableQulDateRegistration}" colspan="2">
            <t:outputLabel value="#{qulIntgResources.qul_date_registration}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" style="margin-right: 37px;"
                                 popupDateFormat="dd/MM/yyyy" forceId="true" id="registrationDate2"
                                 value="#{qualificationAddHelperBean.personsInformationDTO.registrationDate}"
                                 maxlength="#{qualificationAddHelperBean.calendarTextLength}" styleClass="textbox"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
                <%--<c2:requiredFieldValidator componentToValidate="registrationDate2"
                                           errorMessage="#{globalResources.missingField}" display="dynamic"
                                           active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul}"/>--%>
                <c2:dateFormatValidator componentToValidate="registrationDate2" display="dynamic"
                                        errorMessage="#{qulIntgResources.invalidDateFormat}" highlight="false"
                                        active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul}"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:outputLabel value="#{qulIntgResources.graduationdate}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="registrationObtainDate2"
                             value="#{qualificationAddHelperBean.personsInformationDTO.untilDate}" size="20"
                             maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c2:requiredFieldValidator componentToValidate="registrationObtainDate2"
                                       errorMessage="#{globalResources.missingField}" display="dynamic"
                                       active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul}"/>
            <c2:dateFormatValidator componentToValidate="registrationObtainDate2" display="dynamic"
                                    errorMessage="#{qulIntgResources.invalidDateFormat}" highlight="false"
                                    active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul}"/>
            <c2:compareDateValidator componentToValidate="registrationObtainDate2"
                                     componentToCompare="registrationDate2" display="dynamic" operator="afterOrEqual"
                                     errorMessage="#{qulIntgResources.compare_date}" highlight="false"
                                     active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul}"/>
        </t:panelGroup>
        <t:outputLabel value="#{qulIntgResources.qul_degreetype}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu id="qulDegreeType2" forceId="true" styleClass="Dropdownbox"
                             value="#{qualificationAddHelperBean.qulDegreeType}" style="margin-right: 4px;">
                <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                              itemLabel="#{qulIntgResources.Item_Select}"/>
                <f:selectItem itemValue="#{managedConstantsBean.crsGradeTypePercentageConstant}"
                              itemLabel="#{qulIntgResources.crsGradeTypePercentageConstant}"/>
                <f:selectItem itemValue="#{managedConstantsBean.crsGradeTypeLatinConstant}"
                              itemLabel="#{qulIntgResources.crsGradeTypeLatinConstant}"/>
                <f:selectItem itemValue="#{managedConstantsBean.crsGradeTypeLiteralConstant}"
                              itemLabel="#{qulIntgResources.crsGradeTypeLiteralConstant}"/>
                <f:selectItem itemValue="#{managedConstantsBean.crsGradeTypePointFourConstant}"
                              itemLabel="#{qulIntgResources.crsGradeTypePointFiveConstant}"/>
                <f:selectItem itemValue="#{managedConstantsBean.crsGradeTypePointFiveConstant}"
                              itemLabel="#{qulIntgResources.crsGradeTypePointFourConstant}"/>
                <f:selectItem itemValue="#{managedConstantsBean.crsGradeTypePointNineConstant}"
                              itemLabel="#{qulIntgResources.crsGradeTypePointNineConstant}"/>
                <a4j:support action="#{qualificationAddHelperBean.qulDegreeTypeChanged}" event="onchange"
                             reRender="qulDegreeValueID2,qulDegreeValueCombo2,qulPanel,scriptPanel,MsgPanel,searchPanel2,search_panel_id_group,divAddqualification,scrollerPanel_qualification,successAddLockup,titlePAnel,WrongValueEnterd2,qulDegreeValueID2,shangMaxLanghe,xyz"
                             oncomplete="setFocusAfterSelectDegreeType();"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <t:outputLabel value="#{qulIntgResources.qul_degreeValue}" styleClass="lable01"/>
        <t:panelGroup >
            <t:panelGroup rendered="#{qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypeLiteralConstant &&  qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypeLatinConstant }">
                <t:inputText styleClass="textboxsmall" style="margin-right: 5px;" forceId="true" id="qulDegreeValueID2"
                             maxlength="5" onkeypress="enabelFloatOnly(this);" disabled="#{qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypePercentageConstant && qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypePointFourConstant &&  qualificationAddHelperBean.qulDegreeType !=managedConstantsBean.crsGradeTypePointFiveConstant && qualificationAddHelperBean.qulDegreeType !=managedConstantsBean.crsGradeTypePointNineConstant}"
                             value="#{qualificationAddHelperBean.personQualificationsDTO.qualificationDegree}"
                              onblur="enabelFloatOnly(this);" />
                <%-- <t:outputText value=" % " styleClass="lable01" />--%>
                <%-- <t:outputText value="#{qualificationAddHelperBean.qulDegreeType !=
                     managedConstantsBean.crsGradeTypePointFourConstant && qualificationAddHelperBean.qulDegreeType
                     !=managedConstantsBean.crsGradeTypePointFiveConstant && qualificationAddHelperBean.qulDegreeType
                     !=managedConstantsBean.crsGradeTypePointNineConstant}"/>--%>
                <t:outputText value=" % " styleClass="lable01"
                              rendered="#{qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypePointFourConstant &&  qualificationAddHelperBean.qulDegreeType !=managedConstantsBean.crsGradeTypePointFiveConstant && qualificationAddHelperBean.qulDegreeType !=managedConstantsBean.crsGradeTypePointNineConstant}"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c2:requiredFieldValidator active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul && (qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypeLiteralConstant &&  qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypeLatinConstant) }"
                                           componentToValidate="qulDegreeValueID2" display="dynamic"
                                           errorMessage="#{globalResources.missingField}"/>
                <c2:regularExpressionValidator active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul && (qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypeLiteralConstant &&  qualificationAddHelperBean.qulDegreeType != managedConstantsBean.crsGradeTypeLatinConstant) }"
                                               componentToValidate="qulDegreeValueID2" pattern="/^[0-9]/"
                                               display="dynamic" errorMessage="#{qulIntgResources.NotNumber}"/>
                <t:outputLabel value="#{qulIntgResources.wrongValueEnterd}" styleClass="errMsg"
                               rendered="#{qualificationAddHelperBean.inValidValue}" id="WrongValueEnterd2"
                               forceId="true"/>
            </t:panelGroup>
            <t:panelGroup rendered="#{qualificationAddHelperBean.qulDegreeType == managedConstantsBean.crsGradeTypeLiteralConstant ||  qualificationAddHelperBean.qulDegreeType == managedConstantsBean.crsGradeTypeLatinConstant }">
                <t:selectOneMenu id="qulDegreeValueCombo2" forceId="true" styleClass="Dropdownbox"
                                 value="#{qualificationAddHelperBean.selectedQulDegreeValue}">
                    <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                                  itemLabel="#{qulIntgResources.Item_Select}"/>
                    <t:selectItems value="#{qualificationAddHelperBean.degreeValuesList}" var="degreeValuesList"
                                   itemValue="#{degreeValuesList.value}" itemLabel="#{degreeValuesList.value}"/>
                </t:selectOneMenu>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <htm:br/>
                <c2:compareValidator active="#{qualificationAddHelperBean.selectedQulType==qualificationAddHelperBean.nonCertifiedQul && (qualificationAddHelperBean.qulDegreeType == managedConstantsBean.crsGradeTypeLiteralConstant ||  qualificationAddHelperBean.qulDegreeType == managedConstantsBean.crsGradeTypeLatinConstant) }"
                                     componentToValidate="qulDegreeValueCombo2" componentToCompare="virtualvalueId"
                                     operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                                     display="dynamic"/>
            </t:panelGroup>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGrid>
<t:div id="navigationDiv" style="visibility:visible;" forceId="true">
    <t:panelGrid columns="3" border="0" align="center">
        <h:commandButton styleClass="cssButtonSmall" id="SaveButton1" value="#{globalResources.SaveButton}"
                         action="#{qualificationAddHelperBean.Save}"
                         onclick="resetMsgs();reinitializeSaveMsg('successAddLockup');return validatemyForm();"/>
        <t:panelGroup rendered="#{qualificationAddHelperBean.qulIntegrationListBean.saveInDB}">
            <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveNewButton1"
                             value="#{globalResources.AddNewButton}" action="#{qualificationAddHelperBean.saveAndNew}"
                             onclick="reinitializeSaveMsg('successAddLockup');reinitializeSaveMsg('failureInAdd');return validateSaveAndNewClientValidator();"/>
        </t:panelGroup>
        <t:commandButton forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}"
                         styleClass="cssButtonSmall" action="#{qualificationAddHelperBean.back}"
                         onblur="setFocusAtMyFirstElement();"/>
    </t:panelGrid>
</t:div>
<script language="javascript" type="text/javascript">
    function resetMsgs()
    {
        if(document.getElementById('Wrong_missionTypeCode') != null)
        {
            document.getElementById('Wrong_missionTypeCode').innerHTML='';
        } 
        if(document.getElementById('countryFieldValError')!=null)
        {
            document.getElementById('countryFieldValError').innerHTML='';
        }
        if(document.getElementById('certificateFieldValError')!=null)
        {
            document.getElementById('certificateFieldValError').innerHTML='';
        }
    }
    
  function validateSelection() {
      var inputTextValue;
      if (document.getElementById('missionTypeNameValidation') != null) {
          document.getElementById('missionTypeNameValidation').value = '';
      }
      if (document.getElementById('missionTypeNameID') != null)
          inputTextValue = document.getElementById('missionTypeNameID').value;
      if (checkEmpty2(inputTextValue, 'myForm:missionTypeNameValidation')) {
          alert('false');
          return false;
      }

  }

  function goChangeCountryVal(e) {
      if (e.keyCode == 13) {
          changeCountryVal();
          event.preventDefault();
      }
  }

  function ChangeCountryValOnBlur() {
      changeCountryVal();
  }
  
  function keyPressMissionCode(ev1) {
    var evt = ev1 || window.event;

    if (evt.keyCode == 13) {
        evt.cancelBubble = true;
        evt.returnValue = false;
        evt.preventDefault();
        changeCenterCode();
    }
  }
  
</script>
