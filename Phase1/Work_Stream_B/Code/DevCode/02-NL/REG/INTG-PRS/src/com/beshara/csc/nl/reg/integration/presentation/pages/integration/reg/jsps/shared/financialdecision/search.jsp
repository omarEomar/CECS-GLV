<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:saveState value="#{pageBeanName.searchQuery}"/>
<t:saveState value="#{pageBeanName.typesList}"/>
<t:panelGroup id="searchArea" forceId="true">
    <!-- ************************* Start advanced search ******************  -->
    <t:panelGrid columns="2" align="center" width="100%">
        <t:panelGrid columns="4" align="center" width="100%" rowClasses="row01,row02" border="0" cellpadding="3"
                     cellspacing="0">
            <!-- search by REG_NUM -->
            <t:outputLabel value="#{regResources.dec_number}" styleClass="lable01"/>
            <t:inputText styleClass="textbox" style="width:170px" 
                         value="#{pageBeanName.decisionSearchDTO.regNum}"
                         onkeypress="FireButtonClick('search_decision_btn');"/>
            <!-- search By AutoNum -->
            <t:panelGroup colspan="2"
                          rendered="#{pageBeanName.selectedRegTypeKey==0 || pageBeanName.selectedRegTypeKey==3}"/>
            <t:outputLabel value="#{regResources.type}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText forceId="true" id="regulationTypeFilterationId" styleClass="textboxsmall"
                             style="width:35px; margin-left:4px;"
                             value="#{pageBeanName.decisionSearchDTO.regulationType}"
                             onkeypress=" filterationInputOnKeyPress(event,this,'regulationTypeID','errorCodeId_1',null,'YesrSearch');"
                             onblur=" filterationInputOnBlur(event,this,'regulationTypeID','errorCodeId_1',null,'YesrSearch');"
                             onkeyup="enabelIntegerOnly(this);"/>
                <t:selectOneMenu id="regulationTypeID" value="#{pageBeanName.decisionSearchDTO.regulationType}"
                                 styleClass="textbox" forceId="true"
                                 onchange="filterationDropboxOnChange(event,this,'regulationTypeFilterationId','errorCodeId_1',null);">
                    <f:selectItem itemLabel="#{regResources.select_type}" itemValue=""/>
                    <t:selectItems var="type" value="#{pageBeanName.typesList}" itemLabel="#{type.name}"
                                   itemValue="#{type.code.regtypeCode}"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:outputLabel id="errorCodeId_1" value="#{regResources.MessageForWrongCode}" forceId="true"
                               styleClass="errMsg" style="display:none; "/>
            </t:panelGroup>
            <t:outputLabel value="#{regResources.reg_year}" styleClass="lable01"/>
            <t:panelGroup>
                <t:selectOneMenu forceId="true" id="YesrSearch" value="#{pageBeanName.decisionSearchDTO.regulationYear}"
                                 styleClass="textbox" style="width:170px;">
                    <f:selectItem itemLabel="#{regResources.select_year}" itemValue=""/>
                    <t:selectItems var="year" value="#{pageBeanName.yearsList}" itemLabel="#{year.code.key}"
                                   itemValue="#{year.code.yearCode}"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:outputLabel id="errorCodeId_2" value="#{regResources.MessageForWrongCode}" forceId="true"
                               styleClass="errMsg" style="display:none; "/>
            </t:panelGroup>
            <t:outputLabel value="#{regResources.status}" styleClass="lable01"/>
            <t:panelGroup>
                <t:selectOneMenu forceId="true" id="decListMenu1" styleClass="DropdownboxMedium"
                                 value="#{pageBeanName.decisionSearchDTO.regStatusFlage}"  style="width:170px;">
                    <%--<f:selectItem itemLabel="#{resourcesBundle.Select_All_listbox}" itemValue=" "/>--%>
                    <t:selectItems value="#{pageBeanName.finDecisionStatusList2}" var="entry"
                                   itemValue="#{entry.code.regstatusCode}" itemLabel="#{entry.name}"/>
                    <%--<a4j:support event="onchange" oncomplete="foucsAddbuttonOnList();"
                                 action="#{pageBeanName.fillDataTable}"
                                 reRender="dataT_data_panel,OperationBar,paging_panel"/>--%>
                </t:selectOneMenu>
            </t:panelGroup>
            <t:outputLabel value="#{regResources.decision_date_from}" styleClass="lable01"/>
            <t:panelGroup styleClass="search_regDateFrom_dec">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 styleClass="textbox" style="width:170px;"
                                 forceId="true" id="search_regDateFrom_dec" maxlength="10"
                                 onkeypress="FireButtonClick('search_decision_btn');"
                                 currentDayCellClass="currentDayCell" value="#{pageBeanName.decisionSearchDTO.dateFrom}"
                                 renderAsPopup="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:dateFormatValidator componentToValidate="search_regDateFrom_dec" display="dynamic"
                                        errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
            </t:panelGroup>
            <t:outputLabel value="#{regResources.decision_date_to}" styleClass="lable01"/>
            <t:panelGroup styleClass="search_regDateTo_dec">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 styleClass="textbox" style="width:170px;"
                                 forceId="true" id="search_regDateTo_dec" maxlength="10"
                                 onkeypress="FireButtonClick('search_decision_btn');"
                                 currentDayCellClass="currentDayCell" value="#{pageBeanName.decisionSearchDTO.dateTo}"
                                 renderAsPopup="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:dateFormatValidator componentToValidate="search_regDateTo_dec" display="dynamic"
                                        errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
                <c2:compareDateValidator componentToValidate="search_regDateFrom_dec"
                                         componentToCompare="search_regDateTo_dec" operator="before" display="dynamic"
                                         highlight="false" errorMessage="#{regResources.dec_from_to_date}"/>
            </t:panelGroup>
            <t:outputLabel value="#{regResources.civil_id}" styleClass="lable01"/>
            <t:inputText styleClass="textbox" style="width:170px;" maxlength="12"
                         value="#{pageBeanName.decisionSearchDTO.personCivil}" onkeyup="disableCharacters(this)"
                         converter="javax.faces.Long"/>
        </t:panelGrid>
        <t:panelGroup style="width:5px"/>
    </t:panelGrid>
    <!-- ********************** End advanced search ***********************  -->
    <!-- *********************** Start Buttons ****************************  -->
    <t:panelGrid id="buttonsPanel" columns="3" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}" action="#{pageBeanName.search}"
                         onclick="return validatemyForm();" styleClass="cssButtonSmall" forceId="true"
                         id="search_decision_btn"/>
        <t:commandButton value="#{globalResources.back}" onblur="setFocusAtMySearchDiv();" action="#{pageBeanName.back}"
                         styleClass="cssButtonSmall" forceId="true" id="customSearchBackBtn"/>
    </t:panelGrid>
    <!-- ************************* End Buttons ****************************  -->
</t:panelGroup>