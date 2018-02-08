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
    <t:inputText forceId="true"  id="empName" styleClass="textboxlarge"  value="#{detailBeanName.empName}" disabled="true"/>

    <t:panelGroup colspan="4" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.jobHierarchyInformation}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>    
    <%-- Row 01 --%>
    <h:outputText  value="#{resourcesBundle.totalServiceYears}" styleClass="nowrap_txt"/>
    <t:panelGroup colspan="3">
        <t:inputText value="#{pageBeanName.totalServiceDays}" styleClass="textboxsmall" disabled="true"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <h:outputText value="#{resourcesBundle.day}"/>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <t:inputText value="#{pageBeanName.totalServiceMonths}" styleClass="textboxsmall" disabled="true"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <h:outputText value="#{resourcesBundle.month}"/>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <t:inputText value="#{pageBeanName.totalServiceYears}" styleClass="textboxsmall" disabled="true"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <h:outputText value="#{resourcesBundle.year}"/>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <h:outputText value="#{resourcesBundle.tillNow}"/>
    </t:panelGroup>
</t:panelGrid>
</t:panelGroup>
