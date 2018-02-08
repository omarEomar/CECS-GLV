<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form enctype="multipart/form-data" id="myForm" binding="#{fileAttachmentsIntegrationBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infintegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{fileAttachmentsIntegrationBean}">
            <tiles:insert flush="false" definition="attachmentsIntegration.page">
                <t:saveState value="#{pageBeanName.forShowAttachmentOnly}"/>
                
                <t:saveState value="#{pageBeanName.selectedDocType}"/>
                <t:saveState value="#{pageBeanName.docTypeList}"/>
                <t:saveState value="#{pageBeanName.docName}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.fromDate}"/>
                <t:saveState value="#{pageBeanName.untilDate}"/>
                <t:saveState value="#{pageBeanName.fakeImageString}"/>
                
                <t:saveState value="#{pageBeanName.uploadedFile}"/>
                <t:saveState value="#{pageBeanName.uploadingError}"/>
                <t:saveState value="#{pageBeanName.invalidImageType}"/>
                
                <t:saveState value="#{pageBeanName.clonedPageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedImagePath}"/>
                
                <t:saveState value="#{pageBeanName.attachmentList}"/>
                
                <t:saveState value="#{pageBeanName.disableSelectedDocType}"/>
                <t:saveState value="#{pageBeanName.filesTransaction}"/>
                <t:saveState value="#{pageBeanName.file}"/>
                <t:saveState value="#{pageBeanName.doctypeName}"/>
                <t:saveState value="#{pageBeanName.selectedDocType}"/>
                <t:saveState value="#{pageBeanName.disableSelectedDocType}"/>
                <t:saveState value="#{pageBeanName.doctypeName}"/>
                <t:saveState value="#{pageBeanName.currentDisplayList}"/>
                <t:saveState value="#{pageBeanName.refTabrecSerial}"/>
                <t:saveState value="#{pageBeanName.selectedElementName}"/>
            </tiles:insert>
        </t:aliasBean>
        <c:scriptGenerator form="myForm" id="scriptGenerator"/>
    </h:form>
</f:view>

<script type="text/javascript">
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
          setFocusOnElement('DocName');
        }
</script>
