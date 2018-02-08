<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGrid columns="2" id="navigationpanel">
    <t:commandButton styleClass="cssButtonSmall" id="SaveButtonxx" rendered="#{regulationMaintainBean.maintainMode!=2}" disabled="#{!pageBeanName.renderFinish}" value="#{globalResources.SaveButton}" onclick="return validateRequiredFields('#{regulationRecordsBean.record.rowSize}','#{regResources.Fill_mandotary_cells_Msg}');" action="#{detailBeanName.SaveRecords}" />
    <t:commandButton  forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}" styleClass="cssButtonSmall" action="#{detailBeanName.back}" />
</t:panelGrid>