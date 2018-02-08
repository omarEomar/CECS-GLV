<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid columns="1" width="100%" styleClass="gopentab" style="border:1px solid #FFFFFF;">
    <h:panelGroup>
        <t:outputLabel value="#{resourcesBundle.conditionName}:" styleClass="lable01"/>
        <f:verbatim>&nbsp;</f:verbatim>
                <h:outputText value="#{pageBeanName.pageDTO.name}" styleClass="lable01" onmouseout="hideTip()" onmouseover="doTooltip(event,'#{pageBeanName.pageDTO.name}')"/>   
    </h:panelGroup>

</t:panelGrid>
