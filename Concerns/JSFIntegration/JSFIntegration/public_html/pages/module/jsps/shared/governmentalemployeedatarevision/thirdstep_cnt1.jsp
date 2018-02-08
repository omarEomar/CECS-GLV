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
    <t:inputText forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{detailBeanName.realCivilId}" disabled="true"/>
    
    <h:outputText value="#{resourcesBundle.the_name}" styleClass="divtext"/>
    <t:inputText forceId="true"  id="empName" styleClass="textboxmedium2"  value="#{detailBeanName.empName}" disabled="true"/>

    
    <t:panelGroup colspan="4" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.addressInformation}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>    
    <%-- Row 01 --%>    
    <h:outputText  value="#{resourcesBundle.governorate}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.kwMapDTO.parentObject.parentObject.name}" styleClass="textboxmedium2" disabled="true"/>
    
    <h:outputText  value="#{resourcesBundle.area}" />   
    <t:inputText  value="#{pageBeanName.kwtCitizensResidentsDTO.kwMapDTO.parentObject.name}" styleClass="textboxmedium2" disabled="true"/>
    
    <%-- Row 02 --%>    
    <h:outputText  value="#{resourcesBundle.part}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.kwMapDTO.name}" styleClass="textboxmedium2" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.street}" />
    <t:inputText  value="#{pageBeanName.streetName}" styleClass="textboxmedium2" disabled="true"/>
    
    <%-- Row 03 --%>    
    <t:outputText  value="#{resourcesBundle.qasemaNo}" />
    <t:inputText value="" styleClass="textboxmedium2" disabled="true"/><%-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX --%>
    
    <t:outputText  value="#{resourcesBundle.buildingNo}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.buildingNo}" styleClass="textboxmedium2" disabled="true"/>
    
    
    <%-- Row 04 --%>    
    <t:outputText  value="#{resourcesBundle.unitType}" />
    <t:inputText value="" styleClass="textboxmedium2" disabled="true"/><%-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX --%>
    
    <t:outputText  value="#{resourcesBundle.unitNo}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.flatNo}" styleClass="textboxmedium2" disabled="true"/>
    
    <%-- Row 05 --%>    
    <t:outputText  value="#{resourcesBundle.floor}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.floorNo}" styleClass="textboxmedium2" disabled="true"/>
    
    <t:panelGroup colspan="2"/>
    
    
    <t:panelGroup colspan="4" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.phonesInformation}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>    
    <%-- Row 01 --%>    
    <h:outputText  value="#{resourcesBundle.mobile}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.mobileNo}" styleClass="textbox" disabled="true"/>
    
    <h:outputText  value="#{resourcesBundle.phone}" />   
    <t:inputText  value="#{pageBeanName.kwtCitizensResidentsDTO.phonesNo}" styleClass="textbox" disabled="true"/>
    
    
    <t:panelGroup colspan="4" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.residenceInformation}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>    
    <%-- Row 01 --%>
    <h:outputText  value="#{resourcesBundle.passportNo}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.passportNo}" styleClass="textbox" disabled="true"/>
    
    <h:outputText  value="#{resourcesBundle.issuanceDate}" />   
    <t:inputText  value="#{pageBeanName.kwtCitizensResidentsDTO.passportIssueDate}" converter="TimeStampConverter" styleClass="textbox" disabled="true"/>
    
    <%-- Row 02 --%>
    <h:outputText  value="#{resourcesBundle.expirationDate}" />
    <t:inputText value="#{pageBeanName.kwtCitizensResidentsDTO.passportExpiredDate}" converter="TimeStampConverter" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.issuanceCountry}" />
    <t:inputText  value="#{pageBeanName.passportIssuanceCountry}" styleClass="textbox" disabled="true"/>
    
    <%-- Row 03 --%>
    <t:outputText  value="#{resourcesBundle.residenceDate}" />
    <t:inputText value="#{pageBeanName.citizensResidentsDataDTO.issueDate}" converter="SqlDateConverter" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.residencyNo}" />
    <t:inputText value="#{pageBeanName.citizensResidentsDataDTO.residentNo}" styleClass="textbox" disabled="true"/>
    
    
    <%-- Row 04 --%>
    <t:outputText  value="#{resourcesBundle.residencePeriod}" />
    <t:inputText value="" styleClass="textbox" disabled="true"/><%-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX --%>
    
    <t:outputText  value="#{resourcesBundle.residenceExpiration}" />
    <t:inputText value="#{pageBeanName.citizensResidentsDataDTO.expDate}" converter="SqlDateConverter" styleClass="textbox" disabled="true"/>
    
</t:panelGrid>
</t:panelGroup>
