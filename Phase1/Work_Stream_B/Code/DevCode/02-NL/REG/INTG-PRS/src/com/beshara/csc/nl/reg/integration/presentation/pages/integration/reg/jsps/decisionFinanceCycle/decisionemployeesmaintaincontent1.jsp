<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.divContent1Dynamic {
    border-radius: 6px;
    box-shadow: 0 0 3px #5D9BC5;
    margin: 8px 0 3px 4px;
    overflow-x: hidden;
    overflow-y: auto;
    padding: 0;
    width:728px !important;
}

.empListOfValuesStyle{
    background-clip: padding-box;
    background-color: #2779B2;
    background-image: url("../../images/popup_bg.jpg");
    background-position: right top;
    background-repeat: repeat-x;
    border: 1px solid #2779B2;
    border-radius: 6px;
    box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
    direction: rtl;
    left: 13%;
    outline: 0 none;
    position: absolute;
    top: 30px !important;
    visibility: hidden;
    width: 550px !important;
    z-index: 100;
}
.dataT-With-7-row-filter {
    height: 221px !important;
}
</htm:style>
<%--t:panelGroup--%>
<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" border="0" >
    <%--- Start of Row 1--%>
      <h:outputText value="#{regResources.typeDecisionLabel}" />
      <t:panelGroup colspan="2">
      <t:inputText   styleClass="textboxmedium" value="#{pageBeanName.pageDTO.typesDTO.name}" disabled="true"/>
      </t:panelGroup>
      <h:outputText value="#{regResources.issuance_year}" />
      <t:panelGroup colspan="2">
      <t:inputText  styleClass="textbox" value="#{pageBeanName.pageDTO.yearsDTO.code.key}" disabled="true"/>
      </t:panelGroup>
    <%--- Start of Row 2--%>
      <h:outputText value="#{regResources.decision_number}"  rendered="#{pageBeanName.maintainMode==1}"/>
  
      <%--<t:inputText rendered="#{pageBeanName.maintainMode==0}" forceId="true" disabled="true" id="decision_ref_decisionNo" styleClass="textbox" value="#{pageBeanName.pageDTO.decisionNumber}"/>--%>
      <t:panelGroup colspan="5" rendered="#{pageBeanName.maintainMode==1}" >
      <t:inputText forceId="true" disabled="true" id="decision_ref_decisionNoEdit" styleClass="textbox" value="#{pageBeanName.pageDTO.regNum!=null? pageBeanName.pageDTO.regNum : pageBeanName.pageDTO.regAutoNumber}"/>
      </t:panelGroup>
      <h:outputText value="#{regResources.dec_title}"  rendered="#{pageBeanName.maintainMode==0}"/>
      <t:panelGroup colspan="5" rendered="#{pageBeanName.maintainMode == 0}">
        <t:inputText disabled="true"  styleClass="TextBoxLarge2" style="width: 590px;" value="#{pageBeanName.pageDTO.decisionTitle}"/>
      </t:panelGroup>
                <t:outputText forceId="true" id="codeText" styleClass="divtext"
                                      value="#{regResources.merSection}"/>
                <t:panelGroup colspan="5" id="detail_sal_elm_guide" forceId="true">
           <t:inputText id="dtl_sal_elm_guide_code"  styleClass="textboxsmall" forceId="true" value="#{detailBeanName.selectedElementGuideCode}"  disabled="#{pageBeanName.showOnly}" tabindex="1"
                       onblur="onchangeElmguideCode();" onkeypress="handleEnterKey(event)" onkeyup="enabelIntegerOnly(this);"  />
          
            <a4j:jsFunction actionListener="#{detailBeanName.searchElmguideForSpecificTypes}" name="onchangeElmguideCode"
                             reRender="errorcodeMsgPanel,dtl_sal_elm_guide,errorMsg,scriptPanel,scriptGenerator,OperationBar,paging_panel" />
            <f:verbatim>&nbsp;</f:verbatim>
            <t:inputText forceId="true" styleClass="textboxmedium" id="dtl_sal_elm_guide" style="width: 336px;"
                         readonly="true" disabled="true" value="#{detailBeanName.selectedElementGuideName}"/>
            <f:verbatim>&nbsp;</f:verbatim>            
            
            <a4j:commandButton value="..." styleClass="cssButtonSmaller" action="#{detailBeanName.openTreeDiv}" disabled="#{pageBeanName.showOnly}"
                               oncomplete="changeVisibilityDiv(window.blocker,window.pagedDivTree,'backButtonPagedTreeDiv');focusOnSearchText();"
                               reRender="errorcode,pagedTreeDiv,scriptGenerator" id="selectSalGuide"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode != 2}"/>
            <%--<f:verbatim><br/></f:verbatim>--%>
            <t:panelGroup forceId="true" id="errorcodeMsgPanel"> 
            <h:outputText forceId="true" id="errorMsg" value="#{resourcesBundle.MessageForWrongElmguideCode}" styleClass="errMsg" rendered="#{detailBeanName.showErrorMsg1}" />
            </t:panelGroup>
            <f:verbatim><br/></f:verbatim>
            <c2:requiredFieldValidator componentToValidate="dtl_sal_elm_guide" display="dynamic"  group="empData"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{financeDecisionCycleMainDataMaintainBean.divflag==false}"/>
          
        </t:panelGroup>
          <!--end by saad -->
            
        <t:outputText forceId="true" id="merDurationFrom" styleClass="divtext" value="#{regResources.merDurationFrom}"/>
        <t:panelGroup  onkeypress="FireButtonClickOldStyle(event,'ava');"  id="merDurationFromPnl" forceId="true" colspan="1">
            <t:inputCalendar  value="#{pageBeanName.pageDTO.decMerFromDate}" id="clndr_merDurationFromDate"
                title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                popupDateFormat="dd/MM/yyyy" forceId="true" maxlength="10" currentDayCellClass="currentDayCell"
                styleClass="textbox" renderAsPopup="true"
                align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" 
                disabled="#{pageBeanName.showOnly}">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
             <%--<t:outputText forceId="true" id="vaildateIssuanceYearWithDecitionDateId" value="" styleClass="errMsg"/>--%>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:dateFormatValidator componentToValidate="clndr_merDurationFromDate" highlight="false" group="empData"
                            display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}"/>
            <c2:requiredFieldValidator componentToValidate="clndr_merDurationFromDate" display="dynamic" highlight="false"
                errorMessage="#{globalResources.missingField}" uniqueId="maintain_decisionDateID" group="empData"/>
        </t:panelGroup>
        <t:panelGroup id="merDurationToPnl" forceId="true" colspan="1">    
        <t:outputText forceId="true" id="merDurationTo" styleClass="divtext" value="#{regResources.merDurationTo}"/>
         <f:verbatim>&nbsp;&nbsp; </f:verbatim>
            <t:inputCalendar   onkeypress="FireButtonClickOldStyle(event,'ava');" value="#{pageBeanName.pageDTO.decMerToDate}" id="clndr_merDurationToDate"
                title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                popupDateFormat="dd/MM/yyyy" forceId="true" maxlength="10" currentDayCellClass="currentDayCell"
                styleClass="textbox" renderAsPopup="true"
                align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" 
                disabled="#{pageBeanName.showOnly}">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
             <%--<t:outputText forceId="true" id="vaildateIssuanceYearWithDecitionDateId" value="" styleClass="errMsg"/>--%>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:dateFormatValidator componentToValidate="clndr_merDurationToDate" highlight="false" group="empData"
                            display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}"/>
            <c2:requiredFieldValidator componentToValidate="clndr_merDurationToDate" display="dynamic" highlight="false" group="empData"
                errorMessage="#{globalResources.missingField}" uniqueId="maintain_decisionDateID"/>
            <c2:compareDateValidator componentToValidate="clndr_merDurationFromDate" componentToCompare="clndr_merDurationToDate"  group="empData"
                operator="before" display="dynamic" errorMessage="#{regResources.dec_from_to_date}" active="true"/>
        </t:panelGroup>
        <t:panelGroup colspan="3">
        </t:panelGroup>
       <h:outputText value="#{regResources.civil_id}" />  
       <t:panelGroup colspan="1" >
            <t:inputText  tabindex="2" styleClass="textboxsmall2" value="#{detailBeanName.civilID}" maxlength="12" forceId="true" id="civilIdInTxt" converter="javax.faces.Long" onkeyup="disableCharacters(this);" onkeypress="FireButtonClickOldStyle(event,'ava');"  disabled="#{pageBeanName.showOnly}"/>
                
                <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode == 0}"/>
                </t:panelGroup>
               <%--<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </f:verbatim>--%>
              <t:panelGroup colspan="4" >  
            <t:outputText id="valueInDetail" value="#{regResources.financialEmpValueNum}  #{regResources.fels_Denar}"/>
               <f:verbatim>&nbsp;&nbsp; </f:verbatim>
           
            <t:inputText tabindex="4" onkeypress="FireButtonClickOldStyle(event,'ava');" forceId="true" id="fels_number1"  value="#{financeDecisionCycleEmployeesMaintainBean.felsValue}"  onkeyup="disableCharacters(this);" maxlength="8"  styleClass="textbox" style="width:30px;"   disabled="#{pageBeanName.showOnly}" />
            <f:verbatim>&nbsp; </f:verbatim>
            <t:inputText tabindex="3" onkeypress="FireButtonClickOldStyle(event,'ava');" forceId="true" id="denar_number1" value="#{financeDecisionCycleEmployeesMaintainBean.dinarValue}" onkeyup="disableCharacters(this);" maxlength="8"  styleClass="textbox"   style=" width: 50px;  margin-right: 4px; margin-left: 1px;"   disabled="#{pageBeanName.showOnly}" />
                 <%--<h:commandButton id="ava" value="#{globalResources.Available}" styleClass="cssButtonSmall" style="padding:0;" action="#{decisionCycleEmployeesMaintainBean.findEmployeeByCivilIDFromEmployees}" onclick="return(validateRequiredElement('civilIdInTxt','#{resourcesBundle.missingCivilID}','errorCivilMessage') && checkExactLength('12','civilIdInTxt','errorCivilMessage','#{resourcesBundle.civilLengthError}') && isIntegerValueWithRequiredParam('civilIdInTxt','errorCivilMessage','#{globalResources.missingField}','#{resourcesBundle.num_int_msg}','true'));"  />--%>          
                 <f:verbatim>&nbsp;&nbsp; </f:verbatim>
                 <t:commandButton tabindex="5" id="ava" forceId="true" value="#{globalResources.addButton}" styleClass="cssButtonSmall" disabled="#{pageBeanName.showOnly}"
                                   action="#{financeDecisionCycleEmployeesMaintainBean.findEmployeeByCivilIDFromEmployees}"  onclick="return ( validatemyForm('empCivil') && validatemyForm('empData') );jumpToNextField('civilIdInTxt');" 
                                   />
                <%--<a4j:jsFunction action="#{financeDecisionCycleEmployeesMaintainBean.findEmployeeByCivilIDFromEmployees}" name="addEmployee" oncomplete="calcualteFelsAndDenar();"
                             reRender="totalValuePnl,errorCivilMessage1,errorCivilMessage,error_Message_1,scriptPanel,scriptGenerator,OperationBar,paging_panel" />--%>
            <%--onclick="return validatemyForm('empData'); return(validateRequiredElement('civilIdInTxt','#{resourcesBundle.missingCivilID}','errorCivilMessage') && checkExactLength('12','civilIdInTxt','errorCivilMessage','#{resourcesBundle.civilLengthError}'));" --%>
           <%--<f:verbatim>&nbsp;&nbsp; </f:verbatim>--%>
           <f:verbatim>
                <br/>
            </f:verbatim>
           <t:outputText id="errorCivilMessage1" forceId="true" styleClass="errMsg" value="#{detailBeanName.civil_exist}"/>
            <t:outputText id="errorCivilMessage" forceId="true" styleClass="errMsg"/>
            <h:outputText id="error_Message_1" forceId="true" styleClass="errMsg" value="#{regResources.missingCivilID}" rendered="#{ detailBeanName.showErrMsg }"/>
            <c2:requiredFieldValidator group="empCivil" componentToValidate="civilIdInTxt" display="dynamic" 
                errorMessage="#{globalResources.missingField}" highlight="false"
                active="#{financeDecisionCycleMainDataMaintainBean.divflag==false}"/><%--active="#{!stopSalaryListBean.civilExist}"--%>
            <c2:regularExpressionValidator group="empCivil"  componentToValidate="civilIdInTxt" 
                pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"  
                highlight="false"  display="dynamic" active="#{financeDecisionCycleMainDataMaintainBean.divflag==false}"/><%--active="#{!stopSalaryListBean.civilExist}"--%>

        </t:panelGroup>
        
        <!-- ******************** row of Month and Year ******************************-->
        <%--<h:outputText value="#{regResources.month}" /> 
       <t:panelGroup>
            <t:selectOneMenu forceId="true" id="monthMenu" styleClass="textboxsmall2" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode==1}" value="#{detailBeanName.month}">
            <f:selectItem itemLabel="#{regResources.select_month}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                           var="months"/>        
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
           <f:verbatim>
                    <br/>
            </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="monthMenu" display="dynamic" 
                                      errorMessage="#{globalResources.missingField}" highlight="false" active="#{!(pageBeanName.showOnly || pageBeanName.maintainMode==1)}"/>
        </t:panelGroup>
         <t:panelGroup>
         <t:panelGroup>
        <h:outputText value="#{regResources.year}" styleClass="divtext"/>
         </t:panelGroup>
         <f:verbatim>&nbsp;&nbsp;</f:verbatim>
         <t:selectOneMenu forceId="true" id="yearMenu" styleClass="textboxsmall2" value="#{detailBeanName.year}"   disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode==1}">
            <f:selectItem itemLabel="#{regResources.select_year}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                           var="years"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                              
        <c2:requiredFieldValidator componentToValidate="yearMenu" display="dynamic" 
                                      errorMessage="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;#{globalResources.missingField}" highlight="false" active="#{!(pageBeanName.showOnly || pageBeanName.maintainMode==1)}"/>
        </t:panelGroup>--%> 
        <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
        
        <%--<h:outputText value="#{regResources.total_emp_count}"/>--%>    
      <%--<t:panelGroup >
        <t:inputText forceId="true" disabled="true" id="total_emp_count" styleClass="textbox" value="#{detailBeanName.currentListSize}"/>
      </t:panelGroup>--%>
      
      <h:outputText value="#{regResources.total_values}"/>    
      <t:panelGroup id="totalValuePnl" forceId="true" colspan="2">
        <t:inputText forceId="true" disabled="true" id="total_value" styleClass="textbox" value="#{detailBeanName.totalValue}" />
      </t:panelGroup>
        <!-- ************************************************************************************************ -->
</t:panelGrid>
<%--/t:panelGroup--%>

<%--<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="clndr_merDurationToDate,10,10" />--%>

<script language="javascript" type="text/javascript">
    function handleEnterKey(e) {
     
    //alert(e.keyCode);
        if (e.keyCode == 13) {
       // alert('here');
            onchangeElmguideCode();
            //FireButtonClickOldStyle(e,'ava');
        }
    }
    
    function jumpToNextField(fieldId) {
            var nextFieldInput = document.getElementById(fieldId);
            nextFieldInput.focus();
    }
   
</script>