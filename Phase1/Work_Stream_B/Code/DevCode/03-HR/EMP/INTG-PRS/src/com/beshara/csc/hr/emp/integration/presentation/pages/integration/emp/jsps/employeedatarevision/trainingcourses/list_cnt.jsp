<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup id="externalCoursesGroupList" forceId="true" style="background-color:#ffffff;" colspan="6">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
        <htm:tr>
            <htm:td width="9">
                <t:graphicImage value="/app/media/images/op_arrow.jpg" width="9" height="9" border="0"/>
            </htm:td>
            <htm:td style="background: none repeat scroll 0% 0% white;">
                <t:outputLabel value="#{resourcesBundle.traning_courses_title}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <t:graphicImage value="/app/media/images/seprator_group.jpg" width="100%" height="1" border="0"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGroup id="firstTablePG" forceId="true" styleClass="dataT-With-1-row-filter" style="margin-left: 7px;">
    <t:dataTable id="dataT_firstTable" var="list" value="#{pageBeanName.trainingCoursesTableLst}" preserveDataModel="false"
                 renderedIfEmpty="false" footerClass="grid-footer" sortable="false" styleClass="grid-footer"
                 headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2" width="100%"
                 align="center" rowIndexVar="index">
        <t:column id="plan_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.plan}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="plan_content" value="#{list.planCode}"/>
        </t:column>
        <t:column id="course_code_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.course_code}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="course_code_content" value="#{list.trncourseCode}"/>
        </t:column>
        <t:column id="missionName_column" sortable="false" width="40%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.missionName_external_course}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="missionName_content" value="#{list.trncourseName}"/>
        </t:column>
        <t:column id="from_date_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.from_date}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="from_date_content" value="#{list.fromDate}"/>
        </t:column>
        <t:column id="until_date_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.until_date}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="until_date_content" value="#{list.untilDate}"/>
        </t:column>
         <t:column id="program_type_column" sortable="false" width="20%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.program_type}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="program_type_content" value="#{list.courseTypeName}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{pageBeanName.trainingCoursesLstSize == 0}" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>