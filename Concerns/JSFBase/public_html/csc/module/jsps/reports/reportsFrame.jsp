<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!-- read the current page direction from the SharedUtil Bean -->

   
<h:form id="form">
<t:aliasBean alias="#{pageBeanName}" value="#{ReportsBean}">
<div style="text-align:center;color:#CC0000;font-weight:bold;font-size:22px;">
<%
    /* to build parameters string from RequestParameters map whether it is pass from openReportWindow Jsfunction or navigation menu link
    *  added by TechnicalTeam[Ali Agamy] 
    *  13/10/2015
    *  CSC-14760 
    */
    String str="";
    Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements())
    {
        String paramName = paramNames.nextElement();
        String[] paramValues = request.getParameterValues(paramName);
        for (int i = 0; i < paramValues.length; i++) 
        {
            String paramValue = paramValues[i];
            str=str + paramName + "=" + paramValue;
        }
        str=str+"&";
    }
    //remove the last character from String
    str = str.substring(0,str.length()-1);
    //System.out.println(str);    
%>

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
    
    <iframe id='iFrame2' frameborder="0" scrolling="no" src="reportPage.jsf?<%=str%>" width="100%" height="100%"></iframe>
    
</t:aliasBean>
    </h:form>