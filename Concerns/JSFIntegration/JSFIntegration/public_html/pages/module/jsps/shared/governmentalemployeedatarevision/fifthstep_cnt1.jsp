<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>



<t:panelGroup  forceId="true" style="width:99%" id="emp_query_panel_id">

<t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
    <t:inputText forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{detailBeanName.civilID}" disabled="true"/>
    
    <h:outputText value="#{resourcesBundle.the_name}" styleClass="divtext"/>
    <t:inputText forceId="true"  id="empName" styleClass="textboxmedium"  value="#{detailBeanName.empName}" disabled="true"/>
    
    <t:panelGroup colspan="4" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.financialInformation}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>
    
    
    <%-- Row 01 --%>    
    <h:outputText  value="#{resourcesBundle.budgetType}" />
    <t:inputText disabled="true" value="#{pageBeanName.employeesDTO.bgtTypesDTO.name}"  styleClass="textboxmedium"/>
    
    <h:outputText  value="#{resourcesBundle.hireDegree}" />   
    <t:inputText disabled="true" value="#{pageBeanName.degreeWhenHired}" styleClass="textboxmedium"/>
    
    <%-- Row 02 --%>    
    <h:outputText  value="#{resourcesBundle.assignmentCenter}" />
    <t:inputText disabled="true" value="#{pageBeanName.employeesDTO.workCenterDTO.name}"  styleClass="textboxmedium"/>
    
    <t:outputText value="#{resourcesBundle.nextRaiseDate}" />
    <t:inputText value="#{pageBeanName.employeesDTO.dateOfNextRaise}" converter="SqlDateConverter" styleClass="textbox" disabled="true"/>
    
    <%-- Row 03 --%>    
    <t:outputText value="#{resourcesBundle.insuranceFileNo}"/>
    <t:inputText value="#{pageBeanName.employeesDTO.socialInsurNo}" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.insuranceDate}"/>
    <t:inputText value="" styleClass="textbox" disabled="true"/> <%--########################--%>    
    
    <%-- Row 04 --%>    
    <t:outputText  value="#{resourcesBundle.merTotal}" />
    <t:inputText value="#{pageBeanName.totalMerits}" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.dedTotal}" />
    <t:inputText value="#{pageBeanName.totalDeductions}" styleClass="textbox" disabled="true"/>
    
    <%-- Row 05 --%>    
    <t:outputText  value="#{resourcesBundle.netSalary}" />
    <t:inputText value="#{pageBeanName.netSalary}" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.bankName}" />
    <t:inputText value="#{pageBeanName.employeesDTO.bankBrancheDTO.banksDTO.name}" styleClass="textboxmedium" disabled="true"/>
    
    <%-- Row 06--%>    
    <t:outputText  value="#{resourcesBundle.bankBranch}" />
    <t:inputText value="#{pageBeanName.employeesDTO.bankBrancheDTO.name}" styleClass="textboxmedium" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.accountNo}" />
    <t:inputText value="#{pageBeanName.employeesDTO.accountNo}" styleClass="textbox" disabled="true"/>

    
    
</t:panelGrid>
    <t:panelGroup colspan="4" style="width:100%; background-color:#ffffff;">
        <t:panelGrid align="center">
            <a4j:commandButton styleClass="cssButtonMedium" value="#{resourcesBundle.benifitsAndRewards}" action="#{pageBeanName.showBenefitsRewardsDiv}" reRender="salElementTypePnlGrp,customDiv1PnlGrp" oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);document.getElementById('salElementType').focus();"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
