<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<head>
<title> المرفقات</title>
</head>
<f:view locale="#{shared_util.locale}">
 <%--<f:loadBundle basename="com.beshara.csc.nl.org.presentation.resources.org" var="orgResources"/>--%>
 
 <h:form enctype="multipart/form-data" id="myForm"  binding="#{addDocAttachmentsIntegrationBean.frm}">
   <div>
   <center>
    <t:graphicImage forceId="true"  style =" width:100%; height:90%;"
       id="UserGuideButton2"    
       value="#{docAttachmentsIntegrationBean.selectedimgHidden}" ></t:graphicImage>
  </center>
  <br>
  <!-- footer -->
  <center>
  <img src="../../../../module/media/images/footer.jpg" alt="csc footer"   width="100%;" height="30px;"></img>
  </center>
   </div>
   
    
 </h:form>
</f:view>