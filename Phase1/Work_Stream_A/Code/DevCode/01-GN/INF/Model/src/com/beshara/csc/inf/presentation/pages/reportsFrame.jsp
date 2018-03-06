<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!-- read the current page direction from the SharedUtil Bean -->

   
<h:form id="form">
<t:aliasBean alias="#{pageBeanName}" value="#{ReportsBean}">
<div style="text-align:center;color:#CC0000;font-weight:bold;font-size:22px;">

  <t:outputText id="repTitle" forceId="true" escape="false" value="#{ReportsBean.reportTitle}" />
  <t:inputHidden forceId="true" id="pageTitle" value="#{ReportsBean.reportTitle}" />
      <script type="text/javascript">
      var pageTitle = document.getElementById("pageTitle").value;
      changeTitle(pageTitle);
    function changeTitle(title) 
    { 
    document.title =title; 
    }
          
    </script>
</div> 
    <iframe id='iFrame2' frameborder="0" scrolling="no" src="reportPage.jsf?rep=${param.rep}" width="100%" height="100%"></iframe>
    
</t:aliasBean>
    </h:form>