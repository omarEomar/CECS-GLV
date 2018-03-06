<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
                <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
              
<t:panelGroup forceId="true" id="divLinkLookupEdit">
   <t:panelGrid align="center">
        <t:outputText styleClass="TitlePage" value="#{resourcesBundle.link_religion_type_edit}"  />
      </t:panelGrid>
      
    <a4j:jsFunction name="loadGenRegNameFunction" 
            action="#{pageBeanName.loadgenRegNameEdit}" 
            reRender="genRegNameEditId"/>
    <t:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0" rendered="#{pageBeanName.showEditLinkDiv}">
          
        <t:outputText value="#{resourcesBundle.types}" styleClass="lable01"/> 
        <t:panelGroup id="genderListEditGroupID" forceId="true" >
           <t:selectOneMenu id="genderListEditID" forceId="true"  
                styleClass="DropdownboxMedium2" 
                onchange="genderChange();" 
                value="#{pageBeanName.selectedGenderEdit}">
                     <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select_one_item}" />
                     <t:selectItems value="#{pageBeanName.genderEditList}" var="genderEditItem" itemValue="#{genderEditItem.genderTypesDTO.code.key}" itemLabel="#{genderEditItem.genderTypesDTO.gentypeName}" />
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText forceId="true" id="listEditIDError" 
                        value="#{resourcesBundle.select_one_filed}" 
                        style="display:none; color:red;" 
                        styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
                    
        <t:outputText value="#{resourcesBundle.names}" styleClass="lable01"/>
        <t:panelGroup>
        <t:inputText forceId="true" id="genRegNameEditId" value="#{pageBeanName.linkDTO.genregName}" styleClass="textboxlarge" />
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText forceId="true" id="genRegNameEditIdError" 
                        value="#{globalResources.missingField}" 
                        style="display:none; color:red;" 
                        styleClass="mandatoryAsterisk"/>
        </t:panelGroup>                
    </t:panelGrid>
<f:verbatim><br/></f:verbatim>
    <t:panelGrid columns="2" border="0" align="center">
            <t:commandButton forceId="true" id="EditButtonID" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.saveEdit}" onclick="return validateEditDiv();" />
            <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonCustomDiv2" onclick="backEditLinkFunctionJSId(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="settingFoucsAtEditLinkDiv();"/>
            <a4j:jsFunction name="backEditLinkFunctionJSId" action="#{pageBeanName.cancelLink}" oncomplete="hideLookUpDiv(window.blocker,window.customDiv2);foucsAddbuttonOnList();" reRender="divLinkLookupEdit,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
        </t:panelGroup>
</t:panelGrid>

</t:panelGroup>
<t:saveState value="#{pageBeanName.showEditLinkDiv}"/>
<t:saveState value="#{pageBeanName.linkDTO}"/>
<t:saveState value="#{pageBeanName.selectedGenderEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>

<script type="text/javascript">

   function genderChange(){
        clearErrorMsg();
        if(document.getElementById('genderListEditID').value !=''){
            loadGenRegNameFunction();
        }else{
            document.getElementById('genRegNameEditId').value='';
        }
    }

  function clearErrorMsg() {
      document.getElementById("listEditIDError").style.display="none";
      document.getElementById("genRegNameEditIdError").style.display="none";
  }
  function validateEditDiv() {
  
      clearErrorMsg();
      var errorFound = false;
      var prev = true;
      if (document.getElementById("genderListEditID").value == null || document.getElementById("genderListEditID").value =="") {
            document.getElementById("listEditIDError").style.display="";
            errorFound = true;
            prev = false;
      }
      
      if (document.getElementById("genRegNameEditId").value == null || document.getElementById("genRegNameEditId").value =="") {
            document.getElementById("genRegNameEditIdError").style.display="";
            errorFound = true;
            prev = false;
      }
      if (errorFound) {
          return false;
      }
      return true;
  }
</script>