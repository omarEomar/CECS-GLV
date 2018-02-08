<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<htm:style >
    #customDiv1{
        top:70px !important;
        right: 15% !important;
        width: 700px !important;
       
       
    }
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" id="paymentOrderCancelMainPanel" forceId="true">
    <%--<t:panelGrid columns="2" cellpadding="3" cellspacing="0" rowClasses="row01,row02" id="signature_panel" forceId="true" width="100%">
    <t:outputLabel value="#{resourcesBundle.signature}" styleClass="lable01"/>
    <t:selectOneMenu forceId="true" id="signatureList" styleClass="DropdownboxLarge" value="#{pageBeanName.signature}">
        <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
        <f:selectItem itemLabel="#{resourcesBundle.general_manager}" itemValue="1"/>
        <f:selectItem itemLabel="#{resourcesBundle.diwan_boss}" itemValue="2"/>
        <f:selectItem itemLabel="#{resourcesBundle.undersecretary}" itemValue="3"/>
        <f:selectItem itemLabel="#{resourcesBundle.other_sign}" itemValue="-100"/>
        <a4j:support event="onchange" reRender="signature_panel"/>
    </t:selectOneMenu>
    <t:panelGroup rendered="#{pageBeanName.signature == '-100'}" colspan="2" >
    <t:inputText id="signatureStr" value="#{pageBeanName.signatureStr}" styleClass="mastertextboxlarge" style="margin-right: 73px;"/>
    </t:panelGroup>
    </t:panelGrid>--%>
    <t:panelGroup forceId="true" id="paymentOrderCancelCustomDiv" style="display:block;height:220px;overflow:auto;" >
       <%-- <t:dataTable id="paymentOrderCancel_data" var="listC" value="#{pageBeanName.paymentOrderDetailsList}"
                     binding="#{pageBeanName.payOrderDtlsDataTable}" forceIdIndexFormula="#{listC.code.key}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index2" renderedIfEmpty="false"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:salCalcException_data');"
                     footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">--%>
                     
           
            <t:dataTable id="paymentOrderCancel_data" var="listC" value="#{pageBeanName.paymentOrderDetailsList}" preserveDataModel="false" renderedIfEmpty="true" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
                width="100%" align="top" rowIndexVar="index" dir="rtl">

            <t:column id="name_column" sortable="false" width="50%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.bank_name}"/>
                </f:facet>
                <h:inputText id="content_name" value="#{listC.banksDTO.name}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="emp_no_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.emp_no}"/>
                </f:facet>
                <h:inputText id="content_name" value="#{listC.bankCount}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="total_amount_transferred_column" sortable="false" width="30%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.total_amount_transferred}"/>
                </f:facet>
                <h:inputText id="content_name" value="#{listC.bankTotal}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>            
        </t:dataTable>
        
        <t:panelGroup rendered="#{!empty pageBeanName.paymentOrderDetailsList }" >
           <t:panelGroup style="border: 1px solid rgb(191, 228, 242);display: block;margin-right: 305px;text-align: center;width: 117px !important;">
                <t:outputText value="#{pageBeanName.totalEmpsCount}" styleClass="lable01" style="border:1px;"/>
            </t:panelGroup>
             <t:panelGroup style="border: 1px solid rgb(191, 228, 242);display: block;float: left;margin-left: 6px;margin-right: 0;margin-top: -17px;text-align: center;width: 179px;">
                <t:outputText value="#{pageBeanName.totalEmpsMoney}" styleClass="lable01" style="border:1px;"/>
            </t:panelGroup>
        </t:panelGroup>    
     
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.paymentOrderDetailsList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>



    
    </t:panelGroup>
    
    
    
</t:panelGrid>


                             
    <t:panelGrid columns="2" border="0" align="center"  style="text-align: center;">
                
        <t:commandButton  id="payOrderCancelButton" value="#{resourcesBundle.payment_order_cancel_btn_lbl}" styleClass="cssButtonSmall"
                                 type="button" onclick="showDel()">
                    
                    <a4j:jsFunction name="showDel"  oncomplete="hideLookUpDiv(window.blocker,window.customDiv1); changeVisibilityDiv(window.blocker,window.integrationDiv1);return false;"
                                 reRender="divDelete" />
                                
        </t:commandButton>
        <%--<t:commandButton id="payOrderCancelButton" forceId="true" 
                         styleClass="cssButtonSmall" value="#{resourcesBundle.payment_order_cancel_btn_lbl}"                        

                         
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1); changeVisibilityDiv(window.blocker,window.customDiv3);return false;">        
                         --%><!--onclick="hideLookUpDiv(window.blocker,window.customDiv1); changeVisibilityDiv(window.blocker,window.delAlert);settingFoucsAtDivDelete();return false;">--><%--
        </t:commandButton>--%>
        
        
        <t:commandButton id="backButtonCustomDiv1" forceId="true" 
                         styleClass="cssButtonSmall" value="#{globalResources.back}" 
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1);return false;">
        </t:commandButton>

    </t:panelGrid>
<%--<t:inputHidden id="reportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>--%>
<%--<t:inputHidden id="reportWindowId" forceId="true" value="#{pageBeanName.reportWindowName}"/>--%>
<%--<t:saveState value="#{pageBeanName.paymentOrderDetailsList}"/>--%>
<%--<t:saveState value="#{pageBeanName.paymentOrderDetailsListSize}"/>--%>
<%--<t:saveState value="#{pageBeanName.reportUrlLink}"/>--%>
<%--<t:saveState value="#{pageBeanName.reportWindowName}"/>--%>
<%--<t:saveState value="#{pageBeanName.signature}"/>--%>
<%--<t:saveState value="#{pageBeanName.signatureStr}"/>--%>
