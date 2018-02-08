<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view locale="#{shared_util.locale}">
  <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
  <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
   <h:form id="myForm" binding="#{error_page.frm}" >
    <t:aliasBean alias="#{pageBeanName}"  value="#{error_page}">
      <tiles:insert flush="false" definition="errpage" >
      </tiles:insert>
  </t:aliasBean>
  </h:form> 
</f:view>