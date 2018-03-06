


<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid columns="1" cellspacing="0" border="0" cellpadding="3" width="100%" align="center"
             rowClasses="row01,row02">
    <t:panelGroup id="modulesListPnl" forceId="true">
        <f:verbatim>
            <center></center>
        </f:verbatim>
        <t:outputLabel value="#{resourcesBundle.Reasons_type}" styleClass="lable01"/>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" id="modulesListMenu" onmouseout="hideTip()"
                         onmouseover="displayTooltip(event,this)" styleClass="DropdownboxMedium"
                         value="#{pageBeanName.pageDTO.restypeCode}">
            <f:selectItem itemValue="#{pageBeanName.selectedModulesKeyDufalut}"
                          itemLabel="#{resourcesBundle.allTitle}"/>
            <t:selectItems value="#{pageBeanName.reasonType}" var="entry" itemValue="#{entry.restypeCode}"
                           itemLabel="#{entry.name}"/>
            <a4j:support event="onchange" oncomplete="foucsAddbuttonOnList();" action="#{pageBeanName.fillDataTable}"
                         reRender="dataT_data_panel,modulesListMenu,modulesListPnl,OperationBar,paging_panel,divSearch"/>
        </t:selectOneMenu>
        <f:verbatim></f:verbatim>
    </t:panelGroup>
</t:panelGrid>
<t:saveState value="#{pageBeanName.pageDTO}"/>
<t:saveState value="#{pageBeanName.pageDTO.restypeCode}"/>