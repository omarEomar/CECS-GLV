<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<style>
        .fileUploadFakeInputText {
            background: none repeat scroll 0 0 #ffffff;
            border: 0 none;
            border-radius: 5px;
            box-shadow: 0 0 2px #000000;
            color: #1d7cbf;
            font-family: Tahoma;
            font-size: 12px;
            height: 23px;
            left: 0;
            padding-left: 5px;
            padding-right: 5px;
            position: absolute;
            right: 3px;
            top: 1px;
            width: 277px;
            z-index: 3;
        }

        .fileUploadFakeButton {
            right: 285px;
        }

        .fileUploadComponent {
            height: 25px;
            margin-right: 0;
            opacity: 0;
            padding-right: 0;
            width: 360px;
            z-index: 2;
        }
    </style>
<%-- <t:messages showDetail="true"/>--%>
<t:panelGrid columns="4" cellpadding="3" cellspacing="0" rowClasses="row02,row01" width="100%">
    <h:outputText value="#{infintegrationBundle.description}" styleClass="divtext"/>
   
            <t:panelGroup id="description_panel" forceId="true">
                <t:inputText maxlength="12" forceId="true" id="descriptionAdd" value="#{pageBeanName.selectedElementName}"
                             styleClass="textbox"  style="margin-right: 3px; width: 300px;" disabled="true"/>
            </t:panelGroup>
      
    
    <h:outputText value="#{infintegrationBundle.doctype}" styleClass="divtext" rendered="#{!pageBeanName.disableSelectedDocType}"/>
    <t:panelGroup rendered="#{!pageBeanName.disableSelectedDocType}">
        
        <t:selectOneMenu tabindex="5" forceId="true" id="doctypeMenu" styleClass="textboxmedium" onchange="submit()"
                         valueChangeListener="#{pageBeanName.fillAttachmentList}" style="width: 362px;"
                         value="#{pageBeanName.selectedDocType}" >
            <f:selectItem itemLabel="#{infintegrationBundle.selectOne}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.docTypeList}" itemLabel="#{list.name}" itemValue="#{list.code.key}"
                           var="list"/>
        </t:selectOneMenu>
        
    </t:panelGroup>
    <t:panelGroup rendered="#{pageBeanName.disableSelectedDocType}" colspan="4">
    </t:panelGroup>
    <h:outputText value="#{infintegrationBundle.docAttachmentsName}" styleClass="divtext"/>
    <t:panelGroup >
        <t:inputText styleClass="textboxmedium2" forceId="true" id="DocName" style="margin-right: 3px; width: 300px;"
                     value="#{pageBeanName.docName}" onblur="setFocusOnElement('notesId');" onkeypress="doNothing(event);"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="DocName" display="dynamic"
                                  errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <%-- ///////////// upload start--%>
    <t:outputText value="#{infintegrationBundle.attach}" styleClass="lable01"/>
    <t:panelGroup  id="attachmentPanel" forceId="true"
                  style="display:block;position: relative;min-height: 26px;">
        
        <t:inputFileUpload id="myInputFileUpload" styleClass="fileUploadComponent"
                           onchange="document.getElementById('myInputText').value=this.value;" storage="file"
                           accept="image/*,application/pdf,application/doc"
                           value="#{pageBeanName.uploadedFile}"/>
        <t:inputText id="myInputText" onkeydown="disableEditing(event)" value="#{pageBeanName.fakeImageString}"
                     forceId="true" styleClass="fileUploadFakeInputText" onmouseout="hideTip()"
                     onmouseover="doTooltip(event,'(doc,docs,img,pdf,xlsx,txt)')" />
        <h:commandButton styleClass="fileUploadFakeButton" id="browseBTN" value="#{infintegrationBundle.browse}"
                         onclick="return false;" />
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:outputText value="#{infintegrationBundle.invalid_image_type}" styleClass="errMsg"
                      rendered="#{pageBeanName.invalidImageType}"/>
        <t:outputText value="#{infintegrationBundle.uploading_error}" styleClass="errMsg"
                      rendered="#{pageBeanName.uploadingError}"/>
        <c:customValidator componentToValidate="myInputText" errorMessage="#{globalResources.missingField}"
                           function="validateAttachment" display="dynamic"/>
        
    </t:panelGroup>
    
    <t:outputText forceId="true" value="#{infintegrationBundle.notes}" />
    <t:panelGroup styleClass="preformatted" id="notesTitleTextPG" forceId="true" colspan="3">
        <t:inputTextarea forceId="true" id="notesId" value="#{pageBeanName.comments}" 
                          style="width: 763px;height: 66px !important;" onblur="setFocusOnElement('addIntigrationButton');"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup style="text-align: center; display: block;">
    <t:commandButton forceId="true" id="addIntigrationButton" styleClass="cssButtonSmall" value="#{globalResources.add}"
                     action="#{pageBeanName.save}" onclick="return validatemyForm();" />
</t:panelGroup>
<script type="text/javascript">
setFocusOnElement('DocName');
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
  
  function doNothing(event){
  
        var unicode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        if(unicode==13){
            if(event.preventDefault){
                 event.preventDefault() ;
            }else{
              event.returnValue = false;
            } 

        }
        
    }
   
</script>
