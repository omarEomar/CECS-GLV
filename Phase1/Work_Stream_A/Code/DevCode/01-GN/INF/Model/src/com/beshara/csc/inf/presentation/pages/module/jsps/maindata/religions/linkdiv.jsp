<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divLinkLookup">
    <t:panelGrid align="center">
        <t:outputText forceId="true" id="titleLinkId" styleClass="TitlePage" value="#{resourcesBundle.link_religion_type}"/>
    </t:panelGrid>
    <t:outputText forceId="true" id="success" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="alreadyLinked" value="#{resourcesBundle.FailureInLink}" styleClass="errMsg"
                  rendered="#{pageBeanName.alreadyLinked}"/>
    <t:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0"
                 rendered="#{pageBeanName.showLinkDiv}">
        <t:outputText forceId="true" id="typesId" value="#{resourcesBundle.types}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu id="genderListID" forceId="true" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.selectedGender}" >
                <f:selectItem itemValue=""
                              itemLabel="#{resourcesBundle.select_one_item}"/>
                <t:selectItems value="#{pageBeanName.genderList}" var="genderList" itemValue="#{genderList.code.key}"
                               itemLabel="#{genderList.name}"/>
            </t:selectOneMenu>
              <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="genderListID" display="dynamic" errorMessage="#{resourcesBundle.select_one_filed}"  />
        </t:panelGroup>
        <t:outputText forceId="true" id="namesID" value="#{resourcesBundle.names}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="genRegNameId" value="#{pageBeanName.linkDTO.genregName}"
                         styleClass="textboxlarge" />
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="genRegNameId" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" />
        </t:panelGroup>
    </t:panelGrid>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonID" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.linkGenRel}" onclick="return validatemyForm();"/>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="SaveNewButtonId" styleClass="cssButtonSmall"
                             onclick="validateSaveAndNewClientValidator(); " value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.linkAndNew}" reRender="divLinkLookup"
                            oncomplete="setFocusOnlyOnElement('genderListID');"/>
                           
        </t:panelGroup>
       <t:panelGroup>
            <t:commandButton forceId="true" id="backButton_LinkID" onclick="backJsFunctionAdd(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="settingFoucsAtLinkDiv();"/>
            <a4j:jsFunction name="backJsFunctionAdd" action="#{pageBeanName.cancelLink}" oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);" reRender="divLinkLookup,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showLinkDiv}"/>
<t:saveState value="#{pageBeanName.genderList}"/>
<t:saveState value="#{pageBeanName.selectedGender}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>




