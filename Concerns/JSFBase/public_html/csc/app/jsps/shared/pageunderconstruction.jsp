<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view locale="#{shared_util.locale}">
      <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
      
      <h:form id="myForm">
            <t:panelGrid align="center">
                  <h:graphicImage value="/app/media/images/uc_message.png"  style=" position: absolute; right: 250px; vertical-align: middle; margin-top:75px;"/>
                  
                  <t:panelGrid align="center">
                              
                  </t:panelGrid>
            </t:panelGrid>
      </h:form>
</f:view>