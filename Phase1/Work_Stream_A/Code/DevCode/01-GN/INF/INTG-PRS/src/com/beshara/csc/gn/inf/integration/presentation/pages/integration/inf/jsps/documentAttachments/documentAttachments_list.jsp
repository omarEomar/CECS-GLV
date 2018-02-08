<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form enctype="multipart/form-data" id="myForm" binding="#{documentAttachmentsIntegrationBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infintegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{documentAttachmentsIntegrationBean}">
            <tiles:insert flush="false" definition="documentAttachmentsIntegration.page">
                <t:saveState value="#{pageBeanName.forShowAttachmentOnly}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.empName}"/>
                <t:saveState value="#{pageBeanName.selectedDocType}"/>
                <t:saveState value="#{pageBeanName.docTypeList}"/>
                <t:saveState value="#{pageBeanName.docName}"/>
                <t:saveState value="#{pageBeanName.docPath}"/>
                <t:saveState value="#{pageBeanName.fromDate}"/>
                <t:saveState value="#{pageBeanName.untilDate}"/>
                <t:saveState value="#{pageBeanName.attchmentPath}"/>
                <t:saveState value="#{pageBeanName.attchmentName}"/>
                <t:saveState value="#{pageBeanName.doctypeCode}"/>
                <t:saveState value="#{pageBeanName.fakeImageString}"/>
                <t:saveState value="#{pageBeanName.relativeFileName}"/>
                <t:saveState value="#{pageBeanName.uploadedFile}"/>
                <t:saveState value="#{pageBeanName.uploadingError}"/>
                <t:saveState value="#{pageBeanName.invalidImageType}"/>
                <t:saveState value="#{pageBeanName.detailsSavedStates}"/>
                <t:saveState value="#{pageBeanName.clonedPageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedImagePath}"/>
                <t:saveState value="#{pageBeanName.detailsSavedStates}"/>
                <t:saveState value="#{pageBeanName.personDocumntsDTO}"/>
                <t:saveState value="#{pageBeanName.personDocAttachemntsDTO}"/>
                <t:saveState value="#{pageBeanName.attachmentList}"/>
                <t:saveState value="#{pageBeanName.moduleName}"/>
                <t:saveState value="#{pageBeanName.disableSelectedDocType}"/>
                <t:saveState value="#{pageBeanName.filesTransaction}"/>
                <t:saveState value="#{pageBeanName.file}"/>
                <t:saveState value="#{pageBeanName.doctypeName}"/>
                <t:saveState value="#{pageBeanName.empName}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.selectedDocType}"/>
                <t:saveState value="#{pageBeanName.disableSelectedDocType}"/>
                <t:saveState value="#{pageBeanName.moduleName}"/>
                <t:saveState value="#{pageBeanName.doctypeName}"/>
                <t:saveState value="#{pageBeanName.currentDisplayList}"/>
            </tiles:insert>
        </t:aliasBean>
        <c:scriptGenerator form="myForm" id="scriptGenerator"/>
    </h:form>
</f:view>
