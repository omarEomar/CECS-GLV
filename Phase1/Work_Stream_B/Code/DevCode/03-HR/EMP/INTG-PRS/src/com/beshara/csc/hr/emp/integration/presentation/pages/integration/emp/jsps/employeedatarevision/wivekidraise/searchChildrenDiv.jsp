<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
    <t:saveState value="#{raiseKidsBean.searchQuery}"/>
    <t:saveState value="#{raiseKidsBean.searchTyp}"/>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
                   <%-- by css task adding css attrbuite to search comp--%>

    <%--t:panelGrid id="messagePanel" columns="1" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="msg"/>
    </t:panelGrid--%>
    <t:panelGrid id="messagePanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage2" value=" " styleClass="errMsgNoHeight"/>
    </t:panelGrid>
    
    <t:panelGrid id="searchChildren_radioPanel"  columns="1" dir="#{shared_util.pageDirection}"  align="center">
        <t:selectOneRadio forceId="true" id="searchChildrenRadioBtn" value="#{raiseKidsBean.searchTyp}" 
                styleClass="divtext" onblur="document.getElementById('searchChildrenSearchString').focus();" >
            <f:selectItem itemLabel="#{globalResources.globalCivilId}" itemValue="0"/>
            <f:selectItem itemLabel="#{globalResources.globalName}" itemValue="1"/>
        </t:selectOneRadio>
        <%--onblur="settingFoucsAtLovDiv();"--%>
    </t:panelGrid>
    
    <t:panelGrid id="searchChildrenTextPanel" columns="1" dir="#{shared_util.pageDirection}" align="center">
        <t:inputText forceId="true" id="searchChildrenSearchString" value="#{raiseKidsBean.searchQuery}" 
            styleClass="textboxmedium" onkeypress="FireButtonClick('searchChildren_search_btn');"/>
    </t:panelGrid>


    <t:panelGrid id="searchChildrenButtonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}" forceId="true" id="searchChildren_search_btn" 
        onclick="return ( checkRadio_CivilId2('searchChildrenRadioBtn','searchChildrenSearchString','#{globalResources.civil_no_not_less_than_12}' , '#{globalResources.missingField}','#{globalResources.ValidateNumbers}') );" action="#{pageBeanName.search}" styleClass="cssButtonSmall"/><%--onclick="return checkInput();"--%>
      
        <t:commandButton  forceId="true" id="customSearchBackBtn"  value="#{globalResources.back}" 
            onclick="hideDiv();" styleClass="cssButtonSmall"  action="#{pageBeanName.backSearchDiv}"/>
    </t:panelGrid>
<f:verbatim> &nbsp &nbsp &nbsp </f:verbatim>
    
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />                   
