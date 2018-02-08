<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="cv"%>
<t:div forceId="true" id="lookupAddDiv" style="visibility:visible;">
    <t:panelGrid columns="2" width="100%" forceId="true" id="qulPanel" rowClasses="row01,row02" cellpadding="3"
                 cellspacing="0">
        <%-- <t:panelGroup colspan="6" forceId="true" id="errMsgPanel" rendered="#{!pageBeanName.updatePersonInformaion
             && pageBeanName.errorMsg != null && pageBeanName.errorMsg != ''}"> <t:panelGrid align="center">
             <h:outputText id="error" value="#{resourcesBundle[pageBeanName.errorMsg]}" styleClass="errMsg" />
             </t:panelGrid> </t:panelGroup>--%>
        <h:outputText value="#{qulintegration.civilCode}" styleClass="lable01 nowrap_txt"/>
        <t:inputText disabled="true" tabindex="10000" forceId="true" id="civilCode" value="#{pageBeanName.civilId}"
                     styleClass="textbox"/>
        <h:outputText value="#{qulintegration.civilName}" styleClass="lable01 nowrap_txt"/>
        <t:inputText disabled="true" tabindex="10000" forceId="true" id="civilName"
                     value="#{pageBeanName.civilId != null ? pageBeanName.citizinFullName :''}"
                     styleClass="textboxmedium"/>
    </t:panelGrid>
</t:div>