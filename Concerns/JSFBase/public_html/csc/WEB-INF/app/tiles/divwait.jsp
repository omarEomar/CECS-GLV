<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:div forceId="true" id="divWait" styleClass="divWating">
    <t:graphicImage url="/app/media/images/plz_wait.gif" id="GI-divwait"/>
</t:div>
<t:inputHidden id="progressMsgVal" forceId="true" value="#{pageBeanName.progressMsgVal}"/>
<t:outputText style="visibility:hidden" id="pleaseWait" forceId="true" value="#{globalResources.pleaseWait}"/>