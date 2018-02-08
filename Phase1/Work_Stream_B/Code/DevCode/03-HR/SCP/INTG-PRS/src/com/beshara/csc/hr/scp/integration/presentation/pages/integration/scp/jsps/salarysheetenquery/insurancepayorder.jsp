<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<htm:style >
    #customDiv1{
        top:70px !important;
        right: 15% !important;
        width: 700px !important;
    }
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" id="insurPaymentOrderMainPanel" forceId="true">
    <t:panelGrid columns="2" cellpadding="3" cellspacing="0" rowClasses="row01,row02" id="sign_panel" forceId="true" width="100%">
    <t:panelGroup colspan="2" rendered="#{pageBeanName.insuranceRetrieval}">
        <t:outputText forceId="true" id="insurance_Retrieval" value="#{resourcesBundle.insuranceRetrievalMsg}"
                           styleClass="errMsg"/>
    </t:panelGroup>
    <t:panelGroup colspan="2">
            <t:panelGrid columns="4"   width="100%" cellpadding="0" cellspacing="0">
                <h:outputText forceId="true" id="sheetCodel_label" styleClass="lable01" value="#{resourcesBundle.sheet_no}"/>
                <t:inputText forceId="true" id="sheetCode" styleClass="textbox" value="#{pageBeanName.selectedDTOS[0].code.salsheetCode}" disabled="true"
                             readonly="true"/>
                <h:outputText forceId="true" id="createdDate_label" styleClass="lable01" value="#{resourcesBundle.sheet_create_date}"/>
                <t:inputText  forceId="true" id="createdDate" styleClass="textbox" value="#{pageBeanName.selectedDTOS[0].createdDate}" disabled="true"
                             readonly="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputText>
                <h:outputText forceId="true" id="sheetDate_label" styleClass="lable01" value="#{resourcesBundle.to_month}"/>
                <t:inputText forceId="true" id="sheetDate" styleClass="textbox"
                             value="#{pageBeanName.selectedDTOS[0].yearsDTO.yearCode} / #{pageBeanName.selectedDTOS[0].salaryMonth}"
                             disabled="true" readonly="true"/>
            </t:panelGrid>
        </t:panelGroup>
     <h:outputText value="#{resourcesBundle.payment_account_no}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="accountNoList" styleClass="DropdownboxLarge" value="#{pageBeanName.selDedToMinCode}"  ><%--style="width:365px;"--%>
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.accountNoList}" itemLabel="#{account.bankBranchesDTO.banksDTO.name} - #{account.accountNo} - #{account.salBankAccountTypesDTO.name}" itemValue="#{account.code.key}" var="account"/>
            <a4j:support event="onchange" action="#{pageBeanName.fillSalDeductToMinistriesDTO}" reRender="sign_panel"/>
        </t:selectOneMenu>
         <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim><br/></f:verbatim>
          <c:requiredFieldValidator componentToValidate="accountNoList" display="dynamic"
                                          errorMessage="#{resourcesBundle.select_one_item}" highlight="false" group="inspayoreder"/>
          <!--<c:compareValidator componentToValidate="accountNoList" componentToCompare="virtualvalueId" operator="not"
                            errorMessage="#{resourcesBundle.select_one_item}" highlight="false" display="dynamic"/>-->
    </t:panelGroup>
    
     <t:panelGroup rendered="#{pageBeanName.selDedToMin != null && pageBeanName.selDedToMin.bankBranchesDTO.banksDTO.code.key == '1'}" colspan="2" >
        <h:outputText value="#{resourcesBundle.payment_order_reciever}" styleClass="lable01"/>
        <t:selectOneRadio id="recieverRadioButton" value="#{pageBeanName.reciever}" converter="javax.faces.Long"  styleClass="lable01">
            <%--<a4j:support event="onclick" action="#{pageBeanName.changeKuwityType}" reRender="firstTabPanel"/>--%>
            <f:selectItem  itemLabel="#{resourcesBundle.payment_order_reciever_1}" itemValue="#{pageBeanName.reciever1}"/>
            <f:selectItem  itemLabel="#{resourcesBundle.payment_order_reciever_2}" itemValue="#{pageBeanName.reciever2}"/>
        </t:selectOneRadio>
    </t:panelGroup>
    
    <t:outputLabel value="#{resourcesBundle.signature}" styleClass="lable01"/>
    <t:selectOneMenu forceId="true" id="signList" styleClass="DropdownboxLarge" value="#{pageBeanName.signature}">
        <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
        <f:selectItem itemLabel="#{resourcesBundle.general_manager}" itemValue="1"/>
        <f:selectItem itemLabel="#{resourcesBundle.diwan_boss}" itemValue="2"/>
        <f:selectItem itemLabel="#{resourcesBundle.undersecretary}" itemValue="3"/>
         <f:selectItem itemLabel="#{resourcesBundle.public_works_and_baladya_minister}" itemValue="4"/>
        <f:selectItem itemLabel="#{resourcesBundle.diawn_amiry_agent}" itemValue="5"/>
        <f:selectItem itemLabel="#{resourcesBundle.other_sign}" itemValue="-100"/>
        <a4j:support event="onchange" reRender="sign_panel"/>
    </t:selectOneMenu>
    <t:panelGroup rendered="#{pageBeanName.signature == '-100'}" colspan="2" >
        <t:inputText id="signStr" value="#{pageBeanName.signatureStr}" styleClass="mastertextboxlarge" style="margin-right: 116px;"/>
    </t:panelGroup>
    </t:panelGrid>
    
    <t:panelGroup forceId="true" id="insurPaymentOrderCustomDiv">
        <%--<t:dataTable id="paymentOrderDetails_data" var="list" value="#{pageBeanName.paymentOrderDetailsList}"
                     binding="#{pageBeanName.payOrderDtlsDataTable}" forceIdIndexFormula="#{list.code.key}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:salCalcException_data');"
                     footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="name_column" sortable="false" width="35%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.bank_name}"/>
                </f:facet>
                <h:inputText id="content_name" value="#{list.banksDTO.name}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="emp_no_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.emp_no}"/>
                </f:facet>
                <h:inputText id="content_name" value="#{list.bankCount}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="total_amount_transferred_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.total_amount_transferred}"/>
                </f:facet>
                <h:inputText id="content_name" value="#{list.bankTotal}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="btns_transferred_column" sortable="false" width="35%">
                <f:facet name="header"></f:facet>
                <t:commandButton value="#{resourcesBundle.payment_order_print}" styleClass="cssButtonSmall"
                                 type="button">
                    <a4j:support event="onclick" action="#{pageBeanName.printPaymentOrder}"
                                 reRender="reportUrlId,reportWindowId"
                                 oncomplete="openNewWindow('reportUrlId','reportWindowId');"/>
                </t:commandButton>
                <t:commandButton value="#{resourcesBundle.sheet_print}" styleClass="cssButtonSmall" type="button">
                    <a4j:support event="onclick" action="#{pageBeanName.printSheet}"
                                 reRender="reportUrlId,reportWindowId"
                                 oncomplete="openNewWindow('reportUrlId','reportWindowId');"/>
                </t:commandButton>
            </t:column>
        </t:dataTable>--%>
        <%--<t:panelGroup rendered="#{!empty pageBeanName.paymentOrderDetailsList }" >
           <t:panelGroup style="border: 1px solid #BFE4F2;
                                display: block;
                                float: right;
                                margin-right: 221px;
                                text-align: center;
                                width: 93px !important;">
                <t:outputText value="#{pageBeanName.totalEmpsCount}" styleClass="lable01" style="border:1px;"/>
            </t:panelGroup>
             <t:panelGroup style="border: 1px solid #BFE4F2;
                                display: block;
                                float: right;
                                margin-right: 0;
                                text-align: center;
                                width: 91px;">
                <t:outputText value="#{pageBeanName.totalEmpsMoney}" styleClass="lable01" style="border:1px;"/>
            </t:panelGroup>
             
        </t:panelGroup>--%>
        <%--<t:panelGrid columns="1" rendered="#{empty pageBeanName.paymentOrderDetailsList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>--%>



    
    </t:panelGroup>
    
    
    
</t:panelGrid>


                             
    <t:panelGroup style="display:block;text-align: center;">
        <t:commandButton value="#{resourcesBundle.payment_order_print}" styleClass="cssButtonSmall"  disabled="#{pageBeanName.insuranceRetrieval}"
                         type="button" onclick="if(validatemyForm('inspayoreder')){openReport();}else{return false;}">
            
        </t:commandButton>
        <t:commandButton id="backButtonCustomDiv3" forceId="true" type="button"  
                         styleClass="cssButtonSmall" value="#{globalResources.back}" 
                         onclick="hideLookUpDiv(window.blocker,window.customDiv3,null,null,null);return false;">
        </t:commandButton>

    </t:panelGroup>
    <a4j:jsFunction name="openReport" action="#{pageBeanName.printPaymentOrderInsurance}"
                         reRender="insUeportUrlId"
                         oncomplete="openReportWindow('insUeportUrlId');"/>
<t:inputHidden id="insUeportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>
