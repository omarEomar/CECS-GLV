<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<tiles:importAttribute scope="request"/>

<t:saveState value="#{decisionListBean.searchQuery}"/>

<t:saveState value="#{decisionListBean.typesList}" />

<t:panelGroup id="searchArea" forceId="true" >
   
    <!-- ************************* Start advanced search ******************  -->
<t:panelGrid columns="2" align="center"  width="100%">    
 <t:panelGrid columns="4" align="center"  width="100%"  rowClasses="row01,row02" border="0" cellpadding="3" cellspacing="0" onkeypress="FireButtonClick('search_decision_btn');">
    
    <t:outputLabel value="#{regResources.dec_number}"  styleClass="lable01"/>
    <t:inputText  styleClass="textbox" maxlength="10" value="#{decisionListBean.decisionSearchDTO.number}" onkeyup="disableCharacters(this)" converter="javax.faces.Long"/>
    
    <t:outputLabel value="#{regResources.type}"  styleClass="lable01"/>

    <t:selectOneMenu id="regulationTypeID" value="#{decisionListBean.decisionSearchDTO.regulationType}"  styleClass="DropdownboxMedium2" forceId="true">
        <f:selectItem itemLabel="#{regResources.select_type}" itemValue="" />
        <t:selectItems var="type" value="#{decisionListBean.typesList}" itemLabel="#{type.name}" itemValue="#{type.code.regtypeCode}"/>
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.reg_year}"  styleClass="lable01" />

    <t:selectOneMenu id="YesrSearch" value="#{decisionListBean.decisionSearchDTO.regulationYear}"  styleClass="Dropdownbox" >
        <f:selectItem itemLabel="#{regResources.select_year}" itemValue="" />
        <t:selectItems var="year" value="#{decisionListBean.yearsList}" itemLabel="#{year.code.key}" itemValue="#{year.code.yearCode}"/>
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.dec_decision_maker}"  styleClass="lable01"/>

<t:selectOneMenu id="DecisionMakerSearch" value="#{decisionListBean.decisionSearchDTO.decisionMakerType}"  styleClass="DropdownboxMedium2" >
        <f:selectItem itemLabel="#{regResources.select_decision_maker}" itemValue="" />
        <t:selectItems var="decisionMaker" value="#{decisionListBean.decisionMakersList}" itemLabel="#{decisionMaker.name}" itemValue="#{decisionMaker.code.decmkrtypeCode}"/>
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.status}"  styleClass="lable01"/>

    <t:selectOneMenu id="StatusSearch" value="#{decisionListBean.decisionSearchDTO.decisionStatus}" styleClass="Dropdownbox" >
        <f:selectItem itemLabel="#{regResources.select_status}" itemValue="" />
        <f:selectItem itemLabel="#{regResources.decision_status_text_valid}" itemValue="#{decisionListBean.statusValidCode}"/>
        <f:selectItem itemLabel="#{regResources.decision_status_text_canceled}" itemValue="#{decisionListBean.statusCanceledCode}"/>
        <f:selectItem itemLabel="#{regResources.decision_status_text_cancel}" itemValue="#{decisionListBean.statusCancelDecisionCode}"/>                        
    </t:selectOneMenu>

    <t:outputLabel value="#{regResources.decision_subject}"  styleClass="lable01"/>
    <t:selectOneMenu id="SubjectSearch" value="#{decisionListBean.decisionSearchDTO.subjectCode}"  styleClass="DropdownboxMedium2" >
        <f:selectItem itemLabel="#{regResources.select_decision_subject}" itemValue="" />
        <t:selectItems var="subject" value="#{decisionListBean.subjectsList}" itemLabel="#{subject.name}" itemValue="#{subject.code.subjectCode}"/>
    </t:selectOneMenu>


<t:outputLabel value="#{regResources.decision_date_from}"  styleClass="lable01"/>

    <t:panelGroup>
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                forceId="true"
                id="search_regDateFrom_dec" 
                maxlength="10"
                currentDayCellClass="currentDayCell"
                value="#{decisionListBean.decisionSearchDTO.dateFrom}"
                renderAsPopup="true"
                align="#{globalResources.align}"
                popupLeft="#{shared_util.calendarpopupLeft}"
                renderPopupButtonAsImage="true">
            
            <f:converter converterId="TimeStampConverter"/>
        
        </t:inputCalendar>
        <f:verbatim> <br/> </f:verbatim>
        <c2:dateFormatValidator componentToValidate="search_regDateFrom_dec"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
    </t:panelGroup>

    <t:outputLabel value="#{regResources.regulation_date_to}"  styleClass="lable01"/>

    <t:panelGroup>
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"   popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                popupDateFormat="dd/MM/yyyy" styleClass="textbox"
                forceId="true"
                id="search_regDateTo_dec" 
                maxlength="10"
                currentDayCellClass="currentDayCell"
                value="#{decisionListBean.decisionSearchDTO.dateTo}"
                renderAsPopup="true"
                align="#{globalResources.align}"
                popupLeft="#{shared_util.calendarpopupLeft}"
                renderPopupButtonAsImage="true">
        
            <f:converter converterId="TimeStampConverter"/>
        
        </t:inputCalendar>
        <f:verbatim><br/></f:verbatim>
        <c2:dateFormatValidator componentToValidate="search_regDateTo_dec"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
        
        <c2:compareDateValidator componentToValidate="search_regDateFrom_dec" 
            componentToCompare="search_regDateTo_dec" 
            operator="before" display="dynamic" highlight="false"
            errorMessage="#{regResources.dec_from_to_date}" />
    </t:panelGroup>

   
    
    
    <t:outputLabel value="#{regResources.dec_title}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
            <t:inputText maxlength="400" value="#{decisionListBean.decisionSearchDTO.title}"  styleClass="textboxDecisionSearch2"/>
    </t:panelGroup>
    
  </t:panelGrid>
    <t:panelGroup style="width:5px"/>
  </t:panelGrid>
    <!-- ********************** End advanced search ***********************  -->
    
    <f:verbatim><br/></f:verbatim>
    
    <!-- *********************** Start Buttons ****************************  -->
    
    <t:panelGrid id="buttonsPanel" columns="3" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}"  action="#{decisionListBean.search}" onclick="return validatemyForm();" styleClass="cssButtonSmall" forceId="true" id="search_decision_btn"/>
        <t:commandButton value="#{globalResources.back}" onblur="setFocusAtMySearchDiv();" action="#{decisionListBean.back}" styleClass="cssButtonSmall" forceId="true" id="customSearchBackBtn"/>
    </t:panelGrid>
    
    <!-- ************************* End Buttons ****************************  -->
</t:panelGroup>
