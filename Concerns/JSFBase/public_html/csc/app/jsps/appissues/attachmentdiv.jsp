<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid columns="4" cellpadding="3" cellspacing="0" rowClasses="row02,row01" width="100%" rendered="#{!attachmentHelperBean.forShowAttachmentOnly}">    
   
  <t:panelGroup colspan="4" style="width:100%">
   <htm:table width="100%">
           <htm:tr>
               <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                   <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{resourcesBundle.supattachments_title_list}" styleClass="lable01"/> </htm:td>
           </htm:tr>
           <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
        </htm:table>
        </t:panelGroup> 
    <%--<h:outputText value="#{infintegrationBundle.doctype}" styleClass="divtext"/>--%>
    <%--<t:panelGroup colspan="3">
        <t:inputText maxlength="12" forceId="true" id="doctypeName" value="#{attachmentHelperBean.doctypeName}"
                     styleClass="textboxmedium" style="margin-right: 3px;" disabled="true"
                     rendered="#{attachmentHelperBean.disableSelectedDocType}"/>
        <t:selectOneMenu tabindex="5" forceId="true" id="doctypeMenu" styleClass="textboxmedium" onchange="submit()"
                         valueChangeListener="#{attachmentHelperBean.fillAttachmentList}"
                         value="#{attachmentHelperBean.selectedDocType}" rendered="#{!attachmentHelperBean.disableSelectedDocType}">
            <f:selectItem itemLabel="#{infintegrationBundle.selectOne}" itemValue=""/>
            <t:selectItems value="#{attachmentHelperBean.docTypeList}" itemLabel="#{list.name}" itemValue="#{list.code.key}"
                           var="list"/>
        </t:selectOneMenu>
        <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!attachmentHelperBean.disableSelectedDocType}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="doctypeMenu" display="dynamic"
                                  active="#{!attachmentHelperBean.disableSelectedDocType}"
                                  errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>--%>
    <h:outputText value="#{infintegrationBundle.docAttachmentsName}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText styleClass="textboxmedium2" forceId="true" id="DocName" style="margin-right: 3px; width: 300px;"
                     value="#{attachmentHelperBean.docName}" onkeypress="FireButtonClickOldStyle(event,'addIntigrationButton')"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="DocName" display="dynamic" group="attachmentGroupDiv" 
                                  errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <%-- ///////////// upload start--%>
    <t:outputText value="#{infintegrationBundle.attach}" styleClass="lable01"/>
    <t:panelGroup colspan="1" id="attachmentPanel" forceId="true"
                  style="display:block;position: relative;min-height: 26px;">
        <div style="position: relative;"></div>
        <t:inputFileUpload id="myInputFileUpload" styleClass="fileUploadComponent"
                           onchange="document.getElementById('myInputText').value=this.value;" storage="file"
                           accept="image/*,application/pdf,application/doc" style="z-index: 12;"
                           value="#{attachmentHelperBean.uploadedFile}"/>
        <t:inputText id="myInputText" onkeydown="disableEditing(event)" value="#{attachmentHelperBean.fakeImageString}"
                     forceId="true" styleClass="fileUploadFakeInputText" onmouseout="hideTip()"
                     onmouseover="doTooltip(event,'(doc,docs,img,pdf,xlsx,txt)')" style="right: 0!important; left: auto!important"/>
        <h:commandButton styleClass="fileUploadFakeButton" value="#{infintegrationBundle.browse}"
                         onclick="return false;" style="left: auto!important; right: 205px!important;"/>/>
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:outputText value="#{infintegrationBundle.invalid_image_type}" styleClass="errMsg"
                      rendered="#{attachmentHelperBean.invalidImageType}"/>
        <t:outputText value="#{infintegrationBundle.uploading_error}" styleClass="errMsg"
                      rendered="#{attachmentHelperBean.uploadingError}"/>
        <c:customValidator componentToValidate="myInputText" errorMessage="#{globalResources.missingField}" 
                           function="validateAttachment" display="dynamic" group="attachmentGroupDiv" />
       
    </t:panelGroup>
    <%--t:panelGroup rendered="#{!attachmentHelperBean.forShowAttachmentOnly}">
    <t:commandButton  forceId="true" id="addIntigrationButton" styleClass="cssButtonSmall" value="#{globalResources.add}"
                     action="#{attachmentHelperBean.save}" onclick="return validatemyForm('attachmentGroupDiv');"
                     style="display: block; position: relative; margin: 13px -324px 0px 0px;"/>
     <f:verbatim>&nbsp;&nbsp;</f:verbatim>
 
    </t:panelGroup--%>
<t:panelGroup style="display: block; width: 445px;" rendered="#{!attachmentHelperBean.forShowAttachmentOnly}">
    <t:commandButton  forceId="true" id="addIntigrationButton" styleClass="cssButtonSmall" value="#{globalResources.add}"
                     action="#{attachmentHelperBean.save}" onclick="return validatemyForm('attachmentGroupDiv');"/>

</t:panelGroup>
</t:panelGrid>



<script type="text/javascript">
 
</script>



