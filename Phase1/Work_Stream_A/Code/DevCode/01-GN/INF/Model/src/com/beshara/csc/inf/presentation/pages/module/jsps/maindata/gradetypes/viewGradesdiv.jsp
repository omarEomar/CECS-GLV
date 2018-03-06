<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divViewLookup">
    <%-- <h:outputText id="errorPage" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{
         pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>--%>
    <%-- <h:outputText id="clientErrorMessagePage" styleClass="errMsg"/>--%>
    <t:panelGrid id="viewGradesPnlGrp" align="center" width="100%" cellpadding="3" cellspacing="0"
                 rowClasses="row01,row02" columns="2">
        <h:outputText value="#{resourcesBundle.grade_type_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="grade_num_name1"
                         value="#{pageBeanName.gradeName}" styleClass="textboxlarge" forceId="true" disabled="true"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="view_dataTable_panel" rendered="#{pageBeanName.numGradeListSize != 0  }"
                  style="display: block;max-height: 255px;min-height: 60px;overflow: auto;">
        <t:dataTable id="view_dataTable" var="list" value="#{pageBeanName.numGradeList}"
                     rows="#{shared_util.noOfTableRows}" rendered="#{pageBeanName.numGradeListSize != 0 }"
                     rowIndexVar="index" renderedIfEmpty="false" styleClass="grid-footer"
                     headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true">
            <t:column sortable="false">
                <f:facet name="header">
                    <t:commandLink id="garde_val" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.gradeValue}" onclick="return false;"></t:commandLink>
                </f:facet>
                <t:outputText id="garde_val_content" value="#{list.value}"/>
            </t:column>
            <t:column sortable="false">
                <f:facet name="header">
                    <t:commandLink id="percentage_val" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.percentageValue}" onclick="return false;"></t:commandLink>
                </f:facet>
                <t:outputText id="percentage_content" value="%#{list.percentageValue}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid columns="1" rendered="#{pageBeanName.numGradeListSize == 0  }" styleClass="msg msg_no_icon"
                 align="center">
        <t:outputText value="#{globalResources.global_noTableResults}"/>
    </t:panelGrid>
    <t:panelGrid columns="3" border="0" align="center">
        <t:panelGroup>
            <t:commandButton forceId="true" id="backButtonCustomDiv2" onclick="backJsFunctionAdd(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"
                             onblur="settingFoucsAtShowGradeDiv();"/>
            <a4j:jsFunction name="backJsFunctionAdd" action="#{pageBeanName.cancelLink}"
                            oncomplete="hideLookUpDiv(window.blocker,window.customDiv2);foucsAddbuttonOnList();"
                            reRender="divViewLookup,dataT_data_panel,paging_panel,OperationBar"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>