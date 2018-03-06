<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divLinkLookupEdit">
    <t:panelGrid align="center">
        <t:outputText styleClass="TitlePage" value="#{resourcesBundle.link_marital_type_edit}"/>
    </t:panelGrid>
    <a4j:jsFunction name="loadGenMarNameFunction" action="#{pageBeanName.loadgenMarNameEdit}"
                    reRender="genMarNameEditId"/>
    <t:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0"
                 rendered="#{pageBeanName.showEditLinkDiv}">
        <t:outputText value="#{resourcesBundle.types}" styleClass="lable01"/>
        <t:panelGroup id="genderListEditGroupID" forceId="true">
            <t:selectOneMenu id="genderListEditID" forceId="true" styleClass="DropdownboxMedium2"
                             onchange="genderChange();" value="#{pageBeanName.selectedGenderMarEdit}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select_one_item}"/>
                <t:selectItems value="#{pageBeanName.genderEditList}" var="genderEditItem"
                               itemValue="#{genderEditItem.genderTypesDTO.code.key}"
                               itemLabel="#{genderEditItem.genderTypesDTO.gentypeName}"/>
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText forceId="true" id="genderListEditIDError" value="#{globalResources.missingField}"
                          style="display:none; color:red;" styleClass="mandatoryAsterisk"/>
            <%-- <c:requiredFieldValidator componentToValidate="genderListEditID" display="dynamic"
                 errorMessage="#{globalResources.missingField}" active="#{pageBeanName.showEditLinkDiv}"/>--%>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.names}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="genMarNameEditId" value="#{pageBeanName.genderMaritalDTO.genmarName}"
                         styleClass="textboxlarge"/>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText forceId="true" id="genMarNameEditIdError" value="#{globalResources.missingField}"
                          style="display:none; color:red;" styleClass="mandatoryAsterisk"/>
            <%-- <c:requiredFieldValidator componentToValidate="genMarNameEditId" display="dynamic"
                 errorMessage="#{globalResources.missingField}" active="true"/>--%>
        </t:panelGroup>
    </t:panelGrid>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <t:panelGrid columns="2" border="0" align="center">
        <t:commandButton forceId="true" id="EditButtonID" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" onclick="return validateEditDiv();"
                         action="#{pageBeanName.saveEdit}"/>
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonCustomDiv2" onclick="backEditLinkJsFunction(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"
                             onblur="settingFoucsAtEditLinkDiv();" />
            <a4j:jsFunction name="backEditLinkJsFunction" action="#{pageBeanName.cancelLinkage}"
                            oncomplete="hideLookUpDiv(window.blocker,window.customDiv2);foucsAddbuttonOnList();"
                            reRender="divLinkLookupEdit,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function genderChange() {
      clearErrorMsg();
      if (document.getElementById('genderListEditID').value != '') {
          loadGenMarNameFunction();
      }
      else {
          document.getElementById('genMarNameEditId').value = '';
      }
  }

  function clearErrorMsg() {
      document.getElementById("genderListEditIDError").style.display = "none";
      document.getElementById("genMarNameEditIdError").style.display = "none";
  }

  function validateEditDiv() {
      clearErrorMsg();
      var errorFound = false;
      var prev = true;
      if (document.getElementById("genderListEditID").value == null || document.getElementById("genderListEditID").value == "") {
          document.getElementById("genderListEditIDError").style.display = "";
          errorFound = true;
          prev = false;
      }

      if (document.getElementById("genMarNameEditId").value == null || document.getElementById("genMarNameEditId").value == "") {
          document.getElementById("genMarNameEditIdError").style.display = "";
          errorFound = true;
          prev = false;
      }
      if (errorFound) {
          return false;
      }
      return true;
  }
</script>