<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<form id="reportFormTest" name="nReportFormTest" ></form>
<t:aliasBean alias="#{pageBeanName}" value="#{ReportsBean}">
<t:inputHidden forceId="true" id="uName" value="#{ReportsBean.reportUserName}" />
<t:inputHidden forceId="true" id="uPass" value="#{ReportsBean.reportUserPass}"/>
<t:inputHidden forceId="true" id="rptId" value="#{ReportsBean.reportId}"></t:inputHidden>
<t:inputHidden forceId="true" id="url" value="#{ReportsBean.actionURL}"></t:inputHidden>
    </t:aliasBean>

<script type="text/javascript">
  var param;
  var parameter = new Array();
  var reports = new Array();
  var reportAction="";
  var reportParams = "";
  var reportId = document.getElementById("rptId").value;
  var reportUName = document.getElementById("uName").value;
  var reportPassword = document.getElementById("uPass").value;
  var reportAction = document.getElementById("url").value;
  
  
    openReport(reportId)
    function openReport(id){
        submitNewForm();
    }
    
  function submitNewForm() {
     var form = document.forms['reportFormTest'];
     
      form.setAttribute("action", reportAction);     
      form.setAttribute("method", "POST");
      form.setAttribute("target", "_self");
      form.innerHTML = '';
      
      var html = '<input name=\"reportForm_SUBMIT\" value=\"1\" type=\"submit\" style=\"display:none;\">';
      html += '<input name=\"' + "id" + '\" value=\"' + reportUName + '\" type=\"hidden\">';
      html += '<input name=\"' + "passwd" + '\" value=\"' + reportPassword + '\" type=\"hidden\">';
      
      form.innerHTML = html;
      form.submit();
      return false;
  }

</script>