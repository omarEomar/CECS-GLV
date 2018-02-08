<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>


<t:panelGrid  onkeypress="FireButtonClickOldStyle(event,'searchButton')" columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" border="0" forceId="true" id="mainDataPanel">
    
    <t:outputLabel value="#{resourcesBundle.payment_type}" styleClass="lable01" />
    <t:selectOneMenu forceId="true" id="paymentTypeList" styleClass="DropdownboxLarge"
                     value="#{pageBeanName.pageDTO.payTypeCode}" converter="javax.faces.Long">
        <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
        <t:selectItems value="#{pageBeanName.paymentList}" itemLabel="#{type.name}" itemValue="#{type.code.paytypeCode}"
                       var="type"/>
          <a4j:support  event="onchange" actionListener="#{pageBeanName.changePaymentType}" reRender="mainDataPanel"/>              
                       
    </t:selectOneMenu>
    <t:outputLabel value="#{resourcesBundle.sheet_no}" styleClass="lable01"/>
    <t:inputText id="sheetNo" 
                 value="#{pageBeanName.pageDTO.sheetNo}"
                 styleClass="mastertextboxlarge"
                 maxlength="19"
                 onkeypress="enabelIntegerOnly(this); FireButtonClickOldStyle(event, 'searchButton')"
                 onkeyup="enabelIntegerOnly(this);"
                 converter="javax.faces.Long"/>
    <h:outputText value="#{resourcesBundle.month}" styleClass="lable01"/>
    <t:panelGroup id="month_panel" forceId="true">
        <t:selectOneMenu forceId="true" id="monthMenu" styleClass="DropdownboxLarge"
                         value="#{pageBeanName.pageDTO.month}">
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                           var="months"/>
        </t:selectOneMenu>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:outputText id="monthErrMsg" forceId="true" value="#{resourcesBundle.selectItem}" style="display:none;"
                      styleClass="error"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.year}" styleClass="lable01"/>
    <t:panelGroup id="year_panel" forceId="true">
        <t:selectOneMenu forceId="true" id="yearMenu" styleClass="DropdownboxLarge" value="#{pageBeanName.pageDTO.year}"
                         converter="javax.faces.Long">
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems value="#{pageBeanName.years}" itemLabel="#{year.code.key}" itemValue="#{year.code.yearCode}"
                           var="year"/>
        </t:selectOneMenu>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:outputText id="yearErrMsg" forceId="true" value="#{resourcesBundle.selectItem}" style="display:none;"
                      styleClass="error"/>
    </t:panelGroup>
    
    <t:panelGroup id="decGroup" forceId="true" rendered="#{pageBeanName.pageDTO.payTypeCode==pageBeanName.multiMeritFlag}">
        <h:outputText value="#{resourcesBundle.dec_no}" styleClass="lable01"  />
    </t:panelGroup>
    <t:panelGroup id="decGroup2" forceId="true" rendered="#{pageBeanName.pageDTO.payTypeCode==pageBeanName.multiMeritFlag}">
        <t:inputText id="decisionNoId" styleClass="mastertextboxlarge"  value="#{pageBeanName.pageDTO.decisionNo}"   /> 
    </t:panelGroup>   
    
</t:panelGrid>
<%-- --%>
<t:panelGroup style="display:block;text-align:center;">
    <t:commandButton forceId="true" id="searchButton" styleClass="cssButtonSmall" onclick="return validateSearch();"
                     value="#{globalResources.view_menu}" action="#{pageBeanName.search}"/>
</t:panelGroup>
<script type="text/javascript">
  function validateSearch() {
      var month = document.getElementById("monthMenu");
      var year = document.getElementById("yearMenu");
      var yearErrMsg = document.getElementById("yearErrMsg");
      var monthErrMsg = document.getElementById("monthErrMsg");

      var valid = true;
      if (month.value != null && month.value != '' && month.value != '-100') {

          if (year.value == null || year.value == '' || year.value == '-100') {
              yearErrMsg.style.display = "block";
              valid = false;
          }
          else {
              yearErrMsg.style.display = "none";
          }
      }
      
       if (year.value != null && year.value != '' && year.value != '-100') {

          if (month.value == null || month.value == '' || month.value == '-100') {
              monthErrMsg.style.display = "block";
              valid = false;
          }
          else {
              monthErrMsg.style.display = "none";
          }
      }
      return valid;

  }
</script>
