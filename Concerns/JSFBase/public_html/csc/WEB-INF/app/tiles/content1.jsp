<%--@ page import="com.beshara.csc.nl.qul.presentation.backingbean.standardsearch.StandardSearchBean, javax.faces.context.FacesContext" --%>
<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>

<tiles:importAttribute scope="request"/>

    <%
//        StandardSearchBean standardSearchBean = (StandardSearchBean) FacesContext.getCurrentInstance().getApplication().createValueBinding("#{StandardSearchBean}").getValue(FacesContext.getCurrentInstance());
//        String actionString = (String) request.getAttribute("actionString");
//        standardSearchBean.setSearchAction(actionString);
//        request.setAttribute("StandardSearchBean", standardSearchBean);
    %>
<%-- beshara:searchBox direction="#{shared_util.pageDirection}" searchAction="#{searchAction}" searchQuery="#{searchQuery}" searchType="#{searchType}" /--%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<beshara:searchBox direction="#{shared_util.pageDirection}" searchCodeLabel="#{searchCodeLabel}" searchNameLabel="#{searchNameLabel}"
                   searchAction="#{searchAction}" searchQuery="#{searchQuery}"
                   searchType="#{searchType}" tdStyle="divtext" buttonStyle="cssButtonSmall" textBoxStyle="textboxlarge" errorStyle="errMsg" />
<f:verbatim> &nbsp &nbsp &nbsp </f:verbatim>
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />                   
                   <%-- by css task adding css attrbuite to search comp--%>

<%--
<t:saveState value="#{StandardSearchBean.searchAction}"/>
    <t:panelGrid id="messagePanel" columns="1" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="msg"/>
    </t:panelGrid>
    
    <t:panelGrid id="radioPanel" columns="1" dir="#{shared_util.pageDirection}">
        <t:selectOneRadio forceId="true" id="radioBtn">
            <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
            <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
        </t:selectOneRadio>
    </t:panelGrid>
    
    <t:panelGrid id="textPanel" columns="1" dir="#{shared_util.pageDirection}">
        <t:inputText forceId="true" id="searchString" value="#{StandardSearchBean.searchString}"/>
    </t:panelGrid>
    
    <t:panelGrid id="buttonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}" onclick="return checkInput();" action="#{StandardSearchBean.search}"/>
        <t:commandButton value="#{globalResources.back}" onclick="hideDiv();return false;"/>
    </t:panelGrid>--%>
<script language="javascript" type="javascript/text">
    function hideDiv() {
      
        try{
            obj = document.getElementById('iFrame');
            obj.style.visibility = 'hidden';
        }catch(exception) {}
        // window.blocker.style.visibility="hidden";
        document.getElementById("divSearch").style.visibility="hidden";
        
        if(document.getElementById('errorMessage')!=null) 
            document.getElementById('errorMessage').innerHTML = '';
        
        var cancelSearchArr = document.getElementsByClassName("OB_cancleSearch");
        if (cancelSearchArr.length == 0){
        if(document.getElementById('searchString')!=null) 
            document.getElementById('searchString').value='';
        }
          
        window.parent.nav_btn.disabled=false;     
    }
</script>
