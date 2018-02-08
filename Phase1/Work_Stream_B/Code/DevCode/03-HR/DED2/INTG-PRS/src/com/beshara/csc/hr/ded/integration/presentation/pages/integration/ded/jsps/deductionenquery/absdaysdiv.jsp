<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"
           prefix="c"%>

<t:panelGroup styleClass="customDiv_header" style="width:590px !important">
    <t:outputText value="#{resourcesBundle.discount_days}" />
</t:panelGroup>

<t:panelGroup styleClass="lovDiv_body">

<t:panelGrid width="100%" align="center" cellpadding="3" cellspacing="0" forceId="true" id="discountCalcPanelGroup" >
            <t:panelGroup id="days_table_panel" forceId="true"  style="display: block; height: 180px; overflow: auto; text-align: center;">
                <t:dataTable id="days_table" forceId="true" var="day" value="#{deductionEnquiryBean.absDays}" preserveDataModel="false" 
                                footerClass="grid-footer" styleClass="table table-bordered table-hover"
                                 headerClass="table_header" rowClasses="raw_odd,raw_even" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                 align="top" dir="rtl" renderedIfEmpty="false">
                    <t:column  width="30%">
                        <f:facet name="header">
                            <t:outputText value="#{resourcesBundle.day}" />
                        </f:facet>
                        <t:outputText id="week_day" forceId="true" value="" styleClass="output_label"/>
                   </t:column>
                   <t:column  width="70%">
                        <f:facet name="header">
                            <t:outputText value="#{resourcesBundle.date}" />
                        </f:facet>
                        <t:outputText id="date" value="#{day}" styleClass="output_label cal" forceId="true" converter="BesharaDateConverter"/>
                   </t:column>
                </t:dataTable>
                <t:panelGrid id="datatable_empty_msg_panel" forceId="true" rendered="#{empty deductionEnquiryBean.absDays}" styleClass="empty_table_msg">
                    <t:outputText id="datatable_empty_msg"  value="#{resourcesBundle.discount_days_not_found}" style="display: block; text-align: center; width: 198px;"/>
                </t:panelGrid>
            </t:panelGroup>
            
       
</t:panelGrid>
<t:panelGroup style="display:block;text-align:center;">
   <t:commandButton type="button" onclick=" hideDiv('customDiv3');" styleClass="btn btn-info" value="#{resourcesBundle.cancel}"  />
</t:panelGroup>
</t:panelGroup>

<script type="text/javascript"> 
    showWeekDays();
    function showWeekDays() {
        jQuery('.cal').each(function () {
        var d = this.innerHTML;
        if(d != null && d != ''){
            var compId = this.id;
            var index = 0;
            if(compId.length > 7){
                 index = compId.substring(5, 7);
            }else{
                 index = compId.substring(5, 6);
            }
            var week_day_id = "week_day[" + index + "]";      
            var date = parseDate(d);
            var weekday = new Array(7);
            weekday[0] = "الأحد";
            weekday[1] = "الأثنين";
            weekday[2] = "الثلاثاء";
            weekday[3] = "الأربعاء";
            weekday[4] = "الخميس";
            weekday[5] = "الجمعة";
            weekday[6] = "السبت";
        
            var n = weekday[date.getDay()];
            document.getElementById(week_day_id).innerHTML = n;
        }        
                
       });
    }
</script>
  
