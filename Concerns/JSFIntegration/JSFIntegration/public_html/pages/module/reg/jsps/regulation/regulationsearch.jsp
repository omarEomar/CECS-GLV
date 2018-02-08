<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<tiles:importAttribute scope="request"/>

<t:saveState value="#{regulationListBean.searchQuery}"/>

<t:saveState value="#{regulationListBean.typesList}" />

<t:panelGroup id="searchArea" forceId="true">
    <t:panelGrid columns="4" width="95%" cellpadding="3" rowClasses="row01,row02"  cellspacing="0" onkeypress="FireButtonClick('regulations_searchDiv_btn');">
        
        <t:outputLabel value="#{regResources.regulation_References_reg_no}"  styleClass="divtext"/>
        <t:inputText forceId="true" styleClass="textbox" id="reg_number_id" maxlength="10" value="#{regulationListBean.regulationSearchDTO.number}" onblur="setFocusOnlyOnElement('TypeSearch');" onkeyup="disableCharacters(this)" converter="javax.faces.Long"/>
        
        <t:outputLabel value="#{regResources.reg_type}"  styleClass="divtext"/>
        <t:selectOneMenu forceId="true" id="TypeSearch" value="#{regulationListBean.regulationSearchDTO.regulationType}"  styleClass="textboxmedium" >
            <f:selectItem itemLabel="#{regResources.select}" itemValue="" />
            <t:selectItems var="type" value="#{regulationListBean.typesList}" itemLabel="#{type.name}" itemValue="#{type.code.regtypeCode}"/>
        </t:selectOneMenu>
        
        <t:outputLabel value="#{regResources.reg_year}"  styleClass="divtext"/>
        <t:selectOneMenu id="YesrSearch" value="#{regulationListBean.regulationSearchDTO.regulationYear}"  styleClass="textbox" >
            <f:selectItem itemLabel="#{regResources.select_year}" itemValue="" />
            <t:selectItems var="year" value="#{regulationListBean.yearsList}" itemLabel="#{year.code.key}" itemValue="#{year.code.yearCode}"/>
        </t:selectOneMenu>
        
        <t:outputLabel value="#{regResources.decision_maker}"  styleClass="divtext"/>
        <t:selectOneMenu id="DecisionMakerSearch" value="#{regulationListBean.regulationSearchDTO.decisionMakerType}"  styleClass="textboxmedium" >
            <f:selectItem itemLabel="#{regResources.select}" itemValue="" />
            <t:selectItems var="decisionMaker" value="#{regulationListBean.decisionMakersList}" itemLabel="#{decisionMaker.name}" itemValue="#{decisionMaker.code.decmkrtypeCode}"/>
        </t:selectOneMenu>
        
        <t:outputLabel value="#{regResources.status}"  styleClass="divtext"/>
        <t:selectOneMenu id="StatusSearch" value="#{regulationListBean.regulationSearchDTO.regulationStatus}"  styleClass="textbox" >
            <f:selectItem itemLabel="#{regResources.select}" itemValue="" />
            <t:selectItems var="status" value="#{regulationListBean.statusesList}" itemLabel="#{status.name}" itemValue="#{status.code.regstatusCode}"/>
        </t:selectOneMenu>
        
         <t:outputLabel value="#{regResources.regulation_level}"  styleClass="divtext"/>
        <t:selectOneMenu id="ScopeSearch" value="#{regulationListBean.regulationSearchDTO.regulationScopes}"  styleClass="textboxmedium" >
            <f:selectItem itemLabel="#{regResources.select}" itemValue="" />
            <t:selectItems var="scope" value="#{regulationListBean.scopesList}" itemLabel="#{scope.name}" itemValue="#{scope.code.regscopeCode}"/>
        </t:selectOneMenu>
        
        <t:outputLabel value="#{regResources.regulation_date_from}"  styleClass="divtext"/>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                    popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                    forceId="true"
                    id="search_regDateFrom" 
                    size="20"
                    maxlength="#{pageBeanName.calendarTextLength}"
                    currentDayCellClass="currentDayCell"
                    value="#{regulationListBean.regulationSearchDTO.dateFrom}"
                    renderAsPopup="true"
                    align="#{globalResources.align}"
                    popupLeft="#{shared_util.calendarpopupLeft}"
                    renderPopupButtonAsImage="true">
                
                <f:converter converterId="TimeStampConverter"/>
            
            </t:inputCalendar>

            <f:verbatim><br/></f:verbatim>
            
            <c2:dateFormatValidator componentToValidate="search_regDateFrom" 
                display="dynamic" 
                errorMessage="#{globalResources.messageErrorForAdding}"
                highlight="false" 
                uniqueId="search_regDateFrom_divID"/>
            
        </t:panelGroup>
        
        <t:outputLabel value="#{regResources.regulation_date_to}"  styleClass="divtext"/>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                    popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                    forceId="true"
                    id="search_regDateTo" 
                    size="20"
                    maxlength="#{pageBeanName.calendarTextLength}"
                    currentDayCellClass="currentDayCell"
                    value="#{regulationListBean.regulationSearchDTO.dateTo}"
                    renderAsPopup="true"
                    align="#{globalResources.align}"
                    popupLeft="#{shared_util.calendarpopupLeft}"
                    renderPopupButtonAsImage="true">
            
                <f:converter converterId="TimeStampConverter"/>
            
            </t:inputCalendar>
            
            <f:verbatim><br/></f:verbatim>
            
            <c2:dateFormatValidator componentToValidate="search_regDateTo" 
                display="dynamic" 
                errorMessage="#{globalResources.messageErrorForAdding}"
                highlight="false" 
                uniqueId="search_regDateTo_divID"/>
            
            <c2:compareDateValidator componentToValidate="search_regDateFrom" 
                componentToCompare="search_regDateTo" 
                operator="before" display="dynamic" highlight="false"
                errorMessage="#{regResources.dec_from_to_date}" />
            
        </t:panelGroup>
        
       
        
       
        <t:outputLabel value="#{regResources.regulation_References_reg_desc}"  styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:inputText styleClass="regTiTleTextBoxInSearch" maxlength="400" value="#{regulationListBean.regulationSearchDTO.title}"/>
        </t:panelGroup>

    </t:panelGrid>
    
    <f:verbatim><br/></f:verbatim>
    
    <%-- *********************** Start Buttons ****************************  --%>
    
    <t:panelGrid id="buttonsPanel" columns="3" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}"  action="#{regulationListBean.search}" onclick="return validatemyForm();" styleClass="cssButtonSmall" forceId="true" id="regulations_searchDiv_btn"/>
        <h:panelGroup>
            <t:commandButton forceId="true" id="customSearchBackBtn" onblur="setFocusAtMySearchDiv();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{regulationListBean.back}" oncomplete="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.divSearch,null,null); foucsAddbuttonOnList();" reRender="divSearch"/>
        </h:panelGroup>

    </t:panelGrid>
    
    <%-- ************************* End Buttons ****************************  --%>
</t:panelGroup>
