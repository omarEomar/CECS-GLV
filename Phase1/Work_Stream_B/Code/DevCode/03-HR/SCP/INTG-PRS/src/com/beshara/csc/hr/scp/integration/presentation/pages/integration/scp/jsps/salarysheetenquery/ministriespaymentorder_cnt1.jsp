<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="cv"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<htm:style>#customDiv1{ top:70px !important; right: 15% !important; width: 700px !important; }</htm:style>
<t:panelGroup forceId="true" id="cnt1_panel">
    <t:panelGrid columns="2" cellpadding="3" cellspacing="0" rowClasses="row01,row02" id="sign_panel" forceId="true"
                 width="100%">
        <t:panelGroup colspan="2">
            <t:panelGrid columns="6" width="100%" cellpadding="0" cellspacing="0">
                <h:outputText id="sheetCodel_label" styleClass="lable01" value="#{resourcesBundle.sheet_no}"/>
                <t:inputText id="sheetCode" styleClass="textbox"
                             value="#{pageBeanName.salSalarySheetsDTO.code.salsheetCode}" disabled="true"
                             readonly="true"/>
                <h:outputText id="createdDate_label" styleClass="lable01" value="#{resourcesBundle.sheet_create_date}"/>
                <t:inputText id="createdDate" styleClass="textbox"
                             value="#{pageBeanName.salSalarySheetsDTO.createdDate}" disabled="true" readonly="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputText>
                <h:outputText id="sheetDate_label" styleClass="lable01" value="#{resourcesBundle.to_month}"/>
                <t:inputText id="sheetDate" styleClass="textbox"
                             value="#{pageBeanName.salSalarySheetsDTO.yearsDTO.yearCode} / #{pageBeanName.salSalarySheetsDTO.salaryMonth}"
                             disabled="true" readonly="true"/>
            </t:panelGrid>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.payment_account_no}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="accountNoList" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selDedToMinCode}">
                <%-- style="width:365px;"--%>
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
                <t:selectItems value="#{pageBeanName.accountNoList}"
                               itemLabel="#{account.bankBranchesDTO.banksDTO.name} - #{account.accountNo} - #{account.salBankAccountTypesDTO.name}"
                               itemValue="#{account.code.key}" var="account"/>
                <a4j:support event="onchange" action="#{pageBeanName.fillSalDeductToMinistriesDTO}" reRender="sign_panel,dataT_data_panel"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim><br/></f:verbatim>
          <c:requiredFieldValidator componentToValidate="accountNoList" display="dynamic"
                                          errorMessage="#{resourcesBundle.select_one_item}" highlight="false" />
        </t:panelGroup>
        <t:panelGroup rendered="#{pageBeanName.selDedToMin != null && pageBeanName.selDedToMin.bankBranchesDTO.banksDTO.code.key == '1'}"
                      colspan="2">
            <h:outputText value="#{resourcesBundle.payment_order_reciever}" styleClass="lable01"/>
            <t:selectOneRadio id="recieverRadioButton" value="#{pageBeanName.reciever}" styleClass="lable01">
                <%-- <a4j:support event="onclick" action="#{pageBeanName.changeKuwityType}" reRender="firstTabPanel"/>--%>
                <f:selectItem itemLabel="#{resourcesBundle.payment_order_reciever_1}" itemValue="1"/>
                <f:selectItem itemLabel="#{resourcesBundle.payment_order_reciever_2}" itemValue="2"/>
            </t:selectOneRadio>
        </t:panelGroup>
        <t:outputLabel value="#{resourcesBundle.signature}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="signList" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.signature}">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
                <f:selectItem itemLabel="#{resourcesBundle.general_manager}" itemValue="1"/>
                <f:selectItem itemLabel="#{resourcesBundle.diwan_boss}" itemValue="2"/>
                <f:selectItem itemLabel="#{resourcesBundle.undersecretary}" itemValue="3"/>
                <f:selectItem itemLabel="#{resourcesBundle.public_works_and_baladya_minister}" itemValue="4"/>
                <f:selectItem itemLabel="#{resourcesBundle.diawn_amiry_agent}" itemValue="5"/>
                <f:selectItem itemLabel="#{resourcesBundle.other_sign}" itemValue="-100"/>
                <a4j:support event="onchange" reRender="sign_panel"/>
            </t:selectOneMenu>
            <t:panelGroup rendered="#{pageBeanName.signature == '-100'}" colspan="2">
                <t:inputText id="signStr" value="#{pageBeanName.otherSignature}" styleClass="mastertextboxlarge"
                             style="margin-right: 116px;"/>
            </t:panelGroup>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup style="display:block;text-align:center;">
        <t:commandButton forceId="true" id="displayBtn" styleClass="cssButtonSmall" value="#{globalResources.view_menu}"
                         action="#{pageBeanName.getDedMinistriesDetailsBySheetCode}"/>
        <t:commandButton forceId="true" id="accomulatedPrintBtn" value="#{resourcesBundle.accumulated_payment_order_print}" 
                         onclick="if(validatemyForm()){openAccomulatedPrint();}else{return false;}" styleClass="cssButtonSmall" type="button">
            <a4j:jsFunction name="openAccomulatedPrint" action="#{pageBeanName.printAccumulatedPaymentOrder}" reRender="reportUrlId"
                            oncomplete="openReportWindow('reportUrlId');"/>
        </t:commandButton>
        <%-- <t:commandButton id="backButton" forceId="true" styleClass="cssButtonSmall" value="#{globalResources.back}"
             onclick="#{pageBeanName.redirectToSalarySheetPage}"/>--%>
    </t:panelGroup>
</t:panelGroup>
