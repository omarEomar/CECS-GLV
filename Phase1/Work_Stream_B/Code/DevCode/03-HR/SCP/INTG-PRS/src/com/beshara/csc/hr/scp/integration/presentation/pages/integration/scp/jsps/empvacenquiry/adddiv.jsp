<%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
    <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
    <%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
    <%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
    <%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
    <%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup forceId="true" id="wholediv">
        <t:inputHidden forceId="true" id="settlmentResultId" value="#{pageBeanName.settlmentResult}"/>
        <h:outputText id="error"
                      value="#{pageBeanName.showCustomMsg == 0 ? scpIntgResources[pageBeanName.errorMsg] : pageBeanName.errorMsg}"
                      styleClass="errMsg" rendered="#{pageBeanName.settlmentResult != 0}"/>

    <t:panelGrid columns="1" border="0" width="100%" cellpadding="0" cellspacing="0"
                 rowClasses="row02,row01">
        <t:panelGrid columns="5" cellpadding="3" cellspacing="0">
            <t:outputText value="#{scpIntgResources.settlment_types}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="settlmentTypeList" styleClass="Dropdownbox"
                             value="#{pageBeanName.settlmentType}" style="margin-right: 30px">
                <t:selectItems value="#{pageBeanName.settlmentTypesList}" itemLabel="#{settlmentTypesList.name}"
                               itemValue="#{settlmentTypesList.code.key}" var="settlmentTypesList"/>
                <a4j:support actionListener="#{pageBeanName.changeSettlmentType}" event="onchange" reRender="wholediv"/>
            </t:selectOneMenu>
            <t:outputText value="#{scpIntgResources.emp_vac_method}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="paymentTypeList" styleClass="Dropdownbox"
                             disabled="#{pageBeanName.inAdvance}" value="#{pageBeanName.paymentType}">
                <t:selectItems value="#{pageBeanName.paymentTypesList}" itemLabel="#{paymentTypesList.name}"
                               itemValue="#{paymentTypesList.code.key}" var="paymentTypesList"/>
            </t:selectOneMenu>
        </t:panelGrid>
        <t:panelGrid columns="4" cellpadding="5" cellspacing="0" id="paymentgrid">
            <h:outputText value="#{scpIntgResources.vac_type}" styleClass="divtext"/>
            <t:inputText id="svacTypeCode" styleClass="textboxsmall" forceId="true"
                         value="#{pageBeanName.selVacTypeCode}"
                         onKeyPress="filterationInputOnKeyPress(event, this, 'dropVacType', 'errorCodeId', null, null);"
                         onblur="filterationInputOnBlur(event, this, 'dropVacType', 'errorCodeId', null, null);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-right: 30px"/>
            <t:panelGroup colspan="2">
                <t:selectOneMenu forceId="true" id="dropVacType" styleClass="Dropdownbox"
                                 value="#{pageBeanName.selVacType}"
                                 onChange="filterationDropboxOnChange(event, this, 'svacTypeCode', 'errorCodeId', null, null);"
                                 style="width:270">
                    <f:selectItem itemLabel="#{scpIntgResources.select_one_item}" itemValue="#{pageBeanName.virtualConstValue}"/>
                    <t:selectItems value="#{pageBeanName.vacTypeList}" itemLabel="#{vacTypeList.name}"
                                   itemValue="#{vacTypeList.code.key}" var="vacTypeList"/>
                </t:selectOneMenu>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                
                <f:verbatim><br/></f:verbatim>
                <c:requiredFieldValidator componentToValidate="svacTypeCode" active="#{empVacMonSalEnquiryListBean.divMode==0}"  
                    display="dynamic" errorMessage="#{scpIntgResources.select_one_item}" highlight="false"/>
                <t:outputLabel id="errorCodeId" value="#{scpIntgResources.MessageForWrongCode}" forceId="true"
                               styleClass="validation_error" style="display:none;color: #FF0000;"/>
            </t:panelGroup>
        </t:panelGrid>
        <t:panelGrid columns="5" cellpadding="3" cellspacing="0">
            <t:outputLabel styleClass="lable01" id="lblVacAddFrom" value="#{scpIntgResources.vacFromDate}"
                           style="margin-left:10px;"/>
            <t:panelGroup colspan="2">
                <t:inputCalendar forceId="true" id="clndr_Vac_Add_From" title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 value="#{pageBeanName.dateFrom}" size="20"
                                 maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 renderPopupButtonAsImage="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim><br/></f:verbatim>
                <c:requiredFieldValidator componentToValidate="clndr_Vac_Add_From" active="#{empVacMonSalEnquiryListBean.divMode==0}"  
                    display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                <c:dateFormatValidator componentToValidate="clndr_Vac_Add_From"
                                       active="#{empVacMonSalEnquiryListBean.divMode==0}" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
            </t:panelGroup>
            <t:outputLabel styleClass="lable01" id="lblVacAddTo" value="#{scpIntgResources.to}"
                           style="margin-left:10px;"/>
            <t:panelGroup colspan="2">
                <t:inputCalendar forceId="true" id="clndr_Vac_Add_To" title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 value="#{pageBeanName.dateTo}" size="20" maxlength="#{pageBeanName.calendarTextLength}"
                                 styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 renderPopupButtonAsImage="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" style="margin-right: 18px;">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim> <br/> </f:verbatim>
                <c:requiredFieldValidator componentToValidate="clndr_Vac_Add_To" active="#{empVacMonSalEnquiryListBean.divMode==0}"  
                    display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                <c:compareDateValidator componentToValidate="clndr_Vac_Add_From" componentToCompare="clndr_Vac_Add_To"
                                        operator="before" display="dynamic"
                                        errorMessage="#{scpIntgResources.searchIntervalError}"
                                        active="#{empVacMonSalEnquiryListBean.divMode==0}"/>
                <c:dateFormatValidator componentToValidate="clndr_Vac_Add_To" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                       active="#{empVacMonSalEnquiryListBean.divMode==0}"/>
            </t:panelGroup>
        </t:panelGrid>
        <t:panelGrid id="payMonthPnl" forceId="true" columns="4" cellpadding="5" cellspacing="0"
                     rendered="#{pageBeanName.settlmentType == '3'}" >
            <t:outputText id="monthText" forceId="true" value="#{scpIntgResources.pay_month}" styleClass="lable01"/>
            <t:panelGroup id="monthMenuPnl" forceId="true">
                <t:selectOneMenu forceId="true" id="monthMenu" styleClass="Dropdownbox" value="#{pageBeanName.month}" style="margin-right: 24px">
                    <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}"
                                   itemValue="#{months.code.key}" var="months"/>
                    <%-- <a4j:support actionListener="#{pageBeanName.changeSettlmentType}" event="onchange"
                         reRender="pnlButtons,SaveButton,divAddLookup"/>--%>
                </t:selectOneMenu>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <%--<c:requiredFieldValidator componentToValidate="monthMenu" active="#{empVacMonSalEnquiryListBean.divMode == 0 && empVacMonSalEnquiryListBean.settlmentType == '3'}"  
                    display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>--%>

            </t:panelGroup>
            <t:outputText id="yearText" forceId="true" value="#{scpIntgResources.year}" styleClass="lable01"/>
            <t:panelGroup id="yearMenuPnl" forceId="true">
                <t:selectOneMenu forceId="true" id="yearMenu" styleClass="Dropdownbox" value="#{pageBeanName.year}" style="margin-right: 8px">
                    <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}"
                                   itemValue="#{years.code.key}" var="years"/>
                    <%-- <a4j:support actionListener="#{pageBeanName.changeSettlmentType}" event="onchange"
                         reRender="pnlButtons,SaveButton,divAddLookup"/>--%>
                </t:selectOneMenu>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <%--<c:requiredFieldValidator componentToValidate="yearMenu" active="#{empVacMonSalEnquiryListBean.divMode == 0 && empVacMonSalEnquiryListBean.settlmentType == '3'}"  
                    display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGrid>
