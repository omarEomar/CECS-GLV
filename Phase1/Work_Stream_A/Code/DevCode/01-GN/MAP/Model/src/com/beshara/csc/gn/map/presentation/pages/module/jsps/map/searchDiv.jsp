<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:panelGrid columns="1" width="100%">
    <t:panelGrid id="messagePanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="errMsgNoHeight"/>
    </t:panelGrid>
    <t:selectOneRadio styleClass="divtext" onblur="setFocusAtMySearchText();" layout="spread" forceId="true" id="radioBtn" value="#{mappedDataBean.searchTypeLongVal}" converter="javax.faces.Long">
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{0}"/>
           <f:selectItem itemLabel="#{globalResources.SearchName}" itemValue="#{1}"/>
       </t:selectOneRadio>
    <t:panelGrid id="radioPanel" align="center" columns="2" dir="#{shared_util.pageDirection}" styleClass="divtext">
        <%--t:selectOneRadio id="searchType_Radio" value="#{pageBeanName.searchType}" styleClass="radio1" converter="javax.faces.Integer">
            <f:selectItem itemLabel="#{resourcesBundle.civilid}" itemValue="0" />
            <f:selectItem itemLabel="#{resourcesBundle.employeeName}" itemValue="1" />
        </t:selectOneRadio--%>
        
        <t:radio for="radioBtn" index="0"/>
        <t:radio for="radioBtn" index="1"/>
       
    </t:panelGrid>
    
    <t:panelGrid id="textPanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:inputText forceId="true" id="search_first_inputTxt" value="#{mappedDataBean.searchQuery}" styleClass="textboxlarge"/>
    </t:panelGrid>
    
    <t:panelGrid id="buttonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}" onclick="return validateSearchDiv('#{globalResources.missingField}','#{globalResources.SearchMsg}');" styleClass="cssButtonSmall" action="#{mappedDataBean.search}"/>
        <t:commandButton  forceId="true" id="customSearchBackBtn" onblur="setFocusAtMySearchDiv();"  value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);return false;"/>        
    </t:panelGrid>    

</t:panelGrid>
<f:verbatim> &nbsp &nbsp &nbsp </f:verbatim>
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />

<t:saveState value="#{mappedDataBean.searchTypeLongVal}"/>
<t:saveState value="#{mappedDataBean.searchQuery}"/>
