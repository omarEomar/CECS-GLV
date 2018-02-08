<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>


<t:panelGrid columns="4" border="0" width="100%" cellpadding="3" cellspacing="0" rowClasses="row02,row01">
    <t:outputLabel value="#{resourcesBundle.sheetNo}" styleClass="lable01"/>
    <t:inputText  styleClass="textbox" value="#{pageBeanName.sheetNo}" disabled="true"/>
    <t:outputLabel value="#{resourcesBundle.empsNo_have_payedorder}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText styleClass="textbox"  value="#{pageBeanName.empsNoOfPayedOrder}" disabled="true"/>
        <h:outputText value="#{resourcesBundle.emp_label}" styleClass="lable01"/>
    </t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.prepartion_date}" styleClass="lable01"/>
    <t:inputText  styleClass="textbox" value="#{pageBeanName.prepareDate}" disabled="true"/>
    <t:outputLabel value="#{resourcesBundle.empsNo_have_account_no}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText styleClass="textbox" value="#{pageBeanName.empsHaveNoAccount}" disabled="true"/>
        <h:outputText value="#{resourcesBundle.emp_label}" styleClass="lable01"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="1" border="0" align="center">
    <t:panelGroup>
        <t:commandButton forceId="true" id="backButtonAddDiv"
                         onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    </t:panelGroup>
</t:panelGrid>