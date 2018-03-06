<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    

    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
    
    <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>                     
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>                     
        
            <h:outputText value="#{resourcesBundle.currency_name}" styleClass="lable01" />
            <h:panelGroup>
                <t:inputText id="currNameEdit" value="#{pageBeanName.selectedPageDTO.currencyName}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="currNameEdit" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{currenciesBean.pageMode==2}"/>
            </h:panelGroup>         
            
             <t:outputText value="#{resourcesBundle.currency_abrv}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="currAbrvEdit" value="#{pageBeanName.selectedPageDTO.currencyAbrv1}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" onkeypress="validateCurAbrevEdit();" onkeyup=="validateCurAbrevEdit();" />
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="currAbrvEdit" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{currenciesBean.pageMode==2}"/>
             </t:panelGroup>  
             
            <t:outputText value="#{resourcesBundle.currency_abrv2}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="currAbrvEdit2" value="#{pageBeanName.selectedPageDTO.currencyAbrv2}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" onkeypress="validateCurAbrev2Edit();" onkeyup=="validateCurAbrev2Edit();"/>
             </t:panelGroup>  
             
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validatemyForm();"/>
            <t:panelGroup>
            <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('currNameEdit');" />
                <a4j:jsFunction name="cancelEditJs" action="#{pageBeanName.cancelEdit}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'currNameEdit','myForm:errorEdit');" reRender="scriptPanelGroup"/>
            </t:panelGroup>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>

    <script type="text/javascript">
      function validateCurAbrevEdit() {
          var abrev = document.getElementById("currAbrvEdit");
          var abrevValue = abrev.value;
          //to make abrevation text accept 4 digit just uncomment this code and comment for rows down
          //                    if(abrevValue.length >= 5){
          //                        abrev.value = abrevValue.slice(0,4);
          //                        return;
          //                    }
          if (abrevValue.length >= 4) {
              abrev.value = abrevValue.slice(0, 3);
              return;
          }
      }

      function validateCurAbrev2Edit() {
          var abrev = document.getElementById("currAbrvEdit2");
          var abrevValue = abrev.value;
          if (abrevValue.length >= 2) {
              abrev.value = abrevValue.slice(0, 1);
              return;
          }
      }
    </script>
