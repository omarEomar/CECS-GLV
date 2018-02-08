<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:table width="100%" align="center">
    <htm:tr>
        <htm:td align="center" styleClass="row02" style="height:50px" valign="middle"><h:outputText value="#{globalExceptions[error_dissplay.errorMsgKey]}" escape="false" styleClass="errMsg"/></htm:td>
    </htm:tr>
    <htm:tr>
        <htm:td align="center" >
           <h:commandButton value="#{globalResources.back}" action="#{error_dissplay.back}" styleClass="cssButtonSmall"></h:commandButton>
     </htm:td>
    </htm:tr>
</htm:table>
 <t:inputHidden forceId="true" id="errorCode" value="#{error_dissplay.errorMsgKey}"/>
 <t:inputHidden forceId="true" id="exceptionMsg" value="#{error_dissplay.exceptionMsg}"/>
