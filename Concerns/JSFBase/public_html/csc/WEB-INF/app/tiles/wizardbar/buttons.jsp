<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGrid border="0" columns="2" cellpadding="0" cellspacing="0">
    <t:panelGrid  forceId="true" id="buttonsPanel"  binding="#{wizardBarBean.buttonsPanelGrid}" />
    <t:inputText forceId="true" id="fakeInputForFocus" styleClass="fakeInputForFocus"/>
    <!-- Ashraf Gaber 23/03/2011 handle focus problem HR-1227 point 23 -->
</t:panelGrid>