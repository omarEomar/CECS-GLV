<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="cnt1PG">
    <t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
        <t:outputText value="#{resourcesBundle.hol_year_code}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <%--<t:inputText forceId="true" id="yearCodeCnt1Id" styleClass="textboxsmall"
                         value="#{pageBeanName.selectedYears}"
                         onkeypress="filterationInputOnKeyPress(event,this,'yearCodeListCnt1Id','errorCodeId',loadDataTable);"
                         onblur="filterationInputOnBlur(event,this,'yearCodeListCnt1Id','errorCodeId',loadDataTable);"
                         onkeyup="enabelIntegerOnly(this);"/>--%>
            <t:selectOneMenu id="yearCodeListCnt1Id" forceId="true" value="#{pageBeanName.selectedYears}"
                             styleClass="DropdownboxMedium" style=" margin-right: -87px;" onchange="loadDataTable();">
                <f:selectItem itemLabel="#{resourcesBundle.select_year}" itemValue=""/>
                <t:selectItems var="list" value="#{pageBeanName.yearsList}" itemLabel="#{list.code.key}"
                               itemValue="#{list.code.key}"/>
            </t:selectOneMenu>
            <a4j:jsFunction name="loadDataTable" action="#{pageBeanName.loadTable}" reRender="dataT_data_panel,OperationBar,paging_panel"/>
        <%--<t:panelGroup colspan="1" forceId="true" id="errorCodePanelId">
                <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="errMsg" style="display:none"/>
            </t:panelGroup>--%>
            </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
