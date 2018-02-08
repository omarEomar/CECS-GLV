<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.ded.integration.presentation.resources.integration"
                  var="intgBundle"/>
    <h:form id="myForm" binding="#{deductionEnquiryBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{deductionEnquiryBean}">
            <tiles:insert flush="false" definition="deductionEnquiry.page">
                <t:saveState value="#{deductionEnquiryBean.pageDTO}"/>
                <t:saveState value="#{deductionEnquiryBean.elementGuideComboBoxList}"/>
                <t:saveState value="#{deductionEnquiryBean.secandaryGuideComboBoxList}"/>
                <t:saveState value="#{deductionEnquiryBean.categoryComboBoxList}"/>
                <t:saveState value="#{deductionEnquiryBean.ministryComboBoxList}"/>
                <t:saveState value="#{deductionEnquiryBean.dedForCategories}"/>
                <t:saveState value="#{deductionEnquiryBean.dedForMinistries}"/>
                <t:saveState value="#{deductionEnquiryBean.resultMode}"/>
                <t:saveState value="#{deductionEnquiryBean.backNavCase}"/>
                <t:saveState value="#{deductionEnquiryBean.savedDataObjects}"/>
                <t:saveState value="#{deductionEnquiryBean.filterMethodName}"/>
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptpanel">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
   
<script type="text/javascript"> 

function openDivWithWeekDays(){
    
    changeVisibilityDiv(window.blocker,window.customDiv2);
    fillWeekDays();
  

}


 function fillWeekDays(){
    var empDedDataTable = document.getElementById("myForm:days_table1");
    var size=empDedDataTable.rows.length;
    //alert('size'+size);
    for (var i=0; i<size; i++) {
          var elem= document.getElementById("date1["+i+"]");
           //alert('i'+i);  
           
           //alert('v'+d);  
            var week_day_id = "week_day1[" + i + "]";  
           // alert('week_day_id'+week_day_id);
            
          
           /* 
           */
           
            var strDate = elem.innerHTML;
            var dateParts = strDate.split("/");
            var actualDate= new Date(dateParts[2], (dateParts[1]- 1), dateParts[0]); 

            var weekday = new Array(7);
            weekday[0] = "الأحد";
            weekday[1] = "الأثنين";
            weekday[2] = "الثلاثاء";
            weekday[3] = "الأربعاء";
            weekday[4] = "الخميس";
            weekday[5] = "الجمعة";
            weekday[6] = "السبت";
            
            var dayName = weekday[new Date(actualDate).getDay()];
            
          //  alert(dayName);
           
            document.getElementById(week_day_id).innerHTML = dayName;
            }
    
    
 }
    
   /* function showWeekDays() {
   // alert('here');
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
            var week_day_id = "week_day1[" + index + "]";      
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
    */
</script>
  
</f:view>