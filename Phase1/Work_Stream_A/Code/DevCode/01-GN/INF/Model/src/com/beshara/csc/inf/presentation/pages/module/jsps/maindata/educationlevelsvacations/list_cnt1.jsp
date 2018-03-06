<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGrid columns="4" border="0" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
    <h:outputText value="#{resourcesBundle.category}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText forceId="true" id="employees_category_id" styleClass="textboxsmall"
                     value="#{pageBeanName.categoryID}"
                     onkeypress="filterationInputOnKeyPress(event,this,'employees_category','categoryErrMSGWrongCode',Category_Id_fun)"
                     onkeyup="enabelFloatOnly(this);"
                     onblur="filterationInputOnBlur(event,this,'employees_category','categoryErrMSGWrongCode',Category_Id_fun);"
                     disabled="#{pageBeanName.displayButtonDisabled}">
            <a4j:jsFunction name="Category_Id_fun" action="#{pageBeanName.fillMinistries}"
                            reRender="employees_ministries,employees_ministries_id,categoryErrMSG,showResultPanelGroup"/>
        </t:inputText>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" id="employees_category" styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.categoryID}" converter="javax.faces.Long"
                         disabled="#{pageBeanName.displayButtonDisabled}"
                         onchange="filterationDropboxOnChange(event,this,'employees_category_id','categoryErrMSGWrongCode',Category_Id_fun);">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{pageBeanName.categories}" itemLabel="#{entry.name}" itemValue="#{entry.code}"
                           var="entry"/>
            <a4j:jsFunction name="Category_Id_fun" action="#{pageBeanName.fillMinistries}"
                            reRender="employees_ministries,employees_ministries_id,categoryErrMSG,showResultPanelGroup"/>
        </t:selectOneMenu>
        <t:panelGroup colspan="1" forceId="true" id="categoryErrMSG">
            <t:outputLabel id="categoryErrMSGWrongCode" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="mandatoryAsterisk" style="display:none"/>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.ministry}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText forceId="true" id="employees_ministries_id" styleClass="textboxsmall"
                     value="#{pageBeanName.ministryID}"
                     disabled="#{pageBeanName.categoryID==null || pageBeanName.displayButtonDisabled}"
                     onkeypress="filterationInputOnKeyPress(event,this,'employees_ministries','minisrtyErrMSGWrongCode',Ministry_Id_fun)"
                     onkeyup="enabelFloatOnly(this);"
                     onblur="filterationInputOnBlur(event,this,'employees_ministries','minisrtyErrMSGWrongCode',Ministry_Id_fun);">
            <a4j:jsFunction name="Ministry_Id_fun" reRender="ministryErrMSG,showResultPanelGroup"/>
        </t:inputText>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" id="employees_ministries" styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.ministryID}" converter="javax.faces.Long"
                         disabled="#{pageBeanName.categoryID==null || pageBeanName.displayButtonDisabled}"
                         onchange="filterationDropboxOnChange(event,this,'employees_ministries_id','minisrtyErrMSGWrongCode',Ministry_Id_fun);">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{pageBeanName.ministries}" itemLabel="#{entry.name}" itemValue="#{entry.code}"
                           var="entry"/>
            <a4j:jsFunction name="Ministry_Id_fun" reRender="ministryErrMSG,showResultPanelGroup"/>
        </t:selectOneMenu>
        <t:panelGroup colspan="1" forceId="true" id="ministryErrMSG">
            <t:outputLabel id="minisrtyErrMSGWrongCode" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="mandatoryAsterisk" style="display:none"/>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.periodType}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText forceId="true" id="periodType_id" styleClass="textboxsmall" value="#{pageBeanName.periodTypeID}"
                     disabled="#{pageBeanName.displayButtonDisabled}"
                     onkeypress="filterationInputOnKeyPress(event,this,'periodTypeList','periodTypeWrongCode',periodType_Id_fun)"
                     onkeyup="enabelFloatOnly(this);"
                     onblur="filterationInputOnBlur(event,this,'periodTypeList','periodTypeWrongCode',periodType_Id_fun);"></t:inputText>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" id="periodTypeList" styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.periodTypeID}" converter="javax.faces.Long"
                         onchange="filterationDropboxOnChange(event,this,'periodType_id','periodTypeWrongCode',periodType_Id_fun);"
                         disabled="#{pageBeanName.displayButtonDisabled}">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{pageBeanName.periodTypesList}" itemLabel="#{entry.spcprdtypeName}"
                           itemValue="#{entry.spcprdtypeCode}" var="entry"/>
            <a4j:jsFunction name="periodType_Id_fun" reRender="showResultPanelGroup"/>
        </t:selectOneMenu>
        <t:panelGroup colspan="1" forceId="true" id="periodTypeErrMSG">
            <t:outputLabel id="periodTypeWrongCode" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                           styleClass="mandatoryAsterisk" style="display:none"/>
        </t:panelGroup>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid align="center">
    <t:panelGroup style="text-align:center; display: block;" id="showResultPanelGroup">
        <t:commandButton id="submit2" onclick=" return validatemyForm();" forceId="true"
                         disabled="#{pageBeanName.displayButtonDisabled || pageBeanName.ministryID==null || pageBeanName.periodTypeID==null}"
                         action="#{pageBeanName.fillDataTable}" value="#{resourcesBundle.display}"
                         styleClass="cssButtonSmall"></t:commandButton>
        <t:commandButton id="reset" forceId="true" rendered="#{pageBeanName.displayButtonDisabled}"
                         action="#{pageBeanName.resetFilters}" value="#{globalResources.reSetButton}"
                         styleClass="cssButtonSmall"></t:commandButton>
    </t:panelGroup>
</t:panelGrid>
<script language="javascript" type="text/javascript">
  function showWorkCenterIntegrationDiv() {
      changeVisibilityDiv(window.blocker, window.integrationDiv1);

  }

  function changeworkCenterEvent(event) {
      if (event.keyCode == 13) {
          event.cancelBubble = true;
          event.returnValue = false;
          event.preventDefault();

          if (document.getElementById("workCenterId").value == null || document.getElementById("workCenterId").value == "") {
              document.getElementById("workCenterName").value = "";
          }
          else {
              changeworkCenter();
          }
      }
      return;
  }
</script>