<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
    <t:panelGroup forceId="true" id="dataT_data_panel_ATT" styleClass="fullWidthScroll270">
        <t:dataTable id="attch_dataT_data_ATT" var="list" value="#{attachmentHelperBean.currentDisplayList}"
                     rowStyleClass="#{ attachmentHelperBean.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{attachmentHelperBean.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:attch_dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,sztandardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{attachmentHelperBean.sortColumn}" sortAscending="#{attachmentHelperBean.ascending}">
            <t:column width="40%">
                <f:facet name="header">
                    <t:outputText id="attachment_desc_label" value="#{infintegrationBundle.docAttachmentsName}"/>
                </f:facet>
                <t:outputText id="attachment_desc" forceId="true" value="#{list.supattachmentName}"/>
            </t:column>
           <t:column width="40%">
                <f:facet name="header">
                    <t:outputText id="attachment_file_Name" value="#{resourcesBundle.fileName}"/>
                </f:facet>
                <h:outputText value="#{list.name}" id="file_Name">                    
                </h:outputText>
            </t:column>
         
            <t:column width="10%">
                <f:facet name="header">
                    <t:outputText id="view" value="#{globalResources.view_menu}"/>
                </f:facet>
                <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="..."
                                   actionListener="#{attachmentHelperBean.showAttachmentsDetails}"
                                   reRender="attIntgHiddenValues" oncomplete="openRegIntgDecisionDetailsWindow();"></a4j:commandButton>
            </t:column>
            <t:column width="10%" rendered="#{!attachmentHelperBean.forShowAttachmentOnly}">
                <f:facet name="header">
                    <t:outputText id="delete" value="#{infintegrationBundle.delete}"/>
                </f:facet>
                <t:commandButton styleClass="deleteRowDatatable" id="deleteAttachment" value="delete"
                                 action="#{attachmentHelperBean.deleteAttachment}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{attachmentHelperBean.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup forceId="true" id="attIntgHiddenValues">
    <t:inputHidden forceId="true" id="docIntgFullURLId" value="#{attachmentHelperBean.selectedImagePath}"/>
    <t:outputText forceId="true" id="docIntgWindowTitleId" value="#{infintegrationBundle.docAttachmentsPath}"
                  style="display:none;"/>
</t:panelGroup>


<t:saveState value="#{attachmentHelperBean.forShowAttachmentOnly}"/> 
                 <t:saveState value="#{attachmentHelperBean.selectedDocType}"/>
                <t:saveState value="#{attachmentHelperBean.docTypeList}"/>
                <t:saveState value="#{attachmentHelperBean.docName}"/>
                <t:saveState value="#{attachmentHelperBean.docPath}"/>
                <t:saveState value="#{attachmentHelperBean.fromDate}"/>
                <t:saveState value="#{attachmentHelperBean.untilDate}"/>
                <t:saveState value="#{attachmentHelperBean.attchmentPath}"/>
                <t:saveState value="#{attachmentHelperBean.attchmentName}"/>
                <t:saveState value="#{attachmentHelperBean.doctypeCode}"/>
                <t:saveState value="#{attachmentHelperBean.fakeImageString}"/>
                <t:saveState value="#{attachmentHelperBean.relativeFileName}"/>
                <t:saveState value="#{attachmentHelperBean.uploadedFile}"/>
                <t:saveState value="#{attachmentHelperBean.uploadingError}"/>
                <t:saveState value="#{attachmentHelperBean.invalidImageType}"/>
                <t:saveState value="#{attachmentHelperBean.detailsSavedStates}"/>
                <t:saveState value="#{attachmentHelperBean.clonedPageDTO}"/>
                <t:saveState value="#{attachmentHelperBean.selectedImagePath}"/>
                <t:saveState value="#{attachmentHelperBean.detailsSavedStates}"/>                
                <t:saveState value="#{attachmentHelperBean.supAttachmentsDTO}"/>
                <t:saveState value="#{attachmentHelperBean.attachmentList}"/>
                <t:saveState value="#{attachmentHelperBean.moduleName}"/>
                <t:saveState value="#{attachmentHelperBean.disableSelectedDocType}"/>
                <t:saveState value="#{attachmentHelperBean.filesTransaction}"/>
                <t:saveState value="#{attachmentHelperBean.file}"/>
                <t:saveState value="#{attachmentHelperBean.doctypeName}"/>
                
		<t:saveState value="#{attachmentHelperBean.problemCode}"/>
		<t:saveState value="#{attachmentHelperBean.moveSerial}"/>
		<t:saveState value="#{attachmentHelperBean.atctypeCode}"/>                              
                
                <t:saveState value="#{attachmentHelperBean.savedDataObj}"/>
                <t:saveState value="#{attachmentHelperBean.backNavCase}"/>
                <t:saveState value="#{attachmentHelperBean.backMethod}"/>
                <t:saveState value="#{attachmentHelperBean.saveInDB}"/>
                <t:saveState value="#{attachmentHelperBean.currentDisplayList}"/>                
                 <t:saveState value="#{attachmentHelperBean.PAGE_MODE_EDIT}"/>
<script type="text/javascript">
 function updateAppletHiddenFieldByImgsBytes(text) {
      //alert('enter updateAppletHiddenFieldByImgsBytes');
      document.getElementById('appletHiddenField').value = text;
      document.getElementById('appletCButton').click();
  }

  function getSelectedNodeForApplet() {
      //alert('enter getSelectedNodeForApplet');
      return 'true';
  }

  function validateAttachment() {
      var fileUploaderValue = document.getElementById('myInputText').value;
      //var scannerValue = document.getElementById('appletHiddenField').value;&& (scannerValue == null || scannerValue == "")
       
      if ((fileUploaderValue == null || fileUploaderValue == "") ) {
          return false;
      }
      return true;
  }
   function clearInPut() {
      var myInputText = document.getElementById('myInputText').value;
      alert(myInputText);
      var scannerValue = document.getElementById('appletHiddenField').value;
      alert(scannerValue);
       if ((myInputText == null || myInputText == "") && (scannerValue == null || scannerValue == "")){
          document.getElementById('myInputText').value = null;
          document.getElementById('appletHiddenField').value = null;
       }
      
  }
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

<htm:style>


        .fileUploadFakeButton {
            right: 285px!important;
        }


.fileUploadComponent {
    width: 283px!important;
}

</htm:style>