</t:panelGroup>

    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center" id="pnlButtons" forceId="true">
        <%-- <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall"
             value="#{scpIntgResources.settlment_execute_btn}" action="#{pageBeanName.executeSettlment}" onclick="return
             validatemyForm();"/>--%>
        <t:panelGroup>
            <t:commandButton forceId="true" id="addPButton" type="button" styleClass="cssButtonSmall"
                             onclick="if(validateAll()){settlment_execute();}else {return false;}"
                             value="#{globalResources.SaveButton}"/>
            <a4j:jsFunction name="settlment_execute" action="#{pageBeanName.preExecuteSettlment}"
                            oncomplete="checkSettlmentDivVisibilty();"
                            reRender="wholediv,payslipDataPanel,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar,scriptGenerator,divMsg,customDiv1,customDiv2"/>
        </t:panelGroup>
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();"
                             onclick="backJsFunction(); return false;" styleClass="cssButtonSmall"
                             value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action=""
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); "
                            reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
        </t:panelGroup>
    </t:panelGrid>
    
    <t:saveState value="#{pageBeanName.settlmentTypesList}"/>
    <t:saveState value="#{pageBeanName.settlmentType}"/>
    <t:saveState value="#{pageBeanName.selVacType}"/>
    <t:saveState value="#{pageBeanName.vacTypeList1}"/>
    <t:saveState value="#{pageBeanName.vacTypeList2}"/>
    <t:saveState value="#{pageBeanName.selVacTypeCode}"/>
    <t:saveState value="#{pageBeanName.months}"/>
    <t:saveState value="#{pageBeanName.years}"/>
    <t:saveState value="#{pageBeanName.month}"/>
    <t:saveState value="#{pageBeanName.year}"/>
    <t:saveState value="#{pageBeanName.showCustomMsg}"/>
    <t:saveState value="#{pageBeanName.settlmentResult}"/>
    <t:saveState value="#{pageBeanName.disableExecute}"/>
    <t:saveState value="#{pageBeanName.errorMsg}"/>
    <t:saveState value="#{pageBeanName.dateFrom}"/>
    <t:saveState value="#{pageBeanName.dateTo}"/>
    <t:saveState value="#{pageBeanName.divMode}"/>
    <script language="javascript" type="text/javascript">
      function validateAll() {
          //alert("validateAll");
          return validatemyForm();
      }

      function checkSettlmentDivVisibilty() {
          if (document.getElementById('settlmentResultId') != null) {

              if (document.getElementById('settlmentResultId').value == '0') {
                  hideLookUpDiv(window.blocker, window.lookupAddDiv, 'add_first_inputTxt', 'myForm:error', 'add');
                  //settingFoucsAtListPage();
                  changeVisibilityMsg();
              }
              else if (document.getElementById('settlmentResultId').value == '2') {
                  hideLookUpDiv(window.blocker, window.lookupAddDiv, 'add_first_inputTxt', 'myForm:error', 'add');
                  changeVisibilityDiv(window.blocker, window.customDiv2);
                  document.getElementById('backButtonCustomDiv2').focus();
                  return false;

              }
              else {
                  changeVisibilityDiv(window.blocker, window.lookupAddDiv);
              }
          }
      }
    </script>

