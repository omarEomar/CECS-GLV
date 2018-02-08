<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>



<t:panelGroup  forceId="true" style="width:99%" id="emp_query_panel_id">

<t:panelGrid columnClasses="colu1,colu2" columns="5" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel1">
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
    <t:inputText forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{detailBeanName.civilID}" disabled="true"/>
    
    <t:panelGroup colspan="3">
    <h:outputText value="#{resourcesBundle.the_name}" styleClass="divtext"/>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
    <t:inputText forceId="true"  id="empName" styleClass="textboxlarge"  value="#{detailBeanName.empName}" disabled="true"/>
    </t:panelGroup>
  
    <t:panelGroup colspan="5" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.vacationsInformation}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>
    
    
    <%-- Row 01 --%>    
    <h:outputText value="#{resourcesBundle.lastPeriodicalVac}" />
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.from}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.periodicalVac.fromDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.to}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.periodicalVac.untilDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.period}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.periodicalVacPeriod}" styleClass="textboxsmall"/>
    </t:panelGroup>
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.periodicalVacBalance}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.periodicalVacBalance}" styleClass="textboxsmall"/>
    </t:panelGroup>
    <%-- Row 02 --%>    
    <h:outputText value="#{resourcesBundle.lastSickVac}" />
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.from}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.sickVac.fromDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.to}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.sickVac.untilDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup colspan="2">
        <h:outputText value="#{resourcesBundle.period}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.sickVacPeriod}" styleClass="textboxsmall"/>
    </t:panelGroup>
    
    
    <%-- Row 03 --%>    
    <h:outputText value="#{resourcesBundle.lastCasualVac}"/>
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.from}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.casualVac.fromDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup>
        <h:outputText value="#{resourcesBundle.to}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.casualVac.untilDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup colspan="2">
        <h:outputText value="#{resourcesBundle.period}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.casualVacPeriod}" styleClass="textboxsmall"/>
    </t:panelGroup>
    
    
    <%-- Row 04 --%>    
    <h:outputText value="#{resourcesBundle.hajVac}"/>
    <t:panelGroup rendered="#{pageBeanName.empHasHajVac}" colspan="4">
        <h:outputText value="#{resourcesBundle.from}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.hajVac.fromDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup rendered="#{pageBeanName.empHasHajVac}" colspan="4">
        <h:outputText value="#{resourcesBundle.to}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.hajVac.untilDate}" converter="SqlDateConverter" styleClass="textbox"/>
    </t:panelGroup>
    <t:panelGroup rendered="#{pageBeanName.empHasHajVac}"> colspan="4"
        <h:outputText value="#{resourcesBundle.period}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.hajVacPeriod}" styleClass="textboxsmall"/>
    </t:panelGroup>
    
    <t:panelGroup rendered="#{!pageBeanName.empHasHajVac}" colspan="4">
        <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{resourcesBundle.empNotHasHajVac}" styleClass="textbox"/>
    </t:panelGroup>
    
    <t:panelGroup colspan="5" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>
   
    
    <%-- Row 01 --%>    
    <h:outputText value="#{resourcesBundle.lastDelegation}"/>
    <t:panelGroup  colspan="4" >
        <h:outputText value="#{resourcesBundle.inDate}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.delegateMov.movingDate}" converter="SqlDateConverter" styleClass="textbox"/>
        
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        
        <h:outputText value="#{resourcesBundle.toMinistry}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.delegateMov.workCentersDTO.ministriesDTO.name}" styleClass="textbox_175"/>
        
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        
        <h:outputText value="#{resourcesBundle.workCenter}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.delegateMov.workCentersDTO.name}" styleClass="textbox_175"/>
    </t:panelGroup>
    
    <%-- Row 02 --%>    
    <h:outputText value="#{resourcesBundle.lastSeconding}"/>
    <t:panelGroup colspan="4">
        <h:outputText value="#{resourcesBundle.inDate}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.secondingMov.movingDate}" converter="SqlDateConverter" styleClass="textbox"/>
        
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        
        <h:outputText value="#{resourcesBundle.toMinistry}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.secondingMov.workCentersDTO.ministriesDTO.name}" styleClass="textbox_175"/>
        
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        
        <h:outputText value="#{resourcesBundle.workCenter}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText disabled="true" value="#{pageBeanName.secondingMov.workCentersDTO.name}" styleClass="textbox_175"/>
    </t:panelGroup>

</t:panelGrid>

</t:panelGroup>
