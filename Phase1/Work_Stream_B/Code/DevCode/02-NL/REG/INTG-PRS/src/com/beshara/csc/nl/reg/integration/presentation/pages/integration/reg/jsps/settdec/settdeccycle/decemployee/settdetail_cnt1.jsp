<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>


<t:panelGrid cellpadding="3" cellspacing="0" columns="4" width="100%" border="0" rowClasses="row02,row01" rendered="#{settlementDetailsBean.dto.statusFlag !=null && settlementDetailsBean.maintainMode != 2 }">
    <h:outputText value="#{intgResources.month}"
                  rendered="#{settlementDetailsBean.dto.statusFlag !=null && settlementDetailsBean.maintainMode != 2 }"/>
    <t:selectOneMenu forceId="true" id="monthCombo" styleClass="textboxmedium" style="margin-left: 10px;"
                     value="#{settlementDetailsBean.monthKey}" disabled="#{settlementDetailsBean.dto.statusFlag ==null}"
                     rendered="#{settlementDetailsBean.dto.statusFlag !=null && settlementDetailsBean.maintainMode != 2}">
        <t:selectItems value="#{settlementDetailsBean.monthsList}" var="month" itemValue="#{month.code.key}"
                       itemLabel="#{month.name}"/>
    </t:selectOneMenu>
    <h:outputText value="#{intgResources.year}"
                  rendered="#{settlementDetailsBean.dto.statusFlag !=null && settlementDetailsBean.maintainMode != 2}"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="yearCombo" styleClass="textboxmedium" style="margin: 0px 10px;"
                         value="#{settlementDetailsBean.yearsKey}"
                         disabled="#{settlementDetailsBean.dto.statusFlag ==null}"
                         rendered="#{settlementDetailsBean.dto.statusFlag !=null && settlementDetailsBean.maintainMode != 2}">
            <t:selectItems value="#{settlementDetailsBean.yearsList}" var="year" itemValue="#{year.code.key}"
                           itemLabel="#{year.code.key}"/>
        </t:selectOneMenu>
        <t:outputText styleClass="errMsg" forceId="true" id="emplyeeErrorMsgId"
                      value="#{intgResources.mustAddSettlement}" rendered="#{settlementDetailsBean.showErrMsg}"/>
        <t:outputText styleClass="errMsg" forceId="true" id="emplyeeErrorMsgJS"
                      value="#{intgResources.mustAddSettlement}" style="display:none"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid align="center" id="displayBtnPanel"
             style="#{settlementDetailsBean.dto.statusFlag !=null ? '' : 'display:block; text-align: center;'}">
    <t:commandButton type="button" id="display_btn_id" value="#{intgResources.show_details}" style="margin-right: 20px;"
                     styleClass="cssButtonSmall"
                     rendered="#{settlementDetailsBean.dto.statusFlag !=null &&  settlementDetailsBean.maintainMode !=2 }">
        <a4j:support event="onclick" action="#{settlementDetailsBean.viewTable}" oncomplete="showSettlementErrorMsg();"
                     reRender="benfitsList_data,benfitsList_panel,deductionsList_panel,deductionsList_data,benfits_paging_panel,deductions_paging_panel, display_btn_id,totalSalary,dedectionsTotalValue,benfitsTotalValue,totalSalary,totalSalaryGroup,paging_panel_grid,msgShow,errorPNGR"/>
    </t:commandButton>
</t:panelGrid>