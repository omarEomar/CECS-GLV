<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:saveState value="#{pageBeanName.finDecisionStatusList}"/>                
  <t:panelGrid columns="1" cellspacing="0" border="0" align="center"  cellpadding="3" width="100%" rowClasses="row01,row02">
      
        <t:panelGroup id="finDecListPnl" forceId="true">
             <f:verbatim> <center>       </f:verbatim>
            
            <t:outputLabel value="#{resourcesBundle.decStatus}" styleClass="lable01"/>
             <f:verbatim> &nbsp; &nbsp; &nbsp;       </f:verbatim>
              <!--t:selectOneMenu forceId="true" id="decListMenu"  styleClass="DropdownboxMedium" value="#{pageBeanName.selectedTypeKey}" disabled="#{pageBeanName.searchMode}"-->
            <t:selectOneMenu forceId="true" id="decListMenu" disabled="#{pageBeanName.searchMode}" styleClass="DropdownboxMedium" value="#{pageBeanName.selectedTypeKey}" >
                <%--<f:selectItem itemLabel="#{resourcesBundle.Select_All_listbox}" itemValue=""/>--%>
                <t:selectItems value="#{pageBeanName.finDecisionStatusList}" var="entry" itemValue="#{entry.code.regstatusCode}" itemLabel="#{entry.name}"/>
                <a4j:support event="onchange" oncomplete="foucsAddbuttonOnList();" action="#{pageBeanName.fillDataTable}" reRender="dataT_data_panel,OperationBar,paging_panel"/>
            </t:selectOneMenu>
        <f:verbatim> </center> </f:verbatim>
        </t:panelGroup>
        
    </t:panelGrid>