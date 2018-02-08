<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<!-- converter="MonthYearConverter" 
--> 
<t:panelGroup styleClass="customDiv_header" style="width:590px !important">
    <t:outputText value="#{intgBundle.show_Extra_Data}" />
</t:panelGroup>

<t:panelGroup styleClass="lovDiv_body">

<t:panelGrid columns="2" forceId="true" id="extradata" rowClasses="row01,row02" cellpadding="3" cellspacing="0" width="100%">
    
    <t:outputText id="late_date_lbl" value="#{intgBundle.late_month}" forceId="true" styleClass="output_label nowrap_txt" rendered="#{deductionEnquiryBean.ded11369}"/>
    <t:outputText id="absence_date_lbl" value="#{intgBundle.absence_date}" forceId="true" styleClass="output_label nowrap_txt" rendered="#{deductionEnquiryBean.ded11368}"/>    
    
    <t:panelGroup  rendered="#{deductionEnquiryBean.ded11369 || deductionEnquiryBean.ded11368 }">
    <t:inputText styleClass="textboxmedium2" id="absence_month" disabled="true" forceId="true" value="#{deductionEnquiryBean.extraDatadto.absence_Date}"  maxlength="7"  rendered="#{ deductionEnquiryBean.ded11368 }"/>
    <t:inputText id="late_month" disabled="true" forceId="true" value="#{deductionEnquiryBean.extraDatadto.absence_Date}"  maxlength="7"  rendered="#{ deductionEnquiryBean.ded11369 }"/>
    <t:outputText value="(mm/yyyy)" styleClass="gray_hint" rendered="#{deductionEnquiryBean.ded11369 || deductionEnquiryBean.ded11368 }"/>
    </t:panelGroup>    
    <t:outputText id="late_days_no_lbl" value="#{intgBundle.late_days_no}" forceId="true" styleClass="output_label nowrap_txt" rendered="#{deductionEnquiryBean.ded11369}"/>    
    <t:outputText id="absence_days_no_lbl" value="#{intgBundle.absence_days_no}" forceId="true" styleClass="output_label" rendered="#{deductionEnquiryBean.ded11368}"/>
    <t:outputText id="custoday_type_lbl" value="#{intgBundle.custody_type}"  forceId="true" styleClass="output_label" rendered="#{deductionEnquiryBean.ded11407}"/>    
    <t:inputText styleClass="textboxmedium2" id="absence_days_no" disabled="true" forceId="true" value="#{deductionEnquiryBean.noOfDays}"  rendered="#{ deductionEnquiryBean.ded11368 }" />    
    <t:inputText styleClass="textboxmedium2" id="late_days_no" disabled="true" forceId="true" value="#{deductionEnquiryBean.extraDatadto.late_DaysNo}"  rendered="#{ deductionEnquiryBean.ded11369 }" />
    <t:inputText styleClass="textboxmedium2" id="custoday_type" disabled="true" forceId="true" value="#{deductionEnquiryBean.extraDatadto.custodyType == 1 ? intgBundle.hired_emps : deductionEnquiryBean.extraDatadto.custodyType == 2 ? intgBundle.advance_teachers : deductionEnquiryBean.extraDatadto.custodyType == 3 ? intgBundle.advance_cleaners : '' }"  rendered="#{ deductionEnquiryBean.ded11407 }" />
     
</t:panelGrid>

<t:panelGrid width="100%" align="center" cellpadding="3" cellspacing="0" forceId="true" id="discountCalcPanelGroup1" rendered="#{deductionEnquiryBean.ded11368}">
            <t:panelGroup id="days_table_panel1" forceId="true"  styleClass="fullWidthScroll270">
                <t:dataTable id="days_table1" forceId="true" var="day" value="#{deductionEnquiryBean.absDays}" preserveDataModel="false" 
                                footerClass="grid-footer" styleClass="grid-footer"
                                 headerClass="table_header" 
                                 align="top" dir="rtl" renderedIfEmpty="false">
                    <t:column  width="30%">
                        <f:facet name="header">
                            <t:outputText value="#{intgBundle.day}" id="week_day1_header" />
                        </f:facet>
                        <t:outputText id="week_day1" forceId="true" value="" styleClass="output_label"/>
                   </t:column>
                   <t:column  width="70%">
                        <f:facet name="header">
                            <t:outputText value="#{intgBundle.date}"  id="date1_header"/>
                        </f:facet>
                        <t:outputText id="date1" value="#{day}" styleClass="output_label cal" forceId="true" converter="BesharaDateConverter"/>
                   </t:column>
                </t:dataTable>
                <t:panelGrid id="datatable_empty_msg_panel1" forceId="true" rendered="#{empty deductionEnquiryBean.absDays}" styleClass="empty_table_msg">
                    <t:outputText id="datatable_empty_msg1"  value="#{intgBundle.discount_days_not_found}" style="display: block; text-align: center; width: 198px;"/>
                </t:panelGrid>
            </t:panelGroup>
            
       
</t:panelGrid>
<t:panelGroup style="display:block;text-align:center;">
  <t:commandButton id="backButtonCustomDiv2" forceId="true" value="#{globalResources.back}"
                     onclick="hideLookUpDiv(window.blocker,window.customDiv2);" type="button"
                     styleClass="cssButtonSmall"/>
</t:panelGroup>
</t:panelGroup>

