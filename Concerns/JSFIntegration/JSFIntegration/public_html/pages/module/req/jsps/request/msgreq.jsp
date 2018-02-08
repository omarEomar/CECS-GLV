<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:verbatim>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center"><input type="text" value="${globalResources.SuccessAdds}" class="msg" readonly="readonly"/></td>
    </tr>
    <tr>
        <td align="center">
        </f:verbatim>
            <t:commandButton value="#{globalResources.back}" styleClass="cssButtonSmall" action="#{pageBeanName.resetJsCaller}" onclick="block();"/>
        <f:verbatim>
        </td>
    </tr>
</table>
</f:verbatim>
