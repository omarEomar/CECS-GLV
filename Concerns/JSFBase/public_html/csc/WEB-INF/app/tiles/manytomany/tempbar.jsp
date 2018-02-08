<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>
<f:verbatim>
    <script type="text/javascript">
      var buttonOldStyleClass = null;

      function onButtonMouseover(btn, buttonHoverStyleClass) {
          buttonOldStyleClass = btn.className;
          btn.className = buttonHoverStyleClass;
      }

      function onButtonMouseout(btn) {
          btn.className = buttonOldStyleClass;
      }

      function callMenuItemAction(buttonId) {
          document.getElementById(buttonId).click();
      }
    </script>
</f:verbatim>
<t:panelGroup forceId="true" id="OperationBar" style="display:inline;">
    <t:panelGrid id="OperationBarContainer" columns="1" width="100%"
                 rendered="#{OperationBar.pageContainsBar && OperationBar.pageBarRendered}">
        <%-- t:outputText dir="ltr" style="width:100%" styleClass="noOfRecords" value="#{globalResources.noOfRecords} :
             #{OperationBar.recordsCount}" rendered="#{OperationBar.recordsCountRendered}"/--%>
        <t:panelGrid cellpadding="0" cellspacing="0" styleClass="gmainbar" columns="1" width="100%" border="0">
            <t:panelGrid id="buttonsAndMenuContainer" cellpadding="0" cellspacing="0" columns="3" width="97%">
                <t:outputText styleClass="ContainerTitle" dir="ltr" value="#{pageBeanName.titlePage}"/>
                <t:panelGrid id="OperationBarButtonsContainer" cellpadding="0" cellspacing="0"
                             binding="#{OperationBar.buttonsContainer}" rendered="#{OperationBar.buttonsRendered}"/>
                <t:panelGrid id="OperationBarMenuContainer" columns="2" align="left" onclick="closeMenus(this.id);">
                    <t:jscookMenu id="Menu" layout="hbl" theme="BesharaTheme"
                                  styleLocation="/app/media/css/#{globalResources.photoFolder}/jscookmenu"
                                  javascriptLocation="/app/media/css/#{globalResources.photoFolder}/jscookmenu"
                                  binding="#{OperationBar.menu}" rendered="#{OperationBar.menuRendered}"/>
                </t:panelGrid>
            </t:panelGrid>
        </t:panelGrid>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="OperationBarHiddenContainer" binding="#{OperationBar.hiddenContainer}"
                  style="display:none"/>
    <t:saveState id="OperationBarMenuOldViewId" value="#{OperationBar.oldViewId}"/>
    <t:saveState id="OperationBarMenuIntialized" value="#{OperationBar.intialized}"/>
</t:panelGroup>