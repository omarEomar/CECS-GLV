<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<t:panelGrid columns="2" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" >
    <t:outputText value="#{regResources.tableName}" />
    <t:inputText value="#{regulationRecordsBean.designTablesDTO.name}" styleClass="textboxlarge" disabled="true"/>
</t:panelGrid>
