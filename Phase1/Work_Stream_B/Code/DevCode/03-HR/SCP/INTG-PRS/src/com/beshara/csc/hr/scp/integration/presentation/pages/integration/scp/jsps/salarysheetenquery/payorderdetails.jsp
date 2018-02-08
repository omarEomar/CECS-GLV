<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<htm:style >
    #customDiv2{
        top:70px !important;
        right: 15% !important;
        width: 700px !important;
    }
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" id="paymentOrderDetailsMainPanel" forceId="true">
    <t:panelGrid columns="2" cellpadding="3" cellspacing="0" rowClasses="row01,row02" id="signature_panel" forceId="true" width="100%">
    <t:outputLabel value="#{resourcesBundle.signature}" styleClass="lable01"/>
    <t:selectOneMenu forceId="true" id="signatureList" styleClass="DropdownboxLarge" value="#{pageBeanName.signature1}">
        <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
        <f:selectItem itemLabel="#{resourcesBundle.general_manager}" itemValue="1"/>
        <f:selectItem itemLabel="#{resourcesBundle.diwan_boss}" itemValue="2"/>
        <f:selectItem itemLabel="#{resourcesBundle.undersecretary}" itemValue="3"/>
        <f:selectItem itemLabel="#{resourcesBundle.public_works_and_baladya_minister}" itemValue="4"/>
        <f:selectItem itemLabel="#{resourcesBundle.diawn_amiry_agent}" itemValue="5"/>
        <f:selectItem itemLabel="#{resourcesBundle.other_sign}" itemValue="-100"/>
        <a4j:support event="onchange" reRender="signature_panel"/>
    </t:selectOneMenu>
    <t:panelGroup rendered="#{pageBeanName.signature1 == '-100'}" colspan="2" >
    <t:inputText id="signatureStr" value="#{pageBeanName.signatureStr1}" styleClass="mastertextboxlarge" style="margin-right: 73px;"/>
    </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="paymentOrderDetailsCustomDiv" styleClass="delDivScroll" >
        <t:dataTable id="paymentOrderDetails_data" var="list" value="#{pageBeanName.paymentOrderDetailsList}"
                     binding="#{pageBeanName.payOrderDtlsDataTable}" forceIdIndexFormula="#{list.code.key}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" 
                     rowOnMouseOver="javascript:addRowHighlight('myForm:salCalcException_data');"
                     footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="name_column" sortable="false" width="30%">
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
                <h:inputText id="content_name" value="#{list.bankTotal}" readonly="true" converter="BigDecimalThreeDigitConverter"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="status_column" sortable="false" width="5%">
                <f:facet name="header">
                <h:outputText id="sort_status" forceId="true" styleClass="headerLink" value="#{resourcesBundle.fileStatus}"/>
                </f:facet>
                
                <t:graphicImage id="graph1"  value="/app/media/images/green-circle.gif"   onmouseout="hideTip()" onmouseover="doTooltip(event,' #{resourcesBundle.file_created}')" rendered="#{list.fileCreated}"/>
                <t:graphicImage id="graph2"  value="/app/media/images/red-circle.gif"   onmouseout="hideTip()" onmouseover="doTooltip(event,' #{resourcesBundle.file_not_created}')" rendered="#{!list.fileCreated}"/>                    
            </t:column>
            <t:column id="btns_transferred_column" sortable="false" width="35%">
                <f:facet name="header"></f:facet>
                <t:commandButton value="#{resourcesBundle.payment_order_print}" styleClass="cssButtonSmall"
                                 type="button">
                    <a4j:support event="onclick" action="#{pageBeanName.printPaymentOrder}"
                                 reRender="reportUrlId"
                                 oncomplete="openReportWindow('reportUrlId');"/>
                </t:commandButton>
                <t:commandButton value="#{resourcesBundle.sheet_print}" styleClass="cssButtonSmall" type="button">
                    <a4j:support event="onclick" action="#{pageBeanName.printSheet}"
                                 reRender="reportUrlId"
                                 oncomplete="openReportWindow('reportUrlId');"/>
                </t:commandButton>
            </t:column>
        </t:dataTable>
        <t:panelGroup rendered="#{!empty pageBeanName.paymentOrderDetailsList }" >
           <t:panelGroup style="border: 1px solid #BFE4F2;
                                display: block;
                                float: right;
                                margin-right: 185px;
                                text-align: center;
                                width: 88px !important;">
                <t:outputText value="#{pageBeanName.totalEmpsCount}" styleClass="lable01" style="border:1px;"/>
            </t:panelGroup>
             <t:panelGroup style="border: 1px solid #BFE4F2;
                                display: block;
                                float: right;
                                margin-right: 0;
                                text-align: center;
                                width: 88px;">
                <t:outputText value="#{pageBeanName.totalEmpsMoney}"  converter="BigDecimalThreeDigitConverter" styleClass="lable01" style="border:1px;"/>
            </t:panelGroup>
             
        </t:panelGroup>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.paymentOrderDetailsList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>



    
    </t:panelGroup>
    <t:panelGrid columns="3" width="100%">
        <t:panelGroup >
            <h:outputText value="#{resourcesBundle.payment_order_reciever}" styleClass="lable01"/>
            <t:selectOneRadio id="recieverLBRadioButton" value="#{pageBeanName.reciever}" converter="javax.faces.Long"  styleClass="lable01">
                <f:selectItem  itemLabel="#{resourcesBundle.payment_order_reciever_1}" itemValue="#{pageBeanName.reciever1}"/>
                <f:selectItem  itemLabel="#{resourcesBundle.payment_order_reciever_2}" itemValue="#{pageBeanName.reciever2}"/>
            </t:selectOneRadio>
        </t:panelGroup>
        
        <t:commandButton value="#{resourcesBundle.print_central_banks}" styleClass="cssButtonSmall" type="button" >
            <a4j:support event="onclick" action="#{pageBeanName.printCentralBankPayOrder}"
                         reRender="reportUrlId"
                         oncomplete="openReportWindow('reportUrlId');"/>
        </t:commandButton>
        <t:commandButton value="#{resourcesBundle.payment_order_local_banks}" styleClass="cssButtonSmall" type="button" >
            <a4j:support event="onclick" action="#{pageBeanName.printLocalBanksPayOrder}"
                         reRender="reportUrlId"
                         oncomplete="openReportWindow('reportUrlId');"/>
        </t:commandButton>
        
    </t:panelGrid>
    
    
</t:panelGrid>


                             
    <t:panelGrid columns="1" border="0" align="center"  width="100%" style="text-align: center;">
        <t:commandButton id="backButtonCustomDiv2" forceId="true" 
                         styleClass="cssButtonSmall" value="#{globalResources.back}" 
                         onclick="hideLookUpDiv(window.blocker,window.customDiv2);return false;">
        </t:commandButton>

    </t:panelGrid>
<t:inputHidden id="reportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>


