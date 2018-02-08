<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<style>
        .tree-area-With-1-row-filter {
            height: 400px !important;
        }
    </style>
<t:panelGroup forceId="true" style="width:100%" id="emp_query_panel_id">
    <t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%" rowClasses="row01,row02" cellpadding="3"
                 cellspacing="0" forceId="true" id="cnt1Panel">
        <h:outputText value="#{resourcesBundle.kwt_citizen_code}" styleClass="lable01"/>
        <t:panelGroup>
            <t:panelGroup>
                <t:inputText onkeyup="disableCharacters(this);" disabled="#{workDataListBeanName.civilExist}"
                             onblur="document.getElementById('civil_exist_btn').focus();" maxlength="12" forceId="true"
                             id="CivilIdAdd" styleClass="textbox" value="#{workDataListBeanName.civilId}"
                             onkeypress="FireButtonClick('civil_exist_btn')"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <t:commandButton id="civil_exist_btn" forceId="true" onclick="return validatemyForm();"
                                 value="#{workDataListBeanName.civilId == null ?globalResources.Available : globalResources.reSetButton}"
                                 styleClass="cssButtonSmall" action="#{workDataListBeanName.init}"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic"
                                           errorMessage="#{globalResources.missingField}" highlight="false"/>
                <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"
                                               errorMessage="#{globalResources.civil_no_not_less_than_12}"
                                               highlight="false" display="dynamic"/>
            </t:panelGroup> 
            <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                          rendered="#{!workDataListBeanName.validCivilId}" styleClass="errMsg"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.kwt_citizen_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText value="#{workDataListBeanName.citizinFullName}" styleClass="textboxlarge" style="width: 475px;"
                         disabled="true"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  foucsFirstElementOnListPage();

  function foucsFirstElementOnListPage() {
      if (document.getElementById('CivilIdAdd') != null) {
          document.getElementById('CivilIdAdd').focus();
          document.getElementById('CivilIdAdd').focus();
      }
      else if (document.getElementById('resetButton_id') != null) {
          document.getElementById('resetButton_id').focus();
          document.getElementById('resetButton_id').focus();
      }

  }

  function resetMsgInAdd() {
      if (document.getElementById('invalCivilID') != null) {
          document.getElementById('invalCivilID').innerHTML = '';
      }
  }
</script>