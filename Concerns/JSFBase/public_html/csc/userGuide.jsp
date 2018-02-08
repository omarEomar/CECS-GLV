<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<head>
<title>دليل المستخدم</title>
</head>
<f:view locale="#{shared_util.locale}">
 
    <div>
   <center>
   <t:inputHidden value="#{ModuleMenuExt.userGuideUrl}"></t:inputHidden>
       <iframe src="${ModuleMenuExt.userGuideUrl}"  frameborder="0" scrolling="auto" width="100%" height="100%">
       </iframe>
  </center>
  <br>

   </div>
    

</f:view>


