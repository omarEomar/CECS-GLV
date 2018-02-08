<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>



<t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
    
    <t:outputLabel id="sheet_Label" value="#{resourcesBundle.sheettype}" styleClass="lable01"/>
     <t:panelGroup colspan="3" > 
         <t:selectOneRadio forceId="true" id="sheettyperadio" value="#{pageBeanName.pageDTO.sheetType}" styleClass="divtext"  >
            <f:selectItem itemLabel="#{resourcesBundle.main_sheet_type}" itemValue="#{pageBeanName.mainSheet}"/>
            <f:selectItem itemLabel="#{resourcesBundle.minor_sheet_type}" itemValue="#{pageBeanName.minorSheet}"/>
         </t:selectOneRadio>
     </t:panelGroup>
    
    <t:outputLabel value="#{resourcesBundle.payment_type}" styleClass="lable01"/>
    <t:panelGroup colspan="3" > 
         <t:selectOneMenu forceId="true" id="paymentTypeList" styleClass="Dropdownbox" value="#{pageBeanName.pageDTO.paymentType}" converter="javax.faces.Long" >
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems value="#{pageBeanName.paymentList}" itemLabel="#{type.name}" itemValue="#{type.code.paytypeCode}"  var="type"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim><br/></f:verbatim>
          <c:compareValidator componentToValidate="paymentTypeList" componentToCompare="virtualvalueId" operator="not"
                            errorMessage="#{resourcesBundle.select_one_item}" highlight="false" display="dynamic"/>
    </t:panelGroup>
    
     <h:outputText value="#{resourcesBundle.month}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="monthMenu" styleClass="Dropdownbox" value="#{pageBeanName.pageDTO.month}" >
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}" var="months"/>
            <a4j:support actionListener="#{pageBeanName.fillReadyEmpNo}" event="onchange" reRender="noOfEmpsPanelGroup"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim ><br/></f:verbatim>            
        <c:requiredFieldValidator componentToValidate="monthMenu" display="dynamic" errorMessage="#{resourcesBundle.select_one_item}" highlight="false"/>
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.year}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="yearMenu" styleClass="Dropdownbox" value="#{pageBeanName.pageDTO.year}" converter="javax.faces.Long">
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.years}" itemLabel="#{year}" itemValue="#{year}" var="year"/>
             <a4j:support actionListener="#{pageBeanName.fillReadyEmpNo}" event="onchange" reRender="noOfEmpsPanelGroup"/>
        </t:selectOneMenu>
         <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim ><br/></f:verbatim>            
        <c:requiredFieldValidator componentToValidate="yearMenu" display="dynamic" errorMessage="#{resourcesBundle.select_one_item}" highlight="false"/>
    </t:panelGroup>
    
    
     <h:outputText value="#{resourcesBundle.payment_account_no}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="accountNoList" styleClass="Dropdownbox" value="#{pageBeanName.pageDTO.accountNo}" >
            <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems value="#{pageBeanName.accountNoList}" itemLabel="#{account.code.key}" itemValue="#{account.code.key}" var="account"/>
        </t:selectOneMenu>
         <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim><br/></f:verbatim>
          <c:compareValidator componentToValidate="accountNoList" componentToCompare="virtualvalueId" operator="not"
                            errorMessage="#{resourcesBundle.select_one_item}" highlight="false" display="dynamic"/>
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.total_availble_no_emp}" styleClass="lable01"/>
    <t:panelGroup  forceId="true" id="noOfEmpsPanelGroup">
         <t:inputText forceId="true" id="noOfEmps" styleClass="textbox" value="#{pageBeanName.pageDTO.noOfEmps}" disabled="true"/>   
         <h:outputText value="#{resourcesBundle.emp_label}" styleClass="lable01"/>
  </t:panelGroup>

</t:panelGrid>
<%-- --%>
    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
             <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonMedium"  onclick="return validatemyForm();" value="#{resourcesBundle.payment_order_btn}" action="#{pageBeanName.save}" />            
    </t:panelGrid> 

<t:saveState value="#{pageBeanName.pageDTO}"/>
<t:saveState value="#{pageBeanName.paymentList}"/>
<t:saveState value="#{pageBeanName.months}"/>
<t:saveState value="#{pageBeanName.years}"/>
<t:saveState value="#{pageBeanName.accountNoList}"/>


<t:saveState value="#{pageBeanName.sheetNo}"/>
<t:saveState value="#{pageBeanName.empsNoOfPayedOrder}"/>
<t:saveState value="#{pageBeanName.prepareDate}"/>
<t:saveState value="#{pageBeanName.empsHaveNoAccount}"/>
