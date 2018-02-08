<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" value="#{detailBeanName.currentDisplayDetails}"
             rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
             rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');" footerClass="grid-footer"
             styleClass="grid-footer" headerClass="standardTable_Header"
             rowClasses="standardTable_Row1,standardTable_Row2"
             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true">
    <t:column id="check_column" width="5%" rendered="#{pageBeanName.maintainMode!=0}">
        <f:facet name="header">
            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                             oncomplete="setAll('checkAll', 'chk2');" reRender="divDeleteAlert,OperationBar"/>
            </t:selectBooleanCheckbox>
        </f:facet>
        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">
            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                         oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
        </t:selectBooleanCheckbox>
    </t:column>
    <%-- Emp--%>
    <t:column id="civilid_column" width="20%" rendered="#{detailBeanName.isEmployee}">
        <f:facet name="header">
            <t:commandLink id="sort_employeesDTO-realCivilId" forceId="true" styleClass="headerLink"
                           value="#{regResources.civil_id}" actionListener="#{detailBeanName.sortDataTable}">
                <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='employeesDTO-realCivilId') ? '/app/media/images/ascending-arrow.gif' :''}"
                                border="0"/>
                <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='employeesDTO-realCivilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                border="0"/>
            </t:commandLink>
        </f:facet>
        <h:outputText id="content_civilid" value="#{list.employeesDTO.realCivilId}"/>
    </t:column>
    <t:column id="name_column" width="75%" rendered="#{detailBeanName.isEmployee}">
        <f:facet name="header">
            <t:commandLink id="sort_employeesDTO-citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink"
                           value="#{regResources.decision_employees_emp_name}"
                           actionListener="#{detailBeanName.sortDataTable}">
                <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='employeesDTO-citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                border="0"/>
                <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='employeesDTO-citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                border="0"/>
            </t:commandLink>
        </f:facet>
        <h:outputText id="content_name" value="#{list.employeesDTO.citizensResidentsDTO.fullName}"/>
    </t:column>
    <%-- Inf--%>
    <t:column id="civilid_column_Inf" width="20%" rendered="#{!detailBeanName.isEmployee}">
        <f:facet name="header">
            <t:commandLink id="sort_kwtCitizensResidentsDTO-code-civilId" forceId="true" styleClass="headerLink"
                           value="#{regResources.civil_id}" actionListener="#{detailBeanName.sortDataTable}">
                <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='kwtCitizensResidentsDTO-code-civilId') ? '/app/media/images/ascending-arrow.gif' :''}"
                                border="0"/>
                <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='kwtCitizensResidentsDTO-code-civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                border="0"/>
            </t:commandLink>
        </f:facet>
        <h:outputText id="content_civilid_Inf" value="#{list.kwtCitizensResidentsDTO.code.civilId}"/>
    </t:column>
    <t:column id="name_column_Inf" width="75%" rendered="#{!detailBeanName.isEmployee}">
        <f:facet name="header">
            <t:commandLink id="sort_kwtCitizensResidentsDTO-fullName" forceId="true" styleClass="headerLink"
                           value="#{regResources.decision_employees_emp_name}"
                           actionListener="#{detailBeanName.sortDataTable}">
                <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='kwtCitizensResidentsDTO-citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                border="0"/>
                <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='kwtCitizensResidentsDTO-citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                border="0"/>
            </t:commandLink>
        </f:facet>
        <h:outputText id="content_name_Inf" value="#{list.kwtCitizensResidentsDTO.fullName}"/>
    </t:column>
</t:dataTable>
<h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
<%-- h:commandButton action="selectdecisionemployee" styleClass="cssButtonSmall" value="#{globalResources.SearchButton}"/--%>
<t:panelGrid columns="1" align="center">
    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}"
                  rendered="#{ detailBeanName.currentListSize == 0 }"/>
    <h:outputText styleClass="regErrMsg" value="#{regResources.must_add_employee}"
                  rendered="#{ detailBeanName.showErrMsg }"/>
</t:panelGrid>
<t:saveState value="#{detailBeanName.fullColumnName}"/>
<t:saveState value="#{detailBeanName.sortAscending}"/>