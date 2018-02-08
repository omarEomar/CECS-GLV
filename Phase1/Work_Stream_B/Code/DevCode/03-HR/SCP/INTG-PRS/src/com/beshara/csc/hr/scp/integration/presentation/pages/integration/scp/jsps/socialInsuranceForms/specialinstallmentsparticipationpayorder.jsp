<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<htm:style>#customDiv1{ top:70px !important; right: 15% !important; width: 700px !important; }</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" id="insurPaymentOrderMainPanel" forceId="true">
    <t:panelGrid columns="4" cellpadding="3" cellspacing="0" rowClasses="row01,row02" id="sign_panel" forceId="true"
                 width="100%">
        <h:outputText forceId="true" id="sheetCodel_label" styleClass="lable01"
                      value="#{integrationBundle.sheet_no}"/>
        <t:inputText forceId="true" id="sheetCode" styleClass="textboxsmall2"
                     value="#{pageBeanName.selectedDTOS[0].code.salsheetCode}" disabled="true" readonly="true"/>
        <h:outputText forceId="true" id="createdDate_label" styleClass="lable01"
                      value="#{integrationBundle.sheet_create_date}"/>
        <t:inputText forceId="true" id="createdDate" styleClass="textboxsmall2"
                     value="#{pageBeanName.selectedDTOS[0].sheetDateVal}" disabled="true" readonly="true">
            <%--<f:converter converterId="TimeStampConverter"/>--%>
        </t:inputText>
        
        <t:outputLabel rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}"  value="#{integrationBundle.main_insurance_participation}" styleClass="lable01"/>
        <t:panelGroup rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}">
            <t:inputText id="mainInsuranceParticipation" tabindex="1" forceId="true" maxlength="38" styleClass="textboxsmall2" onkeyup="enabelFloatOnly(this)"
                         value="#{pageBeanName.pageDTO.mainInsuranceParticipation}"/>
        </t:panelGroup>
    
        <t:outputLabel rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}" value="#{integrationBundle.supplementary_insurance_participation}" styleClass="lable01"/>
        <t:panelGroup rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}">
            <t:inputText id="supplementaryInsuranceParticipation" tabindex="1" forceId="true" maxlength="38" styleClass="textboxsmall2" onkeyup="enabelFloatOnly(this)"
                         value="#{pageBeanName.pageDTO.supplementaryInsuranceParticipation}"/>
        </t:panelGroup>
    
        <t:outputLabel rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}" value="#{integrationBundle.special_installments}" styleClass="lable01"/>
        <t:panelGroup rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}">
            <t:inputText id="specialInstallments" tabindex="1" forceId="true" maxlength="38" styleClass="textboxsmall2" onkeyup="enabelFloatOnly(this)"
                         value="#{pageBeanName.pageDTO.specialInstallments}"/>
        </t:panelGroup>
    
        <t:outputLabel rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}" value="#{integrationBundle.increase_pensions_fund_participation}" styleClass="lable01"/>
        <t:panelGroup rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}">
            <t:inputText id="increasePensionsFundParticipation" tabindex="1" forceId="true" maxlength="38" styleClass="textboxsmall2" onkeyup="enabelFloatOnly(this)"
                         value="#{pageBeanName.pageDTO.increasePensionsFundParticipation}"/>
        </t:panelGroup>
    
        <t:outputLabel rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}" value="#{integrationBundle.financial_reward_fund_participation}" styleClass="lable01"/>
        <t:panelGroup rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}" colspan="3">
            <t:inputText id="financialRewardFundParticipation" tabindex="1" forceId="true" maxlength="38" styleClass="textboxsmall2" onkeyup="enabelFloatOnly(this)"
                         value="#{pageBeanName.pageDTO.financialRewardFundParticipation}"/>
        </t:panelGroup>
    
    
        <%--t:outputLabel value="#{integrationBundle.signature}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
        <t:selectOneMenu forceId="true" id="signList" styleClass="DropdownboxLarge" value="#{pageBeanName.signature}">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
            <f:selectItem itemLabel="#{integrationBundle.259070700689}" itemValue="259070700689"/>
            <f:selectItem itemLabel="#{integrationBundle.258081400767}" itemValue="258081400767"/>
            <f:selectItem itemLabel="#{integrationBundle.259072900035}" itemValue="259072900035"/>
            <f:selectItem itemLabel="#{integrationBundle.255120201207}" itemValue="255120201207"/>
            <f:selectItem itemLabel="#{integrationBundle.265031600053}" itemValue="265031600053"/>
            <a4j:support event="onchange" reRender="sign_panel"/>
        </t:selectOneMenu>
        </t:panelGroup>
        
        <t:outputLabel rendered="#{pageBeanName.signature == '-100'}" value="" styleClass="lable01"/>
        <t:panelGroup rendered="#{pageBeanName.signature == '-100'}" colspan="3">
            <t:inputText id="signStr" value="#{pageBeanName.signatureStr}" styleClass="mastertextboxlarge"
                         />
        </t:panelGroup--%>
        
    </t:panelGrid>

</t:panelGrid>
<t:panelGroup style="display:block;text-align: center;">
    <t:commandButton value="#{globalResources.view_menu_print}" styleClass="cssButtonSmall" type="button" rendered="#{pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}"
                     onclick="if(validatemyForm()){openReport166();}else{return false;}"></t:commandButton>
    <t:commandButton value="#{globalResources.view_menu_print}" styleClass="cssButtonSmall" type="button" rendered="#{!pageBeanName.IS_INSURANCE_REPORT_CODE_166_REP}"
                     onclick="if(validatemyForm()){openReport167();}else{return false;}"></t:commandButton>
    <t:commandButton id="backButtonCustomDiv1" forceId="true" type="button" styleClass="cssButtonSmall"
                     value="#{globalResources.back}"
                     onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null,null);return false;"></t:commandButton>
</t:panelGroup>
<a4j:jsFunction name="openReport166" action="#{pageBeanName.printSocialInsuranceForm166}" reRender="insUeportUrlId"
                oncomplete="openReportWindow('insUeportUrlId');"/>
<a4j:jsFunction name="openReport167" action="#{pageBeanName.printSocialInsuranceForm167}" reRender="insUeportUrlId"
                oncomplete="openReportWindow('insUeportUrlId');"/>
<t:inputHidden id="insUeportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>