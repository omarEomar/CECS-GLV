 






<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%-- <t:messages showDetail="true" id="test" forceId="true"/>--%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-5-row-filter">
        <t:dataTable id="attch_dataT_data" var="list" value="#{pageBeanName.currentDisplayList}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:attch_dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,sztandardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column width="660">
                <f:facet name="header">
                    <t:outputText id="attachment_desc_label" value="#{infintegrationBundle.docAttachmentsName}"/>
                </f:facet>
                <t:outputText id="attachment_desc" forceId="true" value="#{list.attachmentDesc}"/>
            </t:column>
            <t:column width="200">
                <f:facet name="header">
                    <t:outputText id="fromDate11" value="#{infintegrationBundle.fromDate}"/>
                </f:facet>
                <h:outputText value="#{list.validFrom}" id="from_Date">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            <t:column width="200">
                <f:facet name="header">
                    <t:outputText id="untilDate" value="#{infintegrationBundle.untilDate}"/>
                </f:facet>
                <t:outputText value="#{list.validUntil}" id="until_Date">
                    <f:converter converterId="SqlDateConverter"/>
                </t:outputText>
            </t:column>
            <t:column width="120">
                <f:facet name="header">
                    <t:outputText id="view" value="#{globalResources.view_menu}"/>
                </f:facet>
                <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="..."
                                   actionListener="#{pageBeanName.showAttachmentsDetails}"
                                   reRender="attIntgHiddenValues" oncomplete="openRegIntgDecisionDetailsWindow();"></a4j:commandButton>
            </t:column>
            <t:column width="120">
                <f:facet name="header">
                    <t:outputText id="delete" value="#{infintegrationBundle.delete}"/>
                </f:facet>
                <t:commandButton styleClass="deleteRowDatatable" id="deleteAttachment" value="delete"
                                 action="#{pageBeanName.deleteAttachment}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup forceId="true" id="attIntgHiddenValues">
    <t:inputHidden forceId="true" id="docIntgFullURLId" value="#{pageBeanName.selectedImagePath}"/>
    <t:outputText forceId="true" id="docIntgWindowTitleId" value="#{infintegrationBundle.docAttachmentsPath}"
                  style="display:none;"/>
</t:panelGroup>
<script type="text/javascript">
  function openRegIntgDecisionDetailsWindow() {
      openNewWindow2('docIntgFullURLId', 'docIntgWindowTitleId');
  }

  function openNewWindow2(fullURL, winName) {

      var url = document.getElementById(fullURL).value;
      var windowTitle;
      if (document.getElementById(winName) != null) {
          windowTitle = document.getElementById(winName).value;
          if (windowTitle == null || windowTitle == '') {
              windowTitle = document.getElementById(winName).innerHTML;
          }
      }
      var wOpen;
      var sOptions;
      var popupWindowWidth = 700;
      var popupWindowheight = 540;

      sOptions = 'status=no,menubar=no,location=no,scrollbars=yes,toolbar=no,resizable=0,addressbar=0';
      sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
      sOptions = sOptions + ',height=' + (popupWindowheight).toString();
      sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

      globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=680 height=500 src=\"" + url + "\"/></body></html>";
      wOpen = window.open("", '', sOptions);
      wOpen.document.write(globalHTML);
      wOpen.focus();
      wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

      return wOpen;
  }
</script>
